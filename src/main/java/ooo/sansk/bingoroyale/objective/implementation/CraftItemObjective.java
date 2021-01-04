package ooo.sansk.bingoroyale.objective.implementation;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import ooo.sansk.bingoroyale.objective.BingoObjective;
import ooo.sansk.bingoroyale.util.TextFormatter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CraftItemObjective extends BingoObjective {

    private final Material itemType;
    private final int amountRequired;
    private int count;

    public CraftItemObjective(Player player, Material itemType, int amountRequired) {
        super(player, Material.CRAFTING_TABLE);
        this.itemType = itemType;
        this.amountRequired = amountRequired;
        this.count = 0;
    }

    @Override
    public boolean listensFor(Object event) {
        return event instanceof CraftItemEvent;
    }

    @Override
    public void checkCompleted(Object event) {
        CraftItemEvent craftItemEvent = (CraftItemEvent) event;
        if (!getPlayer().equals(craftItemEvent.getWhoClicked())) {
            return;
        }
        if (!this.itemType.equals(craftItemEvent.getRecipe().getResult().getType())) {
            return;
        }
        this.count = Math.min(this.count + getAmountOfCraftedItems(craftItemEvent), amountRequired);
        if (this.count >= this.amountRequired) {
            setCompleted(true);
        } else {
            getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(getDescription()));
        }
    }

    @Override
    public void setCompleted(boolean completed) {
        super.setCompleted(completed);
        if (!completed) {
            this.count = 0;
        }
    }

    @Override
    public String getName() {
        return "Craft " + amountRequired + " " + TextFormatter.enumNameToFancyString(itemType.name()) + (amountRequired == 1 ? "" : "s");
    }

    @Override
    public String getDescription() {
        return String.format("§7Craft §c%d %s%s §8(%s%d§7/%s%d§8)",
            this.amountRequired,
            TextFormatter.enumNameToFancyString(this.itemType.name()),
            this.amountRequired == 1 ? "" : "s",
            isCompleted() ? "§a" : "§c",
            this.count,
            isCompleted() ? "§a" : "§c",
            this.amountRequired);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CraftItemObjective that = (CraftItemObjective) o;
        return amountRequired == that.amountRequired &&
            itemType == that.itemType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemType, amountRequired);
    }

    //Code taken from https://github.com/ezeiger92/QuestWorld2/blob/master/src/main/java/com/questworld/extension/builtin/CraftMission.java
    private int getAmountOfCraftedItems(CraftItemEvent event) {
        ItemStack test = event.getRecipe().getResult().clone();
        ClickType click = event.getClick();

        int recipeAmount = test.getAmount();

        switch (click) {
            case NUMBER_KEY:
                if (event.getWhoClicked().getInventory().getItem(event.getHotbarButton()) != null)
                    recipeAmount = 0;
                break;

            case DROP:
            case CONTROL_DROP:
                ItemStack cursor = event.getCursor();
                if (cursor != null && cursor.getType() != Material.AIR)
                    recipeAmount = 0;
                break;

            case SHIFT_RIGHT:
            case SHIFT_LEFT:
                if (recipeAmount == 0)
                    break;

                int maxCraftable = getMaxCraftAmount(event.getInventory());
                int capacity = fits(test, event.getView().getBottomInventory());

                // If we can't fit everything, increase "space" to include the items dropped by
                // crafting
                // (Think: Uncrafting 8 iron blocks into 1 slot)
                if (capacity < maxCraftable)
                    maxCraftable = ((capacity + recipeAmount - 1) / recipeAmount) * recipeAmount;

                recipeAmount = maxCraftable;
                break;
            default:
        }

        return recipeAmount;
    }

    private int getMaxCraftAmount(CraftingInventory inv) {
        if (inv.getResult() == null)
            return 0;

        int resultCount = inv.getResult().getAmount();
        int materialCount = Integer.MAX_VALUE;

        for (ItemStack is : inv.getMatrix())
            if (is != null && is.getAmount() < materialCount)
                materialCount = is.getAmount();

        return resultCount * materialCount;
    }

    private int fits(ItemStack stack, Inventory inv) {
        ItemStack[] contents = inv.getContents();
        int result = 0;

        for (ItemStack is : contents)
            if (is == null)
                result += stack.getMaxStackSize();
            else if (is.isSimilar(stack))
                result += Math.max(stack.getMaxStackSize() - is.getAmount(), 0);

        return result;
    }
}

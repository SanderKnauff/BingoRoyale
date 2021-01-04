package ooo.sansk.bingoroyale.objective.implementation;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import ooo.sansk.bingoroyale.objective.BingoObjective;
import ooo.sansk.bingoroyale.util.TextFormatter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;

public class KillEntityTypeObjective extends BingoObjective {

    private final EntityType entityType;
    private final int amountRequired;
    private final EquipmentSlot equipmentSlot;
    private final Material equippedMaterial;
    private int count;

    public KillEntityTypeObjective(Player player, EntityType entityType, int amountRequired, EquipmentSlot equipmentSlot, Material equippedMaterial) {
        super(player, Material.WOODEN_SWORD);
        this.entityType = entityType;
        this.amountRequired = amountRequired;
        this.equipmentSlot = equipmentSlot;
        this.equippedMaterial = equippedMaterial;
        this.count = 0;
    }

    @Override
    public boolean listensFor(Object event) {
        return event instanceof EntityDeathEvent;
    }

    @Override
    public void setCompleted(boolean completed) {
        super.setCompleted(completed);
        if (!completed) {
            this.count = 0;
        }
    }

    @Override
    public void checkCompleted(Object event) {
        EntityDeathEvent entityDeathEvent = (EntityDeathEvent) event;
        if (!entityType.equals(entityDeathEvent.getEntityType())) {
            return;
        }
        if (!getPlayer().equals(entityDeathEvent.getEntity().getKiller())) {
            return;
        }
        if (equipmentSlot != null && equippedMaterial != null
            && getPlayer().getEquipment() != null
            && !getPlayer().getEquipment().getItem(equipmentSlot).getType().equals(equippedMaterial)) {
            return;
        }
        this.count++;
        if (this.count >= this.amountRequired) {
            setCompleted(true);
        } else {
            getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(getDescription()));
        }
    }

    @Override
    public String getName() {
        return "Kill " + amountRequired + " " + TextFormatter.enumNameToFancyString(entityType.name()) + (equipmentSlot != null ? " with equipment " : "") + (amountRequired == 1 ? "" : "s");
    }

    @Override
    public String getDescription() {
        StringBuilder stringBuilder = new StringBuilder().append(ChatColor.GRAY).append("Kill ")
            .append(ChatColor.RED).append(this.amountRequired).append(" ")
            .append(TextFormatter.enumNameToFancyString(this.entityType.name())).append(this.amountRequired == 1 ? "" : "s");
        if (equipmentSlot != null && equippedMaterial != null) {
            String itemName = TextFormatter.enumNameToFancyString(equippedMaterial.name());
            stringBuilder.append(ChatColor.GRAY).append(" while having ").append(aOrAn(itemName)).append(" ")
                .append(ChatColor.RED).append(itemName)
                .append(ChatColor.GRAY).append(" ").append(inOrOn(equipmentSlot)).append(" your ")
                .append(ChatColor.RED).append(TextFormatter.capitalizeWord(equipmentSlot.name()));
        }
        if (this.amountRequired != 1) {
            stringBuilder.append(" ").append(ChatColor.DARK_GRAY).append("(").append(isCompleted() ? ChatColor.GREEN : ChatColor.RED).append(this.count)
                .append(ChatColor.GRAY).append("/")
                .append(isCompleted() ? ChatColor.GREEN : ChatColor.RED).append(this.amountRequired)
                .append(ChatColor.DARK_GRAY).append(")");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KillEntityTypeObjective that = (KillEntityTypeObjective) o;
        return amountRequired == that.amountRequired &&
            entityType == that.entityType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityType, amountRequired);
    }

    private String aOrAn(String word) {
        if (word.startsWith("a") ||
            word.startsWith("A") ||
            word.startsWith("e") ||
            word.startsWith("E") ||
            word.startsWith("i") ||
            word.startsWith("I") ||
            word.startsWith("o") ||
            word.startsWith("O") ||
            word.startsWith("u") ||
            word.startsWith("U")
        ) {
            return "an";
        } else {
            return "a";
        }
    }

    private String inOrOn(EquipmentSlot equipmentSlot) {
        switch (equipmentSlot) {
            case FEET:
            case HEAD:
            case CHEST:
            case LEGS:
                return "on";
            case HAND:
            case OFF_HAND:
                return "in";
            default:
                return "at";
        }
    }
}

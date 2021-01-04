package ooo.sansk.bingoroyale.objective.implementation;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CatchFishObjective extends BingoObjective {

    private static final List<Material> FISH_TYPES = Arrays.asList(
        Material.PUFFERFISH,
        Material.TROPICAL_FISH,
        Material.SALMON,
        Material.COD
    );

    private final int amountRequired;
    private int count;

    public CatchFishObjective(Player player, int amountRequired) {
        super(player, Material.FISHING_ROD);
        this.amountRequired = amountRequired;
        this.count = 0;
    }

    @Override
    public boolean listensFor(Object event) {
        return event instanceof PlayerFishEvent;
    }

    @Override
    public void checkCompleted(Object event) {
        PlayerFishEvent playerFishEvent = (PlayerFishEvent) event;
        if (!playerFishEvent.getPlayer().equals(getPlayer())) {
            return;
        }
        if (!(playerFishEvent.getCaught() instanceof Item)) {
            return;
        }
        Item item = (Item) playerFishEvent.getCaught();
        if (!FISH_TYPES.contains(item.getItemStack().getType())) {
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
    public String getDescription() {
        return String.format("§7Catch %s Fish §8(%s%d§7/%s%d§8)",
            this.amountRequired != 1 ? this.amountRequired : "a",
            isCompleted() ? "§a" : "§c",
            this.count,
            isCompleted() ? "§a" : "§c",
            this.amountRequired);
    }

    @Override
    public String getName() {
        return "Catch Fish";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CatchFishObjective that = (CatchFishObjective) o;
        return amountRequired == that.amountRequired &&
            count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), amountRequired, count);
    }
}

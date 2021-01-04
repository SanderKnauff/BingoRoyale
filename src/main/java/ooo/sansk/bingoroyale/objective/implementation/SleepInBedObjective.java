package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import ooo.sansk.bingoroyale.util.TextFormatter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.util.Objects;

public class SleepInBedObjective extends BingoObjective {

    private final Material bedType;

    public SleepInBedObjective(Player player, Material bedType) {
        super(player, bedType != null ? bedType : Material.RED_BED);
        this.bedType = bedType;
    }

    @Override
    public boolean listensFor(Object event) {
        return event instanceof PlayerBedEnterEvent;
    }

    @Override
    public void checkCompleted(Object event) {
        PlayerBedEnterEvent playerBedEnterEvent = (PlayerBedEnterEvent) event;
        if (!getPlayer().equals(playerBedEnterEvent.getPlayer())) {
            return;
        }
        if (!playerBedEnterEvent.getBedEnterResult().equals(PlayerBedEnterEvent.BedEnterResult.OK)) {
            return;
        }
        if (bedType != null && !bedType.equals(playerBedEnterEvent.getBed().getType())) {
            return;
        }
        setCompleted(true);
    }

    @Override
    public String getName() {
        return "Sleep in a bed";
    }

    @Override
    public String getDescription() {
        return String.format("§7Sleep in a §c%s",
            bedType != null ? TextFormatter.enumNameToFancyString(bedType.name()) : "Bed");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SleepInBedObjective that = (SleepInBedObjective) o;
        return bedType == that.bedType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bedType);
    }
}

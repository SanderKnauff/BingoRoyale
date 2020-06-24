package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class GainLevelObjective extends BingoObjective {

    public GainLevelObjective(Player player) {
        super(player, Material.EXPERIENCE_BOTTLE);
    }

    @Override
    public boolean listensFor(Object event) {
        return event instanceof PlayerLevelChangeEvent;
    }

    @Override
    public void checkCompleted(Object event) {
        PlayerLevelChangeEvent playerLevelChangeEvent = (PlayerLevelChangeEvent) event;
        if (!playerLevelChangeEvent.getPlayer().equals(getPlayer())) {
            return;
        }
        if(playerLevelChangeEvent.getNewLevel() < 20) {
            return;
        }
        setCompleted(true);
    }

    @Override
    public String getDescription() {
        return "§7Gain §c20 §7levels";
    }

    @Override
    public String getName() {
        return "Gather Experience";
    }
}

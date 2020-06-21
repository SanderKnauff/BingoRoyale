package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class GainLevelObjective extends BingoObjective<PlayerLevelChangeEvent> {

    public GainLevelObjective(Player player) {
        super(player, Material.EXPERIENCE_BOTTLE);
    }

    @Override
    public Class<PlayerLevelChangeEvent> getListenerType() {
        return PlayerLevelChangeEvent.class;
    }

    @Override
    public void checkCompleted(PlayerLevelChangeEvent event) {
        if (!event.getPlayer().equals(getPlayer())) {
            return;
        }
        if(event.getNewLevel() < 20) {
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

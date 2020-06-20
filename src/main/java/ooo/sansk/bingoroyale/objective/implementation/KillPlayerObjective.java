package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillPlayerObjective extends BingoObjective<PlayerDeathEvent> {

    public KillPlayerObjective(Player player) {
        super(player, Material.IRON_SWORD);
    }

    @Override
    public Class<PlayerDeathEvent> getListenerType() {
        return PlayerDeathEvent.class;
    }

    @Override
    public void checkCompleted(PlayerDeathEvent event) {
        if (getPlayer().equals(event.getEntity().getKiller())) {
            return;
        }
        setCompleted(true);
    }

    @Override
    public String getDescription() {
        return "Kill another player";
    }

    @Override
    public String getName() {
        return "Kill Player";
    }
}

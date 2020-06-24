package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillPlayerObjective extends BingoObjective {

    public KillPlayerObjective(Player player) {
        super(player, Material.IRON_SWORD);
    }

    @Override
    public boolean listensFor(Object event) {
        return event instanceof PlayerDeathEvent;
    }

    @Override
    public void checkCompleted(Object event) {
        PlayerDeathEvent playerDeathEvent = (PlayerDeathEvent) event;
        if (!getPlayer().equals(playerDeathEvent.getEntity().getKiller())) {
            return;
        }
        setCompleted(true);
    }

    @Override
    public String getDescription() {
        return ChatColor.GRAY  + "Kill another " + ChatColor.RED +"player";
    }

    @Override
    public String getName() {
        return "Kill Player";
    }
}

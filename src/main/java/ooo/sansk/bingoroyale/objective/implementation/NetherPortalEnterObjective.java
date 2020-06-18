package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerPortalEvent;

import java.util.Objects;

public class NetherPortalEnterObjective extends BingoObjective<PlayerPortalEvent> {

    public NetherPortalEnterObjective(Player player) {
        super(player, Material.OBSIDIAN);
    }

    @Override
    public Class<PlayerPortalEvent> getListenerType() {
        return PlayerPortalEvent.class;
    }

    @Override
    public void checkCompleted(PlayerPortalEvent event) {
        if (!getPlayer().equals(event.getPlayer())) {
            return;
        }
        setCompleted(true);
    }

    @Override
    public String getName() {
        return "Create Nether Portal";
    }

    @Override
    public String getDescription() {
        return "§7Enter a §5Nether Portal";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetherPortalEnterObjective that = (NetherPortalEnterObjective) o;
        return getPlayer().equals(that.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayer());
    }
}

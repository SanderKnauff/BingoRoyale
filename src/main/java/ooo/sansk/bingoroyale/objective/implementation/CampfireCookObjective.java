package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CampfireCookObjective extends BingoObjective {

    private Location location;

    public CampfireCookObjective(Player player) {
        super(player, Material.CAMPFIRE);
        this.location = null;
    }

    @Override
    public boolean listensFor(Object event) {
        return event instanceof PlayerInteractEvent || event instanceof BlockCookEvent;
    }

    @Override
    public void checkCompleted(Object event) {
        if (event instanceof PlayerInteractEvent) {
            handlePlayerInteractEvent((PlayerInteractEvent) event);
        }
        if (event instanceof BlockCookEvent) {
            handleBlockCookEvent((BlockCookEvent) event);
        }
    }

    private void handlePlayerInteractEvent(PlayerInteractEvent event) {
        if (!getPlayer().equals(event.getPlayer())) {
            return;
        }
        if (event.getClickedBlock() == null || !event.getClickedBlock().getType().equals(Material.CAMPFIRE)) {
            return;
        }
        this.location = event.getClickedBlock().getLocation();
    }

    private void handleBlockCookEvent(BlockCookEvent event) {
        if (!event.getBlock().getLocation().equals(this.location)) {
            return;
        }
        if (!(event.getBlock().getBlockData() instanceof Campfire) || !((Campfire) event.getBlock().getBlockData()).isSignalFire()) {
            return;
        }
        setCompleted(true);
    }

    @Override
    public void setCompleted(boolean completed) {
        super.setCompleted(completed);
        if (!completed) {
            this.location = null;
        }
    }

    @Override
    public String getDescription() {
        return "§7Cook some §cfood §7on a §cCampfire §7with an §cHay Bale §7under it";
    }

    @Override
    public String getName() {
        return "Cook food on a smokey Campfire";
    }
}

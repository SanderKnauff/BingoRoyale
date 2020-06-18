package ooo.sansk.bingoroyale;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.WorldUnloadEvent;

public class BingoRoyaleListener implements Listener {

    private final BingoRoyaleMinigame bingoRoyaleMinigame;

    public BingoRoyaleListener(BingoRoyaleMinigame bingoRoyaleMinigame) {
        this.bingoRoyaleMinigame = bingoRoyaleMinigame;
    }

    @EventHandler
    public void onAnimalBreed(EntityBreedEvent e) {
        bingoRoyaleMinigame.handleEvent(e);
    }

    @EventHandler
    public void onItemCraft(CraftItemEvent e) {
        bingoRoyaleMinigame.handleEvent(e);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        bingoRoyaleMinigame.handleEvent(e);
    }

    @EventHandler
    public void onPortalCreate(PortalCreateEvent e) {
        bingoRoyaleMinigame.handleEvent(e);
    }

    @EventHandler
    public void onBedSleep(PlayerBedEnterEvent e) {
        bingoRoyaleMinigame.handleEvent(e);
    }

    @EventHandler
    public void onEnterPortal(PlayerPortalEvent e) {
        bingoRoyaleMinigame.handleEvent(e);
    }

    @EventHandler
    public void onUnloadWorld(WorldUnloadEvent e) {
        if(BingoRoyaleMinigame.WORLD_NAME.equals(e.getWorld().getName())){
           bingoRoyaleMinigame.removeWorld();
        }
    }
}

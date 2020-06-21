package ooo.sansk.bingoroyale;

import ooo.sansk.bingoroyale.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;
import java.util.Random;

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
        if (BingoRoyaleMinigame.WORLD_NAME.equals(e.getWorld().getName())) {
            bingoRoyaleMinigame.removeWorld();
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (bingoRoyaleMinigame.isPlayerInGame(e.getPlayer())) {
            return;
        }
        PlayerUtil.cleanPlayer(e.getPlayer(), true);
        if (bingoRoyaleMinigame.isGameActive()) {
            bingoRoyaleMinigame.spawnPlayerInArena(e.getPlayer(), new Random().nextInt(Bukkit.getOnlinePlayers().size()));
        } else {
            e.getPlayer().teleport(Bukkit.getWorlds().get(0).getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e) {
        if (!e.getView().getTitle().equals("§6Bingocard")) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryInteract(InventoryCreativeEvent e) {
        if (!e.getView().getTitle().equals("§6Bingocard")) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryInteract(InventoryDragEvent e) {
        if (!e.getView().getTitle().equals("§6Bingocard")) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        bingoRoyaleMinigame.handlePlayerDeath(e.getEntity());
        bingoRoyaleMinigame.handleEvent(e);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        bingoRoyaleMinigame.handlePlayerRespawn(e);
    }

    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent e) {
        bingoRoyaleMinigame.handleEvent(e);
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        if (!e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
            return;
        }
        if (!EntityType.FIREWORK.equals(e.getDamager().getType())) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        bingoRoyaleMinigame.handleEvent(e);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e) {
        if (e.getEntity().getLocation().getWorld().equals(Bukkit.getWorlds().get(0))) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent e) {
        if (!Objects.equals(e.getHand(), EquipmentSlot.HAND)) {
            return;
        }
        if (e.getItem() == null || !e.getItem().getType().equals(Material.PAPER)) {
            return;
        }
        bingoRoyaleMinigame.openCard(e.getPlayer());
    }
}

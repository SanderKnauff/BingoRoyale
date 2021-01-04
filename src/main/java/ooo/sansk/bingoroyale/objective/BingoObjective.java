package ooo.sansk.bingoroyale.objective;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

public abstract class BingoObjective {

    private final UUID player;
    private final Material icon;
    private boolean completed;

    public BingoObjective(Player player, Material icon) {
        this.player = player.getUniqueId();
        this.icon = icon;
        this.completed = false;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(player);
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
        if (completed) {
            notifyCompletion();
        }
    }

    private void notifyCompletion() {
        getPlayer().playSound(getPlayer().getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 0.5f, 1.5f);
        getPlayer().sendTitle(ChatColor.GOLD + "Objective Complete!", getDescription(), 10, 70, 20);
        getPlayer().getWorld().spawn(getPlayer().getLocation(), Firework.class, firework -> {
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.setPower(3);
            fireworkMeta.addEffects(
                    FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(Color.RED).build(),
                    FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(Color.GREEN).build(),
                    FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(Color.BLUE).build(),
                    FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(Color.YELLOW).build()
            );
            firework.setFireworkMeta(fireworkMeta);
        });
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            onlinePlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.AQUA + getPlayer().getName() + ChatColor.GOLD + " has completed the objective " + ChatColor.BOLD + ChatColor.GREEN + getName()));
        }
    }

    public abstract boolean listensFor(Object event);

    public abstract void checkCompleted(Object event);

    public abstract String getDescription();

    public abstract String getName();

    public ItemStack getItemRepresentation() {
        ItemStack representation = new ItemStack(isCompleted() ? Material.LIME_WOOL : icon, 1);
        ItemMeta itemMeta = representation.getItemMeta();
        if(itemMeta != null) {
            itemMeta.setDisplayName(ChatColor.GOLD + getName());
            itemMeta.setLore(Collections.singletonList((isCompleted() ? "§8[§aCOMPLETED§8] " : "") + getDescription()));
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            representation.setItemMeta(itemMeta);
        }
        return representation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BingoObjective that = (BingoObjective) o;
        return player.equals(that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}

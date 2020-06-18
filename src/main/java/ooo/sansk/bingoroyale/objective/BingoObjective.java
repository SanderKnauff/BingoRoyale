package ooo.sansk.bingoroyale.objective;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.Objects;

public abstract class BingoObjective<T extends Event> {

    private final Player player;
    private boolean completed;

    public BingoObjective(Player player) {
        this.player = player;
        this.completed = false;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isCompleted() {
        return completed;
    }

    protected void setCompleted(boolean completed) {
        this.completed = completed;
        if (completed) {
            notifyCompletion();
        }
    }

    private void notifyCompletion() {
        player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 0.5f, 1.5f);
        player.sendTitle(ChatColor.GOLD + "Objective Complete!", getDescription(), 10, 70, 20);
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            onlinePlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.AQUA + player.getName() + ChatColor.GOLD + " has completed the objective " + ChatColor.BOLD + ChatColor.GREEN + getName()));
        }
    }

    public abstract Class<T> getListenerType();

    public abstract void checkCompleted(T event);

    public abstract String getDescription();

    public abstract String getName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BingoObjective<?> that = (BingoObjective<?>) o;
        return player.equals(that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}

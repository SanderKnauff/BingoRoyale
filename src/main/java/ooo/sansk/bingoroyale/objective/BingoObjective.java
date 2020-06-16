package ooo.sansk.bingoroyale.objective;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

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
    }

    public abstract Class<T> getListenerType();

    public abstract void checkCompleted(T event);

    public abstract String getDescription();
}

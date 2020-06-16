package ooo.sansk.bingoroyale;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldUnloadEvent;

public class BingoRoyaleListener implements Listener {

    private final BingoRoyaleMinigame bingoRoyaleMinigame;

    public BingoRoyaleListener(BingoRoyaleMinigame bingoRoyaleMinigame) {
        this.bingoRoyaleMinigame = bingoRoyaleMinigame;
    }

    @EventHandler
    public void onUnloadWorld(WorldUnloadEvent e) {
        if(BingoRoyaleMinigame.WORLD_NAME.equals(e.getWorld().getName())){
           bingoRoyaleMinigame.removeWorld();
        }
    }
}

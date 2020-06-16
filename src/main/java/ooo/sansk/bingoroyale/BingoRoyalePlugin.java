package ooo.sansk.bingoroyale;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.command.Commands;
import org.bukkit.plugin.java.annotation.plugin.Plugin;

import java.util.Objects;

@Plugin(name = "bingoroyale", version = "0.0.1-SNAPSHOT")
@Commands(value = {
        @Command(name = "startgame")
})
public class BingoRoyalePlugin extends JavaPlugin {

    private BingoRoyaleMinigame bingoRoyaleMinigame;
    private BingoRoyaleListener bingoRoyaleListener;

    @Override
    public void onEnable() {
        this.bingoRoyaleMinigame = new BingoRoyaleMinigame(this);
        this.bingoRoyaleMinigame.removeWorld();
        this.bingoRoyaleMinigame.createWorld();
        Objects.requireNonNull(getCommand("startgame")).setExecutor(new StartGameCommand(this.bingoRoyaleMinigame));
        this.bingoRoyaleListener = new BingoRoyaleListener(bingoRoyaleMinigame);
        getServer().getPluginManager().registerEvents(bingoRoyaleListener, this);
    }

    @Override
    public void onDisable() {
        this.bingoRoyaleMinigame = null;
        this.bingoRoyaleListener = null;
    }

}

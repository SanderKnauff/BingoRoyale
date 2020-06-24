package ooo.sansk.bingoroyale;

import ooo.sansk.bingoroyale.command.StartGameCommand;
import ooo.sansk.bingoroyale.command.StopGameCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.command.Commands;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Plugin;

import java.util.Objects;

@Plugin(name = "bingoroyale", version = "${git.commit.id.abbrev}")
@Commands(value = {
        @Command(name = "startgame"),
        @Command(name = "stopgame")
})
@ApiVersion(ApiVersion.Target.v1_13)
public class BingoRoyalePlugin extends JavaPlugin {

    private BingoRoyaleMinigame bingoRoyaleMinigame;
    private BingoRoyaleListener bingoRoyaleListener;

    @Override
    public void onEnable() {
        this.bingoRoyaleMinigame = new BingoRoyaleMinigame(this);
        this.bingoRoyaleMinigame.removeWorld();
        this.bingoRoyaleMinigame.createWorld();
        Objects.requireNonNull(getCommand("startgame")).setExecutor(new StartGameCommand(this.bingoRoyaleMinigame));
        Objects.requireNonNull(getCommand("stopgame")).setExecutor(new StopGameCommand(this.bingoRoyaleMinigame));
        this.bingoRoyaleListener = new BingoRoyaleListener(bingoRoyaleMinigame);
        getServer().getPluginManager().registerEvents(bingoRoyaleListener, this);
    }

    @Override
    public void onDisable() {
        this.bingoRoyaleMinigame = null;
        this.bingoRoyaleListener = null;
    }

}

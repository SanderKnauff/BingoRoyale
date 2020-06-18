package ooo.sansk.bingoroyale.command;


import ooo.sansk.bingoroyale.BingoRoyaleMinigame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopGameCommand implements CommandExecutor {

    private final BingoRoyaleMinigame bingoRoyaleMinigame;

    public StopGameCommand(BingoRoyaleMinigame bingoRoyaleMinigame) {
        this.bingoRoyaleMinigame = bingoRoyaleMinigame;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            return false;
        }
        if (!sender.isOp()) {
            sender.sendMessage("Foei, jij mag dat niet");
            return true;
        }
        bingoRoyaleMinigame.stopGame();
        return true;
    }
}

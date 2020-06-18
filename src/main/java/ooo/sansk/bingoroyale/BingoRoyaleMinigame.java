package ooo.sansk.bingoroyale;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BingoRoyaleMinigame {

    public static final String WORLD_NAME = "BingoWorld";
    private static final int ITEMS_PER_LINE = 2;
    private static final int WORLD_SIZE = 1000;

    private final Plugin plugin;
    private final List<BingoCard> bingoCards;

    private World world;

    public BingoRoyaleMinigame(Plugin plugin) {
        this.plugin = plugin;
        this.bingoCards = new LinkedList<>();
    }

    public void startGame() {
        List<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        long seed = Instant.now().toEpochMilli();
        for (int i = 0; i < playerList.size(); i++) {
            Player player = playerList.get(i);
            Location location = calculateSpawnLocationInCircle(i, playerList.size());
            player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
            plugin.getLogger().info(() -> "Teleported " + player.getName() + " to " + location);
            player.setGameMode(GameMode.SURVIVAL);
            BingoCard bingoCard = createBingoCard(player, seed);
            bingoCards.add(bingoCard);
            player.sendMessage("Complete the following objectives:");
            for (BingoObjective<Event>[] bingoObjectives : bingoCard.getTable()) {
                for (BingoObjective<Event> bingoObjective : bingoObjectives) {
                    player.sendMessage(bingoObjective.getDescription());
                }
            }
        }
    }

    public void handleEvent(Event event) {
        for (BingoCard bingoCard : bingoCards) {
            if (bingoCard.checkObjectiveCompleted(event) && bingoCard.checkCompletion()) {
                Bukkit.getServer().broadcastMessage(bingoCard.getOwner().getName() + " completed their card!");
                return;
            }
        }
    }

    private BingoCard createBingoCard(Player player, long seed) {
        BingoCard card = new BingoCard(player, ITEMS_PER_LINE);
        card.fillTable(seed);
        return card;
    }

    private Location calculateSpawnLocationInCircle(int point, int amountOfPoints) {
        double x = ((WORLD_SIZE * 0.75) / 2) * Math.cos(((2 * Math.PI) / amountOfPoints) * point);
        double z = ((WORLD_SIZE * 0.75) / 2) * Math.sin(((2 * Math.PI) / amountOfPoints) * point);
        return new Location(world, x, world.getHighestBlockYAt((int) x, (int) z), z);
    }

    public World createWorld() {
        this.world = new WorldCreator(WORLD_NAME)
                .createWorld();
        setupWorldBorder();
        setupGameRules();
        return world;
    }

    public void setupWorldBorder() {
        WorldBorder worldBorder = this.world.getWorldBorder();
        worldBorder.setCenter(0, 0);
        worldBorder.setSize(WORLD_SIZE);
        worldBorder.setWarningDistance(0);
    }

    public void setupGameRules() {
        this.world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        this.world.setGameRule(GameRule.DISABLE_RAIDS, true);
        this.world.setGameRule(GameRule.DO_FIRE_TICK, false);
        this.world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        this.world.setGameRule(GameRule.KEEP_INVENTORY, true);
        this.world.setGameRule(GameRule.DO_INSOMNIA, false);
        this.world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
        this.world.setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
        this.world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
        this.world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
    }

    public void removeWorld() {
        if (world != null) {
            Bukkit.getServer().unloadWorld(world, false);
        }
        File worldContainerFolder = Bukkit.getWorldContainer();
        File worldFolder = new File(worldContainerFolder, WORLD_NAME);
        try {
            Files.walkFileTree(worldFolder.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.TERMINATE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            plugin.getLogger().severe("Could not remove World '" + worldFolder + "'. Reason: (" + e.getClass().getName() + ": " + e.getMessage() + ")");
        }
    }


}

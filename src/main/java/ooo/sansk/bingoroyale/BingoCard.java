package ooo.sansk.bingoroyale;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import ooo.sansk.bingoroyale.objective.ObjectiveType;
import ooo.sansk.bingoroyale.util.Tuple;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoCard {

    private final UUID owner;
    private final BingoObjective<Event>[][] table;
    private final Stack<Integer> insertionOrder;

    public BingoCard(Player owner, int itemsPerLine) {
        if (itemsPerLine != 1 && itemsPerLine != 3 && itemsPerLine != 5) {
            throw new IllegalArgumentException("ItemsPerLine can only be 1, 3 or 5 to make it fit neatly in an inventory");
        }
        this.owner = owner.getUniqueId();
        this.table = new BingoObjective[itemsPerLine][itemsPerLine];
        this.insertionOrder = IntStream.range(0, itemsPerLine * itemsPerLine).boxed().collect(Collectors.toCollection(Stack::new));
        Collections.shuffle(this.insertionOrder);
    }

    public void fillTable(long seed) {
        Random random = new Random(seed);
        for (int i = 0; i < table.length * table.length; i++) {
            int position = insertionOrder.pop();
            int tableX = position / table.length;
            int tableY = position % table.length;
            BingoObjective<Event> bingoObjective;
            do {
                bingoObjective = ObjectiveType.values()[random.nextInt(ObjectiveType.values().length)]
                        .getFactory()
                        .generateObjective(getOwner(), random);
            } while (tableContains(bingoObjective));
            table[tableX][tableY] = bingoObjective;
        }
    }

    public void unCompleteRandomObjective() {
        List<Tuple<Integer, Integer>> completedObjectives = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                BingoObjective<Event> bingoObjective = table[i][j];
                if (bingoObjective.isCompleted()) {
                    completedObjectives.add(new Tuple<>(i, j));
                }
            }
        }
        if (completedObjectives.isEmpty()) {
            getOwner().sendTitle(ChatColor.RED + "You died...", ChatColor.GRAY + "But as you didn't achieve anything yet, you came out unscaved. ", 10, 70, 20);
            return;
        }
        Tuple<Integer, Integer> completedObjective = completedObjectives.get(new Random().nextInt(completedObjectives.size()));
        BingoObjective<Event> eventBingoObjective = table[completedObjective.getX()][completedObjective.getY()];
        eventBingoObjective.setCompleted(false);
        getOwner().sendTitle(ChatColor.RED + "You died...", ChatColor.GRAY + "As punishment, you need to redo " + ChatColor.RED + eventBingoObjective.getName(), 10, 70, 20);
    }

    public boolean checkObjectiveCompleted(Event event) {
        boolean hasCompletedAny = false;
        for (BingoObjective<Event>[] row : table) {
            for (BingoObjective<Event> bingoObjective : row) {
                if (!bingoObjective.isCompleted() && bingoObjective.getListenerType().isInstance(event)) {
                    bingoObjective.checkCompleted(event);
                    if (bingoObjective.isCompleted()) {
                        hasCompletedAny = true;
                    }
                }
            }
        }
        return hasCompletedAny;
    }

    public void displayCard(Player playerToDisplayTo) {
        Inventory inventory = Bukkit.createInventory(playerToDisplayTo, table.length * 9, "§6Bingocard");
        for (int i = 0; i < table.length * 9; i++) {
            if (!isInSquare(i)) {
                inventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS, 1));
            } else {
                Tuple<Integer, Integer> coordinates = calculateTableIndexFromInventoryIndex(i);
                inventory.setItem(i, table[coordinates.getX()][coordinates.getY()].getItemRepresentation());
            }
        }

        playerToDisplayTo.openInventory(inventory);
    }

    private Tuple<Integer, Integer> calculateTableIndexFromInventoryIndex(int i) {
        int padding = (9 - table.length) / 2;
        int x = (i % 9) - padding;
        int y = i / 9;
        return new Tuple<>(x, y);
    }

    private boolean isInSquare(int i) {
        int padding = (9 - table.length) / 2;
        return (i % 9) >= padding && (i % 9) < (9 - padding);
    }

    public boolean checkCompletion() {
        boolean columnDone = true;
        boolean rowDone = true;
        boolean topLeftBottomRightDone = true;
        boolean bottomLeftTopRightDone = true;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                columnDone = columnDone && table[i][j].isCompleted();
                rowDone = rowDone && table[j][i].isCompleted();
                topLeftBottomRightDone = topLeftBottomRightDone && table[j][j].isCompleted();
                bottomLeftTopRightDone = bottomLeftTopRightDone && table[j][(table.length - 1) - j].isCompleted();
            }
            if (columnDone || rowDone || topLeftBottomRightDone || bottomLeftTopRightDone) {
                return true;
            }
        }
        return false;
    }

    public boolean tableContains(BingoObjective objective) {
        for (BingoObjective<?>[] bingoObjectives : table) {
            for (BingoObjective<?> objectiveInTable : bingoObjectives) {
                if (objective.equals(objectiveInTable)) {
                    return true;
                }
            }
        }
        return false;
    }

    public BingoObjective<Event>[][] getTable() {
        return table;
    }

    public Player getOwner() {
        return Bukkit.getPlayer(owner);
    }
}

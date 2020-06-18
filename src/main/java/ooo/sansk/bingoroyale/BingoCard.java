package ooo.sansk.bingoroyale;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import ooo.sansk.bingoroyale.objective.ObjectiveType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Objective;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoCard {

    private final Player owner;
    private final BingoObjective<Event>[][] table;
    private final Stack<Integer> insertionOrder;

    public BingoCard(Player owner, int itemsPerLine) {
        this.owner = owner;
        this.table = new BingoObjective[itemsPerLine][itemsPerLine];
        this.insertionOrder = IntStream.range(0 , itemsPerLine * itemsPerLine).boxed().collect(Collectors.toCollection(Stack::new));
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
                        .generateObjective(owner, random);
            } while (tableContains(bingoObjective));
            table[tableX][tableY] = bingoObjective;
        }
    }

    public boolean checkObjectiveCompleted(Event event) {
        for (BingoObjective<Event>[] row : table) {
            for (BingoObjective<Event> bingoObjective : row) {
                if(!bingoObjective.isCompleted() && bingoObjective.getListenerType().isInstance(event)) {
                    bingoObjective.checkCompleted(event);
                    if (bingoObjective.isCompleted()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkCompletion() {
        boolean columnDone = true;
        boolean rowDone = true;
        boolean diagonalDone = true;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                columnDone = columnDone && table[i][j].isCompleted();
                rowDone = rowDone && table[j][i].isCompleted();
                diagonalDone = diagonalDone && table[j][j].isCompleted();
            }
            if (columnDone || rowDone || diagonalDone) {
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
        return owner;
    }
}

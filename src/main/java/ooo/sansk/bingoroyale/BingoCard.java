package ooo.sansk.bingoroyale;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.List;

public class BingoCard {

    private static final int ITEMS_PER_LINE = 2;

    private final Player owner;
    private final BingoObjective<Event>[][] table;

    public BingoCard(Player owner) {
        this.owner = owner;
        this.table = new BingoObjective[ITEMS_PER_LINE][ITEMS_PER_LINE];
    }

    public void fillTable(List<BingoObjective<Event>> objectives) {
        if (objectives.size() != ITEMS_PER_LINE * ITEMS_PER_LINE) {
            throw new IllegalArgumentException("There should be " + (ITEMS_PER_LINE * ITEMS_PER_LINE) + "objectives in the list");
        }
    }
}

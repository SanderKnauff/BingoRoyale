package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.entity.Player;

import java.util.Random;

public interface ObjectiveFactory<T extends BingoObjective> {

    T generateObjective(Player player, Random random);
}

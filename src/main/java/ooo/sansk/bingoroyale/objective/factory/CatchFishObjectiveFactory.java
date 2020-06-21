package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.CatchFishObjective;
import org.bukkit.entity.Player;

import java.util.Random;

public class CatchFishObjectiveFactory implements ObjectiveFactory<CatchFishObjective> {

    @Override
    public CatchFishObjective generateObjective(Player player, Random random) {
        return new CatchFishObjective(player, 10);
    }
}

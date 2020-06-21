package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.GainLevelObjective;
import org.bukkit.entity.Player;

import java.util.Random;

public class GainLevelObjectiveFactory implements ObjectiveFactory<GainLevelObjective> {

    @Override
    public GainLevelObjective generateObjective(Player player, Random random) {
        return new GainLevelObjective(player);
    }
}

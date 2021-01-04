package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.SwimInLavaObjective;
import org.bukkit.entity.Player;

import java.util.Random;

public class SwimInLavaObjectiveFactory implements ObjectiveFactory<SwimInLavaObjective> {

    @Override
    public SwimInLavaObjective generateObjective(Player player, Random random) {
        return new SwimInLavaObjective(player);
    }
}

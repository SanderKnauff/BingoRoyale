package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.KillPlayerObjective;
import org.bukkit.entity.Player;

import java.util.Random;

public class KillPlayerObjectiveFactory implements ObjectiveFactory<KillPlayerObjective> {

    @Override
    public KillPlayerObjective generateObjective(Player player, Random random) {
        return new KillPlayerObjective(player);
    }
}

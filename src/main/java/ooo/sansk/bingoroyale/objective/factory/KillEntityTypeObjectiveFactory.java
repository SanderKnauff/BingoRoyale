package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.KillEntityTypeObjective;
import ooo.sansk.bingoroyale.util.Tuple;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Random;

public class KillEntityTypeObjectiveFactory implements ObjectiveFactory<KillEntityTypeObjective> {

    private static Tuple<EntityType, Integer>[] ALLOWED_TYPES = new Tuple[] {
            new Tuple<>(EntityType.PHANTOM, 1),
            new Tuple<>(EntityType.BAT, 1),
            new Tuple<>(EntityType.WITCH, 3),
            new Tuple<>(EntityType.WOLF, 5),
            new Tuple<>(EntityType.CREEPER, 8),
            new Tuple<>(EntityType.ZOMBIE, 10),
            new Tuple<>(EntityType.SKELETON, 10),
            new Tuple<>(EntityType.SPIDER, 10),
    };

    @Override
    public KillEntityTypeObjective generateObjective(Player player, Random random) {
        Tuple<EntityType, Integer> craftTarget = ALLOWED_TYPES[random.nextInt(ALLOWED_TYPES.length)];
        return new KillEntityTypeObjective(player, craftTarget.getX(), craftTarget.getY());
    }
}


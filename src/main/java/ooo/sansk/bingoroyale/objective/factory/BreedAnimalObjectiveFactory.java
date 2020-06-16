package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.BreedAnimalObjective;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Random;

public class BreedAnimalObjectiveFactory implements ObjectiveFactory<BreedAnimalObjective> {

    private static final EntityType[] ALLOWED_TYPES = new EntityType[]{
            EntityType.CHICKEN,
            EntityType.PIG,
            EntityType.COW,
            EntityType.SHEEP,
            EntityType.RABBIT,
            EntityType.WOLF,
//            EntityType.CAT, //Disabled. may be very hard
//            EntityType.OCELOT, //Disabled. may be very hard
//            EntityType.HORSE, //Disabled. may be very hard
//            EntityType.MUSHROOM_COW, //Disabled. may be very hard
    };

    @Override
    public BreedAnimalObjective generateObjective(Player player, Random random) {
        return new BreedAnimalObjective(player, ALLOWED_TYPES[random.nextInt(ALLOWED_TYPES.length)]);
    }

}

package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.SleepInBedObjective;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Random;

public class SleepInBedObjectiveFactory implements ObjectiveFactory<SleepInBedObjective> {

    private static final Material[] ALLOWED_BED_TYPES = new Material[] {
            Material.WHITE_BED,
            Material.ORANGE_BED,
            Material.MAGENTA_BED,
            Material.LIGHT_BLUE_BED,
            Material.YELLOW_BED,
            Material.LIME_BED,
            Material.PINK_BED,
            Material.GRAY_BED,
            Material.LIGHT_GRAY_BED,
            Material.CYAN_BED,
            Material.PURPLE_BED,
            Material.BLUE_BED,
            Material.BROWN_BED,
            Material.GREEN_BED,
            Material.RED_BED,
            Material.BLACK_BED
    };

    @Override
    public SleepInBedObjective generateObjective(Player player, Random random) {
        if(random.nextDouble() < 0.75) {
            return new SleepInBedObjective(player, null);
        } else {
            return new SleepInBedObjective(player, ALLOWED_BED_TYPES[random.nextInt(ALLOWED_BED_TYPES.length)]);
        }
    }
}



package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.KillEntityTypeObjective;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KillEntityTypeObjectiveFactory implements ObjectiveFactory<KillEntityTypeObjective> {

    private static final List<KillEntityObjectiveData> ALLOWED_TYPES = Arrays.asList(
            new KillEntityObjectiveData(EntityType.BAT, 1, null, null),
            new KillEntityObjectiveData(EntityType.WITCH, 3, null, null),
            new KillEntityObjectiveData(EntityType.WOLF, 5, null, null),
            new KillEntityObjectiveData(EntityType.CREEPER, 8, null, null),
            new KillEntityObjectiveData(EntityType.ZOMBIE, 10, null, null),
            new KillEntityObjectiveData(EntityType.SKELETON, 10, null, null),
            new KillEntityObjectiveData(EntityType.SPIDER, 10, null, null),

            new KillEntityObjectiveData(EntityType.ENDERMAN, 1, EquipmentSlot.HEAD, Material.CARVED_PUMPKIN)
    );

    @Override
    public KillEntityTypeObjective generateObjective(Player player, Random random) {
        KillEntityObjectiveData killTarget = ALLOWED_TYPES.get(random.nextInt(ALLOWED_TYPES.size()));
        return new KillEntityTypeObjective(player, killTarget.entityType, killTarget.amount, killTarget.equipmentSlot, killTarget.equippedMaterial);
    }

    private static class KillEntityObjectiveData {
        private final EntityType entityType;
        private final int amount;
        private final EquipmentSlot equipmentSlot;
        private final Material equippedMaterial;

        public KillEntityObjectiveData(EntityType entityType, int amount, EquipmentSlot equipmentSlot, Material equippedMaterial) {
            this.entityType = entityType;
            this.amount = amount;
            this.equipmentSlot = equipmentSlot;
            this.equippedMaterial = equippedMaterial;
        }
    }
}


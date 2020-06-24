package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.CraftItemObjective;
import ooo.sansk.bingoroyale.util.Tuple;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CraftItemObjectiveFactory implements ObjectiveFactory<CraftItemObjective> {

    private static List<Tuple<Material, Integer>> ALLOWED_ITEMS = Arrays.asList(
            new Tuple<>(Material.CAKE, 1),
            new Tuple<>(Material.RABBIT_STEW, 1),
            new Tuple<>(Material.DIAMOND_HOE, 1),
            new Tuple<>(Material.ENCHANTING_TABLE, 1),
            new Tuple<>(Material.GOLDEN_APPLE, 1),
            new Tuple<>(Material.ANVIL, 1),
            new Tuple<>(Material.BEETROOT_SOUP, 3),
            new Tuple<>(Material.PUMPKIN_PIE, 3),
            new Tuple<>(Material.TNT, 5),
            new Tuple<>(Material.BOOKSHELF, 3),
            new Tuple<>(Material.STONE_PICKAXE, 27),
            new Tuple<>(Material.STICK, 1024)
    );

    @Override
    public CraftItemObjective generateObjective(Player player, Random random) {
        Tuple<Material, Integer> craftTarget = ALLOWED_ITEMS.get(random.nextInt(ALLOWED_ITEMS.size()));
        return new CraftItemObjective(player, craftTarget.getX(), craftTarget.getY());
    }
}

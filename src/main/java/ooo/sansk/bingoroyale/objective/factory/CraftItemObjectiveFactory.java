package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.CraftItemObjective;
import ooo.sansk.bingoroyale.util.Tuple;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Random;

public class CraftItemObjectiveFactory implements ObjectiveFactory<CraftItemObjective> {

    private static Tuple<Material, Integer>[] ALLOWED_ITEMS = new Tuple[] {
            new Tuple<>(Material.DIAMOND_HOE, 1),
            new Tuple<>(Material.GOLDEN_APPLE, 1),
            new Tuple<>(Material.ANVIL, 1),
            new Tuple<>(Material.BEETROOT_SOUP, 3),
            new Tuple<>(Material.PUMPKIN_PIE, 3),
            new Tuple<>(Material.TNT, 5),
            new Tuple<>(Material.BOOKSHELF, 3),
            new Tuple<>(Material.STONE_PICKAXE, 27),
            new Tuple<>(Material.STICK, 1024),
    };

    @Override
    public CraftItemObjective generateObjective(Player player, Random random) {
        Tuple<Material, Integer> craftTarget = ALLOWED_ITEMS[random.nextInt(ALLOWED_ITEMS.length)];
        return new CraftItemObjective(player, craftTarget.getX(), craftTarget.getY());
    }
}

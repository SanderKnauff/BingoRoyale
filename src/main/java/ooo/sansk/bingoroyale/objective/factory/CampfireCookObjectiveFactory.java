package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.CampfireCookObjective;
import org.bukkit.entity.Player;

import java.util.Random;

public class CampfireCookObjectiveFactory implements ObjectiveFactory<CampfireCookObjective> {

    @Override
    public CampfireCookObjective generateObjective(Player player, Random random) {
        return new CampfireCookObjective(player);
    }
}

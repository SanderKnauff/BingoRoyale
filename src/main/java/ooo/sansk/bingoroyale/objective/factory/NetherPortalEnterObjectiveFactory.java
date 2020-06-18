package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.NetherPortalEnterObjective;
import org.bukkit.entity.Player;

import java.util.Random;

public class NetherPortalEnterObjectiveFactory implements ObjectiveFactory<NetherPortalEnterObjective> {

    @Override
    public NetherPortalEnterObjective generateObjective(Player player, Random random) {
        return new NetherPortalEnterObjective(player);
    }
}

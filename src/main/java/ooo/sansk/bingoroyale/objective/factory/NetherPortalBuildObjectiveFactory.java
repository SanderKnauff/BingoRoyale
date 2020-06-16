package ooo.sansk.bingoroyale.objective.factory;

import ooo.sansk.bingoroyale.objective.implementation.NetherPortalBuildObjective;
import org.bukkit.entity.Player;

import java.util.Random;

public class NetherPortalBuildObjectiveFactory implements ObjectiveFactory<NetherPortalBuildObjective> {

    @Override
    public NetherPortalBuildObjective generateObjective(Player player, Random random) {
        return new NetherPortalBuildObjective(player);
    }
}

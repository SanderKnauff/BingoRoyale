package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class SwimInLavaObjective extends BingoObjective {

    public SwimInLavaObjective(Player player) {
        super(player, Material.LAVA_BUCKET);
    }

    @Override
    public boolean listensFor(Object event) {
        return event instanceof EntityDamageByBlockEvent;
    }

    @Override
    public void checkCompleted(Object event) {
        if (!(event instanceof EntityDamageByBlockEvent)) {
            return;
        }
        EntityDamageByBlockEvent damageEvent = (EntityDamageByBlockEvent) event;

        if (!damageEvent.getCause().equals(EntityDamageEvent.DamageCause.LAVA)) {
            return;
        }

        if (getPlayer().getLocation().getBlock().getType().equals(Material.LAVA)
            && getPlayer().getEyeLocation().getBlock().getType().equals(Material.LAVA)) {
            setCompleted(true);
        }
    }

    @Override
    public String getName() {
        return "Swim in Lava";
    }

    @Override
    public String getDescription() {
        return "§7Take §cdamage §7 while being §ccovered head to toe §7in §cLava";
    }
}

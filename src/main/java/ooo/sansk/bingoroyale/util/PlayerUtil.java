package ooo.sansk.bingoroyale.util;

import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class PlayerUtil {

    public static void cleanPlayer(Player player, boolean clearInv) {
        if(clearInv){
            player.getInventory().clear();
            player.getEnderChest().clear();
        }
        player.setLevel(0);
        player.setExp(0);
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
        player.setFireTicks(0);
        player.setGlowing(false);
    }
}

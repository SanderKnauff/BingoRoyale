package ooo.sansk.bingoroyale.objective;

import ooo.sansk.bingoroyale.objective.factory.BreedAnimalObjectiveFactory;
import ooo.sansk.bingoroyale.objective.factory.CampfireCookObjectiveFactory;
import ooo.sansk.bingoroyale.objective.factory.CatchFishObjectiveFactory;
import ooo.sansk.bingoroyale.objective.factory.CraftItemObjectiveFactory;
import ooo.sansk.bingoroyale.objective.factory.GainLevelObjectiveFactory;
import ooo.sansk.bingoroyale.objective.factory.KillEntityTypeObjectiveFactory;
import ooo.sansk.bingoroyale.objective.factory.KillPlayerObjectiveFactory;
import ooo.sansk.bingoroyale.objective.factory.ObjectiveFactory;
import ooo.sansk.bingoroyale.objective.factory.SleepInBedObjectiveFactory;
import ooo.sansk.bingoroyale.objective.factory.SwimInLavaObjectiveFactory;

public enum ObjectiveType {

    BREED(new BreedAnimalObjectiveFactory()),
    CRAFT(new CraftItemObjectiveFactory()),
    KILL(new KillEntityTypeObjectiveFactory()),
    //    PORTAL(new NetherPortalEnterObjectiveFactory()), Disabled, Event does not fire when nether has been turned off.
    SLEEP(new SleepInBedObjectiveFactory()),
    KILL_PLAYER(new KillPlayerObjectiveFactory()),
    EXPERIENCE(new GainLevelObjectiveFactory()),
    CATCH_FISH(new CatchFishObjectiveFactory()),
    COOK_FOOD_ON_CAMPFIRE(new CampfireCookObjectiveFactory()),
    SWIM_IN_LAVA(new SwimInLavaObjectiveFactory());
    //Build Golems
    //Shear a Snow Golem
    //Tame a horse
    //Equip full armor set (Leather, Gold)
    //X amount Flower Pots placed at the same time
    //Ride a distance with a minecart
    //Hang Paintings
    //Play music disk
    //Breed bees
    //Obtain an emerald
    //Create a banner
    //create a map
    //Build a large campfire
    //Wear a pumpkin
    //Have three different log types in your inventory
    //Collections of different items


    private final ObjectiveFactory<? extends BingoObjective> factory;

    ObjectiveType(ObjectiveFactory<? extends BingoObjective> factory) {
        this.factory = factory;
    }

    public ObjectiveFactory<?> getFactory() {
        return factory;
    }
}

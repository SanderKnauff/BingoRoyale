package ooo.sansk.bingoroyale.objective;

import ooo.sansk.bingoroyale.objective.factory.*;

public enum ObjectiveType {

    BREED(new BreedAnimalObjectiveFactory()),
    CRAFT(new CraftItemObjectiveFactory()),
    KILL(new KillEntityTypeObjectiveFactory()),
    PORTAL(new NetherPortalEnterObjectiveFactory()),
    SLEEP(new SleepInBedObjectiveFactory()),
    KILL_PLAYER(new KillPlayerObjectiveFactory()),
    EXPERIENCE(new GainLevelObjectiveFactory()),
    CATCH_FISH(new CatchFishObjectiveFactory()),
    COOK_FOOD_ON_CAMPFIRE(new CampfireCookObjectiveFactory());
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


    private final ObjectiveFactory factory;

    ObjectiveType(ObjectiveFactory factory) {
        this.factory = factory;
    }

    public ObjectiveFactory getFactory() {
        return factory;
    }
}

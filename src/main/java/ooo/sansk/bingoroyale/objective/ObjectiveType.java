package ooo.sansk.bingoroyale.objective;

import ooo.sansk.bingoroyale.objective.factory.*;

public enum  ObjectiveType {

    BREED(new BreedAnimalObjectiveFactory()),
    CRAFT(new CraftItemObjectiveFactory()),
    KILL(new KillEntityTypeObjectiveFactory()),
    PORTAL(new NetherPortalEnterObjectiveFactory()),
    SLEEP(new SleepInBedObjectiveFactory());

    private final ObjectiveFactory factory;

    ObjectiveType(ObjectiveFactory factory) {
        this.factory = factory;
    }

    public ObjectiveFactory getFactory() {
        return factory;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Data {
    Faction orgrimmarFaction;
    Faction stormwindFaction;
    Faction darkspearFaction;
    Faction darnassusFaction;
    Faction ironforgeFaction;

    Assembly warriorAssembly;
    Assembly hordeWarriorsAssembly;

    Character priestDarkspear;
    Character warriorDarkspear;
    Character priestOrgrimmar;
    Character warriorOrgrimmar;

    Character priestStormwind;
    Character warriorStormwind;
    Character priestDarnassus;
    Character warriorDarnassus;
    Character priestIronforge;
    Character warriorIronforge;

    Building molfenderTower;

    public void init(){
        // Horde factions
        orgrimmarFaction = new Faction("Orgrimmar");
        darkspearFaction = new Faction("Darkspear Trolls");

        // Alliance factions
        stormwindFaction = new Faction("Stormwind");
        darnassusFaction = new Faction("Darnassus");
        ironforgeFaction = new Faction("Ironforge");

        // Horde characters
        warriorOrgrimmar = new Warrior("Garrosh Hellscream");
        priestOrgrimmar = new Priest("Tyelis");
        warriorDarkspear = new Warrior("Paal'gajuk");
        priestDarkspear = new Priest("Talanji");

        // Alliance characters
        warriorStormwind = new Warrior("Varian Wrynn");
        priestStormwind = new Priest("Anduin Wrynn");
        warriorDarnassus = new Warrior("Sildanair");
        priestDarnassus = new Priest("Tyrande Whisperwind");
        warriorIronforge = new Warrior("Muradin Bronzebeard");
        priestIronforge = new Priest("Moira Thaurissan");

        //Assemblies
        List<Class> warriorAssemblyAllowedRoles = new ArrayList<>();
        warriorAssemblyAllowedRoles.add(Warrior.class);
        warriorAssembly = new Assembly("Warriors", warriorAssemblyAllowedRoles);
        hordeWarriorsAssembly = new Assembly("Horde Warriors", warriorAssemblyAllowedRoles);

        // Non-characters entities
        molfenderTower = new Building("Training Dummy", 10000);
    }
}

public class Main {
    public static void main(String[] args) {
        Faction orgrimmarFaction = new Faction("Orgrimmar");;
        Faction stormwindFaction = new Faction("Stormwind");;
        Character priestDarkspear = new Priest("Talanji");
        Character warriorOrgrimmar = new Warrior("Garrosh Hellscream");
        Character priestStormwind = new Warrior("Anduin Wrynn");
        Character warriorStormwind = new Warrior("Varian Wrynn");
        priestStormwind.joinFaction(stormwindFaction);
        warriorStormwind.joinFaction(stormwindFaction);
        priestDarkspear.joinFaction(orgrimmarFaction);
        warriorOrgrimmar.joinFaction(orgrimmarFaction);

        System.out.println(warriorStormwind.toString());
    }
}

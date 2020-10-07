public class Main {
    public static void main(String[] args) {
        Faction hordeFaction = new Faction("Horde");;
        Faction allianceFaction = new Faction("Alliance");;
        Character priestHorde = new Priest("Talanji");
        Character warriorHorde = new Warrior("Garrosh Hellscream");
        Character priestAlliance = new Warrior("Anduin Wrynn");
        Character warriorAlliance = new Warrior("Varian Wrynn");
        priestAlliance.joinFaction(allianceFaction);
        warriorAlliance.joinFaction(allianceFaction);
        priestHorde.joinFaction(hordeFaction);
        warriorHorde.joinFaction(hordeFaction);

        System.out.println(warriorAlliance.toString());
    }
}

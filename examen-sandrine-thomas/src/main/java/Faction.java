import java.util.ArrayList;

public class Faction {

    private final String name;
    private ArrayList<Character> members;

    public Faction(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    void addMember(Character characterToAdd) {
        members.add(characterToAdd);
    }

    void removeMember(Character memberToRemove) {
        members.remove(memberToRemove);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Character> getMembers() {
        return members;
    }
}

import java.util.ArrayList;

public class Faction {

    private final String name;
    private final ArrayList<Character> members;
    private final ArrayList<Faction> friends;

    public Faction(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.friends = new ArrayList<>();
    }

    void addMember(Character characterToAdd) {
        members.add(characterToAdd);
    }

    void removeMember(Character memberToRemove) {
        members.remove(memberToRemove);
    }

    void addFriend(Faction friendFaction) {
        friends.add(friendFaction);
    }

    void removeFriend(Faction factionToRemove) {
        friends.remove(factionToRemove);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Character> getMembers() {
        return members;
    }

    public ArrayList<Faction> getFriends() {
        return friends;
    }
}

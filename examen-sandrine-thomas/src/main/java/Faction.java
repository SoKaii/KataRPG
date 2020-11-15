import java.util.ArrayList;
import java.util.List;

public class Faction {

    private String name;
    private final List<Character> members;
    private final List<Faction> friends;

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
        if(friendFaction == this){
            throw new UnsupportedOperationException("A faction can't add itself as friend faction");
        }
        friends.add(friendFaction);
    }

    void removeFriend(Faction factionToRemove) {
        friends.remove(factionToRemove);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Character> getMembers() {
        return members;
    }

    public List<Faction> getFriends() {
        return friends;
    }
}

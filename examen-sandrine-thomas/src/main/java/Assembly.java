import java.util.ArrayList;
import java.util.List;

public class Assembly extends Faction {

    private List<Class> allowedRoles;
    private Character master;

    public Assembly(String name, List<Class> allowedRoles) {
        super(name);
        this.allowedRoles = allowedRoles;
    }

    public List<Class> getAllowedRoles() {
        return allowedRoles;
    }

    @Override
    void addMember(Character characterToAdd) {
        if (this.getMembers().isEmpty()) {
            this.master = characterToAdd;
        }
        super.addMember(characterToAdd);
    }

    public void setName(String name, Character master) {
        if(this.master.equals(master)) {
            super.setName(name);
        } else {
            throw new UnsupportedOperationException("Only the master can modify the name of the assembly");
        }
    }

    @Override
    void removeMember(Character memberToRemove) {
        super.removeMember(memberToRemove);
        if(this.getMembers().isEmpty()){
            this.master = null;
        } else if (memberToRemove.equals(this.master)) {
            this.master = this.getMembers().get((int) (Math.random() * this.getMembers().size()));
        }
    }

    public Character getMaster() {
        return master;
    }
}

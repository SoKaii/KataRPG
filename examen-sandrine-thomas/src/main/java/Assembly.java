import java.util.ArrayList;

public class Assembly extends Faction {

    private ArrayList<Class> allowedRoles;
    private Character master;

    public Assembly(String name, ArrayList<Class> allowedRoles) {
        super(name);
        this.allowedRoles = allowedRoles;
    }

    public ArrayList<Class> getAllowedRoles() {
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
        if (memberToRemove.equals(this.master) && !this.getMembers().isEmpty()) {
            this.master = this.getMembers().get((int) (Math.random() * this.getMembers().size()));
        }
    }

    public void setAllowedRoles(ArrayList<Class> allowedRoles) {
        this.allowedRoles = allowedRoles;
    }

    public Character getMaster() {
        return master;
    }
}

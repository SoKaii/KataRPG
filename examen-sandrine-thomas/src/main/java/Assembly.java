import java.util.ArrayList;

public class Assembly extends Faction {

    private ArrayList<Class> allowedRoles;

    public Assembly(String name, ArrayList<Class> allowedRoles) {
        super(name);
        this.allowedRoles = allowedRoles;
    }

    public ArrayList<Class> getAllowedRoles() {
        return allowedRoles;
    }

    public void setAllowedRoles(ArrayList<Class> allowedRoles) {
        this.allowedRoles = allowedRoles;
    }
}

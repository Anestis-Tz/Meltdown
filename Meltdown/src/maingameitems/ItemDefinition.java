package maingameitems;

public class ItemDefinition implements java.io.Serializable {
 
    private String name;
    private String description;

    public ItemDefinition(String aName, String aDescription) {
        name = aName;
        description = aDescription;   
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public String getDescription() {
        return description;
    }
    

    public void setDescription(String aDescription) {
        this.description = aDescription;
    }
}

package designPatterns.elevatorSystemDesign.entities;

import designPatterns.elevatorSystemDesign.enums.Direction;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private int floorNumber;
    private List<ExternalButton> externalButtons;
    
    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.externalButtons = new ArrayList<>();
        
        externalButtons.add(new ExternalButton(floorNumber, Direction.UP));
        externalButtons.add(new ExternalButton(floorNumber, Direction.DOWN));
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }
    
    public List<ExternalButton> getExternalButtons() {
        return externalButtons;
    }
    
    public void pressUpButton() {
        externalButtons.get(0).press();
    }
    
    public void pressDownButton() {
        externalButtons.get(1).press();
    }
}

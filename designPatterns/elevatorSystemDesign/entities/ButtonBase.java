package designPatterns.elevatorSystemDesign.entities;

public abstract class ButtonBase {
    protected int floorNumber;
    protected boolean isPressed;
    
    public ButtonBase(int floorNumber){
        this.floorNumber = floorNumber;
        this.isPressed = false;
    }
    
    public abstract void press();
    
    public void unpress() {
        this.isPressed = false;
    }
    
    public int getFloorNumber() {
        return floorNumber;
    }
    
    public boolean isPressed() {
        return isPressed;
    }
}

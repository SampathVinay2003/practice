package designPatterns.elevatorSystemDesign;

import designPatterns.elevatorSystemDesign.dispatcher.Dispatcher;
import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.request.ExternalRequest;
import designPatterns.elevatorSystemDesign.request.InternalRequest;
import designPatterns.elevatorSystemDesign.request.Request;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    List<ElevatorCar> elevatorCars = new ArrayList<>();
    Dispatcher dispatcher;
    
    private static final ElevatorController instance = new ElevatorController();
    private ElevatorController(){}
    public static ElevatorController getInstance() {
        return instance;
    }
    
    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public List<ElevatorCar> getElevatorCars() {
        return elevatorCars;
    }

    public void setElevatorCars(List<ElevatorCar> elevatorCars) {
        this.elevatorCars = elevatorCars;
    }

    public void addElevatorCar(ElevatorCar elevatorCar) {
        elevatorCars.add(elevatorCar);
    }

    public void removeElevatorCar(ElevatorCar elevatorCar) {
        elevatorCars.remove(elevatorCar);
    }


    public void processRequest(Request request) {
        ElevatorCar elevatorCar = request.getElevatorCar(dispatcher, elevatorCars);
        if(elevatorCar == null){
            System.out.println("No available elevator found");
            return;
        }
        elevatorCar.addRequestToQueue(request);
        elevatorCar.move();
    }

    public ElevatorCar getElevatorCar(int i) {
        return elevatorCars.get(i);
    }
}

package designPatterns.elevatorSystemDesign;

import designPatterns.elevatorSystemDesign.dispatcher.Dispatcher;
import designPatterns.elevatorSystemDesign.entities.ElevatorCar;
import designPatterns.elevatorSystemDesign.movingStrategies.SCANStrategy;
import designPatterns.elevatorSystemDesign.request.ExternalRequest;
import designPatterns.elevatorSystemDesign.request.InternalRequest;
import designPatterns.elevatorSystemDesign.request.Request;
import designPatterns.elevatorSystemDesign.selectionStrategies.NearestCarStrategy;

public class ElevatorSystem {
    public static void main(String[] args) {
        ElevatorController controller = ElevatorController.getInstance();
        
        Dispatcher dispatcher = new Dispatcher(new NearestCarStrategy());
        controller.setDispatcher(dispatcher);
        
        ElevatorCar elevator1 = new ElevatorCar();
        elevator1.setElevatorMovingStrategy(new SCANStrategy());
        controller.addElevatorCar(elevator1);
        
        ElevatorCar elevator2 = new ElevatorCar();
        elevator2.setElevatorMovingStrategy(new SCANStrategy());
        controller.addElevatorCar(elevator2);
        
        System.out.println("=== Testing Internal Request ===");
        ElevatorCar elevatorCar = controller.getElevatorCar(0);
        controller.processRequest(new InternalRequest(0, 6, elevatorCar.getElevatorCarId()));
        
        System.out.println("\n=== Testing External Request ===");
        Request request = new ExternalRequest(5, 10, 0);
        controller.processRequest(request);
    }
}

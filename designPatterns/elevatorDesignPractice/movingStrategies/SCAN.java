package designPatterns.elevatorDesignPractice.movingStrategies;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.enums.Direction;
import designPatterns.elevatorDesignPractice.enums.ElevatorState;

import java.util.PriorityQueue;

public class SCAN implements MovingStrategy{
    private static SCAN instance;
    private SCAN(){
    }
    public static SCAN getInstance(){
        if(instance == null){
            return instance = new SCAN();
        }
        return instance;
    }

    @Override
    public void move(ElevatorCar elevatorCar) {
        System.out.println("\n=== SCAN Strategy (goes to extremes): Elevator " + elevatorCar.getId() + " ===");

        if (!elevatorCar.hasRequests()) {
            System.out.println("No requests. Elevator " + elevatorCar.getId() + " is IDLE at floor " + elevatorCar.getCurrentFloor());
            elevatorCar.setElevatorState(ElevatorState.IDLE);
            return;
        }

        elevatorCar.setElevatorState(ElevatorState.MOVING);

        while (elevatorCar.hasRequests()) {
            if (elevatorCar.getDirection() == Direction.UP) {
                processUpDirection(elevatorCar);
            } else {
                processDownDirection(elevatorCar);
            }
        }

        elevatorCar.setElevatorState(ElevatorState.IDLE);
        System.out.println("Elevator " + elevatorCar.getId() + " completed all requests. Now IDLE at floor " + elevatorCar.getCurrentFloor());
    }

    private void processUpDirection(ElevatorCar car) {
        PriorityQueue<Integer> upQueue = car.getUpQueue();

        // Process all UP requests
        while (!upQueue.isEmpty()) {
            int targetFloor = upQueue.poll();
            moveToFloor(car, targetFloor);
        }

        // SCAN: Go to top floor even if no requests there
        if (car.getCurrentFloor() < car.getMaxFloor()) {
            System.out.println("SCAN: Going to top floor " + car.getMaxFloor() + " (building extreme)");
            moveToFloor(car, car.getMaxFloor());
        }

        // Now reverse direction
        if (!car.getDownQueue().isEmpty()) {
            System.out.println("Elevator " + car.getId() + " reached top, reversing to DOWN");
            car.setDirection(Direction.DOWN);
        }
    }

    private void processDownDirection(ElevatorCar car) {
        PriorityQueue<Integer> downQueue = car.getDownQueue();

        // Process all DOWN requests
        while (!downQueue.isEmpty()) {
            int targetFloor = downQueue.poll();
            moveToFloor(car, targetFloor);
        }

        // SCAN: Go to bottom floor even if no requests there
        if (car.getCurrentFloor() > car.getMinFloor()) {
            System.out.println("SCAN: Going to bottom floor " + car.getMinFloor() + " (building extreme)");
            moveToFloor(car, car.getMinFloor());
        }

        // Now reverse direction
        if (!car.getUpQueue().isEmpty()) {
            System.out.println("Elevator " + car.getId() + " reached bottom, reversing to UP");
            car.setDirection(Direction.UP);
        }
    }


}

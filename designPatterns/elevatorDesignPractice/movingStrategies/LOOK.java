package designPatterns.elevatorDesignPractice.movingStrategies;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.enums.Direction;
import designPatterns.elevatorDesignPractice.enums.ElevatorState;

import java.util.PriorityQueue;

public class LOOK implements MovingStrategy{
    private static LOOK instance;
    private LOOK(){
    }
    public static LOOK getInstance() {
        if(instance == null){
            return instance = new LOOK();
        }
        return instance;
    }

    @Override
    public void move(ElevatorCar elevatorCar) {
        System.out.println("\n=== LOOK Strategy (reverses at last request): Elevator " + elevatorCar.getId() + " ===");

        if (!elevatorCar.hasRequests()) {
            System.out.println("No requests. Elevator " + elevatorCar.getId() + " is IDLE at floor " + elevatorCar.getCurrentFloor());
            elevatorCar.setElevatorState(ElevatorState.IDLE);
            return;
        }

        elevatorCar.setElevatorState(ElevatorState.MOVING);
        PriorityQueue<Integer> upQueue = elevatorCar.getUpQueue();
        PriorityQueue<Integer> downQueue = elevatorCar.getDownQueue();

        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            if (elevatorCar.getDirection() == Direction.UP) {
                if (!upQueue.isEmpty()) {
                    processQueue(elevatorCar, upQueue);
                }
                if (!downQueue.isEmpty()) {
                    System.out.println("Elevator " + elevatorCar.getId() + " reversing direction to DOWN");
                    elevatorCar.setDirection(Direction.DOWN);
                }
            } else {
                if (!downQueue.isEmpty()) {
                    processQueue(elevatorCar, downQueue);
                }
                if (!upQueue.isEmpty()) {
                    System.out.println("Elevator " + elevatorCar.getId() + " reversing direction to UP");
                    elevatorCar.setDirection(Direction.UP);
                }
            }
        }

        elevatorCar.setElevatorState(ElevatorState.IDLE);
        System.out.println("Elevator " + elevatorCar.getId() + " completed all requests. Now IDLE at floor " + elevatorCar.getCurrentFloor());
    }

    private void processQueue(ElevatorCar car, PriorityQueue<Integer> queue) {
        while (!queue.isEmpty()) {
            int targetFloor = queue.poll();
            moveToFloor(car, targetFloor);
        }
    }
}

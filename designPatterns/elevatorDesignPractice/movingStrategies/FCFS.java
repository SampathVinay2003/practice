package designPatterns.elevatorDesignPractice.movingStrategies;

import designPatterns.elevatorDesignPractice.entities.ElevatorCar;
import designPatterns.elevatorDesignPractice.enums.ElevatorState;

import java.util.PriorityQueue;

public class FCFS implements MovingStrategy{

    private static FCFS instance;
    private FCFS() {
    }
    public static FCFS getInstance() {
        if(instance == null){
            return instance = new FCFS();
        }
        return instance;
    }

    @Override
    public void move(ElevatorCar elevatorCar) {
        System.out.println("\n=== FCFS Strategy: Elevator " + elevatorCar.getId() + " ===");

        if (!elevatorCar.hasRequests()) {
            System.out.println("No requests. Elevator " + elevatorCar.getId() + " is IDLE at floor " + elevatorCar.getCurrentFloor());
            elevatorCar.setElevatorState(ElevatorState.IDLE);
            return;
        }

        elevatorCar.setElevatorState(ElevatorState.MOVING);
        PriorityQueue<Integer> upQueue = elevatorCar.getUpQueue();
        PriorityQueue<Integer> downQueue = elevatorCar.getDownQueue();

        while (!upQueue.isEmpty() || !downQueue.isEmpty()) {
            if (!upQueue.isEmpty()) {
                int targetFloor = upQueue.poll();
                moveToFloor(elevatorCar, targetFloor);
            }

            if (!downQueue.isEmpty()) {
                int targetFloor = downQueue.poll();
                moveToFloor(elevatorCar, targetFloor);
            }
        }

        elevatorCar.setElevatorState(ElevatorState.IDLE);
        System.out.println("Elevator " + elevatorCar.getId() + " completed all requests. Now IDLE at floor " + elevatorCar.getCurrentFloor());
    }
}

package designPatterns.observerDesignPattern.observable;

import designPatterns.observerDesignPattern.observer.ObserverNotificationAlert;

import java.util.ArrayList;
import java.util.List;

public class IphoneStockObservable implements StockObservable {
    private final List<ObserverNotificationAlert> observers = new ArrayList<>();
    private int data;

    @Override
    public List<ObserverNotificationAlert> observers() {
        return observers;
    }

    @Override
    public void addObserver(ObserverNotificationAlert observerInterface) {
        observers.add(observerInterface);
    }

    @Override
    public void removeObserver(ObserverNotificationAlert observerInterface) {
        observers.remove(observerInterface);
    }

    public void notifyObservers(){
        for(ObserverNotificationAlert observerInterface : observers){
            observerInterface.update();
        }
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public void setData(int data) {
        this.data = data;
    }

}

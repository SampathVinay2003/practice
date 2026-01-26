package designPatterns.observerDesignPattern.observable;

import designPatterns.observerDesignPattern.observer.ObserverNotificationAlert;

import java.util.List;

public interface StockObservable {

    List<ObserverNotificationAlert> observers();

    void addObserver(ObserverNotificationAlert observerInterface);
    void removeObserver(ObserverNotificationAlert observerInterface);
    void notifyObservers();
    int getData();
    void setData(int data);
}

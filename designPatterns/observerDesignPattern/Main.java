package designPatterns.observerDesignPattern;

import designPatterns.observerDesignPattern.observable.StockObservable;
import designPatterns.observerDesignPattern.observable.IphoneStockObservable;
import designPatterns.observerDesignPattern.observer.ObserverNotificationAlert;
import designPatterns.observerDesignPattern.observer.ObserverEmailNotificationAlert;
import designPatterns.observerDesignPattern.observer.ObserverMobileNotificationAlert;

//This is Behavioral Design Pattern
public class Main {
    public static  void main(String[] args) {
        StockObservable observableInterface1 = new IphoneStockObservable();
        ObserverNotificationAlert observerInterface1 = new ObserverEmailNotificationAlert(observableInterface1);
        ObserverNotificationAlert observerInterface2 = new ObserverMobileNotificationAlert(observableInterface1);
        observableInterface1.addObserver(observerInterface1);
        observableInterface1.addObserver(observerInterface2);
        observableInterface1.setData(10);
        observableInterface1.notifyObservers();
    }
}

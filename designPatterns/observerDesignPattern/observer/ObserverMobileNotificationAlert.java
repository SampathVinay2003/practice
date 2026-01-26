package designPatterns.observerDesignPattern.observer;

import designPatterns.observerDesignPattern.observable.StockObservable;

public class ObserverMobileNotificationAlert implements ObserverNotificationAlert {
    StockObservable observableInterface;

    public ObserverMobileNotificationAlert(StockObservable observableInterface) {
        this.observableInterface = observableInterface;
    }

    public void update() {
        System.out.println("Mobile Notified");
    }
}

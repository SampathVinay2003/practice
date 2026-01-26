package designPatterns.observerDesignPattern.observer;

import designPatterns.observerDesignPattern.observable.StockObservable;

public class ObserverEmailNotificationAlert implements ObserverNotificationAlert {
    StockObservable observableInterface;
    public ObserverEmailNotificationAlert(StockObservable observableInterface) {
        this.observableInterface = observableInterface;
    }

    public void update(){
        System.out.println("Email Sent");
    }
}

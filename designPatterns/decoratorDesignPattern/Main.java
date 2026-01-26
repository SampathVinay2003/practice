package designPatterns.decoratorDesignPattern;

import designPatterns.decoratorDesignPattern.baseComponent.Component;
import designPatterns.decoratorDesignPattern.baseComponent.MargarettaPizza;
import designPatterns.decoratorDesignPattern.baseComponent.Pizza;
import designPatterns.decoratorDesignPattern.decorator.MushroomDecorator;
import designPatterns.decoratorDesignPattern.decorator.TomatoDecorator;


//This is Structural Design Pattern
public class Main {
    public  static void main(String[] args) {
        Component component = new Pizza();
        System.out.println(component.getPrice());
        component = new MushroomDecorator(component);
        System.out.println(component.getPrice());
        component = new TomatoDecorator(component);
        System.out.println(component.getPrice());
        component = new MargarettaPizza();
        System.out.println(component.getPrice());
        component = new MushroomDecorator(component);
        System.out.println(component.getPrice());
        component = new TomatoDecorator(component);
        System.out.println(component.getPrice());
    }
}

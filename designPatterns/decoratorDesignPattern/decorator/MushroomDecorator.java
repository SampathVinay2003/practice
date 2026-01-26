package designPatterns.decoratorDesignPattern.decorator;

import designPatterns.decoratorDesignPattern.baseComponent.Component;

public class MushroomDecorator extends Decorator {

    public MushroomDecorator(Component component) {
        this.component = component;
    }

    @Override
    public int getPrice() {
        return this.component.getPrice()+5;
    }
}

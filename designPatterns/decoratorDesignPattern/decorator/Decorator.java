package designPatterns.decoratorDesignPattern.decorator;

import designPatterns.decoratorDesignPattern.baseComponent.Component;

public abstract class Decorator extends Component{
    protected Component component;
    public abstract int getPrice();
}

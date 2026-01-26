package designPatterns.decoratorDesignPattern.baseComponent;

public class MargarettaPizza extends Component {
    int price = 200;
    @Override
    public int getPrice() {
        return price;
    }
}

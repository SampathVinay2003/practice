package designPatterns.decoratorDesignPattern.baseComponent;

public class Pizza extends Component{

    int price = 100;
    public void method(){
        System.out.println("Pizza");
    }

    @Override
    public int getPrice() {
        return price;
    }
}

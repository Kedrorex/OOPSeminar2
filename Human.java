public class Human extends Actor {

    public Human(String name) {
        this.name= name;
    }

    @Override
    public String getName() {

        return name;
    }

    @Override
    public void setMakeOrder(boolean isMakeOrder) {
        this.isMakeOrder = isMakeOrder;

    }

    @Override
    public void setTakeOrder(boolean isTakeOrder) {
        this.isTakeOrder = isTakeOrder;

    }

    @Override
    public boolean isMakeOrder() {
        return isMakeOrder;
    }

    @Override
    public boolean isTakeOrder() {
        return isTakeOrder;
    }

    @Override
    public String toString() {
        return getName();
    }
}

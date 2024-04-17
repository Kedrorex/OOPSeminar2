import java.util.List;
import java.util.Stack;

public class Market implements QueueBehaviour, MarketBehaviour {

    private Stack<Actor> queue;

    public Market() {
        this.queue = new Stack<>();
    }

    @Override
    public void takeInQueue(Actor actor) {
        queue.add(actor);
        System.out.println("Покупатель " + actor.getName() + " добавлен(а) в очередь.");
    }

    @Override
    public void takeOrders() {
        Actor currentActor = queue.get(0);
        if (currentActor instanceof Human && !currentActor.isMakeOrder()) {
            // Логика обработки заказа
            currentActor.setMakeOrder(true);
            System.out.println(currentActor.getName() + " делает заказ.");
        }
    }

    @Override
    public void giveOrders() {
        Actor currentActor = queue.get(0);
        if (currentActor instanceof Human && currentActor.isMakeOrder()) {
            // Логика принятия заказа
            currentActor.setTakeOrder(true);
            System.out.println(currentActor.getName() + " принимает свой заказ.");
        }
    }

    @Override
    public void releaseFromQueue() {
        if (!queue.isEmpty()) {
            Actor currentActor = queue.get(0);
            if (currentActor.isTakeOrder) {
                Actor releasedActor = queue.remove(0);
                System.out.println("Покупатель " + releasedActor.getName() + " освобожден(a) из очереди.");
            }
        } else {
            System.out.println("Очередь пуста.");
        }
    }

    @Override
    public void acceptToMarket(Actor actor) {
        System.out.println("Покупатель " + actor.getName() + " принят(a) в магазин.");
    }

    @Override
    public void releaseFromMarket(List<Actor> actors) {
        for (Actor actor : actors) {
            if (actor instanceof Human && actor.isTakeOrder()) {
                System.out.println("Покупатель " + actor.getName() + " выходит из магазина после получения заказа.");
            } else {
                queue.pop(); //Освобождаем из очереди без товара
                System.out.println("Покупатель " + actor.getName() + " выходит из магазина без заказа.");
            }
        }
    }

    @Override
    public void update() {
        System.out.println("Обновление состояния магазина..."); //Какие-то действия, например обновление ассортимета
        releaseFromQueue(); //Если получил заказ - освобождаем из очереди
        showQueueStatus();  //Показываем, сколько покупателей в очереди
    }

    @Override
    public void showQueueStatus() {
        int customerNumber = queue.size();
        System.out.printf("В очереди сейчас находится %d %s \n", customerNumber, getCustomerPlural(customerNumber));

    }

    public static String getCustomerPlural(int count) {
        if (count == 1) {
            return "покупатель";
        } else if (count >= 2 && count <= 4) {
            return "покупателя";
        } else {
            return "покупателей";
        }
    }
}

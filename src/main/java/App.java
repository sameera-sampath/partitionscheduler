/**
 * Created by Sameera on 7/19/2017.
 */
public class App {

    public static void main(String[] args) {

        PartitionManager part = new PartitionManagerImpl();
        for (int i = 0; i < 10; i++) {
            part.addService(i);
            part.printList();
        }
    }
}
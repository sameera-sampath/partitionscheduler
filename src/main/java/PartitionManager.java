import java.util.List;

/**
 * Created by Sameera on 9/1/2017.
 */
public interface PartitionManager {
    public List<VNode> addService(long serviceID);

    public void printList();
}

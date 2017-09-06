import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sameera on 9/5/2017.
 */
public class PartitionManagerImpl implements PartitionManager {

    ArrayList<VNode> serviceList;
    final int startIndex = 0;
    final int endIndex = 999;

    public PartitionManagerImpl() {
        serviceList = new ArrayList<>();
    }

    @Override
    public List<VNode> addService(long serviceID) {
        VNode toAdd = new VNode(serviceID, startIndex, endIndex);
        int vnodeIndex = 0;
        boolean added = false;
        if(!serviceList.isEmpty()) {
            int services = serviceList.size() + 1;
            int nodePartitionLength = (endIndex - startIndex + 1)/services;
            int largeNodes = (endIndex - startIndex + 1) % services;
            int currentStart = startIndex;
            int currentEnd;

            for (int i = 0; i < serviceList.size(); i++) {
                currentEnd = largeNodes > 0 ? currentStart + nodePartitionLength : currentStart + nodePartitionLength - 1;
                largeNodes = largeNodes > 0 ? largeNodes - 1 : largeNodes;
                VNode vNode = serviceList.get(i);
                if(vNode.getpEnd() > currentEnd) {
                    int firstPart = currentEnd - vNode.getpStart() + 1 ;
                    int secondPart = vNode.getpEnd() - currentEnd;
                    if(secondPart > firstPart) {
                        vnodeIndex = i;
                        toAdd.setpStart(currentStart);
                        toAdd.setpEnd(currentEnd);
                        currentStart = currentEnd + 1;
                        currentEnd = largeNodes > 0 ? currentStart + nodePartitionLength : currentStart + nodePartitionLength - 1;
                        largeNodes = largeNodes > 0 ? largeNodes - 1 : largeNodes;
                        vNode.setpStart(currentStart);
                        vNode.setpEnd(currentEnd);
                        added = true;
                    } else {
                        vNode.setpStart(currentStart);
                        vNode.setpEnd(currentEnd);
                    }

                } else {
                    vNode.setpStart(currentStart);
                    vNode.setpEnd(currentEnd);
                }
                currentStart = currentEnd + 1;
            }
            if(!added) {
                currentEnd = largeNodes > 0 ? currentStart + nodePartitionLength : currentStart + nodePartitionLength - 1;
                vnodeIndex = serviceList.size();
                toAdd.setpStart(currentStart);
                toAdd.setpEnd(currentEnd);
            }
        }
        serviceList.add(vnodeIndex, toAdd);
        return serviceList;
    }

    @Override
    public void printList() {
        serviceList.forEach((vnode) -> {
            System.out.println(" start:" + vnode.getpStart() + " end:" + vnode.getpEnd() + " service:" + vnode.getServiceID());
        });
        System.out.println("-----------------------------------------------------------------");
    }
}

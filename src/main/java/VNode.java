/**
 * Created by Sameera on 9/1/2017.
 */
public class VNode {
    long serviceID;     //id of the service
    int pStart;          //partition startIndex
    int pEnd;           //partition endIndex
    public VNode(long serviceID,int pStart,int pEnd)
    {
        this.serviceID = serviceID;
        this.pStart = pStart;
        this.pEnd = pEnd;
    }

    public long getServiceID() {
        return serviceID;
    }

    public int getpStart() {
        return pStart;
    }

    public void setpStart(int pStart) {
        this.pStart = pStart;
    }

    public int getpEnd() {
        return pEnd;
    }

    public void setpEnd(int pEnd) {
        this.pEnd = pEnd;
    }
}

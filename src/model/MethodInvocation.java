package model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by SBT-Kovalev-DA on 19.03.2016.
 */
public class MethodInvocation {
    private final long tookTime;
    private final String methodName;
    private final long invocationId;

    private MethodInvocation(String methodName, long invocationId, long tookTime) {
        this.tookTime = tookTime;
        this.methodName = methodName;
        this.invocationId = invocationId;
    }

    public static MethodInvocation ofEvents(Event start, Event end) {
           return new MethodInvocation(start.getMethodName(), start.getInvocationId(), getDateDiff(start.getTime(), end.getTime()));
    }

    private static long getDateDiff(Date start, Date end) {
        return end.getTime() - start.getTime();//TimeUnit.MILLISECONDS.toMillis(diffInMillies);
    }


    public long getTookTime() {
        return tookTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public long getInvocationId() {
        return invocationId;
    }
}

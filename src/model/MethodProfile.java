package model;

/**
 * Created by SBT-Kovalev-DA on 19.03.2016.
 */
public class MethodProfile {
    private final String methodName;
    private final long min;
    private final long max;
    private final long avg;
    private final long longestInvocationId;
    private final int invocationsCount;

    public MethodProfile(String methodName, long min, long max, long avg, long longestInvocationId, int invocationsCount) {
        this.methodName = methodName;
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.longestInvocationId = longestInvocationId;
        this.invocationsCount = invocationsCount;
    }

    @Override
    public String toString() {
        return "OperationsImpl:" +
                methodName +
                " min " + min +
                ", max " + max +
                ", avg " + avg +
                ", max id " + longestInvocationId +
                ", count " + invocationsCount;
    }
}

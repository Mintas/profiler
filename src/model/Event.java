package model;

import java.util.Date;

/**
 * Created by SBT-Kovalev-DA on 19.03.2016.
 */
public class Event {
    private final Date time;
    private final EventType type;
    private final String methodName;
    private final long invocationId;

    public Event(Date time, EventType type, String methodName, long invocationId) {
        this.time = time;
        this.type = type;
        this.methodName = methodName;
        this.invocationId = invocationId;
    }

    public Date getTime() {
        return time;
    }

    public EventType getType() {
        return type;
    }

    public String getMethodName() {
        return methodName;
    }

    public long getInvocationId() {
        return invocationId;
    }
}

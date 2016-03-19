package model;

/**
 * Created by SBT-Kovalev-DA on 19.03.2016.
 */
public enum EventType {
    ENTRY("entry"),
    EXIT("exit");

    private final String title;

    EventType(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}

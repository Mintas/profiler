package service;

import model.Event;

import java.util.List;

/**
 * Created by SBT-Kovalev-DA on 19.03.2016.
 */
public interface EventParser {
    List<Event> parseEvents(String fileName);
}

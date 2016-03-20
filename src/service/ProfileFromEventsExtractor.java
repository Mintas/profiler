package service;

import model.Event;
import model.MethodProfile;

import java.util.List;

/**
 * Created by SBT-Kovalev-DA on 19.03.2016.
 */
public interface ProfileFromEventsExtractor {
    List<MethodProfile> extractFrom(List<Event> events);
}

package service;

import model.Event;
import model.MethodInvocation;
import model.MethodProfile;

import java.util.List;
import java.util.Map;

/**
 * Created by SBT-Kovalev-DA on 19.03.2016.
 */
public interface ProfileFromEventsExtractor {
    List<MethodProfile> extractFrom(List<Event> events);
}

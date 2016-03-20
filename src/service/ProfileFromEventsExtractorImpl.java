package service;

import model.Event;
import model.EventType;
import model.MethodInvocation;
import model.MethodProfile;

import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.function.Function;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static model.EventType.ENTRY;
import static model.EventType.EXIT;
import static model.MethodInvocation.ofEvents;

/**
 * Created by SBT-Kovalev-DA on 19.03.2016.
 */
public class ProfileFromEventsExtractorImpl implements ProfileFromEventsExtractor {
    @Override
    public List<MethodProfile> extractFrom(List<Event> events) {
        Map<String, List<Event>> eventsByMethodName = events.stream()
                .collect(groupingBy(Event::getMethodName));
        return eventsByMethodName.entrySet().stream()
                .map(e -> extractMethodProfile(e.getValue()))
                .collect(toList());
    }

    private MethodProfile extractMethodProfile(List<Event> events) {
        Map<Long, List<Event>> eventsByInvocations = events.stream().collect(groupingBy(Event::getInvocationId));
        List<MethodInvocation> methodInvocations = eventsByInvocations.entrySet().stream()
                .filter(e -> e.getValue().size() == 2)
                .map(e -> getMethodInvocation(e.getValue()))
                .collect(toList());
        return calculateProfile(methodInvocations);
    }

    private MethodProfile calculateProfile(List<MethodInvocation> invocations) {
        Function<List<MethodInvocation>, LongStream> toLongStream = list ->
                list.stream().mapToLong(MethodInvocation::getTookTime);
        Function<Function<LongStream, OptionalLong>, Long> calc = reducer ->
                reducer.apply(toLongStream.apply(invocations)).getAsLong();
        long sum = toLongStream.apply(invocations).sum();
        int count = invocations.size();

        long longestInvocationId = invocations.stream()
                .reduce((a, b) -> a.getTookTime() > b.getTookTime() ? a : b)
                .get().getInvocationId();

        return new MethodProfile(invocations.stream().findFirst().get().getMethodName(),
                calc.apply(LongStream::min),
                calc.apply(LongStream::max),
                sum / count, longestInvocationId, count);
    }

    private MethodInvocation getMethodInvocation(List<Event> sameInvocationEvents) {
        return ofEvents(fromInvocationEvents(sameInvocationEvents, ENTRY),
                fromInvocationEvents(sameInvocationEvents, EXIT));
    }

    private Event fromInvocationEvents(List<Event> sameInvocationEvents, EventType type) {
        return sameInvocationEvents.stream().filter(event -> event.getType().equals(type)).findFirst().get();
    }


}

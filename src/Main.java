import model.Event;
import model.MethodProfile;
import service.EventParser;
import service.LogEventParserImpl;
import service.ProfileFromEventsExtractor;
import service.ProfileFromEventsExtractorImpl;

import java.util.List;

/**
 * Created by SBT-Kovalev-DA on 19.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        String test = "C:\\Users\\SBT-Kovalev-DA\\logg.log";
        String givenTest = "C:\\Users\\SBT-Kovalev-DA\\Downloads\\test\\testlog.log";
        EventParser parser = new LogEventParserImpl();
        List<Event> events = parser.parseEvents(givenTest);
        //System.out.println(events.size());

        ProfileFromEventsExtractor extractor = new ProfileFromEventsExtractorImpl();
        List<MethodProfile> methodProfiles = extractor.extractFrom(events);
        methodProfiles.forEach(mp -> System.out.println(mp.toString()));
    }
}

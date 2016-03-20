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
        if (args.length > 0) {
            String path = args[0];

            EventParser parser = new LogEventParserImpl();
            List<Event> events = parser.parseEvents(path);

            ProfileFromEventsExtractor extractor = new ProfileFromEventsExtractorImpl();
            List<MethodProfile> methodProfiles = extractor.extractFrom(events);
            methodProfiles.forEach(mp -> System.out.println(mp.toString()));

        }
    }
}

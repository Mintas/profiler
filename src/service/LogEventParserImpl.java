package service;

import model.Event;
import model.EventType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SBT-Kovalev-DA on 19.03.2016.
 */
public class LogEventParserImpl implements EventParser {
    private static final String SEPARATOR = " ";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss,SSS");

    @Override
    public List<Event> parseEvents(String fileName) {
        List<Event> events = new ArrayList<>();
        Reader reader = null;
        try {
            reader = doParse(fileName, events);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tryCloseReader(reader);
        }
        return events;
    }

    private Reader doParse(String fileName, List<Event> events) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while ((line = br.readLine()) != null) {
            try {
                events.add(parseEvent(line));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return br;
    }

    private Event parseEvent(String line) throws ParseException {
        String[] logLine = line.split(SEPARATOR);
        String[] nameAndId = logLine[5].replaceAll("\\(|\\)", "").split(":");
        return new Event(DATE_FORMAT.parse(logLine[0]),
                EventType.valueOf(logLine[3].toUpperCase()),
                nameAndId[0],
                Integer.valueOf(nameAndId[1]));
    }

    private void tryCloseReader(Reader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

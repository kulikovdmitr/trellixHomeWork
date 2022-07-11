package exercise3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CallLog {

    private ArrayList<Phone> phones = new ArrayList<>();

    public static void main (String[] args) throws FileNotFoundException {

        final String DATE_AND_PHONE_REGEXP = "(([01]\\d|2[0-3]):([0-5]\\d):([0-5]\\d))(.)(\\S{10,12})";
        final int SECONDS_IN_HOUR = 3600;
        final int SECONDS_IN_MINUTE = 60;
        final int COST_CALL_LESS_FIVE_MINUTES = 3;
        final int COST_CALL_MORE_FIVE_MINUTES = 150;

        CallLog callLog = new CallLog();

        try {
            File myObj = new File("src/main/java/exercise3/filename.txt");
            Scanner myReader = new Scanner(myObj);

            List<Object> list = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                final Pattern patternTimeAndPhone = Pattern.compile(DATE_AND_PHONE_REGEXP);
                final Matcher matcherTimeAndPhone = patternTimeAndPhone.matcher(data);

                if (matcherTimeAndPhone.find()) {
                    int duration = Integer.parseInt(matcherTimeAndPhone.group(2)) * SECONDS_IN_HOUR + Integer.parseInt(matcherTimeAndPhone.group(3)) * SECONDS_IN_MINUTE + Integer.parseInt(matcherTimeAndPhone.group(4));

                    if (duration < 300) {
                        Phone p1 = new Phone(matcherTimeAndPhone.group(6), duration,duration * COST_CALL_LESS_FIVE_MINUTES);
                        callLog.phones.add(p1);
                    }

                    if (duration == 300) {
                        Phone p1 = new Phone(matcherTimeAndPhone.group(6), duration, ((duration / SECONDS_IN_MINUTE) * COST_CALL_MORE_FIVE_MINUTES));
                        callLog.phones.add(p1);
                    }

                    if (duration > 300) {
                        double highPrice = ((double) duration / SECONDS_IN_MINUTE);
                        Phone p1 = new Phone(matcherTimeAndPhone.group(6), duration, (int) Math.ceil(highPrice) * COST_CALL_MORE_FIVE_MINUTES);
                        callLog.phones.add(p1);
                    }
                }
            }
            myReader.close();
            System.out.println(callLog.phones);

            /*
            for (int i = 0; i < callLog.phones.size(); i++) {
            ----> Here I wanted to iterate the list with phones, find duplicates and sum up the time and remove duplicates
            so that there is an object with a phone number, total time and total cost.
            But I couldn't figure out how to do it.

            The second option, using a separate function - getDuplicatesWithIteration.
            But I didn't figure out how to iterate over the required fields through the getter and setter
            }
            */

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static <E> List<E> getDuplicatesWithIteration(List<E> callLog) {

        Set<E> duplicates = new HashSet<>();
        for (int i = 0; i < callLog.size(); i++) {
            E callObject = callLog.get(i);
            if (callObject == null) continue;
            for (int j = 0; j < callLog.size(); j++) {
                if (i == j) continue;
                E e2 = callLog.get(j);
                if (callObject.equals(e2)) {
                    duplicates.add(e2);
                }
            }
        }
        return new ArrayList<>(duplicates);
    }
}

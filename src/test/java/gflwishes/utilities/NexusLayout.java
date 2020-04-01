package gflwishes.utilities;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.spi.LoggingEvent;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NexusLayout extends HTMLLayout implements Configurations {

    public NexusLayout() {
        super();
    }

    public String format(LoggingEvent event) {

        final String regexForMessage = "\\s*<\\s*td\\s*\\w*=\\\"[\\\\bMessage\\\\b]*\\\"*>" +
                "\\s*[A-Za-z&0-9,()\\[\\]{} =:;.@<>_\\\\'/\\\"\\s]*\\s*<\\s*/td\\s*>\\s*";

        final String regexForTime = "\\s*<\\s*tr\\s*>\\s*<\\s*td\\s*>\\s*(\\d*)\\s*<\\s*/td\\s*>";

        String details = super.format(event);

        Pattern pattern = Pattern.compile(regexForMessage);
        Matcher matcher = pattern.matcher(details);

        if (!matcher.find()) return details;

        StringBuffer buffer = new StringBuffer(details);
        buffer.replace(matcher.start(0), matcher.end(0), changeLiterals(matcher.group()));

        pattern = Pattern.compile(regexForTime);
        matcher = pattern.matcher(buffer);

        if (!matcher.find()) return buffer.toString();

        buffer.replace(matcher.start(1), matcher.end(1),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(event.timeStamp),
                        TimeZone.getDefault().toZoneId()).
                        format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm:ss a")));


        String log = buffer.toString();
        StringBuilder newBuffer = new StringBuilder();

        for (int s = 0; s < log.split("\n").length; s++) {
            if (log.split("\n")[s].contains("<td")) {
                if (event.getLevel().toString().equalsIgnoreCase("WARN")) {
                    String temp = log.split("\n")[s];
                    temp = temp.substring(0, temp.indexOf(">") + 1) + "<strong><font color=\"orange\">" +
                            temp.substring(temp.indexOf(">") + 1, temp.lastIndexOf("<")) + "</font></strong>" +
                            temp.substring(temp.lastIndexOf("<"));
                    newBuffer.append(temp);
                } else if (event.getLevel().toString().equalsIgnoreCase("ERROR")) {
                    String temp = log.split("\n")[s];
                    temp = temp.substring(0, temp.indexOf(">") + 1) + "<strong><font color=\"red\">" +
                            temp.substring(temp.indexOf(">") + 1, temp.lastIndexOf("<")) + "</font></strong>" +
                            temp.substring(temp.lastIndexOf("<"));
                    newBuffer.append(temp);
                } else {
                    if (log.split("\n")[s].contains("_TC")) {
                        if (log.split("\n")[s].contains("Pass")) {
                            String temp = log.split("\n")[s];
                            temp = temp.substring(0, temp.indexOf(">") + 1) + "<strong><font color=\"green\">" +
                                    temp.substring(temp.indexOf(">") + 1, temp.lastIndexOf("<")) + "</font></strong>" +
                                    temp.substring(temp.lastIndexOf("<"));
                            newBuffer.append(temp);
                        } else {
                            String temp = log.split("\n")[s];
                            temp = temp.substring(0, temp.indexOf(">") + 1) + "<strong>" +
                                    temp.substring(temp.indexOf(">") + 1, temp.lastIndexOf("<")) + "</strong>" +
                                    temp.substring(temp.lastIndexOf("<"));
                            newBuffer.append(temp);
                        }
                    } else {
                        String temp = log.split("\n")[s];
                        temp = temp.substring(0, temp.indexOf(">") + 1) + "<strong>" +
                                temp.substring(temp.indexOf(">") + 1, temp.lastIndexOf("<")) + "</strong>" +
                                temp.substring(temp.lastIndexOf("<"));
                        newBuffer.append(temp);
                    }
                }
            } else {
                newBuffer.append(log.split("\n")[s]);
            }
        }
        return newBuffer.toString();
    }

    private String changeLiterals(String log) {
        if (log.contains("&gt;") || log.contains(">")) log = log.replace("&gt;", ">");
        if (log.contains("&lt;") || log.contains("<")) log = log.replace("&lt;", "<");
        if (log.contains("img")) log = log.replace("..", "ExtentReports");

        return log;
    }

}

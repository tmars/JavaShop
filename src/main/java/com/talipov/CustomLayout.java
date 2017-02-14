package com.talipov;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class CustomLayout  extends PatternLayout
{
    public String format(LoggingEvent event) {
        MailObject obj = (MailObject) event.getMessage();

        StringBuffer sb = new StringBuffer();

        sb.append("<mail>");
        sb.append("<email>").append(obj.getEmail()).append("</name>");
        sb.append("<subject>").append(obj.getSubject()).append("</subject>");
        sb.append("<content>").append(obj.getContent()).append("</content>");
        sb.append("</mail>");
        sb.append("\n");

        return sb.toString();
    }
}
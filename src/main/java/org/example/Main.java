package org.example;

import com.aspose.email.MailMessage;
import com.aspose.email.SaveOptions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.*;
import javax.naming.*;
public class Main {

    public static void main(String[] args) {
        // Load the MSG file to be converted. Please place the msg file where the src is located.
        MailMessage message = MailMessage.load("message.msg");
        message.save("Saved File.html", SaveOptions.getDefaultHtml());

        Element document = Jsoup.parse(message.getHtmlBody());
        Elements table = document.select("table");
        Elements rows = table.select("tr:not(:empty)");
        Elements headers = new Elements(rows.remove(0)).select("td:not(:empty)");

        EmailParsedTable parsedMappings = new EmailParsedTable();
        for (Element header : headers) {
            parsedMappings.getHeaders().add(header.text());
        }

        for (Element row : rows) {
            Elements values = row.select("td:not(:empty)");
            if (values.size() == parsedMappings.getHeaders().size()) {
                List<String> order = new ArrayList<>();
                for (Element value : values) {
                    order.add(value.text());
                }
                parsedMappings.getRows().add(order);
            } else {
                // To do: log that rows missing from processed, one or more columns missing
            }
        }

        // for testing purpose
        for (String str : parsedMappings.getHeaders())
            System.out.println(str);
        for (List<String> order : parsedMappings.getRows())
            System.out.println(order);
    }
}
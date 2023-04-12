package org.example;

import com.aspose.email.MailMessage;
import com.aspose.email.SaveOptions;
import com.sun.mail.imap.DefaultFolder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.*;
public class Main {

    public static void main(String[] args) {
        File inputFile = new File("D:\\Dev\\ZeroBeta\\le-tools\\tools\\ParseEmailHTMLtables\\src\\main\\java\\org\\example\\message.msg");

        // Load the MSG file to be converted
        MailMessage message = MailMessage.load("message.msg");
        // Save MSG as a HTML
        message.save("Saved File.html", SaveOptions.getDefaultHtml());

        Element table = Jsoup.parse("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "  font-family: arial, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "  border: 1px solid #dddddd;\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "  background-color: #dddddd;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>HTML Table</h2>\n" +
                "\n" +
                "<table>\n" +
                "  <tr>\n" +
                "    <th>Company</th>\n" +
                "    <th>Contact</th>\n" +
                "    <th>Country</th>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Alfreds Futterkiste</td>\n" +
                "    <td>Maria Anders</td>\n" +
                "    <td>Germany</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Centro comercial Moctezuma</td>\n" +
                "    <td>Francisco Chang</td>\n" +
                "    <td>Mexico</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Ernst Handel</td>\n" +
                "    <td>Roland Mendel</td>\n" +
                "    <td>Austria</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Island Trading</td>\n" +
                "    <td>Helen Bennett</td>\n" +
                "    <td>UK</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Laughing Bacchus Winecellars</td>\n" +
                "    <td>Yoshi Tannamuri</td>\n" +
                "    <td>Canada</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>Magazzini Alimentari Riuniti</td>\n" +
                "    <td>Giovanni Rovelli</td>\n" +
                "    <td>Italy</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "\n");

        Elements rows = table.select("tr");
        Elements ths = rows.select("th");

        EmailParsedTable parsedMappings = new EmailParsedTable();
        for (Element th : ths) {
            parsedMappings.getHeaders().add(th.text());
        }

        for (Element row : rows) {
            Elements tds = row.select("td");
            if (tds.size() == parsedMappings.getHeaders().size()) {
                List<String> order = new ArrayList<>();
                for (Element td : tds) {
                    order.add(td.text());
                }
                parsedMappings.getRows().add(order);
            }
        }

        // for testing purpose
        for (String str : parsedMappings.getHeaders())
            System.out.println(str);

        for (List<String> order : parsedMappings.getRows()) {
            System.out.println(order);
        }

    }
}
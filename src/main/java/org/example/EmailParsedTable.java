package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EmailParsedTable {
    private List<String> headers;
    private List<List<String>> rows;

    public EmailParsedTable() {
        headers = new ArrayList<>();
        rows = new LinkedList<>();
    }
    public EmailParsedTable (List<String> headers, List<List<String>> rows) {
        this.setHeaders(headers);
        this.setRows(rows);
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<List<String>> getRows() {
        return rows;
    }

    public void setRows(List<List<String>> rows) {
        this.rows = rows;
    }
}

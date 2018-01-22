package com.spoloborota.piano.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

public class RequestSender {
    private static final Logger log = Logger.getLogger(RequestSender.class.getName());
    private StringBuilder baseUrl = new StringBuilder("https://api.stackexchange.com/2.2/search?site=stackoverflow&pagesize=100");

    public InputStream sendRequest() {
        try {
            URL url = new URL(baseUrl.toString());
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            if (request.getResponseCode() == 200) {
                return getWrappedInputStream(request.getInputStream(), "gzip".equalsIgnoreCase(request.getContentEncoding()));
            } else {
                log.log(Level.SEVERE, "Error " + request.getResponseCode());
                return null;
            }
        } catch (IOException ioe) {
            log.log(Level.SEVERE, ioe, () -> "Error while communicztiong with StackExchange");
            return null;
        }
    }

    public void addParam(String paramName, String value) {
        baseUrl.append('&' + paramName + '=' + value);
    }

    private InputStream getWrappedInputStream(InputStream is, boolean gzip) throws IOException {
        return gzip ? new BufferedInputStream(new GZIPInputStream(is)) : new BufferedInputStream(is);
    }


}

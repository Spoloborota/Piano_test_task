package com.spoloborota.piano.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonParser {
    private static final Logger log = Logger.getLogger(JsonParser.class.getName());
    private static GsonBuilder builder = new GsonBuilder();
    static {
        builder.registerTypeAdapter(SearchResponse.class, new SearchResponseDeserializer());
    }

    public SearchResponse parse (InputStream is) throws UnsupportedEncodingException {
        Reader rdr = new InputStreamReader(is, "UTF-8");
        JsonElement element = new com.google.gson.JsonParser().parse(rdr);
        if (!element.isJsonObject()) {
            log.log(Level.SEVERE, () -> "Bad JSON-format in the response from StackExchange");
            return null;
        }

        Gson gson = builder.create();
        return gson.fromJson(element, SearchResponse.class);
    }
}

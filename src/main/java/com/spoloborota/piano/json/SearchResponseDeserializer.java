package com.spoloborota.piano.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.spoloborota.piano.common.Question;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchResponseDeserializer implements JsonDeserializer<SearchResponse> {

    public static final String HAS_MORE = "has_more";
    public static final String ITEMS = "items";
    public static final String OWNER = "owner";
    public static final String DISPLAY_NAME = "display_name";
    public static final String CREATION_DATE = "creation_date";
    public static final String IS_ANSWERED = "is_answered";
    public static final String LINK = "link";
    public static final String TITLE = "title";
    @Override
    public SearchResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        SearchResponse response = new SearchResponse();
        List<Question> lst = new ArrayList<>();
        response.setQuestions(lst);

        JsonObject jsonObject = json.getAsJsonObject();
        boolean has_more = jsonObject.get(HAS_MORE).getAsBoolean();
        response.setHas_more(has_more);
        for (JsonElement questionElement : jsonObject.getAsJsonArray(ITEMS)) {
            JsonObject questionObject = questionElement.getAsJsonObject();
            JsonObject ownerObject = questionObject.getAsJsonObject(OWNER);

            String ownerDisplaName = ownerObject.get(DISPLAY_NAME).getAsString();
            Date creationDate = new Date(questionObject.get(CREATION_DATE).getAsLong() * 1000);
            boolean isAnswered = questionObject.get(IS_ANSWERED).getAsBoolean();
            String link = questionObject.get(LINK).getAsString();
            String title = questionObject.get(TITLE).getAsString();

            lst.add(new Question(ownerDisplaName, creationDate, title, link, isAnswered));
        }

        return response;
    }
}

package com.spoloborota.piano.json;

import com.spoloborota.piano.common.Question;

import java.util.List;

public class SearchResponse {
    List<Question> questions;
    boolean has_more;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }
}

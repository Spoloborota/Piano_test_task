package com.spoloborota.piano;

import java.util.Date;

import com.google.code.stackexchange.schema.Question;

public class QuestionMinimal {
	final private String title;
	final private Date creationDate;
	final private boolean isAnswered;
	final private String author;
	final private String originalLink;
	
	public QuestionMinimal(Question question) {
		title = question.getTitle();
		creationDate = question.getCreationDate();
		isAnswered = question.getIsAnswered();
		author = question.getOwner().getDisplayName();
		originalLink = question.getLink();
	}

	public String getTitle() {
		return title;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public boolean getIsAnswered() {
		return isAnswered;
	}

	public String getAuthor() {
		return author;
	}

	public String getOriginalLink() {
		return originalLink;
	}
	
	
}

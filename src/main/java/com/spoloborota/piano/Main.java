package com.spoloborota.piano;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.code.stackexchange.client.query.AdvanceSearchApiQuery;
import com.google.code.stackexchange.client.query.SearchApiQuery;
import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.Question;
import com.google.code.stackexchange.schema.StackExchangeSite;

public class Main {



	public static void main(String[] args) {
		String query = "XXX";
		int counter = 0;
		StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory.newInstance("L*rbfGkq0Hpx5r8owzTuzg((", StackExchangeSite.STACK_OVERFLOW);
		AdvanceSearchApiQuery searchApiQuery = queryFactory.newAdvanceSearchApiQuery();//newSearchApiQuery();
		List<QuestionMinimal> result = new ArrayList<>();
		int page = 0;
		Question qq;
		
		PagedList<Question> lst = searchApiQuery.withQuery("title:" + query).withPaging(new Paging(page, 100)).list();
		List<QuestionMinimal> temp = lst.parallelStream().map(q -> new QuestionMinimal(q)).collect(Collectors.toList());
		if (temp != null && !temp.isEmpty()) {
			result.addAll(temp);
		}
		while(lst.hasMore()) {
			lst = searchApiQuery.withQuery("title:" + query).withPaging(new Paging(++page, 100)).list();
			temp = lst.parallelStream().map(q -> new QuestionMinimal(q)).collect(Collectors.toList());
			if (temp != null && !temp.isEmpty()) {
				result.addAll(temp);
			}
		}
		System.out.println(counter);
	}
}


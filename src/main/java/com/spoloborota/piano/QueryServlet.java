package com.spoloborota.piano;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.stackexchange.client.query.SearchApiQuery;
import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.Question;
import com.google.code.stackexchange.schema.StackExchangeSite;

import static com.spoloborota.piano.Constants.*;

public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(QueryServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory.newInstance(null, StackExchangeSite.STACK_OVERFLOW);
		SearchApiQuery searchApiQuery = queryFactory.newSearchApiQuery();
		String query = request.getParameter(QUERY_TEXT);
		String pageNumber = request.getParameter(PAGE_NUMBER);

		if (query != null) {
			int page = 1;
			if (pageNumber != null) {
				try {
					page = Integer.parseInt(pageNumber);
				} catch (NumberFormatException nfe) {
					log.log(Level.SEVERE, nfe, () -> "Bad number format of " + pageNumber);
				}
			}
			PagedList<Question> temp = searchApiQuery
					.withInTitle(query)
					.withPaging(new Paging(page, 100))
					.list();
			List<QuestionMinimal> lst = temp.parallelStream().map(QuestionMinimal::new).collect(Collectors.toList());

			request.setAttribute(RESULT_LIST, lst);
			request.setAttribute(CURRENT_QUERY_TEXT, query);
			if (temp.hasMore()) {
				request.setAttribute(NEXT_PAGE_NUMBER, page + 1);
			}
			if (page > 1) {
				request.setAttribute(PREVIOUS_PAGE_NUMBER, page - 1);
			}
			request.getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher( "/WEB-INF/main.jsp").forward(request, response);
		}
	}
}

package com.spoloborota.piano.http;

import com.spoloborota.piano.json.JsonParser;
import com.spoloborota.piano.json.SearchResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.spoloborota.piano.common.Constants.*;

public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(QueryServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestSender requestSender = new RequestSender();
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
			requestSender.addParam(IN_TITLE_PARAM, query);
			requestSender.addParam(PAGE_NUMBER, "" + page);

			InputStream is = requestSender.sendRequest();
			if (is != null) {
				SearchResponse searchResponse = new JsonParser().parse(is);
				if (searchResponse != null) {
					request.setAttribute(RESULT_LIST, searchResponse.getQuestions());
					request.setAttribute(CURRENT_QUERY_TEXT, query);
					if (searchResponse.isHas_more()) {
						request.setAttribute(NEXT_PAGE_NUMBER, page + 1);
					}
					if (page > 1) {
						request.setAttribute(PREVIOUS_PAGE_NUMBER, page - 1);
					}
				}
				is.close();
			}
		}
		request.getRequestDispatcher( "/WEB-INF/main.jsp").forward(request, response);
	}
}

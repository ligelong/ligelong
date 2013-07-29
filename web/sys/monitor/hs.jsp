<%
	org.springframework.web.context.WebApplicationContext webApplicationContext = 
            				org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	org.hibernate.SessionFactory bf2 = (org.hibernate.SessionFactory)webApplicationContext.getBean("sessionFactory");
	org.hibernate.stat.Statistics stat = bf2.getStatistics();
	out.print("<h1>getQueries</h1>");
	String[] querys = stat.getQueries();
	for(String query:querys) {
		out.print(query + "<br/>");
		org.hibernate.stat.QueryStatistics qs = stat.getQueryStatistics(query);
		out.print("execution average time="+qs.getExecutionAvgTime() + "<br/>");
		out.print("execution count="+qs.getExecutionCount() + "<br/>");
		out.print("execution max time="+qs.getExecutionMaxTime() + "<br/>");
		out.print("execution min time="+qs.getExecutionMinTime() + "<br/>");
		out.print("execution row count="+qs.getExecutionRowCount() + "<br/>");
		out.print("cache hit count="+qs.getCacheHitCount() + "<br/>");
		out.print("cache miss count="+qs.getCacheMissCount() + "<br/>");
		out.print("cache put count="+qs.getCachePutCount() + "<br/>");
	}
	out.print("<hr/>");
	out.print("<h1>getEntityNames</h1>");
	String[] names = stat.getEntityNames();
	for(String name:names) {
		out.print("entity=" + name + "<br/>");
		org.hibernate.stat.EntityStatistics es = stat.getEntityStatistics(name);
		out.print("fetch count="+es.getFetchCount() + "<br/>");
		out.print("insert count="+es.getInsertCount() + "<br/>");
		out.print("load count="+es.getLoadCount() + "<br/>");
		out.print("delete count="+es.getDeleteCount() + "<br/>");
		out.print("failure count="+es.getOptimisticFailureCount() + "<br/>");
		out.print("update count="+es.getUpdateCount() + "<br/>");
	}
	out.print("<hr/>");
%>

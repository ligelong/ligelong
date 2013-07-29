<%!
	private static final String urlS = "http://buy.ubox.cn/#/vm/1017/8/2";
	private static final String dir = "/tmp/eat/";
	private static final java.text.DateFormat df = new java.text.SimpleDateFormat("");
%><%@page contentType="text/html;charset=utf-8"%><%@page import="java.io.*,java.net.*,org.apache.commons.io.FileUtils"%><%
	URL url = new URL(urlS);
	URLConnection urlConnection = url.openConnection();
	BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
	String line = null;
	StringBuilder sb = new StringBuilder();
	while ((line = br.readLine()) != null) {
		sb.append(line + "\n");
	}
	br.close();
	String content = sb.toString();
	File f = new File(dir + "c");
	if(!f.exists()) {
		f.createNewFile();
	}
	String old = FileUtils.readFileToString(f);
	StringBuilder sb1 = new StringBuilder();
	StringBuilder sb2 = new StringBuilder();
	for(int i=0; i<old.length(); i++) {
		if(old.charAt(i)!=content.charAt(i)) {
			sb1.append(old.charAt(i));
			if(content.length()<=i) {
				sb2.append(content.charAt(i));
			}
		}
	}
	FileUtils.write(new File(dir + "c"), sb.toString(), false);
%><table border="1" width="80%" height="600px"><tr><td><%=sb1%></td><td><%=sb2%></td></tr></table>

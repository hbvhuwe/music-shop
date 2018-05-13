<%@page import="javax.swing.table.TableModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
	<%
		String tableName = (String) (session.getAttribute("tableName"));
		TableModel tableModel = (TableModel) (session.getAttribute("tableModel"));
	%>
	<b> Таблиця <%=tableName%></b>
	<table border="1/home/vova/Projects/TPP/DBScripts/db_creation.sql">
		<tr>
			<%
					int nCol = tableModel.getColumnCount();
					for (int i = 0; i < nCol; i++) {
						String h = tableModel.getColumnName(i);
				%>
			<th width="100"><%=h%></th>
			<%
					}
				%>
		</tr>
		<%
				int nRow = tableModel.getRowCount();
				for (int r = 0; r < nRow; r++) {
			%>
		<tr>
			<%
					for (int j = 0; j < nCol; j++) {
							Object obj = tableModel.getValueAt(r, j);
							String str = "null";
							if (obj != null)
								str = tableModel.getValueAt(r, j).toString();
				%>
			<td width="100" align="center"><%=str%></td>
			<%
					}
				%>
		</tr>
		<%
				}
			%>
	</table>
</body>
</html>
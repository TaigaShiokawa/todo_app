<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.TodoDTO"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<% TodoDTO todo = (TodoDTO)request.getAttribute("todo"); %>
<body>
	<form action="<%=request.getContextPath()%>/update-servlet" method="post">
	Todo : <input type="text" name="todo" value="<%=todo.getTodo()%>"><br>
	TimeLimit : <input type="date" name="timeLimit" value="<%=todo.getTimeLimit() %>"><br>
	Priority : 
	<select name="priority" value="<%=todo.getPriority()%>">
	<option value="1">最高優先度</option>
	<option value="2">高優先度</option>
	<option value="3" selected>中優先度</option>
	<option value="4">低優先度</option>
	<option value="5">最低優先度</option>
	</select>
	<button type="submit">更新する</button>
	</select>
	<input type="hidden" name="id" value="<%=todo.getId()%>">
	</form>
</body>
</html>
<%@ 
	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="java.util.List, java.util.ArrayList, model.dto.TodoDTO, content.Parameters, model.PriorityMap"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Todo</h1>
	<% List<TodoDTO> todoList = (List)request.getAttribute("todoList"); %>
	<% for(TodoDTO todo : todoList) { %>
	<h3><%=todo.getTodo() %> : <%=todo.getTimeLimit() %> : <%=PriorityMap.getPriority(todo.getPriority()) %></h3>
	<a href="<%=request.getContextPath()%>/update-servlet?<%=todo.getUser_id()%>">Update</a>
	<a href="<%=request.getContextPath()%>/delete-servlet?<%=Parameters.TODO_ID%>=<%=todo.getId()%>">Delete</a>
	<% } %>
	<hr>
		<form action="insert-servlet" method="post">
		Todo : <input type="text" name="todo" placeholder="Todo here..." required><br>
		TimeLimit : <input type="date" name="timeLimit"><br>
		Priority : 
		<select name="priority">
		<option value="1">Top Priority</option>
		<option value="2">Priority</option>
		<option value="3" selected>Medium Priority</option>
		<option value="4">Low Priority</option>
		<option value="5">Lowest Priority</option>
		</select><br>
		<button type="submit">add</button><br>
		</form>
		
		<form action="sort-servlet" method="get">
		<button type="submit">Top</button>
		</form>
		
		<form action="sortdesc-servlet" method="get">
		<button type="submit">Low</button>
		</form>
		
		<form action="priority-servlet" method="get">
		<button type="submit">Top Priority</button>
		</form>
		
		<form action="lowpriority-servlet" method="get">
		<button type="submit">Low Priority</button>
		</form>
		
		<a href="<%=request.getContextPath()%>/logout-servlet">Logout</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<header>
	<h1>Todo App</h1>
		<ul>
			<li><a href="<%=request.getContextPath()%>/register-servlet">Signup</a></li>
			<li><a href="<%=request.getContextPath()%>/login-servlet">Login</a></li>
		</ul>
</header>

	<h2>Register Form</h2>
		<% String notification = (String)session.getAttribute("NOTIFICATION"); %>
		<% if(notification != null) { %>
		<p><%= notification %></p>
		<% session.removeAttribute("NOTIFICATION"); %>
		<% } %>
		<form action="<%=request.getContextPath() %>/register-servlet" method="post">
			User Name : <input type="text" name="username" placeholder="User Name" required><br>
			Password : <input type="password" name="password" placeholder="Password" required><br>
			<button type="submit">Submit</button>
		</form>
</body>
</html>
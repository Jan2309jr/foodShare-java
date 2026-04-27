<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
<h2>Add Food</h2>

<form action="AddFoodServlet" method="post">
    Food: <input type="text" name="food"><br>
    Quantity: <input type="text" name="quantity"><br>
    Location: <input type="text" name="location"><br>
    Expiry: <input type="datetime-local" name="expiry"><br>

    <button type="submit">Add</button>
</form>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: arina
  Date: 21.10.2026
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Save</title>
  </head>
  <body>
  <form action = "/save" method = "post">
    <c:forEach begin="1" end="${usersToAdd}" var="i">
      <h3>User ${i}</h3>

      <input name = "first_name" placeholder="Your name" required/>
      <input name = "last_name" placeholder="Your surname" required/>
      <input name = "middle_name" placeholder="Your patronymic" required/>
      <input name = "age" type="number" min="1" placeholder="Your age" required/>
      <input name = "phone_number" placeholder="Your phone" required/>
      <input name = "email" placeholder="Your email" required/>

      <br><br>
    </c:forEach>
    <input type = "submit" value = "Save"/>
  </form>
  </body>
</html>

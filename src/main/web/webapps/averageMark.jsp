<html>
<head>
    <title>Update</title>
</head>
<body>
<section>
    <jsp:useBean id="person" scope="request" type="org.pupil.Structures.Person"/>
    <form method="post" action="person?action=submit">
        <dt>
            <input type="number" name="classroom" value="&{person.classroom}" placeholder="&{person.classroom}"></dt>
        <dt>AverageRating:</dt>
        <%@page import="org.pupil.DataGroups.ClassroomDataGroups" %>
        <%
            int classroom = Integer.parseInt(request.getAttribute("classroom").toString());
            double averageRating = new ClassroomDataGroups().getAverageRating(classroom);
        %>
        <p>AverageRating: <%= averageRating%>
        </p>
    </form>
</section>
</body>
</html>
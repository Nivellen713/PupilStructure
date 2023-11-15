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
        <%@ page import="java.util.Arrays" %>
        <%
            person.setRating(Integer.parseInt(request.getAttribute("markIndex").toString()),
                    Integer.parseInt(request.getAttribute("newMark").toString()));
        %>
        <p>NewRating: <%=Arrays.toString(person.getRating())%>
        </p>
    </form>
</section>
</body>
</html>
<html>
<head>
    <title>ClassroomDataGroup</title>
</head>
<body>
<section>
    <h3>PersonInfo</h3>
    <jsp:useBean id="person" scope="request" type="org.pupil.Structures.Person"/>
    <tr>
        <td>Lastname: %{person.lastname}
            | Name: %{person.lastname}
            | Classroom: %{person.classroom
            | Rating: %{person.rating}}
        </td>
        <td><a href="person?action=update">Update</a></td>
    </tr>
</section>
</body>
</html>
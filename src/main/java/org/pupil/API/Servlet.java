package org.pupil.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pupil.DataGroups.ClassroomDataGroups;
import org.pupil.Structures.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/*"})
public class Servlet extends HttpServlet {
    Person person;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setAttribute("person", person);
        if ("averageMark".equals(action)) {
            request.getRequestDispatcher("/averageMark.jsp").forward(request, response);
        }
        request.setAttribute("person", person);
        request.getRequestDispatcher("/person.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String markIndex = null;
        String newMark = null;
        if ("setMark".equals(action)) {
            person = new ClassroomDataGroups().getPerson(request.getParameter("lastname"),
                    request.getParameter("name"),
                    Integer.parseInt(request.getParameter("group")));
            markIndex = request.getParameter("markIndex");
            newMark = request.getParameter("newMark");
        }
        request.setAttribute("markIndex", markIndex);
        request.setAttribute("newMark", newMark);
        request.getRequestDispatcher("/markUpdate.jsp").forward(request, response);
    }

}

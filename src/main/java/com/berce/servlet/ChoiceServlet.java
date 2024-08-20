package com.berce.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/choose")
public class ChoiceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choice = request.getParameter("choice");

        String resultMessage;
        if ("1".equals(choice)) {
            resultMessage = "Hero Survived!";
        } else if ("2".equals(choice)) {
            resultMessage = "Hero is Dead!";
        } else {
            resultMessage = "Invalid choice!";
        }

        request.setAttribute("result", resultMessage);
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }
}

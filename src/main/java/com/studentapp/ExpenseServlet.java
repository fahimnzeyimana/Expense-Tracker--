package com.studentapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/expenses")
public class ExpenseServlet extends HttpServlet {
    private List<String> expenses = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String expense = req.getParameter("expense");
        if (expense != null && !expense.trim().isEmpty()) {
            expenses.add(expense);
        }
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("expenses", expenses);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}

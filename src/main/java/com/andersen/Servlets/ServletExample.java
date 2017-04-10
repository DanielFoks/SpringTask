package com.andersen.Servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class ServletExample extends HttpServlet {

    private void processRequest(HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        out.println("<html>\n" +
        "<head>\n" +
        "<meta charset=\"utf-8\">\n" +
        "<title>index</title>\n" +
        "</head>\n" +
        "<body>\n" +
                "<form id=\"form1\" name=\"form1\" method=\"post\">"+
        "<ul>\n" +
        "<li value=\"qwe\"><input name=\"submit\" type=\"submit\" value=\"Administrator\"></li>\n" +
        "<li value=\"qwe\"><input name=\"submit\" type=\"submit\" value=\"Customer\"></li>\n" +
        "</ul>\n" +
                "</form>"+
        "</body>\n" +
        "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRequestAdmin(HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>\n" +
                    "<head>\n" +
                    "<meta charset=\"utf-8\">\n" +
                    "<title>Admin</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"form1\" name=\"form1\" method=\"post\">"+
                    "<ul>\n" +
                    "<li value=\"qwe\"><input name=\"submit\" type=\"submit\" value=\"Customers\"></li>\n" +
                    "<li value=\"qwe\"><input name=\"submit\" type=\"submit\" value=\"Goods\"></li>\n" +
                    "<li value=\"qwe\"><input name=\"submit\" type=\"submit\" value=\"Orders\"></li>\n" +
                    "</ul>\n" +
                    "</form>"+
                    "</body>\n" +
                    "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext contex = new ClassPathXmlApplicationContext("applicationContext.xml");
        getServletContext().setAttribute("springContext",contex);
        processRequest(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("submit").equals("Administrator")){
            processRequestAdmin(resp);
        }else if (req.getParameter("submit").equals("Customers")){
            resp.sendRedirect("/CustomerAdmin");
        }else if(req.getParameter("submit").equals("Goods")){
            resp.sendRedirect("/GoodAdmin");
        }else if (req.getParameter("submit").equals("Orders")){
            resp.sendRedirect("/OrderAdmin");
        }else if (req.getParameter("submit").equals("Customer")){
            resp.sendRedirect("/Customer");
        }
    }
}

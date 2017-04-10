package com.andersen.Servlets;

import com.andersen.Entities.Good;
import com.andersen.Entities.OrderT;
import com.andersen.Repositories.OrderRepository;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/OrderAdmin")
public class OrderAdminServlet extends HttpServlet {

    private List<OrderT> orders;
    private ApplicationContext context;
    private OrderRepository orderRepository;

    private void processRequestAdmin(HttpServletResponse response){
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>\n" +
                    "<head>\n" +
                    "<meta charset=\"utf-8\">\n" +
                    "<title>CustomerAdmin</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"form1\" name=\"form1\" method=\"post\">"+
                    "<ul>\n" +
                    getOrders()+
                    "</ul>\n" +
                    "</form>"+
                    "</body>\n" +
                    "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getOrders() {
        StringBuilder stringBuilder = new StringBuilder();
        orders = orderRepository.findAll();

        int tmp = 0;
        for (OrderT order:orders){
            stringBuilder.append("<li>" + order.getCustomer().getFio()+" Goods(");
            for (Good good:order.getGoods()){
                stringBuilder.append(good.getTitle()+",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length());
            stringBuilder.append(")<input name=\"delete%\" "+ tmp +" type=\"submit\" value=\"delete\"></li>");
            tmp++;
        }
        return stringBuilder.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context = (ApplicationContext) getServletContext().getAttribute("springContext");
        orderRepository = context.getBean(OrderRepository.class);
        processRequestAdmin(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (int i = 0; i < orders.size(); i++)
            if (req.getParameter("delete%" + i) != null && req.getParameter("delete%" + i).equals("delete")) {
                orderRepository.delete(orders.get(i));
            }

        processRequestAdmin(resp);
    }
}

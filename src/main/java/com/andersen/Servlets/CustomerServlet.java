package com.andersen.Servlets;

import com.andersen.Entities.Customer;
import com.andersen.Entities.Good;
import com.andersen.Entities.OrderT;
import com.andersen.Repositories.CustomerRepository;
import com.andersen.Repositories.GoodRepository;
import com.andersen.Repositories.OrderRepository;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/Customer")
public class CustomerServlet extends HttpServlet{

    private ApplicationContext context;
    private CustomerRepository customerRepository;
    private GoodRepository goodRepository;
    private OrderRepository orderRepository;

    private List<Good> goods;
    private Customer customerOrder;

    boolean enter = false;

    private void processRequest(HttpServletResponse response){
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
                    "<li><input name=\"fio\" type=\"text\"/> FIO</li>\n" +
                    "<li><input name=\"pass\" type=\"password\"/> Password</li>\n" +
                    "<li><input name=\"submit\" type=\"submit\" value=\"Enter\"/></li>"+
                    "</ul>\n" +
                    "</form>"+
                    "</body>\n" +
                    "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRequestEnter(HttpServletResponse response,HttpServletRequest request){
        enter = true;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>\n" +
                    "<head>\n" +
                    "<meta charset=\"utf-8\">\n" +
                    "<title>Customer</title>\n" +
                    successOrder(request)+
                    "</head>\n" +
                    "<body>\n" +
                    "<form id=\"form1\" name=\"form1\" method=\"post\">"+
                    "<ul>\n" +
                    getGoods()+
                    "<br>"+
                    "<li><input name=\"submit\" type=\"submit\" value=\"New Order\"/></li>"+
                    "</ul>\n" +
                    "</form>"+
                    "</body>\n" +
                    "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getGoods() {
        StringBuilder stringBuilder = new StringBuilder();
        goods = goodRepository.findAll();
        int tmp = 0;
        for (Good good:goods){
            stringBuilder.append("<li>" + good.getTitle() +" ("+good.getPrice()+")  <label>\n" +
                    "    <input type=\"checkbox\" name=\"add_box\" value=\""+tmp+"\"> add</label></li>");
            tmp++;
        }
        return stringBuilder.toString();
    }

    private String successOrder(HttpServletRequest request){
        StringBuilder stringBuilder = new StringBuilder();
        if (request.getAttribute("successAdded")!=null&&(Boolean) request.getAttribute("successAdded")){
            stringBuilder.append("<script>alert('The new order was added')</script>");
        }
        return stringBuilder.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context = (ApplicationContext) getServletContext().getAttribute("springContext");
        customerRepository = context.getBean(CustomerRepository.class);
        goodRepository = context.getBean(GoodRepository.class);
        orderRepository = context.getBean(OrderRepository.class);

        goods = goodRepository.findAll();
        processRequest(resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customerList = customerRepository.findAll();


        if (!enter) {
            for (Customer customer : customerList) {
                if (req.getParameter("fio") != null && req.getParameter("pass") != null
                        && req.getParameter("fio").equals(customer.getFio()) && customer.checkPassword(req.getParameter("pass").toCharArray())) {
                    customerOrder = customer;
                    processRequestEnter(resp,req);
                    break;
                }
            }
        }

        if (enter){
            String[] addGoods = req.getParameterValues("add_box");
            if (addGoods != null && addGoods.length != 0){

                Set<Good> goodSet = new HashSet<>();
                for(String str:addGoods){
                    goodSet.add(goods.get(Integer.parseInt(str)));
                }
                OrderT orderT = new OrderT();
                orderT.setCustomer(customerOrder);
                orderT.setGoods(goodSet);
                orderRepository.save(orderT);

                req.setAttribute("successAdded",Boolean.TRUE);

                processRequestEnter(resp,req);

            }else {
                processRequest(resp);
            }

        }else {
            processRequest(resp);
        }

    }
}

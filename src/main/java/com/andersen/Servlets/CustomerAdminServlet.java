package com.andersen.Servlets;

import com.andersen.Entities.Customer;
import com.andersen.Entities.OrderT;
import com.andersen.Repositories.CustomerRepository;
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

@WebServlet("/CustomerAdmin")
public class CustomerAdminServlet extends HttpServlet {

    private List<Customer> customers;
    private ApplicationContext context;
    private CustomerRepository customerRepository;

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
                    getCustomers()+
                    "<li>FIO <input name=\"fio\" type=\"text\"/> PASSWORD <input name=\"pass\" type=\"password\"/><input name=\"submit\" type=\"submit\" value=\"ADD\"/></li>"+
                    "</ul>\n" +
                    "</form>"+
                    "</body>\n" +
                    "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCustomers(){
        StringBuilder stringBuilder = new StringBuilder();
        customers = customerRepository.findAll();

        int tmp = 0;
        for (Customer customer:customers){
            stringBuilder.append("<li>" + customer.getFio() + "   <input name=\"delete%" + tmp + "\" type=\"submit\" value=\"delete\"></li>");
            tmp++;
        }
        return stringBuilder.toString();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context = (ApplicationContext) getServletContext().getAttribute("springContext");
        customerRepository = context.getBean(CustomerRepository.class);
        processRequestAdmin(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderRepository orderRepository = context.getBean(OrderRepository.class);
        List<OrderT> orderTs;
        for (int i = 0; i < customers.size(); i++)
            if (req.getParameter("delete%" + i) != null && req.getParameter("delete%" + i).equals("delete")) {
                orderTs = customers.get(i).getOrderTs();
                orderTs.forEach(orderRepository::delete);
                customerRepository.delete(customers.get(i));
            }

        if (req.getParameter("submit")!=null&&req.getParameter("submit").equals("ADD")){
            if (req.getParameter("fio")!=null&&req.getParameter("pass")!=null){
                Customer customer = new Customer();
                customer.setFio(req.getParameter("fio"));
                customer.setPassword(req.getParameter("pass"));
                customerRepository.save(customer);
            }
        }
        processRequestAdmin(resp);
    }
}

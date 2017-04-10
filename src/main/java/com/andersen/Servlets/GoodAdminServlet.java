package com.andersen.Servlets;

import com.andersen.Entities.Good;
import com.andersen.Repositories.GoodRepository;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GoodAdmin")
public class GoodAdminServlet extends HttpServlet {

    private List<Good> goods;
    private ApplicationContext context;
    private GoodRepository goodRepository;

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
                    getGoods()+
                    "<li>TITLE <input name=\"title\" type=\"text\"/> PRICE <input name=\"price\" type=\"number\"/><input name=\"submit\" type=\"submit\" value=\"ADD\"/></li>"+
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
            stringBuilder.append("<li>" + good.getTitle() +" ("+good.getPrice()+")<input name=\"delete%" + tmp + "\" type=\"submit\" value=\"delete\"></li>");
            tmp++;
        }
        return stringBuilder.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        context = (ApplicationContext) getServletContext().getAttribute("springContext");
        goodRepository = context.getBean(GoodRepository.class);
        processRequestAdmin(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (int i = 0; i < goods.size(); i++)
            if (req.getParameter("delete%" + i) != null && req.getParameter("delete%" + i).equals("delete")) {
                goodRepository.delete(goods.get(i));
            }

        if (req.getParameter("submit")!=null&&req.getParameter("submit").equals("ADD")){
            if (req.getParameter("title")!=null&&req.getParameter("price")!=null){
                Good good = new Good();
                good.setTitle(req.getParameter("title"));
                good.setPrice(Integer.parseInt(req.getParameter("price")));
                goodRepository.save(good);
            }
        }
        processRequestAdmin(resp);
    }
}

package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = "/ticketOrder")
public class TicketOrderServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    public TicketOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        TicketOrder ticket = (TicketOrder) req.getAttribute("ticket");
        Movie mostBoughtMovie = (Movie) req.getAttribute("mostBoughtMovie");

        context.setVariable("ticket", ticket);
        context.setVariable("mostBoughtMovie", mostBoughtMovie);

        springTemplateEngine
                .process("orderConfirmation.html",
                        context,
                        resp.getWriter());
    }
}

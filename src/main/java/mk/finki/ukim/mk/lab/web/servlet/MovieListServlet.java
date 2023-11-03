package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;

import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import mk.finki.ukim.mk.lab.service.MovieService;

import java.io.IOException;

@WebServlet(urlPatterns = "")
public class MovieListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final MovieService movieService;
    private final TicketOrderService ticketOrderService;
    public MovieListServlet(SpringTemplateEngine springTemplateEngine, MovieService movieService, TicketOrderService ticketOrderService) {
        this.springTemplateEngine = springTemplateEngine;
        this.movieService = movieService;
        this.ticketOrderService = ticketOrderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("movies", movieService.listAll());
        Movie mostBoughtMovie =  movieService.mostBoughtMovie();
        context.setVariable("mostBoughtMovie", mostBoughtMovie);

        springTemplateEngine.process(
                "listMovies.html",
                context,
                resp.getWriter()
        );

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("radio");
        int tickets = Integer.parseInt(req.getParameter("numTickets"));

        TicketOrder ticket = ticketOrderService.placeOrder(title, "Stefan Vasilev", req.getRemoteAddr(), tickets);

        RequestDispatcher dispatcher = req.getRequestDispatcher("ticketOrder");
        req.setAttribute("ticket", ticket);

        dispatcher.forward(req,resp);
    }
}

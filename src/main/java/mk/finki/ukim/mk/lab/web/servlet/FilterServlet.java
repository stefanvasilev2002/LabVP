package mk.finki.ukim.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.impl.MovieServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = "/filter")
public class FilterServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final MovieService movieService;
    public FilterServlet(SpringTemplateEngine springTemplateEngine, MovieService movieService) {
        this.springTemplateEngine = springTemplateEngine;
        this.movieService = movieService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(exchange);

        context.setVariable("movies",
                movieService.searchMovies(req.getParameter("textFilter"),
                        req.getParameter("ratingFilter")));
        springTemplateEngine.process("listMovies.html", context, resp.getWriter());
    }
}

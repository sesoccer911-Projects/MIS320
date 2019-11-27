/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dvdrental.Actor;
import dvdrental.Category;
import dvdrental.Film;
import dvdrental.FilmController;
import dvdrental.FilmHelper;
import dvdrental.HibernateUtil;
import dvdrental.Language;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Ben
 */
public class AutoCompleteForward extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    FilmHelper help;
    private ServletContext context;
    Session session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AutoCompleteForward</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AutoCompleteForward at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public Session initialize() {
        Session sess = null;
        try {
            sess = HibernateUtil.getSessionFactory().getCurrentSession();
        } catch (org.hibernate.HibernateException he) {
            sess = HibernateUtil.getSessionFactory().openSession();
        }
        return sess;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.getParameter("film"));
        String idS = request.getParameter("film");
        Film film = null;
        try {
            session = initialize();
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Film as film where film.title = '" + idS + "'");
            film = (Film) q.uniqueResult();
            tx.commit();
            tx = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(film.getTitle());
        int langID = film.getLanguageByLanguageId().getLanguageId().intValue();
        Language language = null;
        try {
            session = initialize();
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Language as lang where lang.languageId=" + langID);
            language = (Language) q.uniqueResult();
            tx.commit();
            tx = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(language.getName());
        String lang = language.getName();
        List<Actor> actorList = null;
        try {
            session = initialize();
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Actor as actor where actor.actorId in (select filmActor.actor.actorId from FilmActor as filmActor where filmActor.film.filmId='" + film.getFilmId() + "')");
            actorList = (List<Actor>) q.list();
            tx.commit();
            tx = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuffer totalCast = new StringBuffer();
        for (int i = 0; i < actorList.size(); i++) {
            Actor actor = (Actor) actorList.get(i);
            totalCast.append(actor.getFirstName());
            totalCast.append(" ");
            totalCast.append(actor.getLastName());
            totalCast.append("  ");
        }
        List<Category> categoryList = null;
        try {
            session = initialize();
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Category as category where category.categoryId in (select filmCat.category.categoryId from FilmCategory as filmCat where filmCat.film.filmId='" + film.getFilmId() + "')");
            categoryList = (List<Category>) q.list();
            tx.commit();
            tx = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuffer totalCate = new StringBuffer();
        for (int i = 0; i < categoryList.size(); i++) {
            Category actor = (Category) categoryList.get(i);
            totalCate.append(actor.getName());
            totalCate.append(" ");
        }
        request.setAttribute("title", film.getTitle());
        request.setAttribute("description", film.getDescription());
        request.setAttribute("genres", totalCate);
        request.setAttribute("actors", totalCast);
        request.setAttribute("length", film.getLength());
        request.setAttribute("language", lang);
        request.setAttribute("releaseYear", film.getReleaseYear());
        request.setAttribute("rentalDuration", film.getRentalDuration());
        request.setAttribute("rentalRate", film.getRentalRate());
        request.setAttribute("replacementCost", film.getReplacementCost());
        request.setAttribute("rating", film.getRating());
        request.setAttribute("specialFeatures", film.getSpecialFeatures());
        request.setAttribute("lastUpdate", film.getLastUpdate());
        request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

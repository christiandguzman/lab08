/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.NotesDBException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Note;
import services.NoteService;

/**
 *
 * @author 683676
 */
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get session
        HttpSession session = request.getSession();
        List<Note> noteList = (List<Note>) session.getAttribute("noteList");

        //check if databaase have been loaded
        if (noteList != null) {
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/notes.jsp")
                    .forward(request, response);
        }
        NoteService service = new NoteService();
        try {
            noteList = service.getAll();
            session.setAttribute("noteList", noteList);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/notes.jsp")
                    .forward(request, response);
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/notes.jsp")
                    .forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            NoteService service = new NoteService();

            String action = request.getParameter("action");

            switch (action) {
                case "delete": {
                    int noteid = Integer.parseInt(request.getParameter("noteid"));
                    service.delete(noteid);

                }
                break;

                case "edit":
                    int noteid = Integer.parseInt(request.getParameter("noteid"));
                    Note note = service.get(noteid);
                    request.setAttribute("contents", note.getContents());
                    session.setAttribute("selectedNote", note);
                    
                    break;

                case "add":

                    String contents = request.getParameter("contents");
                    service.insert(contents);

                    break;
                case "update":
                    note = (Note)session.getAttribute("selectedNote");
                    String newContents = request.getParameter("contents");
                    service.update(note.getNoteid(), newContents);
                    session.setAttribute("selectedNote", null);
                    
                    break;
            }

            List<Note> noteList = service.getAll();
            session.setAttribute("noteList", noteList);
            getServletContext()
                    .getRequestDispatcher("/WEB-INF/notes.jsp")
                    .forward(request, response);
        } catch (NotesDBException ex) {
            Logger.getLogger(NoteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

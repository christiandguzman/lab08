<%-- 
    Document   : notes
    Created on : Oct 31, 2018, 5:28:08 PM
    Author     : 683676
--%>


<%@page import="models.Note"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    List<Note> noteList = (List<Note>) session.getAttribute("noteList");
    session.setAttribute("noteList", noteList);
    Note selectedNote = (Note) session.getAttribute("selectedNote");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes</title>
    </head>
    <body>
        <h1>Notes!</h1>

        <table>
            <tr>
                <th>Note_ID</th>
                <th>Date_Created</th> 
                <th>Contents</th>
                <th></th>
                <th></th>

            </tr>
            <c:forEach var="note" items="${noteList}" varStatus="i" >
                <tr>
                    <td>${note.noteId}</td> 
                    <td>${note.dateCreated}</td>
                    <td>${note.contents}</td>
                <form method="post" action="note">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="noteid" value="${note.noteId}">
                    <td><input type="submit" value="Delete"></td>
                </form>
                <form method="post" action="note">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="noteid" value="${note.noteId}">
                    <td><input type="submit" value="Edit"></td>
                </form>
                </tr>
            </c:forEach>
        </table>
        <h1>Add Note</h1>
        <br>
        <c:if test="${selectedNote == null}">

            <form method="post" action="note">
                <textarea name="contents" rows="4" cols="50"></textarea>
                <input type="hidden" name="action" value="add">
                <br>
                <input type="submit" value="add">
            </form>
        </c:if>
        <c:if test="${selectedNote != null}">
            <form method="post" action="note">
                <textarea name="contents" rows="4" cols="50"></textarea>
                <input type="hidden" name="action" value="update">
                <br>
                <input type="submit" value="Save">
            </form>
        </c:if>
    </body>
</html>


<%-- 
    Document   : update_hc_handler
    Created on : Dec 5, 2021, 12:34:58 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="WEB-INF/health_center_tag.tld" prefix="vaccination" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EDIT</title>
         
         <!-- bootstrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"></script>
        <script src="https://use.fontawesome.com/a042e00ef5.js"></script>
        <title>Edit</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <a class="navbar-brand" href="health_center_list.jsp">&lAarr; Back</a>
        </nav>
        <div class="container">
            <div class="row">
               <div class="col-md-10 mr-auto ml-auto">
                   <p></p>
                   <vaccination:update 
                       table="health_centre"
                       newvalues='<%= "name=\'" + request.getParameter("hc") + "\',location=\'" + request.getParameter("loc") %>'
                       where='<%= request.getParameter("id") %>'
                   />
                 </div>
            </div>
        </div>
    </body>
</html>
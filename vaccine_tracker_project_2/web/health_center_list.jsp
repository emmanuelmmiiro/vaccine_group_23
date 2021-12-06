<%-- 
    Document   : health_center_list
    Created on : Dec 5, 2021, 11:01:44 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="WEB-INF/health_center_tag.tld" prefix="vaccination" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <!-- bootstrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js"></script>
        <script src="https://use.fontawesome.com/a042e00ef5.js"></script>
        <title>Health Centers</title>
    </head>
    <body>
         <nav class="navbar navbar-expand-lg navbar-light bg-light">
             <a class="navbar-brand" href="index.html">VACCINE TRACKER</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.html">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="health_center_list.jsp">Health Centers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">Vaccines</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">Bookings</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">People</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="row">
               <div class="col-md-12 mr-auto ml-auto">
                <center>
                    <h5 id="loading-msg">LOADING, PLEASE WAIT</h5>
                </center>
                <div class="row" style="margin-top: 40px">
                    <div class="col-md-6">
                        <h3>HEALTH CENTER LIST</h3>
                    </div>
                    <div class="col-md-6">
                        <a style="margin-left:20%" type="button" class="btn btn-sm btn-secondary" href="register_health_center.jsp">New Health Center</a>
                        <a type="button" class="btn btn-sm btn-secondary" href="needs_assessment.jsp">Needs Assessment</a>
                    </div>
                </div>
                
                <p></p>
                <vaccination:select table="health_centre" displayformat="table"/>
                
              </div>
            </div>
        </div>
                
        <script>
           $(window).on('load', function () {
              $('#loading-msg').hide();
           });
        </script>
                
    </body>
</html>

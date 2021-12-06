<%-- 
    Document   : register_health_center
    Created on : Dec 5, 2021, 11:20:36 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Register Health Center</title>
    </head>
    <body>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <a class="navbar-brand" href="health_center_list.jsp">&lAarr; Back</a>
      </nav>
      <div class="container">
          <div class="row">
              <div class="col-md-6 mr-auto ml-auto">
                    <center><h3>REGISTER HEALTH</h3></center>
                    <form action="register_hc_handler.jsp" method="post">
                        <label>Health Center</label><br>
                        <input class="form-control" type="text" name="name" required=""><br>
                        <label>Location</label><br>
                        <input class="form-control" type="text" name="location" required=""><br>
                        <input type="submit" class="btn btn-sm btn-secondary" value="Save">
                    </form>
              </div>
          <//div>
      </div>     
    </body>
</html>


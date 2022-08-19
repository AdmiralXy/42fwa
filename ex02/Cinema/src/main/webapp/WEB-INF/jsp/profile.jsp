<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:layout>
    <jsp:attribute name="head">
      <style>
          .profile-text {
              text-align: left;
              font-size: 26px;
          }

          .profile-email {
              font-size: 18px;
          }

          .table-scrollable {
              max-height: 22vh;
              overflow-y: auto;
          }

          .table-scrollable::-webkit-scrollbar {
              width: 5px
          }

          .table-scrollable::-webkit-scrollbar-track {
              background: #f1f1f1
          }

          .table-scrollable::-webkit-scrollbar-thumb {
              background: #888
          }

          .table-scrollable::-webkit-scrollbar-thumb:hover {
              background: #555
          }
      </style>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
        <jsp:useBean id="auths" class="java.util.ArrayList" scope="application" />
            <div class="row">
                <div class="col-md-4">
                    <img class="profile-avatar img-fluid" src="https://via.placeholder.com/750" alt="none"/>
                    <div class="mt-3">
                        <input class="form-control" type="file">
                    </div>
                </div>
                <div class="col-md-8">
                    <p class="profile-text profile-name mb-0">John Doe</p>
                    <p class="profile-text profile-email">john@doe.com</p>
                    <c:if test="${not empty auths}">
                        <div class="table-scrollable">
                            <table class="table table-bordered table-dark">
                                <thead>
                                <tr>
                                    <th scope="col">Date</th>
                                    <th scope="col">Time</th>
                                    <th scope="col">IP</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="auth" items="${auths}">
                                    <tr>
                                        <td>${auth.date}</td>
                                        <td>${auth.time}</td>
                                        <td>${auth.address}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>
                <div class="col-md-12 pt-3">
                    <div class="table-scrollable">
                        <table class="table table-bordered table-dark">
                            <thead>
                            <tr>
                                <th scope="col">File name</th>
                                <th scope="col">Size</th>
                                <th scope="col">MIME</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>avatar.jpg</td>
                                <td>106Kb</td>
                                <td>image/jpg</td>
                            </tr>
                            <tr>
                                <td>123.png</td>
                                <td>6Kb</td>
                                <td>image/png</td>
                            </tr>
                            <tr>
                                <td>avatar.jpg</td>
                                <td>106Kb</td>
                                <td>image/jpg</td>
                            </tr>
                            <tr>
                                <td>123.png</td>
                                <td>6Kb</td>
                                <td>image/png</td>
                            </tr>
                            <tr>
                                <td>avatar.jpg</td>
                                <td>106Kb</td>
                                <td>image/jpg</td>
                            </tr>
                            <tr>
                                <td>123.png</td>
                                <td>6Kb</td>
                                <td>image/png</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>

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

          .profile-avatar {
              min-width: 100%;
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
        <jsp:useBean id="auths" class="java.util.ArrayList" scope="application" />
        <jsp:useBean id="user" scope="request" class="com.admiralxy.cinema.models.User" />
        <jsp:useBean id="images" scope="request" class="java.util.ArrayList" />
        <c:set var="profileImage" scope="request" value="${pageContext.request.contextPath}/cinema/images/${user.image}" />

        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <img class="profile-avatar img-fluid" src="${empty user.image ? 'https://via.placeholder.com/750' : profileImage}" alt="none"/>
                    <form method="POST" action="${pageContext.request.contextPath}/images" enctype="multipart/form-data">
                        <div class="mt-3">
                            <input class="form-control" type="file" name="image">
                            <input class="form-control mt-2" type="submit" value="Upload">
                        </div>
                    </form>
                </div>
                <div class="col-md-8">
                    <p class="profile-text profile-name mb-0">${user.firstName} ${user.lastName}</p>
                    <p class="profile-text profile-email">${user.email}</p>
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
                <c:if test="${not empty images}">
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
                                <c:forEach var="image" items="${images}">
                                    <tr>
                                        <td>
                                            <a target="_blank" href="${pageContext.request.contextPath}/cinema/images/${image.name}">${image.originalName}</a>
                                        </td>
                                        <td>${image.size}</td>
                                        <td>${image.mime}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:layout>

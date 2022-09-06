<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
        <div class="form-signin">
            <form method="POST" action="${pageContext.request.contextPath}/signUp">
                <h1 class="h3 mb-3 fw-normal">Sign up</h1>

                <div class="form-floating pb-2">
                    <input type="text" class="form-control" name="first_name" id="floatingFirstName" placeholder="John" required>
                    <label for="floatingFirstName">First name</label>
                </div>
                <div class="form-floating pb-2">
                    <input type="text" class="form-control" name="last_name" id="floatingLastName" placeholder="Doe" required>
                    <label for="floatingLastName">Last name</label>
                </div>
                <div class="form-floating pb-2">
                    <input type="text" class="form-control" name="phone_number" id="floatingNumber" placeholder="+7 (990) 555-35-35" required>
                    <label for="floatingNumber">Phone number</label>
                </div>
                <div class="form-floating pb-2">
                    <input type="email" class="form-control" name="email" id="floatingEmail" placeholder="john@doe.com" required>
                    <label for="floatingEmail">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" name="password" id="floatingPassword" placeholder="Password" required>
                    <label for="floatingPassword">Password</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit">Sign up</button>
            </form>
        </div>
    </jsp:body>
</t:layout>

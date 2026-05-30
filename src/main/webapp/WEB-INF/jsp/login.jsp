<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Nebula Rift Login</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>">
</head>
<body>
<div class="login-page">
    <div class="login-card">
        <h1>Player Login</h1>
        <p class="login-subtitle">Enter command credentials to launch the mission.</p>
        <form method="post" action="<c:url value='/login'/>">
            <div class="input-group">
                <label for="username">Username</label>
                <input id="username" name="username" type="text" autocomplete="username" required>
            </div>
            <div class="input-group">
                <label for="password">Password</label>
                <input id="password" name="password" type="password" autocomplete="current-password" required>
            </div>
            <c:if test="${not empty error}">
                <div class="form-error">${error}</div>
            </c:if>
            <button class="btn" type="submit">Launch</button>
        </form>
    </div>
</div>
</body>
</html>

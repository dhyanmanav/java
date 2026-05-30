<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Nebula Rift - Hangar</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>">
</head>
<body>
<div class="page">
    <header class="topbar">
        <div class="logo">Nebula Rift</div>
        <div class="player-pill">Welcome, ${playerName}</div>
        <nav class="nav">
            <a href="<c:url value='/'/>">Command Deck</a>
            <a href="<c:url value='/hangar'/>" class="active">Hangar</a>
            <a href="<c:url value='/servlet/state'/>">Servlet JSON</a>
        </nav>
    </header>

    <section class="hero slim">
        <div class="hero-copy">
            <h1>Fleet Hangar</h1>
            <p>Configure loadouts, review ship roles, and plan tactical upgrades.</p>
        </div>
    </section>

    <section class="grid hangar-grid">
        <div class="card">
            <h2>Fleet Overview</h2>
            <c:forEach items="${ships}" var="ship">
                <div class="stat-row">
                    <div class="stat-title">${ship.callsign} · ${ship.shipClass.label}</div>
                    <div class="stat-sub">Primary: ${ship.primaryWeapon.label}</div>
                    <div class="stat-values">
                        <span>SPD ${ship.speed}</span>
                        <span>SHD ${ship.shield}</span>
                        <span>HULL ${ship.hull}</span>
                    </div>
                    <div class="stat-tags">
                        <c:forEach items="${ship.loadout}" var="weapon">
                            <span class="tag">${weapon.label}</span>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="card">
            <h2>Unlocked Upgrades</h2>
            <c:forEach items="${upgrades}" var="upgrade">
                <div class="stat-row slim">
                    <div class="stat-title">${upgrade}</div>
                </div>
            </c:forEach>
        </div>
    </section>

    <footer class="footer">
        Return to the <a href="<c:url value='/'/>">Command Deck</a> to launch the arcade mission.
    </footer>
</div>
</body>
</html>

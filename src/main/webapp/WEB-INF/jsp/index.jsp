<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Nebula Rift - Space Shooter</title>
    <link rel="stylesheet" href="<c:url value='/css/styles.css'/>">
</head>
<body>
<div class="page full">
    <header class="topbar">
        <div class="logo">Nebula Rift</div>
        <div class="player-pill">Welcome, ${playerName}</div>
        <nav class="nav">
            <a href="<c:url value='/hangar'/>">Hangar</a>
            <a href="<c:url value='/servlet/state'/>">Servlet JSON</a>
        </nav>
    </header>

    <section class="game-stage">
        <div class="game-frame">
            <div class="game-hud">
                <div class="hud-title">Pilot ${playerName} online</div>
                <div class="hud-sub">Difficulty: <span id="difficultyLabel">${state.difficultySummary}</span></div>
            </div>
            <canvas id="gameCanvas"></canvas>
        </div>
        <div class="controls">Move: WASD / Arrow Keys · Fire: Space</div>
    </section>
</div>
<script src="<c:url value='/js/game.js'/>"></script>
</body>
</html>

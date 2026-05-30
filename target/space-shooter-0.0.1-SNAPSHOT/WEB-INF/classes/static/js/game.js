(() => {
    const canvas = document.getElementById("gameCanvas");
    if (!canvas) {
        return;
    }
    const ctx = canvas.getContext("2d");
    const keys = new Set();
    const bullets = [];
    const enemies = [];
    const player = { x: 80, y: 160, size: 14, speed: 4 };
    let isDead = false;
    let lastSpawn = 0;
    let score = 0;
    let width = canvas.clientWidth;
    let height = canvas.clientHeight;

    function clamp(value, min, max) {
        return Math.min(Math.max(value, min), max);
    }

    function resize() {
        const scale = window.devicePixelRatio || 1;
        width = canvas.clientWidth;
        height = canvas.clientHeight;
        canvas.width = width * scale;
        canvas.height = height * scale;
        ctx.setTransform(scale, 0, 0, scale, 0, 0);
    }

    function shoot() {
        bullets.push({ x: player.x + player.size, y: player.y, speed: 6 });
    }

    function spawnEnemy() {
        const size = 12 + Math.random() * 10;
        enemies.push({
            x: width,
            y: 20 + Math.random() * (height - 40),
            size,
            speed: 1.4 + Math.random() * 1.8
        });
    }

    function drawPlayer() {
        ctx.save();
        ctx.translate(player.x, player.y);
        ctx.fillStyle = "#7bb3ff";
        ctx.strokeStyle = "#1b2d4f";
        ctx.lineWidth = 2;
        ctx.beginPath();
        ctx.moveTo(player.size + 8, 0);
        ctx.lineTo(-player.size, -player.size * 0.9);
        ctx.lineTo(-player.size * 0.6, 0);
        ctx.lineTo(-player.size, player.size * 0.9);
        ctx.closePath();
        ctx.fill();
        ctx.stroke();

        ctx.fillStyle = "#4f8bff";
        ctx.beginPath();
        ctx.moveTo(-player.size * 0.4, -player.size * 0.6);
        ctx.lineTo(player.size * 0.3, -2);
        ctx.lineTo(-player.size * 0.2, -2);
        ctx.closePath();
        ctx.fill();

        ctx.beginPath();
        ctx.moveTo(-player.size * 0.4, player.size * 0.6);
        ctx.lineTo(player.size * 0.3, 2);
        ctx.lineTo(-player.size * 0.2, 2);
        ctx.closePath();
        ctx.fill();

        ctx.fillStyle = "#e6f2ff";
        ctx.beginPath();
        ctx.arc(0, 0, 3.5, 0, Math.PI * 2);
        ctx.fill();
        ctx.restore();
    }

    function drawEnemy(enemy) {
        const gradient = ctx.createRadialGradient(
            enemy.x - enemy.size * 0.3,
            enemy.y - enemy.size * 0.3,
            enemy.size * 0.2,
            enemy.x,
            enemy.y,
            enemy.size
        );
        gradient.addColorStop(0, "#ffb3b3");
        gradient.addColorStop(0.6, "#ff6b6b");
        gradient.addColorStop(1, "#6b1f1f");
        ctx.fillStyle = gradient;
        ctx.beginPath();
        ctx.arc(enemy.x, enemy.y, enemy.size, 0, Math.PI * 2);
        ctx.fill();
        ctx.strokeStyle = "rgba(255, 200, 200, 0.6)";
        ctx.lineWidth = 2;
        ctx.stroke();
    }

    function update(delta) {
        if (isDead) {
            return;
        }
        const moveY = (keys.has("ArrowUp") || keys.has("KeyW") ? -1 : 0) +
            (keys.has("ArrowDown") || keys.has("KeyS") ? 1 : 0);
        const moveX = (keys.has("ArrowLeft") || keys.has("KeyA") ? -1 : 0) +
            (keys.has("ArrowRight") || keys.has("KeyD") ? 1 : 0);

        player.x = clamp(player.x + moveX * player.speed, 10, width / 2.2);
        player.y = clamp(player.y + moveY * player.speed, 20, height - 40);

        bullets.forEach((bullet) => {
            bullet.x += bullet.speed;
        });

        for (let i = bullets.length - 1; i >= 0; i -= 1) {
            if (bullets[i].x > width) {
                bullets.splice(i, 1);
            }
        }

        enemies.forEach((enemy) => {
            enemy.x -= enemy.speed;
        });

        for (let i = enemies.length - 1; i >= 0; i -= 1) {
            if (enemies[i].x < -20) {
                enemies.splice(i, 1);
            }
        }

        for (let i = enemies.length - 1; i >= 0; i -= 1) {
            const enemy = enemies[i];
            for (let j = bullets.length - 1; j >= 0; j -= 1) {
                const bullet = bullets[j];
                const hit =
                    Math.abs(enemy.x - bullet.x) < enemy.size &&
                    Math.abs(enemy.y - bullet.y) < enemy.size;
                if (hit) {
                    enemies.splice(i, 1);
                    bullets.splice(j, 1);
                    score += 120;
                    break;
                }
            }
        }

        for (let i = 0; i < enemies.length; i += 1) {
            const enemy = enemies[i];
            const distance = Math.hypot(enemy.x - player.x, enemy.y - player.y);
            if (distance < enemy.size + player.size * 0.7) {
                isDead = true;
                break;
            }
        }

        if (performance.now() - lastSpawn > 1100) {
            spawnEnemy();
            lastSpawn = performance.now();
        }
    }

    function draw() {
        ctx.clearRect(0, 0, width, height);
        ctx.fillStyle = "#0b1220";
        ctx.fillRect(0, 0, width, height);

        drawPlayer();

        ctx.strokeStyle = "#9bbcff";
        ctx.lineWidth = 2;
        bullets.forEach((bullet) => {
            ctx.beginPath();
            ctx.moveTo(bullet.x - 4, bullet.y);
            ctx.lineTo(bullet.x + 6, bullet.y);
            ctx.stroke();
        });

        enemies.forEach((enemy) => {
            drawEnemy(enemy);
        });

        const scoreText = `Score: ${score}`;
        ctx.font = "13px Segoe UI";
        const padding = 8;
        const textWidth = ctx.measureText(scoreText).width;
        const boxWidth = textWidth + padding * 2;
        const boxHeight = 22;
        const boxX = width - boxWidth - 12;
        const boxY = 10;
        ctx.fillStyle = "rgba(7, 11, 18, 0.65)";
        ctx.fillRect(boxX, boxY, boxWidth, boxHeight);
        ctx.fillStyle = "#cbd8f3";
        ctx.fillText(scoreText, boxX + padding, boxY + 15);

        if (isDead) {
            ctx.fillStyle = "rgba(11, 18, 32, 0.8)";
            ctx.fillRect(0, 0, width, height);
            ctx.fillStyle = "#ffb3b3";
            ctx.font = "28px Segoe UI";
            ctx.textAlign = "center";
            ctx.fillText("Mission Failed", width / 2, height / 2);
            ctx.font = "14px Segoe UI";
            ctx.fillStyle = "#cbd8f3";
            ctx.fillText("Reload the page to respawn.", width / 2, height / 2 + 24);
            ctx.textAlign = "start";
        }
    }

    function loop(timestamp) {
        update(timestamp);
        draw();
        requestAnimationFrame(loop);
    }

    window.addEventListener("keydown", (event) => {
        if (event.code === "Space" && !isDead) {
            shoot();
            event.preventDefault();
        }
        keys.add(event.code);
    });

    window.addEventListener("keyup", (event) => {
        keys.delete(event.code);
    });

    window.addEventListener("resize", resize);

    fetch("/api/state")
        .then((response) => response.json())
        .then((data) => {
            const label = document.getElementById("difficultyLabel");
            if (label && data.difficultySummary) {
                label.textContent = data.difficultySummary;
            }
        })
        .catch(() => {
            // Keep UI stable if the API is not available yet.
        });

    resize();
    requestAnimationFrame(loop);
})();

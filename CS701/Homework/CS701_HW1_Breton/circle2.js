window.onload = init;

function init() {
    // canvas and context variables
    var canvas, context;
    canvas = document.getElementById("canvas");
    context = canvas.getContext("2d");

    // Variables for keeping track of circles
    var circles = [];
    var radius = 30;
    var i = 0;

    // Click event handler => draw a circle
    canvas.onclick = function(e) {

        // Click x and y relative to canvas
        x = e.clientX - e.target.offsetLeft;
        y = e.clientY - e.target.offsetTop;

        // Clear out circle(s) if new touches previous
        checkCollision(context, circles, x, y);

        // Get and set fill color to random color
        var color = getRandomColor();

        // Circle identifier
        var name = "circle" + i;

        // Add new cirlcle to array
        circles.push( { name: name,
                        x: x,
                        y: y,
                        radius: radius,
                        color: color,
                        active: true }
                    );

        // Draw the circles
        for (var circle of circles) {
            if (circle.active == false) { continue; }
            context.fillStyle = circle.color;
            context.beginPath();
            context.arc(circle.x, circle.y, circle.radius, 0, 2 * Math.PI);
            context.fill();
            context.closePath();
        }

        // Increment circle count
        i+=1;
    }
}

// Check if new circle collides with current circles on canvas
function checkCollision(context, circles, x, y) {
    circles.forEach(function(circle) {
        var distance = Math.sqrt( (circle.x - x)**2 + (circle.y - y)**2 );

        // If circles meet, clear previous circle
        if (distance < circle.radius*2) {
            console.log(("Setting " + circle.name + " inactive"));
            circle.active = false;
            // clear the drawing area
            context.clearRect(0, 0, canvas.width, canvas.height);
        }
    })
}

// Generate random hex color string
function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = '#';
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

window.onload = init;

function init() {
    // canvas and context variables
    var canvas;
    var context;

    canvas = document.getElementById("canvas");
    context = canvas.getContext("2d");

    // Click event handler => draw a circle
    canvas.onclick = function(e) {

        // Click x and y relative to canvas
        x = e.clientX - e.target.offsetLeft;
        y = e.clientY - e.target.offsetTop;

        // set fill color to random color
        context.fillStyle = getRandomColor();

        // Draw the circle
        context.beginPath();
        context.arc(x, y, 30, 0, 2 * Math.PI);
        context.fill();
        context.closePath();
    }
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

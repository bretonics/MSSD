var sliderModule = (function(win, doc) {

    win.onload = init;

    // canvas and context variables
    var canvas;
    var context;

    // center of the pattern
    var centerX, centerY;


    function init() {

            canvas = doc.getElementById("bullsEye");
            context = canvas.getContext("2d");

            centerX = canvas.width / 2;
            centerY = canvas.height / 2;

            // draw the initial pattern
            drawPattern();
    }


    // called whenever the slider value changes
    function drawPattern() {
        // clear the drawing area
        context.clearRect(0, 0, canvas.width, canvas.height);

        // get the current band width
        var bandWidth = doc.getElementById("bandWidth").value;

        //  Display the current band width
        doc.getElementById("currentWidth").innerHTML = "Current Band Width: " + bandWidth;

        // set fill color to red
        var color = "red";

        // Set initial radius size
        var radius = 200

        // Draw circles, each time bandWidth smaller
        while (radius > 0) {
            context.fillStyle = color; // set fill color

            // draw the pattern
            context.beginPath();
            context.arc(centerX, centerY, radius, 0, 2 * Math.PI);
            context.fill();
            context.closePath();

            radius -= bandWidth; // decrement radius size
            color = color == "red" ? "blue":"red"; // alternate colors
        }
    }

    return {
        drawPattern: drawPattern
    };



})(window, document);

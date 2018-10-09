window.onload = init;

// Google map
var map, lastMarker = null;
var latitude, longitude;

// Interval in seconds to call new location update
var interval = 5000;

// Keep track of locations
var path = [];
var counter = 0;


// Register event handler for "Start" button
function init() {
    var startButton = document.getElementById("startButton");
    startButton.onclick = startTrackingLocation;
}

function startTrackingLocation() {
    // Geolocation options
    var options = {
        enableHighAccuracy : true,
        timeout : 50000,
        maximumAge : 0
    };

    // Get initial location
    navigator.geolocation.getCurrentPosition(displayLocation);

    // Disable button
    document.getElementById("startButton").disabled = true;

     // Invoke simulated locations every 5 seconds
     setInterval(updateMyLocation, interval);

}

function displayLocation(position) {
    // Get locations information
    latitude = position.coords.latitude;
    longitude = position.coords.longitude;

    // Update
    path.push(new google.maps.LatLng(latitude, longitude));
    counter++;

    // Update DOM
    document.getElementById("counter").innerHTML = "Update #: " + counter;
    document.getElementById("startLatitude").innerHTML = "Start Latitude: " + latitude;
    document.getElementById("startLongitude").innerHTML = "Start Longitude: " + longitude;
    document.getElementById("currentLatitude").innerHTML = "Current Latitude: " + latitude;
    document.getElementById("currentLongitude").innerHTML = "Current Longitude: " + longitude;

    // Generate Google Map
    showMap(position.coords);
}

function updateMyLocation() {
    counter++

    // Generate random numbers to change direction
    latitude += Math.random() / 100;
    longitude -= Math.random() / 100;

    // Update DOM
    document.getElementById("counter").innerHTML = "Update #: " + counter;
    document.getElementById("currentLatitude").innerHTML = "Current Latitude: " + latitude;
    document.getElementById("currentLongitude").innerHTML = "Current Longitude: " + longitude;

    // Next point in travel
    latlong = new google.maps.LatLng(latitude, longitude);
    path.push(latlong);

    // Draw line between points
    var line = new google.maps.Polyline({
        path: path,
        strokeColor: '#0000ff',
        strokeOpacity: 1.0,
        strokeWeight: 3
    });
    line.setMap(map);
    map.panTo(latlong);

    // Draw last marker
    if (lastMarker) { lastMarker.setMap(null); }
    lastMarker = addMarker(map, latlong)
}

// Generate Google Maps
function showMap(position) {
    var googlePosition = new google.maps.LatLng(position.latitude, position.longitude);

    var mapOptions = {
        zoom: 15,
        center: googlePosition,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    var mapElement = document.getElementById("map");
    map = new google.maps.Map(mapElement, mapOptions);

    addMarker(map, googlePosition);
}

function addMarker(map, googlePosition) {
    var options = {
        map: map,
        position: googlePosition,
        clickable: true
    };

    var marker = new google.maps.Marker(options);

    return marker;
}

window.onload = init;

var members, dropLists, democrats, republicans, msg, name;  // containers
var senatorList ; // array of JSON objects
var xhr;  //XML request object

function init() {
    // Initialize JSON array of senators
    senatorList = JSON.parse(localStorage.getItem("senators")) || new Array();

    members = document.getElementById("members");
    dropLists = document.getElementById("dropLists");
    democrats = document.getElementById("democrats");
    republicans = document.getElementById("republicans");
    msg = document.getElementById("msg");

    // Get senator data from local storage or XMl file
    getData();

    // Add event handlers for dragging senators
    members.ondragstart = dragStartHandler;
    members.ondrag = dragHandler;
    members.ondragend = dragEndHandler;

    // Add event handlers for target droplists
    dropLists.ondragenter = dragEnterHandler;
    dropLists.ondragover = dragOverHandler;
    dropLists.ondrop = dropHandler;
}

// Get senator data from local storage or XMl file
function getData() {
    // Check if local storage already had list of senators
    if (senatorList.length > 0) {
        var quantity = senatorList.length;
        updateDOMmembers();  // Update DOM list of senators
        updateLists(senatorList);  // update lists of members who voted in previous session
        updateMessage("From LocalStorage loaded " + quantity + " senators");
    } else {
        // Load XML file of party memmbers
        makeRequest("partyWise.xml");
    }
}

// Update DOM senators list
function updateDOMmembers() {
    members.innerHTML = ""; // clear

    // Loop local storage array of senator objects
    var senators = JSON.parse(localStorage.getItem("senators"));  // List all senator objects
    for ( var senator of senators ) {
        var item = document.createElement("li");  // create a line item to add to list
        item.draggable=true;  // add draggable attribute to list item
        item.id = senator.name;  // set element id
        item.innerHTML = senator.name;  // senator list entry
        members.appendChild(item);  // add senator to DOM list
    }
}

// Updates drop lists with senators whom have voted in previous window session (reload)
function updateLists(senatorList) {
    // Check list of senators for voting status
    for (var senator of senatorList) {
        var party = senator.party;
        if (senator.voted) {  // if senator has already voted (in droplist bucket)
            if (party == "Democrat") {
                var newElement = duplicateElement(senator.name);  // New element to add
                democrats.appendChild(newElement);  // Add new element to dmeocrats' list
            }
            if (party == "Republican") {
                var newElement = duplicateElement(senator.name);  // New element to add
                republicans.appendChild(newElement);  // Add new element to republicans' list
            }
            // Update members DOM list element
            updateElement(senator.name)
        }
    }
}

// XMLHttpRequest - asynchronous loading of XML data
function makeRequest(url) {
    // Create Http request
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }

    if (xhr) {  // Request created successfully
        xhr.onreadystatechange = loadXML;
        xhr.open("GET", url, true);
        xhr.send(null);
    }
    else {
        updateMessage("Sorry, couldn't create an XMLHttpRequest");
    }
}

// Callback function when data is loaded from Http request
function loadXML() {
    if (xhr.readyState == 4) {
        if (xhr.status == 200) {
            // Get all the senator elements on XML file
            var senators = xhr.responseXML.getElementsByTagName("senator");
            for (var i = 0; i < senators.length; i++) {
                var name = senators[i].getElementsByTagName("name")[0].textContent;
                var party = senators[i].getElementsByTagName("party")[0].textContent;

                // Create a new JSON object for each senator
                var senator = {
                    "name" : name,
                    "party" : party,
                    "voted" : false,
                };
                // add senator object to the array
                senatorList.push(senator);
            }

            // Save senators list to localStorage
            updateLocalStorage("senators", senatorList);

            // Update
            updateDOMmembers();
            updateMessage("From AJAX loaded " + senatorList.length + " senators");

        } else {
            document.getElementById("status").innerHTML = "There was a problem with the request " + xhr.status;
        }
    }
}

// Display message
function updateMessage(message) {
    msg.innerHTML = message;
}


// Drag (Senators) event handler callback functions
function dragStartHandler(e) {
    name = e.target.id;  // explicit name of dragged object
    e.dataTransfer.setData("text", name);  // add data to dragged object
    e.target.classList.add("dragged");  // add "dragged" class for visualization
    updateColors(e); // update droplists colors

}
function dragHandler(e) {  // while dragging
    updateMessage("Dragging " + e.target.id); // Update message
}
function dragEndHandler(e) {  // finished dragging
    // Remove "dragged" class from elements
    var elems = document.querySelectorAll(".dragged");
    for(var i = 0; i < elems.length; i++) {
        elems[i].classList.remove("dragged");
    }
    // Update message
    updateMessage("Drag ended");
    updateColors(e, "r"); // reset colors
}


// Drag target (Droplists) event handler callback functions
function dragEnterHandler(e) {
    e.preventDefault();
}
function dragOverHandler(e) {
    var senator = getSenator();
    var party = senator.party.toLowerCase();
    var list = e.target.id;

    // Update message if senator in correct party droplist
    if (list.includes(party)) {
        updateMessage("Okay to drop Senator" + senator.name);
    }
    if (list.includes(party)){
        updateMessage("Okay to drop Senator " + senator.name);
    }

    e.preventDefault();
}
function dropHandler(e) {
    // New element to add
    var newElement = duplicateElement(name);

    var senator = getSenator();
    var party = senator.party.toLowerCase();
    var list = e.target.id;

    // Only allow to add Senator to appropriate party list
    if (list.includes(party) && party == "democrat") {
        // Add new element to list
        democrats.appendChild(newElement);
        // Update members DOM list element only after correct drop
        updateElement(senator.name);
        // Update local storage
        updateSenatorList(senator);
    }
    if (list.includes(party) && party == "republican") {
        // Add new element to list
        republicans.appendChild(newElement);
        // Update members DOM list element only after correct drop
        updateElement(senator.name);
        // Update local storage
        updateSenatorList(senator);
    }

    updateColors(e, "r"); // reset colors
    e.preventDefault();

}

function getSenator() {
    var senators = JSON.parse(localStorage.getItem("senators")); // List of all senator objects
    return ( senators.find(senator => { return senator.name == name }) );
}

function updateElement(id) {
    var element = document.getElementById(id);
    element.style.textDecoration = "line-through";
    element.style.opacity = 0.5;
    element.draggable=false;
}

function updateSenatorList(senator) {
    // Update LocalStorage senator item
    for ( var s of senatorList ) {
        if (s.name == senator.name) {
            s.voted = true;
        }
    }
    updateLocalStorage("senators", senatorList);
}

function updateLocalStorage(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
}

// Set droplists background colors according to party
function updateColors(e, r) {
    var element;
    var party = getSenator().party;

    // Set background color of droplist of dragged member's party
    var fieldsets = document.querySelectorAll("fieldset");
    if (party == "Democrat") {
        element = fieldsets.item(0);
        r ? element.style.background = "" : element.style.background = "#36abf9";  // add droplist background color
    }

    if (party == "Republican") {
        element = fieldsets.item(1);
        r ? element.style.background = "" : element.style.background = "#ef4545";  // add droplist background color
    }
}

function duplicateElement(id) {
    var sourceElement = document.getElementById(id);
    var newElement = sourceElement.cloneNode(true);
    newElement.draggable=false;
    return(newElement);
}

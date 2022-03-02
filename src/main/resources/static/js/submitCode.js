// Highlight code according to language used

// detect keypress

// implement

function send() {
    let now = new Date();
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": document.getElementById("time_restriction").value,
        "views": document.getElementById("views_restriction").value
    };

    console.log("js:" + object);
    document.getElementById("code_snippet").value = "";

    let json = JSON.stringify(object);

    // AJAX XML
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/snippetshare/api/code/new", false);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status == 200) {
      var x = document.getElementById("sentAlert");
      if(x.classList.contains("d-none")){
        x.classList.remove("d-none");
        x.classList.add("show");
      }
    }
}
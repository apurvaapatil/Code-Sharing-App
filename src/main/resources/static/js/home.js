function send(){
    var id = document.getElementById("snippetId").value;
    var url = "/snippetshare/code/get/"+id;

    // AJAX XML
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", url, false); // true for asynchronous
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
           window.location.replace(url);
        }
    };
    xmlhttp.send();

    console.log(xmlhttp.status);
}
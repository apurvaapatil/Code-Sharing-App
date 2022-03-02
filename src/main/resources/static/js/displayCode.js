function copyText(){
  document.getElementById("copy").setAttribute("title", "Copied!");
  var text = document.getElementById("code_snippet").innerHTML;
  console.log(text);
  navigator.clipboard.writeText(text);
}

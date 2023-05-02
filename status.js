var numImages = 8;
var range = 100;

const searchParams = new URLSearchParams(window.location.search);
let v = parseInt(searchParams.get("v"));
if (isNaN(v) || v < 0 || v > range) {
  // Generate a random integer within the range of 0-100
  v = Math.floor(Math.random() * range+1);
}
var imageIndex = Math.floor(v / (range / numImages));

const filename = "images/cage"+imageIndex+".png";
console.log("filename: " + filename);

var img = document.getElementById("selected-image");
img.src = filename;
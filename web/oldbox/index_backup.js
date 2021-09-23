function select() {
	blob = $("#file")[0].files[0]
}

function scan() {
	Tesseract.recognize(blob).then(function(result) {
		console.log(result.text);
		$("#scan-result").text(result.text);
	});
}

$(document).ready(function () {
	$("#file").change(select)
	$("#scan").click(scan)
})


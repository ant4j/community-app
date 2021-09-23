$(document).ready(function () {

    $("#collection-back-btn").click(() => {
        location.href = "collections.html"
    })

    $("#collection-subjects-list-label").text(sessionStorage.getItem("collection-name"))
    sessionStorage.removeItem("collection-name")

    // let subjArr = 
	// [ { "id": "1", "name": "Il giorno dei giorni"}, 
	//   { "id": "2", "name": "Una vita da mediano"} ]
	
	// if(subjArr.length > 0) {
	// 	let listGroupItem = $(".list-group-item")
	// 	$(".list-group-item").remove()
	// 	for(let index in subjArr) {
	// 		listGroupItem
	// 		.clone()
	// 		.data("id", subjArr[index].id)
	// 		.text(subjArr[index].name)
	// 		.click((event) => {
    //             console.log("work in progress")
	// 		})
	// 		.appendTo("#collection-subjects-list")
	// 	}
	// }

})
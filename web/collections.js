$(document).ready(function () {

    $("#collections-back-btn").click(() => {
        location.href = "home.html"
    })

	let collArr = 
	[ { "id": "1", "name": "Cantici", "type": "c" }, 
	  { "id": "2", "name": "Letture", "type": "l" }, 
	  { "id": "3", "name": "Battute", "type": "l" } ]
	
	if(collArr.length > 0) {
		let listGroupItem = $(".list-group-item")
		$(".list-group-item").remove()
		for(let index in collArr) {
			listGroupItem
			.clone()
			.data("id", collArr[index].id)
            .data("name", collArr[index].name)
            .data("type", collArr[index].type)
			.text(collArr[index].name)
			.click((event) => {
                // TODO passare anche l'id
                sessionStorage.setItem("collection-name", $(event.target).data("name"))
                location.href = "collection.html"
			})
			.appendTo("#collections-list")
		}
	}
    
})
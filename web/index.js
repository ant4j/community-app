$(document).ready(function () {
	$("#commty-btn-enter").click(() => {
		location.href = "user.html"
	})

	let commtyArr = 
	[ { "id": "1", "name": "Community 1" }, 
	  { "id": "2", "name": "Community 2" }, 
	  { "id": "3", "name": "Community 3" } ]
	
	if(commtyArr.length > 0) {
		let listGroupItem = $(".list-group-item")
		$(".list-group-item").remove()
		for(let index in commtyArr) {
			listGroupItem
			.clone()
			.data("id", commtyArr[index].id)
			.text(commtyArr[index].name)
			.click((event) => {
				$("#commty-list .list-group-item.active").removeClass("active")
				event.target.classList.add("active")
			})
			.appendTo("#commty-list")
		}
	}
})

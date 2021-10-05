exports.main = async function main(args) {

	let collId = args.collId

	console.log("collId: " + collId)

	return {
		"body": [
			{ id: "1", name: "Contenuto 1" },
			{ id: "2", name: "Contenuto 2" }
		]
	}
}
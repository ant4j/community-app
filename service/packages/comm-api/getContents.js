function main(args) {

	let idColl = args.idColl

	console.log(idColl)

	return {
		"body": [
			{ id: "1", name: "Cantico 1" },
			{ id: "2", name: "Cantico 2" }
		]
	}
}
exports.main = main
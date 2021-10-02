exports.main = async function main(args) {

	let idColl = args.idColl

	console.log("idColl: " + idColl)

	return {
		"body": [
			{ id: "1", name: "Cantico 1" },
			{ id: "2", name: "Cantico 2" }
		]
	}
}
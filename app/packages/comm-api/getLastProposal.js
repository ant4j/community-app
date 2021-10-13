exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" }
	}

	let res = { "status": status.not_exists }

	let propId = await db.getAsync("prop_id_seq")

	let key = "prop:" + args.commId + ":" + propId

	let lastProposal = JSON.parse(await db.getAsync(key))

	if (lastProposal) {

		let contKeys = await db.keysAsync("cont:" + lastProposal.coll_id + ":" + lastProposal.cont_id + ":*")

		let content = JSON.parse(await db.getAsync(contKeys[0]))

		let collKeys = await db.keysAsync("coll:" + args.commId + ":" + lastProposal.coll_id + ":*")

		let collection = JSON.parse(await db.getAsync(collKeys[0]))

		let data = { "username": lastProposal.username, "contentTitle": content.title, "collectionType": collection.type, "collId": lastProposal.coll_id, "contId": lastProposal.cont_id }

		res = { "status": status.success, "data": data }
	}

	return {
		"body": res
	}
}
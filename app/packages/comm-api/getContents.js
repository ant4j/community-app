exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" },
		"no_data": { "code": "-2", "description": "no data" }
	}

	let contKeys = await db.keysAsync("cont:" + args.collId + ":*:*")

	let res = { "status": status.no_data, "collName": "", "data": [] }

	let collKeys = await db.keysAsync("coll:*:" + args.collId + ":*")

	let coll = JSON.parse(await db.getAsync(collKeys[0]))

	res.collName = coll.name

	console.log("contKeys.length: " + contKeys.length)

	if (contKeys.length) {
		let conts = await db.mgetAsync(contKeys)
		res.data = conts.map(JSON.parse)
		res.status = status.success

		console.log("res: " + JSON.stringify(res))
	}

	return {
		"body": res
	}
}
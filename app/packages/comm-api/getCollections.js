exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" },
		"no_data": { "code": "-2", "description": "no data" }
	}

	let collKeys = await db.keysAsync("coll:" + args.commId + ":*")

	let res = []

	if (collKeys.length) {
		let colls = await db.mgetAsync(collKeys)
		res = colls.map(JSON.parse)
	}

	return {
		"body": res
	}
}
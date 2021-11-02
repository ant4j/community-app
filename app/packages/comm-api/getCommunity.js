exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" },
		"no_data": { "code": "-2", "description": "no data" }
	}

	let res = { "status": status.not_exists }

	let commKeys = await db.keysAsync("comm:*:*:" + args.commCode + ":*")

	if (commKeys.length) {
		let comm = JSON.parse(await db.getAsync(commKeys[0]))
		res = { "status": status.success, "data": comm }
	}

	return {
		"body": res
	}
}
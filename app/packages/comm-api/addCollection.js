exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" },
		"no_data": { "code": "-2", "description": "no data" }
	}

	let collKeys = await db.keysAsync("coll:*:*:*" + args.name)

	let res = { "status": status.already_exists }

	if (!collKeys.length) {
		db.incr("coll_id_seq")

		let collId = await db.getAsync("coll_id_seq")

		let key = "coll:" + args.commId + ":" + collId + ":" + args.name
		let value = JSON.stringify({ "id": collId, "name": args.name, "type": args.type })

		await db.setAsync(key, value)

		res = { "status": status.success }
	}

	return {
		"body": res
	}
}
exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" }
	}

	let commKeys = await db.keysAsync("comm:*:" + args.name)

	let res = { "status": status.already_exists }

	if (!commKeys.length) {
		db.incr("comm_id_seq")

		let commId = await db.getAsync("comm_id_seq")

		let key = "comm:" + commId + ":" + args.name
		let value = JSON.stringify({ "id": commId, "name": args.name, "watchword": args.watchword })

		await db.setAsync(key, value)

		let commCreated = JSON.parse(await db.getAsync(key))

		res = { "status": status.success, "data": commCreated }
	}

	return {
		"body": res
	}
}
exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" }
	}

	let contKeys = await db.keysAsync("cont:*:*:*" + args.title)

	let res = { "status": status.already_exists }

	if (!contKeys.length) {
		db.incr("cont_id_seq")

		let contId = await db.getAsync("cont_id_seq")

		let key = "cont:" + args.collId + ":" + contId + ":" + args.title
		let value = JSON.stringify({ "id": contId, "title": args.title })

		await db.setAsync(key, value)

		await db.setAsync("cont_text:" + args.collId + ":" + contId, args.text)

		res = { "status": status.success }
	}

	return {
		"body": res
	}
}
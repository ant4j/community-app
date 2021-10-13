exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" }
	}

	db.incr("prop_id_seq")

	let propId = await db.getAsync("prop_id_seq")

	let key = "prop:" + args.commId + ":" + propId

	let value = JSON.stringify({ "username": args.username, "coll_id": args.collId, "cont_id": args.contId })

	db.setAsync(key, value)

	let res = { "status": status.success }

	console.log("res: " + res)

	return {
		"body": res
	}
}
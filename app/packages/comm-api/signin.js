exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" },
		"no_data": { "code": "-2", "description": "no data" }
	}

	let res = { "status": status.not_exists }

	let key = "comm_auth:" + args.commId

	let existsKey = await db.existsAsync(key)

	if (existsKey == 1) {
		let watchword = await db.getAsync(key)
		console.log("watchword: " + JSON.stringify(watchword))
		if (args.watchword == watchword) {
			db.incr("user_id_seq")

			let userId = await db.getAsync("user_id_seq")

			let username = "user" + userId

			db.setAsync("user:" + args.commId + ":" + username, username)

			res = { "status": status.success, "username": username }
		}
	}

	return {
		"body": res
	}
}
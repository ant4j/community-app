exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" }
	}

	let res = { "status": status.not_exists }

	let commKeys = await db.keysAsync("comm:" + args.commId + ":*")

	if (commKeys.length > 0) {
		let comm = JSON.parse(await db.getAsync(commKeys[0]))
		console.log("comm: " + JSON.stringify(comm))
		if (args.watchword == comm.watchword) {
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
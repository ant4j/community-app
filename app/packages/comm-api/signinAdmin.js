exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" },
		"no_data": { "code": "-2", "description": "no data" }
	}

	let res = { "status": status.not_exists }

	let adminKeys = await db.keysAsync("admin:*:" + args.username)

	if (adminKeys.length > 0) {
		let admin = JSON.parse(await db.getAsync(adminKeys[0]))
		console.log("admin: " + JSON.stringify(admin))
		if (args.password == admin.password) {
			res = { "status": status.success, "adminId": admin.id }
		}
	}

	return {
		"body": res
	}
}
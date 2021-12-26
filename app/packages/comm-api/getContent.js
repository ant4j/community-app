exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let status = {
		"success": { "code": "1", "description": "success" },
		"already_exists": { "code": "0", "description": "already exists" },
		"not_exists": { "code": "-1", "description": "not exists" },
		"no_data": { "code": "-2", "description": "no data" }
	}

	let contText = await db.getAsync("cont_text:" + args.collId + ":" + args.contId)

	let contKeys = await db.keysAsync("cont:" + args.collId + ":" + args.contId + ":*")

	let cont = JSON.parse(await db.getAsync(contKeys[0]))

	let res = { "title": cont.title, "text": contText }

	console.log("res: " + res)

	return {
		"body": res
	}
}
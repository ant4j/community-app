exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let contText = await db.getAsync("cont_text:" + args.collId + ":" + args.contId)

	let contKeys = await db.keysAsync("cont:" + args.collId + ":" + args.contId + ":*")

	let cont = JSON.parse(await db.getAsync(contKeys[0]))

	res = { "title": cont.title, "text": contText }

	console.log("res: " + res)

	return {
		"body": res
	}
}
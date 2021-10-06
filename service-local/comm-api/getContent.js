exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let contText = await db.getAsync("cont_text:" + args.collId + ":" + args.contId)
	res = { "text": contText }

	console.log("res: " + res)

	return {
		"body": res
	}
}
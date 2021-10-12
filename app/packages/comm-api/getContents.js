exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let contKeys = await db.keysAsync("cont:" + args.collId + ":*:*")

	let res = []

	console.log("contKeys.length: " + contKeys.length)

	if (contKeys.length) {
		let conts = await db.mgetAsync(contKeys)
		res = conts.map(JSON.parse)

		console.log("res: " + res)
	}

	return {
		"body": res
	}
}
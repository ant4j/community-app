exports.main = async function main(args) {

	let db = require("@nimbella/sdk").redis()

	let collKeys = await db.keysAsync("coll:" + args.commId + ":*")

	let res = []

	if (collKeys.length) {
		let colls = await db.mgetAsync(collKeys)
		res = colls.map(JSON.parse)
	}

	return {
		"body": res
	}
}
exports.main = async function main(args) {

    let db = require("@nimbella/sdk").redis()

    let commKeys = await db.keysAsync("comm:*")

    let res = []

    if (commKeys.length) {
        let comms = await db.mgetAsync(commKeys)
        res = comms.map(JSON.parse)
    }

    return {
        "body": res
    }
}
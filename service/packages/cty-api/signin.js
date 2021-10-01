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
        console.log("comm: " + comm)
        if (args.watchword == comm.watchword) {
            db.sadd("users:" + args.commId, args.username)
            res = { "status": status.success }
        }
    }

    return {
        "body": res
    }
}
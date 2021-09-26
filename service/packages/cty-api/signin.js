function main(args) {

    let communityId = args.communityId || "none";
    let watchword = args.watchword || "none";
    let username = args.username || "none";

    let status = [{ "code": "0", "description": "error" }, { "code": "1", "description": "success" }]

    let res = {};

    if (communityId == "1" &&
        watchword == "prova" &&
        username == "mar") {
        res.status = status[1];
        // TODO va restituito anche idUtente/nomeUtente e idCommunity/nomeCommunity
    } else {
        res.status = status[0];
    }

    return {
        "body": res
    }
}
exports.main = main
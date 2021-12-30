const Redis = require("ioredis");
const client = new Redis(process.env.REDIS_URL);

client.on('error', (err) => console.log('Redis Client ', err));

module.exports.createCommunity = async (id, adminId, code, name, watchword) => {
	await client.sadd("comm", JSON.stringify({ id: id, admin_id: adminId, code: code, name: name }));
	return await client.set(`comm_auth:${id}`, watchword);
};

module.exports.detachCommunityId = async () => {
	return await client.incr("comm_id_seq");
};

module.exports.findCommunity = async (code, name) => {
	let pattern = `*`;

	if (code) {
		pattern = `*"code":"${code}",*`;
	} else if (name) {
		pattern = `*"name":"${name}"*`;
	}

	let res = await client.sscan("comm", 0, "match", pattern);
	if (res[1][0] == undefined) {
		return undefined;
	}
	return JSON.parse(res[1][0]);
};

module.exports.findCommunities = async () => {
	let communities = await client.smembers("comm");
	return communities.map(JSON.parse);
};

module.exports.findAuthentication = async (commId) => {
	let watchword = await client.get(`comm_auth:${commId}`);
	if (!watchword) {
		return undefined;
	}
	return watchword;
};
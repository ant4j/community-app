const Redis = require("ioredis");
const client = new Redis(process.env.REDIS_URL);

client.on('error', (err) => console.log('Redis Client ', err));

module.exports.createCollection = async (id, commId, name, type) => {
	return await client.sadd("coll", JSON.stringify({ id: id, comm_id: commId, name: name, type: type }));
};

module.exports.detachCollectionId = async () => {
	return await client.incr("coll_id_seq");
};

module.exports.findCollection = async (id, name) => {
	let pattern = `*`;

	if(id) {
		pattern = `*"id":${id},*`;
	} else if(name) {
		pattern = `*"name":"${name}",*`;
	}
	
	let res = await client.sscan("coll", 0, "match", pattern);
	if (res[1][0] == undefined) {
		return undefined;
	}
	return JSON.parse(res[1][0]);
};

module.exports.findCollections = async (commId) => {
	let pattern = `*"comm_id":"${commId}",*`;
	let res = await client.sscan("coll", 0, "match", pattern);
	if (res[1] == undefined) {
		return [];
	}
	return res[1].map(JSON.parse);
};
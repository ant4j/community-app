const Redis = require("ioredis");
const client = new Redis(process.env.REDIS_URL);

client.on('error', (err) => console.log('Redis Client ', err));

module.exports.createContent = async (id, collId, title, text) => {
	await client.sadd("cont", JSON.stringify({ id: id, coll_id: collId, title: title }));
	return await client.set(`cont_text:${id}:${collId}`, text);
};

module.exports.detachContentId = async () => {
	return await client.incr("cont_id_seq");
};

module.exports.findContent = async (id, title) => {
	let pattern = `*`;
	
	if(id) {
		pattern = `*"id":${id}*`;
	} else if(title) {
		pattern = `*"title":"${title}"*`;
	}

	let res = await client.sscan("cont", 0, "match", pattern);
	if (res[1][0] == undefined) {
		return undefined;
	}
	return JSON.parse(res[1][0]);
};

module.exports.findContents = async () => {
	let contents = await client.smembers("cont");
	return contents.map(JSON.parse);
};

module.exports.findContentText = async (contId, collId) => {
	return await client.get("cont_text:" + contId + ":" + collId);
};
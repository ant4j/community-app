// const Redis = require("ioredis");
// const client = new Redis(process.env.REDIS_URL);

// client.on('error', (err) => console.log('Redis Client ', err));

// module.exports.createContent = async (id, collId, title, text) => {
// 	await client.sadd("cont", JSON.stringify({ id: id, coll_id: collId, title: title }));
// 	return await client.set(`cont_text:${id}:${collId}`, text);
// };

// module.exports.detachContentId = async () => {
// 	return await client.incr("cont_id_seq");
// };

// module.exports.findContent = async (id, title) => {
// 	let pattern = `*`;

// 	if(id) {
// 		pattern = `*"id":${id}*`;
// 	} else if(title) {
// 		pattern = `*"title":"${title}"*`;
// 	}

// 	let res = await client.sscan("cont", 0, "match", pattern);
// 	if (res[1][0] == undefined) {
// 		return undefined;
// 	}
// 	return JSON.parse(res[1][0]);
// };

// module.exports.findContents = async () => {
// 	let contents = await client.smembers("cont");
// 	return contents.map(JSON.parse);
// };

// module.exports.findContentText = async (contId, collId) => {
// 	return await client.get("cont_text:" + contId + ":" + collId);
// };


const { MongoClient } = require('mongodb');
const client = new MongoClient(process.env.MONGO_URL);

client.on('error', (err) => console.log('Mongo Client ', err));

module.exports.createContent = async (id, collId, title, text) => {
	await client.connect();
	await client.db("pool").collection("cont").insertOne(
		{ id: id, coll_id: collId, title: title }
	);
	await client.db("pool").collection("cont_text").insertOne(
		{ cont_id: id, coll_id: collId, text: text }
	);
	client.close();
	return "OK";
};

module.exports.detachContentId = async () => {
	await client.connect();
	let res = await client.db("pool").collection("sequence").findOneAndUpdate(
		{ id: "cont_id_seq" },
		{ $inc: { seq_value: 1 } },
		{ upsert: true, returnDocument: "after" }
	);
	client.close();
	return res.value.seq_value;
};

module.exports.findContent = async (id, title) => {
	let filter = {};

	if (id) {
		filter.id = id;
	}

	if (title) {
		filter.title = title;
	}

	await client.connect();
	const res = await client.db("pool").collection("cont").findOne(filter);
	client.close();
	if (res == null) {
		return undefined;
	}
	return res;
};

module.exports.findContents = async (collId) => {
	await client.connect();
	let res = await client.db("pool").collection("cont").find(
		{ coll_id: collId }
	).toArray();
	client.close();
	if (res == null) {
		return undefined;
	}
	return res;
};

module.exports.findContentText = async (contId, collId) => {
	await client.connect();
	const res = await client.db("pool").collection("cont_text").findOne(
		{ cont_id: contId, coll_id: collId }
	);
	client.close();
	if (res == null) {
		return undefined;
	}
	return res;
};
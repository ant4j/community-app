// const Redis = require("ioredis");
// const client = new Redis(process.env.REDIS_URL);

// client.on('error', (err) => console.log('Redis Client ', err));

// module.exports.createCollection = async (id, commId, name, type) => {
// 	return await client.sadd("coll", JSON.stringify({ id: id, comm_id: commId, name: name, type: type }));
// };

// module.exports.detachCollectionId = async () => {
// 	return await client.incr("coll_id_seq");
// };

// module.exports.findCollection = async (id, name) => {
// 	let pattern = `*`;

// 	if(id) {
// 		pattern = `*"id":${id},*`;
// 	} else if(name) {
// 		pattern = `*"name":"${name}",*`;
// 	}

// 	let res = await client.sscan("coll", 0, "match", pattern);
// 	if (res[1][0] == undefined) {
// 		return undefined;
// 	}
// 	return JSON.parse(res[1][0]);
// };

// module.exports.findCollections = async (commId) => {
// 	let pattern = `*"comm_id":"${commId}",*`;
// 	let res = await client.sscan("coll", 0, "match", pattern);
// 	if (res[1] == undefined) {
// 		return [];
// 	}
// 	return res[1].map(JSON.parse);
// };

const { MongoClient } = require('mongodb');
const client = new MongoClient(process.env.MONGO_URL);

client.on('error', (err) => console.log('Mongo Client ', err));

module.exports.createCollection = async (id, commId, name, type) => {
	await client.connect();
	await client.db("pool").collection("coll").insertOne(
		{ id: id, comm_id: commId, name: name, type: type }
	);
	client.close();
	return "OK";
};

module.exports.detachCollectionId = async () => {
	await client.connect();
	let res = await client.db("pool").collection("sequence").findOneAndUpdate(
		{ id: "coll_id_seq" },
		{ $inc: { seq_value: 1 } },
		{ upsert: true, returnDocument: "after" }
	);
	client.close();
	return res.value.seq_value;
};

module.exports.findCollection = async (id, name) => {
	let filter = {};

	if (id) {
		filter.id = id;
	}

	if (name) {
		filter.name = name;
	}

	await client.connect();
	const res = await client.db("pool").collection("coll").findOne(filter);
	client.close();
	if (res == null) {
		return undefined;
	}
	return res;
};

module.exports.findCollections = async () => {
	await client.connect();
	let res = await client.db("pool").collection("coll").find().toArray();
	client.close();
	return res;
};
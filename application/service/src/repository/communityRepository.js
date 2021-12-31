// const Redis = require("ioredis");
// const client = new Redis(process.env.REDIS_URL);

// client.on('error', (err) => console.log('Redis Client ', err));

// module.exports.createCommunity = async (id, adminId, code, name, watchword) => {
// 	await client.sadd("comm", JSON.stringify({ id: id, admin_id: adminId, code: code, name: name }));
// 	return await client.set(`comm_auth:${id}`, watchword);
// };

// module.exports.detachCommunityId = async () => {
// 	return await client.incr("comm_id_seq");
// };

// module.exports.findCommunity = async (code, name) => {
// 	let pattern = `*`;

// 	if (code) {
// 		pattern = `*"code":"${code}",*`;
// 	} else if (name) {
// 		pattern = `*"name":"${name}"*`;
// 	}

// 	let res = await client.sscan("comm", 0, "match", pattern);
// 	if (res[1][0] == undefined) {
// 		return undefined;
// 	}
// 	return JSON.parse(res[1][0]);
// };

// module.exports.findCommunities = async () => {
// 	let communities = await client.smembers("comm");
// 	return communities.map(JSON.parse);
// };

// module.exports.findAuthentication = async (commId) => {
// 	let watchword = await client.get(`comm_auth:${commId}`);
// 	if (!watchword) {
// 		return undefined;
// 	}
// 	return watchword;
// };


const { MongoClient } = require('mongodb');
const client = new MongoClient(process.env.MONGO_URL);

client.on('error', (err) => console.log('Mongo Client ', err));

module.exports.createCommunity = async (id, adminId, code, name, watchword) => {
	await client.connect();
	await client.db("pool").collection("comm").insertOne(
		{ id: id, admin_id: adminId, code: code, name: name }
	);
	await client.db("pool").collection("comm_auth").insertOne(
		{ comm_id: id, watchword: watchword }
	);
	client.close();
	return "OK";
};

module.exports.detachCommunityId = async () => {
	await client.connect();
	let res = await client.db("pool").collection("sequence").findOneAndUpdate(
		{ id: "comm_id_seq" },
		{ $inc: { seq_value: 1 } },
		{ upsert: true, returnDocument: "after" }
	);
	client.close();
	return res.value.seq_value;
};

module.exports.findCommunity = async (code, name) => {
	let filter = {};

	if (code) {
		filter.code = code;
	}
	
	if (name) {
		filter.name = name;
	}

	await client.connect();
	const res = await client.db("pool").collection("comm").findOne(filter);
	client.close();
	if (res == null) {
		return undefined;
	}
	return res;
};

module.exports.findCommunities = async () => {
	await client.connect();
	let res = await client.db("pool").collection("comm").find().toArray();
	client.close();
	return res;
};

module.exports.findAuthentication = async (commId) => {
	await client.connect();
	const res = await client.db("pool").collection("comm_auth").findOne(
		{ comm_id: commId }
	);
	client.close();
	if (res == null) {
		return undefined;
	}
	return res;
};
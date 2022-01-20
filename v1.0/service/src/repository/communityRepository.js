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

module.exports.retrieveCommunity = async (code, name) => {
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

module.exports.retrieveCommunities = async () => {
	await client.connect();
	let res = await client.db("pool").collection("comm").find().toArray();
	client.close();
	return res;
};

module.exports.retrieveAuthentication = async (commId) => {
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
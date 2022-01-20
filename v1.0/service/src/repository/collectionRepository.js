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

module.exports.retrieveCollection = async (id, name) => {
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

module.exports.retrieveCollections = async (commId) => {
	await client.connect();
	let res = await client.db("pool").collection("coll").find(
		{comm_id: commId}
	).toArray();
	client.close();
	return res;
};
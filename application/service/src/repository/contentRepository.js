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

module.exports.retrieveContent = async (id, title) => {
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

module.exports.retrieveContents = async (collId) => {
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

module.exports.retrieveContentText = async (contId, collId) => {
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
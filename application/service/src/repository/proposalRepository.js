const { MongoClient } = require('mongodb');
const client = new MongoClient(process.env.MONGO_URL);

client.on('error', (err) => console.log('Mongo Client ', err));

module.exports.createProposal = async (id, commId, collId, contId, username) => {
	await client.connect();
	await client.db("pool").collection("prop").insertOne(
		{ id: id, comm_id: commId, coll_id: collId, cont_id: contId, username: username }
	);
	await client.db("pool").collection("last_prop").updateOne(
		{ comm_id: commId },
		{ $set: { prop_id: id } },
		{ upsert: true }
	);
	client.close();
	return "OK";
};

module.exports.detachProposalId = async () => {
	await client.connect();
	let res = await client.db("pool").collection("sequence").findOneAndUpdate(
		{ id: "prop_id_seq" },
		{ $inc: { seq_value: 1 } },
		{ upsert: true, returnDocument: "after" }
	);
	client.close();
	return res.value.seq_value;
};

module.exports.findProposal = async (id, commId) => {
	let filter = { id: id, comm_id: commId };

	await client.connect();
	const res = await client.db("pool").collection("prop").findOne(filter);
	client.close();
	if (res == null) {
		return undefined;
	}
	return res;
};

module.exports.findLastProposalId = async (commId) => {
	await client.connect();
	const res = await client.db("pool").collection("last_prop").findOne(
		{ comm_id: commId }
	);
	client.close();
	if (res == null) {
		return undefined;
	}
	return res.prop_id;
};
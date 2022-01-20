const { MongoClient } = require('mongodb');
const client = new MongoClient(process.env.MONGO_URL);

client.on('error', (err) => console.log('Mongo Client ', err));

module.exports.retrieveAdmin = async (username, password) => {
	await client.connect();
	const res = await client.db("pool").collection("admin").findOne(
		{ username: username, password: password }
	);
	client.close();
	if (res == null) {
		return undefined;
	}
	return res;
};
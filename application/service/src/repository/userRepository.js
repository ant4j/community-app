// const Redis = require("ioredis");
// const client = new Redis(process.env.REDIS_URL);

// client.on('error', (err) => console.log('Redis Client ', err));

// module.exports.detachUserId = async () => {
// 	return await client.incr("user_id_seq");
// };

const { MongoClient } = require('mongodb');
const client = new MongoClient(process.env.MONGO_URL);

client.on('error', (err) => console.log('Mongo Client ', err));

module.exports.detachUserId = async () => {
	await client.connect();
	let res = await client.db("pool").collection("sequence").findOneAndUpdate(
		{ id: "user_id_seq" },
		{ $inc: { seq_value: 1 } },
		{ upsert: true, returnDocument: "after" }
	);
	client.close();
	return res.value.seq_value;
};
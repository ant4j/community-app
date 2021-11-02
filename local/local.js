let express = require("express")
let cors = require("cors")

const port = 3000
let serviceLocal = express()
serviceLocal.use(cors(), express.json())

serviceLocal.listen(port, () => {
	console.log(`local listening at http://localhost:${port}`)
})

function exposeGet(path, func) {
	serviceLocal.get(path, (req, res) => {
		console.log("Query params: " + JSON.stringify(req.query))
		func(req.query)
			.then(bodyWrapper => {
				console.log("bodyWrapper.body: " + JSON.stringify(bodyWrapper.body))
				res.send(bodyWrapper.body)
			})
	})
}

function exposePost(path, func) {
	serviceLocal.post(path, (req, res) => {
		console.log("Body: " + JSON.stringify(req.body))
		func(req.body)
			.then(bodyWrapper => {
				console.log("bodyWrapper.body: " + JSON.stringify(bodyWrapper.body))
				res.send(bodyWrapper.body)
			})
	})
}

exposePost("/addCommunity", require("./comm-api/addCommunity.js").main)
exposeGet("/getCommunities", require("./comm-api/getCommunities.js").main)
exposeGet("/getContents", require("./comm-api/getContents.js").main)
exposePost("/signin", require("./comm-api/signin.js").main)
exposeGet("/getCollections", require("./comm-api/getCollections.js").main)
exposePost("/addCollection", require("./comm-api/addCollection.js").main)
exposePost("/addContent", require("./comm-api/addContent.js").main)
exposeGet("/getContent", require("./comm-api/getContent.js").main)
exposePost("/proposeContent", require("./comm-api/proposeContent.js").main)
exposeGet("/getLastProposal", require("./comm-api/getLastProposal.js").main)
exposePost("/signinAdmin", require("./comm-api/signinAdmin.js").main)
exposeGet("/getCommunity", require("./comm-api/getCommunity.js").main)
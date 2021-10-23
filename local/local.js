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

let addCommunity = require("./comm-api/addCommunity.js")
let getCommunities = require("./comm-api/getCommunities.js")
let getContents = require("./comm-api/getContents.js")
let signin = require("./comm-api/signin.js")

let getCollections = require("./comm-api/getCollections.js")
let addCollection = require("./comm-api/addCollection.js")
let addContent = require("./comm-api/addContent.js")
let getContent = require("./comm-api/getContent.js")
let proposeContent = require("./comm-api/proposeContent.js")
let getLastProposal = require("./comm-api/getLastProposal.js")

let signinAdmin = require("./comm-api/signinAdmin.js")

exposePost("/addCommunity", addCommunity.main)
exposeGet("/getCommunities", getCommunities.main)
exposeGet("/getContents", getContents.main)
exposePost("/signin", signin.main)

exposeGet("/getCollections", getCollections.main)
exposePost("/addCollection", addCollection.main)
exposePost("/addContent", addContent.main)
exposeGet("/getContent", getContent.main)
exposePost("/proposeContent", proposeContent.main)
exposeGet("/getLastProposal", getLastProposal.main)

exposePost("/signinAdmin", signinAdmin.main)
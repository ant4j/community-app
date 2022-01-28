import Signin from "./routes/Signin.svelte";
import Home from "./routes/Home.svelte";
import NotFound from "./routes/NotFound.svelte";

export default {
	"/signin/:communityCode": Signin,
	"/home": Home,
	"/NotFound": NotFound,
	"*": NotFound
};

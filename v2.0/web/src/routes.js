import Signin from "./routes/user/Signin.svelte";
import Home from "./routes/user/Home.svelte";
import NotFound from "./routes/NotFound.svelte";

export default {
	"/signin/:communityCode": Signin,
	"/home": Home,
	"/NotFound": NotFound,
	"*": NotFound
};

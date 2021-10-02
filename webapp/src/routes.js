import Home from "./routes/Home.svelte";
import Signin from "./routes/Signin.svelte";
import Collection from "./routes/Collection.svelte";
import Content from "./routes/Content.svelte";
import ContentNew from "./routes/ContentNew.svelte";
import NotFound from "./routes/NotFound.svelte";

export default {
	"/": Signin,
	"/home": Home,
	"/collection/:idColl": Collection,
	"/content": Content,
	"/content-new": ContentNew,
	// The catch-all route must always be last
	"*": NotFound
};

import Home from "./routes/Home.svelte";
import Signin from "./routes/Signin.svelte";
import Collection from "./routes/Collection.svelte";
import CollectionNew from "./routes/CollectionNew.svelte";
import Content from "./routes/Content.svelte";
import ContentNew from "./routes/ContentNew.svelte";
import NotFound from "./routes/NotFound.svelte";

export default {
	"/": Signin,
	"/home": Home,
	"/collection/:collId": Collection,
	"/collection-new": CollectionNew,
	"/content": Content,
	"/content-new/:collId": ContentNew,
	// The catch-all route must always be last
	"*": NotFound
};

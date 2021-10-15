import Home from "./routes/Home.svelte";
import Signin from "./routes/Signin.svelte";
import Collection from "./routes/Collection.svelte";
import CollectionNew from "./routes/CollectionNew.svelte";
import Content from "./routes/Content.svelte";
import ContentNew from "./routes/ContentNew.svelte";
import Bedrock from "./routes/Bedrock.svelte";
import NotFound from "./routes/NotFound.svelte";

export default {
	"/": Home,
	"/signin": Signin,
	"/collection/:collId": Collection,
	"/collection-new": CollectionNew,
	"/content-new/:collId": ContentNew,
	"/content/:context/:collId/:contId": Content,
	"/bedrock": Bedrock,
	// The catch-all route must always be last
	"*": NotFound
};

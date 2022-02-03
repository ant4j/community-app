import Signin from "./routes/Signin.svelte";
import Home from "./routes/Home.svelte";
import Collection from "./routes/Collection.svelte";
import Content from "./routes/Content.svelte";
import Balloon from "./routes/Balloon.svelte";
import NotFound from "./routes/NotFound.svelte";

export default {
	"/signin/:communityCode": Signin,
	"/home": Home,
	"/collection/:collectionId": Collection,
	"/content/:context/:contentId": Content,
	"/balloon": Balloon,
	"/not-found": NotFound,
	"*": NotFound
};

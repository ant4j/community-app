import Index from "./routes/Index.svelte";
import Signin from "./routes/Signin.svelte";
import Home from "./routes/Home.svelte";
import Collection from "./routes/Collection.svelte";
import CollectionNew from "./routes/CollectionNew.svelte";
import Content from "./routes/Content.svelte";
import ContentNew from "./routes/ContentNew.svelte";
import Bedrock from "./routes/Bedrock.svelte";
import Admin from "./routes/admin/Admin.svelte";
import Dashboard from "./routes/admin/Dashboard.svelte";
import NotFound from "./routes/NotFound.svelte";

export default {
	"/": Index,
	"/signin/:commCode": Signin,
	"/home": Home,
	"/collection/:collId": Collection,
	"/collection-new": CollectionNew,
	"/content/:context/:collId/:contId": Content,
	"/content-new/:collId": ContentNew,
	"/bedrock": Bedrock,
	"/admin": Admin,
	"/dashboard": Dashboard,
	"*": NotFound
};

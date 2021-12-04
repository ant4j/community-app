import Index from "./routes/Index.svelte";
import Signin from "./routes/Signin.svelte";
import Home from "./routes/Home.svelte";
import Collection from "./routes/Collection.svelte";
import Content from "./routes/Content.svelte";
import Bedrock from "./routes/Bedrock.svelte";
import Balloon from "./routes/Balloon.svelte";
import Admin from "./routes/admin/Admin.svelte";
import AdminDashboard from "./routes/admin/AdminDashboard.svelte";
import AdminCollections from "./routes/admin/AdminCollections.svelte";
import AdminCollectionNew from "./routes/admin/AdminCollectionNew.svelte";
import AdminCollection from "./routes/admin/AdminCollection.svelte";
import AdminContentNew from "./routes/admin/AdminContentNew.svelte";
import NotFound from "./routes/NotFound.svelte";

export default {
	"/": Index,
	"/signin/:commCode": Signin,
	"/home": Home,
	"/collection/:collId": Collection,
	"/content/:context/:collId/:contId": Content,
	"/bedrock": Bedrock,
	"/balloon": Balloon,
	"/admin": Admin,
	"/admin-dashboard": AdminDashboard,
	"/admin-collections/:commId": AdminCollections,
	"/admin-collection-new/:commId": AdminCollectionNew,
	"/admin-collection/:collId": AdminCollection,
	"/admin-content-new/:collId": AdminContentNew,
	"*": NotFound
};

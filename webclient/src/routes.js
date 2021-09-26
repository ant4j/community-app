import Home from "./routes/Home.svelte";
import Signup from "./routes/Signup.svelte";
import Collection from "./routes/Collection.svelte";
import Content from "./routes/Content.svelte";
import ContentNew from "./routes/ContentNew.svelte";
import NotFound from "./routes/NotFound.svelte";

export default {
    "/": Signup,
    "/home": Home,
    "/collection/:idCollection": Collection,
    "/content": Content,
    "/content-new": ContentNew,
    // The catch-all route must always be last
    "*": NotFound
};
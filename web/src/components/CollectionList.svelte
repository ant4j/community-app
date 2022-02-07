<script>
	import { onMount } from "svelte";
	import { push } from "svelte-spa-router";

	import appconfig from "../appconfig.json";

	export let communityId = "",
		route = "";

	let collectionList = [];

	onMount(() => getCollections());

	async function getCollections() {
		let baseUrl = appconfig.endpoint.cmmSrv.baseUrl;
		let path = appconfig.endpoint.cmmSrv.path.collections;
		let endpoint = `${baseUrl}${path}/${communityId}`;
		let res = await fetch(endpoint, {
			method: "GET",
		});
		let jsonRes = await res.json();
		//TODO fare controllo se lo status e' OK
		collectionList = jsonRes.collectionList;
	}
</script>

<label for="collection-list" class="form-label">Elenco raccolte</label>
<div
	id="collection-list"
	class="list-group"
	aria-describedby="collection-list-help"
>
	{#each collectionList as collection}
		<button
			type="button"
			class="list-group-item list-group-item-action"
			on:click={() => push(`${route}${collection.id}`)}
		>
			{collection.name}
		</button>
	{/each}
</div>
<div class="form-text" id="collection-list-help">
	Seleziona una Raccolta per visualizzarla.
</div>

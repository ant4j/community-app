<script>
	import { onMount } from "svelte";
	import { push } from "svelte-spa-router";
	import { writable, derived } from "svelte/store";

	import appconfig from "../appconfig.json";

	import { blurOnEnter } from "../directives/input";

	export let collectionId = "";

	let collectionName = "";

	let termContentSearchText = "";
	const term = writable("");
	const items = writable([]);
	const itemsFiltered = derived([term, items], ([$term, $items]) =>
		$items.filter((x) => x.title.includes($term))
	);
	$: term.set(termContentSearchText.toUpperCase());

	onMount(() => retrieveContents());

	async function retrieveContents() {
		let baseUrl = appconfig.endpoint.cmmSrv.baseUrl;
		let path = appconfig.endpoint.cmmSrv.path.contents;
		let endpoint = `${baseUrl}${path}/${collectionId}`;
		let res = await fetch(endpoint, {
			method: "GET",
		});
		let json = await res.json();
		//TODO fare controllo se lo status e' OK
		collectionName = json.collectionName;
		items.set(json.contentList);
	}
</script>

<label for="content-list" class="form-label">{collectionName}</label>
<input
	type="search"
	class="form-control text-uppercase"
	placeholder="Cerca"
	bind:value={termContentSearchText}
	use:blurOnEnter
	id="content-search"
/>
<div
	class="list-group content-list-group"
	aria-describedby="content-list-help"
	id="content-list"
>
	{#each $itemsFiltered as item}
		<button
			type="button"
			class="list-group-item list-group-item-action"
			on:click={() =>
				push(
					`/content/${appconfig.viewing}/${item.id}`
				)}
		>
			{item.title}
		</button>
	{/each}
</div>
<div class="form-text" id="content-list-help">
	Seleziona un contenuto per visualizzarlo.
</div>

<style>
	.content-list-group {
		max-height: 380px;
		overflow: scroll;
		-webkit-overflow-scrolling: touch;
	}

	#content-search {
		margin-bottom: 8px;
	}
</style>

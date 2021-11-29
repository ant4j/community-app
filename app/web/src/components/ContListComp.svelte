<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import { push } from "svelte-spa-router";
	import { writable, derived } from "svelte/store";
	import { blurOnEnter } from "../directives/inputDirectives";

	export let collId = "";

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
		console.log("retrieveContents, collId: " + collId);
		let res = await fetch(
			endpoint.service.getContents + "?collId=" + collId
		);
		let json = await res.json();

		console.log("retrieveContents, json: " + JSON.stringify(json));

		collectionName = json.collName;

		items.set(json.data);
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
	<!-- TODO centralizzare il context=0 nel endpoint.json che diventera' config.json -->
	<!-- TODO generalizzare la route del push in modo che funziona anche per la modalita' admin -->
	{#each $itemsFiltered as item}
		<button
			type="button"
			class="list-group-item list-group-item-action"
			on:click={() => push("/content/0/" + collId + "/" + item.id)}
			>{item.title}</button
		>
	{/each}
</div>
<div class="form-text" id="content-list-help">
	Seleziona un contenuto per visualizzarlo.
</div>

<style>
	.content-list-group {
		max-height: 400px;
		overflow: scroll;
		-webkit-overflow-scrolling: touch;
	}

	#content-search {
		margin-bottom: 8px;
	}
</style>

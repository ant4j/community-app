<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import { push, replace } from "svelte-spa-router";
	import BackComp from "../components/BackComp.svelte";
	import { isSignedIn } from "../utils";
	import { writable, derived } from "svelte/store";

	export let params = {};

	let collectionName = "";

	let termContentSearchText = "";

	const term = writable("");
	const items = writable([]);
	const itemsFiltered = derived([term, items], ([$term, $items]) =>
		$items.filter((x) => x.title.includes($term))
	);

	$: term.set(termContentSearchText);

	let isSignedInCheck = false;

	onMount(() => init());

	function init() {
		isSignedInCheck = isSignedIn();
		if (isSignedInCheck) {
			retrieveContents();
		} else {
			replace("/signin");
		}
	}

	async function retrieveContents() {
		console.log("retrieveContents, params.collId: " + params.collId);
		let res = await fetch(
			endpoint.service.getContents + "?collId=" + params.collId
		);
		let json = await res.json();

		console.log("retrieveContents, json: " + JSON.stringify(json));

		collectionName = json.collName;

		items.set(json.data);
	}
</script>

{#if isSignedInCheck}
	<div class="mb-3 text-center">
		<BackComp />
	</div>

	<div class="mb-3">
		<h5>{collectionName}</h5>
	</div>

	<div class="mb-3">
		<input
			type="search"
			class="form-control"
			placeholder="Cerca"
			bind:value={termContentSearchText}
			id="content-search"
		/>
	</div>

	<div class="mb-3">
		<!-- <label for="content-list" class="form-label" /> -->
		<div
			class="list-group content-list-group"
			aria-describedby="content-list-help"
			id="content-list"
		>
			<!-- TODO centralizzare il context=0 nel endpoint.json che diventera' config.json -->
			{#each $itemsFiltered as item}
				<button
					type="button"
					class="list-group-item list-group-item-action"
					on:click={() =>
						push("/content/0/" + params.collId + "/" + item.id)}
					>{item.title}</button
				>
			{/each}
		</div>
		<div class="form-text" id="content-list-help">
			Seleziona un contenuto per visualizzarlo.
		</div>
	</div>

	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				class="btn btn-primary"
				type="button"
				on:click={() => push("/content-new/" + params.collId)}
				>Aggiungi un Nuovo Contenuto <i
					class="bi bi-file-earmark-plus"
				/></button
			>
		</div>
	</div>
{/if}

<style>
	.content-list-group {
		max-height: 360px;
		overflow: scroll;
		-webkit-overflow-scrolling: touch;
	}
</style>

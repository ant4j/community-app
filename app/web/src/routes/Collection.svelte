<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import { push, replace } from "svelte-spa-router";
	import BackBtn from "../components/BackBtn.svelte";
	import Utils from "../utils";

	export let params = {};

	let collectionName = "";
	let contentArr = [];

	onMount(() => init());

	function init() {
		if (Utils.isSignedIn()) {
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
		contentArr = json.data;
	}
</script>

{#if Utils.isSignedIn()}
	<div class="mb-3 text-center">
		<BackBtn />
	</div>

	<div class="mb-3">
		<h5>{collectionName}</h5>
	</div>

	<div class="mb-3">
		<!-- <label for="content-list" class="form-label" /> -->
		<div
			class="list-group"
			aria-describedby="content-list-help"
			id="content-list"
		>
			<!-- TODO centralizzare il context=0 nel endpoint.json che diventera' config.json -->
			{#each contentArr as contentEl}
				<button
					type="button"
					class="list-group-item list-group-item-action"
					on:click={() =>
						push(
							"/content/0/" + params.collId + "/" + contentEl.id
						)}>{contentEl.title}</button
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
				>Aggiungi un nuovo contenuto <i
					class="bi bi-file-earmark-plus"
				/></button
			>
		</div>
	</div>
{/if}

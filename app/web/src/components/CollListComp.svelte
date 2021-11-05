<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import { push } from "svelte-spa-router";

	export let commId = "";
	export let route = "";

	let collArr = [];

	onMount(() => retrieveCollections());

	async function retrieveCollections() {
		let res = await fetch(
			endpoint.service.getCollections + "?commId=" + commId
		);
		let json = await res.json();
		collArr = json;
	}
</script>

<label for="coll-list" class="form-label">Elenco raccolte</label>
<div class="list-group" aria-describedby="coll-list-help" id="coll-list">
	{#each collArr as collEl}
		<button
			type="button"
			class="list-group-item list-group-item-action"
			on:click={() => push(route + collEl.id)}>{collEl.name}</button
		>
	{/each}
</div>
<div class="form-text" id="coll-list-help">
	Seleziona una Raccolta per visualizzarla.
</div>

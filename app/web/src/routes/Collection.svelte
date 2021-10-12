<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import { push, replace } from "svelte-spa-router";
	import BackBtn from "../components/BackBtn.svelte";

	export let params = {};

	let contentArr = [];

	onMount(() => retrieveContents());

	async function retrieveContents() {
		console.log("retrieveContents, params.collId: " + params.collId);
		let res = await fetch(
			endpoint.service.getContents + "?collId=" + params.collId
		);
		let json = await res.json();
		contentArr = json;
	}
</script>

<div class="mb-3 text-center">
	<BackBtn />
</div>

<div class="mb-3">
	<label for="content-list" class="form-label" />
	<div class="list-group" id="content-list">
		{#each contentArr as contentEl}
			<button
				type="button"
				class="list-group-item list-group-item-action"
				on:click={() =>
					push("/content/" + params.collId + "/" + contentEl.id)}
				>{contentEl.title}</button
			>
		{/each}
	</div>
</div>

<div class="mb-3">
	<div class="d-grid gap-2">
		<button
			class="btn btn-primary"
			type="button"
			on:click={() => push("/content-new/" + params.collId)}
			>Aggiungi Nuovo</button
		>
	</div>
</div>

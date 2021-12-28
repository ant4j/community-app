<script>
	import endpoint from "../../endpoint.json";
	import { pop, replace } from "svelte-spa-router";
	import BackComp from "../../components/BackComp.svelte";
	import Cookies from "js-cookie";
	import httpStatus from "http-status";
	import { onMount } from "svelte";
	import { isSignedIn, MODE } from "../../utils";

	export let params = "";

	let collData = { commId: params.commId };

	let view = false;

	onMount(() => init());

	function init() {
		if (isSignedIn(MODE.ADMIN)) {
			view = true;
		} else {
			replace("/");
		}
	}

	async function createCollection() {
		let res = await fetch(endpoint.service.addCollection, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(collData),
		});
		if (res.status == httpStatus.CREATED) {
			pop();
		} else if (res.status == httpStatus.CONFLICT) {
			alert("La collection esiste gia'");
		}
	}
</script>

{#if view}
	<div class="mb-3 text-center">
		<BackComp />
	</div>

	<div class="mb-3">
		<h5>Crea una Nuova Raccolta</h5>
	</div>

	<div class="mb-3">
		<label for="coll-name" class="form-label">Nome raccolta</label>
		<input
			type="text"
			class="form-control"
			placeholder="Nome raccolta"
			bind:value={collData.name}
			id="coll-name"
		/>
	</div>

	<div class="mb-3">
		<label for="coll-type" class="form-label">Tipo raccolta</label>
		<select
			class="form-select"
			aria-label="Tipo raccolta"
			bind:value={collData.type}
			id="coll-type"
		>
			<option value="0" selected>Canti</option>
			<option value="1">Letture</option>
		</select>
	</div>

	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				class="btn btn-primary"
				type="button"
				on:click={() => createCollection()}
				>Crea Raccolta <i class="bi bi-stars" /></button
			>
		</div>
	</div>
{/if}

<script>
	import endpoint from "../endpoint.json";
	import { push, pop, replace } from "svelte-spa-router";
	import BackBtn from "../components/BackBtn.svelte";
	import Cookies from "js-cookie";

	let collData = { commId: Cookies.get("signin-comm-id") };

	async function createCollection() {
		console.log("createCollection, collData: " + JSON.stringify(collData));

		let res = await fetch(endpoint.service.addCollection, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(collData),
		});
		let json = await res.json();
		console.log("createCollection, json: " + JSON.stringify(json));
		if (json.status.code == "1") {
			push("/home");
		} else if(json.status.code == "0") {
			alert("La collection esiste gia'");
		}
	}
</script>

<div class="mb-3 text-center">
	<BackBtn />
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
		<option value="0" selected>Cantico</option>
		<option value="1">Lettura</option>
	</select>
</div>

<div class="mb-3">
	<div class="d-grid gap-2">
		<button
			class="btn btn-primary"
			type="button"
			on:click={() => createCollection()}>Crea raccolta</button
		>
	</div>
</div>

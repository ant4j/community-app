<script>
	import { onMount } from "svelte";
	import Cookies from "js-cookie";
	import { push, pop, replace } from "svelte-spa-router";
	import endpoint from "../../endpoint.json";

	let commArr = [];
	let commToCreate = {};

	onMount(() => init());

	function init() {
		retrieveCommunities();
	}

	async function retrieveCommunities() {
		let res = await fetch(endpoint.service.getCommunities);
		let json = await res.json();
		console.log("retrieveCommunities, json: " + JSON.stringify(json));
		commArr = json;
	}

	async function createCommunity() {
		commToCreate.adminId = Cookies.get("admin-id-133-1");
		let res = await fetch(endpoint.service.addCommunity, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(commToCreate),
		});
		let json = await res.json();
		console.log("createCommunity, json: " + JSON.stringify(json));
		if (json.status.code == "1") {
			// alert("Community creata.");
			retrieveCommunities();
		} else {
			alert("Community gia' esistente");
		}
	}

	function exit() {
		Cookies.remove("admin-id-133-1");
		Cookies.remove("adnim-username-133-1");
		push("/signin");
	}
</script>

<div class="mb-3">
	<h5>Area Amministratore</h5>
</div>

<div class="mb-3">
	Hai effettuato l'accesso come <span class="fw-bolder"
		>{Cookies.get("adnim-username-133-1")}</span
	>.
</div>
<div class="mb-3">Qui puoi gestire i contenuti di CommunityApp.</div>

<div class="mb-3">
	<label for="comm-list" class="form-label">Elenco delle Community</label>
	<div class="list-group" aria-describedby="comm-list-help" id="comm-list">
		{#if commArr.length}
			{#each commArr as commEl}
				<button
					type="button"
					class="list-group-item list-group-item-action"
					on:click={() => {
						alert("WIP");
					}}>{commEl.name}</button
				>
			{/each}
		{/if}
	</div>
	<div class="form-text" id="comm-list-help">
		Seleziona la Community in cui vuoi entrare.
	</div>
</div>

<hr />
<div class="mb-3">
	<label for="" class="form-label">Crea una Community</label>
	<input
		type="text"
		class="form-control"
		placeholder="Nome Community"
		bind:value={commToCreate.name}
	/>
</div>

<div class="mb-3">
	<input
		type="text"
		class="form-control"
		placeholder="Parola d'ordine"
		bind:value={commToCreate.watchword}
	/>
</div>

<div class="mb-3">
	<div class="d-grid gap-2">
		<button
			type="button"
			class="btn btn-primary"
			on:click={() => createCommunity()}
			>Crea Community <i class="bi bi-people-fill" /></button
		>
	</div>
</div>

<div class="mb-3">
	<div class="btn-group" role="group">
		<button
			type="button"
			class="btn btn-outline-primary"
			on:click={() => exit()}>Esci</button
		>
	</div>
</div>

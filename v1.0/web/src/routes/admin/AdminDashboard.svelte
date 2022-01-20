<script>
	import { onMount } from "svelte";
	import Cookies from "js-cookie";
	import { push, replace } from "svelte-spa-router";
	import endpoint from "../../endpoint.json";
	import httpStatus from "http-status";
	import { isSignedIn, MODE } from "../../utils";

	let commArr = [];
	let commToCreate = {};

	let view = false;

	onMount(() => init());

	function init() {
		if (isSignedIn(MODE.ADMIN)) {
			retrieveCommunities();
			view = true;
		} else {
			replace("/admin");
		}
	}

	// TODO recuperare solo le communities create da un certo admin con cui si e' acceduti
	async function retrieveCommunities() {
		let res = await fetch(endpoint.service.retrieveCommunities);
		let json = await res.json();
		commArr = json;
	}

	async function createCommunity() {
		commToCreate.adminId = Cookies.get("admin-id-133-1");
		let res = await fetch(endpoint.service.createCommunity, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(commToCreate),
		});
		if (res.status == httpStatus.CREATED) {
			retrieveCommunities();
		} else if (res.status == httpStatus.CONFLICT) {
			alert("La community esiste gia'");
		}
	}

	function exit() {
		Cookies.remove("admin-id-133-1");
		Cookies.remove("adnim-username-133-1");
		push("/admin");
	}
</script>

{#if view}
	<div class="mb-3">
		<h5>Area Amministratore</h5>
	</div>

	<div class="mb-3">
		Hai effettuato l'accesso come <span class="fw-bolder"
			>{Cookies.get("adnim-username-133-1")}</span
		>.
	</div>

	<div class="mb-3">
		<label for="comm-list" class="form-label">Elenco delle Community</label>
		<div
			class="list-group"
			aria-describedby="comm-list-help"
			id="comm-list"
		>
			{#if commArr.length}
				{#each commArr as commEl}
					<button
						type="button"
						class="list-group-item list-group-item-action"
						on:click={() => {
							push("/admin-collections/" + commEl.id);
						}}>{commEl.name}</button
					>
				{/each}
			{/if}
		</div>
		<!-- <div class="form-text" id="comm-list-help">
		Seleziona la Community in cui vuoi entrare.
	</div> -->
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
{/if}

<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import { push, pop, replace } from "svelte-spa-router";
	import Cookies from "js-cookie";

	let commArr = [];
	let signinData = {};

	let commName = "";

	onMount(() => init());

	function init() {
		retrieveCommunities();
	}

	async function retrieveCommunities() {
		console.log(
			"COOKIES: " +
				Cookies.get("signin-comm-id-133-1") +
				", " +
				Cookies.get("signin-comm-name-133-1") +
				", " +
				Cookies.get("signin-username-133-1")
		);

		let res = await fetch(endpoint.service.getCommunities);
		let json = await res.json();
		console.log("retrieveCommunities, json: " + JSON.stringify(json));
		commArr = json;
		if (commArr.length > 0) {
			signinData.commId = commArr[0].id;
			commName = commArr[0].name;
		}
	}

	async function signin() {
		console.log("signin, signinData: " + JSON.stringify(signinData));
		let res = await fetch(endpoint.service.signin, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(signinData),
		});
		let json = await res.json();
		console.log("signin, json: " + JSON.stringify(json));
		if (json.status.code == "1") {
			const inHalfADay = 0.5;

			Cookies.set("signin-comm-id-133-1", signinData.commId, {
				expires: inHalfADay,
			});
			Cookies.set("signin-comm-name-133-1", commName, {
				expires: inHalfADay,
			});
			Cookies.set("signin-username-133-1", json.username, {
				expires: inHalfADay,
			});

			console.log(
				"COOKIES: " +
					Cookies.get("signin-comm-id-133-1") +
					", " +
					Cookies.get("signin-comm-name-133-1") +
					", " +
					Cookies.get("signin-username-133-1")
			);

			push("/");
		} else {
			alert("errore");
			console.log("errore");
		}
	}
</script>

<div class="mb-3">
	<h5>Accesso</h5>
</div>

<div class="mb-3">
	<label for="comm-list" class="form-label">Elenco delle Community</label>
	<div class="list-group" aria-describedby="comm-list-help" id="comm-list">
		{#if commArr.length}
			{#each commArr as commEl}
				<button
					type="button"
					class="list-group-item list-group-item-action"
					class:active={signinData.commId === commEl.id}
					on:click={() => {
						signinData.commId = commEl.id;
						commName = commEl.name;
					}}>{commEl.name}</button
				>
			{/each}
		{/if}
	</div>
	<div class="form-text" id="comm-list-help">
		Seleziona la Community in cui vuoi entrare.
	</div>
</div>

<div class="mb-3">
	<label for="watchword" class="form-label"
		>Parola d'ordine della Community</label
	>
	<input
		type="text"
		class="form-control"
		placeholder="Parola d'ordine"
		aria-describedby="watchword-help"
		bind:value={signinData.watchword}
		id="watchword"
	/>
	<div class="form-text" id="watchword-help">
		Serve per entrare nella community selezionata.
	</div>
</div>

<!-- <hr />

<div class="mb-3">
	<label for="username" class="form-label"
		>Il tuo Nome utente nella Community</label
	>
	<input
		type="text"
		class="form-control"
		maxlength="3"
		placeholder="Nome utente"
		aria-describedby="username-help"
		bind:value={signinData.username}
		id="username"
	/>
	<div class="form-text" id="username-help">
		Prime 2 lettere del tuo nome + prima lettera del tuo cognome.
		<br />
		Es.: Mario Rossi = mar.
	</div>
</div> -->

<div class="mb-3">
	<div class="d-grid gap-2">
		<button type="button" class="btn btn-primary" on:click={() => signin()}
			>Entra <i class="bi bi-door-open" /></button
		>
	</div>
</div>

<div class="mb-3">
	<button
		type="button"
		class="btn btn-link btn-sm text-decoration-none"
		on:click={() => push("/bedrock")}
	>
		Fondamento di CommunityApp
	</button>
</div>

<!-- <div class="mb-3">
	<button
		type="button"
		class="btn btn-link btn-sm text-decoration-none"
		on:click={() => push("/admin-signin")}
	>
		Area Amministratore
	</button>
</div> -->

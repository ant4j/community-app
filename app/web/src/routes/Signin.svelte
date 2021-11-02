<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import { push, replace } from "svelte-spa-router";
	import Cookies from "js-cookie";
	import { isSignedIn } from "../utils";

	export let params = {};

	let commId = "";
	let commCode = "";
	let commName = "";
	let watchword = "";

	let view = false;

	onMount(() => init());

	function init() {
		console.log(
			"COOKIES: " +
				Cookies.get("signin-comm-id-133-1") +
				", " +
				Cookies.get("signin-comm-code-133-1") +
				", " +
				Cookies.get("signin-comm-name-133-1") +
				", " +
				Cookies.get("signin-username-133-1")
		);

		if (!isSignedIn()) {
			retrieveCommunity();
		} else {
			replace("/home");
		}
	}

	async function retrieveCommunity() {
		let res = await fetch(
			endpoint.service.getCommunity + "?commCode=" + params.commCode
		);
		let json = await res.json();
		console.log("retrieveCommunity, json: " + JSON.stringify(json));
		if (json.status.code == "1") {
			commId = json.data.id;
			commCode = json.data.code;
			commName = json.data.name;
			view = true;
		} else {
			// alert("community non esistente");
			replace("/");
		}
	}

	async function signin() {
		let reqData = { commId: commId, watchword: watchword };
		console.log("signin, reqData: " + JSON.stringify(reqData));
		let res = await fetch(endpoint.service.signin, {
			method: "post",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(reqData),
		});
		let json = await res.json();
		console.log("signin, json: " + JSON.stringify(json));
		if (json.status.code == "1") {
			const inHalfADay = 0.5;

			Cookies.set("signin-comm-id-133-1", commId, {
				expires: inHalfADay,
			});
			Cookies.set("signin-comm-code-133-1", commCode, {
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
					Cookies.get("signin-comm-code-133-1") +
					", " +
					Cookies.get("signin-comm-name-133-1") +
					", " +
					Cookies.get("signin-username-133-1")
			);

			push("/home");
		} else {
			alert("errore");
			console.log("errore");
		}
	}
</script>

{#if view}
	<div class="mb-3">
		<h5>Accesso</h5>
	</div>

	<div class="mb-3">
		<div class="card text-white bg-primary mb-3">
			<div class="card-body">
				<p class="card-text">
					<i class="bi bi-people-fill" />
					{commName}
				</p>
			</div>
		</div>
	</div>
	<!-- <div class="mb-3">
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
</div> -->

	<div class="mb-3">
		<label for="watchword" class="form-label"
			>Parola d'ordine della Community</label
		>
		<input
			type="text"
			class="form-control"
			placeholder="Parola d'ordine"
			aria-describedby="watchword-help"
			bind:value={watchword}
			id="watchword"
		/>
		<div class="form-text" id="watchword-help">
			Serve per entrare nella community selezionata.
		</div>
	</div>

	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				type="button"
				class="btn btn-primary"
				on:click={() => signin()}
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
{/if}

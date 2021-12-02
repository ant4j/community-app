<script>
	import { onMount } from "svelte";
	import endpoint from "../endpoint.json";
	import { push, replace } from "svelte-spa-router";
	import { querystring } from "svelte-spa-router";
	import { parse } from "qs";
	import Cookies from "js-cookie";
	import { isSignedIn, setupCookies } from "../utils";
	import ModalComp from "../components/ModalComp.svelte";
	import { blurOnEnter } from "../directives/inputDirectives";

	let commId = "";
	let commCode = "";
	let commName = "";
	let watchword = "";

	let view = false;

	let watchwordComp;
	let eyeClass = "bi bi-eye-slash";

	let modalComp;

	onMount(() => init());

	function init() {
		console.log(
			`COOKIES: ${Cookies.get("signin-comm-id-133-1")}, ${Cookies.get(
				"signin-comm-code-133-1"
			)}, ${Cookies.get("signin-comm-name-133-1")}, ${Cookies.get(
				"signin-username-133-1"
			)}`
		);

		if (!isSignedIn()) {
			retrieveCommunity();
		} else {
			replace("/home");
		}
	}

	//TODO fare i replace da un'altra parte
	async function retrieveCommunity() {
		let queryParams = parse($querystring);
		if (typeof queryParams === "object" && queryParams.hasOwnProperty("c")) {
			let res = await fetch(
				endpoint.service.getCommunity + "?commCode=" + queryParams.c
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
		} else {
			replace("/");
		}
	}

	async function signin() {
		if (watchword == "") {
			modalComp.openModal(
				"Accesso non riuscito",
				"Ops! Non hai inserito la parola d'ordine! Riprova."
			);
		} else {
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
				setupCookies({
					commId: commId,
					commCode: commCode,
					commName: commName,
					username: json.username,
				});
				push("/home");
			} else {
				modalComp.openModal(
					"Accesso non riuscito",
					"Ops! Hai sbagliato la parola d'ordine! Riprova."
				);
			}
		}
	}

	//TODO si puo' trasformare in una direttiva
	function toggleEye() {
		if (watchwordComp.type == "password") {
			watchwordComp.type = "text";
			eyeClass = "bi bi-eye";
		} else {
			watchwordComp.type = "password";
			eyeClass = "bi bi-eye-slash";
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

	<div class="mb-3">
		<label for="watchword" class="form-label"
			>Parola d'ordine della Community</label
		>
		<div class="input-group">
			<input
				type="password"
				class="form-control"
				placeholder="parola d'ordine"
				aria-describedby="watchword-help"
				bind:value={watchword}
				bind:this={watchwordComp}
				use:blurOnEnter
				id="watchword"
			/>
			<span
				class="input-group-text"
				on:click={() => toggleEye()}
				id="toggle-eye"
			>
				<i class={eyeClass} />
			</span>
		</div>
		<div class="form-text" id="watchword-help">
			Serve per entrare nella community.
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

	<div class="mb-3 text-center text-small">
		<a class="text-decoration-none text-muted" href="#/bedrock"
			>Fondamento di CommunityApp</a
		>
		<!-- &#x1F388; -->
	</div>

	<div class="mb-3">
		<ModalComp icon="bi bi-exclamation-diamond" bind:this={modalComp} />
	</div>
{/if}

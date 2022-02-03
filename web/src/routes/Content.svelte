<script>
	import { onMount } from "svelte";
	import { push, replace } from "svelte-spa-router";
	import httpStatus from "http-status";

	import appconfig from "../appconfig.json";
	import cookies from "../handlers/cookies";

	import BackButton from "../components/BackButton.svelte";
	import HomeButton from "../components/HomeButton.svelte";
	import ErrorModal from "../components/ErrorModal.svelte";

	export let params = {
		context: appconfig.contentContext.viewing,
		contentId: 0,
	};

	let view = {
		display: false,
		errorModal: undefined,
	};

	let model = {
		title: "",
		text: "",
	};

	onMount(() => init());

	function init() {
		if (cookies.areCookiesSetup()) {
			getContent();
			view.display = true;
		} else {
			replace("/");
		}
	}

	async function getContent() {
		let baseUrl = appconfig.endpoint.cmmSrv.baseUrl;
		let path = appconfig.endpoint.cmmSrv.path.contentText;
		let endpoint = `${baseUrl}${path}/${params.contentId}`;
		let res = await fetch(endpoint);
		let json = await res.json();
		//TODO fare controllo se lo status e' OK
		model.title = json.contentTitle;
		model.text = json.text;
	}

	async function proposeContent() {
		let jsonBody = {
			communityId: cookies.getCommunityIdCookie(),
			contentId: params.contentId,
			username: cookies.getUsernameCookie(),
		};
		let baseUrl = appconfig.endpoint.cmmSrv.baseUrl;
		let path = appconfig.endpoint.cmmSrv.path.proposal;
		let endpoint = `${baseUrl}${path}`;
		let res = await fetch(endpoint, {
			method: "PUT",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(jsonBody),
		});
		if (res.status == httpStatus.OK) {
			push("/home");
		} else if (res.status == httpStatus.CONFLICT) {
			view.errorModal.show(
				"Attenzione",
				"Ops! E' stato già proposto! Prova a proporre qualcosa di diverso."
			);
		} else {
			view.errorModal.show(
				"Errore interno",
				"Si è verificato un problema. Riprova."
			);
		}
	}
</script>

{#if view.display}
	{#if params.context == appconfig.contentContext.participation}
		<div class="mb-3 text-start">
			<BackButton />
		</div>
		<div class="mb-3 text-center">
			<div class="card bg-light text-muted participation-msg-card">
				<div class="card-body">
					<img
						src="img/party.png"
						width="35"
						height="35"
						alt="party"
					/>
					Partecipazione in corso
					<img
						src="img/party.png"
						width="35"
						height="35"
						alt="party"
					/>
				</div>
			</div>
		</div>
	{:else}
		<div class="mb-3 text-start">
			<BackButton />
			<HomeButton />
		</div>
	{/if}

	<div class="mb-3">
		<h5 class="fw-bolder">{model.title}</h5>
	</div>

	<div class="mb-3">
		<div class="content-text">
			{model.text}
		</div>
	</div>

	{#if params.context == appconfig.contentContext.participation}
		<div class="mb-3">
			<div class="d-grid gap-2">
				<button
					class="btn btn-primary"
					type="button"
					on:click={() => push("/balloon")}
				>
					Abbiamo finito! <i class="bi bi-alarm" />
				</button>
			</div>
		</div>
	{:else}
		<div class="mb-3">
			<div class="d-grid gap-2">
				<button
					class="btn btn-primary"
					type="button"
					on:click={() => proposeContent()}
				>
					Proponi <i class="bi bi-megaphone" />
				</button>
			</div>
		</div>
	{/if}

	<div class="mb-3">
		<ErrorModal bind:this={view.errorModal} />
	</div>
{/if}

<style>
	.content-text {
		white-space: pre;
	}
</style>

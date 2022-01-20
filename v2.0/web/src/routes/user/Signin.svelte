<script>
	import { onMount } from "svelte";
	import { push, replace } from "svelte-spa-router";
	import httpStatus from "http-status";

	import { userCookies } from "../../handlers/cookies";
	import { endpoint } from "../../handlers/endpoint";
	import { t } from "../../handlers/i18n";
	
	import PasswordField from "../../components/PasswordField.svelte";
	import ErrorModal from "../../components/ErrorModal.svelte";
	import LocaleSwitch from "../../components/LocaleSwitch.svelte";

	export let params = { communityCode: "" };

	let view = {
		display: false,
		errorModal: undefined,
	};

	let model = {
		communityId: 0,
		communityCode: params.communityCode,
		communityName: "",
		watchword: "",
	};

	onMount(() => init());

	function init() {
		if (!userCookies.isUserCookiesSetup()) {
			getCommunity();
		} else {
			replace("/home");
		}
	}

	async function getCommunity() {
		let res = await fetch(endpoint.buildCommunityGet(model.communityCode));
		if (res.status == httpStatus.OK) {
			let jsonRes = await res.json();
			model.communityId = jsonRes.id;
			model.communityName = jsonRes.name;
			view.display = true;
		} else if (res.status == httpStatus.NOT_FOUND) {
			replace("/");
		} else {
			replace("/");
		}
	}

	async function signin() {
		if (model.watchword == "") {
			view.errorModal.show($t("access-failed"), $t("watchword-forgot"));
			return;
		}
		let jsonReq = {
			communityId: model.communityId,
			watchword: model.watchword,
		};
		let res = await fetch(endpoint.buildAuthPost(), {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: jsonReq,
		});
		if (res.status == httpStatus.OK) {
			let jsonRes = await res.json();
			userCookies.removeOldCookies();
			userCookies.setupUserCookies({
				communityId: model.communityId,
				communityCode: model.communityCode,
				communityName: model.communityName,
				username: jsonRes.username,
			});
			push("/home");
		} else if (res.status == httpStatus.UNAUTHORIZED) {
			view.errorModal.show(
				$t("access-failed"),
				"Ops! Hai sbagliato la parola d'ordine! Riprova."
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
	<div class="mb-3">
		<h5>{$t("access")}</h5>
	</div>

	<div class="mb-3">
		<div class="card text-white bg-primary mb-3">
			<div class="card-body">
				<p class="card-text">
					<i class="bi bi-people-fill" />
					{model.communityName}
				</p>
			</div>
		</div>
	</div>

	<div class="mb-3">
		<PasswordField
			id="watchword"
			bind:value={model.watchword}
			label={$t("watchword-label")}
			placeholder={$t("watchword")}
			help={$t("watchword-help-msg")}
		/>
	</div>

	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				type="button"
				class="btn btn-primary"
				on:click={() => signin()}
			>
				{$t("enter")} <i class="bi bi-door-open" />
			</button>
		</div>
	</div>

	<div class="mb-3 text-center text-small">
		<a class="text-decoration-none text-muted" href="#/bedrock">
			{$t("community-app-bedrock")}
		</a>
		<!-- &#x1F388; -->
	</div>

	<div class="mb-3 text-center text-small">
		<LocaleSwitch />
	</div>

	<div class="mb-3">
		<ErrorModal bind:this={view.errorModal} />
	</div>
{/if}

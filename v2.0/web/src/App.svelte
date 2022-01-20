<script>
	import Router from "svelte-spa-router";
	import routes from "./routes";

	import { setupI18n } from "./handlers/i18n";
	import config from "./config.json";

	const localeLoading = setupI18n({ withLocale: config.default_locale });
</script>

<main>
	<div class="container">
		<div class="mb-3">
			<div class="w-75 p-1 app-logo text-center">
				<h1 class="fw-bolder">CommunityApp</h1>
				<div id="slogan" class="text-small">
					Psalm 133 &middot; It&rsquo;s Good Together!
				</div>
			</div>
		</div>
		{#await localeLoading}
			<div class="d-flex justify-content-center">
				<div class="spinner-border text-primary" role="status">
					<span class="visually-hidden">Loading...</span>
				</div>
			</div>
		{:then}
			<Router {routes} />
		{:catch error}
			<p class="locale-loading-error-msg">{error.message}</p>
		{/await}
	</div>
</main>

<style>
	:global(.text-small) {
		font-size: 11px;
	}

	.app-logo {
		color: #ffff;
		background: #0d6efd;
		border-radius: 6px 40px;
	}

	.locale-loading-error-msg {
		color: rgb(255, 0, 0);
	}
</style>

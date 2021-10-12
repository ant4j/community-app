<script>
	import Router from "svelte-spa-router";
	import routes from "./routes";
	// import { Styles, Button } from "sveltestrap";
</script>

<svelte:head>
	<!-- Bootstrap CSS -->
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
	/>
	<!-- Bootstrap CSS icons -->
	<link
		rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	/>

	<!-- Popper js -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>

	<!-- Bootstrap js -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"></script>
</svelte:head>

<!-- <Styles /> -->

<main>
	<div class="container">
		<div class="mb-3">
			<h1>CommunityApp</h1>
		</div>
		<Router {routes} />
	</div>
</main>

<style>
	:global(.list-group) {
		max-height: 200px;
		overflow: scroll;
		-webkit-overflow-scrolling: touch;
	}

	:global(.text-small) {
		font-size: 11px;
	}
</style>

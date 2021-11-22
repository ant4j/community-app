<script>
	import Router from "svelte-spa-router";
	import { location } from "svelte-spa-router";
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
			<div class="w-75 p-1 app-logo text-center">
				<h1 class="fw-bolder">CommunityApp</h1>
				<div class="text-small">
					{#if $location.includes("admin")}
						ADMIN <i class="bi bi-wrench" />
					{:else}
						Psalm 133 &#8231; It&rsquo;s Good Together!
					{/if}
				</div>
			</div>
		</div>
		<Router {routes} />
	</div>
</main>

<style>
	/* :global(.list-group) {
		max-height: 200px;
		overflow: scroll;
		-webkit-overflow-scrolling: touch;
	} */

	:global(.text-small) {
		font-size: 11px;
	}

	.app-logo {
		color: #ffff;
		background: #0d6efd;
		border-radius: 6px 40px;
	}
</style>

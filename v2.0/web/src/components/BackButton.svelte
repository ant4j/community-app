<script>
	import { pop } from "svelte-spa-router";
</script>

<button
	type="button"
	class="btn btn-link text-decoration-none"
	on:click={() => pop()}
>
	<i class="bi bi-arrow-left-short" /> indietro
</button>

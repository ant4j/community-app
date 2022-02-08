<script>
	import { push } from "svelte-spa-router";
</script>

<button
	type="button"
	class="btn btn-link text-decoration-none"
	on:click={() => push("/home")}
>
	<i class="bi bi-house" /> Home
</button>

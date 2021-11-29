<script>
	import { onMount } from "svelte";
	import BackComp from "../components/BackComp.svelte";
	import { push, replace } from "svelte-spa-router";
	import { isSignedIn } from "../utils";
	import confetti from "canvas-confetti";

	let view = false;

	onMount(() => init());

	function init() {
		if (isSignedIn()) {
			view = true;
			confetti({
				particleCount: 100,
				spread: 50,
				origin: { y: 0.9 },
			});
		} else {
			replace("/");
		}
	}
</script>

{#if view}
	<div class="mb-3 text-center">
		<BackComp showHomeBtn="true" />
	</div>

	<div class="mb-3 text-center">
		<h6 class="rainbow rainbow_text_animated text-uppercase">
			Grazie per la tua partecipazione!
			<br />
			Ecco a te un bel palloncino!
		</h6>
	</div>

	<div class="mb-3 text-center" style="height:350px">
		<div class="balloon" />
	</div>

	<div class="mb-3">
		<div class="d-grid gap-2">
			<button
				class="btn btn-primary"
				type="button"
				on:click={() => push("/home")}
				>Grazie <i class="bi bi-emoji-smile" /></button
			>
		</div>
	</div>
{/if}

<style>
	.rainbow {
		font-family: monospace;
		letter-spacing: 4px;
	}
	.rainbow_text_animated {
		background: linear-gradient(
			to right,
			#6666ff,
			#0099ff,
			#00ff00,
			#ff3399,
			#6666ff
		);
		-webkit-background-clip: text;
		background-clip: text;
		color: transparent;
		animation: rainbow_animation 6s ease-in-out infinite;
		background-size: 400% 100%;
	}

	@keyframes rainbow_animation {
		0%,
		100% {
			background-position: 0 0;
		}

		50% {
			background-position: 100% 0;
		}
	}

	.balloon {
		display: inline-block;
		width: 120px;
		height: 145px;
		background: hsl(215, 50%, 65%);
		border-radius: 80%;
		position: relative;
		box-shadow: inset -10px -10px 0 rgba(0, 0, 0, 0.07);
		margin: 20px 30px;
		transition: transform 0.5s ease;
		z-index: 10;
		animation: balloons 4s ease-in-out infinite;
		transform-origin: bottom center;
	}

	@keyframes balloons {
		0%,
		100% {
			transform: translateY(0) rotate(-4deg);
		}
		50% {
			transform: translateY(-25px) rotate(4deg);
		}
	}

	.balloon:before {
		content: "▲";
		font-size: 20px;
		color: hsl(215, 30%, 50%);
		display: block;
		text-align: center;
		width: 100%;
		position: absolute;
		bottom: -12px;
		z-index: -100;
	}

	.balloon:after {
		display: inline-block;
		top: 153px;
		position: absolute;
		height: 200px;
		width: 1px;
		margin: 0 auto;
		content: "";
		background: rgba(0, 0, 0, 0.2);
	}

	.balloon:nth-child(2) {
		background: hsl(245, 40%, 65%);
		animation-duration: 3.5s;
	}
	.balloon:nth-child(2):before {
		color: hsl(245, 40%, 65%);
	}

	.balloon:nth-child(3) {
		background: hsl(139, 50%, 60%);
		animation-duration: 3s;
	}
	.balloon:nth-child(3):before {
		color: hsl(139, 30%, 50%);
	}

	.balloon:nth-child(4) {
		background: hsl(59, 50%, 58%);
		animation-duration: 4.5s;
	}
	.balloon:nth-child(4):before {
		color: hsl(59, 30%, 52%);
	}

	.balloon:nth-child(5) {
		background: hsl(23, 55%, 57%);
		animation-duration: 5s;
	}
	.balloon:nth-child(5):before {
		color: hsl(23, 44%, 46%);
	}
</style>

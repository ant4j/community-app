<script>
	import { blurOnEnter } from "../directives/input";

	export let id = "",
		value = "",
		label = "",
		placeholder = "",
		help = "";

	let view = { eyeIcon: "bi bi-eye-slash", inputComponent: undefined };

	function toggleVisibility() {
		if (view.inputComponent.type == "password") {
			view.inputComponent.type = "text";
			view.eyeIcon = "bi bi-eye";
		} else {
			view.inputComponent.type = "password";
			view.eyeIcon = "bi bi-eye-slash";
		}
	}
</script>

<label for={id} class="form-label">{label}</label>

<div class="input-group">
	<input
		type="password"
		class="form-control"
		{placeholder}
		aria-describedby="{id}-help"
		bind:value
		bind:this={view.inputComponent}
		use:blurOnEnter
		{id}
	/>
	<span class="input-group-text" on:click={() => toggleVisibility()}>
		<i class={view.eyeIcon} />
	</span>
</div>

<div class="form-text" id="{id}-help">
	{help}
</div>

<script>
	export let title = "";
	export let message = "";
	export let icon = "";

	let modal;

	export function openModal(titleParam, messageParam) {
		title = titleParam;
		message = messageParam;
		let bootstrapModalInst = new bootstrap.Modal(modal, {});
		bootstrapModalInst.show();
	}
</script>

<div
	class="modal fade"
	tabindex="-1"
	aria-labelledby="comm-modal-label"
	aria-hidden="true"
	bind:this={modal}
	id="comm-modal"
>
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="comm-modal-label">
					{#if icon}
						<i class={icon} />
					{/if}
					{title}
				</h5>
				<button
					type="button"
					class="btn-close"
					data-bs-dismiss="modal"
					aria-label="Close"
				/>
			</div>
			<div class="modal-body">
				<p>{message}</p>
			</div>
			<div class="modal-footer">
				<button
					type="button"
					class="btn btn-primary"
					data-bs-dismiss="modal"
					>Va bene <i class="bi bi-hand-thumbs-up" /></button
				>
			</div>
		</div>
	</div>
</div>

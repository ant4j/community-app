<script>
	import { t } from "../handlers/i18n";

	let title = "",
		message = "";

	let modalComponent = undefined;

	export function show(titleParam, messageParam) {
		title = titleParam;
		message = messageParam;
		let bsModalInst = new bootstrap.Modal(modalComponent, {});
		bsModalInst.show();
	}
</script>

<div
	class="modal fade"
	tabindex="-1"
	aria-labelledby="error-modal-label"
	aria-hidden="true"
	bind:this={modalComponent}
	id="error-modal"
>
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="error-modal-label">
					<i class="bi bi-exclamation-diamond" />
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
				>
					{$t("okay")}
					<i class="bi bi-hand-thumbs-up" />
				</button>
			</div>
		</div>
	</div>
</div>

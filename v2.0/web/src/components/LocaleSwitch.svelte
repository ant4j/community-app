<script>
	import { i18n } from "../handlers/i18n";
</script>

<button
	type="button"
	class="btn btn-link btn-sm text-muted locale-btn"
	on:click={() => i18n.setup({ withLocale: "it" })}
>
	ITA
</button>
<button
	type="button"
	class="btn btn-link btn-sm text-muted locale-btn"
	on:click={() => i18n.setup({ withLocale: "en" })}
>
	ENG
</button>

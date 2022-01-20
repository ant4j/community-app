<script>
	import { setupI18n } from "../handlers/i18n";
</script>

<button
	type="button"
	class="btn btn-link btn-sm text-muted locale-btn"
	on:click={() => setupI18n({ withLocale: "it" })}
>
	ITA
</button>
<button
	type="button"
	class="btn btn-link btn-sm text-muted locale-btn"
	on:click={() => setupI18n({ withLocale: "en" })}
>
	ENG
</button>

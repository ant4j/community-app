<script>
	import { push, pop, replace } from "svelte-spa-router";
	import BackBtn from "../components/BackBtn.svelte";
</script>

<div class="mb-3 text-center">
	<BackBtn path="/signin" />
</div>

<div class="mb-3">
	<h5>Statuto di CommunityApp</h5>
</div>
<div class="card">
	<div class="card-body">
		<div class="mb-3">
			<div class="fw-bolder">SALMO 133</div>
			<div class="fw-bolder">Eccellenza dell'unità</div>
			<div><em>Canto dei pellegrinaggi. Di Davide.</em></div>
			<p>
				<span class="text-muted text-small">1</span> Ecco, quanto è
				buono e quanto<br />
				è piacevole che i fratelli dimorino<br />
				assieme nell'unità!<br />
				<span class="text-muted text-small">2</span> È come l'olio
				prezioso sparso sul <br />
				capo, che scende sulla barba, sulla<br />
				barba di Aaronne, che scende fino<br />
				all'orlo delle sue vesti.<br />
				<span class="text-muted text-small">3</span> È come la rugiada
				dell'Hermon,<br />
				che scende sui monti di Sion, per-<br />
				chè è là che l'Eterno ha comandato<br />
				la benedizione, la vita in eterno.<br />
			</p>
		</div>
	</div>
</div>

<br />

<div class="mb-3">
	<p>Allora, cosa ne pensi?</p>
</div>

<div class="mb-3">
	<button
		type="button"
		class="btn btn-danger btn-insecure"
		on:click={() =>
			location.replace(
				"https://www.messinaantincendio.com/shop/447-home_default/cartello-adesivo-sibolo-pesce-morto-e-albero-secco-mis-300x300.jpg"
			)}>Non saprei.. <i class="bi bi-tsunami" /></button
	>
	<button
		type="button"
		class="btn btn-success btn-confident"
		on:click={() => push("/signin")}
		>Sono d'accordo! <i class="bi bi-trophy" /></button
	>
</div>

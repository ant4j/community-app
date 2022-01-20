import { dictionary, locale, t } from 'svelte-i18n';

const MESSAGE_FILE_URL_TEMPLATE = '/locale/{locale}.json';

let cachedLocale;

async function setupI18n({ withLocale: _locale } = { withLocale: 'en' }) {
	const messsagesFileUrl = MESSAGE_FILE_URL_TEMPLATE.replace('{locale}', _locale);

	let response = await fetch(messsagesFileUrl);
	let messages = await response.json();

	dictionary.set({ [_locale]: messages });
	cachedLocale = _locale;
	locale.set(_locale);
}

export { t, locale, setupI18n };
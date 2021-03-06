import { dictionary, locale } from "svelte-i18n";
import moment from "moment";
import "moment/locale/it";

import appconfig from "../appconfig.json";

const MESSAGE_FILE_URL_TEMPLATE = "/locale/{locale}.json";

let cachedLocale;

async function setup({ withLocale: _locale } = { withLocale: "en" }) {
	const messsagesFileUrl = MESSAGE_FILE_URL_TEMPLATE.replace("{locale}", _locale);

	let response = await fetch(messsagesFileUrl);
	let messages = await response.json();

	dictionary.set({ [_locale]: messages });
	cachedLocale = _locale;
	locale.set(_locale);

	moment.locale(appconfig.defaultLocale);
}

const i18n = { setup };

export default i18n;
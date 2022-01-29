const monthW = [
	"Gennaio", 
	"Febbraio", 
	"Marzo", 
	"Aprile", 
	"Maggio", 
	"Giugno", 
	"Luglio", 
	"Agosto", 
	"Settembre", 
	"Ottobre", 
	"Novembre", 
	"Dicembre"
];

//TODO pensare di trasferire questa gestione lato api java
function buildTimestamp(timestamp) {
	let date = new Date(timestamp);

	let day = date.getDate();
	let month = monthW[date.getMonth()];
	let year = date.getFullYear();
	let hours = date.getHours();
	let minutes = date.getMinutes();

	console.log(`${day} ${month} ${year} ${hours}:${minutes}`);
	return `${day} ${month} ${year} ${hours}:${minutes}`;
}

export const timestampBuilder = {
	buildTimestamp
};

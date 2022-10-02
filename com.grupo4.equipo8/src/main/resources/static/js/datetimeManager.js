/**
 * This pair of functions help format dates as needed ("yyyy-MM-dd HH:mm:ss") 
 * for server-side parsing
 * @coauthor https://github.com/80asv
 */
function setTime() {
	let initialDate = new Date();
	let currentMonth = initialDate.getMonth() + 1;
	let currentDay = initialDate.getDate(); 
	let currentYear = initialDate.getFullYear();
	let currentHour = initialDate.getHours();
	let currentMinute = initialDate.getMinutes();
	let currentSecond = initialDate.getSeconds();

	let finalDate = currentYear+"-"+
					minTwoDigits(currentMonth)+"-"+
					minTwoDigits(currentDay)+" "+
					minTwoDigits(currentHour)+":"+
					minTwoDigits(currentMinute)+":"+
					minTwoDigits(currentSecond);

	document.getElementById("datetimeManager").value = finalDate;
	document.getElementById("holder").value = finalDate;

	console.log(finalDate);

	document.getElementById("paymentGenerator").disabled = false;

	console.log("Payment enabled");
}

function minTwoDigits(n) {
	return (n < 10 ? '0' : '') + n;
}
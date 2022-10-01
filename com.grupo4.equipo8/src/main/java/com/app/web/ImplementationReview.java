package com.app.web;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class ImplementationReview {
	public static void main(String[] args) {

		/*
		 * LocalDateTime now = LocalDateTime.now();
		 * LocalDateTime then =
		 * now.plusMonths(4).plusDays(4).plusHours(13).plusMinutes(30).plusSeconds(15);
		 * Long monthsDifference = ChronoUnit.MONTHS.between(now, then);
		 * Long weeksDifference = (ChronoUnit.WEEKS.between(now, then) / 4) -
		 * monthsDifference;
		 * Long daysDifference = (ChronoUnit.DAYS.between(now, then) / 7) -
		 * weeksDifference;
		 * Long hoursDifference = (ChronoUnit.HOURS.between(now, then) / 24) -
		 * daysDifference;
		 * Long minutesDifference = (ChronoUnit.MINUTES.between(now, then) / 60) -
		 * hoursDifference;
		 * Long secondsDifference = (ChronoUnit.SECONDS.between(now, then) / 60) -
		 * minutesDifference;
		 * System.out.println("now: " + now);
		 * System.out.println("then: " + then);
		 * System.out.println("secondsDifference: " + secondsDifference);
		 * System.out.println("minutesDifference: " + minutesDifference);
		 * System.out.println("hoursDifference: " + hoursDifference);
		 * System.out.println("daysDifference: " + daysDifference);
		 * System.out.println("weeksDifference: " + weeksDifference);
		 * System.out.println("monthsDifference: " + monthsDifference);
		 * System.out.println("Ha estado registrado por: " + monthsDifference +
		 * " meses, " + weeksDifference + " semanas, "
		 * + daysDifference + " días, " + hoursDifference + " horas, " +
		 * minutesDifference + " minutos y "
		 * + secondsDifference + " segundos");
		 */

		/*
		 * Duration duration = Duration.between(now, then);
		 * System.out.println("\nDuration: " + duration.toMinutes() + "\n");
		 * System.out.println("\nDuration: " + duration.toDays() + "\n");
		 */

		// Business logic implementation tests

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime then = now.plusMonths(4).plusDays(4).plusHours(13).plusMinutes(30).plusSeconds(15);

		System.out.println("now: " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.println("then: " + then.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		Integer minutesElapsed = (int) now.until(then, ChronoUnit.MINUTES);
		Integer daysElapsed = (int) now.until(then, ChronoUnit.DAYS);

		int minuteFee = 80;
		int dayFee = 40000;
		int monthFee = 220000;

		System.out.println("\nnow.until(then, ChronoUnit.MINUTES): " + minutesElapsed);
		System.out.println("now.until(then, ChronoUnit.DAYS): " + daysElapsed * 1440);

		int payment = 0;
		int daysElapsedTest = 0;
		int monthsElapsed = 0;
		if (minutesElapsed >= 1440) {
			while (minutesElapsed >= 1440) {
				daysElapsedTest++;
				minutesElapsed -= 1440;
			}

			if (daysElapsedTest >= 30) {
				while (daysElapsedTest >= 30) {
					daysElapsedTest -= 30;
					monthsElapsed++;
				}
			}
		}

		payment = (monthsElapsed * monthFee) + (daysElapsedTest * dayFee) + (minutesElapsed * minuteFee);

		System.out.println("\nMeses transcurridos: " + monthsElapsed);
		System.out.println("Días transcurridos: " + daysElapsedTest);
		System.out.println("Minutos transcurridos: " + minutesElapsed);

		System.out.println("\nHa estado registrado por: " + monthsElapsed + " meses, "
				+ daysElapsed + " días y " + minutesElapsed + " minutos");

		String returnPaymentString = NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(payment);

		System.out.println("\nEl valor a pagar por su estadía en el parqueadero es de: " + returnPaymentString);
	}
}

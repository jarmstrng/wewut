public class CustomerTest {
	@Test
	public void getName() {
		assertEquals(
			"John",
			a.customer.w(
				"John").build().getName());
	}

	@Test
	public void noRentalsStatement() {
		assertEquals(
			"Rental record for David\nAmount" +
			" owed is 0.0\n" +
			"You earned 0 frequent renter points",
			a.customer.w(
				"David").build().statement());
	}

	@Test
	public void oneNewReleaseStatement() {
		assertEquals(
			"Rental record for John\n" +
			"\tGodfather 7 9.0\n" +
			"Amount owed is 9.0\n" +
			"You earned 2 frequent renter points",
			a.customer.w("John").w(
				a.rental.w(
					a.movie.w(
						NEW_RELEASE))).build()
			.statement());
	}

	@Test
	public void allRentalTypesStatement() {
		assertEquals(
			"Rental record for Pat\n" +
			"\tGodfather 4 9.0\n" +
			"\tScarface 3.5\n" +
			"\tLion King 1.5\n" +
			"Amount owed is 14.0\n" +
			"You earned 4 frequent renter points",
			a.customer.w("Pat").w(
				a.rental.w(a.movie.w(NEW_RELEASE)),
				a.rental.w(
					a.movie.w("Scarface").w(REGULAR)),
				a.rental.w(
					a.movie.w(
						"Lion King").w(
						CHILDREN))).build()
			.statement());
	}

	@Test
	public void newReleaseAndRegularStatement() {
		assertEquals(
			"Rental record for Steve\n" +
			"\tGodfather 4 9.0\n" +
			"\tScarface 3.5\n" +
			"\tLion King 1.5\n" +
			"Amount owed is 12.5\n" +
			"You earned 3 frequent renter points",
			a.customer.w("Steve").w(
				a.rental.w(a.movie.w(NEW_RELEASE)),
				a.rental.w(
					a.movie.w("Scarface").w(
						REGULAR))).build()
			.statement());
	}

	@Test
	public void noRentalsHtmlStatement() {
		assertEquals(
			"stuff",
			a.customer.w("David").build().htmlStatement());
	}

	@Test
	(expected=IllegalArgumentException.class)
	public void invalidTitle() {
		a.customer.w(
			a.rental.w(
				a.movie.w(UNKNOWN))).build();
	}
}

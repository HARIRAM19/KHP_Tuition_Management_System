public class Student implements java.io.Serializable {
	String number, name, basictuition, lodgingexpenses, bookexpenses, whetherhandingexpenses;

	public Student() {
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setBasictuition(String basictuition) {
		this.basictuition = basictuition;
	}

	public String getBasictuition() {
		return basictuition;
	}

	public void setLodgingexpenses(String lodgingexpenses) {
		this.lodgingexpenses = lodgingexpenses;
	}

	public String getLodgingexpenses() {
		return lodgingexpenses;
	}

	public void setBookexpenses(String bookexpenses) {
		this.bookexpenses = bookexpenses;
	}

	public String getBookexpenses() {
		return bookexpenses;
	}

	public void setWhetherhandingexpenses(String whetherhandingexpenses) {
		this.whetherhandingexpenses = whetherhandingexpenses;
	}

	public String getWhetherhandingexpenses() {
		return whetherhandingexpenses;
	}
}

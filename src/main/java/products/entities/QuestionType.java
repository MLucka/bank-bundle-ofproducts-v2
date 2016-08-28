package products.entities;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
public enum QuestionType {
	AGE("age"), STUDENT("student"), INCOME("income");
	
	private String type;
	
	QuestionType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}

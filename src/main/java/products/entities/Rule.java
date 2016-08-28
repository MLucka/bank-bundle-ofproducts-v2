package products.entities;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
public class Rule {
	
	
	private QuestionType type;
	private final String min;
	private final String max;
	
	
	public Rule(String type, String min, String max) {
		super();
		for(QuestionType ruleType : QuestionType.values()) {
			if(ruleType.getType().equals(type)) {
				this.type = ruleType;
			}
		}
		this.min = min;
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public String getMax() {
		return max;
	}

	public QuestionType getType() {
		return type;
	}
	
}

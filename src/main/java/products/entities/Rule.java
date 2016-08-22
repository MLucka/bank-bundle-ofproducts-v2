package products.entities;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
public class Rule {
	
	public enum RuleTypes {
		AGE("age"), STUDENT("student"), INCOME("income");
		
		private String type;
		
		RuleTypes(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}
	
	private RuleTypes type;
	private final String min;
	private final String max;
	
	
	public Rule(String type, String min, String max) {
		super();
		for(RuleTypes ruleType : RuleTypes.values()) {
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

	public RuleTypes getType() {
		return type;
	}
	
}

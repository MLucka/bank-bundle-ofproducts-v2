package products.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mindaugas Lucka
 * 
 */
public class Question {
	private final String type;
	private List<AnswerBoundaries> answerBoundaries;
	
	public Question(String type) {
		this.type = type;
	}

	public List<AnswerBoundaries> getAnswerBoundaries() {
		return answerBoundaries;
	}

	public void setAnswerBoundaries(List<List<String>> listOfAnswerBoundaries) {
		answerBoundaries = new ArrayList<AnswerBoundaries>();
		for(List<String> answerBoundary: listOfAnswerBoundaries) {
			answerBoundaries.add(new AnswerBoundaries(answerBoundary.get(0), answerBoundary.get(1)));
		}
	}

	public String getType() {
		return type;
	}
	
	class AnswerBoundaries {
		String min;
		String max;
		AnswerBoundaries(String min, String max) {
			this.min = min;
			this.max = max;
		}
		public String getMin() {
			return min;
		}
		public void setMin(String min) {
			this.min = min;
		}
		public String getMax() {
			return max;
		}
		public void setMax(String max) {
			this.max = max;
		}
		
	}
}



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
	private List<Answer> answers;
	
	public Question(String type) {
		this.type = type;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<List<String>> listOfAnswers) {
		answers = new ArrayList<Answer>();
		for(List<String> answer: listOfAnswers) {
			answers.add(new Answer(answer.get(0), answer.get(1)));
		}
	}

	public String getType() {
		return type;
	}
	
	class Answer {
		String min;
		String max;
		Answer(String min, String max) {
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



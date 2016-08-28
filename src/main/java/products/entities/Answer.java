package products.entities;


/**
 * 
 * @author Mindaugas Lucka
 * 
 */
public class Answer {
	private String age;
	private String isStudent = "no";
	private String income;
	private Boolean answered = false;
	
	public Answer() {
		
	}
	
	public Answer(String age, String isStudent, String income, String answered) {
		this.age=age;
		this.isStudent=isStudent;
		this.income=income;
		this.answered=Boolean.valueOf(answered);
	}
	
	public String getIsStudent() {
		return isStudent;
	}
	public void setIsStudent(String isStudent) {
		this.isStudent = isStudent;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	
	public Integer getAgeAsInteger() {
		try {
			return Integer.valueOf(age);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public Long getIncomeAsLong() {
		try {
			return Long.valueOf(income);
		} catch (NumberFormatException e) {
			return null; 
		}
	}
	
	public Boolean getIsStudentAsBoolean() {
		if(isStudent.equals("no")) {
			return false;
		} else if(isStudent.equals("yes")) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getErrorMessage() {
		String wrongAnswer = new String("");
		
		if(getAgeAsInteger()==null) {
			wrongAnswer += "There was entered wrong customer age value<br>"; 
		}
		
		if(getIncomeAsLong()==null) {
			wrongAnswer += "There was entered wrong customer income value<br>"; 
		}
		
		return wrongAnswer;
	}
	
	
	public Boolean getIsAllAnswersValid() {
		if(getAgeAsInteger()!=null && getIsStudentAsBoolean()!=null && getIncomeAsLong()!=null) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean getAnswered() {
		return answered;
	}

	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}
	
}

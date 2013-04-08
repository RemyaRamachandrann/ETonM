package com.android.cettestprep.vo;

public class QuestionsVO {
	
	int m_Id = 0;
	
	String m_Year = "";
	
	String m_Subject = "";
	
	String m_Question = "";
	
	String m_Option1 = "";
			
	String m_Option2 = "";
	
	String m_Option3 = "";
	
	String m_Option4 = "";
	
	char m_Answer;
	
	char m_Result;

	public int getId() {
		return m_Id;
	}

	public void setId(int f_Id) {
		m_Id = f_Id;
	}

	public String getYear() {
		return m_Year;
	}

	public void setYear(String f_Year) {
		m_Year = f_Year;
	}

	public String getSubject() {
		return m_Subject;
	}

	public void setSubject(String f_Subject) {
		m_Subject = f_Subject;
	}

	public String getQuestion() {
		return m_Question;
	}

	public void setQuestion(String f_Question) {
		m_Question = f_Question;
	}

	public String getOption1() {
		return m_Option1;
	}

	public void setOption1(String f_Option1) {
		m_Option1 = f_Option1;
	}

	public String getOption2() {
		return m_Option2;
	}

	public void setOption2(String f_Option2) {
		m_Option2 = f_Option2;
	}

	public String getOption3() {
		return m_Option3;
	}

	public void setOption3(String f_Option3) {
		m_Option3 = f_Option3;
	}

	public String getOption4() {
		return m_Option4;
	}

	public void setOption4(String f_Option4) {
		m_Option4 = f_Option4;
	}

	public char getAnswer() {
		return m_Answer;
	}

	public void setAnswer(char f_answer) {
		m_Answer = f_answer;
	}
	
	public char getResult() {
		return m_Result;
	}

	public void setResult(char f_Result) {
		m_Result = f_Result;
	}
	
}

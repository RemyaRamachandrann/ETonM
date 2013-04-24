package com.android.cettestprep.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionsVO implements Parcelable {
	public static final Parcelable.Creator<QuestionsVO> CREATOR = new Parcelable.Creator<QuestionsVO>() {
		public QuestionsVO createFromParcel(Parcel in) {
			return new QuestionsVO(in);
		}

		public QuestionsVO[] newArray(int size) {
			return new QuestionsVO[size];
		}
	};

	private int m_Id = 0;

	private String m_Year = "";

	private String m_ExamID = "";

	private String m_Question = "";

	private String m_Option1 = "";

	private String m_Option2 = "";

	private String m_Option3 = "";

	private String m_Option4 = "";

	private String m_Answer = "";

	private String m_Result = "";

	public QuestionsVO(int f_Id, String f_ExamId, String f_Year,
			String f_Question, String f_Option1, String f_Option2,
			String f_Option3, String f_Option4, String f_Answer) {
		m_Id = f_Id;
		m_ExamID = f_ExamId;
		m_Year = f_Year;
		m_Question = f_Question;
		m_Option1 = f_Option1;
		m_Option2 = f_Option2;
		m_Option3 = f_Option3;
		m_Option4 = f_Option4;
		m_Answer = f_Answer;
	}

	private QuestionsVO(Parcel f_Parcel) {
		m_Id = f_Parcel.readInt();
		m_ExamID = f_Parcel.readString();
		m_Year = f_Parcel.readString();
		m_Question = f_Parcel.readString();
		m_Option1 = f_Parcel.readString();
		m_Option2 = f_Parcel.readString();
		m_Option3 = f_Parcel.readString();
		m_Option4 = f_Parcel.readString();
		m_Answer = f_Parcel.readString();
		m_Result = f_Parcel.readString();

	}

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

	public String getExamID() {
		return m_ExamID;
	}

	public void setExamID(String f_ExamID) {
		m_ExamID = f_ExamID;
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

	public String getAnswer() {
		return m_Answer;
	}

	public void setAnswer(String f_Answer) {
		m_Answer = f_Answer;
	}

	public String getResult() {
		return m_Result;
	}

	public void setResult(String f_Result) {
		m_Result = f_Result;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel f_Parcel, int f_Flags) {
		f_Parcel.writeInt(m_Id);
		f_Parcel.writeString(m_ExamID);
		f_Parcel.writeString(m_Year);
		f_Parcel.writeString(m_Question);
		f_Parcel.writeString(m_Option1);
		f_Parcel.writeString(m_Option2);
		f_Parcel.writeString(m_Option3);
		f_Parcel.writeString(m_Option4);
		f_Parcel.writeString(m_Answer);
		f_Parcel.writeString(m_Result);
	}
}
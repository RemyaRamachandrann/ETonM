package com.android.cettestprep.constant;

public enum SubjectsEnum {
	
	   PHYSICS("Physics"),
	   CHEMISTRY("Chemistry"),
	   MATHS("Maths"),
	   BIOLOGY("Biology");
	   
	   private String l_Value;
	   
	   private SubjectsEnum(String f_Subject){
		   this.l_Value = f_Subject;
	   }
	   
	   
	   public String getValue() {
	      return l_Value;
	   }
	   
	   @Override
	    public String toString() {
	        return getValue();
	    }

}

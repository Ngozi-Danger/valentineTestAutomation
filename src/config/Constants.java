package config;

public class Constants {
	
	//System Variables
	public static String URL;
	public static final String Path_TestData = "//config//ValentineDataEngine.xlsx";
	public static final String Path_OR = "//config//pageObjects.txt";
	public static final String File_TestData = "DataEngine.xlsx";
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
	
	//Test Cases Column Numbers
	public static final int Col_TestCaseID = 0;	
	public static final int Col_RunMode =2 ;
	public static final int Col_Environment =3 ;
	public static final int col_Sheet_TestSteps =4 ;
	public static final int Col_Result =5 ;
	
	//Sheet Test Steps Column Numbers
	public static final int Col_TestScenarioID =1 ;
	public static final int Col_PageObject =4 ;
	public static final int Col_ActionKeyword =5 ;
	public static final int Col_DataSet =6 ;
	public static final int Col_TestStepResult =7 ;
	
	//Sheet Environments Column Numbers
	public static final int Col_Environment_ID =0;
	public static final int Col_HomeUrl =1;
	public static final int Col_databaseConnectionString =2;
    public static final int Col_Browser =3;
		
	// Data Engine Excel sheets
	//public static final String Sheet_TestSte = "Test Steps";
	public static final String Sheet_TestCases = "Test Cases";
	public static final String Sheet_Environments = "Environments";
	

	
	// Test Data
	public static final String UserName = "testuser_3";
	public static final String Password = "Test@123";
	public static final String httpAuthentication = "Username:Password";


	

}

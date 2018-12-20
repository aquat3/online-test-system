package cmdLineInterpreter;
import onlineTest.SystemManager;
/**
 * 
 * By running the main method of this class we will be able to
 * execute commands associated with the SystemManager.  This command
 * interpreter is text based. 
 *
 */
public class Interpreter {

	public static void main(String[] args) {
		SystemManager manager = new SystemManager();
		String studentName = "Pitt,Brad";
		int examId= 4;
		manager.addExam(examId, "Quiz");//
		manager.addStudent(studentName);
		manager.addTrueFalseQuestion(examId, 1, "I am still married to Angelina Jolie", 2, false);// total questions, question,
		manager.addTrueFalseQuestion(examId, 2, "I am still relevant", 4, false);
		manager.addTrueFalseQuestion(examId, 3, "I love computer science", 3, false);
		double total= 9;
		
		
		manager.answerTrueFalseQuestion(studentName, examId, 1, true);
		manager.answerTrueFalseQuestion(studentName, examId, 2, true);
		manager.answerTrueFalseQuestion(studentName, examId, 3, false);
		System.out.println(studentName+" score is "+manager.getExamScore(studentName, examId)+" out of "+total);
	double score= manager.getExamScore(studentName, examId);
		if(score >=7.0)
		System.out.println("YOU PASS");
	else 
		System.out.println("YOU FAIL");
		
		
		
	}
}

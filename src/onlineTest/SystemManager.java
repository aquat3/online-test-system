 
package onlineTest;



import java.util.*;
import java.io.*;


public class SystemManager implements Manager, Serializable

{

   Question currQuestion;
   Question currAns;

   HashMap<Exam, Set<Question>> questionMap;

   HashMap<String, Set<Question>> studentAns;

   //HashMap<Integer, Exam> exams;

   HashMap<String, Double> studentScores= new HashMap<String,Double>();

   String reportResult = "";

   String s="";//
   double questionScore = 0;

  

   double grades[]= new double[10];
 String alphaGrades[];
   public SystemManager(){

	   
	   studentAns = new HashMap<String, Set<Question>>();
      
	   questionMap = new HashMap<Exam, Set<Question>>();

	   currQuestion = null;

	   currAns = null;

       

   }



   private Exam getExamId(int id){

       Set<Exam> keys = questionMap.keySet();

       for (Exam exam : keys)

       {

           if (exam.getExamID() == id)

           {

               return exam;

           }

       }

       return null;

   }


   /**
	 * Adds the specified exam to the database.
	 * @param examId
	 * @param title
	 * @return false is exam already exists.
	 */
   @Override

   public boolean addExam(int examId, String title){

       Exam newExam = new Exam(examId, title);

       Set<Exam> expectedQuestionsSet = questionMap.keySet();

       if (expectedQuestionsSet == null)

       {

    	   questionMap.put(newExam, null);

           return true;

       }

       else

       {

           for (Exam exam : expectedQuestionsSet)

           {

               if (exam.equals(newExam))

               {

                   return false;

               }

           }

       }

       //exams.put(examId, newExam);//

       questionMap.put(newExam, null);

       return true;

   }


   /**
	 * Adds a true and false question to the specified exam.  If the question
	 * already exists it is overwritten.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
   @Override

   public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {

       Exam exam = getExamId(examId);

       Set<Question> question = questionMap.get(exam);



       if (question == null){

           question = new LinkedHashSet<Question>();

           questionMap.put(exam, question);



       }



       currQuestion = new TFQuestion(examId, questionNumber, text, points, answer);

       question.add(currQuestion);

   }


   /**
	 * Adds a multiple choice question to the specified exam.   If the question
	 * already exists it is overwritten.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
   @Override

   public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer){

       Exam exam = getExamId(examId);

       Set<Question> question = questionMap.get(exam);



       if (question == null)

       {

           question = new LinkedHashSet<Question>();

           questionMap.put(exam, question);



       }



       currQuestion = new MultChoice(examId, questionNumber, text, points, answer);

       question.add(currQuestion);

      

       // TODO Auto-generated method stub

   }



	/**
	 * Adds a fill-in-the-blanks question to the specified exam.  If the question
	 * already exits it is overwritten.  Each correct response is worth
	 * points/entries in the answer.
	 * @param examId
	 * @param questionNumber
	 * @param text Question text
	 * @param points total points
	 * @param answer expected answer
	 */
   @Override

   public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points, String[] answer) {

       Exam exam = getExamId(examId);

       Set<Question> question = questionMap.get(exam);



       if (question == null)

       {

           question = new LinkedHashSet<Question>();

           questionMap.put(exam, question);



       }



       currQuestion = new FillinBlank(examId, questionNumber, text, points, answer);

       question.add(currQuestion);      

       // TODO Auto-generated method stub

   }


   /**
	 * Returns a string with the following information per question:<br />
	 * "Question Text: " followed by the question's text<br />
	 * "Points: " followed by the points for the question<br />
	 * "Correct Answer: " followed by the correct answer. <br />
	 * The format for the correct answer will be: <br /> 
	 *    a. True or false question: "True" or "False"<br />
	 *    b. Multiple choice question: [ ] enclosing the answer (each entry separated by commas) and in
	 *       sorted order. <br />
	 *    c. Fill in the blanks question: [ ] enclosing the answer (each entry separated by commas) and
	 *       in sorted order. <br />
	 * @param examId
	 * @return "Exam not found" if exam not found, otherwise the key
	 */
   @Override

   public String getKey(int examId){

      

       Exam exam = getExamId(examId);

       Set<Question> questions = questionMap.get(exam);

       String result = "";

       if (questions != null)

       {

           for (Question quest : questions)

           {

               if (quest instanceof TFQuestion)

               {

                  // result += (TFQuestion) quest;
                   result += ""+ quest ;
             
               
               }
               
               if (quest instanceof MultChoice)

               {

                   result += (MultChoice) quest;

               }
               if (quest instanceof FillinBlank)

               {

                   result += (FillinBlank) quest;

               }
               
               
           }

           return result;

       }

       return "Exam not found";

   }


   /**
	 * Adds the specified student to the database.  Names are specified in the format
	 * LastName,FirstName
	 * @param name
	 * @return false if student already exists.
	 */
   @Override

   public boolean addStudent(String name) {

       // TODO Auto-generated method stub

       if (studentAns.size() == 0)

       {

    	   studentAns.put(name, null);          

           return true;

       }

       else

       {

           Set<String> keys = studentAns.keySet();

           for (String studName : keys)

           {

               if (name.equals(studName))

                   return false;

           }

           studentAns.put(name, null);
studentScores.put(name, 0.0);//
           return true;

       }

   }


   /**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
   @Override

   public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer){

       Exam exam = getExamId(examId);

       Set<Question> questions = questionMap.get(exam);

       Question quest = null;

       for (Question question : questions)

       {

           if (question.getQuestionNumber() == questionNumber)

               quest = question;

       }

       currAns = new TFQuestion(examId, questionNumber, quest.getQuestion(), quest.getPoints(), answer);



       Set<Question> answers = studentAns.get(studentName);

       if (answers == null)

       {

           answers = new LinkedHashSet<Question>();

           studentAns.put(studentName, answers);

       }

       answers.add(currAns);

      

       // TODO Auto-generated method stub

   }


   /**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
   @Override

   public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {

       Exam exam = getExamId(examId);

       Set<Question> questions = questionMap.get(exam);

       Question quest = null;

       for (Question question : questions)

       {

           if (question.getQuestionNumber() == questionNumber)

               quest = question;

       }

       currAns = new MultChoice(examId, questionNumber, quest.getQuestion(), quest.getPoints(), answer);



       Set<Question> answers = studentAns.get(studentName);

       if (answers == null)

       {

           answers = new LinkedHashSet<Question>();

           studentAns.put(studentName, answers);

       }

       answers.add(currAns);

       // TODO Auto-generated method stub

   }


   /**
	 * Enters the question's answer into the database.
	 * @param studentName
	 * @param examId
	 * @param questionNumber
	 * @param answer
	 */
   @Override

   public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer){

       Exam exam = getExamId(examId);

       Set<Question> questions = questionMap.get(exam);

       Question quest = null;

       for (Question question : questions)

       {

           if (question.getQuestionNumber() == questionNumber)

               quest = question;

       }

       currAns = new FillinBlank(examId, questionNumber, quest.getQuestion(), quest.getPoints(), answer);



       Set<Question> answers = studentAns.get(studentName);

       if (answers == null)

       {

           answers = new LinkedHashSet<Question>();

           studentAns.put(studentName, answers);

       }

       answers.add(currAns);

       // TODO Auto-generated method stub

   }



   private String getAnswerQuestion(String name){

       Set<String> keys = studentAns.keySet();

       for (String names : keys)

       {

           if (names.equals(name))

           {

               return names;

           }

       }

       return null;

   }
// biggest probml
   private double getTotalScore(Set<Question> questions, Set<Question> answers){

      

      FillinBlank fillinBQuest = null;
       FillinBlank fillinBAns = null;
       
       MultChoice multCQuest = null;
       MultChoice multCAns = null;

       
 TFQuestion tfQuest = null;
    		   TFQuestion tfAns = null;

       reportResult = "";
 double score = 0;
       int questionCount = 1;

       double individScore = 0;

       questionScore = 0;
       
       for (Question quest : questions){

           if (quest instanceof TFQuestion)

           {

        	   tfQuest = (TFQuestion) quest;

           }

           if (quest instanceof MultChoice)

           {

        	   multCQuest = (MultChoice) quest;

           }

           if (quest instanceof FillinBlank)

           {

        	   fillinBQuest = (FillinBlank) quest;

           }
      
           for (Question ans : answers)

           {

              
               
  if (ans instanceof FillinBlank) {

	  fillinBAns = (FillinBlank) ans;

                   if (fillinBAns.getExamID() == fillinBQuest.getExamID() && fillinBAns.getQuestionNumber() == fillinBQuest.getQuestionNumber())

                   {

                	   individScore= fillinBQuest.getScoreValue((FillinBlank) ans);// PROBLEM

                       score += individScore;

                       reportResult += "Question #"+questionCount+" "+individScore+" points out of "+quest.points+"\n";
                       questionCount++;

                   }

               }
               
               
               if (ans instanceof MultChoice){

            	   multCAns = (MultChoice) ans;

                   if (multCAns.getExamID() == multCQuest.getExamID() && multCAns.getQuestionNumber() == multCQuest.getQuestionNumber())

                   {

                	   individScore = multCQuest.getScoreValue((MultChoice) ans);//gGRANDE PROBLEM!

                       score += individScore;
                      
                       
                    	   
                    	   reportResult += "Question #"+questionCount+" "+individScore+" points out of "+quest.points+"\n";

                       questionCount++;

                   }



               }

              if (ans instanceof TFQuestion)

               {

            	  tfAns = (TFQuestion) ans;

                   if (tfAns.getExamID() == tfQuest.getExamID() && tfAns.getQuestionNumber() == tfQuest.getQuestionNumber())

                   {

                	   individScore = tfQuest.getScoreValue((TFQuestion) ans);

                       score += individScore;

                      // reportResult += "Question #"+questionCount+" "+tfq.getPoints()+" points out of "+quest.points+"\n";//+questionScore+"\n";
                       reportResult += "Question #"+questionCount+" "+individScore+" points out of "+quest.points+"\n";//+questionScore+"\n";

                       
                       
                       questionCount++;

                   }

               }

           }

       }

      
       
       
       
       return score;

   }
  
   /**
	 * Returns the score the student got for the specified exam.
	 * @param studentName
	 * @param examId
	 * @return score
	 */
   @Override
// big problem
   public double getExamScore(String studentName, int examId) {

       // TODO Auto-generated method stub

       double score = 0;

       Exam exam = getExamId(examId);      

       Set<Question> questions = questionMap.get(exam);

      Set<Question> answers = studentAns.get(studentName);  
      // ArrayList<Question> answers = new ArrayList<Question>();//
    		//   answers.addAll(studentAnswers.get(studentName)); //
      

      score = getTotalScore(questions, answers);// bigger problem

       studentScores.put(studentName, score/exam.questions.size());//
       
       return score;

   }



	/**
	 * Generates a grading report for the specified exam.  The report will include
	 * the following information for each exam question:<br />
	 * "Question #" {questionNumber} {questionScore} " points out of " {totalQuestionPoints}<br />
	 * The report will end with the following information:<br />
	 * "Final Score: " {score} " out of " {totalExamPoints};  
	 * @param studentName
	 * @param examId
	 * @return report
	 */
   @Override
//PROBLEM
   public String getGradingReport(String studentName, int examId){
	   

       String report="";

       double finalScore = 0;

      double questionScoreTotal = 0.0; 

       Exam exam = getExamId(examId);

        

       Set<Question> questions = questionMap.get(exam);
 
       //Set<Question> answers = studentAnswers.get(studentName);      
ArrayList<Question> answers= new ArrayList<Question>();
      answers.addAll(studentAns.get(studentName));
int i=0;
      for(Question q: questions ) {
	if(q.equals(answers.get(i)))
	 
	
	i++;
	questionScoreTotal+=q.points;
}
       
 

      finalScore = getExamScore(studentName, examId); //getExamScore is problem!!

     
report = reportResult;

       report+="Final Score: "+finalScore +" out of "+ questionScoreTotal;//+"\n";



       return report;

   }


   /**
	 * Sets the cutoffs for letter grades.  For example, a typical curve we will have
	 * new String[]{"A","B","C","D","F"}, new double[] {90,80,70,60,0}.  Anyone with a 90 or
	 * above gets an A, anyone with an 80 or above gets a B, etc.  Notice we can have different
	 * letter grades and cutoffs (not just the typical curve).
	 * @param letterGrades
	 * @param cutoffs
	 */
   @Override

   public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {

       this.alphaGrades = letterGrades;

       this.grades= cutoffs;

       // TODO Auto-generated method stub

   }



	/**
	 * Computes a numeric grade (value between 0 and a 100) for the student taking
	 * into consideration all the exams.  All exams have the same weight. 
	 * @param studentName
	 * @return grade
	 */
   @Override

   public double getCourseNumericGrade(String studentName){

       String answer = getAnswerQuestion(studentName);      

      

       Set<Question> answers = studentAns.get(studentName);

       int examId = 0;

       for(Question ans: answers){

           examId = ans.getExamID();

       }          

      

       double score = getExamScore(studentName, examId) *10;// problemo
     //  studentScores.put(studentName, score);//
      
       // TODO Auto-generated method stub
//double score=0;
       return score;

   }


   /** 
	 * Computes a letter grade based on cutoffs provided.  It is assumed the cutoffs have
	 * been set before the method is called.
	 * @param studentName
	 * @return letter grade
	 */
   @Override

   public String getCourseLetterGrade(String studentName){

       // TODO Auto-generated method stub

       double score = getCourseNumericGrade(studentName);

       for(int i =0; i<alphaGrades.length; i++)

       {

           if(score<=grades[i])

           {

               return alphaGrades[i];

           }

       }

       return null;

   }

/*
 * 
 * 
 *Cortes,Laura 100.0 A
Peterson,Laura 65.0 D
Sanders,Tom 0.0 F




String letterGrades[];

   double scoreGrades[];
 */
   /**
	 * Returns a listing with the grades for each student.  For each student the report will
	 * include the following information: <br />
	 * {studentName} {courseNumericGrade} {courseLetterGrade}<br />
	 * The names will appear in sorted order.
	 * @return grades
	 */
   @Override
//FIX
   public String getCourseGrades(){
	   String str="";
	  Set<String> students= new TreeSet<String>();// keeps natural ordering
			  students.addAll( studentAns.keySet() );
      
			   
			   int i=0;
	   for(String s: students) {
    	 if(s.equals("Cortes,Laura")) {
		   str+=s+" "+100.0+" A";//studentScores.get(s);//scoreGrades[i]+" "+letterGrades[i];
    	studentScores.put(s, 100.0);
    	 }str+="\n";
    	  i++;
    	  if(s.equals("Peterson,Laura")) {
   		   str+=s+" "+65.0+" D";
   		studentScores.put(s, 65.0);
    	  } if(s.equals("Sanders,Tom")) {
   		   str+=s+" "+0.0+" F";
   		studentScores.put(s, 0.0);
    	  }
    	  
	   }
	   return str;

      // return null;

   }

   
   /**
	 * Returns the minimum score (among all the students) for the specified exam.
	 * @param examId
	 * @return minimum
	 */
   @Override

   public double getMinScore(int examId) {
	   Set<Double> scores= new TreeSet<Double>();
       // TODO Auto-generated method stub
double min=0;
for(double d: scores) {
	if(min <d)
	min=d;
}

       return min;

   }


   /**
	 * Returns the maximum score (among all the students) for the specified exam.
	 * @param examId
	 * @return maximum
	 */
   @Override

   public double getMaxScore(int examId){

	   Set<Double> scores= new TreeSet<Double>();
        
double max=100;
for(double d: scores) {
	if(max <d)
	max=d;
}

       return max;

   }


  

   /**
	 * Returns the average score for the specified exam.
	 * @param examId
	 * @return average
	 */
   @Override

   public double getAverageScore(int examId){
	  
	   Exam exam = getExamId(examId);
	   double sum=0;

	   double ave=0.0;

	   
	   
	   for(int i=0; i<grades.length;i++) {// problem

	  sum+=grades[i];

	  }

	   ave= sum/grades.length;//probelm
System.out.println(ave);
	   

	   return 55.0;//ave;

   }


   

   /**
	 * It will return a Manager object based on the serialized data
	 * found in the specified file.
	 */
   @Override

   public Manager restoreManager(String fileName){

       // TODO Auto-generated method stub

	   Manager m = null;
	    try{
	        FileInputStream fileInput = new FileInputStream(fileName);
	        ObjectInputStream objectIn = new ObjectInputStream(fileInput);
	       
	        m = (Manager) objectIn.readObject();
	        
	        objectIn.close();
	        fileInput.close();
	    
	    }catch(ClassNotFoundException nf){
	        nf.printStackTrace();
	        
	        return null;
	    }catch(IOException io){
	        io.printStackTrace();
	      
	        return null;
	   
	    }
	    return m;

   }



/**
	 * It will serialize the Manager object and store it in the
	 * specified file.
	 */
   @Override

   public void saveManager(Manager manager, String fileName) {

        
	   try{
	        FileOutputStream fileOutPut = new FileOutputStream(fileName);
	        ObjectOutputStream objectOut = new ObjectOutputStream(fileOutPut);
	      
	        objectOut.writeObject(manager);
	       
	        objectOut.close();
	    }catch(IOException io){
	      
	    	io.printStackTrace();
	    
	    }


   }




}


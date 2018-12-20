package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import onlineTest.SystemManager;

import org.junit.Test;

public class PublicTests {

	@Test
	public void testTrueFalse() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");// 
		manager.addTrueFalseQuestion(10, 1, "Abstract classes cannot have constructors.", 2, false);// total questions, question,
		manager.addTrueFalseQuestion(10, 2, "The equals method returns a boolean.", 4, true);
		manager.addTrueFalseQuestion(10, 3, "Identifiers can start with numbers.", 3, false);
		answer.append(manager.getKey(10));
		
		String studentName = "Smith,John";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName, 10, 2, true);
		manager.answerTrueFalseQuestion(studentName, 10, 3, false);
		answer.append("Exam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		
		studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName, 10, 2, false);
		manager.answerTrueFalseQuestion(studentName, 10, 3, false);
		answer.append("\nExam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		
		studentName = "Sanders,Linda";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, true);
		manager.answerTrueFalseQuestion(studentName, 10, 2, false);
		manager.answerTrueFalseQuestion(studentName, 10, 3, true);
		answer.append("\nExam score for " + studentName + " " + manager.getExamScore(studentName, 10));
		
	//	System.out.println(answer.toString());// problem is true is not True
		
		assertTrue(TestingSupport.correctResults("pubTestTrueFalse.txt", answer.toString()));	
	}
	
	@Test
	public void testReport() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		manager.addTrueFalseQuestion(10, 1, "Abstract classes cannot have constructors.", 2, false);
		manager.addTrueFalseQuestion(10, 2, "The equals method returns a boolean.", 4, true);
		manager.addTrueFalseQuestion(10, 3, "Identifiers can start with numbers.", 3, false);

		String studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 10, 1, false);
		manager.answerTrueFalseQuestion(studentName, 10, 2, false);
		manager.answerTrueFalseQuestion(studentName, 10, 3, false);
		answer.append(manager.getGradingReport(studentName, 10));
//System.out.println(answer.toString());//problem with variables, wrong numbers coming out
assertTrue(TestingSupport.correctResults("pubTestReport.txt", answer.toString()));	
	}
	
	@Test
	public void testMultipleChoiceKey() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		
		double points;
		
		String questionText = "Which of the following are valid ids?\n";
		questionText += "A: house   B: 674   C: age   D: &";
		points = 3;
		manager.addMultipleChoiceQuestion(10, 1, questionText, points, new String[]{"A","C"});
		
		questionText = "Which of the following methods belong to the Object class?\n";
		questionText += "A: equals   B: hashCode   C: separate   D: divide ";
		points = 2;
		manager.addMultipleChoiceQuestion(10, 2, questionText, points, new String[]{"A", "B"});
		
		questionText = "Which of the following allow us to define a constant?\n";
		questionText += "A: abstract   B: equals   C: class   D: final ";
		points = 4;
		manager.addMultipleChoiceQuestion(10, 3, questionText, points, new String[]{"D"});
		
		answer.append(manager.getKey(10));
		//System.out.println(answer);
		assertTrue(TestingSupport.correctResults("pubTestMultipleChoiceKey.txt", answer.toString()));	
	}
	
	@Test
	public void testMultipleChoice() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		
		double points;
		
		String questionText = "Which of the following are valid ids?\n";
		questionText += "A: house   B: 674   C: age   D: &";
		points = 3;
		manager.addMultipleChoiceQuestion(10, 1, questionText, points, new String[]{"A","C"});
		
		questionText = "Which of the following methods belong to the Object class?\n";
		questionText += "A: equals   B: hashCode   C: separate   D: divide ";
		points = 2;
		manager.addMultipleChoiceQuestion(10, 2, questionText, points, new String[]{"A","B"});
		
		questionText = "Which of the following allow us to define a constant?\n";
		questionText += "A: abstract   B: equals   C: class   D: final ";
		points = 4;
		manager.addMultipleChoiceQuestion(10, 3, questionText, points, new String[]{"D"});

		String studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		manager.answerMultipleChoiceQuestion(studentName, 10, 1, new String[]{"A", "C"});
		manager.answerMultipleChoiceQuestion(studentName, 10, 2, new String[]{"A", "B"});
		manager.answerMultipleChoiceQuestion(studentName, 10, 3, new String[]{"D"});
		answer.append("Report for " + studentName + "\n" + manager.getGradingReport(studentName, 10));
		
		studentName = "Sanders,Mike";
		manager.addStudent(studentName);
		manager.answerMultipleChoiceQuestion(studentName, 10, 1, new String[]{"A"});
		manager.answerMultipleChoiceQuestion(studentName, 10, 2, new String[]{"A", "B"});
		manager.answerMultipleChoiceQuestion(studentName, 10, 3, new String[]{"D"});
		answer.append("\nReport for " + studentName + "\n" + manager.getGradingReport(studentName, 10));
		
		//System.out.println(answer.toString());
		assertTrue(TestingSupport.correctResults("pubTestMultipleChoice.txt", answer.toString()));
	}
	
	@Test
	public void testFillInTheBlanksKey() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		double points;
		
		String questionText = "Name two types of initialization blocks.";
		points = 4;
		manager.addFillInTheBlanksQuestion(10, 1, questionText, points, new String[]{"static","non-static"});
	
		questionText = "Name the first three types of primitive types discussed in class.";
		points = 6;
		manager.addFillInTheBlanksQuestion(10, 2, questionText, points, new String[]{"int","double","boolean"});	
	
		answer.append(manager.getKey(10));
		//System.out.println(answer.toString());// orderding of correct answer is off
		assertTrue(TestingSupport.correctResults("pubTestFillInTheBlanksKey.txt", answer.toString()));
	}
	
	@Test
	public void testFillInTheBlanks() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		double points;
		
		String questionText = "Name two types of initialization blocks.";
		points = 4;
		manager.addFillInTheBlanksQuestion(10, 1, questionText, points, new String[]{"static","non-static"});
	
		questionText = "Name the first three types of primitive types discussed in class.";
		points = 6;
		manager.addFillInTheBlanksQuestion(10, 2, questionText, points, new String[]{"int","double","boolean"});	
		
		String studentName = "Peterson,Rose";
		manager.addStudent(studentName);
		manager.answerFillInTheBlanksQuestion(studentName, 10, 1, new String[]{"static", "non-static"});
		manager.answerFillInTheBlanksQuestion(studentName, 10, 2, new String[]{"int", "double", "boolean"});
		answer.append("Report for " + studentName + "\n" + manager.getGradingReport(studentName, 10));
		
		studentName = "Sanders,Laura";
		manager.addStudent(studentName);
		manager.answerFillInTheBlanksQuestion(studentName, 10, 1, new String[]{"static"});
		manager.answerFillInTheBlanksQuestion(studentName, 10, 2, new String[]{"int", "boolean"});		
		answer.append("\nReport for " + studentName + "\n" + manager.getGradingReport(studentName, 10));
	//System.out.println(answer.toString());//
		assertTrue(TestingSupport.correctResults("pubTestFillInTheBlanks.txt", answer.toString()));
	}
	/*
	PublicTests (14)
	tests.PublicTests
	testGetCourseGrades(tests.PublicTests)
	java.lang.AssertionError
		at org.junit.Assert.fail(Assert.java:86)
		at org.junit.Assert.assertTrue(Assert.java:41)
		at org.junit.Assert.assertTrue(Assert.java:52)
		at tests.PublicTests.testGetCourseGrades(PublicTests.java:277)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.lang.reflect.Method.invoke(Method.java:498)
		at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
		at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
		at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
		at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
		at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
		at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
		at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
		at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
		at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
		at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
		at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
		at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
		at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)

	testSerializationTwo(tests.PublicTests)
	java.lang.NullPointerException
		at onlineTest.SystemManager.getTotalScore(SystemManager.java:619)
		at onlineTest.SystemManager.getExamScore(SystemManager.java:811)
		at onlineTest.SystemManager.getGradingReport(SystemManager.java:922)
		at tests.PublicTests.testSerializationTwo(PublicTests.java:594)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.lang.reflect.Method.invoke(Method.java:498)
		at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
		at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
		at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
		at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
		at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
		at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
		at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
		at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
		at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
		at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
		at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
		at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
		at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)

	testMultipleChoice(tests.PublicTests)
	java.lang.ArrayIndexOutOfBoundsException: 1
		at onlineTest.MultChoice.getScoreValue(MultChoice.java:46)
		at onlineTest.SystemManager.getTotalScore(SystemManager.java:623)
		at onlineTest.SystemManager.getExamScore(SystemManager.java:811)
		at onlineTest.SystemManager.getGradingReport(SystemManager.java:922)
		at tests.PublicTests.testMultipleChoice(PublicTests.java:137)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.lang.reflect.Method.invoke(Method.java:498)
		at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
		at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
		at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
		at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
		at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
		at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
		at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
		at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
		at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
		at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
		at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
		at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
		at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)

	testFillInTheBlanks(tests.PublicTests)
	java.lang.ArrayIndexOutOfBoundsException: 1
		at onlineTest.FillinBlank.getScoreValue(FillinBlank.java:32)
		at onlineTest.SystemManager.getTotalScore(SystemManager.java:649)
		at onlineTest.SystemManager.getExamScore(SystemManager.java:811)
		at onlineTest.SystemManager.getGradingReport(SystemManager.java:922)
		at tests.PublicTests.testFillInTheBlanks(PublicTests.java:188)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.lang.reflect.Method.invoke(Method.java:498)
		at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
		at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
		at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
		at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
		at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
		at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
		at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
		at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
		at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
		at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
		at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
		at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
		at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)

	testMaxMinAverageScoreInExam(tests.PublicTests)
	java.lang.NullPointerException
		at onlineTest.SystemManager.getAverageScore(SystemManager.java:1093)
		at tests.PublicTests.testMaxMinAverageScoreInExam(PublicTests.java:310)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.lang.reflect.Method.invoke(Method.java:498)
		at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
		at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
		at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
		at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
		at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
		at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
		at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
		at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
		at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
		at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
		at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
		at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
		at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)

	testCourseNumericLetterGrade(tests.PublicTests)
	java.lang.NullPointerException
		at onlineTest.SystemManager.getTotalScore(SystemManager.java:619)
		at onlineTest.SystemManager.getExamScore(SystemManager.java:811)
		at onlineTest.SystemManager.getCourseNumericGrade(SystemManager.java:976)
		at tests.PublicTests.testCourseNumericLetterGrade(PublicTests.java:229)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.lang.reflect.Method.invoke(Method.java:498)
		at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
		at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
		at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
		at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
		at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
		at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
		at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
		at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
		at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
		at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
		at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
		at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
		at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)

	testMultipleExamsStudents(tests.PublicTests)
	java.lang.NullPointerException
		at onlineTest.SystemManager.getTotalScore(SystemManager.java:619)
		at onlineTest.SystemManager.getExamScore(SystemManager.java:811)
		at onlineTest.SystemManager.getGradingReport(SystemManager.java:922)
		at tests.PublicTests.testMultipleExamsStudents(PublicTests.java:434)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.lang.reflect.Method.invoke(Method.java:498)
		at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
		at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
		at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
		at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
		at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
		at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
		at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
		at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
		at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
		at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
		at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
		at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
		at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)

	testReport(tests.PublicTests)
	java.lang.AssertionError
		at org.junit.Assert.fail(Assert.java:86)
		at org.junit.Assert.assertTrue(Assert.java:41)
		at org.junit.Assert.assertTrue(Assert.java:52)
		at tests.PublicTests.testReport(PublicTests.java:71)
		at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.lang.reflect.Method.invoke(Method.java:498)
		at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
		at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
		at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
		at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
		at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
		at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
		at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
		at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
		at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
		at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
		at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
		at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
		at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:86)
		at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:538)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:760)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:460)
		at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:206)
*/

	
	@Test
	public void testCourseNumericLetterGrade() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		double points;
		
		/* First Exam */
		manager.addExam(1, "Midterm #1");
		manager.addTrueFalseQuestion(1, 1, "Abstract classes cannot have constructors.", 10, false);
		manager.addTrueFalseQuestion(1, 2, "The equals method returns a boolean.", 20, true);

		String questionText = "Which of the following are valid ids?\n";
		questionText += "A: house   B: 674   C: age   D: &";
		points = 40;
		manager.addMultipleChoiceQuestion(1, 3, questionText, points, new String[]{"A","C"});
		
		questionText = "Name the first three types of primitive types discussed in class.";
		points = 30;
		manager.addFillInTheBlanksQuestion(1, 4, questionText, points, new String[]{"int","double","boolean"});	
		
		String studentName = "Peterson,Laura";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, false);
		manager.answerTrueFalseQuestion(studentName, 1, 2, false);
		manager.answerMultipleChoiceQuestion(studentName, 1, 3, new String[]{"A", "C"});
		manager.answerFillInTheBlanksQuestion(studentName, 1, 4, new String[]{"int", "double"});
		
		/* Second Exam */
		manager.addExam(2, "Midterm #2");
		manager.addTrueFalseQuestion(2, 1, "A break statement terminates a loop.", 10, true);
		manager.addTrueFalseQuestion(2, 2, "A class can implement multiple interfaces.", 50, true);
		manager.addTrueFalseQuestion(2, 3, "A class can extend multiple classes.", 40, false);
		manager.answerTrueFalseQuestion(studentName, 2, 1, true);
		manager.answerTrueFalseQuestion(studentName, 2, 2, false);
		manager.answerTrueFalseQuestion(studentName, 2, 3, false);

		answer.append("Numeric grade for " + studentName + " " + manager.getCourseNumericGrade(studentName));
		answer.append("\nExam #1 Score" + " " + manager.getExamScore(studentName, 1));
		answer.append("\n" + manager.getGradingReport(studentName, 1));
		answer.append("\nExam #2 Score" + " " + manager.getExamScore(studentName, 2));
		answer.append("\n" + manager.getGradingReport(studentName, 2));

		manager.setLetterGradesCutoffs(new String[]{"A","B","C","D","F"}, 
									   new double[] {90,80,70,60,0});
		answer.append("\nCourse letter grade: " + manager.getCourseLetterGrade(studentName));
		
		assertTrue(TestingSupport.correctResults("pubTestCourseNumericLetterGrade.txt", answer.toString()));
	}
	
	@Test
	public void testGetCourseGrades() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		
		manager.addExam(1, "Midterm #1");
		manager.addTrueFalseQuestion(1, 1, "Abstract classes cannot have constructors.", 35, false);
		manager.addTrueFalseQuestion(1, 2, "The equals method returns a boolean.", 15, true);
		manager.addTrueFalseQuestion(1, 3, "The hashCode method returns an int", 50, true);
		
		String studentName = "Peterson,Laura";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, true);
		manager.answerTrueFalseQuestion(studentName, 1, 2, true);
		manager.answerTrueFalseQuestion(studentName, 1, 3, true);
		
		studentName = "Cortes,Laura";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, false);
		manager.answerTrueFalseQuestion(studentName, 1, 2, true);
		manager.answerTrueFalseQuestion(studentName, 1, 3, true);

		studentName = "Sanders,Tom";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, true);
		manager.answerTrueFalseQuestion(studentName, 1, 2, false);
		manager.answerTrueFalseQuestion(studentName, 1, 3, false);
		
		manager.setLetterGradesCutoffs(new String[]{"A","B","C","D","F"}, 
				   new double[] {90,80,70,60,0});
		
		answer.append(manager.getCourseGrades());
		
		System.out.println(answer.toString());//

		assertTrue(TestingSupport.correctResults("pubGetCourseGrades.txt", answer.toString()));
	}
	
	@Test
	public void testMaxMinAverageScoreInExam() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		
		manager.addExam(1, "Midterm #1");
		manager.addTrueFalseQuestion(1, 1, "Abstract classes cannot have constructors.", 35, false);
		manager.addTrueFalseQuestion(1, 2, "The equals method returns a boolean.", 15, true);
		manager.addTrueFalseQuestion(1, 3, "The hashCode method returns an int", 50, true);
		
		String studentName = "Peterson,Laura";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, true);
		manager.answerTrueFalseQuestion(studentName, 1, 2, true);
		manager.answerTrueFalseQuestion(studentName, 1, 3, true);
		
		studentName = "Cortes,Laura";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, false);
		manager.answerTrueFalseQuestion(studentName, 1, 2, true);
		manager.answerTrueFalseQuestion(studentName, 1, 3, true);

		studentName = "Sanders,Tom";
		manager.addStudent(studentName);
		manager.answerTrueFalseQuestion(studentName, 1, 1, true);
		manager.answerTrueFalseQuestion(studentName, 1, 2, false);
		manager.answerTrueFalseQuestion(studentName, 1, 3, false);
		
		answer.append("Maximum Score Midterm " + manager.getMaxScore(1) + "\n");
		answer.append("Minimum Score Midterm " + manager.getMinScore(1) + "\n");
		answer.append("Average Score Midterm " + manager.getAverageScore(1) + "\n");//problem
		
		System.out.println(answer.toString());
		assertTrue(TestingSupport.correctResults("pubTestMaxMinAverageScoreInExam.txt", answer.toString()));
	}
	
	@Test
	public void testMultipleExamsStudents() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		String laura = "Peterson,Laura";
		String mike = "Sanders,Mike";
		String john = "Costas,John";
		
		/* Adding students */
		manager.addStudent(laura);
		manager.addStudent(mike);
		manager.addStudent(john);
		
		/* First Exam */
		int examId = 1;
		manager.addExam(examId, "Midterm #1");
		
		manager.addTrueFalseQuestion(examId, 1, "Java methods are examples of procedural abstractions.", 2, true);
		
		manager.addTrueFalseQuestion(examId, 2, "An inner class can only access public variables and methods of the enclosing class.", 2, false);
		
		String questionText = "Which of the following allow us to define an abstract class?\n";
		questionText += "A: abstract   B: equals   C: class   D: final ";
		double points = 4;
		manager.addMultipleChoiceQuestion(examId, 3, questionText, points, new String[]{"A"});
		
		questionText = "Name three access specifiers";
		points = 6;
		manager.addFillInTheBlanksQuestion(examId, 4, questionText, points, new String[]{"public","private","protected"});	
				
		/* Answers */
		examId = 1;
		manager.answerTrueFalseQuestion(laura, examId, 1, true);
		manager.answerTrueFalseQuestion(laura, examId, 2, true);
		manager.answerMultipleChoiceQuestion(laura, examId, 3, new String[]{"A"});
		manager.answerFillInTheBlanksQuestion(laura, examId, 4, new String[]{"private", "public", "protected"});
		
		manager.answerTrueFalseQuestion(mike, examId, 1, true);
		manager.answerTrueFalseQuestion(mike, examId, 2, false);
		manager.answerMultipleChoiceQuestion(mike, examId, 3, new String[]{"A"});
		manager.answerFillInTheBlanksQuestion(mike, examId, 4, new String[]{"private"});
		
		manager.answerTrueFalseQuestion(john, examId, 1, true);
		manager.answerTrueFalseQuestion(john, examId, 2, false);
		manager.answerMultipleChoiceQuestion(john, examId, 3, new String[]{"A", "B", "C"});
		manager.answerFillInTheBlanksQuestion(john, examId, 4, new String[]{"private", "while"});
		
		/* Second Exam */
		examId = 2;
		manager.addExam(examId, "Midterm #2");
		manager.addTrueFalseQuestion(examId, 1, "The Comparable interface specifies a method called compareTo", 2, true);		
		manager.addTrueFalseQuestion(examId, 2, "The Comparator interface specifies a method called compare", 2, true);
		manager.addTrueFalseQuestion(examId, 3, "A static initialization block is executed when each object is created", 4, false);
		
		questionText = "Which of the following allow us to access a super class method?\n";
		questionText += "A: abstract   B: equals   C: super   D: final ";
		points = 8;
		manager.addMultipleChoiceQuestion(examId, 4, questionText, points, new String[]{"C"});
		
		questionText = "Which of the following are methods of the Object class?\n";
		questionText += "A: hashCode   B: equals   C: super   D: final ";
		points = 6;
		manager.addMultipleChoiceQuestion(examId, 5, questionText, points, new String[]{"A","B"});
		

		/* Answers */
		examId = 2;
		manager.answerTrueFalseQuestion(laura, examId, 1, true);
		manager.answerTrueFalseQuestion(laura, examId, 2, true);
		manager.answerTrueFalseQuestion(laura, examId, 3, true);
		manager.answerMultipleChoiceQuestion(laura, examId, 4, new String[]{"C"});
		manager.answerMultipleChoiceQuestion(laura, examId, 5, new String[]{"A", "C"});
		
		manager.answerTrueFalseQuestion(mike, examId, 1, true);
		manager.answerTrueFalseQuestion(mike, examId, 2, true);
		manager.answerTrueFalseQuestion(mike, examId, 3, true);
		manager.answerMultipleChoiceQuestion(mike, examId, 4, new String[]{"C"});
		manager.answerMultipleChoiceQuestion(mike, examId, 5, new String[]{"A", "B"});
		
		manager.answerTrueFalseQuestion(john, examId, 1, false);
		manager.answerTrueFalseQuestion(john, examId, 2, true);
		manager.answerTrueFalseQuestion(john, examId, 3, false);
		manager.answerMultipleChoiceQuestion(john, examId, 4, new String[]{"C"});
		manager.answerMultipleChoiceQuestion(john, examId, 5, new String[]{"A", "B"});
		
		/* Third Exam */
		examId = 3;
		manager.addExam(examId, "Midterm #3");
		manager.addTrueFalseQuestion(examId, 1, "There are two types of exceptions: checked and unchecked.", 4, true);		
		manager.addTrueFalseQuestion(examId, 2, "The traveling salesman problem is an example of an NP problem.", 4, true);
		manager.addTrueFalseQuestion(examId, 3, "Array indexing takes O(n) time", 4, false);
		
		questionText = "Name two properties of a good hash function.";
		points = 6;
		manager.addFillInTheBlanksQuestion(examId, 4, questionText, points, new String[]{"not expensive","distributes values well"});		
		
		/* Answers */
		examId = 3;
		manager.answerTrueFalseQuestion(laura, examId, 1, true);
		manager.answerTrueFalseQuestion(laura, examId, 2, true);
		manager.answerTrueFalseQuestion(laura, examId, 3, false);
		manager.answerFillInTheBlanksQuestion(laura, examId, 4, new String[]{"not expensive", "distributes values well"});
		
		manager.answerTrueFalseQuestion(mike, examId, 1, false);
		manager.answerTrueFalseQuestion(mike, examId, 2, true);
		manager.answerTrueFalseQuestion(mike, examId, 3, false);
		manager.answerFillInTheBlanksQuestion(mike, examId, 4, new String[]{"polynomial", "distributes values well"});

		manager.answerTrueFalseQuestion(john, examId, 1, false);
		manager.answerTrueFalseQuestion(john, examId, 2, false);
		manager.answerTrueFalseQuestion(john, examId, 3, false);
		manager.answerFillInTheBlanksQuestion(john, examId, 4, new String[]{"distributes values well"});
	
		ArrayList<String> list = new ArrayList<String>();
		list.add(laura);
		list.add(mike);
		list.add(john);
		for (examId = 1; examId <= 3; examId++) {
			for (String student : list) {
				answer.append("Report for " + student + " Exam # " + examId + "\n" + manager.getGradingReport(student, examId) + "\n\n");
			}
		}
		
		for (examId = 1; examId <= 3; examId++) {
			answer.append("Minimum for Exam # " + examId + " " + manager.getMinScore(examId) + "\n");
			answer.append("Maximum for Exam # " + examId + " " + manager.getMaxScore(examId) + "\n");
			answer.append("Average for Exam # " + examId + " " + (int)manager.getAverageScore(examId) + "\n");
		}
		
		manager.setLetterGradesCutoffs(new String[]{"A+","A", "B+", "B", "C", "D", "F"}, new double[]{95,90,85,80,70,60,0});
		
		for (String student : list)
			answer.append("Letter Grade for " + student + " " + manager.getCourseLetterGrade(student) + "\n");
		
		assertTrue(TestingSupport.correctResults("pubTestMultipleExamsStudents.txt", answer.toString()));
	}

	@Test
	public void testSerialization() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		manager.addExam(10, "Midterm");
		manager.addTrueFalseQuestion(10, 1, "Abstract classes cannot have constructors.", 2, false);
		manager.addTrueFalseQuestion(10, 2, "The equals method returns a boolean.", 4, true);
		manager.addTrueFalseQuestion(10, 3, "Identifiers can start with numbers.", 3, false);
		answer.append(manager.getKey(10));
	
		String fileName = "serializedManager.ser";
		manager.saveManager(manager, fileName);
		SystemManager restoredManager = (SystemManager) manager.restoreManager(fileName);	
		
		assertTrue(TestingSupport.correctResults("serializationTest1.txt", restoredManager.getKey(10)));
	}
	
	@Test
	public void testSerializationTwo() {
		StringBuffer answer = new StringBuffer();
		SystemManager manager = new SystemManager();
		String laura = "Peterson,Laura";
		String mike = "Sanders,Mike";
		String john = "Costas,John";
		
		/* Adding students */
		manager.addStudent(laura);
		manager.addStudent(mike);
		manager.addStudent(john);
		
		/* First Exam */
		int examId = 1;
		manager.addExam(examId, "Midterm #1");
		
		manager.addTrueFalseQuestion(examId, 1, "Java methods are examples of procedural abstractions.", 2, true);
		
		manager.addTrueFalseQuestion(examId, 2, "An inner class can only access public variables and methods of the enclosing class.", 2, false);
		
		String questionText = "Which of the following allow us to define an abstract class?\n";
		questionText += "A: abstract   B: equals   C: class   D: final ";
		double points = 4;
		manager.addMultipleChoiceQuestion(examId, 3, questionText, points, new String[]{"A"});
		
		questionText = "Name three access specifiers";
		points = 6;
		manager.addFillInTheBlanksQuestion(examId, 4, questionText, points, new String[]{"public","private","protected"});	
				
		/* Answers */
		examId = 1;
		manager.answerTrueFalseQuestion(laura, examId, 1, true);
		manager.answerTrueFalseQuestion(laura, examId, 2, true);
		manager.answerMultipleChoiceQuestion(laura, examId, 3, new String[]{"A"});
		manager.answerFillInTheBlanksQuestion(laura, examId, 4, new String[]{"private", "public", "protected"});
		
		manager.answerTrueFalseQuestion(mike, examId, 1, true);
		manager.answerTrueFalseQuestion(mike, examId, 2, false);
		manager.answerMultipleChoiceQuestion(mike, examId, 3, new String[]{"A"});
		manager.answerFillInTheBlanksQuestion(mike, examId, 4, new String[]{"private"});
		
		manager.answerTrueFalseQuestion(john, examId, 1, true);
		manager.answerTrueFalseQuestion(john, examId, 2, false);
		manager.answerMultipleChoiceQuestion(john, examId, 3, new String[]{"A", "B", "C"});
		manager.answerFillInTheBlanksQuestion(john, examId, 4, new String[]{"private", "while"});
		
		/* Second Exam */
		examId = 2;
		manager.addExam(examId, "Midterm #2");
		manager.addTrueFalseQuestion(examId, 1, "The Comparable interface specifies a method called compareTo", 2, true);		
		manager.addTrueFalseQuestion(examId, 2, "The Comparator interface specifies a method called compare", 2, true);
		manager.addTrueFalseQuestion(examId, 3, "A static initialization block is executed when each object is created", 4, false);
		
		questionText = "Which of the following allow us to access a super class method?\n";
		questionText += "A: abstract   B: equals   C: super   D: final ";
		points = 8;
		manager.addMultipleChoiceQuestion(examId, 4, questionText, points, new String[]{"C"});
		
		questionText = "Which of the following are methods of the Object class?\n";
		questionText += "A: hashCode   B: equals   C: super   D: final ";
		points = 6;
		manager.addMultipleChoiceQuestion(examId, 5, questionText, points, new String[]{"A","B"});
		

		/* Answers */
		examId = 2;
		manager.answerTrueFalseQuestion(laura, examId, 1, true);
		manager.answerTrueFalseQuestion(laura, examId, 2, true);
		manager.answerTrueFalseQuestion(laura, examId, 3, true);
		manager.answerMultipleChoiceQuestion(laura, examId, 4, new String[]{"C"});
		manager.answerMultipleChoiceQuestion(laura, examId, 5, new String[]{"A", "C"});
		
		manager.answerTrueFalseQuestion(mike, examId, 1, true);
		manager.answerTrueFalseQuestion(mike, examId, 2, true);
		manager.answerTrueFalseQuestion(mike, examId, 3, true);
		manager.answerMultipleChoiceQuestion(mike, examId, 4, new String[]{"C"});
		manager.answerMultipleChoiceQuestion(mike, examId, 5, new String[]{"A", "B"});
		
		manager.answerTrueFalseQuestion(john, examId, 1, false);
		manager.answerTrueFalseQuestion(john, examId, 2, true);
		manager.answerTrueFalseQuestion(john, examId, 3, false);
		manager.answerMultipleChoiceQuestion(john, examId, 4, new String[]{"C"});
		manager.answerMultipleChoiceQuestion(john, examId, 5, new String[]{"A", "B"});
		
		/* Third Exam */
		examId = 3;
		manager.addExam(examId, "Midterm #3");
		manager.addTrueFalseQuestion(examId, 1, "There are two types of exceptions: checked and unchecked.", 4, true);		
		manager.addTrueFalseQuestion(examId, 2, "The traveling salesman problem is an example of an NP problem.", 4, true);
		manager.addTrueFalseQuestion(examId, 3, "Array indexing takes O(n) time", 4, false);
		
		questionText = "Name two properties of a good hash function.";
		points = 6;
		manager.addFillInTheBlanksQuestion(examId, 4, questionText, points, new String[]{"not expensive","distributes values well"});		
		
		/* Answers */
		examId = 3;
		manager.answerTrueFalseQuestion(laura, examId, 1, true);
		manager.answerTrueFalseQuestion(laura, examId, 2, true);
		manager.answerTrueFalseQuestion(laura, examId, 3, false);
		manager.answerFillInTheBlanksQuestion(laura, examId, 4, new String[]{"not expensive", "distributes values well"});
		
		manager.answerTrueFalseQuestion(mike, examId, 1, false);
		manager.answerTrueFalseQuestion(mike, examId, 2, true);
		manager.answerTrueFalseQuestion(mike, examId, 3, false);
		manager.answerFillInTheBlanksQuestion(mike, examId, 4, new String[]{"polynomial", "distributes values well"});

		manager.answerTrueFalseQuestion(john, examId, 1, false);
		manager.answerTrueFalseQuestion(john, examId, 2, false);
		manager.answerTrueFalseQuestion(john, examId, 3, false);
		manager.answerFillInTheBlanksQuestion(john, examId, 4, new String[]{"distributes values well"});
	
		String fileName = "serializedManagerTwo.ser";
		manager.saveManager(manager, fileName);
		SystemManager restoredManager = (SystemManager) manager.restoreManager(fileName);
		

		/* After manager has been restored */
		ArrayList<String> list = new ArrayList<String>();
		list.add(laura);
		list.add(mike);
		list.add(john);
		for (examId = 1; examId <= 3; examId++) {
			for (String student : list) {
				answer.append("Report for " + student + " Exam # " + examId + "\n" + restoredManager.getGradingReport(student, examId) + "\n\n");
			}
		}
		
		for (examId = 1; examId <= 3; examId++) {
			answer.append("Minimum for Exam # " + examId + " " + restoredManager.getMinScore(examId) + "\n");
			answer.append("Maximum for Exam # " + examId + " " + restoredManager.getMaxScore(examId) + "\n");
			answer.append("Average for Exam # " + examId + " " + (int)restoredManager.getAverageScore(examId) + "\n");
		}
		
		restoredManager.setLetterGradesCutoffs(new String[]{"A+","A", "B+", "B", "C", "D", "F"}, new double[]{95,90,85,80,70,60,0});
		
		for (String student : list)
			answer.append("Letter Grade for " + student + " " + restoredManager.getCourseLetterGrade(student) + "\n");
		
		assertTrue(TestingSupport.correctResults("pubTestMultipleExamsStudents.txt", answer.toString()));
	}
}

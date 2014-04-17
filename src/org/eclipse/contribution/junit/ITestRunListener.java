package org.eclipse.contribution.junit;

public interface ITestRunListener {

	void testsStarted(int testCount);

	void testsFinished();

	void testStarted(String klass, String method);

	void testFailed(String klass, String method, String trace);

}

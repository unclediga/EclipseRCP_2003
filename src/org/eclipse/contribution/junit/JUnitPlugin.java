package org.eclipse.contribution.junit;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jdt.core.IType;
import org.osgi.framework.BundleContext;

public class JUnitPlugin extends Plugin {
	private static JUnitPlugin instance;
	private ArrayList listeners = new ArrayList();
	
	public JUnitPlugin(){

	}
	
	public static JUnitPlugin getPlugin() {
		return instance;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		instance = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		instance = null;
		super.stop(context);
	}

	public void run(IType type) {
		new TestRunner().run(type);
	}

	public void addTestListener(ITestRunListener listener) {
		listeners.add(listener);
	}

	public void removeTestListener(ITestRunListener listener) {
		listeners.remove(listener);
	}

	public void fireTestsStarted(int count) {
		for (Iterator all = getListeners().iterator(); all.hasNext();) {
			ITestRunListener each = (ITestRunListener) all.next();
			each.testsStarted(count);
		}
	}

	public void fireTestsFinished() {
		for (Iterator all = getListeners().iterator(); all.hasNext();) {
			ITestRunListener each = (ITestRunListener) all.next();
			each.testsFinished();
		}
	}

	private ArrayList getListeners() {
		return listeners;
	}

	public void fireTestStarted(String klass, String methodName) {
		for (Iterator all = getListeners().iterator(); all.hasNext();) {
			ITestRunListener each = (ITestRunListener) all.next();
			each.testStarted(klass, methodName);
		}
	}

	public void fireTestFailed(String klass, String method, String trace) {
		for (Iterator all = getListeners().iterator(); all.hasNext();) {
			ITestRunListener each = (ITestRunListener) all.next();
			each.testFailed(klass, method, trace);
		}
	}

}

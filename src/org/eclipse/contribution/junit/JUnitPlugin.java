package org.eclipse.contribution.junit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jdt.core.IType;
import org.osgi.framework.BundleContext;

public class JUnitPlugin extends Plugin {
	private static JUnitPlugin instance;
	private List listeners;

	private static final String listenerId=
			   "org.eclipse.contribution.junit.listeners";

	
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

	public void fireTestsStarted(final int testCount) {
		for (Iterator all = getListeners().iterator(); all.hasNext();) {
			final ITestRunListener each = (ITestRunListener) all.next();
			ISafeRunnable runnable = new ISafeRunnable() {
				public void handleException(Throwable exception) {
				}

				public void run() throws Exception {
					each.testsStarted(testCount);
				}
			};
			Platform.run(runnable);
		}
	}

	public void fireTestsFinished() {
		for (Iterator all = getListeners().iterator(); all.hasNext();) {
			final ITestRunListener each = (ITestRunListener) all.next();
			ISafeRunnable runnable = new ISafeRunnable() {
				@Override
				public void handleException(Throwable exception) {
				}
				@Override
				public void run() throws Exception {
					each.testsFinished();
				}
			};
			Platform.run(runnable);
			
		}
	}

	public void fireTestStarted(final String klass, final String methodName) {
		for (Iterator all = getListeners().iterator(); all.hasNext();) {
			final ITestRunListener each = (ITestRunListener) all.next();
			ISafeRunnable runnable = new ISafeRunnable() {

				@Override
				public void handleException(Throwable exception) {
				}

				@Override
				public void run() throws Exception {
					each.testStarted(klass, methodName);
				}
			};
			Platform.run(runnable);

		}
	}
	
	public void fireTestFailed(final String klass, final String method, final String trace) {
		for (Iterator all = getListeners().iterator(); all.hasNext();) {
			final ITestRunListener each = (ITestRunListener) all.next();
			ISafeRunnable runnable = new ISafeRunnable() {

				@Override
				public void handleException(Throwable exception) {
				}

				@Override
				public void run() throws Exception {
					each.testFailed(klass, method, trace);
				}
				
			};
			Platform.run(runnable);
		}
	}
	
	private List getListeners() {
		if(listeners == null){
			listeners =  computeListeners(); 
		}
		return listeners;
	}

	private List computeListeners() {
		IExtensionRegistry registry = Platform.getExtensionRegistry();
		IExtensionPoint extentionPoint = registry.getExtensionPoint(listenerId);
		IExtension[] extentions = extentionPoint.getExtensions();
		ArrayList result = new ArrayList();
		for (int i = 0; i < extentions.length; i++) {
			IConfigurationElement[] elements = extentions[i].getConfigurationElements();
			for (int j = 0; j < elements.length; j++) {
				try {
					Object listener = elements[i].createExecutableExtension("class");
					if(listener instanceof ITestRunListener){
						result.add(listener);
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}


}

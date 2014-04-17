package org.eclipse.contribution.junit;

import org.eclipse.jdt.core.IType;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class RunTestAction implements IObjectActionDelegate {

	private ISelection selection;

	public RunTestAction() {
		System.out
				.println("MY:" + this.getClass().getName() + ".Constructor()");
	}

	@Override
	public void run(IAction action) {
		if (!(selection instanceof IStructuredSelection))
			return;
		IStructuredSelection structured = (IStructuredSelection) selection;
		IType type = (IType) structured.getFirstElement();
		ITestRunListener listener = new Listener();
		JUnitPlugin.getPlugin().addTestListener(listener);
		JUnitPlugin.getPlugin().run(type);
		JUnitPlugin.getPlugin().removeTestListener(listener);

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		System.out.println("MY:" + this.getClass().getName()
				+ ".selectionChanged()");
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	// //////////////////////////////////////////////////////////////
	public static class Listener implements ITestRunListener {
		private boolean passed = true;

		public void testsStarted(int testCount) {
		}

		public void testsFinished() {
			String message = passed ? "Pass" : "Fail";
			MessageDialog.openInformation(null, "Test Results", message);
		}

		public void testStarted(String klass, String method) {
		}

		public void testFailed(String klass, String method, String trace) {
			passed = false;
		}
	}

}

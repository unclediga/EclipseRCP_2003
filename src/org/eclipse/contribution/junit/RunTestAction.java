package org.eclipse.contribution.junit;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.jdt.core.IType;

public class RunTestAction implements IObjectActionDelegate {

	private ISelection selection;

	public RunTestAction() {
		// TODO Auto-generated constructor stub
		System.out
				.println("MY:" + this.getClass().getName() + ".Constructor()");
	}

	@Override
	public void run(IAction action) {
		if (!(selection instanceof IStructuredSelection))
			return;
		IStructuredSelection structured = (IStructuredSelection) selection;
		IType type = (IType) structured.getFirstElement();
		System.out.println("MY:" + this.getClass().getName() + ".run()");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
		System.out.println("MY:" + this.getClass().getName()
				+ ".selectionChanged()");
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
	}

}

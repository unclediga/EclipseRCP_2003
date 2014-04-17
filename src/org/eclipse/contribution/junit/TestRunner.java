package org.eclipse.contribution.junit;

import org.eclipse.jdt.core.IType;
import org.eclipse.jface.dialogs.MessageDialog;

public class TestRunner {

	public void run(IType type) {
		MessageDialog.openInformation(null, "Test Runner", "Run tests....");
	}

}

package org.eclipse.contribution.junit;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jdt.core.IType;
import org.osgi.framework.BundleContext;

public class JUnitPlugin extends Plugin {
	private static JUnitPlugin instance;

	public JUnitPlugin(IPluginDescriptor descriptor){
		super(descriptor);
		instance = this;
		System.out.println("MY:"+this.getClass().getName()+".Constructor()");	
	}
	
	public JUnitPlugin getPlugin() {
		return instance;
	}
	
	public void run(IType type) {
		// TODO 
		System.out.println("MY:"+this.getClass().getName()+".run()");	
	}

	@Override
	public void shutdown() throws CoreException {
		// TODO Auto-generated method stub
		System.out.println("MY:"+this.getClass().getName()+".shutdown()");	
		super.shutdown();
	}

	@Override
	public void start(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MY:"+this.getClass().getName()+".start()");	
		super.start(context);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MY:"+this.getClass().getName()+".stOP()");	
		super.stop(context);
	}

	@Override
	public void startup() throws CoreException {
		// TODO Auto-generated method stub
		System.out.println("MY:"+this.getClass().getName()+".startup()");	
		super.startup();
	}
}

package edu.ucsb.cs.rpc.ice;
public class EchoServer {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
            Ice.StringSeqHolder argsH = new Ice.StringSeqHolder(args);
            Ice.Properties props = Ice.Util.createProperties(argsH);
            props.setProperty("Ice.MessageSizeMax", "2048");
            Ice.InitializationData id = new Ice.InitializationData();
            id.properties = props;
			ic = Ice.Util.initialize(id);
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints(
					"EchoAdapter", "default -p 9999");
			Ice.Object object = new EchoI();
			adapter.add(object, ic.stringToIdentity("EchoService"));
			adapter.activate();
			ic.waitForShutdown();
		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}
		if (ic != null) {
			// Clean up
			//
			try {
				ic.destroy();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}
}
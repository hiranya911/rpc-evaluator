import java.io.*;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import edu.ucsb.cs.rpc.base.Server;

class JsonServlet extends HttpServlet {
	
	private static final long serialVersionUID = -4390171626730974139L;
	private JsonRpcServer jsonRpcServer;
	private JsonServerImpl serverService;
	
	public void init(ServletConfig config) {
	 	serverService = new JsonServerImpl();
		jsonRpcServer = new JsonRpcServer (serverService, Server.class);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			jsonRpcServer.handle(req, resp);
		} catch (IOException e) { e.printStackTrace();}
	}
}

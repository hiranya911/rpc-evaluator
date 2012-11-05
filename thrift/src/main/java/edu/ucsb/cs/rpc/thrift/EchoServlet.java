package edu.ucsb.cs.rpc.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServlet;

public class EchoServlet extends TServlet {

    public EchoServlet() {
        super(new EchoService.Processor<EchoServiceHandler>(new EchoServiceHandler()),
                new TBinaryProtocol.Factory(), new TBinaryProtocol.Factory());
    }
}

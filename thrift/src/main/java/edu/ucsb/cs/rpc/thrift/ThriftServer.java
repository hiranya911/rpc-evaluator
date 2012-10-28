package edu.ucsb.cs.rpc.thrift;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class ThriftServer {

    public static void main(String[] args) {
        int port = 9090;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        try {
            EchoServiceHandler handler = new EchoServiceHandler();
            EchoService.Processor<EchoServiceHandler> processor =
                    new EchoService.Processor<EchoServiceHandler>(handler);
            TServerTransport serverTransport = new TServerSocket(port);
            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(
                    serverTransport).processor(processor));
            System.out.println("Starting Thrift server on port: " + port);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

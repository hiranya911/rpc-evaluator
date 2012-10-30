#!/bin/sh
rmiregistry &
java -cp ../lib/rpc-base-1.0.jar:../lib/rpc-rmi-service-1.0.jar edu.ucsb.cs.rpc.rmi.service.RmiEchoServer

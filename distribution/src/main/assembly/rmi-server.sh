#!/bin/sh
rmiregistry &
java -Djava.security.policy=../conf/server.policy -cp ../lib/rpc-base-1.0.jar:../lib/rpc-rmi-service-1.0.jar edu.ucsb.cs.rpc.rmi.service.RmiEchoServer

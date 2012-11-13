#!/bin/sh
java -cp ../lib/rpc-base-1.0.jar:../lib/commons-logging-1.1.1.jar:../lib/netty-3.4.1.Final.jar:../lib/protobuf-java-2.4.1.jar:../lib/protobuf-rpc-pro-duplex-2.0.1.jar:../lib/rpc-protobuf-service-1.0.jar edu.ucsb.cs.rpc.protobuf.ProtobufEchoServer ../conf/rpc.properties

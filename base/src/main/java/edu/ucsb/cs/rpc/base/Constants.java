package edu.ucsb.cs.rpc.base;

public class Constants {

    public static final int HELP = 0;
    public static final int NUMBER_OF_ITERATIONS = 1;
    public static final int OPERATION = 2;
    public static final int CLIENT_API = 3;
    public static final int CONFIGURATION_FILE = 4;
    public static final int INPUT_SIZE = 5;
    public static final int WARM_UP_ROUNDS = 6;

    public static final String[] ARGS = {
            "-h",    // Display a help message
            "-n",   // Number of iterations to run
            "-o",   // Name of the operation to run (eg: doNothing)
            "-c",   // Client API to use (eg: edu.ucsb.cs.rpc.soap.SOAPClient)
            "-f",   // Configuration file location (optional)
            "-s",   // Input size (interpretation depends on the operation)
            "-w",   // Number of warm up invocations to execute
    };

    public static final int OP_DO_NOTHING  = 0;
    public static final int OP_ECHO_STRING = 1;
    public static final int OP_ECHO_INT    = 2;
    public static final int OP_ECHO_ARRAY  = 3;
    public static final int OP_ECHO_OBJECT = 4;
    public static final int OP_ECHO_MAP    = 5;
    public static final int OP_ECHO_BLOB   = 6;
    public static final String[] OPERATIONS = { "doNothing", "echoString", "echoInt",
            "echoArray", "echoObject", "echoMap", "echoBlob"
    };

    public static final int UNSPECIFIED = -1234;

    public static final Integer[] INPUT_SIZE_REQUIRED_OPS = { OP_ECHO_STRING,
            OP_ECHO_ARRAY, OP_ECHO_MAP, OP_ECHO_BLOB };

    public static final String ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ1bcdefghijklmnopqrstuvwxyz1234567890";

    public static final String[] CLIENTS = {
            "soap",
            "thrift",
            "rmi",
            "json",
            "protobuf",
            "ice"
    };

    public static final String[] CLIENT_API_NAMES = {
            "edu.ucsb.cs.rpc.soap.client.EchoClient",
            "edu.ucsb.cs.rpc.thrift.EchoClient",
            "edu.ucsb.cs.rpc.rmi.client.EchoClient",
            "edu.ucsb.cs.rpc.json.client.JsonClient",
            "edu.ucsb.cs.rpc.protobuf.client.EchoClient",
            "edu.ucsb.cs.rpc.ice.EchoClient"
    };
}

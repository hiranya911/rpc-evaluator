package edu.ucsb.cs.rpc.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        int iterations = Constants.UNSPECIFIED;
        int operation = Constants.UNSPECIFIED;
        int inputSize = Constants.UNSPECIFIED;
        String client = null;
        Properties properties = new Properties();

        List<String> arguments = Arrays.asList(Constants.ARGS);
        for (int i = 0; i < args.length; i++) {
            int index = arguments.indexOf(args[i]);
            switch (index) {
                case Constants.HELP:
                    printUsageAndExit(null);
                    break;
                case Constants.NUMBER_OF_ITERATIONS:
                    iterations = Integer.parseInt(args[++i]);
                    break;
                case Constants.OPERATION:
                    operation = Arrays.asList(Constants.OPERATIONS).indexOf(args[++i]);
                    break;
                case Constants.CLIENT_API:
                    client = args[++i];
                    break;
                case Constants.CONFIGURATION_FILE:
                    try {
                        properties.load(new FileInputStream(args[++i]));
                    } catch (IOException e) {
                        handleException("Error loading specified properties file", e);
                        return;
                    }
                    break;
                case Constants.INPUT_SIZE:
                    inputSize = Integer.parseInt(args[i++]);
                default:
                    printUsageAndExit("Unrecognized command line option: " + args[i]);
            }
        }

        if (iterations == Constants.UNSPECIFIED) {
            printUsageAndExit("Iteration count not specified");
        } else if (iterations <= 0) {
            printUsageAndExit("Invalid iteration count");
        } else if (operation == Constants.UNSPECIFIED) {
            printUsageAndExit("Operation not specified");
        } else if (operation == -1) {
            printUsageAndExit("Invalid operation name");
        } else if (client == null) {
            printUsageAndExit("Client API not specified");
        } else if (Arrays.asList(Constants.INPUT_SIZE_REQUIRED_OPS).contains(operation)) {
            if (inputSize == Constants.UNSPECIFIED) {
                printUsageAndExit("Input size not specified");
            } else if (inputSize <= 0) {
                printUsageAndExit("Invalid input size");
            }
        }

        Client clientImpl;
        try {
            clientImpl = (Client) Main.class.getClassLoader().loadClass(client).newInstance();
            clientImpl.init(properties);
        } catch (Exception e) {
            handleException("Error initializing the client API", e);
            return;
        }

        RPCEvaluation evaluation = new RPCEvaluation(iterations, operation, inputSize, clientImpl);
        RPCEvaluationResult result = evaluation.run();
        System.out.println(result);
        clientImpl.destroy();
    }

    private static void handleException(String msg, Throwable t) {
        System.err.println(msg);
        t.printStackTrace();
    }

    private static void printUsageAndExit(String error) {
        int status = 0;
        if (error != null) {
            System.out.println(error);
            status = 1;
        }
        System.out.println("Usage: Main -n iterations -o operation -c client [-f file]");
        System.exit(status);
    }
}

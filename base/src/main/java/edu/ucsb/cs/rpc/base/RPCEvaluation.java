package edu.ucsb.cs.rpc.base;

import java.util.*;

public class RPCEvaluation {

    private int iterations;
    private int operation;
    private int inputSize;
    private Client client;

    private String inputString;
    private int inputInt;
    private int[] inputArray;
    private Map<Integer,Integer> inputMap;
    private DataObject inputObject;

    public RPCEvaluation(int iterations, int operation, int inputSize, Client client) {
        this.iterations = iterations;
        this.operation = operation;
        this.inputSize = inputSize;
        this.client = client;
    }

    public void run() {
        preProcess();
        List<InvocationResult> results = new ArrayList<InvocationResult>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            InvocationResult result = null;
            switch (operation) {
                case Constants.OP_DO_NOTHING:
                    result = client.doNothing();
                    break;
                case Constants.OP_ECHO_STRING:
                    result = client.echoString(inputString);
                    break;
                case Constants.OP_ECHO_INT:
                    result = client.echoInt(inputInt);
                    break;
                case Constants.OP_ECHO_ARRAY:
                    result = client.echoArray(inputArray);
                    break;
                case Constants.OP_ECHO_MAP:
                    result = client.echoMap(inputMap);
                    break;
                case Constants.OP_ECHO_OBJECT:
                    result = client.echoObject(inputObject);
                    break;
            }
            results.add(result);
        }
        long endTime = System.currentTimeMillis();
        postProcess(results, endTime - startTime);
    }

    private void preProcess() {
        Random randomizer = new Random();
        switch (operation) {
            case Constants.OP_ECHO_STRING:
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < inputSize; i++) {
                    builder.append(Constants.ALPHABET.charAt(randomizer.nextInt(
                            Constants.ALPHABET.length())));
                }
                inputString = builder.toString();
                break;
            case Constants.OP_ECHO_INT:
                inputInt = randomizer.nextInt(10);
                break;
            case Constants.OP_ECHO_ARRAY:
                inputArray = new int[inputSize];
                for (int i = 0; i < inputSize; i++) {
                    inputArray[i] = randomizer.nextInt(10);
                }
                break;
            case Constants.OP_ECHO_MAP:
                inputMap = new HashMap<Integer, Integer>();
                for (int i = 0; i < inputSize; i++) {
                    inputMap.put(randomizer.nextInt(10), randomizer.nextInt(10));
                }
                break;
            case Constants.OP_ECHO_OBJECT:
                inputObject = new DataObject();
                inputObject.setCharacter(Constants.ALPHABET.charAt(
                        randomizer.nextInt(Constants.ALPHABET.length())));
                inputObject.setDecimal(randomizer.nextFloat());
                inputObject.setInteger(randomizer.nextInt(10));
                inputObject.setString("RPC_EVALUATION");
        }
    }

    private void postProcess(List<InvocationResult> results, long duration) {
        long success = 0;
        long fail = 0;
        long success_time = 0;
        long fail_time = 0;
        for (InvocationResult result : results) {
            if (result.isSuccess()) {
                success++;
                success_time += result.getLatency();
            } else {
                fail++;
                fail_time += result.getLatency();
            }
        }

        long total = success + fail;
        System.out.println("Total duration: " + duration + "ms");
        System.out.println("Total invocations: " + total);
        System.out.println("Successful invocations: " + success);
        System.out.println("Failed invocations: " + fail);
        System.out.println("Success rate: " + (success * 100/ total) + "%");
        System.out.println("Success throughput: " + (success/success_time/1000) + "TPS");
        System.out.println("Failure throughput: " + (fail/fail_time/1000) + "TPS");
        System.out.println("Overall throughput: " + (total/duration/1000) + "TPS");
        System.out.println("Success Latency: " + (success_time/success) + "ms");
        System.out.println("Failure Latency: " + (fail_time/fail) + "ms");
        System.out.println("Overall Latency: " + ((success_time + fail_time)/total) + "ms");
    }


}

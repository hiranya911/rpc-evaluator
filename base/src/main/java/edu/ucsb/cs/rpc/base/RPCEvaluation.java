package edu.ucsb.cs.rpc.base;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class RPCEvaluation {

    private int iterations;
    private int operation;
    private int inputSize;
    private int warmUpRounds;
    private Client client;

    private String inputString;
    private int inputInt;
    private int[] inputArray;
    private Map<Integer,Integer> inputMap;
    private DataObject inputObject;
    private byte[] inputBlob;

    private int progressCounter;

    public RPCEvaluation(int iterations, int operation, int inputSize,
                         int warmUpRounds, Client client) {
        this.iterations = iterations;
        this.operation = operation;
        this.inputSize = inputSize;
        this.warmUpRounds = warmUpRounds;
        this.client = client;
    }

    public RPCEvaluationResult run() {
        preProcess();
        if (warmUpRounds > 0) {
            System.out.println("Running " + warmUpRounds + " warm up invocations");
            for (int i = 0; i < warmUpRounds; i++) {
                invoke();
            }
        }

        System.out.println("Starting test...");
        progressCounter = 0;
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture future = exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Completed " + progressCounter + " iterations");
            }
        }, 30, 30, TimeUnit.SECONDS);
        List<InvocationResult> results = new ArrayList<InvocationResult>();
        long startTime = System.currentTimeMillis();
        for (;progressCounter < iterations; progressCounter++) {
            results.add(invoke());
        }
        long endTime = System.currentTimeMillis();
        future.cancel(true);
        exec.shutdownNow();
        return new RPCEvaluationResult(results, startTime, endTime);
    }

    private InvocationResult invoke() {
        switch (operation) {
            case Constants.OP_DO_NOTHING:
                return client.doNothing();
            case Constants.OP_ECHO_STRING:
                return client.echoString(inputString);
            case Constants.OP_ECHO_INT:
                return client.echoInt(inputInt);
            case Constants.OP_ECHO_ARRAY:
                return client.echoArray(inputArray);
            case Constants.OP_ECHO_MAP:
                return client.echoMap(inputMap);
            case Constants.OP_ECHO_OBJECT:
                return client.echoObject(inputObject);
            case Constants.OP_ECHO_BLOB:
                return client.echoBlob(inputBlob);
        }
        return null;
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
                    inputMap.put(i, randomizer.nextInt(10));
                }
                break;
            case Constants.OP_ECHO_OBJECT:
                inputObject = new DataObject();
                inputObject.setCharacter(Constants.ALPHABET.charAt(
                        randomizer.nextInt(Constants.ALPHABET.length())));
                inputObject.setDecimal(randomizer.nextFloat());
                inputObject.setInteger(randomizer.nextInt(10));
                inputObject.setString("RPC_EVALUATION");
                break;
            case Constants.OP_ECHO_BLOB:
                inputBlob = new byte[inputSize];
                randomizer.nextBytes(inputBlob);
        }
    }
}

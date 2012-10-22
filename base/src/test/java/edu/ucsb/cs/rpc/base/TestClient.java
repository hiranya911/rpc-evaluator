package edu.ucsb.cs.rpc.base;

import java.util.Map;
import java.util.Properties;

public class TestClient implements Client {

    private int totalLength = 0;
    private int delay;

    public TestClient(int delay) {
        this.delay = delay;
    }

    public int getTotalLength() {
        return totalLength;
    }

    @Override
    public void init(Properties properties) throws RPCEvaluatorException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public InvocationResult doNothing() {
        return null;
    }

    @Override
    public InvocationResult echoString(String s) {
        delay();
        totalLength += s.length();
        return new InvocationResult(100, 100 + delay);
    }

    @Override
    public InvocationResult echoInt(int i) {
        delay();
        return new InvocationResult(100, 100 + delay, new Exception());
    }

    @Override
    public InvocationResult echoArray(int[] array) {
        return null;
    }

    @Override
    public InvocationResult echoObject(DataObject o) {
        return null;
    }

    @Override
    public InvocationResult echoMap(Map<Integer, Integer> map) {
        return null;
    }

    @Override
    public InvocationResult echoBlob(byte[] blob) {
        return null;
    }

    private void delay() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {

        }
    }
}

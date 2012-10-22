package edu.ucsb.cs.rpc.base;

public class RPCEvaluatorException extends Exception {

    public RPCEvaluatorException(String s) {
        super(s);
    }

    public RPCEvaluatorException(String s, Throwable throwable) {
        super(s, throwable);
    }
}

package edu.ucsb.cs.rpc.base;

public class InvocationResult {

    private long startTime = -1L;
    private long endTime = -1L;
    private boolean failed;
    private Throwable exception;

    public InvocationResult(long startTime, long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public InvocationResult(long startTime, long endTime, Throwable exception) {
        this(startTime, endTime);
        this.failed = true;
        this.exception = exception;
    }

    public long getLatency() {
        return endTime - startTime;
    }

    public boolean isSuccess() {
        return !failed;
    }

    public Throwable getException() {
        return exception;
    }
}

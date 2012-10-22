package edu.ucsb.cs.rpc.base;

import java.util.Collection;

public class RPCEvaluationResult {

    private long duration;
    private long totalInvocations;
    private long successfulInvocations;
    private long failedInvocations;
    private long totalSuccessLatency;
    private long totalFailureLatency;
    private long minSuccessLatency = Long.MAX_VALUE;
    private long maxSuccessLatency = Long.MIN_VALUE;
    private long minFailureLatency = Long.MAX_VALUE;
    private long maxFailureLatency = Long.MIN_VALUE;

    public RPCEvaluationResult(Collection<InvocationResult> results, long start, long end) {
        for (InvocationResult result : results) {
            if (result.isSuccess()) {
                successfulInvocations++;
                totalSuccessLatency += result.getLatency();
                minSuccessLatency = Math.min(minSuccessLatency, result.getLatency());
                maxSuccessLatency = Math.max(maxSuccessLatency, result.getLatency());
            } else {
                failedInvocations++;
                totalFailureLatency += result.getLatency();
                minFailureLatency = Math.min(minFailureLatency, result.getLatency());
                maxFailureLatency = Math.max(maxFailureLatency, result.getLatency());
            }
        }
        duration = end - start;
        totalInvocations = successfulInvocations + failedInvocations;
    }

    public long getDuration() {
        return duration;
    }

    public long getTotalInvocations() {
        return totalInvocations;
    }

    public long getSuccessfulInvocations() {
        return successfulInvocations;
    }

    public long getFailedInvocations() {
        return failedInvocations;
    }

    public long getTotalSuccessLatency() {
        return totalSuccessLatency;
    }

    public long getTotalFailureLatency() {
        return totalFailureLatency;
    }

    public long getMinSuccessLatency() {
        return minSuccessLatency;
    }

    public long getMaxSuccessLatency() {
        return maxSuccessLatency;
    }

    public long getMinFailureLatency() {
        return minFailureLatency;
    }

    public long getMaxFailureLatency() {
        return maxFailureLatency;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Total time elapsed: " + duration + " ms\n")
            .append("Total invocations: " + totalInvocations + "\n")
            .append("Successful invocations: " + successfulInvocations + "\n")
            .append("Failed invocations: " + failedInvocations + "\n")
            .append("Success rate: " + (successfulInvocations * 100 / totalInvocations) + "%\n");

        if (totalSuccessLatency > 0) {
            builder.append("Success throughput: " +
                    (successfulInvocations / (totalSuccessLatency / 1000.0)) + " TPS\n");
        }
        if (totalFailureLatency > 0) {
            builder.append("Failure throughput: " +
                    (failedInvocations / (totalFailureLatency / 1000.0)) + " TPS\n");
        }

        builder.append("Overall throughput: " +
                (totalInvocations / ((totalSuccessLatency + totalFailureLatency) / 1000.0)) + " TPS\n");

        if (successfulInvocations > 0) {
            builder.append("Average success latency: " +
                    (totalSuccessLatency / successfulInvocations) + " ms\n")
                    .append("Minimum success latency: " + minSuccessLatency + " ms\n")
                    .append("Maximum success latency: " + maxSuccessLatency + " ms\n");
        }
        if (failedInvocations > 0) {
            builder.append("Average failure latency: " +
                    (totalFailureLatency / failedInvocations) + " ms\n")
                    .append("Minimum failure latency: " + minFailureLatency + " ms\n")
                    .append("Maximum failure latency: " + maxFailureLatency + " ms\n");
        }
        builder.append("Average overall latency: " +
                ((totalSuccessLatency + totalFailureLatency) / totalInvocations) + " ms\n");
        return builder.toString();
    }

}

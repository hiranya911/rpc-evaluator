package edu.ucsb.cs.rpc.base;

import java.text.DecimalFormat;
import java.util.Collection;

public class RPCEvaluationResult {

    private long duration;
    private long totalInvocations;
    private long successfulInvocations;
    private long failedInvocations;
    private long totalSuccessLatency;
    private long totalFailureLatency;
    private long totalLatency;
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
        totalLatency = totalSuccessLatency + totalFailureLatency;
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

    public long getTotalLatency() {
        return totalLatency;
    }

    private String formatDecimal(double number) {
        DecimalFormat format = new DecimalFormat("#.##");
        return format.format(number);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nTotal time elapsed: ").append(duration).append(" ms\n")
                .append("Total invocations: ").append(totalInvocations).append("\n")
                .append("Successful invocations: ").append(successfulInvocations).append("\n")
                .append("Failed invocations: ").append(failedInvocations).append("\n")
                .append("Success rate: ")
                .append(formatDecimal(successfulInvocations * 100.0 / totalInvocations))
                .append("%\n");

        if (totalSuccessLatency > 0) {
            builder.append("Successful invocation throughput: ")
                    .append(formatDecimal(successfulInvocations / (totalSuccessLatency / 1000.0)))
                    .append(" TPS\n");
        }
        if (totalFailureLatency > 0) {
            builder.append("Failed invocation throughput: ")
                    .append(formatDecimal(failedInvocations / (totalFailureLatency / 1000.0)))
                    .append(" TPS\n");
        }

        builder.append("Overall invocation throughput: ")
                .append(
                        formatDecimal(totalInvocations / (totalLatency / 1000.0)))
                .append(" TPS\n")
                .append("Application throughput: ")
                .append(formatDecimal(totalInvocations / (duration / 1000.0)))
                .append(" TPS\n");

        if (successfulInvocations > 0) {
            builder.append("Successful invocation latency: ")
                    .append(formatDecimal((double) totalSuccessLatency / successfulInvocations))
                    .append(" ms\n")
                    .append("Minimum successful invocation latency: ")
                    .append(minSuccessLatency).append(" ms\n")
                    .append("Maximum success invocation latency: ")
                    .append(maxSuccessLatency).append(" ms\n");
        }
        if (failedInvocations > 0) {
            builder.append("Failed invocation latency: ")
                    .append(formatDecimal((double) totalFailureLatency / failedInvocations))
                    .append(" ms\n")
                    .append("Minimum failed invocation latency: ")
                    .append(minFailureLatency).append(" ms\n")
                    .append("Maximum failed invocation latency: ")
                    .append(maxFailureLatency).append(" ms\n");
        }
        builder.append("Overall invocation latency: ")
                .append(formatDecimal((double) totalLatency / totalInvocations))
                .append(" ms\n")
                .append("Application latency: ")
                .append(formatDecimal((double) duration / totalInvocations))
                .append(" ms\n");
        return builder.toString();
    }

}

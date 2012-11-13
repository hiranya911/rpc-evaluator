package edu.ucsb.cs.rpc.base;

import java.text.DecimalFormat;
import java.util.Collection;

public class RPCEvaluationResult {

    private long duration;
    private long totalInvocations;
    private long successfulInvocations;
    private long failedInvocations;
    private long totalLatency;
    private long minLatency = Long.MAX_VALUE;
    private long maxLatency = Long.MIN_VALUE;
    private double latencyVariance;

    public RPCEvaluationResult(Collection<InvocationResult> results, long start, long end) {
        for (InvocationResult result : results) {
            if (result.isSuccess()) {
                successfulInvocations++;
            } else {
                failedInvocations++;
                result.getException().printStackTrace();
            }
            minLatency = Math.min(minLatency, result.getLatency());
            maxLatency = Math.max(maxLatency, result.getLatency());
            totalLatency += result.getLatency();
        }
        duration = end - start;
        totalInvocations = successfulInvocations + failedInvocations;

        double mean = (double) totalLatency / totalInvocations;
        double temp = 0;
        for (InvocationResult result : results) {
            temp += (mean - result.getLatency())*(mean - result.getLatency());
        }
        latencyVariance = temp/totalInvocations;
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

    public long getTotalLatency() {
        return totalLatency;
    }

    public long getMinLatency() {
        return minLatency;
    }

    public long getMaxLatency() {
        return maxLatency;
    }

    public double getLatencyVariance() {
        return latencyVariance;
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
                .append("%\n")
                .append("Average RPC throughput: ")
                .append(formatDecimal(totalInvocations / (totalLatency / 1000.0)))
                .append(" TPS\n")
                .append("Average application throughput: ")
                .append(formatDecimal(totalInvocations / (duration / 1000.0)))
                .append(" TPS\n")
                .append("Average RPC latency: ")
                .append(formatDecimal((double) totalLatency / totalInvocations))
                .append(" ms\n")
                .append("Minimum RPC latency: ")
                .append(formatDecimal(minLatency))
                .append(" ms\n")
                .append("Maximum RPC latency: ")
                .append(formatDecimal(maxLatency))
                .append(" ms\n")
                .append("RPC latency variance: ")
                .append(formatDecimal(latencyVariance))
                .append("\n")
                .append("RPC latency std dev: ")
                .append(formatDecimal(Math.sqrt(latencyVariance)))
                .append("\n")
                .append("Average Application latency: ")
                .append(formatDecimal((double) duration / totalInvocations))
                .append(" ms\n");
        return builder.toString();
    }

}

package edu.ucsb.cs.rpc.base;

import junit.framework.TestCase;

public class TestRPCEvaluation extends TestCase {

    public void testSuccessfulEvaluation() {
        TestClient client = new TestClient(100);
        RPCEvaluation evaluation = new RPCEvaluation(5, Constants.OP_ECHO_STRING, 10, 0, client);
        RPCEvaluationResult result = evaluation.run();
        assertEquals(5, result.getTotalInvocations());
        assertEquals(5, result.getSuccessfulInvocations());
        assertEquals(0, result.getFailedInvocations());
        assertEquals(5 * 10, client.getTotalLength());
        assertEquals(100 * 5, result.getTotalLatency());
        assertEquals(100, result.getMaxLatency());
        assertEquals(100, result.getMinLatency());
        assertEquals(0.0, result.getLatencyVariance());
        System.out.println(result);
    }

}

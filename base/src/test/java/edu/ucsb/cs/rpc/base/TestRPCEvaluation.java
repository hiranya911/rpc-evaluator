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
        assertEquals(100 * 5, result.getTotalSuccessLatency());
        assertEquals(0, result.getTotalFailureLatency());
        assertEquals(100, result.getMaxSuccessLatency());
        assertEquals(100, result.getMinSuccessLatency());
        System.out.println(result);
    }

}

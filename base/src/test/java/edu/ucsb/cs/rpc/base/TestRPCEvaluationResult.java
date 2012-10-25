package edu.ucsb.cs.rpc.base;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;

public class TestRPCEvaluationResult extends TestCase {

    public void testSingleInvocationResult() {
        Collection<InvocationResult> results = new ArrayList<InvocationResult>();
        results.add(new InvocationResult(1, 10));
        RPCEvaluationResult finalResult = new RPCEvaluationResult(results, 0, 11);
        System.out.println(finalResult.toString());
        assertEquals(11, finalResult.getDuration());
        assertEquals(9, finalResult.getTotalSuccessLatency());
        assertEquals(9, finalResult.getMaxSuccessLatency());
        assertEquals(9, finalResult.getMinSuccessLatency());
        assertEquals(0, finalResult.getTotalFailureLatency());
        assertEquals(Long.MAX_VALUE, finalResult.getMinFailureLatency());
        assertEquals(Long.MIN_VALUE, finalResult.getMaxFailureLatency());
        assertEquals(9, finalResult.getTotalLatency());
    }
}

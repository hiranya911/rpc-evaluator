package edu.ucsb.cs.rpc.base;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collection;

public class TestRPCEvaluationResult extends TestCase {

    public void testSingleInvocationResult() {
        Collection<InvocationResult> results = new ArrayList<InvocationResult>();
        results.add(new InvocationResult(1, 124));
        RPCEvaluationResult finalResult = new RPCEvaluationResult(results, 0, 124);
        System.out.println(finalResult.toString());
    }
}

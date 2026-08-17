package org.bouncycastle.util.test;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TestFailedException extends RuntimeException {
    private TestResult _result;

    public TestFailedException(TestResult testResult) {
        this._result = testResult;
    }

    public TestResult getResult() {
        return this._result;
    }
}

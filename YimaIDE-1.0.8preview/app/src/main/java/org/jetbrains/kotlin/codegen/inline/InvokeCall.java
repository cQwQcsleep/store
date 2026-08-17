package org.jetbrains.kotlin.codegen.inline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
class InvokeCall {
    public final int finallyDepthShift;
    public final FunctionalArgument functionalArgument;

    public InvokeCall(FunctionalArgument functionalArgument, int i) {
        this.functionalArgument = functionalArgument;
        this.finallyDepthShift = i;
    }
}

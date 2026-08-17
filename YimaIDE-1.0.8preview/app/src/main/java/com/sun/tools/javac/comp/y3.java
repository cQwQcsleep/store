package com.sun.tools.javac.comp;

import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class y3 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return new ThisEscapeAnalyzer.ThisRef((ThisEscapeAnalyzer.ExprRef) obj);
    }
}

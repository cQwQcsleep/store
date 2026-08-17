package com.sun.tools.javac.comp;

import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class p5 implements Function {
    public final /* synthetic */ Class b;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (ThisEscapeAnalyzer.Ref) this.b.cast((ThisEscapeAnalyzer.Ref) obj);
    }
}

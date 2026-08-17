package com.sun.tools.javac.comp;

import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class o5 implements Predicate {
    public final /* synthetic */ Class b;

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return this.b.isInstance((ThisEscapeAnalyzer.Ref) obj);
    }
}

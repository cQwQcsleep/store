package com.sun.tools.javac.comp;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class b4 implements Consumer {
    public final /* synthetic */ ThisEscapeAnalyzer.RefSet b;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.b.add((ThisEscapeAnalyzer.ThisRef) obj);
    }
}

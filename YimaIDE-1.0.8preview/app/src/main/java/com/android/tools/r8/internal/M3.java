package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M3 extends AbstractC1751iZ {
    public final Function g;
    public ArrayList h;

    public M3(AbstractC3114yW abstractC3114yW, Function function) {
        super(abstractC3114yW);
        this.g = function;
    }

    @Override // com.android.tools.r8.internal.AbstractC1751iZ
    public final J2 c(String str, Consumer consumer, Object obj) {
        this.h = new ArrayList();
        AbstractC3114yW abstractC3114yW = this.a;
        return new L3(this, abstractC3114yW, abstractC3114yW, consumer, str);
    }
}

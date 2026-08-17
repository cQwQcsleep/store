package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class YI implements InterfaceC0418Cr, Serializable {
    public final int b;

    public YI(int i) {
        this.b = i;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0418Cr
    public final int b() {
        return this.b;
    }

    public final String toString() {
        AbstractC2654t40.a.getClass();
        String string = getClass().getGenericInterfaces()[0].toString();
        if (string.startsWith("kotlin.jvm.functions.")) {
            string = string.substring(21);
        }
        KB.b(string, "renderLambdaToString(...)");
        return string;
    }
}

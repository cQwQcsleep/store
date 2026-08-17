package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.q90, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2409q90 extends AbstractC2323p90 implements InterfaceC0418Cr {
    public final int c;

    public AbstractC2409q90(InterfaceC0952Xg interfaceC0952Xg) {
        super(interfaceC0952Xg);
        this.c = 2;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0418Cr
    public final int b() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.AbstractC2323p90
    public final String toString() {
        if (this.b != null) {
            return super.toString();
        }
        AbstractC2654t40.a.getClass();
        String string = getClass().getGenericInterfaces()[0].toString();
        if (string.startsWith("kotlin.jvm.functions.")) {
            string = string.substring(21);
        }
        KB.b(string, "renderLambdaToString(...)");
        return string;
    }
}

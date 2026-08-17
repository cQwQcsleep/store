package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yE, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3096yE extends RuntimeException {
    public final AbstractC3114yW b;

    public C3096yE(AbstractC3114yW abstractC3114yW, String str) {
        super(str);
        this.b = abstractC3114yW;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        String message = super.getMessage();
        StringBuilder sb = new StringBuilder();
        for (AbstractC3114yW abstractC3114yWC = this.b; abstractC3114yWC != null; abstractC3114yWC = abstractC3114yWC.c()) {
            sb.append("\n  at ");
            sb.append(abstractC3114yWC.b());
            sb.append(": ");
            sb.append(abstractC3114yWC.a());
        }
        return message + sb.toString();
    }

    public C3096yE(AbstractC3114yW abstractC3114yW, RuntimeException runtimeException) {
        super(runtimeException);
        this.b = abstractC3114yW;
    }
}

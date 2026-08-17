package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S50 extends AbstractC2409q90 implements InterfaceC2635sr {
    public Iterator d;
    public C1805j70 e;
    public Iterator f;
    public F80 g;
    public Iterator h;
    public int i;
    public /* synthetic */ Object j;
    public final /* synthetic */ M70 k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public S50(M70 m70, InterfaceC0952Xg interfaceC0952Xg) {
        super(interfaceC0952Xg);
        this.k = m70;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2635sr
    public final Object a(Object obj, Object obj2) {
        S50 s50 = new S50(this.k, (InterfaceC0952Xg) obj2);
        s50.j = (Va0) obj;
        return s50.c(C2028lk0.a);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0038  */
    /* JADX WARN: Code duplicated, block: B:14:0x004b  */
    /* JADX WARN: Code duplicated, block: B:24:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:9:0x0032 A[PHI: r1 r15
      0x0032: PHI (r1v4 java.util.Iterator) = (r1v3 java.util.Iterator), (r1v5 java.util.Iterator) binds: [B:8:0x0023, B:13:0x0049] A[DONT_GENERATE, DONT_INLINE]
      0x0032: PHI (r15v3 com.android.tools.r8.internal.Va0) = (r15v2 com.android.tools.r8.internal.Va0), (r15v4 com.android.tools.r8.internal.Va0) binds: [B:8:0x0023, B:13:0x0049] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0038 -> B:12:0x0045). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x004b -> B:15:0x005a). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:24:0x00cc
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.android.tools.r8.internal.AbstractC2323p90
    public final java.lang.Object c(java.lang.Object r15) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.tools.r8.internal.S50.c(java.lang.Object):java.lang.Object");
    }

    @Override // com.android.tools.r8.internal.AbstractC2323p90
    public final InterfaceC0952Xg a(Va0 va0, Va0 va1) {
        S50 s50 = new S50(this.k, va1);
        s50.j = va0;
        return s50;
    }
}

package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fg0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1508fg0 extends YI implements InterfaceC2635sr {
    public final /* synthetic */ char[] c;
    public final /* synthetic */ boolean d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1508fg0(char[] cArr, boolean z) {
        super(2);
        this.c = cArr;
        this.d = z;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2635sr
    public final Object a(Object obj, Object obj2) {
        CharSequence charSequence = (CharSequence) obj;
        int iIntValue = ((Number) obj2).intValue();
        KB.c(charSequence, "$this$$receiver");
        int iA = AbstractC1679hg0.a(charSequence, this.c, iIntValue, this.d);
        if (iA < 0) {
            return null;
        }
        return new C1491fW(Integer.valueOf(iA), 1);
    }
}

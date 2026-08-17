package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.d40, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1288d40 extends AbstractC2409q90 implements InterfaceC2635sr {
    public int d;
    public int e;
    public /* synthetic */ Object f;
    public final /* synthetic */ String g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1288d40(String str, InterfaceC0952Xg interfaceC0952Xg) {
        super(interfaceC0952Xg);
        this.g = str;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2635sr
    public final Object a(Object obj, Object obj2) {
        C1288d40 c1288d40 = new C1288d40(this.g, (InterfaceC0952Xg) obj2);
        c1288d40.f = (Va0) obj;
        return c1288d40.c(C2028lk0.a);
    }

    @Override // com.android.tools.r8.internal.AbstractC2323p90
    public final Object c(Object obj) throws Throwable {
        Va0 va0;
        int i;
        int iA;
        EnumC1171bh enumC1171bh = EnumC1171bh.b;
        int i2 = this.e;
        if (i2 == 0) {
            AbstractC2579s90.a(obj);
            va0 = (Va0) this.f;
            i = 0;
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            i = this.d;
            va0 = (Va0) this.f;
            AbstractC2579s90.a(obj);
        }
        if (i >= this.g.length() || (iA = AbstractC1679hg0.a((CharSequence) this.g, "android_res/", i, false, 4)) == -1) {
            return C2028lk0.a;
        }
        int i3 = iA + 12;
        int i4 = i3;
        while (i4 < this.g.length() && !Character.isWhitespace(this.g.charAt(i4))) {
            i4++;
        }
        String strSubstring = this.g.substring(i3, i4);
        KB.b(strSubstring, "substring(...)");
        this.f = va0;
        this.d = i4;
        this.e = 1;
        va0.c = strSubstring;
        va0.b = 3;
        va0.d = this;
        EnumC1171bh enumC1171bh2 = EnumC1171bh.b;
        return enumC1171bh;
    }

    @Override // com.android.tools.r8.internal.AbstractC2323p90
    public final InterfaceC0952Xg a(Va0 va0, Va0 va1) {
        C1288d40 c1288d40 = new C1288d40(this.g, va1);
        c1288d40.f = va0;
        return c1288d40;
    }
}

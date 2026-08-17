package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class CD extends ED {
    public final String a;
    public final String b;

    public CD(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    @Override // com.android.tools.r8.internal.ED
    public final String a() {
        return this.a + ':' + this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CD)) {
            return false;
        }
        CD cd = (CD) obj;
        return KB.a((Object) this.a, (Object) cd.a) && KB.a((Object) this.b, (Object) cd.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }
}

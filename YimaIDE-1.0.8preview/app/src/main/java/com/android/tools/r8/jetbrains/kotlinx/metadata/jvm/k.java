package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.internal.KB;
import com.reandroid.arsc.chunk.TypeBlock;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class k extends g {
    public final String a;
    public final String b;

    public k(String str, String str2) {
        KB.c(str, TypeBlock.NAME_name);
        KB.c(str2, "descriptor");
        this.a = str;
        this.b = str2;
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.g
    public final String c() {
        return this.b;
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.g
    public String d() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return KB.a((Object) this.a, (Object) kVar.a) && KB.a((Object) this.b, (Object) kVar.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.g
    public final String toString() {
        return d() + this.b;
    }
}

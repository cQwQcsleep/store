package com.android.tools.r8.jetbrains.kotlinx.metadata.jvm;

import com.android.tools.r8.internal.KB;
import com.reandroid.arsc.chunk.TypeBlock;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class e extends g {
    public final String a;
    public final String b;

    public e(String str, String str2) {
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

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return KB.a((Object) this.a, (Object) eVar.a) && KB.a((Object) this.b, (Object) eVar.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    @Override // com.android.tools.r8.jetbrains.kotlinx.metadata.jvm.g
    public final String toString() {
        return d() + ':' + this.b;
    }
}

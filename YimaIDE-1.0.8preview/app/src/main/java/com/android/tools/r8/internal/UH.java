package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UH extends VH {
    public final String a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UH(String str) {
        super(0);
        KB.c(str, TypeBlock.NAME_name);
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof UH) && KB.a((Object) this.a, (Object) ((UH) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "Class(name=" + this.a + ')';
    }
}

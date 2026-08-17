package com.android.tools.r8.references;

import com.android.tools.r8.internal.C0929Wj;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ClassReference implements TypeReference {
    private final String a;

    private ClassReference(String str) {
        this.a = str;
    }

    public static ClassReference a(String str) {
        return new ClassReference(str);
    }

    @Override // com.android.tools.r8.references.TypeReference
    public ClassReference asClass() {
        return this;
    }

    @Override // com.android.tools.r8.references.TypeReference
    public /* bridge */ /* synthetic */ PrimitiveReference asPrimitive() {
        return super.asPrimitive();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ClassReference) {
            return this.a.equals(((ClassReference) obj).a);
        }
        return false;
    }

    public String getBinaryName() {
        return C0929Wj.f(this.a);
    }

    @Override // com.android.tools.r8.references.TypeReference
    public String getDescriptor() {
        return this.a;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.android.tools.r8.references.TypeReference
    public boolean isClass() {
        return true;
    }

    public String toString() {
        return getDescriptor();
    }
}

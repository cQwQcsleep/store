package com.android.tools.r8.references;

import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.C0929Wj;
import defpackage.x0g;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ArrayReference implements TypeReference {
    static final /* synthetic */ boolean d = true;
    private final int a;
    private final TypeReference b;
    private final String c;

    private ArrayReference(int i, TypeReference typeReference, String str) {
        if (!d && i <= 0) {
            x1f.a();
            throw null;
        }
        this.a = i;
        this.b = typeReference;
        this.c = str;
    }

    public static ArrayReference a(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '[') {
                if (i <= 0) {
                    break;
                }
                return new ArrayReference(i, Reference.typeFromDescriptor(str.substring(i)), str);
            }
        }
        x0g.a("Invalid array type descriptor: ".concat(str));
        return null;
    }

    @Override // com.android.tools.r8.references.TypeReference
    public ArrayReference asArray() {
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
        if (!(obj instanceof ArrayReference)) {
            return false;
        }
        ArrayReference arrayReference = (ArrayReference) obj;
        return this.a == arrayReference.a && this.b.equals(arrayReference.b);
    }

    public TypeReference getBaseType() {
        return this.b;
    }

    @Override // com.android.tools.r8.references.TypeReference
    public String getDescriptor() {
        return this.c;
    }

    public int getDimensions() {
        return this.a;
    }

    public TypeReference getMemberType() {
        return Reference.arrayFromDescriptor(this.c.substring(1));
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.a), this.b);
    }

    @Override // com.android.tools.r8.references.TypeReference
    public boolean isArray() {
        return true;
    }

    public static ArrayReference a(TypeReference typeReference, int i) {
        String descriptor = typeReference.getDescriptor();
        AbstractC0706Nu abstractC0706Nu = C0929Wj.a;
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append('[');
        }
        sb.append(descriptor);
        return new ArrayReference(i, typeReference, sb.toString());
    }
}

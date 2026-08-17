package com.android.tools.r8.references;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FieldReference {
    static final /* synthetic */ boolean d = true;
    private final ClassReference a;
    private final String b;
    private final TypeReference c;

    public FieldReference(ClassReference classReference, String str, TypeReference typeReference) {
        boolean z = d;
        if (!z && classReference == null) {
            x1f.a();
            throw null;
        }
        if (!z && str == null) {
            x1f.a();
            throw null;
        }
        if (!z && typeReference == null) {
            x1f.a();
            throw null;
        }
        this.a = classReference;
        this.b = str;
        this.c = typeReference;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldReference)) {
            return false;
        }
        FieldReference fieldReference = (FieldReference) obj;
        return this.a.equals(fieldReference.a) && this.b.equals(fieldReference.b) && this.c.equals(fieldReference.c);
    }

    public String getFieldName() {
        return this.b;
    }

    public TypeReference getFieldType() {
        return this.c;
    }

    public ClassReference getHolderClass() {
        return this.a;
    }

    public int hashCode() {
        return Objects.hash(this.a, this.b, this.c);
    }

    public String toSourceString() {
        return getFieldType().getTypeName() + " " + getHolderClass().getTypeName() + "." + getFieldName();
    }

    public String toString() {
        return getHolderClass() + getFieldName() + ":" + getFieldType().getDescriptor();
    }
}

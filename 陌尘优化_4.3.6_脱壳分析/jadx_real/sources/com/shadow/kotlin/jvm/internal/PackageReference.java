package com.shadow.kotlin.jvm.internal;

import com.shadow.kotlin.io.CloseableKt;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class PackageReference implements ClassBasedDeclarationContainer {
    private final Class<?> jClass;

    public PackageReference(Class cls) {
        CloseableKt.checkNotNullParameter(cls, "jClass");
        this.jClass = cls;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof PackageReference) {
            if (CloseableKt.areEqual(this.jClass, ((PackageReference) obj).jClass)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.shadow.kotlin.jvm.internal.ClassBasedDeclarationContainer
    public final Class<?> getJClass() {
        return this.jClass;
    }

    public final int hashCode() {
        return this.jClass.hashCode();
    }

    public final String toString() {
        return this.jClass.toString() + " (Kotlin reflection is not available)";
    }
}

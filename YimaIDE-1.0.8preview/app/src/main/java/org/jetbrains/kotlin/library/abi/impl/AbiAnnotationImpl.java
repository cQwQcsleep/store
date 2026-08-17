package org.jetbrains.kotlin.library.abi.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.library.abi.AbiAnnotation;
import org.jetbrains.kotlin.library.abi.AbiQualifiedName;
import org.jetbrains.kotlin.library.abi.ExperimentalLibraryAbiReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007Ê\u0001\u0002\b\u0013¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/library/abi/impl/AbiAnnotationImpl;", "Lorg/jetbrains/kotlin/library/abi/AbiAnnotation;", "qualifiedName", "Lorg/jetbrains/kotlin/library/abi/AbiQualifiedName;", "<init>", "(Lorg/jetbrains/kotlin/library/abi/AbiQualifiedName;)V", "getQualifiedName", "()Lorg/jetbrains/kotlin/library/abi/AbiQualifiedName;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:kotlin-util-klib-abi", "Lorg/jetbrains/kotlin/library/abi/ExperimentalLibraryAbiReader;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@ExperimentalLibraryAbiReader
public final /* data */ class AbiAnnotationImpl implements AbiAnnotation {
    private final AbiQualifiedName qualifiedName;

    public AbiAnnotationImpl(AbiQualifiedName abiQualifiedName) {
        abiQualifiedName.getClass();
        this.qualifiedName = abiQualifiedName;
    }

    public static /* synthetic */ AbiAnnotationImpl copy$default(AbiAnnotationImpl abiAnnotationImpl, AbiQualifiedName abiQualifiedName, int i, Object obj) {
        if ((i & 1) != 0) {
            abiQualifiedName = abiAnnotationImpl.qualifiedName;
        }
        return abiAnnotationImpl.copy(abiQualifiedName);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AbiQualifiedName getQualifiedName() {
        return this.qualifiedName;
    }

    public final AbiAnnotationImpl copy(AbiQualifiedName qualifiedName) {
        qualifiedName.getClass();
        return new AbiAnnotationImpl(qualifiedName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AbiAnnotationImpl) && Intrinsics.areEqual(this.qualifiedName, ((AbiAnnotationImpl) other).qualifiedName);
    }

    @Override // org.jetbrains.kotlin.library.abi.AbiAnnotation
    public AbiQualifiedName getQualifiedName() {
        return this.qualifiedName;
    }

    public int hashCode() {
        return this.qualifiedName.hashCode();
    }

    public String toString() {
        return "AbiAnnotationImpl(qualifiedName=" + this.qualifiedName + ')';
    }
}

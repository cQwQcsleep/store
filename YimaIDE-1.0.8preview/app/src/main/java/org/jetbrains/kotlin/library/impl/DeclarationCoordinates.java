package org.jetbrains.kotlin.library.impl;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/library/impl/DeclarationCoordinates;", "", "offset", "", "size", "(II)V", "getOffset", "()I", "getSize", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class DeclarationCoordinates {
    private final int offset;
    private final int size;

    public DeclarationCoordinates(int i, int i2) {
        this.offset = i;
        this.size = i2;
    }

    public static /* synthetic */ DeclarationCoordinates copy$default(DeclarationCoordinates declarationCoordinates, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = declarationCoordinates.offset;
        }
        if ((i3 & 2) != 0) {
            i2 = declarationCoordinates.size;
        }
        return declarationCoordinates.copy(i, i2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getOffset() {
        return this.offset;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getSize() {
        return this.size;
    }

    public final DeclarationCoordinates copy(int offset, int size) {
        return new DeclarationCoordinates(offset, size);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeclarationCoordinates)) {
            return false;
        }
        DeclarationCoordinates declarationCoordinates = (DeclarationCoordinates) other;
        return this.offset == declarationCoordinates.offset && this.size == declarationCoordinates.size;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getSize() {
        return this.size;
    }

    public int hashCode() {
        return (Integer.hashCode(this.offset) * 31) + Integer.hashCode(this.size);
    }

    public String toString() {
        return "DeclarationCoordinates(offset=" + this.offset + ", size=" + this.size + ')';
    }
}

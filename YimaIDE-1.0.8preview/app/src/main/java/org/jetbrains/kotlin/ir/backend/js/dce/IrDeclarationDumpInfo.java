package org.jetbrains.kotlin.ir.backend.js.dce;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/dce/IrDeclarationDumpInfo;", "", "fqName", "", "type", "size", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "getFqName", "()Ljava/lang/String;", "getType", "getSize", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
final /* data */ class IrDeclarationDumpInfo {
    private final String fqName;
    private final int size;
    private final String type;

    public IrDeclarationDumpInfo(String str, String str2, int i) {
        str.getClass();
        str2.getClass();
        this.fqName = str;
        this.type = str2;
        this.size = i;
    }

    public static /* synthetic */ IrDeclarationDumpInfo copy$default(IrDeclarationDumpInfo irDeclarationDumpInfo, String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = irDeclarationDumpInfo.fqName;
        }
        if ((i2 & 2) != 0) {
            str2 = irDeclarationDumpInfo.type;
        }
        if ((i2 & 4) != 0) {
            i = irDeclarationDumpInfo.size;
        }
        return irDeclarationDumpInfo.copy(str, str2, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFqName() {
        return this.fqName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getSize() {
        return this.size;
    }

    public final IrDeclarationDumpInfo copy(String fqName, String type, int size) {
        fqName.getClass();
        type.getClass();
        return new IrDeclarationDumpInfo(fqName, type, size);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IrDeclarationDumpInfo)) {
            return false;
        }
        IrDeclarationDumpInfo irDeclarationDumpInfo = (IrDeclarationDumpInfo) other;
        return Intrinsics.areEqual(this.fqName, irDeclarationDumpInfo.fqName) && Intrinsics.areEqual(this.type, irDeclarationDumpInfo.type) && this.size == irDeclarationDumpInfo.size;
    }

    public final String getFqName() {
        return this.fqName;
    }

    public final int getSize() {
        return this.size;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return (((this.fqName.hashCode() * 31) + this.type.hashCode()) * 31) + Integer.hashCode(this.size);
    }

    public String toString() {
        return "IrDeclarationDumpInfo(fqName=" + this.fqName + ", type=" + this.type + ", size=" + this.size + ')';
    }
}

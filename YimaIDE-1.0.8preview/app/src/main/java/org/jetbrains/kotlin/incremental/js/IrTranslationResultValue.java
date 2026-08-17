package org.jetbrains.kotlin.incremental.js;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jq\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004J\n\u0010*\u001a\u00020+HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/incremental/js/IrTranslationResultValue;", "", "fileData", "", "types", "signatures", "strings", "declarations", "bodies", "fqn", "fileMetadata", "debugInfo", "fileEntries", "<init>", "([B[B[B[B[B[B[B[B[B[B)V", "getFileData", "()[B", "getTypes", "getSignatures", "getStrings", "getDeclarations", "getBodies", "getFqn", "getFileMetadata", "getDebugInfo", "getFileEntries", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class IrTranslationResultValue {
    private final byte[] bodies;
    private final byte[] debugInfo;
    private final byte[] declarations;
    private final byte[] fileData;
    private final byte[] fileEntries;
    private final byte[] fileMetadata;
    private final byte[] fqn;
    private final byte[] signatures;
    private final byte[] strings;
    private final byte[] types;

    public IrTranslationResultValue(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10) {
        bArr.getClass();
        bArr2.getClass();
        bArr3.getClass();
        bArr4.getClass();
        bArr5.getClass();
        bArr6.getClass();
        bArr7.getClass();
        bArr8.getClass();
        this.fileData = bArr;
        this.types = bArr2;
        this.signatures = bArr3;
        this.strings = bArr4;
        this.declarations = bArr5;
        this.bodies = bArr6;
        this.fqn = bArr7;
        this.fileMetadata = bArr8;
        this.debugInfo = bArr9;
        this.fileEntries = bArr10;
    }

    public static /* synthetic */ IrTranslationResultValue copy$default(IrTranslationResultValue irTranslationResultValue, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = irTranslationResultValue.fileData;
        }
        if ((i & 2) != 0) {
            bArr2 = irTranslationResultValue.types;
        }
        if ((i & 4) != 0) {
            bArr3 = irTranslationResultValue.signatures;
        }
        if ((i & 8) != 0) {
            bArr4 = irTranslationResultValue.strings;
        }
        if ((i & 16) != 0) {
            bArr5 = irTranslationResultValue.declarations;
        }
        if ((i & 32) != 0) {
            bArr6 = irTranslationResultValue.bodies;
        }
        if ((i & 64) != 0) {
            bArr7 = irTranslationResultValue.fqn;
        }
        if ((i & 128) != 0) {
            bArr8 = irTranslationResultValue.fileMetadata;
        }
        if ((i & 256) != 0) {
            bArr9 = irTranslationResultValue.debugInfo;
        }
        if ((i & 512) != 0) {
            bArr10 = irTranslationResultValue.fileEntries;
        }
        byte[] bArr11 = bArr9;
        byte[] bArr12 = bArr10;
        byte[] bArr13 = bArr7;
        byte[] bArr14 = bArr8;
        byte[] bArr15 = bArr5;
        byte[] bArr16 = bArr6;
        return irTranslationResultValue.copy(bArr, bArr2, bArr3, bArr4, bArr15, bArr16, bArr13, bArr14, bArr11, bArr12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final byte[] getFileData() {
        return this.fileData;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final byte[] getFileEntries() {
        return this.fileEntries;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final byte[] getTypes() {
        return this.types;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final byte[] getSignatures() {
        return this.signatures;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final byte[] getStrings() {
        return this.strings;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final byte[] getDeclarations() {
        return this.declarations;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final byte[] getBodies() {
        return this.bodies;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final byte[] getFqn() {
        return this.fqn;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final byte[] getFileMetadata() {
        return this.fileMetadata;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final byte[] getDebugInfo() {
        return this.debugInfo;
    }

    public final IrTranslationResultValue copy(byte[] fileData, byte[] types, byte[] signatures, byte[] strings, byte[] declarations, byte[] bodies, byte[] fqn, byte[] fileMetadata, byte[] debugInfo, byte[] fileEntries) {
        fileData.getClass();
        types.getClass();
        signatures.getClass();
        strings.getClass();
        declarations.getClass();
        bodies.getClass();
        fqn.getClass();
        fileMetadata.getClass();
        return new IrTranslationResultValue(fileData, types, signatures, strings, declarations, bodies, fqn, fileMetadata, debugInfo, fileEntries);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IrTranslationResultValue)) {
            return false;
        }
        IrTranslationResultValue irTranslationResultValue = (IrTranslationResultValue) other;
        return Intrinsics.areEqual(this.fileData, irTranslationResultValue.fileData) && Intrinsics.areEqual(this.types, irTranslationResultValue.types) && Intrinsics.areEqual(this.signatures, irTranslationResultValue.signatures) && Intrinsics.areEqual(this.strings, irTranslationResultValue.strings) && Intrinsics.areEqual(this.declarations, irTranslationResultValue.declarations) && Intrinsics.areEqual(this.bodies, irTranslationResultValue.bodies) && Intrinsics.areEqual(this.fqn, irTranslationResultValue.fqn) && Intrinsics.areEqual(this.fileMetadata, irTranslationResultValue.fileMetadata) && Intrinsics.areEqual(this.debugInfo, irTranslationResultValue.debugInfo) && Intrinsics.areEqual(this.fileEntries, irTranslationResultValue.fileEntries);
    }

    public final byte[] getBodies() {
        return this.bodies;
    }

    public final byte[] getDebugInfo() {
        return this.debugInfo;
    }

    public final byte[] getDeclarations() {
        return this.declarations;
    }

    public final byte[] getFileData() {
        return this.fileData;
    }

    public final byte[] getFileEntries() {
        return this.fileEntries;
    }

    public final byte[] getFileMetadata() {
        return this.fileMetadata;
    }

    public final byte[] getFqn() {
        return this.fqn;
    }

    public final byte[] getSignatures() {
        return this.signatures;
    }

    public final byte[] getStrings() {
        return this.strings;
    }

    public final byte[] getTypes() {
        return this.types;
    }

    public int hashCode() {
        int iHashCode = ((((((((((((((Arrays.hashCode(this.fileData) * 31) + Arrays.hashCode(this.types)) * 31) + Arrays.hashCode(this.signatures)) * 31) + Arrays.hashCode(this.strings)) * 31) + Arrays.hashCode(this.declarations)) * 31) + Arrays.hashCode(this.bodies)) * 31) + Arrays.hashCode(this.fqn)) * 31) + Arrays.hashCode(this.fileMetadata)) * 31;
        byte[] bArr = this.debugInfo;
        int iHashCode2 = (iHashCode + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31;
        byte[] bArr2 = this.fileEntries;
        return iHashCode2 + (bArr2 != null ? Arrays.hashCode(bArr2) : 0);
    }

    public String toString() {
        return "IrTranslationResultValue(fileData=" + Arrays.toString(this.fileData) + ", types=" + Arrays.toString(this.types) + ", signatures=" + Arrays.toString(this.signatures) + ", strings=" + Arrays.toString(this.strings) + ", declarations=" + Arrays.toString(this.declarations) + ", bodies=" + Arrays.toString(this.bodies) + ", fqn=" + Arrays.toString(this.fqn) + ", fileMetadata=" + Arrays.toString(this.fileMetadata) + ", debugInfo=" + Arrays.toString(this.debugInfo) + ", fileEntries=" + Arrays.toString(this.fileEntries) + ')';
    }
}

package org.jetbrains.kotlin.library;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/library/SerializedIrFile;", "", "fileData", "", "fqName", "", "path", "types", "signatures", "strings", "bodies", "declarations", "debugInfo", "backendSpecificMetadata", "fileEntries", "([BLjava/lang/String;Ljava/lang/String;[B[B[B[B[B[B[B[B)V", "getBackendSpecificMetadata", "()[B", "getBodies", "getDebugInfo", "getDeclarations", "getFileData", "getFileEntries", "getFqName", "()Ljava/lang/String;", "getPath", "getSignatures", "getStrings", "getTypes", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SerializedIrFile {
    private final byte[] backendSpecificMetadata;
    private final byte[] bodies;
    private final byte[] debugInfo;
    private final byte[] declarations;
    private final byte[] fileData;
    private final byte[] fileEntries;
    private final String fqName;
    private final String path;
    private final byte[] signatures;
    private final byte[] strings;
    private final byte[] types;

    public SerializedIrFile(byte[] bArr, String str, String str2, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9) {
        bArr.getClass();
        str.getClass();
        str2.getClass();
        bArr2.getClass();
        bArr3.getClass();
        bArr4.getClass();
        bArr5.getClass();
        bArr6.getClass();
        this.fileData = bArr;
        this.fqName = str;
        this.path = str2;
        this.types = bArr2;
        this.signatures = bArr3;
        this.strings = bArr4;
        this.bodies = bArr5;
        this.declarations = bArr6;
        this.debugInfo = bArr7;
        this.backendSpecificMetadata = bArr8;
        this.fileEntries = bArr9;
    }

    public final byte[] getBackendSpecificMetadata() {
        return this.backendSpecificMetadata;
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

    public final String getFqName() {
        return this.fqName;
    }

    public final String getPath() {
        return this.path;
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
}

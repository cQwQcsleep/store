package org.jetbrains.kotlin.library;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/library/SerializedDeclaration;", "", "id", "", "bytes", "", "(I[B)V", "getBytes", "()[B", "getId", "()I", "size", "getSize", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SerializedDeclaration {
    private final byte[] bytes;
    private final int id;
    private final int size;

    public SerializedDeclaration(int i, byte[] bArr) {
        bArr.getClass();
        this.id = i;
        this.bytes = bArr;
        this.size = bArr.length;
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    public final int getId() {
        return this.id;
    }

    public final int getSize() {
        return this.size;
    }
}

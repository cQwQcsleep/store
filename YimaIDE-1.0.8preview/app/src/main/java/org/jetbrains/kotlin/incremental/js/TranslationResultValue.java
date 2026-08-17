package org.jetbrains.kotlin.incremental.js;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/incremental/js/TranslationResultValue;", "", "metadata", "", "binaryAst", "inlineData", "<init>", "([B[B[B)V", "getMetadata", "()[B", "getBinaryAst", "getInlineData", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TranslationResultValue {
    private final byte[] binaryAst;
    private final byte[] inlineData;
    private final byte[] metadata;

    public TranslationResultValue(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        bArr.getClass();
        bArr2.getClass();
        bArr3.getClass();
        this.metadata = bArr;
        this.binaryAst = bArr2;
        this.inlineData = bArr3;
    }

    public static /* synthetic */ TranslationResultValue copy$default(TranslationResultValue translationResultValue, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = translationResultValue.metadata;
        }
        if ((i & 2) != 0) {
            bArr2 = translationResultValue.binaryAst;
        }
        if ((i & 4) != 0) {
            bArr3 = translationResultValue.inlineData;
        }
        return translationResultValue.copy(bArr, bArr2, bArr3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final byte[] getMetadata() {
        return this.metadata;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final byte[] getBinaryAst() {
        return this.binaryAst;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final byte[] getInlineData() {
        return this.inlineData;
    }

    public final TranslationResultValue copy(byte[] metadata, byte[] binaryAst, byte[] inlineData) {
        metadata.getClass();
        binaryAst.getClass();
        inlineData.getClass();
        return new TranslationResultValue(metadata, binaryAst, inlineData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TranslationResultValue)) {
            return false;
        }
        TranslationResultValue translationResultValue = (TranslationResultValue) other;
        return Intrinsics.areEqual(this.metadata, translationResultValue.metadata) && Intrinsics.areEqual(this.binaryAst, translationResultValue.binaryAst) && Intrinsics.areEqual(this.inlineData, translationResultValue.inlineData);
    }

    public final byte[] getBinaryAst() {
        return this.binaryAst;
    }

    public final byte[] getInlineData() {
        return this.inlineData;
    }

    public final byte[] getMetadata() {
        return this.metadata;
    }

    public int hashCode() {
        return (((Arrays.hashCode(this.metadata) * 31) + Arrays.hashCode(this.binaryAst)) * 31) + Arrays.hashCode(this.inlineData);
    }

    public String toString() {
        return "TranslationResultValue(metadata=" + Arrays.toString(this.metadata) + ", binaryAst=" + Arrays.toString(this.binaryAst) + ", inlineData=" + Arrays.toString(this.inlineData) + ')';
    }
}

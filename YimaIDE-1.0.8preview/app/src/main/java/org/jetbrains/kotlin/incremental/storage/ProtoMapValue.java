package org.jetbrains.kotlin.incremental.storage;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ2\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/ProtoMapValue;", "", "isPackageFacade", "", "bytes", "", "strings", "", "", "<init>", "(Z[B[Ljava/lang/String;)V", "()Z", "getBytes", "()[B", "getStrings", "()[Ljava/lang/String;", "[Ljava/lang/String;", "component1", "component2", "component3", "copy", "(Z[B[Ljava/lang/String;)Lorg/jetbrains/kotlin/incremental/storage/ProtoMapValue;", "equals", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ProtoMapValue {
    private final byte[] bytes;
    private final boolean isPackageFacade;
    private final String[] strings;

    public ProtoMapValue(boolean z, byte[] bArr, String[] strArr) {
        bArr.getClass();
        strArr.getClass();
        this.isPackageFacade = z;
        this.bytes = bArr;
        this.strings = strArr;
    }

    public static /* synthetic */ ProtoMapValue copy$default(ProtoMapValue protoMapValue, boolean z, byte[] bArr, String[] strArr, int i, Object obj) {
        if ((i & 1) != 0) {
            z = protoMapValue.isPackageFacade;
        }
        if ((i & 2) != 0) {
            bArr = protoMapValue.bytes;
        }
        if ((i & 4) != 0) {
            strArr = protoMapValue.strings;
        }
        return protoMapValue.copy(z, bArr, strArr);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsPackageFacade() {
        return this.isPackageFacade;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final byte[] getBytes() {
        return this.bytes;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String[] getStrings() {
        return this.strings;
    }

    public final ProtoMapValue copy(boolean isPackageFacade, byte[] bytes, String[] strings) {
        bytes.getClass();
        strings.getClass();
        return new ProtoMapValue(isPackageFacade, bytes, strings);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProtoMapValue)) {
            return false;
        }
        ProtoMapValue protoMapValue = (ProtoMapValue) other;
        return this.isPackageFacade == protoMapValue.isPackageFacade && Intrinsics.areEqual(this.bytes, protoMapValue.bytes) && Intrinsics.areEqual(this.strings, protoMapValue.strings);
    }

    public final byte[] getBytes() {
        return this.bytes;
    }

    public final String[] getStrings() {
        return this.strings;
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.isPackageFacade) * 31) + Arrays.hashCode(this.bytes)) * 31) + Arrays.hashCode(this.strings);
    }

    public final boolean isPackageFacade() {
        return this.isPackageFacade;
    }

    public String toString() {
        return "ProtoMapValue(isPackageFacade=" + this.isPackageFacade + ", bytes=" + Arrays.toString(this.bytes) + ", strings=" + Arrays.toString(this.strings) + ')';
    }
}

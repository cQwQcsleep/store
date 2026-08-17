package org.jetbrains.kotlin.load.kotlin.incremental.components;

import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ(\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/incremental/components/JvmPackagePartProto;", "Ljava/io/Serializable;", "data", "", "strings", "", "", "<init>", "([B[Ljava/lang/String;)V", "getData", "()[B", "getStrings", "()[Ljava/lang/String;", "[Ljava/lang/String;", "component1", "component2", "copy", "([B[Ljava/lang/String;)Lorg/jetbrains/kotlin/load/kotlin/incremental/components/JvmPackagePartProto;", "equals", "", "other", "", "hashCode", "", "toString", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class JvmPackagePartProto implements Serializable {
    private final byte[] data;
    private final String[] strings;

    public JvmPackagePartProto(byte[] bArr, String[] strArr) {
        bArr.getClass();
        strArr.getClass();
        this.data = bArr;
        this.strings = strArr;
    }

    public static /* synthetic */ JvmPackagePartProto copy$default(JvmPackagePartProto jvmPackagePartProto, byte[] bArr, String[] strArr, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = jvmPackagePartProto.data;
        }
        if ((i & 2) != 0) {
            strArr = jvmPackagePartProto.strings;
        }
        return jvmPackagePartProto.copy(bArr, strArr);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final byte[] getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String[] getStrings() {
        return this.strings;
    }

    public final JvmPackagePartProto copy(byte[] data, String[] strings) {
        data.getClass();
        strings.getClass();
        return new JvmPackagePartProto(data, strings);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JvmPackagePartProto)) {
            return false;
        }
        JvmPackagePartProto jvmPackagePartProto = (JvmPackagePartProto) other;
        return Intrinsics.areEqual(this.data, jvmPackagePartProto.data) && Intrinsics.areEqual(this.strings, jvmPackagePartProto.strings);
    }

    public final byte[] getData() {
        return this.data;
    }

    public final String[] getStrings() {
        return this.strings;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.data) * 31) + Arrays.hashCode(this.strings);
    }

    public String toString() {
        return "JvmPackagePartProto(data=" + Arrays.toString(this.data) + ", strings=" + Arrays.toString(this.strings) + ')';
    }
}

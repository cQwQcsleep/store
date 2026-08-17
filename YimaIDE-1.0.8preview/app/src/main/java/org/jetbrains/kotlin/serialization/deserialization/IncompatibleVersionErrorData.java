package org.jetbrains.kotlin.serialization.deserialization;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0013\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u0014\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u0015\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u0016\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\bHÆ\u0003JF\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00002\b\b\u0002\u0010\u0005\u001a\u00028\u00002\b\b\u0002\u0010\u0006\u001a\u00028\u00002\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\bHÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0006\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/IncompatibleVersionErrorData;", "T", "", "actualVersion", "compilerVersion", "languageVersion", "expectedVersion", "filePath", "", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;)V", "getActualVersion", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getCompilerVersion", "getLanguageVersion", "getExpectedVersion", "getFilePath", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;)Lorg/jetbrains/kotlin/serialization/deserialization/IncompatibleVersionErrorData;", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final /* data */ class IncompatibleVersionErrorData<T> {
    private final T actualVersion;
    private final T compilerVersion;
    private final T expectedVersion;
    private final String filePath;
    private final T languageVersion;

    public IncompatibleVersionErrorData(T t, T t2, T t3, T t4, String str) {
        str.getClass();
        this.actualVersion = t;
        this.compilerVersion = t2;
        this.languageVersion = t3;
        this.expectedVersion = t4;
        this.filePath = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IncompatibleVersionErrorData copy$default(IncompatibleVersionErrorData incompatibleVersionErrorData, Object obj, Object obj2, Object obj3, Object obj4, String str, int i, Object obj5) {
        if ((i & 1) != 0) {
            obj = incompatibleVersionErrorData.actualVersion;
        }
        if ((i & 2) != 0) {
            obj2 = incompatibleVersionErrorData.compilerVersion;
        }
        if ((i & 4) != 0) {
            obj3 = incompatibleVersionErrorData.languageVersion;
        }
        if ((i & 8) != 0) {
            obj4 = incompatibleVersionErrorData.expectedVersion;
        }
        if ((i & 16) != 0) {
            str = incompatibleVersionErrorData.filePath;
        }
        String str2 = str;
        Object obj6 = obj3;
        return incompatibleVersionErrorData.copy(obj, obj2, obj6, obj4, str2);
    }

    public final T component1() {
        return this.actualVersion;
    }

    public final T component2() {
        return this.compilerVersion;
    }

    public final T component3() {
        return this.languageVersion;
    }

    public final T component4() {
        return this.expectedVersion;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getFilePath() {
        return this.filePath;
    }

    public final IncompatibleVersionErrorData<T> copy(T actualVersion, T compilerVersion, T languageVersion, T expectedVersion, String filePath) {
        filePath.getClass();
        return new IncompatibleVersionErrorData<>(actualVersion, compilerVersion, languageVersion, expectedVersion, filePath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IncompatibleVersionErrorData)) {
            return false;
        }
        IncompatibleVersionErrorData incompatibleVersionErrorData = (IncompatibleVersionErrorData) other;
        return Intrinsics.areEqual(this.actualVersion, incompatibleVersionErrorData.actualVersion) && Intrinsics.areEqual(this.compilerVersion, incompatibleVersionErrorData.compilerVersion) && Intrinsics.areEqual(this.languageVersion, incompatibleVersionErrorData.languageVersion) && Intrinsics.areEqual(this.expectedVersion, incompatibleVersionErrorData.expectedVersion) && Intrinsics.areEqual(this.filePath, incompatibleVersionErrorData.filePath);
    }

    public final T getActualVersion() {
        return this.actualVersion;
    }

    public final T getCompilerVersion() {
        return this.compilerVersion;
    }

    public final T getExpectedVersion() {
        return this.expectedVersion;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final T getLanguageVersion() {
        return this.languageVersion;
    }

    public int hashCode() {
        T t = this.actualVersion;
        int iHashCode = (t == null ? 0 : t.hashCode()) * 31;
        T t2 = this.compilerVersion;
        int iHashCode2 = (iHashCode + (t2 == null ? 0 : t2.hashCode())) * 31;
        T t3 = this.languageVersion;
        int iHashCode3 = (iHashCode2 + (t3 == null ? 0 : t3.hashCode())) * 31;
        T t4 = this.expectedVersion;
        return ((iHashCode3 + (t4 != null ? t4.hashCode() : 0)) * 31) + this.filePath.hashCode();
    }

    public String toString() {
        return "IncompatibleVersionErrorData(actualVersion=" + this.actualVersion + ", compilerVersion=" + this.compilerVersion + ", languageVersion=" + this.languageVersion + ", expectedVersion=" + this.expectedVersion + ", filePath=" + this.filePath + ')';
    }
}

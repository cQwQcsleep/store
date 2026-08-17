package org.jetbrains.kotlin.utils.kapt;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/utils/kapt/MemoryLeak;", "", "className", "", "fieldName", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "getFieldName", "getDescription", "equals", "", "other", "hashCode", "", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class MemoryLeak {
    private final String className;
    private final String description;
    private final String fieldName;

    public MemoryLeak(String str, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.className = str;
        this.fieldName = str2;
        this.description = str3;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(MemoryLeak.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        MemoryLeak memoryLeak = (MemoryLeak) other;
        return Intrinsics.areEqual(this.className, memoryLeak.className) && Intrinsics.areEqual(this.fieldName, memoryLeak.fieldName);
    }

    public final String getClassName() {
        return this.className;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getFieldName() {
        return this.fieldName;
    }

    public int hashCode() {
        return (this.className.hashCode() * 31) + this.fieldName.hashCode();
    }
}

package org.jetbrains.kotlin.incremental.storage;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/ICClassesAttributes;", "", "isSealed", "", "<init>", "(Z)V", "()Z", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ICClassesAttributes {
    private final boolean isSealed;

    public ICClassesAttributes(boolean z) {
        this.isSealed = z;
    }

    public static /* synthetic */ ICClassesAttributes copy$default(ICClassesAttributes iCClassesAttributes, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = iCClassesAttributes.isSealed;
        }
        return iCClassesAttributes.copy(z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsSealed() {
        return this.isSealed;
    }

    public final ICClassesAttributes copy(boolean isSealed) {
        return new ICClassesAttributes(isSealed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ICClassesAttributes) && this.isSealed == ((ICClassesAttributes) other).isSealed;
    }

    public int hashCode() {
        return Boolean.hashCode(this.isSealed);
    }

    public final boolean isSealed() {
        return this.isSealed;
    }

    public String toString() {
        return "ICClassesAttributes(isSealed=" + this.isSealed + ')';
    }
}

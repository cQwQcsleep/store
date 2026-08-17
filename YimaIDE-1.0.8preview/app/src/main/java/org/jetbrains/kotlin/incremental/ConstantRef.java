package org.jetbrains.kotlin.incremental;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000b¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/incremental/ConstantRef;", "", "owner", "", "name", "constType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getOwner", "()Ljava/lang/String;", "setOwner", "(Ljava/lang/String;)V", "getName", "setName", "getConstType", "setConstType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ConstantRef {
    private String constType;
    private String name;
    private String owner;

    public ConstantRef(String str, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.owner = str;
        this.name = str2;
        this.constType = str3;
    }

    public static /* synthetic */ ConstantRef copy$default(ConstantRef constantRef, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = constantRef.owner;
        }
        if ((i & 2) != 0) {
            str2 = constantRef.name;
        }
        if ((i & 4) != 0) {
            str3 = constantRef.constType;
        }
        return constantRef.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOwner() {
        return this.owner;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getConstType() {
        return this.constType;
    }

    public final ConstantRef copy(String owner, String name, String constType) {
        owner.getClass();
        name.getClass();
        constType.getClass();
        return new ConstantRef(owner, name, constType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConstantRef)) {
            return false;
        }
        ConstantRef constantRef = (ConstantRef) other;
        return Intrinsics.areEqual(this.owner, constantRef.owner) && Intrinsics.areEqual(this.name, constantRef.name) && Intrinsics.areEqual(this.constType, constantRef.constType);
    }

    public final String getConstType() {
        return this.constType;
    }

    public final String getName() {
        return this.name;
    }

    public final String getOwner() {
        return this.owner;
    }

    public int hashCode() {
        return (((this.owner.hashCode() * 31) + this.name.hashCode()) * 31) + this.constType.hashCode();
    }

    public final void setConstType(String str) {
        str.getClass();
        this.constType = str;
    }

    public final void setName(String str) {
        str.getClass();
        this.name = str;
    }

    public final void setOwner(String str) {
        str.getClass();
        this.owner = str;
    }

    public String toString() {
        return "ConstantRef(owner=" + this.owner + ", name=" + this.name + ", constType=" + this.constType + ')';
    }
}

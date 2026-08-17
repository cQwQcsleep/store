package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/MethodId;", Argument.Delimiters.none, "ownerInternalName", Argument.Delimiters.none, "method", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/commons/Method;)V", "getOwnerInternalName", "()Ljava/lang/String;", "getMethod", "()Lorg/jetbrains/org/objectweb/asm/commons/Method;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class MethodId {
    private final Method method;
    private final String ownerInternalName;

    public MethodId(String str, Method method) {
        str.getClass();
        method.getClass();
        this.ownerInternalName = str;
        this.method = method;
    }

    public static /* synthetic */ MethodId copy$default(MethodId methodId, String str, Method method, int i, Object obj) {
        if ((i & 1) != 0) {
            str = methodId.ownerInternalName;
        }
        if ((i & 2) != 0) {
            method = methodId.method;
        }
        return methodId.copy(str, method);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOwnerInternalName() {
        return this.ownerInternalName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Method getMethod() {
        return this.method;
    }

    public final MethodId copy(String ownerInternalName, Method method) {
        ownerInternalName.getClass();
        method.getClass();
        return new MethodId(ownerInternalName, method);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MethodId)) {
            return false;
        }
        MethodId methodId = (MethodId) other;
        return Intrinsics.areEqual(this.ownerInternalName, methodId.ownerInternalName) && Intrinsics.areEqual(this.method, methodId.method);
    }

    public final Method getMethod() {
        return this.method;
    }

    public final String getOwnerInternalName() {
        return this.ownerInternalName;
    }

    public int hashCode() {
        return (this.ownerInternalName.hashCode() * 31) + this.method.hashCode();
    }

    public String toString() {
        return "MethodId(ownerInternalName=" + this.ownerInternalName + ", method=" + this.method + ')';
    }
}

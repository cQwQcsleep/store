package org.jetbrains.kotlin.load.java.typeEnhancement;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\f\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\tJ\t\u0010\r\u001a\u00020\u0005HÆ\u0003J(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/load/java/typeEnhancement/WithMigrationStatus;", "T", "", "qualifier", "isForWarningOnly", "", "<init>", "(Ljava/lang/Object;Z)V", "getQualifier", "()Ljava/lang/Object;", "Ljava/lang/Object;", "()Z", "component1", "component2", "copy", "(Ljava/lang/Object;Z)Lorg/jetbrains/kotlin/load/java/typeEnhancement/WithMigrationStatus;", "equals", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class WithMigrationStatus<T> {
    private final boolean isForWarningOnly;
    private final T qualifier;

    public /* synthetic */ WithMigrationStatus(Object obj, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? false : z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WithMigrationStatus copy$default(WithMigrationStatus withMigrationStatus, Object obj, boolean z, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = withMigrationStatus.qualifier;
        }
        if ((i & 2) != 0) {
            z = withMigrationStatus.isForWarningOnly;
        }
        return withMigrationStatus.copy(obj, z);
    }

    public final T component1() {
        return this.qualifier;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsForWarningOnly() {
        return this.isForWarningOnly;
    }

    public final WithMigrationStatus<T> copy(T qualifier, boolean isForWarningOnly) {
        return new WithMigrationStatus<>(qualifier, isForWarningOnly);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WithMigrationStatus)) {
            return false;
        }
        WithMigrationStatus withMigrationStatus = (WithMigrationStatus) other;
        return Intrinsics.areEqual(this.qualifier, withMigrationStatus.qualifier) && this.isForWarningOnly == withMigrationStatus.isForWarningOnly;
    }

    public final T getQualifier() {
        return this.qualifier;
    }

    public int hashCode() {
        T t = this.qualifier;
        return ((t == null ? 0 : t.hashCode()) * 31) + Boolean.hashCode(this.isForWarningOnly);
    }

    public final boolean isForWarningOnly() {
        return this.isForWarningOnly;
    }

    public String toString() {
        return "WithMigrationStatus(qualifier=" + this.qualifier + ", isForWarningOnly=" + this.isForWarningOnly + ')';
    }

    public WithMigrationStatus(T t, boolean z) {
        this.qualifier = t;
        this.isForWarningOnly = z;
    }
}

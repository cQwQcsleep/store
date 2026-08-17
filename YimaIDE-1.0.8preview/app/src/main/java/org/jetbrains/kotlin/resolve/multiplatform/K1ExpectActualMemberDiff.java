package org.jetbrains.kotlin.resolve.multiplatform;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003:\u0001\u001cB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u000e\u0010\u0011\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\rJ\u000e\u0010\u0012\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\rJ8\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00028\u00002\b\b\u0002\u0010\u0007\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\r¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualMemberDiff;", "M", "C", "", "kind", "Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualMemberDiff$Kind;", "actualMember", "expectClass", "<init>", "(Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualMemberDiff$Kind;Ljava/lang/Object;Ljava/lang/Object;)V", "getKind", "()Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualMemberDiff$Kind;", "getActualMember", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getExpectClass", "component1", "component2", "component3", "copy", "(Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualMemberDiff$Kind;Ljava/lang/Object;Ljava/lang/Object;)Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualMemberDiff;", "equals", "", "other", "hashCode", "", "toString", "", "Kind", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final /* data */ class K1ExpectActualMemberDiff<M, C> {
    private final M actualMember;
    private final C expectClass;
    private final Kind kind;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/resolve/multiplatform/K1ExpectActualMemberDiff$Kind;", "", "rawMessage", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getRawMessage", "()Ljava/lang/String;", "ReturnTypeChangedInOverride", "ModalityChangedInOverride", "VisibilityChangedInOverride", "SetterVisibilityChangedInOverride", "ParameterNameChangedInOverride", "PropertyKindChangedInOverride", "LateinitChangedInOverride", "VarargChangedInOverride", "TypeParameterNamesChangedInOverride", "Unknown", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public enum Kind {
        ReturnTypeChangedInOverride("{0}: the return type of this member must be the same in the expect class and the actual class. This error happens because the expect class ''{1}'' is non-final"),
        ModalityChangedInOverride("{0}: the modality of this member must be the same in the expect class and the actual class. This error happens because the expect class ''{1}'' is non-final"),
        VisibilityChangedInOverride("{0}: the visibility of this member must be the same in the expect class and the actual class. This error happens because the expect class ''{1}'' is non-final"),
        SetterVisibilityChangedInOverride("{0}: the setter visibility of this member must be the same in the expect class and the actual class. This error happens because the expect class ''{1}'' is non-final"),
        ParameterNameChangedInOverride("{0}: the parameter names of this member must be the same in the expect class and the actual class. This error happens because the expect class ''{1}'' is non-final"),
        PropertyKindChangedInOverride("{0}: the property kind (val vs var) of this member must be the same in the expect class and the actual class. This error happens because the expect class ''{1}'' is non-final"),
        LateinitChangedInOverride("{0}: the property modifiers (lateinit) of this member must be the same in the expect class and the actual class. This error happens because the expect class ''{1}'' is non-final"),
        VarargChangedInOverride("{0}: the parameter modifiers (vararg) of this member must be the same in the expect class and the actual class. This error happens because the expect class ''{1}'' is non-final"),
        TypeParameterNamesChangedInOverride("{0}: the type parameter names of this member must be the same in the expect class and the actual class. This error happens because the expect class ''{1}'' is non-final"),
        Unknown("{0}: normally, this error should never happen. Please report to https://kotl.in/issue. This error happens because the expect class ''{1}'' is non-final");

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final String rawMessage;

        Kind(String str) {
            this.rawMessage = str;
        }

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }

        public final String getRawMessage() {
            return this.rawMessage;
        }
    }

    public K1ExpectActualMemberDiff(Kind kind, M m, C c) {
        kind.getClass();
        this.kind = kind;
        this.actualMember = m;
        this.expectClass = c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ K1ExpectActualMemberDiff copy$default(K1ExpectActualMemberDiff k1ExpectActualMemberDiff, Kind kind, Object obj, Object obj2, int i, Object obj3) {
        if ((i & 1) != 0) {
            kind = k1ExpectActualMemberDiff.kind;
        }
        if ((i & 2) != 0) {
            obj = k1ExpectActualMemberDiff.actualMember;
        }
        if ((i & 4) != 0) {
            obj2 = k1ExpectActualMemberDiff.expectClass;
        }
        return k1ExpectActualMemberDiff.copy(kind, obj, obj2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Kind getKind() {
        return this.kind;
    }

    public final M component2() {
        return this.actualMember;
    }

    public final C component3() {
        return this.expectClass;
    }

    public final K1ExpectActualMemberDiff<M, C> copy(Kind kind, M actualMember, C expectClass) {
        kind.getClass();
        return new K1ExpectActualMemberDiff<>(kind, actualMember, expectClass);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof K1ExpectActualMemberDiff)) {
            return false;
        }
        K1ExpectActualMemberDiff k1ExpectActualMemberDiff = (K1ExpectActualMemberDiff) other;
        return this.kind == k1ExpectActualMemberDiff.kind && Intrinsics.areEqual(this.actualMember, k1ExpectActualMemberDiff.actualMember) && Intrinsics.areEqual(this.expectClass, k1ExpectActualMemberDiff.expectClass);
    }

    public final M getActualMember() {
        return this.actualMember;
    }

    public final C getExpectClass() {
        return this.expectClass;
    }

    public final Kind getKind() {
        return this.kind;
    }

    public int hashCode() {
        int iHashCode = this.kind.hashCode() * 31;
        M m = this.actualMember;
        int iHashCode2 = (iHashCode + (m == null ? 0 : m.hashCode())) * 31;
        C c = this.expectClass;
        return iHashCode2 + (c != null ? c.hashCode() : 0);
    }

    public String toString() {
        return "K1ExpectActualMemberDiff(kind=" + this.kind + ", actualMember=" + this.actualMember + ", expectClass=" + this.expectClass + ')';
    }
}

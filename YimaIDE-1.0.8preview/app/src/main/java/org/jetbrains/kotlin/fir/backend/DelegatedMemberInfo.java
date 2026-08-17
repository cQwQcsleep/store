package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrOverridableDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\r\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\r\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J9\u0010\u0016\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\f\b\u0002\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/DelegatedMemberInfo;", Argument.Delimiters.none, "delegatedMember", "Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;", "delegateTargetFromBaseType", "delegateField", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;Lorg/jetbrains/kotlin/ir/declarations/IrField;Lorg/jetbrains/kotlin/ir/declarations/IrClass;)V", "getDelegatedMember", "()Lorg/jetbrains/kotlin/ir/declarations/IrOverridableDeclaration;", "getDelegateTargetFromBaseType", "getDelegateField", "()Lorg/jetbrains/kotlin/ir/declarations/IrField;", "getParent", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "component1", "component2", "component3", "component4", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final /* data */ class DelegatedMemberInfo {
    private final IrField delegateField;
    private final IrOverridableDeclaration<?> delegateTargetFromBaseType;
    private final IrOverridableDeclaration<?> delegatedMember;
    private final IrClass parent;

    public DelegatedMemberInfo(IrOverridableDeclaration<?> irOverridableDeclaration, IrOverridableDeclaration<?> irOverridableDeclaration2, IrField irField, IrClass irClass) {
        irOverridableDeclaration.getClass();
        irOverridableDeclaration2.getClass();
        irField.getClass();
        irClass.getClass();
        this.delegatedMember = irOverridableDeclaration;
        this.delegateTargetFromBaseType = irOverridableDeclaration2;
        this.delegateField = irField;
        this.parent = irClass;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DelegatedMemberInfo copy$default(DelegatedMemberInfo delegatedMemberInfo, IrOverridableDeclaration irOverridableDeclaration, IrOverridableDeclaration irOverridableDeclaration2, IrField irField, IrClass irClass, int i, Object obj) {
        if ((i & 1) != 0) {
            irOverridableDeclaration = delegatedMemberInfo.delegatedMember;
        }
        if ((i & 2) != 0) {
            irOverridableDeclaration2 = delegatedMemberInfo.delegateTargetFromBaseType;
        }
        if ((i & 4) != 0) {
            irField = delegatedMemberInfo.delegateField;
        }
        if ((i & 8) != 0) {
            irClass = delegatedMemberInfo.parent;
        }
        return delegatedMemberInfo.copy(irOverridableDeclaration, irOverridableDeclaration2, irField, irClass);
    }

    public final IrOverridableDeclaration<?> component1() {
        return this.delegatedMember;
    }

    public final IrOverridableDeclaration<?> component2() {
        return this.delegateTargetFromBaseType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final IrField getDelegateField() {
        return this.delegateField;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final IrClass getParent() {
        return this.parent;
    }

    public final DelegatedMemberInfo copy(IrOverridableDeclaration<?> delegatedMember, IrOverridableDeclaration<?> delegateTargetFromBaseType, IrField delegateField, IrClass parent) {
        delegatedMember.getClass();
        delegateTargetFromBaseType.getClass();
        delegateField.getClass();
        parent.getClass();
        return new DelegatedMemberInfo(delegatedMember, delegateTargetFromBaseType, delegateField, parent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DelegatedMemberInfo)) {
            return false;
        }
        DelegatedMemberInfo delegatedMemberInfo = (DelegatedMemberInfo) other;
        return Intrinsics.areEqual(this.delegatedMember, delegatedMemberInfo.delegatedMember) && Intrinsics.areEqual(this.delegateTargetFromBaseType, delegatedMemberInfo.delegateTargetFromBaseType) && Intrinsics.areEqual(this.delegateField, delegatedMemberInfo.delegateField) && Intrinsics.areEqual(this.parent, delegatedMemberInfo.parent);
    }

    public final IrField getDelegateField() {
        return this.delegateField;
    }

    public final IrOverridableDeclaration<?> getDelegateTargetFromBaseType() {
        return this.delegateTargetFromBaseType;
    }

    public final IrOverridableDeclaration<?> getDelegatedMember() {
        return this.delegatedMember;
    }

    public final IrClass getParent() {
        return this.parent;
    }

    public int hashCode() {
        return (((((this.delegatedMember.hashCode() * 31) + this.delegateTargetFromBaseType.hashCode()) * 31) + this.delegateField.hashCode()) * 31) + this.parent.hashCode();
    }

    public String toString() {
        return "DelegatedMemberInfo(delegatedMember=" + this.delegatedMember + ", delegateTargetFromBaseType=" + this.delegateTargetFromBaseType + ", delegateField=" + this.delegateField + ", parent=" + this.parent + ')';
    }
}

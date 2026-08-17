package org.jetbrains.kotlin.ir.util;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0001H\u0016J\b\u0010\u0011\u001a\u00020\u0001H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0014\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/ir/util/IdSignature$SpecialFakeOverrideSignature;", "Lorg/jetbrains/kotlin/ir/util/IdSignature;", "memberSignature", "overriddenSignatures", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/util/IdSignature;Ljava/util/List;)V", "getMemberSignature", "()Lorg/jetbrains/kotlin/ir/util/IdSignature;", "getOverriddenSignatures", "()Ljava/util/List;", "isPubliclyVisible", "", "()Z", "asPublic", "Lorg/jetbrains/kotlin/ir/util/IdSignature$CommonSignature;", "topLevelSignature", "nearestPublicSig", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "equals", "other", "", "hashCode", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class IdSignature$SpecialFakeOverrideSignature extends IdSignature {
    private final int hashCode;
    private final IdSignature memberSignature;
    private final List<IdSignature> overriddenSignatures;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IdSignature$SpecialFakeOverrideSignature(IdSignature idSignature, List<? extends IdSignature> list) {
        super((DefaultConstructorMarker) null);
        idSignature.getClass();
        list.getClass();
        this.memberSignature = idSignature;
        this.overriddenSignatures = list;
        this.hashCode = (idSignature.hashCode() * 31) + list.hashCode();
    }

    public IdSignature.CommonSignature asPublic() {
        return this.memberSignature.asPublic();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(IdSignature$SpecialFakeOverrideSignature.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        IdSignature$SpecialFakeOverrideSignature idSignature$SpecialFakeOverrideSignature = (IdSignature$SpecialFakeOverrideSignature) other;
        return Intrinsics.areEqual(this.memberSignature, idSignature$SpecialFakeOverrideSignature.memberSignature) && Intrinsics.areEqual(this.overriddenSignatures, idSignature$SpecialFakeOverrideSignature.overriddenSignatures);
    }

    public final IdSignature getMemberSignature() {
        return this.memberSignature;
    }

    public final List<IdSignature> getOverriddenSignatures() {
        return this.overriddenSignatures;
    }

    /* JADX INFO: renamed from: hashCode, reason: from getter */
    public int getHashCode() {
        return this.hashCode;
    }

    public boolean isPubliclyVisible() {
        return this.memberSignature.isPubliclyVisible();
    }

    public IdSignature nearestPublicSig() {
        return this.memberSignature.isPubliclyVisible() ? this : this.memberSignature.nearestPublicSig();
    }

    public FqName packageFqName() {
        return this.memberSignature.packageFqName();
    }

    public IdSignature topLevelSignature() {
        return this.memberSignature.topLevelSignature();
    }
}

package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeClassLikeErrorLookupTag;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "delegatedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getDelegatedType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeClassLikeErrorLookupTag extends ConeClassLikeLookupTag {
    private final ClassId classId;
    private final ConeKotlinType delegatedType;
    private final ConeDiagnostic diagnostic;

    public ConeClassLikeErrorLookupTag(ClassId classId, ConeDiagnostic coneDiagnostic, ConeKotlinType coneKotlinType) {
        classId.getClass();
        coneDiagnostic.getClass();
        this.classId = classId;
        this.diagnostic = coneDiagnostic;
        this.delegatedType = coneKotlinType;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag
    public ClassId getClassId() {
        return this.classId;
    }

    public final ConeKotlinType getDelegatedType() {
        return this.delegatedType;
    }

    public final ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    public /* synthetic */ ConeClassLikeErrorLookupTag(ClassId classId, ConeDiagnostic coneDiagnostic, ConeKotlinType coneKotlinType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(classId, coneDiagnostic, (i & 4) != 0 ? null : coneKotlinType);
    }
}

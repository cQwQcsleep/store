package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\n\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/OriginalProjectionTypeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "data", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;)V", "getData", "()Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "union", "other", "intersect", "add", "isSubtypeOf", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class OriginalProjectionTypeAttribute extends ConeAttribute<OriginalProjectionTypeAttribute> {
    private final ConeTypeProjection data;

    public OriginalProjectionTypeAttribute(ConeTypeProjection coneTypeProjection) {
        coneTypeProjection.getClass();
        this.data = coneTypeProjection;
    }

    public final ConeTypeProjection getData() {
        return this.data;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean getKeepInInferredDeclarationType() {
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public KClass<? extends OriginalProjectionTypeAttribute> getKey() {
        return Reflection.getOrCreateKotlinClass(OriginalProjectionTypeAttribute.class);
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public String toString() {
        return "OriginalProjectionTypeAttribute: " + this.data;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public OriginalProjectionTypeAttribute add(OriginalProjectionTypeAttribute other) {
        return other == null ? this : other;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public OriginalProjectionTypeAttribute intersect(OriginalProjectionTypeAttribute other) {
        return other == null ? this : other;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean isSubtypeOf(OriginalProjectionTypeAttribute other) {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public OriginalProjectionTypeAttribute union(OriginalProjectionTypeAttribute other) {
        return other == null ? this : other;
    }
}

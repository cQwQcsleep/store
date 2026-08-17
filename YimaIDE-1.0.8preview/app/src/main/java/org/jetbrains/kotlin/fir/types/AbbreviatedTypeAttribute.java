package org.jetbrains.kotlin.fir.types;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0014\u0010\n\u001a\u0004\u0018\u00010\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000b\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/AbbreviatedTypeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttributeWithConeType;", "coneType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "getConeType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "union", "other", "intersect", "add", "isSubtypeOf", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "copyWith", "newType", "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AbbreviatedTypeAttribute extends ConeAttributeWithConeType<AbbreviatedTypeAttribute> {
    private final ConeKotlinType coneType;

    public AbbreviatedTypeAttribute(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        this.coneType = coneKotlinType;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttributeWithConeType
    public AbbreviatedTypeAttribute copyWith(ConeKotlinType newType) {
        newType.getClass();
        return new AbbreviatedTypeAttribute(newType);
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttributeWithConeType
    public ConeKotlinType getConeType() {
        return this.coneType;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean getKeepInInferredDeclarationType() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public KClass<? extends AbbreviatedTypeAttribute> getKey() {
        return Reflection.getOrCreateKotlinClass(AbbreviatedTypeAttribute.class);
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public String toString() {
        return "{" + ConeTypeUtilsKt.renderForDebugging(getConeType()) + "=}";
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public AbbreviatedTypeAttribute add(AbbreviatedTypeAttribute other) {
        return other == null ? this : other;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public AbbreviatedTypeAttribute intersect(AbbreviatedTypeAttribute other) {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean isSubtypeOf(AbbreviatedTypeAttribute other) {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public AbbreviatedTypeAttribute union(AbbreviatedTypeAttribute other) {
        return null;
    }
}

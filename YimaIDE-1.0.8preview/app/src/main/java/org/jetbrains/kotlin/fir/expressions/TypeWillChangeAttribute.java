package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\f\u001a\u0004\u0018\u00010\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0000H\u0016J\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u000f\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0000H\u0016J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\r\u001a\u0004\u0018\u00010\u0000H\u0016J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u00112\b\u0010\r\u001a\u0004\u0018\u00010\u001dHÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/TypeWillChangeAttribute;", "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "newType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/config/LanguageFeature;)V", "getNewType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getLanguageFeature", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "union", "other", "intersect", "add", "isSubtypeOf", Argument.Delimiters.none, "key", "Lkotlin/reflect/KClass;", "getKey", "()Lkotlin/reflect/KClass;", "keepInInferredDeclarationType", "getKeepInInferredDeclarationType", "()Z", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class TypeWillChangeAttribute extends ConeAttribute<TypeWillChangeAttribute> {
    private final LanguageFeature languageFeature;
    private final ConeKotlinType newType;

    public TypeWillChangeAttribute(ConeKotlinType coneKotlinType, LanguageFeature languageFeature) {
        coneKotlinType.getClass();
        languageFeature.getClass();
        this.newType = coneKotlinType;
        this.languageFeature = languageFeature;
    }

    public static /* synthetic */ TypeWillChangeAttribute copy$default(TypeWillChangeAttribute typeWillChangeAttribute, ConeKotlinType coneKotlinType, LanguageFeature languageFeature, int i, Object obj) {
        if ((i & 1) != 0) {
            coneKotlinType = typeWillChangeAttribute.newType;
        }
        if ((i & 2) != 0) {
            languageFeature = typeWillChangeAttribute.languageFeature;
        }
        return typeWillChangeAttribute.copy(coneKotlinType, languageFeature);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ConeKotlinType getNewType() {
        return this.newType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final LanguageFeature getLanguageFeature() {
        return this.languageFeature;
    }

    public final TypeWillChangeAttribute copy(ConeKotlinType newType, LanguageFeature languageFeature) {
        newType.getClass();
        languageFeature.getClass();
        return new TypeWillChangeAttribute(newType, languageFeature);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TypeWillChangeAttribute)) {
            return false;
        }
        TypeWillChangeAttribute typeWillChangeAttribute = (TypeWillChangeAttribute) other;
        return Intrinsics.areEqual(this.newType, typeWillChangeAttribute.newType) && this.languageFeature == typeWillChangeAttribute.languageFeature;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean getKeepInInferredDeclarationType() {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public KClass<? extends TypeWillChangeAttribute> getKey() {
        return Reflection.getOrCreateKotlinClass(TypeWillChangeAttribute.class);
    }

    public final LanguageFeature getLanguageFeature() {
        return this.languageFeature;
    }

    public final ConeKotlinType getNewType() {
        return this.newType;
    }

    public int hashCode() {
        return (this.newType.hashCode() * 31) + this.languageFeature.hashCode();
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public String toString() {
        return "TypeWillChangeAttribute(newType=" + this.newType + ", languageFeature=" + this.languageFeature + ')';
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public TypeWillChangeAttribute add(TypeWillChangeAttribute other) {
        return other == null ? this : other;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public TypeWillChangeAttribute intersect(TypeWillChangeAttribute other) {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public boolean isSubtypeOf(TypeWillChangeAttribute other) {
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeAttribute
    public TypeWillChangeAttribute union(TypeWillChangeAttribute other) {
        return null;
    }
}

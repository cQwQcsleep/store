package org.jetbrains.kotlin.fir.resolve.substitution;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralTypeImplKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeSystemContextHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B+\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0016J\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeTypeSubstitutorByTypeConstructor;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/AbstractConeSubstitutor;", "map", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "approximateIntegerLiterals", Argument.Delimiters.none, "<init>", "(Ljava/util/Map;Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;Z)V", "substituteType", ModuleXmlParser.TYPE, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class ConeTypeSubstitutorByTypeConstructor extends AbstractConeSubstitutor {
    private final boolean approximateIntegerLiterals;
    private final Map<TypeConstructorMarker, ConeKotlinType> map;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConeTypeSubstitutorByTypeConstructor(Map<TypeConstructorMarker, ? extends ConeKotlinType> map, ConeTypeContext coneTypeContext, boolean z) {
        super(coneTypeContext);
        map.getClass();
        coneTypeContext.getClass();
        this.map = map;
        this.approximateIntegerLiterals = z;
    }

    public static CharSequence b(Map.Entry entry) {
        entry.getClass();
        return ((TypeConstructorMarker) entry.getKey()) + " -> " + ConeTypeUtilsKt.renderForDebugging((ConeKotlinType) entry.getValue());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.substitution.AbstractConeSubstitutor
    public ConeKotlinType substituteType(ConeKotlinType type) {
        ConeKotlinType coneKotlinTypeApproximateIntegerLiteralType$default;
        type.getClass();
        if ((!(type instanceof ConeLookupTagBasedType) && !(type instanceof ConeStubType) && !(type instanceof ConeTypeVariableType)) || (coneKotlinTypeApproximateIntegerLiteralType$default = this.map.get(TypeSystemContextHelpersKt.typeConstructor(type, getTypeContext()))) == null) {
            return null;
        }
        if (this.approximateIntegerLiterals) {
            coneKotlinTypeApproximateIntegerLiteralType$default = ConeIntegerLiteralTypeImplKt.approximateIntegerLiteralType$default(coneKotlinTypeApproximateIntegerLiteralType$default, (ConeKotlinType) null, 1, (Object) null);
        }
        return LookupTagUtilsKt.withCombinedAttributesFrom(updateNullabilityIfNeeded(coneKotlinTypeApproximateIntegerLiteralType$default, type), type);
    }

    public String toString() {
        return CollectionsKt.joinToString$default(this.map.entrySet(), " | ", "{", "}", 0, (CharSequence) null, new Function1() { // from class: org.jetbrains.kotlin.fir.resolve.substitution.a
            public final Object invoke(Object obj) {
                return ConeTypeSubstitutorByTypeConstructor.b((Map.Entry) obj);
            }
        }, 24, (Object) null);
    }
}

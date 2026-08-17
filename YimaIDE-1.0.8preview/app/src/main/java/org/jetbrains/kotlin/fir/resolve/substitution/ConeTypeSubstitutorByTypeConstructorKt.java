package org.jetbrains.kotlin.fir.resolve.substitution;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a*\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"createTypeSubstitutorByTypeConstructor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "map", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "context", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "approximateIntegerLiterals", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeSubstitutorByTypeConstructorKt {
    public static final ConeSubstitutor createTypeSubstitutorByTypeConstructor(Map<TypeConstructorMarker, ? extends ConeKotlinType> map, ConeTypeContext coneTypeContext, boolean z) {
        map.getClass();
        coneTypeContext.getClass();
        return map.isEmpty() ? ConeSubstitutor.Empty.INSTANCE : new ConeTypeSubstitutorByTypeConstructor(map, coneTypeContext, z);
    }
}

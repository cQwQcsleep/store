package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JB\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000bH&J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirTypeResolver;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "resolveType", "Lorg/jetbrains/kotlin/fir/resolve/FirTypeResolutionResult;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "configuration", "Lorg/jetbrains/kotlin/fir/resolve/TypeResolutionConfiguration;", "areBareTypesAllowed", Argument.Delimiters.none, "isOperandOfIsOperator", "resolveDeprecations", "supertypeSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "expandTypeAliases", "resolveTypeOnDoubleColonLHS", "Lorg/jetbrains/kotlin/fir/resolve/DoubleColonLHS$Type;", "qualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirTypeResolver implements FirSessionComponent {
    public static /* synthetic */ FirTypeResolutionResult resolveType$default(FirTypeResolver firTypeResolver, FirTypeRef firTypeRef, TypeResolutionConfiguration typeResolutionConfiguration, boolean z, boolean z2, boolean z3, SupertypeSupplier supertypeSupplier, boolean z4, int i, Object obj) {
        if (obj == null) {
            return firTypeResolver.resolveType(firTypeRef, typeResolutionConfiguration, z, z2, z3, supertypeSupplier, (i & 64) != 0 ? true : z4);
        }
        c41.a("Super calls with default arguments not supported in this target, function: resolveType");
        return null;
    }

    public abstract FirTypeResolutionResult resolveType(FirTypeRef typeRef, TypeResolutionConfiguration configuration, boolean areBareTypesAllowed, boolean isOperandOfIsOperator, boolean resolveDeprecations, SupertypeSupplier supertypeSupplier, boolean expandTypeAliases);

    public abstract DoubleColonLHS.Type resolveTypeOnDoubleColonLHS(FirResolvedQualifier qualifier, TypeResolutionConfiguration configuration);
}

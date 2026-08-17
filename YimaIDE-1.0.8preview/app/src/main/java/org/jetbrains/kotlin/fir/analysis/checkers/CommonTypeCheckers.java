package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirCoroutineContextAsContextParameterTypeRefChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirArrayOfNothingTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirContextualFunctionTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirDefinitelyNotNullableChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirDeprecatedTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirDslMarkerPropagationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirDuplicateParameterNameInFunctionTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirInOutProjectionModifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirIncompatibleClassTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirInlineExposedLessVisibleTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirKotlinActualAnnotationHasNoEffectInKotlinTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirMissingDependencyClassInTypeAliasTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirOptInUsageTypeRefChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirOptionalExpectationTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirProjectionRelationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirStarProjectionModifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirSuspendModifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirUnsupportedDefaultValueInFunctionTypeParameterChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirUnsupportedModifiersInFunctionTypeParameterChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirUpperBoundViolatedTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.PlatformClassMappedToKotlinTypeRefChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.RedundantNullableChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.TypeArgumentsInPackagesTypeRefChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\n¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/CommonTypeCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "<init>", "()V", "typeRefCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeRefChecker;", "getTypeRefCheckers", "()Ljava/util/Set;", "resolvedTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "getResolvedTypeRefCheckers", "intersectionTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirIntersectionTypeRefChecker;", "getIntersectionTypeRefCheckers", "functionTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirFunctionTypeRefChecker;", "getFunctionTypeRefCheckers", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonTypeCheckers extends TypeCheckers {
    public static final CommonTypeCheckers INSTANCE = new CommonTypeCheckers();
    private static final Set<FirTypeChecker<FirTypeRef>> typeRefCheckers = SetsKt.setOf(new FirTypeChecker[]{FirSuspendModifierChecker.INSTANCE, FirCoroutineContextAsContextParameterTypeRefChecker.INSTANCE});
    private static final Set<FirTypeChecker<FirResolvedTypeRef>> resolvedTypeRefCheckers = SetsKt.setOf(new FirTypeChecker[]{FirTypeAnnotationChecker.INSTANCE, FirDeprecatedTypeChecker.INSTANCE, FirOptInUsageTypeRefChecker.INSTANCE, FirStarProjectionModifierChecker.INSTANCE, FirInOutProjectionModifierChecker.INSTANCE, FirDuplicateParameterNameInFunctionTypeChecker.INSTANCE, FirOptionalExpectationTypeChecker.INSTANCE, FirIncompatibleClassTypeChecker.INSTANCE, FirContextualFunctionTypeChecker.INSTANCE, FirKotlinActualAnnotationHasNoEffectInKotlinTypeChecker.INSTANCE, FirProjectionRelationChecker.INSTANCE, FirUpperBoundViolatedTypeChecker.INSTANCE, FirArrayOfNothingTypeChecker.INSTANCE, FirInlineExposedLessVisibleTypeChecker.INSTANCE, RedundantNullableChecker.INSTANCE, PlatformClassMappedToKotlinTypeRefChecker.INSTANCE, FirMissingDependencyClassInTypeAliasTypeChecker.INSTANCE, FirRootIdePackageDeprecatedInCliTypeChecker.INSTANCE, TypeArgumentsInPackagesTypeRefChecker.INSTANCE});
    private static final Set<FirTypeChecker<FirIntersectionTypeRef>> intersectionTypeRefCheckers = SetsKt.setOf(FirDefinitelyNotNullableChecker.INSTANCE);
    private static final Set<FirTypeChecker<FirFunctionTypeRef>> functionTypeRefCheckers = SetsKt.setOf(new FirTypeChecker[]{FirUnsupportedDefaultValueInFunctionTypeParameterChecker.INSTANCE, FirUnsupportedModifiersInFunctionTypeParameterChecker.INSTANCE, FirDslMarkerPropagationChecker.INSTANCE});

    private CommonTypeCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirFunctionTypeRef>> getFunctionTypeRefCheckers() {
        return functionTypeRefCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirIntersectionTypeRef>> getIntersectionTypeRefCheckers() {
        return intersectionTypeRefCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirResolvedTypeRef>> getResolvedTypeRefCheckers() {
        return resolvedTypeRefCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirTypeRef>> getTypeRefCheckers() {
        return typeRefCheckers;
    }
}

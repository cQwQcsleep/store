package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirUnderscoreHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponentKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ9\u0010\u000e\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnyTypeAliasChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeAliasChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;)V", "checkTypeAliasExpansionCapturesOuterTypeParameters", "fullyExpandedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "expandedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnyTypeAliasChecker extends FirDeclarationChecker<FirTypeAlias> {
    public static final FirAnyTypeAliasChecker INSTANCE = new FirAnyTypeAliasChecker();

    private FirAnyTypeAliasChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkTypeAliasExpansionCapturesOuterTypeParameters(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeAlias firTypeAlias, ConeKotlinType coneKotlinType, FirTypeRef firTypeRef) {
        if (FirHelpersKt.isTopLevel(checkerContext) || firTypeAlias.getStatus().isInner()) {
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        checkTypeAliasExpansionCapturesOuterTypeParameters$checkRecursively(checkerContext, firTypeAlias, linkedHashSet, coneKotlinType);
        if (linkedHashSet.isEmpty()) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getTYPEALIAS_EXPANSION_CAPTURES_OUTER_TYPE_PARAMETERS(), (Object) linkedHashSet, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    private static final void checkTypeAliasExpansionCapturesOuterTypeParameters$checkRecursively(CheckerContext checkerContext, FirTypeAlias firTypeAlias, Set<FirTypeParameterSymbol> set, ConeKotlinType coneKotlinType) {
        ConeKotlinType coneKotlinTypeFullyExpandedType;
        for (ConeTypeProjection coneTypeProjection : coneKotlinType.getTypeArguments()) {
            ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
            if (type != null && (coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, type)) != null) {
                checkTypeAliasExpansionCapturesOuterTypeParameters$checkRecursively(checkerContext, firTypeAlias, set, coneKotlinTypeFullyExpandedType);
            }
        }
        FirTypeParameterSymbol typeParameterSymbol = ToSymbolUtilsKt.toTypeParameterSymbol(checkerContext, coneKotlinType);
        if (typeParameterSymbol == null || Intrinsics.areEqual(firTypeAlias.getSymbol(), typeParameterSymbol.getContainingDeclarationSymbol())) {
            return;
        }
        set.add(typeParameterSymbol);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0081  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeAlias firTypeAlias) {
        ConeKotlinType coneKotlinType;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeAlias.getClass();
        if (!FirHelpersKt.isTopLevel(checkerContext)) {
            FirHelpersKt.requireFeatureSupport$default(checkerContext, diagnosticReporter, firTypeAlias, firTypeAlias.getIsLocal() ? LanguageFeature.LocalTypeAliases : LanguageFeature.NestedTypeAliases, (SourceElementPositioningStrategy) null, 8, (Object) null);
        }
        FirTypeRef expandedTypeRef = firTypeAlias.getExpandedTypeRef();
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(expandedTypeRef));
        checkTypeAliasExpansionCapturesOuterTypeParameters(checkerContext, diagnosticReporter, firTypeAlias, coneKotlinTypeFullyExpandedType, expandedTypeRef);
        if (ConeTypeUtilsKt.hasError(coneKotlinTypeFullyExpandedType)) {
            coneKotlinType = coneKotlinTypeFullyExpandedType;
        } else {
            FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, coneKotlinTypeFullyExpandedType);
            if ((regularClassSymbol != null ? regularClassSymbol.getClassKind() : null) == ClassKind.ANNOTATION_CLASS && FirAnnotationsPlatformSpecificSupportComponentKt.getAnnotationPlatformSupport(checkerContext.getSession()).getRequiredAnnotations().contains(regularClassSymbol.getClassId())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) expandedTypeRef.getSource(), (KtDiagnosticFactoryForDeprecation1) FirErrors.INSTANCE.getTYPEALIAS_EXPANDS_TO_COMPILER_REQUIRED_ANNOTATION(), (Object) regularClassSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            if ((regularClassSymbol == null || (coneKotlinTypeFullyExpandedType instanceof ConeDynamicType)) && ToSymbolUtilsKt.toTypeAliasSymbol(checkerContext, FirTypeUtilsKt.getConeType(expandedTypeRef)) == null) {
                coneKotlinType = coneKotlinTypeFullyExpandedType;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) expandedTypeRef.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getTYPEALIAS_SHOULD_EXPAND_TO_CLASS(), (Object) coneKotlinType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            } else {
                coneKotlinType = coneKotlinTypeFullyExpandedType;
            }
        }
        FirUnderscoreHelpersKt.checkTypeRefForUnderscore(checkerContext, diagnosticReporter, expandedTypeRef);
        if (FirHelpersKt.isMalformedExpandedType(checkerContext, coneKotlinType, LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.NullableNothingInReifiedPosition))) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeAlias.getExpandedTypeRef().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getTYPEALIAS_EXPANDS_TO_ARRAY_OF_NOTHINGS(), (Object) coneKotlinType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }
}

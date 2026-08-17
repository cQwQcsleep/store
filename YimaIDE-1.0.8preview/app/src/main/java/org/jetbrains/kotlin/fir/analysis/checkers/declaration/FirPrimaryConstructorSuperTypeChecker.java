package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPrimaryConstructorSuperTypeCheckerPlatformComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitAnyTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ5\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0012J-\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0002H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPrimaryConstructorSuperTypeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "checkSuperTypeNotInitialized", "primaryConstructorSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "regularClass", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "checkSupertypeInitializedWithoutPrimaryConstructor", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPrimaryConstructorSuperTypeChecker extends FirDeclarationChecker<FirClass> {
    public static final FirPrimaryConstructorSuperTypeChecker INSTANCE = new FirPrimaryConstructorSuperTypeChecker();

    private FirPrimaryConstructorSuperTypeChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkSuperTypeNotInitialized(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirConstructorSymbol firConstructorSymbol, FirClass firClass) {
        Object next;
        FirRegularClassSymbol regularClassSymbol;
        KtSourceElement source;
        List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
        if (!(containingDeclarations instanceof List)) {
            Iterator it = CollectionsKt.reversed(containingDeclarations).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!(next instanceof FirRegularClassSymbol));
        } else {
            int size = containingDeclarations.size() - 1;
            if (size < 0) {
                next = null;
                break;
            }
            while (true) {
                int i = size - 1;
                next = containingDeclarations.get(size);
                if (next instanceof FirRegularClassSymbol) {
                    break;
                }
                if (i < 0) {
                    next = null;
                    break;
                }
                size = i;
            }
        }
        FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) next;
        FirDelegatedConstructorCall resolvedDelegatedConstructorCall = firConstructorSymbol.getResolvedDelegatedConstructorCall();
        if (resolvedDelegatedConstructorCall == null) {
            return;
        }
        FirTypeRef constructedTypeRef = resolvedDelegatedConstructorCall.getConstructedTypeRef();
        if (constructedTypeRef instanceof FirImplicitAnyTypeRef) {
            return;
        }
        KtSourceElement source2 = constructedTypeRef.getSource();
        if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.PluginGenerated.INSTANCE) || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, FirTypeUtilsKt.getConeType(constructedTypeRef))) == null || regularClassSymbol.getClassKind().isSingleton() || DeclarationUtilsKt.isEffectivelyExpect(checkerContext, firClass, firRegularClassSymbol) || DeclarationUtilsKt.isEffectivelyExternal(checkerContext, firClass, firRegularClassSymbol) || (source = resolvedDelegatedConstructorCall.getSource()) == null || !(source.getKind() instanceof KtFakeSourceElementKind) || FirPrimaryConstructorSuperTypeCheckerPlatformComponentKt.getPrimaryConstructorSuperTypePlatformSupport(checkerContext.getSession()).getSupertypesThatDontNeedInitializationInSubtypesConstructors().contains(regularClassSymbol.getClassId()) || Intrinsics.areEqual(source.getElementType(), KtNodeTypes.SUPER_TYPE_CALL_ENTRY)) {
            return;
        }
        if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession()).supportsFeature(LanguageFeature.AllowAnyAsAnActualTypeForExpectInterface) && ConeBuiltinTypeUtilsKt.isAny(FirTypeUtilsKt.getConeType(resolvedDelegatedConstructorCall.getConstructedTypeRef()))) {
            List<ConeClassLikeType> superConeTypes = FirDeclarationUtilKt.getSuperConeTypes(firClass);
            if (!(superConeTypes instanceof Collection) || !superConeTypes.isEmpty()) {
                for (ConeClassLikeType coneClassLikeType : superConeTypes) {
                    if (AbbreviatedTypeAttributeKt.getAbbreviatedType(coneClassLikeType) != null && ConeBuiltinTypeUtilsKt.isAny(coneClassLikeType)) {
                        return;
                    }
                }
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) constructedTypeRef.getSource(), FirErrors.INSTANCE.getSUPERTYPE_NOT_INITIALIZED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private final void checkSupertypeInitializedWithoutPrimaryConstructor(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirClass firClass) {
        SourceNavigator sourceNavigatorForElement = SourceNavigator.INSTANCE.forElement(firClass);
        for (FirTypeRef firTypeRef : firClass.getSuperTypeRefs()) {
            if (sourceNavigatorForElement.isInConstructorCallee(firTypeRef)) {
                KtSourceElement source = firTypeRef.getSource();
                if (source == null) {
                    source = firClass.getSource();
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getSUPERTYPE_INITIALIZED_WITHOUT_PRIMARY_CONSTRUCTOR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        if (firClass.getClassKind() == ClassKind.INTERFACE) {
            SourceNavigator sourceNavigatorForElement = SourceNavigator.INSTANCE.forElement(firClass);
            for (FirTypeRef firTypeRef : firClass.getSuperTypeRefs()) {
                if (sourceNavigatorForElement.isInConstructorCallee(firTypeRef)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeRef.getSource(), FirErrors.INSTANCE.getSUPERTYPE_INITIALIZED_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
            return;
        }
        if (firClass.getClassKind() == ClassKind.ENUM_ENTRY) {
            return;
        }
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(firClass, checkerContext.getSession());
        if (firConstructorSymbolPrimaryConstructorIfAny == null || FirDeclarationUtilKt.isErrorPrimaryConstructor(firConstructorSymbolPrimaryConstructorIfAny)) {
            checkSupertypeInitializedWithoutPrimaryConstructor(diagnosticReporter, checkerContext, firClass);
        } else {
            checkSuperTypeNotInitialized(checkerContext, diagnosticReporter, firConstructorSymbolPrimaryConstructorIfAny, firClass);
        }
    }
}

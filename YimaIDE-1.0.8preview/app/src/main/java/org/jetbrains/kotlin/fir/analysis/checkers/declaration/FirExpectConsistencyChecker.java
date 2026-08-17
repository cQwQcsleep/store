package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.impl.FirPrimaryConstructor;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\f\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013H\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\f\u001a\u00020\u0011H\u0002J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000f2\u0006\u0010\f\u001a\u00020\u0011H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u0011H\u0002J\u001e\u0010\u001a\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u00112\f\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0013H\u0002J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u0011H\u0002J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u0011H\u0002J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u0011H\u0002¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExpectConsistencyChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "getConstructorProhibitedPropertyParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "containingClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getConstructorDelegationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "getClassSuperTypeReferencesWithInitializers", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "isProhibitedPrivateDeclaration", Argument.Delimiters.none, "isProhibitedEnumConstructor", "lastClass", "isProhibitedDeclarationWithBody", "isProhibitedEnumEntryWithBody", "isProhibitedEnumEntryWithInitializer", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpectConsistencyChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirExpectConsistencyChecker INSTANCE = new FirExpectConsistencyChecker();

    private FirExpectConsistencyChecker() {
        super(MppCheckerKind.Common);
    }

    private final List<FirTypeRef> getClassSuperTypeReferencesWithInitializers(FirMemberDeclaration declaration) {
        if (!(declaration instanceof FirRegularClass)) {
            return CollectionsKt.emptyList();
        }
        SourceNavigator sourceNavigatorForSource = SourceNavigator.INSTANCE.forSource(declaration.getSource());
        List<FirTypeRef> superTypeRefs = ((FirRegularClass) declaration).getSuperTypeRefs();
        ArrayList arrayList = new ArrayList();
        for (Object obj : superTypeRefs) {
            if (sourceNavigatorForSource.isInConstructorCallee((FirTypeRef) obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final FirDelegatedConstructorCall getConstructorDelegationCall(FirMemberDeclaration declaration) {
        if (declaration instanceof FirConstructor) {
            FirConstructor firConstructor = (FirConstructor) declaration;
            if (!firConstructor.getIsPrimary()) {
                FirDelegatedConstructorCall delegatedConstructor = firConstructor.getDelegatedConstructor();
                KtSourceElement source = delegatedConstructor != null ? delegatedConstructor.getSource() : null;
                if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind)) {
                    return delegatedConstructor;
                }
            }
        }
        return null;
    }

    private final List<FirValueParameter> getConstructorProhibitedPropertyParameters(FirMemberDeclaration declaration, FirClassSymbol<?> containingClass) {
        if (!(declaration instanceof FirPrimaryConstructor) || containingClass == null || containingClass.getClassKind() == ClassKind.ANNOTATION_CLASS || containingClass.getRawStatus().isInline() || containingClass.getRawStatus().isValue()) {
            return CollectionsKt.emptyList();
        }
        List<FirValueParameter> valueParameters = ((FirPrimaryConstructor) declaration).getValueParameters();
        ArrayList arrayList = new ArrayList();
        for (Object obj : valueParameters) {
            if (FirKeywordUtilsKt.getValOrVarKeyword(((FirValueParameter) obj).getSource()) != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final boolean isProhibitedDeclarationWithBody(FirMemberDeclaration declaration) {
        return (declaration instanceof FirFunction) && ((FirFunction) declaration).getBody() != null;
    }

    private final boolean isProhibitedEnumConstructor(FirMemberDeclaration declaration, FirClassSymbol<?> lastClass) {
        if (declaration instanceof FirConstructor) {
            return (lastClass != null ? lastClass.getClassKind() : null) == ClassKind.ENUM_CLASS;
        }
        return false;
    }

    private final boolean isProhibitedEnumEntryWithBody(FirMemberDeclaration declaration) {
        return (declaration instanceof FirEnumEntry) && Intrinsics.areEqual(SourceNavigator.INSTANCE.forSource(declaration.getSource()).hasBody((FirEnumEntry) declaration), Boolean.TRUE);
    }

    private final boolean isProhibitedEnumEntryWithInitializer(FirMemberDeclaration declaration) {
        return (declaration instanceof FirEnumEntry) && Intrinsics.areEqual(SourceNavigator.INSTANCE.forSource(declaration.getSource()).hasInitializer((FirEnumEntry) declaration), Boolean.TRUE);
    }

    private final boolean isProhibitedPrivateDeclaration(FirMemberDeclaration declaration) {
        return ((declaration instanceof FirConstructor) || (declaration instanceof FirPropertyAccessor) || !Visibilities.INSTANCE.isPrivate(declaration.getStatus().getVisibility())) ? false : true;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        KtSourceElement source = firDeclaration.getSource();
        if (source == null || (source.getKind() instanceof KtFakeSourceElementKind)) {
            return;
        }
        Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        FirClassSymbol<?> firClassSymbol = objLastOrNull instanceof FirClassSymbol ? (FirClassSymbol) objLastOrNull : null;
        if (firDeclaration instanceof FirAnonymousInitializer) {
            if (firClassSymbol == null || !firClassSymbol.getRawStatus().isExpect()) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getEXPECTED_DECLARATION_WITH_BODY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        if (firDeclaration instanceof FirMemberDeclaration) {
            FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
            if (firMemberDeclaration.getStatus().isExpect()) {
                FirDelegatedConstructorCall constructorDelegationCall = getConstructorDelegationCall(firMemberDeclaration);
                if (constructorDelegationCall != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) constructorDelegationCall.getSource(), FirErrors.INSTANCE.getEXPECTED_CLASS_CONSTRUCTOR_DELEGATION_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                Iterator<FirTypeRef> it = getClassSuperTypeReferencesWithInitializers(firMemberDeclaration).iterator();
                while (it.hasNext()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) it.next().getSource(), FirErrors.INSTANCE.getSUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                Iterator<FirValueParameter> it2 = getConstructorProhibitedPropertyParameters(firMemberDeclaration, firClassSymbol).iterator();
                while (it2.hasNext()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) it2.next().getSource(), FirErrors.INSTANCE.getEXPECTED_CLASS_CONSTRUCTOR_PROPERTY_PARAMETER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                if (isProhibitedEnumConstructor(firMemberDeclaration, firClassSymbol)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getEXPECTED_ENUM_CONSTRUCTOR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                if (isProhibitedEnumEntryWithBody(firMemberDeclaration)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getEXPECTED_ENUM_ENTRY_WITH_BODY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                if (isProhibitedEnumEntryWithInitializer(firMemberDeclaration)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getSUPERTYPE_INITIALIZED_IN_EXPECTED_CLASS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                if (isProhibitedPrivateDeclaration(firMemberDeclaration)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getEXPECTED_PRIVATE_DECLARATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                if (isProhibitedDeclarationWithBody(firMemberDeclaration)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getEXPECTED_DECLARATION_WITH_BODY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }
}

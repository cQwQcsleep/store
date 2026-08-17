package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0002J\f\u0010\u0010\u001a\u00020\u000f*\u00020\u0002H\u0002J/\u0010\u0011\u001a\u00020\u0007*\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInapplicableLateinitChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "hasGetter", Argument.Delimiters.none, "hasSetter", "reportError", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "target", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/lang/String;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInapplicableLateinitChecker extends FirDeclarationChecker<FirProperty> {
    public static final FirInapplicableLateinitChecker INSTANCE = new FirInapplicableLateinitChecker();

    private FirInapplicableLateinitChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean hasGetter(FirProperty firProperty) {
        return (firProperty.getGetter() == null || (firProperty.getGetter() instanceof FirDefaultPropertyGetter)) ? false : true;
    }

    private final boolean hasSetter(FirProperty firProperty) {
        return (firProperty.getSetter() == null || (firProperty.getSetter() instanceof FirDefaultPropertySetter)) ? false : true;
    }

    private final void reportError(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, String str) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getINAPPLICABLE_LATEINIT_MODIFIER(), (Object) str, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        if (!firProperty.getStatus().isLateInit() || (firProperty.getReturnTypeRef() instanceof FirErrorTypeRef)) {
            return;
        }
        if (firProperty.getIsVal()) {
            reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is allowed only on mutable properties");
        }
        if (firProperty.getInitializer() != null) {
            if (firProperty.getSymbol() instanceof FirLocalPropertySymbol) {
                reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on local variables with initializer");
            } else {
                reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on properties with initializer");
            }
        }
        if (firProperty.getDelegate() != null) {
            reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on delegated properties");
        }
        if (TypeUtilsKt.canBeNull$default(FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef()), checkerContext.getSession(), false, null, 6, null)) {
            reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on properties of a type with nullable upper bound");
        }
        if (ConeBuiltinTypeUtilsKt.isPrimitive(FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef()))) {
            if (firProperty.getSymbol() instanceof FirLocalPropertySymbol) {
                reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on local variables of primitive types");
            } else {
                reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on properties of primitive types");
            }
        }
        if (DeclarationAttributesKt.getHasExplicitBackingField(firProperty)) {
            reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "must be moved to the field declaration");
        }
        if ((hasGetter(firProperty) || hasSetter(firProperty)) && firProperty.getDelegate() == null) {
            reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on properties with a custom getter or setter");
        }
        if (FirDeclarationUtilKt.isInstanceExtension(firProperty)) {
            reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on extension properties");
        }
        if (!firProperty.getContextParameters().isEmpty()) {
            reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on properties with context receivers");
        }
        if (firProperty.getStatus().getModality() == Modality.ABSTRACT) {
            reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on abstract properties");
        }
        if (FirHelpersKt.isSingleFieldValueClass(FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef()), checkerContext.getSession())) {
            ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef()));
            String str = firProperty.getSymbol() instanceof FirLocalPropertySymbol ? "local variables" : "properties";
            if (ConeBuiltinTypeUtilsKt.isUnsignedType(coneKotlinTypeFullyExpandedType)) {
                reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on " + str + " of unsigned types");
                return;
            }
            reportError(checkerContext, diagnosticReporter, firProperty.getSource(), "is not allowed on " + str + " of inline class types");
        }
    }
}

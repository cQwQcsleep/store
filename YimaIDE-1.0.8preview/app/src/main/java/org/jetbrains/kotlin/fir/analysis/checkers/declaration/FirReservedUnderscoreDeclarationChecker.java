package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirUnderscoreHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.SourceNavigator;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ7\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirReservedUnderscoreDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "reportIfUnderscore", "isSingleUnderscoreAllowed", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Z)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReservedUnderscoreDeclarationChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirReservedUnderscoreDeclarationChecker INSTANCE = new FirReservedUnderscoreDeclarationChecker();

    private FirReservedUnderscoreDeclarationChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0055  */
    private final void reportIfUnderscore(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, boolean z) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        String rawName;
        KtSourceElement source = firDeclaration.getSource();
        if (source == null || (source.getKind() instanceof KtFakeSourceElementKind)) {
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
        } else {
            FirProperty firProperty = firDeclaration instanceof FirProperty ? (FirProperty) firDeclaration : null;
            if (Intrinsics.areEqual(firProperty != null ? firProperty.getName() : null, SpecialNames.UNDERSCORE_FOR_UNUSED_VAR) || (rawName = SourceNavigator.INSTANCE.forElement(firDeclaration).getRawName(firDeclaration)) == null || !FirUnderscoreHelpersKt.isUnderscore(rawName) || (z && Intrinsics.areEqual(rawName, InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER))) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getUNDERSCORE_IS_RESERVED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        if (firDeclaration instanceof FirValueParameter) {
            FirUnderscoreHelpersKt.checkTypeRefForUnderscore(checkerContext2, diagnosticReporter2, ((FirValueParameter) firDeclaration).getReturnTypeRef());
        } else if (firDeclaration instanceof FirFunction) {
            FirFunction firFunction = (FirFunction) firDeclaration;
            FirUnderscoreHelpersKt.checkTypeRefForUnderscore(checkerContext2, diagnosticReporter2, firFunction.getReturnTypeRef());
            FirReceiverParameter receiverParameter = firFunction.getReceiverParameter();
            FirUnderscoreHelpersKt.checkTypeRefForUnderscore(checkerContext2, diagnosticReporter2, receiverParameter != null ? receiverParameter.getTypeRef() : null);
        }
    }

    public static /* synthetic */ void reportIfUnderscore$default(FirReservedUnderscoreDeclarationChecker firReservedUnderscoreDeclarationChecker, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        firReservedUnderscoreDeclarationChecker.reportIfUnderscore(checkerContext, diagnosticReporter, firDeclaration, z);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if ((firDeclaration instanceof FirRegularClass) || (((firDeclaration instanceof FirProperty) && !Intrinsics.areEqual(ClassMembersKt.isCatchParameter((FirProperty) firDeclaration), Boolean.TRUE)) || (firDeclaration instanceof FirTypeAlias))) {
            reportIfUnderscore$default(this, checkerContext, diagnosticReporter, firDeclaration, false, 8, null);
            return;
        }
        if (firDeclaration instanceof FirTypeParameter) {
            reportIfUnderscore$default(this, checkerContext, diagnosticReporter, firDeclaration, false, 8, null);
            Iterator<T> it = ((FirTypeParameter) firDeclaration).getBounds().iterator();
            while (it.hasNext()) {
                FirUnderscoreHelpersKt.checkTypeRefForUnderscore(checkerContext, diagnosticReporter, (FirTypeRef) it.next());
            }
            return;
        }
        if (!(firDeclaration instanceof FirFunction)) {
            if (firDeclaration instanceof FirFile) {
                Iterator<FirImport> it2 = ((FirFile) firDeclaration).getImports().iterator();
                while (it2.hasNext()) {
                    FirUnderscoreHelpersKt.checkUnderscoreDiagnostics(checkerContext, diagnosticReporter, it2.next().getAliasSource(), false);
                }
                return;
            }
            return;
        }
        if (firDeclaration instanceof FirNamedFunction) {
            reportIfUnderscore$default(this, checkerContext, diagnosticReporter, firDeclaration, false, 8, null);
        }
        boolean z = (firDeclaration instanceof FirAnonymousFunction) || (firDeclaration instanceof FirPropertyAccessor);
        Iterator<FirValueParameter> it3 = ((FirFunction) firDeclaration).getValueParameters().iterator();
        while (it3.hasNext()) {
            reportIfUnderscore(checkerContext, diagnosticReporter, it3.next(), z);
        }
    }
}

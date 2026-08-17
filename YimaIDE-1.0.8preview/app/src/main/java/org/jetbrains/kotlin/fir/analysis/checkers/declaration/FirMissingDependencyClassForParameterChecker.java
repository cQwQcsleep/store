package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.LinkedHashSet;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirMissingDependencySupertypeUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirMissingDependencyClassProxy;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u00032\u00020\u0004B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J-\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0016R\u00020\tR\u00020\u000bj\u0006\u0010\n\u001a\u00020\tj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u000eJ-\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002R\u00020\tR\u00020\u000bj\u0006\u0010\n\u001a\u00020\tj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u000eJ-\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0002H\u0002R\u00020\tR\u00020\u000bj\u0006\u0010\n\u001a\u00020\tj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u000e¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirMissingDependencyClassForParameterChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueParameterChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirMissingDependencyClassProxy;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)V", "checkLambdaParameter", "parameter", "checkDataClassParameter", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMissingDependencyClassForParameterChecker extends FirDeclarationChecker<FirValueParameter> implements FirMissingDependencyClassProxy {
    public static final FirMissingDependencyClassForParameterChecker INSTANCE = new FirMissingDependencyClassForParameterChecker();

    private FirMissingDependencyClassForParameterChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkDataClassParameter(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirValueParameter firValueParameter) {
        FirMissingDependencySupertypeUtilsKt.checkMissingDependencySuperTypes(checkerContext, diagnosticReporter, FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()), firValueParameter.getSource());
    }

    private final void checkLambdaParameter(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirValueParameter firValueParameter) {
        KtSourceElement source = firValueParameter.getReturnTypeRef().getSource();
        if ((source != null ? source.getKind() : null) instanceof KtRealSourceElementKind) {
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        considerType(checkerContext, FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef()), linkedHashSet);
        KtSourceElement source2 = firValueParameter.getSource();
        Name name = firValueParameter.getName();
        Name nameIdentifier = name.isSpecial() ? null : name;
        if (nameIdentifier == null) {
            nameIdentifier = Name.identifier(InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER);
            nameIdentifier.getClass();
        }
        reportMissingTypes(checkerContext, diagnosticReporter, source2, linkedHashSet, new FirMissingDependencyClassProxy.MissingTypeOrigin.LambdaParameter(nameIdentifier));
        FirImplicitReturnTypeAnnotationMissingDependencyChecker.INSTANCE.check(checkerContext, diagnosticReporter, firValueParameter.getReturnTypeRef(), firValueParameter.getReturnTypeRef().getSource());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirValueParameter firValueParameter) {
        FirClassLikeSymbol<?> containingClassSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firValueParameter.getClass();
        FirBasedSymbol<?> containingDeclarationSymbol = firValueParameter.getContainingDeclarationSymbol();
        if (containingDeclarationSymbol instanceof FirAnonymousFunctionSymbol) {
            checkLambdaParameter(checkerContext, diagnosticReporter, firValueParameter);
        } else {
            if (ClassMembersKt.getCorrespondingProperty(firValueParameter) == null || (containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(containingDeclarationSymbol)) == null || !containingClassSymbol.getRawStatus().isData()) {
                return;
            }
            checkDataClassParameter(checkerContext, diagnosticReporter, firValueParameter);
        }
    }
}

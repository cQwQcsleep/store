package org.jetbrains.kotlin.fir.analysis.checkers.type;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.analysis.checkers.FirUpperBoundViolatedHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirUpperBoundViolatedTypeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "typeRef", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUpperBoundViolatedTypeChecker extends FirTypeChecker<FirResolvedTypeRef> {
    public static final FirUpperBoundViolatedTypeChecker INSTANCE = new FirUpperBoundViolatedTypeChecker();

    private FirUpperBoundViolatedTypeChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedTypeRef firResolvedTypeRef) throws KotlinIllegalArgumentExceptionWithAttachments {
        List qualifier;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedTypeRef.getClass();
        boolean z = true;
        FirElement firElement = (FirElement) CollectionsKt.lastOrNull(CollectionsKt.dropLast(checkerContext.getContainingElements(), 1));
        FirUserTypeRef delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef();
        FirUserTypeRef firUserTypeRef = delegatedTypeRef instanceof FirUserTypeRef ? delegatedTypeRef : null;
        FirQualifierPart firQualifierPart = (firUserTypeRef == null || (qualifier = firUserTypeRef.getQualifier()) == null) ? null : (FirQualifierPart) CollectionsKt.lastOrNull(qualifier);
        boolean z2 = firQualifierPart != null && firQualifierPart.getTypeArgumentList().getTypeArguments().isEmpty();
        boolean z3 = CollectionsKt.lastOrNull(CollectionsKt.dropLast(checkerContext.getContainingElements(), 1)) instanceof FirTypeOperatorCall;
        boolean z4 = z3 && z2;
        boolean z5 = (CollectionsKt.lastOrNull(CollectionsKt.dropLast(checkerContext.getContainingElements(), 1)) instanceof FirTypeParameter) && !(CollectionsKt.lastOrNull(CollectionsKt.dropLast(checkerContext.getContainingElements(), 2)) instanceof FirClass);
        if (firElement instanceof FirCallableDeclaration) {
            KtSourceElement source = firResolvedTypeRef.getSource();
            if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) {
                return;
            }
        }
        if ((firElement instanceof FirTypeProjectionWithVariance) || (firElement instanceof FirFunctionTypeParameter) || (firElement instanceof FirFunctionTypeRef) || z4) {
            return;
        }
        boolean z6 = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations()) instanceof FirTypeAliasSymbol;
        if (!z3 && !z5) {
            z = false;
        }
        FirUpperBoundViolatedHelpersKt.checkUpperBoundViolated(checkerContext, diagnosticReporter, firResolvedTypeRef, z6, z);
    }
}

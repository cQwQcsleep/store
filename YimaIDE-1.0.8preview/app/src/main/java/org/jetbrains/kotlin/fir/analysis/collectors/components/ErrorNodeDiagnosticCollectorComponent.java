package org.jetbrains.kotlin.fir.analysis.collectors.components;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.SourceHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.ConeDiagnosticToFirDiagnosticKt;
import org.jetbrains.kotlin.fir.declarations.FirErrorFunction;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirErrorProperty;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.diagnostics.ConeAmbiguousSuper;
import org.jetbrains.kotlin.fir.diagnostics.ConeContextParameterWithDefaultValue;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirErrorAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirErrorLoop;
import org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirErrorSuperReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableWrongReceiver;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInstanceAccessBeforeSuperCall;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 F2\u00020\u0001:\u0001FB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\rH\u0016J\u0014\u0010\u0011\u001a\u00020\u0012*\u00020\u00102\u0006\u0010\f\u001a\u00020\rH\u0002J\f\u0010\u0013\u001a\u00020\u0012*\u00020\u0010H\u0002J\u0014\u0010\u0014\u001a\u00020\u0012*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020&2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\rH\u0002J\u000e\u0010+\u001a\u00020\u0012*\u0004\u0018\u00010\u0015H\u0002J\u0018\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020.2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010/\u001a\u00020\t2\u0006\u00100\u001a\u0002012\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u00102\u001a\u00020\t2\u0006\u00103\u001a\u0002042\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u00105\u001a\u00020\t2\u0006\u00106\u001a\u0002072\u0006\u0010\f\u001a\u00020\rH\u0016J\f\u00108\u001a\u00020\u0012*\u000209H\u0002J\u0018\u0010:\u001a\u00020\t2\u0006\u0010;\u001a\u00020<2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010=\u001a\u00020\t2\u0006\u0010>\u001a\u00020?2\u0006\u0010\f\u001a\u00020\rH\u0016J:\u0010@\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010A\u001a\u0004\u0018\u00010B2\u0006\u0010*\u001a\u00020\r2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010B2\n\b\u0002\u0010D\u001a\u0004\u0018\u00010EH\u0002¨\u0006G"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/components/ErrorNodeDiagnosticCollectorComponent;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;)V", "visitErrorLoop", Argument.Delimiters.none, "errorLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorLoop;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "visitErrorTypeRef", "errorTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "isLambdaReturnTypeRefThatDoesntNeedReporting", Argument.Delimiters.none, "hasExpandedTypeAliasDeclarationSiteError", "hasDiagnostic", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "visitResolvedTypeRef", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "visitErrorAnnotationCall", "errorAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorAnnotationCall;", "visitErrorNamedReference", "errorNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirErrorNamedReference;", "visitResolvedErrorReference", "resolvedErrorReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedErrorReference;", "visitErrorSuperReference", "errorSuperReference", "Lorg/jetbrains/kotlin/fir/references/FirErrorSuperReference;", "processErrorReference", "reference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "context", "cannotBeResolved", "visitErrorExpression", "errorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;", "visitErrorFunction", "errorFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorFunction;", "visitErrorProperty", "errorProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorProperty;", "visitErrorResolvedQualifier", "errorResolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "hasErrorOrParentWithError", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "visitErrorPrimaryConstructor", "errorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "visitThisReference", "thisReference", "Lorg/jetbrains/kotlin/fir/references/FirThisReference;", "reportFirDiagnostic", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "callOrAssignmentSource", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Companion", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ErrorNodeDiagnosticCollectorComponent extends AbstractDiagnosticCollectorComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorNodeDiagnosticCollectorComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter) {
        super(firSession, pendingDiagnosticReporter);
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
    }

    private final boolean cannotBeResolved(FirExpression firExpression) {
        ConeKotlinType resolvedType;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = (firExpression == null || (resolvedType = FirTypeUtilsKt.getResolvedType(firExpression)) == null) ? null : ConeTypeUtilsKt.lowerBoundIfFlexible(resolvedType);
        ConeErrorType coneErrorType = coneRigidTypeLowerBoundIfFlexible instanceof ConeErrorType ? (ConeErrorType) coneRigidTypeLowerBoundIfFlexible : null;
        ConeDiagnostic diagnostic = coneErrorType != null ? coneErrorType.getDiagnostic() : null;
        if ((diagnostic instanceof ConeUnresolvedNameError) || (diagnostic instanceof ConeInstanceAccessBeforeSuperCall) || (diagnostic instanceof ConeAmbiguousSuper)) {
            return true;
        }
        if (!(diagnostic instanceof ConeSimpleDiagnostic)) {
            return false;
        }
        ConeSimpleDiagnostic coneSimpleDiagnostic = (ConeSimpleDiagnostic) diagnostic;
        return coneSimpleDiagnostic.getKind() == DiagnosticKind.NotASupertype || coneSimpleDiagnostic.getKind() == DiagnosticKind.SuperNotAvailable || coneSimpleDiagnostic.getKind() == DiagnosticKind.UnresolvedLabel || coneSimpleDiagnostic.getKind() == DiagnosticKind.AmbiguousLabel;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean hasDiagnostic(FirExpression firExpression, ConeDiagnostic coneDiagnostic) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
        ConeErrorType coneErrorType = resolvedType instanceof ConeErrorType ? (ConeErrorType) resolvedType : null;
        if (Intrinsics.areEqual(coneErrorType != null ? coneErrorType.getDiagnostic() : null, coneDiagnostic)) {
            return true;
        }
        Object reference = ReferenceUtilsKt.toReference(firExpression, getSession());
        FirDiagnosticHolder firDiagnosticHolder = reference instanceof FirDiagnosticHolder ? (FirDiagnosticHolder) reference : null;
        return Intrinsics.areEqual(firDiagnosticHolder != null ? firDiagnosticHolder.getDiagnostic() : null, coneDiagnostic);
    }

    private final boolean hasErrorOrParentWithError(FirResolvedQualifier firResolvedQualifier) {
        if (firResolvedQualifier instanceof FirErrorResolvedQualifier) {
            return true;
        }
        FirResolvedQualifier explicitParent = firResolvedQualifier.getExplicitParent();
        return explicitParent != null && hasErrorOrParentWithError(explicitParent);
    }

    private final boolean hasExpandedTypeAliasDeclarationSiteError(FirErrorTypeRef firErrorTypeRef) {
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(firErrorTypeRef.getConeType());
        ConeErrorType coneErrorType = coneRigidTypeLowerBoundIfFlexible instanceof ConeErrorType ? (ConeErrorType) coneRigidTypeLowerBoundIfFlexible : null;
        return (coneErrorType == null || !Intrinsics.areEqual(coneErrorType.getDiagnostic(), firErrorTypeRef.getDiagnostic()) || AbbreviatedTypeAttributeKt.getAbbreviatedType(coneErrorType) == null) ? false : true;
    }

    private final boolean isLambdaReturnTypeRefThatDoesntNeedReporting(FirErrorTypeRef firErrorTypeRef, CheckerContext checkerContext) {
        KtSourceElement source = firErrorTypeRef.getSource();
        if (!Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitFunctionReturnType.INSTANCE)) {
            return false;
        }
        FirBasedSymbol firBasedSymbol = (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        if (firBasedSymbol instanceof FirAnonymousFunctionSymbol) {
            FirAnonymousFunctionSymbol firAnonymousFunctionSymbol = (FirAnonymousFunctionSymbol) firBasedSymbol;
            if (Intrinsics.areEqual(firAnonymousFunctionSymbol.getResolvedReturnTypeRef(), firErrorTypeRef)) {
                List<FirExpression> returnedExpressions = FirHelpersKt.getReturnedExpressions(firAnonymousFunctionSymbol);
                if (!(returnedExpressions instanceof Collection) || !returnedExpressions.isEmpty()) {
                    Iterator<T> it = returnedExpressions.iterator();
                    while (it.hasNext()) {
                        if (hasDiagnostic((FirExpression) it.next(), firErrorTypeRef.getDiagnostic())) {
                            return true;
                        }
                    }
                }
                List<FirStatement> callsOrAssignments = checkerContext.getCallsOrAssignments();
                if (!(callsOrAssignments instanceof Collection) || !callsOrAssignments.isEmpty()) {
                    for (FirStatement firStatement : callsOrAssignments) {
                        if ((firStatement instanceof FirExpression) && hasDiagnostic((FirExpression) firStatement, firErrorTypeRef.getDiagnostic())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private final void processErrorReference(FirReference reference, ConeDiagnostic diagnostic, CheckerContext context) {
        KtSourceElement source = reference.getSource();
        FirStatement firStatement = (FirStatement) CollectionsKt.lastOrNull(context.getCallsOrAssignments());
        if (firStatement == null || !Intrinsics.areEqual(ReferenceUtilsKt.toReference(firStatement, getSession()), reference)) {
            firStatement = null;
        }
        if (Intrinsics.areEqual(source != null ? source.getElementType() : null, KtNodeTypes.ANNOTATION_ENTRY) && (diagnostic instanceof ConeUnresolvedNameError)) {
            return;
        }
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ArrayAccessNameReference.INSTANCE) && (diagnostic instanceof ConeUnresolvedNameError)) {
            return;
        }
        if (firStatement instanceof FirQualifiedAccessExpression) {
            FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) firStatement;
            if (cannotBeResolved(firQualifiedAccessExpression.getDispatchReceiver()) || cannotBeResolved(firQualifiedAccessExpression.getExtensionReceiver()) || cannotBeResolved(firQualifiedAccessExpression.getExplicitReceiver())) {
                return;
            }
        }
        reportFirDiagnostic$default(this, diagnostic, source != null ? SourceHelpersKt.delegatedPropertySourceOrThis(context, source) : null, context, firStatement != null ? firStatement.getSource() : null, null, 16, null);
    }

    private final void reportFirDiagnostic(ConeDiagnostic diagnostic, KtSourceElement source, CheckerContext context, KtSourceElement callOrAssignmentSource, FirValueParameter valueParameter) {
        INSTANCE.reportFirDiagnostic$org_jetbrains_kotlin_checkers(diagnostic, source, context, getSession(), getReporter(), callOrAssignmentSource, valueParameter);
    }

    public static /* synthetic */ void reportFirDiagnostic$default(ErrorNodeDiagnosticCollectorComponent errorNodeDiagnosticCollectorComponent, ConeDiagnostic coneDiagnostic, KtSourceElement ktSourceElement, CheckerContext checkerContext, KtSourceElement ktSourceElement2, FirValueParameter firValueParameter, int i, Object obj) {
        if ((i & 8) != 0) {
            ktSourceElement2 = null;
        }
        if ((i & 16) != 0) {
            firValueParameter = null;
        }
        errorNodeDiagnosticCollectorComponent.reportFirDiagnostic(coneDiagnostic, ktSourceElement, checkerContext, ktSourceElement2, firValueParameter);
    }

    /* JADX INFO: renamed from: visitErrorAnnotationCall, reason: avoid collision after fix types in other method */
    public void visitErrorAnnotationCall2(FirErrorAnnotationCall errorAnnotationCall, CheckerContext data) {
        errorAnnotationCall.getClass();
        data.getClass();
        reportFirDiagnostic$default(this, errorAnnotationCall.getDiagnostic(), errorAnnotationCall.getSource(), data, null, null, 24, null);
    }

    /* JADX INFO: renamed from: visitErrorExpression, reason: avoid collision after fix types in other method */
    public void visitErrorExpression2(FirErrorExpression errorExpression, CheckerContext data) {
        errorExpression.getClass();
        data.getClass();
        ConeDiagnostic diagnostic = errorExpression.getDiagnostic();
        if (diagnostic instanceof ConeSyntaxDiagnostic) {
            return;
        }
        if (Intrinsics.areEqual(diagnostic, ConeContextParameterWithDefaultValue.INSTANCE)) {
            List<FirBasedSymbol<?>> containingDeclarations = data.getContainingDeclarations();
            if (FirHelpersKt.isPrimaryConstructor((FirBasedSymbol) CollectionsKt.getOrNull(containingDeclarations, CollectionsKt.getLastIndex(containingDeclarations) - 1))) {
                return;
            }
        }
        reportFirDiagnostic$default(this, diagnostic, errorExpression.getSource(), data, null, null, 24, null);
    }

    /* JADX INFO: renamed from: visitErrorFunction, reason: avoid collision after fix types in other method */
    public void visitErrorFunction2(FirErrorFunction errorFunction, CheckerContext data) {
        errorFunction.getClass();
        data.getClass();
        reportFirDiagnostic$default(this, errorFunction.getDiagnostic(), errorFunction.getSource(), data, null, null, 24, null);
    }

    /* JADX INFO: renamed from: visitErrorLoop, reason: avoid collision after fix types in other method */
    public void visitErrorLoop2(FirErrorLoop errorLoop, CheckerContext data) {
        errorLoop.getClass();
        data.getClass();
        reportFirDiagnostic$default(this, errorLoop.getDiagnostic(), errorLoop.getSource(), data, null, null, 24, null);
    }

    /* JADX INFO: renamed from: visitErrorNamedReference, reason: avoid collision after fix types in other method */
    public void visitErrorNamedReference2(FirErrorNamedReference errorNamedReference, CheckerContext data) {
        errorNamedReference.getClass();
        data.getClass();
        processErrorReference(errorNamedReference, errorNamedReference.getDiagnostic(), data);
    }

    /* JADX INFO: renamed from: visitErrorPrimaryConstructor, reason: avoid collision after fix types in other method */
    public void visitErrorPrimaryConstructor2(FirErrorPrimaryConstructor errorPrimaryConstructor, CheckerContext data) {
        errorPrimaryConstructor.getClass();
        data.getClass();
        reportFirDiagnostic$default(this, errorPrimaryConstructor.getDiagnostic(), errorPrimaryConstructor.getSource(), data, null, null, 24, null);
    }

    /* JADX INFO: renamed from: visitErrorProperty, reason: avoid collision after fix types in other method */
    public void visitErrorProperty2(FirErrorProperty errorProperty, CheckerContext data) {
        errorProperty.getClass();
        data.getClass();
        reportFirDiagnostic$default(this, errorProperty.getDiagnostic(), errorProperty.getSource(), data, null, null, 24, null);
    }

    /* JADX INFO: renamed from: visitErrorResolvedQualifier, reason: avoid collision after fix types in other method */
    public void visitErrorResolvedQualifier2(FirErrorResolvedQualifier errorResolvedQualifier, CheckerContext data) {
        errorResolvedQualifier.getClass();
        data.getClass();
        FirResolvedQualifier explicitParent = errorResolvedQualifier.getExplicitParent();
        if (explicitParent == null || !hasErrorOrParentWithError(explicitParent)) {
            reportFirDiagnostic$default(this, errorResolvedQualifier.getDiagnostic(), errorResolvedQualifier.getSource(), data, null, null, 24, null);
        }
    }

    /* JADX INFO: renamed from: visitErrorSuperReference, reason: avoid collision after fix types in other method */
    public void visitErrorSuperReference2(FirErrorSuperReference errorSuperReference, CheckerContext data) {
        errorSuperReference.getClass();
        data.getClass();
        processErrorReference(errorSuperReference, errorSuperReference.getDiagnostic(), data);
    }

    /* JADX INFO: renamed from: visitErrorTypeRef, reason: avoid collision after fix types in other method */
    public void visitErrorTypeRef2(FirErrorTypeRef errorTypeRef, CheckerContext data) {
        errorTypeRef.getClass();
        data.getClass();
        if (isLambdaReturnTypeRefThatDoesntNeedReporting(errorTypeRef, data) || hasExpandedTypeAliasDeclarationSiteError(errorTypeRef)) {
            return;
        }
        ConeDiagnostic diagnostic = errorTypeRef.getDiagnostic();
        KtSourceElement source = errorTypeRef.getSource();
        Object orNull = CollectionsKt.getOrNull(data.getContainingElements(), CollectionsKt.getLastIndex(data.getContainingElements()) - 1);
        reportFirDiagnostic$default(this, diagnostic, source, data, null, orNull instanceof FirValueParameter ? (FirValueParameter) orNull : null, 8, null);
    }

    /* JADX INFO: renamed from: visitResolvedErrorReference, reason: avoid collision after fix types in other method */
    public void visitResolvedErrorReference2(FirResolvedErrorReference resolvedErrorReference, CheckerContext data) {
        resolvedErrorReference.getClass();
        data.getClass();
        processErrorReference(resolvedErrorReference, resolvedErrorReference.getDiagnostic(), data);
    }

    /* JADX INFO: renamed from: visitResolvedTypeRef, reason: avoid collision after fix types in other method */
    public void visitResolvedTypeRef2(FirResolvedTypeRef resolvedTypeRef, CheckerContext data) {
        resolvedTypeRef.getClass();
        data.getClass();
        resolvedTypeRef.getConeType();
    }

    /* JADX INFO: renamed from: visitThisReference, reason: avoid collision after fix types in other method */
    public void visitThisReference2(FirThisReference thisReference, CheckerContext data) {
        thisReference.getClass();
        data.getClass();
        ConeDiagnostic diagnostic = thisReference.getDiagnostic();
        if (diagnostic == null) {
            return;
        }
        KtSourceElement source = thisReference.getSource();
        if (source == null) {
            FirElement firElement = (FirElement) CollectionsKt.getOrNull(data.getContainingElements(), 1);
            source = firElement != null ? firElement.getSource() : null;
        }
        reportFirDiagnostic$default(this, diagnostic, source, data, null, null, 24, null);
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JQ\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0000¢\u0006\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/components/ErrorNodeDiagnosticCollectorComponent$Companion;", Argument.Delimiters.none, "<init>", "()V", "reportFirDiagnostic", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "callOrAssignmentSource", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "reportFirDiagnostic$org_jetbrains_kotlin_checkers", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void reportFirDiagnostic$org_jetbrains_kotlin_checkers$default(Companion companion, ConeDiagnostic coneDiagnostic, KtSourceElement ktSourceElement, CheckerContext checkerContext, FirSession firSession, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement2, FirValueParameter firValueParameter, int i, Object obj) {
            if ((i & 8) != 0) {
                firSession = checkerContext.getSession();
            }
            companion.reportFirDiagnostic$org_jetbrains_kotlin_checkers(coneDiagnostic, ktSourceElement, checkerContext, firSession, diagnosticReporter, (i & 32) != 0 ? null : ktSourceElement2, (i & 64) != 0 ? null : firValueParameter);
        }

        public final void reportFirDiagnostic$org_jetbrains_kotlin_checkers(ConeDiagnostic diagnostic, KtSourceElement source, CheckerContext context, FirSession session, DiagnosticReporter reporter, KtSourceElement callOrAssignmentSource, FirValueParameter valueParameter) {
            diagnostic.getClass();
            context.getClass();
            session.getClass();
            reporter.getClass();
            if (!Intrinsics.areEqual(source != null ? source.getElementType() : null, KtNodeTypes.DESTRUCTURING_DECLARATION_ENTRY) || Intrinsics.areEqual(source.getKind(), KtFakeSourceElementKind.DesugaredNameBasedDestructuring.INSTANCE)) {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE) && ((diagnostic instanceof ConeUnresolvedNameError) || (diagnostic instanceof ConeAmbiguityError) || (diagnostic instanceof ConeInapplicableWrongReceiver) || (diagnostic instanceof ConeInapplicableCandidateError))) {
                    return;
                }
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitConstructor.INSTANCE)) {
                    return;
                }
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DesugaredForLoop.INSTANCE)) {
                    return;
                }
                if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredPrefixSecondGetReference) {
                    return;
                }
                if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.UnresolvedWhenConditionSubject) {
                    return;
                }
                Iterator<KtDiagnostic> it = ConeDiagnosticToFirDiagnosticKt.toFirDiagnostics(diagnostic, session, source, callOrAssignmentSource, valueParameter).iterator();
                while (it.hasNext()) {
                    reporter.report(it.next(), context);
                }
            }
        }

        private Companion() {
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitResolvedTypeRef(FirResolvedTypeRef firResolvedTypeRef, CheckerContext checkerContext) {
        visitResolvedTypeRef2(firResolvedTypeRef, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorNamedReference(FirErrorNamedReference firErrorNamedReference, CheckerContext checkerContext) {
        visitErrorNamedReference2(firErrorNamedReference, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorSuperReference(FirErrorSuperReference firErrorSuperReference, CheckerContext checkerContext) {
        visitErrorSuperReference2(firErrorSuperReference, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitResolvedErrorReference(FirResolvedErrorReference firResolvedErrorReference, CheckerContext checkerContext) {
        visitResolvedErrorReference2(firResolvedErrorReference, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorAnnotationCall(FirErrorAnnotationCall firErrorAnnotationCall, CheckerContext checkerContext) {
        visitErrorAnnotationCall2(firErrorAnnotationCall, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorFunction(FirErrorFunction firErrorFunction, CheckerContext checkerContext) {
        visitErrorFunction2(firErrorFunction, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorLoop(FirErrorLoop firErrorLoop, CheckerContext checkerContext) {
        visitErrorLoop2(firErrorLoop, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorPrimaryConstructor(FirErrorPrimaryConstructor firErrorPrimaryConstructor, CheckerContext checkerContext) {
        visitErrorPrimaryConstructor2(firErrorPrimaryConstructor, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorProperty(FirErrorProperty firErrorProperty, CheckerContext checkerContext) {
        visitErrorProperty2(firErrorProperty, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorResolvedQualifier(FirErrorResolvedQualifier firErrorResolvedQualifier, CheckerContext checkerContext) {
        visitErrorResolvedQualifier2(firErrorResolvedQualifier, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitThisReference(FirThisReference firThisReference, CheckerContext checkerContext) {
        visitThisReference2(firThisReference, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorExpression(FirErrorExpression firErrorExpression, CheckerContext checkerContext) {
        visitErrorExpression2(firErrorExpression, checkerContext);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorTypeRef(FirErrorTypeRef firErrorTypeRef, CheckerContext checkerContext) {
        visitErrorTypeRef2(firErrorTypeRef, checkerContext);
        return Unit.INSTANCE;
    }
}

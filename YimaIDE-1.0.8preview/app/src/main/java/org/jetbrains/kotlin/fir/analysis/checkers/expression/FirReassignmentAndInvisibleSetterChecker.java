package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.analysis.cfa.FirPropertyInitializationAnalyzerKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.references.FirBackingFieldReference;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDiagnosticWithCandidates;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVisibilityError;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u0013\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u0014\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u0015\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\f\u0010\u0016\u001a\u00020\u0010*\u00020\u0011H\u0002J-\u0010\u0017\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ!\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001bJ+\u0010\u001c\u001a\u00020\u00102\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001fR\u0018\u0010\u000f\u001a\u00020\u0010*\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0012¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReassignmentAndInvisibleSetterChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirVariableAssignmentChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;)V", "checkInvisibleSetter", "isVisibilityError", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/references/FirReference;", "(Lorg/jetbrains/kotlin/fir/references/FirReference;)Z", "checkValReassignmentViaBackingField", "checkValReassignmentOnValueParameterOrEnumEntry", "checkVariableExpected", "isConflictingError", "checkValReassignment", "isInFileGraph", "property", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Z", "isInOwnersInitializer", "receiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReassignmentAndInvisibleSetterChecker extends FirExpressionChecker<FirVariableAssignment> {
    public static final FirReassignmentAndInvisibleSetterChecker INSTANCE = new FirReassignmentAndInvisibleSetterChecker();

    private FirReassignmentAndInvisibleSetterChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkInvisibleSetter(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirVariableAssignment firVariableAssignment) {
        Visibility visibility;
        FirReference calleeReference = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.getCalleeReference(firVariableAssignment);
        if (calleeReference == null || !isVisibilityError(calleeReference)) {
            FirReference calleeReference2 = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.getCalleeReference(firVariableAssignment);
            FirCallableSymbol resolvedCallableSymbol$default = calleeReference2 != null ? FirReferenceUtilsKt.toResolvedCallableSymbol$default(calleeReference2, false, 1, null) : null;
            if (resolvedCallableSymbol$default instanceof FirPropertySymbol) {
                FirPropertySymbol firPropertySymbol = (FirPropertySymbol) resolvedCallableSymbol$default;
                if (checkInvisibleSetter$shouldInvisibleSetterBeReported(checkerContext, firVariableAssignment, firPropertySymbol)) {
                    KtSourceElement source = firVariableAssignment.getLValue().getSource();
                    KtDiagnosticFactory3<FirPropertySymbol, Visibility, CallableId> invisible_setter = FirErrors.INSTANCE.getINVISIBLE_SETTER();
                    FirPropertyAccessorSymbol setterSymbol = firPropertySymbol.getSetterSymbol();
                    if (setterSymbol == null || (visibility = setterSymbol.getResolvedStatus().getVisibility()) == null) {
                        visibility = Visibilities.Private.INSTANCE;
                    }
                    Visibility visibility2 = visibility;
                    CallableId callableId = firPropertySymbol.getCallableId();
                    callableId.getClass();
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory3<FirCallableSymbol, Visibility, CallableId>) ((KtDiagnosticFactory3<Object, Object, Object>) invisible_setter), resolvedCallableSymbol$default, visibility2, callableId, (64 & 64) != 0 ? null : null);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean checkInvisibleSetter$shouldInvisibleSetterBeReported(CheckerContext checkerContext, FirVariableAssignment firVariableAssignment, FirPropertySymbol firPropertySymbol) {
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firPropertySymbol.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol");
            return false;
        }
        FirPropertyAccessorSymbol setterSymbol = ((FirPropertySymbol) symbol).getSetterSymbol();
        if (setterSymbol == null) {
            return false;
        }
        FirVisibilityChecker visibilityChecker = FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession());
        FirSession session = checkerContext.getSession();
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        containingFileSymbol.getClass();
        return !FirVisibilityCheckerKt.isVisible$default(visibilityChecker, setterSymbol, session, containingFileSymbol, checkerContext.getContainingDeclarations(), FirExpressionUtilKt.getDispatchReceiver(firVariableAssignment), false, 32, null);
    }

    private final void checkValReassignment(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirVariableAssignment firVariableAssignment) {
        FirReference calleeReference = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.getCalleeReference(firVariableAssignment);
        if (calleeReference != null) {
            FirVariableSymbol resolvedVariableSymbol$default = FirReferenceUtilsKt.toResolvedVariableSymbol$default(calleeReference, false, 1, null);
            if (resolvedVariableSymbol$default == null || resolvedVariableSymbol$default.isVar()) {
                return;
            }
            if (resolvedVariableSymbol$default instanceof FirPropertySymbol) {
                if (((resolvedVariableSymbol$default instanceof FirLocalPropertySymbol) || isInFileGraph(checkerContext, (FirPropertySymbol) resolvedVariableSymbol$default)) && FirPropertyInitializationAnalyzerKt.requiresInitialization((FirPropertySymbol) resolvedVariableSymbol$default, false)) {
                    return;
                }
                FirPropertySymbol firPropertySymbol = (FirPropertySymbol) resolvedVariableSymbol$default;
                if (FirPropertyInitializationAnalyzerKt.requiresInitialization(firPropertySymbol, true)) {
                    FirExpression dispatchReceiver = FirExpressionUtilKt.getDispatchReceiver(firVariableAssignment);
                    if (isInOwnersInitializer(checkerContext, dispatchReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(dispatchReceiver) : null, firPropertySymbol)) {
                        return;
                    }
                }
            } else if (!(resolvedVariableSymbol$default instanceof FirFieldSymbol)) {
                if ((resolvedVariableSymbol$default instanceof FirBackingFieldSymbol) || (resolvedVariableSymbol$default instanceof FirDelegateFieldSymbol) || (resolvedVariableSymbol$default instanceof FirValueParameterSymbol) || (resolvedVariableSymbol$default instanceof FirEnumEntrySymbol)) {
                    return;
                }
                bu8.a();
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firVariableAssignment.getLValue().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getVAL_REASSIGNMENT(), (Object) resolvedVariableSymbol$default, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final void checkValReassignmentOnValueParameterOrEnumEntry(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirVariableAssignment firVariableAssignment) {
        FirReference calleeReference = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.getCalleeReference(firVariableAssignment);
        FirVariableSymbol resolvedVariableSymbol$default = calleeReference != null ? FirReferenceUtilsKt.toResolvedVariableSymbol$default(calleeReference, false, 1, null) : null;
        if ((resolvedVariableSymbol$default instanceof FirValueParameterSymbol) || (resolvedVariableSymbol$default instanceof FirEnumEntrySymbol)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firVariableAssignment.getLValue().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getVAL_REASSIGNMENT(), (Object) resolvedVariableSymbol$default, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final void checkValReassignmentViaBackingField(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirVariableAssignment firVariableAssignment) {
        FirReference calleeReference = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.getCalleeReference(firVariableAssignment);
        FirPropertyAccessorSymbol firPropertyAccessorSymbol = null;
        FirBackingFieldReference firBackingFieldReference = calleeReference instanceof FirBackingFieldReference ? (FirBackingFieldReference) calleeReference : null;
        if (firBackingFieldReference == null) {
            return;
        }
        FirBackingFieldSymbol resolvedSymbol = firBackingFieldReference.getResolvedSymbol();
        if (resolvedSymbol.isVar()) {
            return;
        }
        for (FirBasedSymbol firBasedSymbol : CollectionsKt.asReversed(checkerContext.getContainingDeclarations())) {
            if (!(firBasedSymbol instanceof FirPropertyAccessorSymbol)) {
                firBasedSymbol = null;
            }
            FirPropertyAccessorSymbol firPropertyAccessorSymbol2 = (FirPropertyAccessorSymbol) firBasedSymbol;
            if (firPropertyAccessorSymbol2 != null) {
                if (!firPropertyAccessorSymbol2.isGetter()) {
                    firPropertyAccessorSymbol2 = null;
                }
                if (firPropertyAccessorSymbol2 != null) {
                    firPropertyAccessorSymbol = firPropertyAccessorSymbol2;
                    break;
                }
            }
        }
        if (firPropertyAccessorSymbol != null && Intrinsics.areEqual(resolvedSymbol.getGetterSymbol(), firPropertyAccessorSymbol)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firBackingFieldReference.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getVAL_REASSIGNMENT_VIA_BACKING_FIELD_ERROR(), (Object) resolvedSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final void checkVariableExpected(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirVariableAssignment firVariableAssignment) {
        FirReference calleeReference = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.getCalleeReference(firVariableAssignment);
        if (FirExpressionUtilKt.unwrapLValue(firVariableAssignment) instanceof FirPropertyAccessExpression) {
            if (calleeReference != null && isConflictingError(calleeReference)) {
                return;
            }
            if ((calleeReference != null ? FirReferenceUtilsKt.toResolvedVariableSymbol$default(calleeReference, false, 1, null) : null) != null) {
                return;
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firVariableAssignment.getLValue().getSource(), FirErrors.INSTANCE.getVARIABLE_EXPECTED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isConflictingError(FirReference firReference) {
        if (!FirReferenceUtilsKt.isError(firReference)) {
            return false;
        }
        ConeDiagnostic diagnostic = ((FirDiagnosticHolder) firReference).getDiagnostic();
        if (diagnostic instanceof ConeSimpleDiagnostic) {
            return ((ConeSimpleDiagnostic) diagnostic).getKind() == DiagnosticKind.VariableExpected;
        }
        if (diagnostic instanceof ConeUnresolvedNameError) {
            return true;
        }
        if (diagnostic instanceof ConeDiagnosticWithCandidates) {
            Collection<AbstractCandidate> candidates = ((ConeDiagnosticWithCandidates) diagnostic).getCandidates();
            if ((candidates instanceof Collection) && candidates.isEmpty()) {
                return false;
            }
            Iterator<T> it = candidates.iterator();
            while (it.hasNext()) {
                if (((AbstractCandidate) it.next()).getSymbol() instanceof FirPropertySymbol) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean isInFileGraph(CheckerContext checkerContext, FirPropertySymbol firPropertySymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirControlFlowGraphReference controlFlowGraphReference;
        List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (Object obj : containingDeclarations) {
            if (z) {
                arrayList.add(obj);
            } else if (((FirBasedSymbol) obj) instanceof FirFileSymbol) {
                arrayList.add(obj);
                z = true;
            }
        }
        Object objFirstOrNull = CollectionsKt.firstOrNull(arrayList);
        Object obj2 = null;
        FirFileSymbol firFileSymbol = objFirstOrNull instanceof FirFileSymbol ? (FirFileSymbol) objFirstOrNull : null;
        if (firFileSymbol == null || !Intrinsics.areEqual(firFileSymbol, ContainingClassUtilsKt.getContainingSymbol(firPropertySymbol, checkerContext.getSession()))) {
            return false;
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            FirAnnotationContainer fir = ((FirBasedSymbol) it.next()).getFir();
            FirControlFlowGraphOwner firControlFlowGraphOwner = fir instanceof FirControlFlowGraphOwner ? (FirControlFlowGraphOwner) fir : null;
            arrayList2.add((firControlFlowGraphOwner == null || (controlFlowGraphReference = firControlFlowGraphOwner.getControlFlowGraphReference()) == null) ? null : FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference));
        }
        Iterator it2 = arrayList2.iterator();
        if (it2.hasNext()) {
            Object next = it2.next();
            while (it2.hasNext()) {
                ControlFlowGraph controlFlowGraph = (ControlFlowGraph) it2.next();
                ControlFlowGraph controlFlowGraph2 = (ControlFlowGraph) next;
                next = (controlFlowGraph == null || controlFlowGraph2 == null || !controlFlowGraph2.getSubGraphs().contains(controlFlowGraph)) ? null : controlFlowGraph;
            }
            obj2 = next;
        }
        return ((ControlFlowGraph) obj2) != null;
    }

    private final boolean isInOwnersInitializer(CheckerContext checkerContext, FirExpression firExpression, FirPropertySymbol firPropertySymbol) {
        FirThisReference calleeReference;
        FirThisOwnerSymbol<?> boundSymbol;
        FirBasedSymbol firBasedSymbol;
        FirThisReceiverExpression firThisReceiverExpression = firExpression instanceof FirThisReceiverExpression ? (FirThisReceiverExpression) firExpression : null;
        if (firThisReceiverExpression == null || (calleeReference = firThisReceiverExpression.getCalleeReference()) == null || (boundSymbol = calleeReference.getBoundSymbol()) == null || !Intrinsics.areEqual(boundSymbol, ContainingClassUtilsKt.getContainingSymbol(firPropertySymbol, checkerContext.getSession()))) {
            return false;
        }
        List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
        Iterator<FirBasedSymbol<?>> it = containingDeclarations.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (Intrinsics.areEqual(it.next(), boundSymbol)) {
                break;
            }
            i++;
        }
        if (i == -1) {
            return false;
        }
        int size = containingDeclarations.size();
        while (i < size) {
            if (containingDeclarations.get(i) instanceof FirClassSymbol) {
                FirBasedSymbol firBasedSymbol2 = (FirBasedSymbol) CollectionsKt.getOrNull(containingDeclarations, i + 1);
                if ((firBasedSymbol2 instanceof FirPropertySymbol) && (firBasedSymbol = (FirBasedSymbol) CollectionsKt.getOrNull(containingDeclarations, i + 2)) != null) {
                    if (!(firBasedSymbol instanceof FirPropertyAccessorSymbol)) {
                        firBasedSymbol = null;
                    }
                    if (firBasedSymbol != null) {
                        firBasedSymbol2 = firBasedSymbol;
                    }
                }
                if (firBasedSymbol2 != null && !FirPropertyInitializationAnalyzerKt.getEvaluatedInPlace((FirBasedSymbol<?>) firBasedSymbol2)) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }

    private final boolean isVisibilityError(FirReference firReference) {
        return (firReference instanceof FirResolvedErrorReference) && (((FirResolvedErrorReference) firReference).getDiagnostic() instanceof ConeVisibilityError);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirVariableAssignment firVariableAssignment) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firVariableAssignment.getClass();
        checkInvisibleSetter(checkerContext, diagnosticReporter, firVariableAssignment);
        checkValReassignmentViaBackingField(checkerContext, diagnosticReporter, firVariableAssignment);
        checkValReassignmentOnValueParameterOrEnumEntry(checkerContext, diagnosticReporter, firVariableAssignment);
        checkVariableExpected(checkerContext, diagnosticReporter, firVariableAssignment);
        checkValReassignment(checkerContext, diagnosticReporter, firVariableAssignment);
    }
}

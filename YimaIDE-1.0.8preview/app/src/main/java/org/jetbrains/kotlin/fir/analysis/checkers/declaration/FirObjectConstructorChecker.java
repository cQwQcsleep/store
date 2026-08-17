package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0001\u0011B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rR\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirObjectConstructorChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "objectRefVisitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirObjectConstructorChecker$Data;", "Data", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirObjectConstructorChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirObjectConstructorChecker INSTANCE = new FirObjectConstructorChecker();
    private static final FirVisitor<Unit, Data> objectRefVisitor = new FirVisitor<Unit, Data>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirObjectConstructorChecker$objectRefVisitor$1
        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitAnonymousObjectExpression(FirAnonymousObjectExpression firAnonymousObjectExpression, FirObjectConstructorChecker.Data data) {
            visitAnonymousObjectExpression2(firAnonymousObjectExpression, data);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitCallableReferenceAccess(FirCallableReferenceAccess firCallableReferenceAccess, FirObjectConstructorChecker.Data data) {
            visitCallableReferenceAccess2(firCallableReferenceAccess, data);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: visitElement, reason: avoid collision after fix types in other method */
        public void visitElement2(FirElement element, FirObjectConstructorChecker.Data data) {
            element.getClass();
            data.getClass();
            element.acceptChildren(this, data);
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitGetClassCall(FirGetClassCall firGetClassCall, FirObjectConstructorChecker.Data data) {
            visitGetClassCall2(firGetClassCall, data);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: visitResolvedQualifier, reason: avoid collision after fix types in other method */
        public void visitResolvedQualifier2(FirResolvedQualifier resolvedQualifier, FirObjectConstructorChecker.Data data) {
            FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
            resolvedQualifier.getClass();
            data.getClass();
            if (Intrinsics.areEqual(resolvedQualifier.getSymbol(), data.getObjectSymbol())) {
                KtDiagnosticReportHelpersKt.reportOn$default(data.getReporter(), (AbstractKtSourceElement) resolvedQualifier.getSource(), FirErrors.INSTANCE.getSELF_CALL_IN_NESTED_OBJECT_CONSTRUCTOR_ERROR(), (DiagnosticContext) data.getContext(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else if (resolvedQualifier.getResolvedToCompanionObject()) {
                FirClassLikeSymbol<?> symbol = resolvedQualifier.getSymbol();
                if (Intrinsics.areEqual((symbol == null || (firRegularClassSymbolFullyExpandedClass = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.fullyExpandedClass(symbol, data.getContext().getSession())) == null) ? null : firRegularClassSymbolFullyExpandedClass.getResolvedCompanionObjectSymbol(), data.getObjectSymbol())) {
                    KtDiagnosticReportHelpersKt.reportOn$default(data.getReporter(), (AbstractKtSourceElement) resolvedQualifier.getSource(), FirErrors.INSTANCE.getSELF_CALL_IN_NESTED_OBJECT_CONSTRUCTOR_ERROR(), (DiagnosticContext) data.getContext(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }

        /* JADX INFO: renamed from: visitThisReceiverExpression, reason: avoid collision after fix types in other method */
        public void visitThisReceiverExpression2(FirThisReceiverExpression thisReceiverExpression, FirObjectConstructorChecker.Data data) {
            thisReceiverExpression.getClass();
            data.getClass();
            if (Intrinsics.areEqual(thisReceiverExpression.getCalleeReference().getBoundSymbol(), data.getObjectSymbol())) {
                KtDiagnosticReportHelpersKt.reportOn$default(data.getReporter(), (AbstractKtSourceElement) thisReceiverExpression.getSource(), FirErrors.INSTANCE.getSELF_CALL_IN_NESTED_OBJECT_CONSTRUCTOR_ERROR(), (DiagnosticContext) data.getContext(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }

        /* JADX INFO: renamed from: visitAnonymousObjectExpression, reason: avoid collision after fix types in other method */
        public void visitAnonymousObjectExpression2(FirAnonymousObjectExpression anonymousObjectExpression, FirObjectConstructorChecker.Data data) {
            anonymousObjectExpression.getClass();
            data.getClass();
        }

        /* JADX INFO: renamed from: visitCallableReferenceAccess, reason: avoid collision after fix types in other method */
        public void visitCallableReferenceAccess2(FirCallableReferenceAccess callableReferenceAccess, FirObjectConstructorChecker.Data data) {
            callableReferenceAccess.getClass();
            data.getClass();
        }

        /* JADX INFO: renamed from: visitGetClassCall, reason: avoid collision after fix types in other method */
        public void visitGetClassCall2(FirGetClassCall getClassCall, FirObjectConstructorChecker.Data data) {
            getClassCall.getClass();
            data.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitElement(FirElement firElement, FirObjectConstructorChecker.Data data) {
            visitElement2(firElement, data);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitThisReceiverExpression(FirThisReceiverExpression firThisReceiverExpression, FirObjectConstructorChecker.Data data) {
            visitThisReceiverExpression2(firThisReceiverExpression, data);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Unit visitResolvedQualifier(FirResolvedQualifier firResolvedQualifier, FirObjectConstructorChecker.Data data) {
            visitResolvedQualifier2(firResolvedQualifier, data);
            return Unit.INSTANCE;
        }
    };

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirObjectConstructorChecker$Data;", Argument.Delimiters.none, "objectSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "getObjectSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "getReporter", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Data {
        private final CheckerContext context;
        private final FirRegularClassSymbol objectSymbol;
        private final DiagnosticReporter reporter;

        public Data(FirRegularClassSymbol firRegularClassSymbol, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
            firRegularClassSymbol.getClass();
            checkerContext.getClass();
            diagnosticReporter.getClass();
            this.objectSymbol = firRegularClassSymbol;
            this.context = checkerContext;
            this.reporter = diagnosticReporter;
        }

        public final CheckerContext getContext() {
            return this.context;
        }

        public final FirRegularClassSymbol getObjectSymbol() {
            return this.objectSymbol;
        }

        public final DiagnosticReporter getReporter() {
            return this.reporter;
        }
    }

    private FirObjectConstructorChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        FirRegularClassSymbol symbol;
        FirRegularClassSymbol superClassSymbolOrAny;
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny;
        FirDelegatedConstructorCall resolvedDelegatedConstructorCall;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (firRegularClass.getClassKind() != ClassKind.OBJECT || (superClassSymbolOrAny = SupertypeUtilsKt.getSuperClassSymbolOrAny((symbol = firRegularClass.getSymbol()), checkerContext.getSession())) == null || !Intrinsics.areEqual(superClassSymbolOrAny, ContainingClassUtilsKt.getContainingClassSymbol(symbol)) || (firConstructorSymbolPrimaryConstructorIfAny = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(symbol, checkerContext.getSession())) == null || (resolvedDelegatedConstructorCall = firConstructorSymbolPrimaryConstructorIfAny.getResolvedDelegatedConstructorCall()) == null) {
            return;
        }
        resolvedDelegatedConstructorCall.accept(objectRefVisitor, new Data(symbol, checkerContext, diagnosticReporter));
    }
}

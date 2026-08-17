package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatusKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirContractCallBlock;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b&\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0002\u0014\u0015B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u0006\u001a\u00020\u0007H&R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ)\u0010\u000b\u001a\u00060\fR\u00020\u0000H$R\u00020\bR\u00020\rj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u000fJ-\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\rj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0013¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "isEnabled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "createVisitor", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageVisitorBase;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageVisitorBase;", "check", Argument.Delimiters.none, "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "UsageState", "UsageVisitorBase", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirUnusedCheckerBase extends FirDeclarationChecker<FirDeclaration> {

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bt\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u0082\u0001\u0004\b\t\n\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;", Argument.Delimiters.none, "isUnused", Argument.Delimiters.none, "Used", "Unused", "UnusedFromCoercion", "UsedInReturn", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState$Unused;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState$UnusedFromCoercion;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState$Used;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState$UsedInReturn;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface UsageState {

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState$Unused;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class Unused implements UsageState {
            public static final Unused INSTANCE = new Unused();

            private Unused() {
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof Unused);
            }

            public int hashCode() {
                return -897743485;
            }

            public String toString() {
                return "Unused";
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState$UnusedFromCoercion;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class UnusedFromCoercion implements UsageState {
            public static final UnusedFromCoercion INSTANCE = new UnusedFromCoercion();

            private UnusedFromCoercion() {
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof UnusedFromCoercion);
            }

            public int hashCode() {
                return 1583444299;
            }

            public String toString() {
                return "UnusedFromCoercion";
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState$Used;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class Used implements UsageState {
            public static final Used INSTANCE = new Used();

            private Used() {
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof Used);
            }

            public int hashCode() {
                return -1265732950;
            }

            public String toString() {
                return "Used";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState$UsedInReturn;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;)V", "getReturnExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class UsedInReturn implements UsageState {
            private final FirReturnExpression returnExpression;

            public UsedInReturn(FirReturnExpression firReturnExpression) {
                firReturnExpression.getClass();
                this.returnExpression = firReturnExpression;
            }

            public static /* synthetic */ UsedInReturn copy$default(UsedInReturn usedInReturn, FirReturnExpression firReturnExpression, int i, Object obj) {
                if ((i & 1) != 0) {
                    firReturnExpression = usedInReturn.returnExpression;
                }
                return usedInReturn.copy(firReturnExpression);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final FirReturnExpression getReturnExpression() {
                return this.returnExpression;
            }

            public final UsedInReturn copy(FirReturnExpression returnExpression) {
                returnExpression.getClass();
                return new UsedInReturn(returnExpression);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof UsedInReturn) && Intrinsics.areEqual(this.returnExpression, ((UsedInReturn) other).returnExpression);
            }

            public final FirReturnExpression getReturnExpression() {
                return this.returnExpression;
            }

            public int hashCode() {
                return this.returnExpression.hashCode();
            }

            public String toString() {
                return "UsedInReturn(returnExpression=" + this.returnExpression + ')';
            }
        }

        default boolean isUnused() {
            return Intrinsics.areEqual(this, Unused.INSTANCE) || Intrinsics.areEqual(this, UnusedFromCoercion.INSTANCE);
        }
    }

    public FirUnusedCheckerBase() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (isEnabled(checkerContext) && !checkerContext.getIsContractBody()) {
            UsageVisitorBase usageVisitorBaseCreateVisitor = createVisitor(checkerContext, diagnosticReporter);
            if (firDeclaration instanceof FirCodeFragment) {
                ((FirCodeFragment) firDeclaration).getBlock().acceptChildren(usageVisitorBaseCreateVisitor, UsageState.Used.INSTANCE);
                return;
            }
            if (firDeclaration instanceof FirAnonymousInitializer) {
                FirBlock body = ((FirAnonymousInitializer) firDeclaration).getBody();
                if (body != null) {
                    body.acceptChildren(usageVisitorBaseCreateVisitor, UsageState.Unused.INSTANCE);
                    return;
                }
                return;
            }
            if (firDeclaration instanceof FirAnonymousFunction) {
                return;
            }
            if (firDeclaration instanceof FirFunction) {
                FirBlock body2 = ((FirFunction) firDeclaration).getBody();
                if (body2 != null) {
                    body2.accept(usageVisitorBaseCreateVisitor, UsageState.Unused.INSTANCE);
                    return;
                }
                return;
            }
            if (firDeclaration instanceof FirVariable) {
                FirVariable firVariable = (FirVariable) firDeclaration;
                FirExpression initializer = firVariable.getInitializer();
                if (initializer != null) {
                    initializer.accept(usageVisitorBaseCreateVisitor, UsageState.Used.INSTANCE);
                }
                FirExpression delegate = firVariable.getDelegate();
                if (delegate != null) {
                    delegate.accept(usageVisitorBaseCreateVisitor, UsageState.Used.INSTANCE);
                }
            }
        }
    }

    public abstract UsageVisitorBase createVisitor(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter);

    public abstract boolean isEnabled(CheckerContext checkerContext);

    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b¤\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H&J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u0018\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u0018\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u0018\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u0018\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u0018\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u0018\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0011\u001a\u00020\u0003H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageVisitorBase;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase$UsageState;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirUnusedCheckerBase;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;)V", "getContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "getReporter", "()Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "checkExpression", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "data", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "visitAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "visitAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "visitWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "visitTryExpression", "tryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "visitBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "visitLoop", "loop", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public abstract class UsageVisitorBase extends FirDefaultVisitor<Unit, UsageState> {
        private final CheckerContext context;
        private final DiagnosticReporter reporter;
        final /* synthetic */ FirUnusedCheckerBase this$0;

        public UsageVisitorBase(FirUnusedCheckerBase firUnusedCheckerBase, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            this.this$0 = firUnusedCheckerBase;
            this.context = checkerContext;
            this.reporter = diagnosticReporter;
        }

        public abstract void checkExpression(FirExpression expression, UsageState data);

        public final CheckerContext getContext() {
            return this.context;
        }

        public final DiagnosticReporter getReporter() {
            return this.reporter;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitAnnotation(FirAnnotation firAnnotation, Object obj) {
            visitAnnotation(firAnnotation, (UsageState) obj);
            return Unit.INSTANCE;
        }

        public void visitAnonymousFunction(FirAnonymousFunction anonymousFunction, UsageState data) {
            anonymousFunction.getClass();
            data.getClass();
            FirBlock body = anonymousFunction.getBody();
            if (body != null) {
                body.accept(this, anonymousFunction.getIsLambda() ? UsageState.Used.INSTANCE : UsageState.Unused.INSTANCE);
            }
        }

        public void visitBlock(FirBlock block, UsageState data) {
            UsageState usageState;
            block.getClass();
            data.getClass();
            if (block instanceof FirContractCallBlock) {
                return;
            }
            KtSourceElement source = block.getSource();
            if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredIncrementOrDecrement) {
                return;
            }
            List<FirStatement> statements = block.getStatements();
            int lastIndex = CollectionsKt.getLastIndex(statements);
            int size = statements.size();
            int i = 0;
            while (i < size) {
                FirStatement firStatement = statements.get(i);
                if (block.getIsUnitCoerced()) {
                    usageState = UsageState.UnusedFromCoercion.INSTANCE;
                } else {
                    usageState = i == lastIndex ? data : UsageState.Unused.INSTANCE;
                }
                firStatement.accept(this, usageState);
                i++;
            }
        }

        public void visitElement(FirElement element, UsageState data) {
            element.getClass();
            data.getClass();
            if (element instanceof FirDeclaration) {
                return;
            }
            if (element instanceof FirExpression) {
                FirExpression firExpression = (FirExpression) element;
                if (firExpression.getSource() != null) {
                    checkExpression(firExpression, data);
                }
            }
            element.acceptChildren(this, UsageState.Used.INSTANCE);
        }

        public void visitLoop(FirLoop loop, UsageState data) {
            loop.getClass();
            data.getClass();
            loop.getCondition().accept(this, UsageState.Used.INSTANCE);
            loop.getBlock().accept(this, UsageState.Unused.INSTANCE);
        }

        public void visitTryExpression(FirTryExpression tryExpression, UsageState data) {
            tryExpression.getClass();
            data.getClass();
            tryExpression.getTryBlock().accept(this, data);
            Iterator<FirCatch> it = tryExpression.getCatches().iterator();
            while (it.hasNext()) {
                it.next().getBlock().accept(this, data);
            }
            FirBlock finallyBlock = tryExpression.getFinallyBlock();
            if (finallyBlock != null) {
                finallyBlock.accept(this, UsageState.Unused.INSTANCE);
            }
        }

        public void visitWhenExpression(FirWhenExpression whenExpression, UsageState data) {
            FirExpression initializer;
            whenExpression.getClass();
            data.getClass();
            FirVariable subjectVariable = whenExpression.getSubjectVariable();
            if (subjectVariable != null && (initializer = subjectVariable.getInitializer()) != null) {
                initializer.accept(this, UsageState.Used.INSTANCE);
            }
            if (!whenExpression.getUsedAsExpression() && !ExhaustivenessStatusKt.isExhaustive(whenExpression)) {
                data = UsageState.Unused.INSTANCE;
            }
            for (FirWhenBranch firWhenBranch : whenExpression.getBranches()) {
                firWhenBranch.getCondition().accept(this, UsageState.Used.INSTANCE);
                if (!FirUnusedCheckerBaseKt.isUnitBlock(firWhenBranch.getResult())) {
                    firWhenBranch.getResult().accept(this, data);
                }
            }
        }

        public void visitAnnotation(FirAnnotation annotation, UsageState data) {
            annotation.getClass();
            data.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitLoop(FirLoop firLoop, Object obj) {
            visitLoop(firLoop, (UsageState) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitAnonymousFunction(FirAnonymousFunction firAnonymousFunction, Object obj) {
            visitAnonymousFunction(firAnonymousFunction, (UsageState) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitElement(FirElement firElement, Object obj) {
            visitElement(firElement, (UsageState) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitTryExpression(FirTryExpression firTryExpression, Object obj) {
            visitTryExpression(firTryExpression, (UsageState) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitBlock(FirBlock firBlock, Object obj) {
            visitBlock(firBlock, (UsageState) obj);
            return Unit.INSTANCE;
        }

        @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
        public /* bridge */ /* synthetic */ Object visitWhenExpression(FirWhenExpression firWhenExpression, Object obj) {
            visitWhenExpression(firWhenExpression, (UsageState) obj);
            return Unit.INSTANCE;
        }
    }
}

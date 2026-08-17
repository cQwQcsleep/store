package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0002\u0012\u0013B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J9\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00022\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0004R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0011\u0082\u0001\u0002\u0014\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameCallableChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "containingClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)V", "Regular", "ForExpectClass", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameCallableChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameCallableChecker$Regular;", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirNativeObjCNameCallableChecker extends FirDeclarationChecker<FirCallableDeclaration> {

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameCallableChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameCallableChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirNativeObjCNameCallableChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firCallableDeclaration.getClass();
            Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            FirClassSymbol<?> firClassSymbol = objLastOrNull instanceof FirClassSymbol ? (FirClassSymbol) objLastOrNull : null;
            if (firClassSymbol != null && firClassSymbol.getRawStatus().isExpect()) {
                check(checkerContext, diagnosticReporter, firCallableDeclaration, firClassSymbol);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameCallableChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCNameCallableChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirNativeObjCNameCallableChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firCallableDeclaration.getClass();
            Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
            FirClassSymbol<?> firClassSymbol = objLastOrNull instanceof FirClassSymbol ? (FirClassSymbol) objLastOrNull : null;
            if (firClassSymbol == null || firClassSymbol.getRawStatus().isExpect()) {
                return;
            }
            check(checkerContext, diagnosticReporter, firCallableDeclaration, firClassSymbol);
        }
    }

    public /* synthetic */ FirNativeObjCNameCallableChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    public final void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration, FirClassSymbol<?> firClassSymbol) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableDeclaration.getClass();
        firClassSymbol.getClass();
        if ((firCallableDeclaration instanceof FirNamedFunction) || (firCallableDeclaration instanceof FirProperty)) {
            FirNativeObjCNameUtilities.INSTANCE.checkCallableMember(checkerContext, diagnosticReporter, FirHelpersKt.unsubstitutedScope(checkerContext, firClassSymbol), firCallableDeclaration.getSymbol(), firCallableDeclaration);
        }
    }

    private FirNativeObjCNameCallableChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}

package org.jetbrains.kotlin.fir.analysis.checkers.context;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractKtDiagnosticFactory;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirInlineBodyResolvableExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.extra.FirAnonymousUnusedParamChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010?\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020AH\u0016R\u0012\u0010\u0005\u001a\u00020\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u0018\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0018\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0010R\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0010R\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0014\u0010 \u001a\u0004\u0018\u00010!X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u0004\u0018\u00010%X¦\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u0004\u0018\u00010)X¦\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0018\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0012\u00101\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u001fR\u0012\u00103\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b4\u0010\u001fR\u0012\u00105\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\u001fR\u0014\u00107\u001a\u0002088VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0014\u0010;\u001a\u00020<8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0014\u0010B\u001a\u00020C8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0014\u0010F\u001a\u0004\u0018\u00010GX¦\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0016\u0010J\u001a\u0004\u0018\u00010K8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bL\u0010M¨\u0006N"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "<init>", "()V", "sessionHolder", "getSessionHolder", "()Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "getReturnTypeCalculator", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getContainingDeclarations", "()Ljava/util/List;", "callsOrAssignments", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "getCallsOrAssignments", "getClassCalls", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "getGetClassCalls", "annotationContainers", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "getAnnotationContainers", "containingElements", "Lorg/jetbrains/kotlin/fir/FirElement;", "getContainingElements", "isContractBody", Argument.Delimiters.none, "()Z", "inlineFunctionBodyContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "getInlineFunctionBodyContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineDeclarationChecker$InlineFunctionBodyContext;", "inlinableParameterContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;", "getInlinableParameterContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInlineBodyResolvableExpressionChecker$InlinableParameterContext;", "lambdaBodyContext", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;", "getLambdaBodyContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/FirAnonymousUnusedParamChecker$LambdaBodyContext;", "suppressedDiagnostics", Argument.Delimiters.none, Argument.Delimiters.none, "getSuppressedDiagnostics", "()Ljava/util/Set;", "allInfosSuppressed", "getAllInfosSuppressed", "allWarningsSuppressed", "getAllWarningsSuppressed", "allErrorsSuppressed", "getAllErrorsSuppressed", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "isDiagnosticSuppressed", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "containingFileSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "getContainingFileSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "containingFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "getContainingFile", "()Lorg/jetbrains/kotlin/KtSourceFile;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CheckerContext implements DiagnosticContext, SessionAndScopeSessionHolder {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Severity.values().length];
            try {
                iArr[Severity.INFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Severity.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Severity.STRONG_WARNING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Severity.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Severity.FIXED_WARNING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public abstract boolean getAllErrorsSuppressed();

    public abstract boolean getAllInfosSuppressed();

    public abstract boolean getAllWarningsSuppressed();

    public abstract List<FirAnnotationContainer> getAnnotationContainers();

    public abstract List<FirStatement> getCallsOrAssignments();

    public abstract List<FirBasedSymbol<?>> getContainingDeclarations();

    public abstract List<FirElement> getContainingElements();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext
    public KtSourceFile getContainingFile() {
        FirFileSymbol containingFileSymbol = getContainingFileSymbol();
        if (containingFileSymbol != null) {
            return containingFileSymbol.getSourceFile();
        }
        return null;
    }

    public abstract FirFileSymbol getContainingFileSymbol();

    public abstract List<FirGetClassCall> getGetClassCalls();

    public abstract FirInlineBodyResolvableExpressionChecker.InlinableParameterContext getInlinableParameterContext();

    public abstract FirInlineDeclarationChecker.InlineFunctionBodyContext getInlineFunctionBodyContext();

    public abstract FirAnonymousUnusedParamChecker.LambdaBodyContext getLambdaBodyContext();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext, org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext
    public LanguageVersionSettings getLanguageVersionSettings() {
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession());
    }

    public abstract ReturnTypeCalculator getReturnTypeCalculator();

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return getSessionHolder().getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return getSessionHolder().getSession();
    }

    public abstract SessionAndScopeSessionHolder getSessionHolder();

    public abstract Set<String> getSuppressedDiagnostics();

    public abstract boolean isContractBody();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticContext
    public boolean isDiagnosticSuppressed(KtDiagnostic diagnostic) {
        boolean allInfosSuppressed;
        diagnostic.getClass();
        AbstractKtDiagnosticFactory factory = diagnostic.getFactory();
        String name = factory.getName();
        if (Intrinsics.areEqual(name, FirErrors.INSTANCE.getERROR_SUPPRESSION().getName())) {
            return false;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[factory.getSeverity().ordinal()];
        if (i == 1) {
            allInfosSuppressed = getAllInfosSuppressed();
        } else if (i == 2 || i == 3) {
            allInfosSuppressed = getAllWarningsSuppressed();
        } else {
            if (i != 4) {
                if (i != 5) {
                    bu8.a();
                    return false;
                }
                AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                wq6.a();
                return false;
            }
            allInfosSuppressed = getAllErrorsSuppressed();
        }
        return allInfosSuppressed || getSuppressedDiagnostics().contains(name);
    }
}

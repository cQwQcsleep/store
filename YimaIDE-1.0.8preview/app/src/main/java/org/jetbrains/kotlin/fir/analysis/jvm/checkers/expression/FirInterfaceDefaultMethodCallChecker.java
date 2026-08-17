package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.JvmDefaultMode;
import org.jetbrains.kotlin.config.JvmDefaultModeKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.FirJvmAnnotationHelperKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.SpecialNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0012\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000f*\u00020\bH\u0002¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirInterfaceDefaultMethodCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "findContainingMember", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInterfaceDefaultMethodCallChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirInterfaceDefaultMethodCallChecker INSTANCE = new FirInterfaceDefaultMethodCallChecker();

    private FirInterfaceDefaultMethodCallChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0041  */
    /* JADX WARN: Code duplicated, block: B:20:0x0045  */
    private final FirCallableSymbol<?> findContainingMember(CheckerContext checkerContext) {
        FirCallableSymbol<?> firCallableSymbol;
        Iterator it = CollectionsKt.asReversed(checkerContext.getContainingDeclarations()).iterator();
        while (true) {
            firCallableSymbol = null;
            if (!it.hasNext()) {
                break;
            }
            FirBasedSymbol firBasedSymbol = (FirBasedSymbol) it.next();
            if (!(firBasedSymbol instanceof FirCallableSymbol)) {
                firBasedSymbol = null;
            }
            FirCallableSymbol<?> firCallableSymbol2 = (FirCallableSymbol) firBasedSymbol;
            if (firCallableSymbol2 != null) {
                if (firCallableSymbol2 instanceof FirNamedFunctionSymbol) {
                    ClassId classId = ((FirNamedFunctionSymbol) firCallableSymbol2).getCallableId().getClassId();
                    if (Intrinsics.areEqual(classId != null ? classId.getRelativeClassName() : null, SpecialNames.ANONYMOUS_FQ_NAME)) {
                        if (firCallableSymbol2 instanceof FirPropertySymbol) {
                        }
                    }
                } else {
                    firCallableSymbol = firCallableSymbol2 instanceof FirPropertySymbol ? firCallableSymbol2 : null;
                }
                if (firCallableSymbol != null) {
                    break;
                }
            }
        }
        return firCallableSymbol;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag;
        FirRegularClassSymbol regularClassSymbol;
        FirCallableSymbol<?> firCallableSymbolFindContainingMember;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.AllowSuperCallToJavaInterface)) {
            return;
        }
        FirRegularClassSymbol firRegularClassSymbol = null;
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedCallableSymbol$default == null || (coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) resolvedCallableSymbol$default)) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag)) == null || ((FirClassLikeDeclaration) regularClassSymbol.getFir()).getIsLocal() || FirHelpersKt.explicitReceiverIsNotSuperReference(firQualifiedAccessExpression)) {
            return;
        }
        for (FirBasedSymbol firBasedSymbol : CollectionsKt.asReversed(checkerContext.getContainingDeclarations())) {
            if (!(firBasedSymbol instanceof FirRegularClassSymbol)) {
                firBasedSymbol = null;
            }
            FirRegularClassSymbol firRegularClassSymbol2 = (FirRegularClassSymbol) firBasedSymbol;
            if (firRegularClassSymbol2 != null) {
                firRegularClassSymbol = firRegularClassSymbol2;
                break;
            }
        }
        if (firRegularClassSymbol == null) {
            return;
        }
        FirSession session = checkerContext.getSession();
        JvmDefaultMode jvmDefaultMode = JvmDefaultModeKt.getJvmDefaultMode(FirLanguageSettingsComponentKt.getLanguageVersionSettings(session));
        ClassKind classKind = regularClassSymbol.getClassKind();
        ClassKind classKind2 = ClassKind.INTERFACE;
        if (classKind == classKind2) {
            if (((regularClassSymbol.getOrigin() instanceof FirDeclarationOrigin.Java) || FirJvmAnnotationHelperKt.isCompiledToJvmDefault(resolvedCallableSymbol$default, session, jvmDefaultMode)) && firRegularClassSymbol.getClassKind() == classKind2 && (firCallableSymbolFindContainingMember = findContainingMember(checkerContext)) != null && !FirJvmAnnotationHelperKt.isCompiledToJvmDefault(firCallableSymbolFindContainingMember, session, jvmDefaultMode)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), FirJvmErrors.INSTANCE.getINTERFACE_CANT_CALL_DEFAULT_METHOD_VIA_SUPER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }
}

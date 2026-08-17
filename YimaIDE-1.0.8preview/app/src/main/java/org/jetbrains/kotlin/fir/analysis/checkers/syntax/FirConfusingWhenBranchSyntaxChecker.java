package org.jetbrains.kotlin.fir.analysis.checkers.syntax;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.ElementTypeUtils;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtBinaryExpression;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtIsExpression;
import org.jetbrains.kotlin.psi.KtParenthesizedExpression;
import org.jetbrains.kotlin.psi.KtPsiUtil;
import org.jetbrains.kotlin.psi.KtWhenCondition;
import org.jetbrains.kotlin.psi.KtWhenConditionInRange;
import org.jetbrains.kotlin.psi.KtWhenConditionWithExpression;
import org.jetbrains.kotlin.psi.KtWhenEntry;
import org.jetbrains.kotlin.psi.KtWhenExpression;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001%B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J5\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u000fJK\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0018H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0019J=\u0010\u001a\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0003H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001dJ5\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010!J7\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010$¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirConfusingWhenBranchSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirExpressionSyntaxChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "Lcom/intellij/psi/PsiElement;", "<init>", "()V", "checkLightTree", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "element", "source", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/KtLightSourceElement;)V", "checkConditionExpression", "booleanSubject", Argument.Delimiters.none, "offset", Argument.Delimiters.none, "expression", "Lcom/intellij/lang/LighterASTNode;", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;ZILcom/intellij/lang/LighterASTNode;Lcom/intellij/util/diff/FlyweightCapableTreeStructure;)V", "checkPsi", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "psi", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Lorg/jetbrains/kotlin/KtPsiSourceElement;Lcom/intellij/psi/PsiElement;)V", "checkCondition", "condition", "Lorg/jetbrains/kotlin/psi/KtWhenCondition;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;ZLorg/jetbrains/kotlin/psi/KtWhenCondition;)V", "rawExpression", "Lorg/jetbrains/kotlin/psi/KtExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;ZLorg/jetbrains/kotlin/psi/KtExpression;)V", "ConfusingWhenBranchReporter", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConfusingWhenBranchSyntaxChecker extends FirExpressionSyntaxChecker<FirWhenExpression, PsiElement> {
    public static final FirConfusingWhenBranchSyntaxChecker INSTANCE = new FirConfusingWhenBranchSyntaxChecker();

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bâ\u0080\u0001\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bJ+\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirConfusingWhenBranchSyntaxChecker$ConfusingWhenBranchReporter;", Argument.Delimiters.none, "report", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;", "context", "reporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "source", "Lorg/jetbrains/kotlin/AbstractKtSourceElement;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/AbstractKtSourceElement;)V", "Companion", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface ConfusingWhenBranchReporter {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086\u0002R\u0013\u0010\u0004\u001a\u00070\u0005¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirConfusingWhenBranchSyntaxChecker$ConfusingWhenBranchReporter$Companion;", Argument.Delimiters.none, "<init>", "()V", "prohibitedTokens", "Lcom/intellij/psi/tree/TokenSet;", "Lorg/jetbrains/annotations/NotNull;", "invoke", "Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirConfusingWhenBranchSyntaxChecker$ConfusingWhenBranchReporter;", "operationToken", "Lcom/intellij/psi/tree/IElementType;", "booleanSubject", Argument.Delimiters.none, "Generic", "getGeneric", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/syntax/FirConfusingWhenBranchSyntaxChecker$ConfusingWhenBranchReporter;", "GuardSuggestion", "getGuardSuggestion", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            private static final ConfusingWhenBranchReporter Generic;
            private static final ConfusingWhenBranchReporter GuardSuggestion;
            private static final TokenSet prohibitedTokens;

            static {
                TokenSet tokenSetCreate = TokenSet.create(new IElementType[]{KtTokens.IN_KEYWORD, KtTokens.NOT_IN, KtTokens.LT, KtTokens.LTEQ, KtTokens.GT, KtTokens.GTEQ, KtTokens.EQEQ, KtTokens.EXCLEQ, KtTokens.EQEQEQ, KtTokens.EXCLEQEQEQ, KtTokens.ANDAND, KtTokens.OROR});
                tokenSetCreate.getClass();
                prohibitedTokens = tokenSetCreate;
                Generic = new ConfusingWhenBranchReporter() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.syntax.a
                    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirConfusingWhenBranchSyntaxChecker.ConfusingWhenBranchReporter
                    public final void report(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement) {
                        FirConfusingWhenBranchSyntaxChecker.ConfusingWhenBranchReporter.Companion.a(diagnosticContext, diagnosticReporter, abstractKtSourceElement);
                    }
                };
                GuardSuggestion = new ConfusingWhenBranchReporter() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.syntax.b
                    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirConfusingWhenBranchSyntaxChecker.ConfusingWhenBranchReporter
                    public final void report(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement) {
                        FirConfusingWhenBranchSyntaxChecker.ConfusingWhenBranchReporter.Companion.b(diagnosticContext, diagnosticReporter, abstractKtSourceElement);
                    }
                };
            }

            private Companion() {
            }

            public static void a(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement) {
                diagnosticContext.getClass();
                diagnosticReporter.getClass();
                KtDiagnosticReportHelpersKt.reportOn$default(diagnosticContext, diagnosticReporter, abstractKtSourceElement, FirErrors.INSTANCE.getCONFUSING_BRANCH_CONDITION_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }

            public static void b(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement) {
                diagnosticContext.getClass();
                diagnosticReporter.getClass();
                KtDiagnosticReportHelpersKt.reportOn$default(diagnosticContext, diagnosticReporter, abstractKtSourceElement, FirErrors.INSTANCE.getWRONG_CONDITION_SUGGEST_GUARD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }

            public final ConfusingWhenBranchReporter getGeneric() {
                return Generic;
            }

            public final ConfusingWhenBranchReporter getGuardSuggestion() {
                return GuardSuggestion;
            }

            public final ConfusingWhenBranchReporter invoke(IElementType operationToken, boolean booleanSubject) {
                operationToken.getClass();
                if (Intrinsics.areEqual(operationToken, KtTokens.ANDAND) && !booleanSubject) {
                    return GuardSuggestion;
                }
                if (prohibitedTokens.contains(operationToken)) {
                    return Generic;
                }
                return null;
            }
        }

        void report(DiagnosticContext diagnosticContext, DiagnosticReporter diagnosticReporter, AbstractKtSourceElement abstractKtSourceElement);
    }

    private FirConfusingWhenBranchSyntaxChecker() {
    }

    private final void checkCondition(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, boolean z, KtWhenCondition ktWhenCondition) {
        if (ktWhenCondition instanceof KtWhenConditionWithExpression) {
            checkConditionExpression(checkerContext, diagnosticReporter, z, ((KtWhenConditionWithExpression) ktWhenCondition).getExpression());
        } else if (ktWhenCondition instanceof KtWhenConditionInRange) {
            checkConditionExpression(checkerContext, diagnosticReporter, z, ((KtWhenConditionInRange) ktWhenCondition).getRangeExpression());
        }
    }

    private final void checkConditionExpression(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, boolean z, int i, LighterASTNode lighterASTNode, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure) {
        ConfusingWhenBranchReporter confusingWhenBranchReporterInvoke;
        LighterASTNode lighterASTNode2;
        IElementType tokenType = lighterASTNode.getTokenType();
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.IS_EXPRESSION)) {
            confusingWhenBranchReporterInvoke = ConfusingWhenBranchReporter.INSTANCE.getGeneric();
        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.BINARY_EXPRESSION)) {
            Iterator it = LightTreeUtilsKt.getChildren(lighterASTNode, flyweightCapableTreeStructure).iterator();
            do {
                if (!it.hasNext()) {
                    hb9.a("Collection contains no element matching the predicate.");
                    return;
                }
                lighterASTNode2 = (LighterASTNode) it.next();
            } while (!Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.OPERATION_REFERENCE));
            confusingWhenBranchReporterInvoke = ConfusingWhenBranchReporter.INSTANCE.invoke(ElementTypeUtils.INSTANCE.getOperationSymbol(lighterASTNode2, flyweightCapableTreeStructure), z);
        } else {
            confusingWhenBranchReporterInvoke = null;
        }
        if (confusingWhenBranchReporterInvoke == null) {
            return;
        }
        confusingWhenBranchReporterInvoke.report(checkerContext, diagnosticReporter, new KtLightSourceElement(lighterASTNode, i + lighterASTNode.getStartOffset(), i + lighterASTNode.getEndOffset(), flyweightCapableTreeStructure, (KtSourceElementKind) null, 16, (DefaultConstructorMarker) null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkLightTree(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirWhenExpression firWhenExpression, KtLightSourceElement ktLightSourceElement) {
        FirTypeRef returnTypeRef;
        ConeKotlinType coneType;
        FirExpression initializer;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firWhenExpression.getClass();
        ktLightSourceElement.getClass();
        FirVariable subjectVariable = firWhenExpression.getSubjectVariable();
        if (subjectVariable == null || (initializer = subjectVariable.getInitializer()) == null || (coneType = FirTypeUtilsKt.getResolvedType(initializer)) == null) {
            FirVariable subjectVariable2 = firWhenExpression.getSubjectVariable();
            if (subjectVariable2 == null || (returnTypeRef = subjectVariable2.getReturnTypeRef()) == null) {
                return;
            } else {
                coneType = FirTypeUtilsKt.getConeType(returnTypeRef);
            }
        }
        boolean zIsBooleanOrNullableBoolean = ConeBuiltinTypeUtilsKt.isBooleanOrNullableBoolean(coneType);
        FlyweightCapableTreeStructure<LighterASTNode> treeStructure = ktLightSourceElement.getTreeStructure();
        List children = LightTreeUtilsKt.getChildren(ktLightSourceElement.getLighterASTNode(), treeStructure);
        ArrayList arrayList = new ArrayList();
        for (Object obj : children) {
            if (Intrinsics.areEqual(((LighterASTNode) obj).getTokenType(), KtNodeTypes.WHEN_ENTRY)) {
                arrayList.add(obj);
            }
        }
        int startOffset = ktLightSourceElement.getStartOffset() - ktLightSourceElement.getLighterASTNode().getStartOffset();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            for (LighterASTNode lighterASTNode : LightTreeUtilsKt.getChildren((LighterASTNode) it.next(), treeStructure)) {
                IElementType tokenType = lighterASTNode.getTokenType();
                Object obj2 = null;
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.WHEN_CONDITION_EXPRESSION)) {
                    for (Object obj3 : LightTreeUtilsKt.getChildren(lighterASTNode, treeStructure)) {
                        if (ElementTypeUtils.INSTANCE.isExpression((LighterASTNode) obj3)) {
                            obj2 = obj3;
                            break;
                        }
                    }
                    obj2 = (LighterASTNode) obj2;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.WHEN_CONDITION_IN_RANGE)) {
                    for (Object obj4 : LightTreeUtilsKt.getChildren(lighterASTNode, treeStructure)) {
                        LighterASTNode lighterASTNode2 = (LighterASTNode) obj4;
                        if (!Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.OPERATION_REFERENCE) && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode2)) {
                            obj2 = obj4;
                            break;
                        }
                    }
                    obj2 = (LighterASTNode) obj2;
                }
                LighterASTNode lighterASTNode3 = obj2;
                if (lighterASTNode3 != 0) {
                    checkConditionExpression(checkerContext, diagnosticReporter, zIsBooleanOrNullableBoolean, startOffset, lighterASTNode3, treeStructure);
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirSyntaxChecker
    public void checkPsi(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirWhenExpression firWhenExpression, KtPsiSourceElement ktPsiSourceElement, PsiElement psiElement) {
        FirTypeRef returnTypeRef;
        ConeKotlinType coneType;
        FirExpression initializer;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firWhenExpression.getClass();
        ktPsiSourceElement.getClass();
        psiElement.getClass();
        FirVariable subjectVariable = firWhenExpression.getSubjectVariable();
        if (subjectVariable == null || (initializer = subjectVariable.getInitializer()) == null || (coneType = FirTypeUtilsKt.getResolvedType(initializer)) == null) {
            FirVariable subjectVariable2 = firWhenExpression.getSubjectVariable();
            if (subjectVariable2 == null || (returnTypeRef = subjectVariable2.getReturnTypeRef()) == null) {
                return;
            } else {
                coneType = FirTypeUtilsKt.getConeType(returnTypeRef);
            }
        }
        boolean zIsBooleanOrNullableBoolean = ConeBuiltinTypeUtilsKt.isBooleanOrNullableBoolean(coneType);
        KtWhenExpression ktWhenExpression = (KtWhenExpression) psiElement;
        if (ktWhenExpression.getSubjectExpression() == null && ktWhenExpression.getSubjectVariable() == null) {
            return;
        }
        Iterator it = ktWhenExpression.getEntries().iterator();
        while (it.hasNext()) {
            KtWhenCondition[] conditions = ((KtWhenEntry) it.next()).getConditions();
            conditions.getClass();
            for (KtWhenCondition ktWhenCondition : conditions) {
                ktWhenCondition.getClass();
                checkCondition(checkerContext, diagnosticReporter, zIsBooleanOrNullableBoolean, ktWhenCondition);
            }
        }
    }

    private final void checkConditionExpression(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, boolean z, KtExpression ktExpression) {
        ConfusingWhenBranchReporter confusingWhenBranchReporterInvoke;
        if (ktExpression == null || (ktExpression instanceof KtParenthesizedExpression)) {
            return;
        }
        KtBinaryExpression ktBinaryExpressionSafeDeparenthesize = KtPsiUtil.safeDeparenthesize(ktExpression);
        ktBinaryExpressionSafeDeparenthesize.getClass();
        if (ktBinaryExpressionSafeDeparenthesize instanceof KtIsExpression) {
            confusingWhenBranchReporterInvoke = ConfusingWhenBranchReporter.INSTANCE.getGeneric();
        } else if (ktBinaryExpressionSafeDeparenthesize instanceof KtBinaryExpression) {
            ConfusingWhenBranchReporter.Companion companion = ConfusingWhenBranchReporter.INSTANCE;
            IElementType operationToken = ktBinaryExpressionSafeDeparenthesize.getOperationToken();
            operationToken.getClass();
            confusingWhenBranchReporterInvoke = companion.invoke(operationToken, z);
        } else {
            confusingWhenBranchReporterInvoke = null;
        }
        if (confusingWhenBranchReporterInvoke == null) {
            return;
        }
        confusingWhenBranchReporterInvoke.report(checkerContext, diagnosticReporter, new KtRealPsiSourceElement(ktExpression));
    }
}

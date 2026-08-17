package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOverloadabilityHelperKt;
import org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.ImplicitReceiverUtilsKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0002%&B\u0011\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J-\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016R\u00020\nR\u00020\fj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u0012H\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013JL\u0010\u0014\u001a\u0004\u0018\u0001H\u0015\"\u0004\b\u0000\u0010\u00152\u0014\b\u0004\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u00020\u00110\u00172\u001e\u0010\u0018\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u00020\t0\u0017\u0012\u0004\u0012\u00020\t0\u0017H\u0082\b¢\u0006\u0002\u0010\u0019JT\u0010\u001a\u001a\u0004\u0018\u0001H\u001b\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\u001b2\u0016\b\u0004\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u0002H\u0015\u0012\u0006\u0012\u0004\u0018\u0001H\u001b0\u00172\u001e\u0010\u0018\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u00020\t0\u0017\u0012\u0004\u0012\u00020\t0\u0017H\u0082\b¢\u0006\u0002\u0010\u0019J-\u0010\u001d\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u001e2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0002R\u00020\nj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010 R\u001c\u0010!\u001a\u00020\"*\u0006\u0012\u0002\b\u00030\u001e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$\u0082\u0001\u0002'(¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExtensionShadowedByMemberChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "kind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "isVisible", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "findFirstSymbolByCondition", "T", "condition", "Lkotlin/Function1;", "processMembers", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "findFirstNotNullSymbol", "K", "transform", "shadows", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "extension", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;)Z", "varargParameterPosition", Argument.Delimiters.none, "getVarargParameterPosition", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;)I", "Regular", "ForExpectDeclaration", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExtensionShadowedByMemberChecker$ForExpectDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExtensionShadowedByMemberChecker$Regular;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirExtensionShadowedByMemberChecker extends FirDeclarationChecker<FirCallableDeclaration> {

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExtensionShadowedByMemberChecker$ForExpectDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExtensionShadowedByMemberChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ForExpectDeclaration extends FirExtensionShadowedByMemberChecker {
        public static final ForExpectDeclaration INSTANCE = new ForExpectDeclaration();

        private ForExpectDeclaration() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExtensionShadowedByMemberChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firCallableDeclaration.getClass();
            if (firCallableDeclaration.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firCallableDeclaration);
            }
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ForExpectDeclaration);
        }

        public int hashCode() {
            return -1702148819;
        }

        public String toString() {
            return "ForExpectDeclaration";
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExtensionShadowedByMemberChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExtensionShadowedByMemberChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Regular extends FirExtensionShadowedByMemberChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExtensionShadowedByMemberChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firCallableDeclaration.getClass();
            if (firCallableDeclaration.getStatus().isExpect()) {
                return;
            }
            super.check(checkerContext, diagnosticReporter, firCallableDeclaration);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Regular);
        }

        public int hashCode() {
            return 1107106279;
        }

        public String toString() {
            return "Regular";
        }
    }

    public /* synthetic */ FirExtensionShadowedByMemberChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }

    private final int getVarargParameterPosition(FirFunctionSymbol<?> firFunctionSymbol) {
        Iterator<FirValueParameterSymbol> it = firFunctionSymbol.getValueParameterSymbols().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().isVararg()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isVisible(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol) {
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        if (containingFileSymbol != null) {
            return FirVisibilityCheckerKt.isVisible$default(FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession()), firCallableSymbol, checkerContext.getSession(), containingFileSymbol, checkerContext.getContainingDeclarations(), null, false, 32, null);
        }
        k2d.a("No containing file present when running a checker for top-level functions");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shadows(CheckerContext checkerContext, FirFunctionSymbol<?> firFunctionSymbol, FirFunctionSymbol<?> firFunctionSymbol2) {
        if (FirSymbolStatusUtilsKt.isExtension(firFunctionSymbol) || firFunctionSymbol2.getValueParameterSymbols().size() != firFunctionSymbol.getValueParameterSymbols().size() || getVarargParameterPosition(firFunctionSymbol2) != getVarargParameterPosition(firFunctionSymbol)) {
            return false;
        }
        if (firFunctionSymbol2.getResolvedStatus().isOperator() && !firFunctionSymbol.getResolvedStatus().isOperator()) {
            return false;
        }
        if ((firFunctionSymbol2.getResolvedStatus().isInfix() && !firFunctionSymbol.getResolvedStatus().isInfix()) || firFunctionSymbol2.getTypeParameterSymbols().size() != firFunctionSymbol.getTypeParameterSymbols().size()) {
            return false;
        }
        boolean hasStableParameterNames = firFunctionSymbol2.getResolvedStatus().getHasStableParameterNames();
        boolean hasStableParameterNames2 = firFunctionSymbol.getResolvedStatus().getHasStableParameterNames();
        int size = firFunctionSymbol2.getValueParameterSymbols().size();
        for (int i = 0; i < size; i++) {
            FirValueParameterSymbol firValueParameterSymbol = firFunctionSymbol2.getValueParameterSymbols().get(i);
            FirValueParameterSymbol firValueParameterSymbol2 = firFunctionSymbol.getValueParameterSymbols().get(i);
            if (firValueParameterSymbol.getHasDefaultValue() && !firValueParameterSymbol2.getHasDefaultValue()) {
                return false;
            }
            if (hasStableParameterNames && (!hasStableParameterNames2 || !Intrinsics.areEqual(firValueParameterSymbol.getName(), firValueParameterSymbol2.getName()))) {
                return false;
            }
        }
        return FirDeclarationOverloadabilityHelperKt.getDeclarationOverloadabilityHelper(checkerContext.getSession()).isExtensionShadowedByMember(firFunctionSymbol2, firFunctionSymbol);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, final FirCallableDeclaration firCallableDeclaration) {
        FirReceiverParameter receiverParameter;
        FirReceiverParameter receiverParameter2;
        FirTypeRef typeRef;
        ConeKotlinType coneType;
        FirClassLikeSymbol<?> classLikeSymbol;
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirScope firScopeUnsubstitutedScope;
        FirCallableSymbol firCallableSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableDeclaration.getClass();
        if (FirAnnotationUtilsKt.hasAnnotation((FirDeclaration) firCallableDeclaration, StandardClassIds$Annotations.INSTANCE.getHidesMembers(), checkerContext.getSession()) || (receiverParameter = firCallableDeclaration.getReceiverParameter()) == null || TypeUtilsKt.canBeNull$default(FirTypeUtilsKt.getConeType(receiverParameter.getTypeRef()), checkerContext.getSession(), false, null, 6, null) || Intrinsics.areEqual(FirDeclarationUtilKt.getNameOrSpecialName(firCallableDeclaration), SpecialNames.NO_NAME_PROVIDED) || firCallableDeclaration.getStatus().isActual() || firCallableDeclaration.getStatus().isOverride() || (receiverParameter2 = firCallableDeclaration.getReceiverParameter()) == null || (typeRef = receiverParameter2.getTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(typeRef)) == null || (classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(checkerContext, coneType)) == null || (firRegularClassSymbolFullyExpandedClass = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.fullyExpandedClass(checkerContext, classLikeSymbol)) == null) {
            return;
        }
        if (FirStatusUtilsKt.isCompanionExtension(firCallableDeclaration)) {
            firScopeUnsubstitutedScope = ImplicitReceiverUtilsKt.staticScope(firRegularClassSymbolFullyExpandedClass, checkerContext);
            if (firScopeUnsubstitutedScope == null) {
                return;
            }
        } else {
            firScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firRegularClassSymbolFullyExpandedClass);
        }
        if (firCallableDeclaration instanceof FirVariable) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            firScopeUnsubstitutedScope.processPropertiesByName(((FirVariable) firCallableDeclaration).getName(), new Function1<FirVariableSymbol<?>, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExtensionShadowedByMemberChecker$check$$inlined$findFirstSymbolByCondition$1
                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m225invoke(FirVariableSymbol<?> firVariableSymbol) {
                    if (objectRef.element == null) {
                        FirVariableSymbol<?> firVariableSymbol2 = firVariableSymbol;
                        if (!this.isVisible(checkerContext, firVariableSymbol2) || FirSymbolStatusUtilsKt.isExtension(firVariableSymbol2)) {
                            firVariableSymbol = null;
                        }
                        if (firVariableSymbol != null) {
                            objectRef.element = firVariableSymbol;
                        }
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m225invoke((FirVariableSymbol<?>) obj);
                    return Unit.INSTANCE;
                }
            });
            firCallableSymbol = (FirCallableSymbol) objectRef.element;
        } else {
            if (!(firCallableDeclaration instanceof FirNamedFunction)) {
                return;
            }
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            firScopeUnsubstitutedScope.processFunctionsByName(((FirNamedFunction) firCallableDeclaration).getName(), new Function1<FirNamedFunctionSymbol, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExtensionShadowedByMemberChecker$check$$inlined$findFirstSymbolByCondition$2
                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m226invoke(FirNamedFunctionSymbol firNamedFunctionSymbol) {
                    if (objectRef2.element == null) {
                        FirNamedFunctionSymbol firNamedFunctionSymbol2 = firNamedFunctionSymbol;
                        if (!this.isVisible(checkerContext, firNamedFunctionSymbol2) || !this.shadows(checkerContext, firNamedFunctionSymbol2, ((FirNamedFunction) firCallableDeclaration).getSymbol())) {
                            firNamedFunctionSymbol = null;
                        }
                        if (firNamedFunctionSymbol != null) {
                            objectRef2.element = firNamedFunctionSymbol;
                        }
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m226invoke((FirNamedFunctionSymbol) obj);
                    return Unit.INSTANCE;
                }
            });
            firCallableSymbol = (FirCallableSymbol) objectRef2.element;
        }
        if (firCallableSymbol != null) {
            FirDeprecationInfo deprecation = DeprecationUtilsKt.getDeprecation(firCallableSymbol, checkerContext.getSession(), firCallableDeclaration);
            if ((deprecation != null ? deprecation.getDeprecationLevel() : null) != DeprecationLevelValue.HIDDEN) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableDeclaration.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getEXTENSION_SHADOWED_BY_MEMBER(), (Object) firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
        }
        if (firCallableDeclaration instanceof FirNamedFunction) {
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            FirNamedFunction firNamedFunction = (FirNamedFunction) firCallableDeclaration;
            firScopeUnsubstitutedScope.processPropertiesByName(firNamedFunction.getName(), new Function1<FirVariableSymbol<?>, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExtensionShadowedByMemberChecker$check$$inlined$findFirstNotNullSymbol$1
                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m224invoke(FirVariableSymbol<?> firVariableSymbol) {
                    FirClassLikeSymbol<?> classLikeSymbol2;
                    FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass2;
                    FirTypeScope firTypeScopeUnsubstitutedScope;
                    if (objectRef3.element == null) {
                        FirVariableSymbol<?> firVariableSymbol2 = firVariableSymbol;
                        Pair pair = null;
                        if (this.isVisible(checkerContext, firVariableSymbol2) && !FirSymbolStatusUtilsKt.isExtension(firVariableSymbol2) && !FirCallableSymbolKt.getHasContextParameters(firVariableSymbol2) && (classLikeSymbol2 = ToSymbolUtilsKt.toClassLikeSymbol(checkerContext, firVariableSymbol2.getResolvedReturnType())) != null && (firRegularClassSymbolFullyExpandedClass2 = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.fullyExpandedClass(checkerContext, classLikeSymbol2)) != null && (firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firRegularClassSymbolFullyExpandedClass2)) != null) {
                            final Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                            final FirExtensionShadowedByMemberChecker firExtensionShadowedByMemberChecker = this;
                            final CheckerContext checkerContext2 = checkerContext;
                            final FirCallableDeclaration firCallableDeclaration2 = firCallableDeclaration;
                            firTypeScopeUnsubstitutedScope.processFunctionsByName(OperatorNameConventions.INVOKE, new Function1<FirNamedFunctionSymbol, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExtensionShadowedByMemberChecker$check$lambda$5$$inlined$findFirstSymbolByCondition$1
                                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                                public final void m227invoke(FirNamedFunctionSymbol firNamedFunctionSymbol) {
                                    if (objectRef4.element == null) {
                                        FirNamedFunctionSymbol firNamedFunctionSymbol2 = firNamedFunctionSymbol;
                                        if (!firExtensionShadowedByMemberChecker.isVisible(checkerContext2, firNamedFunctionSymbol2) || !firNamedFunctionSymbol2.getResolvedStatus().isOperator() || !firExtensionShadowedByMemberChecker.shadows(checkerContext2, firNamedFunctionSymbol2, ((FirNamedFunction) firCallableDeclaration2).getSymbol())) {
                                            firNamedFunctionSymbol = null;
                                        }
                                        if (firNamedFunctionSymbol != null) {
                                            objectRef4.element = firNamedFunctionSymbol;
                                        }
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                    m227invoke((FirNamedFunctionSymbol) obj);
                                    return Unit.INSTANCE;
                                }
                            });
                            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) objectRef4.element;
                            if (firNamedFunctionSymbol != null) {
                                pair = TuplesKt.to(firNamedFunctionSymbol, firVariableSymbol2);
                            }
                        }
                        if (pair != null) {
                            objectRef3.element = pair;
                        }
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m224invoke((FirVariableSymbol<?>) obj);
                    return Unit.INSTANCE;
                }
            });
            Pair pair = (Pair) objectRef3.element;
            if (pair == null) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunction.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getEXTENSION_FUNCTION_SHADOWED_BY_MEMBER_PROPERTY_WITH_INVOKE(), pair.component2(), pair.component1(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }

    private FirExtensionShadowedByMemberChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
    }
}

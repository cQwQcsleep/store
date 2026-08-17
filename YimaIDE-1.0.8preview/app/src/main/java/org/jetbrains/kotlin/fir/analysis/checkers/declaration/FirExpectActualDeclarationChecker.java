package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContext;
import org.jetbrains.kotlin.fir.FirExpectActualMatchingContextKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.mpp.CallableSymbolMarker;
import org.jetbrains.kotlin.mpp.ClassLikeSymbolMarker;
import org.jetbrains.kotlin.mpp.RegularClassSymbolMarker;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;
import org.jetbrains.kotlin.resolve.calls.mpp.AbstractExpectActualChecker;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualIncompatibility;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualMatchingCompatibility;
import org.jetbrains.kotlin.resolve.multiplatform.MemberIncompatibility;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0010H\u0002J-\u0010\u0011\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0012J-\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0016J-\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001aJ-\u0010\u001b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0012JO\u0010\u001c\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00102 \u0010\u001d\u001a\u001c\u0012\u0004\u0012\u00020\u001f\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030!0 0\u001ej\u0002`\"H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010#J]\u0010$\u001a\u00020\u00072\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020!2\f\u0010&\u001a\b\u0012\u0002\b\u0003\u0018\u00010!2\u0010\u0010'\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030!0(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010+JO\u0010,\u001a\u00020\u00072\u0016\u0010-\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030!0.0 2\b\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010/\u001a\u000200H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00101JG\u00102\u001a\u00020\u00072\n\u00103\u001a\u0006\u0012\u0002\b\u00030!2\n\u00104\u001a\u0006\u0012\u0002\b\u00030!2\b\u0010)\u001a\u0004\u0018\u00010*H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00105JG\u00102\u001a\u00020\u00072\u0016\u00106\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030!0.0 2\b\u0010)\u001a\u0004\u0018\u00010*H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00107J\"\u00108\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030!2\b\u00109\u001a\u0004\u0018\u0001002\u0006\u0010:\u001a\u00020;H\u0002J]\u0010<\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030!0=0 2\n\u0010>\u001a\u0006\u0012\u0002\b\u00030!2\n\u0010?\u001a\u0006\u0012\u0002\b\u00030!2\b\u0010@\u001a\u0004\u0018\u0001002\b\u00109\u001a\u0004\u0018\u0001002\u0006\u0010:\u001a\u00020;H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010AJ[\u0010B\u001a\u00020\u00072\n\u0010C\u001a\u0006\u0012\u0002\b\u00030!2\u001c\u0010D\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030!0 0\u001e2\n\u0010%\u001a\u0006\u0012\u0002\b\u00030!H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010EJ$\u0010F\u001a\u00020\u000f2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030!2\u0006\u0010@\u001a\u0002002\u0006\u0010G\u001a\u00020HH\u0002J\f\u0010I\u001a\u00020\u000f*\u00020JH\u0002J$\u0010K\u001a\u00020\u000f2\n\u0010%\u001a\u0006\u0012\u0002\b\u00030!2\u0006\u0010@\u001a\u0002002\u0006\u0010G\u001a\u00020HH\u0002¨\u0006L"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirExpectActualDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "containsExpectOrActualModifier", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "checkExpectDeclarationModifiers", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;)V", "checkExpectPropertyAccessorsModifiers", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "checkExpectPropertyAccessorModifiers", "accessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;)V", "checkExpectDeclarationHasNoExternalModifier", "checkActualDeclarationHasExpected", "matchingCompatibilityToMembersMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/ExpectForActualMatchingData;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;Ljava/util/Map;)V", "reportClassScopesIncompatibility", "symbol", "expectedSingleCandidate", "checkingCompatibility", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualIncompatibility$ClassScopes;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualIncompatibility$ClassScopes;Lorg/jetbrains/kotlin/KtSourceElement;)V", "reportDefaultArgsIncompatibleMembers", "defaultArgsIncompatibleMembers", "Lorg/jetbrains/kotlin/resolve/multiplatform/MemberIncompatibility;", "expectClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "reportIgnorabilityIncompatibleMembers", "expect", "actual", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)V", "ignorabilityIncompatibleMembers", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;Lorg/jetbrains/kotlin/KtSourceElement;)V", "isFakeOverride", "expectContainingClass", "expectActualMatchingContext", "Lorg/jetbrains/kotlin/fir/FirExpectActualMatchingContext;", "getCheckingCompatibility", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualIncompatibility;", "actualSymbol", "expectSymbol", "actualContainingClass", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/FirExpectActualMatchingContext;)Ljava/util/List;", "checkAmbiguousExpects", "actualDeclaration", "compatibility", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "requireActualModifier", "platformSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "hasActualModifier", "Lorg/jetbrains/kotlin/fir/FirElement;", "isUnderlyingPropertyOfInlineClass", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpectActualDeclarationChecker extends FirDeclarationChecker<FirDeclaration> {
    public static final FirExpectActualDeclarationChecker INSTANCE = new FirExpectActualDeclarationChecker();

    private FirExpectActualDeclarationChecker() {
        super(MppCheckerKind.Platform);
    }

    private final void checkActualDeclarationHasExpected(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirMemberDeclaration firMemberDeclaration, Map<ExpectActualMatchingCompatibility, ? extends List<? extends FirBasedSymbol<?>>> map) {
        List<ExpectActualIncompatibility<FirBasedSymbol<?>>> listEmptyList;
        FirBasedSymbol<FirDeclaration> symbol = firMemberDeclaration.getSymbol();
        ExpectActualMatchingCompatibility.MatchedSuccessfully matchedSuccessfully = ExpectActualMatchingCompatibility.MatchedSuccessfully.INSTANCE;
        List<? extends FirBasedSymbol<?>> list = map.get(matchedSuccessfully);
        FirBasedSymbol<?> firBasedSymbol = list != null ? (FirBasedSymbol) CollectionsKt.singleOrNull(list) : null;
        FirExpectActualMatchingContext firExpectActualMatchingContextCreate = FirExpectActualMatchingContextKt.getExpectActualMatchingContextFactory(checkerContext.getSession()).create(checkerContext.getSession(), checkerContext.getScopeSession(), true);
        Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        FirRegularClassSymbol firRegularClassSymbol = objLastOrNull instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) objLastOrNull : null;
        FirBasedSymbol<?> singleMatchedExpectForActualOrNull = firRegularClassSymbol != null ? ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull(firRegularClassSymbol) : null;
        FirRegularClassSymbol firRegularClassSymbol2 = singleMatchedExpectForActualOrNull instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) singleMatchedExpectForActualOrNull : null;
        if (firBasedSymbol != null) {
            listEmptyList = getCheckingCompatibility(checkerContext, symbol, firBasedSymbol, firRegularClassSymbol, firRegularClassSymbol2, firExpectActualMatchingContextCreate);
            symbol = symbol;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        checkAmbiguousExpects(checkerContext, diagnosticReporter, symbol, map, symbol);
        FirExpectActualDeclarationChecker firExpectActualDeclarationChecker = this;
        FirBasedSymbol<FirDeclaration> firBasedSymbol2 = symbol;
        AbstractKtSourceElement source = firMemberDeclaration.getSource();
        if (!firExpectActualDeclarationChecker.hasActualModifier(firMemberDeclaration) && !firMemberDeclaration.getStatus().isExpect() && map.containsKey(matchedSuccessfully) && ((firRegularClassSymbol == 0 || firExpectActualDeclarationChecker.requireActualModifier(firBasedSymbol2, firRegularClassSymbol, checkerContext.getSession())) && firBasedSymbol != null && !firExpectActualDeclarationChecker.isFakeOverride(firBasedSymbol, firRegularClassSymbol2, firExpectActualMatchingContextCreate))) {
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, source, FirErrors.INSTANCE.getACTUAL_MISSING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        AbstractKtSourceElement abstractKtSourceElement = source;
        if (!map.containsKey(matchedSuccessfully) || (firBasedSymbol != null && firExpectActualDeclarationChecker.hasActualModifier(firMemberDeclaration) && firExpectActualDeclarationChecker.isFakeOverride(firBasedSymbol, firRegularClassSymbol2, firExpectActualMatchingContextCreate))) {
            KtDiagnosticReportHelpersKt.reportOn$default(checkerContext, diagnosticReporter, abstractKtSourceElement, FirErrors.INSTANCE.getACTUAL_WITHOUT_EXPECT(), firBasedSymbol2, map, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listEmptyList) {
            ExpectActualIncompatibility.ClassScopes classScopes = (ExpectActualIncompatibility.ClassScopes) (!(obj instanceof ExpectActualIncompatibility.ClassScopes) ? null : obj);
            if (classScopes != null) {
                arrayList.add(classScopes);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list2 = (List) pair.component1();
        List<ExpectActualIncompatibility> list3 = (List) pair.component2();
        for (ExpectActualIncompatibility expectActualIncompatibility : list3) {
            if (firBasedSymbol == null) {
                k2d.a("Check failed.");
                return;
            }
            if ((firMemberDeclaration instanceof FirFunction) && Intrinsics.areEqual(expectActualIncompatibility, ExpectActualIncompatibility.ActualFunctionWithOptionalParameters.INSTANCE)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirFunction) firMemberDeclaration).getSource(), FirErrors.INSTANCE.getACTUAL_FUNCTION_WITH_DEFAULT_ARGUMENTS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else if (Intrinsics.areEqual(expectActualIncompatibility, ExpectActualIncompatibility.IgnorabilityIsDifferent.INSTANCE)) {
                firExpectActualDeclarationChecker.reportIgnorabilityIncompatibleMembers(diagnosticReporter, checkerContext, firBasedSymbol, firBasedSymbol2, abstractKtSourceElement);
                firExpectActualDeclarationChecker = this;
            } else {
                AbstractKtSourceElement abstractKtSourceElement2 = abstractKtSourceElement;
                FirBasedSymbol<FirDeclaration> firBasedSymbol3 = firBasedSymbol2;
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, abstractKtSourceElement2, (KtDiagnosticFactory3<FirBasedSymbol<?>, FirBasedSymbol<FirDeclaration>, String>) ((KtDiagnosticFactory3<Object, Object, Object>) FirExpectActualDeclarationCheckerKt.access$toDiagnostic(expectActualIncompatibility)), firBasedSymbol, firBasedSymbol3, expectActualIncompatibility.getReason(), (64 & 64) != 0 ? null : null);
                firExpectActualDeclarationChecker = this;
                firBasedSymbol2 = firBasedSymbol3;
                abstractKtSourceElement = abstractKtSourceElement2;
            }
        }
        AbstractKtSourceElement abstractKtSourceElement3 = abstractKtSourceElement;
        FirBasedSymbol<FirDeclaration> firBasedSymbol4 = firBasedSymbol2;
        List<ExpectActualIncompatibility> list4 = list3;
        if (!(list4 instanceof Collection) || !list4.isEmpty()) {
            for (ExpectActualIncompatibility expectActualIncompatibility2 : list4) {
                if ((expectActualIncompatibility2 instanceof ExpectActualIncompatibility.ClassKind) || (expectActualIncompatibility2 instanceof ExpectActualIncompatibility.Modality)) {
                    return;
                }
            }
        }
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            AbstractKtSourceElement abstractKtSourceElement4 = abstractKtSourceElement3;
            reportClassScopesIncompatibility(diagnosticReporter, checkerContext, firBasedSymbol4, firBasedSymbol, (ExpectActualIncompatibility.ClassScopes) it.next(), abstractKtSourceElement4);
            abstractKtSourceElement3 = abstractKtSourceElement4;
        }
    }

    private final void checkAmbiguousExpects(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirBasedSymbol<?> firBasedSymbol, Map<ExpectActualMatchingCompatibility, ? extends List<? extends FirBasedSymbol<?>>> map, FirBasedSymbol<?> firBasedSymbol2) {
        List<? extends FirBasedSymbol<?>> listEmptyList = map.get(ExpectActualMatchingCompatibility.MatchedSuccessfully.INSTANCE);
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List<? extends FirBasedSymbol<?>> list = listEmptyList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirBasedSymbol) it.next()).getModuleData());
        }
        List list2 = CollectionsKt.toList(CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExpectActualDeclarationChecker$checkAmbiguousExpects$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((FirModuleData) t).getName().asString(), ((FirModuleData) t2).getName().asString());
            }
        }));
        if (list2.size() > 1) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firBasedSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getAMBIGUOUS_EXPECTS(), (Object) firBasedSymbol2, (Object) list2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }

    private final void checkExpectDeclarationHasNoExternalModifier(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirMemberDeclaration firMemberDeclaration) {
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.MultiplatformRestrictions) && firMemberDeclaration.getStatus().isExternal()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firMemberDeclaration.getSource(), FirErrors.INSTANCE.getEXPECTED_EXTERNAL_DECLARATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final void checkExpectDeclarationModifiers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirMemberDeclaration firMemberDeclaration) {
        checkExpectDeclarationHasNoExternalModifier(checkerContext, diagnosticReporter, firMemberDeclaration);
        if (firMemberDeclaration instanceof FirProperty) {
            checkExpectPropertyAccessorsModifiers(checkerContext, diagnosticReporter, (FirProperty) firMemberDeclaration);
        }
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.MultiplatformRestrictions) && (firMemberDeclaration instanceof FirFunction) && firMemberDeclaration.getStatus().isTailRec()) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirFunction) firMemberDeclaration).getSource(), FirErrors.INSTANCE.getEXPECTED_TAILREC_FUNCTION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final void checkExpectPropertyAccessorModifiers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirPropertyAccessor firPropertyAccessor) {
        if (checkExpectPropertyAccessorModifiers$isDefault(firPropertyAccessor)) {
            return;
        }
        checkExpectDeclarationHasNoExternalModifier(checkerContext, diagnosticReporter, firPropertyAccessor);
    }

    private static final boolean checkExpectPropertyAccessorModifiers$isDefault(FirPropertyAccessor firPropertyAccessor) {
        KtSourceElement source = firPropertyAccessor.getSource();
        if (source != null) {
            return Intrinsics.areEqual(source.getKind(), KtFakeSourceElementKind.DefaultAccessor.INSTANCE);
        }
        k2d.a("expect-actual matching is only possible for code with sources");
        return false;
    }

    private final void checkExpectPropertyAccessorsModifiers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        Iterator it = CollectionsKt.listOfNotNull(new FirPropertyAccessor[]{firProperty.getGetter(), firProperty.getSetter()}).iterator();
        while (it.hasNext()) {
            checkExpectPropertyAccessorModifiers(checkerContext, diagnosticReporter, (FirPropertyAccessor) it.next());
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean containsExpectOrActualModifier(FirMemberDeclaration declaration) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirModifierList modifierList = FirKeywordUtilsKt.getModifierList(declaration.getSource());
        if (modifierList == null) {
            return false;
        }
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.EXPECT_KEYWORD;
        ktModifierKeywordToken.getClass();
        if (modifierList.contains(ktModifierKeywordToken)) {
            return true;
        }
        KtModifierKeywordToken ktModifierKeywordToken2 = KtTokens.ACTUAL_KEYWORD;
        ktModifierKeywordToken2.getClass();
        return modifierList.contains(ktModifierKeywordToken2);
    }

    private final List<ExpectActualIncompatibility<FirBasedSymbol<?>>> getCheckingCompatibility(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, FirBasedSymbol<?> firBasedSymbol2, FirRegularClassSymbol firRegularClassSymbol, FirRegularClassSymbol firRegularClassSymbol2, FirExpectActualMatchingContext firExpectActualMatchingContext) {
        if ((firBasedSymbol instanceof FirCallableSymbol) && (firBasedSymbol2 instanceof FirCallableSymbol)) {
            return AbstractExpectActualChecker.INSTANCE.getCallablesCompatibility((CallableSymbolMarker) firBasedSymbol2, (CallableSymbolMarker) firBasedSymbol, firRegularClassSymbol2, firRegularClassSymbol, firExpectActualMatchingContext, checkerContext.get$languageVersionSettings());
        }
        if ((firBasedSymbol instanceof FirClassLikeSymbol) && (firBasedSymbol2 instanceof RegularClassSymbolMarker)) {
            return AbstractExpectActualChecker.INSTANCE.getClassifiersCompatibility((RegularClassSymbolMarker) firBasedSymbol2, (ClassLikeSymbolMarker) firBasedSymbol, firExpectActualMatchingContext, checkerContext.get$languageVersionSettings());
        }
        k2d.a("These expect/actual shouldn't have been matched by FirExpectActualResolver");
        return null;
    }

    private final boolean hasActualModifier(FirElement firElement) {
        KtSourceElement source = firElement.getSource();
        KtSourceElementKind kind = source != null ? source.getKind() : null;
        if (kind == null || Intrinsics.areEqual(kind, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE) || Intrinsics.areEqual(kind, KtFakeSourceElementKind.EnumGeneratedDeclaration.INSTANCE) || Intrinsics.areEqual(kind, KtFakeSourceElementKind.ImplicitConstructor.INSTANCE)) {
            return false;
        }
        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.ACTUAL_KEYWORD;
        ktModifierKeywordToken.getClass();
        return FirKeywordUtilsKt.hasModifier(firElement, ktModifierKeywordToken);
    }

    private final boolean isFakeOverride(FirBasedSymbol<?> firBasedSymbol, FirRegularClassSymbol firRegularClassSymbol, FirExpectActualMatchingContext firExpectActualMatchingContext) {
        return firRegularClassSymbol != null && (firBasedSymbol instanceof FirCallableSymbol) && firExpectActualMatchingContext.isFakeOverride((CallableSymbolMarker) firBasedSymbol, firRegularClassSymbol);
    }

    private final boolean isUnderlyingPropertyOfInlineClass(FirBasedSymbol<?> symbol, FirRegularClassSymbol actualContainingClass, FirSession platformSession) {
        List<FirValueParameterSymbol> valueParameterSymbols;
        if ((!actualContainingClass.getRawStatus().isInline() && !actualContainingClass.getRawStatus().isValue()) || !(symbol instanceof FirPropertySymbol)) {
            return false;
        }
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(actualContainingClass, platformSession);
        return Intrinsics.areEqual((firConstructorSymbolPrimaryConstructorIfAny == null || (valueParameterSymbols = firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols()) == null) ? null : (FirValueParameterSymbol) CollectionsKt.singleOrNull(valueParameterSymbols), DeclarationAttributesKt.getCorrespondingValueParameterFromPrimaryConstructor((FirPropertySymbol) symbol));
    }

    private final void reportClassScopesIncompatibility(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirBasedSymbol<? extends FirDeclaration> firBasedSymbol, FirBasedSymbol<?> firBasedSymbol2, ExpectActualIncompatibility.ClassScopes<FirBasedSymbol<?>> classScopes, KtSourceElement ktSourceElement) {
        if ((!(firBasedSymbol instanceof FirRegularClassSymbol) && !(firBasedSymbol instanceof FirTypeAliasSymbol)) || !(firBasedSymbol2 instanceof FirRegularClassSymbol)) {
            ev7.a("Incompatible.ClassScopes is only possible for a class or a typealias: ", firBasedSymbol, 32, firBasedSymbol2);
            return;
        }
        List incompatibleMembers = classScopes.getIncompatibleMembers();
        ArrayList arrayList = new ArrayList();
        for (Object obj : incompatibleMembers) {
            if (!reportClassScopesIncompatibility$hasSingleActualSuspect((MemberIncompatibility) obj)) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                if (Intrinsics.areEqual(((MemberIncompatibility) obj2).getIncompatibility(), ExpectActualIncompatibility.ParametersWithDefaultValuesInExpectActualizedByFakeOverride.INSTANCE)) {
                    arrayList2.add(obj2);
                }
            }
            reportDefaultArgsIncompatibleMembers(diagnosticReporter, checkerContext, arrayList2, ktSourceElement, (FirRegularClassSymbol) firBasedSymbol2);
            ArrayList arrayList3 = new ArrayList();
            for (Object obj3 : arrayList) {
                if (Intrinsics.areEqual(((MemberIncompatibility) obj3).getIncompatibility(), ExpectActualIncompatibility.IgnorabilityIsDifferent.INSTANCE)) {
                    arrayList3.add(obj3);
                }
            }
            reportIgnorabilityIncompatibleMembers(diagnosticReporter, checkerContext, arrayList3, ktSourceElement);
            ArrayList<MemberIncompatibility> arrayList4 = new ArrayList();
            for (Object obj4 : arrayList) {
                MemberIncompatibility memberIncompatibility = (MemberIncompatibility) obj4;
                if (!Intrinsics.areEqual(memberIncompatibility.getIncompatibility(), ExpectActualIncompatibility.IgnorabilityIsDifferent.INSTANCE) && !Intrinsics.areEqual(memberIncompatibility.getIncompatibility(), ExpectActualIncompatibility.ParametersWithDefaultValuesInExpectActualizedByFakeOverride.INSTANCE)) {
                    arrayList4.add(obj4);
                }
            }
            if (!arrayList4.isEmpty()) {
                for (MemberIncompatibility memberIncompatibility2 : arrayList4) {
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory4<FirBasedSymbol<? extends FirDeclaration>, Object, Object, String>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) FirErrors.INSTANCE.getEXPECT_ACTUAL_INCOMPATIBLE_CLASS_SCOPE()), firBasedSymbol, memberIncompatibility2.getExpect(), memberIncompatibility2.getActual(), memberIncompatibility2.getIncompatibility().getReason(), (128 & 128) != 0 ? null : null);
                }
            }
        }
        if (classScopes.getMismatchedMembers().isEmpty()) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getNO_ACTUAL_CLASS_MEMBER_FOR_EXPECTED_CLASS(), (Object) firBasedSymbol, (Object) classScopes.getMismatchedMembers(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private static final boolean reportClassScopesIncompatibility$hasSingleActualSuspect(MemberIncompatibility<? extends FirBasedSymbol<?>> memberIncompatibility) {
        Collection<List<FirBasedSymbol<?>>> collectionValues;
        List list;
        Map<ExpectActualMatchingCompatibility, List<FirBasedSymbol<?>>> expectForActual = ExpectActualAttributesKt.getExpectForActual(((FirBasedSymbol) memberIncompatibility.getActual()).getFir());
        return Intrinsics.areEqual((expectForActual == null || (collectionValues = expectForActual.values()) == null || (list = (List) CollectionsKt.singleOrNull(collectionValues)) == null) ? null : (FirBasedSymbol) CollectionsKt.singleOrNull(list), memberIncompatibility.getExpect());
    }

    private final void reportDefaultArgsIncompatibleMembers(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, List<? extends MemberIncompatibility<? extends FirBasedSymbol<?>>> list, KtSourceElement ktSourceElement, FirRegularClassSymbol firRegularClassSymbol) {
        if (list.isEmpty()) {
            return;
        }
        List<? extends MemberIncompatibility<? extends FirBasedSymbol<?>>> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            Object expect = ((MemberIncompatibility) it.next()).getExpect();
            FirNamedFunctionSymbol firNamedFunctionSymbol = expect instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) expect : null;
            if (firNamedFunctionSymbol == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(ExpectActualIncompatibility.ParametersWithDefaultValuesInExpectActualizedByFakeOverride.INSTANCE);
                ej7.a(sb, " can be reported only for ", Reflection.getOrCreateKotlinClass(FirNamedFunctionSymbol.class));
                return;
            }
            arrayList.add(firNamedFunctionSymbol);
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getDEFAULT_ARGUMENTS_IN_EXPECT_ACTUALIZED_BY_FAKE_OVERRIDE(), (Object) firRegularClassSymbol, (Object) arrayList, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private final void reportIgnorabilityIncompatibleMembers(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, FirBasedSymbol<?> firBasedSymbol2, KtSourceElement ktSourceElement) {
        FirCallableSymbol firCallableSymbol = firBasedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) firBasedSymbol : null;
        if (firCallableSymbol == null) {
            k2d.a("Ignorability incompatibility can be reported only for callables");
            return;
        }
        FirCallableSymbol firCallableSymbol2 = firBasedSymbol2 instanceof FirCallableSymbol ? (FirCallableSymbol) firBasedSymbol2 : null;
        if (firCallableSymbol2 != null) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory4<FirCallableSymbol, ReturnValueStatus, FirCallableSymbol, ReturnValueStatus>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) FirErrors.INSTANCE.getACTUAL_IGNORABILITY_NOT_MATCH_EXPECT()), firCallableSymbol, firCallableSymbol.getResolvedStatus().getReturnValueStatus(), firCallableSymbol2, firCallableSymbol2.getResolvedStatus().getReturnValueStatus(), (128 & 128) != 0 ? null : null);
        } else {
            k2d.a("Ignorability incompatibility can be reported only for callables");
        }
    }

    private final boolean requireActualModifier(FirBasedSymbol<?> declaration, FirRegularClassSymbol actualContainingClass, FirSession platformSession) {
        KtSourceElement source = declaration.getSource();
        if (source != null) {
            return (Intrinsics.areEqual(source.getKind(), KtFakeSourceElementKind.ImplicitConstructor.INSTANCE) || Intrinsics.areEqual(source.getKind(), KtFakeSourceElementKind.EnumGeneratedDeclaration.INSTANCE) || Intrinsics.areEqual(declaration.getOrigin(), FirDeclarationOrigin.Synthetic.DataClassMember.INSTANCE) || org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isAnnotationConstructor(declaration, platformSession) || org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isPrimaryConstructorOfInlineOrValueClass(declaration, platformSession) || isUnderlyingPropertyOfInlineClass(declaration, actualContainingClass, platformSession) || (declaration instanceof FirEnumEntrySymbol)) ? false : true;
        }
        k2d.a("expect-actual matching is only possible for code with sources");
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        KtSourceElement source;
        KtSourceElementKind kind;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (firDeclaration instanceof FirMemberDeclaration) {
            if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.MultiPlatformProjects)) {
                FirMemberDeclaration firMemberDeclaration = (FirMemberDeclaration) firDeclaration;
                if ((!firMemberDeclaration.getStatus().isExpect() && !firMemberDeclaration.getStatus().isActual()) || !containsExpectOrActualModifier(firMemberDeclaration) || (source = firMemberDeclaration.getSource()) == null || (kind = source.getKind()) == null || kind.getShouldSkipErrorTypeReporting()) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firMemberDeclaration.getSource(), FirErrors.INSTANCE.getNOT_A_MULTIPLATFORM_COMPILATION(), (AbstractSourceElementPositioningStrategy) SourceElementPositioningStrategies.INSTANCE.getEXPECT_ACTUAL_MODIFIER());
                return;
            }
            if ((firDeclaration instanceof FirBackingField) || (firDeclaration instanceof FirPropertyAccessor) || (firDeclaration instanceof FirValueParameter)) {
                return;
            }
            FirMemberDeclaration firMemberDeclaration2 = (FirMemberDeclaration) firDeclaration;
            if (firMemberDeclaration2.getStatus().isExpect()) {
                checkExpectDeclarationModifiers(checkerContext, diagnosticReporter, firMemberDeclaration2);
            }
            Map<ExpectActualMatchingCompatibility, List<FirBasedSymbol<?>>> expectForActual = ExpectActualAttributesKt.getExpectForActual(firMemberDeclaration2.getSymbol());
            if (expectForActual == null) {
                expectForActual = MapsKt.emptyMap();
            }
            if ((expectForActual.containsKey(ExpectActualMatchingCompatibility.MatchedSuccessfully.INSTANCE) || hasActualModifier(firDeclaration)) && !DeclarationUtilsKt.isLocalDeclaredInBlock(firDeclaration)) {
                checkActualDeclarationHasExpected(checkerContext, diagnosticReporter, firMemberDeclaration2, expectForActual);
            }
        }
    }

    private final void reportIgnorabilityIncompatibleMembers(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, List<? extends MemberIncompatibility<? extends FirBasedSymbol<?>>> list, KtSourceElement ktSourceElement) {
        for (MemberIncompatibility<? extends FirBasedSymbol<?>> memberIncompatibility : list) {
            reportIgnorabilityIncompatibleMembers(diagnosticReporter, checkerContext, (FirBasedSymbol) memberIncompatibility.getExpect(), (FirBasedSymbol) memberIncompatibility.getActual(), ktSourceElement);
        }
    }
}

package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.ConeDiagnosticToFirDiagnosticKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirComponentCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedExpression;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeConstraintSystemHasContradiction;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeHiddenCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableWrongReceiver;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVisibilityError;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.lexer.KtSingleValueToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicabilityKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u0005EFGHIB\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\f\u001a\u00020\u0002H\u0002J\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J-\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0014H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0018J/\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0014H\u0000R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u001a\u0010\u0018J\u000e\u0010\u001b\u001a\u0004\u0018\u00010\u0014*\u00020\u0014H\u0002JE\u0010\u001c\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0017\u001a\u00020\u0014H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010!J/\u0010\"\u001a\u0004\u0018\u00010#*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010&J7\u0010'\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00142\b\u0010(\u001a\u0004\u0018\u00010)H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010*J\u000e\u0010+\u001a\u00020,*\u0004\u0018\u00010-H\u0002JM\u0010.\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010/\u001a\u00020\u001e2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u000204H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00105JE\u00106\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u00102\u001a\u00020\u00022\u0006\u00107\u001a\u00020\u00112\u0006\u00103\u001a\u000204H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00108J5\u00109\u001a\u00020\u00072\u0006\u00100\u001a\u0002012\u0006\u00103\u001a\u000204H\u0002R\u00020\nR\u00020\bj\u0006\u0010\u000b\u001a\u00020\nj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010:R\u001a\u0010;\u001a\u0004\u0018\u00010<*\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0018\u0010?\u001a\u00020)*\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b@\u0010AR\u001a\u0010B\u001a\u0004\u0018\u00010\u0011*\u00020<8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bC\u0010D¨\u0006J"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "getDestructuringVariableIfEntry", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "getDestructuringVariableOfEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "syntaxKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$DestructuringSyntax;", "Lorg/jetbrains/kotlin/KtSourceElement;", "originalDestructuringDeclaration", "checkFullFormLanguageFeature", "source", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;)V", "checkSquareBracketsLanguageFeature", "checkSquareBracketsLanguageFeature$org_jetbrains_kotlin_checkers", "findSquareBracket", "checkChangingMeaningOfShortSyntax", "originalDestructuringDeclarationType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "componentIndex", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;ILorg/jetbrains/kotlin/KtSourceElement;)V", "getProblem", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$ProblemType;", "destructuredName", "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;ILorg/jetbrains/kotlin/name/Name;)Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$ProblemType;", "checkInitializer", "initializer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "isMissingInitializer", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "reportGivenDiagnostic", "destructuringDeclarationType", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "property", "componentCall", "Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;)V", "checkComponentTypeMismatch", "destructuringDeclaration", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;)V", "reportDefaultDiagnostics", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;)V", "explicitReceiverOfQualifiedAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "getExplicitReceiverOfQualifiedAccess", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "unwrapped", "getUnwrapped", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "resolvedVariable", "getResolvedVariable", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "DestructuringSyntax", "ProblemType", "DataClassNameMismatch", "NonDataClass", "DataClassCustomComponent", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDestructuringDeclarationChecker extends FirDeclarationChecker<FirProperty> {
    public static final FirDestructuringDeclarationChecker INSTANCE = new FirDestructuringDeclarationChecker();

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$DataClassCustomComponent;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$ProblemType;", "<init>", "()V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DataClassCustomComponent extends ProblemType {
        public static final DataClassCustomComponent INSTANCE = new DataClassCustomComponent();

        private DataClassCustomComponent() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$DataClassNameMismatch;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$ProblemType;", "propertyName", "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/name/Name;)V", "getPropertyName", "()Lorg/jetbrains/kotlin/name/Name;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DataClassNameMismatch extends ProblemType {
        private final Name propertyName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DataClassNameMismatch(Name name) {
            super(null);
            name.getClass();
            this.propertyName = name;
        }

        public final Name getPropertyName() {
            return this.propertyName;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$DestructuringSyntax;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "ParensShort", "ParensFull", "SquareBracketsShort", "SquareBracketsFull", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum DestructuringSyntax {
        ParensShort,
        ParensFull,
        SquareBracketsShort,
        SquareBracketsFull;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<DestructuringSyntax> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$NonDataClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$ProblemType;", "<init>", "()V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class NonDataClass extends ProblemType {
        public static final NonDataClass INSTANCE = new NonDataClass();

        private NonDataClass() {
            super(null);
        }
    }

    private FirDestructuringDeclarationChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkChangingMeaningOfShortSyntax(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty, ConeKotlinType coneKotlinType, int i, KtSourceElement ktSourceElement) {
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.DeprecateNameMismatchInShortDestructuringWithParentheses) || LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.EnableNameBasedDestructuringShortForm)) {
            return;
        }
        if (Intrinsics.areEqual(firProperty.getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getDESTRUCTURING_SHORT_FORM_UNDERSCORE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        ProblemType problem = getProblem(checkerContext, coneKotlinType, i, firProperty.getName());
        if (problem == null) {
            return;
        }
        if (Intrinsics.areEqual(problem, NonDataClass.INSTANCE) || Intrinsics.areEqual(problem, DataClassCustomComponent.INSTANCE)) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory3<ConeKotlinType, Name, String>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getDESTRUCTURING_SHORT_FORM_OF_NON_DATA_CLASS()), coneKotlinType, firProperty.getName(), problem instanceof NonDataClass ? "non-data class" : "custom component operators of data class", (64 & 64) != 0 ? null : null);
        } else if (problem instanceof DataClassNameMismatch) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getDESTRUCTURING_SHORT_FORM_NAME_MISMATCH(), (Object) firProperty.getName(), (Object) ((DataClassNameMismatch) problem).getPropertyName(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        } else {
            bu8.a();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void checkComponentTypeMismatch(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, KtSourceElement ktSourceElement, FirProperty firProperty, FirVariable firVariable, FirComponentCall firComponentCall) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firComponentCall);
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef());
        if (AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(checkerContext.getSession()), resolvedType, coneType, false, 8, (Object) null)) {
            return;
        }
        if (firVariable instanceof FirValueParameter) {
            ktSourceElement = firProperty.getSource();
        }
        KtSourceElement ktSourceElement2 = ktSourceElement;
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement2, (KtDiagnosticFactory3<Name, ConeKotlinType, ConeKotlinType>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getCOMPONENT_FUNCTION_RETURN_TYPE_MISMATCH()), firComponentCall.getCalleeReference().getName(), resolvedType, coneType, (64 & 64) != 0 ? null : null);
    }

    private final void checkFullFormLanguageFeature(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement) {
        LanguageFeature languageFeature = LanguageFeature.NameBasedDestructuring;
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, languageFeature)) {
            return;
        }
        TokenSet tokenSet = KtTokens.VAL_VAR;
        tokenSet.getClass();
        KtSourceElement child$default = FirSourceUtilsKt.getChild$default(ktSourceElement, tokenSet, 0, 1, false, 10, (Object) null);
        if (child$default != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) child$default, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), (Object) TuplesKt.to(languageFeature, checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final void checkInitializer(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, KtSourceElement ktSourceElement, FirExpression firExpression) {
        if (isMissingInitializer(firExpression)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getINITIALIZER_REQUIRED_FOR_DESTRUCTURING_DECLARATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final KtSourceElement findSquareBracket(KtSourceElement ktSourceElement) {
        IElementType elementType = ktSourceElement.getElementType();
        IElementType iElementType = KtNodeTypes.DESTRUCTURING_DECLARATION;
        if (Intrinsics.areEqual(elementType, iElementType)) {
            KtSingleValueToken ktSingleValueToken = KtTokens.LBRACKET;
            ktSingleValueToken.getClass();
            return FirSourceUtilsKt.getChild$default(ktSourceElement, (IElementType) ktSingleValueToken, 0, 1, false, 10, (Object) null);
        }
        if (Intrinsics.areEqual(elementType, KtNodeTypes.VALUE_PARAMETER)) {
            iElementType.getClass();
            KtSourceElement child$default = FirSourceUtilsKt.getChild$default(ktSourceElement, iElementType, 0, 1, false, 10, (Object) null);
            if (child$default != null) {
                return findSquareBracket(child$default);
            }
        }
        return null;
    }

    private final FirVariable getDestructuringVariableOfEntry(FirProperty declaration) {
        FirQualifiedAccessExpression explicitReceiverOfQualifiedAccess;
        FirExpression initializer = declaration.getInitializer();
        if (initializer == null || (explicitReceiverOfQualifiedAccess = getExplicitReceiverOfQualifiedAccess(initializer)) == null) {
            return null;
        }
        return getResolvedVariable(explicitReceiverOfQualifiedAccess);
    }

    private final FirQualifiedAccessExpression getExplicitReceiverOfQualifiedAccess(FirExpression firExpression) {
        FirExpression explicitReceiver;
        FirQualifiedAccessExpression firQualifiedAccessExpression = firExpression instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firExpression : null;
        FirExpression unwrapped = (firQualifiedAccessExpression == null || (explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver()) == null) ? null : getUnwrapped(explicitReceiver);
        if (unwrapped instanceof FirQualifiedAccessExpression) {
            return (FirQualifiedAccessExpression) unwrapped;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0053  */
    private final ProblemType getProblem(CheckerContext checkerContext, ConeKotlinType coneKotlinType, int i, Name name) {
        Name name2;
        Object next;
        List<FirValueParameterSymbol> valueParameterSymbols;
        FirValueParameterSymbol firValueParameterSymbol;
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType));
        if (regularClassSymbol == null) {
            return null;
        }
        if (regularClassSymbol.getRawStatus().isData()) {
            Iterator<T> it = FirScopeKt.getDeclaredConstructors(FirHelpersKt.declaredMemberScope(checkerContext, regularClassSymbol)).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!((FirConstructorSymbol) next).isPrimary());
            FirConstructorSymbol firConstructorSymbol = (FirConstructorSymbol) next;
            if (firConstructorSymbol == null || (valueParameterSymbols = firConstructorSymbol.getValueParameterSymbols()) == null || (firValueParameterSymbol = (FirValueParameterSymbol) CollectionsKt.getOrNull(valueParameterSymbols, i - 1)) == null) {
                name2 = null;
            } else {
                name2 = firValueParameterSymbol.getName();
            }
        } else if (!Intrinsics.areEqual(regularClassSymbol.getClassId(), StandardClassIds.INSTANCE.getMapEntry())) {
            name2 = null;
        } else if (i == 1) {
            name2 = StandardNames.MAP_ENTRY_KEY;
        } else if (i != 2) {
            name2 = null;
        } else {
            name2 = StandardNames.MAP_ENTRY_VALUE;
        }
        if (!regularClassSymbol.getRawStatus().isData() && name2 == null && i == 1) {
            return NonDataClass.INSTANCE;
        }
        if (regularClassSymbol.getRawStatus().isData() && name2 == null) {
            return DataClassCustomComponent.INSTANCE;
        }
        if (name2 == null || Intrinsics.areEqual(name2, name)) {
            return null;
        }
        return new DataClassNameMismatch(name2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirVariable getResolvedVariable(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        FirVariableSymbol resolvedVariableSymbol$default = FirReferenceUtilsKt.toResolvedVariableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedVariableSymbol$default == null) {
            return null;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(resolvedVariableSymbol$default, FirResolvePhase.BODY_RESOLVE);
        return (FirVariable) resolvedVariableSymbol$default.getFir();
    }

    private final FirExpression getUnwrapped(FirExpression firExpression) {
        if (firExpression instanceof FirSmartCastExpression) {
            return ((FirSmartCastExpression) firExpression).getOriginalExpression();
        }
        return firExpression instanceof FirWrappedExpression ? ((FirWrappedExpression) firExpression).getExpression() : firExpression;
    }

    private final boolean isMissingInitializer(FirElement firElement) {
        if (firElement != null) {
            return (firElement instanceof FirErrorExpression) && (((FirErrorExpression) firElement).getDiagnostic() instanceof ConeSyntaxDiagnostic);
        }
        return true;
    }

    private final void reportDefaultDiagnostics(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, ConeDiagnostic coneDiagnostic, FirComponentCall firComponentCall) {
        Iterator it = ConeDiagnosticToFirDiagnosticKt.toFirDiagnostics$default(coneDiagnostic, checkerContext.getSession(), firComponentCall.getSource(), null, null, 8, null).iterator();
        while (it.hasNext()) {
            diagnosticReporter.report((KtDiagnostic) it.next(), checkerContext);
        }
    }

    private final void reportGivenDiagnostic(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, ConeDiagnostic coneDiagnostic, FirProperty firProperty, FirComponentCall firComponentCall) {
        if (coneDiagnostic instanceof ConeUnresolvedNameError) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getCOMPONENT_FUNCTION_MISSING(), (Object) ((ConeUnresolvedNameError) coneDiagnostic).getName(), (Object) coneKotlinType, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        if (coneDiagnostic instanceof ConeHiddenCandidateError) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getCOMPONENT_FUNCTION_MISSING(), (Object) ((ConeHiddenCandidateError) coneDiagnostic).getCandidate().getCallInfo().getName(), (Object) coneKotlinType, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        if (coneDiagnostic instanceof ConeInapplicableWrongReceiver) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getCOMPONENT_FUNCTION_MISSING(), (Object) ((AbstractCallCandidate) CollectionsKt.first(((ConeInapplicableWrongReceiver) coneDiagnostic).getCandidates())).getCallInfo().getName(), (Object) coneKotlinType, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        boolean z = coneDiagnostic instanceof ConeAmbiguityError;
        if (z) {
            ConeAmbiguityError coneAmbiguityError = (ConeAmbiguityError) coneDiagnostic;
            if (CandidateApplicabilityKt.isSuccess(coneAmbiguityError.getApplicability())) {
                KtDiagnosticFactory3<Name, Collection<FirBasedSymbol<?>>, ConeKotlinType> component_function_ambiguity = FirErrors.INSTANCE.getCOMPONENT_FUNCTION_AMBIGUITY();
                Name name = coneAmbiguityError.getName();
                Collection<AbstractCandidate> candidates = coneAmbiguityError.getCandidates();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(candidates, 10));
                Iterator<T> it = candidates.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AbstractCandidate) it.next()).getSymbol());
                }
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory3<Name, ArrayList, ConeKotlinType>) ((KtDiagnosticFactory3<Object, Object, Object>) component_function_ambiguity), name, arrayList, coneKotlinType, (64 & 64) != 0 ? null : null);
                return;
            }
        }
        if (z) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getCOMPONENT_FUNCTION_MISSING(), (Object) ((ConeAmbiguityError) coneDiagnostic).getName(), (Object) coneKotlinType, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        if (coneDiagnostic instanceof ConeInapplicableCandidateError) {
            if (!ConeTypeUtilsKt.isMarkedNullable(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType))) {
                reportDefaultDiagnostics(diagnosticReporter, checkerContext, coneDiagnostic, firComponentCall);
                return;
            }
            KtDiagnosticFactory2<Name, ConeKotlinType> component_function_on_nullable = FirErrors.INSTANCE.getCOMPONENT_FUNCTION_ON_NULLABLE();
            FirBasedSymbol<?> symbol = ((ConeInapplicableCandidateError) coneDiagnostic).getCandidate().getSymbol();
            symbol.getClass();
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) component_function_on_nullable, (Object) ((FirNamedFunctionSymbol) symbol).getCallableId().getCallableName(), (Object) coneKotlinType, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        if (coneDiagnostic instanceof ConeConstraintSystemHasContradiction) {
            if (FirTypeUtilsKt.getResolvedType(firComponentCall) instanceof ConeErrorType) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getCOMPONENT_FUNCTION_MISSING(), (Object) ((AbstractCallCandidate) CollectionsKt.first(((ConeConstraintSystemHasContradiction) coneDiagnostic).getCandidates())).getCallInfo().getName(), (Object) coneKotlinType, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        } else if (coneDiagnostic instanceof ConeVisibilityError) {
            diagnosticReporter.report(ConeDiagnosticToFirDiagnosticKt.toInvisibleReferenceDiagnostic(((ConeVisibilityError) coneDiagnostic).getSymbol(), firProperty.getSource(), checkerContext.getSession()), checkerContext);
        } else {
            reportDefaultDiagnostics(diagnosticReporter, checkerContext, coneDiagnostic, firComponentCall);
        }
    }

    private final DestructuringSyntax syntaxKind(KtSourceElement ktSourceElement, FirVariable firVariable) {
        KtSourceElement source = firVariable.getSource();
        boolean z = (source != null ? findSquareBracket(source) : null) != null;
        TokenSet tokenSet = KtTokens.VAL_VAR;
        tokenSet.getClass();
        boolean z2 = FirSourceUtilsKt.getChild$default(ktSourceElement, tokenSet, 0, 1, false, 10, (Object) null) != null;
        if (z) {
            if (z2) {
                return DestructuringSyntax.SquareBracketsFull;
            }
            if (!z2) {
                return DestructuringSyntax.SquareBracketsShort;
            }
            bu8.a();
            return null;
        }
        if (z) {
            bu8.a();
            return null;
        }
        if (z2) {
            return DestructuringSyntax.ParensFull;
        }
        if (!z2) {
            return DestructuringSyntax.ParensShort;
        }
        bu8.a();
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:104:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0132  */
    /* JADX WARN: Code duplicated, block: B:85:0x013f  */
    /* JADX WARN: Code duplicated, block: B:88:0x0148  */
    /* JADX WARN: Code duplicated, block: B:90:0x0159  */
    /* JADX WARN: Code duplicated, block: B:93:0x0168  */
    /* JADX WARN: Code duplicated, block: B:95:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirVariable destructuringVariableOfEntry;
        FirElement firElement;
        KtSourceElement source;
        ConeKotlinType coneKotlinType;
        ConeKotlinType resolvedType;
        FirComponentCall firComponentCall;
        ConeDiagnostic diagnostic;
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        FirExpression initializer;
        FirExpression initializer2;
        KtSourceElement source2;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firProperty.getClass();
        KtSourceElement source3 = firProperty.getSource();
        if (source3 == null) {
            return;
        }
        if (Intrinsics.areEqual(source3.getElementType(), KtNodeTypes.DESTRUCTURING_DECLARATION)) {
            checkInitializer(diagnosticReporter, checkerContext, source3, firProperty.getInitializer());
            checkSquareBracketsLanguageFeature$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, source3);
            return;
        }
        if (Intrinsics.areEqual(firProperty.getName(), SpecialNames.DESTRUCT)) {
            checkSquareBracketsLanguageFeature$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, source3);
        }
        if (Intrinsics.areEqual(source3.getElementType(), KtNodeTypes.DESTRUCTURING_DECLARATION_ENTRY)) {
            FirExpression initializer3 = firProperty.getInitializer();
            FirQualifiedAccessExpression firQualifiedAccessExpression = initializer3 instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) initializer3 : null;
            if (firQualifiedAccessExpression == null || (destructuringVariableOfEntry = getDestructuringVariableOfEntry(firProperty)) == null) {
                return;
            }
            if (destructuringVariableOfEntry instanceof FirProperty) {
                FirProperty firProperty2 = (FirProperty) destructuringVariableOfEntry;
                FirExpression initializer4 = firProperty2.getInitializer();
                if (Intrinsics.areEqual((initializer4 == null || (source2 = initializer4.getSource()) == null) ? null : source2.getElementType(), KtNodeTypes.FOR)) {
                    FirExpression initializer5 = firProperty2.getInitializer();
                    FirQualifiedAccessExpression explicitReceiverOfQualifiedAccess = initializer5 != null ? getExplicitReceiverOfQualifiedAccess(initializer5) : null;
                    FirVariable resolvedVariable = explicitReceiverOfQualifiedAccess != null ? getResolvedVariable(explicitReceiverOfQualifiedAccess) : null;
                    FirProperty firProperty3 = resolvedVariable instanceof FirProperty ? (FirProperty) resolvedVariable : null;
                    initializer = (firProperty3 == null || (initializer2 = firProperty3.getInitializer()) == null) ? null : getExplicitReceiverOfQualifiedAccess(initializer2);
                } else {
                    initializer = firProperty2.getInitializer();
                }
                firElement = initializer;
            } else {
                firElement = destructuringVariableOfEntry instanceof FirValueParameter ? destructuringVariableOfEntry : null;
            }
            if (firElement == null) {
                return;
            }
            DestructuringSyntax destructuringSyntaxSyntaxKind = syntaxKind(source3, destructuringVariableOfEntry);
            if (destructuringSyntaxSyntaxKind == DestructuringSyntax.ParensFull) {
                checkFullFormLanguageFeature(checkerContext, diagnosticReporter, source3);
            }
            if (firQualifiedAccessExpression instanceof FirComponentCall) {
                KtSingleValueToken ktSingleValueToken = KtTokens.EQ;
                ktSingleValueToken.getClass();
                KtSourceElement child$default = FirSourceUtilsKt.getChild$default(source3, (IElementType) ktSingleValueToken, 0, 1, false, 10, (Object) null);
                if (child$default != null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) child$default, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), (Object) TuplesKt.to(LanguageFeature.EnableNameBasedDestructuringShortForm, checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                if (isMissingInitializer(firElement) || (source = firElement.getSource()) == null) {
                    return;
                }
                if (!(firElement instanceof FirVariable)) {
                    if (firElement instanceof FirExpression) {
                        resolvedType = FirTypeUtilsKt.getResolvedType((FirExpression) firElement);
                    } else {
                        coneKotlinType = null;
                    }
                    if (coneKotlinType == null) {
                        return;
                    }
                    firComponentCall = (FirComponentCall) firQualifiedAccessExpression;
                    FirNamedReference calleeReference = firComponentCall.getCalleeReference();
                    diagnostic = FirReferenceUtilsKt.isError(calleeReference) ? ((FirDiagnosticHolder) calleeReference).getDiagnostic() : null;
                    if (diagnostic != null) {
                        checkerContext2 = checkerContext;
                        diagnosticReporter2 = diagnosticReporter;
                        reportGivenDiagnostic(diagnosticReporter2, checkerContext2, source, coneKotlinType, diagnostic, firProperty, firComponentCall);
                        firComponentCall = firComponentCall;
                    } else {
                        checkerContext2 = checkerContext;
                        diagnosticReporter2 = diagnosticReporter;
                    }
                    checkComponentTypeMismatch(diagnosticReporter2, checkerContext2, source, firProperty, destructuringVariableOfEntry, firComponentCall);
                    if (destructuringSyntaxSyntaxKind == DestructuringSyntax.ParensShort) {
                        checkChangingMeaningOfShortSyntax(checkerContext, diagnosticReporter, firProperty, coneKotlinType, firComponentCall.getComponentIndex(), source3);
                    }
                }
                resolvedType = FirTypeUtilsKt.getConeType(((FirVariable) firElement).getReturnTypeRef());
                coneKotlinType = resolvedType;
                if (coneKotlinType == null) {
                    return;
                }
                firComponentCall = (FirComponentCall) firQualifiedAccessExpression;
                FirNamedReference calleeReference2 = firComponentCall.getCalleeReference();
                diagnostic = FirReferenceUtilsKt.isError(calleeReference2) ? ((FirDiagnosticHolder) calleeReference2).getDiagnostic() : null;
                if (diagnostic != null) {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                    reportGivenDiagnostic(diagnosticReporter2, checkerContext2, source, coneKotlinType, diagnostic, firProperty, firComponentCall);
                    firComponentCall = firComponentCall;
                } else {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                }
                checkComponentTypeMismatch(diagnosticReporter2, checkerContext2, source, firProperty, destructuringVariableOfEntry, firComponentCall);
                if (destructuringSyntaxSyntaxKind == DestructuringSyntax.ParensShort) {
                    checkChangingMeaningOfShortSyntax(checkerContext, diagnosticReporter, firProperty, coneKotlinType, firComponentCall.getComponentIndex(), source3);
                }
            }
        }
    }

    public final void checkSquareBracketsLanguageFeature$org_jetbrains_kotlin_checkers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement) {
        KtSourceElement ktSourceElementFindSquareBracket;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        ktSourceElement.getClass();
        LanguageFeature languageFeature = LanguageFeature.NameBasedDestructuring;
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, languageFeature) || (ktSourceElementFindSquareBracket = findSquareBracket(ktSourceElement)) == null) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElementFindSquareBracket, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), (Object) TuplesKt.to(languageFeature, checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    public final FirVariableSymbol<?> getDestructuringVariableIfEntry(FirProperty declaration) {
        FirVariable destructuringVariableOfEntry;
        declaration.getClass();
        KtSourceElement source = declaration.getSource();
        if (!Intrinsics.areEqual(source != null ? source.getElementType() : null, KtNodeTypes.DESTRUCTURING_DECLARATION_ENTRY) || (destructuringVariableOfEntry = INSTANCE.getDestructuringVariableOfEntry(declaration)) == null) {
            return null;
        }
        return destructuringVariableOfEntry.getSymbol();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0004\u0005\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$ProblemType;", Argument.Delimiters.none, "<init>", "()V", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$DataClassCustomComponent;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$DataClassNameMismatch;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDestructuringDeclarationChecker$NonDataClass;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ProblemType {
        public /* synthetic */ ProblemType(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private ProblemType() {
        }
    }
}

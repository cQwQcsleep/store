package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.KotlinCompilerVersion;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersion;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo;
import org.jetbrains.kotlin.fir.declarations.FutureApiDeprecationInfo;
import org.jetbrains.kotlin.fir.declarations.RequireKotlinDeprecationInfo;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeCallToDeprecatedOverrideOfHidden;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0010JC\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00022\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0016J\u0017\u0010\u001c\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u0019H\u0000¢\u0006\u0002\b\u001eJ)\u0010\u001f\u001a\u00020 *\u00020\u00022\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010!JI\u0010\"\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0000R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b%\u0010&J\u0018\u0010)\u001a\u00020 *\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010*\u001a\u00020+H\u0002J3\u0010,\u001a\u00020 *\u00020+2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\b\u0010#\u001a\u0004\u0018\u00010$H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010-JM\u0010.\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010/\u001a\u00020 2\u0006\u00100\u001a\u00020+H\u0000R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b1\u00102JC\u00103\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u00100\u001a\u000204H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u00105JK\u00106\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010/\u001a\u00020 2\u0006\u00100\u001a\u00020+H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u00102J7\u00107\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u00100\u001a\u000208H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u00109J1\u0010:\u001a\u0004\u0018\u00010+2\b\u0010#\u001a\u0004\u0018\u00010$2\n\u0010;\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010<J#\u0010=\u001a\b\u0012\u0002\b\u0003\u0018\u00010>*\u00020?H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010@R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0018X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirDeprecationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "<init>", "()V", "filteredSourceKinds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "reportCallToDeprecatedOverrideOfHidden", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "referencedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "DeprecatedOverrideOfHiddenReplacements", Argument.Delimiters.none, Argument.Delimiters.none, "getDeprecatedOverrideOfHiddenReplacements$org_jetbrains_kotlin_checkers", "()Ljava/util/Map;", "getDeprecatedOverrideOfHiddenMessage", "callableName", "getDeprecatedOverrideOfHiddenMessage$org_jetbrains_kotlin_checkers", "isDelegatedPropertySelfAccess", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "reportApiStatusIfNeeded", "callSite", "Lorg/jetbrains/kotlin/fir/FirElement;", "reportApiStatusIfNeeded$org_jetbrains_kotlin_checkers", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/FirElement;)V", "NestedTypeAliasesSinceVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement$Version;", "isNestedTypeAliasReferenceAndRelevantDeprecation", "deprecation", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "isTypealiasExpansionOf", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/FirElement;)Z", "reportApiStatus", "isTypealiasExpansion", "deprecationInfo", "reportApiStatus$org_jetbrains_kotlin_checkers", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;ZLorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;)V", "reportVersionRequirementDeprecation", "Lorg/jetbrains/kotlin/fir/declarations/RequireKotlinDeprecationInfo;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/declarations/RequireKotlinDeprecationInfo;)V", "reportDeprecation", "reportApiNotAvailable", "Lorg/jetbrains/kotlin/fir/declarations/FutureApiDeprecationInfo;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/declarations/FutureApiDeprecationInfo;)V", "getWorstDeprecation", "symbol", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "classSymbolItIsCalledThrough", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeprecationChecker extends FirExpressionChecker<FirStatement> {
    private static final VersionRequirement.Version NestedTypeAliasesSinceVersion;
    public static final FirDeprecationChecker INSTANCE = new FirDeprecationChecker();
    private static final Set<KtFakeSourceElementKind> filteredSourceKinds = SetsKt.setOf(new KtFakeSourceElementKind[]{KtFakeSourceElementKind.PropertyFromParameter.INSTANCE, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE});
    private static final Map<String, String> DeprecatedOverrideOfHiddenReplacements = MapsKt.mapOf(new Pair[]{TuplesKt.to("getFirst", "first()"), TuplesKt.to("getLast", "last()"), TuplesKt.to("toArray", null)});

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DeprecationLevelValue.values().length];
            try {
                iArr[DeprecationLevelValue.WARNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DeprecationLevelValue.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DeprecationLevelValue.HIDDEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ProtoBuf.VersionRequirement.VersionKind.values().length];
            try {
                iArr2[ProtoBuf.VersionRequirement.VersionKind.COMPILER_VERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ProtoBuf.VersionRequirement.VersionKind.LANGUAGE_VERSION.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ProtoBuf.VersionRequirement.VersionKind.API_VERSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        LanguageVersion sinceVersion = LanguageFeature.NestedTypeAliases.getSinceVersion();
        sinceVersion.getClass();
        NestedTypeAliasesSinceVersion = new VersionRequirement.Version(sinceVersion.getMajor(), sinceVersion.getMinor(), 0, 4, (DefaultConstructorMarker) null);
    }

    private FirDeprecationChecker() {
        super(MppCheckerKind.Common);
    }

    private final FirClassLikeSymbol<?> classSymbolItIsCalledThrough(CheckerContext checkerContext, FirConstructorSymbol firConstructorSymbol) {
        FirTypeAliasSymbol typeAliasSymbol;
        TypeAliasConstructorInfo<?> typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo(firConstructorSymbol);
        return (typeAliasConstructorInfo == null || (typeAliasSymbol = typeAliasConstructorInfo.getTypeAliasSymbol()) == null) ? TypeUtilsKt.toRegularClassSymbol(firConstructorSymbol.getResolvedReturnTypeRef(), checkerContext.getSession()) : typeAliasSymbol;
    }

    private final FirDeprecationInfo getWorstDeprecation(CheckerContext checkerContext, FirElement firElement, FirBasedSymbol<?> firBasedSymbol) {
        FirClassLikeSymbol<?> firClassLikeSymbolClassSymbolItIsCalledThrough;
        FirDeprecationInfo deprecation = null;
        FirConstructorSymbol firConstructorSymbol = firBasedSymbol instanceof FirConstructorSymbol ? (FirConstructorSymbol) firBasedSymbol : null;
        if (firConstructorSymbol != null && (firClassLikeSymbolClassSymbolItIsCalledThrough = classSymbolItIsCalledThrough(checkerContext, firConstructorSymbol)) != null) {
            deprecation = DeprecationUtilsKt.getDeprecation(firClassLikeSymbolClassSymbolItIsCalledThrough, checkerContext.getSession(), firElement);
        }
        return (FirDeprecationInfo) CollectionsKt.maxOrNull(CollectionsKt.listOfNotNull(new FirDeprecationInfo[]{deprecation, DeprecationUtilsKt.getDeprecation(firBasedSymbol, checkerContext.getSession(), firElement)}));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean isDelegatedPropertySelfAccess(CheckerContext checkerContext, FirStatement firStatement, FirBasedSymbol<?> firBasedSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        KtSourceElement source = firStatement.getSource();
        if (!Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE)) {
            return false;
        }
        List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
        int size = containingDeclarations.size();
        FirDeclaration fir = firBasedSymbol.getFir();
        FirBasedSymbol firBasedSymbol2 = (FirBasedSymbol) CollectionsKt.getOrNull(containingDeclarations, size - 1);
        if (Intrinsics.areEqual(firBasedSymbol2 != null ? firBasedSymbol2.getFir() : null, fir)) {
            return true;
        }
        FirBasedSymbol firBasedSymbol3 = (FirBasedSymbol) CollectionsKt.getOrNull(containingDeclarations, size - 2);
        return Intrinsics.areEqual(firBasedSymbol3 != null ? firBasedSymbol3.getFir() : null, fir);
    }

    private final boolean isNestedTypeAliasReferenceAndRelevantDeprecation(FirBasedSymbol<?> firBasedSymbol, FirDeprecationInfo firDeprecationInfo) {
        ClassId classId;
        VersionRequirement versionRequirement;
        if (!(firBasedSymbol instanceof FirTypeAliasSymbol)) {
            if (firBasedSymbol instanceof FirConstructorSymbol) {
                FirConstructorSymbol firConstructorSymbol = (FirConstructorSymbol) firBasedSymbol;
                if (Intrinsics.areEqual(firConstructorSymbol.getOrigin(), FirDeclarationOrigin.Synthetic.TypeAliasConstructor.INSTANCE)) {
                    ConeKotlinType abbreviatedType = AbbreviatedTypeAttributeKt.getAbbreviatedType(firConstructorSymbol.getResolvedReturnType());
                    ConeClassLikeTypeImpl coneClassLikeTypeImpl = abbreviatedType instanceof ConeClassLikeTypeImpl ? (ConeClassLikeTypeImpl) abbreviatedType : null;
                    if (coneClassLikeTypeImpl != null && (classId = ConeTypeUtilsKt.getClassId((ConeClassLikeType) coneClassLikeTypeImpl)) != null && classId.isNestedClass()) {
                    }
                }
            }
        }
        if (!((FirTypeAliasSymbol) firBasedSymbol).getClassId().isNestedClass()) {
            return false;
        }
        RequireKotlinDeprecationInfo requireKotlinDeprecationInfo = firDeprecationInfo instanceof RequireKotlinDeprecationInfo ? (RequireKotlinDeprecationInfo) firDeprecationInfo : null;
        return requireKotlinDeprecationInfo != null && (versionRequirement = requireKotlinDeprecationInfo.getVersionRequirement()) != null && versionRequirement.getKind() == ProtoBuf.VersionRequirement.VersionKind.LANGUAGE_VERSION && versionRequirement.getLevel() == DeprecationLevel.ERROR && Intrinsics.areEqual(versionRequirement.getVersion(), NestedTypeAliasesSinceVersion);
    }

    private final boolean isTypealiasExpansionOf(CheckerContext checkerContext, FirDeprecationInfo firDeprecationInfo, FirBasedSymbol<?> firBasedSymbol, FirElement firElement) {
        FirTypeAliasSymbol typeAliasSymbol;
        if (!(firBasedSymbol instanceof FirConstructorSymbol)) {
            if (!(firBasedSymbol instanceof FirTypeAliasSymbol)) {
                return false;
            }
            FirDeprecationInfo ownDeprecation = DeprecationUtilsKt.getOwnDeprecation(firBasedSymbol, checkerContext.getSession(), firElement);
            return ownDeprecation == null || ownDeprecation.compareTo(firDeprecationInfo) < 0;
        }
        TypeAliasConstructorInfo<?> typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo((FirConstructorSymbol) firBasedSymbol);
        if (typeAliasConstructorInfo == null || (typeAliasSymbol = typeAliasConstructorInfo.getTypeAliasSymbol()) == null) {
            return false;
        }
        return INSTANCE.isTypealiasExpansionOf(checkerContext, firDeprecationInfo, typeAliasSymbol, firElement);
    }

    private final void reportApiNotAvailable(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, FutureApiDeprecationInfo futureApiDeprecationInfo) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getAPI_NOT_AVAILABLE(), (Object) futureApiDeprecationInfo.getSinceVersion(), (Object) checkerContext.get$languageVersionSettings().getApiVersion(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    public static /* synthetic */ void reportApiStatusIfNeeded$org_jetbrains_kotlin_checkers$default(FirDeprecationChecker firDeprecationChecker, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, FirBasedSymbol firBasedSymbol, FirElement firElement, int i, Object obj) {
        if ((i & 16) != 0) {
            firElement = null;
        }
        firDeprecationChecker.reportApiStatusIfNeeded$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, ktSourceElement, firBasedSymbol, firElement);
    }

    private final void reportCallToDeprecatedOverrideOfHidden(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement, KtSourceElement ktSourceElement, FirBasedSymbol<?> firBasedSymbol) {
        FirCallableSymbol delegateFunctionSymbol;
        CallableId callableId;
        Name callableName;
        FirSyntheticPropertyAccessorSymbol getterSymbol;
        if ((firStatement instanceof FirQualifiedAccessExpression) && ((FirQualifiedAccessExpression) firStatement).getNonFatalDiagnostics().contains(ConeCallToDeprecatedOverrideOfHidden.INSTANCE)) {
            String strAsString = null;
            FirSyntheticPropertySymbol firSyntheticPropertySymbol = firBasedSymbol instanceof FirSyntheticPropertySymbol ? (FirSyntheticPropertySymbol) firBasedSymbol : null;
            if (firSyntheticPropertySymbol == null || (getterSymbol = firSyntheticPropertySymbol.getGetterSymbol()) == null || (delegateFunctionSymbol = getterSymbol.getDelegateFunctionSymbol()) == null) {
                delegateFunctionSymbol = firBasedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) firBasedSymbol : null;
            }
            if (delegateFunctionSymbol != null && (callableId = delegateFunctionSymbol.getCallableId()) != null && (callableName = callableId.getCallableName()) != null) {
                strAsString = callableName.asString();
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getDEPRECATION(), (Object) firBasedSymbol, (Object) getDeprecatedOverrideOfHiddenMessage$org_jetbrains_kotlin_checkers(strAsString), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }

    private final void reportDeprecation(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, FirBasedSymbol<?> firBasedSymbol, boolean z, FirDeprecationInfo firDeprecationInfo) {
        KtDiagnosticFactory3<FirBasedSymbol<?>, FirBasedSymbol<?>, String> typealias_expansion_deprecation;
        KtDiagnosticFactory2<FirBasedSymbol<?>, String> deprecation;
        if (z) {
            int i = WhenMappings.$EnumSwitchMapping$0[firDeprecationInfo.getDeprecationLevel().ordinal()];
            if (i == 1) {
                typealias_expansion_deprecation = FirErrors.INSTANCE.getTYPEALIAS_EXPANSION_DEPRECATION();
            } else {
                if (i != 2 && i != 3) {
                    bu8.a();
                    return;
                }
                typealias_expansion_deprecation = FirErrors.INSTANCE.getTYPEALIAS_EXPANSION_DEPRECATION_ERROR();
            }
            KtDiagnosticFactory3<FirBasedSymbol<?>, FirBasedSymbol<?>, String> ktDiagnosticFactory3 = typealias_expansion_deprecation;
            String message = firDeprecationInfo.getMessage(checkerContext.getSession());
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory3<FirBasedSymbol<?>, FirBasedSymbol<?>, String>) ((KtDiagnosticFactory3<Object, Object, Object>) ktDiagnosticFactory3), firBasedSymbol, firBasedSymbol, message == null ? Argument.Delimiters.none : message, (64 & 64) != 0 ? null : null);
            return;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[firDeprecationInfo.getDeprecationLevel().ordinal()];
        if (i2 == 1) {
            deprecation = FirErrors.INSTANCE.getDEPRECATION();
        } else {
            if (i2 != 2 && i2 != 3) {
                bu8.a();
                return;
            }
            deprecation = FirErrors.INSTANCE.getDEPRECATION_ERROR();
        }
        KtDiagnosticFactory2<FirBasedSymbol<?>, String> ktDiagnosticFactory2 = deprecation;
        String message2 = firDeprecationInfo.getMessage(checkerContext.getSession());
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) ktDiagnosticFactory2, (Object) firBasedSymbol, (Object) (message2 == null ? Argument.Delimiters.none : message2), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private final void reportVersionRequirementDeprecation(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, FirBasedSymbol<?> firBasedSymbol, RequireKotlinDeprecationInfo requireKotlinDeprecationInfo) {
        String versionString;
        KtDiagnosticFactory4<FirBasedSymbol<?>, VersionRequirement.Version, String, String> version_requirement_deprecation = WhenMappings.$EnumSwitchMapping$0[requireKotlinDeprecationInfo.getDeprecationLevel().ordinal()] == 1 ? FirErrors.INSTANCE.getVERSION_REQUIREMENT_DEPRECATION() : FirErrors.INSTANCE.getVERSION_REQUIREMENT_DEPRECATION_ERROR();
        LanguageVersionSettings languageVersionSettings = FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession());
        int i = WhenMappings.$EnumSwitchMapping$1[requireKotlinDeprecationInfo.getVersionRequirement().getKind().ordinal()];
        if (i == 1) {
            versionString = KotlinCompilerVersion.VERSION;
        } else if (i == 2) {
            versionString = languageVersionSettings.getLanguageVersion().getVersionString();
        } else {
            if (i != 3) {
                bu8.a();
                return;
            }
            versionString = languageVersionSettings.getApiVersion().getVersionString();
        }
        String str = versionString;
        VersionRequirement.Version version = requireKotlinDeprecationInfo.getVersionRequirement().getVersion();
        String message = requireKotlinDeprecationInfo.getMessage(checkerContext.getSession());
        if (message == null) {
            message = Argument.Delimiters.none;
        }
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory4<FirBasedSymbol<?>, VersionRequirement.Version, String, String>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) version_requirement_deprecation), firBasedSymbol, version, str, message, (128 & 128) != 0 ? null : null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirStatement firStatement) {
        FirReference reference;
        FirResolvedNamedReference resolved;
        FirDeprecationChecker firDeprecationChecker;
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        FirStatement firStatement2;
        FirBasedSymbol<?> firBasedSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firStatement.getClass();
        Set<KtFakeSourceElementKind> set = filteredSourceKinds;
        KtSourceElement source = firStatement.getSource();
        if (CollectionsKt.contains(set, source != null ? source.getKind() : null) || (firStatement instanceof FirAnnotation) || FirHelpersKt.isLhsOfAssignment(checkerContext, firStatement) || (reference = org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt.toReference(firStatement, checkerContext.getSession())) == null || (resolved = FirReferenceUtilsKt.getResolved(reference)) == null) {
            return;
        }
        FirBasedSymbol<?> resolvedSymbol = resolved.getResolvedSymbol();
        if (isDelegatedPropertySelfAccess(checkerContext, firStatement, resolvedSymbol)) {
            return;
        }
        KtSourceElement source2 = resolved.getSource();
        if (source2 == null) {
            source2 = firStatement.getSource();
        }
        KtSourceElement ktSourceElement = source2;
        if (firStatement instanceof FirDelegatedConstructorCall) {
            FirDeprecationInfo deprecation = DeprecationUtilsKt.getDeprecation(resolvedSymbol, checkerContext.getSession(), firStatement);
            if (deprecation == null) {
                return;
            }
            firDeprecationChecker = this;
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
            firDeprecationChecker.reportApiStatus$org_jetbrains_kotlin_checkers(checkerContext2, diagnosticReporter2, ktSourceElement, resolvedSymbol, AbbreviatedTypeAttributeKt.isTypealiasExpansion(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(((FirDelegatedConstructorCall) firStatement).getConstructedTypeRef()))), deprecation);
            firBasedSymbol = resolvedSymbol;
            firStatement2 = firStatement;
        } else {
            firDeprecationChecker = this;
            checkerContext2 = checkerContext;
            diagnosticReporter2 = diagnosticReporter;
            firDeprecationChecker.reportApiStatusIfNeeded$org_jetbrains_kotlin_checkers(checkerContext2, diagnosticReporter2, ktSourceElement, resolvedSymbol, firStatement);
            firStatement2 = firStatement;
            firBasedSymbol = resolvedSymbol;
        }
        firDeprecationChecker.reportCallToDeprecatedOverrideOfHidden(checkerContext2, diagnosticReporter2, firStatement2, ktSourceElement, firBasedSymbol);
    }

    public final String getDeprecatedOverrideOfHiddenMessage$org_jetbrains_kotlin_checkers(String callableName) {
        String str = DeprecatedOverrideOfHiddenReplacements.get(callableName);
        if (str == null) {
            return "This declaration is redundant in Kotlin and might be removed soon.";
        }
        return "This declaration will be renamed in a future version of Kotlin. Please consider using the '" + str + "' stdlib extension if the collection supports fast random access.";
    }

    public final Map<String, String> getDeprecatedOverrideOfHiddenReplacements$org_jetbrains_kotlin_checkers() {
        return DeprecatedOverrideOfHiddenReplacements;
    }

    public final void reportApiStatus$org_jetbrains_kotlin_checkers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, FirBasedSymbol<?> firBasedSymbol, boolean z, FirDeprecationInfo firDeprecationInfo) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firBasedSymbol.getClass();
        firDeprecationInfo.getClass();
        if (firDeprecationInfo instanceof FutureApiDeprecationInfo) {
            reportApiNotAvailable(checkerContext, diagnosticReporter, ktSourceElement, (FutureApiDeprecationInfo) firDeprecationInfo);
        } else if (firDeprecationInfo instanceof RequireKotlinDeprecationInfo) {
            reportVersionRequirementDeprecation(checkerContext, diagnosticReporter, ktSourceElement, firBasedSymbol, (RequireKotlinDeprecationInfo) firDeprecationInfo);
        } else {
            reportDeprecation(checkerContext, diagnosticReporter, ktSourceElement, firBasedSymbol, z, firDeprecationInfo);
        }
    }

    public final void reportApiStatusIfNeeded$org_jetbrains_kotlin_checkers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, FirBasedSymbol<?> firBasedSymbol, FirElement firElement) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firBasedSymbol.getClass();
        FirDeprecationInfo worstDeprecation = getWorstDeprecation(checkerContext, firElement, firBasedSymbol);
        if (worstDeprecation == null) {
            return;
        }
        if (isNestedTypeAliasReferenceAndRelevantDeprecation(firBasedSymbol, worstDeprecation)) {
            FirHelpersKt.requireFeatureSupport$default(checkerContext, diagnosticReporter, ktSourceElement, LanguageFeature.NestedTypeAliases, (SourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            reportApiStatus$org_jetbrains_kotlin_checkers(checkerContext, diagnosticReporter, ktSourceElement, firBasedSymbol, isTypealiasExpansionOf(checkerContext, worstDeprecation, firBasedSymbol, firElement), worstDeprecation);
        }
    }
}

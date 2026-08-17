package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirSinceKotlinAccessibility;
import org.jetbrains.kotlin.fir.analysis.checkers.FirSinceKotlinHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDestructuringDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.EnumValueArgumentInfo;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.AbbreviatedTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.DataClassResolver;
import org.jetbrains.kotlin.resolve.checkers.OptInDiagnosticMessageProvider;
import org.jetbrains.kotlin.resolve.checkers.OptInInheritanceDiagnosticMessageProvider;
import org.jetbrains.kotlin.resolve.checkers.OptInNames;
import org.jetbrains.kotlin.resolve.checkers.OptInUsagesDiagnosticMessageProvider;
import org.jetbrains.kotlin.resolve.checkers.OptInUsagesDiagnosticMessageProviderForDeprecation;
import org.jetbrains.kotlin.utils.SmartSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001WB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n*\u00020\rR\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u000eJ$\u0010\u000f\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013J.\u0010\u000f\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J+\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u001aJ+\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0018R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\u001aJE\u0010\u001d\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\u001e*\u0006\u0012\u0002\b\u00030\u00112\f\u0010\u001f\u001a\b\u0012\u0002\b\u0003\u0018\u00010 R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010!J-\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\n*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010#\u001a\u00020\u0015R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010$J%\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00050\n*\u0006\u0012\u0002\b\u00030 R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010&J\u0016\u0010'\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030 2\u0006\u0010\u0007\u001a\u00020\bJY\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050(*\u0006\u0012\u0002\b\u00030\u00112\u000e\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010(2\u0010\u0010*\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110+2\u0006\u0010#\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010,JI\u0010-\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030.2\u0010\u0010*\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110+2\u0006\u0010#\u001a\u00020\u00152\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050(H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010/JA\u00100\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030 2\u0010\u0010*\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110+2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050(H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u00101JA\u00102\u001a\u00020\u0010*\u0004\u0018\u0001032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050(2\u0012\b\u0002\u0010*\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110+H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u00104J\"\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u00105\u001a\u0004\u0018\u000106H\u0002JO\u00107\u001a\u00020\u00102\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00050;2\u0006\u0010<\u001a\u00020=2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010?2\b\b\u0002\u0010@\u001a\u00020\u0015R\u00020\u000bR\u000208j\u0006\u0010\f\u001a\u00020\u000bj\u0006\u00109\u001a\u000208¢\u0006\u0002\u0010AJ=\u0010B\u001a\u00020\u00102\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00050;2\n\u0010C\u001a\u0006\u0012\u0002\b\u00030.R\u00020\u000bR\u000208j\u0006\u0010\f\u001a\u00020\u000bj\u0006\u00109\u001a\u000208¢\u0006\u0002\u0010DJ)\u0010E\u001a\u00020\u00152\u0006\u0010F\u001a\u00020G2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010HJ1\u0010I\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010F\u001a\u00020G2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010JJ\u0010\u0010K\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u0011H\u0002J)\u0010L\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010F\u001a\u00020GH\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010MJ)\u0010N\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010F\u001a\u00020GH\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010MJ+\u0010N\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020O0\u00182\u0006\u0010F\u001a\u00020GH\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010PJ)\u0010Q\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010F\u001a\u00020GH\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010MJ\u0014\u0010R\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010?0\u0018*\u00020SH\u0002R\u000e\u0010T\u001a\u00020UX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020UX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker;", Argument.Delimiters.none, "<init>", "()V", "loadExperimentalityForMarkerAnnotation", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "loadExperimentalitiesFromConstructor", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;)Ljava/util/Set;", "loadExperimentalitiesFromAnnotationTo", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "fromSupertype", Argument.Delimiters.none, "loadExperimentalitiesFromTypeArguments", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;)Ljava/util/Set;", "loadExperimentalitiesFromConeArguments", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "loadExperimentalitiesForQualifier", "Lkotlin/Pair;", "companionObjectSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Lkotlin/Pair;", "loadExperimentalities", "fromSetter", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Z)Ljava/util/Set;", "loadExperimentalitiesFromSupertype", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Ljava/util/Set;", "isExperimentalMarker", "Lorg/jetbrains/kotlin/utils/SmartSet;", "knownExperimentalities", "visited", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/utils/SmartSet;Ljava/util/Set;ZZ)Lorg/jetbrains/kotlin/utils/SmartSet;", "loadCallableSpecificExperimentalities", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Ljava/util/Set;ZLorg/jetbrains/kotlin/utils/SmartSet;)V", "loadClassLikeSpecificExperimentalities", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Ljava/util/Set;Lorg/jetbrains/kotlin/utils/SmartSet;)V", "addExperimentalities", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/utils/SmartSet;Ljava/util/Set;)V", "annotatedOwnerClassName", Argument.Delimiters.none, "reportNotAcceptedExperimentalities", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "experimentalities", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "reportErrorsAsDeprecationWarnings", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/Collection;Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/KtSourceElement;Z)V", "reportNotAcceptedOverrideExperimentalities", "symbol", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/Collection;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "isExperimentalityAcceptableInContext", "annotationClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/name/ClassId;Z)Z", "isExperimentalityAcceptable", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/name/ClassId;Z)Z", "isImplicitDeclaration", "primaryConstructorParameterIsExperimentalityAcceptable", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/name/ClassId;)Z", "isAnnotatedWithOptIn", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;Lorg/jetbrains/kotlin/name/ClassId;)Z", "isAnnotatedWithSubclassOptInRequired", "getMarkerArgumentsSources", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "LEVEL", "Lorg/jetbrains/kotlin/name/Name;", "MESSAGE", "Experimentality", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptInUsageBaseChecker {
    public static final FirOptInUsageBaseChecker INSTANCE = new FirOptInUsageBaseChecker();
    private static final Name LEVEL;
    private static final Name MESSAGE;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Experimentality.Severity.values().length];
            try {
                iArr[Experimentality.Severity.WARNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Experimentality.Severity.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        Name nameIdentifier = Name.identifier("level");
        nameIdentifier.getClass();
        LEVEL = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("message");
        nameIdentifier2.getClass();
        MESSAGE = nameIdentifier2;
    }

    private FirOptInUsageBaseChecker() {
    }

    private final void addExperimentalities(CheckerContext checkerContext, ConeKotlinType coneKotlinType, SmartSet<Experimentality> smartSet, Set<FirBasedSymbol<?>> set) {
        CheckerContext checkerContext2;
        SmartSet<Experimentality> smartSet2;
        Set<FirBasedSymbol<?>> set2;
        ConeKotlinType type;
        if (coneKotlinType instanceof ConeClassLikeType) {
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneKotlinType;
            FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext, coneClassLikeType.getLookupTag());
            if (symbol != null) {
                checkerContext2 = checkerContext;
                smartSet2 = smartSet;
                set2 = set;
                loadExperimentalities(checkerContext2, symbol, smartSet2, set2, false, false);
            } else {
                checkerContext2 = checkerContext;
                smartSet2 = smartSet;
                set2 = set;
            }
            ConeTypeProjection[] typeArguments = TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) checkerContext2, coneClassLikeType).getTypeArguments();
            for (ConeTypeProjection coneTypeProjection : typeArguments) {
                if (!ConeTypeProjectionKt.isStarProjection(coneTypeProjection) && (type = ConeTypeProjectionKt.getType(coneTypeProjection)) != null) {
                    INSTANCE.addExperimentalities(checkerContext2, type, smartSet2, set2);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void addExperimentalities$default(FirOptInUsageBaseChecker firOptInUsageBaseChecker, CheckerContext checkerContext, ConeKotlinType coneKotlinType, SmartSet smartSet, Set set, int i, Object obj) {
        if ((i & 4) != 0) {
            set = new LinkedHashSet();
        }
        firOptInUsageBaseChecker.addExperimentalities(checkerContext, coneKotlinType, smartSet, set);
    }

    private final boolean isAnnotatedWithOptIn(CheckerContext checkerContext, List<? extends FirAnnotation> list, ClassId classId) {
        FirExpression firExpressionFindArgumentByName$default;
        ConeClassLikeLookupTag lookupTag;
        for (FirAnnotation firAnnotation : list) {
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef());
            ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
            ClassId classId2 = (coneClassLikeType == null || (lookupTag = coneClassLikeType.getLookupTag()) == null) ? null : lookupTag.getClassId();
            OptInNames optInNames = OptInNames.INSTANCE;
            if (Intrinsics.areEqual(classId2, optInNames.getOPT_IN_CLASS_ID()) && (firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotation, optInNames.getOPT_IN_ANNOTATION_CLASS(), false, 2, null)) != null) {
                List<FirRegularClassSymbol> listExtractClassesFromArgument = FirAnnotationHelpersKt.extractClassesFromArgument(firExpressionFindArgumentByName$default, checkerContext.getSession());
                if (!(listExtractClassesFromArgument instanceof Collection) || !listExtractClassesFromArgument.isEmpty()) {
                    Iterator<T> it = listExtractClassesFromArgument.iterator();
                    while (it.hasNext()) {
                        if (Intrinsics.areEqual(((FirRegularClassSymbol) it.next()).getClassId(), classId)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private final boolean isAnnotatedWithSubclassOptInRequired(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, ClassId classId) {
        FirExpression firExpressionFindArgumentByName$default;
        ConeClassLikeLookupTag lookupTag;
        for (FirAnnotation firAnnotation : firBasedSymbol.getResolvedAnnotationsWithArguments()) {
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef());
            ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
            ClassId classId2 = (coneClassLikeType == null || (lookupTag = coneClassLikeType.getLookupTag()) == null) ? null : lookupTag.getClassId();
            OptInNames optInNames = OptInNames.INSTANCE;
            if (Intrinsics.areEqual(classId2, optInNames.getSUBCLASS_OPT_IN_REQUIRED_CLASS_ID()) && (firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotation, optInNames.getOPT_IN_ANNOTATION_CLASS(), false, 2, null)) != null) {
                List<FirRegularClassSymbol> listExtractClassesFromArgument = FirAnnotationHelpersKt.extractClassesFromArgument(firExpressionFindArgumentByName$default, checkerContext.getSession());
                if (!(listExtractClassesFromArgument instanceof Collection) || !listExtractClassesFromArgument.isEmpty()) {
                    Iterator<T> it = listExtractClassesFromArgument.iterator();
                    while (it.hasNext()) {
                        if (Intrinsics.areEqual(((FirRegularClassSymbol) it.next()).getClassId(), classId)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private final boolean isExperimentalityAcceptable(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, ClassId classId, boolean z) {
        if (FirAnnotationUtilsKt.hasAnnotationWithClassId(firBasedSymbol, classId, checkerContext.getSession()) || isAnnotatedWithOptIn(checkerContext, firBasedSymbol, classId)) {
            return true;
        }
        return (z && isAnnotatedWithSubclassOptInRequired(checkerContext, firBasedSymbol, classId)) || primaryConstructorParameterIsExperimentalityAcceptable(checkerContext, firBasedSymbol, classId) || isImplicitDeclaration(firBasedSymbol);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isExperimentalityAcceptableInContext(CheckerContext checkerContext, ClassId classId, boolean z) {
        FirVariableSymbol<?> destructuringVariableIfEntry;
        if (((List) FirLanguageSettingsComponentKt.getLanguageVersionSettings(checkerContext.getSession()).getFlag(AnalysisFlags.getOptIn())).contains(classId.asFqNameString())) {
            return true;
        }
        for (FirAnnotationContainer firAnnotationContainer : checkerContext.getAnnotationContainers()) {
            if ((firAnnotationContainer instanceof FirDeclaration) && isExperimentalityAcceptable(checkerContext, ((FirDeclaration) firAnnotationContainer).getSymbol(), classId, z)) {
                return true;
            }
            if (firAnnotationContainer instanceof FirStatement) {
                FirStatement firStatement = (FirStatement) firAnnotationContainer;
                if (FirAnnotationUtilsKt.getAnnotationByClassId(firStatement, classId, checkerContext.getSession()) != null || INSTANCE.isAnnotatedWithOptIn(checkerContext, firStatement.getAnnotations(), classId)) {
                    return true;
                }
            }
        }
        FirBasedSymbol firBasedSymbol = (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        return (firBasedSymbol instanceof FirPropertySymbol) && (destructuringVariableIfEntry = FirDestructuringDeclarationChecker.INSTANCE.getDestructuringVariableIfEntry((FirProperty) ((FirPropertySymbol) firBasedSymbol).getFir())) != null && INSTANCE.isExperimentalityAcceptable(checkerContext, destructuringVariableIfEntry, classId, z);
    }

    private final boolean isImplicitDeclaration(FirBasedSymbol<?> firBasedSymbol) {
        return !Intrinsics.areEqual(firBasedSymbol.getOrigin(), FirDeclarationOrigin.Source.INSTANCE);
    }

    private final void loadCallableSpecificExperimentalities(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol, Set<FirBasedSymbol<?>> set, boolean z, SmartSet<Experimentality> smartSet) {
        SmartSet<Experimentality> smartSet2;
        ConeKotlinType resolvedType;
        FirBasedSymbol<?> setterSymbol;
        FirBasedSymbol<?> typeAliasSymbol;
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableSymbol);
        Object obj = null;
        FirRegularClassSymbol regularClassSymbol = coneClassLikeLookupTagContainingClassLookupTag != null ? ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag) : null;
        if (firCallableSymbol instanceof FirConstructorSymbol) {
            TypeAliasConstructorInfo<?> typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo((FirConstructorSymbol) firCallableSymbol);
            FirBasedSymbol<?> firBasedSymbol = (typeAliasConstructorInfo == null || (typeAliasSymbol = typeAliasConstructorInfo.getTypeAliasSymbol()) == null) ? regularClassSymbol : typeAliasSymbol;
            if (firBasedSymbol != null) {
                smartSet2 = smartSet;
                loadExperimentalities(checkerContext, firBasedSymbol, smartSet2, set, false, false);
            } else {
                smartSet2 = smartSet;
            }
        } else {
            smartSet2 = smartSet;
            addExperimentalities(checkerContext, AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(firCallableSymbol.getResolvedReturnType()), smartSet2, set);
            FirReceiverParameterSymbol receiverParameterSymbol = firCallableSymbol.getReceiverParameterSymbol();
            addExperimentalities(checkerContext, (receiverParameterSymbol == null || (resolvedType = receiverParameterSymbol.getResolvedType()) == null) ? null : AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(resolvedType), smartSet2, set);
        }
        if (firCallableSymbol instanceof FirFunctionSymbol) {
            FirFunctionSymbol firFunctionSymbol = (FirFunctionSymbol) firCallableSymbol;
            Iterator<T> it = firFunctionSymbol.getValueParameterSymbols().iterator();
            while (it.hasNext()) {
                INSTANCE.addExperimentalities(checkerContext, AbbreviatedTypeAttributeKt.getAbbreviatedTypeOrSelf(((FirValueParameterSymbol) it.next()).getResolvedReturnType()), smartSet2, set);
            }
            if (regularClassSymbol != null && regularClassSymbol.getRawStatus().isData()) {
                DataClassResolver dataClassResolver = DataClassResolver.INSTANCE;
                if (dataClassResolver.isComponentLike(firFunctionSymbol.getCallableId().getCallableName()) && regularClassSymbol.getClassKind() == ClassKind.CLASS) {
                    String identifier = firFunctionSymbol.getCallableId().getCallableName().getIdentifier();
                    identifier.getClass();
                    int componentIndex = dataClassResolver.getComponentIndex(identifier);
                    FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(regularClassSymbol, checkerContext.getSession());
                    List<FirValueParameterSymbol> valueParameterSymbols = firConstructorSymbolPrimaryConstructorIfAny != null ? firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols() : null;
                    FirValueParameterSymbol firValueParameterSymbol = valueParameterSymbols != null ? (FirValueParameterSymbol) CollectionsKt.getOrNull(valueParameterSymbols, componentIndex - 1) : null;
                    for (Object obj2 : DeclarationUtilsKt.declaredProperties$default(regularClassSymbol, checkerContext.getSession(), null, 2, null)) {
                        if (Intrinsics.areEqual(((FirPropertySymbol) obj2).getName(), firValueParameterSymbol != null ? firValueParameterSymbol.getName() : null)) {
                            obj = obj2;
                            break;
                        }
                    }
                    FirBasedSymbol<?> firBasedSymbol2 = (FirPropertySymbol) obj;
                    if (firBasedSymbol2 != null) {
                        loadExperimentalities(checkerContext, firBasedSymbol2, smartSet2, set, false, false);
                    }
                }
            }
        }
        if (z && (firCallableSymbol instanceof FirPropertySymbol) && (setterSymbol = ((FirPropertySymbol) firCallableSymbol).getSetterSymbol()) != null) {
            loadExperimentalities(checkerContext, setterSymbol, smartSet, set, false, false);
        }
    }

    private final void loadClassLikeSpecificExperimentalities(CheckerContext checkerContext, FirClassLikeSymbol<?> firClassLikeSymbol, Set<FirBasedSymbol<?>> set, SmartSet<Experimentality> smartSet) {
        if (firClassLikeSymbol instanceof FirRegularClassSymbol) {
            FirClassLikeSymbol<?> firClassLikeSymbolOuterClassSymbol = FirHelpersKt.outerClassSymbol(checkerContext, firClassLikeSymbol);
            if (firClassLikeSymbolOuterClassSymbol != null) {
                loadExperimentalities(checkerContext, firClassLikeSymbolOuterClassSymbol, smartSet, set, false, false);
                return;
            }
            return;
        }
        if ((firClassLikeSymbol instanceof FirAnonymousObjectSymbol) || (firClassLikeSymbol instanceof FirTypeAliasSymbol)) {
            return;
        }
        bu8.a();
    }

    private final SmartSet<Experimentality> loadExperimentalities(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, SmartSet<Experimentality> smartSet, Set<FirBasedSymbol<?>> set, boolean z, boolean z2) {
        FirOptInUsageBaseChecker firOptInUsageBaseChecker;
        CheckerContext checkerContext2;
        if (!set.add(firBasedSymbol)) {
            return SmartSet.Companion.create();
        }
        if (smartSet == null) {
            smartSet = SmartSet.Companion.create();
        }
        SmartSet<Experimentality> smartSet2 = smartSet;
        FirSession session = checkerContext.getSession();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            firOptInUsageBaseChecker = this;
            checkerContext2 = checkerContext;
            firOptInUsageBaseChecker.loadCallableSpecificExperimentalities(checkerContext2, (FirCallableSymbol) firBasedSymbol, set, z, smartSet2);
        } else {
            firOptInUsageBaseChecker = this;
            checkerContext2 = checkerContext;
            if (firBasedSymbol instanceof FirClassLikeSymbol) {
                firOptInUsageBaseChecker.loadClassLikeSpecificExperimentalities(checkerContext2, (FirClassLikeSymbol) firBasedSymbol, set, smartSet2);
            }
        }
        firOptInUsageBaseChecker.loadExperimentalitiesFromAnnotationTo(firBasedSymbol, session, smartSet2, z2);
        if (FirAnnotationUtilsKt.hasAnnotationWithClassId(firBasedSymbol, OptInNames.INSTANCE.getWAS_EXPERIMENTAL_CLASS_ID(), session)) {
            FirSinceKotlinAccessibility firSinceKotlinAccessibilityCheckSinceKotlinVersionAccessibility = FirSinceKotlinHelpersKt.checkSinceKotlinVersionAccessibility(checkerContext2, firBasedSymbol);
            if (firSinceKotlinAccessibilityCheckSinceKotlinVersionAccessibility instanceof FirSinceKotlinAccessibility.NotAccessibleButWasExperimental) {
                Iterator<T> it = ((FirSinceKotlinAccessibility.NotAccessibleButWasExperimental) firSinceKotlinAccessibilityCheckSinceKotlinVersionAccessibility).getMarkerClasses().iterator();
                while (it.hasNext()) {
                    org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(smartSet2, INSTANCE.loadExperimentalityForMarkerAnnotation((FirRegularClassSymbol) it.next(), session));
                }
            }
        }
        return smartSet2;
    }

    private final void loadExperimentalitiesFromAnnotationTo(FirBasedSymbol<?> firBasedSymbol, FirSession firSession, Collection<Experimentality> collection, boolean z) {
        CallableId callableId;
        FqName className;
        Name nameShortName;
        FirExpression firExpressionFindArgumentByName$default;
        for (FirAnnotation firAnnotation : firBasedSymbol.getResolvedAnnotationsWithArguments()) {
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef());
            ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
            if (coneClassLikeType != null) {
                String strAsString = firBasedSymbol instanceof FirRegularClassSymbol ? ((FirRegularClassSymbol) firBasedSymbol).getName().asString() : (!(firBasedSymbol instanceof FirCallableSymbol) || (callableId = ((FirCallableSymbol) firBasedSymbol).getCallableId()) == null || (className = callableId.getClassName()) == null || (nameShortName = className.shortName()) == null) ? null : nameShortName.asString();
                FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeType.getLookupTag(), firSession);
                org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(collection, regularClassSymbol != null ? loadExperimentalityForMarkerAnnotation(regularClassSymbol, firSession, strAsString) : null);
                if (z) {
                    ClassId classId = coneClassLikeType.getLookupTag().getClassId();
                    OptInNames optInNames = OptInNames.INSTANCE;
                    if (Intrinsics.areEqual(classId, optInNames.getSUBCLASS_OPT_IN_REQUIRED_CLASS_ID()) && (firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotation, optInNames.getOPT_IN_ANNOTATION_CLASS(), false, 2, null)) != null) {
                        Iterator<T> it = FirAnnotationHelpersKt.extractClassesFromArgument(firExpressionFindArgumentByName$default, firSession).iterator();
                        while (it.hasNext()) {
                            Experimentality experimentalityLoadExperimentalityForMarkerAnnotation = INSTANCE.loadExperimentalityForMarkerAnnotation((FirRegularClassSymbol) it.next(), firSession);
                            org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(collection, experimentalityLoadExperimentalityForMarkerAnnotation != null ? Experimentality.copy$default(experimentalityLoadExperimentalityForMarkerAnnotation, null, null, null, null, true, 15, null) : null);
                        }
                    }
                }
            }
        }
    }

    private final Experimentality loadExperimentalityForMarkerAnnotation(FirRegularClassSymbol firRegularClassSymbol, FirSession firSession, String str) {
        EnumValueArgumentInfo enumValueArgumentInfoExtractEnumValueArgumentInfo;
        Name enumEntryName;
        FirAnnotation annotationWithResolvedArgumentsByClassId = FirAnnotationUtilsKt.getAnnotationWithResolvedArgumentsByClassId(firRegularClassSymbol, OptInNames.INSTANCE.getREQUIRES_OPT_IN_CLASS_ID(), firSession);
        Object obj = null;
        if (annotationWithResolvedArgumentsByClassId == null) {
            return null;
        }
        FirExpression firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(annotationWithResolvedArgumentsByClassId, LEVEL, false, 2, null);
        String strAsString = (firExpressionFindArgumentByName$default == null || (enumValueArgumentInfoExtractEnumValueArgumentInfo = FirAnnotationUtilsKt.extractEnumValueArgumentInfo(firExpressionFindArgumentByName$default)) == null || (enumEntryName = enumValueArgumentInfoExtractEnumValueArgumentInfo.getEnumEntryName()) == null) ? null : enumEntryName.asString();
        for (Object obj2 : Experimentality.Severity.getEntries()) {
            if (Intrinsics.areEqual(((Experimentality.Severity) obj2).name(), strAsString)) {
                obj = obj2;
                break;
            }
        }
        Experimentality.Severity default_severity = (Experimentality.Severity) obj;
        if (default_severity == null) {
            default_severity = Experimentality.INSTANCE.getDEFAULT_SEVERITY();
        }
        return new Experimentality(firRegularClassSymbol.getClassId(), default_severity, FirAnnotationUtilsKt.getStringArgument(annotationWithResolvedArgumentsByClassId, MESSAGE), str, false, 16, null);
    }

    private final boolean primaryConstructorParameterIsExperimentalityAcceptable(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, ClassId classId) {
        FirValueParameterSymbol correspondingValueParameterFromPrimaryConstructor;
        if ((firBasedSymbol instanceof FirPropertySymbol) && (correspondingValueParameterFromPrimaryConstructor = DeclarationAttributesKt.getCorrespondingValueParameterFromPrimaryConstructor((FirPropertySymbol) firBasedSymbol)) != null) {
            return isExperimentalityAcceptable(checkerContext, correspondingValueParameterFromPrimaryConstructor, classId, false);
        }
        return false;
    }

    public static /* synthetic */ void reportNotAcceptedExperimentalities$default(FirOptInUsageBaseChecker firOptInUsageBaseChecker, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, Collection collection, FirElement firElement, KtSourceElement ktSourceElement, boolean z, int i, Object obj) {
        if ((i & 16) != 0) {
            ktSourceElement = firElement.getSource();
        }
        KtSourceElement ktSourceElement2 = ktSourceElement;
        if ((i & 32) != 0) {
            z = false;
        }
        firOptInUsageBaseChecker.reportNotAcceptedExperimentalities(checkerContext, diagnosticReporter, collection, firElement, ktSourceElement2, z);
    }

    public final boolean isExperimentalMarker(FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession) {
        firClassLikeSymbol.getClass();
        firSession.getClass();
        return (firClassLikeSymbol instanceof FirRegularClassSymbol) && FirAnnotationUtilsKt.hasAnnotationWithClassId(firClassLikeSymbol, OptInNames.INSTANCE.getREQUIRES_OPT_IN_CLASS_ID(), firSession);
    }

    public final Pair<Set<Experimentality>, Set<Experimentality>> loadExperimentalitiesForQualifier(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, FirClassLikeSymbol<?> firClassLikeSymbol) {
        checkerContext.getClass();
        firBasedSymbol.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        SmartSet<Experimentality> smartSetLoadExperimentalities = loadExperimentalities(checkerContext, firBasedSymbol, null, linkedHashSet, false, false);
        if (firClassLikeSymbol == null) {
            return TuplesKt.to(smartSetLoadExperimentalities, SetsKt.emptySet());
        }
        SmartSet collection = CollectionsKt.toCollection(smartSetLoadExperimentalities, SmartSet.Companion.create());
        loadExperimentalities(checkerContext, firClassLikeSymbol, smartSetLoadExperimentalities, linkedHashSet, false, false);
        return TuplesKt.to(collection, SetsKt.minus(smartSetLoadExperimentalities, collection));
    }

    public final Set<Experimentality> loadExperimentalitiesFromConeArguments(CheckerContext checkerContext, List<? extends ConeTypeProjection> list) {
        CheckerContext checkerContext2;
        ConeKotlinType type;
        checkerContext.getClass();
        list.getClass();
        if (list.isEmpty()) {
            return SetsKt.emptySet();
        }
        SmartSet smartSetCreate = SmartSet.Companion.create();
        for (ConeTypeProjection coneTypeProjection : list) {
            if (ConeTypeProjectionKt.isStarProjection(coneTypeProjection) || (type = ConeTypeProjectionKt.getType(coneTypeProjection)) == null) {
                checkerContext2 = checkerContext;
            } else {
                checkerContext2 = checkerContext;
                addExperimentalities$default(INSTANCE, checkerContext2, type, smartSetCreate, null, 4, null);
            }
            checkerContext = checkerContext2;
        }
        return smartSetCreate;
    }

    public final Set<Experimentality> loadExperimentalitiesFromConstructor(CheckerContext checkerContext, FirConstructorSymbol firConstructorSymbol) {
        checkerContext.getClass();
        firConstructorSymbol.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        loadExperimentalitiesFromAnnotationTo(firConstructorSymbol, checkerContext.getSession(), linkedHashSet);
        return linkedHashSet;
    }

    public final Set<Experimentality> loadExperimentalitiesFromSupertype(CheckerContext checkerContext, FirClassLikeSymbol<?> firClassLikeSymbol) {
        checkerContext.getClass();
        firClassLikeSymbol.getClass();
        return loadExperimentalities(checkerContext, firClassLikeSymbol, null, new LinkedHashSet(), false, true);
    }

    public final Set<Experimentality> loadExperimentalitiesFromTypeArguments(CheckerContext checkerContext, List<? extends FirTypeProjection> list) {
        checkerContext.getClass();
        list.getClass();
        if (list.isEmpty()) {
            return SetsKt.emptySet();
        }
        List<? extends FirTypeProjection> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(FirTypeUtilsKt.toConeTypeProjection((FirTypeProjection) it.next()));
        }
        return loadExperimentalitiesFromConeArguments(checkerContext, arrayList);
    }

    public final void reportNotAcceptedExperimentalities(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, Collection<Experimentality> collection, FirElement firElement, KtSourceElement ktSourceElement, boolean z) {
        Triple triple;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        collection.getClass();
        firElement.getClass();
        Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        FirClassSymbol<?> firClassSymbol = objLastOrNull instanceof FirClassSymbol ? (FirClassSymbol) objLastOrNull : null;
        boolean zBooleanValue = firClassSymbol != null ? ((Boolean) FirOptInAnnotationCallChecker.INSTANCE.getSubclassOptInApplicabilityAndMessage(firClassSymbol).getFirst()).booleanValue() : false;
        for (Experimentality experimentality : collection) {
            ClassId annotationClassId = experimentality.getAnnotationClassId();
            Experimentality.Severity severity = experimentality.getSeverity();
            String message = experimentality.getMessage();
            boolean fromSupertype = experimentality.getFromSupertype();
            if (!isExperimentalityAcceptableInContext(checkerContext, annotationClassId, fromSupertype)) {
                int i = WhenMappings.$EnumSwitchMapping$0[severity.ordinal()];
                if (i == 1 && fromSupertype) {
                    triple = new Triple(FirErrors.INSTANCE.getOPT_IN_TO_INHERITANCE(), new OptInInheritanceDiagnosticMessageProvider(zBooleanValue), "should");
                } else if (i == 1) {
                    triple = new Triple(FirErrors.INSTANCE.getOPT_IN_USAGE(), OptInUsagesDiagnosticMessageProvider.INSTANCE, "should");
                } else if (i == 2 && fromSupertype) {
                    triple = new Triple(FirErrors.INSTANCE.getOPT_IN_TO_INHERITANCE_ERROR(), new OptInInheritanceDiagnosticMessageProvider(zBooleanValue), "must");
                } else if (i == 2 && z) {
                    triple = new Triple(FirErrors.INSTANCE.getOPT_IN_USAGE(), OptInUsagesDiagnosticMessageProviderForDeprecation.INSTANCE, "must");
                } else {
                    if (i != 2) {
                        bu8.a();
                        return;
                    }
                    triple = new Triple(FirErrors.INSTANCE.getOPT_IN_USAGE_ERROR(), OptInUsagesDiagnosticMessageProvider.INSTANCE, "must");
                }
                KtDiagnosticFactory2 ktDiagnosticFactory2 = (KtDiagnosticFactory2) triple.component1();
                OptInDiagnosticMessageProvider optInDiagnosticMessageProvider = (OptInDiagnosticMessageProvider) triple.component2();
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, ktDiagnosticFactory2, (Object) annotationClassId, (Object) ((message == null || StringsKt.isBlank(message)) ? optInDiagnosticMessageProvider.buildDefaultDiagnosticMessage(annotationClassId.asFqNameString(), (String) triple.component3()) : optInDiagnosticMessageProvider.buildCustomDiagnosticMessage(message)), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }
    }

    public final void reportNotAcceptedOverrideExperimentalities(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, Collection<Experimentality> collection, FirCallableSymbol<?> firCallableSymbol) {
        Pair pair;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        collection.getClass();
        firCallableSymbol.getClass();
        for (Experimentality experimentality : collection) {
            ClassId annotationClassId = experimentality.getAnnotationClassId();
            Experimentality.Severity severity = experimentality.getSeverity();
            String message = experimentality.getMessage();
            String supertypeName = experimentality.getSupertypeName();
            if (!isExperimentalityAcceptable(checkerContext, firCallableSymbol, annotationClassId, false) && !isExperimentalityAcceptableInContext(checkerContext, annotationClassId, false)) {
                int i = WhenMappings.$EnumSwitchMapping$0[severity.ordinal()];
                if (i == 1) {
                    pair = TuplesKt.to(FirErrors.INSTANCE.getOPT_IN_OVERRIDE(), "should");
                } else {
                    if (i != 2) {
                        bu8.a();
                        return;
                    }
                    pair = TuplesKt.to(FirErrors.INSTANCE.getOPT_IN_OVERRIDE_ERROR(), "must");
                }
                KtDiagnosticFactory2 ktDiagnosticFactory2 = (KtDiagnosticFactory2) pair.component1();
                String str = (String) pair.component2();
                OptInNames optInNames = OptInNames.INSTANCE;
                if (supertypeName == null) {
                    supertypeName = "???";
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), ktDiagnosticFactory2, (Object) annotationClassId, (Object) optInNames.buildOverrideMessage(supertypeName, message, str, annotationClassId.asFqNameString()), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\n\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0014\u0010\u0016\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\nHÆ\u0003J?\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\n\u0010 \u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality;", Argument.Delimiters.none, "annotationClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "severity", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality$Severity;", "message", Argument.Delimiters.none, "supertypeName", "fromSupertype", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality$Severity;Ljava/lang/String;Ljava/lang/String;Z)V", "getAnnotationClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getSeverity", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality$Severity;", "getMessage", "()Ljava/lang/String;", "getSupertypeName", "getFromSupertype", "()Z", "equals", "other", "hashCode", Argument.Delimiters.none, "component1", "component2", "component3", "component4", "component5", "copy", "toString", "Severity", "Companion", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class Experimentality {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Severity DEFAULT_SEVERITY = Severity.ERROR;
        private final ClassId annotationClassId;
        private final boolean fromSupertype;
        private final String message;
        private final Severity severity;
        private final String supertypeName;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality$Severity;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "WARNING", "ERROR", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public enum Severity {
            WARNING,
            ERROR;

            private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

            public static EnumEntries<Severity> getEntries() {
                return $ENTRIES;
            }
        }

        public Experimentality(ClassId classId, Severity severity, String str, String str2, boolean z) {
            classId.getClass();
            severity.getClass();
            this.annotationClassId = classId;
            this.severity = severity;
            this.message = str;
            this.supertypeName = str2;
            this.fromSupertype = z;
        }

        public static /* synthetic */ Experimentality copy$default(Experimentality experimentality, ClassId classId, Severity severity, String str, String str2, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                classId = experimentality.annotationClassId;
            }
            if ((i & 2) != 0) {
                severity = experimentality.severity;
            }
            if ((i & 4) != 0) {
                str = experimentality.message;
            }
            if ((i & 8) != 0) {
                str2 = experimentality.supertypeName;
            }
            if ((i & 16) != 0) {
                z = experimentality.fromSupertype;
            }
            boolean z2 = z;
            String str3 = str;
            return experimentality.copy(classId, severity, str3, str2, z2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ClassId getAnnotationClassId() {
            return this.annotationClassId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Severity getSeverity() {
            return this.severity;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSupertypeName() {
            return this.supertypeName;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getFromSupertype() {
            return this.fromSupertype;
        }

        public final Experimentality copy(ClassId annotationClassId, Severity severity, String message, String supertypeName, boolean fromSupertype) {
            annotationClassId.getClass();
            severity.getClass();
            return new Experimentality(annotationClassId, severity, message, supertypeName, fromSupertype);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Experimentality)) {
                return false;
            }
            Experimentality experimentality = (Experimentality) other;
            return Intrinsics.areEqual(this.annotationClassId, experimentality.annotationClassId) && this.severity == experimentality.severity && Intrinsics.areEqual(this.message, experimentality.message) && this.fromSupertype == experimentality.fromSupertype;
        }

        public final ClassId getAnnotationClassId() {
            return this.annotationClassId;
        }

        public final boolean getFromSupertype() {
            return this.fromSupertype;
        }

        public final String getMessage() {
            return this.message;
        }

        public final Severity getSeverity() {
            return this.severity;
        }

        public final String getSupertypeName() {
            return this.supertypeName;
        }

        public int hashCode() {
            int iHashCode = ((this.annotationClassId.hashCode() * 31) + this.severity.hashCode()) * 31;
            String str = this.message;
            return ((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.fromSupertype);
        }

        public String toString() {
            return "Experimentality(annotationClassId=" + this.annotationClassId + ", severity=" + this.severity + ", message=" + this.message + ", supertypeName=" + this.supertypeName + ", fromSupertype=" + this.fromSupertype + ')';
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality$Companion;", Argument.Delimiters.none, "<init>", "()V", "DEFAULT_SEVERITY", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality$Severity;", "getDEFAULT_SEVERITY", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageBaseChecker$Experimentality$Severity;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final Severity getDEFAULT_SEVERITY() {
                return Experimentality.DEFAULT_SEVERITY;
            }

            private Companion() {
            }
        }

        public /* synthetic */ Experimentality(ClassId classId, Severity severity, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(classId, severity, str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? false : z);
        }
    }

    public final Experimentality loadExperimentalityForMarkerAnnotation(FirRegularClassSymbol firRegularClassSymbol, FirSession firSession) {
        firRegularClassSymbol.getClass();
        firSession.getClass();
        return loadExperimentalityForMarkerAnnotation(firRegularClassSymbol, firSession, null);
    }

    public final Set<Experimentality> loadExperimentalities(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, boolean z) {
        checkerContext.getClass();
        firBasedSymbol.getClass();
        return loadExperimentalities(checkerContext, firBasedSymbol, null, new LinkedHashSet(), z, false);
    }

    private final boolean isAnnotatedWithOptIn(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol, ClassId classId) {
        return isAnnotatedWithOptIn(checkerContext, firBasedSymbol.getResolvedAnnotationsWithArguments(), classId);
    }

    public final void loadExperimentalitiesFromAnnotationTo(FirBasedSymbol<?> firBasedSymbol, FirSession firSession, Collection<Experimentality> collection) {
        firBasedSymbol.getClass();
        firSession.getClass();
        collection.getClass();
        loadExperimentalitiesFromAnnotationTo(firBasedSymbol, firSession, collection, false);
    }
}

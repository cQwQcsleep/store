package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.FirOverridesBackwardCompatibilityHelperKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOverrideChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirDeprecationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirOptInUsageBaseChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.CallToPotentiallyHiddenSymbolResult;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationsPerUseSite;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionOverrideFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionOverridePropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;
import org.jetbrains.kotlin.types.TypeCheckerState;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002OPB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0011J9\u0010\u0012\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0017J\u001e\u0010\u0018\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002J*\u0010\u001b\u001a\u0018\u0012\u0004\u0012\u00020\u001d\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020\u001e0\u001c2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002J*\u0010\u001f\u001a\u0018\u0012\u0004\u0012\u00020\u001d\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0004\u0012\u00020\u001e0\u001c2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002J \u0010#\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00142\u0010\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140%H\u0002J$\u0010&\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0014*\u00020'2\u0010\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140%H\u0002JG\u0010(\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010)\u001a\u00020\u00102\u0010\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140%H\u0002R\u00020\rR\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010*J?\u0010.\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u00142\u0010\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140%H\u0002R\u00020\rR\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010/J-\u00100\u001a\u00020\n*\u0006\u0012\u0002\b\u000301H\u0002R\u00020\rR\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u00102JG\u00103\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u00142\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140%2\u0006\u0010)\u001a\u00020\u0010H\u0002R\u00020\rR\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u00105JG\u00106\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u00142\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140%2\u0006\u0010)\u001a\u00020\u0010H\u0002R\u00020\rR\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u00105JI\u00107\u001a\u00020\n2\n\u00108\u001a\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010)\u001a\u00020\u00102\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0002R\u00020\rR\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010=JO\u0010>\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\u00142\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140%2\u0006\u0010)\u001a\u00020\u00102\u0006\u00109\u001a\u00020:H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010?JC\u0010@\u001a\u00020\n2\n\u0010A\u001a\u0006\u0012\u0002\b\u00030\u00142\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140%H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010BJ1\u0010C\u001a\u00020\n*\u00020\r2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010;\u001a\u00020<H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010DJ5\u0010E\u001a\u00020\n*\u00020\r2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030\u00142\n\u0010G\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010HJ5\u0010I\u001a\u00020\n*\u00020\r2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030\u00142\n\u0010G\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010HJ5\u0010J\u001a\u00020\n*\u00020\r2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030\u00142\n\u0010G\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010HJ5\u0010K\u001a\u00020\n*\u00020\r2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030\u00142\n\u0010G\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010HJ5\u0010L\u001a\u00020\n*\u00020\r2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030\u00142\n\u0010G\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010HJ5\u0010M\u001a\u00020\n*\u00020\r2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030\u00142\n\u0010G\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010HJ5\u0010N\u001a\u00020\n*\u00020\r2\n\u0010F\u001a\u0006\u0012\u0002\b\u00030\u00142\n\u0010G\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010HR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0018\u0010+\u001a\u00020\u0013*\u00020'8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-\u0082\u0001\u0002QR¨\u0006S"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOverrideChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAbstractOverrideChecker;", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "consideredOrigins", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "ensureKnownVisibility", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)Z", "chooseCannotInferVisibilityFor", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "symbol", "chooseCannotChangeAccessPrivilegeFor", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "Lorg/jetbrains/kotlin/name/Name;", "chooseCannotWeakenAccessPrivilegeFor", "wouldMissDiagnosticInK1", "getWouldMissDiagnosticInK1", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Z", "checkModality", "overriddenSymbols", Argument.Delimiters.none, "checkMutability", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "checkVisibility", "containingClass", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Ljava/util/List;)V", "canDelegateVisibilityConsistencyChecksToAccessors", "getCanDelegateVisibilityConsistencyChecksToAccessors", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Z", "checkDeprecation", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Ljava/util/List;)V", "checkDefaultValues", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;)V", "checkDataClassCopy", "overriddenMemberSymbols", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Ljava/util/List;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "checkSuspend", "checkMember", "member", "typeCheckerState", "Lorg/jetbrains/kotlin/types/TypeCheckerState;", "firTypeScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/types/TypeCheckerState;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)V", "checkDataClassMembers", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Ljava/util/List;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/types/TypeCheckerState;)Z", "checkOverriddenExperimentalities", "memberSymbol", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Ljava/util/List;)V", "reportNothingToOverride", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)V", "reportOverridingFinalMember", "overriding", "overridden", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "reportVarOverriddenByVal", "reportCannotWeakenAccessPrivilege", "reportCannotChangeAccessPrivilege", "reportReturnTypeMismatchOnFunction", "reportTypeMismatchOnProperty", "reportTypeMismatchOnVariable", "Regular", "ForExpectClass", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOverrideChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOverrideChecker$Regular;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirOverrideChecker extends FirAbstractOverrideChecker {
    private final Set<FirDeclarationOrigin> consideredOrigins;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOverrideChecker$ForExpectClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOverrideChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForExpectClass extends FirOverrideChecker {
        public static final ForExpectClass INSTANCE = new ForExpectClass();

        private ForExpectClass() {
            super(MppCheckerKind.Common, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOverrideChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                super.check(checkerContext, diagnosticReporter, firClass);
            }
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOverrideChecker$Regular;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOverrideChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Regular extends FirOverrideChecker {
        public static final Regular INSTANCE = new Regular();

        private Regular() {
            super(MppCheckerKind.Platform, null);
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOverrideChecker, org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
        public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
            checkerContext.getClass();
            diagnosticReporter.getClass();
            firClass.getClass();
            if (firClass.getStatus().isExpect()) {
                return;
            }
            super.check(checkerContext, diagnosticReporter, firClass);
        }
    }

    private FirOverrideChecker(MppCheckerKind mppCheckerKind) {
        super(mppCheckerKind);
        this.consideredOrigins = SetsKt.setOf(new FirDeclarationOrigin[]{FirDeclarationOrigin.Source.INSTANCE, FirDeclarationOrigin.Synthetic.DataClassMember.INSTANCE, FirDeclarationOrigin.Delegated.INSTANCE, FirDeclarationOrigin.IntersectionOverride.INSTANCE});
    }

    public static ProcessorAction b(String str, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol firCallableSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (DeprecationUtilsKt.hiddenStatusOfCall(firNamedFunctionSymbol, false, true) != CallToPotentiallyHiddenSymbolResult.VisibleWithDeprecation) {
            return ProcessorAction.NEXT;
        }
        final String deprecatedOverrideOfHiddenMessage$org_jetbrains_kotlin_checkers = FirDeprecationChecker.INSTANCE.getDeprecatedOverrideOfHiddenMessage$org_jetbrains_kotlin_checkers(str);
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirNamedFunctionSymbol) firCallableSymbol).getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getOVERRIDE_DEPRECATION(), (Object) firNamedFunctionSymbol, (Object) new FirDeprecationInfo() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOverrideChecker$checkDeprecation$1$deprecationInfo$1
            @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
            public DeprecationLevelValue getDeprecationLevel() {
                return DeprecationLevelValue.WARNING;
            }

            @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
            public String getMessage(FirSession session) {
                session.getClass();
                return deprecatedOverrideOfHiddenMessage$org_jetbrains_kotlin_checkers;
            }

            @Override // org.jetbrains.kotlin.fir.declarations.FirDeprecationInfo
            public boolean getPropagatesToOverrides() {
                return false;
            }
        }, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        return ProcessorAction.STOP;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:19:0x0044  */
    public static final void check$checkMember(FirOverrideChecker firOverrideChecker, FirClass firClass, DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, TypeCheckerState typeCheckerState, FirTypeScope firTypeScope, FirCallableSymbol<?> firCallableSymbol) {
        boolean z = firOverrideChecker.consideredOrigins.contains(firCallableSymbol.getOrigin()) && Intrinsics.areEqual(ClassMembersKt.containingClassLookupTag(firCallableSymbol), firClass.getSymbol().getLookupTag());
        if (z && !ClassMembersKt.isSubstitutionOverride(firCallableSymbol)) {
            firOverrideChecker.checkMember(diagnosticReporter, checkerContext, firCallableSymbol, firClass, typeCheckerState, firTypeScope);
            return;
        }
        KtSourceElement source = firCallableSymbol.getSource();
        if (source == null) {
            source = firClass.getSource();
        } else {
            if (!z) {
                source = null;
            }
            if (source == null) {
                source = firClass.getSource();
            }
        }
        firOverrideChecker.ensureKnownVisibility(checkerContext, diagnosticReporter, firCallableSymbol, source);
    }

    private final void checkDataClassCopy(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol, List<? extends FirCallableSymbol<?>> list, FirClass firClass) {
        FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) CollectionsKt.firstOrNull(list);
        if (firCallableSymbol2 == null) {
            return;
        }
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firCallableSymbol2);
        FirClassSymbol firClassSymbol = containingClassSymbol instanceof FirClassSymbol ? (FirClassSymbol) containingClassSymbol : null;
        if (firClassSymbol == null) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getDATA_CLASS_OVERRIDE_DEFAULT_VALUES(), (Object) firCallableSymbol, (Object) firClassSymbol, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private final boolean checkDataClassMembers(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, List<? extends FirCallableSymbol<?>> list, FirClass firClass, TypeCheckerState typeCheckerState) {
        KtSourceElement source = firCallableSymbol.getSource();
        Object obj = null;
        if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DataClassGeneratedMembers)) {
            return false;
        }
        for (Object obj2 : list) {
            if (((FirCallableSymbol) obj2).getResolvedStatus().getModality() == Modality.FINAL) {
                obj = obj2;
                break;
            }
        }
        FirCallableSymbol<?> firCallableSymbolCheckReturnType = (FirCallableSymbol) obj;
        if (firCallableSymbolCheckReturnType == null) {
            firCallableSymbolCheckReturnType = checkReturnType(checkerContext, firCallableSymbol, list, typeCheckerState);
        }
        FirCallableSymbol<?> firCallableSymbol2 = firCallableSymbolCheckReturnType;
        if (firCallableSymbol2 != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firClass.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getDATA_CLASS_OVERRIDE_CONFLICT(), (Object) firCallableSymbol, (Object) firCallableSymbol2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
        if (!Intrinsics.areEqual(firCallableSymbol.getName(), StandardNames.DATA_CLASS_COPY)) {
            return true;
        }
        checkDataClassCopy(diagnosticReporter, checkerContext, firCallableSymbol, list, firClass);
        return true;
    }

    private final void checkDefaultValues(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirFunctionSymbol<?> firFunctionSymbol) {
        for (FirValueParameterSymbol firValueParameterSymbol : firFunctionSymbol.getValueParameterSymbols()) {
            if (firValueParameterSymbol.getHasDefaultValue()) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firValueParameterSymbol.getDefaultValueSource(), FirErrors.INSTANCE.getDEFAULT_VALUE_NOT_ALLOWED_IN_OVERRIDE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    private final void checkDeprecation(final DiagnosticReporter diagnosticReporter, final CheckerContext checkerContext, final FirCallableSymbol<?> firCallableSymbol, List<? extends FirCallableSymbol<?>> list) {
        Collection<FirDeprecationInfo> collectionValues;
        DeprecationsPerUseSite deprecation = firCallableSymbol.getDeprecation(checkerContext.get$languageVersionSettings());
        if (deprecation == null || !deprecation.isNotEmpty()) {
            List<? extends FirCallableSymbol<?>> list2 = list;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list2, 10)), 16));
            Iterator<T> it = list2.iterator();
            while (true) {
                DeprecationsPerUseSite deprecationsPerUseSite = null;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                DeprecationsPerUseSite deprecation2 = ((FirCallableSymbol) next).getDeprecation(checkerContext.get$languageVersionSettings());
                if (deprecation2 != null && deprecation2.isNotEmpty()) {
                    deprecationsPerUseSite = deprecation2;
                }
                linkedHashMap.put(next, deprecationsPerUseSite);
            }
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                if (((DeprecationsPerUseSite) entry.getValue()) != null) {
                    linkedHashMap2.put(entry.getKey(), entry.getValue());
                }
            }
            if (linkedHashMap2.size() == list.size()) {
                for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
                    FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) entry2.getKey();
                    DeprecationsPerUseSite deprecationsPerUseSite2 = (DeprecationsPerUseSite) entry2.getValue();
                    deprecationsPerUseSite2.getClass();
                    FirDeprecationInfo all = deprecationsPerUseSite2.getAll();
                    if (all == null) {
                        Map<AnnotationUseSiteTarget, FirDeprecationInfo> bySpecificSite = deprecationsPerUseSite2.getBySpecificSite();
                        all = (bySpecificSite == null || (collectionValues = bySpecificSite.values()) == null) ? null : (FirDeprecationInfo) CollectionsKt.firstOrNull(collectionValues);
                        if (all == null) {
                        }
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getOVERRIDE_DEPRECATION(), (Object) firCallableSymbol2, (Object) all, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    return;
                }
            }
            if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
                FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firCallableSymbol;
                final String strAsString = firNamedFunctionSymbol.getName().asString();
                strAsString.getClass();
                if (FirDeprecationChecker.INSTANCE.getDeprecatedOverrideOfHiddenReplacements$org_jetbrains_kotlin_checkers().containsKey(strAsString)) {
                    FirHelpersKt.processOverriddenFunctionsWithActionSafe(checkerContext, firNamedFunctionSymbol, new Function1() { // from class: nb5
                        public final Object invoke(Object obj) {
                            return FirOverrideChecker.b(strAsString, checkerContext, diagnosticReporter, firCallableSymbol, (FirNamedFunctionSymbol) obj);
                        }
                    });
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void checkMember(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol, FirClass firClass, TypeCheckerState typeCheckerState, FirTypeScope firTypeScope) {
        FirCallableSymbol<?> firCallableSymbolCheckReturnType;
        FirCallableSymbol<?> firCallableSymbolCheckMutability;
        FirCallableSymbol<?> firCallableSymbolCheckModality;
        Object next;
        FirRegularClassSymbol regularClassSymbol;
        FirCallableSymbol<FirCallableDeclaration> symbol;
        List<FirCallableSymbol<?>> directOverriddenSafe = FirTypeScopeKt.getDirectOverriddenSafe(firTypeScope, firCallableSymbol);
        checkSuspend(diagnosticReporter, checkerContext, firCallableSymbol, directOverriddenSafe, firClass);
        if (checkDataClassMembers(checkerContext, diagnosticReporter, firCallableSymbol, directOverriddenSafe, firClass, typeCheckerState)) {
            return;
        }
        if (firCallableSymbol.getResolvedStatus().isOverride()) {
            if (directOverriddenSafe.isEmpty()) {
                reportNothingToOverride(checkerContext, diagnosticReporter, firCallableSymbol, firTypeScope);
                return;
            }
            checkOverriddenExperimentalities(checkerContext, diagnosticReporter, firCallableSymbol, directOverriddenSafe);
            if (!ClassMembersKt.isIntersectionOverride(firCallableSymbol) && !ClassMembersKt.isDelegated(firCallableSymbol) && (firCallableSymbolCheckModality = checkModality(directOverriddenSafe)) != null) {
                reportOverridingFinalMember(checkerContext, diagnosticReporter, firCallableSymbol, firCallableSymbolCheckModality);
            }
            boolean z = firCallableSymbol instanceof FirPropertySymbol;
            if (z && !ClassMembersKt.isDelegated(firCallableSymbol) && (firCallableSymbolCheckMutability = checkMutability((FirPropertySymbol) firCallableSymbol, directOverriddenSafe)) != null) {
                reportVarOverriddenByVal(checkerContext, diagnosticReporter, firCallableSymbol, firCallableSymbolCheckMutability);
            }
            checkVisibility(diagnosticReporter, checkerContext, firCallableSymbol, firClass, directOverriddenSafe);
            if (Intrinsics.areEqual(firCallableSymbol.getOrigin(), FirDeclarationOrigin.Source.INSTANCE)) {
                checkDeprecation(diagnosticReporter, checkerContext, firCallableSymbol, directOverriddenSafe);
            }
            if (firCallableSymbol instanceof FirFunctionSymbol) {
                FirFunctionSymbol<?> firFunctionSymbol = (FirFunctionSymbol) firCallableSymbol;
                if (!Intrinsics.areEqual(firFunctionSymbol.getOrigin(), FirDeclarationOrigin.Synthetic.DataClassMember.INSTANCE)) {
                    checkDefaultValues(diagnosticReporter, checkerContext, firFunctionSymbol);
                }
            }
            if (ClassMembersKt.isIntersectionOverride(firCallableSymbol) || ClassMembersKt.isDelegated(firCallableSymbol) || (firCallableSymbolCheckReturnType = checkReturnType(checkerContext, firCallableSymbol, directOverriddenSafe, typeCheckerState)) == null) {
                return;
            }
            if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
                reportReturnTypeMismatchOnFunction(checkerContext, diagnosticReporter, firCallableSymbol, firCallableSymbolCheckReturnType);
                return;
            } else {
                if (z) {
                    if (((FirPropertySymbol) firCallableSymbol).isVar()) {
                        reportTypeMismatchOnVariable(checkerContext, diagnosticReporter, firCallableSymbol, firCallableSymbolCheckReturnType);
                        return;
                    } else {
                        reportTypeMismatchOnProperty(checkerContext, diagnosticReporter, firCallableSymbol, firCallableSymbolCheckReturnType);
                        return;
                    }
                }
                return;
            }
        }
        if (directOverriddenSafe.isEmpty() || FirOverridesBackwardCompatibilityHelperKt.getOverridesBackwardCompatibilityHelper(checkerContext.getSession()).overrideCanBeOmitted(checkerContext, directOverriddenSafe)) {
            return;
        }
        KtSourceElement source = firCallableSymbol.getSource();
        KtSourceElementKind kind = source != null ? source.getKind() : null;
        if ((kind instanceof KtRealSourceElementKind) || (kind instanceof KtFakeSourceElementKind.PropertyFromParameter)) {
            FirVisibilityChecker visibilityChecker = FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession());
            FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
            if (containingFileSymbol == null) {
                return;
            }
            List listPlus = CollectionsKt.plus(checkerContext.getContainingDeclarations(), firClass.getSymbol());
            Iterator<T> it = directOverriddenSafe.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirCallableSymbol) next).getFir();
                while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                    FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr == null) {
                        originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                    }
                    if (originalForSubstitutionOverrideAttr == null) {
                        break;
                    } else {
                        firCallableDeclaration = originalForSubstitutionOverrideAttr;
                    }
                }
                symbol = firCallableDeclaration.getSymbol();
                if (symbol == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                    return;
                }
            } while (!FirVisibilityCheckerKt.isVisible(visibilityChecker, symbol, checkerContext.getSession(), containingFileSymbol, listPlus, null, true));
            FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) next;
            if (firCallableSymbol2 != null) {
                FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) firCallableSymbol2.getFir();
                while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration2)) {
                    FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                    if (originalForSubstitutionOverrideAttr2 == null) {
                        originalForSubstitutionOverrideAttr2 = ClassMembersKt.isIntersectionOverride(firCallableDeclaration2) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration2) : null;
                    }
                    if (originalForSubstitutionOverrideAttr2 == null) {
                        break;
                    } else {
                        firCallableDeclaration2 = originalForSubstitutionOverrideAttr2;
                    }
                }
                FirCallableSymbol<FirCallableDeclaration> symbol2 = firCallableDeclaration2.getSymbol();
                if (symbol2 == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                    return;
                }
                ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(symbol2);
                if (coneClassLikeLookupTagContainingClassLookupTag == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol((SessionHolder) checkerContext, coneClassLikeLookupTagContainingClassLookupTag)) == null) {
                    return;
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getVIRTUAL_MEMBER_HIDDEN(), (Object) firCallableSymbol, (Object) regularClassSymbol, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }
    }

    private final FirCallableSymbol<?> checkModality(List<? extends FirCallableSymbol<?>> overriddenSymbols) {
        for (FirCallableSymbol<?> firCallableSymbol : overriddenSymbols) {
            if (firCallableSymbol.getResolvedStatus().getModality() == Modality.FINAL) {
                return firCallableSymbol;
            }
        }
        return null;
    }

    private final FirCallableSymbol<?> checkMutability(FirPropertySymbol firPropertySymbol, List<? extends FirCallableSymbol<?>> list) {
        boolean zIsVar = firPropertySymbol.isVar();
        Object obj = null;
        if (zIsVar) {
            return null;
        }
        for (Object obj2 : list) {
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) obj2;
            FirPropertySymbol firPropertySymbol2 = firCallableSymbol instanceof FirPropertySymbol ? (FirPropertySymbol) firCallableSymbol : null;
            if (firPropertySymbol2 != null && firPropertySymbol2.isVar()) {
                obj = obj2;
                break;
            }
        }
        return (FirCallableSymbol) obj;
    }

    private final void checkOverriddenExperimentalities(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, List<? extends FirCallableSymbol<?>> list) {
        List intersections;
        FirOptInUsageBaseChecker firOptInUsageBaseChecker = FirOptInUsageBaseChecker.INSTANCE;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        FirSession session = checkerContext.getSession();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) it.next();
            if (firCallableSymbol2 instanceof FirIntersectionOverridePropertySymbol) {
                intersections = ((FirIntersectionOverridePropertySymbol) firCallableSymbol2).getIntersections();
            } else {
                intersections = firCallableSymbol2 instanceof FirIntersectionOverrideFunctionSymbol ? ((FirIntersectionOverrideFunctionSymbol) firCallableSymbol2).getIntersections() : CollectionsKt.listOf(firCallableSymbol2);
            }
            CollectionsKt.addAll(arrayList, intersections);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            firOptInUsageBaseChecker.loadExperimentalitiesFromAnnotationTo((FirCallableSymbol) it2.next(), session, linkedHashSet);
        }
        firOptInUsageBaseChecker.reportNotAcceptedOverrideExperimentalities(checkerContext, diagnosticReporter, linkedHashSet, firCallableSymbol);
    }

    private final void checkSuspend(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol, List<? extends FirCallableSymbol<?>> list, FirClass firClass) {
        Object next;
        List<? extends FirCallableSymbol<?>> list2 = list;
        Iterator<T> it = list2.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((FirCallableSymbol) next).getRawStatus().isSuspend() == firCallableSymbol.getRawStatus().isSuspend());
        FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) next;
        if (firCallableSymbol2 == null) {
            return;
        }
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator<T> it2 = list2.iterator();
            while (it2.hasNext()) {
                if (((FirCallableSymbol) it2.next()).getRawStatus().isSuspend() != firCallableSymbol2.getRawStatus().isSuspend()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getCONFLICTING_INHERITED_MEMBERS(), (Object) firClass.getSymbol(), (Object) list, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    return;
                }
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) (firCallableSymbol.getRawStatus().isSuspend() ? FirErrors.INSTANCE.getNON_SUSPEND_OVERRIDDEN_BY_SUSPEND() : FirErrors.INSTANCE.getSUSPEND_OVERRIDDEN_BY_NON_SUSPEND()), (Object) firCallableSymbol, (Object) firCallableSymbol2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    /* JADX WARN: Code duplicated, block: B:36:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:39:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:42:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:77:0x00ef A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x00f9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:? A[LOOP:4: B:37:0x00c7->B:79:?, LOOP_END, SYNTHETIC] */
    private final void checkVisibility(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, final FirCallableSymbol<?> firCallableSymbol, FirClass firClass, List<? extends FirCallableSymbol<?>> list) {
        FirClass firClass2;
        FirCallableSymbol<?> firCallableSymbol2;
        Integer numCompare;
        FirFileSymbol containingFileSymbol;
        if (ensureKnownVisibility$default(this, checkerContext, diagnosticReporter, firCallableSymbol, null, 4, null) && !list.isEmpty()) {
            List<? extends FirCallableSymbol<?>> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                FirCallableSymbol firCallableSymbol3 = (FirCallableSymbol) it.next();
                arrayList.add(TuplesKt.to(firCallableSymbol3, firCallableSymbol3.getResolvedStatus().getVisibility()));
            }
            List<Pair> listSortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOverrideChecker$checkVisibility$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Visibilities visibilities = Visibilities.INSTANCE;
                    Integer numCompare2 = visibilities.compare(firCallableSymbol.getResolvedStatus().getVisibility(), (Visibility) ((Pair) t).getSecond());
                    Integer numValueOf = Integer.valueOf(numCompare2 != null ? numCompare2.intValue() : Integer.MIN_VALUE);
                    Integer numCompare3 = visibilities.compare(firCallableSymbol.getResolvedStatus().getVisibility(), (Visibility) ((Pair) t2).getSecond());
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(numCompare3 != null ? numCompare3.intValue() : Integer.MIN_VALUE));
                }
            });
            if (!(firCallableSymbol instanceof FirPropertySymbol)) {
                firClass2 = firClass;
                while (r12.hasNext()) {
                    firCallableSymbol2 = (FirCallableSymbol) pair.component1();
                    numCompare = Visibilities.INSTANCE.compare(firCallableSymbol.getResolvedStatus().getVisibility(), (Visibility) pair.component2());
                    if (numCompare == null) {
                        if (numCompare.intValue() < 0) {
                            reportCannotWeakenAccessPrivilege(checkerContext, diagnosticReporter, firCallableSymbol, firCallableSymbol2);
                            break;
                        }
                    } else {
                        reportCannotChangeAccessPrivilege(checkerContext, diagnosticReporter, firCallableSymbol, firCallableSymbol2);
                        break;
                    }
                }
            } else {
                FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firCallableSymbol;
                if (!getCanDelegateVisibilityConsistencyChecksToAccessors(firPropertySymbol)) {
                    firClass2 = firClass;
                    for (Pair pair : listSortedWith) {
                        firCallableSymbol2 = (FirCallableSymbol) pair.component1();
                        numCompare = Visibilities.INSTANCE.compare(firCallableSymbol.getResolvedStatus().getVisibility(), (Visibility) pair.component2());
                        if (numCompare == null) {
                            if (numCompare.intValue() < 0) {
                                reportCannotWeakenAccessPrivilege(checkerContext, diagnosticReporter, firCallableSymbol, firCallableSymbol2);
                                break;
                            }
                        } else {
                            reportCannotChangeAccessPrivilege(checkerContext, diagnosticReporter, firCallableSymbol, firCallableSymbol2);
                            break;
                        }
                    }
                } else {
                    FirPropertyAccessorSymbol getterSymbol = firPropertySymbol.getGetterSymbol();
                    if (getterSymbol != null) {
                        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                        Iterator<T> it2 = list2.iterator();
                        while (it2.hasNext()) {
                            FirCallableSymbol firCallableSymbol4 = (FirCallableSymbol) it2.next();
                            firCallableSymbol4.getClass();
                            FirPropertyAccessorSymbol getterSymbol2 = ((FirPropertySymbol) firCallableSymbol4).getGetterSymbol();
                            if (getterSymbol2 != null) {
                                firCallableSymbol4 = getterSymbol2;
                            }
                            arrayList2.add(firCallableSymbol4);
                        }
                        firClass2 = firClass;
                        checkVisibility(diagnosticReporter, checkerContext, getterSymbol, firClass2, arrayList2);
                    } else {
                        firClass2 = firClass;
                    }
                    FirPropertyAccessorSymbol setterSymbol = firPropertySymbol.getSetterSymbol();
                    if (setterSymbol != null) {
                        ArrayList arrayList3 = new ArrayList();
                        Iterator<T> it3 = list2.iterator();
                        while (it3.hasNext()) {
                            FirCallableSymbol firCallableSymbol5 = (FirCallableSymbol) it3.next();
                            firCallableSymbol5.getClass();
                            FirPropertyAccessorSymbol setterSymbol2 = ((FirPropertySymbol) firCallableSymbol5).getSetterSymbol();
                            if (setterSymbol2 != null) {
                                arrayList3.add(setterSymbol2);
                            }
                        }
                        checkVisibility(diagnosticReporter, checkerContext, setterSymbol, firClass2, arrayList3);
                    }
                }
            }
            if ((firCallableSymbol instanceof FirPropertyAccessorSymbol) || (containingFileSymbol = checkerContext.getContainingFileSymbol()) == null) {
                return;
            }
            List listPlus = CollectionsKt.plus(checkerContext.getContainingDeclarations(), firClass2.getSymbol());
            FirVisibilityChecker visibilityChecker = FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession());
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator<T> it4 = list2.iterator();
                while (it4.hasNext()) {
                    FirCallableSymbol firCallableSymbol6 = (FirCallableSymbol) it4.next();
                    FirLazyDeclarationResolverKt.lazyResolveToPhase(firCallableSymbol6, FirResolvePhase.STATUS);
                    if (FirVisibilityCheckerKt.isVisible(visibilityChecker, firCallableSymbol6, checkerContext.getSession(), containingFileSymbol, listPlus, null, true)) {
                        return;
                    }
                }
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getCANNOT_OVERRIDE_INVISIBLE_MEMBER(), (Object) firCallableSymbol, CollectionsKt.first(list), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }

    private final KtDiagnosticFactory3<Visibility, FirCallableSymbol<?>, Name> chooseCannotChangeAccessPrivilegeFor(FirCallableSymbol<?> symbol) {
        return !getWouldMissDiagnosticInK1(symbol) ? FirErrors.INSTANCE.getCANNOT_CHANGE_ACCESS_PRIVILEGE() : FirErrors.INSTANCE.getCANNOT_CHANGE_ACCESS_PRIVILEGE_WARNING();
    }

    private final KtDiagnosticFactory1<FirCallableSymbol<?>> chooseCannotInferVisibilityFor(FirCallableSymbol<?> symbol) {
        return !getWouldMissDiagnosticInK1(symbol) ? FirErrors.INSTANCE.getCANNOT_INFER_VISIBILITY() : FirErrors.INSTANCE.getCANNOT_INFER_VISIBILITY_WARNING();
    }

    private final KtDiagnosticFactory3<Visibility, FirCallableSymbol<?>, Name> chooseCannotWeakenAccessPrivilegeFor(FirCallableSymbol<?> symbol) {
        return !getWouldMissDiagnosticInK1(symbol) ? FirErrors.INSTANCE.getCANNOT_WEAKEN_ACCESS_PRIVILEGE() : FirErrors.INSTANCE.getCANNOT_WEAKEN_ACCESS_PRIVILEGE_WARNING();
    }

    private final boolean ensureKnownVisibility(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, KtSourceElement ktSourceElement) {
        if (!Intrinsics.areEqual(firCallableSymbol.getResolvedStatus().getVisibility(), Visibilities.Unknown.INSTANCE)) {
            return true;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) chooseCannotInferVisibilityFor(firCallableSymbol), (Object) firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        return false;
    }

    public static /* synthetic */ boolean ensureKnownVisibility$default(FirOverrideChecker firOverrideChecker, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol firCallableSymbol, KtSourceElement ktSourceElement, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: ensureKnownVisibility");
            return false;
        }
        if ((i & 4) != 0) {
            ktSourceElement = firCallableSymbol.getSource();
        }
        return firOverrideChecker.ensureKnownVisibility(checkerContext, diagnosticReporter, firCallableSymbol, ktSourceElement);
    }

    private final boolean getCanDelegateVisibilityConsistencyChecksToAccessors(FirPropertySymbol firPropertySymbol) {
        return (firPropertySymbol.getGetterSymbol() == null && firPropertySymbol.getSetterSymbol() == null) ? false : true;
    }

    private final boolean getWouldMissDiagnosticInK1(FirCallableSymbol<?> firCallableSymbol) {
        if (!(firCallableSymbol instanceof FirPropertyAccessorSymbol)) {
            return false;
        }
        FirPropertyAccessorSymbol firPropertyAccessorSymbol = (FirPropertyAccessorSymbol) firCallableSymbol;
        return ClassMembersKt.isIntersectionOverride(firPropertyAccessorSymbol.getPropertySymbol()) && !Intrinsics.areEqual(firCallableSymbol.getResolvedStatus().getVisibility(), firPropertyAccessorSymbol.getPropertySymbol().getResolvedStatus().getVisibility());
    }

    private final void reportCannotChangeAccessPrivilege(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableSymbol2);
        if (coneClassLikeLookupTagContainingClassLookupTag == null) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory3<Visibility, FirCallableSymbol<?>, Name>) ((KtDiagnosticFactory3<Object, Object, Object>) chooseCannotChangeAccessPrivilegeFor(firCallableSymbol)), firCallableSymbol.getResolvedStatus().getVisibility(), firCallableSymbol2, coneClassLikeLookupTagContainingClassLookupTag.getName(), (64 & 64) != 0 ? null : null);
    }

    private final void reportCannotWeakenAccessPrivilege(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableSymbol2);
        if (coneClassLikeLookupTagContainingClassLookupTag == null) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory3<Visibility, FirCallableSymbol<?>, Name>) ((KtDiagnosticFactory3<Object, Object, Object>) chooseCannotWeakenAccessPrivilegeFor(firCallableSymbol)), firCallableSymbol.getResolvedStatus().getVisibility(), firCallableSymbol2, coneClassLikeLookupTagContainingClassLookupTag.getName(), (64 & 64) != 0 ? null : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void reportNothingToOverride(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, FirTypeScope firTypeScope) {
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firCallableSymbol);
        Iterable properties = firCallableSymbol instanceof FirPropertySymbol ? FirScopeKt.getProperties(firTypeScope, ((FirPropertySymbol) firCallableSymbol).getName()) : FirScopeKt.getFunctions(firTypeScope, firCallableSymbol.getName());
        ArrayList arrayList = new ArrayList();
        for (Object obj : properties) {
            FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) obj;
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol2.getFir();
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                } else {
                    firCallableDeclaration = originalForSubstitutionOverrideAttr;
                }
            }
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            if (symbol == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return;
            } else if (!Intrinsics.areEqual(ContainingClassUtilsKt.getContainingClassSymbol(symbol), containingClassSymbol) && (firCallableSymbol2.getResolvedStatus().getModality() == Modality.OPEN || firCallableSymbol2.getResolvedStatus().getModality() == Modality.ABSTRACT)) {
                arrayList.add(obj);
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getNOTHING_TO_OVERRIDE(), (Object) firCallableSymbol, (Object) arrayList, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private final void reportOverridingFinalMember(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableSymbol2);
        if (coneClassLikeLookupTagContainingClassLookupTag != null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getOVERRIDING_FINAL_MEMBER(), (Object) firCallableSymbol2, (Object) coneClassLikeLookupTagContainingClassLookupTag.getName(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }

    private final void reportReturnTypeMismatchOnFunction(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getRETURN_TYPE_MISMATCH_ON_OVERRIDE(), (Object) firCallableSymbol, (Object) firCallableSymbol2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private final void reportTypeMismatchOnProperty(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getPROPERTY_TYPE_MISMATCH_ON_OVERRIDE(), (Object) firCallableSymbol, (Object) firCallableSymbol2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private final void reportTypeMismatchOnVariable(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getVAR_TYPE_MISMATCH_ON_OVERRIDE(), (Object) firCallableSymbol, (Object) firCallableSymbol2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    private final void reportVarOverriddenByVal(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableSymbol.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getVAR_OVERRIDDEN_BY_VAL(), (Object) firCallableSymbol2, (Object) firCallableSymbol, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        List<FirTypeRef> superTypeRefs = firClass.getSuperTypeRefs();
        if (!(superTypeRefs instanceof Collection) || !superTypeRefs.isEmpty()) {
            Iterator<T> it = superTypeRefs.iterator();
            while (it.hasNext()) {
                if (((FirTypeRef) it.next()) instanceof FirErrorTypeRef) {
                    return;
                }
            }
        }
        TypeCheckerState typeCheckerStateNewTypeCheckerState = TypeComponentsKt.getTypeContext(checkerContext.getSession()).newTypeCheckerState(false, false, LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.AllowDnnTypeOverridingFlexibleType));
        FirTypeScope firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(checkerContext, firClass);
        FirContainingNamesAwareScopeKt.processAllProperties(firTypeScopeUnsubstitutedScope, new AnonymousClass2(firClass, diagnosticReporter, checkerContext, typeCheckerStateNewTypeCheckerState, firTypeScopeUnsubstitutedScope));
        FirContainingNamesAwareScopeKt.processAllFunctions(firTypeScopeUnsubstitutedScope, new AnonymousClass3(firClass, diagnosticReporter, checkerContext, typeCheckerStateNewTypeCheckerState, firTypeScopeUnsubstitutedScope));
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOverrideChecker$check$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<FirCallableSymbol<?>, Unit> {
        final /* synthetic */ CheckerContext $context;
        final /* synthetic */ FirClass $declaration;
        final /* synthetic */ FirTypeScope $firTypeScope;
        final /* synthetic */ DiagnosticReporter $reporter;
        final /* synthetic */ TypeCheckerState $typeCheckerState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(FirClass firClass, DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, TypeCheckerState typeCheckerState, FirTypeScope firTypeScope) {
            super(1, Intrinsics.Kotlin.class, "checkMember", "check$checkMember(Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOverrideChecker;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/types/TypeCheckerState;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", 0);
            this.$declaration = firClass;
            this.$reporter = diagnosticReporter;
            this.$context = checkerContext;
            this.$typeCheckerState = typeCheckerState;
            this.$firTypeScope = firTypeScope;
        }

        public final void invoke(FirCallableSymbol<?> firCallableSymbol) {
            firCallableSymbol.getClass();
            FirOverrideChecker.check$checkMember(FirOverrideChecker.this, this.$declaration, this.$reporter, this.$context, this.$typeCheckerState, this.$firTypeScope, firCallableSymbol);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((FirCallableSymbol<?>) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOverrideChecker$check$3, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function1<FirCallableSymbol<?>, Unit> {
        final /* synthetic */ CheckerContext $context;
        final /* synthetic */ FirClass $declaration;
        final /* synthetic */ FirTypeScope $firTypeScope;
        final /* synthetic */ DiagnosticReporter $reporter;
        final /* synthetic */ TypeCheckerState $typeCheckerState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(FirClass firClass, DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, TypeCheckerState typeCheckerState, FirTypeScope firTypeScope) {
            super(1, Intrinsics.Kotlin.class, "checkMember", "check$checkMember(Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirOverrideChecker;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/types/TypeCheckerState;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", 0);
            this.$declaration = firClass;
            this.$reporter = diagnosticReporter;
            this.$context = checkerContext;
            this.$typeCheckerState = typeCheckerState;
            this.$firTypeScope = firTypeScope;
        }

        public final void invoke(FirCallableSymbol<?> firCallableSymbol) {
            firCallableSymbol.getClass();
            FirOverrideChecker.check$checkMember(FirOverrideChecker.this, this.$declaration, this.$reporter, this.$context, this.$typeCheckerState, this.$firTypeScope, firCallableSymbol);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((FirCallableSymbol<?>) obj);
            return Unit.INSTANCE;
        }
    }

    public /* synthetic */ FirOverrideChecker(MppCheckerKind mppCheckerKind, DefaultConstructorMarker defaultConstructorMarker) {
        this(mppCheckerKind);
    }
}

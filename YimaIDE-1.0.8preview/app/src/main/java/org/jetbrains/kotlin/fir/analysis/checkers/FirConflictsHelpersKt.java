package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirNameConflictsTracker;
import org.jetbrains.kotlin.fir.FirNameConflictsTrackerKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirConflictsHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOverloadabilityHelper;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOverloadabilityHelperKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirOuterClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImplKt;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirPackageMemberScope;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirErrorCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.util.ListMultimap;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;
import org.jetbrains.kotlin.utils.SmartSet;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u0010\u0010\u0006\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0007H\u0002\u001a \u0010\u000e\u001a\u00020\u00012\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00072\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0002\u001a3\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00122\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002R\u00020\u0015j\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0002\u0010\u001a\u001a \u0010\u001b\u001a\u00020\u001c*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u001d2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f\u001a+\u0010$\u001a\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030%\u0018\u00010\u001d2\u0006\u0010&\u001a\u00020'R\u00020\u0015j\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0002\u0010(\u001aY\u0010)\u001a\u00020\u001c\"\f\b\u0000\u0010**\u0006\u0012\u0002\b\u00030\u0007\"\b\b\u0001\u0010+*\u0002H**\b\u0012\u0004\u0012\u0002H*0\u001d2\u0006\u0010,\u001a\u0002H+2\u0006\u0010-\u001a\u00020.2\u0018\u0010/\u001a\u0014\u0012\u0004\u0012\u00020.\u0012\n\u0012\b\u0012\u0004\u0012\u0002H+0100H\u0002¢\u0006\u0002\u00102\u001a$\u00103\u001a\u00020\u001c*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u001d2\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207\u001a/\u00108\u001a\u0010\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020;\u0018\u000109*\u0006\u0012\u0002\b\u00030<H\u0002R\u00020\u0015j\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0002\u0010=\u001a \u0010>\u001a\u00020\u00012\n\u0010?\u001a\u0006\u0012\u0002\b\u00030\u00072\n\u0010@\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0002\u001aV\u0010A\u001a\u00020\u001c*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u001d2\n\u0010,\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010B\u001a\u00020.2\u0006\u0010C\u001a\u0002052\n\u0010D\u001a\u0006\u0012\u0002\b\u00030\u00072\n\b\u0002\u0010E\u001a\u0004\u0018\u00010.2\n\b\u0002\u0010F\u001a\u0004\u0018\u000105H\u0002\u001a\u0014\u0010G\u001a\u00020\u0001*\u00020\t2\u0006\u0010H\u001a\u00020IH\u0002\u001a:\u0010J\u001a\u00020\u00012\n\u0010K\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010L\u001a\u0002052\n\u0010M\u001a\u0006\u0012\u0002\b\u00030\u00072\b\u0010N\u001a\u0004\u0018\u0001052\u0006\u0010H\u001a\u00020IH\u0002\u001a(\u0010O\u001a\u00020P*\u0006\u0012\u0002\b\u00030\u001d2\n\u0010,\u001a\u0006\u0012\u0002\b\u00030\u00072\n\u0010Q\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0002\u001a$\u0010R\u001a\u00020P*\u00020S2\n\u0010,\u001a\u0006\u0012\u0002\b\u00030T2\n\u0010Q\u001a\u0006\u0012\u0002\b\u00030TH\u0002\u001a1\u0010U\u001a\u00020\u001c2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020Y0\u0018R\u00020\u0015R\u00020Vj\u0006\u0010\u0016\u001a\u00020\u0015j\u0006\u0010W\u001a\u00020V¢\u0006\u0002\u0010Z\"\u0018\u0010\u0003\u001a\u00020\u0001*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005\"\u0018\u0010\b\u001a\u00020\u0001*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n\"\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00078@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u001c\u0010 \u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006["}, d2 = {"isAllowedForMainFunction", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "isTopLevel", "Lorg/jetbrains/kotlin/name/CallableId;", "(Lorg/jetbrains/kotlin/name/CallableId;)Z", "isCollectable", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isCollectableAccordingToSource", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)Z", "resolvedStatus", "getResolvedStatus", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "isAtLeastOneExpect", "first", "second", "groupTopLevelByName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/DeclarationBuckets;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "declarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;)Ljava/util/Map;", "collectClassMembers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirDeclarationCollector;", "klass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "getName", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;)Lorg/jetbrains/kotlin/name/Name;", "collectConflictingLocalFunctionsFrom", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)Lorg/jetbrains/kotlin/fir/analysis/checkers/FirDeclarationCollector;", "collect", "D", "S", "declaration", "representation", Argument.Delimiters.none, "map", Argument.Delimiters.none, Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/FirDeclarationCollector;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Ljava/lang/String;Ljava/util/Map;)V", "collectTopLevel", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "packageMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirPackageMemberScope;", "expandedClassWithConstructorsScope", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Lkotlin/Pair;", "shouldCheckForMultiplatformRedeclaration", "dependency", "dependent", "collectTopLevelConflict", "declarationPresentation", "containingFile", "conflictingSymbol", "conflictingPresentation", "conflictingFile", "representsMainFunctionAllowingConflictingOverloads", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "areCompatibleMainFunctions", "declaration1", "file1", "declaration2", "file2", "getConflictState", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ConflictState;", "conflicting", "getConflictStateWithContextParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOverloadabilityHelper;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "checkForLocalRedeclarations", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "elements", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/List;)V", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConflictsHelpersKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ConflictState.values().length];
            try {
                iArr[ConflictState.Conflict.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ConflictState.ContextParameterShadowing.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ConflictState.NoConflict.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FirDeclarationOverloadabilityHelper.ContextParameterShadowing.values().length];
            try {
                iArr2[FirDeclarationOverloadabilityHelper.ContextParameterShadowing.BothWays.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[FirDeclarationOverloadabilityHelper.ContextParameterShadowing.Shadowing.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[FirDeclarationOverloadabilityHelper.ContextParameterShadowing.None.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private static final boolean areCompatibleMainFunctions(FirBasedSymbol<?> firBasedSymbol, FirFile firFile, FirBasedSymbol<?> firBasedSymbol2, FirFile firFile2, FirSession firSession) {
        return !Intrinsics.areEqual(firFile, firFile2) && (firBasedSymbol instanceof FirNamedFunctionSymbol) && (firBasedSymbol2 instanceof FirNamedFunctionSymbol) && representsMainFunctionAllowingConflictingOverloads((FirNamedFunctionSymbol) firBasedSymbol, firSession) && representsMainFunctionAllowingConflictingOverloads((FirNamedFunctionSymbol) firBasedSymbol2, firSession);
    }

    /* JADX WARN: Code duplicated, block: B:22:0x007f  */
    public static final void checkForLocalRedeclarations(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List<? extends FirElement> list) {
        Pair pair;
        FirTypeRef typeRef;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        list.getClass();
        if (list.size() <= 1) {
            return;
        }
        ListMultimap listMultimap = new ListMultimap();
        for (FirElement firElement : list) {
            ConeKotlinType coneType = null;
            if (firElement instanceof FirProperty) {
                if (Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration((FirDeclaration) firElement), Boolean.TRUE)) {
                    FirProperty firProperty = (FirProperty) firElement;
                    if (firProperty.getDelegate() == null || firProperty.getReceiverParameter() == null) {
                        FirProperty firProperty2 = (FirProperty) firElement;
                        pair = TuplesKt.to(firProperty2.getSymbol(), firProperty2.getName());
                    } else {
                        FirPropertySymbol symbol = firProperty.getSymbol();
                        StringBuilder sb = new StringBuilder();
                        FirReceiverParameter receiverParameter = firProperty.getReceiverParameter();
                        if (receiverParameter != null && (typeRef = receiverParameter.getTypeRef()) != null) {
                            coneType = FirTypeUtilsKt.getConeType(typeRef);
                        }
                        sb.append(coneType);
                        sb.append('.');
                        sb.append(firProperty.getName());
                        pair = TuplesKt.to(symbol, Name.identifier(sb.toString()));
                    }
                } else {
                    FirProperty firProperty3 = (FirProperty) firElement;
                    pair = TuplesKt.to(firProperty3.getSymbol(), firProperty3.getName());
                }
            } else if (firElement instanceof FirVariable) {
                FirVariable firVariable = (FirVariable) firElement;
                pair = TuplesKt.to(firVariable.getSymbol(), firVariable.getName());
            } else if (firElement instanceof FirClassLikeDeclaration) {
                FirClassLikeSymbol<FirClassLikeDeclaration> symbol2 = ((FirClassLikeDeclaration) firElement).getSymbol();
                pair = TuplesKt.to(symbol2, symbol2.getName());
            } else if (!(firElement instanceof FirOuterClassTypeParameterRef)) {
                if (firElement instanceof FirTypeParameterRef) {
                    FirTypeParameterSymbol symbol3 = ((FirTypeParameterRef) firElement).getSymbol();
                    pair = TuplesKt.to(symbol3, symbol3.getName());
                } else {
                    pair = TuplesKt.to(null, null);
                }
            }
            FirBasedSymbol firBasedSymbol = (FirBasedSymbol) pair.component1();
            Name name = (Name) pair.component2();
            if (name != null && !name.isSpecial()) {
                firBasedSymbol.getClass();
                listMultimap.put(name, firBasedSymbol);
            }
        }
        Iterator it = listMultimap.getKeys().iterator();
        while (it.hasNext()) {
            List list2 = (List) listMultimap.get((Name) it.next());
            if (list2.size() > 1) {
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirBasedSymbol) it2.next()).getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getREDECLARATION(), (Object) list2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
            }
        }
    }

    /* JADX WARN: Incorrect types in method signature: <D:Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol<*>;S::TD;>(Lorg/jetbrains/kotlin/fir/analysis/checkers/FirDeclarationCollector<TD;>;TS;Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/util/Set<TS;>;>;)V */
    /* JADX WARN: Multi-variable type inference failed */
    private static final void collect(FirDeclarationCollector firDeclarationCollector, FirBasedSymbol firBasedSymbol, String str, Map map) {
        Object linkedHashSet = map.get(str);
        if (linkedHashSet == null) {
            linkedHashSet = new LinkedHashSet();
            map.put(str, linkedHashSet);
        }
        Set<FirBasedSymbol> set = (Set) linkedHashSet;
        if (set.add(firBasedSymbol)) {
            SmartSet.Companion companion = SmartSet.Companion;
            SmartSet<FirBasedSymbol<?>> smartSetCreate = companion.create();
            SmartSet<FirBasedSymbol<?>> smartSetCreate2 = companion.create();
            for (FirBasedSymbol firBasedSymbol2 : set) {
                if (!Intrinsics.areEqual(firBasedSymbol2, firBasedSymbol)) {
                    int i = WhenMappings.$EnumSwitchMapping$0[getConflictState(firDeclarationCollector, firBasedSymbol, firBasedSymbol2).ordinal()];
                    if (i == 1) {
                        smartSetCreate.add(firBasedSymbol2);
                        HashMap declarationConflictingSymbols = firDeclarationCollector.getDeclarationConflictingSymbols();
                        Object objCreate = declarationConflictingSymbols.get(firBasedSymbol2);
                        if (objCreate == null) {
                            objCreate = SmartSet.Companion.create();
                            declarationConflictingSymbols.put(firBasedSymbol2, objCreate);
                        }
                        ((SmartSet) objCreate).add(firBasedSymbol);
                    } else if (i == 2) {
                        smartSetCreate2.add(firBasedSymbol2);
                    } else if (i != 3) {
                        bu8.a();
                        return;
                    }
                }
            }
            firDeclarationCollector.getDeclarationConflictingSymbols().put(firBasedSymbol, smartSetCreate);
            firDeclarationCollector.getDeclarationShadowedViaContextParameters().put(firBasedSymbol, smartSetCreate2);
        }
    }

    public static final void collectClassMembers(final FirDeclarationCollector<FirBasedSymbol<?>> firDeclarationCollector, final FirClassSymbol<?> firClassSymbol) {
        firDeclarationCollector.getClass();
        firClassSymbol.getClass();
        CheckerContext context = firDeclarationCollector.getContext();
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        FirContainingNamesAwareScope firContainingNamesAwareScopeDeclaredMemberScope = FirHelpersKt.declaredMemberScope(context, firClassSymbol);
        final FirTypeScope firTypeScopeUnsubstitutedScope = FirHelpersKt.unsubstitutedScope(context, firClassSymbol);
        FirContainingNamesAwareScopeKt.processAllFunctions(firContainingNamesAwareScopeDeclaredMemberScope, new Function1() { // from class: xz4
            public final Object invoke(Object obj) {
                return FirConflictsHelpersKt.collectClassMembers$lambda$0$0(firDeclarationCollector, linkedHashMap2, firTypeScopeUnsubstitutedScope, firClassSymbol, (FirNamedFunctionSymbol) obj);
            }
        });
        if (FirHelpersKt.isTopLevel(firDeclarationCollector.getContext())) {
            firTypeScopeUnsubstitutedScope.processDeclaredConstructors(new Function1() { // from class: yz4
                public final Object invoke(Object obj) {
                    return FirConflictsHelpersKt.collectClassMembers$lambda$0$1(firClassSymbol, firDeclarationCollector, linkedHashMap2, (FirConstructorSymbol) obj);
                }
            });
        }
        FirContainingNamesAwareScopeKt.processAllProperties(firContainingNamesAwareScopeDeclaredMemberScope, new Function1() { // from class: zz4
            public final Object invoke(Object obj) {
                return FirConflictsHelpersKt.collectClassMembers$lambda$0$2(firDeclarationCollector, linkedHashMap, firTypeScopeUnsubstitutedScope, firClassSymbol, (FirVariableSymbol) obj);
            }
        });
        for (final FirBasedSymbol<?> firBasedSymbol : firClassSymbol.getDeclarationSymbols()) {
            if (firBasedSymbol instanceof FirClassifierSymbol) {
                FirClassifierSymbol firClassifierSymbol = (FirClassifierSymbol) firBasedSymbol;
                final FirDeclarationCollector<FirBasedSymbol<?>> firDeclarationCollector2 = firDeclarationCollector;
                final FirClassSymbol<?> firClassSymbol2 = firClassSymbol;
                collectClassMembers$lambda$0$processClassifier(firClassSymbol2, firDeclarationCollector2, linkedHashMap, context, linkedHashMap2, firClassifierSymbol, true);
                Name name = getName(firClassifierSymbol);
                final LinkedHashMap linkedHashMap3 = linkedHashMap2;
                final LinkedHashMap linkedHashMap4 = linkedHashMap;
                final CheckerContext checkerContext = context;
                final Function1 function1 = new Function1() { // from class: a05
                    public final Object invoke(Object obj) {
                        return FirConflictsHelpersKt.collectClassMembers$lambda$0$4(firBasedSymbol, firClassSymbol2, firDeclarationCollector2, linkedHashMap4, checkerContext, linkedHashMap3, (FirClassifierSymbol) obj);
                    }
                };
                linkedHashMap = linkedHashMap4;
                context = checkerContext;
                linkedHashMap2 = linkedHashMap3;
                firTypeScopeUnsubstitutedScope.processClassifiersByNameWithSubstitution(name, new Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.FirConflictsHelpersKt$collectClassMembers$lambda$0$$inlined$processClassifiersByName$1
                    public final void invoke(FirClassifierSymbol<?> firClassifierSymbol2, ConeSubstitutor coneSubstitutor) {
                        firClassifierSymbol2.getClass();
                        coneSubstitutor.getClass();
                        function1.invoke(firClassifierSymbol2);
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
                        return Unit.INSTANCE;
                    }
                });
                firClassSymbol = firClassSymbol2;
                firDeclarationCollector = firDeclarationCollector2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit collectClassMembers$lambda$0$0(final FirDeclarationCollector firDeclarationCollector, final Map map, FirTypeScope firTypeScope, final FirClassSymbol firClassSymbol, final FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (!isCollectable(firNamedFunctionSymbol)) {
            return Unit.INSTANCE;
        }
        collect(firDeclarationCollector, firNamedFunctionSymbol, FirRedeclarationPresenter.INSTANCE.represent(firNamedFunctionSymbol), map);
        firTypeScope.processFunctionsByName(firNamedFunctionSymbol.getName(), new Function1() { // from class: uz4
            public final Object invoke(Object obj) {
                return FirConflictsHelpersKt.collectClassMembers$lambda$0$0$0(firNamedFunctionSymbol, firClassSymbol, firDeclarationCollector, map, (FirNamedFunctionSymbol) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit collectClassMembers$lambda$0$0$0(FirNamedFunctionSymbol firNamedFunctionSymbol, FirClassSymbol firClassSymbol, FirDeclarationCollector firDeclarationCollector, Map map, FirNamedFunctionSymbol firNamedFunctionSymbol2) {
        firNamedFunctionSymbol2.getClass();
        if (!Intrinsics.areEqual(firNamedFunctionSymbol2, firNamedFunctionSymbol) && isCollectable(firNamedFunctionSymbol2) && FirVisibilityCheckerKt.isVisibleInClass(firNamedFunctionSymbol2, (FirClassSymbol<?>) firClassSymbol)) {
            collect(firDeclarationCollector, firNamedFunctionSymbol2, FirRedeclarationPresenter.INSTANCE.represent(firNamedFunctionSymbol2), map);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit collectClassMembers$lambda$0$1(FirClassSymbol firClassSymbol, FirDeclarationCollector firDeclarationCollector, Map map, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        if (isCollectable(firConstructorSymbol) && FirVisibilityCheckerKt.isVisibleInClass(firConstructorSymbol, (FirClassSymbol<?>) firClassSymbol)) {
            collect(firDeclarationCollector, firConstructorSymbol, FirRedeclarationPresenter.INSTANCE.represent(firConstructorSymbol, firClassSymbol), map);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit collectClassMembers$lambda$0$2(final FirDeclarationCollector firDeclarationCollector, final Map map, FirTypeScope firTypeScope, final FirClassSymbol firClassSymbol, final FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (!isCollectable(firVariableSymbol)) {
            return Unit.INSTANCE;
        }
        collect(firDeclarationCollector, firVariableSymbol, FirRedeclarationPresenter.INSTANCE.represent((FirVariableSymbol<?>) firVariableSymbol), map);
        firTypeScope.processPropertiesByName(firVariableSymbol.getName(), new Function1() { // from class: vz4
            public final Object invoke(Object obj) {
                return FirConflictsHelpersKt.collectClassMembers$lambda$0$2$0(firVariableSymbol, firClassSymbol, firDeclarationCollector, map, (FirVariableSymbol) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit collectClassMembers$lambda$0$2$0(FirVariableSymbol firVariableSymbol, FirClassSymbol firClassSymbol, FirDeclarationCollector firDeclarationCollector, Map map, FirVariableSymbol firVariableSymbol2) {
        firVariableSymbol2.getClass();
        if (!Intrinsics.areEqual(firVariableSymbol2, firVariableSymbol) && isCollectable(firVariableSymbol2) && FirVisibilityCheckerKt.isVisibleInClass(firVariableSymbol2, (FirClassSymbol<?>) firClassSymbol)) {
            collect(firDeclarationCollector, firVariableSymbol2, FirRedeclarationPresenter.INSTANCE.represent((FirVariableSymbol<?>) firVariableSymbol2), map);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit collectClassMembers$lambda$0$4(FirBasedSymbol firBasedSymbol, FirClassSymbol firClassSymbol, FirDeclarationCollector firDeclarationCollector, Map map, CheckerContext checkerContext, Map map2, FirClassifierSymbol firClassifierSymbol) {
        firClassifierSymbol.getClass();
        if (!Intrinsics.areEqual(firClassifierSymbol, firBasedSymbol)) {
            collectClassMembers$lambda$0$processClassifier(firClassSymbol, firDeclarationCollector, map, checkerContext, map2, firClassifierSymbol, false);
        }
        return Unit.INSTANCE;
    }

    private static final void collectClassMembers$lambda$0$processClassifier(FirClassSymbol<?> firClassSymbol, final FirDeclarationCollector<FirBasedSymbol<?>> firDeclarationCollector, Map<String, Set<FirBasedSymbol<?>>> map, CheckerContext checkerContext, final Map<String, Set<FirFunctionSymbol<?>>> map2, final FirClassifierSymbol<?> firClassifierSymbol, boolean z) {
        Pair<FirRegularClassSymbol, FirScope> pairExpandedClassWithConstructorsScope;
        if (isCollectable(firClassifierSymbol)) {
            if (z || FirVisibilityCheckerKt.isVisibleInClass(firClassifierSymbol, firClassSymbol)) {
                if (firClassifierSymbol instanceof FirRegularClassSymbol) {
                    collect(firDeclarationCollector, firClassifierSymbol, FirRedeclarationPresenter.INSTANCE.represent((FirRegularClassSymbol) firClassifierSymbol), map);
                } else if (firClassifierSymbol instanceof FirTypeAliasSymbol) {
                    collect(firDeclarationCollector, firClassifierSymbol, FirRedeclarationPresenter.INSTANCE.represent((FirTypeAliasSymbol) firClassifierSymbol), map);
                }
                if ((firClassifierSymbol instanceof FirClassLikeSymbol) && (pairExpandedClassWithConstructorsScope = expandedClassWithConstructorsScope(checkerContext, (FirClassLikeSymbol) firClassifierSymbol)) != null) {
                    FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) pairExpandedClassWithConstructorsScope.component1();
                    FirScope firScope = (FirScope) pairExpandedClassWithConstructorsScope.component2();
                    if (firRegularClassSymbol.getClassKind() == ClassKind.OBJECT) {
                        return;
                    }
                    firScope.processDeclaredConstructors(new Function1() { // from class: wz4
                        public final Object invoke(Object obj) {
                            return FirConflictsHelpersKt.collectClassMembers$lambda$0$processClassifier$3$0(firDeclarationCollector, firClassifierSymbol, map2, (FirConstructorSymbol) obj);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit collectClassMembers$lambda$0$processClassifier$3$0(FirDeclarationCollector firDeclarationCollector, FirClassifierSymbol firClassifierSymbol, Map map, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        collect(firDeclarationCollector, firConstructorSymbol, FirRedeclarationPresenter.INSTANCE.represent(firConstructorSymbol, (FirClassLikeSymbol) firClassifierSymbol), map);
        return Unit.INSTANCE;
    }

    public static final FirDeclarationCollector<FirFunctionSymbol<?>> collectConflictingLocalFunctionsFrom(CheckerContext checkerContext, FirBlock firBlock) {
        Pair<FirRegularClassSymbol, FirScope> pairExpandedClassWithConstructorsScope;
        checkerContext.getClass();
        firBlock.getClass();
        List<FirStatement> statements = firBlock.getStatements();
        ArrayList<FirStatement> arrayList = new ArrayList();
        for (Object obj : statements) {
            FirAnnotationContainer firAnnotationContainer = (FirStatement) obj;
            if ((firAnnotationContainer instanceof FirNamedFunction) || (firAnnotationContainer instanceof FirRegularClass)) {
                if (isCollectable(((FirDeclaration) firAnnotationContainer).getSymbol())) {
                    arrayList.add(obj);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        final FirDeclarationCollector<FirFunctionSymbol<?>> firDeclarationCollector = new FirDeclarationCollector<>(checkerContext);
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (final FirStatement firStatement : arrayList) {
            if (firStatement instanceof FirNamedFunction) {
                FirNamedFunction firNamedFunction = (FirNamedFunction) firStatement;
                collect(firDeclarationCollector, firNamedFunction.getSymbol(), FirRedeclarationPresenter.INSTANCE.represent(firNamedFunction.getSymbol()), linkedHashMap);
            } else if ((firStatement instanceof FirClassLikeDeclaration) && (pairExpandedClassWithConstructorsScope = expandedClassWithConstructorsScope(checkerContext, ((FirClassLikeDeclaration) firStatement).getSymbol())) != null) {
                ((FirScope) pairExpandedClassWithConstructorsScope.component2()).processDeclaredConstructors(new Function1() { // from class: tz4
                    public final Object invoke(Object obj2) {
                        return FirConflictsHelpersKt.collectConflictingLocalFunctionsFrom$lambda$1$0(firDeclarationCollector, firStatement, linkedHashMap, (FirConstructorSymbol) obj2);
                    }
                });
            }
        }
        return firDeclarationCollector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit collectConflictingLocalFunctionsFrom$lambda$1$0(FirDeclarationCollector firDeclarationCollector, FirStatement firStatement, Map map, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        collect(firDeclarationCollector, firConstructorSymbol, FirRedeclarationPresenter.INSTANCE.represent(firConstructorSymbol, ((FirClassLikeDeclaration) firStatement).getSymbol()), map);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final void collectTopLevel(final FirDeclarationCollector<FirBasedSymbol<?>> firDeclarationCollector, final FirFile firFile, FirPackageMemberScope firPackageMemberScope) throws KotlinIllegalArgumentExceptionWithAttachments {
        Collection<FirNameConflictsTracker.ClassifierRedeclaration> classifierRedeclarations;
        firDeclarationCollector.getClass();
        firFile.getClass();
        firPackageMemberScope.getClass();
        CheckerContext context = firDeclarationCollector.getContext();
        for (Map.Entry<Name, DeclarationBuckets> entry : groupTopLevelByName(context, firFile.getDeclarations()).entrySet()) {
            final Name key = entry.getKey();
            final DeclarationBuckets value = entry.getValue();
            boolean z = (value.getClassLikes().isEmpty() && value.getProperties().isEmpty()) ? false : true;
            boolean zIsEmpty = value.getSimpleFunctions().isEmpty();
            final boolean z2 = !zIsEmpty;
            if (!zIsEmpty || !value.getConstructors().isEmpty()) {
                final CheckerContext checkerContext = context;
                context = checkerContext;
                firPackageMemberScope.processFunctionsByName(key, new Function1() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.f
                    public final Object invoke(Object obj) {
                        return FirConflictsHelpersKt.collectTopLevel$lambda$0$1(value, firDeclarationCollector, firFile, checkerContext, key, (FirNamedFunctionSymbol) obj);
                    }
                });
            }
            if (z || !zIsEmpty) {
                final CheckerContext checkerContext2 = context;
                Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> function2 = new Function2() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.g
                    public final Object invoke(Object obj, Object obj2) {
                        return FirConflictsHelpersKt.collectTopLevel$lambda$0$2(value, z2, checkerContext2, firDeclarationCollector, firFile, key, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2);
                    }
                };
                boolean z3 = z2;
                context = checkerContext2;
                key = key;
                firPackageMemberScope.processClassifiersByNameWithSubstitution(key, function2);
                FirNameConflictsTracker nameConflictsTracker = FirNameConflictsTrackerKt.getNameConflictsTracker(context.getSession());
                if (nameConflictsTracker != null && (classifierRedeclarations = nameConflictsTracker.getClassifierRedeclarations(new ClassId(UtilsKt.getPackageFqName(firFile), key))) != null) {
                    for (FirNameConflictsTracker.ClassifierRedeclaration classifierRedeclaration : classifierRedeclarations) {
                        FirClassLikeSymbol<?> classifierSymbol = classifierRedeclaration.getClassifierSymbol();
                        FirFile containingFile = classifierRedeclaration.getContainingFile();
                        DeclarationBuckets declarationBuckets = value;
                        boolean z4 = z3;
                        collectTopLevel$lambda$0$collectFromClassifierSource$default(declarationBuckets, z4, context, firDeclarationCollector, firFile, key, classifierSymbol, null, containingFile, 128, null);
                        value = declarationBuckets;
                        z3 = z4;
                    }
                }
                for (Pair<FirClassLikeSymbol<?>, String> pair : value.getClassLikes()) {
                    FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) pair.component1();
                    String str = (String) pair.component2();
                    DeclarationBuckets declarationBuckets2 = value;
                    boolean z5 = z3;
                    collectTopLevel$lambda$0$collectFromClassifierSource(declarationBuckets2, z5, context, firDeclarationCollector, firFile, key, firClassLikeSymbol, str, firFile);
                    value = declarationBuckets2;
                    z3 = z5;
                }
            }
            if (z || !value.getExtensionProperties().isEmpty()) {
                final CheckerContext checkerContext3 = context;
                context = checkerContext3;
                firPackageMemberScope.processPropertiesByName(key, new Function1() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.d
                    public final Object invoke(Object obj) {
                        return FirConflictsHelpersKt.collectTopLevel$lambda$0$4(value, firDeclarationCollector, firFile, checkerContext3, key, (FirVariableSymbol) obj);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final Unit collectTopLevel$lambda$0$1(DeclarationBuckets declarationBuckets, FirDeclarationCollector firDeclarationCollector, FirFile firFile, CheckerContext checkerContext, Name name, FirNamedFunctionSymbol firNamedFunctionSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        firNamedFunctionSymbol.getClass();
        collectTopLevel$lambda$0$collect$default(firDeclarationCollector, firFile, checkerContext, name, declarationBuckets.getSimpleFunctions(), firNamedFunctionSymbol, null, null, 192, null);
        collectTopLevel$lambda$0$collect$default(firDeclarationCollector, firFile, checkerContext, name, declarationBuckets.getConstructors(), firNamedFunctionSymbol, null, null, 192, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final Unit collectTopLevel$lambda$0$2(DeclarationBuckets declarationBuckets, boolean z, CheckerContext checkerContext, FirDeclarationCollector firDeclarationCollector, FirFile firFile, Name name, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor) throws KotlinIllegalArgumentExceptionWithAttachments {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        collectTopLevel$lambda$0$collectFromClassifierSource$default(declarationBuckets, z, checkerContext, firDeclarationCollector, firFile, name, firClassifierSymbol, null, null, 384, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final Unit collectTopLevel$lambda$0$4(DeclarationBuckets declarationBuckets, FirDeclarationCollector firDeclarationCollector, FirFile firFile, CheckerContext checkerContext, Name name, FirVariableSymbol firVariableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        firVariableSymbol.getClass();
        collectTopLevel$lambda$0$collect$default(firDeclarationCollector, firFile, checkerContext, name, declarationBuckets.getClassLikes(), firVariableSymbol, null, null, 192, null);
        collectTopLevel$lambda$0$collect$default(firDeclarationCollector, firFile, checkerContext, name, declarationBuckets.getProperties(), firVariableSymbol, null, null, 192, null);
        collectTopLevel$lambda$0$collect$default(firDeclarationCollector, firFile, checkerContext, name, declarationBuckets.getExtensionProperties(), firVariableSymbol, null, null, 192, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final void collectTopLevel$lambda$0$collect(FirDeclarationCollector<FirBasedSymbol<?>> firDeclarationCollector, FirFile firFile, CheckerContext checkerContext, Name name, List<? extends Pair<? extends FirBasedSymbol<?>, String>> list, FirBasedSymbol<?> firBasedSymbol, String str, FirFile firFile2) throws KotlinIllegalArgumentExceptionWithAttachments {
        for (Pair<? extends FirBasedSymbol<?>, String> pair : list) {
            FirBasedSymbol firBasedSymbol2 = (FirBasedSymbol) pair.component1();
            collectTopLevelConflict(firDeclarationCollector, firBasedSymbol2, (String) pair.component2(), firFile, firBasedSymbol, str, firFile2);
            FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(checkerContext.getSession());
            if (lookupTracker != null) {
                FirLookupTrackerComponentKt.recordNameLookup(lookupTracker, name, UtilsKt.getPackageFqName(firFile).asString(), firBasedSymbol2.getSource(), firFile.getSource());
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ void collectTopLevel$lambda$0$collect$default(FirDeclarationCollector firDeclarationCollector, FirFile firFile, CheckerContext checkerContext, Name name, List list, FirBasedSymbol firBasedSymbol, String str, FirFile firFile2, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 64) != 0) {
            str = null;
        }
        if ((i & 128) != 0) {
            firFile2 = null;
        }
        collectTopLevel$lambda$0$collect(firDeclarationCollector, firFile, checkerContext, name, list, firBasedSymbol, str, firFile2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final void collectTopLevel$lambda$0$collectFromClassifierSource(final DeclarationBuckets declarationBuckets, boolean z, final CheckerContext checkerContext, final FirDeclarationCollector<FirBasedSymbol<?>> firDeclarationCollector, final FirFile firFile, final Name name, final FirClassifierSymbol<?> firClassifierSymbol, String str, FirFile firFile2) throws KotlinIllegalArgumentExceptionWithAttachments {
        Pair<FirRegularClassSymbol, FirScope> pairExpandedClassWithConstructorsScope;
        collectTopLevel$lambda$0$collect(firDeclarationCollector, firFile, checkerContext, name, declarationBuckets.getClassLikes(), firClassifierSymbol, str, firFile2);
        collectTopLevel$lambda$0$collect(firDeclarationCollector, firFile, checkerContext, name, declarationBuckets.getProperties(), firClassifierSymbol, str, firFile2);
        if (z && (firClassifierSymbol instanceof FirClassLikeSymbol) && (pairExpandedClassWithConstructorsScope = expandedClassWithConstructorsScope(checkerContext, (FirClassLikeSymbol) firClassifierSymbol)) != null) {
            FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) pairExpandedClassWithConstructorsScope.component1();
            FirScope firScope = (FirScope) pairExpandedClassWithConstructorsScope.component2();
            if (firRegularClassSymbol.getClassKind() == ClassKind.OBJECT || firRegularClassSymbol.getClassKind() == ClassKind.ENUM_ENTRY) {
                return;
            }
            firScope.processDeclaredConstructors(new Function1() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.c
                public final Object invoke(Object obj) {
                    return FirConflictsHelpersKt.collectTopLevel$lambda$0$collectFromClassifierSource$0$0(firClassifierSymbol, declarationBuckets, firDeclarationCollector, firFile, checkerContext, name, (FirConstructorSymbol) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final Unit collectTopLevel$lambda$0$collectFromClassifierSource$0$0(FirClassifierSymbol firClassifierSymbol, DeclarationBuckets declarationBuckets, FirDeclarationCollector firDeclarationCollector, FirFile firFile, CheckerContext checkerContext, Name name, FirConstructorSymbol firConstructorSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        firConstructorSymbol.getClass();
        collectTopLevel$lambda$0$collect$default(firDeclarationCollector, firFile, checkerContext, name, declarationBuckets.getSimpleFunctions(), firConstructorSymbol, FirRedeclarationPresenter.INSTANCE.represent(firConstructorSymbol, (FirClassLikeSymbol) firClassifierSymbol), null, 128, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ void collectTopLevel$lambda$0$collectFromClassifierSource$default(DeclarationBuckets declarationBuckets, boolean z, CheckerContext checkerContext, FirDeclarationCollector firDeclarationCollector, FirFile firFile, Name name, FirClassifierSymbol firClassifierSymbol, String str, FirFile firFile2, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 128) != 0) {
            str = null;
        }
        if ((i & 256) != 0) {
            firFile2 = null;
        }
        collectTopLevel$lambda$0$collectFromClassifierSource(declarationBuckets, z, checkerContext, firDeclarationCollector, firFile, name, firClassifierSymbol, str, firFile2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final void collectTopLevelConflict(FirDeclarationCollector<FirBasedSymbol<?>> firDeclarationCollector, FirBasedSymbol<?> firBasedSymbol, String str, FirFile firFile, FirBasedSymbol<?> firBasedSymbol2, String str2, FirFile firFile2) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirFile firCallableContainerFile;
        Map declarationConflictingSymbols;
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firBasedSymbol2, FirResolvePhase.STATUS);
        if (Intrinsics.areEqual(firBasedSymbol2, firBasedSymbol)) {
            return;
        }
        if (Intrinsics.areEqual(firBasedSymbol.getModuleData(), firBasedSymbol2.getModuleData()) || shouldCheckForMultiplatformRedeclaration(firBasedSymbol, firBasedSymbol2)) {
            if (Intrinsics.areEqual(str2 == null ? FirRedeclarationPresenter.INSTANCE.represent(firBasedSymbol2) : str2, str)) {
                if (firFile2 != null) {
                    firCallableContainerFile = firFile2;
                } else if (firBasedSymbol2 instanceof FirClassLikeSymbol) {
                    firCallableContainerFile = FirProviderKt.getFirProvider(firDeclarationCollector.getSession$org_jetbrains_kotlin_checkers()).getFirClassifierContainerFileIfAny((FirClassLikeSymbol<?>) firBasedSymbol2);
                } else {
                    firCallableContainerFile = firBasedSymbol2 instanceof FirCallableSymbol ? FirProviderKt.getFirProvider(firDeclarationCollector.getSession$org_jetbrains_kotlin_checkers()).getFirCallableContainerFile((FirCallableSymbol) firBasedSymbol2) : null;
                }
                if (isCollectable(firBasedSymbol2) && !areCompatibleMainFunctions(firBasedSymbol, firFile, firBasedSymbol2, firCallableContainerFile, firDeclarationCollector.getSession$org_jetbrains_kotlin_checkers())) {
                    FirDeclaration fir = firBasedSymbol2.getFir();
                    if (!(fir instanceof FirCallableDeclaration) || FirVisibilityChecker.isVisible$default(FirVisibilityCheckerKt.getVisibilityChecker(firDeclarationCollector.getSession$org_jetbrains_kotlin_checkers()), (FirMemberDeclaration) fir, firDeclarationCollector.getSession$org_jetbrains_kotlin_checkers(), firFile, CollectionsKt.emptyList(), null, false, null, false, null, 480, null)) {
                        int i = WhenMappings.$EnumSwitchMapping$0[getConflictState(firDeclarationCollector, firBasedSymbol, firBasedSymbol2).ordinal()];
                        if (i == 1) {
                            declarationConflictingSymbols = firDeclarationCollector.getDeclarationConflictingSymbols();
                        } else {
                            if (i != 2) {
                                if (i == 3) {
                                    return;
                                }
                                bu8.a();
                                return;
                            }
                            declarationConflictingSymbols = firDeclarationCollector.getDeclarationShadowedViaContextParameters();
                        }
                        Object objCreate = declarationConflictingSymbols.get(firBasedSymbol);
                        if (objCreate == null) {
                            objCreate = SmartSet.Companion.create();
                            declarationConflictingSymbols.put(firBasedSymbol, objCreate);
                        }
                        ((SmartSet) objCreate).add(firBasedSymbol2);
                    }
                }
            }
        }
    }

    private static final Pair<FirRegularClassSymbol, FirScope> expandedClassWithConstructorsScope(CheckerContext checkerContext, FirClassLikeSymbol<?> firClassLikeSymbol) {
        return ScopeUtilsKt.expandedClassWithConstructorsScope(firClassLikeSymbol, checkerContext.getSession(), checkerContext.getScopeSession(), FirResolvePhase.STATUS);
    }

    private static final ConflictState getConflictState(FirDeclarationCollector<?> firDeclarationCollector, FirBasedSymbol<?> firBasedSymbol, FirBasedSymbol<?> firBasedSymbol2) {
        if ((!isAtLeastOneExpect(firBasedSymbol, firBasedSymbol2) || Intrinsics.areEqual(firBasedSymbol.getModuleData(), firBasedSymbol2.getModuleData())) && FirAnnotationUtilsKt.hasLowPriorityAnnotation(firBasedSymbol.getResolvedAnnotationsWithClassIds()) == FirAnnotationUtilsKt.hasLowPriorityAnnotation(firBasedSymbol2.getResolvedAnnotationsWithClassIds())) {
            if (!(firBasedSymbol instanceof FirCallableSymbol) || !(firBasedSymbol2 instanceof FirCallableSymbol)) {
                return ConflictState.Conflict;
            }
            boolean zIsEffectivelyFinal = DeclarationUtilsKt.isEffectivelyFinal(firBasedSymbol);
            boolean zIsEffectivelyFinal2 = DeclarationUtilsKt.isEffectivelyFinal(firBasedSymbol2);
            if (!zIsEffectivelyFinal || !zIsEffectivelyFinal2 || (!DeprecationUtilsKt.isDeprecationLevelHidden(firBasedSymbol, firDeclarationCollector.getSession$org_jetbrains_kotlin_checkers()) && !DeprecationUtilsKt.isDeprecationLevelHidden(firBasedSymbol2, firDeclarationCollector.getSession$org_jetbrains_kotlin_checkers()))) {
                FirDeclarationOverloadabilityHelper declarationOverloadabilityHelper = FirDeclarationOverloadabilityHelperKt.getDeclarationOverloadabilityHelper(firDeclarationCollector.getSession$org_jetbrains_kotlin_checkers());
                if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(firDeclarationCollector.getSession$org_jetbrains_kotlin_checkers()).supportsFeature(LanguageFeature.ContextParameters)) {
                    return getConflictStateWithContextParameters(declarationOverloadabilityHelper, (FirCallableSymbol) firBasedSymbol, (FirCallableSymbol) firBasedSymbol2);
                }
                return declarationOverloadabilityHelper.isConflicting((FirCallableSymbol) firBasedSymbol, (FirCallableSymbol) firBasedSymbol2) ? ConflictState.Conflict : ConflictState.NoConflict;
            }
            return ConflictState.NoConflict;
        }
        return ConflictState.NoConflict;
    }

    private static final ConflictState getConflictStateWithContextParameters(FirDeclarationOverloadabilityHelper firDeclarationOverloadabilityHelper, FirCallableSymbol<?> firCallableSymbol, FirCallableSymbol<?> firCallableSymbol2) {
        int i = WhenMappings.$EnumSwitchMapping$1[firDeclarationOverloadabilityHelper.getContextParameterShadowing(firCallableSymbol, firCallableSymbol2).ordinal()];
        if (i == 1) {
            return ConflictState.Conflict;
        }
        if (i == 2) {
            return ConflictState.ContextParameterShadowing;
        }
        if (i == 3) {
            return ConflictState.NoConflict;
        }
        bu8.a();
        return null;
    }

    private static final Name getName(FirClassifierSymbol<?> firClassifierSymbol) {
        if (firClassifierSymbol instanceof FirClassLikeSymbol) {
            return ((FirClassLikeSymbol) firClassifierSymbol).getName();
        }
        if (firClassifierSymbol instanceof FirTypeParameterSymbol) {
            return ((FirTypeParameterSymbol) firClassifierSymbol).getName();
        }
        bu8.a();
        return null;
    }

    public static final FirResolvedDeclarationStatus getResolvedStatus(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return ((FirCallableSymbol) firBasedSymbol).getResolvedStatus();
        }
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            return ((FirClassLikeSymbol) firBasedSymbol).getResolvedStatus();
        }
        return null;
    }

    private static final Map<Name, DeclarationBuckets> groupTopLevelByName(CheckerContext checkerContext, List<? extends FirDeclaration> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (final FirDeclaration firDeclaration : list) {
            if (isCollectable(firDeclaration.getSymbol())) {
                if (firDeclaration instanceof FirNamedFunction) {
                    FirNamedFunction firNamedFunction = (FirNamedFunction) firDeclaration;
                    Name name = firNamedFunction.getName();
                    Object declarationBuckets = linkedHashMap.get(name);
                    if (declarationBuckets == null) {
                        declarationBuckets = new DeclarationBuckets();
                        linkedHashMap.put(name, declarationBuckets);
                    }
                    ((DeclarationBuckets) declarationBuckets).getSimpleFunctions().add(TuplesKt.to(firNamedFunction.getSymbol(), FirRedeclarationPresenter.INSTANCE.represent(firNamedFunction.getSymbol())));
                } else if (firDeclaration instanceof FirProperty) {
                    FirProperty firProperty = (FirProperty) firDeclaration;
                    Name name2 = firProperty.getName();
                    Object declarationBuckets2 = linkedHashMap.get(name2);
                    if (declarationBuckets2 == null) {
                        declarationBuckets2 = new DeclarationBuckets();
                        linkedHashMap.put(name2, declarationBuckets2);
                    }
                    DeclarationBuckets declarationBuckets3 = (DeclarationBuckets) declarationBuckets2;
                    String strRepresent = FirRedeclarationPresenter.INSTANCE.represent((FirVariableSymbol<?>) firProperty.getSymbol());
                    if (firProperty.getReceiverParameter() != null) {
                        declarationBuckets3.getExtensionProperties().add(TuplesKt.to(firProperty.getSymbol(), strRepresent));
                    } else {
                        declarationBuckets3.getProperties().add(TuplesKt.to(firProperty.getSymbol(), strRepresent));
                    }
                } else if (firDeclaration instanceof FirClassLikeDeclaration) {
                    FirClassLikeDeclaration firClassLikeDeclaration = (FirClassLikeDeclaration) firDeclaration;
                    String strRepresent2 = FirRedeclarationPresenter.INSTANCE.represent(firClassLikeDeclaration.getSymbol());
                    if (strRepresent2 != null) {
                        Name nameOrSpecialName = FirDeclarationUtilKt.getNameOrSpecialName((FirMemberDeclaration) firDeclaration);
                        Object declarationBuckets4 = linkedHashMap.get(nameOrSpecialName);
                        if (declarationBuckets4 == null) {
                            declarationBuckets4 = new DeclarationBuckets();
                            linkedHashMap.put(nameOrSpecialName, declarationBuckets4);
                        }
                        final DeclarationBuckets declarationBuckets5 = (DeclarationBuckets) declarationBuckets4;
                        declarationBuckets5.getClassLikes().add(TuplesKt.to(firClassLikeDeclaration.getSymbol(), strRepresent2));
                        Pair<FirRegularClassSymbol, FirScope> pairExpandedClassWithConstructorsScope = expandedClassWithConstructorsScope(checkerContext, firClassLikeDeclaration.getSymbol());
                        if (pairExpandedClassWithConstructorsScope != null) {
                            FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) pairExpandedClassWithConstructorsScope.component1();
                            FirScope firScope = (FirScope) pairExpandedClassWithConstructorsScope.component2();
                            if (firRegularClassSymbol.getClassKind() != ClassKind.OBJECT) {
                                firScope.processDeclaredConstructors(new Function1() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.e
                                    public final Object invoke(Object obj) {
                                        return FirConflictsHelpersKt.groupTopLevelByName$lambda$3$0(declarationBuckets5, firDeclaration, (FirConstructorSymbol) obj);
                                    }
                                });
                            }
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit groupTopLevelByName$lambda$3$0(DeclarationBuckets declarationBuckets, FirDeclaration firDeclaration, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        declarationBuckets.getConstructors().add(TuplesKt.to(firConstructorSymbol, FirRedeclarationPresenter.INSTANCE.represent(firConstructorSymbol, ((FirClassLikeDeclaration) firDeclaration).getSymbol())));
        return Unit.INSTANCE;
    }

    private static final boolean isAllowedForMainFunction(FirResolvedDeclarationStatus firResolvedDeclarationStatus) {
        FirResolvedDeclarationStatus default_status_for_statusless_declarations = FirResolvedDeclarationStatusImpl.INSTANCE.getDEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS();
        return (Intrinsics.areEqual(firResolvedDeclarationStatus.getVisibility(), default_status_for_statusless_declarations.getVisibility()) || firResolvedDeclarationStatus.getModality() == default_status_for_statusless_declarations.getModality() || Intrinsics.areEqual(firResolvedDeclarationStatus.getEffectiveVisibility(), default_status_for_statusless_declarations.getEffectiveVisibility())) && FirDeclarationStatusImplKt.getModifiersRepresentation(UtilsKt.copy(firResolvedDeclarationStatus, (8388575 & 1) != 0 ? firResolvedDeclarationStatus.getVisibility() : null, (8388575 & 2) != 0 ? firResolvedDeclarationStatus.getModality() : null, (8388575 & 4) != 0 ? firResolvedDeclarationStatus.isExpect() : false, (8388575 & 8) != 0 ? firResolvedDeclarationStatus.isActual() : false, (8388575 & 16) != 0 ? firResolvedDeclarationStatus.isOverride() : false, (8388575 & 32) != 0 ? firResolvedDeclarationStatus.isOperator() : false, (8388575 & 64) != 0 ? firResolvedDeclarationStatus.isInfix() : false, (8388575 & 128) != 0 ? firResolvedDeclarationStatus.isInline() : false, (8388575 & 256) != 0 ? firResolvedDeclarationStatus.isValue() : false, (8388575 & 512) != 0 ? firResolvedDeclarationStatus.isTailRec() : false, (8388575 & 1024) != 0 ? firResolvedDeclarationStatus.isExternal() : false, (8388575 & 2048) != 0 ? firResolvedDeclarationStatus.isConst() : false, (8388575 & 4096) != 0 ? firResolvedDeclarationStatus.isLateInit() : false, (8388575 & 8192) != 0 ? firResolvedDeclarationStatus.isInner() : false, (8388575 & 16384) != 0 ? firResolvedDeclarationStatus.isCompanion() : false, (8388575 & 32768) != 0 ? firResolvedDeclarationStatus.isData() : false, (8388575 & 65536) != 0 ? firResolvedDeclarationStatus.isSuspend() : false, (8388575 & 131072) != 0 ? firResolvedDeclarationStatus.isStatic() : false, (8388575 & 262144) != 0 ? firResolvedDeclarationStatus.isFromSealedClass() : false, (8388575 & 524288) != 0 ? firResolvedDeclarationStatus.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? firResolvedDeclarationStatus.isFun() : false, (8388575 & 2097152) != 0 ? firResolvedDeclarationStatus.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? firResolvedDeclarationStatus.getReturnValueStatus() : ReturnValueStatus.Unspecified)) == FirDeclarationStatusImplKt.getModifiersRepresentation(default_status_for_statusless_declarations);
    }

    private static final boolean isAtLeastOneExpect(FirBasedSymbol<?> firBasedSymbol, FirBasedSymbol<?> firBasedSymbol2) {
        FirResolvedDeclarationStatus resolvedStatus;
        FirResolvedDeclarationStatus resolvedStatus2 = getResolvedStatus(firBasedSymbol);
        return (resolvedStatus2 != null && resolvedStatus2.isExpect()) || ((resolvedStatus = getResolvedStatus(firBasedSymbol2)) != null && resolvedStatus.isExpect());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean isCollectable(FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirCallableSymbol) {
            if (firBasedSymbol instanceof FirErrorCallableSymbol) {
                return false;
            }
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) firBasedSymbol;
            List<FirValueParameterSymbol> contextParameterSymbols = firCallableSymbol.getContextParameterSymbols();
            if (!(contextParameterSymbols instanceof Collection) || !contextParameterSymbols.isEmpty()) {
                Iterator<T> it = contextParameterSymbols.iterator();
                while (it.hasNext()) {
                    if (ConeTypeUtilsKt.hasError(((FirValueParameterSymbol) it.next()).getResolvedReturnType())) {
                        return false;
                    }
                }
            }
            List<FirTypeParameterSymbol> typeParameterSymbols = firCallableSymbol.getTypeParameterSymbols();
            if (!(typeParameterSymbols instanceof Collection) || !typeParameterSymbols.isEmpty()) {
                Iterator<T> it2 = typeParameterSymbols.iterator();
                while (it2.hasNext()) {
                    if (ConeTypeUtilsKt.hasError(FirNestedClassifierScopeKt.toConeType((FirTypeParameterSymbol) it2.next()))) {
                        return false;
                    }
                }
            }
            ConeKotlinType resolvedReceiverType = firCallableSymbol.getResolvedReceiverType();
            if (resolvedReceiverType != null && ConeTypeUtilsKt.hasError(resolvedReceiverType)) {
                return false;
            }
            if (firBasedSymbol instanceof FirFunctionSymbol) {
                List<FirValueParameterSymbol> valueParameterSymbols = ((FirFunctionSymbol) firBasedSymbol).getValueParameterSymbols();
                if (!(valueParameterSymbols instanceof Collection) || !valueParameterSymbols.isEmpty()) {
                    Iterator<T> it3 = valueParameterSymbols.iterator();
                    while (it3.hasNext()) {
                        if (ConeTypeUtilsKt.hasError(((FirValueParameterSymbol) it3.next()).getResolvedReturnType())) {
                            return false;
                        }
                    }
                }
            }
            if (Intrinsics.areEqual(DeprecationUtilsKt.isHiddenToOvercomeSignatureClash((FirCallableDeclaration) firCallableSymbol.getFir()), Boolean.TRUE)) {
                return false;
            }
        }
        if (firBasedSymbol instanceof FirNamedFunctionSymbol) {
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firBasedSymbol;
            return isCollectableAccordingToSource(firNamedFunctionSymbol) && !Intrinsics.areEqual(firNamedFunctionSymbol.getName(), SpecialNames.NO_NAME_PROVIDED);
        }
        if (firBasedSymbol instanceof FirRegularClassSymbol) {
            return !Intrinsics.areEqual(((FirRegularClassSymbol) firBasedSymbol).getName(), SpecialNames.NO_NAME_PROVIDED);
        }
        if (firBasedSymbol instanceof FirPropertySymbol) {
            KtSourceElement source = ((FirPropertySymbol) firBasedSymbol).getSource();
            return !((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.EnumGeneratedDeclaration);
        }
        if (!(firBasedSymbol instanceof FirFieldSymbol)) {
            return true;
        }
        KtSourceElement source2 = ((FirFieldSymbol) firBasedSymbol).getSource();
        return !Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ClassDelegationField.INSTANCE);
    }

    private static final boolean isCollectableAccordingToSource(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        KtSourceElement source = firNamedFunctionSymbol.getSource();
        if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind)) {
            return true;
        }
        KtSourceElement source2 = firNamedFunctionSymbol.getSource();
        return Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE);
    }

    private static final boolean isTopLevel(CallableId callableId) {
        return callableId.getClassName() == null;
    }

    private static final boolean representsMainFunctionAllowingConflictingOverloads(FirNamedFunctionSymbol firNamedFunctionSymbol, FirSession firSession) {
        FirResolvedTypeRef resolvedReturnTypeRef;
        ConeKotlinType coneType;
        ConeKotlinType coneKotlinTypeFullyExpandedType$default;
        if (!Intrinsics.areEqual(firNamedFunctionSymbol.getName(), StandardNames.MAIN) || !isTopLevel(firNamedFunctionSymbol.getCallableId()) || !isAllowedForMainFunction(firNamedFunctionSymbol.getResolvedStatus()) || firNamedFunctionSymbol.getReceiverParameterSymbol() != null || !firNamedFunctionSymbol.getTypeParameterSymbols().isEmpty() || FirCallableSymbolKt.getHasContextParameters(firNamedFunctionSymbol) || !ConeBuiltinTypeUtilsKt.isUnit(TypeExpansionUtilsKt.fullyExpandedType$default(firNamedFunctionSymbol.getResolvedReturnType(), firSession, (Function1) null, 2, (Object) null))) {
            return false;
        }
        if (firNamedFunctionSymbol.getValueParameterSymbols().isEmpty()) {
            return true;
        }
        FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) CollectionsKt.singleOrNull(firNamedFunctionSymbol.getValueParameterSymbols());
        if (firValueParameterSymbol == null || (resolvedReturnTypeRef = firValueParameterSymbol.getResolvedReturnTypeRef()) == null || (coneType = resolvedReturnTypeRef.getConeType()) == null || (coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneType, firSession, (Function1) null, 2, (Object) null)) == null || !ConeBuiltinTypeUtilsKt.isNonPrimitiveArray(coneKotlinTypeFullyExpandedType$default)) {
            return false;
        }
        Object objSingleOrNull = ArraysKt.singleOrNull(coneKotlinTypeFullyExpandedType$default.getTypeArguments());
        ConeKotlinTypeProjection coneKotlinTypeProjection = objSingleOrNull instanceof ConeKotlinTypeProjection ? (ConeKotlinTypeProjection) objSingleOrNull : null;
        if (coneKotlinTypeProjection == null) {
            return false;
        }
        if ((coneKotlinTypeProjection instanceof ConeKotlinType) || (coneKotlinTypeProjection instanceof ConeKotlinTypeProjectionOut)) {
            return ConeBuiltinTypeUtilsKt.isString(TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinTypeProjection.getType(), firSession, (Function1) null, 2, (Object) null));
        }
        return false;
    }

    private static final boolean shouldCheckForMultiplatformRedeclaration(FirBasedSymbol<?> firBasedSymbol, FirBasedSymbol<?> firBasedSymbol2) {
        if (firBasedSymbol2.getModuleData().getAllDependsOnDependencies().contains(firBasedSymbol.getModuleData())) {
            return !isAtLeastOneExpect(firBasedSymbol, firBasedSymbol2);
        }
        return false;
    }
}

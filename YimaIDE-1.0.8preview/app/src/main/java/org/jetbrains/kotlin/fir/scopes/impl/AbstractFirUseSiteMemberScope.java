package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirOverrideCheckerKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.impl.AbstractFirUseSiteMemberScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\"\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u001e2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020;0>J\u0016\u0010?\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010<\u001a\u00020\u001eH\u0014J\u001e\u0010@\u001a\u00020;2\u0006\u0010<\u001a\u00020\u001e2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020 0BH\u0004J\f\u0010C\u001a\u00020D*\u00020 H$J\u0010\u0010E\u001a\u00020D*\u0006\u0012\u0002\b\u00030FH\u0002J,\u0010G\u001a\u00020;2\u0006\u0010<\u001a\u00020\u001e2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020 0B2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020 02H\u0004JB\u0010I\u001a\u00020;\"\f\b\u0000\u0010J*\u0006\u0012\u0002\b\u00030F*\b\u0012\u0004\u0012\u0002HJ0$2\u0010\u0010K\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030F022\u000e\u0010A\u001a\n\u0012\u0006\b\u0000\u0012\u0002HJ0BH\u0004J\u001c\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0$0\n2\u0006\u0010<\u001a\u00020\u001eH\u0002J&\u0010M\u001a\u00020;2\u0006\u0010<\u001a\u00020\u001e2\u0016\u0010=\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"\u0012\u0004\u0012\u00020;0>J\u001a\u0010N\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"0\u001f2\u0006\u0010<\u001a\u00020\u001eH$J\u001c\u0010O\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0$0\n2\u0006\u0010P\u001a\u00020 H\u0002J\"\u0010Q\u001a\u00020D2\u0006\u0010R\u001a\u00020 2\u0006\u0010S\u001a\u00020 2\b\u0010T\u001a\u0004\u0018\u00010\u000bH\u0014J\u0094\u0001\u0010U\u001a\u00020;\"\f\b\u0000\u0010J*\u0006\u0012\u0002\b\u00030F*\b\u0012\u0004\u0012\u0002HJ0$2\u0006\u0010V\u001a\u0002HJ2\u0014\u0010W\u001a\u0010\u0012\f\b\u0000\u0012\b\u0012\u0004\u0012\u0002HJ0$0B2K\u0010X\u001aG\u0012\u0013\u0012\u0011HJ¢\u0006\f\bZ\u0012\b\b<\u0012\u0004\b\b([\u0012\u0013\u0012\u0011HJ¢\u0006\f\bZ\u0012\b\b<\u0012\u0004\b\b(S\u0012\u0013\u0012\u00110\u000b¢\u0006\f\bZ\u0012\b\b<\u0012\u0004\b\b(T\u0012\u0004\u0012\u00020D0YH\u0084\bø\u0001\u0000¢\u0006\u0002\u0010\\J*\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020 2\u0018\u0010=\u001a\u0014\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020^0`H\u0016J*\u0010a\u001a\u00020^2\u0006\u0010b\u001a\u00020(2\u0018\u0010=\u001a\u0014\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020^0`H\u0016J}\u0010c\u001a\u00020^\"\f\b\u0000\u0010d*\u0006\u0012\u0002\b\u00030F2\u001e\u0010e\u001a\u001a\u0012\u0004\u0012\u0002Hd\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0$0\n0f2\u001e\u0010g\u001a\u001a\u0012\u0004\u0012\u00020\u001e\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hd0$0\n0f2\u0006\u0010h\u001a\u0002Hd2\u0018\u0010=\u001a\u0014\u0012\u0004\u0012\u0002Hd\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020^0`H\u0002¢\u0006\u0002\u0010iJ.\u0010j\u001a\u00020;2\u0006\u0010<\u001a\u00020\u001e2\u001c\u0010=\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030k\u0012\u0004\u0012\u00020l\u0012\u0004\u0012\u00020;0`H\u0016J\u001c\u0010m\u001a\u00020;2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020n\u0012\u0004\u0012\u00020;0>H\u0016J\u000e\u0010o\u001a\b\u0012\u0004\u0012\u00020\u001e02H\u0016J\u000e\u0010p\u001a\b\u0012\u0004\u0012\u00020\u001e02H\u0016J\f\u0010q\u001a\u00020 *\u00020 H\u0014J\u001c\u0010r\u001a\u00020\u00002\u0006\u0010s\u001a\u00020\u00052\u0006\u0010t\u001a\u00020uH'b\u0002\bvR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u000e\u001a\u00020\u000fX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u0019X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR \u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001f0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010!\u001a\u0018\u0012\u0004\u0012\u00020\u001e\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\"0\u001f0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010#\u001a\u001a\u0012\u0004\u0012\u00020 \u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0$0\n0\u001dX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R,\u0010'\u001a\u001a\u0012\u0004\u0012\u00020(\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0$0\n0\u001dX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010&R,\u0010*\u001a\u001a\u0012\u0004\u0012\u00020\u001e\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0$0\n0\u001dX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010&R,\u0010,\u001a\u001a\u0012\u0004\u0012\u00020\u001e\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0$0\n0\u001dX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010&R&\u0010.\u001a\u0014\u0012\u0004\u0012\u00020\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\n0\u001dX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010&R!\u00101\u001a\b\u0012\u0004\u0012\u00020\u001e028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b3\u00104R!\u00107\u001a\b\u0012\u0004\u0012\u00020\u001e028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b9\u00106\u001a\u0004\b8\u00104\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006w"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/AbstractFirUseSiteMemberScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/AbstractFirOverrideScope;", "ownerClassLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "overrideCheckerForBaseClass", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "overrideCheckerForIntersection", "superTypeScopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "declaredMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;)V", "getOwnerClassLookupTag", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "getSuperTypeScopes", "()Ljava/util/List;", "getDeclaredMemberScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "supertypeScopeContext", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext;", "getSupertypeScopeContext", "()Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext;", "functions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "properties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "directOverriddenFunctions", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;", "getDirectOverriddenFunctions", "()Ljava/util/Map;", "directOverriddenProperties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getDirectOverriddenProperties", "functionsFromSupertypes", "getFunctionsFromSupertypes", "propertiesFromSupertypes", "getPropertiesFromSupertypes", "fieldsFromSupertypes", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "getFieldsFromSupertypes", "callableNamesCached", Argument.Delimiters.none, "getCallableNamesCached", "()Ljava/util/Set;", "callableNamesCached$delegate", "Lkotlin/Lazy;", "classifierNamesCached", "getClassifierNamesCached", "classifierNamesCached$delegate", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function1;", "collectFunctions", "collectDeclaredFunctions", "destination", Argument.Delimiters.none, "isVisibleInCurrentClass", Argument.Delimiters.none, "isInvisible", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "collectFunctionsFromSupertypes", "explicitlyDeclaredFunctions", "collectNonOverriddenDeclarations", "T", "explicitlyDeclared", "getFunctionsFromSupertypesByName", "processPropertiesByName", "collectProperties", "computeDirectOverriddenForDeclaredFunction", "declaredFunctionSymbol", "isOverriddenFunction", "overrideCandidate", "baseDeclaration", "baseScope", "collectDirectOverriddenForDeclared", "declared", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "isOverridden", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "declaredSymbol", "(Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Ljava/util/List;Lkotlin/jvm/functions/Function3;)V", "processDirectOverriddenFunctionsWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "functionSymbol", "Lkotlin/Function2;", "processDirectOverriddenPropertiesWithBaseScope", "propertySymbol", "processDirectOverriddenMembersWithBaseScopeImpl", "D", "directOverriddenMap", Argument.Delimiters.none, "callablesFromSupertypes", "callableSymbol", "(Ljava/util/Map;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "processClassifiersByNameWithSubstitution", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "getCallableNames", "getClassifierNames", "replaceWithWrapperSymbolIfNeeded", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFirUseSiteMemberScope extends AbstractFirOverrideScope {

    /* JADX INFO: renamed from: callableNamesCached$delegate, reason: from kotlin metadata */
    private final Lazy callableNamesCached;

    /* JADX INFO: renamed from: classifierNamesCached$delegate, reason: from kotlin metadata */
    private final Lazy classifierNamesCached;
    private final FirContainingNamesAwareScope declaredMemberScope;
    private final Map<FirNamedFunctionSymbol, List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>>> directOverriddenFunctions;
    private final Map<FirPropertySymbol, List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>>> directOverriddenProperties;
    private final Map<Name, List<FirFieldSymbol>> fieldsFromSupertypes;
    private final Map<Name, Collection<FirNamedFunctionSymbol>> functions;
    private final Map<Name, List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>>> functionsFromSupertypes;
    private final ConeClassLikeLookupTag ownerClassLookupTag;
    private final Map<Name, Collection<FirVariableSymbol<?>>> properties;
    private final Map<Name, List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>>> propertiesFromSupertypes;
    private final List<FirTypeScope> superTypeScopes;
    private final FirTypeIntersectionScopeContext supertypeScopeContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AbstractFirUseSiteMemberScope(ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession, FirOverrideChecker firOverrideChecker, FirOverrideChecker firOverrideChecker2, List<? extends FirTypeScope> list, ConeSimpleKotlinType coneSimpleKotlinType, FirContainingNamesAwareScope firContainingNamesAwareScope) {
        super(firSession, firOverrideChecker);
        coneClassLikeLookupTag.getClass();
        firSession.getClass();
        firOverrideChecker.getClass();
        list.getClass();
        coneSimpleKotlinType.getClass();
        firContainingNamesAwareScope.getClass();
        this.ownerClassLookupTag = coneClassLikeLookupTag;
        this.superTypeScopes = list;
        this.declaredMemberScope = firContainingNamesAwareScope;
        this.supertypeScopeContext = new FirTypeIntersectionScopeContext(firSession, firOverrideChecker2 != null ? firOverrideChecker2 : firOverrideChecker, list, coneSimpleKotlinType, true);
        this.functions = new HashMap();
        this.properties = new HashMap();
        this.directOverriddenFunctions = new HashMap();
        this.directOverriddenProperties = new HashMap();
        this.functionsFromSupertypes = new HashMap();
        this.propertiesFromSupertypes = new HashMap();
        this.fieldsFromSupertypes = new HashMap();
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.callableNamesCached = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: yn
            public final Object invoke() {
                return AbstractFirUseSiteMemberScope.e(this.b);
            }
        });
        this.classifierNamesCached = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: zn
            public final Object invoke() {
                return AbstractFirUseSiteMemberScope.d(this.b);
            }
        });
    }

    public static Unit b(AbstractFirUseSiteMemberScope abstractFirUseSiteMemberScope, List list, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (!firNamedFunctionSymbol.getRawStatus().isStatic() && abstractFirUseSiteMemberScope.isVisibleInCurrentClass(firNamedFunctionSymbol)) {
            abstractFirUseSiteMemberScope.directOverriddenFunctions.put(firNamedFunctionSymbol, abstractFirUseSiteMemberScope.computeDirectOverriddenForDeclaredFunction(firNamedFunctionSymbol));
            list.add(abstractFirUseSiteMemberScope.replaceWithWrapperSymbolIfNeeded(firNamedFunctionSymbol));
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    public static Unit c(Ref.BooleanRef booleanRef, Function2 function2, FirClassifierSymbol firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
        firClassifierSymbol.getClass();
        coneSubstitutor.getClass();
        booleanRef.element = true;
        function2.invoke(firClassifierSymbol, coneSubstitutor);
        return Unit.INSTANCE;
    }

    private final List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>> computeDirectOverriddenForDeclaredFunction(FirNamedFunctionSymbol declaredFunctionSymbol) {
        ArrayList arrayList = new ArrayList();
        for (FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol> resultOfIntersection : getFunctionsFromSupertypesByName(declaredFunctionSymbol.getName())) {
            if (resultOfIntersection instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember) {
                FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember singleMember = (FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember) resultOfIntersection;
                if (isOverriddenFunction(declaredFunctionSymbol, (FirNamedFunctionSymbol) singleMember.getChosenSymbol(), singleMember.getScopeOfChosenSymbol())) {
                    arrayList.add(resultOfIntersection);
                }
            } else {
                if (!(resultOfIntersection instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial)) {
                    bu8.a();
                    return null;
                }
                Iterable overriddenMembers = ((FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial) resultOfIntersection).getOverriddenMembers();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : overriddenMembers) {
                    MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) obj;
                    if (isOverriddenFunction(declaredFunctionSymbol, (FirNamedFunctionSymbol) memberWithBaseScope.getMember(), memberWithBaseScope.getBaseScope())) {
                        arrayList2.add(obj);
                    } else {
                        arrayList3.add(obj);
                    }
                }
                Pair pair = new Pair(arrayList2, arrayList3);
                List list = (List) pair.component1();
                if (((List) pair.component2()).isEmpty()) {
                    arrayList.add(resultOfIntersection);
                } else if (!list.isEmpty()) {
                    FirTypeIntersectionScopeContext firTypeIntersectionScopeContext = this.supertypeScopeContext;
                    List<MemberWithBaseScope> list2 = list;
                    ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                    for (MemberWithBaseScope memberWithBaseScope2 : list2) {
                        arrayList4.add(TuplesKt.to(memberWithBaseScope2.getBaseScope(), CollectionsKt.listOf(memberWithBaseScope2.getMember())));
                    }
                    CollectionsKt.addAll(arrayList, firTypeIntersectionScopeContext.convertGroupedCallablesToIntersectionResults(arrayList4));
                }
            }
        }
        return arrayList;
    }

    public static Set d(AbstractFirUseSiteMemberScope abstractFirUseSiteMemberScope) {
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        setCreateSetBuilder.addAll(abstractFirUseSiteMemberScope.declaredMemberScope.getClassifierNames());
        Set set = setCreateSetBuilder;
        Iterator<T> it = abstractFirUseSiteMemberScope.superTypeScopes.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(set, ((FirTypeScope) it.next()).getClassifierNames());
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    public static Set e(AbstractFirUseSiteMemberScope abstractFirUseSiteMemberScope) {
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        setCreateSetBuilder.addAll(abstractFirUseSiteMemberScope.declaredMemberScope.getCallableNames());
        Set set = setCreateSetBuilder;
        Iterator<T> it = abstractFirUseSiteMemberScope.superTypeScopes.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(set, ((FirTypeScope) it.next()).getCallableNames());
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    private final Set<Name> getCallableNamesCached() {
        return (Set) this.callableNamesCached.getValue();
    }

    private final Set<Name> getClassifierNamesCached() {
        return (Set) this.classifierNamesCached.getValue();
    }

    private final List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>> getFunctionsFromSupertypesByName(Name name) {
        Map<Name, List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>>> map = this.functionsFromSupertypes;
        List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>> listConvertGroupedCallablesToIntersectionResults = map.get(name);
        if (listConvertGroupedCallablesToIntersectionResults == null) {
            FirTypeIntersectionScopeContext firTypeIntersectionScopeContext = this.supertypeScopeContext;
            List<FirTypeScope> scopes = firTypeIntersectionScopeContext.getScopes();
            ArrayList arrayList = new ArrayList();
            for (FirTypeScope firTypeScope : scopes) {
                ArrayList arrayList2 = new ArrayList();
                firTypeScope.processFunctionsByName(name, new FirTypeIntersectionScopeContext$collectMembersGroupedByScope$1$1(arrayList2));
                if (arrayList2.isEmpty()) {
                    arrayList2 = null;
                }
                Pair pair = arrayList2 != null ? TuplesKt.to(firTypeScope, arrayList2) : null;
                if (pair != null) {
                    arrayList.add(pair);
                }
            }
            listConvertGroupedCallablesToIntersectionResults = firTypeIntersectionScopeContext.convertGroupedCallablesToIntersectionResults(arrayList);
            map.put(name, listConvertGroupedCallablesToIntersectionResults);
        }
        return listConvertGroupedCallablesToIntersectionResults;
    }

    private final boolean isInvisible(FirCallableSymbol<?> firCallableSymbol) {
        return (firCallableSymbol instanceof FirNamedFunctionSymbol) && !isVisibleInCurrentClass((FirNamedFunctionSymbol) firCallableSymbol);
    }

    private final <D extends FirCallableSymbol<?>> ProcessorAction processDirectOverriddenMembersWithBaseScopeImpl(Map<D, ? extends List<? extends FirTypeIntersectionScopeContext.ResultOfIntersection<D>>> directOverriddenMap, Map<Name, ? extends List<? extends FirTypeIntersectionScopeContext.ResultOfIntersection<D>>> callablesFromSupertypes, D callableSymbol, Function2<? super D, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        Object next;
        List<? extends FirTypeIntersectionScopeContext.ResultOfIntersection<D>> list = directOverriddenMap.get(callableSymbol);
        if (list != null) {
            Iterator<? extends FirTypeIntersectionScopeContext.ResultOfIntersection<D>> it = list.iterator();
            while (it.hasNext()) {
                for (MemberWithBaseScope<D> memberWithBaseScope : it.next().getOverriddenMembers()) {
                    if (((ProcessorAction) processor.invoke(memberWithBaseScope.component1(), memberWithBaseScope.getBaseScope())).not()) {
                        return ProcessorAction.STOP;
                    }
                }
            }
            return ProcessorAction.NONE;
        }
        List<? extends FirTypeIntersectionScopeContext.ResultOfIntersection<D>> list2 = callablesFromSupertypes.get(callableSymbol.getName());
        if (list2 != null) {
            Iterator<T> it2 = list2.iterator();
            do {
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
            } while (!Intrinsics.areEqual(((FirTypeIntersectionScopeContext.ResultOfIntersection) next).getChosenSymbol(), callableSymbol));
            FirTypeIntersectionScopeContext.ResultOfIntersection resultOfIntersection = (FirTypeIntersectionScopeContext.ResultOfIntersection) next;
            if (resultOfIntersection != null) {
                for (MemberWithBaseScope<D> memberWithBaseScope2 : resultOfIntersection.getOverriddenMembers()) {
                    if (((ProcessorAction) processor.invoke(memberWithBaseScope2.component1(), memberWithBaseScope2.getBaseScope())).not()) {
                        return ProcessorAction.STOP;
                    }
                }
                return ProcessorAction.NONE;
            }
        }
        return ProcessorAction.NONE;
    }

    public final void collectDeclaredFunctions(Name name, final List<FirNamedFunctionSymbol> destination) {
        name.getClass();
        destination.getClass();
        this.declaredMemberScope.processFunctionsByName(name, new Function1() { // from class: bo
            public final Object invoke(Object obj) {
                return AbstractFirUseSiteMemberScope.b(this.b, destination, (FirNamedFunctionSymbol) obj);
            }
        });
    }

    public final <T extends FirCallableSymbol<?>> void collectDirectOverriddenForDeclared(FirTypeIntersectionScopeContext.ResultOfIntersection<T> resultOfIntersection, T t, List<? super FirTypeIntersectionScopeContext.ResultOfIntersection<T>> list, Function3<? super T, ? super T, ? super FirTypeScope, Boolean> function3) {
        resultOfIntersection.getClass();
        t.getClass();
        list.getClass();
        function3.getClass();
        if (resultOfIntersection instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember) {
            FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember singleMember = (FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember) resultOfIntersection;
            if (((Boolean) function3.invoke(t, singleMember.getChosenSymbol(), singleMember.getScopeOfChosenSymbol())).booleanValue()) {
                list.add(resultOfIntersection);
                return;
            }
            return;
        }
        if (!(resultOfIntersection instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial)) {
            bu8.a();
            return;
        }
        Iterable overriddenMembers = ((FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial) resultOfIntersection).getOverriddenMembers();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : overriddenMembers) {
            MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) obj;
            if (((Boolean) function3.invoke(t, memberWithBaseScope.getMember(), memberWithBaseScope.getBaseScope())).booleanValue()) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list2 = (List) pair.component1();
        if (((List) pair.component2()).isEmpty()) {
            list.add(resultOfIntersection);
            return;
        }
        if (list2.isEmpty()) {
            return;
        }
        List<? super FirTypeIntersectionScopeContext.ResultOfIntersection<T>> list3 = list;
        FirTypeIntersectionScopeContext firTypeIntersectionScopeContext = this.supertypeScopeContext;
        List<MemberWithBaseScope> list4 = list2;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
        for (MemberWithBaseScope memberWithBaseScope2 : list4) {
            arrayList3.add(TuplesKt.to(memberWithBaseScope2.getBaseScope(), CollectionsKt.listOf(memberWithBaseScope2.getMember())));
        }
        CollectionsKt.addAll(list3, firTypeIntersectionScopeContext.convertGroupedCallablesToIntersectionResults(arrayList3));
    }

    public Collection<FirNamedFunctionSymbol> collectFunctions(Name name) {
        name.getClass();
        ArrayList arrayList = new ArrayList();
        collectDeclaredFunctions(name, arrayList);
        collectFunctionsFromSupertypes(name, arrayList, CollectionsKt.toSet(arrayList));
        return arrayList;
    }

    public final void collectFunctionsFromSupertypes(Name name, List<FirNamedFunctionSymbol> destination, Set<? extends FirNamedFunctionSymbol> explicitlyDeclaredFunctions) {
        name.getClass();
        destination.getClass();
        explicitlyDeclaredFunctions.getClass();
        Iterator<FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>> it = getFunctionsFromSupertypesByName(name).iterator();
        while (it.hasNext()) {
            collectNonOverriddenDeclarations(it.next(), explicitlyDeclaredFunctions, destination);
        }
    }

    public final <T extends FirCallableSymbol<?>> void collectNonOverriddenDeclarations(FirTypeIntersectionScopeContext.ResultOfIntersection<T> resultOfIntersection, Set<? extends FirCallableSymbol<?>> set, List<? super T> list) {
        resultOfIntersection.getClass();
        set.getClass();
        list.getClass();
        if (resultOfIntersection instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember) {
            FirCallableSymbol<?> chosenSymbol = ((FirTypeIntersectionScopeContext.ResultOfIntersection.SingleMember) resultOfIntersection).getChosenSymbol();
            if (!isInvisible(chosenSymbol) && getOverridden(chosenSymbol, set) == null) {
                list.add(chosenSymbol);
                return;
            }
            return;
        }
        if (!(resultOfIntersection instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial)) {
            bu8.a();
            return;
        }
        FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial nonTrivial = (FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial) resultOfIntersection;
        Iterable overriddenMembers = nonTrivial.getOverriddenMembers();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : overriddenMembers) {
            MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) obj;
            if (isInvisible(memberWithBaseScope.getMember()) || getOverridden(memberWithBaseScope.getMember(), set) != null) {
                arrayList2.add(obj);
            } else {
                arrayList.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list2 = (List) pair.component1();
        if (((List) pair.component2()).isEmpty()) {
            list.add(nonTrivial.getChosenSymbol());
            return;
        }
        if (list2.isEmpty()) {
            return;
        }
        List<? super T> list3 = list;
        FirTypeIntersectionScopeContext firTypeIntersectionScopeContext = this.supertypeScopeContext;
        List<MemberWithBaseScope> list4 = list2;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
        for (MemberWithBaseScope memberWithBaseScope2 : list4) {
            arrayList3.add(TuplesKt.to(memberWithBaseScope2.getBaseScope(), CollectionsKt.listOf(memberWithBaseScope2.getMember())));
        }
        List listConvertGroupedCallablesToIntersectionResults = firTypeIntersectionScopeContext.convertGroupedCallablesToIntersectionResults(arrayList3);
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listConvertGroupedCallablesToIntersectionResults, 10));
        Iterator it = listConvertGroupedCallablesToIntersectionResults.iterator();
        while (it.hasNext()) {
            arrayList4.add(((FirTypeIntersectionScopeContext.ResultOfIntersection) it.next()).getChosenSymbol());
        }
        CollectionsKt.addAll(list3, arrayList4);
    }

    public abstract Collection<FirVariableSymbol<?>> collectProperties(Name name);

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return getCallableNamesCached();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return getClassifierNamesCached();
    }

    public final FirContainingNamesAwareScope getDeclaredMemberScope() {
        return this.declaredMemberScope;
    }

    public final Map<FirNamedFunctionSymbol, List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>>> getDirectOverriddenFunctions() {
        return this.directOverriddenFunctions;
    }

    public final Map<FirPropertySymbol, List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>>> getDirectOverriddenProperties() {
        return this.directOverriddenProperties;
    }

    public final Map<Name, List<FirFieldSymbol>> getFieldsFromSupertypes() {
        return this.fieldsFromSupertypes;
    }

    public final Map<Name, List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirNamedFunctionSymbol>>> getFunctionsFromSupertypes() {
        return this.functionsFromSupertypes;
    }

    public final ConeClassLikeLookupTag getOwnerClassLookupTag() {
        return this.ownerClassLookupTag;
    }

    public final Map<Name, List<FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>>> getPropertiesFromSupertypes() {
        return this.propertiesFromSupertypes;
    }

    public final List<FirTypeScope> getSuperTypeScopes() {
        return this.superTypeScopes;
    }

    public final FirTypeIntersectionScopeContext getSupertypeScopeContext() {
        return this.supertypeScopeContext;
    }

    public boolean isOverriddenFunction(FirNamedFunctionSymbol overrideCandidate, FirNamedFunctionSymbol baseDeclaration, FirTypeScope baseScope) {
        overrideCandidate.getClass();
        baseDeclaration.getClass();
        return FirOverrideCheckerKt.isOverriddenFunction(getOverrideChecker(), overrideCandidate, baseDeclaration);
    }

    public abstract boolean isVisibleInCurrentClass(FirNamedFunctionSymbol firNamedFunctionSymbol);

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, final Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (getClassifierNames().contains(name)) {
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            this.declaredMemberScope.processClassifiersByNameWithSubstitution(name, new Function2() { // from class: ao
                public final Object invoke(Object obj, Object obj2) {
                    return AbstractFirUseSiteMemberScope.c(booleanRef, processor, (FirClassifierSymbol) obj, (ConeSubstitutor) obj2);
                }
            });
            if (booleanRef.element) {
                return;
            }
            this.supertypeScopeContext.processClassifiersByNameWithSubstitution(name, processor);
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        this.declaredMemberScope.processDeclaredConstructors(processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenFunctionsWithBaseScope(FirNamedFunctionSymbol functionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        functionSymbol.getClass();
        processor.getClass();
        return processDirectOverriddenMembersWithBaseScopeImpl(this.directOverriddenFunctions, this.functionsFromSupertypes, functionSymbol, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenPropertiesWithBaseScope(FirPropertySymbol propertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        propertySymbol.getClass();
        processor.getClass();
        return processDirectOverriddenMembersWithBaseScopeImpl(this.directOverriddenProperties, this.propertiesFromSupertypes, propertySymbol, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public final void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (getCallableNames().contains(name)) {
            Map<Name, Collection<FirNamedFunctionSymbol>> map = this.functions;
            Collection<FirNamedFunctionSymbol> collectionCollectFunctions = map.get(name);
            if (collectionCollectFunctions == null) {
                collectionCollectFunctions = collectFunctions(name);
                map.put(name, collectionCollectFunctions);
            }
            Iterator<T> it = collectionCollectFunctions.iterator();
            while (it.hasNext()) {
                processor.invoke((FirNamedFunctionSymbol) it.next());
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public final void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (getCallableNames().contains(name)) {
            Map<Name, Collection<FirVariableSymbol<?>>> map = this.properties;
            Collection<FirVariableSymbol<?>> collectionCollectProperties = map.get(name);
            if (collectionCollectProperties == null) {
                collectionCollectProperties = collectProperties(name);
                map.put(name, collectionCollectProperties);
            }
            Iterator<T> it = collectionCollectProperties.iterator();
            while (it.hasNext()) {
                processor.invoke((FirVariableSymbol) it.next());
            }
        }
    }

    public FirNamedFunctionSymbol replaceWithWrapperSymbolIfNeeded(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return firNamedFunctionSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public abstract AbstractFirUseSiteMemberScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession);
}

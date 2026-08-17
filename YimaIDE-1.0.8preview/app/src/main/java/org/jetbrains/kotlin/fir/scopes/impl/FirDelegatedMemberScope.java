package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DelegatedWrapperData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirOverrideChecker;
import org.jetbrains.kotlin.fir.scopes.FirOverrideCheckerKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMemberScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0001\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ$\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001a0\u001eH\u0016J\u0012\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020\fH\u0002J&\u0010#\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001f0%H\u0002J(\u0010&\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0016\u0010\u001d\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030'\u0012\u0004\u0012\u00020\u001a0\u001eH\u0016J.\u0010(\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u001c\u0010\u001d\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030*\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u001a0)H\u0016J\u001c\u0010,\u001a\u00020\u001a2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u001a0\u001eH\u0016J&\u0010.\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020/0%H\u0002J\u000e\u00109\u001a\b\u0012\u0004\u0012\u00020\u001c01H\u0016J\u000e\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001c01H\u0016J\u001c\u0010;\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u0006H\u0017b\u0002\b>R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R!\u00100\u001a\b\u0012\u0004\u0012\u00020\u001c018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b2\u00103R!\u00106\u001a\b\u0012\u0004\u0012\u00020\u001c018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u00105\u001a\u0004\b7\u00103¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMemberScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "containingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "declaredMemberScope", "delegateFields", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;Ljava/util/List;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "overrideChecker", "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "delegatedMembersFilter", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter;", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "buildScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "delegateField", "collectFunctionsFromSpecificField", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processClassifiersByNameWithSubstitution", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "processDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "collectPropertiesFromSpecificField", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "callableNamesLazy", Argument.Delimiters.none, "getCallableNamesLazy", "()Ljava/util/Set;", "callableNamesLazy$delegate", "Lkotlin/Lazy;", "classifierNamesLazy", "getClassifierNamesLazy", "classifierNamesLazy$delegate", "getCallableNames", "getClassifierNames", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegatedMemberScope extends FirContainingNamesAwareScope implements SessionAndScopeSessionHolder {

    /* JADX INFO: renamed from: callableNamesLazy$delegate, reason: from kotlin metadata */
    private final Lazy callableNamesLazy;

    /* JADX INFO: renamed from: classifierNamesLazy$delegate, reason: from kotlin metadata */
    private final Lazy classifierNamesLazy;
    private final FirClass containingClass;
    private final FirContainingNamesAwareScope declaredMemberScope;
    private final List<FirField> delegateFields;
    private final FirDelegatedMembersFilter delegatedMembersFilter;
    private final ConeClassLikeType dispatchReceiverType;
    private final FirOverrideChecker overrideChecker;
    private final ScopeSession scopeSession;
    private final FirSession session;

    /* JADX WARN: Multi-variable type inference failed */
    public FirDelegatedMemberScope(FirSession firSession, ScopeSession scopeSession, FirClass firClass, FirContainingNamesAwareScope firContainingNamesAwareScope, List<? extends FirField> list) {
        firSession.getClass();
        scopeSession.getClass();
        firClass.getClass();
        firContainingNamesAwareScope.getClass();
        list.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.containingClass = firClass;
        this.declaredMemberScope = firContainingNamesAwareScope;
        this.delegateFields = list;
        this.dispatchReceiverType = ScopeUtilsKt.defaultType(firClass);
        this.overrideChecker = FirOverrideCheckerKt.getFirOverrideChecker(getSession());
        this.delegatedMembersFilter = FirDelegatedMemberScopeKt.getDelegatedMembersFilter(getSession());
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.callableNamesLazy = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: r25
            public final Object invoke() {
                return FirDelegatedMemberScope.c(this.b);
            }
        });
        this.classifierNamesLazy = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: s25
            public final Object invoke() {
                return FirDelegatedMemberScope.f(this.b);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit b(final FirDelegatedMemberScope firDelegatedMemberScope, Name name, List list, FirField firField, FirVariableSymbol firVariableSymbol) {
        Object next;
        firVariableSymbol.getClass();
        if (!(firVariableSymbol instanceof FirPropertySymbol)) {
            return Unit.INSTANCE;
        }
        FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firVariableSymbol;
        if (firPropertySymbol.getRawStatus().getModality() == Modality.FINAL || Intrinsics.areEqual(firPropertySymbol.getRawStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
            return Unit.INSTANCE;
        }
        if (firDelegatedMemberScope.delegatedMembersFilter.shouldNotGenerateDelegatedMember(firVariableSymbol)) {
            return Unit.INSTANCE;
        }
        final FirProperty firProperty = (FirProperty) firPropertySymbol.getFir();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        firDelegatedMemberScope.declaredMemberScope.processPropertiesByName(name, new Function1() { // from class: p25
            public final Object invoke(Object obj) {
                return FirDelegatedMemberScope.collectPropertiesFromSpecificField$lambda$0$0(this.b, firProperty, booleanRef, (FirVariableSymbol) obj);
            }
        });
        if (booleanRef.element) {
            return Unit.INSTANCE;
        }
        Iterator it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!firDelegatedMemberScope.overrideChecker.isOverriddenProperty((FirCallableDeclaration) ((FirPropertySymbol) next).getFir(), firProperty));
        FirPropertySymbol firPropertySymbol2 = (FirPropertySymbol) next;
        if (firPropertySymbol2 != null) {
            FirDelegatedMemberScopeKt.setMultipleDelegatesWithTheSameSignature((FirCallableDeclaration) firPropertySymbol2.getFir(), Boolean.TRUE);
            return Unit.INSTANCE;
        }
        FirProperty firPropertyCreateCopyForFirProperty$default = FirFakeOverrideGenerator.createCopyForFirProperty$default(FirFakeOverrideGenerator.INSTANCE, new FirRegularPropertySymbol(new CallableId(FirDeclarationUtilKt.getClassId(firDelegatedMemberScope.containingClass), firPropertySymbol.getName())), firProperty, firDelegatedMemberScope.dispatchReceiverType.getLookupTag(), firDelegatedMemberScope.getSession(), FirDeclarationOrigin.Delegated.INSTANCE, false, firDelegatedMemberScope.dispatchReceiverType, null, null, null, null, Modality.OPEN, null, null, null, null, null, null, null, 522144, null);
        ClassMembersKt.setDelegatedWrapperData(firPropertyCreateCopyForFirProperty$default, new DelegatedWrapperData((FirCallableDeclaration) firPropertySymbol.getFir(), firDelegatedMemberScope.containingClass.getSymbol().getLookupTag(), firField.getSymbol()));
        list.add(firPropertyCreateCopyForFirProperty$default.getSymbol());
        return Unit.INSTANCE;
    }

    private final FirTypeScope buildScope(FirField delegateField) {
        return ScopeUtilsKt.scope(this, delegateField.getSymbol().getResolvedReturnType(), CallableCopyTypeCalculator.CalculateDeferredWhenPossible.INSTANCE, (FirResolvePhase) null);
    }

    public static Set c(FirDelegatedMemberScope firDelegatedMemberScope) {
        Set<Name> setEmptySet;
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        setCreateSetBuilder.addAll(firDelegatedMemberScope.declaredMemberScope.getCallableNames());
        Set set = setCreateSetBuilder;
        Iterator<T> it = firDelegatedMemberScope.delegateFields.iterator();
        while (it.hasNext()) {
            FirTypeScope firTypeScopeBuildScope = firDelegatedMemberScope.buildScope((FirField) it.next());
            if (firTypeScopeBuildScope == null || (setEmptySet = firTypeScopeBuildScope.getCallableNames()) == null) {
                setEmptySet = SetsKt.emptySet();
            }
            CollectionsKt.addAll(set, setEmptySet);
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    private final void collectFunctionsFromSpecificField(final FirField delegateField, final Name name, final List<FirNamedFunctionSymbol> result) {
        FirTypeScope firTypeScopeBuildScope = buildScope(delegateField);
        if (firTypeScopeBuildScope == null) {
            return;
        }
        firTypeScopeBuildScope.processFunctionsByName(name, new Function1() { // from class: q25
            public final Object invoke(Object obj) {
                return FirDelegatedMemberScope.d(this.b, name, result, delegateField, (FirNamedFunctionSymbol) obj);
            }
        });
    }

    private final void collectPropertiesFromSpecificField(final FirField delegateField, final Name name, final List<FirPropertySymbol> result) {
        FirTypeScope firTypeScopeBuildScope = buildScope(delegateField);
        if (firTypeScopeBuildScope == null) {
            return;
        }
        firTypeScopeBuildScope.processPropertiesByName(name, new Function1() { // from class: o25
            public final Object invoke(Object obj) {
                return FirDelegatedMemberScope.b(this.b, name, result, delegateField, (FirVariableSymbol) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit collectPropertiesFromSpecificField$lambda$0$0(FirDelegatedMemberScope firDelegatedMemberScope, FirProperty firProperty, Ref.BooleanRef booleanRef, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if ((firVariableSymbol instanceof FirPropertySymbol) && firDelegatedMemberScope.overrideChecker.isOverriddenProperty((FirCallableDeclaration) ((FirPropertySymbol) firVariableSymbol).getFir(), firProperty)) {
            booleanRef.element = true;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit d(FirDelegatedMemberScope firDelegatedMemberScope, Name name, List list, FirField firField, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        Object next;
        firNamedFunctionSymbol.getClass();
        FirNamedFunction firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol.getFir();
        if (FirDelegatedMemberScopeKt.isPublicInAny(firNamedFunction) && firNamedFunction.getStatus().getModality() != Modality.ABSTRACT) {
            return Unit.INSTANCE;
        }
        if (firNamedFunction.getStatus().getModality() == Modality.FINAL || Intrinsics.areEqual(firNamedFunction.getStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
            return Unit.INSTANCE;
        }
        if (firDelegatedMemberScope.delegatedMembersFilter.shouldNotGenerateDelegatedMember(firNamedFunction.getSymbol())) {
            return Unit.INSTANCE;
        }
        List<FirNamedFunctionSymbol> functions = FirScopeKt.getFunctions(firDelegatedMemberScope.declaredMemberScope, name);
        if (!(functions instanceof Collection) || !functions.isEmpty()) {
            Iterator<T> it = functions.iterator();
            while (it.hasNext()) {
                if (firDelegatedMemberScope.overrideChecker.isOverriddenFunction((FirNamedFunction) ((FirNamedFunctionSymbol) it.next()).getFir(), firNamedFunction)) {
                    return Unit.INSTANCE;
                }
            }
        }
        Iterator it2 = list.iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
        } while (!firDelegatedMemberScope.overrideChecker.isOverriddenFunction((FirNamedFunction) ((FirNamedFunctionSymbol) next).getFir(), firNamedFunction));
        FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) next;
        if (firNamedFunctionSymbol2 != null) {
            FirDelegatedMemberScopeKt.setMultipleDelegatesWithTheSameSignature((FirCallableDeclaration) firNamedFunctionSymbol2.getFir(), Boolean.TRUE);
            return Unit.INSTANCE;
        }
        FirFakeOverrideGenerator firFakeOverrideGenerator = FirFakeOverrideGenerator.INSTANCE;
        FirNamedFunctionSymbol firNamedFunctionSymbol3 = new FirNamedFunctionSymbol(new CallableId(FirDeclarationUtilKt.getClassId(firDelegatedMemberScope.containingClass), firNamedFunctionSymbol.getName()));
        ConeClassLikeLookupTag lookupTag = firDelegatedMemberScope.dispatchReceiverType.getLookupTag();
        FirSession session = firDelegatedMemberScope.getSession();
        FirDeclarationOrigin.Delegated delegated = FirDeclarationOrigin.Delegated.INSTANCE;
        ConeClassLikeType coneClassLikeType = firDelegatedMemberScope.dispatchReceiverType;
        Modality modality = Modality.OPEN;
        KtSourceElement source = firDelegatedMemberScope.containingClass.getSource();
        FirNamedFunction firNamedFunctionCreateCopyForFirFunction$default = FirFakeOverrideGenerator.createCopyForFirFunction$default(firFakeOverrideGenerator, firNamedFunctionSymbol3, firNamedFunction, lookupTag, session, delegated, false, coneClassLikeType, null, null, null, null, null, modality, null, null, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.MembersImplementedByDelegation.INSTANCE, null, 2, null) : null, true, 28576, null);
        ClassMembersKt.setDelegatedWrapperData(firNamedFunctionCreateCopyForFirFunction$default, new DelegatedWrapperData((FirCallableDeclaration) firNamedFunctionSymbol.getFir(), firDelegatedMemberScope.containingClass.getSymbol().getLookupTag(), firField.getSymbol()));
        list.add(firNamedFunctionCreateCopyForFirFunction$default.getSymbol());
        return Unit.INSTANCE;
    }

    public static Set f(FirDelegatedMemberScope firDelegatedMemberScope) {
        Set<Name> setEmptySet;
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        setCreateSetBuilder.addAll(firDelegatedMemberScope.declaredMemberScope.getClassifierNames());
        Set set = setCreateSetBuilder;
        Iterator<T> it = firDelegatedMemberScope.delegateFields.iterator();
        while (it.hasNext()) {
            FirTypeScope firTypeScopeBuildScope = firDelegatedMemberScope.buildScope((FirField) it.next());
            if (firTypeScopeBuildScope == null || (setEmptySet = firTypeScopeBuildScope.getClassifierNames()) == null) {
                setEmptySet = SetsKt.emptySet();
            }
            CollectionsKt.addAll(set, setEmptySet);
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    private final Set<Name> getCallableNamesLazy() {
        return (Set) this.callableNamesLazy.getValue();
    }

    private final Set<Name> getClassifierNamesLazy() {
        return (Set) this.classifierNamesLazy.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return getCallableNamesLazy();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return getClassifierNamesLazy();
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.declaredMemberScope.processClassifiersByNameWithSubstitution(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        this.declaredMemberScope.processDeclaredConstructors(processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.declaredMemberScope.processFunctionsByName(name, processor);
        ArrayList arrayList = new ArrayList();
        Iterator<FirField> it = this.delegateFields.iterator();
        while (it.hasNext()) {
            collectFunctionsFromSpecificField(it.next(), name, arrayList);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            processor.invoke(it2.next());
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.declaredMemberScope.processPropertiesByName(name, processor);
        ArrayList arrayList = new ArrayList();
        Iterator<FirField> it = this.delegateFields.iterator();
        while (it.hasNext()) {
            collectPropertiesFromSpecificField(it.next(), name, arrayList);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            processor.invoke(it2.next());
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirDelegatedMemberScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirClass firClass = this.containingClass;
        FirContainingNamesAwareScope firContainingNamesAwareScopeWithReplacedSessionOrNull = this.declaredMemberScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firContainingNamesAwareScopeWithReplacedSessionOrNull == null) {
            firContainingNamesAwareScopeWithReplacedSessionOrNull = this.declaredMemberScope;
        }
        return new FirDelegatedMemberScope(newSession, newScopeSession, firClass, firContainingNamesAwareScopeWithReplacedSessionOrNull, this.delegateFields);
    }
}

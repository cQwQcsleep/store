package org.jetbrains.kotlin.fir.resolve.calls.tower;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.CompanionExtensionPolicy;
import org.jetbrains.kotlin.fir.resolve.calls.ConstructorFilter;
import org.jetbrains.kotlin.fir.resolve.calls.ConstructorProcessingKt;
import org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.tower.ScopeBasedTowerLevel;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirActualizingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDefaultStarImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.types.AbstractTypeChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BC\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0006\u0010\u001c\u001a\u00020\nJ\u001e\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 2\u0006\u0010!\u001a\u00020\"H\u0002J\u0014\u0010#\u001a\u00020\n2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0002J$\u0010$\u001a\u00020%2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010&\u001a\u00020'H\u0002J\u0018\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\"2\u0006\u0010&\u001a\u00020'H\u0016J\u0018\u0010+\u001a\u00020)2\u0006\u0010*\u001a\u00020\"2\u0006\u0010&\u001a\u00020'H\u0016J\u0018\u0010,\u001a\u00020)2\u0006\u0010*\u001a\u00020\"2\u0006\u0010&\u001a\u00020'H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/ScopeBasedTowerLevel;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevel;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "bodyResolveComponents", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "givenScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "givenExtensionReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "withHideMembersOnly", Argument.Delimiters.none, "constructorFilter", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConstructorFilter;", "companionExtensionPolicy", "Lorg/jetbrains/kotlin/fir/resolve/calls/CompanionExtensionPolicy;", "dispatchReceiverForStatics", "Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/scopes/FirScope;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;ZLorg/jetbrains/kotlin/fir/resolve/calls/ConstructorFilter;Lorg/jetbrains/kotlin/fir/resolve/calls/CompanionExtensionPolicy;Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "value", "hasFoundCompanionExtensions", "getHasFoundCompanionExtensions$org_jetbrains_kotlin_resolve", "()Z", "scope", "areThereExtensionReceiverOptions", "dispatchReceiverValue", "Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverValue;", "candidate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "shouldSkipCandidateWithInconsistentExtensionReceiver", "consumeCallableCandidate", Argument.Delimiters.none, "processor", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevelProcessor;", "processFunctionsByName", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/ProcessResult;", "info", "processPropertiesByName", "processObjectsByName", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ScopeBasedTowerLevel extends TowerLevel implements SessionHolder {
    private final BodyResolveComponents bodyResolveComponents;
    private final CompanionExtensionPolicy companionExtensionPolicy;
    private final ConstructorFilter constructorFilter;
    private final ExpressionReceiverValue dispatchReceiverForStatics;
    private final FirExpression givenExtensionReceiver;
    private boolean hasFoundCompanionExtensions;
    private final FirScope scope;
    private final boolean withHideMembersOnly;

    public ScopeBasedTowerLevel(BodyResolveComponents bodyResolveComponents, FirScope firScope, FirExpression firExpression, boolean z, ConstructorFilter constructorFilter, CompanionExtensionPolicy companionExtensionPolicy, ExpressionReceiverValue expressionReceiverValue) {
        bodyResolveComponents.getClass();
        firScope.getClass();
        constructorFilter.getClass();
        companionExtensionPolicy.getClass();
        this.bodyResolveComponents = bodyResolveComponents;
        this.givenExtensionReceiver = firExpression;
        this.withHideMembersOnly = z;
        this.constructorFilter = constructorFilter;
        this.companionExtensionPolicy = companionExtensionPolicy;
        this.dispatchReceiverForStatics = expressionReceiverValue;
        this.scope = LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.MultiPlatformProjects) ? new FirActualizingScope(firScope, getSession()) : firScope;
    }

    public static Unit b(Ref.BooleanRef booleanRef, TowerLevelProcessor towerLevelProcessor, ScopeBasedTowerLevel scopeBasedTowerLevel, FirClassifierSymbol firClassifierSymbol) {
        firClassifierSymbol.getClass();
        booleanRef.element = false;
        TowerLevelProcessor.consumeCandidate$default(towerLevelProcessor, firClassifierSymbol, null, null, scopeBasedTowerLevel.scope, true, false, 32, null);
        return Unit.INSTANCE;
    }

    public static boolean c(ScopeBasedTowerLevel scopeBasedTowerLevel, ConeClassLikeLookupTag coneClassLikeLookupTag, ImplicitReceiverValue implicitReceiverValue) {
        implicitReceiverValue.getClass();
        return Intrinsics.areEqual(ConeTypeUtilsKt.getLookupTagIfAny(TypeExpansionUtilsKt.fullyExpandedType(scopeBasedTowerLevel, implicitReceiverValue.getType())), coneClassLikeLookupTag);
    }

    private final void consumeCallableCandidate(FirCallableSymbol<?> candidate, CallInfo callInfo, TowerLevelProcessor processor) {
        FirCallableDeclaration original;
        FirCallableSymbol<FirCallableDeclaration> symbol;
        boolean zIsCompanionExtension = FirSymbolStatusUtilsKt.isCompanionExtension(candidate);
        if (zIsCompanionExtension) {
            this.hasFoundCompanionExtensions = true;
        }
        if (zIsCompanionExtension != this.companionExtensionPolicy.getAcceptsCompanionExtension()) {
            return;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(candidate, FirResolvePhase.TYPES);
        if (!this.withHideMembersOnly || FirAnnotationUtilsKt.hasAnnotationWithClassId(candidate, StandardClassIds$Annotations.INSTANCE.getHidesMembers(), getSession())) {
            boolean z = this.withHideMembersOnly || areThereExtensionReceiverOptions();
            FirReceiverParameter receiverParameter = ((FirCallableDeclaration) candidate.getFir()).getReceiverParameter();
            if (((receiverParameter != null ? receiverParameter.getTypeRef() : null) == null) == z) {
                return;
            }
            ReceiverValue receiverValueDispatchReceiverValue = dispatchReceiverValue(candidate, callInfo);
            if (receiverValueDispatchReceiverValue == null && shouldSkipCandidateWithInconsistentExtensionReceiver(candidate)) {
                return;
            }
            ImportedFromObjectOrStaticData importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData((FirCallableDeclaration) candidate.getFir());
            TowerLevelProcessor.consumeCandidate$default(processor, (importedFromObjectOrStaticData == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || (symbol = original.getSymbol()) == null) ? candidate : symbol, receiverValueDispatchReceiverValue != null ? receiverValueDispatchReceiverValue.getReceiverExpression() : null, this.givenExtensionReceiver, this.scope, false, false, 48, null);
        }
    }

    public static Unit d(FirLookupTrackerComponent firLookupTrackerComponent, CallInfo callInfo, Ref.BooleanRef booleanRef, ScopeBasedTowerLevel scopeBasedTowerLevel, TowerLevelProcessor towerLevelProcessor, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (firLookupTrackerComponent != null) {
            FirLookupTrackerComponentKt.recordCallableCandidateAsLookup(firLookupTrackerComponent, firVariableSymbol, callInfo.getCallSite().getSource(), callInfo.getContainingFile().getSource());
        }
        booleanRef.element = false;
        scopeBasedTowerLevel.consumeCallableCandidate(firVariableSymbol, callInfo, towerLevelProcessor);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ReceiverValue dispatchReceiverValue(FirCallableSymbol<?> candidate, CallInfo callInfo) {
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData((FirCallableDeclaration) candidate.getFir());
        if (importedFromObjectOrStaticData != null) {
            FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(getSession()).getClassLikeSymbolByClassId(importedFromObjectOrStaticData.getObjectClassId());
            if (classLikeSymbolByClassId instanceof FirRegularClassSymbol) {
                FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) classLikeSymbolByClassId;
                BodyResolveComponents bodyResolveComponents = this.bodyResolveComponents;
                KtSourceElement source = callInfo.getCallSite().getSource();
                return new ExpressionReceiverValue(ResolveUtilsKt.toImplicitResolvedQualifierReceiver(firRegularClassSymbol, bodyResolveComponents, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitReceiver.INSTANCE, null, 2, null) : null));
            }
        }
        if (!(candidate instanceof FirBackingFieldSymbol)) {
            if (candidate.getRawStatus().isStatic()) {
                return this.dispatchReceiverForStatics;
            }
            return null;
        }
        final ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(((FirBackingField) ((FirBackingFieldSymbol) candidate).getFir()).getPropertySymbol());
        if (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull != null) {
            return this.bodyResolveComponents.getImplicitValueStorage().lastDispatchReceiver(new Function1() { // from class: wwc
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(ScopeBasedTowerLevel.c(this.b, coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull, (ImplicitReceiverValue) obj));
                }
            });
        }
        return null;
    }

    public static Unit e(FirLookupTrackerComponent firLookupTrackerComponent, CallInfo callInfo, Ref.BooleanRef booleanRef, ScopeBasedTowerLevel scopeBasedTowerLevel, TowerLevelProcessor towerLevelProcessor, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        if (firLookupTrackerComponent != null) {
            FirLookupTrackerComponentKt.recordCallableCandidateAsLookup(firLookupTrackerComponent, firCallableSymbol, callInfo.getCallSite().getSource(), callInfo.getContainingFile().getSource());
        }
        booleanRef.element = false;
        scopeBasedTowerLevel.consumeCallableCandidate(firCallableSymbol, callInfo, towerLevelProcessor);
        return Unit.INSTANCE;
    }

    private final boolean shouldSkipCandidateWithInconsistentExtensionReceiver(FirCallableSymbol<?> candidate) {
        if (!(this.scope instanceof FirDefaultStarImportingScope) || !areThereExtensionReceiverOptions()) {
            return false;
        }
        ConeKotlinType resolvedReceiverType = candidate.getResolvedReceiverType();
        ConeClassLikeType coneClassLikeType = resolvedReceiverType instanceof ConeClassLikeType ? (ConeClassLikeType) resolvedReceiverType : null;
        if (coneClassLikeType == null) {
            return false;
        }
        ConeClassLikeLookupTag lookupTag = coneClassLikeType.getLookupTag();
        ConeTypeProjection[] typeArguments = coneClassLikeType.getTypeArguments();
        ArrayList arrayList = new ArrayList(typeArguments.length);
        for (ConeTypeProjection coneTypeProjection : typeArguments) {
            arrayList.add(ConeStarProjection.INSTANCE);
        }
        ConeClassLikeType coneClassLikeTypeConstructClassType$default = TypeConstructionUtilsKt.constructClassType$default(lookupTag, (ConeTypeProjection[]) arrayList.toArray(new ConeStarProjection[0]), true, null, 4, null);
        FirExpression firExpression = this.givenExtensionReceiver;
        ConeKotlinType resolvedType = firExpression != null ? FirTypeUtilsKt.getResolvedType(firExpression) : null;
        if (resolvedType instanceof ConeClassLikeType) {
            return !AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(getSession()), resolvedType, coneClassLikeTypeConstructClassType$default, false, 8, (Object) null);
        }
        return false;
    }

    public final boolean areThereExtensionReceiverOptions() {
        return this.givenExtensionReceiver != null;
    }

    /* JADX INFO: renamed from: getHasFoundCompanionExtensions$org_jetbrains_kotlin_resolve, reason: from getter */
    public final boolean getHasFoundCompanionExtensions() {
        return this.hasFoundCompanionExtensions;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.bodyResolveComponents.getSession();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.tower.TowerLevel
    public ProcessResult processFunctionsByName(final CallInfo info, final TowerLevelProcessor processor) {
        info.getClass();
        processor.getClass();
        final FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordCallLookup(lookupTracker, info, this.scope.getScopeOwnerLookupNames());
        }
        ConstructorProcessingKt.processFunctionsAndConstructorsByName(this.scope, info, this.bodyResolveComponents, this.constructorFilter, new Function1() { // from class: vwc
            public final Object invoke(Object obj) {
                return ScopeBasedTowerLevel.e(lookupTracker, info, booleanRef, this, processor, (FirCallableSymbol) obj);
            }
        });
        return booleanRef.element ? ProcessResult.SCOPE_EMPTY : ProcessResult.FOUND;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.tower.TowerLevel
    public ProcessResult processObjectsByName(CallInfo info, final TowerLevelProcessor processor) {
        info.getClass();
        processor.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordCallLookup(lookupTracker, info, this.scope.getScopeOwnerLookupNames());
        }
        FirScope firScope = this.scope;
        Name name = info.getName();
        final Function1 function1 = new Function1() { // from class: twc
            public final Object invoke(Object obj) {
                return ScopeBasedTowerLevel.b(booleanRef, processor, this, (FirClassifierSymbol) obj);
            }
        };
        firScope.processClassifiersByNameWithSubstitution(name, new Function2<FirClassifierSymbol<?>, ConeSubstitutor, Unit>() { // from class: org.jetbrains.kotlin.fir.resolve.calls.tower.ScopeBasedTowerLevel$processObjectsByName$$inlined$processClassifiersByName$1
            public final void invoke(FirClassifierSymbol<?> firClassifierSymbol, ConeSubstitutor coneSubstitutor) {
                firClassifierSymbol.getClass();
                coneSubstitutor.getClass();
                function1.invoke(firClassifierSymbol);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((FirClassifierSymbol<?>) obj, (ConeSubstitutor) obj2);
                return Unit.INSTANCE;
            }
        });
        return booleanRef.element ? ProcessResult.SCOPE_EMPTY : ProcessResult.FOUND;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.tower.TowerLevel
    public ProcessResult processPropertiesByName(final CallInfo info, final TowerLevelProcessor processor) {
        info.getClass();
        processor.getClass();
        final FirLookupTrackerComponent lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(getSession());
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        booleanRef.element = true;
        if (lookupTracker != null) {
            FirLookupTrackerComponentKt.recordCallLookup(lookupTracker, info, this.scope.getScopeOwnerLookupNames());
        }
        this.scope.processPropertiesByName(info.getName(), new Function1() { // from class: uwc
            public final Object invoke(Object obj) {
                return ScopeBasedTowerLevel.d(lookupTracker, info, booleanRef, this, processor, (FirVariableSymbol) obj);
            }
        });
        return booleanRef.element ? ProcessResult.SCOPE_EMPTY : ProcessResult.FOUND;
    }
}

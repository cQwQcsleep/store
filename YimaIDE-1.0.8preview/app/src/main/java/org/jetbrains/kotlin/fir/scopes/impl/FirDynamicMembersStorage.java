package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\fJ\u0010\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u0014H\u0002J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0014H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0010R%\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R%\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirDynamicMembersStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "dynamicScopeCacheByScope", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDynamicScope;", Argument.Delimiters.none, "getDynamicScopeCacheByScope$annotations", "()V", "getDynamicScopeFor", "scopeSession", "functionsCacheByName", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "getFunctionsCacheByName", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "propertiesCacheByName", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getPropertiesCacheByName", "dynamicTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "anyArrayTypeRef", "buildPseudoFunctionByName", ModuleXmlParser.NAME, "buildPseudoPropertyByName", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDynamicMembersStorage implements FirSessionComponent {
    private final FirResolvedTypeRef anyArrayTypeRef;
    private final FirCachesFactory cachesFactory;
    private final FirCache dynamicScopeCacheByScope;
    private final FirResolvedTypeRef dynamicTypeRef;
    private final FirCache functionsCacheByName;
    private final FirCache propertiesCacheByName;
    private final FirSession session;

    public FirDynamicMembersStorage(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        FirCachesFactory firCachesFactory = FirCachesFactoryKt.getFirCachesFactory(firSession);
        this.cachesFactory = firCachesFactory;
        this.dynamicScopeCacheByScope = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirDynamicMembersStorage$special$$inlined$createCache$1
            public final FirDynamicScope invoke(ScopeSession scopeSession, Void r2) {
                scopeSession.getClass();
                return new FirDynamicScope(this.this$0.getSession(), scopeSession);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((ScopeSession) obj, (Void) obj2);
            }
        });
        this.functionsCacheByName = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirDynamicMembersStorage$special$$inlined$createCache$2
            public final FirNamedFunction invoke(Name name, Void r2) {
                name.getClass();
                return this.this$0.buildPseudoFunctionByName(name);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((Name) obj, (Void) obj2);
            }
        });
        this.propertiesCacheByName = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirDynamicMembersStorage$special$$inlined$createCache$3
            public final FirProperty invoke(Name name, Void r2) {
                name.getClass();
                return this.this$0.buildPseudoPropertyByName(name);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((Name) obj, (Void) obj2);
            }
        });
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(TypeUtilsKt.create$default(ConeDynamicType.Companion, firSession, null, 2, null));
        FirResolvedTypeRef firResolvedTypeRefBuild = firResolvedTypeRefBuilder.build();
        this.dynamicTypeRef = firResolvedTypeRefBuild;
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder2.setConeType(new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(StandardClassIds.INSTANCE.getArray()), new ConeKotlinType[]{firResolvedTypeRefBuild.getConeType()}, false, null, 8, null));
        this.anyArrayTypeRef = firResolvedTypeRefBuilder2.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirNamedFunction buildPseudoFunctionByName(Name name) {
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE);
        firResolvedDeclarationStatusImpl.setInfix(true);
        firResolvedDeclarationStatusImpl.setOperator(true);
        firNamedFunctionBuilder.setStatus(firResolvedDeclarationStatusImpl);
        firNamedFunctionBuilder.setName(name);
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(new CallableId(StandardNames.DYNAMIC_FQ_NAME, firNamedFunctionBuilder.getName())));
        firNamedFunctionBuilder.setLocal(false);
        firNamedFunctionBuilder.setModuleData(FirModuleDataKt.getModuleData(this.session));
        FirDeclarationOrigin.DynamicScope dynamicScope = FirDeclarationOrigin.DynamicScope.INSTANCE;
        firNamedFunctionBuilder.setOrigin(dynamicScope);
        FirResolvePhase firResolvePhase = FirResolvePhase.BODY_RESOLVE;
        firNamedFunctionBuilder.setResolvePhase(firResolvePhase);
        firNamedFunctionBuilder.setReturnTypeRef(this.dynamicTypeRef);
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        firValueParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(this.session));
        firValueParameterBuilder.setContainingDeclarationSymbol(firNamedFunctionBuilder.getSymbol());
        firValueParameterBuilder.setOrigin(dynamicScope);
        firValueParameterBuilder.setResolvePhase(firResolvePhase);
        firValueParameterBuilder.setReturnTypeRef(this.anyArrayTypeRef);
        Name nameIdentifier = Name.identifier("args");
        nameIdentifier.getClass();
        firValueParameterBuilder.setName(nameIdentifier);
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        firValueParameterBuilder.setCrossinline(false);
        firValueParameterBuilder.setNoinline(false);
        firValueParameterBuilder.setVararg(true);
        firNamedFunctionBuilder.getValueParameters().add(firValueParameterBuilder.mo288build());
        return firNamedFunctionBuilder.mo288build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirProperty buildPseudoPropertyByName(Name name) {
        FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
        firPropertyBuilder.setName(name);
        firPropertyBuilder.setSymbol(new FirRegularPropertySymbol(new CallableId(StandardNames.DYNAMIC_FQ_NAME, firPropertyBuilder.getName())));
        firPropertyBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE));
        firPropertyBuilder.setLocal(false);
        firPropertyBuilder.setModuleData(FirModuleDataKt.getModuleData(this.session));
        firPropertyBuilder.setOrigin(FirDeclarationOrigin.DynamicScope.INSTANCE);
        firPropertyBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firPropertyBuilder.setReturnTypeRef(this.dynamicTypeRef);
        firPropertyBuilder.setVar(true);
        return firPropertyBuilder.mo288build();
    }

    public final FirDynamicScope getDynamicScopeFor(ScopeSession scopeSession) {
        scopeSession.getClass();
        return (FirDynamicScope) this.dynamicScopeCacheByScope.getValue(scopeSession, null);
    }

    public final FirCache getFunctionsCacheByName() {
        return this.functionsCacheByName;
    }

    public final FirCache getPropertiesCacheByName() {
        return this.propertiesCacheByName;
    }

    public final FirSession getSession() {
        return this.session;
    }
}

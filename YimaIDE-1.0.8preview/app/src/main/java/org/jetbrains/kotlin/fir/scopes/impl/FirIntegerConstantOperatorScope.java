package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.CallableCopyTypeCalculator;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorTypeImpl;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitUIntTypeRef;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00170\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0014H\u0002J(\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0016\u0010\u0019\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001e\u0012\u0004\u0012\u00020\u00170\u001aH\u0016J\u001c\u0010\u001f\u001a\u00020\u00172\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00170\u001aH\u0016J\u0010\u0010!\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0013H\u0016J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00130#H\u0016J.\u0010$\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u001c\u0010\u0019\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030&\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00170%H\u0016J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00130#H\u0016J*\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00142\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020*0%H\u0016J*\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020.2\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020*0%H\u0016J\u001c\u0010/\u001a\u00020\u00002\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u0006H\u0017b\u0002\b2R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0011\u001a\"\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u0012j\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirIntegerConstantOperatorScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "isUnsigned", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Z)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "()Z", "baseScope", "mappedFunctions", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lkotlin/collections/HashMap;", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function1;", "wrapIntOperator", "originalSymbol", "processPropertiesByName", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "mayContainName", "getCallableNames", Argument.Delimiters.none, "processClassifiersByNameWithSubstitution", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "getClassifierNames", "processDirectOverriddenFunctionsWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "functionSymbol", "processDirectOverriddenPropertiesWithBaseScope", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirIntegerConstantOperatorScope extends FirTypeScope implements SessionAndScopeSessionHolder {
    private final FirTypeScope baseScope;
    private final boolean isUnsigned;
    private final HashMap<Name, FirNamedFunctionSymbol> mappedFunctions;
    private final ScopeSession scopeSession;
    private final FirSession session;

    public FirIntegerConstantOperatorScope(FirSession firSession, ScopeSession scopeSession, boolean z) {
        FirImplicitUIntTypeRef intType;
        firSession.getClass();
        scopeSession.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.isUnsigned = z;
        if (z) {
            intType = getSession().getBuiltinTypes().getUIntType();
        } else {
            if (z) {
                bu8.a();
                throw null;
            }
            intType = getSession().getBuiltinTypes().getIntType();
        }
        FirTypeScope firTypeScopeScope = ScopeUtilsKt.scope(this, intType.getConeType(), CallableCopyTypeCalculator.DoNothing.INSTANCE, FirResolvePhase.STATUS);
        this.baseScope = firTypeScopeScope == null ? FirTypeScope.Empty.INSTANCE : firTypeScopeScope;
        this.mappedFunctions = new HashMap<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirNamedFunctionSymbol wrapIntOperator(FirNamedFunctionSymbol originalSymbol) {
        FirNamedFunction firNamedFunction = (FirNamedFunction) originalSymbol.getFir();
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setSource(firNamedFunction.getSource());
        firNamedFunctionBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firNamedFunction));
        firNamedFunctionBuilder.setModuleData(firNamedFunction.getModuleData());
        firNamedFunctionBuilder.setOrigin(firNamedFunction.getOrigin());
        firNamedFunctionBuilder.setAttributes(firNamedFunction.getAttributes().copy());
        firNamedFunctionBuilder.setStatus(firNamedFunction.getStatus());
        firNamedFunctionBuilder.setLocal(firNamedFunction.getIsLocal());
        firNamedFunctionBuilder.setReturnTypeRef(firNamedFunction.getReturnTypeRef());
        firNamedFunctionBuilder.setReceiverParameter(firNamedFunction.getReceiverParameter());
        firNamedFunctionBuilder.setDeprecationsProvider(firNamedFunction.getDeprecationsProvider());
        firNamedFunctionBuilder.setContainerSource(firNamedFunction.getContainerSource());
        firNamedFunctionBuilder.setDispatchReceiverType(firNamedFunction.getDispatchReceiverType());
        firNamedFunctionBuilder.getContextParameters().addAll(firNamedFunction.getContextParameters());
        firNamedFunctionBuilder.getValueParameters().addAll(firNamedFunction.getValueParameters());
        firNamedFunctionBuilder.setBody(firNamedFunction.getBody());
        firNamedFunctionBuilder.setContractDescription(firNamedFunction.getContractDescription());
        firNamedFunctionBuilder.setName(firNamedFunction.getName());
        firNamedFunctionBuilder.getAnnotations().addAll(firNamedFunction.getAnnotations());
        firNamedFunctionBuilder.getTypeParameters().addAll(firNamedFunction.getTypeParameters());
        firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(originalSymbol.getCallableId()));
        firNamedFunctionBuilder.setOrigin(FirDeclarationOrigin.WrappedIntegerOperator.INSTANCE);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(new ConeIntegerConstantOperatorTypeImpl(this.isUnsigned, false));
        firNamedFunctionBuilder.setReturnTypeRef(firResolvedTypeRefBuilder.build());
        FirNamedFunction firNamedFunctionMo288build = firNamedFunctionBuilder.mo288build();
        FirIntegerConstantOperatorScopeKt.setOriginalForWrappedIntegerOperator(firNamedFunctionMo288build, originalSymbol);
        FirIntegerConstantOperatorScopeKt.setUnsignedWrappedIntegerOperator(firNamedFunctionMo288build, Boolean.valueOf(this.isUnsigned));
        return firNamedFunctionMo288build.getSymbol();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        return this.baseScope.getCallableNames();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        return SetsKt.emptySet();
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    /* JADX INFO: renamed from: isUnsigned, reason: from getter */
    public final boolean getIsUnsigned() {
        return this.isUnsigned;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public boolean mayContainName(Name name) {
        name.getClass();
        return this.baseScope.mayContainName(name);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processClassifiersByNameWithSubstitution(Name name, Function2<? super FirClassifierSymbol<?>, ? super ConeSubstitutor, Unit> processor) {
        name.getClass();
        processor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        this.baseScope.processDeclaredConstructors(processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenFunctionsWithBaseScope(FirNamedFunctionSymbol functionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        functionSymbol.getClass();
        processor.getClass();
        return ProcessorAction.NONE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenPropertiesWithBaseScope(FirPropertySymbol propertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        propertySymbol.getClass();
        processor.getClass();
        return ProcessorAction.NONE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        Object next;
        boolean zIsUInt;
        name.getClass();
        processor.getClass();
        boolean z = false;
        boolean z2 = !this.isUnsigned && ConvertibleIntegerOperators.INSTANCE.getUnaryOperatorNames().contains(name);
        ConvertibleIntegerOperators convertibleIntegerOperators = ConvertibleIntegerOperators.INSTANCE;
        boolean zContains = convertibleIntegerOperators.getBinaryOperatorsNames().contains(name);
        if (!z2 && !zContains) {
            this.baseScope.processFunctionsByName(name, processor);
            return;
        }
        if (this.isUnsigned && !convertibleIntegerOperators.getBinaryOperatorsWithSignedArgument().contains(name)) {
            z = true;
        }
        HashMap<Name, FirNamedFunctionSymbol> map = this.mappedFunctions;
        FirNamedFunctionSymbol firNamedFunctionSymbolWrapIntOperator = map.get(name);
        if (firNamedFunctionSymbolWrapIntOperator == null) {
            Iterator<T> it = FirScopeKt.getFunctions(this.baseScope, name).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) next;
                if (z2) {
                    zIsUInt = true;
                } else {
                    ConeKotlinType coneType = FirTypeUtilsKt.getConeType(((FirValueParameter) CollectionsKt.first(((FirNamedFunction) firNamedFunctionSymbol.getFir()).getValueParameters())).getReturnTypeRef());
                    zIsUInt = z ? ConeBuiltinTypeUtilsKt.isUInt(coneType) : ConeBuiltinTypeUtilsKt.isInt(coneType);
                }
            } while (!zIsUInt);
            FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) next;
            firNamedFunctionSymbolWrapIntOperator = firNamedFunctionSymbol2 != null ? wrapIntOperator(firNamedFunctionSymbol2) : null;
            map.put(name, firNamedFunctionSymbolWrapIntOperator);
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol3 = firNamedFunctionSymbolWrapIntOperator;
        if (firNamedFunctionSymbol3 != null) {
            processor.invoke(firNamedFunctionSymbol3);
        }
        this.baseScope.processFunctionsByName(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.baseScope.processPropertiesByName(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public FirIntegerConstantOperatorScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new FirIntegerConstantOperatorScope(newSession, newScopeSession, this.isUnsigned);
    }
}

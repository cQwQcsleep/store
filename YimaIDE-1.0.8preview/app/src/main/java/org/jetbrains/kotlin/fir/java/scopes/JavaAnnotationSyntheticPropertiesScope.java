package org.jetbrains.kotlin.fir.java.scopes;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.UnresolvedDeprecationProvider;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyBuilderKt;
import org.jetbrains.kotlin.fir.java.scopes.JavaAnnotationSyntheticPropertiesScope;
import org.jetbrains.kotlin.fir.java.symbols.FirJavaOverriddenSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000e2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00150\u0018H\u0016J(\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000e2\u0016\u0010\u0017\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0012\u0004\u0012\u00020\u00150\u0018H\u0016J*\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00112\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001b0\u001dH\u0016J*\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!2\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001b0\u001dH\u0016J\n\u0010\"\u001a\u00020#H\u0096\u0080\u0004J\u001c\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020\u00032\u0006\u0010&\u001a\u00020'H\u0017b\u0002\b(R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u000f\u001a&\u0012\u0004\u0012\u00020\u0011\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0010j\u0012\u0012\u0004\u0012\u00020\u0011\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/JavaAnnotationSyntheticPropertiesScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirDelegatingTypeScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "delegateScope", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassMembersEnhancementScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassMembersEnhancementScope;)V", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "names", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "syntheticPropertiesCache", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lkotlin/collections/HashMap;", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function1;", "processPropertiesByName", "processDirectOverriddenFunctionsWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "functionSymbol", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "processDirectOverriddenPropertiesWithBaseScope", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "toString", Argument.Delimiters.none, "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaAnnotationSyntheticPropertiesScope extends FirDelegatingTypeScope {
    private final ClassId classId;
    private final JavaClassMembersEnhancementScope delegateScope;
    private final Set<Name> names;
    private final FirRegularClassSymbol owner;
    private final FirSession session;
    private final HashMap<FirNamedFunctionSymbol, FirVariableSymbol<?>> syntheticPropertiesCache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public JavaAnnotationSyntheticPropertiesScope(FirSession firSession, FirRegularClassSymbol firRegularClassSymbol, JavaClassMembersEnhancementScope javaClassMembersEnhancementScope) {
        super(javaClassMembersEnhancementScope);
        firSession.getClass();
        firRegularClassSymbol.getClass();
        javaClassMembersEnhancementScope.getClass();
        this.session = firSession;
        this.owner = firRegularClassSymbol;
        this.delegateScope = javaClassMembersEnhancementScope;
        this.classId = firRegularClassSymbol.getClassId();
        List<FirDeclaration> declarations = ((FirRegularClass) firRegularClassSymbol.getFir()).getDeclarations();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (FirDeclaration firDeclaration : declarations) {
            FirNamedFunction firNamedFunction = firDeclaration instanceof FirNamedFunction ? (FirNamedFunction) firDeclaration : null;
            Name name = firNamedFunction != null ? firNamedFunction.getName() : null;
            if (name != null) {
                linkedHashSet.add(name);
            }
        }
        this.names = linkedHashSet;
        this.syntheticPropertiesCache = new HashMap<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit c(final JavaAnnotationSyntheticPropertiesScope javaAnnotationSyntheticPropertiesScope, Function1 function1, final Name name, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        final FirNamedFunction firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol.getFir();
        HashMap<FirNamedFunctionSymbol, FirVariableSymbol<?>> map = javaAnnotationSyntheticPropertiesScope.syntheticPropertiesCache;
        FirVariableSymbol<?> firVariableSymbol = map.get(firNamedFunctionSymbol);
        if (firVariableSymbol == null) {
            CallableId callableId = new CallableId(javaAnnotationSyntheticPropertiesScope.classId, name);
            final FirJavaOverriddenSyntheticPropertySymbol firJavaOverriddenSyntheticPropertySymbol = new FirJavaOverriddenSyntheticPropertySymbol(callableId, callableId);
            FirSyntheticPropertyBuilderKt.buildSyntheticProperty(new Function1() { // from class: hb7
                public final Object invoke(Object obj) {
                    return JavaAnnotationSyntheticPropertiesScope.processPropertiesByName$lambda$0$0$0$0(this.b, firNamedFunction, name, firJavaOverriddenSyntheticPropertySymbol, (FirSyntheticPropertyBuilder) obj);
                }
            });
            map.put(firNamedFunctionSymbol, firJavaOverriddenSyntheticPropertySymbol);
            firVariableSymbol = firJavaOverriddenSyntheticPropertySymbol;
        }
        function1.invoke(firVariableSymbol);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit processPropertiesByName$lambda$0$0$0$0(JavaAnnotationSyntheticPropertiesScope javaAnnotationSyntheticPropertiesScope, FirNamedFunction firNamedFunction, Name name, FirJavaOverriddenSyntheticPropertySymbol firJavaOverriddenSyntheticPropertySymbol, FirSyntheticPropertyBuilder firSyntheticPropertyBuilder) {
        firSyntheticPropertyBuilder.getClass();
        FirModuleData nullableModuleData = FirModuleDataKt.getNullableModuleData(javaAnnotationSyntheticPropertiesScope.session);
        if (nullableModuleData == null) {
            nullableModuleData = firNamedFunction.getModuleData();
        }
        firSyntheticPropertyBuilder.setModuleData(nullableModuleData);
        firSyntheticPropertyBuilder.setName(name);
        firSyntheticPropertyBuilder.setSymbol(firJavaOverriddenSyntheticPropertySymbol);
        FirDeclarationStatus status = firNamedFunction.getStatus();
        firSyntheticPropertyBuilder.setCustomStatus(UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : null, (8388575 & 2) != 0 ? status.getModality() : Modality.FINAL, (8388575 & 4) != 0 ? status.isExpect() : false, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : false, (8388575 & 32) != 0 ? status.isOperator() : false, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : false, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null));
        firSyntheticPropertyBuilder.setDelegateGetter(firNamedFunction);
        firSyntheticPropertyBuilder.setDeprecationsProvider(UnresolvedDeprecationProvider.INSTANCE);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenFunctionsWithBaseScope(FirNamedFunctionSymbol functionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        functionSymbol.getClass();
        processor.getClass();
        return ProcessorAction.NONE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenPropertiesWithBaseScope(FirPropertySymbol propertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        propertySymbol.getClass();
        processor.getClass();
        return ProcessorAction.NONE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (this.names.contains(name)) {
            return;
        }
        super.processFunctionsByName(name, processor);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(final Name name, final Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        if (this.names.contains(name)) {
            this.delegateScope.processFunctionsByName(name, new Function1() { // from class: gb7
                public final Object invoke(Object obj) {
                    return JavaAnnotationSyntheticPropertiesScope.c(this.b, processor, name, (FirNamedFunctionSymbol) obj);
                }
            });
        }
    }

    public String toString() {
        return "Java annotation synthetic properties scope for " + this.classId;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public JavaAnnotationSyntheticPropertiesScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new JavaAnnotationSyntheticPropertiesScope(newSession, this.owner, this.delegateScope.withReplacedSessionOrNull(newSession, newScopeSession));
    }
}

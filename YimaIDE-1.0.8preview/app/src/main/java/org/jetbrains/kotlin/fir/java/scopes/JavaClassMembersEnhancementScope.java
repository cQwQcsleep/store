package org.jetbrains.kotlin.fir.java.scopes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.java.enhancement.FirSignatureEnhancement;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassMembersEnhancementScope;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ(\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0016\u0010\u0016\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0018\u0012\u0004\u0012\u00020\u00130\u0017H\u0016J$\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130\u0017H\u0016J\u0012\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b*\u00020\u001cH\u0002J\u001c\u0010\u001d\u001a\u00020\u00132\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00130\u0017H\u0016J*\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\f2\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020 0\"H\u0016J*\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020\u000f2\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020 0\"H\u0016J\u0088\u0001\u0010&\u001a\u00020 \"\f\b\u0000\u0010'*\u0006\u0012\u0002\b\u00030(2\u0006\u0010)\u001a\u0002H'2\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020 0\"2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u0002H'0+25\u0010,\u001a1\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u0002H'\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020 0\"\u0012\u0004\u0012\u00020 0-¢\u0006\u0002\b.H\u0002¢\u0006\u0002\u0010/J\n\u00100\u001a\u000201H\u0096\u0080\u0004J\u001c\u00102\u001a\u00020\u00002\u0006\u00103\u001a\u00020\u00032\u0006\u00104\u001a\u000205H\u0017b\u0002\b6R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000bj\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassMembersEnhancementScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirDelegatingTypeScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "useSiteMemberScope", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassUseSiteMemberScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassUseSiteMemberScope;)V", "enhancedToOriginalFunctions", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lkotlin/collections/HashMap;", "enhancedToOriginalProperties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "signatureEnhancement", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirSignatureEnhancement;", "processPropertiesByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "processor", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "processFunctionsByName", "overriddenMembers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "processDeclaredConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "processDirectOverriddenFunctionsWithBaseScope", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "functionSymbol", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "processDirectOverriddenPropertiesWithBaseScope", "propertySymbol", "doProcessDirectOverriddenCallables", "S", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "callableSymbol", "enhancedToOriginalMap", Argument.Delimiters.none, "processDirectOverriddenCallables", "Lkotlin/Function3;", "Lkotlin/ExtensionFunctionType;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function2;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "toString", Argument.Delimiters.none, "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaClassMembersEnhancementScope extends FirDelegatingTypeScope {
    private final HashMap<FirNamedFunctionSymbol, FirNamedFunctionSymbol> enhancedToOriginalFunctions;
    private final HashMap<FirPropertySymbol, FirPropertySymbol> enhancedToOriginalProperties;
    private final FirRegularClassSymbol owner;
    private final FirSignatureEnhancement signatureEnhancement;
    private final JavaClassUseSiteMemberScope useSiteMemberScope;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.scopes.JavaClassMembersEnhancementScope$processDirectOverriddenFunctionsWithBaseScope$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirNamedFunctionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(3, FirTypeScope.class, "processDirectOverriddenFunctionsWithBaseScope", "processDirectOverriddenFunctionsWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
        }

        public final ProcessorAction invoke(FirTypeScope firTypeScope, FirNamedFunctionSymbol firNamedFunctionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
            firTypeScope.getClass();
            firNamedFunctionSymbol.getClass();
            function2.getClass();
            return firTypeScope.processDirectOverriddenFunctionsWithBaseScope(firNamedFunctionSymbol, function2);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.scopes.JavaClassMembersEnhancementScope$processDirectOverriddenPropertiesWithBaseScope$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00351 extends FunctionReferenceImpl implements Function3<FirTypeScope, FirPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction>, ProcessorAction> {
        public static final C00351 INSTANCE = new C00351();

        public C00351() {
            super(3, FirTypeScope.class, "processDirectOverriddenPropertiesWithBaseScope", "processDirectOverriddenPropertiesWithBaseScope(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", 0);
        }

        public final ProcessorAction invoke(FirTypeScope firTypeScope, FirPropertySymbol firPropertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> function2) {
            firTypeScope.getClass();
            firPropertySymbol.getClass();
            function2.getClass();
            return firTypeScope.processDirectOverriddenPropertiesWithBaseScope(firPropertySymbol, function2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public JavaClassMembersEnhancementScope(FirSession firSession, FirRegularClassSymbol firRegularClassSymbol, JavaClassUseSiteMemberScope javaClassUseSiteMemberScope) {
        super(javaClassUseSiteMemberScope);
        firSession.getClass();
        firRegularClassSymbol.getClass();
        javaClassUseSiteMemberScope.getClass();
        this.owner = firRegularClassSymbol;
        this.useSiteMemberScope = javaClassUseSiteMemberScope;
        this.enhancedToOriginalFunctions = new HashMap<>();
        this.enhancedToOriginalProperties = new HashMap<>();
        this.signatureEnhancement = new FirSignatureEnhancement((FirRegularClass) firRegularClassSymbol.getFir(), firSession, false, new Function1() { // from class: rb7
            public final Object invoke(Object obj) {
                return JavaClassMembersEnhancementScope.b(this.b, (FirCallableDeclaration) obj);
            }
        }, 4, null);
    }

    public static List b(JavaClassMembersEnhancementScope javaClassMembersEnhancementScope, FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return javaClassMembersEnhancementScope.overriddenMembers(firCallableDeclaration);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Unit c(JavaClassMembersEnhancementScope javaClassMembersEnhancementScope, Name name, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        FirNamedFunctionSymbol symbol = ((FirNamedFunction) FirSignatureEnhancement.enhancedFunction$default(javaClassMembersEnhancementScope.signatureEnhancement, firNamedFunctionSymbol, name, null, 4, null).getFir()).getSymbol();
        javaClassMembersEnhancementScope.enhancedToOriginalFunctions.put(symbol, firNamedFunctionSymbol);
        function1.invoke(symbol);
        return Unit.INSTANCE;
    }

    public static Unit d(JavaClassMembersEnhancementScope javaClassMembersEnhancementScope, Function1 function1, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        function1.invoke(javaClassMembersEnhancementScope.signatureEnhancement.enhancedConstructor(firConstructorSymbol));
        return Unit.INSTANCE;
    }

    private final <S extends FirCallableSymbol<?>> ProcessorAction doProcessDirectOverriddenCallables(S callableSymbol, Function2<? super S, ? super FirTypeScope, ? extends ProcessorAction> processor, Map<S, ? extends S> enhancedToOriginalMap, Function3<? super FirTypeScope, ? super S, ? super Function2<? super S, ? super FirTypeScope, ? extends ProcessorAction>, ? extends ProcessorAction> processDirectOverriddenCallables) {
        if (Intrinsics.areEqual(callableSymbol.getOrigin(), FirDeclarationOrigin.RenamedForOverride.INSTANCE)) {
            FirNamedFunctionSymbol initialSignatureAttr = ClassMembersKt.getInitialSignatureAttr((FirCallableDeclaration) callableSymbol.getFir());
            if (initialSignatureAttr == null) {
                initialSignatureAttr = null;
            }
            if (initialSignatureAttr != null) {
                callableSymbol = initialSignatureAttr;
            }
        }
        S s = enhancedToOriginalMap.get(callableSymbol);
        return s == null ? ProcessorAction.NONE : (ProcessorAction) processDirectOverriddenCallables.invoke(this.useSiteMemberScope, s, processor);
    }

    public static Unit e(JavaClassMembersEnhancementScope javaClassMembersEnhancementScope, Name name, Function1 function1, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        FirVariableSymbol<?> firVariableSymbolEnhancedProperty = javaClassMembersEnhancementScope.signatureEnhancement.enhancedProperty(firVariableSymbol, name);
        if ((firVariableSymbol instanceof FirPropertySymbol) && (firVariableSymbolEnhancedProperty instanceof FirPropertySymbol)) {
            javaClassMembersEnhancementScope.enhancedToOriginalProperties.put((FirPropertySymbol) firVariableSymbolEnhancedProperty, (FirPropertySymbol) firVariableSymbol);
        }
        function1.invoke(firVariableSymbolEnhancedProperty);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<FirCallableDeclaration> overriddenMembers(FirCallableDeclaration firCallableDeclaration) {
        List directOverriddenMembers$default = FirTypeScopeKt.getDirectOverriddenMembers$default(this.useSiteMemberScope, firCallableDeclaration.getSymbol(), false, 2, null);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(directOverriddenMembers$default, 10));
        Iterator it = directOverriddenMembers$default.iterator();
        while (it.hasNext()) {
            arrayList.add((FirCallableDeclaration) ((FirCallableSymbol) it.next()).getFir());
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processDeclaredConstructors(final Function1<? super FirConstructorSymbol, Unit> processor) {
        processor.getClass();
        this.useSiteMemberScope.processDeclaredConstructors(new Function1() { // from class: qb7
            public final Object invoke(Object obj) {
                return JavaClassMembersEnhancementScope.d(this.b, processor, (FirConstructorSymbol) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenFunctionsWithBaseScope(FirNamedFunctionSymbol functionSymbol, Function2<? super FirNamedFunctionSymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        functionSymbol.getClass();
        processor.getClass();
        return doProcessDirectOverriddenCallables(functionSymbol, processor, this.enhancedToOriginalFunctions, AnonymousClass1.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope
    public ProcessorAction processDirectOverriddenPropertiesWithBaseScope(FirPropertySymbol propertySymbol, Function2<? super FirPropertySymbol, ? super FirTypeScope, ? extends ProcessorAction> processor) {
        propertySymbol.getClass();
        processor.getClass();
        return doProcessDirectOverriddenCallables(propertySymbol, processor, this.enhancedToOriginalProperties, C00351.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(final Name name, final Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.useSiteMemberScope.processFunctionsByName(name, new Function1() { // from class: ob7
            public final Object invoke(Object obj) {
                return JavaClassMembersEnhancementScope.c(this.b, name, processor, (FirNamedFunctionSymbol) obj);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(final Name name, final Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        this.useSiteMemberScope.processPropertiesByName(name, new Function1() { // from class: pb7
            public final Object invoke(Object obj) {
                return JavaClassMembersEnhancementScope.e(this.b, name, processor, (FirVariableSymbol) obj);
            }
        });
    }

    public String toString() {
        return "Java enhancement scope for " + this.owner.getClassId();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirDelegatingTypeScope, org.jetbrains.kotlin.fir.scopes.FirTypeScope, org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public JavaClassMembersEnhancementScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        return new JavaClassMembersEnhancementScope(newSession, this.owner, this.useSiteMemberScope.withReplacedSessionOrNull(newSession, newScopeSession));
    }
}

package org.jetbrains.kotlin.fir.java.scopes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassStaticUseSiteScope;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.scopes.DelicateScopeAPI;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0001\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00170\u001aH\u0016J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c2\u0006\u0010\u0018\u001a\u00020\u000eH\u0002J(\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0016\u0010\u0019\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0013\u0012\u0004\u0012\u00020\u00170\u001aH\u0016J\u001a\u0010\u001e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u001c2\u0006\u0010\u0018\u001a\u00020\u000eH\u0002J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0 H\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000e0 H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\u000eH\u0016J\u001e\u0010(\u001a\u0004\u0018\u00010\u00002\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020+H\u0017b\u0002\b,R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\f\u001a*\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\rj\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\u0012\u001a2\u0012\u0004\u0012\u00020\u000e\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u000f0\rj\u0018\u0012\u0004\u0012\u00020\u000e\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u000f`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006-"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassStaticUseSiteScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "declaredMemberScope", "superClassScope", "superTypesScopes", Argument.Delimiters.none, "klass", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;Ljava/util/List;Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;)V", "functions", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/name/Name;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lkotlin/collections/HashMap;", "properties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "overrideChecker", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaOverrideChecker;", "processFunctionsByName", Argument.Delimiters.none, ModuleXmlParser.NAME, "processor", "Lkotlin/Function1;", "computeFunctions", Argument.Delimiters.none, "processPropertiesByName", "computeProperties", "getCallableNames", Argument.Delimiters.none, "getClassifierNames", "mayContainName", Argument.Delimiters.none, "scopeOwnerLookupNames", Argument.Delimiters.none, "getScopeOwnerLookupNames", "()Ljava/util/List;", "withReplacedSessionOrNull", "newSession", "newScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "Lorg/jetbrains/kotlin/fir/scopes/DelicateScopeAPI;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaClassStaticUseSiteScope extends FirContainingNamesAwareScope {
    private final FirContainingNamesAwareScope declaredMemberScope;
    private final HashMap<Name, Collection<FirNamedFunctionSymbol>> functions;
    private final FirJavaClass klass;
    private final JavaOverrideChecker overrideChecker;
    private final HashMap<Name, Collection<FirVariableSymbol<?>>> properties;
    private final FirContainingNamesAwareScope superClassScope;
    private final List<FirContainingNamesAwareScope> superTypesScopes;

    /* JADX WARN: Multi-variable type inference failed */
    public JavaClassStaticUseSiteScope(FirSession firSession, FirContainingNamesAwareScope firContainingNamesAwareScope, FirContainingNamesAwareScope firContainingNamesAwareScope2, List<? extends FirContainingNamesAwareScope> list, FirJavaClass firJavaClass) {
        firSession.getClass();
        firContainingNamesAwareScope.getClass();
        firContainingNamesAwareScope2.getClass();
        list.getClass();
        firJavaClass.getClass();
        this.declaredMemberScope = firContainingNamesAwareScope;
        this.superClassScope = firContainingNamesAwareScope2;
        this.superTypesScopes = list;
        this.klass = firJavaClass;
        this.functions = new HashMap<>();
        this.properties = new HashMap<>();
        this.overrideChecker = new JavaOverrideChecker(firSession, firJavaClass, null, false);
    }

    public static Unit b(List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (!firVariableSymbol.getRawStatus().isStatic()) {
            return Unit.INSTANCE;
        }
        list.add(firVariableSymbol);
        return Unit.INSTANCE;
    }

    private final List<FirNamedFunctionSymbol> computeFunctions(Name name) {
        final ArrayList arrayList = new ArrayList();
        this.superClassScope.processFunctionsByName(name, new Function1() { // from class: yb7
            public final Object invoke(Object obj) {
                return JavaClassStaticUseSiteScope.e(arrayList, (FirNamedFunctionSymbol) obj);
            }
        });
        final ArrayList arrayList2 = new ArrayList();
        this.declaredMemberScope.processFunctionsByName(name, new Function1() { // from class: zb7
            public final Object invoke(Object obj) {
                return JavaClassStaticUseSiteScope.d(arrayList2, arrayList, this, (FirNamedFunctionSymbol) obj);
            }
        });
        CollectionsKt.addAll(arrayList2, arrayList);
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean computeFunctions$lambda$1$0(JavaClassStaticUseSiteScope javaClassStaticUseSiteScope, FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol2) {
        firNamedFunctionSymbol2.getClass();
        return javaClassStaticUseSiteScope.overrideChecker.isOverriddenFunction((FirNamedFunction) firNamedFunctionSymbol.getFir(), (FirNamedFunction) firNamedFunctionSymbol2.getFir());
    }

    private final List<FirVariableSymbol<?>> computeProperties(Name name) {
        final ArrayList arrayList = new ArrayList();
        this.declaredMemberScope.processPropertiesByName(name, new Function1() { // from class: wb7
            public final Object invoke(Object obj) {
                return JavaClassStaticUseSiteScope.b(arrayList, (FirVariableSymbol) obj);
            }
        });
        if (arrayList.isEmpty()) {
            Iterator<FirContainingNamesAwareScope> it = this.superTypesScopes.iterator();
            while (it.hasNext()) {
                it.next().processPropertiesByName(name, new Function1() { // from class: xb7
                    public final Object invoke(Object obj) {
                        return JavaClassStaticUseSiteScope.f(arrayList, (FirVariableSymbol) obj);
                    }
                });
            }
        }
        return arrayList;
    }

    public static Unit d(List list, List list2, final JavaClassStaticUseSiteScope javaClassStaticUseSiteScope, final FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (!firNamedFunctionSymbol.getRawStatus().isStatic()) {
            return Unit.INSTANCE;
        }
        list.add(firNamedFunctionSymbol);
        CollectionsKt.removeAll(list2, new Function1() { // from class: ac7
            public final Object invoke(Object obj) {
                return Boolean.valueOf(JavaClassStaticUseSiteScope.computeFunctions$lambda$1$0(this.b, firNamedFunctionSymbol, (FirNamedFunctionSymbol) obj));
            }
        });
        return Unit.INSTANCE;
    }

    public static Unit e(List list, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        list.add(firNamedFunctionSymbol);
        return Unit.INSTANCE;
    }

    public static Unit f(List list, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (!firVariableSymbol.getRawStatus().isStatic()) {
            return Unit.INSTANCE;
        }
        list.add(firVariableSymbol);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getCallableNames() {
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        setCreateSetBuilder.addAll(this.declaredMemberScope.getCallableNames());
        Iterator<FirContainingNamesAwareScope> it = this.superTypesScopes.iterator();
        while (it.hasNext()) {
            setCreateSetBuilder.addAll(it.next().getCallableNames());
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope
    public Set<Name> getClassifierNames() {
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        setCreateSetBuilder.addAll(this.declaredMemberScope.getClassifierNames());
        Iterator<FirContainingNamesAwareScope> it = this.superTypesScopes.iterator();
        while (it.hasNext()) {
            setCreateSetBuilder.addAll(it.next().getClassifierNames());
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public List<String> getScopeOwnerLookupNames() {
        return this.declaredMemberScope.getScopeOwnerLookupNames();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public boolean mayContainName(Name name) {
        name.getClass();
        if (this.declaredMemberScope.mayContainName(name)) {
            return true;
        }
        List<FirContainingNamesAwareScope> list = this.superTypesScopes;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((FirContainingNamesAwareScope) it.next()).mayContainName(name)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processFunctionsByName(Name name, Function1<? super FirNamedFunctionSymbol, Unit> processor) {
        name.getClass();
        processor.getClass();
        HashMap<Name, Collection<FirNamedFunctionSymbol>> map = this.functions;
        List<FirNamedFunctionSymbol> listComputeFunctions = map.get(name);
        if (listComputeFunctions == null) {
            listComputeFunctions = computeFunctions(name);
            map.put(name, listComputeFunctions);
        }
        Iterator<T> it = listComputeFunctions.iterator();
        while (it.hasNext()) {
            processor.invoke(it.next());
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScope
    public void processPropertiesByName(Name name, Function1<? super FirVariableSymbol<?>, Unit> processor) {
        name.getClass();
        processor.getClass();
        HashMap<Name, Collection<FirVariableSymbol<?>>> map = this.properties;
        List<FirVariableSymbol<?>> listComputeProperties = map.get(name);
        if (listComputeProperties == null) {
            listComputeProperties = computeProperties(name);
            map.put(name, listComputeProperties);
        }
        Iterator<T> it = listComputeProperties.iterator();
        while (it.hasNext()) {
            processor.invoke(it.next());
        }
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope, org.jetbrains.kotlin.fir.scopes.FirScope
    @DelicateScopeAPI
    public JavaClassStaticUseSiteScope withReplacedSessionOrNull(FirSession newSession, ScopeSession newScopeSession) {
        newSession.getClass();
        newScopeSession.getClass();
        FirContainingNamesAwareScope firContainingNamesAwareScopeWithReplacedSessionOrNull = this.declaredMemberScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firContainingNamesAwareScopeWithReplacedSessionOrNull == null) {
            firContainingNamesAwareScopeWithReplacedSessionOrNull = this.declaredMemberScope;
        }
        FirContainingNamesAwareScope firContainingNamesAwareScope = firContainingNamesAwareScopeWithReplacedSessionOrNull;
        FirContainingNamesAwareScope firContainingNamesAwareScopeWithReplacedSessionOrNull2 = this.superClassScope.withReplacedSessionOrNull(newSession, newScopeSession);
        if (firContainingNamesAwareScopeWithReplacedSessionOrNull2 == null) {
            firContainingNamesAwareScopeWithReplacedSessionOrNull2 = this.superClassScope;
        }
        FirContainingNamesAwareScope firContainingNamesAwareScope2 = firContainingNamesAwareScopeWithReplacedSessionOrNull2;
        List<FirContainingNamesAwareScope> list = this.superTypesScopes;
        List arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        boolean z = false;
        while (true) {
            FirScope firScope = null;
            if (!it.hasNext()) {
                break;
            }
            FirScope firScope2 = (FirScope) it.next();
            FirScope firScopeWithReplacedSessionOrNull = firScope2.withReplacedSessionOrNull(newSession, newScopeSession);
            if (firScopeWithReplacedSessionOrNull != null) {
                z = true;
                firScope = firScopeWithReplacedSessionOrNull;
            }
            FirContainingNamesAwareScope firContainingNamesAwareScope3 = (FirContainingNamesAwareScope) firScope;
            if (firContainingNamesAwareScope3 != null) {
                firScope2 = firContainingNamesAwareScope3;
            }
            arrayList.add(firScope2);
        }
        if (!z) {
            arrayList = null;
        }
        if (arrayList == null) {
            arrayList = this.superTypesScopes;
        }
        return new JavaClassStaticUseSiteScope(newSession, firContainingNamesAwareScope, firContainingNamesAwareScope2, arrayList, this.klass);
    }
}

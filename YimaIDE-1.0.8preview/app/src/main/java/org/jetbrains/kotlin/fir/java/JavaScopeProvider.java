package org.jetbrains.kotlin.fir.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.java.JavaScopeProvider;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.java.scopes.JavaAnnotationSyntheticPropertiesScope;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassMembersEnhancementScope;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassStaticEnhancementScope;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassStaticUseSiteScope;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassUseSiteMemberScope;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSessionKey;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirNameAwareOnlyCallablesScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.DFS;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J(\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J*\u0010\u0018\u001a\u00020\u00172\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J*\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\"\u0010 \u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\"\u0010!\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J0\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001c0%H\u0002J\u0017\u0010&\u001a\u0004\u0018\u00010\u001c*\u00020\u001c2\u0006\u0010\b\u001a\u00020\tH\u0082\u0010J\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0(*\u00020\u001c2\u0006\u0010\b\u001a\u00020\tH\u0002J\"\u0010)\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/JavaScopeProvider;", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "<init>", "()V", "getUseSiteMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "memberRequiredPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getTypealiasConstructorScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "buildSyntheticScopeForAnnotations", "session", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "enhancementScope", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassMembersEnhancementScope;", "buildJavaEnhancementScope", "buildDeclaredMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "buildUseSiteMemberScopeWithJavaTypes", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassUseSiteMemberScope;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "getStaticCallableMemberScope", "getStaticCallableMemberScopeForBackend", "getStaticMemberScopeForCallables", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassStaticEnhancementScope;", "visitedClasses", Argument.Delimiters.none, "findJavaSuperClass", "findClosestJavaSuperTypes", Argument.Delimiters.none, "getNestedClassifierScope", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaScopeProvider extends FirScopeProvider {
    public static final JavaScopeProvider INSTANCE = new JavaScopeProvider();

    private JavaScopeProvider() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Iterable a(FirSession firSession, FirRegularClass firRegularClass) {
        FirRegularClassSymbol regularClassSymbol;
        List<ConeKotlinType> resolvedSuperTypes = firRegularClass.getSymbol().getResolvedSuperTypes();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = resolvedSuperTypes.iterator();
        while (it.hasNext()) {
            ConeClassifierLookupTag lookupTagIfAny = ConeTypeUtilsKt.getLookupTagIfAny((ConeKotlinType) it.next());
            FirRegularClass firRegularClass2 = (lookupTagIfAny == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(lookupTagIfAny, firSession)) == null) ? null : (FirRegularClass) regularClassSymbol.getFir();
            if (firRegularClass2 != null) {
                arrayList.add(firRegularClass2);
            }
        }
        return arrayList;
    }

    private final FirContainingNamesAwareScope buildDeclaredMemberScope(FirSession useSiteSession, FirRegularClass regularClass) {
        return regularClass instanceof FirJavaClass ? FirDeclaredMemberScopeProviderKt.declaredMemberScopeWithLazyNestedScope(useSiteSession, regularClass, ((FirJavaClass) regularClass).getExistingNestedClassifierNames$org_jetbrains_kotlin_fir_jvm()) : FirDeclaredMemberScopeProviderKt.declaredMemberScope(useSiteSession, regularClass, (FirResolvePhase) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final JavaClassMembersEnhancementScope buildJavaEnhancementScope(FirSession useSiteSession, FirRegularClassSymbol symbol, ScopeSession scopeSession, FirResolvePhase memberRequiredPhase) {
        ScopeSessionKey<?, ?> scopeSessionKey = JavaScopeProviderKt.JAVA_ENHANCEMENT;
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(symbol);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(symbol, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        Object obj = map2.get(scopeSessionKey);
        if (obj == null) {
            FirRegularClass firRegularClass = (FirRegularClass) symbol.getFir();
            if (!(firRegularClass instanceof FirJavaClass)) {
                StringBuilder sb = new StringBuilder();
                sb.append(FirDeclarationUtilKt.getClassId(firRegularClass));
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(firRegularClass.getClass());
                sb.append(" is expected to be FirJavaClass, but ");
                sb.append(orCreateKotlinClass);
                sb.append(" found");
                throw new IllegalArgumentException(sb.toString().toString());
            }
            JavaClassMembersEnhancementScope javaClassMembersEnhancementScope = new JavaClassMembersEnhancementScope(useSiteSession, symbol, INSTANCE.buildUseSiteMemberScopeWithJavaTypes((FirJavaClass) firRegularClass, useSiteSession, scopeSession, memberRequiredPhase));
            map2.put(scopeSessionKey, javaClassMembersEnhancementScope);
            obj = javaClassMembersEnhancementScope;
        }
        return (JavaClassMembersEnhancementScope) obj;
    }

    private final FirTypeScope buildSyntheticScopeForAnnotations(FirSession session, FirRegularClassSymbol symbol, ScopeSession scopeSession, JavaClassMembersEnhancementScope enhancementScope) {
        ScopeSessionKey<?, ?> scopeSessionKey = JavaScopeProviderKt.JAVA_SYNTHETIC_FOR_ANNOTATIONS;
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(symbol);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(symbol, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        Object javaAnnotationSyntheticPropertiesScope = map2.get(scopeSessionKey);
        if (javaAnnotationSyntheticPropertiesScope == null) {
            javaAnnotationSyntheticPropertiesScope = new JavaAnnotationSyntheticPropertiesScope(session, symbol, enhancementScope);
            map2.put(scopeSessionKey, javaAnnotationSyntheticPropertiesScope);
        }
        return (JavaAnnotationSyntheticPropertiesScope) javaAnnotationSyntheticPropertiesScope;
    }

    private final JavaClassUseSiteMemberScope buildUseSiteMemberScopeWithJavaTypes(FirJavaClass regularClass, FirSession useSiteSession, ScopeSession scopeSession, FirResolvePhase memberRequiredPhase) {
        FirJavaClass firJavaClass;
        FirSession firSession;
        List listLookupSuperTypes$default;
        FirRegularClassSymbol symbol = regularClass.getSymbol();
        ScopeSessionKey<?, ?> scopeSessionKey = JavaScopeProviderKt.JAVA_USE_SITE;
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(symbol);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(symbol, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        Object obj = map2.get(scopeSessionKey);
        if (obj == null) {
            FirContainingNamesAwareScope firContainingNamesAwareScopeBuildDeclaredMemberScope = INSTANCE.buildDeclaredMemberScope(useSiteSession, regularClass);
            if (SupertypeUtilsKt.isThereLoopInSupertypes(regularClass, useSiteSession)) {
                listLookupSuperTypes$default = CollectionsKt.listOf(TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getAny(), null, false, null, 7, null));
                firJavaClass = regularClass;
                firSession = useSiteSession;
            } else {
                firJavaClass = regularClass;
                firSession = useSiteSession;
                listLookupSuperTypes$default = SupertypeUtilsKt.lookupSuperTypes$default(firJavaClass, true, false, firSession, true, null, 32, null);
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = listLookupSuperTypes$default.iterator();
            while (it.hasNext()) {
                FirTypeScope firTypeScopeScopeForSupertype = FirKotlinScopeProviderKt.scopeForSupertype((ConeClassLikeType) it.next(), firSession, scopeSession, firJavaClass, memberRequiredPhase);
                if (firTypeScopeScopeForSupertype != null) {
                    arrayList.add(firTypeScopeScopeForSupertype);
                }
            }
            JavaClassUseSiteMemberScope javaClassUseSiteMemberScope = new JavaClassUseSiteMemberScope(firJavaClass, firSession, arrayList, firContainingNamesAwareScopeBuildDeclaredMemberScope);
            map2.put(scopeSessionKey, javaClassUseSiteMemberScope);
            obj = javaClassUseSiteMemberScope;
        }
        return (JavaClassUseSiteMemberScope) obj;
    }

    private final Collection<FirRegularClass> findClosestJavaSuperTypes(final FirRegularClass firRegularClass, final FirSession firSession) {
        final ArrayList arrayList = new ArrayList();
        DFS.dfs(CollectionsKt.listOf(firRegularClass), new DFS.Neighbors() { // from class: fl7
            public final Iterable getNeighbors(Object obj) {
                return JavaScopeProvider.a(firSession, (FirRegularClass) obj);
            }
        }, new DFS.AbstractNodeHandler<FirRegularClass, Unit>() { // from class: org.jetbrains.kotlin.fir.java.JavaScopeProvider.findClosestJavaSuperTypes.2
            public boolean beforeChildren(FirRegularClass current) {
                if (firRegularClass == current || !(current instanceof FirJavaClass)) {
                    return true;
                }
                arrayList.add(current);
                return false;
            }

            public /* bridge */ /* synthetic */ Object result() {
                m549result();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: result, reason: collision with other method in class */
            public void m549result() {
            }
        });
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirRegularClass findJavaSuperClass(FirRegularClass firRegularClass, FirSession firSession) {
        ConeClassifierLookupTag lookupTagIfAny;
        FirRegularClassSymbol regularClassSymbol;
        do {
            Iterator<T> it = firRegularClass.getSymbol().getResolvedSuperTypes().iterator();
            do {
                if (!it.hasNext()) {
                    firRegularClass = null;
                    break;
                }
                ConeKotlinType coneKotlinType = (ConeKotlinType) it.next();
                if (ConeBuiltinTypeUtilsKt.isAny(coneKotlinType) || (lookupTagIfAny = ConeTypeUtilsKt.getLookupTagIfAny(coneKotlinType)) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(lookupTagIfAny, firSession)) == null || (firRegularClass = (FirRegularClass) regularClassSymbol.getFir()) == null || firRegularClass.getClassKind() != ClassKind.CLASS) {
                    firRegularClass = null;
                }
            } while (firRegularClass == null);
            if (firRegularClass == null) {
                return null;
            }
        } while (!(firRegularClass.getOrigin() instanceof FirDeclarationOrigin.Java));
        return firRegularClass;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0059  */
    private final JavaClassStaticEnhancementScope getStaticMemberScopeForCallables(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession, Set<FirRegularClass> visitedClasses) {
        FirContainingNamesAwareScope staticMemberScopeForCallables;
        if (!(klass instanceof FirJavaClass) || !visitedClasses.add(klass)) {
            return null;
        }
        FirJavaClass firJavaClass = (FirJavaClass) klass;
        FirRegularClassSymbol symbol = firJavaClass.getSymbol();
        ScopeSessionKey<?, ?> scopeSessionKey = JavaScopeProviderKt.JAVA_ENHANCEMENT_FOR_STATIC;
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(symbol);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(symbol, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        Object obj = map2.get(scopeSessionKey);
        if (obj == null) {
            JavaScopeProvider javaScopeProvider = INSTANCE;
            FirRegularClass firRegularClass = (FirRegularClass) klass;
            FirContainingNamesAwareScope firContainingNamesAwareScopeBuildDeclaredMemberScope = javaScopeProvider.buildDeclaredMemberScope(useSiteSession, firRegularClass);
            FirRegularClass firRegularClassFindJavaSuperClass = javaScopeProvider.findJavaSuperClass(firRegularClass, useSiteSession);
            if (firRegularClassFindJavaSuperClass != null) {
                FirScopeProvider scopeProvider = firRegularClassFindJavaSuperClass.getScopeProvider();
                JavaScopeProvider javaScopeProvider2 = scopeProvider instanceof JavaScopeProvider ? (JavaScopeProvider) scopeProvider : null;
                staticMemberScopeForCallables = javaScopeProvider2 != null ? javaScopeProvider2.getStaticMemberScopeForCallables(firRegularClassFindJavaSuperClass, useSiteSession, scopeSession, visitedClasses) : null;
                if (staticMemberScopeForCallables == null) {
                    staticMemberScopeForCallables = FirTypeScope.Empty.INSTANCE;
                }
            } else {
                staticMemberScopeForCallables = FirTypeScope.Empty.INSTANCE;
            }
            Collection<FirRegularClass> collectionFindClosestJavaSuperTypes = javaScopeProvider.findClosestJavaSuperTypes(firRegularClass, useSiteSession);
            ArrayList arrayList = new ArrayList();
            for (FirRegularClass firRegularClass2 : collectionFindClosestJavaSuperTypes) {
                FirScopeProvider scopeProvider2 = firRegularClass2.getScopeProvider();
                JavaScopeProvider javaScopeProvider3 = scopeProvider2 instanceof JavaScopeProvider ? (JavaScopeProvider) scopeProvider2 : null;
                JavaClassStaticEnhancementScope staticMemberScopeForCallables2 = javaScopeProvider3 != null ? javaScopeProvider3.getStaticMemberScopeForCallables(firRegularClass2, useSiteSession, scopeSession, visitedClasses) : null;
                if (staticMemberScopeForCallables2 != null) {
                    arrayList.add(staticMemberScopeForCallables2);
                }
            }
            JavaClassStaticEnhancementScope javaClassStaticEnhancementScope = new JavaClassStaticEnhancementScope(useSiteSession, firJavaClass.getSymbol(), new JavaClassStaticUseSiteScope(useSiteSession, firContainingNamesAwareScopeBuildDeclaredMemberScope, staticMemberScopeForCallables, arrayList, firJavaClass));
            map2.put(scopeSessionKey, javaClassStaticEnhancementScope);
            obj = javaClassStaticEnhancementScope;
        }
        JavaClassStaticEnhancementScope javaClassStaticEnhancementScope2 = (JavaClassStaticEnhancementScope) obj;
        visitedClasses.remove(klass);
        return javaClassStaticEnhancementScope2;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScopeProvider
    public FirContainingNamesAwareScope getNestedClassifierScope(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession) {
        klass.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        return FirDeclaredMemberScopeProviderKt.lazyNestedClassifierScope(useSiteSession, FirDeclarationUtilKt.getClassId(klass), ((FirJavaClass) klass).getExistingNestedClassifierNames$org_jetbrains_kotlin_fir_jvm());
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScopeProvider
    public FirContainingNamesAwareScope getStaticCallableMemberScope(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession) {
        klass.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        JavaClassStaticEnhancementScope staticMemberScopeForCallables = getStaticMemberScopeForCallables(klass, useSiteSession, scopeSession, new HashSet());
        if (staticMemberScopeForCallables == null) {
            return null;
        }
        return new FirNameAwareOnlyCallablesScope(staticMemberScopeForCallables);
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScopeProvider
    public FirContainingNamesAwareScope getStaticCallableMemberScopeForBackend(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession) {
        klass.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        return getStaticCallableMemberScope(klass, useSiteSession, scopeSession);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.scopes.FirScopeProvider
    public FirScope getTypealiasConstructorScope(FirTypeAlias typeAlias, FirSession useSiteSession, ScopeSession scopeSession) throws KotlinNothingValueException {
        typeAlias.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        AddToStdlibKt.shouldNotBeCalled("Java doesn't support typealias");
        throw new KotlinNothingValueException();
    }

    @Override // org.jetbrains.kotlin.fir.scopes.FirScopeProvider
    public FirTypeScope getUseSiteMemberScope(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession, FirResolvePhase memberRequiredPhase) {
        klass.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        FirClassSymbol<FirClass> symbol = klass.getSymbol();
        symbol.getClass();
        FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) symbol;
        JavaClassMembersEnhancementScope javaClassMembersEnhancementScopeBuildJavaEnhancementScope = buildJavaEnhancementScope(useSiteSession, firRegularClassSymbol, scopeSession, memberRequiredPhase);
        return klass.getClassKind() == ClassKind.ANNOTATION_CLASS ? buildSyntheticScopeForAnnotations(useSiteSession, firRegularClassSymbol, scopeSession, javaClassMembersEnhancementScopeBuildJavaEnhancementScope) : javaClassMembersEnhancementScopeBuildJavaEnhancementScope;
    }
}

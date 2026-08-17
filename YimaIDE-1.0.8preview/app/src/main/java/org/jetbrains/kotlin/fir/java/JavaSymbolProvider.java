package org.jetbrains.kotlin.fir.java;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.java.JavaSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProviderWithoutCallables;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001:\u0001)B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\fH\u0016J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J*\u0010\u0015\u001a\u00020\u00162\u0010\u0010\u0017\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J&\u0010\u001e\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J&\u0010 \u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020!0\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001bH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR*\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010%\u001a\u00020&X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/JavaSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "javaFacade", "Lorg/jetbrains/kotlin/fir/java/FirJavaFacade;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/java/FirJavaFacade;)V", "getJavaFacade", "()Lorg/jetbrains/kotlin/fir/java/FirJavaFacade;", "classCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/java/JavaSymbolProvider$ClassCacheContext;", "getClassCache", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "getClassLikeSymbolByClassId", "classId", "javaClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getTopLevelFunctionSymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getTopLevelPropertySymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "hasPackage", Argument.Delimiters.none, "fqName", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "ClassCacheContext", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class JavaSymbolProvider extends FirSymbolProvider {
    private final FirCache<ClassId, FirRegularClassSymbol, ClassCacheContext> classCache;
    private final FirJavaFacade javaFacade;
    private final FirSymbolNamesProvider symbolNamesProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JavaSymbolProvider(FirSession firSession, FirJavaFacade firJavaFacade) {
        super(firSession);
        firSession.getClass();
        firJavaFacade.getClass();
        this.javaFacade = firJavaFacade;
        this.classCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: jl7
            public final Object invoke(Object obj, Object obj2) {
                return JavaSymbolProvider.a(this.b, (ClassId) obj, (JavaSymbolProvider.ClassCacheContext) obj2);
            }
        });
        this.symbolNamesProvider = new FirSymbolNamesProviderWithoutCallables() { // from class: org.jetbrains.kotlin.fir.java.JavaSymbolProvider$symbolNamesProvider$1
            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificClassifierPackageNamesComputation() {
                return false;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                Set<String> setKnownClassNamesInPackage = this.this$0.getJavaFacade().knownClassNamesInPackage(packageFqName);
                if (setKnownClassNamesInPackage == null) {
                    return null;
                }
                Set<String> set = setKnownClassNamesInPackage;
                if (set.isEmpty()) {
                    return SetsKt.emptySet();
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Iterator<T> it = set.iterator();
                while (it.hasNext()) {
                    linkedHashSet.add(Name.identifier((String) it.next()));
                }
                return linkedHashSet;
            }
        };
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static FirRegularClassSymbol a(JavaSymbolProvider javaSymbolProvider, ClassId classId, ClassCacheContext classCacheContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        JavaClass javaClassFindClass$default;
        classId.getClass();
        if ((classCacheContext == null || (javaClassFindClass$default = classCacheContext.getFoundJavaClass()) == null) && (javaClassFindClass$default = FirJavaFacade.findClass$default(javaSymbolProvider.javaFacade, classId, null, 2, null)) == null) {
            return null;
        }
        FirRegularClassSymbol firRegularClassSymbol = new FirRegularClassSymbol(classId);
        javaSymbolProvider.javaFacade.convertJavaClassToFir(firRegularClassSymbol, classCacheContext != null ? classCacheContext.getParentClassSymbol() : null, javaClassFindClass$default);
        return firRegularClassSymbol;
    }

    public final FirCache<ClassId, FirRegularClassSymbol, ClassCacheContext> getClassCache() {
        return this.classCache;
    }

    public final FirRegularClassSymbol getClassLikeSymbolByClassId(ClassId classId, JavaClass javaClass) {
        classId.getClass();
        FirCache<ClassId, FirRegularClassSymbol, ClassCacheContext> firCache = this.classCache;
        ClassId outerClassId = classId.getOuterClassId();
        return firCache.getValue(classId, new ClassCacheContext(outerClassId != null ? getClassLikeSymbolByClassId(outerClassId, null) : null, javaClass));
    }

    public final FirJavaFacade getJavaFacade() {
        return this.javaFacade;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirSymbolNamesProvider getSymbolNamesProvider() {
        return this.symbolNamesProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return this.javaFacade.hasPackage(fqName);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/JavaSymbolProvider$ClassCacheContext;", Argument.Delimiters.none, "parentClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "foundJavaClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/load/java/structure/JavaClass;)V", "getParentClassSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getFoundJavaClass", "()Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ClassCacheContext {
        private final JavaClass foundJavaClass;
        private final FirRegularClassSymbol parentClassSymbol;

        public /* synthetic */ ClassCacheContext(FirRegularClassSymbol firRegularClassSymbol, JavaClass javaClass, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : firRegularClassSymbol, (i & 2) != 0 ? null : javaClass);
        }

        public final JavaClass getFoundJavaClass() {
            return this.foundJavaClass;
        }

        public final FirRegularClassSymbol getParentClassSymbol() {
            return this.parentClassSymbol;
        }

        public ClassCacheContext(FirRegularClassSymbol firRegularClassSymbol, JavaClass javaClass) {
            this.parentClassSymbol = firRegularClassSymbol;
            this.foundJavaClass = javaClass;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public ClassCacheContext() {
            FirRegularClassSymbol firRegularClassSymbol = null;
            this(firRegularClassSymbol, firRegularClassSymbol, 3, firRegularClassSymbol);
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirRegularClassSymbol getClassLikeSymbolByClassId(ClassId classId) {
        classId.getClass();
        if (this.javaFacade.hasTopLevelClassOf(classId)) {
            return getClassLikeSymbolByClassId(classId, null);
        }
        return null;
    }
}

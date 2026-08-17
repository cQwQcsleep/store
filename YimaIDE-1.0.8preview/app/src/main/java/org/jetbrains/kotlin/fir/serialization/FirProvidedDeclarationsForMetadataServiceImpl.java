package org.jetbrains.kotlin.fir.serialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\u00132\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00132\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\"\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f\u0012\u0004\u0012\u00020\r0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirProvidedDeclarationsForMetadataServiceImpl;", "Lorg/jetbrains/kotlin/fir/serialization/FirProvidedDeclarationsForMetadataService;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "topLevelsCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "memberCache", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "Lorg/jetbrains/kotlin/fir/serialization/FirProvidedDeclarationsForMetadataServiceImpl$ClassDeclarations;", "registerDeclaration", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getProvidedTopLevelDeclarations", Argument.Delimiters.none, "packageFqName", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getProvidedConstructors", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "owner", "getProvidedCallables", "ClassDeclarations", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirProvidedDeclarationsForMetadataServiceImpl extends FirProvidedDeclarationsForMetadataService {
    private final Map<FirClassSymbol<?>, ClassDeclarations> memberCache;
    private final FirSession session;
    private final Map<FqName, List<FirDeclaration>> topLevelsCache;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirProvidedDeclarationsForMetadataServiceImpl$ClassDeclarations;", Argument.Delimiters.none, "<init>", "()V", "providedCallables", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getProvidedCallables", "()Ljava/util/List;", "providedConstructors", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "getProvidedConstructors", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ClassDeclarations {
        private final List<FirCallableDeclaration> providedCallables = new ArrayList();
        private final List<FirConstructor> providedConstructors = new ArrayList();

        public final List<FirCallableDeclaration> getProvidedCallables() {
            return this.providedCallables;
        }

        public final List<FirConstructor> getProvidedConstructors() {
            return this.providedConstructors;
        }
    }

    public FirProvidedDeclarationsForMetadataServiceImpl(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.topLevelsCache = new HashMap();
        this.memberCache = new HashMap();
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirProvidedDeclarationsForMetadataService
    public List<FirCallableDeclaration> getProvidedCallables(FirClassSymbol<?> owner, ScopeSession scopeSession) {
        List<FirCallableDeclaration> providedCallables;
        owner.getClass();
        scopeSession.getClass();
        ClassDeclarations classDeclarations = this.memberCache.get(owner);
        return (classDeclarations == null || (providedCallables = classDeclarations.getProvidedCallables()) == null) ? CollectionsKt.emptyList() : providedCallables;
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirProvidedDeclarationsForMetadataService
    public List<FirConstructor> getProvidedConstructors(FirClassSymbol<?> owner, ScopeSession scopeSession) {
        List<FirConstructor> providedConstructors;
        owner.getClass();
        scopeSession.getClass();
        ClassDeclarations classDeclarations = this.memberCache.get(owner);
        return (classDeclarations == null || (providedConstructors = classDeclarations.getProvidedConstructors()) == null) ? CollectionsKt.emptyList() : providedConstructors;
    }

    @Override // org.jetbrains.kotlin.fir.serialization.FirProvidedDeclarationsForMetadataService
    public List<FirDeclaration> getProvidedTopLevelDeclarations(FqName packageFqName, ScopeSession scopeSession) {
        packageFqName.getClass();
        scopeSession.getClass();
        List<FirDeclaration> list = this.topLevelsCache.get(packageFqName);
        return list == null ? CollectionsKt.emptyList() : list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.serialization.FirProvidedDeclarationsForMetadataService
    public void registerDeclaration(FirCallableDeclaration declaration) {
        FirRegularClassSymbol regularClassSymbol;
        declaration.getClass();
        ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(declaration);
        FirRegularClass firRegularClass = (coneClassLikeLookupTagContainingClassLookupTag == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, this.session)) == null) ? null : (FirRegularClass) regularClassSymbol.getFir();
        if (firRegularClass == null) {
            Map<FqName, List<FirDeclaration>> map = this.topLevelsCache;
            CallableId callableId = declaration.getSymbol().getCallableId();
            callableId.getClass();
            FqName packageName = callableId.getPackageName();
            List<FirDeclaration> arrayList = map.get(packageName);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                map.put(packageName, arrayList);
            }
            arrayList.add(declaration);
            return;
        }
        Map<FirClassSymbol<?>, ClassDeclarations> map2 = this.memberCache;
        FirRegularClassSymbol symbol = firRegularClass.getSymbol();
        ClassDeclarations classDeclarations = map2.get(symbol);
        if (classDeclarations == null) {
            classDeclarations = new ClassDeclarations();
            map2.put(symbol, classDeclarations);
        }
        ClassDeclarations classDeclarations2 = classDeclarations;
        if (declaration instanceof FirConstructor) {
            classDeclarations2.getProvidedConstructors().add(declaration);
        } else {
            classDeclarations2.getProvidedCallables().add(declaration);
        }
    }
}

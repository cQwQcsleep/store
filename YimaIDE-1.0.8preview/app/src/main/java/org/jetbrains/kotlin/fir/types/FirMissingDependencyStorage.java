package org.jetbrains.kotlin.fir.types;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\bJ\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\bH\u0002J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\t*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u0006\u001a \u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/FirMissingDependencyStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "cache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", Argument.Delimiters.none, "getMissingSuperTypes", "declaration", "findMissingSuperTypes", "collectSuperTypes", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMissingDependencyStorage implements FirSessionComponent {
    private final FirCache cache;
    private final FirSession session;

    public FirMissingDependencyStorage(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.cache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.types.FirMissingDependencyStorage$cache$1
            public final Set<ConeKotlinType> invoke(FirClassSymbol<?> firClassSymbol, Void r2) {
                firClassSymbol.getClass();
                return this.this$0.findMissingSuperTypes(firClassSymbol);
            }
        });
    }

    private final Set<ConeKotlinType> collectSuperTypes(FirClassSymbol<?> firClassSymbol, FirSession firSession) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectSuperTypes$collect(linkedHashSet, firSession, firClassSymbol);
        return linkedHashSet;
    }

    private static final void collectSuperTypes$collect(Set<ConeKotlinType> set, FirSession firSession, FirClassSymbol<?> firClassSymbol) {
        FirClassSymbol<?> classSymbol;
        Iterator<FirResolvedTypeRef> it = firClassSymbol.getResolvedSuperTypeRefs().iterator();
        while (it.hasNext()) {
            ConeKotlinType coneType = it.next().getConeType();
            if (!ConeBuiltinTypeUtilsKt.isAny(coneType) && set.add(coneType) && (classSymbol = ToSymbolUtilsKt.toClassSymbol(coneType, firSession)) != null) {
                collectSuperTypes$collect(set, firSession, classSymbol);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<ConeKotlinType> findMissingSuperTypes(FirClassSymbol<?> declaration) {
        Set<ConeKotlinType> setCollectSuperTypes = collectSuperTypes(declaration, this.session);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : setCollectSuperTypes) {
            ConeKotlinType coneKotlinType = (ConeKotlinType) obj;
            if (!(coneKotlinType instanceof ConeErrorType) && !(coneKotlinType instanceof ConeDynamicType)) {
                ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
                if ((coneRigidTypeLowerBoundIfFlexible instanceof ConeLookupTagBasedType) && ToSymbolUtilsKt.toSymbol(coneRigidTypeLowerBoundIfFlexible, this.session) == null) {
                    linkedHashSet.add(obj);
                }
            }
        }
        return linkedHashSet;
    }

    public final Set<ConeKotlinType> getMissingSuperTypes(FirClassSymbol<?> declaration) {
        declaration.getClass();
        return (Set) this.cache.getValue(declaration, null);
    }
}

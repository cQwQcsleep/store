package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a?\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0019\u0010\f\u001a\u0015\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00060\r¢\u0006\u0002\b\u000fH\u0002\u001a-\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0011\"\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\t*\u0002H\u00012\u0006\u0010\u0012\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u0013\u001a/\u0010\u001d\u001a\u00020\u001e\"\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\t*\b\u0012\u0004\u0012\u0002H\u00010\u001f\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0000\"\u001f\u0010\u0017\u001a\u00020\u0018*\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001a*@\u0010\u0000\u001a\u0004\b\u0000\u0010\u0001\"\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00020\u00030\u00022\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00020\u00030\u0002*R\u0010\u0014\"&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u0011\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u00152&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u0011\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u0015ò\u0001\n\n\b\u0012\u0004\u0012\u0002H\u00010\u0016¨\u0006 "}, d2 = {"MembersByScope", "D", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "intersectReturnTypes", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "overrides", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getReturnType", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lkotlin/ExtensionFunctionType;", "withScope", "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "baseScope", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "FirIntersectionOverrideCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection$NonTrivial;", "intersectionOverrideStorage", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirIntersectionOverrideStorage;", "getIntersectionOverrideStorage", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirIntersectionOverrideStorage;", "intersectionOverrideStorage$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "isIntersectionOverride", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeIntersectionScopeContextKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirTypeIntersectionScopeContextKt.class, "intersectionOverrideStorage", "getIntersectionOverrideStorage(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/impl/FirIntersectionOverrideStorage;", 1)};
    private static final ArrayMapAccessor intersectionOverrideStorage$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirIntersectionOverrideStorage.class), (Object) null, 2, (Object) null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirIntersectionOverrideStorage getIntersectionOverrideStorage(FirSession firSession) {
        return (FirIntersectionOverrideStorage) intersectionOverrideStorage$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ConeKotlinType intersectReturnTypes(Collection<? extends FirCallableSymbol<?>> collection, FirSession firSession, Function1<? super FirCallableDeclaration, ? extends ConeKotlinType> function1) {
        Collection<? extends FirCallableSymbol<?>> collection2 = collection;
        FirCallableSymbol firCallableSymbol = (FirCallableSymbol) CollectionsKt.first(collection2);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collection2.iterator();
        while (true) {
            ConeKotlinType coneKotlinTypeSubstituteOrSelf = null;
            if (!it.hasNext()) {
                break;
            }
            FirCallableSymbol firCallableSymbol2 = (FirCallableSymbol) it.next();
            ConeKotlinType coneKotlinType = (ConeKotlinType) function1.invoke(firCallableSymbol2.getFir());
            if (coneKotlinType != null) {
                if (Intrinsics.areEqual(firCallableSymbol2, firCallableSymbol)) {
                    coneKotlinTypeSubstituteOrSelf = coneKotlinType;
                } else {
                    ConeSubstitutor coneSubstitutorBuildSubstitutorForOverridesCheck = FirAbstractOverrideCheckerKt.buildSubstitutorForOverridesCheck((FirCallableDeclaration) firCallableSymbol2.getFir(), (FirCallableDeclaration) firCallableSymbol.getFir(), firSession);
                    if (coneSubstitutorBuildSubstitutorForOverridesCheck != null) {
                        coneKotlinTypeSubstituteOrSelf = coneSubstitutorBuildSubstitutorForOverridesCheck.substituteOrSelf(coneKotlinType);
                    }
                }
            }
            if (coneKotlinTypeSubstituteOrSelf != null) {
                arrayList.add(coneKotlinTypeSubstituteOrSelf);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return TypeComponentsKt.getTypeContext(firSession).m684intersectTypes((Collection) arrayList);
    }

    public static final <D extends FirCallableSymbol<?>> boolean isIntersectionOverride(FirTypeIntersectionScopeContext.ResultOfIntersection<D> resultOfIntersection) {
        resultOfIntersection.getClass();
        return resultOfIntersection instanceof FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <D extends FirCallableSymbol<?>> MemberWithBaseScope<D> withScope(D d, FirTypeScope firTypeScope) {
        return new MemberWithBaseScope<>(d, firTypeScope);
    }
}

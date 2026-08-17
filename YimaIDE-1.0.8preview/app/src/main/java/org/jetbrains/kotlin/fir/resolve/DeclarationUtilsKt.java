package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a \u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0004*\b\u0012\u0004\u0012\u00020\u00010\u00042\u0006\u0010\u0002\u001a\u00020\u0003\u001a \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u0001*\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u001b\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012*\b\u0012\u0004\u0012\u00020\u00140\u0006¢\u0006\u0002\u0010\u0015\u001a\u000e\u0010 \u001a\u00020\u000b*\u0006\u0012\u0002\b\u00030!\"\u001f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006*\u0006\u0012\u0002\b\u00030\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\t\"3\u0010\u0018\u001a\u0004\u0018\u00010\u0017*\u00020\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00178F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006\""}, d2 = {"getContainingDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "classTypeParameterSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "getClassTypeParameterSymbols", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Ljava/util/List;", "isValidTypeParameterFromOuterDeclaration", Argument.Delimiters.none, "typeParameterSymbol", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "firClassLike", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "toTypeProjections", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "(Ljava/util/List;)[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "<set-?>", "Lorg/jetbrains/kotlin/fir/resolve/FirCodeFragmentContext;", "codeFragmentContext", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "getCodeFragmentContext", "(Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;)Lorg/jetbrains/kotlin/fir/resolve/FirCodeFragmentContext;", "setCodeFragmentContext", "(Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;Lorg/jetbrains/kotlin/fir/resolve/FirCodeFragmentContext;)V", "codeFragmentContext$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "isContextParameter", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeclarationUtilsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(DeclarationUtilsKt.class, "codeFragmentContext", "getCodeFragmentContext(Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;)Lorg/jetbrains/kotlin/fir/resolve/FirCodeFragmentContext;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor codeFragmentContext$delegate = FirDeclarationDataRegistry.INSTANCE.data(CodeFragmentContextDataKey.INSTANCE);

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirClassLikeDeclaration firClassLike(FirTypeRef firTypeRef, FirSession firSession) {
        FirClassLikeSymbol<?> symbol;
        firTypeRef.getClass();
        firSession.getClass();
        FirResolvedTypeRef firResolvedTypeRef = firTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) firTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        if (!(coneType instanceof ConeClassLikeType)) {
            coneType = null;
        }
        ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
        if (coneClassLikeType == null || (symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeType.getLookupTag(), firSession)) == null) {
            return null;
        }
        return (FirClassLikeDeclaration) symbol.getFir();
    }

    public static final List<FirTypeParameterSymbol> getClassTypeParameterSymbols(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        List<FirTypeParameterSymbol> typeParameterSymbols = firClassLikeSymbol.getTypeParameterSymbols();
        ArrayList arrayList = new ArrayList();
        for (Object obj : typeParameterSymbols) {
            if (((FirTypeParameterSymbol) obj).getContainingDeclarationSymbol() instanceof FirClassLikeSymbol) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final FirCodeFragmentContext getCodeFragmentContext(FirCodeFragment firCodeFragment) {
        firCodeFragment.getClass();
        return (FirCodeFragmentContext) codeFragmentContext$delegate.getValue(firCodeFragment, $$delegatedProperties[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirClassLikeDeclaration getContainingDeclaration(FirClassLikeDeclaration firClassLikeDeclaration, FirSession firSession) {
        firClassLikeDeclaration.getClass();
        firSession.getClass();
        FirClassLikeSymbol<FirClassLikeDeclaration> containingDeclaration = getContainingDeclaration(firClassLikeDeclaration.getSymbol(), firSession);
        if (containingDeclaration != null) {
            return (FirClassLikeDeclaration) containingDeclaration.getFir();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isContextParameter(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        return (firBasedSymbol instanceof FirValueParameterSymbol) && ((FirValueParameter) ((FirValueParameterSymbol) firBasedSymbol).getFir()).getValueParameterKind() == FirValueParameterKind.ContextParameter;
    }

    public static final boolean isValidTypeParameterFromOuterDeclaration(FirTypeParameterSymbol firTypeParameterSymbol, FirDeclaration firDeclaration, FirSession firSession) {
        firTypeParameterSymbol.getClass();
        firSession.getClass();
        if (firDeclaration == null) {
            return true;
        }
        return isValidTypeParameterFromOuterDeclaration$containsTypeParameter(new LinkedHashSet(), firSession, firTypeParameterSymbol, firDeclaration);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final boolean isValidTypeParameterFromOuterDeclaration$containsTypeParameter(Set<FirDeclaration> set, FirSession firSession, FirTypeParameterSymbol firTypeParameterSymbol, FirDeclaration firDeclaration) {
        if (firDeclaration != 0 && set.add(firDeclaration) && (firDeclaration instanceof FirTypeParameterRefsOwner)) {
            List<FirTypeParameterRef> typeParameters = ((FirTypeParameterRefsOwner) firDeclaration).getTypeParameters();
            if (!(typeParameters instanceof Collection) || !typeParameters.isEmpty()) {
                Iterator<T> it = typeParameters.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(((FirTypeParameterRef) it.next()).getSymbol(), firTypeParameterSymbol)) {
                        return true;
                    }
                }
            }
            if (firDeclaration instanceof FirCallableDeclaration) {
                ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableDeclaration) firDeclaration);
                if (coneClassLikeLookupTagContainingClassLookupTag == null) {
                    return true;
                }
                FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTagContainingClassLookupTag, firSession);
                return isValidTypeParameterFromOuterDeclaration$containsTypeParameter(set, firSession, firTypeParameterSymbol, symbol != null ? (FirClassLikeDeclaration) symbol.getFir() : null);
            }
            if (firDeclaration instanceof FirClass) {
                Iterator<FirTypeRef> it2 = ((FirClass) firDeclaration).getSuperTypeRefs().iterator();
                while (it2.hasNext()) {
                    FirClassLikeDeclaration firClassLikeDeclarationFirClassLike = firClassLike(it2.next(), firSession);
                    if (firClassLikeDeclarationFirClassLike == null) {
                        return true;
                    }
                    if ((firClassLikeDeclarationFirClassLike instanceof FirRegularClass) && isValidTypeParameterFromOuterDeclaration$containsTypeParameter(set, firSession, firTypeParameterSymbol, firClassLikeDeclarationFirClassLike)) {
                        return true;
                    }
                    if ((firClassLikeDeclarationFirClassLike instanceof FirTypeAlias) && isValidTypeParameterFromOuterDeclaration$containsTypeParameter(set, firSession, firTypeParameterSymbol, TypeExpansionUtilsKt.fullyExpandedClass((FirTypeAlias) firClassLikeDeclarationFirClassLike, firSession))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final void setCodeFragmentContext(FirCodeFragment firCodeFragment, FirCodeFragmentContext firCodeFragmentContext) {
        firCodeFragment.getClass();
        codeFragmentContext$delegate.setValue(firCodeFragment, $$delegatedProperties[0], firCodeFragmentContext);
    }

    public static final ConeTypeProjection[] toTypeProjections(List<? extends FirQualifierPart> list) {
        list.getClass();
        List listAsReversed = CollectionsKt.asReversed(list);
        ArrayList arrayList = new ArrayList();
        Iterator it = listAsReversed.iterator();
        while (it.hasNext()) {
            List<FirTypeProjection> typeArguments = ((FirQualifierPart) it.next()).getTypeArgumentList().getTypeArguments();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeArguments, 10));
            Iterator<T> it2 = typeArguments.iterator();
            while (it2.hasNext()) {
                arrayList2.add(FirTypeUtilsKt.toConeTypeProjection((FirTypeProjection) it2.next()));
            }
            CollectionsKt.addAll(arrayList, arrayList2);
        }
        return (ConeTypeProjection[]) arrayList.toArray(new ConeTypeProjection[0]);
    }

    public static final FirClassLikeSymbol<FirClassLikeDeclaration> getContainingDeclaration(FirClassLikeSymbol<? extends FirClassLikeDeclaration> firClassLikeSymbol, FirSession firSession) {
        firClassLikeSymbol.getClass();
        firSession.getClass();
        return FirProviderKt.getFirProvider(firSession).getContainingClass(firClassLikeSymbol);
    }
}

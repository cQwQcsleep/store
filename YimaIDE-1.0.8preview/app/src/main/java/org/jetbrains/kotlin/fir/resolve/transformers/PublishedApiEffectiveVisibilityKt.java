package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.EffectiveVisibilityUtilsKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.PublishedApiEffectiveVisibilityKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aV\u0010\u0015\u001a\u0004\u0018\u00010\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00012\f\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u00012\b\u0010\u001f\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#\u001aN\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0006\u0010$\u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00012\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\u001e\u001a\u00020\u00012\b\u0010\u001f\u001a\u0004\u0018\u00010\u00012\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002\u001a\u0012\u0010'\u001a\u00020(*\u00020)2\u0006\u0010\"\u001a\u00020#\u001a*\u0010'\u001a\u00020(*\u00020)2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010\"\u001a\u00020#\u001a$\u0010'\u001a\u00020(*\u00020)2\u0006\u0010,\u001a\u00020!2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010\"\u001a\u00020#\"3\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u00032\b\u0010\u0000\u001a\u0004\u0018\u00010\u00018F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"C\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\n*\u00020\u00032\u0010\u0010\u0000\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\n8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\t\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\"\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0005\"\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00138Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014¨\u0006-"}, d2 = {"<set-?>", "Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "nonLazyPublishedApiEffectiveVisibility", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getNonLazyPublishedApiEffectiveVisibility", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "setNonLazyPublishedApiEffectiveVisibility", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;)V", "nonLazyPublishedApiEffectiveVisibility$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "Lkotlin/Lazy;", "lazyPublishedApiEffectiveVisibility", "getLazyPublishedApiEffectiveVisibility", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lkotlin/Lazy;", "setLazyPublishedApiEffectiveVisibility", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lkotlin/Lazy;)V", "lazyPublishedApiEffectiveVisibility$delegate", "publishedApiEffectiveVisibility", "getPublishedApiEffectiveVisibility", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", "computePublishedApiEffectiveVisibility", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "selfEffectiveVisibility", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "parentEffectiveVisibility", "parentPublishedEffectiveVisibility", "forClass", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "hasPublishedApiAnnotation", "containingClassLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "setLazyPublishedVisibility", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "parentProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "hasPublishedApi", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PublishedApiEffectiveVisibilityKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(PublishedApiEffectiveVisibilityKt.class, "nonLazyPublishedApiEffectiveVisibility", "getNonLazyPublishedApiEffectiveVisibility(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lorg/jetbrains/kotlin/descriptors/EffectiveVisibility;", 1), new MutablePropertyReference1Impl<>(PublishedApiEffectiveVisibilityKt.class, "lazyPublishedApiEffectiveVisibility", "getLazyPublishedApiEffectiveVisibility(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Lkotlin/Lazy;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor lazyPublishedApiEffectiveVisibility$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor nonLazyPublishedApiEffectiveVisibility$delegate;

    static {
        FirDeclarationDataRegistry firDeclarationDataRegistry = FirDeclarationDataRegistry.INSTANCE;
        nonLazyPublishedApiEffectiveVisibility$delegate = firDeclarationDataRegistry.data(PublishedApiEffectiveVisibilityKey.INSTANCE);
        lazyPublishedApiEffectiveVisibility$delegate = firDeclarationDataRegistry.data(LazyPublishedApiEffectiveVisibilityKey.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0082 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:37:0x0084  */
    /* JADX WARN: Code duplicated, block: B:38:0x008d  */
    /* JADX WARN: Code duplicated, block: B:7:0x0012  */
    /* JADX WARN: Type inference failed for: r12v2, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public static EffectiveVisibility a(FirProperty firProperty, FirMemberDeclaration firMemberDeclaration, FirSession firSession, boolean z) {
        CallableId callableId;
        ClassId classId;
        EffectiveVisibility effectiveVisibility;
        EffectiveVisibility effectiveVisibility2;
        EffectiveVisibility publishedApiEffectiveVisibility;
        EffectiveVisibility publishedApiEffectiveVisibility2 = null;
        if (firProperty != null) {
            CallableId callableId2 = firProperty.getSymbol().getCallableId();
            if (callableId2 != null) {
                classId = callableId2.getClassId();
            } else {
                classId = null;
            }
        } else if (firMemberDeclaration instanceof FirClassLikeDeclaration) {
            classId = FirDeclarationUtilKt.getClassId((FirClassLikeDeclaration) firMemberDeclaration).getOuterClassId();
        } else if (!(firMemberDeclaration instanceof FirCallableDeclaration) || (callableId = ((FirCallableDeclaration) firMemberDeclaration).getSymbol().getCallableId()) == null) {
            classId = null;
        } else {
            classId = callableId.getClassId();
        }
        ConeClassLikeLookupTagImpl lookupTag = classId != null ? TypeConstructionUtilsKt.toLookupTag(classId) : null;
        FirDeclarationStatus status = firMemberDeclaration.getStatus();
        status.getClass();
        FirResolvedDeclarationStatus firResolvedDeclarationStatus = (FirResolvedDeclarationStatus) status;
        FirClassLikeSymbol<?> symbol = lookupTag != null ? ToSymbolUtilsKt.toSymbol((ConeClassLikeLookupTag) lookupTag, firSession) : null;
        Visibility visibility = firResolvedDeclarationStatus.getVisibility();
        boolean z2 = firMemberDeclaration instanceof FirClass;
        EffectiveVisibility effectiveVisibility$default = EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(firResolvedDeclarationStatus.getVisibility(), (FirClassLikeSymbol) symbol, z2, false, 4, (Object) null);
        if (firProperty != null) {
            FirDeclarationStatus status2 = firProperty.getStatus();
            FirResolvedDeclarationStatus firResolvedDeclarationStatus2 = status2 instanceof FirResolvedDeclarationStatus ? (FirResolvedDeclarationStatus) status2 : null;
            if (firResolvedDeclarationStatus2 == null || (effectiveVisibility = firResolvedDeclarationStatus2.getEffectiveVisibility()) == null) {
                effectiveVisibility = EffectiveVisibility.Local.INSTANCE;
            }
            if (effectiveVisibility == null) {
                if (symbol != null) {
                    effectiveVisibility = symbol.getResolvedStatus().getEffectiveVisibility();
                } else {
                    effectiveVisibility = EffectiveVisibility.Public.INSTANCE;
                }
            }
        } else if (symbol != null) {
            effectiveVisibility = symbol.getResolvedStatus().getEffectiveVisibility();
        } else {
            effectiveVisibility = EffectiveVisibility.Public.INSTANCE;
        }
        EffectiveVisibility effectiveVisibility3 = effectiveVisibility;
        if (firProperty == null || (publishedApiEffectiveVisibility = getPublishedApiEffectiveVisibility(firProperty)) == null) {
            if (symbol != null) {
                FirLazyDeclarationResolverKt.lazyResolveToPhase(symbol, FirResolvePhase.STATUS);
                publishedApiEffectiveVisibility2 = getPublishedApiEffectiveVisibility((FirDeclaration) symbol.getFir());
            }
            effectiveVisibility2 = publishedApiEffectiveVisibility2;
        } else {
            effectiveVisibility2 = publishedApiEffectiveVisibility;
        }
        return computePublishedApiEffectiveVisibility(z, visibility, effectiveVisibility$default, lookupTag, effectiveVisibility3, effectiveVisibility2, z2, firSession);
    }

    public static final EffectiveVisibility computePublishedApiEffectiveVisibility(List<? extends FirAnnotation> list, Visibility visibility, EffectiveVisibility effectiveVisibility, FirClassLikeSymbol<?> firClassLikeSymbol, EffectiveVisibility effectiveVisibility2, EffectiveVisibility effectiveVisibility3, boolean z, FirSession firSession) {
        list.getClass();
        visibility.getClass();
        effectiveVisibility.getClass();
        effectiveVisibility2.getClass();
        firSession.getClass();
        return computePublishedApiEffectiveVisibility(FirAnnotationUtilsKt.hasAnnotationSafe(list, StandardClassIds$Annotations.INSTANCE.getPublishedApi(), firSession), visibility, effectiveVisibility, firClassLikeSymbol != null ? firClassLikeSymbol.getLookupTag() : null, effectiveVisibility2, effectiveVisibility3, z, firSession);
    }

    public static final Lazy<EffectiveVisibility> getLazyPublishedApiEffectiveVisibility(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (Lazy) lazyPublishedApiEffectiveVisibility$delegate.getValue(firDeclaration, $$delegatedProperties[1]);
    }

    public static final EffectiveVisibility getNonLazyPublishedApiEffectiveVisibility(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        return (EffectiveVisibility) nonLazyPublishedApiEffectiveVisibility$delegate.getValue(firDeclaration, $$delegatedProperties[0]);
    }

    public static final EffectiveVisibility getPublishedApiEffectiveVisibility(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        EffectiveVisibility nonLazyPublishedApiEffectiveVisibility = getNonLazyPublishedApiEffectiveVisibility(firDeclaration);
        if (nonLazyPublishedApiEffectiveVisibility != null) {
            return nonLazyPublishedApiEffectiveVisibility;
        }
        Lazy<EffectiveVisibility> lazyPublishedApiEffectiveVisibility = getLazyPublishedApiEffectiveVisibility(firDeclaration);
        if (lazyPublishedApiEffectiveVisibility != null) {
            return (EffectiveVisibility) lazyPublishedApiEffectiveVisibility.getValue();
        }
        return null;
    }

    public static final void setLazyPublishedApiEffectiveVisibility(FirDeclaration firDeclaration, Lazy<? extends EffectiveVisibility> lazy) {
        firDeclaration.getClass();
        lazyPublishedApiEffectiveVisibility$delegate.setValue(firDeclaration, $$delegatedProperties[1], lazy);
    }

    public static final void setLazyPublishedVisibility(FirMemberDeclaration firMemberDeclaration, List<? extends FirAnnotation> list, FirProperty firProperty, FirSession firSession) {
        ConeClassLikeLookupTag lookupTag;
        firMemberDeclaration.getClass();
        list.getClass();
        firSession.getClass();
        List<? extends FirAnnotation> list2 = list;
        boolean z = false;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                FirResolvedTypeRef annotationTypeRef = ((FirAnnotation) it.next()).getAnnotationTypeRef();
                ClassId classId = null;
                FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
                ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
                if (coneClassLikeType != null && (lookupTag = coneClassLikeType.getLookupTag()) != null) {
                    classId = lookupTag.getClassId();
                }
                if (Intrinsics.areEqual(classId, StandardClassIds$Annotations.INSTANCE.getPublishedApi())) {
                    z = true;
                    break;
                }
            }
        }
        setLazyPublishedVisibility(firMemberDeclaration, z, firProperty, firSession);
    }

    public static final void setNonLazyPublishedApiEffectiveVisibility(FirDeclaration firDeclaration, EffectiveVisibility effectiveVisibility) {
        firDeclaration.getClass();
        nonLazyPublishedApiEffectiveVisibility$delegate.setValue(firDeclaration, $$delegatedProperties[0], effectiveVisibility);
    }

    public static final EffectiveVisibility getPublishedApiEffectiveVisibility(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firBasedSymbol, FirResolvePhase.STATUS);
        return getPublishedApiEffectiveVisibility(firBasedSymbol.getFir());
    }

    private static final EffectiveVisibility computePublishedApiEffectiveVisibility(boolean z, Visibility visibility, EffectiveVisibility effectiveVisibility, ConeClassLikeLookupTag coneClassLikeLookupTag, EffectiveVisibility effectiveVisibility2, EffectiveVisibility effectiveVisibility3, boolean z2, FirSession firSession) {
        EffectiveVisibility effectiveVisibility4 = z ? EffectiveVisibilityUtilsKt.toEffectiveVisibility(visibility, coneClassLikeLookupTag, z2, true) : null;
        if (effectiveVisibility4 == null && effectiveVisibility3 == null) {
            return null;
        }
        if (effectiveVisibility3 != null) {
            effectiveVisibility2 = effectiveVisibility3;
        }
        if (effectiveVisibility4 != null) {
            effectiveVisibility = effectiveVisibility4;
        }
        return effectiveVisibility2.lowerBound(effectiveVisibility, TypeComponentsKt.getTypeContext(firSession));
    }

    public static final void setLazyPublishedVisibility(FirMemberDeclaration firMemberDeclaration, FirSession firSession) {
        firMemberDeclaration.getClass();
        firSession.getClass();
        setLazyPublishedVisibility(firMemberDeclaration, firMemberDeclaration.getAnnotations(), (FirProperty) null, firSession);
    }

    public static final void setLazyPublishedVisibility(final FirMemberDeclaration firMemberDeclaration, final boolean z, final FirProperty firProperty, final FirSession firSession) {
        firMemberDeclaration.getClass();
        firSession.getClass();
        setLazyPublishedApiEffectiveVisibility(firMemberDeclaration, LazyKt.lazy(new Function0() { // from class: avb
            public final Object invoke() {
                return PublishedApiEffectiveVisibilityKt.a(firProperty, firMemberDeclaration, firSession, z);
            }
        }));
    }
}

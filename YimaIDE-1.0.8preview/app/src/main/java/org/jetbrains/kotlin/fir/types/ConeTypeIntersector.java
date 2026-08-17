package org.jetbrains.kotlin.fir.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeIntersector;
import org.jetbrains.kotlin.types.AbstractTypeChecker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tJR\u0010\n\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\r\u001a\u00020\u000e26\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00150\u0010H\u0002J\u001c\u0010\u0016\u001a\u00020\u0015*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005H\u0002¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeTypeIntersector;", Argument.Delimiters.none, "<init>", "()V", "intersectTypes", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "context", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "types", Argument.Delimiters.none, "removeIfNonSingleErrorOrInRelation", Argument.Delimiters.none, Argument.Delimiters.none, "versionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "predicate", "Lkotlin/Function2;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "candidate", "other", Argument.Delimiters.none, "isStrictSubtypeOf", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "supertype", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypeIntersector {
    public static final ConeTypeIntersector INSTANCE = new ConeTypeIntersector();

    private ConeTypeIntersector() {
    }

    public static boolean a(ConeInferenceContext coneInferenceContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        return AbstractTypeChecker.equalTypes$default(AbstractTypeChecker.INSTANCE, coneInferenceContext, coneKotlinType, coneKotlinType2, false, false, 24, (Object) null);
    }

    public static boolean b(ConeInferenceContext coneInferenceContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        return INSTANCE.isStrictSubtypeOf(coneKotlinType2, coneInferenceContext, coneKotlinType);
    }

    private final boolean isStrictSubtypeOf(ConeKotlinType coneKotlinType, ConeTypeContext coneTypeContext, ConeKotlinType coneKotlinType2) {
        AbstractTypeChecker abstractTypeChecker = AbstractTypeChecker.INSTANCE;
        return AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker, coneTypeContext, coneKotlinType, coneKotlinType2, false, 8, (Object) null) && !AbstractTypeChecker.isSubtypeOf$default(abstractTypeChecker, coneTypeContext, coneKotlinType2, coneKotlinType, false, 8, (Object) null);
    }

    private final void removeIfNonSingleErrorOrInRelation(Collection<ConeKotlinType> collection, LanguageVersionSettings languageVersionSettings, Function2<? super ConeKotlinType, ? super ConeKotlinType, Boolean> function2) {
        Iterator<ConeKotlinType> it = collection.iterator();
        while (it.hasNext()) {
            ConeKotlinType next = it.next();
            if (!(next instanceof ConeErrorType) || collection.size() <= 1) {
                if (!removeIfNonSingleErrorOrInRelation$shouldBeSkipped(next, languageVersionSettings)) {
                    Collection<ConeKotlinType> collection2 = collection;
                    if (!(collection2 instanceof Collection) || !collection2.isEmpty()) {
                        for (ConeKotlinType coneKotlinType : collection2) {
                            if (coneKotlinType == next || removeIfNonSingleErrorOrInRelation$shouldBeSkipped(coneKotlinType, languageVersionSettings) || !((Boolean) function2.invoke(next, coneKotlinType)).booleanValue()) {
                            }
                        }
                    }
                }
            }
            it.remove();
        }
    }

    private static final boolean removeIfNonSingleErrorOrInRelation$shouldBeSkipped(ConeKotlinType coneKotlinType, LanguageVersionSettings languageVersionSettings) {
        List<ConeKotlinType> supertypes;
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound;
        if (!languageVersionSettings.supportsFeature(LanguageFeature.ChangedIntersectionWithRecursiveCapturedType)) {
            return false;
        }
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound2 = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(coneKotlinType);
        ConeCapturedType coneCapturedType = coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound2 instanceof ConeCapturedType ? (ConeCapturedType) coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound2 : null;
        if (coneCapturedType != null && (supertypes = coneCapturedType.getConstructor().getSupertypes()) != null) {
            List<ConeKotlinType> list = supertypes;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    for (ConeTypeProjection coneTypeProjection : ((ConeKotlinType) it.next()).getTypeArguments()) {
                        ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                        if (Intrinsics.areEqual((type == null || (coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(type)) == null) ? null : ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound), coneCapturedType)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final ConeKotlinType intersectTypes(ConeInferenceContext context, Collection<? extends ConeKotlinType> types) {
        ConeInferenceContext coneInferenceContext;
        context.getClass();
        types.getClass();
        int size = types.size();
        if (size == 0) {
            k2d.a("Expected some types");
            return null;
        }
        if (size == 1) {
            return (ConeKotlinType) CollectionsKt.single(types);
        }
        ArrayList<ConeKotlinType> arrayList = new ArrayList();
        for (ConeKotlinType coneKotlinType : types) {
            if (coneKotlinType instanceof ConeIntersectionType) {
                arrayList.addAll(((ConeIntersectionType) coneKotlinType).getIntersectedTypes());
            } else {
                arrayList.add(coneKotlinType);
            }
        }
        boolean z = false;
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((ConeKotlinType) it.next()) instanceof ConeFlexibleType) {
                    if (!arrayList.isEmpty()) {
                        Iterator it2 = arrayList.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                ConeKotlinType coneKotlinType2 = (ConeKotlinType) it2.next();
                                if (TypeUtilsKt.isRaw(coneKotlinType2) || (coneKotlinType2 instanceof ConeDynamicType)) {
                                    break;
                                }
                            }
                        }
                    }
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        arrayList2.add(ConeTypeUtilsKt.lowerBoundIfFlexible((ConeKotlinType) it3.next()));
                    }
                    ConeKotlinType coneKotlinTypeIntersectTypes = intersectTypes(context, arrayList2);
                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
                    Iterator it4 = arrayList.iterator();
                    while (it4.hasNext()) {
                        arrayList3.add(ConeTypeUtilsKt.upperBoundIfFlexible((ConeKotlinType) it4.next()));
                    }
                    ConeKotlinType coneKotlinTypeIntersectTypes2 = intersectTypes(context, arrayList3);
                    return ConeBuiltinTypeUtilsKt.isNothing(coneKotlinTypeIntersectTypes) ? coneKotlinTypeIntersectTypes2 : TypeUtilsKt.coneFlexibleOrSimpleType(context, coneKotlinTypeIntersectTypes, coneKotlinTypeIntersectTypes2, false);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            Iterator it5 = arrayList.iterator();
            while (it5.hasNext()) {
                if (!context.isNullableType((ConeKotlinType) it5.next())) {
                    z = true;
                    break;
                }
            }
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (ConeKotlinType coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default : arrayList) {
            if (z) {
                coneInferenceContext = context;
                coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default = TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull$default(coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default, (ConeTypeContext) coneInferenceContext, false, false, 6, (Object) null);
            } else {
                coneInferenceContext = context;
            }
            linkedHashSet.add(coneKotlinTypeMakeConeTypeDefinitelyNotNullOrNotNull$default);
            context = coneInferenceContext;
        }
        final ConeInferenceContext coneInferenceContext2 = context;
        if (linkedHashSet.size() == 1) {
            return (ConeKotlinType) CollectionsKt.single(linkedHashSet);
        }
        List mutableList = CollectionsKt.toMutableList(linkedHashSet);
        LanguageVersionSettings languageVersionSettings = FirLanguageSettingsComponentKt.getLanguageVersionSettings(coneInferenceContext2.getSession());
        List list = mutableList;
        removeIfNonSingleErrorOrInRelation(list, languageVersionSettings, new Function2() { // from class: kq2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConeTypeIntersector.b(coneInferenceContext2, (ConeKotlinType) obj, (ConeKotlinType) obj2));
            }
        });
        list.isEmpty();
        ConeKotlinType coneKotlinTypeFindCommonIntersectionType = ConeIntegerLiteralIntersector.INSTANCE.findCommonIntersectionType(list);
        if (coneKotlinTypeFindCommonIntersectionType != null) {
            return coneKotlinTypeFindCommonIntersectionType;
        }
        removeIfNonSingleErrorOrInRelation(list, languageVersionSettings, new Function2() { // from class: lq2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(ConeTypeIntersector.a(coneInferenceContext2, (ConeKotlinType) obj, (ConeKotlinType) obj2));
            }
        });
        list.isEmpty();
        ConeKotlinType coneKotlinType3 = (ConeKotlinType) CollectionsKt.singleOrNull(mutableList);
        return coneKotlinType3 == null ? new ConeIntersectionType(list, null, 2, null) : coneKotlinType3;
    }
}

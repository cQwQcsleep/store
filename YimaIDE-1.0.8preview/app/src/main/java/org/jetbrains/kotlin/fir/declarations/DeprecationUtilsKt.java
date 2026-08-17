package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.metadata.deserialization.VersionRequirement;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.deprecation.DeprecationLevelValue;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u001a%\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u0006\u0012\u0002\b\u00030\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010\r\u001a\"\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a\"\u0010\u0012\u001a\u0004\u0018\u00010\u000f*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a\u0012\u0010\u0013\u001a\u00020\u0014*\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0012\u0010\u0013\u001a\u00020\u0014*\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0012\u0010\u0017\u001a\u00020\u0014*\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0011\u001a*\u0010\u0019\u001a\u00020\u0001*\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001f\u001a\"\u0010 \u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\"\u001a\"\u0010$\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\"\u001a2\u0010%\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u001f2\u0010\b\u0002\u0010'\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\u001c\u001a3\u0010)\u001a\u0004\u0018\u00010\u000f*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0002¢\u0006\u0002\u0010+\u001a1\u0010,\u001a\u0004\u0018\u00010\u000f*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0012\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t¢\u0006\u0002\u0010+\u001a\u000e\u0010-\u001a\u0004\u0018\u00010.*\u00020\u001dH\u0002\u001a2\u0010/\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010\u0010\u001a\u00020\u00112\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\u001cH\u0002\u001a\u0016\u00100\u001a\u00020\u001f*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u001e\u0010?\u001a\u00020@*\u0006\u0012\u0002\b\u00030A2\u0006\u0010B\u001a\u00020\u001f2\u0006\u0010C\u001a\u00020\u001f\"3\u00103\u001a\u0004\u0018\u000102*\u00020\u00152\b\u00101\u001a\u0004\u0018\u0001028F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b4\u00105\"\u0004\b6\u00107\"3\u0010:\u001a\u0004\u0018\u00010\u001f*\u00020\u00152\b\u00101\u001a\u0004\u0018\u00010\u001f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b>\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006D"}, d2 = {"buildDeprecationAnnotationInfoPerUseSiteStorage", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationAnnotationInfoPerUseSiteStorage;", "builder", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationAnnotationInfoPerUseSiteStorageBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "getUseSitesForCallSite", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "callSite", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/FirElement;)[Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "getOwnDeprecation", "Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getDeprecation", "getDeprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "getDeprecationsProviderForStubAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "extractDeprecationInfoPerUseSite", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "customAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "processPropertyIfAccessor", Argument.Delimiters.none, "getDeprecationsProviderFromAccessors", "getter", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "setter", "getDeprecationsAnnotationInfoByUseSiteFromAccessors", "getDeprecationsProviderFromAnnotations", "fromJava", "versionRequirements", "Lorg/jetbrains/kotlin/metadata/deserialization/VersionRequirement;", "getOwnDeprecationForCallSite", "sites", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/FirSession;[Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;)Lorg/jetbrains/kotlin/fir/declarations/FirDeprecationInfo;", "getDeprecationForCallSite", "getDeprecationLevel", "Lorg/jetbrains/kotlin/resolve/deprecation/DeprecationLevelValue;", "extractDeprecationAnnotationInfoPerUseSite", "isDeprecationLevelHidden", "<set-?>", "Lorg/jetbrains/kotlin/fir/declarations/HiddenEverywhereBesideSuperCallsStatus;", "hiddenEverywhereBesideSuperCallsStatus", "getHiddenEverywhereBesideSuperCallsStatus", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/declarations/HiddenEverywhereBesideSuperCallsStatus;", "setHiddenEverywhereBesideSuperCallsStatus", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/declarations/HiddenEverywhereBesideSuperCallsStatus;)V", "hiddenEverywhereBesideSuperCallsStatus$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "isHiddenToOvercomeSignatureClash", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Ljava/lang/Boolean;", "setHiddenToOvercomeSignatureClash", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Ljava/lang/Boolean;)V", "isHiddenToOvercomeSignatureClash$delegate", "hiddenStatusOfCall", "Lorg/jetbrains/kotlin/fir/declarations/CallToPotentiallyHiddenSymbolResult;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "isSuperCall", "isCallToOverride", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeprecationUtilsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(DeprecationUtilsKt.class, "hiddenEverywhereBesideSuperCallsStatus", "getHiddenEverywhereBesideSuperCallsStatus(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Lorg/jetbrains/kotlin/fir/declarations/HiddenEverywhereBesideSuperCallsStatus;", 1), new MutablePropertyReference1Impl<>(DeprecationUtilsKt.class, "isHiddenToOvercomeSignatureClash", "isHiddenToOvercomeSignatureClash(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Ljava/lang/Boolean;", 1)};
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor hiddenEverywhereBesideSuperCallsStatus$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isHiddenToOvercomeSignatureClash$delegate;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[HiddenEverywhereBesideSuperCallsStatus.values().length];
            try {
                iArr[HiddenEverywhereBesideSuperCallsStatus.HIDDEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HiddenEverywhereBesideSuperCallsStatus.HIDDEN_IN_DECLARING_CLASS_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[HiddenEverywhereBesideSuperCallsStatus.HIDDEN_FAKE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        FirDeclarationDataRegistry firDeclarationDataRegistry = FirDeclarationDataRegistry.INSTANCE;
        hiddenEverywhereBesideSuperCallsStatus$delegate = firDeclarationDataRegistry.data(IsHiddenEverywhereBesideSuperCalls.INSTANCE);
        isHiddenToOvercomeSignatureClash$delegate = firDeclarationDataRegistry.data(IsHiddenToOvercomeSignatureClash.INSTANCE);
    }

    public static final DeprecationAnnotationInfoPerUseSiteStorage buildDeprecationAnnotationInfoPerUseSiteStorage(Function1<? super DeprecationAnnotationInfoPerUseSiteStorageBuilder, Unit> function1) {
        function1.getClass();
        DeprecationAnnotationInfoPerUseSiteStorageBuilder deprecationAnnotationInfoPerUseSiteStorageBuilder = new DeprecationAnnotationInfoPerUseSiteStorageBuilder();
        function1.invoke(deprecationAnnotationInfoPerUseSiteStorageBuilder);
        return deprecationAnnotationInfoPerUseSiteStorageBuilder.build();
    }

    private static final DeprecationAnnotationInfoPerUseSiteStorage extractDeprecationAnnotationInfoPerUseSite(List<? extends FirAnnotation> list, boolean z, FirSession firSession, List<VersionRequirement> list2) {
        ApiVersion apiVersion;
        ConeClassLikeType coneClassLikeType;
        ConeClassLikeLookupTag lookupTag;
        Object next;
        ConeClassLikeType coneClassLikeType2;
        ConeClassLikeLookupTag lookupTag2;
        ConeClassLikeLookupTag lookupTag3;
        ConeClassLikeLookupTag lookupTag4;
        Map<ClassId, Boolean> deprecationAnnotationsWithOverridesPropagation = FirAnnotationsPlatformSpecificSupportComponentKt.getAnnotationPlatformSupport(firSession).getDeprecationAnnotationsWithOverridesPropagation();
        ArrayList<Pair> arrayList = new ArrayList();
        for (Map.Entry<ClassId, Boolean> entry : deprecationAnnotationsWithOverridesPropagation.entrySet()) {
            ClassId key = entry.getKey();
            Boolean value = entry.getValue();
            value.booleanValue();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : list) {
                FirResolvedTypeRef annotationTypeRef = ((FirAnnotation) obj).getAnnotationTypeRef();
                FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
                ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
                ConeClassLikeType coneClassLikeType3 = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
                if (Intrinsics.areEqual((coneClassLikeType3 == null || (lookupTag4 = coneClassLikeType3.getLookupTag()) == null) ? null : lookupTag4.getClassId(), key)) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(TuplesKt.to((FirAnnotation) it.next(), value));
            }
            CollectionsKt.addAll(arrayList, arrayList3);
        }
        DeprecationAnnotationInfoPerUseSiteStorageBuilder deprecationAnnotationInfoPerUseSiteStorageBuilder = new DeprecationAnnotationInfoPerUseSiteStorageBuilder();
        for (Pair pair : arrayList) {
            FirAnnotation firAnnotation = (FirAnnotation) pair.component1();
            boolean zBooleanValue = ((Boolean) pair.component2()).booleanValue();
            FirResolvedTypeRef annotationTypeRef2 = firAnnotation.getAnnotationTypeRef();
            FirResolvedTypeRef firResolvedTypeRef2 = annotationTypeRef2 instanceof FirResolvedTypeRef ? annotationTypeRef2 : null;
            ConeKotlinType coneType2 = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
            ConeClassLikeType coneClassLikeType4 = coneType2 instanceof ConeClassLikeType ? (ConeClassLikeType) coneType2 : null;
            boolean z2 = false;
            if (Intrinsics.areEqual((coneClassLikeType4 == null || (lookupTag3 = coneClassLikeType4.getLookupTag()) == null) ? null : lookupTag3.getClassId(), StandardClassIds$Annotations.INSTANCE.getSinceKotlin())) {
                FirExpression firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotation, StandardClassIds$Annotations.ParameterNames.INSTANCE.getSinceKotlinVersion(), false, 2, null);
                FirLiteralExpression firLiteralExpression = firExpressionFindArgumentByName$default instanceof FirLiteralExpression ? (FirLiteralExpression) firExpressionFindArgumentByName$default : null;
                Object value2 = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
                String str = value2 instanceof String ? (String) value2 : null;
                if (str != null && (apiVersion = ApiVersion.INSTANCE.parse(str)) != null) {
                    List<? extends FirAnnotation> list3 = list;
                    if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                        Iterator<T> it2 = list3.iterator();
                        do {
                            if (it2.hasNext()) {
                                FirResolvedTypeRef annotationTypeRef3 = ((FirAnnotation) it2.next()).getAnnotationTypeRef();
                                FirResolvedTypeRef firResolvedTypeRef3 = annotationTypeRef3 instanceof FirResolvedTypeRef ? annotationTypeRef3 : null;
                                ConeKotlinType coneType3 = firResolvedTypeRef3 != null ? firResolvedTypeRef3.getConeType() : null;
                                coneClassLikeType = coneType3 instanceof ConeClassLikeType ? (ConeClassLikeType) coneType3 : null;
                            }
                        } while (!Intrinsics.areEqual((coneClassLikeType == null || (lookupTag = coneClassLikeType.getLookupTag()) == null) ? null : lookupTag.getClassId(), StandardClassIds$Annotations.INSTANCE.getWasExperimental()));
                    }
                    deprecationAnnotationInfoPerUseSiteStorageBuilder.add(firAnnotation.getUseSiteTarget(), new SinceKotlinProvider(apiVersion));
                    break;
                }
            } else {
                DeprecationLevelValue deprecationLevel = getDeprecationLevel(firAnnotation);
                if (deprecationLevel == null) {
                    deprecationLevel = DeprecationLevelValue.WARNING;
                }
                if (zBooleanValue && !z) {
                    z2 = true;
                }
                Iterator<T> it3 = list.iterator();
                do {
                    if (!it3.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it3.next();
                    FirResolvedTypeRef annotationTypeRef4 = ((FirAnnotation) next).getAnnotationTypeRef();
                    FirResolvedTypeRef firResolvedTypeRef4 = annotationTypeRef4 instanceof FirResolvedTypeRef ? annotationTypeRef4 : null;
                    ConeKotlinType coneType4 = firResolvedTypeRef4 != null ? firResolvedTypeRef4.getConeType() : null;
                    coneClassLikeType2 = coneType4 instanceof ConeClassLikeType ? (ConeClassLikeType) coneType4 : null;
                } while (!Intrinsics.areEqual((coneClassLikeType2 == null || (lookupTag2 = coneClassLikeType2.getLookupTag()) == null) ? null : lookupTag2.getClassId(), StandardClassIds$Annotations.INSTANCE.getDeprecatedSinceKotlin()));
                FirAnnotation firAnnotation2 = (FirAnnotation) next;
                deprecationAnnotationInfoPerUseSiteStorageBuilder.add(firAnnotation.getUseSiteTarget(), firAnnotation2 == null ? new SimpleDeprecatedProvider(deprecationLevel, z2, firAnnotation) : new DeprecatedSinceKotlinProvider(firAnnotation2, firAnnotation, z2));
            }
        }
        if (list2 != null) {
            Iterator<T> it4 = list2.iterator();
            while (it4.hasNext()) {
                deprecationAnnotationInfoPerUseSiteStorageBuilder.add((AnnotationUseSiteTarget) null, new RequireKotlinProvider((VersionRequirement) it4.next()));
            }
        }
        return deprecationAnnotationInfoPerUseSiteStorageBuilder.build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final DeprecationAnnotationInfoPerUseSiteStorage extractDeprecationInfoPerUseSite(FirAnnotationContainer firAnnotationContainer, FirSession firSession, List<? extends FirAnnotation> list, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        boolean zIsJavaOrEnhancement;
        List<VersionRequirement> versionRequirements;
        firAnnotationContainer.getClass();
        firSession.getClass();
        list.getClass();
        if (firAnnotationContainer instanceof FirDeclaration) {
            FirDeclaration firDeclaration = (FirDeclaration) firAnnotationContainer;
            zIsJavaOrEnhancement = DeclarationUtilsKt.isJavaOrEnhancement(firDeclaration);
            versionRequirements = FirVersionRequirementsTableKeyKt.getVersionRequirements(firDeclaration);
        } else {
            zIsJavaOrEnhancement = false;
            versionRequirements = null;
        }
        DeprecationAnnotationInfoPerUseSiteStorageBuilder deprecationAnnotationInfoPerUseSiteStorageBuilder = new DeprecationAnnotationInfoPerUseSiteStorageBuilder();
        deprecationAnnotationInfoPerUseSiteStorageBuilder.add(extractDeprecationAnnotationInfoPerUseSite(list, zIsJavaOrEnhancement, firSession, versionRequirements));
        if (firAnnotationContainer instanceof FirProperty) {
            FirProperty firProperty = (FirProperty) firAnnotationContainer;
            deprecationAnnotationInfoPerUseSiteStorageBuilder.add(getDeprecationsAnnotationInfoByUseSiteFromAccessors(firSession, firProperty.getGetter(), firProperty.getSetter()));
        }
        if (z && (firAnnotationContainer instanceof FirPropertyAccessor)) {
            List<FirAnnotation> annotations = ((FirPropertyAccessor) firAnnotationContainer).getPropertySymbol().getAnnotations();
            ArrayList arrayList = new ArrayList();
            for (Object obj : annotations) {
                if (((FirAnnotation) obj).getUseSiteTarget() == AnnotationUseSiteTarget.ALL) {
                    arrayList.add(obj);
                }
            }
            deprecationAnnotationInfoPerUseSiteStorageBuilder.add(extractDeprecationAnnotationInfoPerUseSite(arrayList, zIsJavaOrEnhancement, firSession, versionRequirements));
        }
        return deprecationAnnotationInfoPerUseSiteStorageBuilder.build();
    }

    public static /* synthetic */ DeprecationAnnotationInfoPerUseSiteStorage extractDeprecationInfoPerUseSite$default(FirAnnotationContainer firAnnotationContainer, FirSession firSession, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return extractDeprecationInfoPerUseSite(firAnnotationContainer, firSession, list, z);
    }

    public static final FirDeprecationInfo getDeprecation(FirBasedSymbol<?> firBasedSymbol, FirSession firSession, FirElement firElement) {
        firBasedSymbol.getClass();
        firSession.getClass();
        AnnotationUseSiteTarget[] useSitesForCallSite = getUseSitesForCallSite(firBasedSymbol, firElement);
        return getDeprecationForCallSite(firBasedSymbol, firSession, (AnnotationUseSiteTarget[]) Arrays.copyOf(useSitesForCallSite, useSitesForCallSite.length));
    }

    public static final FirDeprecationInfo getDeprecationForCallSite(FirBasedSymbol<?> firBasedSymbol, FirSession firSession, AnnotationUseSiteTarget... annotationUseSiteTargetArr) {
        FirDeprecationInfo firDeprecationInfo;
        firBasedSymbol.getClass();
        firSession.getClass();
        annotationUseSiteTargetArr.getClass();
        if (!(firBasedSymbol instanceof FirTypeAliasSymbol)) {
            return getOwnDeprecationForCallSite(firBasedSymbol, firSession, (AnnotationUseSiteTarget[]) Arrays.copyOf(annotationUseSiteTargetArr, annotationUseSiteTargetArr.length));
        }
        FirDeprecationInfo ownDeprecationForCallSite = getOwnDeprecationForCallSite(firBasedSymbol, firSession, (AnnotationUseSiteTarget[]) Arrays.copyOf(annotationUseSiteTargetArr, annotationUseSiteTargetArr.length));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{((FirTypeAliasSymbol) firBasedSymbol).getResolvedExpandedTypeRef().getConeType()});
        while (!listMutableListOf.isEmpty()) {
            ConeKotlinType coneKotlinType = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf);
            Object deprecationForCallSite = linkedHashMap.get(coneKotlinType);
            if (deprecationForCallSite != null) {
                firDeprecationInfo = (FirDeprecationInfo) deprecationForCallSite;
                if (firDeprecationInfo != null && (ownDeprecationForCallSite == null || firDeprecationInfo.compareTo(ownDeprecationForCallSite) > 0)) {
                    ownDeprecationForCallSite = firDeprecationInfo;
                }
            } else {
                FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneKotlinType, firSession);
                if (symbol != null) {
                    deprecationForCallSite = getDeprecationForCallSite(symbol, firSession, (AnnotationUseSiteTarget[]) Arrays.copyOf(annotationUseSiteTargetArr, annotationUseSiteTargetArr.length));
                    linkedHashMap.put(coneKotlinType, deprecationForCallSite);
                    firDeprecationInfo = (FirDeprecationInfo) deprecationForCallSite;
                    if (firDeprecationInfo != null) {
                        ownDeprecationForCallSite = firDeprecationInfo;
                    }
                }
            }
            if (coneKotlinType instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
                listMutableListOf.add(coneFlexibleType.getLowerBound());
                if (!coneFlexibleType.getIsTrivial()) {
                    listMutableListOf.add(coneFlexibleType.getUpperBound());
                }
            } else if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
            } else if (coneKotlinType instanceof ConeIntersectionType) {
                listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType).getIntersectedTypes());
            } else {
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
                    if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                        listMutableListOf.add(coneKotlinTypeProjection.getType());
                    }
                }
            }
        }
        return ownDeprecationForCallSite;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final DeprecationLevelValue getDeprecationLevel(FirAnnotation firAnnotation) {
        Object next;
        FirExpression firExpression;
        FirExpression firExpressionUnwrapArgument;
        EnumValueArgumentInfo enumValueArgumentInfoExtractEnumValueArgumentInfo;
        Name enumEntryName;
        String strAsString;
        Object obj = null;
        if (FirAnnotationUtilsKt.getResolved(firAnnotation)) {
            firExpression = firAnnotation.getArgumentMapping().getMapping().get(StandardClassIds$Annotations.ParameterNames.INSTANCE.getDeprecatedLevel());
        } else {
            FirAnnotationCall firAnnotationCall = firAnnotation instanceof FirAnnotationCall ? (FirAnnotationCall) firAnnotation : null;
            if (firAnnotationCall == null) {
                return null;
            }
            Iterator<T> it = firAnnotationCall.getArgumentList().getArguments().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                FirExpression firExpression2 = (FirExpression) next;
                if ((firExpression2 instanceof FirNamedArgumentExpression) && Intrinsics.areEqual(((FirNamedArgumentExpression) firExpression2).getName(), StandardClassIds$Annotations.ParameterNames.INSTANCE.getDeprecatedLevel())) {
                    break;
                }
            }
            FirExpression firExpression3 = (FirExpression) next;
            firExpression = (firExpression3 == null || (firExpressionUnwrapArgument = FirExpressionUtilKt.unwrapArgument(firExpression3)) == null) ? (FirExpression) CollectionsKt.lastOrNull(((FirCall) firAnnotation).getArgumentList().getArguments()) : firExpressionUnwrapArgument;
        }
        if (firExpression == null || (enumValueArgumentInfoExtractEnumValueArgumentInfo = FirAnnotationUtilsKt.extractEnumValueArgumentInfo(firExpression)) == null || (enumEntryName = enumValueArgumentInfoExtractEnumValueArgumentInfo.getEnumEntryName()) == null || (strAsString = enumEntryName.asString()) == null) {
            return null;
        }
        for (Object obj2 : DeprecationLevelValue.getEntries()) {
            if (Intrinsics.areEqual(((DeprecationLevelValue) obj2).name(), strAsString)) {
                obj = obj2;
                break;
            }
        }
        return (DeprecationLevelValue) obj;
    }

    public static final DeprecationAnnotationInfoPerUseSiteStorage getDeprecationsAnnotationInfoByUseSiteFromAccessors(FirSession firSession, FirFunction firFunction, FirFunction firFunction2) {
        Map<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> storage;
        Map<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> storage2;
        firSession.getClass();
        DeprecationAnnotationInfoPerUseSiteStorageBuilder deprecationAnnotationInfoPerUseSiteStorageBuilder = new DeprecationAnnotationInfoPerUseSiteStorageBuilder();
        DeprecationAnnotationInfoPerUseSiteStorage deprecationAnnotationInfoPerUseSiteStorageExtractDeprecationInfoPerUseSite = firFunction2 != null ? extractDeprecationInfoPerUseSite(firFunction2, firSession, firFunction2.getAnnotations(), false) : null;
        if (deprecationAnnotationInfoPerUseSiteStorageExtractDeprecationInfoPerUseSite != null && (storage2 = deprecationAnnotationInfoPerUseSiteStorageExtractDeprecationInfoPerUseSite.getStorage()) != null) {
            for (Map.Entry<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> entry : storage2.entrySet()) {
                AnnotationUseSiteTarget key = entry.getKey();
                List<DeprecationInfoProvider> value = entry.getValue();
                if (key == null) {
                    key = AnnotationUseSiteTarget.PROPERTY_SETTER;
                }
                deprecationAnnotationInfoPerUseSiteStorageBuilder.add(key, value);
            }
        }
        DeprecationAnnotationInfoPerUseSiteStorage deprecationAnnotationInfoPerUseSiteStorageExtractDeprecationInfoPerUseSite2 = firFunction != null ? extractDeprecationInfoPerUseSite(firFunction, firSession, firFunction.getAnnotations(), false) : null;
        if (deprecationAnnotationInfoPerUseSiteStorageExtractDeprecationInfoPerUseSite2 != null && (storage = deprecationAnnotationInfoPerUseSiteStorageExtractDeprecationInfoPerUseSite2.getStorage()) != null) {
            for (Map.Entry<AnnotationUseSiteTarget, List<DeprecationInfoProvider>> entry2 : storage.entrySet()) {
                AnnotationUseSiteTarget key2 = entry2.getKey();
                List<DeprecationInfoProvider> value2 = entry2.getValue();
                if (key2 == null) {
                    key2 = AnnotationUseSiteTarget.PROPERTY_GETTER;
                }
                deprecationAnnotationInfoPerUseSiteStorageBuilder.add(key2, value2);
            }
        }
        return deprecationAnnotationInfoPerUseSiteStorageBuilder.build();
    }

    public static final DeprecationsProvider getDeprecationsProvider(FirCallableDeclaration firCallableDeclaration, FirSession firSession) {
        firCallableDeclaration.getClass();
        firSession.getClass();
        return extractDeprecationInfoPerUseSite$default(firCallableDeclaration, firSession, firCallableDeclaration.getAnnotations(), false, 4, null).toDeprecationsProvider(FirCachesFactoryKt.getFirCachesFactory(firSession));
    }

    public static final DeprecationsProvider getDeprecationsProviderForStubAccessor(FirPropertyAccessor firPropertyAccessor, FirSession firSession) {
        firPropertyAccessor.getClass();
        firSession.getClass();
        return extractDeprecationInfoPerUseSite(firPropertyAccessor, firSession, firPropertyAccessor.getAnnotations(), false).toDeprecationsProvider(FirCachesFactoryKt.getFirCachesFactory(firSession));
    }

    public static final DeprecationsProvider getDeprecationsProviderFromAccessors(FirSession firSession, FirFunction firFunction, FirFunction firFunction2) {
        firSession.getClass();
        return getDeprecationsAnnotationInfoByUseSiteFromAccessors(firSession, firFunction, firFunction2).toDeprecationsProvider(FirCachesFactoryKt.getFirCachesFactory(firSession));
    }

    public static final DeprecationsProvider getDeprecationsProviderFromAnnotations(List<? extends FirAnnotation> list, FirSession firSession, boolean z, List<VersionRequirement> list2) {
        list.getClass();
        firSession.getClass();
        return extractDeprecationAnnotationInfoPerUseSite(list, z, firSession, list2).toDeprecationsProvider(FirCachesFactoryKt.getFirCachesFactory(firSession));
    }

    public static /* synthetic */ DeprecationsProvider getDeprecationsProviderFromAnnotations$default(List list, FirSession firSession, boolean z, List list2, int i, Object obj) {
        if ((i & 4) != 0) {
            list2 = null;
        }
        return getDeprecationsProviderFromAnnotations(list, firSession, z, list2);
    }

    public static final HiddenEverywhereBesideSuperCallsStatus getHiddenEverywhereBesideSuperCallsStatus(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return (HiddenEverywhereBesideSuperCallsStatus) hiddenEverywhereBesideSuperCallsStatus$delegate.getValue(firCallableDeclaration, $$delegatedProperties[0]);
    }

    public static final FirDeprecationInfo getOwnDeprecation(FirBasedSymbol<?> firBasedSymbol, FirSession firSession, FirElement firElement) {
        firBasedSymbol.getClass();
        firSession.getClass();
        AnnotationUseSiteTarget[] useSitesForCallSite = getUseSitesForCallSite(firBasedSymbol, firElement);
        return getOwnDeprecationForCallSite(firBasedSymbol, firSession, (AnnotationUseSiteTarget[]) Arrays.copyOf(useSitesForCallSite, useSitesForCallSite.length));
    }

    private static final FirDeprecationInfo getOwnDeprecationForCallSite(FirBasedSymbol<?> firBasedSymbol, FirSession firSession, AnnotationUseSiteTarget... annotationUseSiteTargetArr) {
        DeprecationsPerUseSite ownDeprecation;
        if (firBasedSymbol instanceof FirCallableSymbol) {
            ownDeprecation = ((FirCallableSymbol) firBasedSymbol).getDeprecation(FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession));
        } else {
            ownDeprecation = firBasedSymbol instanceof FirClassLikeSymbol ? ((FirClassLikeSymbol) firBasedSymbol).getOwnDeprecation(FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession)) : null;
        }
        if (ownDeprecation != null) {
            return ownDeprecation.forUseSite((AnnotationUseSiteTarget[]) Arrays.copyOf(annotationUseSiteTargetArr, annotationUseSiteTargetArr.length));
        }
        return null;
    }

    private static final AnnotationUseSiteTarget[] getUseSitesForCallSite(FirBasedSymbol<?> firBasedSymbol, FirElement firElement) {
        if (!(firBasedSymbol instanceof FirPropertySymbol)) {
            return new AnnotationUseSiteTarget[0];
        }
        if (firElement instanceof FirVariableAssignment) {
            return new AnnotationUseSiteTarget[]{AnnotationUseSiteTarget.PROPERTY_SETTER, AnnotationUseSiteTarget.PROPERTY};
        }
        return firElement instanceof FirPropertyAccessExpression ? new AnnotationUseSiteTarget[]{AnnotationUseSiteTarget.PROPERTY_GETTER, AnnotationUseSiteTarget.PROPERTY} : new AnnotationUseSiteTarget[]{AnnotationUseSiteTarget.PROPERTY};
    }

    public static final CallToPotentiallyHiddenSymbolResult hiddenStatusOfCall(FirCallableSymbol<?> firCallableSymbol, boolean z, boolean z2) {
        firCallableSymbol.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
        if (Intrinsics.areEqual(isHiddenToOvercomeSignatureClash(firCallableDeclaration), Boolean.TRUE)) {
            return CallToPotentiallyHiddenSymbolResult.Hidden;
        }
        HiddenEverywhereBesideSuperCallsStatus hiddenEverywhereBesideSuperCallsStatus = getHiddenEverywhereBesideSuperCallsStatus(firCallableDeclaration);
        if (hiddenEverywhereBesideSuperCallsStatus == null) {
            return CallToPotentiallyHiddenSymbolResult.Visible;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[hiddenEverywhereBesideSuperCallsStatus.ordinal()];
        if (i == 1) {
            return z ? CallToPotentiallyHiddenSymbolResult.Visible : CallToPotentiallyHiddenSymbolResult.Hidden;
        }
        if (i == 2) {
            return (z || z2) ? CallToPotentiallyHiddenSymbolResult.VisibleWithDeprecation : CallToPotentiallyHiddenSymbolResult.Hidden;
        }
        if (i == 3) {
            return z2 ? CallToPotentiallyHiddenSymbolResult.VisibleWithDeprecation : CallToPotentiallyHiddenSymbolResult.Hidden;
        }
        bu8.a();
        return null;
    }

    public static final boolean isDeprecationLevelHidden(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        return FirHiddenDeprecationProviderKt.getHiddenDeprecationProvider(firSession).isDeprecationLevelHidden(firBasedSymbol);
    }

    public static final Boolean isHiddenToOvercomeSignatureClash(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        return (Boolean) isHiddenToOvercomeSignatureClash$delegate.getValue(firCallableDeclaration, $$delegatedProperties[1]);
    }

    public static final void setHiddenEverywhereBesideSuperCallsStatus(FirCallableDeclaration firCallableDeclaration, HiddenEverywhereBesideSuperCallsStatus hiddenEverywhereBesideSuperCallsStatus) {
        firCallableDeclaration.getClass();
        hiddenEverywhereBesideSuperCallsStatus$delegate.setValue(firCallableDeclaration, $$delegatedProperties[0], hiddenEverywhereBesideSuperCallsStatus);
    }

    public static final void setHiddenToOvercomeSignatureClash(FirCallableDeclaration firCallableDeclaration, Boolean bool) {
        firCallableDeclaration.getClass();
        isHiddenToOvercomeSignatureClash$delegate.setValue(firCallableDeclaration, $$delegatedProperties[1], bool);
    }

    public static final DeprecationsProvider getDeprecationsProvider(FirClassLikeDeclaration firClassLikeDeclaration, FirSession firSession) {
        firClassLikeDeclaration.getClass();
        firSession.getClass();
        return extractDeprecationInfoPerUseSite$default(firClassLikeDeclaration, firSession, firClassLikeDeclaration.getAnnotations(), false, 4, null).toDeprecationsProvider(FirCachesFactoryKt.getFirCachesFactory(firSession));
    }
}

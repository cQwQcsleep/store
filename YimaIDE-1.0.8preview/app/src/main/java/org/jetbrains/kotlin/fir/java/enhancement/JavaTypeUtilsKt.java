package org.jetbrains.kotlin.fir.java.enhancement;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionIn;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeRawType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.load.java.typeEnhancement.JavaTypeQualifiers;
import org.jetbrains.kotlin.load.java.typeEnhancement.MutabilityQualifier;
import org.jetbrains.kotlin.load.java.typeEnhancement.NullabilityQualifier;
import org.jetbrains.kotlin.load.java.typeEnhancement.TypeComponentPosition;
import org.jetbrains.kotlin.load.java.typeEnhancement.TypeComponentPositionKt;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a.\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u0002`\bH\u0000\u001a\u001a\u0010\t\u001a\u00020\u0006*\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u0002\u001aL\u0010\f\u001a\u0004\u0018\u00010\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u0002`\b2\u0006\u0010\r\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002\u001a\f\u0010\u0012\u001a\u00020\u0001*\u00020\u0001H\u0002\u001a\u000e\u0010\u0013\u001a\u0004\u0018\u00010\u0014*\u00020\u0014H\u0000\u001a\u000e\u0010\u0015\u001a\u0004\u0018\u00010\u0014*\u00020\u0014H\u0002\u001as\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020\u00172\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00192\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u0002`\b2\u0006\u0010\r\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\u0010\u0010\u001a\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001c\u0018\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010\u001f\u001a\f\u0010 \u001a\u00020\u0011*\u00020\u0007H\u0002\u001a\u0085\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020!2\u0006\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u0002`\b2\u0006\u0010\r\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00112\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00112\u0010\u0010\u001a\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001c\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010(\u001a-\u0010)\u001a\u00020\u00112\u000e\u0010*\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001c0\u001b2\u000e\u0010+\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001c0\u001bH\u0002¢\u0006\u0002\u0010,\u001a\u001c\u0010-\u001a\u00020&*\u00020&2\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0002¨\u0006."}, d2 = {"enhance", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "qualifiers", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/typeEnhancement/JavaTypeQualifiers;", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/IndexedJavaTypeQualifiers;", "computeSubtreeSizes", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "enhanceConeKotlinType", "index", "subtreeSizes", Argument.Delimiters.none, "convertErrorsToWarnings", Argument.Delimiters.none, "withEnhancedNullabilityAttributeIfRigid", "readOnlyToMutable", "Lorg/jetbrains/kotlin/name/ClassId;", "mutableToReadOnly", "enhanceInflexibleType", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "position", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/TypeComponentPosition;", "precomputedTypeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "isFromDefinitelyNotNullType", "convertErrorToWarning", "(Lorg/jetbrains/kotlin/fir/types/ConeRigidType;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/load/java/typeEnhancement/TypeComponentPosition;Lkotlin/jvm/functions/Function1;ILjava/util/List;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;ZZ)Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "enhancesSomethingForError", "Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;", "isDefinitelyNotNull", "nullabilityFromQualifiers", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifier;", "enhancedTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;", "convertNestedErrorsToWarnings", "(Lorg/jetbrains/kotlin/fir/types/ConeLookupTagBasedType;Lorg/jetbrains/kotlin/fir/FirSession;Lkotlin/jvm/functions/Function1;ILjava/util/List;ZZLorg/jetbrains/kotlin/load/java/typeEnhancement/NullabilityQualifier;Lorg/jetbrains/kotlin/fir/types/ConeClassifierLookupTag;Z[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;)Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "contentIdentityEqual", "a", "b", "([Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;[Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;)Z", "enhanceMutability", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaTypeUtilsKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[NullabilityQualifier.values().length];
            try {
                iArr[NullabilityQualifier.NULLABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NullabilityQualifier.NOT_NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ProjectionKind.values().length];
            try {
                iArr2[ProjectionKind.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[ProjectionKind.OUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ProjectionKind.STAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ProjectionKind.INVARIANT.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[MutabilityQualifier.values().length];
            try {
                iArr3[MutabilityQualifier.READ_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr3[MutabilityQualifier.MUTABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    private static final int computeSubtreeSizes(ConeKotlinType coneKotlinType, List<Integer> list) {
        int size = list.size();
        int i = 0;
        list.add(0);
        ConeTypeProjection[] typeArguments = coneKotlinType.getTypeArguments();
        int length = typeArguments.length;
        int i2 = 0;
        while (true) {
            int iComputeSubtreeSizes = 1;
            if (i >= length) {
                list.set(size, Integer.valueOf(1 + i2));
                return list.get(size).intValue();
            }
            ConeKotlinType type = ConeTypeProjectionKt.getType(typeArguments[i]);
            if (type != null) {
                iComputeSubtreeSizes = computeSubtreeSizes(type, list);
            } else {
                list.add(1);
            }
            i2 += iComputeSubtreeSizes;
            i++;
        }
    }

    private static final boolean contentIdentityEqual(ConeTypeProjection[] coneTypeProjectionArr, ConeTypeProjection[] coneTypeProjectionArr2) {
        if (coneTypeProjectionArr == coneTypeProjectionArr2) {
            return true;
        }
        if (coneTypeProjectionArr.length == coneTypeProjectionArr2.length) {
            int length = coneTypeProjectionArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                if (coneTypeProjectionArr[i] == coneTypeProjectionArr2[i2]) {
                    i++;
                    i2 = i3;
                }
            }
            return true;
        }
        return false;
    }

    public static final ConeKotlinType enhance(ConeKotlinType coneKotlinType, FirSession firSession, Function1<? super Integer, JavaTypeQualifiers> function1) {
        coneKotlinType.getClass();
        firSession.getClass();
        function1.getClass();
        ArrayList arrayList = new ArrayList();
        computeSubtreeSizes(coneKotlinType, arrayList);
        Unit unit = Unit.INSTANCE;
        return enhanceConeKotlinType(coneKotlinType, firSession, function1, 0, arrayList, false);
    }

    private static final ConeKotlinType enhanceConeKotlinType(ConeKotlinType coneKotlinType, FirSession firSession, Function1<? super Integer, JavaTypeQualifiers> function1, int i, List<Integer> list, boolean z) {
        ConeTypeProjection[] typeArguments;
        if (!(coneKotlinType instanceof ConeFlexibleType)) {
            if (coneKotlinType instanceof ConeSimpleKotlinType) {
                return enhanceInflexibleType((ConeRigidType) coneKotlinType, firSession, TypeComponentPosition.INFLEXIBLE, function1, i, list, null, false, z);
            }
            return null;
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
        ConeRigidType coneRigidTypeEnhanceInflexibleType = enhanceInflexibleType(coneFlexibleType.getLowerBound(), firSession, TypeComponentPosition.FLEXIBLE_LOWER, function1, i, list, null, false, z);
        ConeRigidType upperBound = coneFlexibleType.getUpperBound();
        TypeComponentPosition typeComponentPosition = TypeComponentPosition.FLEXIBLE_UPPER;
        if (coneFlexibleType.getIsTrivial()) {
            typeArguments = coneRigidTypeEnhanceInflexibleType != null ? coneRigidTypeEnhanceInflexibleType.getTypeArguments() : null;
        } else {
            typeArguments = null;
        }
        ConeRigidType coneRigidTypeEnhanceInflexibleType2 = enhanceInflexibleType(upperBound, firSession, typeComponentPosition, function1, i, list, typeArguments, false, z);
        if (coneRigidTypeEnhanceInflexibleType == null && coneRigidTypeEnhanceInflexibleType2 == null) {
            return null;
        }
        if (coneKotlinType instanceof ConeRawType) {
            ConeRawType.Companion companion = ConeRawType.INSTANCE;
            if (coneRigidTypeEnhanceInflexibleType == null) {
                coneRigidTypeEnhanceInflexibleType = ((ConeRawType) coneKotlinType).getLowerBound();
            }
            if (coneRigidTypeEnhanceInflexibleType2 == null) {
                coneRigidTypeEnhanceInflexibleType2 = ((ConeRawType) coneKotlinType).getUpperBound();
            }
            return companion.create(coneRigidTypeEnhanceInflexibleType, coneRigidTypeEnhanceInflexibleType2);
        }
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
        if (coneRigidTypeEnhanceInflexibleType == null) {
            coneRigidTypeEnhanceInflexibleType = coneFlexibleType.getLowerBound();
        }
        if (coneRigidTypeEnhanceInflexibleType2 == null) {
            coneRigidTypeEnhanceInflexibleType2 = coneFlexibleType.getUpperBound();
        }
        return withEnhancedNullabilityAttributeIfRigid(TypeUtilsKt.coneFlexibleOrSimpleType(typeContext, coneRigidTypeEnhanceInflexibleType, coneRigidTypeEnhanceInflexibleType2, coneFlexibleType.getIsTrivial()));
    }

    /* JADX WARN: Code duplicated, block: B:51:0x0115  */
    /* JADX WARN: Multi-variable type inference failed */
    private static final ConeRigidType enhanceInflexibleType(ConeLookupTagBasedType coneLookupTagBasedType, FirSession firSession, Function1<? super Integer, JavaTypeQualifiers> function1, int i, List<Integer> list, boolean z, boolean z2, NullabilityQualifier nullabilityQualifier, ConeClassifierLookupTag coneClassifierLookupTag, boolean z3, ConeTypeProjection[] coneTypeProjectionArr) {
        boolean isMarkedNullable;
        ConeTypeProjection[] coneTypeProjectionArr2;
        ConeDefinitelyNotNullType coneDefinitelyNotNullTypeCreate$default;
        FirSession firSession2;
        Function1<? super Integer, JavaTypeQualifiers> function2;
        ConeKotlinTypeProjectionOut coneKotlinTypeProjectionOutEnhanceConeKotlinType;
        ConeKotlinTypeProjectionOut coneKotlinTypeProjectionOut;
        ConeKotlinTypeProjectionOut coneKotlinTypeProjectionIn;
        FirClassLikeDeclaration firClassLikeDeclaration;
        int i2 = nullabilityQualifier == null ? -1 : WhenMappings.$EnumSwitchMapping$0[nullabilityQualifier.ordinal()];
        boolean z4 = false;
        if (i2 != 1) {
            isMarkedNullable = i2 != 2 ? coneLookupTagBasedType.getIsMarkedNullable() : false;
        } else {
            isMarkedNullable = true;
        }
        int i3 = i + 1;
        if (coneTypeProjectionArr == null) {
            int length = coneLookupTagBasedType.getTypeArguments().length;
            coneTypeProjectionArr2 = new ConeTypeProjection[length];
            int i4 = i3;
            int i5 = 0;
            while (i5 < length) {
                ConeTypeProjection coneTypeProjection = coneLookupTagBasedType.getTypeArguments()[i5];
                int iIntValue = i4 + list.get(i4).intValue();
                if (ConeTypeProjectionKt.getType(coneTypeProjection) == null) {
                    function2 = function1;
                    if (((JavaTypeQualifiers) function2.invoke(Integer.valueOf(i4))).getNullability() == NullabilityQualifier.FORCE_FLEXIBILITY) {
                        firSession2 = firSession;
                        FirClassLikeSymbol<?> classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(coneLookupTagBasedType.getLookupTag(), firSession2);
                        List<FirTypeParameterRef> typeParameters = (classLikeSymbol == null || (firClassLikeDeclaration = (FirClassLikeDeclaration) classLikeSymbol.getFir()) == null) ? null : firClassLikeDeclaration.getTypeParameters();
                        if (typeParameters != null) {
                            ConeKotlinType coneType = FirTypeUtilsKt.getConeType((FirTypeRef) CollectionsKt.first(((FirTypeParameter) typeParameters.get(i5).getSymbol().getFir()).getBounds()));
                            coneKotlinTypeProjectionOut = new ConeKotlinTypeProjectionOut(new ConeFlexibleType((ConeRigidType) TypeUtilsKt.withNullability$default(ConeTypeUtilsKt.lowerBoundIfFlexible(coneType), false, TypeComponentsKt.getTypeContext(firSession2), null, false, 12, null), (ConeRigidType) TypeUtilsKt.withNullability$default(ConeTypeUtilsKt.upperBoundIfFlexible(coneType), true, TypeComponentsKt.getTypeContext(firSession2), null, false, 12, null), false));
                        }
                        coneTypeProjectionArr2[i5] = coneKotlinTypeProjectionOut;
                        i5++;
                        i4 = iIntValue;
                    } else {
                        firSession2 = firSession;
                    }
                } else {
                    firSession2 = firSession;
                    function2 = function1;
                }
                ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
                if (type == null || (coneKotlinTypeProjectionOutEnhanceConeKotlinType = enhanceConeKotlinType(type, firSession2, function2, i4, list, z3)) == null) {
                    coneKotlinTypeProjectionOutEnhanceConeKotlinType = coneLookupTagBasedType.getTypeArguments()[i5];
                } else {
                    int i6 = WhenMappings.$EnumSwitchMapping$1[coneTypeProjection.getKind().ordinal()];
                    if (i6 != 1) {
                        if (i6 == 2) {
                            coneKotlinTypeProjectionIn = new ConeKotlinTypeProjectionOut(coneKotlinTypeProjectionOutEnhanceConeKotlinType);
                        } else if (i6 == 3) {
                            coneKotlinTypeProjectionOutEnhanceConeKotlinType = ConeStarProjection.INSTANCE;
                        } else if (i6 != 4) {
                            bu8.a();
                            return null;
                        }
                        if (coneKotlinTypeProjectionOutEnhanceConeKotlinType == null) {
                            coneKotlinTypeProjectionOutEnhanceConeKotlinType = coneLookupTagBasedType.getTypeArguments()[i5];
                        }
                    } else {
                        coneKotlinTypeProjectionIn = new ConeKotlinTypeProjectionIn(coneKotlinTypeProjectionOutEnhanceConeKotlinType);
                    }
                    coneKotlinTypeProjectionOutEnhanceConeKotlinType = coneKotlinTypeProjectionIn;
                    if (coneKotlinTypeProjectionOutEnhanceConeKotlinType == null) {
                        coneKotlinTypeProjectionOutEnhanceConeKotlinType = coneLookupTagBasedType.getTypeArguments()[i5];
                    }
                }
                coneKotlinTypeProjectionOut = coneKotlinTypeProjectionOutEnhanceConeKotlinType;
                coneTypeProjectionArr2[i5] = coneKotlinTypeProjectionOut;
                i5++;
                i4 = iIntValue;
            }
        } else {
            coneTypeProjectionArr2 = coneTypeProjectionArr;
        }
        if (nullabilityQualifier == NullabilityQualifier.NOT_NULL && !CompilerConeAttributesKt.getHasEnhancedNullability(coneLookupTagBasedType)) {
            z4 = true;
        }
        if (Intrinsics.areEqual(coneLookupTagBasedType.getLookupTag(), coneClassifierLookupTag) && isMarkedNullable == coneLookupTagBasedType.getIsMarkedNullable() && !z4 && contentIdentityEqual(coneTypeProjectionArr2, coneLookupTagBasedType.getTypeArguments())) {
            return null;
        }
        ConeAttributes attributes = coneLookupTagBasedType.getAttributes();
        if (z4) {
            attributes = attributes.add((ConeAttribute<?>) CompilerConeAttributes.EnhancedNullability.INSTANCE);
        }
        ConeLookupTagBasedType coneLookupTagBasedTypeConstructType = TypeConstructionUtilsKt.constructType(coneClassifierLookupTag, coneTypeProjectionArr2, isMarkedNullable, attributes);
        return ((z2 || (z && nullabilityQualifier == null)) && (coneDefinitelyNotNullTypeCreate$default = TypeUtilsKt.create$default(ConeDefinitelyNotNullType.INSTANCE, coneLookupTagBasedTypeConstructType, TypeComponentsKt.getTypeContext(firSession), false, 4, null)) != null) ? coneDefinitelyNotNullTypeCreate$default : coneLookupTagBasedTypeConstructType;
    }

    private static final ConeClassifierLookupTag enhanceMutability(ConeClassifierLookupTag coneClassifierLookupTag, JavaTypeQualifiers javaTypeQualifiers, TypeComponentPosition typeComponentPosition) {
        if (!TypeComponentPositionKt.shouldEnhance(typeComponentPosition) || !(coneClassifierLookupTag instanceof ConeClassLikeLookupTag)) {
            return coneClassifierLookupTag;
        }
        MutabilityQualifier mutability = javaTypeQualifiers.getMutability();
        int i = mutability == null ? -1 : WhenMappings.$EnumSwitchMapping$2[mutability.ordinal()];
        if (i == -1) {
            return coneClassifierLookupTag;
        }
        if (i == 1) {
            ClassId classIdMutableToReadOnly = mutableToReadOnly(((ConeClassLikeLookupTag) coneClassifierLookupTag).getClassId());
            return (typeComponentPosition != TypeComponentPosition.FLEXIBLE_LOWER || classIdMutableToReadOnly == null) ? coneClassifierLookupTag : TypeConstructionUtilsKt.toLookupTag(classIdMutableToReadOnly);
        }
        if (i == 2) {
            ClassId onlyToMutable = readOnlyToMutable(((ConeClassLikeLookupTag) coneClassifierLookupTag).getClassId());
            return (typeComponentPosition != TypeComponentPosition.FLEXIBLE_UPPER || onlyToMutable == null) ? coneClassifierLookupTag : TypeConstructionUtilsKt.toLookupTag(onlyToMutable);
        }
        bu8.a();
        return null;
    }

    private static final boolean enhancesSomethingForError(JavaTypeQualifiers javaTypeQualifiers) {
        if (javaTypeQualifiers.isNullabilityQualifierForWarning()) {
            return false;
        }
        return (javaTypeQualifiers.getNullability() == null && javaTypeQualifiers.getMutability() == null && !javaTypeQualifiers.getDefinitelyNotNull()) ? false : true;
    }

    private static final ClassId mutableToReadOnly(ClassId classId) {
        return JavaToKotlinClassMap.INSTANCE.mutableToReadOnly(classId);
    }

    public static final ClassId readOnlyToMutable(ClassId classId) {
        classId.getClass();
        return JavaToKotlinClassMap.INSTANCE.readOnlyToMutable(classId);
    }

    private static final ConeKotlinType withEnhancedNullabilityAttributeIfRigid(ConeKotlinType coneKotlinType) {
        return !(coneKotlinType instanceof ConeFlexibleType) ? TypeUtilsKt.withAttributes(coneKotlinType, coneKotlinType.getAttributes().add((ConeAttribute<?>) CompilerConeAttributes.EnhancedNullability.INSTANCE)) : coneKotlinType;
    }

    private static final ConeRigidType enhanceInflexibleType(ConeRigidType coneRigidType, FirSession firSession, TypeComponentPosition typeComponentPosition, Function1<? super Integer, JavaTypeQualifiers> function1, int i, List<Integer> list, ConeTypeProjection[] coneTypeProjectionArr, boolean z, boolean z2) {
        ConeLookupTagBasedType coneLookupTagBasedTypeConstructType;
        if (coneRigidType instanceof ConeDefinitelyNotNullType) {
            return enhanceInflexibleType(((ConeDefinitelyNotNullType) coneRigidType).getOriginal(), firSession, typeComponentPosition, function1, i, list, coneTypeProjectionArr, true, z2);
        }
        boolean zShouldEnhance = TypeComponentPositionKt.shouldEnhance(typeComponentPosition);
        if ((!zShouldEnhance && coneRigidType.getTypeArguments().length == 0) || !(coneRigidType instanceof ConeLookupTagBasedType)) {
            return null;
        }
        JavaTypeQualifiers javaTypeQualifiers = (JavaTypeQualifiers) function1.invoke(Integer.valueOf(i));
        ConeLookupTagBasedType coneLookupTagBasedType = (ConeLookupTagBasedType) coneRigidType;
        ConeClassifierLookupTag coneClassifierLookupTagEnhanceMutability = enhanceMutability(coneLookupTagBasedType.getLookupTag(), javaTypeQualifiers, typeComponentPosition);
        ConeRigidType coneRigidTypeEnhanceInflexibleType = enhanceInflexibleType(coneLookupTagBasedType, firSession, function1, i, list, z, javaTypeQualifiers.getDefinitelyNotNull(), zShouldEnhance ? javaTypeQualifiers.getNullability() : null, coneClassifierLookupTagEnhanceMutability, z2 || (javaTypeQualifiers.isNullabilityQualifierForWarning() && !FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).supportsFeature(LanguageFeature.SupportJavaErrorEnhancementOfArgumentsOfWarningLevelEnhanced)), coneTypeProjectionArr);
        if (coneRigidTypeEnhanceInflexibleType == null || (!javaTypeQualifiers.isNullabilityQualifierForWarning() && ((!javaTypeQualifiers.isMutabilityQualifierForWarning() || Intrinsics.areEqual(coneClassifierLookupTagEnhanceMutability, coneLookupTagBasedType.getLookupTag())) && !z2))) {
            if (coneRigidTypeEnhanceInflexibleType != null) {
                return (ConeRigidType) TypeUtilsKt.withAttributes(coneRigidTypeEnhanceInflexibleType, coneRigidTypeEnhanceInflexibleType.getAttributes().remove(Reflection.getOrCreateKotlinClass(EnhancedTypeForWarningAttribute.class)));
            }
            return null;
        }
        ConeAttributes coneAttributesAdd = coneRigidType.getAttributes().add(new EnhancedTypeForWarningAttribute(EnhancedTypeForWarningAttributeKt.getEnhancedTypeForWarningOrSelf(coneRigidTypeEnhanceInflexibleType), z2 && enhancesSomethingForError(javaTypeQualifiers)));
        if (!Intrinsics.areEqual(coneClassifierLookupTagEnhanceMutability, coneLookupTagBasedType.getLookupTag()) && !javaTypeQualifiers.isMutabilityQualifierForWarning()) {
            return TypeConstructionUtilsKt.constructType(coneClassifierLookupTagEnhanceMutability, coneRigidTypeEnhanceInflexibleType.getTypeArguments(), coneLookupTagBasedType.getIsMarkedNullable(), coneAttributesAdd);
        }
        if (!Intrinsics.areEqual(coneClassifierLookupTagEnhanceMutability, coneLookupTagBasedType.getLookupTag()) && !javaTypeQualifiers.isNullabilityQualifierForWarning()) {
            coneLookupTagBasedTypeConstructType = TypeConstructionUtilsKt.constructType(coneLookupTagBasedType.getLookupTag(), coneRigidTypeEnhanceInflexibleType.getTypeArguments(), ConeTypeUtilsKt.isMarkedNullable(coneRigidTypeEnhanceInflexibleType), coneAttributesAdd);
        } else {
            coneLookupTagBasedTypeConstructType = (ConeLookupTagBasedType) TypeUtilsKt.withArguments(TypeUtilsKt.withAttributes(coneRigidType, coneAttributesAdd), coneRigidTypeEnhanceInflexibleType.getTypeArguments());
        }
        if (z) {
            return TypeUtilsKt.create$default(ConeDefinitelyNotNullType.INSTANCE, coneLookupTagBasedTypeConstructType, TypeComponentsKt.getTypeContext(firSession), false, 4, null);
        }
        return coneLookupTagBasedTypeConstructType;
    }
}

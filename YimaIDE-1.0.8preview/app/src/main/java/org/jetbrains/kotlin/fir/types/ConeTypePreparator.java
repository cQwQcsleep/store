package org.jetbrains.kotlin.fir.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.types.AbstractTypePreparator;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\b\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\n2\u0006\u0010\u000b\u001a\u0002H\tH\u0002¢\u0006\u0002\u0010\fJ\u0010\u0010\b\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0016J\f\u0010\u0011\u001a\u00020\r*\u00020\rH\u0002J\f\u0010\u0011\u001a\u00020\n*\u00020\nH\u0002J\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0012H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/types/ConeTypePreparator;", "Lorg/jetbrains/kotlin/types/AbstractTypePreparator;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "prepareType", "T", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", ModuleXmlParser.TYPE, "(Lorg/jetbrains/kotlin/fir/types/ConeRigidType;)Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "clearTypeFromUnnecessaryAttributes", "Lorg/jetbrains/kotlin/types/model/RigidTypeMarker;", "dropEnhancedNullability", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeTypePreparator extends AbstractTypePreparator {
    private final FirSession session;

    public ConeTypePreparator(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final ConeKotlinType dropEnhancedNullability(ConeKotlinType coneKotlinType) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (coneKotlinType instanceof ConeFlexibleType) {
            ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
            ConeRigidType coneRigidTypeDropEnhancedNullability = dropEnhancedNullability(coneFlexibleType.getLowerBound());
            ConeRigidType coneRigidTypeDropEnhancedNullability2 = dropEnhancedNullability(coneFlexibleType.getUpperBound());
            if (coneRigidTypeDropEnhancedNullability == coneFlexibleType.getLowerBound() && coneRigidTypeDropEnhancedNullability2 == coneFlexibleType.getUpperBound()) {
                return coneKotlinType;
            }
            return coneKotlinType instanceof ConeRawType ? ConeRawType.INSTANCE.create(coneRigidTypeDropEnhancedNullability, coneRigidTypeDropEnhancedNullability2) : new ConeFlexibleType(coneRigidTypeDropEnhancedNullability, coneRigidTypeDropEnhancedNullability2, coneFlexibleType.getIsTrivial());
        }
        if (!(coneKotlinType instanceof ConeIntersectionType)) {
            if (!(coneKotlinType instanceof ConeCapturedType)) {
                if (coneKotlinType instanceof ConeRigidType) {
                    return dropEnhancedNullability((ConeRigidType) coneKotlinType);
                }
                bu8.a();
                return null;
            }
            ConeCapturedType coneCapturedType = (ConeCapturedType) coneKotlinType;
            ConeCapturedTypeConstructor constructor = coneCapturedType.getConstructor();
            ConeKotlinType lowerType = constructor.getLowerType();
            ConeKotlinType coneKotlinTypeDropEnhancedNullability = lowerType != null ? dropEnhancedNullability(lowerType) : null;
            ConeTypeProjection coneTypeProjectionDropEnhancedNullability = dropEnhancedNullability(constructor.getProjection());
            ConeCapturedTypeConstructor coneCapturedTypeConstructor = (coneKotlinTypeDropEnhancedNullability == constructor.getLowerType() && coneTypeProjectionDropEnhancedNullability == constructor.getProjection()) ? constructor : new ConeCapturedTypeConstructor(coneTypeProjectionDropEnhancedNullability, coneKotlinTypeDropEnhancedNullability, constructor.getCaptureStatus(), null, null, 24, null);
            if (constructor != coneCapturedTypeConstructor) {
                coneCapturedType = new ConeCapturedType(coneCapturedType.isMarkedNullable(), coneCapturedTypeConstructor, coneCapturedType.getAttributes());
            }
            return dropEnhancedNullability((ConeRigidType) coneCapturedType);
        }
        ConeIntersectionType coneIntersectionType = (ConeIntersectionType) coneKotlinType;
        Collection<ConeKotlinType> intersectedTypes = coneIntersectionType.getIntersectedTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intersectedTypes, 10));
        Iterator<T> it = intersectedTypes.iterator();
        while (it.hasNext()) {
            arrayList.add(dropEnhancedNullability((ConeKotlinType) it.next()));
        }
        List<Pair> listZip = CollectionsKt.zip(arrayList, coneIntersectionType.getIntersectedTypes());
        if ((listZip instanceof Collection) && listZip.isEmpty()) {
            return coneKotlinType;
        }
        for (Pair pair : listZip) {
            if (((ConeKotlinType) pair.component1()) != ((ConeKotlinType) pair.component2())) {
                ConeKotlinType upperBoundForApproximation = coneIntersectionType.getUpperBoundForApproximation();
                coneKotlinType = new ConeIntersectionType(arrayList, upperBoundForApproximation != null ? dropEnhancedNullability(upperBoundForApproximation) : null);
                break;
            }
        }
        return coneKotlinType;
    }

    /* JADX INFO: renamed from: clearTypeFromUnnecessaryAttributes, reason: merged with bridge method [inline-methods] */
    public ConeRigidType m697clearTypeFromUnnecessaryAttributes(RigidTypeMarker type) {
        type.getClass();
        return dropEnhancedNullability((ConeRigidType) type);
    }

    public final FirSession getSession() {
        return this.session;
    }

    /* JADX INFO: renamed from: prepareType, reason: merged with bridge method [inline-methods] */
    public ConeKotlinType m698prepareType(KotlinTypeMarker type) {
        ConeRigidType lowerBound;
        ConeRigidType upperBound;
        type.getClass();
        ConeKotlinType coneKotlinTypeCreate = null;
        if (!(type instanceof ConeKotlinType)) {
            pe1.a("Unexpected type in ConeTypePreparator: ", ConeTypePreparator.class);
            return null;
        }
        ConeKotlinType coneKotlinType = (ConeKotlinType) type;
        if (!(coneKotlinType instanceof ConeFlexibleType)) {
            if (coneKotlinType instanceof ConeRigidType) {
                return prepareType((ConeRigidType) type);
            }
            bu8.a();
            return null;
        }
        ConeFlexibleType coneFlexibleType = (ConeFlexibleType) type;
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(this.session);
        ConeRigidType coneRigidTypePrepareType = prepareType(coneFlexibleType.getLowerBound());
        ConeRigidType lowerBound2 = coneRigidTypePrepareType != coneFlexibleType.getLowerBound() ? coneRigidTypePrepareType : null;
        if (!coneFlexibleType.getIsTrivial()) {
            ConeRigidType coneRigidTypePrepareType2 = prepareType(coneFlexibleType.getUpperBound());
            if (coneRigidTypePrepareType2 == coneFlexibleType.getUpperBound()) {
                coneRigidTypePrepareType2 = null;
            }
            if (lowerBound2 != null || coneRigidTypePrepareType2 != null) {
                if (coneFlexibleType instanceof ConeRawType) {
                    ConeRawType.Companion companion = ConeRawType.INSTANCE;
                    if (lowerBound2 == null || (lowerBound = ConeTypeUtilsKt.lowerBoundIfFlexible(lowerBound2)) == null) {
                        lowerBound = coneFlexibleType.getLowerBound();
                    }
                    if (coneRigidTypePrepareType2 == null || (upperBound = ConeTypeUtilsKt.upperBoundIfFlexible(coneRigidTypePrepareType2)) == null) {
                        upperBound = coneFlexibleType.getUpperBound();
                    }
                    coneKotlinTypeCreate = companion.create(lowerBound, upperBound);
                } else {
                    if (lowerBound2 == null) {
                        lowerBound2 = coneFlexibleType.getLowerBound();
                    }
                    if (coneRigidTypePrepareType2 == null) {
                        coneRigidTypePrepareType2 = coneFlexibleType.getUpperBound();
                    }
                    coneKotlinTypeCreate = TypeUtilsKt.coneFlexibleOrSimpleType(typeContext, lowerBound2, coneRigidTypePrepareType2, false);
                }
            }
        } else if (lowerBound2 != null) {
            coneKotlinTypeCreate = TypeUtilsKt.coneFlexibleOrSimpleType(typeContext, lowerBound2, TypeUtilsKt.withNullability$default(lowerBound2, true, typeContext, null, true, 4, null), true);
        }
        return coneKotlinTypeCreate == null ? coneFlexibleType : coneKotlinTypeCreate;
    }

    private final <T extends ConeRigidType> T prepareType(T type) {
        if (type instanceof ConeClassLikeType) {
            type = TypeExpansionUtilsKt.fullyExpandedType$default((ConeClassLikeType) type, this.session, (Function1) null, 2, (Object) null);
        } else if (type instanceof ConeDefinitelyNotNullType) {
            type = new ConeDefinitelyNotNullType((ConeSimpleKotlinType) prepareType(((ConeDefinitelyNotNullType) type).getOriginal()));
        }
        type.getClass();
        return type;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final ConeRigidType dropEnhancedNullability(ConeRigidType coneRigidType) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType coneKotlinTypeWithAttributes = TypeUtilsKt.withAttributes(coneRigidType, coneRigidType.getAttributes().remove((ConeAttribute<?>) CompilerConeAttributes.EnhancedNullability.INSTANCE));
        ConeTypeProjection[] typeArguments = coneKotlinTypeWithAttributes.getTypeArguments();
        if (typeArguments.length != 0) {
            int length = typeArguments.length;
            ConeTypeProjection[] coneTypeProjectionArr = new ConeTypeProjection[length];
            for (int i = 0; i < length; i++) {
                coneTypeProjectionArr[i] = dropEnhancedNullability(typeArguments[i]);
            }
            coneKotlinTypeWithAttributes = TypeUtilsKt.withArguments(coneKotlinTypeWithAttributes, coneTypeProjectionArr);
        }
        return (ConeRigidType) coneKotlinTypeWithAttributes;
    }

    private final ConeTypeProjection dropEnhancedNullability(ConeTypeProjection coneTypeProjection) {
        if (!(coneTypeProjection instanceof ConeKotlinTypeProjection)) {
            return coneTypeProjection;
        }
        ConeKotlinTypeProjection coneKotlinTypeProjection = (ConeKotlinTypeProjection) coneTypeProjection;
        return ConeTypeProjectionKt.replaceType(coneKotlinTypeProjection, dropEnhancedNullability(coneKotlinTypeProjection.getType()));
    }
}

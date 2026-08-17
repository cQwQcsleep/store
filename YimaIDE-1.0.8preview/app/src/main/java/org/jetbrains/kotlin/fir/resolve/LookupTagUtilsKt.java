package org.jetbrains.kotlin.fir.resolve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationArgumentMappingBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.LookupTagInternals;
import org.jetbrains.kotlin.fir.types.ConeAttribute;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.ParameterNameTypeAttribute;
import org.jetbrains.kotlin.fir.types.ParameterNameTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.util.WeakPair;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\u001a \u0010\u0000\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a&\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0002H\u0007b\u0002\b\t\u001a\u0012\u0010\n\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r\u001a\u0012\u0010\n\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000e\u001a\u001c\u0010\n\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u001a\u0012\u0010\u0013\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b\u001a\u0014\u0010\u0013\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u0017*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a$\u0010\u0019\u001a\u0004\u0018\u00010\u0017*\b\u0012\u0004\u0012\u00020\u000b0\u001c2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u001d"}, d2 = {"getClassAndItsOuterClassesWhenLocal", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "bindSymbolToLookupTag", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/ConeClassLikeLookupTagImpl;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/LookupTagInternals;", "withParameterNameAnnotation", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "parameter", "Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "element", "Lorg/jetbrains/kotlin/KtSourceElement;", "withCombinedAttributesFrom", "other", "Lorg/jetbrains/kotlin/fir/types/ConeAttributes;", "findClassRepresentation", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "dispatchReceiverParameterType", "findClassRepresentationThatIsSubtypeOf", "Lorg/jetbrains/kotlin/fir/symbols/ConeTypeParameterLookupTag;", "supertype", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LookupTagUtilsKt {
    public static FirClassLikeSymbol a(FirSession firSession, FirClassLikeSymbol firClassLikeSymbol) {
        ConeClassLikeLookupTag containingClassLookupTag;
        firClassLikeSymbol.getClass();
        if (!firClassLikeSymbol.getRawStatus().isInner() || (containingClassLookupTag = ClassMembersKt.getContainingClassLookupTag(firClassLikeSymbol)) == null) {
            return null;
        }
        return ToSymbolUtilsKt.toRegularClassSymbol(containingClassLookupTag, firSession);
    }

    @LookupTagInternals
    public static final void bindSymbolToLookupTag(ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl, FirSession firSession, FirClassLikeSymbol<?> firClassLikeSymbol) {
        coneClassLikeLookupTagImpl.getClass();
        firSession.getClass();
        coneClassLikeLookupTagImpl.setBoundSymbol(new WeakPair<>(firSession, firClassLikeSymbol));
    }

    public static final ConeClassLikeLookupTag findClassRepresentation(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirSession firSession) {
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        firSession.getClass();
        if (coneKotlinType instanceof ConeClassLikeType) {
            return TypeExpansionUtilsKt.fullyExpandedType$default((ConeClassLikeType) coneKotlinType, firSession, (Function1) null, 2, (Object) null).getLookupTag();
        }
        if (coneKotlinType instanceof ConeDynamicType) {
            return findClassRepresentation(((ConeDynamicType) coneKotlinType).getUpperBound(), coneKotlinType2, firSession);
        }
        if (coneKotlinType instanceof ConeFlexibleType) {
            return findClassRepresentation(((ConeFlexibleType) coneKotlinType).getLowerBound(), coneKotlinType2, firSession);
        }
        if (coneKotlinType instanceof ConeCapturedType) {
            List<ConeKotlinType> supertypes = ((ConeCapturedType) coneKotlinType).getConstructor().getSupertypes();
            if (supertypes == null) {
                supertypes = CollectionsKt.emptyList();
            }
            return findClassRepresentationThatIsSubtypeOf(supertypes, coneKotlinType2, firSession);
        }
        if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
            return findClassRepresentation(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal(), coneKotlinType2, firSession);
        }
        if (coneKotlinType instanceof ConeIntegerLiteralType) {
            return findClassRepresentationThatIsSubtypeOf((Collection<? extends ConeKotlinType>) ((ConeIntegerLiteralType) coneKotlinType).getPossibleTypes(), coneKotlinType2, firSession);
        }
        if (coneKotlinType instanceof ConeIntersectionType) {
            return findClassRepresentationThatIsSubtypeOf((Collection<? extends ConeKotlinType>) ((ConeIntersectionType) coneKotlinType).getIntersectedTypes(), coneKotlinType2, firSession);
        }
        if (coneKotlinType instanceof ConeTypeParameterType) {
            return findClassRepresentationThatIsSubtypeOf(((ConeTypeParameterType) coneKotlinType).getLookupTag(), coneKotlinType2, firSession);
        }
        if (coneKotlinType instanceof ConeTypeVariableType) {
            TypeParameterMarker originalTypeParameter = ((ConeTypeVariableType) coneKotlinType).getTypeConstructor().getOriginalTypeParameter();
            ConeTypeParameterLookupTag coneTypeParameterLookupTag = originalTypeParameter instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) originalTypeParameter : null;
            if (coneTypeParameterLookupTag != null) {
                return findClassRepresentationThatIsSubtypeOf(coneTypeParameterLookupTag, coneKotlinType2, firSession);
            }
            return null;
        }
        if (!(coneKotlinType instanceof ConeStubType)) {
            if (coneKotlinType instanceof ConeLookupTagBasedType) {
                return null;
            }
            bu8.a();
            return null;
        }
        TypeParameterMarker originalTypeParameter2 = ((ConeStubType) coneKotlinType).getConstructor().getVariable().getTypeConstructor().getOriginalTypeParameter();
        ConeTypeParameterLookupTag coneTypeParameterLookupTag2 = originalTypeParameter2 instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) originalTypeParameter2 : null;
        if (coneTypeParameterLookupTag2 != null) {
            return findClassRepresentationThatIsSubtypeOf(coneTypeParameterLookupTag2, coneKotlinType2, firSession);
        }
        return null;
    }

    private static final ConeClassLikeLookupTag findClassRepresentationThatIsSubtypeOf(ConeTypeParameterLookupTag coneTypeParameterLookupTag, ConeKotlinType coneKotlinType, FirSession firSession) {
        List<FirResolvedTypeRef> resolvedBounds = coneTypeParameterLookupTag.getTypeParameterSymbol().getResolvedBounds();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(resolvedBounds, 10));
        Iterator<T> it = resolvedBounds.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
        }
        return findClassRepresentationThatIsSubtypeOf(arrayList, coneKotlinType, firSession);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Set<FirClassLikeSymbol<?>> getClassAndItsOuterClassesWhenLocal(FirClassLikeSymbol<?> firClassLikeSymbol, final FirSession firSession) {
        firClassLikeSymbol.getClass();
        firSession.getClass();
        if (!((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getIsLocal()) {
            firClassLikeSymbol = null;
        }
        return SequencesKt.toSet(SequencesKt.generateSequence(firClassLikeSymbol, new Function1() { // from class: yi9
            public final Object invoke(Object obj) {
                return LookupTagUtilsKt.a(firSession, (FirClassLikeSymbol) obj);
            }
        }));
    }

    private static final ConeKotlinType withCombinedAttributesFrom(ConeKotlinType coneKotlinType, ConeAttributes coneAttributes) {
        return coneAttributes.isEmpty() ? coneKotlinType : TypeUtilsKt.withAttributes(coneKotlinType, coneKotlinType.getAttributes().add(coneAttributes));
    }

    public static final ConeKotlinType withParameterNameAnnotation(ConeKotlinType coneKotlinType, Name name, KtSourceElement ktSourceElement) {
        coneKotlinType.getClass();
        name.getClass();
        if (Intrinsics.areEqual(name, SpecialNames.NO_NAME_PROVIDED) || Intrinsics.areEqual(name, SpecialNames.UNDERSCORE_FOR_UNUSED_VAR) || ParameterNameTypeAttributeKt.getParameterNameAttribute(coneKotlinType.getAttributes()) != null) {
            return coneKotlinType;
        }
        KtSourceElement ktSourceElementFakeElement$default = ktSourceElement != null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ParameterNameAnnotationCall.INSTANCE, null, 2, null) : null;
        FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
        firAnnotationBuilder.setSource(ktSourceElementFakeElement$default);
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setSource(ktSourceElementFakeElement$default);
        firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(StandardNames.FqNames.parameterNameClassId), new ConeTypeProjection[0], false, null, 8, null));
        firAnnotationBuilder.setAnnotationTypeRef(firResolvedTypeRefBuilder.build());
        FirAnnotationArgumentMappingBuilder firAnnotationArgumentMappingBuilder = new FirAnnotationArgumentMappingBuilder();
        firAnnotationArgumentMappingBuilder.getMapping().put(StandardClassIds$Annotations.ParameterNames.INSTANCE.getParameterNameName(), FirConstExpressionBuilderKt.buildLiteralExpression$default(ktSourceElementFakeElement$default, ConstantValueKind.String.INSTANCE, name.asString(), null, true, null, 40, null));
        firAnnotationBuilder.setArgumentMapping(firAnnotationArgumentMappingBuilder.build());
        return TypeUtilsKt.withAttributes(coneKotlinType, coneKotlinType.getAttributes().add((ConeAttribute<?>) new ParameterNameTypeAttribute(name, CollectionsKt.listOf(firAnnotationBuilder.mo288build()))));
    }

    public static final ConeKotlinType withCombinedAttributesFrom(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        return withCombinedAttributesFrom(coneKotlinType, coneKotlinType2.getAttributes());
    }

    private static final ConeClassLikeLookupTag findClassRepresentationThatIsSubtypeOf(Collection<? extends ConeKotlinType> collection, ConeKotlinType coneKotlinType, FirSession firSession) {
        FirSession firSession2;
        Object next;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        Iterator<T> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                firSession2 = firSession;
                next = null;
                break;
            }
            next = it.next();
            firSession2 = firSession;
            if (TypeUtilsKt.isSubtypeOf$default((ConeKotlinType) next, coneRigidTypeLowerBoundIfFlexible, firSession2, false, 4, null)) {
                break;
            }
            firSession = firSession2;
        }
        ConeKotlinType coneKotlinType2 = (ConeKotlinType) next;
        if (coneKotlinType2 == null) {
            return null;
        }
        return findClassRepresentation(coneKotlinType2, coneRigidTypeLowerBoundIfFlexible, firSession2);
    }

    public static final ConeKotlinType withParameterNameAnnotation(ConeKotlinType coneKotlinType, FirValueParameter firValueParameter) {
        coneKotlinType.getClass();
        firValueParameter.getClass();
        return withParameterNameAnnotation(coneKotlinType, firValueParameter.getName(), firValueParameter.getSource());
    }

    public static final ConeKotlinType withParameterNameAnnotation(ConeKotlinType coneKotlinType, FirFunctionTypeParameter firFunctionTypeParameter) {
        coneKotlinType.getClass();
        firFunctionTypeParameter.getClass();
        Name name = firFunctionTypeParameter.getName();
        return name == null ? coneKotlinType : withParameterNameAnnotation(coneKotlinType, name, firFunctionTypeParameter.getSource());
    }
}

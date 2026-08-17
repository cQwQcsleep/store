package org.jetbrains.kotlin.fir.deserialization;

import defpackage.f2f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.StandardTypes;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationArgumentMappingBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirClassReferenceExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCollectionLiteralBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirEnumEntryDeserializedAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirGetClassCallBuilder;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolver;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.serialization.deserialization.NameResolverUtilKt;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a@\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a8\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00012\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a6\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00012\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a]\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u000e\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u0011*\u0002H\u00102\u0006\u0010\u0003\u001a\u00020\u00042\u001a\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u0002H\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0001\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u0014\u001a,\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002\u001a \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u001c\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\"\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#H\u0002¨\u0006$"}, d2 = {"loadAnnotationsFromMetadataGuarded", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "annotations", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "useSiteTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "loadAnnotationsFromMetadataIfNotEmpty", "loadAnnotationsFromMetadata", "loadAnnotationsFromProtocol", "T", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;", "extension", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;", "(Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;)Ljava/util/List;", "deserializeAnnotation", "proto", "createArgumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "toFirExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation$Argument$Value;", "const", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "kind", "Lorg/jetbrains/kotlin/types/ConstantValueKind;", "value", Argument.Delimiters.none, "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "org.jetbrains.kotlin:fir-deserialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnnotationDeserializationUtilKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ProtoBuf.Annotation.Argument.Value.Type.values().length];
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.LONG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.CHAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.DOUBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.STRING.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.ANNOTATION.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.CLASS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.ENUM.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ProtoBuf.Annotation.Argument.Value.Type.ARRAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: const, reason: not valid java name */
    private static final FirLiteralExpression m371const(ConstantValueKind constantValueKind, Object obj, FirResolvedTypeRef firResolvedTypeRef) {
        FirLiteralExpression firLiteralExpressionBuildLiteralExpression$default = FirConstExpressionBuilderKt.buildLiteralExpression$default(null, constantValueKind, obj, null, true, null, 40, null);
        firLiteralExpressionBuildLiteralExpression$default.replaceConeTypeOrNull(firResolvedTypeRef.getConeType());
        return firLiteralExpressionBuildLiteralExpression$default;
    }

    private static final FirAnnotationArgumentMapping createArgumentMapping(FirSession firSession, ProtoBuf.Annotation annotation, NameResolver nameResolver) {
        FirAnnotationArgumentMappingBuilder firAnnotationArgumentMappingBuilder = new FirAnnotationArgumentMappingBuilder();
        if (annotation.getArgumentCount() != 0) {
            List<ProtoBuf.Annotation.Argument> argumentList = annotation.getArgumentList();
            argumentList.getClass();
            ArrayList arrayList = new ArrayList();
            for (ProtoBuf.Annotation.Argument argument : argumentList) {
                Name name = NameResolverUtilKt.getName(nameResolver, argument.getNameId());
                ProtoBuf.Annotation.Argument.Value value = argument.getValue();
                value.getClass();
                Pair pair = TuplesKt.to(name, toFirExpression(value, firSession, nameResolver));
                if (pair != null) {
                    arrayList.add(pair);
                }
            }
            MapsKt.toMap(arrayList, firAnnotationArgumentMappingBuilder.getMapping());
        }
        return firAnnotationArgumentMappingBuilder.build();
    }

    private static final FirAnnotation deserializeAnnotation(FirSession firSession, ProtoBuf.Annotation annotation, NameResolver nameResolver, AnnotationUseSiteTarget annotationUseSiteTarget) {
        ClassId classId = NameResolverUtilKt.getClassId(nameResolver, annotation.getId());
        FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(TypeConstructionUtilsKt.constructClassType$default(TypeConstructionUtilsKt.toLookupTag(classId), null, false, null, 7, null));
        firAnnotationBuilder.setAnnotationTypeRef(firResolvedTypeRefBuilder.build());
        FirLazyDeclarationResolver lazyDeclarationResolver = FirLazyDeclarationResolverKt.getLazyDeclarationResolver(firSession);
        Boolean bool = lazyDeclarationResolver.get_lazyResolveContractChecksEnabled().get();
        lazyDeclarationResolver.get_lazyResolveContractChecksEnabled().set(Boolean.FALSE);
        try {
            firAnnotationBuilder.setArgumentMapping(createArgumentMapping(firSession, annotation, nameResolver));
            Unit unit = Unit.INSTANCE;
            lazyDeclarationResolver.get_lazyResolveContractChecksEnabled().set(bool);
            if (annotationUseSiteTarget != null) {
                firAnnotationBuilder.setUseSiteTarget(annotationUseSiteTarget);
            }
            return firAnnotationBuilder.mo289build();
        } catch (Throwable th) {
            lazyDeclarationResolver.get_lazyResolveContractChecksEnabled().set(bool);
            throw th;
        }
    }

    public static /* synthetic */ FirAnnotation deserializeAnnotation$default(FirSession firSession, ProtoBuf.Annotation annotation, NameResolver nameResolver, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if ((i & 8) != 0) {
            annotationUseSiteTarget = null;
        }
        return deserializeAnnotation(firSession, annotation, nameResolver, annotationUseSiteTarget);
    }

    public static final List<FirAnnotation> loadAnnotationsFromMetadata(FirSession firSession, List<ProtoBuf.Annotation> list, NameResolver nameResolver, AnnotationUseSiteTarget annotationUseSiteTarget) {
        firSession.getClass();
        list.getClass();
        nameResolver.getClass();
        List<ProtoBuf.Annotation> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(deserializeAnnotation(firSession, (ProtoBuf.Annotation) it.next(), nameResolver, annotationUseSiteTarget));
        }
        return arrayList;
    }

    public static /* synthetic */ List loadAnnotationsFromMetadata$default(FirSession firSession, List list, NameResolver nameResolver, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if ((i & 8) != 0) {
            annotationUseSiteTarget = null;
        }
        return loadAnnotationsFromMetadata(firSession, list, nameResolver, annotationUseSiteTarget);
    }

    public static final List<FirAnnotation> loadAnnotationsFromMetadataGuarded(FirSession firSession, List<ProtoBuf.Annotation> list, NameResolver nameResolver, LanguageFeature languageFeature, AnnotationUseSiteTarget annotationUseSiteTarget) {
        firSession.getClass();
        list.getClass();
        nameResolver.getClass();
        languageFeature.getClass();
        if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).supportsFeature(languageFeature)) {
            return loadAnnotationsFromMetadataIfNotEmpty(firSession, list, nameResolver, annotationUseSiteTarget);
        }
        return null;
    }

    public static /* synthetic */ List loadAnnotationsFromMetadataGuarded$default(FirSession firSession, List list, NameResolver nameResolver, LanguageFeature languageFeature, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if ((i & 16) != 0) {
            annotationUseSiteTarget = null;
        }
        return loadAnnotationsFromMetadataGuarded(firSession, list, nameResolver, languageFeature, annotationUseSiteTarget);
    }

    public static final List<FirAnnotation> loadAnnotationsFromMetadataIfNotEmpty(FirSession firSession, List<ProtoBuf.Annotation> list, NameResolver nameResolver, AnnotationUseSiteTarget annotationUseSiteTarget) {
        firSession.getClass();
        list.getClass();
        nameResolver.getClass();
        if (list.isEmpty()) {
            return null;
        }
        return loadAnnotationsFromMetadata(firSession, list, nameResolver, annotationUseSiteTarget);
    }

    public static /* synthetic */ List loadAnnotationsFromMetadataIfNotEmpty$default(FirSession firSession, List list, NameResolver nameResolver, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if ((i & 8) != 0) {
            annotationUseSiteTarget = null;
        }
        return loadAnnotationsFromMetadataIfNotEmpty(firSession, list, nameResolver, annotationUseSiteTarget);
    }

    public static final <T extends GeneratedMessageLite.ExtendableMessage<T>> List<FirAnnotation> loadAnnotationsFromProtocol(T t, FirSession firSession, GeneratedMessageLite.GeneratedExtension<T, List<ProtoBuf.Annotation>> generatedExtension, NameResolver nameResolver, AnnotationUseSiteTarget annotationUseSiteTarget) {
        t.getClass();
        firSession.getClass();
        nameResolver.getClass();
        if (generatedExtension == null) {
            return CollectionsKt.emptyList();
        }
        List list = (List) t.getExtension(generatedExtension);
        list.getClass();
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(deserializeAnnotation(firSession, (ProtoBuf.Annotation) it.next(), nameResolver, annotationUseSiteTarget));
        }
        return arrayList;
    }

    public static /* synthetic */ List loadAnnotationsFromProtocol$default(GeneratedMessageLite.ExtendableMessage extendableMessage, FirSession firSession, GeneratedMessageLite.GeneratedExtension generatedExtension, NameResolver nameResolver, AnnotationUseSiteTarget annotationUseSiteTarget, int i, Object obj) {
        if ((i & 8) != 0) {
            annotationUseSiteTarget = null;
        }
        return loadAnnotationsFromProtocol(extendableMessage, firSession, generatedExtension, nameResolver, annotationUseSiteTarget);
    }

    public static final FirExpression toFirExpression(ProtoBuf.Annotation.Argument.Value value, FirSession firSession, NameResolver nameResolver) {
        value.getClass();
        firSession.getClass();
        nameResolver.getClass();
        Boolean bool = Flags.IS_UNSIGNED.get(value.getFlags());
        bool.getClass();
        boolean zBooleanValue = bool.booleanValue();
        ProtoBuf.Annotation.Argument.Value.Type type = value.getType();
        switch (type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                return m371const(zBooleanValue ? ConstantValueKind.UnsignedByte.INSTANCE : ConstantValueKind.Byte.INSTANCE, Byte.valueOf((byte) value.getIntValue()), firSession.getBuiltinTypes().getByteType());
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return m371const(zBooleanValue ? ConstantValueKind.UnsignedShort.INSTANCE : ConstantValueKind.Short.INSTANCE, Short.valueOf((short) value.getIntValue()), firSession.getBuiltinTypes().getShortType());
            case 3:
                return m371const(zBooleanValue ? ConstantValueKind.UnsignedInt.INSTANCE : ConstantValueKind.Int.INSTANCE, Integer.valueOf((int) value.getIntValue()), firSession.getBuiltinTypes().getIntType());
            case 4:
                return m371const(zBooleanValue ? ConstantValueKind.UnsignedLong.INSTANCE : ConstantValueKind.Long.INSTANCE, Long.valueOf(value.getIntValue()), firSession.getBuiltinTypes().getLongType());
            case 5:
                return m371const(ConstantValueKind.Char.INSTANCE, Character.valueOf((char) value.getIntValue()), firSession.getBuiltinTypes().getCharType());
            case 6:
                return m371const(ConstantValueKind.Float.INSTANCE, Float.valueOf(value.getFloatValue()), firSession.getBuiltinTypes().getFloatType());
            case 7:
                return m371const(ConstantValueKind.Double.INSTANCE, Double.valueOf(value.getDoubleValue()), firSession.getBuiltinTypes().getDoubleType());
            case 8:
                return m371const(ConstantValueKind.Boolean.INSTANCE, Boolean.valueOf(value.getIntValue() != 0), firSession.getBuiltinTypes().getBooleanType());
            case 9:
                return m371const(ConstantValueKind.String.INSTANCE, nameResolver.getString(value.getStringValue()), firSession.getBuiltinTypes().getStringType());
            case 10:
                ProtoBuf.Annotation annotation = value.getAnnotation();
                annotation.getClass();
                return deserializeAnnotation$default(firSession, annotation, nameResolver, null, 8, null);
            case 11:
                FirGetClassCallBuilder firGetClassCallBuilder = new FirGetClassCallBuilder();
                ConeLookupTagBasedType coneLookupTagBasedTypeConstructType$default = TypeConstructionUtilsKt.constructType$default((ConeClassifierLookupTag) TypeConstructionUtilsKt.toLookupTag(NameResolverUtilKt.getClassId(nameResolver, value.getClassId())), (ConeTypeProjection[]) null, false, (ConeAttributes) null, 7, (Object) null);
                ConeClassLikeType coneClassLikeTypeConstructClassLikeType$default = TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getKClass(), new ConeLookupTagBasedType[]{coneLookupTagBasedTypeConstructType$default}, false, null, 4, null);
                FirClassReferenceExpressionBuilder firClassReferenceExpressionBuilder = new FirClassReferenceExpressionBuilder();
                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                firResolvedTypeRefBuilder.setConeType(coneLookupTagBasedTypeConstructType$default);
                firClassReferenceExpressionBuilder.setClassTypeRef(firResolvedTypeRefBuilder.build());
                firClassReferenceExpressionBuilder.setConeTypeOrNull(coneClassLikeTypeConstructClassLikeType$default);
                firGetClassCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firClassReferenceExpressionBuilder.mo289build()));
                firGetClassCallBuilder.setConeTypeOrNull(coneClassLikeTypeConstructClassLikeType$default);
                return firGetClassCallBuilder.mo289build();
            case 12:
                FirEnumEntryDeserializedAccessExpressionBuilder firEnumEntryDeserializedAccessExpressionBuilder = new FirEnumEntryDeserializedAccessExpressionBuilder();
                firEnumEntryDeserializedAccessExpressionBuilder.setEnumClassId(NameResolverUtilKt.getClassId(nameResolver, value.getClassId()));
                firEnumEntryDeserializedAccessExpressionBuilder.setEnumEntryName(NameResolverUtilKt.getName(nameResolver, value.getEnumValueId()));
                return firEnumEntryDeserializedAccessExpressionBuilder.mo289build();
            case 13:
                FirCollectionLiteralBuilder firCollectionLiteralBuilder = new FirCollectionLiteralBuilder();
                firCollectionLiteralBuilder.setConeTypeOrNull(ArrayUtilsKt.createOutArrayType$default(StandardTypes.INSTANCE.getAny(), false, false, 3, null));
                FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
                List<ProtoBuf.Annotation.Argument.Value> arrayElementList = value.getArrayElementList();
                arrayElementList.getClass();
                List<FirExpression> arguments = firArgumentListBuilder.getArguments();
                for (ProtoBuf.Annotation.Argument.Value value2 : arrayElementList) {
                    value2.getClass();
                    arguments.add(toFirExpression(value2, firSession, nameResolver));
                }
                firCollectionLiteralBuilder.setArgumentList(firArgumentListBuilder.build());
                return firCollectionLiteralBuilder.mo289build();
            default:
                f2f.a("Unsupported annotation argument type: ", value.getType());
                return null;
        }
    }
}

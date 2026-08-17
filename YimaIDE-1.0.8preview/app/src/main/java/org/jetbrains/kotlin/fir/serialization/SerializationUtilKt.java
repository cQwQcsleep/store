package org.jetbrains.kotlin.fir.serialization;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001a\u0010\u0007\u001a\u00020\b*\u00020\t2\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u001a\u0012\u0010\r\u001a\u00020\b*\u00020\t2\u0006\u0010\u000e\u001a\u00020\b\u001a\u009e\u0001\u0010\u000f\u001a\u00020\u0010\"\u000e\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u0012\"\u0014\b\u0001\u0010\u0013*\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00130\u0014*\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00130\u00142\u001a\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d\u0018\u00010\u001c2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u0010\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"\u001a\"\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001d*\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u001a\u009a\u0001\u0010\u000f\u001a\u00020\u0010\"\u000e\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u0012\"\u0014\b\u0001\u0010\u0013*\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00130\u0014*\b\u0012\u0004\u0012\u00020$0\u001d2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00130\u00142\u001a\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d\u0018\u00010\u001c2\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u0010\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"\u001a,\u0010%\u001a\u00020\u0010*\b\u0012\u0004\u0012\u00020$0\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00100 \u001an\u0010&\u001a\u00020\u0010\"\u000e\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\u0012\"\u0014\b\u0001\u0010\u0013*\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00130\u0014*\b\u0012\u0004\u0012\u00020$0\u001d2\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00130\u00142\u001a\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d\u0018\u00010\u001c¨\u0006'"}, d2 = {"suspendFunctionTypeToFunctionTypeWithContinuation", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "continuationClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "isNotExpectOrShouldBeSerialized", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "actualizedExpectDeclaration", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "isNotPrivateOrShouldBeSerialized", "produceHeaderKlib", "serializeAnnotations", Argument.Delimiters.none, "MessageType", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;", "BuilderType", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableBuilder;", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "additionalMetadataProvider", "Lorg/jetbrains/kotlin/fir/serialization/FirAdditionalMetadataProvider;", "annotationSerializer", "Lorg/jetbrains/kotlin/fir/serialization/FirAnnotationSerializer;", "proto", "extension", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;", "addAnnotation", "Lkotlin/Function1;", "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "allRequiredAnnotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "serializeAnnotationsToMetadata", "serializeAnnotationsToExtension", "org.jetbrains.kotlin:fir-serialization"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SerializationUtilKt {
    public static final List<FirAnnotation> allRequiredAnnotations(FirAnnotationContainer firAnnotationContainer, FirSession firSession, FirAdditionalMetadataProvider firAdditionalMetadataProvider) {
        firAnnotationContainer.getClass();
        firSession.getClass();
        List<FirAnnotation> listNonSourceAnnotations = FirAnnotationUtilsKt.nonSourceAnnotations(firAnnotationContainer, firSession);
        return (!(firAnnotationContainer instanceof FirDeclaration) || firAdditionalMetadataProvider == null) ? listNonSourceAnnotations : CollectionsKt.plus(listNonSourceAnnotations, firAdditionalMetadataProvider.findGeneratedAnnotationsFor((FirDeclaration) firAnnotationContainer));
    }

    public static final boolean isNotExpectOrShouldBeSerialized(FirMemberDeclaration firMemberDeclaration, Set<? extends FirDeclaration> set) {
        firMemberDeclaration.getClass();
        return (firMemberDeclaration.getStatus().isExpect() && set != null && set.contains(firMemberDeclaration)) ? false : true;
    }

    public static final boolean isNotPrivateOrShouldBeSerialized(FirMemberDeclaration firMemberDeclaration, boolean z) {
        firMemberDeclaration.getClass();
        if (!z || firMemberDeclaration.getStatus().getVisibility().getIsPublicAPI() || Intrinsics.areEqual(firMemberDeclaration.getStatus().getVisibility(), Visibilities.Internal.INSTANCE)) {
            return true;
        }
        FirClass firClass = firMemberDeclaration instanceof FirClass ? (FirClass) firMemberDeclaration : null;
        return (firClass != null && firClass.getClassKind() == ClassKind.INTERFACE) || (firMemberDeclaration instanceof FirTypeAlias);
    }

    public static final <MessageType extends GeneratedMessageLite.ExtendableMessage<MessageType>, BuilderType extends GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType>> void serializeAnnotations(List<? extends FirAnnotation> list, FirSession firSession, FirAnnotationSerializer firAnnotationSerializer, GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType> extendableBuilder, GeneratedMessageLite.GeneratedExtension<MessageType, List<ProtoBuf.Annotation>> generatedExtension, Function1<? super ProtoBuf.Annotation, Unit> function1, LanguageFeature languageFeature) {
        list.getClass();
        firSession.getClass();
        firAnnotationSerializer.getClass();
        extendableBuilder.getClass();
        if (function1 == null || languageFeature == null || !FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).supportsFeature(languageFeature)) {
            serializeAnnotationsToExtension(list, firAnnotationSerializer, extendableBuilder, generatedExtension);
        } else {
            serializeAnnotationsToMetadata(list, firAnnotationSerializer, function1);
        }
    }

    public static /* synthetic */ void serializeAnnotations$default(FirAnnotationContainer firAnnotationContainer, FirSession firSession, FirAdditionalMetadataProvider firAdditionalMetadataProvider, FirAnnotationSerializer firAnnotationSerializer, GeneratedMessageLite.ExtendableBuilder extendableBuilder, GeneratedMessageLite.GeneratedExtension generatedExtension, Function1 function1, LanguageFeature languageFeature, int i, Object obj) {
        if ((i & 32) != 0) {
            function1 = null;
        }
        if ((i & 64) != 0) {
            languageFeature = null;
        }
        serializeAnnotations(firAnnotationContainer, firSession, firAdditionalMetadataProvider, firAnnotationSerializer, extendableBuilder, generatedExtension, function1, languageFeature);
    }

    public static final <MessageType extends GeneratedMessageLite.ExtendableMessage<MessageType>, BuilderType extends GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType>> void serializeAnnotationsToExtension(List<? extends FirAnnotation> list, FirAnnotationSerializer firAnnotationSerializer, GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType> extendableBuilder, GeneratedMessageLite.GeneratedExtension<MessageType, List<ProtoBuf.Annotation>> generatedExtension) {
        list.getClass();
        firAnnotationSerializer.getClass();
        extendableBuilder.getClass();
        if (generatedExtension == null) {
            return;
        }
        Iterator<? extends FirAnnotation> it = list.iterator();
        while (it.hasNext()) {
            ProtoBuf.Annotation annotationSerializeAnnotation = firAnnotationSerializer.serializeAnnotation(it.next());
            if (annotationSerializeAnnotation != null) {
                extendableBuilder.addExtension(generatedExtension, annotationSerializeAnnotation);
            }
        }
    }

    public static final void serializeAnnotationsToMetadata(List<? extends FirAnnotation> list, FirAnnotationSerializer firAnnotationSerializer, Function1<? super ProtoBuf.Annotation, Unit> function1) {
        list.getClass();
        firAnnotationSerializer.getClass();
        function1.getClass();
        Iterator<? extends FirAnnotation> it = list.iterator();
        while (it.hasNext()) {
            ProtoBuf.Annotation annotationSerializeAnnotation = firAnnotationSerializer.serializeAnnotation(it.next());
            if (annotationSerializeAnnotation != null) {
                function1.invoke(annotationSerializeAnnotation);
            }
        }
    }

    public static final ConeClassLikeType suspendFunctionTypeToFunctionTypeWithContinuation(ConeKotlinType coneKotlinType, FirSession firSession, ClassId classId) {
        coneKotlinType.getClass();
        firSession.getClass();
        classId.getClass();
        if (!FunctionalTypeUtilsKt.isSuspendOrKSuspendFunctionType(coneKotlinType, firSession)) {
            w01.a("Failed requirement.");
            return null;
        }
        FunctionTypeKind.KFunction kFunction = FunctionalTypeUtilsKt.isReflectFunctionType(coneKotlinType, firSession) ? FunctionTypeKind.KFunction.INSTANCE : FunctionTypeKind.Function.INSTANCE;
        ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null);
        ConeTypeProjection[] typeArguments = coneKotlinTypeFullyExpandedType$default.getTypeArguments();
        return new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(new ClassId(kFunction.getPackageFqName(), kFunction.numberedClassName(typeArguments.length))), (ConeTypeProjection[]) CollectionsKt.plus(CollectionsKt.plus(ArraysKt.dropLast(typeArguments, 1), TypeConstructionUtilsKt.constructClassType$default(TypeConstructionUtilsKt.toLookupTag(classId), new ConeTypeProjection[]{(ConeTypeProjection) ArraysKt.last(typeArguments)}, false, null, 6, null)), firSession.getBuiltinTypes().getNullableAnyType().getConeType()).toArray(new ConeTypeProjection[0]), ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeFullyExpandedType$default), coneKotlinTypeFullyExpandedType$default.getAttributes());
    }

    public static /* synthetic */ void serializeAnnotations$default(List list, FirSession firSession, FirAnnotationSerializer firAnnotationSerializer, GeneratedMessageLite.ExtendableBuilder extendableBuilder, GeneratedMessageLite.GeneratedExtension generatedExtension, Function1 function1, LanguageFeature languageFeature, int i, Object obj) {
        if ((i & 16) != 0) {
            function1 = null;
        }
        if ((i & 32) != 0) {
            languageFeature = null;
        }
        serializeAnnotations(list, firSession, firAnnotationSerializer, extendableBuilder, generatedExtension, function1, languageFeature);
    }

    public static final <MessageType extends GeneratedMessageLite.ExtendableMessage<MessageType>, BuilderType extends GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType>> void serializeAnnotations(FirAnnotationContainer firAnnotationContainer, FirSession firSession, FirAdditionalMetadataProvider firAdditionalMetadataProvider, FirAnnotationSerializer firAnnotationSerializer, GeneratedMessageLite.ExtendableBuilder<MessageType, BuilderType> extendableBuilder, GeneratedMessageLite.GeneratedExtension<MessageType, List<ProtoBuf.Annotation>> generatedExtension, Function1<? super ProtoBuf.Annotation, Unit> function1, LanguageFeature languageFeature) {
        firAnnotationContainer.getClass();
        firSession.getClass();
        firAnnotationSerializer.getClass();
        extendableBuilder.getClass();
        serializeAnnotations(allRequiredAnnotations(firAnnotationContainer, firSession, firAdditionalMetadataProvider), firSession, firAnnotationSerializer, extendableBuilder, generatedExtension, function1, languageFeature);
    }
}

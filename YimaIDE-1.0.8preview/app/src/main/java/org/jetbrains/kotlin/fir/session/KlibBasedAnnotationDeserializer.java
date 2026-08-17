package org.jetbrains.kotlin.fir.session;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializationUtilKt;
import org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer;
import org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.library.metadata.KlibMetadataSerializerProtocol;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.TypeTable;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.protobuf.MessageLite;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J0\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J:\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J0\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016JR\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J&\u0010$\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u000b\u001a\u00020\fH\u0016J8\u0010)\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010*\u001a\u00020#H\u0016J8\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010,\u001a\u00020#H\u0016J0\u0010-\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J0\u0010.\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J8\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!H\u0016J\u001e\u00100\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u00101\u001a\u0002022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001e\u00103\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u00104\u001a\u0002052\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/KlibBasedAnnotationDeserializer;", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializerWithProtocol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "loadClassAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "loadFunctionAnnotations", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "functionProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "typeTable", "Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "loadPropertyAnnotations", "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "containingClassProto", "loadConstructorAnnotations", "constructorProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor;", "loadValueParameterAnnotations", "callableProto", "Lorg/jetbrains/kotlin/protobuf/MessageLite;", "valueParameterProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter;", "kind", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer$CallableKind;", "parameterIndex", Argument.Delimiters.none, "loadEnumEntryAnnotations", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "enumEntryProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$EnumEntry;", "loadPropertyGetterAnnotations", "getterFlags", "loadPropertySetterAnnotations", "setterFlags", "loadPropertyBackingFieldAnnotations", "loadPropertyDelegatedFieldAnnotations", "loadExtensionReceiverParameterAnnotations", "loadTypeAnnotations", "typeProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "loadTypeParameterAnnotations", "typeParameterProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KlibBasedAnnotationDeserializer extends AnnotationDeserializerWithProtocol {
    private final FirSession session;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KlibBasedAnnotationDeserializer(FirSession firSession) {
        super(firSession, KlibMetadataSerializerProtocol.INSTANCE);
        firSession.getClass();
        this.session = firSession;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadClassAnnotations(ProtoBuf.Class classProto, NameResolver nameResolver) {
        classProto.getClass();
        nameResolver.getClass();
        FirSession firSession = this.session;
        List annotationList = classProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty$default(firSession, annotationList, nameResolver, null, 8, null);
        return listLoadAnnotationsFromMetadataIfNotEmpty$default == null ? super.loadClassAnnotations(classProto, nameResolver) : listLoadAnnotationsFromMetadataIfNotEmpty$default;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadConstructorAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Constructor constructorProto, NameResolver nameResolver, TypeTable typeTable) {
        constructorProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List annotationList = constructorProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty$default(firSession, annotationList, nameResolver, null, 8, null);
        return listLoadAnnotationsFromMetadataIfNotEmpty$default == null ? super.loadConstructorAnnotations(containerSource, constructorProto, nameResolver, typeTable) : listLoadAnnotationsFromMetadataIfNotEmpty$default;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadEnumEntryAnnotations(ClassId classId, ProtoBuf.EnumEntry enumEntryProto, NameResolver nameResolver) {
        classId.getClass();
        enumEntryProto.getClass();
        nameResolver.getClass();
        FirSession firSession = this.session;
        List annotationList = enumEntryProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty$default(firSession, annotationList, nameResolver, null, 8, null);
        return listLoadAnnotationsFromMetadataIfNotEmpty$default == null ? super.loadEnumEntryAnnotations(classId, enumEntryProto, nameResolver) : listLoadAnnotationsFromMetadataIfNotEmpty$default;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadExtensionReceiverParameterAnnotations(DeserializedContainerSource containerSource, MessageLite callableProto, NameResolver nameResolver, TypeTable typeTable, AnnotationDeserializer.CallableKind kind) {
        NameResolver nameResolver2;
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty$default;
        callableProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        kind.getClass();
        if (callableProto instanceof ProtoBuf.Function) {
            FirSession firSession = this.session;
            List extensionReceiverAnnotationList = ((ProtoBuf.Function) callableProto).getExtensionReceiverAnnotationList();
            extensionReceiverAnnotationList.getClass();
            nameResolver2 = nameResolver;
            listLoadAnnotationsFromMetadataIfNotEmpty$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty$default(firSession, extensionReceiverAnnotationList, nameResolver2, null, 8, null);
        } else {
            nameResolver2 = nameResolver;
            if (callableProto instanceof ProtoBuf.Property) {
                FirSession firSession2 = this.session;
                List extensionReceiverAnnotationList2 = ((ProtoBuf.Property) callableProto).getExtensionReceiverAnnotationList();
                extensionReceiverAnnotationList2.getClass();
                listLoadAnnotationsFromMetadataIfNotEmpty$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty$default(firSession2, extensionReceiverAnnotationList2, nameResolver2, null, 8, null);
            } else {
                listLoadAnnotationsFromMetadataIfNotEmpty$default = null;
            }
        }
        if (listLoadAnnotationsFromMetadataIfNotEmpty$default == null) {
            nameResolver2 = nameResolver2;
            return super.loadExtensionReceiverParameterAnnotations(containerSource, callableProto, nameResolver2, typeTable, kind);
        }
        nameResolver2 = nameResolver2;
        return listLoadAnnotationsFromMetadataIfNotEmpty$default;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadFunctionAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Function functionProto, NameResolver nameResolver, TypeTable typeTable) {
        functionProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List annotationList = functionProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty$default(firSession, annotationList, nameResolver, null, 8, null);
        return listLoadAnnotationsFromMetadataIfNotEmpty$default == null ? super.loadFunctionAnnotations(containerSource, functionProto, nameResolver, typeTable) : listLoadAnnotationsFromMetadataIfNotEmpty$default;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, ProtoBuf.Class containingClassProto, NameResolver nameResolver, TypeTable typeTable) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List annotationList = propertyProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty(firSession, annotationList, nameResolver, AnnotationUseSiteTarget.PROPERTY);
        return listLoadAnnotationsFromMetadataIfNotEmpty == null ? super.loadPropertyAnnotations(containerSource, propertyProto, containingClassProto, nameResolver, typeTable) : listLoadAnnotationsFromMetadataIfNotEmpty;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyBackingFieldAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List backingFieldAnnotationList = propertyProto.getBackingFieldAnnotationList();
        backingFieldAnnotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty(firSession, backingFieldAnnotationList, nameResolver, AnnotationUseSiteTarget.FIELD);
        return listLoadAnnotationsFromMetadataIfNotEmpty == null ? super.loadPropertyBackingFieldAnnotations(containerSource, propertyProto, nameResolver, typeTable) : listLoadAnnotationsFromMetadataIfNotEmpty;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyDelegatedFieldAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List delegateFieldAnnotationList = propertyProto.getDelegateFieldAnnotationList();
        delegateFieldAnnotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty(firSession, delegateFieldAnnotationList, nameResolver, AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD);
        return listLoadAnnotationsFromMetadataIfNotEmpty == null ? super.loadPropertyDelegatedFieldAnnotations(containerSource, propertyProto, nameResolver, typeTable) : listLoadAnnotationsFromMetadataIfNotEmpty;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyGetterAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable, int getterFlags) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List getterAnnotationList = propertyProto.getGetterAnnotationList();
        getterAnnotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty(firSession, getterAnnotationList, nameResolver, AnnotationUseSiteTarget.PROPERTY_GETTER);
        return listLoadAnnotationsFromMetadataIfNotEmpty == null ? super.loadPropertyGetterAnnotations(containerSource, propertyProto, nameResolver, typeTable, getterFlags) : listLoadAnnotationsFromMetadataIfNotEmpty;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertySetterAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable, int setterFlags) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        FirSession firSession = this.session;
        List setterAnnotationList = propertyProto.getSetterAnnotationList();
        setterAnnotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty(firSession, setterAnnotationList, nameResolver, AnnotationUseSiteTarget.PROPERTY_SETTER);
        return listLoadAnnotationsFromMetadataIfNotEmpty == null ? super.loadPropertySetterAnnotations(containerSource, propertyProto, nameResolver, typeTable, setterFlags) : listLoadAnnotationsFromMetadataIfNotEmpty;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadTypeAnnotations(ProtoBuf.Type typeProto, NameResolver nameResolver) {
        typeProto.getClass();
        nameResolver.getClass();
        FirSession firSession = this.session;
        List annotationList = typeProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty$default(firSession, annotationList, nameResolver, null, 8, null);
        return listLoadAnnotationsFromMetadataIfNotEmpty$default == null ? super.loadTypeAnnotations(typeProto, nameResolver) : listLoadAnnotationsFromMetadataIfNotEmpty$default;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadTypeParameterAnnotations(ProtoBuf.TypeParameter typeParameterProto, NameResolver nameResolver) {
        typeParameterProto.getClass();
        nameResolver.getClass();
        FirSession firSession = this.session;
        List annotationList = typeParameterProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty$default(firSession, annotationList, nameResolver, null, 8, null);
        return listLoadAnnotationsFromMetadataIfNotEmpty$default == null ? super.loadTypeParameterAnnotations(typeParameterProto, nameResolver) : listLoadAnnotationsFromMetadataIfNotEmpty$default;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializerWithProtocol, org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadValueParameterAnnotations(DeserializedContainerSource containerSource, MessageLite callableProto, ProtoBuf.ValueParameter valueParameterProto, ProtoBuf.Class classProto, NameResolver nameResolver, TypeTable typeTable, AnnotationDeserializer.CallableKind kind, int parameterIndex) {
        callableProto.getClass();
        valueParameterProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        kind.getClass();
        FirSession firSession = this.session;
        List annotationList = valueParameterProto.getAnnotationList();
        annotationList.getClass();
        List<FirAnnotation> listLoadAnnotationsFromMetadataIfNotEmpty$default = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadataIfNotEmpty$default(firSession, annotationList, nameResolver, null, 8, null);
        return listLoadAnnotationsFromMetadataIfNotEmpty$default == null ? super.loadValueParameterAnnotations(containerSource, callableProto, valueParameterProto, classProto, nameResolver, typeTable, kind, parameterIndex) : listLoadAnnotationsFromMetadataIfNotEmpty$default;
    }
}

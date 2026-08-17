package org.jetbrains.kotlin.fir.deserialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.TypeTable;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.protobuf.MessageLite;
import org.jetbrains.kotlin.serialization.SerializerExtensionProtocol;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0001H\u0016J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J0\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J:\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J0\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J0\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J8\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020&H\u0016J8\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020&H\u0016J0\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0016JR\u0010,\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020&H\u0016J&\u00104\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J8\u00109\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010-\u001a\u00020.2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u00101\u001a\u000202H\u0016J4\u0010:\u001a\u0004\u0018\u00010;2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010<\u001a\u00020=2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u001e\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010?\u001a\u00020@2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001e\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010B\u001a\u00020C2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006D"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializerWithProtocol;", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "protocol", "Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;)V", "getProtocol", "()Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "inheritAnnotationInfo", Argument.Delimiters.none, "parent", "loadClassAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "loadTypeAliasAnnotations", "aliasProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeAlias;", "loadFunctionAnnotations", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "functionProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "typeTable", "Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "loadPropertyAnnotations", "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "containingClassProto", "loadPropertyBackingFieldAnnotations", "loadPropertyDelegatedFieldAnnotations", "loadPropertyGetterAnnotations", "getterFlags", Argument.Delimiters.none, "loadPropertySetterAnnotations", "setterFlags", "loadConstructorAnnotations", "constructorProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor;", "loadValueParameterAnnotations", "callableProto", "Lorg/jetbrains/kotlin/protobuf/MessageLite;", "valueParameterProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter;", "kind", "Lorg/jetbrains/kotlin/fir/deserialization/AnnotationDeserializer$CallableKind;", "parameterIndex", "loadEnumEntryAnnotations", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "enumEntryProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$EnumEntry;", "loadExtensionReceiverParameterAnnotations", "loadAnnotationPropertyDefaultValue", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "expectedPropertyType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "loadTypeAnnotations", "typeProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "loadTypeParameterAnnotations", "typeParameterProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter;", "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AnnotationDeserializerWithProtocol extends AnnotationDeserializer {
    private final SerializerExtensionProtocol protocol;
    private final FirSession session;

    public AnnotationDeserializerWithProtocol(FirSession firSession, SerializerExtensionProtocol serializerExtensionProtocol) {
        firSession.getClass();
        serializerExtensionProtocol.getClass();
        this.session = firSession;
        this.protocol = serializerExtensionProtocol;
    }

    public final SerializerExtensionProtocol getProtocol() {
        return this.protocol;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public void inheritAnnotationInfo(AnnotationDeserializer parent) {
        parent.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public FirExpression loadAnnotationPropertyDefaultValue(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, FirTypeRef expectedPropertyType, NameResolver nameResolver, TypeTable typeTable) {
        propertyProto.getClass();
        expectedPropertyType.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadClassAnnotations(ProtoBuf.Class classProto, NameResolver nameResolver) {
        classProto.getClass();
        nameResolver.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol$default(classProto, this.session, this.protocol.getClassAnnotation(), nameResolver, null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadConstructorAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Constructor constructorProto, NameResolver nameResolver, TypeTable typeTable) {
        constructorProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol$default(constructorProto, this.session, this.protocol.getConstructorAnnotation(), nameResolver, null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadEnumEntryAnnotations(ClassId classId, ProtoBuf.EnumEntry enumEntryProto, NameResolver nameResolver) {
        classId.getClass();
        enumEntryProto.getClass();
        nameResolver.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol$default(enumEntryProto, this.session, this.protocol.getEnumEntryAnnotation(), nameResolver, null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadExtensionReceiverParameterAnnotations(DeserializedContainerSource containerSource, MessageLite callableProto, NameResolver nameResolver, TypeTable typeTable, AnnotationDeserializer.CallableKind kind) {
        callableProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        kind.getClass();
        if (callableProto instanceof ProtoBuf.Property) {
            return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol$default((GeneratedMessageLite.ExtendableMessage) callableProto, this.session, this.protocol.getPropertyExtensionReceiverAnnotation(), nameResolver, null, 8, null);
        }
        return callableProto instanceof ProtoBuf.Function ? AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol$default((GeneratedMessageLite.ExtendableMessage) callableProto, this.session, this.protocol.getFunctionExtensionReceiverAnnotation(), nameResolver, null, 8, null) : CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadFunctionAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Function functionProto, NameResolver nameResolver, TypeTable typeTable) {
        functionProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol$default(functionProto, this.session, this.protocol.getFunctionAnnotation(), nameResolver, null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, ProtoBuf.Class containingClassProto, NameResolver nameResolver, TypeTable typeTable) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol(propertyProto, this.session, this.protocol.getPropertyAnnotation(), nameResolver, AnnotationUseSiteTarget.PROPERTY);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyBackingFieldAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol(propertyProto, this.session, this.protocol.getPropertyBackingFieldAnnotation(), nameResolver, AnnotationUseSiteTarget.FIELD);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyDelegatedFieldAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol(propertyProto, this.session, this.protocol.getPropertyDelegatedFieldAnnotation(), nameResolver, AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertyGetterAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable, int getterFlags) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol(propertyProto, this.session, this.protocol.getPropertyGetterAnnotation(), nameResolver, AnnotationUseSiteTarget.PROPERTY_GETTER);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadPropertySetterAnnotations(DeserializedContainerSource containerSource, ProtoBuf.Property propertyProto, NameResolver nameResolver, TypeTable typeTable, int setterFlags) {
        propertyProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol(propertyProto, this.session, this.protocol.getPropertySetterAnnotation(), nameResolver, AnnotationUseSiteTarget.PROPERTY_SETTER);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadTypeAliasAnnotations(ProtoBuf.TypeAlias aliasProto, NameResolver nameResolver) {
        aliasProto.getClass();
        nameResolver.getClass();
        FirSession firSession = this.session;
        List annotationList = aliasProto.getAnnotationList();
        annotationList.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromMetadata$default(firSession, annotationList, nameResolver, null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadTypeAnnotations(ProtoBuf.Type typeProto, NameResolver nameResolver) {
        typeProto.getClass();
        nameResolver.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol$default(typeProto, this.session, this.protocol.getTypeAnnotation(), nameResolver, null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadTypeParameterAnnotations(ProtoBuf.TypeParameter typeParameterProto, NameResolver nameResolver) {
        typeParameterProto.getClass();
        nameResolver.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol$default(typeParameterProto, this.session, this.protocol.getTypeParameterAnnotation(), nameResolver, null, 8, null);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializer
    public List<FirAnnotation> loadValueParameterAnnotations(DeserializedContainerSource containerSource, MessageLite callableProto, ProtoBuf.ValueParameter valueParameterProto, ProtoBuf.Class classProto, NameResolver nameResolver, TypeTable typeTable, AnnotationDeserializer.CallableKind kind, int parameterIndex) {
        callableProto.getClass();
        valueParameterProto.getClass();
        nameResolver.getClass();
        typeTable.getClass();
        kind.getClass();
        return AnnotationDeserializationUtilKt.loadAnnotationsFromProtocol$default(valueParameterProto, this.session, this.protocol.getParameterAnnotation(), nameResolver, null, 8, null);
    }
}

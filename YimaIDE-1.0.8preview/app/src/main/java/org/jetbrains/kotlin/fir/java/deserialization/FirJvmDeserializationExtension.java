package org.jetbrains.kotlin.fir.java.deserialization;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.builtins.jvm.JvmBuiltInsSignatures;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.deserialization.FirConstDeserializer;
import org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinarySourceElement;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.ProtoBufUtilKt;
import org.jetbrains.kotlin.metadata.jvm.JvmProtoBuf;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.serialization.SerializerExtensionProtocol;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0014\u0010\f\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u00020\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0017\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016¢\u0006\u0002\u0010\u001cJ\u0012\u0010\u001d\u001a\u00020\u00192\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016R\u0014\u0010\u001e\u001a\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/FirJvmDeserializationExtension;", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationExtension;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "createConstDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "serializerExtensionProtocol", "Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "configureDeserializedClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "addSerializableIfNeeded", "loadModuleName", Argument.Delimiters.none, "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "loadHasBackingFieldFlag", Argument.Delimiters.none, "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;)Ljava/lang/Boolean;", "isMaybeMultiFieldValueClass", "isLoadingOfAnnotationsOnAnnotationPropertiesEnabled", "()Z", "Companion", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmDeserializationExtension extends FirDeserializationExtension {
    private static final ClassId JAVA_IO_SERIALIZABLE = ClassId.Companion.topLevel(new FqName("java.io.Serializable"));

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJvmDeserializationExtension(FirSession firSession) {
        super(firSession);
        firSession.getClass();
    }

    private final void addSerializableIfNeeded(FirRegularClassBuilder firRegularClassBuilder, ClassId classId) {
        if (!firRegularClassBuilder.getStatus().isExpect() && JvmBuiltInsSignatures.INSTANCE.isSerializableInJava(classId.asSingleFqName().toUnsafe())) {
            List<FirTypeRef> superTypeRefs = firRegularClassBuilder.getSuperTypeRefs();
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(TypeConstructionUtilsKt.toLookupTag(JAVA_IO_SERIALIZABLE), ConeTypeProjection.Companion.getEMPTY_ARRAY(), false, null, 8, null));
            superTypeRefs.add(firResolvedTypeRefBuilder.build());
        }
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
    public void configureDeserializedClass(FirRegularClassBuilder firRegularClassBuilder, ClassId classId) {
        firRegularClassBuilder.getClass();
        classId.getClass();
        addSerializableIfNeeded(firRegularClassBuilder, classId);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
    public FirConstDeserializer createConstDeserializer(DeserializedContainerSource containerSource, FirSession session, SerializerExtensionProtocol serializerExtensionProtocol) {
        session.getClass();
        serializerExtensionProtocol.getClass();
        if (containerSource instanceof KotlinJvmBinarySourceElement) {
            return new FirJvmConstDeserializer(((KotlinJvmBinarySourceElement) containerSource).getBinaryClass(), serializerExtensionProtocol);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
    public boolean isLoadingOfAnnotationsOnAnnotationPropertiesEnabled() {
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).supportsFeature(LanguageFeature.JvmLoadAnnotationsOnAnnotationProperties);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
    public boolean isMaybeMultiFieldValueClass(DeserializedContainerSource containerSource) {
        KotlinJvmBinaryClass binaryClass;
        KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement = containerSource instanceof KotlinJvmBinarySourceElement ? (KotlinJvmBinarySourceElement) containerSource : null;
        if (kotlinJvmBinarySourceElement == null || (binaryClass = kotlinJvmBinarySourceElement.getBinaryClass()) == null) {
            return false;
        }
        return binaryClass.getClassHeader().getMetadataVersion().isAtLeast(1, 5, 1);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
    public Boolean loadHasBackingFieldFlag(ProtoBuf.Property propertyProto) {
        propertyProto.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.propertySignature;
        generatedExtension.getClass();
        JvmProtoBuf.JvmPropertySignature jvmPropertySignature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull(propertyProto, generatedExtension);
        if (jvmPropertySignature != null) {
            return Boolean.valueOf(jvmPropertySignature.hasField());
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirDeserializationExtension
    public String loadModuleName(ProtoBuf.Class classProto, NameResolver nameResolver) {
        classProto.getClass();
        nameResolver.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = JvmProtoBuf.classModuleName;
        generatedExtension.getClass();
        Integer num = (Integer) ProtoBufUtilKt.getExtensionOrNull(classProto, generatedExtension);
        if (num != null) {
            return nameResolver.getString(num.intValue());
        }
        return null;
    }
}

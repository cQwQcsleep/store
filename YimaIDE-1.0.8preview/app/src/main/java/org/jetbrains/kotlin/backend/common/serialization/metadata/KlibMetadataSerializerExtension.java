package org.jetbrains.kotlin.backend.common.serialization.metadata;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithSource;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf;
import org.jetbrains.kotlin.library.metadata.KlibMetadataSerializerProtocol;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.BinaryVersion;
import org.jetbrains.kotlin.metadata.serialization.MutableVersionRequirementTable;
import org.jetbrains.kotlin.serialization.DescriptorSerializer;
import org.jetbrains.kotlin.serialization.KotlinSerializerExtensionBase;
import org.jetbrains.kotlin.serialization.SerializerExtension;
import org.jetbrains.kotlin.serialization.StringTableImpl;
import org.jetbrains.kotlin.types.FlexibleType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0011\u001a\u00020\tH\u0016J\u0017\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002¢\u0006\u0002\u0010\u001aJ \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 H\u0016J(\u0010\"\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H\u0016J \u0010*\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020+2\u0006\u0010$\u001a\u00020,2\u0006\u0010(\u001a\u00020)H\u0016J*\u0010-\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020.2\u0006\u0010$\u001a\u00020/2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020)H\u0016J*\u00100\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u0002012\u0006\u0010$\u001a\u0002022\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020)H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/metadata/KlibMetadataSerializerExtension;", "Lorg/jetbrains/kotlin/serialization/KotlinSerializerExtensionBase;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "stringTable", "Lorg/jetbrains/kotlin/serialization/StringTableImpl;", "exportKDoc", Argument.Delimiters.none, "produceHeaderKlib", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageVersionSettings;Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;Lorg/jetbrains/kotlin/serialization/StringTableImpl;ZZ)V", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/BinaryVersion;", "getStringTable", "()Lorg/jetbrains/kotlin/serialization/StringTableImpl;", "shouldUseTypeTable", "customClassMembersProducer", "Lorg/jetbrains/kotlin/serialization/SerializerExtension$ClassMembersProducer;", "getCustomClassMembersProducer", "()Lorg/jetbrains/kotlin/serialization/SerializerExtension$ClassMembersProducer;", "descriptorFileId", Argument.Delimiters.none, "descriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorWithSource;", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorWithSource;)Ljava/lang/Integer;", "serializeFlexibleType", Argument.Delimiters.none, "flexibleType", "Lorg/jetbrains/kotlin/types/FlexibleType;", "lowerProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type$Builder;", "upperProto", "serializeClass", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class$Builder;", "versionRequirementTable", "Lorg/jetbrains/kotlin/metadata/serialization/MutableVersionRequirementTable;", "childSerializer", "Lorg/jetbrains/kotlin/serialization/DescriptorSerializer;", "serializeConstructor", "Lorg/jetbrains/kotlin/descriptors/ConstructorDescriptor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor$Builder;", "serializeProperty", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", "serializeFunction", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function$Builder;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KlibMetadataSerializerExtension extends KotlinSerializerExtensionBase {
    private final boolean exportKDoc;
    private final LanguageVersionSettings languageVersionSettings;
    private final BinaryVersion metadataVersion;
    private final boolean produceHeaderKlib;
    private final StringTableImpl stringTable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KlibMetadataSerializerExtension(LanguageVersionSettings languageVersionSettings, BinaryVersion binaryVersion, StringTableImpl stringTableImpl, boolean z, boolean z2) {
        super(KlibMetadataSerializerProtocol.INSTANCE);
        languageVersionSettings.getClass();
        binaryVersion.getClass();
        stringTableImpl.getClass();
        this.languageVersionSettings = languageVersionSettings;
        this.metadataVersion = binaryVersion;
        this.stringTable = stringTableImpl;
        this.exportKDoc = z;
        this.produceHeaderKlib = z2;
    }

    private final Integer descriptorFileId(DeclarationDescriptorWithSource descriptor) {
        String name = descriptor.getSource().getContainingFile().getName();
        if (name == null) {
            return null;
        }
        return Integer.valueOf(getStringTable().getStringIndex(name));
    }

    public SerializerExtension.ClassMembersProducer getCustomClassMembersProducer() {
        return this.produceHeaderKlib ? new customClassMembersProducer.1() : super/*org.jetbrains.kotlin.serialization.SerializerExtension*/.getCustomClassMembersProducer();
    }

    public BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public void serializeClass(ClassDescriptor descriptor, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, DescriptorSerializer childSerializer) {
        String strFindKDocString;
        descriptor.getClass();
        proto.getClass();
        versionRequirementTable.getClass();
        childSerializer.getClass();
        Integer numDescriptorFileId = descriptorFileId(descriptor);
        if (numDescriptorFileId != null) {
            proto.setExtension(KlibMetadataProtoBuf.classFile, Integer.valueOf(numDescriptorFileId.intValue()));
        }
        if (this.exportKDoc && (strFindKDocString = KlibMetadataSerializerExtensionKt.findKDocString(descriptor)) != null) {
            proto.setExtension(KlibMetadataProtoBuf.classKdoc, strFindKDocString);
        }
        super.serializeClass(descriptor, proto, versionRequirementTable, childSerializer);
        ProtoBuf.TypeTable typeTableSerialize = childSerializer.getTypeTable().serialize();
        if (typeTableSerialize != null) {
            proto.mergeTypeTable(typeTableSerialize);
        }
    }

    public void serializeConstructor(ConstructorDescriptor descriptor, ProtoBuf.Constructor.Builder proto, DescriptorSerializer childSerializer) {
        String strFindKDocString;
        descriptor.getClass();
        proto.getClass();
        childSerializer.getClass();
        if (this.exportKDoc && (strFindKDocString = KlibMetadataSerializerExtensionKt.findKDocString(descriptor)) != null) {
            proto.setExtension(KlibMetadataProtoBuf.constructorKdoc, strFindKDocString);
        }
        super.serializeConstructor(descriptor, proto, childSerializer);
    }

    public void serializeFlexibleType(FlexibleType flexibleType, ProtoBuf.Type.Builder lowerProto, ProtoBuf.Type.Builder upperProto) {
        flexibleType.getClass();
        lowerProto.getClass();
        upperProto.getClass();
        lowerProto.setFlexibleTypeCapabilitiesId(getStringTable().getStringIndex("kotlin.DynamicType"));
    }

    public void serializeFunction(FunctionDescriptor descriptor, ProtoBuf.Function.Builder proto, MutableVersionRequirementTable versionRequirementTable, DescriptorSerializer childSerializer) {
        String strFindKDocString;
        descriptor.getClass();
        proto.getClass();
        childSerializer.getClass();
        Integer numDescriptorFileId = descriptorFileId(descriptor);
        if (numDescriptorFileId != null) {
            proto.setExtension(KlibMetadataProtoBuf.functionFile, Integer.valueOf(numDescriptorFileId.intValue()));
        }
        if (this.exportKDoc && (strFindKDocString = KlibMetadataSerializerExtensionKt.findKDocString(descriptor)) != null) {
            proto.setExtension(KlibMetadataProtoBuf.functionKdoc, strFindKDocString);
        }
        super.serializeFunction(descriptor, proto, versionRequirementTable, childSerializer);
    }

    public void serializeProperty(PropertyDescriptor descriptor, ProtoBuf.Property.Builder proto, MutableVersionRequirementTable versionRequirementTable, DescriptorSerializer childSerializer) {
        String strFindKDocString;
        descriptor.getClass();
        proto.getClass();
        childSerializer.getClass();
        Integer numDescriptorFileId = descriptorFileId(descriptor);
        if (numDescriptorFileId != null) {
            proto.setExtension(KlibMetadataProtoBuf.propertyFile, Integer.valueOf(numDescriptorFileId.intValue()));
        }
        if (this.exportKDoc && (strFindKDocString = KlibMetadataSerializerExtensionKt.findKDocString(descriptor)) != null) {
            proto.setExtension(KlibMetadataProtoBuf.propertyKdoc, strFindKDocString);
        }
        super.serializeProperty(descriptor, proto, versionRequirementTable, childSerializer);
    }

    public boolean shouldUseTypeTable() {
        return true;
    }

    public StringTableImpl getStringTable() {
        return this.stringTable;
    }
}

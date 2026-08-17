package org.jetbrains.kotlin.backend.common.serialization;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorUtilKt;
import org.jetbrains.kotlin.descriptors.MemberDescriptor;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.SourceFile;
import org.jetbrains.kotlin.library.metadata.DeserializedSourceFile;
import org.jetbrains.kotlin.library.metadata.KlibMetadataDeserializedPackageFragment;
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf;
import org.jetbrains.kotlin.library.metadata.KlibModuleOriginKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.resolve.multiplatform.OptionalAnnotationUtil;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedClassConstructorDescriptor;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedSimpleFunctionDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0007\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\n\u0010\r\u001a\u00020\b*\u00020\n\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003¨\u0006\u0010"}, d2 = {"isExpectMember", "", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;)Z", "isSerializableExpectClass", "findPackage", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentDescriptor;", "sourceByIndex", "Lorg/jetbrains/kotlin/descriptors/SourceFile;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "index", "", "findSourceFile", "extractSerializedKdocString", "", "org.jetbrains.kotlin:ir.serialization.common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class LegacyDescriptorUtilsKt {
    public static final String extractSerializedKdocString(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        if (declarationDescriptor instanceof DeserializedClassDescriptor) {
            return (String) ((DeserializedClassDescriptor) declarationDescriptor).getClassProto().getExtension(KlibMetadataProtoBuf.classKdoc);
        }
        if (declarationDescriptor instanceof DeserializedSimpleFunctionDescriptor) {
            return (String) ((DeserializedSimpleFunctionDescriptor) declarationDescriptor).getProto().getExtension(KlibMetadataProtoBuf.functionKdoc);
        }
        if (declarationDescriptor instanceof DeserializedPropertyDescriptor) {
            return (String) ((DeserializedPropertyDescriptor) declarationDescriptor).getProto().getExtension(KlibMetadataProtoBuf.propertyKdoc);
        }
        if (declarationDescriptor instanceof DeserializedClassConstructorDescriptor) {
            return (String) ((DeserializedClassConstructorDescriptor) declarationDescriptor).getProto().getExtension(KlibMetadataProtoBuf.constructorKdoc);
        }
        return null;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Moved to the ':core:descriptors' module")
    public static final /* synthetic */ PackageFragmentDescriptor findPackage(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return DescriptorUtilKt.findPackage(declarationDescriptor);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    public static final SourceFile findSourceFile(CallableMemberDescriptor callableMemberDescriptor) throws NotImplementedError {
        callableMemberDescriptor.getClass();
        SourceFile containingFile = callableMemberDescriptor.getSource().getContainingFile();
        containingFile.getClass();
        if (!Intrinsics.areEqual(containingFile, SourceFile.NO_SOURCE_FILE)) {
            return containingFile;
        }
        if (callableMemberDescriptor instanceof DeserializedSimpleFunctionDescriptor) {
            DeserializedSimpleFunctionDescriptor deserializedSimpleFunctionDescriptor = (DeserializedSimpleFunctionDescriptor) callableMemberDescriptor;
            ProtoBuf.Function proto = deserializedSimpleFunctionDescriptor.getProto();
            GeneratedMessageLite.GeneratedExtension generatedExtension = KlibMetadataProtoBuf.functionFile;
            if (proto.hasExtension(generatedExtension)) {
                Object extension = deserializedSimpleFunctionDescriptor.getProto().getExtension(generatedExtension);
                extension.getClass();
                return sourceByIndex(callableMemberDescriptor, ((Number) extension).intValue());
            }
        }
        if (callableMemberDescriptor instanceof DeserializedPropertyDescriptor) {
            DeserializedPropertyDescriptor deserializedPropertyDescriptor = (DeserializedPropertyDescriptor) callableMemberDescriptor;
            ProtoBuf.Property proto2 = deserializedPropertyDescriptor.getProto();
            GeneratedMessageLite.GeneratedExtension generatedExtension2 = KlibMetadataProtoBuf.propertyFile;
            if (proto2.hasExtension(generatedExtension2)) {
                Object extension2 = deserializedPropertyDescriptor.getProto().getExtension(generatedExtension2);
                extension2.getClass();
                return sourceByIndex(callableMemberDescriptor, ((Number) extension2).intValue());
            }
        }
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }

    public static final boolean isExpectMember(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return (declarationDescriptor instanceof MemberDescriptor) && ((MemberDescriptor) declarationDescriptor).isExpect();
    }

    public static final boolean isSerializableExpectClass(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        return (declarationDescriptor instanceof ClassDescriptor) && OptionalAnnotationUtil.shouldGenerateExpectClass((ClassDescriptor) declarationDescriptor);
    }

    private static final SourceFile sourceByIndex(CallableMemberDescriptor callableMemberDescriptor, int i) {
        KlibMetadataDeserializedPackageFragment klibMetadataDeserializedPackageFragmentFindPackage = DescriptorUtilKt.findPackage(callableMemberDescriptor);
        klibMetadataDeserializedPackageFragmentFindPackage.getClass();
        String str = (String) klibMetadataDeserializedPackageFragmentFindPackage.getProto().getStrings().getStringList().get(i);
        str.getClass();
        return new DeserializedSourceFile(str, KlibModuleOriginKt.getKotlinLibrary(DescriptorUtilsKt.getModule(callableMemberDescriptor)));
    }
}

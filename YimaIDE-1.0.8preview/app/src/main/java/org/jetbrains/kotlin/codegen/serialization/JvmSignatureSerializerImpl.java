package org.jetbrains.kotlin.codegen.serialization;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.ReceiverParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.metadata.jvm.deserialization.ClassMapperLite;
import org.jetbrains.kotlin.metadata.serialization.StringTable;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0014J\u0018\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/codegen/serialization/JvmSignatureSerializerImpl;", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSignatureSerializer;", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "stringTable", "Lorg/jetbrains/kotlin/metadata/serialization/StringTable;", "<init>", "(Lorg/jetbrains/kotlin/metadata/serialization/StringTable;)V", "requiresFunctionSignature", Argument.Delimiters.none, "descriptor", "desc", Argument.Delimiters.none, "requiresPropertySignature", "mapTypeDefault", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/types/KotlinType;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmSignatureSerializerImpl extends JvmSignatureSerializer<FunctionDescriptor, PropertyDescriptor> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JvmSignatureSerializerImpl(StringTable stringTable) {
        super(stringTable);
        stringTable.getClass();
    }

    private final String mapTypeDefault(KotlinType type) {
        ClassId classId;
        ClassDescriptor declarationDescriptor = type.getConstructor().getDeclarationDescriptor();
        ClassDescriptor classDescriptor = declarationDescriptor instanceof ClassDescriptor ? declarationDescriptor : null;
        if (classDescriptor == null || (classId = DescriptorUtilsKt.getClassId(classDescriptor)) == null) {
            return null;
        }
        return ClassMapperLite.mapClass(classId.asString());
    }

    public boolean requiresFunctionSignature(FunctionDescriptor descriptor, String desc) {
        descriptor.getClass();
        desc.getClass();
        StringBuilder sb = new StringBuilder("(");
        ReceiverParameterDescriptor extensionReceiverParameter = descriptor.getExtensionReceiverParameter();
        if (extensionReceiverParameter != null) {
            KotlinType type = extensionReceiverParameter.getValue().getType();
            type.getClass();
            String strMapTypeDefault = mapTypeDefault(type);
            if (strMapTypeDefault == null) {
                return true;
            }
            sb.append(strMapTypeDefault);
        }
        Iterator<ValueParameterDescriptor> it = descriptor.getValueParameters().iterator();
        while (it.hasNext()) {
            KotlinType type2 = it.next().getType();
            type2.getClass();
            String strMapTypeDefault2 = mapTypeDefault(type2);
            if (strMapTypeDefault2 == null) {
                return true;
            }
            sb.append(strMapTypeDefault2);
        }
        sb.append(")");
        KotlinType returnType = descriptor.getReturnType();
        String strMapTypeDefault3 = returnType == null ? "V" : mapTypeDefault(returnType);
        if (strMapTypeDefault3 == null) {
            return true;
        }
        sb.append(strMapTypeDefault3);
        return !Intrinsics.areEqual(sb.toString(), desc);
    }

    public boolean requiresPropertySignature(PropertyDescriptor descriptor, String desc) {
        descriptor.getClass();
        desc.getClass();
        KotlinType type = descriptor.getType();
        type.getClass();
        return !Intrinsics.areEqual(desc, mapTypeDefault(type));
    }
}

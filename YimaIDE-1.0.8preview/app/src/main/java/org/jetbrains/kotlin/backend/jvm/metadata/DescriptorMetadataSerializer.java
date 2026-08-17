package org.jetbrains.kotlin.backend.jvm.metadata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.jvm.JvmBackendContext;
import org.jetbrains.kotlin.backend.jvm.JvmIrAttributesKt;
import org.jetbrains.kotlin.backend.jvm.JvmLoweredDeclarationOrigin;
import org.jetbrains.kotlin.backend.jvm.mapping.IrTypeMappingKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.FakeDescriptorsForReferencesKt;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializationBindings;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializerExtension;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.ScriptDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptorWithAccessors;
import org.jetbrains.kotlin.ir.declarations.DescriptorMetadataSource;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.serialization.JvmStringTable;
import org.jetbrains.kotlin.protobuf.MessageLite;
import org.jetbrains.kotlin.serialization.DescriptorSerializer;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u000b\u0010\fJ(\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00122\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0018\u0010 \u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J$\u0010\"\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020#0\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/metadata/DescriptorMetadataSerializer;", "Lorg/jetbrains/kotlin/backend/jvm/metadata/MetadataSerializer;", "context", "Lorg/jetbrains/kotlin/backend/jvm/JvmBackendContext;", "irClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "serializationBindings", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;", "parent", "<init>", "(Lorg/jetbrains/kotlin/backend/jvm/JvmBackendContext;Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/org/objectweb/asm/Type;Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;Lorg/jetbrains/kotlin/backend/jvm/metadata/MetadataSerializer;)V", "serializerExtension", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializerExtension;", "serializer", "Lorg/jetbrains/kotlin/serialization/DescriptorSerializer;", "serialize", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/protobuf/MessageLite;", "Lorg/jetbrains/kotlin/metadata/jvm/serialization/JvmStringTable;", "metadata", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "containingFile", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$File;", "bindPropertyMetadata", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Property;", "signature", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "bindMethodMetadata", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Function;", "bindFieldMetadata", Argument.Delimiters.none, "org.jetbrains.kotlin:backend.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DescriptorMetadataSerializer implements MetadataSerializer {
    private final JvmBackendContext context;
    private final IrClass irClass;
    private final JvmSerializationBindings serializationBindings;
    private final DescriptorSerializer serializer;
    private final JvmSerializerExtension serializerExtension;
    private final Type type;

    public DescriptorMetadataSerializer(JvmBackendContext jvmBackendContext, IrClass irClass, Type type, JvmSerializationBindings jvmSerializationBindings, MetadataSerializer metadataSerializer) {
        jvmBackendContext.getClass();
        irClass.getClass();
        type.getClass();
        jvmSerializationBindings.getClass();
        this.context = jvmBackendContext;
        this.irClass = irClass;
        this.type = type;
        this.serializationBindings = jvmSerializationBindings;
        JvmSerializerExtension jvmSerializerExtension = new JvmSerializerExtension(jvmSerializationBindings, jvmBackendContext.getState(), jvmBackendContext.getDefaultTypeMapper());
        this.serializerExtension = jvmSerializerExtension;
        LanguageVersionSettings languageVersionSettings = jvmBackendContext.getConfig().getLanguageVersionSettings();
        DescriptorMetadataSource.Class metadata = irClass.getMetadata();
        DescriptorSerializer descriptorSerializerCreateForLambda = null;
        if (metadata instanceof DescriptorMetadataSource.Class) {
            DescriptorSerializer.Companion companion = DescriptorSerializer.Companion;
            ClassDescriptor descriptor = metadata.getDescriptor();
            DescriptorMetadataSerializer descriptorMetadataSerializer = metadataSerializer instanceof DescriptorMetadataSerializer ? (DescriptorMetadataSerializer) metadataSerializer : null;
            descriptorSerializerCreateForLambda = companion.create(descriptor, jvmSerializerExtension, descriptorMetadataSerializer != null ? descriptorMetadataSerializer.serializer : null, languageVersionSettings, jvmBackendContext.getState().getProject());
        } else if (metadata instanceof DescriptorMetadataSource.Script) {
            DescriptorSerializer.Companion companion2 = DescriptorSerializer.Companion;
            ScriptDescriptor descriptor2 = ((DescriptorMetadataSource.Script) metadata).getDescriptor();
            DescriptorMetadataSerializer descriptorMetadataSerializer2 = metadataSerializer instanceof DescriptorMetadataSerializer ? (DescriptorMetadataSerializer) metadataSerializer : null;
            descriptorSerializerCreateForLambda = companion2.create(descriptor2, jvmSerializerExtension, descriptorMetadataSerializer2 != null ? descriptorMetadataSerializer2.serializer : null, languageVersionSettings, jvmBackendContext.getState().getProject());
        } else if (metadata instanceof DescriptorMetadataSource.File) {
            descriptorSerializerCreateForLambda = DescriptorSerializer.Companion.createTopLevel(jvmSerializerExtension, languageVersionSettings, jvmBackendContext.getState().getProject());
        } else if (metadata instanceof DescriptorMetadataSource.Function) {
            descriptorSerializerCreateForLambda = DescriptorSerializer.Companion.createForLambda(jvmSerializerExtension, languageVersionSettings);
        }
        this.serializer = descriptorSerializerCreateForLambda;
    }

    public void bindFieldMetadata(MetadataSource.Property metadata, Pair<Type, String> signature) {
        metadata.getClass();
        signature.getClass();
        this.context.getState().getGlobalSerializationBindings().put(JvmSerializationBindings.FIELD_FOR_PROPERTY, ((DescriptorMetadataSource.Property) metadata).getDescriptor(), signature);
    }

    public void bindMethodMetadata(MetadataSource.Function metadata, Method signature) {
        metadata.getClass();
        signature.getClass();
        this.serializationBindings.put(JvmSerializationBindings.METHOD_FOR_FUNCTION, ((DescriptorMetadataSource.Function) metadata).getDescriptor(), signature);
    }

    public void bindPropertyMetadata(MetadataSource.Property metadata, Method signature, IrDeclarationOrigin origin) {
        JvmSerializationBindings.SerializationMappingSlice<PropertyDescriptor, Method> serializationMappingSlice;
        metadata.getClass();
        signature.getClass();
        origin.getClass();
        PropertyDescriptor descriptor = ((DescriptorMetadataSource.Property) metadata).getDescriptor();
        if (Intrinsics.areEqual(origin, JvmLoweredDeclarationOrigin.INSTANCE.getSYNTHETIC_METHOD_FOR_PROPERTY_OR_TYPEALIAS_ANNOTATIONS())) {
            serializationMappingSlice = JvmSerializationBindings.SYNTHETIC_METHOD_FOR_PROPERTY;
        } else {
            if (!Intrinsics.areEqual(origin, IrDeclarationOrigin.Companion.getPROPERTY_DELEGATE())) {
                yj.a("invalid origin ", origin, " for property-related method ", signature);
                return;
            }
            serializationMappingSlice = JvmSerializationBindings.DELEGATE_METHOD_FOR_PROPERTY;
        }
        this.context.getState().getGlobalSerializationBindings().put(serializationMappingSlice, descriptor, signature);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0123  */
    public Pair<MessageLite, JvmStringTable> serialize(MetadataSource metadata, MetadataSource.File containingFile) {
        ProtoBuf.Class classBuild;
        metadata.getClass();
        List localDelegatedProperties = JvmIrAttributesKt.getLocalDelegatedProperties(this.irClass);
        if (localDelegatedProperties != null && !localDelegatedProperties.isEmpty()) {
            Map<Type, List<VariableDescriptorWithAccessors>> localDelegatedProperties2 = this.context.getState().getLocalDelegatedProperties();
            Type typeMapClass = (!IrUtilsKt.isInterface(this.irClass) || this.context.getConfig().getJvmDefaultMode().isEnabled()) ? this.type : IrTypeMappingKt.mapClass(this.context.getDefaultTypeMapper(), this.context.getCachedDeclarations().getDefaultImplsClass(this.irClass));
            ArrayList arrayList = new ArrayList();
            Iterator it = localDelegatedProperties.iterator();
            while (it.hasNext()) {
                DescriptorMetadataSource.LocalDelegatedProperty metadata2 = ((IrLocalDelegatedPropertySymbol) it.next()).getOwner().getMetadata();
                DescriptorMetadataSource.LocalDelegatedProperty localDelegatedProperty = metadata2 instanceof DescriptorMetadataSource.LocalDelegatedProperty ? metadata2 : null;
                VariableDescriptorWithAccessors descriptor = localDelegatedProperty != null ? localDelegatedProperty.getDescriptor() : null;
                if (descriptor != null) {
                    arrayList.add(descriptor);
                }
            }
            localDelegatedProperties2.put(typeMapClass, arrayList);
        }
        if (metadata instanceof DescriptorMetadataSource.Class) {
            DescriptorSerializer descriptorSerializer = this.serializer;
            descriptorSerializer.getClass();
            classBuild = descriptorSerializer.classProto(((DescriptorMetadataSource.Class) metadata).getDescriptor()).build();
        } else if (metadata instanceof DescriptorMetadataSource.Script) {
            DescriptorSerializer descriptorSerializer2 = this.serializer;
            descriptorSerializer2.getClass();
            classBuild = descriptorSerializer2.classProto(((DescriptorMetadataSource.Script) metadata).getDescriptor()).build();
        } else if (metadata instanceof DescriptorMetadataSource.File) {
            DescriptorSerializer descriptorSerializer3 = this.serializer;
            descriptorSerializer3.getClass();
            ProtoBuf.Package.Builder builderPackagePartProto = descriptorSerializer3.packagePartProto(IrUtilsKt.getPackageFragment(this.irClass).getPackageFqName(), ((DescriptorMetadataSource.File) metadata).getDescriptors());
            this.serializerExtension.serializeJvmPackage(builderPackagePartProto, this.type);
            classBuild = builderPackagePartProto.build();
        } else if (metadata instanceof DescriptorMetadataSource.Function) {
            DescriptorMetadataSource.Function function = (DescriptorMetadataSource.Function) metadata;
            FunctionDescriptor functionDescriptorCreateFreeFakeLambdaDescriptor = FakeDescriptorsForReferencesKt.createFreeFakeLambdaDescriptor(function.getDescriptor(), this.context.getState().getTypeApproximator());
            JvmSerializationBindings jvmSerializationBindings = this.serializationBindings;
            JvmSerializationBindings.SerializationMappingSlice<FunctionDescriptor, Method> serializationMappingSlice = JvmSerializationBindings.METHOD_FOR_FUNCTION;
            Method method = (Method) jvmSerializationBindings.get(serializationMappingSlice, function.getDescriptor());
            if (method != null) {
                this.serializationBindings.put(serializationMappingSlice, functionDescriptorCreateFreeFakeLambdaDescriptor, method);
            }
            DescriptorSerializer descriptorSerializer4 = this.serializer;
            descriptorSerializer4.getClass();
            ProtoBuf.Function.Builder builderFunctionProto = descriptorSerializer4.functionProto(functionDescriptorCreateFreeFakeLambdaDescriptor);
            if (builderFunctionProto != null) {
                classBuild = builderFunctionProto.build();
            } else {
                classBuild = null;
            }
        } else {
            classBuild = null;
        }
        if (classBuild == null) {
            return null;
        }
        DescriptorSerializer descriptorSerializer5 = this.serializer;
        descriptorSerializer5.getClass();
        JvmStringTable stringTable = descriptorSerializer5.getStringTable();
        stringTable.getClass();
        return TuplesKt.to(classBuild, stringTable);
    }
}

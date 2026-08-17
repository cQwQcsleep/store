package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.backend.jvm.JvmBackendContext;
import org.jetbrains.kotlin.backend.jvm.JvmBackendExtension;
import org.jetbrains.kotlin.backend.jvm.ModuleMetadataSerializer;
import org.jetbrains.kotlin.backend.jvm.metadata.BuiltinsSerializer;
import org.jetbrains.kotlin.backend.jvm.metadata.MetadataSerializer;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializationBindings;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.serialization.FirElementAwareStringTable;
import org.jetbrains.kotlin.fir.serialization.FirElementSerializer;
import org.jetbrains.kotlin.fir.serialization.SerializationUtilKt;
import org.jetbrains.kotlin.fir.serialization.TypeApproximatorForMetadataSerializer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.serialization.MutableVersionRequirementTable;
import org.jetbrains.kotlin.serialization.StringTableImpl;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;
import org.jetbrains.org.objectweb.asm.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ2\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirJvmBackendExtension;", "Lorg/jetbrains/kotlin/backend/jvm/JvmBackendExtension;", "components", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "actualizedExpectDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Ljava/util/Set;)V", "createSerializer", "Lorg/jetbrains/kotlin/backend/jvm/metadata/MetadataSerializer;", "context", "Lorg/jetbrains/kotlin/backend/jvm/JvmBackendContext;", "klass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "bindings", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;", "parentSerializer", "createModuleMetadataSerializer", "Lorg/jetbrains/kotlin/backend/jvm/ModuleMetadataSerializer;", "createBuiltinsSerializer", "Lorg/jetbrains/kotlin/backend/jvm/metadata/BuiltinsSerializer;", "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmBackendExtension implements JvmBackendExtension {
    private final Set<FirDeclaration> actualizedExpectDeclarations;
    private final Fir2IrComponents components;

    /* JADX WARN: Multi-variable type inference failed */
    public FirJvmBackendExtension(Fir2IrComponents fir2IrComponents, Set<? extends FirDeclaration> set) {
        fir2IrComponents.getClass();
        this.components = fir2IrComponents;
        this.actualizedExpectDeclarations = set;
    }

    public BuiltinsSerializer createBuiltinsSerializer() {
        return new FirBuiltInsSerializer(this.components.getSession(), this.components.getScopeSession());
    }

    public ModuleMetadataSerializer createModuleMetadataSerializer(final JvmBackendContext context) {
        context.getClass();
        return new ModuleMetadataSerializer() { // from class: org.jetbrains.kotlin.fir.backend.jvm.FirJvmBackendExtension.createModuleMetadataSerializer.1
            /* JADX WARN: Type inference failed for: r9v0, types: [org.jetbrains.kotlin.fir.backend.jvm.FirJvmBackendExtension$createModuleMetadataSerializer$1$serializeOptionalAnnotationClass$firSerializerExtension$2] */
            public ProtoBuf.Class serializeOptionalAnnotationClass(MetadataSource.Class metadata, final StringTableImpl stringTable) {
                metadata.getClass();
                stringTable.getClass();
                if (!(metadata instanceof FirMetadataSource.Class)) {
                    dt1.a("Metadata is expected to be ", Reflection.getOrCreateKotlinClass(FirMetadataSource.Class.class).getSimpleName());
                    return null;
                }
                final FirSession session = FirJvmBackendExtension.this.components.getSession();
                FirClass fir = ((FirMetadataSource.Class) metadata).getFir();
                final TypeApproximatorForMetadataSerializer typeApproximatorForMetadataSerializer = new TypeApproximatorForMetadataSerializer(session);
                final JvmSerializationBindings jvmSerializationBindings = new JvmSerializationBindings();
                final GenerationState state = context.getState();
                final List listEmptyList = CollectionsKt.emptyList();
                final Fir2IrComponents fir2IrComponents = FirJvmBackendExtension.this.components;
                final ?? r9 = new FirElementAwareStringTable() { // from class: org.jetbrains.kotlin.fir.backend.jvm.FirJvmBackendExtension$createModuleMetadataSerializer$1$serializeOptionalAnnotationClass$firSerializerExtension$2
                    @Override // org.jetbrains.kotlin.metadata.serialization.StringTable
                    public int getPackageFqNameIndexByString(String fqName) {
                        fqName.getClass();
                        return stringTable.getPackageFqNameIndexByString(fqName);
                    }

                    @Override // org.jetbrains.kotlin.metadata.serialization.StringTable
                    public int getQualifiedClassNameIndex(String className, boolean isLocal) {
                        className.getClass();
                        return stringTable.getQualifiedClassNameIndex(className, isLocal);
                    }

                    @Override // org.jetbrains.kotlin.metadata.serialization.StringTable
                    public int getStringIndex(String string) {
                        string.getClass();
                        return stringTable.getStringIndex(string);
                    }
                };
                final JvmBackendContext jvmBackendContext = context;
                ProtoBuf.Class classBuild = FirElementSerializer.INSTANCE.create(session, FirJvmBackendExtension.this.components.getScopeSession(), fir, new FirJvmSerializerExtension(typeApproximatorForMetadataSerializer, jvmBackendContext, jvmSerializationBindings, state, listEmptyList, fir2IrComponents, r9) { // from class: org.jetbrains.kotlin.fir.backend.jvm.FirJvmBackendExtension$createModuleMetadataSerializer$1$serializeOptionalAnnotationClass$firSerializerExtension$1
                    final /* synthetic */ JvmBackendContext $context;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(this.$session, jvmSerializationBindings, state, listEmptyList, typeApproximatorForMetadataSerializer, fir2IrComponents, r9);
                        this.$context = jvmBackendContext;
                    }

                    @Override // org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension
                    public boolean isOptionalAnnotationClassSerialization() {
                        return true;
                    }

                    @Override // org.jetbrains.kotlin.fir.backend.jvm.FirJvmSerializerExtension, org.jetbrains.kotlin.fir.serialization.FirSerializerExtension
                    public void serializeClass(FirClass klass, ProtoBuf.Class.Builder proto, MutableVersionRequirementTable versionRequirementTable, FirElementSerializer childSerializer) {
                        FirClass firClass;
                        ProtoBuf.Class.Builder builder;
                        klass.getClass();
                        proto.getClass();
                        versionRequirementTable.getClass();
                        childSerializer.getClass();
                        if (this.$context.getConfig().getMetadataVersion().isAtLeast(2, 2, 0)) {
                            firClass = klass;
                            builder = proto;
                        } else {
                            firClass = klass;
                            builder = proto;
                            SerializationUtilKt.serializeAnnotations$default(firClass, this.$session, getAdditionalMetadataProvider(), getAnnotationSerializer(), builder, BuiltInSerializerProtocol.INSTANCE.getClassAnnotation(), null, null, 96, null);
                        }
                        super.serializeClass(firClass, builder, versionRequirementTable, childSerializer);
                    }
                }, null, typeApproximatorForMetadataSerializer, context.getConfig().getLanguageVersionSettings(), (128 & 128) != 0 ? false : false).classProto(fir, FirProviderKt.getFirProvider(session).getFirClassifierContainerFileIfAny(fir.getSymbol())).build();
                classBuild.getClass();
                return classBuild;
            }
        };
    }

    public MetadataSerializer createSerializer(JvmBackendContext context, IrClass klass, Type type, JvmSerializationBindings bindings, MetadataSerializer parentSerializer) {
        context.getClass();
        klass.getClass();
        type.getClass();
        bindings.getClass();
        return FirMetadataSerializerKt.makeFirMetadataSerializerForIrClass(this.components.getSession(), context, klass, bindings, this.components, parentSerializer, this.actualizedExpectDeclarations);
    }
}

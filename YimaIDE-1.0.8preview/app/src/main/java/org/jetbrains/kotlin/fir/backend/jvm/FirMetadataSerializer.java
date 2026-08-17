package org.jetbrains.kotlin.fir.backend.jvm;

import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.backend.jvm.JvmLoweredDeclarationOrigin;
import org.jetbrains.kotlin.backend.jvm.metadata.MetadataSerializer;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializationBindings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.serialization.FirElementSerializer;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.MetadataSource;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.jvm.serialization.JvmStringTable;
import org.jetbrains.kotlin.protobuf.MessageLite;
import org.jetbrains.kotlin.types.AbstractTypeApproximator;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJ(\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0018\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J$\u0010!\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/jvm/FirMetadataSerializer;", "Lorg/jetbrains/kotlin/backend/jvm/metadata/MetadataSerializer;", "globalSerializationBindings", "Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;", "serializationBindings", "approximator", "Lorg/jetbrains/kotlin/types/AbstractTypeApproximator;", "serializer", "Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "actualizedExpectDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "<init>", "(Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;Lorg/jetbrains/kotlin/codegen/serialization/JvmSerializationBindings;Lorg/jetbrains/kotlin/types/AbstractTypeApproximator;Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;Ljava/util/Set;)V", "getSerializer$org_jetbrains_kotlin_jvm_backend", "()Lorg/jetbrains/kotlin/fir/serialization/FirElementSerializer;", "serialize", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/protobuf/MessageLite;", "Lorg/jetbrains/kotlin/metadata/jvm/serialization/JvmStringTable;", "metadata", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "containingFile", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$File;", "bindPropertyMetadata", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Property;", "signature", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "origin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "bindMethodMetadata", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Function;", "bindFieldMetadata", "Lorg/jetbrains/org/objectweb/asm/Type;", Argument.Delimiters.none, "org.jetbrains.kotlin:jvm-backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMetadataSerializer implements MetadataSerializer {
    private final Set<FirDeclaration> actualizedExpectDeclarations;
    private final AbstractTypeApproximator approximator;
    private final JvmSerializationBindings globalSerializationBindings;
    private final JvmSerializationBindings serializationBindings;
    private final FirElementSerializer serializer;

    /* JADX WARN: Multi-variable type inference failed */
    public FirMetadataSerializer(JvmSerializationBindings jvmSerializationBindings, JvmSerializationBindings jvmSerializationBindings2, AbstractTypeApproximator abstractTypeApproximator, FirElementSerializer firElementSerializer, Set<? extends FirDeclaration> set) {
        jvmSerializationBindings.getClass();
        jvmSerializationBindings2.getClass();
        abstractTypeApproximator.getClass();
        this.globalSerializationBindings = jvmSerializationBindings;
        this.serializationBindings = jvmSerializationBindings2;
        this.approximator = abstractTypeApproximator;
        this.serializer = firElementSerializer;
        this.actualizedExpectDeclarations = set;
    }

    public void bindFieldMetadata(MetadataSource.Property metadata, Pair<Type, String> signature) {
        metadata.getClass();
        signature.getClass();
        if (metadata instanceof FirMetadataSource.Property) {
            this.globalSerializationBindings.put(FirJvmSerializerExtension.INSTANCE.getFIELD_FOR_PROPERTY(), ((FirMetadataSource.Property) metadata).getFir(), signature);
            return;
        }
        if (!(metadata instanceof FirMetadataSource.Field)) {
            w04.a("Unexpected metadata: ", metadata);
            return;
        }
        FirField fir = ((FirMetadataSource.Field) metadata).getFir();
        KtSourceElement source = fir.getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ClassDelegationField.INSTANCE)) {
            return;
        }
        StringBuilder sb = new StringBuilder("Expected delegate field, got ");
        sb.append(UtilsKt.render(fir));
        sb.append(" with source kind ");
        KtSourceElement source2 = fir.getSource();
        s76.a(sb, source2 != null ? source2.getKind() : null);
    }

    public void bindMethodMetadata(MetadataSource.Function metadata, Method signature) {
        metadata.getClass();
        signature.getClass();
        this.serializationBindings.put(FirJvmSerializerExtension.INSTANCE.getMETHOD_FOR_FIR_FUNCTION(), ((FirMetadataSource.Function) metadata).getFir(), signature);
    }

    public void bindPropertyMetadata(MetadataSource.Property metadata, Method signature, IrDeclarationOrigin origin) {
        JvmSerializationBindings.SerializationMappingSlice<FirVariable, Method> delegate_method_for_fir_variable;
        metadata.getClass();
        signature.getClass();
        origin.getClass();
        FirProperty fir = ((FirMetadataSource.Property) metadata).getFir();
        if (Intrinsics.areEqual(origin, JvmLoweredDeclarationOrigin.INSTANCE.getSYNTHETIC_METHOD_FOR_PROPERTY_OR_TYPEALIAS_ANNOTATIONS())) {
            delegate_method_for_fir_variable = FirJvmSerializerExtension.INSTANCE.getSYNTHETIC_METHOD_FOR_FIR_VARIABLE();
        } else {
            if (!Intrinsics.areEqual(origin, IrDeclarationOrigin.Companion.getPROPERTY_DELEGATE())) {
                yj.a("invalid origin ", origin, " for property-related method ", signature);
                return;
            }
            delegate_method_for_fir_variable = FirJvmSerializerExtension.INSTANCE.getDELEGATE_METHOD_FOR_FIR_VARIABLE();
        }
        this.globalSerializationBindings.put(delegate_method_for_fir_variable, fir, signature);
    }

    /* JADX INFO: renamed from: getSerializer$org_jetbrains_kotlin_jvm_backend, reason: from getter */
    public final FirElementSerializer getSerializer() {
        return this.serializer;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x00ae  */
    public Pair<MessageLite, JvmStringTable> serialize(MetadataSource metadata, MetadataSource.File containingFile) {
        ProtoBuf.Class classBuild;
        metadata.getClass();
        if (metadata instanceof FirMetadataSource.Class) {
            FirElementSerializer firElementSerializer = this.serializer;
            firElementSerializer.getClass();
            FirMetadataSource.File file = (FirMetadataSource.File) containingFile;
            classBuild = firElementSerializer.classProto(((FirMetadataSource.Class) metadata).getFir(), file != null ? file.getFir() : null).build();
        } else if (metadata instanceof FirMetadataSource.File) {
            FirElementSerializer firElementSerializer2 = this.serializer;
            firElementSerializer2.getClass();
            classBuild = firElementSerializer2.packagePartProto(((FirMetadataSource.File) metadata).getFir(), this.actualizedExpectDeclarations).build();
        } else if (metadata instanceof FirMetadataSource.Function) {
            FirMetadataSource.Function function = (FirMetadataSource.Function) metadata;
            FirAnonymousFunction firAnonymousFunctionCopyToFreeAnonymousFunction = FirMetadataSerializerKt.copyToFreeAnonymousFunction(function.getFir(), this.approximator);
            JvmSerializationBindings jvmSerializationBindings = this.serializationBindings;
            FirJvmSerializerExtension.Companion companion = FirJvmSerializerExtension.INSTANCE;
            Method method = (Method) jvmSerializationBindings.get(companion.getMETHOD_FOR_FIR_FUNCTION(), function.getFir());
            if (method != null) {
                this.serializationBindings.put(companion.getMETHOD_FOR_FIR_FUNCTION(), firAnonymousFunctionCopyToFreeAnonymousFunction, method);
            }
            FirElementSerializer firElementSerializer3 = this.serializer;
            firElementSerializer3.getClass();
            ProtoBuf.Function.Builder builderFunctionProto = firElementSerializer3.functionProto(firAnonymousFunctionCopyToFreeAnonymousFunction);
            if (builderFunctionProto != null) {
                classBuild = builderFunctionProto.build();
            } else {
                classBuild = null;
            }
        } else if (metadata instanceof FirMetadataSource.Script) {
            FirElementSerializer firElementSerializer4 = this.serializer;
            firElementSerializer4.getClass();
            classBuild = firElementSerializer4.scriptProto(((FirMetadataSource.Script) metadata).getFir()).build();
        } else if (metadata instanceof FirMetadataSource.ReplSnippet) {
            FirElementSerializer firElementSerializer5 = this.serializer;
            firElementSerializer5.getClass();
            classBuild = firElementSerializer5.snippetProto(((FirMetadataSource.ReplSnippet) metadata).getFir()).build();
        } else {
            classBuild = null;
        }
        if (classBuild == null) {
            return null;
        }
        FirElementSerializer firElementSerializer6 = this.serializer;
        firElementSerializer6.getClass();
        JvmStringTable stringTable = firElementSerializer6.getStringTable();
        stringTable.getClass();
        return TuplesKt.to(classBuild, stringTable);
    }
}

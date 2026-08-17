package org.jetbrains.kotlin.fir.java.deserialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.deserialization.FirConstDeserializer;
import org.jetbrains.kotlin.fir.deserialization.FirConstDeserializerKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.SerializerExtensionProtocol;
import org.jetbrains.kotlin.types.ConstantValueKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/FirJvmConstDeserializer;", "Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;", "binaryClass", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;", "protocol", "Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "<init>", "(Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass;Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;)V", "loadConstant", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "isUnsigned", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmConstDeserializer extends FirConstDeserializer {
    private final KotlinJvmBinaryClass binaryClass;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirJvmConstDeserializer(KotlinJvmBinaryClass kotlinJvmBinaryClass, SerializerExtensionProtocol serializerExtensionProtocol) {
        super(serializerExtensionProtocol);
        kotlinJvmBinaryClass.getClass();
        serializerExtensionProtocol.getClass();
        this.binaryClass = kotlinJvmBinaryClass;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirConstDeserializer
    public FirExpression loadConstant(ProtoBuf.Property propertyProto, final CallableId callableId, final NameResolver nameResolver, final boolean isUnsigned) {
        propertyProto.getClass();
        callableId.getClass();
        nameResolver.getClass();
        if (!Flags.HAS_CONSTANT.get(propertyProto.getFlags()).booleanValue()) {
            return null;
        }
        FirLiteralExpression firLiteralExpression = getConstantCache().get(callableId);
        if (firLiteralExpression == null) {
            this.binaryClass.visitMembers(new KotlinJvmBinaryClass.MemberVisitor() { // from class: org.jetbrains.kotlin.fir.java.deserialization.FirJvmConstDeserializer.loadConstant.2
                public KotlinJvmBinaryClass.AnnotationVisitor visitField(Name name, String desc, Object initializer) {
                    FirLiteralExpression firLiteralExpressionBuildFirConstant;
                    name.getClass();
                    desc.getClass();
                    if (initializer != null && (firLiteralExpressionBuildFirConstant = FirConstDeserializerKt.buildFirConstant(null, initializer, desc, nameResolver, isUnsigned)) != null) {
                        this.getConstantCache().put(FirConstDeserializerKt.replaceName(callableId, name), firLiteralExpressionBuildFirConstant);
                    }
                    return null;
                }

                public KotlinJvmBinaryClass.MethodAnnotationVisitor visitMethod(Name name, String desc) {
                    name.getClass();
                    desc.getClass();
                    return null;
                }
            }, (byte[]) null);
            return getConstantCache().get(callableId);
        }
        if (firLiteralExpression.getKind().isUnsigned() != isUnsigned) {
            ConstantValueKind kind = firLiteralExpression.getKind();
            ConstantValueKind unsigned = isUnsigned ? kind.toUnsigned() : kind.toSigned();
            firLiteralExpression.replaceKind(unsigned);
            firLiteralExpression.replaceConeTypeOrNull(FirConstExpressionBuilderKt.toConeType(unsigned));
        }
        return firLiteralExpression;
    }
}

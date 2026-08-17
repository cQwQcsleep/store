package org.jetbrains.kotlin.fir.deserialization;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.ProtoBufUtilKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.serialization.SerializerExtensionProtocol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J*\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;", Argument.Delimiters.none, "protocol", "Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;", "<init>", "(Lorg/jetbrains/kotlin/serialization/SerializerExtensionProtocol;)V", "constantCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/CallableId;", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "getConstantCache", "()Ljava/util/Map;", "loadConstant", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "propertyProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "callableId", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "isUnsigned", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-deserialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirConstDeserializer {
    private final Map<CallableId, FirLiteralExpression> constantCache;
    private final SerializerExtensionProtocol protocol;

    public FirConstDeserializer(SerializerExtensionProtocol serializerExtensionProtocol) {
        serializerExtensionProtocol.getClass();
        this.protocol = serializerExtensionProtocol;
        this.constantCache = new HashMap();
    }

    public final Map<CallableId, FirLiteralExpression> getConstantCache() {
        return this.constantCache;
    }

    public FirExpression loadConstant(ProtoBuf.Property propertyProto, CallableId callableId, NameResolver nameResolver, boolean isUnsigned) {
        FirLiteralExpression firLiteralExpressionBuildFirConstant;
        propertyProto.getClass();
        callableId.getClass();
        nameResolver.getClass();
        if (!Flags.HAS_CONSTANT.get(propertyProto.getFlags()).booleanValue()) {
            return null;
        }
        FirLiteralExpression firLiteralExpression = this.constantCache.get(callableId);
        if (firLiteralExpression != null) {
            return firLiteralExpression;
        }
        ProtoBuf.Annotation.Argument.Value value = (ProtoBuf.Annotation.Argument.Value) ProtoBufUtilKt.getExtensionOrNull(propertyProto, this.protocol.getCompileTimeValue());
        if (value == null || (firLiteralExpressionBuildFirConstant = FirConstDeserializerKt.buildFirConstant(value, null, value.getType().name(), nameResolver, isUnsigned)) == null) {
            return null;
        }
        this.constantCache.put(callableId, firLiteralExpressionBuildFirConstant);
        return firLiteralExpressionBuildFirConstant;
    }
}

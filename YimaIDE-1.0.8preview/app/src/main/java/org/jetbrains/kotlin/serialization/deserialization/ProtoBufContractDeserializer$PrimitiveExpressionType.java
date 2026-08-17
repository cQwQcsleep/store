package org.jetbrains.kotlin.serialization.deserialization;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/ProtoBufContractDeserializer$PrimitiveExpressionType;", "", "<init>", "(Ljava/lang/String;I)V", "VALUE_PARAMETER_REFERENCE", "RECEIVER_REFERENCE", "CONSTANT", "INSTANCE_CHECK", "NULLABILITY_CHECK", "org.jetbrains.kotlin:deserialization.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public enum ProtoBufContractDeserializer$PrimitiveExpressionType {
    VALUE_PARAMETER_REFERENCE,
    RECEIVER_REFERENCE,
    CONSTANT,
    INSTANCE_CHECK,
    NULLABILITY_CHECK;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<ProtoBufContractDeserializer$PrimitiveExpressionType> getEntries() {
        return $ENTRIES;
    }
}

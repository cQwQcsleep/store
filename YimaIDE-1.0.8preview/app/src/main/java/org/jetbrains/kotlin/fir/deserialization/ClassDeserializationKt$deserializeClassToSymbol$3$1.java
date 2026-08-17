package org.jetbrains.kotlin.fir.deserialization;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.metadata.ProtoBuf;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class ClassDeserializationKt$deserializeClassToSymbol$3$1 extends FunctionReferenceImpl implements Function1<ProtoBuf.Type, ConeRigidType> {
    public ClassDeserializationKt$deserializeClassToSymbol$3$1(Object obj) {
        super(1, obj, FirTypeDeserializer.class, "rigidType", "rigidType(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;)Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", 0);
    }

    public final ConeRigidType invoke(ProtoBuf.Type type) {
        type.getClass();
        return ((FirTypeDeserializer) ((CallableReference) this).receiver).rigidType(type);
    }
}

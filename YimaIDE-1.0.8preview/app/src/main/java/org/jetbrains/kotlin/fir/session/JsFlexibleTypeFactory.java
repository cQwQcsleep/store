package org.jetbrains.kotlin.fir.session;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.metadata.ProtoBuf;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J \u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/JsFlexibleTypeFactory;", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "createFlexibleType", "Lorg/jetbrains/kotlin/fir/types/ConeFlexibleType;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "lowerBound", "Lorg/jetbrains/kotlin/fir/types/ConeRigidType;", "upperBound", "createDynamicType", "Lorg/jetbrains/kotlin/fir/types/ConeDynamicType;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JsFlexibleTypeFactory implements FirTypeDeserializer.FlexibleTypeFactory {
    private final FirSession session;

    public JsFlexibleTypeFactory(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer.FlexibleTypeFactory
    /* JADX INFO: renamed from: createDynamicType, reason: merged with bridge method [inline-methods] */
    public ConeDynamicType mo620createDynamicType(ProtoBuf.Type proto, ConeRigidType lowerBound, ConeRigidType upperBound) {
        proto.getClass();
        lowerBound.getClass();
        upperBound.getClass();
        return TypeUtilsKt.create(ConeDynamicType.Companion, this.session, lowerBound.getAttributes());
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer.FlexibleTypeFactory
    public ConeFlexibleType createFlexibleType(ProtoBuf.Type proto, ConeRigidType lowerBound, ConeRigidType upperBound) {
        proto.getClass();
        lowerBound.getClass();
        upperBound.getClass();
        return new ConeFlexibleType(lowerBound, upperBound, false);
    }
}

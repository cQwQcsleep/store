package org.jetbrains.kotlin.fir.serialization;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.ProtoBuf;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirAnnotationArgumentVisitorData;", Argument.Delimiters.none, "serializer", "Lorg/jetbrains/kotlin/fir/serialization/FirAnnotationSerializer;", "builder", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation$Argument$Value$Builder;", "<init>", "(Lorg/jetbrains/kotlin/fir/serialization/FirAnnotationSerializer;Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation$Argument$Value$Builder;)V", "getSerializer", "()Lorg/jetbrains/kotlin/fir/serialization/FirAnnotationSerializer;", "getBuilder", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation$Argument$Value$Builder;", "stringTable", "Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "getStringTable", "()Lorg/jetbrains/kotlin/fir/serialization/FirElementAwareStringTable;", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationArgumentVisitorData {
    private final ProtoBuf.Annotation.Argument.Value.Builder builder;
    private final FirAnnotationSerializer serializer;

    public FirAnnotationArgumentVisitorData(FirAnnotationSerializer firAnnotationSerializer, ProtoBuf.Annotation.Argument.Value.Builder builder) {
        firAnnotationSerializer.getClass();
        builder.getClass();
        this.serializer = firAnnotationSerializer;
        this.builder = builder;
    }

    public final ProtoBuf.Annotation.Argument.Value.Builder getBuilder() {
        return this.builder;
    }

    public final FirAnnotationSerializer getSerializer() {
        return this.serializer;
    }

    public final FirElementAwareStringTable getStringTable() {
        return this.serializer.getStringTable();
    }
}

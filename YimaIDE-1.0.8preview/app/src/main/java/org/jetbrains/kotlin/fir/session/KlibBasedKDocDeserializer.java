package org.jetbrains.kotlin.fir.session;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer;
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.ProtoBufUtilKt;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/KlibBasedKDocDeserializer;", "Lorg/jetbrains/kotlin/fir/deserialization/FirKDocDeserializer;", "<init>", "()V", "loadPropertyKDoc", Argument.Delimiters.none, "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "loadFunctionKDoc", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "loadConstructorKDoc", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Constructor;", "loadClassKDoc", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KlibBasedKDocDeserializer implements FirKDocDeserializer {
    public static final KlibBasedKDocDeserializer INSTANCE = new KlibBasedKDocDeserializer();

    private KlibBasedKDocDeserializer() {
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer
    public String loadClassKDoc(ProtoBuf.Class proto) {
        proto.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = KlibMetadataProtoBuf.classKdoc;
        generatedExtension.getClass();
        return (String) ProtoBufUtilKt.getExtensionOrNull(proto, generatedExtension);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer
    public String loadConstructorKDoc(ProtoBuf.Constructor proto) {
        proto.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = KlibMetadataProtoBuf.constructorKdoc;
        generatedExtension.getClass();
        return (String) ProtoBufUtilKt.getExtensionOrNull(proto, generatedExtension);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer
    public String loadFunctionKDoc(ProtoBuf.Function proto) {
        proto.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = KlibMetadataProtoBuf.functionKdoc;
        generatedExtension.getClass();
        return (String) ProtoBufUtilKt.getExtensionOrNull(proto, generatedExtension);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.FirKDocDeserializer
    public String loadPropertyKDoc(ProtoBuf.Property proto) {
        proto.getClass();
        GeneratedMessageLite.GeneratedExtension generatedExtension = KlibMetadataProtoBuf.propertyKdoc;
        generatedExtension.getClass();
        return (String) ProtoBufUtilKt.getExtensionOrNull(proto, generatedExtension);
    }
}

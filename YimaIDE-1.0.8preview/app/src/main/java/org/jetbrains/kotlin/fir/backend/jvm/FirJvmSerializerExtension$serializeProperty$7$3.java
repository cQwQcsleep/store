package org.jetbrains.kotlin.fir.backend.jvm;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.AdaptedFunctionReference;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.ProtoBuf;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class FirJvmSerializerExtension$serializeProperty$7$3 extends AdaptedFunctionReference implements Function1<ProtoBuf.Annotation, Unit> {
    public FirJvmSerializerExtension$serializeProperty$7$3(Object obj) {
        super(1, obj, ProtoBuf.Property.Builder.class, "addDelegateFieldAnnotation", "addDelegateFieldAnnotation(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property$Builder;", 8);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ProtoBuf.Annotation) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ProtoBuf.Annotation annotation) {
        ((ProtoBuf.Property.Builder) ((AdaptedFunctionReference) this).receiver).addDelegateFieldAnnotation(annotation);
    }
}

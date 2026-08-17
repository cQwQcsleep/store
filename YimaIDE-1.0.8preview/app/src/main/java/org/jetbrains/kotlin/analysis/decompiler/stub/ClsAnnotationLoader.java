package org.jetbrains.kotlin.analysis.decompiler.stub;

import kotlin.Metadata;
import org.jetbrains.kotlin.constant.ConstantValue;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.serialization.deserialization.AnnotationLoader;
import org.jetbrains.kotlin.serialization.deserialization.ProtoContainer;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001e\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/stub/ClsAnnotationLoader;", "Lorg/jetbrains/kotlin/serialization/deserialization/AnnotationLoader;", "Lorg/jetbrains/kotlin/analysis/decompiler/stub/AnnotationWithArgs;", "loadPropertyInitializer", "Lorg/jetbrains/kotlin/constant/ConstantValue;", "container", "Lorg/jetbrains/kotlin/serialization/deserialization/ProtoContainer;", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "org.jetbrains.kotlin:decompiler-to-stubs"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ClsAnnotationLoader extends AnnotationLoader<AnnotationWithArgs> {
    ConstantValue<?> loadPropertyInitializer(ProtoContainer container, ProtoBuf.Property proto);
}

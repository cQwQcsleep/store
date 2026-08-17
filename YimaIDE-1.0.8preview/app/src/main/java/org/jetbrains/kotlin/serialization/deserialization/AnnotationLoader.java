package org.jetbrains.kotlin.serialization.deserialization;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.protobuf.MessageLite;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J&\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000eH&J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000eH&J\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0011H&J6\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\u0016H&J&\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J8\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0005\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\t\u001a\u0004\u0018\u00010\u0016H&J\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\t\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH&J\u001e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\t\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001cH&J\u001d\u0010\u001f\u001a\u00028\u00002\u0006\u0010\t\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u001cH&¢\u0006\u0002\u0010!ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\"À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/serialization/deserialization/AnnotationLoader;", "A", "", "loadClassAnnotations", "", "container", "Lorg/jetbrains/kotlin/serialization/deserialization/ProtoContainer$Class;", "loadCallableAnnotations", "Lorg/jetbrains/kotlin/serialization/deserialization/ProtoContainer;", "proto", "Lorg/jetbrains/kotlin/protobuf/MessageLite;", "kind", "Lorg/jetbrains/kotlin/serialization/deserialization/AnnotatedCallableKind;", "loadPropertyBackingFieldAnnotations", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "loadPropertyDelegateFieldAnnotations", "loadEnumEntryAnnotations", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$EnumEntry;", "loadValueParameterAnnotations", "callableProto", "parameterIndex", "", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$ValueParameter;", "loadExtensionReceiverParameterAnnotations", "loadContextParameterAnnotations", "loadTypeAnnotations", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;", "nameResolver", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "loadTypeParameterAnnotations", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$TypeParameter;", "loadAnnotation", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;", "(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Annotation;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;)Ljava/lang/Object;", "org.jetbrains.kotlin:deserialization.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface AnnotationLoader<A> {
    A loadAnnotation(ProtoBuf.Annotation proto, NameResolver nameResolver);

    List<A> loadCallableAnnotations(ProtoContainer container, MessageLite proto, AnnotatedCallableKind kind);

    List<A> loadClassAnnotations(ProtoContainer.Class container);

    List<A> loadContextParameterAnnotations(ProtoContainer container, MessageLite callableProto, AnnotatedCallableKind kind, int parameterIndex, ProtoBuf.ValueParameter proto);

    List<A> loadEnumEntryAnnotations(ProtoContainer container, ProtoBuf.EnumEntry proto);

    List<A> loadExtensionReceiverParameterAnnotations(ProtoContainer container, MessageLite proto, AnnotatedCallableKind kind);

    List<A> loadPropertyBackingFieldAnnotations(ProtoContainer container, ProtoBuf.Property proto);

    List<A> loadPropertyDelegateFieldAnnotations(ProtoContainer container, ProtoBuf.Property proto);

    List<A> loadTypeAnnotations(ProtoBuf.Type proto, NameResolver nameResolver);

    List<A> loadTypeParameterAnnotations(ProtoBuf.TypeParameter proto, NameResolver nameResolver);

    List<A> loadValueParameterAnnotations(ProtoContainer container, MessageLite callableProto, AnnotatedCallableKind kind, int parameterIndex, ProtoBuf.ValueParameter proto);
}

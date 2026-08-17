package com.intellij.util.xmlb;

import java.lang.reflect.Type;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J-\u0010\t\u001a\u00020\n\"\b\b\u0000\u0010\u000b*\u00020\u00012\u0006\u0010\f\u001a\u0002H\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH'¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J9\u0010\u0015\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u000b*\u00020\u00012\b\u0010\u0016\u001a\u0004\u0018\u00010\u00012\u0006\u0010\f\u001a\u0002H\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH'¢\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0004\u001a\u00020\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001aÀ\u0006\u0001"}, d2 = {"Lcom/intellij/util/xmlb/Binding;", "", "serialize", "", "bean", "parent", "Lorg/jdom/Element;", "filter", "Lcom/intellij/util/xmlb/SerializationFilter;", "isBoundTo", "", "T", "element", "adapter", "Lcom/intellij/util/xmlb/DomAdapter;", "(Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)Z", "init", "originalType", "Ljava/lang/reflect/Type;", "serializer", "Lcom/intellij/util/xmlb/Serializer;", "deserialize", "context", "(Ljava/lang/Object;Ljava/lang/Object;Lcom/intellij/util/xmlb/DomAdapter;)Ljava/lang/Object;", "toJson", "Lkotlinx/serialization/json/JsonElement;", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface Binding {
    <T> Object deserialize(Object context, T element, DomAdapter<T> adapter);

    default void init(Type originalType, Serializer serializer) {
        originalType.getClass();
        serializer.getClass();
    }

    <T> boolean isBoundTo(T element, DomAdapter<T> adapter);
}

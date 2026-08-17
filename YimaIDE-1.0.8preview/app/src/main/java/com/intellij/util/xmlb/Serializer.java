package com.intellij.util.xmlb;

import java.lang.reflect.Type;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016J\u001e\u0010\b\u001a\u0004\u0018\u00010\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\t\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/intellij/util/xmlb/Serializer;", "", "getRootBinding", "Lcom/intellij/util/xmlb/Binding;", "aClass", "Ljava/lang/Class;", "originalType", "Ljava/lang/reflect/Type;", "getBinding", "type", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface Serializer {
    Binding getBinding(Class<?> aClass, Type type);

    Binding getRootBinding(Class<?> aClass, Type originalType);
}

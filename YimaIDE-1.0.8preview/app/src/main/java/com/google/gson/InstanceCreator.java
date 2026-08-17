package com.google.gson;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
public interface InstanceCreator<T> {
    T createInstance(Type type);
}

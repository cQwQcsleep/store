package com.intellij.util.xmlb;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0005\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0006\u001a\u00020\u0007H&¢\u0006\u0002\u0010\bJ\u0017\u0010\t\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00028\u0000H&¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/intellij/util/xmlb/Converter;", "T", "", "<init>", "()V", "fromString", "value", "", "(Ljava/lang/String;)Ljava/lang/Object;", "toString", "(Ljava/lang/Object;)Ljava/lang/String;", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class Converter<T> {
    public abstract T fromString(String value);
}

package com.intellij.util.xmlb;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bw\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u0006\u001a\u00020\u0004H&¢\u0006\u0002\u0010\u0007J\u0017\u0010\b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\tJ\u001f\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\u0004H&¢\u0006\u0002\u0010\u0007J\u0015\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\rJ\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0010J\u001f\u0010\u0011\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0005\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\u0004H&¢\u0006\u0002\u0010\u0012\u0082\u0001\u0002\u0013\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Lcom/intellij/util/xmlb/DomAdapter;", "T", "", "getTextValue", "", "element", "defaultText", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;", "firstElement", "(Ljava/lang/Object;)Ljava/lang/Object;", "getAttributeValue", "name", "getName", "(Ljava/lang/Object;)Ljava/lang/String;", "getChildren", "", "(Ljava/lang/Object;)Ljava/util/List;", "getChild", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", "Lcom/intellij/util/xmlb/JdomAdapter;", "Lcom/intellij/util/xmlb/XmlDomAdapter;", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface DomAdapter<T> {
    T firstElement(T element);

    String getAttributeValue(T element, String name);

    T getChild(T element, String name);

    List<T> getChildren(T element);

    String getName(T element);

    String getTextValue(T element, String defaultText);
}

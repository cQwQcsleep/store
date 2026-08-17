package com.intellij.util.xmlb;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J:\u0010\u0006\u001a\u0004\u0018\u00010\u0007\"\b\b\u0000\u0010\b*\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\b0\rH&J\u0016\u0010\u000e\u001a\u00020\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bH\u0016J\u0016\u0010\u0011\u001a\u00020\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Lcom/intellij/util/xmlb/MultiNodeBinding;", "Lcom/intellij/util/xmlb/Binding;", "isMulti", "", "()Z", "isSurroundWithTag", "deserializeList", "", "T", "currentValue", "elements", "", "adapter", "Lcom/intellij/util/xmlb/DomAdapter;", "deserializeListToJson", "Lkotlinx/serialization/json/JsonElement;", "Lorg/jdom/Element;", "doDeserializeListToJson", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface MultiNodeBinding extends Binding {
    <T> Object deserializeList(Object currentValue, List<? extends T> elements, DomAdapter<T> adapter);

    boolean isMulti();
}

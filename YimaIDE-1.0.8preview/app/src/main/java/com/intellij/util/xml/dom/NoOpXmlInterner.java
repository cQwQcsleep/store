package com.intellij.util.xml.dom;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lcom/intellij/util/xml/dom/NoOpXmlInterner;", "Lcom/intellij/util/xml/dom/XmlInterner;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "name", "", "value", "intellij.platform.util.xmlDom"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class NoOpXmlInterner implements XmlInterner {
    public static final NoOpXmlInterner INSTANCE = new NoOpXmlInterner();

    private NoOpXmlInterner() {
    }

    @Override // com.intellij.util.xml.dom.XmlInterner
    public String name(String value) {
        value.getClass();
        return value;
    }

    @Override // com.intellij.util.xml.dom.XmlInterner
    public String value(String name, String value) {
        name.getClass();
        value.getClass();
        return value;
    }
}

package com.intellij.util.xmlb;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jdom.Element;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0006HÖ\u0001¨\u0006\u0017"}, d2 = {"Lcom/intellij/util/xmlb/JdomAdapter;", "Lcom/intellij/util/xmlb/DomAdapter;", "Lorg/jdom/Element;", "<init>", "()V", "getName", "", "element", "getTextValue", "defaultText", "firstElement", "getAttributeValue", "name", "getChildren", "", "getChild", "equals", "", "other", "", "hashCode", "", "toString", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class JdomAdapter implements DomAdapter<Element> {
    public static final JdomAdapter INSTANCE = new JdomAdapter();

    private JdomAdapter() {
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof JdomAdapter);
    }

    @Override // com.intellij.util.xmlb.DomAdapter
    public Element firstElement(Element element) {
        element.getClass();
        List content = element.getContent();
        content.getClass();
        return (Element) CollectionsKt.firstOrNull(content);
    }

    @Override // com.intellij.util.xmlb.DomAdapter
    public String getAttributeValue(Element element, String name) {
        element.getClass();
        name.getClass();
        return element.getAttributeValue(name);
    }

    @Override // com.intellij.util.xmlb.DomAdapter
    public Element getChild(Element element, String name) {
        element.getClass();
        name.getClass();
        return element.getChild(name);
    }

    @Override // com.intellij.util.xmlb.DomAdapter
    public List<Element> getChildren(Element element) {
        element.getClass();
        List<Element> children = element.getChildren();
        children.getClass();
        return children;
    }

    @Override // com.intellij.util.xmlb.DomAdapter
    public String getName(Element element) {
        element.getClass();
        String name = element.getName();
        name.getClass();
        return name;
    }

    @Override // com.intellij.util.xmlb.DomAdapter
    public String getTextValue(Element element, String defaultText) {
        element.getClass();
        defaultText.getClass();
        String textValue = XmlSerializerImpl.getTextValue(element, defaultText);
        textValue.getClass();
        return textValue;
    }

    public int hashCode() {
        return 317702902;
    }

    public String toString() {
        return "JdomAdapter";
    }
}

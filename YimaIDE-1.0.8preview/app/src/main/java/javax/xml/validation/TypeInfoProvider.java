package javax.xml.validation;

import org.w3c.dom.TypeInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public abstract class TypeInfoProvider {
    public abstract TypeInfo getAttributeTypeInfo(int i);

    public abstract TypeInfo getElementTypeInfo();

    public abstract boolean isIdAttribute(int i);

    public abstract boolean isSpecified(int i);
}

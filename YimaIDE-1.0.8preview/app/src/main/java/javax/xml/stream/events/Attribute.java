package javax.xml.stream.events;

import javax.xml.namespace.QName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Attribute extends XMLEvent {
    String getDTDType();

    QName getName();

    String getValue();

    boolean isSpecified();
}

package com.sun.xml.internal.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NamedEvent extends DummyEvent {
    private QName name;

    public NamedEvent(String str, String str2, String str3) {
        this.name = new QName(str2, str3, str);
    }

    public QName getName() {
        return this.name;
    }

    public String getNamespace() {
        return this.name.getNamespaceURI();
    }

    public String getPrefix() {
        return this.name.getPrefix();
    }

    public String nameAsString() {
        boolean zEquals = "".equals(this.name.getNamespaceURI());
        QName qName = this.name;
        if (zEquals) {
            return qName.getLocalPart();
        }
        String prefix = qName.getPrefix();
        QName qName2 = this.name;
        if (prefix == null) {
            return "['" + qName2.getNamespaceURI() + "']:" + this.name.getLocalPart();
        }
        return "['" + qName2.getNamespaceURI() + "']:" + getPrefix() + ":" + this.name.getLocalPart();
    }

    public void setName(QName qName) {
        this.name = qName;
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(nameAsString());
    }

    public NamedEvent(QName qName) {
        this.name = qName;
    }

    public NamedEvent() {
    }
}

package com.sun.xml.internal.stream.events;

import com.sun.xml.internal.stream.util.ReadOnlyIterator;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EndElementEvent extends DummyEvent implements EndElement {
    List<Namespace> fNamespaces;
    QName fQName;

    public EndElementEvent(QName qName) {
        this.fNamespaces = null;
        this.fQName = qName;
        init();
    }

    public void addNamespace(Namespace namespace) {
        if (namespace != null) {
            this.fNamespaces.add(namespace);
        }
    }

    @Override // javax.xml.stream.events.EndElement
    public QName getName() {
        return this.fQName;
    }

    @Override // javax.xml.stream.events.EndElement
    public Iterator<Namespace> getNamespaces() {
        List<Namespace> list = this.fNamespaces;
        if (list != null) {
            list.iterator();
        }
        return new ReadOnlyIterator();
    }

    public final void init() {
        setEventType(2);
        this.fNamespaces = new ArrayList();
    }

    public String nameAsString() {
        boolean zEquals = "".equals(this.fQName.getNamespaceURI());
        QName qName = this.fQName;
        if (zEquals) {
            return qName.getLocalPart();
        }
        String prefix = qName.getPrefix();
        QName qName2 = this.fQName;
        if (prefix == null) {
            return "['" + qName2.getNamespaceURI() + "']:" + this.fQName.getLocalPart();
        }
        return "['" + qName2.getNamespaceURI() + "']:" + this.fQName.getPrefix() + ":" + this.fQName.getLocalPart();
    }

    public void setName(QName qName) {
        this.fQName = qName;
    }

    public String toString() {
        return ("</" + nameAsString()).concat(">");
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write("</");
        String prefix = this.fQName.getPrefix();
        if (prefix != null && prefix.length() > 0) {
            writer.write(prefix);
            writer.write(58);
        }
        writer.write(this.fQName.getLocalPart());
        writer.write(62);
    }

    public EndElementEvent(String str, String str2, String str3) {
        this(new QName(str2, str3, str));
    }

    public EndElementEvent() {
        this.fNamespaces = null;
        init();
    }
}

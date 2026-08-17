package com.sun.xml.internal.stream.events;

import com.sun.xml.internal.stream.util.ReadOnlyIterator;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StartElementEvent extends DummyEvent implements StartElement {
    private Map<QName, Attribute> fAttributes;
    private NamespaceContext fNamespaceContext;
    private List<Namespace> fNamespaces;
    private QName fQName;

    public StartElementEvent(StartElement startElement) {
        this(startElement.getName());
        addAttributes(startElement.getAttributes());
        addNamespaceAttributes(startElement.getNamespaces());
    }

    public void addAttribute(Attribute attribute) {
        if (attribute.isNamespace()) {
            this.fNamespaces.add((Namespace) attribute);
        } else {
            this.fAttributes.put(attribute.getName(), attribute);
        }
    }

    public final void addAttributes(Iterator<? extends Attribute> it) {
        if (it == null) {
            return;
        }
        while (it.hasNext()) {
            Attribute next = it.next();
            this.fAttributes.put(next.getName(), next);
        }
    }

    public void addNamespaceAttribute(Namespace namespace) {
        if (namespace == null) {
            return;
        }
        this.fNamespaces.add(namespace);
    }

    public final void addNamespaceAttributes(Iterator<? extends Namespace> it) {
        if (it == null) {
            return;
        }
        while (it.hasNext()) {
            this.fNamespaces.add(it.next());
        }
    }

    public Attribute getAttributeByName(QName qName) {
        if (qName == null) {
            return null;
        }
        return this.fAttributes.get(qName);
    }

    public Iterator<Attribute> getAttributes() {
        Map<QName, Attribute> map = this.fAttributes;
        return map != null ? new ReadOnlyIterator(map.values().iterator()) : new ReadOnlyIterator();
    }

    public QName getName() {
        return this.fQName;
    }

    public String getNamespace() {
        return this.fQName.getNamespaceURI();
    }

    public NamespaceContext getNamespaceContext() {
        return this.fNamespaceContext;
    }

    public String getNamespaceURI(String str) {
        if (getNamespace() != null && this.fQName.getPrefix().equals(str)) {
            return getNamespace();
        }
        NamespaceContext namespaceContext = this.fNamespaceContext;
        if (namespaceContext != null) {
            return namespaceContext.getNamespaceURI(str);
        }
        return null;
    }

    public Iterator<Namespace> getNamespaces() {
        List<Namespace> list = this.fNamespaces;
        return list != null ? new ReadOnlyIterator(list.iterator()) : new ReadOnlyIterator();
    }

    public final void init() {
        setEventType(1);
        this.fAttributes = new HashMap();
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

    public void setNamespaceContext(NamespaceContext namespaceContext) {
        this.fNamespaceContext = namespaceContext;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(nameAsString());
        if (this.fAttributes != null) {
            Iterator<Attribute> attributes = getAttributes();
            while (attributes.hasNext()) {
                Attribute next = attributes.next();
                sb.append(" ");
                sb.append(next.toString());
            }
        }
        List<Namespace> list = this.fNamespaces;
        if (list != null) {
            for (Namespace namespace : list) {
                sb.append(" ");
                sb.append(namespace.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(toString());
    }

    public StartElementEvent(QName qName) {
        this.fNamespaceContext = null;
        this.fQName = qName;
        init();
    }

    public StartElementEvent(String str, String str2, String str3) {
        this(new QName(str2, str3, str));
    }
}

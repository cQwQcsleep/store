package com.sun.xml.internal.stream.events;

import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.EntityDeclaration;
import jdk.xml.internal.JdkXmlUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EntityDeclarationImpl extends DummyEvent implements EntityDeclaration {
    private String fEntityName;
    private String fNotationName;
    private String fReplacementText;
    private XMLResourceIdentifier fXMLResourceIdentifier;

    public EntityDeclarationImpl(String str, String str2, XMLResourceIdentifier xMLResourceIdentifier) {
        init();
        this.fEntityName = str;
        this.fReplacementText = str2;
        this.fXMLResourceIdentifier = xMLResourceIdentifier;
    }

    public String getBaseURI() {
        XMLResourceIdentifier xMLResourceIdentifier = this.fXMLResourceIdentifier;
        if (xMLResourceIdentifier != null) {
            return xMLResourceIdentifier.getBaseSystemId();
        }
        return null;
    }

    public String getEntityName() {
        return this.fEntityName;
    }

    public String getName() {
        return this.fEntityName;
    }

    public String getNotationName() {
        return this.fNotationName;
    }

    public String getPublicId() {
        XMLResourceIdentifier xMLResourceIdentifier = this.fXMLResourceIdentifier;
        if (xMLResourceIdentifier != null) {
            return xMLResourceIdentifier.getPublicId();
        }
        return null;
    }

    public String getReplacementText() {
        return this.fReplacementText;
    }

    public String getSystemId() {
        XMLResourceIdentifier xMLResourceIdentifier = this.fXMLResourceIdentifier;
        if (xMLResourceIdentifier != null) {
            return xMLResourceIdentifier.getLiteralSystemId();
        }
        return null;
    }

    public XMLResourceIdentifier getXMLResourceIdentifier() {
        return this.fXMLResourceIdentifier;
    }

    public void init() {
        setEventType(15);
    }

    public void setEntityName(String str) {
        this.fEntityName = str;
    }

    public void setEntityReplacementText(String str) {
        this.fReplacementText = str;
    }

    public void setNotationName(String str) {
        this.fNotationName = str;
    }

    public void setXMLResourceIdentifier(XMLResourceIdentifier xMLResourceIdentifier) {
        this.fXMLResourceIdentifier = xMLResourceIdentifier;
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write("<!ENTITY ");
        writer.write(this.fEntityName);
        if (this.fReplacementText != null) {
            writer.write(" \"");
            charEncode(writer, this.fReplacementText);
            writer.write("\"");
        } else {
            writer.write(JdkXmlUtils.getDTDExternalDecl(getPublicId(), getSystemId()));
        }
        if (this.fNotationName != null) {
            writer.write(" NDATA ");
            writer.write(this.fNotationName);
        }
        writer.write(">");
    }

    public EntityDeclarationImpl(String str, String str2) {
        this(str, str2, null);
    }

    public EntityDeclarationImpl() {
        init();
    }
}

package com.sun.xml.internal.stream.events;

import com.sun.xml.internal.stream.dtd.nonvalidating.XMLNotationDecl;
import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.NotationDeclaration;
import jdk.xml.internal.JdkXmlUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NotationDeclarationImpl extends DummyEvent implements NotationDeclaration {
    String fName;
    String fPublicId;
    String fSystemId;

    public NotationDeclarationImpl(XMLNotationDecl xMLNotationDecl) {
        this.fName = null;
        this.fPublicId = null;
        this.fSystemId = null;
        this.fName = xMLNotationDecl.name;
        this.fPublicId = xMLNotationDecl.publicId;
        this.fSystemId = xMLNotationDecl.systemId;
        setEventType(14);
    }

    public String getName() {
        return this.fName;
    }

    public String getPublicId() {
        return this.fPublicId;
    }

    public String getSystemId() {
        return this.fSystemId;
    }

    public void setName(String str) {
        this.fName = str;
    }

    public void setPublicId(String str) {
        this.fPublicId = str;
    }

    public void setSystemId(String str) {
        this.fSystemId = str;
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write("<!NOTATION ");
        writer.write(getName());
        writer.write(JdkXmlUtils.getDTDExternalDecl(this.fPublicId, this.fSystemId));
        writer.write(62);
    }

    public NotationDeclarationImpl(String str, String str2, String str3) {
        this.fName = str;
        this.fPublicId = str2;
        this.fSystemId = str3;
        setEventType(14);
    }

    public NotationDeclarationImpl() {
        this.fName = null;
        this.fPublicId = null;
        this.fSystemId = null;
        setEventType(14);
    }
}

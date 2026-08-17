package com.sun.xml.internal.stream.events;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.NotationDeclaration;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DTDEvent extends DummyEvent implements DTD {
    private String fDoctypeDeclaration;
    private List<EntityDeclaration> fEntities;
    private List<NotationDeclaration> fNotations;

    public DTDEvent(String str) {
        init();
        this.fDoctypeDeclaration = str;
    }

    public String getDocumentTypeDeclaration() {
        return this.fDoctypeDeclaration;
    }

    public List<EntityDeclaration> getEntities() {
        return this.fEntities;
    }

    public List<NotationDeclaration> getNotations() {
        return this.fNotations;
    }

    public Object getProcessedDTD() {
        return null;
    }

    public final void init() {
        setEventType(11);
    }

    public void setDocumentTypeDeclaration(String str) {
        this.fDoctypeDeclaration = str;
    }

    public void setEntities(List<EntityDeclaration> list) {
        this.fEntities = list;
    }

    public void setNotations(List<NotationDeclaration> list) {
        this.fNotations = list;
    }

    public String toString() {
        return this.fDoctypeDeclaration;
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write(this.fDoctypeDeclaration);
    }

    public DTDEvent() {
        init();
    }
}

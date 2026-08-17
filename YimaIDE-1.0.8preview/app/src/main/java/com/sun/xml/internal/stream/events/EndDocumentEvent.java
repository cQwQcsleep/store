package com.sun.xml.internal.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.EndDocument;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class EndDocumentEvent extends DummyEvent implements EndDocument {
    public EndDocumentEvent() {
        init();
    }

    public void init() {
        setEventType(8);
    }

    public String toString() {
        return "ENDDOCUMENT";
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
    }
}

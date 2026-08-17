package com.sun.xml.internal.stream.events;

import java.io.IOException;
import java.io.Writer;
import javax.xml.stream.events.Comment;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CommentEvent extends DummyEvent implements Comment {
    private String fText;

    public CommentEvent(String str) {
        init();
        this.fText = str;
    }

    public String getText() {
        return this.fText;
    }

    public void init() {
        setEventType(5);
    }

    public String toString() {
        return "<!--" + getText() + "-->";
    }

    @Override // com.sun.xml.internal.stream.events.DummyEvent
    public void writeAsEncodedUnicodeEx(Writer writer) throws IOException {
        writer.write("<!--" + getText() + "-->");
    }

    public CommentEvent() {
        init();
    }
}

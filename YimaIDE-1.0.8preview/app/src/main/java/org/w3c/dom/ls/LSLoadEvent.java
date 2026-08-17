package org.w3c.dom.ls;

import org.w3c.dom.Document;
import org.w3c.dom.events.Event;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LSLoadEvent extends Event {
    LSInput getInput();

    Document getNewDocument();
}

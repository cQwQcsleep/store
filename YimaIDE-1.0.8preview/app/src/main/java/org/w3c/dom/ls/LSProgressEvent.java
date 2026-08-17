package org.w3c.dom.ls;

import org.w3c.dom.events.Event;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface LSProgressEvent extends Event {
    LSInput getInput();

    int getPosition();

    int getTotalSize();
}

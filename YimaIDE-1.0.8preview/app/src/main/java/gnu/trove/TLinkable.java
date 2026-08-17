package gnu.trove;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface TLinkable extends Serializable {
    TLinkable getNext();

    TLinkable getPrevious();

    void setNext(TLinkable tLinkable);

    void setPrevious(TLinkable tLinkable);
}

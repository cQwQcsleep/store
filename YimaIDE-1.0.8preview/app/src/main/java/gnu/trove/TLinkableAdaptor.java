package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TLinkableAdaptor implements TLinkable {
    TLinkable _next;
    TLinkable _previous;

    @Override // gnu.trove.TLinkable
    public TLinkable getNext() {
        return this._next;
    }

    @Override // gnu.trove.TLinkable
    public TLinkable getPrevious() {
        return this._previous;
    }

    @Override // gnu.trove.TLinkable
    public void setNext(TLinkable tLinkable) {
        this._next = tLinkable;
    }

    @Override // gnu.trove.TLinkable
    public void setPrevious(TLinkable tLinkable) {
        this._previous = tLinkable;
    }
}

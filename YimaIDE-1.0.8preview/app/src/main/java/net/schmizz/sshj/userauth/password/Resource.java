package net.schmizz.sshj.userauth.password;

import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class Resource<H> {
    private final H detail;

    public Resource(H h) {
        this.detail = h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Resource)) {
            return false;
        }
        H h = this.detail;
        H h2 = ((Resource) obj).detail;
        if (h == null) {
            return h2 == null;
        }
        return h.equals(h2);
    }

    public H getDetail() {
        return this.detail;
    }

    public abstract Reader getReader() throws IOException;

    public int hashCode() {
        H h = this.detail;
        if (h != null) {
            return h.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + this.detail;
    }
}

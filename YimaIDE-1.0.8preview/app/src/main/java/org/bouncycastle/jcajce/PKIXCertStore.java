package org.bouncycastle.jcajce;

import java.security.cert.Certificate;
import java.util.Collection;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface PKIXCertStore<T extends Certificate> extends Store<T> {
    Collection<T> getMatches(Selector<T> selector) throws StoreException;
}

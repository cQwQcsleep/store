package org.bouncycastle.crypto.constraints;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import org.bouncycastle.crypto.CryptoServicesConstraints;
import org.bouncycastle.util.Strings;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class ServicesConstraint implements CryptoServicesConstraints {
    protected static final Logger LOG = Logger.getLogger(ServicesConstraint.class.getName());
    private final Set<String> exceptions;

    public ServicesConstraint(Set<String> set) {
        if (set.isEmpty()) {
            this.exceptions = Collections.EMPTY_SET;
            return;
        }
        this.exceptions = new HashSet(set.size());
        Iterator<String> it = set.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            Set<String> set2 = this.exceptions;
            if (!zHasNext) {
                Utils.addAliases(set2);
                return;
            }
            set2.add(Strings.toUpperCase(it.next().toString()));
        }
    }

    public boolean isException(String str) {
        if (this.exceptions.isEmpty()) {
            return false;
        }
        return this.exceptions.contains(Strings.toUpperCase(str));
    }
}

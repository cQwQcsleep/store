package com.intellij;

import com.intellij.psi.impl.source.tree.ChildRole;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u0002H\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/intellij/ResourceBundleWithParentEnumeration;", "Ljava/util/Enumeration;", "", "set", "", "enumeration", "<init>", "(Ljava/util/Set;Ljava/util/Enumeration;)V", "iterator", "", "next", "hasMoreElements", "", "nextElement", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
final class ResourceBundleWithParentEnumeration implements Enumeration<String> {
    private Enumeration<String> enumeration;
    private Iterator<String> iterator;
    private String next;
    private Set<String> set;

    public ResourceBundleWithParentEnumeration(Set<String> set, Enumeration<String> enumeration) {
        set.getClass();
        enumeration.getClass();
        this.set = set;
        this.enumeration = enumeration;
        this.iterator = set.iterator();
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        if (this.next == null) {
            if (this.iterator.hasNext()) {
                this.next = this.iterator.next();
            } else {
                while (this.next == null && this.enumeration.hasMoreElements()) {
                    String strNextElement = this.enumeration.nextElement();
                    this.next = strNextElement;
                    if (this.set.contains(strNextElement)) {
                        this.next = null;
                    }
                }
            }
        }
        return this.next != null;
    }

    @Override // java.util.Enumeration
    public String nextElement() {
        if (!hasMoreElements()) {
            z0e.a();
            return null;
        }
        String str = this.next;
        this.next = null;
        return str;
    }
}

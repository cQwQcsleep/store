package org.eclipse.tm4e.core.internal.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
final class TMParserPropertyPath extends ArrayList<Object> implements TMParser.PropertyPath {
    private static final long serialVersionUID = 1;

    @Override // org.eclipse.tm4e.core.internal.parser.TMParser.PropertyPath
    public int depth() {
        return size();
    }

    @Override // org.eclipse.tm4e.core.internal.parser.TMParser.PropertyPath
    public Object first() {
        if (!isEmpty()) {
            return get(0);
        }
        z0e.a();
        return null;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<Object> iterator() {
        final Iterator it = super.iterator();
        return new Iterator<Object>() { // from class: org.eclipse.tm4e.core.internal.parser.TMParserPropertyPath.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public Object next() {
                return it.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // org.eclipse.tm4e.core.internal.parser.TMParser.PropertyPath
    public Object last() {
        if (!isEmpty()) {
            return get(size() - 1);
        }
        z0e.a();
        return null;
    }

    public Object removeLastElement() {
        return remove(size() - 1);
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return "/" + ((String) stream().map(new ulf()).collect(Collectors.joining("/")));
    }
}

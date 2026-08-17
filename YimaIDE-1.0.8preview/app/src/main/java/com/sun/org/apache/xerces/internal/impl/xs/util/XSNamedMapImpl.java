package com.sun.org.apache.xerces.internal.impl.xs.util;

import com.sun.org.apache.xerces.internal.util.SymbolHash;
import com.sun.org.apache.xerces.internal.xs.XSNamedMap;
import com.sun.org.apache.xerces.internal.xs.XSObject;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XSNamedMapImpl extends AbstractMap<QName, XSObject> implements XSNamedMap {
    public static final XSNamedMapImpl EMPTY_MAP = new XSNamedMapImpl(new XSObject[0], 0);
    XSObject[] fArray;
    private Set<Map.Entry<QName, XSObject>> fEntrySet;
    int fLength;
    final SymbolHash[] fMaps;
    final int fNSNum;
    final String[] fNamespaces;

    public XSNamedMapImpl(XSObject[] xSObjectArr, int i) {
        this.fArray = null;
        this.fLength = -1;
        this.fEntrySet = null;
        if (i == 0) {
            this.fNamespaces = null;
            this.fMaps = null;
            this.fNSNum = 0;
            this.fArray = xSObjectArr;
            this.fLength = 0;
            return;
        }
        this.fNamespaces = new String[]{xSObjectArr[0].getNamespace()};
        this.fMaps = null;
        this.fNSNum = 1;
        this.fArray = xSObjectArr;
        this.fLength = i;
    }

    public static boolean isEqual(String str, String str2) {
        if (str != null) {
            return str.equals(str2);
        }
        return str2 == null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public synchronized Set<Map.Entry<QName, XSObject>> entrySet() {
        try {
            if (this.fEntrySet == null) {
                final int length = getLength();
                final XSNamedMapEntry[] xSNamedMapEntryArr = new XSNamedMapEntry[length];
                for (int i = 0; i < length; i++) {
                    XSObject xSObjectItem = item(i);
                    xSNamedMapEntryArr[i] = new XSNamedMapEntry(new QName(xSObjectItem.getNamespace(), xSObjectItem.getName()), xSObjectItem);
                }
                this.fEntrySet = new AbstractSet<Map.Entry<QName, XSObject>>() { // from class: com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMapImpl.1
                    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                    public Iterator<Map.Entry<QName, XSObject>> iterator() {
                        return new Iterator<Map.Entry<QName, XSObject>>() { // from class: com.sun.org.apache.xerces.internal.impl.xs.util.XSNamedMapImpl.1.1
                            private int index = 0;

                            @Override // java.util.Iterator
                            public boolean hasNext() {
                                return this.index < length;
                            }

                            @Override // java.util.Iterator
                            public Map.Entry<QName, XSObject> next() {
                                int i2 = this.index;
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                if (i2 >= length) {
                                    z0e.a();
                                    return null;
                                }
                                XSNamedMapEntry[] xSNamedMapEntryArr2 = xSNamedMapEntryArr;
                                this.index = i2 + 1;
                                return xSNamedMapEntryArr2[i2];
                            }

                            @Override // java.util.Iterator
                            public void remove() {
                                throw new UnsupportedOperationException();
                            }
                        };
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                    public int size() {
                        return length;
                    }
                };
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.fEntrySet;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.AbstractMap, java.util.Map
    public XSObject get(Object obj) {
        if (!(obj instanceof QName)) {
            return null;
        }
        QName qName = (QName) obj;
        String namespaceURI = qName.getNamespaceURI();
        return itemByName("".equals(namespaceURI) ? null : namespaceURI, qName.getLocalPart());
    }

    public synchronized int getLength() {
        try {
            if (this.fLength == -1) {
                this.fLength = 0;
                for (int i = 0; i < this.fNSNum; i++) {
                    this.fLength += this.fMaps[i].getLength();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.fLength;
    }

    public synchronized XSObject item(int i) {
        try {
            if (this.fArray == null) {
                getLength();
                this.fArray = new XSObject[this.fLength];
                int values = 0;
                for (int i2 = 0; i2 < this.fNSNum; i2++) {
                    values += this.fMaps[i2].getValues(this.fArray, values);
                }
            }
            if (i >= 0 && i < this.fLength) {
                return this.fArray[i];
            }
            return null;
        } catch (Throwable th) {
            throw th;
        }
    }

    public XSObject itemByName(String str, String str2) {
        for (int i = 0; i < this.fNSNum; i++) {
            if (isEqual(str, this.fNamespaces[i])) {
                SymbolHash[] symbolHashArr = this.fMaps;
                if (symbolHashArr != null) {
                    return (XSObject) symbolHashArr[i].get(str2);
                }
                for (int i2 = 0; i2 < this.fLength; i2++) {
                    XSObject xSObject = this.fArray[i2];
                    if (xSObject.getName().equals(str2)) {
                        return xSObject;
                    }
                }
                return null;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return getLength();
    }

    public static final class XSNamedMapEntry implements Map.Entry<QName, XSObject> {
        private final QName key;
        private final XSObject value;

        public XSNamedMapEntry(QName qName, XSObject xSObject) {
            this.key = qName;
            this.value = xSObject;
        }

        public boolean equals(XSNamedMapEntry xSNamedMapEntry) {
            if (xSNamedMapEntry != null) {
                QName key = xSNamedMapEntry.getKey();
                XSObject value = xSNamedMapEntry.getValue();
                QName qName = this.key;
                if (qName != null ? qName.equals(key) : key == null) {
                    XSObject xSObject = this.value;
                    if (xSObject == null) {
                        if (value == null) {
                            return true;
                        }
                    } else if (xSObject.equals(value)) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            QName qName = this.key;
            int iHashCode = qName == null ? 0 : qName.hashCode();
            XSObject xSObject = this.value;
            return iHashCode ^ (xSObject != null ? xSObject.hashCode() : 0);
        }

        public String toString() {
            return String.valueOf(this.key) + '=' + String.valueOf(this.value);
        }

        @Override // java.util.Map.Entry
        public QName getKey() {
            return this.key;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Map.Entry
        public XSObject getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public XSObject setValue(XSObject xSObject) {
            throw new UnsupportedOperationException();
        }
    }

    public XSNamedMapImpl(String[] strArr, SymbolHash[] symbolHashArr, int i) {
        this.fArray = null;
        this.fLength = -1;
        this.fEntrySet = null;
        this.fNamespaces = strArr;
        this.fMaps = symbolHashArr;
        this.fNSNum = i;
    }

    public XSNamedMapImpl(String str, SymbolHash symbolHash) {
        this.fArray = null;
        this.fLength = -1;
        this.fEntrySet = null;
        this.fNamespaces = new String[]{str};
        this.fMaps = new SymbolHash[]{symbolHash};
        this.fNSNum = 1;
    }
}

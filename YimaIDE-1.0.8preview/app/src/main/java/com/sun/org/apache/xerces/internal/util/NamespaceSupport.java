package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NamespaceSupport implements NamespaceContext {
    protected int[] fContext;
    protected int fCurrentContext;
    protected String[] fNamespace;
    protected int fNamespaceSize;
    protected String[] fPrefixes;

    public final class IteratorPrefixes implements Iterator<String> {
        private int counter = 0;
        private String[] prefixes;
        private int size;

        public IteratorPrefixes(String[] strArr, int i) {
            this.prefixes = strArr;
            this.size = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.counter < this.size;
        }

        @Override // java.util.Iterator
        public String next() {
            int i = this.counter;
            if (i >= this.size) {
                hb9.a("Illegal access to Namespace prefixes enumeration.");
                return null;
            }
            String[] strArr = NamespaceSupport.this.fPrefixes;
            this.counter = i + 1;
            return strArr[i];
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.size; i++) {
                sb.append(this.prefixes[i]);
                sb.append(" ");
            }
            return sb.toString();
        }
    }

    public final class Prefixes implements Enumeration<String> {
        private int counter = 0;
        private String[] prefixes;
        private int size;

        public Prefixes(String[] strArr, int i) {
            this.prefixes = strArr;
            this.size = i;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.counter < this.size;
        }

        @Override // java.util.Enumeration
        public String nextElement() {
            int i = this.counter;
            if (i >= this.size) {
                hb9.a("Illegal access to Namespace prefixes enumeration.");
                return null;
            }
            String[] strArr = NamespaceSupport.this.fPrefixes;
            this.counter = i + 1;
            return strArr[i];
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.size; i++) {
                sb.append(this.prefixes[i]);
                sb.append(" ");
            }
            return sb.toString();
        }
    }

    public NamespaceSupport(NamespaceContext namespaceContext) {
        this.fNamespace = new String[32];
        this.fContext = new int[8];
        this.fPrefixes = new String[16];
        pushContext();
        Enumeration<String> allPrefixes = namespaceContext.getAllPrefixes();
        while (allPrefixes.hasMoreElements()) {
            String strNextElement = allPrefixes.nextElement();
            declarePrefix(strNextElement, namespaceContext.getURI(strNextElement));
        }
    }

    public boolean containsPrefix(String str) {
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            if (this.fNamespace[i - 2] == str) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPrefixInCurrentContext(String str) {
        for (int i = this.fContext[this.fCurrentContext]; i < this.fNamespaceSize; i += 2) {
            if (this.fNamespace[i] == str) {
                return true;
            }
        }
        return false;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public boolean declarePrefix(String str, String str2) {
        if (str == XMLSymbols.PREFIX_XML || str == XMLSymbols.PREFIX_XMLNS) {
            return false;
        }
        for (int i = this.fNamespaceSize; i > this.fContext[this.fCurrentContext]; i -= 2) {
            String[] strArr = this.fNamespace;
            if (strArr[i - 2] == str) {
                strArr[i - 1] = str2;
                return true;
            }
        }
        int i2 = this.fNamespaceSize;
        String[] strArr2 = this.fNamespace;
        if (i2 == strArr2.length) {
            String[] strArr3 = new String[i2 * 2];
            System.arraycopy(strArr2, 0, strArr3, 0, i2);
            this.fNamespace = strArr3;
        }
        String[] strArr4 = this.fNamespace;
        int i3 = this.fNamespaceSize;
        int i4 = i3 + 1;
        this.fNamespaceSize = i4;
        strArr4[i3] = str;
        this.fNamespaceSize = i3 + 2;
        strArr4[i4] = str2;
        return true;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public Enumeration<String> getAllPrefixes() {
        if (this.fPrefixes.length < this.fNamespace.length / 2) {
            this.fPrefixes = new String[this.fNamespaceSize];
        }
        int i = 0;
        int i2 = 2;
        while (i2 < this.fNamespaceSize - 2) {
            i2 += 2;
            String str = this.fNamespace[i2];
            int i3 = 0;
            while (true) {
                String[] strArr = this.fPrefixes;
                if (i3 >= i) {
                    strArr[i] = str;
                    i++;
                    break;
                }
                if (strArr[i3] == str) {
                    break;
                }
                i3++;
            }
        }
        return new Prefixes(this.fPrefixes, i);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public String getDeclaredPrefixAt(int i) {
        return this.fNamespace[this.fContext[this.fCurrentContext] + (i * 2)];
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public int getDeclaredPrefixCount() {
        return (this.fNamespaceSize - this.fContext[this.fCurrentContext]) / 2;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public String getPrefix(String str) {
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            String[] strArr = this.fNamespace;
            if (strArr[i - 1] == str) {
                int i2 = i - 2;
                if (getURI(strArr[i2]) == str) {
                    return this.fNamespace[i2];
                }
            }
        }
        return null;
    }

    public Iterator<String> getPrefixes() {
        if (this.fPrefixes.length < this.fNamespace.length / 2) {
            this.fPrefixes = new String[this.fNamespaceSize];
        }
        int i = 0;
        int i2 = 2;
        while (i2 < this.fNamespaceSize - 2) {
            i2 += 2;
            String str = this.fNamespace[i2];
            int i3 = 0;
            while (true) {
                String[] strArr = this.fPrefixes;
                if (i3 >= i) {
                    strArr[i] = str;
                    i++;
                    break;
                }
                if (strArr[i3] == str) {
                    break;
                }
                i3++;
            }
        }
        return new IteratorPrefixes(this.fPrefixes, i);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public String getURI(String str) {
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            String[] strArr = this.fNamespace;
            if (strArr[i - 2] == str) {
                return strArr[i - 1];
            }
        }
        return null;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public void popContext() {
        int[] iArr = this.fContext;
        int i = this.fCurrentContext;
        this.fCurrentContext = i - 1;
        this.fNamespaceSize = iArr[i];
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public void pushContext() {
        int i = this.fCurrentContext + 1;
        int[] iArr = this.fContext;
        if (i == iArr.length) {
            int[] iArr2 = new int[iArr.length * 2];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.fContext = iArr2;
        }
        int[] iArr3 = this.fContext;
        int i2 = this.fCurrentContext + 1;
        this.fCurrentContext = i2;
        iArr3[i2] = this.fNamespaceSize;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.NamespaceContext
    public void reset() {
        this.fCurrentContext = 0;
        String[] strArr = this.fNamespace;
        this.fNamespaceSize = 1;
        strArr[0] = XMLSymbols.PREFIX_XML;
        int i = 1 + 1;
        this.fNamespaceSize = i;
        strArr[1] = NamespaceContext.XML_URI;
        int i2 = i + 1;
        this.fNamespaceSize = i2;
        strArr[i] = XMLSymbols.PREFIX_XMLNS;
        int i3 = i + 2;
        this.fNamespaceSize = i3;
        strArr[i2] = NamespaceContext.XMLNS_URI;
        this.fContext[0] = i3;
    }

    public NamespaceSupport() {
        this.fNamespace = new String[32];
        this.fContext = new int[8];
        this.fPrefixes = new String[16];
    }

    public List<String> getPrefixes(String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            String[] strArr = this.fNamespace;
            if (strArr[i - 1] == str) {
                int i2 = i - 2;
                if (!arrayList.contains(strArr[i2])) {
                    arrayList.add(this.fNamespace[i2]);
                }
            }
        }
        return arrayList;
    }
}

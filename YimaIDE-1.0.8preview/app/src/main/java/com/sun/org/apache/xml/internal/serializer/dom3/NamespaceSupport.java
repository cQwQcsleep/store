package com.sun.org.apache.xml.internal.serializer.dom3;

import java.util.Enumeration;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class NamespaceSupport {
    protected int fCurrentContext;
    protected int fNamespaceSize;
    static final String PREFIX_XML = "xml".intern();
    static final String PREFIX_XMLNS = "xmlns".intern();
    public static final String XML_URI = "http://www.w3.org/XML/1998/namespace".intern();
    public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/".intern();
    protected String[] fNamespace = new String[32];
    protected int[] fContext = new int[8];
    protected String[] fPrefixes = new String[16];

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

    public boolean declarePrefix(String str, String str2) {
        if (str == PREFIX_XML || str == PREFIX_XMLNS) {
            return false;
        }
        for (int i = this.fNamespaceSize; i > this.fContext[this.fCurrentContext]; i -= 2) {
            if (this.fNamespace[i - 2].equals(str)) {
                this.fNamespace[i - 1] = str2;
                return true;
            }
        }
        int i2 = this.fNamespaceSize;
        String[] strArr = this.fNamespace;
        if (i2 == strArr.length) {
            String[] strArr2 = new String[i2 * 2];
            System.arraycopy(strArr, 0, strArr2, 0, i2);
            this.fNamespace = strArr2;
        }
        String[] strArr3 = this.fNamespace;
        int i3 = this.fNamespaceSize;
        int i4 = i3 + 1;
        this.fNamespaceSize = i4;
        strArr3[i3] = str;
        this.fNamespaceSize = i3 + 2;
        strArr3[i4] = str2;
        return true;
    }

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

    public String getDeclaredPrefixAt(int i) {
        return this.fNamespace[this.fContext[this.fCurrentContext] + (i * 2)];
    }

    public int getDeclaredPrefixCount() {
        return (this.fNamespaceSize - this.fContext[this.fCurrentContext]) / 2;
    }

    public String getPrefix(String str) {
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            if (this.fNamespace[i - 1].equals(str)) {
                int i2 = i - 2;
                if (getURI(this.fNamespace[i2]).equals(str)) {
                    return this.fNamespace[i2];
                }
            }
        }
        return null;
    }

    public String getURI(String str) {
        for (int i = this.fNamespaceSize; i > 0; i -= 2) {
            if (this.fNamespace[i - 2].equals(str)) {
                return this.fNamespace[i - 1];
            }
        }
        return null;
    }

    public void popContext() {
        int[] iArr = this.fContext;
        int i = this.fCurrentContext;
        this.fCurrentContext = i - 1;
        this.fNamespaceSize = iArr[i];
    }

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

    public void reset() {
        this.fNamespaceSize = 0;
        this.fCurrentContext = 0;
        this.fContext[0] = 0;
        String[] strArr = this.fNamespace;
        this.fNamespaceSize = 1;
        strArr[0] = PREFIX_XML;
        int i = 1 + 1;
        this.fNamespaceSize = i;
        strArr[1] = XML_URI;
        int i2 = i + 1;
        this.fNamespaceSize = i2;
        strArr[i] = PREFIX_XMLNS;
        this.fNamespaceSize = i + 2;
        strArr[i2] = XMLNS_URI;
        this.fCurrentContext = 1;
    }
}

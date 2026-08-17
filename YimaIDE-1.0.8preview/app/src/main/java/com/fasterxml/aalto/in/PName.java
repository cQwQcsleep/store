package com.fasterxml.aalto.in;

import javax.xml.namespace.QName;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class PName {
    protected final String _localName;
    protected NsBinding _namespaceBinding = null;
    protected final String _prefix;
    protected final String _prefixedName;

    public PName(String str, String str2, String str3) {
        this._prefixedName = str;
        this._prefix = str2;
        this._localName = str3;
    }

    public final boolean boundEquals(String str, String str2) {
        if (!this._localName.equals(str2)) {
            return false;
        }
        String nsUri = getNsUri();
        if (str == null || str.length() == 0) {
            return nsUri == null;
        }
        return str.equals(nsUri);
    }

    public final int boundHashCode() {
        return this._localName.hashCode();
    }

    public final QName constructQName(NsBinding nsBinding) {
        String str;
        String str2 = this._prefix;
        String str3 = XmlPullParser.NO_NAMESPACE;
        if (str2 == null) {
            str2 = XmlPullParser.NO_NAMESPACE;
        }
        NsBinding nsBinding2 = this._namespaceBinding;
        if (nsBinding2 != null && (str = nsBinding2.mURI) != null) {
            return new QName(str, this._localName, str2);
        }
        String str4 = nsBinding.mURI;
        if (str4 != null) {
            str3 = str4;
        }
        return new QName(str3, this._localName, str2);
    }

    public abstract PName createBoundName(NsBinding nsBinding);

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PName)) {
            return false;
        }
        PName pName = (PName) obj;
        return pName._prefix == this._prefix && pName._localName == this._localName;
    }

    public final String getLocalName() {
        return this._localName;
    }

    public final String getNsUri() {
        NsBinding nsBinding = this._namespaceBinding;
        if (nsBinding == null) {
            return null;
        }
        return nsBinding.mURI;
    }

    public final String getPrefix() {
        return this._prefix;
    }

    public final String getPrefixedName() {
        return this._prefixedName;
    }

    public abstract int getQuad(int i);

    public int hashCode() {
        return this._prefixedName.hashCode();
    }

    public final boolean isBound() {
        NsBinding nsBinding = this._namespaceBinding;
        return nsBinding == null || nsBinding.mURI != null;
    }

    public abstract int sizeInQuads();

    public final String toString() {
        return this._prefixedName;
    }

    public final boolean unboundEquals(PName pName) {
        return pName._prefixedName == this._prefixedName;
    }

    public final int unboundHashCode() {
        return this._prefixedName.hashCode();
    }

    public static int boundHashCode(String str, String str2) {
        return str2.hashCode();
    }

    public final boolean boundEquals(PName pName) {
        return pName != null && pName._localName == this._localName && pName.getNsUri() == getNsUri();
    }

    public final QName constructQName() {
        String str = this._prefix;
        NsBinding nsBinding = this._namespaceBinding;
        String str2 = nsBinding == null ? null : nsBinding.mURI;
        if (str2 == null) {
            str2 = XmlPullParser.NO_NAMESPACE;
        }
        String str3 = this._localName;
        if (str == null) {
            str = XmlPullParser.NO_NAMESPACE;
        }
        return new QName(str2, str3, str);
    }
}

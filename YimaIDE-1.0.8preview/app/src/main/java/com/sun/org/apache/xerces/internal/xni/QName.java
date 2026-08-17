package com.sun.org.apache.xerces.internal.xni;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class QName implements Cloneable {
    public String localpart;
    public String prefix;
    public String rawname;
    public String uri;

    public QName() {
        clear();
    }

    public void clear() {
        this.prefix = null;
        this.localpart = null;
        this.rawname = null;
        this.uri = null;
    }

    public Object clone() {
        return new QName(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof QName)) {
            QName qName = (QName) obj;
            if (qName.uri != null) {
                return qName.localpart.equals(this.localpart) && qName.uri.equals(this.uri);
            }
            if (this.uri == null) {
                return this.rawname.equals(qName.rawname);
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.uri;
        if (str != null) {
            int iHashCode = str.hashCode();
            String str2 = this.localpart;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }
        String str3 = this.rawname;
        if (str3 != null) {
            return str3.hashCode();
        }
        return 0;
    }

    public void setValues(QName qName) {
        this.prefix = qName.prefix;
        this.localpart = qName.localpart;
        this.rawname = qName.rawname;
        this.uri = qName.uri;
    }

    public String toString() {
        boolean z;
        StringBuffer stringBuffer = new StringBuffer();
        boolean z2 = true;
        if (this.prefix != null) {
            stringBuffer.append("prefix=\"" + this.prefix + '\"');
            z = true;
        } else {
            z = false;
        }
        if (this.localpart != null) {
            if (z) {
                stringBuffer.append(',');
            }
            stringBuffer.append("localpart=\"" + this.localpart + '\"');
            z = true;
        }
        if (this.rawname != null) {
            if (z) {
                stringBuffer.append(',');
            }
            stringBuffer.append("rawname=\"" + this.rawname + '\"');
        } else {
            z2 = z;
        }
        if (this.uri != null) {
            if (z2) {
                stringBuffer.append(',');
            }
            stringBuffer.append("uri=\"" + this.uri + '\"');
        }
        return stringBuffer.toString();
    }

    public QName(String str, String str2, String str3, String str4) {
        setValues(str, str2, str3, str4);
    }

    public QName(QName qName) {
        setValues(qName);
    }

    public void setValues(String str, String str2, String str3, String str4) {
        this.prefix = str;
        this.localpart = str2;
        this.rawname = str3;
        this.uri = str4;
    }
}

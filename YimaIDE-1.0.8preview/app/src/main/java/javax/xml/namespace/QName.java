package javax.xml.namespace;

import defpackage.yba;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class QName implements Serializable {
    private static final long serialVersionUID = -9120448754896609940L;
    private final String localPart;
    private final String namespaceURI;
    private final String prefix;

    public QName(String str, String str2, String str3) {
        if (str == null) {
            this.namespaceURI = "";
        } else {
            this.namespaceURI = str;
        }
        if (str2 == null) {
            w01.a("local part cannot be \"null\" when creating a QName");
            throw null;
        }
        this.localPart = str2;
        if (str3 != null) {
            this.prefix = str3;
        } else {
            w01.a("prefix cannot be \"null\" when creating a QName");
            throw null;
        }
    }

    public static QName valueOf(String str) {
        if (str == null) {
            w01.a("cannot create QName from \"null\" or \"\" String");
            return null;
        }
        if (str.length() != 0 && str.charAt(0) == '{') {
            if (str.startsWith("{}")) {
                yba.a("Namespace URI .equals(XMLConstants.NULL_NS_URI), .equals(\"\"), only the local part, \"", str.substring(2), "\", should be provided.");
                return null;
            }
            int iIndexOf = str.indexOf(125);
            if (iIndexOf != -1) {
                return new QName(str.substring(1, iIndexOf), str.substring(iIndexOf + 1), "");
            }
            kg9.a("cannot create QName from \"", str, "\", missing closing \"}\"");
            return null;
        }
        return new QName("", str, "");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof QName)) {
            QName qName = (QName) obj;
            if (this.localPart.equals(qName.localPart) && this.namespaceURI.equals(qName.namespaceURI)) {
                return true;
            }
        }
        return false;
    }

    public String getLocalPart() {
        return this.localPart;
    }

    public String getNamespaceURI() {
        return this.namespaceURI;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public final int hashCode() {
        return this.localPart.hashCode() ^ this.namespaceURI.hashCode();
    }

    public String toString() {
        if (this.namespaceURI.equals("")) {
            return this.localPart;
        }
        return "{" + this.namespaceURI + "}" + this.localPart;
    }

    public QName(String str, String str2) {
        this(str, str2, "");
    }

    public QName(String str) {
        this("", str, "");
    }
}

package com.reandroid.xml;

import com.reandroid.common.Namespace;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.xml.base.Attribute;
import java.io.IOException;
import java.util.Objects;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XMLAttribute extends XMLNode implements Attribute {
    private String mName;
    private XMLNamespace mNamespace;
    private String mValue;

    public XMLAttribute(String str, String str2) {
        this();
        this.mName = str;
        this.mValue = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof XMLAttribute) {
            return ObjectsUtil.equals(getName(false), ((XMLAttribute) obj).getName(false));
        }
        return false;
    }

    public boolean equalsName(String str) {
        if (str == null) {
            return getName() == null;
        }
        String strSplitPrefix = XMLUtil.splitPrefix(str);
        if (strSplitPrefix == null || strSplitPrefix.equals(getPrefix())) {
            return str.equals(getName());
        }
        return false;
    }

    @Override // com.reandroid.xml.base.NamedNode
    public String getName(boolean z) {
        String prefix;
        String str = this.mName;
        if (!z || str == null || (prefix = getPrefix()) == null) {
            return str;
        }
        return prefix + ":" + str;
    }

    @Override // com.reandroid.xml.XMLNode, com.reandroid.xml.base.Node, com.reandroid.xml.base.Attribute
    public XMLElement getParentNode() {
        return (XMLElement) super.getParentNode();
    }

    @Override // com.reandroid.xml.base.NamedNode
    public String getPrefix() {
        XMLNamespace namespace = getNamespace();
        if (namespace != null) {
            return namespace.getPrefix();
        }
        String str = this.mName;
        int iIndexOf = str.indexOf(58);
        if (iIndexOf > 0) {
            return str.substring(0, iIndexOf);
        }
        return null;
    }

    public String getValueAsString(boolean z) {
        String str = this.mValue;
        if (str == null) {
            str = "";
            this.mValue = "";
        }
        return z ? XMLUtil.escapeXmlChars(str, true) : str;
    }

    public int hashCode() {
        return ObjectsUtil.hash(getName(false));
    }

    @Override // com.reandroid.xml.base.XmlSerializable
    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.attribute(getUri(), getName(), getValueAsString(false));
    }

    public XMLAttribute set(String str, String str2) {
        this.mName = str;
        this.mValue = str2;
        return this;
    }

    public void setFrom(XMLAttribute xMLAttribute) {
        set(xMLAttribute.getName(true), xMLAttribute.getValueAsString(false));
        setNamespaceFrom(xMLAttribute.getNamespace());
    }

    public void setName(String str, String str2, String str3) {
        this.mName = XMLUtil.splitName(str3);
        if (XMLUtil.isEmpty(str2)) {
            str2 = XMLUtil.splitPrefix(str3);
        }
        XMLNamespace xMLNamespaceByPrefix = null;
        if (XMLUtil.isEmpty(str)) {
            str = null;
        }
        XMLElement parentNode = getParentNode();
        if (parentNode == null) {
            w01.a("Parent element is null");
            return;
        }
        if (str != null && str2 != null) {
            xMLNamespaceByPrefix = parentNode.getOrCreateXMLNamespace(str, str2);
        } else if (str != null) {
            xMLNamespaceByPrefix = parentNode.getXMLNamespaceByUri(str);
            if (xMLNamespaceByPrefix == null) {
                w01.a("Namespace not found for uri: ".concat(str));
                return;
            }
        } else if (str2 != null && (xMLNamespaceByPrefix = parentNode.getXMLNamespaceByPrefix(str2)) == null) {
            w01.a("Namespace not found for prefix: ".concat(str2));
            return;
        }
        if (xMLNamespaceByPrefix != null) {
            setNamespace(xMLNamespaceByPrefix);
        }
        this.mName = str3;
    }

    public void setNamespace(String str, String str2) {
        XMLElement parentNode = getParentNode();
        if (parentNode != null) {
            setNamespace(parentNode.getOrCreateXMLNamespace(str, str2));
        } else {
            w01.a("Parent element is null");
        }
    }

    public void setNamespaceFrom(Namespace namespace) {
        String uri;
        String prefix;
        if (namespace != null) {
            uri = namespace.getUri();
            prefix = namespace.getPrefix();
        } else {
            uri = null;
            prefix = null;
        }
        setNamespace(uri, prefix);
    }

    public void setPrefix(String str) {
        if (Objects.equals(str, getPrefix())) {
            return;
        }
        XMLElement parentNode = getParentNode();
        if (parentNode != null) {
            setNamespace(parentNode.getXMLNamespaceByPrefix(str));
        } else {
            w01.a("Parent element is null");
        }
    }

    public void setValue(String str) {
        this.mValue = str;
    }

    @Override // com.reandroid.xml.XMLNode
    public String toString() {
        return getName(true) + "=\"" + getValueAsString() + "\"";
    }

    @Override // com.reandroid.xml.XMLNode
    public void write(Appendable appendable, boolean z, boolean z2) throws IOException {
        appendable.append(getName(true));
        appendable.append('=');
        if (z) {
            appendable.append('\"');
        }
        appendable.append(getValueAsString(z2));
        if (z) {
            appendable.append('\"');
        }
    }

    @Override // com.reandroid.xml.base.Attribute, com.reandroid.xml.base.NamedNode
    public XMLNamespace getNamespace() {
        return this.mNamespace;
    }

    public XMLAttribute() {
    }

    @Override // com.reandroid.xml.base.Attribute
    public String getValueAsString() {
        return getValueAsString(false);
    }

    @Override // com.reandroid.xml.base.Attribute, com.reandroid.xml.base.NamedNode
    public void setNamespace(Namespace namespace) {
        this.mNamespace = (XMLNamespace) namespace;
    }

    @Override // com.reandroid.xml.base.NamedNode
    public String getName() {
        return getName(false);
    }

    public void setName(String str, String str2) {
        setName(str, null, str2);
    }

    @Override // com.reandroid.xml.base.NamedNode
    public void setName(String str) {
        setName(null, null, str);
    }
}

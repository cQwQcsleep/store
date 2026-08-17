package com.reandroid.identifiers;

import com.reandroid.arsc.coder.xml.XmlCoder;
import com.reandroid.utils.HexUtil;
import java.io.File;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Identifier implements Comparable<Identifier> {
    public static final boolean CASE_INSENSITIVE_FS = new File("ABC").equals(new File("abc"));
    static final String XML_ATTRIBUTE_ID = "id";
    static final String XML_ATTRIBUTE_NAME = "name";
    static final String XML_ATTRIBUTE_PACKAGE = "package";
    static final String XML_ATTRIBUTE_TYPE = "type";
    static final String XML_TAG_PUBLIC = "public";
    static final String XML_TAG_RESOURCES = "resources";
    private int id;
    private Identifier mParent;
    private Object mTag;
    private String name;

    public Identifier(int i, String str) {
        this.id = i;
        this.name = str;
    }

    public static boolean isAapt() {
        return XmlCoder.getInstance().getSetting().isAapt();
    }

    @Override // java.lang.Comparable
    public int compareTo(Identifier identifier) {
        return Long.compare(getUniqueId(), identifier.getUniqueId());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && getUniqueId() == ((Identifier) obj).getUniqueId();
    }

    public String getHexId() {
        return HexUtil.toHex2((byte) getId());
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Identifier getParent() {
        return this.mParent;
    }

    public Object getTag() {
        return this.mTag;
    }

    public long getUniqueId() {
        return getId();
    }

    public int hashCode() {
        return Long.hashCode(getUniqueId());
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setParent(Identifier identifier) {
        if (identifier == this) {
            return;
        }
        this.mParent = identifier;
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public String toString() {
        return getName() + "(" + getHexId() + ")";
    }
}

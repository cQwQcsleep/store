package com.reandroid.dex.resource;

import com.reandroid.apk.XmlHelper;
import com.reandroid.arsc.model.ResourceName;
import com.reandroid.arsc.value.AttributeDataFormat;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeclareStyleable implements Comparable<DeclareStyleable>, Iterable<Attr> {
    private final ArrayCollection<Attr> attrList = new ArrayCollection<>();
    private final String name;
    public static final String TAG = ObjectsUtil.of("declare-styleable");
    public static final String NAME_format = ObjectsUtil.of(Constants.ATTRNAME_FORMAT);

    public static class Attr {
        private final String format;
        private final int id;
        private final String name;
        private final DeclareStyleable parent;

        public Attr(DeclareStyleable declareStyleable, int i, String str, String str2) {
            this.parent = declareStyleable;
            this.id = i;
            this.name = str;
            this.format = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.id == ((Attr) obj).id;
        }

        public String getFormat() {
            return this.format;
        }

        public int getFormatFlags() {
            return AttributeDataFormat.sum(AttributeDataFormat.parseValueTypes(getFormat()));
        }

        public int getId() {
            return this.id;
        }

        public int getIndex() {
            return getParent().getAttrList().indexOf(this);
        }

        public String getName() {
            return this.name;
        }

        public DeclareStyleable getParent() {
            return this.parent;
        }

        public int hashCode() {
            return this.id;
        }

        public void serialize(XmlSerializer xmlSerializer) throws IOException {
            xmlSerializer.startTag(null, "attr");
            XmlHelper.setIndent(xmlSerializer, false);
            xmlSerializer.attribute(null, "name", getName());
            xmlSerializer.attribute(null, DeclareStyleable.NAME_format, getFormat());
            XmlHelper.setIndent(xmlSerializer, true);
            xmlSerializer.endTag(null, "attr");
        }

        public ResourceName toResourceName() {
            String strSubstring;
            String name = getName();
            int iIndexOf = name.indexOf(58);
            if (iIndexOf > 0) {
                strSubstring = name.substring(0, iIndexOf);
                name = name.substring(iIndexOf + 1);
            } else {
                strSubstring = null;
            }
            return new ResourceName(strSubstring, "attr", name);
        }

        public String toString() {
            return "id=" + HexUtil.toHex8(this.id) + ", name='" + this.name + "', format='" + this.format + '\'';
        }
    }

    public DeclareStyleable(String str) {
        this.name = str;
    }

    public static void serialize(List<DeclareStyleable> list, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startDocument("utf8", null);
        XmlHelper.setIndent(xmlSerializer, true);
        xmlSerializer.startTag(null, XmlHelper.RESOURCES_TAG);
        Iterator<DeclareStyleable> it = list.iterator();
        while (it.hasNext()) {
            it.next().serialize(xmlSerializer);
        }
        XmlHelper.setIndent(xmlSerializer, true);
        xmlSerializer.endTag(null, XmlHelper.RESOURCES_TAG);
        xmlSerializer.endDocument();
    }

    public void add(Attr attr) {
        if (attr != null) {
            ArrayCollection<Attr> arrayCollection = this.attrList;
            if (arrayCollection.contains(attr)) {
                return;
            }
            arrayCollection.add(attr);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(DeclareStyleable declareStyleable) {
        if (declareStyleable == this) {
            return 0;
        }
        return CompareUtil.compare(getName(), declareStyleable.getName());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            DeclareStyleable declareStyleable = (DeclareStyleable) obj;
            if (ObjectsUtil.equals(getName(), declareStyleable.getName()) && getAttrList().equals(declareStyleable.getAttrList())) {
                return true;
            }
        }
        return false;
    }

    public List<Attr> getAttrList() {
        return this.attrList;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return (ObjectsUtil.hash(getName()) * 31) + getAttrList().hashCode();
    }

    public boolean isEmpty() {
        return getAttrList().isEmpty();
    }

    @Override // java.lang.Iterable
    public Iterator<Attr> iterator() {
        return getAttrList().iterator();
    }

    public int size() {
        return getAttrList().size();
    }

    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, TAG);
        xmlSerializer.attribute(null, "name", getName());
        Iterator<Attr> it = iterator();
        while (it.hasNext()) {
            it.next().serialize(xmlSerializer);
        }
        XmlHelper.setIndent(xmlSerializer, true);
        xmlSerializer.endTag(null, TAG);
    }
}

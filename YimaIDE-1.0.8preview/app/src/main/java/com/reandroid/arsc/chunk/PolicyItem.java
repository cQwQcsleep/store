package com.reandroid.arsc.chunk;

import com.reandroid.apk.xmlencoder.EncodeException;
import com.reandroid.arsc.coder.CoderUnknownReferenceId;
import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.xml.XMLUtil;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PolicyItem extends IntegerItem {
    public static final String ATTR_name = ObjectsUtil.of(TypeBlock.NAME_name);
    public static final String ATTR_type = ObjectsUtil.of("type");
    public static final String TAG_item = ObjectsUtil.of("item");

    private void skipToEnd(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        XMLUtil.ensureTag(xmlPullParser);
        if (xmlPullParser.getEventType() == 3) {
            xmlPullParser.next();
            XMLUtil.ensureTag(xmlPullParser);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof PolicyItem) && get() == ((PolicyItem) obj).get();
    }

    public ResourceEntry getResourceEntry() {
        PackageBlock packageBlock = (PackageBlock) getParentInstance(PackageBlock.class);
        if (packageBlock != null) {
            return packageBlock.getResource(get());
        }
        return null;
    }

    public int hashCode() {
        return get();
    }

    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        EncodeResult encodeResultEncode;
        String str = ATTR_name;
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        if (StringsUtil.isEmpty(attributeValue)) {
            throw new EncodeException(xmlPullParser, "Missing attribute '" + str + "'");
        }
        String attributeValue2 = xmlPullParser.getAttributeValue(null, ATTR_type);
        if (StringsUtil.isEmpty(attributeValue2) && (encodeResultEncode = CoderUnknownReferenceId.INS.encode(attributeValue)) != null) {
            set(encodeResultEncode.value);
            skipToEnd(xmlPullParser);
            return;
        }
        PackageBlock packageBlock = (PackageBlock) getParentInstance(PackageBlock.class);
        ResourceEntry resource = packageBlock.getResource(attributeValue2, attributeValue);
        if (resource == null) {
            resource = packageBlock.getTableBlock().getResource((String) null, attributeValue2, attributeValue);
        }
        if (resource != null) {
            set(resource.getResourceId());
            skipToEnd(xmlPullParser);
            return;
        }
        throw new EncodeException(xmlPullParser, "Unknown policy item: type = " + attributeValue2 + ", name = " + attributeValue + XmlPullParser.NO_NAMESPACE);
    }

    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        String str = TAG_item;
        xmlSerializer.startTag(null, str);
        ResourceEntry resourceEntry = getResourceEntry();
        if (resourceEntry != null) {
            xmlSerializer.attribute(null, ATTR_type, resourceEntry.getType());
            xmlSerializer.attribute(null, ATTR_name, resourceEntry.getName());
        } else {
            xmlSerializer.attribute(null, ATTR_type, XmlPullParser.NO_NAMESPACE);
            xmlSerializer.attribute(null, ATTR_name, CoderUnknownReferenceId.INS.decode(get()));
        }
        xmlSerializer.endTag(null, str);
    }

    @Override // com.reandroid.arsc.item.IntegerItem
    public String toString() {
        ResourceEntry resourceEntry = getResourceEntry();
        return resourceEntry != null ? resourceEntry.buildReference((PackageBlock) getParentInstance(PackageBlock.class)) : toHex();
    }
}

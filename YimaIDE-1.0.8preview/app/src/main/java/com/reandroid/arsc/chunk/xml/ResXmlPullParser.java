package com.reandroid.arsc.chunk.xml;

import android.content.res.XmlResourceParser;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.utils.StringsUtil;
import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlPullParser extends ResXmlEventParser implements XmlResourceParser {
    public ResXmlPullParser(ResXmlNode resXmlNode) {
        this(resXmlNode.getParserEvents());
    }

    private int convertValueToList(String str, String[] strArr, int i) {
        if (!StringsUtil.isEmpty(str)) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (str.equals(strArr[i2])) {
                    return i2;
                }
            }
        }
        return i;
    }

    private int getAttributeIntValue(ResXmlAttribute resXmlAttribute, int i) {
        int type;
        return (resXmlAttribute == null || (type = resXmlAttribute.getType() & 255) <= 16 || type > 31) ? i : resXmlAttribute.getData();
    }

    @Override // android.content.res.XmlResourceParser, java.lang.AutoCloseable
    public void close() {
    }

    @Override // android.util.AttributeSet
    public boolean getAttributeBooleanValue(String str, String str2, boolean z) {
        ResXmlAttribute attribute = getAttribute(str, str2);
        if (attribute != null) {
            return getAttributeIntValue(attribute, 0) != 0;
        }
        return z;
    }

    @Override // android.util.AttributeSet
    public float getAttributeFloatValue(String str, String str2, float f) {
        ResXmlAttribute attribute = getAttribute(str, str2);
        return (attribute == null || attribute.getValueType() != ValueType.FLOAT) ? f : Float.intBitsToFloat(attribute.getData());
    }

    @Override // android.util.AttributeSet
    public int getAttributeListValue(int i, String[] strArr, int i2) {
        ResXmlAttribute resXmlAttributeAt = getResXmlAttributeAt(i);
        return (resXmlAttributeAt == null || resXmlAttributeAt.getValueType() != ValueType.STRING || strArr == null || strArr.length == 0) ? i2 : convertValueToList(resXmlAttributeAt.getValueAsString(), strArr, i2);
    }

    @Override // android.util.AttributeSet
    public int getAttributeNameResource(int i) {
        ResXmlAttribute resXmlAttributeAt = getResXmlAttributeAt(i);
        if (resXmlAttributeAt != null) {
            return resXmlAttributeAt.getNameId();
        }
        return 0;
    }

    @Override // android.util.AttributeSet
    public int getAttributeResourceValue(String str, String str2, int i) {
        ResXmlAttribute attribute = getAttribute(str, str2);
        return (attribute == null || attribute.getValueType() != ValueType.REFERENCE) ? i : attribute.getData();
    }

    @Override // android.util.AttributeSet
    public int getAttributeUnsignedIntValue(String str, String str2, int i) {
        return getAttributeIntValue(getAttribute(str, str2), i);
    }

    @Override // android.util.AttributeSet
    public String getClassAttribute() {
        ResXmlAttribute classAttribute;
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement == null || (classAttribute = currentElement.getClassAttribute()) == null) {
            return null;
        }
        return classAttribute.getName();
    }

    @Override // android.util.AttributeSet
    public String getIdAttribute() {
        ResXmlAttribute idAttribute;
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement == null || (idAttribute = currentElement.getIdAttribute()) == null) {
            return null;
        }
        return idAttribute.getName();
    }

    @Override // android.util.AttributeSet
    public int getIdAttributeResourceValue(int i) {
        ResXmlAttribute idAttribute;
        ResXmlElement currentElement = getCurrentElement();
        return (currentElement == null || (idAttribute = currentElement.getIdAttribute()) == null) ? i : idAttribute.getNameId();
    }

    @Override // android.util.AttributeSet
    public int getStyleAttribute() {
        ResXmlAttribute styleAttribute;
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement == null || (styleAttribute = currentElement.getStyleAttribute()) == null) {
            return 0;
        }
        return styleAttribute.getNameId();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlEventParser, org.xmlpull.v1.XmlPullParser
    public void setInput(Reader reader) throws XmlPullParserException {
        throw new XmlPullParserException("Unsupported operation");
    }

    public ResXmlPullParser(Iterator<ResXmlEvent> it) {
        super(it);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlEventParser, org.xmlpull.v1.XmlPullParser
    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        throw new XmlPullParserException("Unsupported operation");
    }

    @Override // android.util.AttributeSet
    public int getAttributeUnsignedIntValue(int i, int i2) {
        return getAttributeIntValue(getResXmlAttributeAt(i), i2);
    }

    @Override // android.util.AttributeSet
    public boolean getAttributeBooleanValue(int i, boolean z) {
        ResXmlAttribute resXmlAttributeAt = getResXmlAttributeAt(i);
        if (resXmlAttributeAt != null) {
            z = false;
            if (getAttributeIntValue(resXmlAttributeAt, 0) != 0) {
                return true;
            }
        }
        return z;
    }

    @Override // android.util.AttributeSet
    public int getAttributeResourceValue(int i, int i2) {
        ResXmlAttribute resXmlAttributeAt = getResXmlAttributeAt(i);
        return (resXmlAttributeAt == null || resXmlAttributeAt.getValueType() != ValueType.REFERENCE) ? i2 : resXmlAttributeAt.getData();
    }

    @Override // android.util.AttributeSet
    public int getAttributeIntValue(int i, int i2) {
        return getAttributeIntValue(getResXmlAttributeAt(i), i2);
    }

    @Override // android.util.AttributeSet
    public int getAttributeIntValue(String str, String str2, int i) {
        return getAttributeIntValue(getAttribute(str, str2), i);
    }

    @Override // android.util.AttributeSet
    public float getAttributeFloatValue(int i, float f) {
        ResXmlAttribute resXmlAttributeAt = getResXmlAttributeAt(i);
        return (resXmlAttributeAt == null || resXmlAttributeAt.getValueType() != ValueType.FLOAT) ? f : Float.intBitsToFloat(resXmlAttributeAt.getData());
    }

    @Override // android.util.AttributeSet
    public int getAttributeListValue(String str, String str2, String[] strArr, int i) {
        ResXmlAttribute attribute = getAttribute(str, str2);
        return (attribute == null || attribute.getValueType() != ValueType.STRING || strArr == null || strArr.length == 0) ? i : convertValueToList(attribute.getValueAsString(), strArr, i);
    }
}

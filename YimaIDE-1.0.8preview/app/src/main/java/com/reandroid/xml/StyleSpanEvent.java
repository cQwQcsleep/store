package com.reandroid.xml;

import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StyleSpanEvent implements Comparable<StyleSpanEvent> {
    private final char mChar;
    private final Span span;
    private final int type;
    public static final int TYPE_START_END = ObjectsUtil.of(0);
    public static final int TYPE_START_TAG = ObjectsUtil.of(1);
    public static final int TYPE_CHAR = ObjectsUtil.of(2);
    public static final int TYPE_END_TAG = ObjectsUtil.of(3);

    public StyleSpanEvent(int i, char c, Span span) {
        this.type = i;
        this.mChar = c;
        this.span = span;
    }

    @Override // java.lang.Comparable
    public int compareTo(StyleSpanEvent styleSpanEvent) {
        if (styleSpanEvent == this) {
            return 0;
        }
        int type = getType();
        int type2 = styleSpanEvent.getType();
        int iCompare = CompareUtil.compare(type, type2);
        int i = TYPE_CHAR;
        if (type != i && type2 != i) {
            int iCompare2 = CompareUtil.compare(getSpan().getSpanOrder(), styleSpanEvent.getSpan().getSpanOrder());
            int i2 = TYPE_START_END;
            if (type == i2) {
                if (type2 == TYPE_END_TAG) {
                    return 1;
                }
                return iCompare2;
            }
            if (type2 == i2) {
                if (type == TYPE_END_TAG) {
                    return 1;
                }
                return iCompare2;
            }
            if (iCompare2 != 0) {
                return (iCompare == 0 && type == TYPE_END_TAG) ? -iCompare2 : iCompare2;
            }
        }
        return iCompare;
    }

    public char getChar() {
        return this.mChar;
    }

    public Span getSpan() {
        return this.span;
    }

    public int getType() {
        return this.type;
    }

    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        int type = getType();
        if (type == TYPE_CHAR) {
            xmlSerializer.text(String.valueOf(getChar()));
            return;
        }
        if (type == TYPE_START_END) {
            StyleElement element = getSpan().toElement();
            String name = element.getName();
            xmlSerializer.startTag(null, name);
            Iterator<StyleAttribute> attributes = element.getAttributes();
            while (attributes.hasNext()) {
                attributes.next().serialize(xmlSerializer);
            }
            xmlSerializer.endTag(null, name);
            return;
        }
        if (type != TYPE_START_TAG) {
            if (type == TYPE_END_TAG) {
                xmlSerializer.endTag(null, getSpan().getTagName());
                return;
            } else {
                t8g.a("Unknown span event: ", type);
                return;
            }
        }
        StyleElement element2 = getSpan().toElement();
        xmlSerializer.startTag(null, element2.getName());
        Iterator<StyleAttribute> attributes2 = element2.getAttributes();
        while (attributes2.hasNext()) {
            attributes2.next().serialize(xmlSerializer);
        }
    }

    public String toString() {
        int type = getType();
        if (type == TYPE_CHAR) {
            return String.valueOf(getChar());
        }
        if (type == TYPE_START_TAG) {
            return "<" + getSpan().getTagName() + ">";
        }
        if (type == TYPE_START_END) {
            return "<" + getSpan().getTagName() + "/>";
        }
        return "</" + getSpan().getTagName() + ">";
    }

    public StyleSpanEvent(char c) {
        this(TYPE_CHAR, c, null);
    }

    public StyleSpanEvent(int i, Span span) {
        this(i, (char) 0, span);
    }
}

package com.reandroid.arsc.chunk.xml;

import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlEvent {
    private final int type;
    private final ResXmlNode xmlNode;
    public static final int START_DOCUMENT = ObjectsUtil.of(0);
    public static final int END_DOCUMENT = ObjectsUtil.of(1);
    public static final int START_TAG = ObjectsUtil.of(2);
    public static final int END_TAG = ObjectsUtil.of(3);
    public static final int TEXT = ObjectsUtil.of(4);
    public static final int CDSECT = ObjectsUtil.of(5);
    public static final int ENTITY_REF = ObjectsUtil.of(6);
    public static final int IGNORABLE_WHITESPACE = ObjectsUtil.of(7);
    public static final int PROCESSING_INSTRUCTION = ObjectsUtil.of(8);
    public static final int COMMENT = ObjectsUtil.of(9);
    public static final int DOCDECL = ObjectsUtil.of(10);

    public ResXmlEvent(int i, ResXmlNode resXmlNode) {
        this.type = i;
        this.xmlNode = resXmlNode;
    }

    public static ResXmlEvent comment(ResXmlTextNode resXmlTextNode) {
        if (StringsUtil.isEmpty(resXmlTextNode.getComment())) {
            return null;
        }
        return new ResXmlEvent(COMMENT, resXmlTextNode) { // from class: com.reandroid.arsc.chunk.xml.ResXmlEvent.3
            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public int getLineNumber() {
                return getXmlNode().getLineNumber();
            }

            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public String getText() {
                return ((ResXmlTextNode) getXmlNode()).getComment();
            }
        };
    }

    public static ResXmlEvent endComment(ResXmlElement resXmlElement) {
        if (StringsUtil.isEmpty(resXmlElement.getEndComment())) {
            return null;
        }
        return new ResXmlEvent(COMMENT, resXmlElement) { // from class: com.reandroid.arsc.chunk.xml.ResXmlEvent.2
            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public int getLineNumber() {
                return getXmlNode().getEndLineNumber();
            }

            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public String getText() {
                return ((ResXmlElement) getXmlNode()).getEndComment();
            }
        };
    }

    public static ResXmlEvent endDocument(ResXmlDocument resXmlDocument) {
        return new ResXmlEvent(END_DOCUMENT, resXmlDocument) { // from class: com.reandroid.arsc.chunk.xml.ResXmlEvent.8
            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public int getLineNumber() {
                return getXmlNode().getEndLineNumber();
            }
        };
    }

    public static ResXmlEvent endTag(ResXmlElement resXmlElement) {
        return new ResXmlEvent(END_TAG, resXmlElement) { // from class: com.reandroid.arsc.chunk.xml.ResXmlEvent.5
            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public int getLineNumber() {
                return getXmlNode().getEndLineNumber();
            }

            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public String getText() {
                return ((ResXmlElement) getXmlNode()).getName(false);
            }
        };
    }

    public static ResXmlEvent startComment(ResXmlElement resXmlElement) {
        if (StringsUtil.isEmpty(resXmlElement.getStartComment())) {
            return null;
        }
        return new ResXmlEvent(COMMENT, resXmlElement) { // from class: com.reandroid.arsc.chunk.xml.ResXmlEvent.1
            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public int getLineNumber() {
                return getXmlNode().getStartLineNumber();
            }

            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public String getText() {
                return ((ResXmlElement) getXmlNode()).getStartComment();
            }
        };
    }

    public static ResXmlEvent startDocument(ResXmlDocument resXmlDocument) {
        return new ResXmlEvent(START_DOCUMENT, resXmlDocument) { // from class: com.reandroid.arsc.chunk.xml.ResXmlEvent.7
            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public int getLineNumber() {
                return getXmlNode().getStartLineNumber();
            }
        };
    }

    public static ResXmlEvent startTag(ResXmlElement resXmlElement) {
        return new ResXmlEvent(START_TAG, resXmlElement) { // from class: com.reandroid.arsc.chunk.xml.ResXmlEvent.4
            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public int getLineNumber() {
                return getXmlNode().getStartLineNumber();
            }

            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public String getText() {
                return ((ResXmlElement) getXmlNode()).getName(false);
            }
        };
    }

    public static ResXmlEvent text(ResXmlTextNode resXmlTextNode) {
        if (resXmlTextNode.isIndent()) {
            return null;
        }
        return new ResXmlEvent(TEXT, resXmlTextNode) { // from class: com.reandroid.arsc.chunk.xml.ResXmlEvent.6
            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public int getLineNumber() {
                return getXmlNode().getLineNumber();
            }

            @Override // com.reandroid.arsc.chunk.xml.ResXmlEvent
            public String getText() {
                return ((ResXmlTextNode) getXmlNode()).getText();
            }
        };
    }

    public int getDepth() {
        return getXmlNode().getDepth();
    }

    public String getEventName() {
        int type = getType();
        String[] strArr = XmlPullParser.TYPES;
        if (type >= 0 && type < strArr.length) {
            return strArr[type];
        }
        return "unknown-" + type;
    }

    public int getLineNumber() {
        return 0;
    }

    public String getText() {
        return null;
    }

    public int getType() {
        return this.type;
    }

    public ResXmlNode getXmlNode() {
        return this.xmlNode;
    }

    public String toString() {
        String text = getText();
        if (text != null && text.length() > 100) {
            text = text.substring(0, 100).concat(" ...");
        }
        return getEventName() + " {" + text + "} ";
    }
}

package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.coder.XmlSanitizer;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.reandroid.xml.XMLComment;
import com.reandroid.xml.XMLNode;
import com.reandroid.xml.XMLText;
import com.reandroid.xml.XMLUtil;
import com.reandroid.xml.base.Text;
import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlTextNode extends ResXmlNode implements Text {
    private String mIndentText;

    public ResXmlTextNode() {
        super(new ResXmlTextChunk());
    }

    private static boolean isIndent(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return true;
        }
        if (str.charAt(0) != '\n') {
            return false;
        }
        for (int i = 1; i < length; i++) {
            if (!StringsUtil.isWhiteSpace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isNextElement() {
        ResXmlNodeTree resXmlNodeTreeMo60getParentNode = mo60getParentNode();
        if (resXmlNodeTreeMo60getParentNode != null) {
            return resXmlNodeTreeMo60getParentNode.m61get(getIndex() + 1) instanceof ResXmlElement;
        }
        return false;
    }

    public void append(String str) {
        String text = getText();
        if (text == null || text.length() == 0) {
            text = this.mIndentText;
        }
        if (text == null && isIndent(str)) {
            this.mIndentText = str;
            return;
        }
        if (text != null) {
            str = text + str;
        }
        setText(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int autoSetLineNumber(int i) {
        int iCountChar;
        if (isComment()) {
            iCountChar = i + 1;
        } else {
            String text = getText();
            if (isIndent(text) && isNextElement()) {
                iCountChar = i;
                i++;
            } else {
                iCountChar = StringsUtil.countChar(text, '\n') + i;
            }
        }
        setLineNumber(i);
        return iCountChar;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void fromJson(JSONObject jSONObject) {
        setText(jSONObject.optString(ResXmlNode.JSON_value, (String) null));
        setComment(jSONObject.optString(ResXmlNode.JSON_comment, (String) null));
        setLineNumber(jSONObject.optInt(ResXmlNode.JSON_line));
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public ResXmlTextChunk getChunk() {
        return (ResXmlTextChunk) super.getChunk();
    }

    public String getComment() {
        return getChunk().getComment();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int getEndLineNumber() {
        int startLineNumber = getStartLineNumber();
        return !isIndent() ? startLineNumber + StringsUtil.countChar(getText(), '\n') : startLineNumber;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    /* JADX INFO: renamed from: getParentNode, reason: merged with bridge method [inline-methods] */
    public ResXmlNodeTree m66getParentNode() {
        return (ResXmlNodeTree) super.mo60getParentNode();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public Iterator<ResXmlEvent> getParserEvents() {
        if (isComment()) {
            return SingleIterator.of(ResXmlEvent.comment(this));
        }
        return hasComment() ? CombiningIterator.singleOne(ResXmlEvent.comment(this), SingleIterator.of(ResXmlEvent.text(this))) : SingleIterator.of(ResXmlEvent.text(this));
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int getStartLineNumber() {
        return getChunk().getLineNumber();
    }

    public String getText() {
        return getChunk().getText();
    }

    public boolean hasComment() {
        return !StringsUtil.isEmpty(getComment());
    }

    public boolean isBlank() {
        return StringsUtil.isBlank(getText());
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public boolean isComment() {
        return hasComment() && isEmpty();
    }

    public boolean isEmpty() {
        return StringsUtil.isEmpty(getText());
    }

    @Override // com.reandroid.arsc.container.WrappedBlock, com.reandroid.arsc.base.Block
    public boolean isNull() {
        return getChunk().isNull();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public boolean isText() {
        return !isComment();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void linkStringReferences() {
        getChunk().linkStringReferences();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void merge(ResXmlNode resXmlNode) {
        if (resXmlNode == this) {
            return;
        }
        ResXmlTextNode resXmlTextNode = (ResXmlTextNode) resXmlNode;
        setText(resXmlTextNode.getText());
        setComment(resXmlTextNode.getComment());
        setLineNumber(resXmlTextNode.getLineNumber());
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void mergeWithName(ResourceMergeOption resourceMergeOption, ResXmlNode resXmlNode) {
        merge(resXmlNode);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public String nodeTypeName() {
        return isComment() ? ResXmlNode.JSON_node_type_comment : ResXmlNode.JSON_node_type_text;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void onPreRemove() {
        getChunk().onPreRemove();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        setLineNumber(xmlPullParser.getLineNumber());
        if (xmlPullParser.getEventType() == 9) {
            setComment(xmlPullParser.getText());
            xmlPullParser.nextToken();
            return;
        }
        if (ResXmlNode.isTextEvent(xmlPullParser.getEventType())) {
            while (ResXmlNode.isTextEvent(xmlPullParser.getEventType())) {
                append(XmlSanitizer.unEscapeUnQuote(xmlPullParser.getText()));
                xmlPullParser.nextToken();
            }
            if (isNull()) {
                removeSelf();
                return;
            }
            return;
        }
        throw new XmlPullParserException("Expecting text events, but found: " + XMLUtil.toEventName(xmlPullParser.getEventType()) + ", " + xmlPullParser.getPositionDescription());
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public boolean removeSelf() {
        ResXmlNodeTree resXmlNodeTreeMo60getParentNode = mo60getParentNode();
        if (resXmlNodeTreeMo60getParentNode != null) {
            return resXmlNodeTreeMo60getParentNode.remove((ResXmlNode) this);
        }
        return false;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void serialize(XmlSerializer xmlSerializer, boolean z) throws IOException {
        String text;
        serializeComment(xmlSerializer, getComment());
        if (!isText() || (text = getText()) == null) {
            return;
        }
        xmlSerializer.text(text);
    }

    public void setComment(String str) {
        getChunk().setComment(str);
    }

    public void setLineNumber(int i) {
        getChunk().setLineNumber(i);
    }

    public void setText(String str) {
        getChunk().setText(str);
        this.mIndentText = null;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResXmlNode.JSON_node_type, nodeTypeName());
        jSONObject.put(ResXmlNode.JSON_line, getLineNumber());
        if (isText()) {
            jSONObject.put(ResXmlNode.JSON_value, getText());
        }
        jSONObject.put(ResXmlNode.JSON_comment, getComment());
        return jSONObject;
    }

    @Override // com.reandroid.arsc.container.WrappedBlock
    public String toString() {
        if (isText()) {
            String text = getText();
            return text == null ? "null" : text;
        }
        return "<!--" + getComment() + "-->";
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    /* JADX INFO: renamed from: toXml */
    public XMLNode mo52toXml(boolean z) {
        return isComment() ? new XMLComment(getComment()) : new XMLText(getText());
    }

    public boolean isIndent() {
        return !hasComment() && isIndent(getText());
    }
}

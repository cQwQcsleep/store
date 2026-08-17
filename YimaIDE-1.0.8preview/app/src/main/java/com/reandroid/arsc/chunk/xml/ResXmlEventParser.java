package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.coder.XmlSanitizer;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.xml.XMLUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlEventParser implements XmlPullParser {
    private final Iterator<ResXmlEvent> eventIterator;
    private Object location;
    private ResXmlEvent mCurrent;
    private boolean mFinished;
    private boolean mFirstPulled;
    private boolean processNamespaces = true;
    private boolean reportNamespaceAttrs = true;

    public ResXmlEventParser(Iterator<ResXmlEvent> it) {
        this.eventIterator = it;
    }

    private String decodeAttributeName(ResXmlAttribute resXmlAttribute) {
        if (resXmlAttribute != null) {
            return resXmlAttribute.getPackageBlock() == null ? resXmlAttribute.getName(isProcessNamespaces()) : resXmlAttribute.decodeName(isProcessNamespaces());
        }
        return null;
    }

    private String decodeAttributeValue(ResXmlAttribute resXmlAttribute) {
        if (resXmlAttribute == null) {
            return null;
        }
        String strDecodeValue = resXmlAttribute.decodeValue();
        return resXmlAttribute.getValueType() == ValueType.STRING ? XmlSanitizer.escapeSpecialCharacter(strDecodeValue) : strDecodeValue;
    }

    private String getNamespaceAttributeName(int i) {
        return "xmlns:" + getCurrentElement().m55getNamespaceAt(i).getPrefix();
    }

    private String getNamespaceAttributeValue(int i) {
        return getCurrentElement().m55getNamespaceAt(i).getUri();
    }

    private int getNamespaceCountInternal() {
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement != null) {
            return currentElement.getNamespaceCount();
        }
        return 0;
    }

    private int getRealAttributeIndex(int i) {
        return isCountNamespacesAsAttribute() ? i - getNamespaceCountInternal() : i;
    }

    private ResXmlNode getXmlNode() {
        ResXmlEvent current = getCurrent();
        if (current != null) {
            return current.getXmlNode();
        }
        return null;
    }

    private boolean isCountNamespacesAsAttribute() {
        return isReportNamespaceAttrs() & isProcessNamespaces();
    }

    private void nextParserEvent() throws XmlPullParserException {
        this.mFirstPulled = true;
        if (!this.mFinished && this.eventIterator.hasNext()) {
            this.mCurrent = this.eventIterator.next();
        } else if (this.mFinished) {
            b78.a("Finished", this);
        } else {
            this.mFinished = true;
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void defineEntityReplacementText(String str, String str2) {
    }

    public ResXmlAttribute getAttribute(String str, String str2) {
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement != null) {
            return currentElement.searchAttribute(str, str2);
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getAttributeCount() {
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement == null) {
            return 0;
        }
        int attributeCount = currentElement.getAttributeCount();
        return isCountNamespacesAsAttribute() ? attributeCount + currentElement.getNamespaceCount() : attributeCount;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeName(int i) {
        return (!isCountNamespacesAsAttribute() || i >= getNamespaceCountInternal()) ? decodeAttributeName(getResXmlAttributeAt(i)) : getNamespaceAttributeName(i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeNamespace(int i) {
        ResXmlAttribute resXmlAttributeAt;
        if (isProcessNamespaces() || (resXmlAttributeAt = getResXmlAttributeAt(i)) == null) {
            return null;
        }
        return resXmlAttributeAt.getUri();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributePrefix(int i) {
        ResXmlAttribute resXmlAttributeAt;
        if (isProcessNamespaces() || (resXmlAttributeAt = getResXmlAttributeAt(i)) == null) {
            return null;
        }
        return resXmlAttributeAt.getPrefix();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeType(int i) {
        return "CDATA";
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeValue(int i) {
        return (!isCountNamespacesAsAttribute() || i >= getNamespaceCountInternal()) ? decodeAttributeValue(getResXmlAttributeAt(i)) : getNamespaceAttributeValue(i);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getColumnNumber() {
        return 0;
    }

    public ResXmlEvent getCurrent() {
        if (this.mFinished) {
            return null;
        }
        if (!this.mFirstPulled) {
            try {
                nextParserEvent();
            } catch (Throwable unused) {
            }
        }
        return this.mCurrent;
    }

    public ResXmlElement getCurrentElement() {
        ResXmlNode xmlNode = getXmlNode();
        return xmlNode instanceof ResXmlElement ? (ResXmlElement) xmlNode : (ResXmlElement) ObjectsUtil.getNull();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getDepth() {
        ResXmlEvent current = getCurrent();
        if (current != null) {
            return current.getDepth();
        }
        return 0;
    }

    public ResXmlDocument getDocument() {
        ResXmlNode xmlNode = getXmlNode();
        if (xmlNode instanceof ResXmlDocument) {
            return (ResXmlDocument) xmlNode;
        }
        if (xmlNode instanceof ResXmlElement) {
            return ((ResXmlElement) xmlNode).getParentDocument();
        }
        if (xmlNode instanceof ResXmlTextNode) {
            ResXmlNodeTree parentNode = ((ResXmlTextNode) xmlNode).m66getParentNode();
            if (parentNode instanceof ResXmlDocument) {
                return (ResXmlDocument) parentNode;
            }
            if (parentNode instanceof ResXmlElement) {
                return ((ResXmlElement) parentNode).getParentDocument();
            }
        }
        return (ResXmlDocument) ObjectsUtil.getNull();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getEventType() throws XmlPullParserException {
        ResXmlEvent current = getCurrent();
        if (current != null) {
            return current.getType();
        }
        return -1;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean getFeature(String str) {
        if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(str)) {
            return this.processNamespaces;
        }
        if (XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES.equals(str)) {
            return this.reportNamespaceAttrs;
        }
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getInputEncoding() {
        ResXmlDocument document = getDocument();
        if (document != null) {
            return document.getEncoding();
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getLineNumber() {
        ResXmlEvent current = getCurrent();
        if (current != null) {
            return current.getLineNumber();
        }
        return 0;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getName() {
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement != null) {
            return currentElement.getName();
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespace(String str) {
        ResXmlNamespace namespaceForPrefix;
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement == null || (namespaceForPrefix = currentElement.getNamespaceForPrefix(str)) == null) {
            return null;
        }
        return namespaceForPrefix.getUri();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int getNamespaceCount(int i) throws XmlPullParserException {
        if (isCountNamespacesAsAttribute()) {
            return 0;
        }
        ResXmlElement currentElement = getCurrentElement();
        while (currentElement != null && currentElement.getDepth() > i) {
            currentElement = currentElement.getParentElement();
        }
        if (currentElement != null) {
            return currentElement.getNamespaceCount();
        }
        return 0;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespacePrefix(int i) throws XmlPullParserException {
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement != null) {
            return currentElement.m55getNamespaceAt(i).getPrefix();
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespaceUri(int i) {
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement != null) {
            return currentElement.m55getNamespaceAt(i).getUri();
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getPositionDescription() {
        StringBuilder sb = new StringBuilder();
        Object location = XMLUtil.getLocation(this);
        if (location != null) {
            sb.append(" at ");
            sb.append(location);
        }
        sb.append(" Binary XML file line #");
        sb.append(getLineNumber());
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement != null) {
            if (getCurrent().getType() == 2) {
                sb.append(" START_TAG ");
            } else {
                sb.append(" END_TAG ");
            }
            sb.append('<');
            sb.append(currentElement.getName(true));
            sb.append('>');
        }
        return sb.toString();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getPrefix() {
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement != null) {
            return currentElement.getPrefix();
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public Object getProperty(String str) {
        if (XMLUtil.PROPERTY_LOCATION.equals(str)) {
            return this.location;
        }
        return null;
    }

    public ResXmlAttribute getResXmlAttributeAt(int i) {
        int realAttributeIndex = getRealAttributeIndex(i);
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement == null) {
            return null;
        }
        return currentElement.m53getAttributeAt(realAttributeIndex);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getText() {
        ResXmlEvent current = getCurrent();
        if (current != null) {
            return current.getText();
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public char[] getTextCharacters(int[] iArr) {
        String text = getText();
        if (text == null) {
            iArr[0] = -1;
            iArr[1] = -1;
            return null;
        }
        char[] charArray = text.toCharArray();
        iArr[0] = 0;
        iArr[1] = charArray.length;
        return charArray;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isAttributeDefault(int i) {
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isEmptyElementTag() {
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement != null) {
            return currentElement.size() == 0 && currentElement.getAttributeCount() == 0;
        }
        return true;
    }

    public boolean isProcessNamespaces() {
        return this.processNamespaces;
    }

    public boolean isReportNamespaceAttrs() {
        return this.reportNamespaceAttrs;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public boolean isWhitespace() throws XmlPullParserException {
        ResXmlNode xmlNode = getXmlNode();
        if (xmlNode instanceof ResXmlTextNode) {
            return ((ResXmlTextNode) xmlNode).isBlank();
        }
        return false;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int next() throws XmlPullParserException, IOException {
        nextParserEvent();
        return getEventType();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int nextTag() throws XmlPullParserException, IOException {
        if (getEventType() != 2) {
            b78.a("precondition: START_TAG", this);
            return 0;
        }
        int next = next();
        while (next != 2 && next != 1) {
            next = next();
        }
        return next;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String nextText() throws XmlPullParserException, IOException {
        int eventType = getEventType();
        if (eventType != 2) {
            b78.a("precondition: START_TAG", this);
            return null;
        }
        while (eventType != 4 && eventType != 3 && eventType != 1) {
            eventType = next();
        }
        return eventType == 4 ? getText() : XmlPullParser.NO_NAMESPACE;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public int nextToken() throws XmlPullParserException, IOException {
        nextParserEvent();
        return getEventType();
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void require(int i, String str, String str2) throws XmlPullParserException, IOException {
        if (i == getEventType() && ((str == null || str.equals(getNamespace())) && (str2 == null || str2.equals(getName())))) {
            return;
        }
        throw new XmlPullParserException("expected: " + XmlPullParser.TYPES[i] + " {" + str + "}" + str2, this, null);
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setFeature(String str, boolean z) throws XmlPullParserException {
        if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(str)) {
            this.processNamespaces = z;
        } else if (XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES.equals(str)) {
            this.reportNamespaceAttrs = z;
        } else {
            throw new XmlPullParserException("Unsupported feature: " + str);
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setInput(Reader reader) throws XmlPullParserException {
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public void setProperty(String str, Object obj) throws XmlPullParserException {
        if (XMLUtil.PROPERTY_LOCATION.equals(str)) {
            this.location = obj;
        } else {
            throw new XmlPullParserException("unsupported property: " + str);
        }
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getNamespace() {
        ResXmlElement currentElement = getCurrentElement();
        if (currentElement != null) {
            return currentElement.getUri();
        }
        return null;
    }

    @Override // org.xmlpull.v1.XmlPullParser
    public String getAttributeValue(String str, String str2) {
        return decodeAttributeValue(getAttribute(str, str2));
    }
}

package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.chunk.xml.ResXmlAttribute;
import com.reandroid.arsc.chunk.xml.ResXmlNamespace;
import com.reandroid.arsc.pool.ResXmlStringPool;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.common.Namespace;
import com.reandroid.json.JSONException;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.reandroid.xml.XMLElement;
import com.reandroid.xml.XMLUtil;
import com.reandroid.xml.base.Element;
import com.reandroid.xml.base.Text;
import defpackage.efc;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlElement extends ResXmlDocumentOrElement implements Element<ResXmlNode> {
    public ResXmlElement() {
        super(new ResXmlElementChunk());
    }

    private ResXmlAttributeArray getAttributeArray() {
        return getStartElement().getResXmlAttributeArray();
    }

    private ResXmlEndElement getEndElement() {
        return getChunk().getEndElement();
    }

    private ResXmlStartElement getStartElement() {
        return getChunk().getStartElement();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.json.JSONException */
    private void setNamespaceFromJson(JSONObject jSONObject) throws JSONException {
        String str = ResXmlNode.JSON_uri;
        String strOptString = jSONObject.optString(str, (String) null);
        String str2 = ResXmlNode.JSON_prefix;
        String strOptString2 = jSONObject.optString(str2, (String) null);
        if (strOptString == null && strOptString2 != null) {
            efc.a(str2, strOptString2, str);
        } else if (strOptString2 != null || strOptString == null) {
            setNamespace(strOptString, strOptString2);
        } else {
            efc.a(str, strOptString, str2);
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ boolean autoSetAttributeNames() {
        return super.autoSetAttributeNames();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int autoSetLineNumber(int i) {
        int i2 = i + 1;
        Iterator<ResXmlNamespace> namespaces = getNamespaces();
        while (namespaces.hasNext()) {
            namespaces.next().setLineNumber(i2);
        }
        getStartElement().setLineNumber(i2);
        Iterator<ResXmlNode> it = iterator();
        int iAutoSetLineNumber = i2;
        while (it.hasNext()) {
            iAutoSetLineNumber = it.next().autoSetLineNumber(iAutoSetLineNumber);
        }
        if (i2 != iAutoSetLineNumber) {
            iAutoSetLineNumber++;
        }
        getEndElement().setLineNumber(iAutoSetLineNumber);
        return iAutoSetLineNumber;
    }

    public ResXmlAttribute createAndroidAttribute(String str, int i) {
        return getAttributeArray().getOrCreateAndroidAttribute(str, i);
    }

    public ResXmlAttribute createAttribute(String str, int i) {
        return newAttribute(str, i);
    }

    public boolean equalsName(String str) {
        if (str == null) {
            return getName() == null;
        }
        return XMLUtil.splitName(str).equals(getName(false));
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ void fixAttributeNames() {
        super.fixAttributeNames();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public void fixNamespaces() {
        Iterator<ResXmlNamespace> namespaces = getNamespaces();
        while (namespaces.hasNext()) {
            ((ResXmlStartNamespace) namespaces.next()).fixEmpty();
        }
        Iterator<ResXmlAttribute> attributes = getAttributes();
        while (attributes.hasNext()) {
            attributes.next().autoSetNamespace();
        }
        super.fixNamespaces();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.json.JSONException */
    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void fromJson(JSONObject jSONObject) throws JSONException {
        setName(jSONObject.getString(ResXmlNode.JSON_name));
        String str = ResXmlNode.JSON_line;
        setStartLineNumber(jSONObject.optInt(str));
        setEndLineNumber(jSONObject.optInt(str));
        getNamespaceList().fromJson(jSONObject.optJSONArray(ResXmlNode.JSON_namespaces));
        setNamespaceFromJson(jSONObject);
        setComment(jSONObject.optString(ResXmlNode.JSON_comment, (String) null));
        getAttributeArray().fromJson(jSONObject.optJSONArray(ResXmlNode.JSON_attributes));
        nodesFromJson(jSONObject);
    }

    /* JADX INFO: renamed from: getAttributeAt, reason: merged with bridge method [inline-methods] */
    public ResXmlAttribute m53getAttributeAt(int i) {
        return (ResXmlAttribute) getAttributeArray().get(i);
    }

    public int getAttributeCount() {
        return getAttributeArray().size();
    }

    public Iterator<ResXmlAttribute> getAttributes() {
        return getAttributeArray().clonedIterator();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public ResXmlElementChunk getChunk() {
        return (ResXmlElementChunk) super.getChunk();
    }

    public ResXmlAttribute getClassAttribute() {
        return getStartElement().getClassAttributePosition().getAttribute();
    }

    public String getComment() {
        String endComment;
        String startComment = getStartComment();
        return (!StringsUtil.isEmpty(startComment) || (endComment = getEndComment()) == null) ? startComment : endComment;
    }

    public Iterator<ResXmlElement> getDescendingParentsWithSelf() {
        return CollectionUtil.reversedOf(getParentElementsWithSelf());
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ ResXmlElement getElement(String str) {
        return super.getElement(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator getElements() {
        return super.getElements();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ int getElementsCount() {
        return super.getElementsCount();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator getElementsWithChild(String[] strArr) {
        return super.getElementsWithChild(strArr);
    }

    public String getEndComment() {
        return getEndElement().getComment();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int getEndLineNumber() {
        return getEndElement().getLineNumber();
    }

    public ResXmlAttribute getIdAttribute() {
        return getStartElement().getIdAttributePosition().getAttribute();
    }

    public String getName() {
        return getStartElement().getName();
    }

    /* JADX INFO: renamed from: getNamespace, reason: merged with bridge method [inline-methods] */
    public ResXmlNamespace m54getNamespace() {
        return getChunk().getStartElement().getResXmlStartNamespace();
    }

    /* JADX INFO: renamed from: getNamespaceAt, reason: merged with bridge method [inline-methods] */
    public ResXmlNamespace m55getNamespaceAt(int i) {
        return (ResXmlNamespace) getNamespaceList().get(i);
    }

    public int getNamespaceCount() {
        return getNamespaceList().size();
    }

    public ResXmlNamespace getNamespaceForPrefix(String str) {
        return getNamespaceList().getForPrefix(str);
    }

    public ResXmlNamespace getNamespaceForUri(String str) {
        return getNamespaceList().getForUri(str);
    }

    public ResXmlNamespace getNamespaceForUriReference(int i) {
        return getNamespaceList().getForUriReference(i);
    }

    public ResXmlStartNamespaceList getNamespaceList() {
        return getChunk().getStartNamespaceList();
    }

    public Iterator<ResXmlNamespace> getNamespaces() {
        return getNamespaceList().getNamespaces();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public ResXmlNodeList getNodeList() {
        return getChunk().getNodeList();
    }

    public ResXmlAttribute getOrCreateAndroidAttribute(String str, int i) {
        return getAttributeArray().getOrCreateAndroidAttribute(str, i);
    }

    public ResXmlAttribute getOrCreateAttribute(String str, String str2, String str3, int i) {
        return getAttributeArray().getOrCreateAttribute(str, str2, str3, i);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ ResXmlElement getOrCreateElement(String str) {
        return super.getOrCreateElement(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ ResXmlTextNode getOrCreateLastText() {
        return super.getOrCreateLastText();
    }

    public ResXmlNamespace getOrCreateNamespace(String str, String str2) {
        return getNamespaceList().getOrCreate(str, str2);
    }

    public ResXmlNamespace getOrCreateNamespaceForPrefix(String str) {
        return getNamespaceList().getOrCreateForPrefix(str);
    }

    public ResXmlDocument getParentDocument() {
        return (ResXmlDocument) getParentInstance(ResXmlDocument.class);
    }

    public ResXmlElement getParentElement() {
        ResXmlNodeTree resXmlNodeTreeMo60getParentNode = mo60getParentNode();
        if (resXmlNodeTreeMo60getParentNode instanceof ResXmlElement) {
            return (ResXmlElement) resXmlNodeTreeMo60getParentNode;
        }
        return null;
    }

    public Iterator<ResXmlElement> getParentElementsWithSelf() {
        return visitParentNodes(ResXmlElement.class, ResXmlDocument.class);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public Iterator<ResXmlEvent> getParserEvents() {
        return CombiningIterator.singleThree(ResXmlEvent.startTag(this), SingleIterator.of(ResXmlEvent.startComment(this)), new IterableIterator<ResXmlNode, ResXmlEvent>(iterator()) { // from class: com.reandroid.arsc.chunk.xml.ResXmlElement.1
            public Iterator<ResXmlEvent> iterator(ResXmlNode resXmlNode) {
                return resXmlNode.getParserEvents();
            }
        }, CombiningIterator.singleOne(ResXmlEvent.endComment(this), SingleIterator.of(ResXmlEvent.endTag(this))));
    }

    public String getPrefix() {
        return getStartElement().getPrefix();
    }

    public ResXmlElement getRootElement() {
        return (ResXmlElement) CollectionUtil.getLast(getParentElementsWithSelf());
    }

    public String getStartComment() {
        return getStartElement().getComment();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int getStartLineNumber() {
        return getStartElement().getLineNumber();
    }

    public ResXmlStringPool getStringPool() {
        ResXmlDocument parentDocument = getParentDocument();
        if (parentDocument != null) {
            return parentDocument.getStringPool();
        }
        return null;
    }

    public ResXmlAttribute getStyleAttribute() {
        return getStartElement().getStyleAttributePosition().getAttribute();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator getTexts() {
        return super.getTexts();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ int getTextsCount() {
        return super.getTextsCount();
    }

    public String getUri() {
        return getStartElement().getUri();
    }

    public Iterator<ResXmlNamespace> getVisibleNamespaces() {
        return (Iterator) ObjectsUtil.cast(getNamespaceList().getVisibleNamespaces());
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ boolean hasElement() {
        return super.hasElement();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ boolean hasText() {
        return super.hasText();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public boolean isElement() {
        return true;
    }

    public boolean isUndefined() {
        return size() == 0 && getAttributeCount() == 0 && getNamespaceCount() == 0 && StringsUtil.isEmpty(getName());
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ int lastIndexOf(String str) {
        return super.lastIndexOf(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement, com.reandroid.arsc.chunk.xml.ResXmlNode
    public void linkStringReferences() {
        getChunk().linkStringReferences();
        super.linkStringReferences();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    @Deprecated
    public /* bridge */ /* synthetic */ List listElements(String str) {
        return super.listElements(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree, com.reandroid.arsc.chunk.xml.ResXmlNode
    public void merge(ResXmlNode resXmlNode) {
        if (resXmlNode == this) {
            return;
        }
        ResXmlElement resXmlElement = (ResXmlElement) resXmlNode;
        setName(resXmlElement.getName(false));
        getNamespaceList().merge(resXmlElement.getNamespaceList());
        setNamespace(resXmlElement.m54getNamespace());
        getAttributeArray().merge(resXmlElement.getAttributeArray());
        setStartComment(resXmlElement.getStartComment());
        setEndComment(resXmlElement.getEndComment());
        setStartLineNumber(resXmlElement.getStartLineNumber());
        super.merge(resXmlNode);
        setEndLineNumber(resXmlElement.getEndLineNumber());
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree, com.reandroid.arsc.chunk.xml.ResXmlNode
    public void mergeWithName(ResourceMergeOption resourceMergeOption, ResXmlNode resXmlNode) {
        if (resXmlNode == this) {
            return;
        }
        ResXmlElement resXmlElement = (ResXmlElement) resXmlNode;
        setName(resXmlElement.getName(false));
        getNamespaceList().merge(resXmlElement.getNamespaceList());
        setNamespace(resXmlElement.m54getNamespace());
        getAttributeArray().mergeWithName(resourceMergeOption, resXmlElement.getAttributeArray());
        setStartComment(resXmlElement.getStartComment());
        setEndComment(resXmlElement.getEndComment());
        setStartLineNumber(resXmlElement.getStartLineNumber());
        super.mergeWithName(resourceMergeOption, resXmlNode);
        setEndLineNumber(resXmlElement.getEndLineNumber());
    }

    /* JADX INFO: renamed from: newAttribute, reason: merged with bridge method [inline-methods] */
    public ResXmlAttribute m56newAttribute() {
        return getAttributeArray().createNext();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement, com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public /* bridge */ /* synthetic */ ResXmlDocument newDocument() {
        return super.newDocument();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement, com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public /* bridge */ /* synthetic */ ResXmlElement newElement() {
        return super.newElement();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ ResXmlElement newElementAt(int i) {
        return super.newElementAt(i);
    }

    /* JADX INFO: renamed from: newNamespace, reason: merged with bridge method [inline-methods] */
    public ResXmlNamespace m58newNamespace(String str, String str2) {
        return getNamespaceList().createNext(str, str2);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement, com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public /* bridge */ /* synthetic */ ResXmlTextNode newText() {
        return super.newText();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement, com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public /* bridge */ /* synthetic */ UnknownResXmlNode newUnknown() {
        return super.newUnknown();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public String nodeTypeName() {
        return ResXmlNode.JSON_node_type_element;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree, com.reandroid.arsc.chunk.xml.ResXmlNode
    public void onPreRemove() {
        super.onPreRemove();
        getChunk().onPreRemove();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement, com.reandroid.arsc.chunk.xml.ResXmlNode
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        XMLUtil.expectEvent(xmlPullParser, 2);
        setStartLineNumber(xmlPullParser.getLineNumber());
        getNamespaceList().parse(xmlPullParser);
        setName(xmlPullParser.getName());
        setNamespace(xmlPullParser.getNamespace(), xmlPullParser.getPrefix());
        getAttributeArray().parse(xmlPullParser);
        xmlPullParser.nextToken();
        parseInnerNodes(xmlPullParser);
        xmlPullParser.nextToken();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator recursiveAttributes() {
        return super.recursiveAttributes();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator recursiveElements() {
        return super.recursiveElements();
    }

    public boolean removeAttribute(ResXmlAttribute resXmlAttribute) {
        return getAttributeArray().remove(resXmlAttribute);
    }

    public boolean removeAttributeAt(int i) {
        return getAttributeArray().remove(i) != null;
    }

    public boolean removeAttributeIf(Predicate<? super ResXmlAttribute> predicate) {
        return getAttributeArray().removeIf(predicate);
    }

    public boolean removeAttributesWithId(final int i) {
        return removeAttributeIf(new Predicate() { // from class: ffc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResXmlAttribute) obj).equalsNameId(i);
            }
        });
    }

    public boolean removeAttributesWithName(final String str) {
        return removeAttributeIf(new Predicate() { // from class: ifc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResXmlAttribute) obj).equalsNameWithNoId(str);
            }
        });
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ boolean removeElementsIf(Predicate predicate) {
        return super.removeElementsIf(predicate);
    }

    public boolean removeNamespace(ResXmlNamespace resXmlNamespace) {
        return getNamespaceList().remove((ResXmlStartNamespace) resXmlNamespace);
    }

    public boolean removeNamespaceIf(Predicate<? super ResXmlNamespace> predicate) {
        return getNamespaceList().removeIf(predicate);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ boolean removeNullElements() {
        return super.removeNullElements();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public void removeUndefinedAttributes() {
        removeAttributeIf(new Predicate() { // from class: hfc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResXmlAttribute) obj).isUndefined();
            }
        });
        super.removeUndefinedAttributes();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public void removeUnusedNamespaces() {
        removeNamespaceIf(new Predicate() { // from class: gfc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResXmlNamespace) obj).isUnused();
            }
        });
        super.removeUnusedNamespaces();
    }

    public ResXmlAttribute searchAttribute(String str, String str2) {
        return getAttributeArray().searchAttribute(str, str2);
    }

    public ResXmlAttribute searchAttributeByName(String str) {
        return getAttributeArray().searchAttributeByName(str);
    }

    public ResXmlAttribute searchAttributeByResourceId(int i) {
        return getAttributeArray().searchAttributeByResourceId(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void serialize(XmlSerializer xmlSerializer, boolean z) throws IOException {
        ResXmlNode.setIndent(xmlSerializer, true);
        Iterator<ResXmlNamespace> namespaces = getNamespaces();
        while (namespaces.hasNext()) {
            ResXmlNamespace next = namespaces.next();
            xmlSerializer.setPrefix(next.getPrefix(), next.getUri());
        }
        serializeComment(xmlSerializer, getStartComment());
        xmlSerializer.startTag(getUri(), getName(false));
        getAttributeArray().serialize(xmlSerializer, z);
        serializeNodes(xmlSerializer, z);
        xmlSerializer.endTag(getUri(), getName(false));
        serializeComment(xmlSerializer, getEndComment());
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public void setAttributesUnitSize(int i, boolean z) {
        getStartElement().getAttributeUnitSize().set(i);
        if (z) {
            super.setAttributesUnitSize(i, true);
        }
    }

    public void setComment(String str) {
        setStartComment(str);
    }

    public void setEndComment(String str) {
        getEndElement().setComment(str);
    }

    public void setEndLineNumber(int i) {
        getEndElement().setLineNumber(i);
    }

    public void setLineNumber(int i) {
        setStartLineNumber(i);
    }

    public void setName(String str) {
        getChunk().setName(str);
    }

    public void setNamespace(Namespace namespace) {
        if (namespace != null) {
            setNamespace(namespace.getUri(), namespace.getPrefix());
        } else {
            setNamespace(null, null);
        }
    }

    public void setStartComment(String str) {
        getStartElement().setComment(str);
    }

    public void setStartLineNumber(int i) {
        getStartElement().setLineNumber(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResXmlNode.JSON_node_type, nodeTypeName());
        jSONObject.put(ResXmlNode.JSON_namespaces, getNamespaceList().m65toJson());
        jSONObject.put(ResXmlNode.JSON_name, getName());
        jSONObject.put(ResXmlNode.JSON_uri, getUri());
        jSONObject.put(ResXmlNode.JSON_prefix, getPrefix());
        jSONObject.put(ResXmlNode.JSON_line, getStartLineNumber());
        jSONObject.put(ResXmlNode.JSON_line_end, getEndLineNumber());
        jSONObject.put(ResXmlNode.JSON_comment, getComment());
        jSONObject.put(ResXmlNode.JSON_attributes, getAttributeArray().m46toJson());
        jSONObject.put(ResXmlNode.JSON_nodes, nodesToJson());
        return jSONObject;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree, com.reandroid.arsc.container.WrappedBlock
    public String toString() {
        touchChildNodesForDebug();
        StringBuilder sb = new StringBuilder("<");
        sb.append(getName(true));
        Iterator<ResXmlNamespace> namespaces = getNamespaces();
        if (namespaces.hasNext()) {
            sb.append(' ');
            sb.append(StringsUtil.join(namespaces, ' '));
        }
        Iterator<ResXmlAttribute> attributes = getAttributes();
        if (attributes.hasNext()) {
            sb.append(' ');
            sb.append(StringsUtil.join(attributes, ' '));
        }
        sb.append('>');
        return sb.toString();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    /* JADX INFO: renamed from: toXml, reason: merged with bridge method [inline-methods] */
    public XMLElement mo52toXml(boolean z) {
        XMLElement xMLElement = new XMLElement();
        xMLElement.setName(getName(false));
        Iterator<ResXmlNamespace> namespaces = getNamespaces();
        while (namespaces.hasNext()) {
            ResXmlNamespace next = namespaces.next();
            xMLElement.addNamespace(next.getUri(), next.getPrefix());
        }
        xMLElement.setNamespace(m54getNamespace());
        getAttributeArray().toXml(xMLElement, z);
        return xMLElement;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator getElements(String str) {
        return super.getElements(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ int getElementsCount(String str) {
        return super.getElementsCount(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ ResXmlElement newElement(String str) {
        return super.newElement(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ ResXmlElement newElementAt(int i, String str) {
        return super.newElementAt(i, str);
    }

    /* JADX INFO: renamed from: newText, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Text m59newText() {
        return super.newText();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement, com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public /* bridge */ /* synthetic */ UnknownResXmlNode newUnknown(ChunkType chunkType) {
        return super.newUnknown(chunkType);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator recursiveAttributes(Predicate predicate) {
        return super.recursiveAttributes(predicate);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator recursiveElements(Predicate predicate) {
        return super.recursiveElements(predicate);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator getElements(Predicate predicate) {
        return super.getElements((Predicate<? super ResXmlElement>) predicate);
    }

    /* JADX INFO: renamed from: newElement, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Element m57newElement() {
        return super.newElement();
    }

    public Iterator<ResXmlAttribute> getAttributes(Predicate<? super ResXmlAttribute> predicate) {
        return getAttributeArray().iterator(predicate);
    }

    public String getName(boolean z) {
        return getStartElement().getName(z);
    }

    public ResXmlAttribute getOrCreateAttribute(String str, int i) {
        return getAttributeArray().getOrCreateAttribute(str, i);
    }

    public ResXmlAttribute newAttribute(String str, int i) {
        ResXmlAttribute resXmlAttributeM56newAttribute = m56newAttribute();
        resXmlAttributeM56newAttribute.setName(str, i);
        return resXmlAttributeM56newAttribute;
    }

    public ResXmlNamespace getNamespace(String str, String str2) {
        return getNamespaceList().get(str, str2);
    }

    public void setNamespace(String str, String str2) {
        getStartElement().setTagNamespace(str, str2);
    }
}

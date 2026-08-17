package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.common.Namespace;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.xml.XMLElement;
import com.reandroid.xml.XMLUtil;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlAttributeArray extends CountedBlockList<ResXmlAttribute> implements JSONConvert<JSONArray> {

    public static class AttributesCreator implements Creator<ResXmlAttribute> {
        private final IntegerReference unitSize;

        public AttributesCreator(IntegerReference integerReference) {
            this.unitSize = integerReference;
        }

        public ResXmlAttribute newInstance() {
            ResXmlAttribute resXmlAttribute = new ResXmlAttribute();
            resXmlAttribute.setAttributesUnitSize(this.unitSize.get());
            return resXmlAttribute;
        }
    }

    public ResXmlAttributeArray(IntegerReference integerReference, IntegerReference integerReference2) {
        super(new AttributesCreator(integerReference), integerReference2);
    }

    private void computePositionalAttributes() {
        ResXmlStartElement startElement = getStartElement();
        startElement.getIdAttributePosition().computePosition();
        startElement.getClassAttributePosition().computePosition();
        startElement.getStyleAttributePosition().computePosition();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ResXmlElement element() {
        return (ResXmlElement) getParentInstance(ResXmlElement.class);
    }

    private ResXmlAttributePosition getPosition(int i) {
        if (i != -1) {
            ResXmlStartElement startElement = getStartElement();
            if (i == ResXmlAttributePosition.TYPE_ID) {
                return startElement.getIdAttributePosition();
            }
            if (i == ResXmlAttributePosition.TYPE_CLASS) {
                return startElement.getClassAttributePosition();
            }
            if (i == ResXmlAttributePosition.TYPE_STYLE) {
                return startElement.getStyleAttributePosition();
            }
        }
        return (ResXmlAttributePosition) ObjectsUtil.getNull();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ResXmlStartElement getStartElement() {
        return (ResXmlStartElement) getParentInstance(ResXmlStartElement.class);
    }

    private void linkPositionalAttribute(ResXmlAttribute resXmlAttribute) {
        ResXmlAttributePosition position = getPosition(ResXmlAttributePosition.getPositionType(resXmlAttribute));
        if (position != null) {
            position.setAttribute(resXmlAttribute);
        }
    }

    private void mergePositionalAttribute(ResXmlAttribute resXmlAttribute, ResXmlAttributeArray resXmlAttributeArray, ResXmlAttribute resXmlAttribute2) {
        ResXmlAttributePosition position = resXmlAttributeArray.getPosition(resXmlAttribute2);
        if (position != null) {
            getPosition(position.type()).setAttribute(resXmlAttribute);
        }
    }

    public void clear() {
        clearChildes();
        updateCountReference();
    }

    public ResXmlAttribute createNext() {
        ResXmlAttribute resXmlAttribute = (ResXmlAttribute) super/*com.reandroid.arsc.container.BlockList*/.createNext();
        updateCountReference();
        return resXmlAttribute;
    }

    public void fromJson(JSONArray jSONArray) {
        if (jSONArray != null) {
            int size = size();
            int length = jSONArray.length();
            setSize(size + length);
            for (int i = 0; i < length; i++) {
                ((ResXmlAttribute) get(size + i)).fromJson(jSONArray.getJSONObject(i));
            }
            computePositionalAttributes();
            sort();
        }
    }

    public ResXmlAttribute getOrCreateAndroidAttribute(String str, int i) {
        return getOrCreateAttribute(Namespace.URI_ANDROID, Namespace.PREFIX_ANDROID, str, i);
    }

    public ResXmlAttribute getOrCreateAttribute(String str, String str2, String str3, int i) {
        ResXmlAttribute resXmlAttributeSearchAttribute = searchAttribute(str3, i);
        if (resXmlAttributeSearchAttribute != null) {
            return resXmlAttributeSearchAttribute;
        }
        ResXmlAttribute resXmlAttributeCreateNext = createNext();
        resXmlAttributeCreateNext.setName(str3, i);
        resXmlAttributeCreateNext.setNamespace(str, str2);
        linkPositionalAttribute(resXmlAttributeCreateNext);
        return resXmlAttributeCreateNext;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void merge(ResXmlAttributeArray resXmlAttributeArray) {
        Iterator it = resXmlAttributeArray.iterator();
        while (it.hasNext()) {
            ResXmlAttribute resXmlAttribute = (ResXmlAttribute) it.next();
            ResXmlAttribute resXmlAttributeCreateNext = createNext();
            resXmlAttributeCreateNext.merge(resXmlAttribute);
            mergePositionalAttribute(resXmlAttributeCreateNext, resXmlAttributeArray, resXmlAttribute);
        }
    }

    public void mergeWithName(ResourceMergeOption resourceMergeOption, ResXmlAttributeArray resXmlAttributeArray) {
        Iterator it = resXmlAttributeArray.iterator();
        while (it.hasNext()) {
            ResXmlAttribute resXmlAttribute = (ResXmlAttribute) it.next();
            ResXmlAttribute resXmlAttributeCreateNext = createNext();
            resXmlAttributeCreateNext.mergeWithName(resourceMergeOption, resXmlAttribute);
            mergePositionalAttribute(resXmlAttributeCreateNext, resXmlAttributeArray, resXmlAttribute);
        }
    }

    public void onPreRemove(ResXmlAttribute resXmlAttribute) {
        super/*com.reandroid.arsc.container.BlockList*/.onPreRemove(resXmlAttribute);
        ResXmlAttributePosition position = getPosition(resXmlAttribute);
        if (position != null) {
            position.setAttribute(null);
        }
        resXmlAttribute.onRemoved();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        int i = getCountReference().get();
        setSize(i);
        if (i != 0) {
            int i2 = getStartElement().getAttributeUnitSize().get();
            for (int i3 = 0; i3 < i; i3++) {
                ResXmlAttribute resXmlAttribute = (ResXmlAttribute) get(i3);
                resXmlAttribute.setAttributesUnitSize(i2);
                int position = blockReader.getPosition();
                resXmlAttribute.readBytes(blockReader);
                blockReader.seek(position + i2);
            }
        }
    }

    public void parse(XmlPullParser xmlPullParser) throws IOException {
        String attributeNamespace;
        ResXmlNamespace namespaceForPrefix;
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String strSplitPrefix = XMLUtil.splitPrefix(attributeName);
            String strSplitName = XMLUtil.splitName(attributeName);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (!Namespace.isValidNamespace(attributeValue, strSplitPrefix)) {
                if (strSplitPrefix == null) {
                    strSplitPrefix = StringsUtil.emptyToNull(xmlPullParser.getAttributePrefix(i));
                }
                String str = strSplitPrefix;
                if (str != null) {
                    attributeNamespace = xmlPullParser.getAttributeNamespace(i);
                    if (StringsUtil.isEmpty(attributeNamespace) && (namespaceForPrefix = element().getNamespaceForPrefix(str)) != null) {
                        attributeNamespace = namespaceForPrefix.getUri();
                    }
                } else {
                    attributeNamespace = null;
                }
                try {
                    createNext().encode(false, attributeNamespace, str, strSplitName, attributeValue);
                } catch (IOException e) {
                    throw new IOException(XMLUtil.getSimplePositionDescription(xmlPullParser) + "\n" + e.getMessage(), e);
                }
            }
        }
        if (attributeCount != 0) {
            computePositionalAttributes();
            sort();
        }
    }

    public ResXmlAttribute searchAttribute(String str, String str2) {
        int size = size();
        for (int i = 0; i < size; i++) {
            ResXmlAttribute resXmlAttribute = (ResXmlAttribute) get(i);
            if (resXmlAttribute.isEqual(str, str2)) {
                return resXmlAttribute;
            }
        }
        return null;
    }

    public ResXmlAttribute searchAttributeByName(String str) {
        ResXmlAttribute resXmlAttribute = null;
        if (str == null) {
            return null;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            ResXmlAttribute resXmlAttribute2 = (ResXmlAttribute) get(i);
            if (resXmlAttribute2.equalsName(str)) {
                if (resXmlAttribute2.getNameId() == 0) {
                    return resXmlAttribute2;
                }
                resXmlAttribute = resXmlAttribute2;
            }
        }
        return resXmlAttribute;
    }

    public ResXmlAttribute searchAttributeByResourceId(int i) {
        if (i == 0) {
            return null;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            ResXmlAttribute resXmlAttribute = (ResXmlAttribute) get(i2);
            if (i == resXmlAttribute.getNameId()) {
                return resXmlAttribute;
            }
        }
        return null;
    }

    public void serialize(XmlSerializer xmlSerializer, boolean z) throws IOException {
        if (z) {
            getStartElement().fixClassStyleAttributeNames();
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            ((ResXmlAttribute) get(i)).serialize(xmlSerializer, z);
        }
    }

    public void setSize(int i) {
        if (i != size()) {
            getCountReference().set(i);
            super/*com.reandroid.arsc.container.BlockList*/.setSize(i);
        }
    }

    public boolean sort(Comparator<? super ResXmlAttribute> comparator) {
        if (!super/*com.reandroid.arsc.container.BlockList*/.sort(comparator)) {
            return false;
        }
        getStartElement().refreshAttributePositions();
        return true;
    }

    public void toXml(XMLElement xMLElement, boolean z) {
        if (z) {
            getStartElement().fixClassStyleAttributeNames();
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            xMLElement.addAttribute(((ResXmlAttribute) get(i)).toXml(z));
        }
    }

    /* JADX INFO: renamed from: toJson, reason: merged with bridge method [inline-methods] */
    public JSONArray m46toJson() {
        return BlockList.toJsonArray(this);
    }

    public void sort() {
        sort(CompareUtil.getComparableComparator());
    }

    public ResXmlAttribute getOrCreateAttribute(String str, int i) {
        ResXmlAttribute resXmlAttributeSearchAttribute = searchAttribute(str, i);
        if (resXmlAttributeSearchAttribute != null) {
            return resXmlAttributeSearchAttribute;
        }
        ResXmlAttribute resXmlAttributeCreateNext = createNext();
        resXmlAttributeCreateNext.setName(str, i);
        linkPositionalAttribute(resXmlAttributeCreateNext);
        return resXmlAttributeCreateNext;
    }

    private ResXmlAttribute searchAttribute(String str, int i) {
        if (i == 0) {
            return searchAttributeByName(str);
        }
        return searchAttributeByResourceId(i);
    }

    private ResXmlAttributePosition getPosition(ResXmlAttribute resXmlAttribute) {
        ResXmlStartElement startElement = getStartElement();
        ResXmlAttributePosition idAttributePosition = startElement.getIdAttributePosition();
        if (idAttributePosition.getAttribute() == resXmlAttribute) {
            return idAttributePosition;
        }
        ResXmlAttributePosition classAttributePosition = startElement.getClassAttributePosition();
        if (classAttributePosition.getAttribute() == resXmlAttribute) {
            return classAttributePosition;
        }
        ResXmlAttributePosition styleAttributePosition = startElement.getStyleAttributePosition();
        if (styleAttributePosition.getAttribute() == resXmlAttribute) {
            return styleAttributePosition;
        }
        return null;
    }
}

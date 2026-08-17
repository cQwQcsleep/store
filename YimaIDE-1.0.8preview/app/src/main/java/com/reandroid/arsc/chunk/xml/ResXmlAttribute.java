package com.reandroid.arsc.chunk.xml;

import android.R;
import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.arsc.coder.ValueCoder;
import com.reandroid.arsc.coder.XmlSanitizer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ReferenceBlock;
import com.reandroid.arsc.item.ReferenceItem;
import com.reandroid.arsc.item.ResXmlID;
import com.reandroid.arsc.item.ResXmlString;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.pool.ResXmlStringPool;
import com.reandroid.arsc.pool.StringPool;
import com.reandroid.arsc.refactor.ResourceMergeOption;
import com.reandroid.arsc.value.AttributeValue;
import com.reandroid.arsc.value.ValueItem;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.arsc.value.attribute.AttributeBag;
import com.reandroid.common.Namespace;
import com.reandroid.json.JSONException;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.xml.XMLAttribute;
import com.reandroid.xml.XMLUtil;
import com.reandroid.xml.base.Attribute;
import defpackage.efc;
import java.io.IOException;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlAttribute extends AttributeValue implements Attribute, Comparable<ResXmlAttribute> {
    private static final int OFFSET_NAME = 4;
    private static final int OFFSET_NS = 0;
    private static final int OFFSET_SIZE = 12;
    private static final int OFFSET_STRING = 8;
    private ResXmlStartNamespace mLinkedNamespace;
    private ReferenceItem mNSReference;
    private ReferenceItem mNameIdReference;
    private ReferenceItem mNameReference;
    private ReferenceItem mValueStringReference;
    public static final int ATTRIBUTE_RESOURCE_ID_id = ObjectsUtil.of(R.attr.id);
    public static final String ATTRIBUTE_NAME_CLASS = ObjectsUtil.of("class");
    public static final String ATTRIBUTE_NAME_STYLE = ObjectsUtil.of("style");

    public ResXmlAttribute(int i) {
        super(i, OFFSET_SIZE);
        byte[] bytesInternal = getBytesInternal();
        Block.putInteger(bytesInternal, 0, -1);
        Block.putInteger(bytesInternal, 4, -1);
        Block.putInteger(bytesInternal, 8, -1);
    }

    private boolean autoSetNamespace(ResourceEntry resourceEntry) {
        if (resourceEntry == null) {
            return false;
        }
        PackageBlock packageBlock = resourceEntry.getPackageBlock();
        String prefix = getPrefix();
        String uri = getUri();
        String name = packageBlock.getName();
        if (!packageBlock.isMultiPackage() && Namespace.isValidPrefix(prefix, name) && Namespace.isValidUri(uri, name)) {
            return false;
        }
        return setNamespace(packageBlock.getUri(), packageBlock.getPrefix());
    }

    private String buildErrorMessage(String str, String str2) {
        ResXmlElement parentElement = getParentElement();
        return str + ", at line = " + parentElement.getLineNumber() + ", <" + parentElement.getName(true) + " " + getName(true) + "=\"" + str2 + "\"";
    }

    private ResXmlString getOrCreateAttributeName(String str, int i) {
        ResXmlStringPool stringPool = getStringPool();
        if (stringPool == null) {
            return null;
        }
        return stringPool.getOrCreate(i, str);
    }

    private ResXmlID getResXmlID() {
        ResXmlString resXmlString = (ResXmlString) getStringItem(getNameReference());
        if (resXmlString != null) {
            return resXmlString.getResXmlID();
        }
        return null;
    }

    private ResXmlIDMap getResXmlIDMap() {
        ResXmlDocument parentDocument;
        ResXmlElement parentElement = getParentElement();
        if (parentElement == null || (parentDocument = parentElement.getParentDocument()) == null) {
            return null;
        }
        return parentDocument.getResXmlIDMap();
    }

    private ResXmlStartNamespace getStartNamespace() {
        int namespaceReference = getNamespaceReference();
        if (namespaceReference < 0) {
            return null;
        }
        ResXmlStartNamespace resXmlStartNamespace = this.mLinkedNamespace;
        if (resXmlStartNamespace != null && resXmlStartNamespace.getUriReference() == namespaceReference) {
            return resXmlStartNamespace;
        }
        ResXmlElement parentElement = getParentElement();
        if (parentElement != null) {
            return (ResXmlStartNamespace) parentElement.getNamespaceForUriReference(namespaceReference);
        }
        return null;
    }

    private String getString(int i) {
        StringItem stringItem;
        if (getStringPool() == null || (stringItem = getStringItem(i)) == null) {
            return null;
        }
        return stringItem.getHtml();
    }

    private StringItem getStringItem(int i) {
        ResXmlStringPool stringPool;
        if (i >= 0 && (stringPool = getStringPool()) != null) {
            return stringPool.get(i);
        }
        return null;
    }

    private ReferenceItem link(int i) {
        ResXmlStringPool stringPool;
        ResXmlString resXmlString;
        if (i < 0 || (stringPool = getStringPool()) == null || (resXmlString = stringPool.get(Block.getInteger(getBytesInternal(), i))) == null) {
            return null;
        }
        ReferenceBlock referenceBlock = new ReferenceBlock(this, i);
        resXmlString.addReference(referenceBlock);
        return referenceBlock;
    }

    private void linkAll() {
        unlink(this.mNSReference);
        this.mNSReference = link(0);
        unlink(this.mNameReference);
        this.mNameReference = link(4);
        unlink(this.mValueStringReference);
        this.mValueStringReference = link(8);
        linkNameId();
    }

    private void linkNameId() {
        ResXmlID resXmlID = getResXmlID();
        if (resXmlID == null) {
            return;
        }
        if (this.mNameIdReference == null || !resXmlID.hasReference(this)) {
            unLinkNameId(resXmlID);
            ReferenceBlock referenceBlock = new ReferenceBlock(this, 4);
            resXmlID.addReference(referenceBlock);
            this.mNameIdReference = referenceBlock;
        }
    }

    private void linkStartNameSpace() {
        unLinkStartNameSpace();
        ResXmlStartNamespace startNamespace = getStartNamespace();
        if (startNamespace == null) {
            return;
        }
        this.mLinkedNamespace = startNamespace;
        startNamespace.addAttributeReference(this);
    }

    private void logEncodeError(EncodeResult encodeResult, String str) {
        System.out.println(buildErrorMessage(encodeResult.getError(), str));
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

    private void unLinkNameId(ResXmlID resXmlID) {
        ResXmlIDMap resXmlIDMap;
        ReferenceItem referenceItem = this.mNameIdReference;
        if (referenceItem == null || resXmlID == null) {
            return;
        }
        resXmlID.removeReference(referenceItem);
        this.mNameIdReference = null;
        if (resXmlID.hasReference() || (resXmlIDMap = getResXmlIDMap()) == null) {
            return;
        }
        resXmlIDMap.removeSafely(resXmlID);
    }

    private void unLinkStartNameSpace() {
        ResXmlStartNamespace resXmlStartNamespace = this.mLinkedNamespace;
        if (resXmlStartNamespace == null) {
            return;
        }
        this.mLinkedNamespace = null;
        resXmlStartNamespace.removeAttributeReference(this);
    }

    private void unlink(ReferenceItem referenceItem) {
        ResXmlStringPool stringPool;
        if (referenceItem == null || (stringPool = getStringPool()) == null) {
            return;
        }
        stringPool.removeReference(referenceItem);
    }

    private void unlinkAll() {
        unlink(this.mNSReference);
        unlink(this.mNameReference);
        unlink(this.mValueStringReference);
        this.mNSReference = null;
        this.mNameReference = null;
        this.mValueStringReference = null;
        unLinkNameId(getResXmlID());
    }

    public boolean autoSetName(boolean z) {
        String name;
        int nameId = getNameId();
        if (nameId == 0) {
            if (z) {
                String uri = getUri();
                if (!Namespace.isExternalUri(uri) || !Namespace.isValidUri(uri) || !Namespace.isValidPrefix(getPrefix())) {
                    return setNamespace(null, null);
                }
            }
            return false;
        }
        ResourceEntry resourceEntryResolveName = resolveName();
        if (resourceEntryResolveName == null || resourceEntryResolveName.isEmpty() || (name = resourceEntryResolveName.getName()) == null) {
            return false;
        }
        boolean zAutoSetNamespace = autoSetNamespace(resourceEntryResolveName);
        String name2 = getName();
        setName(name, nameId);
        return zAutoSetNamespace || Objects.equals(name2, name);
    }

    @Override // java.lang.Comparable
    public int compareTo(ResXmlAttribute resXmlAttribute) {
        int nameId = getNameId();
        int nameId2 = resXmlAttribute.getNameId();
        if (nameId == 0 && nameId2 != 0) {
            return 1;
        }
        if (nameId2 == 0 && nameId != 0) {
            return -1;
        }
        if (nameId != 0) {
            return Integer.compare(nameId, nameId2);
        }
        String name = getName();
        String str = XmlPullParser.NO_NAMESPACE;
        if (name == null) {
            name = XmlPullParser.NO_NAMESPACE;
        }
        String name2 = resXmlAttribute.getName();
        if (name2 != null) {
            str = name2;
        }
        return name.compareTo(str);
    }

    @Override // com.reandroid.arsc.value.AttributeValue
    public String decodeName(boolean z) {
        String strDecodePrefix;
        String strDecodePrefix2;
        String strDecodePrefix3;
        int nameId = getNameId();
        if (nameId == 0) {
            String name = getName(false);
            if (!z || (strDecodePrefix3 = decodePrefix()) == null) {
                return name;
            }
            return strDecodePrefix3 + ":" + name;
        }
        ResourceEntry resourceEntryResolveName = resolveName();
        if (resourceEntryResolveName == null || !resourceEntryResolveName.isDeclared()) {
            String strDecodeUnknownNameId = ValueCoder.decodeUnknownNameId(nameId);
            if (!z || (strDecodePrefix = decodePrefix()) == null) {
                return strDecodeUnknownNameId;
            }
            return strDecodePrefix + ":" + strDecodeUnknownNameId;
        }
        String name2 = resourceEntryResolveName.getName();
        if (!z || name2 == null || (strDecodePrefix2 = decodePrefix()) == null) {
            return name2;
        }
        return strDecodePrefix2 + ":" + name2;
    }

    @Override // com.reandroid.arsc.value.AttributeValue
    public String decodePrefix() {
        int nameId = getNameId();
        String prefix = getPrefix();
        if (nameId != 0) {
            ResourceEntry resourceEntryResolveName = resolveName();
            if (resourceEntryResolveName == null) {
                return Namespace.prefixForResourceId(nameId);
            }
            PackageBlock packageBlock = resourceEntryResolveName.getPackageBlock();
            if (packageBlock.isMultiPackage() || !Namespace.isValidPrefix(prefix, packageBlock.getName())) {
                return packageBlock.getPrefix();
            }
        } else if (!Namespace.isValidPrefix(prefix) || !Namespace.isExternalUri(getUri())) {
            return null;
        }
        return prefix;
    }

    @Deprecated
    public XMLAttribute decodeToXml() {
        return toXml(true);
    }

    public String decodeUri() {
        String uri = getUri();
        int nameId = getNameId();
        if (nameId != 0) {
            ResourceEntry resourceEntryResolveName = resolveName();
            if (!Namespace.isValidUri(uri, nameId)) {
                return resourceEntryResolveName == null ? Namespace.uriForResourceId(nameId) : resourceEntryResolveName.getPackageBlock().getUri();
            }
        } else if (!Namespace.isExternalUri(uri)) {
            return null;
        }
        return uri;
    }

    public void encode(String str, String str2, String str3, String str4, boolean z) throws IOException {
        ResourceEntry resourceEntryEncodeAttributeName = encodeAttributeName(str, str2, str3);
        EncodeResult encodeResultEncodeReference = ValueCoder.encodeReference(getPackageBlock(), str4);
        if (encodeResultEncodeReference != null) {
            if (encodeResultEncodeReference.isError()) {
                a16.a(buildErrorMessage(encodeResultEncodeReference.getError(), str4));
                return;
            } else {
                setValue(encodeResultEncodeReference);
                return;
            }
        }
        if (resourceEntryEncodeAttributeName != null) {
            resourceEntryEncodeAttributeName = resourceEntryEncodeAttributeName.resolveReference();
        }
        if (resourceEntryEncodeAttributeName == null || resourceEntryEncodeAttributeName.isEmpty()) {
            EncodeResult encodeResultEncode = ValueCoder.encode(str4);
            if (encodeResultEncode != null) {
                setValue(encodeResultEncode);
                return;
            } else {
                setValueAsString(XmlSanitizer.unEscapeSpecialCharacter(str4));
                return;
            }
        }
        EncodeResult encodeResultEncode2 = AttributeBag.create(resourceEntryEncodeAttributeName.get()).encode(str4);
        if (encodeResultEncode2 != null) {
            if (encodeResultEncode2.valueType == ValueType.STRING) {
                setValueAsString(XmlSanitizer.unEscapeSpecialCharacter(str4));
                return;
            } else if (!encodeResultEncode2.isError()) {
                setValue(encodeResultEncode2);
                return;
            } else {
                if (z) {
                    a16.a(buildErrorMessage(encodeResultEncode2.getError(), str4));
                    return;
                }
                logEncodeError(encodeResultEncode2, str4);
            }
        }
        EncodeResult encodeResultEncode3 = ValueCoder.encode(str4);
        if (encodeResultEncode3 != null) {
            setValue(encodeResultEncode3);
        } else {
            setValueAsString(XmlSanitizer.unEscapeSpecialCharacter(str4));
        }
    }

    public ResourceEntry encodeAttributeName(String str, String str2, String str3) throws IOException {
        setNamespace(str, str2);
        if (!Namespace.isValidUri(str) || Namespace.isExternalUri(str)) {
            setName(str3, 0);
            return null;
        }
        ResourceEntry resourceEntryEncodeAttrName = super.encodeAttrName(str2, str3);
        if (resourceEntryEncodeAttrName != null) {
            return resourceEntryEncodeAttrName;
        }
        String strEmptyToNull = StringsUtil.emptyToNull(str2);
        if (strEmptyToNull == null) {
            setName(str3, 0);
            return null;
        }
        wba.a("Unknown attribute name '", strEmptyToNull, ":", str3, "'");
        return null;
    }

    public boolean equalsName(String str) {
        if (str == null) {
            return getName() == null;
        }
        String strSplitPrefix = XMLUtil.splitPrefix(str);
        if (strSplitPrefix == null || strSplitPrefix.equals(getPrefix())) {
            return XMLUtil.splitName(str).equals(getName());
        }
        return false;
    }

    public boolean equalsNameId(int i) {
        return i != 0 && i == getNameId();
    }

    public boolean equalsNameWithNoId(String str) {
        return getNameId() == 0 && equalsName(str);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.json.JSONException */
    @Override // com.reandroid.arsc.value.ValueItem
    public void fromJson(JSONObject jSONObject) throws JSONException {
        setName(jSONObject.optString(ResXmlNode.JSON_name, XmlPullParser.NO_NAMESPACE), jSONObject.optInt(ResXmlNode.JSON_id, 0));
        setNamespaceFromJson(jSONObject);
        super.fromJson(jSONObject);
    }

    public int getAttributesUnitSize() {
        return super.getSize() + OFFSET_SIZE;
    }

    public int getLineNumber() {
        return m43getParentNode().getLineNumber();
    }

    public String getName(boolean z) {
        String prefix;
        String name = getName();
        if (name == null || !z || (prefix = getPrefix()) == null) {
            return name;
        }
        return prefix + ":" + name;
    }

    @Override // com.reandroid.arsc.value.AttributeValue
    public int getNameId() {
        ResXmlID resXmlID = getResXmlID();
        if (resXmlID != null) {
            return resXmlID.get();
        }
        return 0;
    }

    public int getNameReference() {
        return Block.getInteger(getBytesInternal(), 4);
    }

    public int getNamespaceReference() {
        return Block.getInteger(getBytesInternal(), 0);
    }

    public ResXmlDocument getParentChunk() {
        ResXmlElement parentElement = getParentElement();
        if (parentElement != null) {
            return parentElement.getParentDocument();
        }
        return null;
    }

    public ResXmlElement getParentElement() {
        return (ResXmlElement) getParent(ResXmlElement.class);
    }

    /* JADX INFO: renamed from: getParentNode, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ResXmlElement m43getParentNode() {
        return (ResXmlElement) getParentInstance(ResXmlElement.class);
    }

    public String getPrefix() {
        ResXmlStartNamespace startNamespace = getStartNamespace();
        if (startNamespace != null) {
            return startNamespace.getPrefix();
        }
        return null;
    }

    @Override // com.reandroid.arsc.value.ValueItem
    public ResXmlStringPool getStringPool() {
        StringPool<?> stringPool = super.getStringPool();
        if (stringPool instanceof ResXmlStringPool) {
            return (ResXmlStringPool) stringPool;
        }
        return null;
    }

    public String getUri() {
        return getString(getNamespaceReference());
    }

    public String getValueString() {
        return getString(getValueStringReference());
    }

    public int getValueStringReference() {
        return Block.getInteger(getBytesInternal(), 8);
    }

    public boolean isEqual(String str, String str2) {
        if (str2 == null || !ObjectsUtil.equals(getUri(), str)) {
            return false;
        }
        String strSplitName = XMLUtil.splitName(str2);
        return strSplitName.equals(getName(false)) || strSplitName.equals(decodeName(false));
    }

    @Override // com.reandroid.arsc.value.ValueItem
    public boolean isUndefined() {
        return getNameReference() < 0;
    }

    @Override // com.reandroid.arsc.value.ValueItem
    public void merge(ValueItem valueItem) {
        super.merge(valueItem);
        ResXmlAttribute resXmlAttribute = (ResXmlAttribute) valueItem;
        setName(resXmlAttribute.getName(false), resXmlAttribute.getNameId());
        setNamespace(resXmlAttribute.m40getNamespace());
    }

    @Override // com.reandroid.arsc.value.AttributeValue, com.reandroid.arsc.value.ValueItem
    public void mergeWithName(ResourceMergeOption resourceMergeOption, ValueItem valueItem) {
        super.mergeWithName(resourceMergeOption, valueItem);
        setNamespace(((ResXmlAttribute) valueItem).m40getNamespace());
    }

    @Override // com.reandroid.arsc.value.ValueItem
    public void onDataChanged() {
        if (getValueType() == ValueType.STRING) {
            setValueStringReference(getData());
        } else {
            setValueStringReference(-1);
        }
    }

    @Override // com.reandroid.arsc.value.ValueItem, com.reandroid.arsc.item.BlockItem, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        super.onDataLoaded();
        linkAll();
        linkStartNameSpace();
    }

    @Override // com.reandroid.arsc.value.ValueItem
    public void onRemoved() {
        super.onRemoved();
        unLinkStartNameSpace();
        unlinkAll();
    }

    @Override // com.reandroid.arsc.value.ValueItem
    public void onUnlinkDataString(ReferenceItem referenceItem) {
        unlink(referenceItem);
    }

    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    public boolean removeSelf() {
        ResXmlElement parentElement = getParentElement();
        if (parentElement != null) {
            return parentElement.removeAttribute(this);
        }
        return false;
    }

    public void serialize(XmlSerializer xmlSerializer, boolean z) throws IOException {
        String strDecodeValue;
        if (getValueType() == ValueType.STRING) {
            String valueAsString = getValueAsString();
            if (valueAsString == null) {
                return;
            }
            strDecodeValue = XmlSanitizer.escapeSpecialCharacter(valueAsString);
            if (getNameId() == 0 || resolveName() == null) {
                strDecodeValue = XmlSanitizer.escapeDecodedValue(strDecodeValue);
            }
        } else {
            strDecodeValue = decodeValue(z);
        }
        xmlSerializer.attribute(z ? decodeUri() : getUri(), z ? decodeName(false) : getName(false), strDecodeValue);
    }

    public void setAttributesUnitSize(int i) {
        super.setSize(i - 12);
    }

    public void setLineNumber(int i) {
    }

    @Override // com.reandroid.arsc.value.AttributeValue
    public void setName(String str, int i) {
        if (ObjectsUtil.equals(str, getName()) && i == getNameId()) {
            return;
        }
        unlink(this.mNameReference);
        unLinkNameId(getResXmlID());
        ResXmlString orCreateAttributeName = getOrCreateAttributeName(str, i);
        if (orCreateAttributeName == null) {
            return;
        }
        setNameReference(orCreateAttributeName.getIndex());
        this.mNameReference = link(4);
        linkNameId();
    }

    @Override // com.reandroid.arsc.value.AttributeValue
    public void setNameId(int i) {
        ResXmlIDMap resXmlIDMap = getResXmlIDMap();
        if (resXmlIDMap == null) {
            return;
        }
        setNameReference(resXmlIDMap.getOrCreate(i).getIndex());
    }

    public void setNameReference(int i) {
        if (i == getNameReference()) {
            return;
        }
        unLinkNameId(getResXmlID());
        unlink(this.mNameReference);
        Block.putInteger(getBytesInternal(), 4, i);
        this.mNameReference = link(4);
        linkNameId();
    }

    public boolean setNamespace(String str, String str2) {
        ResXmlNamespace namespaceForUri;
        String strEmptyToNull = StringsUtil.emptyToNull(str);
        String strEmptyToNull2 = StringsUtil.emptyToNull(str2);
        if (strEmptyToNull == null && strEmptyToNull2 == null) {
            return setNamespaceReference(-1);
        }
        ResXmlElement parentElement = getParentElement();
        if (parentElement == null) {
            return false;
        }
        if (strEmptyToNull == null || strEmptyToNull2 == null) {
            namespaceForUri = strEmptyToNull != null ? parentElement.getNamespaceForUri(strEmptyToNull) : parentElement.getNamespaceForPrefix(strEmptyToNull2);
        } else {
            namespaceForUri = parentElement.getOrCreateNamespace(strEmptyToNull, strEmptyToNull2);
        }
        if (namespaceForUri == null) {
            return false;
        }
        return setNamespaceReference(namespaceForUri.getUriReference());
    }

    public boolean setNamespaceReference(int i) {
        if (i == getNamespaceReference()) {
            return false;
        }
        setUriReference(i);
        linkStartNameSpace();
        return true;
    }

    public void setUriReference(int i) {
        StringItem stringItem = getStringItem(getNamespaceReference());
        Block.putInteger(getBytesInternal(), 0, i);
        if (stringItem != null) {
            stringItem.removeReference(this.mNSReference);
        }
        this.mNSReference = link(0);
    }

    public void setValueStringReference(int i) {
        ResXmlStringPool stringPool;
        ReferenceBlock referenceBlock;
        if ((i != getValueStringReference() || this.mValueStringReference == null) && (stringPool = getStringPool()) != null) {
            ResXmlString resXmlString = stringPool.get(i);
            unlink(this.mValueStringReference);
            if (resXmlString != null) {
                i = resXmlString.getIndex();
            }
            Block.putInteger(getBytesInternal(), 8, i);
            if (resXmlString != null) {
                referenceBlock = new ReferenceBlock(this, 8);
                resXmlString.addReference(referenceBlock);
            } else {
                referenceBlock = null;
            }
            this.mValueStringReference = referenceBlock;
        }
    }

    @Override // com.reandroid.arsc.value.ValueItem
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResXmlNode.JSON_name, getName());
        jSONObject.put(ResXmlNode.JSON_id, getNameId());
        jSONObject.put(ResXmlNode.JSON_uri, getUri());
        jSONObject.put(ResXmlNode.JSON_prefix, getPrefix());
        JSONObject json = super.toJson();
        for (String str : json.keySet()) {
            jSONObject.put(str, json.get(str));
        }
        return jSONObject;
    }

    @Override // com.reandroid.arsc.value.ValueItem
    public String toString() {
        String strValueOf;
        String name = getName(true);
        if (name == null) {
            return getClass().getSimpleName() + ": " + getIndex() + "{NamespaceReference=" + getNamespaceReference() + ", NameReference=" + getNameReference() + ", ValueStringReference=" + getValueStringReference() + ", ValueSize=" + getSize() + ", ValueTypeByte=" + (getType() & 255) + ", Data=" + getData() + "}";
        }
        int nameId = getNameId();
        if (nameId != 0) {
            name = name + "(@" + HexUtil.toHex8(nameId) + ")";
        }
        ValueType valueType = getValueType();
        if (valueType == ValueType.STRING) {
            strValueOf = getValueAsString();
        } else if (valueType == ValueType.BOOLEAN) {
            strValueOf = String.valueOf(getValueAsBoolean());
        } else if (valueType == ValueType.DEC) {
            strValueOf = String.valueOf(getData());
        } else {
            strValueOf = "[" + valueType + "] " + HexUtil.toHex8(getData());
        }
        if (strValueOf != null) {
            return name + "=\"" + strValueOf + "\"";
        }
        return name + "[" + valueType + "]=\"" + getData() + "\"";
    }

    public XMLAttribute toXml(boolean z) {
        return z ? new XMLAttribute(decodeName(false), decodeValue()) : new XMLAttribute(getName(false), decodeValue(false));
    }

    /* JADX INFO: renamed from: getNamespace, reason: merged with bridge method [inline-methods] */
    public ResXmlNamespace m40getNamespace() {
        return getStartNamespace();
    }

    public ResXmlAttribute() {
        this(20);
    }

    public XMLAttribute toXml() {
        return toXml(false);
    }

    public String getName() {
        return getString(getNameReference());
    }

    public boolean autoSetNamespace(boolean z) {
        if (getNameId() != 0) {
            return autoSetNamespace(resolveName());
        }
        if (!z) {
            return false;
        }
        String uri = getUri();
        if (Namespace.isExternalUri(uri) && Namespace.isValidUri(uri) && Namespace.isValidPrefix(getPrefix())) {
            return false;
        }
        return setNamespace(null, null);
    }

    public void setName(String str) {
        setName(str, 0);
    }

    public void setNamespace(Namespace namespace) {
        if (namespace != null) {
            setNamespace(namespace.getUri(), namespace.getPrefix());
        } else {
            setNamespace(null, null);
        }
    }

    public boolean autoSetNamespace() {
        return autoSetNamespace(true);
    }

    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        serialize(xmlSerializer, true);
    }

    public boolean autoSetName() {
        return autoSetName(true);
    }

    public void encode(boolean z, String str, String str2, String str3, String str4) throws IOException {
        EncodeResult encodeResultEncodeStyleValue = super.encodeStyleValue(z, encodeAttributeName(str, str2, str3), str4);
        if (encodeResultEncodeStyleValue.isError()) {
            a16.a(buildErrorMessage(encodeResultEncodeStyleValue.getError(), str4));
        }
    }
}

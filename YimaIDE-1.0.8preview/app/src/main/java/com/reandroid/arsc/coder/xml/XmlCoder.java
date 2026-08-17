package com.reandroid.arsc.coder.xml;

import com.reandroid.arsc.array.ResValueMapArray;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.coder.CoderSetting;
import com.reandroid.arsc.coder.EncodeResult;
import com.reandroid.arsc.coder.ValueCoder;
import com.reandroid.arsc.coder.XmlSanitizer;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.item.TypeString;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.AttributeDataFormat;
import com.reandroid.arsc.value.AttributeType;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.arsc.value.ResTableMapEntry;
import com.reandroid.arsc.value.ResValue;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.arsc.value.ValueHeader;
import com.reandroid.arsc.value.ValueType;
import com.reandroid.utils.io.IOUtil;
import com.reandroid.xml.StyleDocument;
import com.reandroid.xml.XMLAttribute;
import com.reandroid.xml.XMLElement;
import com.reandroid.xml.XMLFactory;
import com.reandroid.xml.XMLUtil;
import defpackage.o0g;
import defpackage.p0g;
import defpackage.q0g;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class XmlCoder {
    static final String ATTR_formats = "formats";
    static final String ATTR_name = "name";
    static final String ATTR_parent = "parent";
    static final String ATTR_quantity = "quantity";
    static final String ATTR_type = "type";
    static final String ATTR_value = "value";
    static final String TAG_item = "item";
    private static XmlCoder sInstance;
    public final ValuesXml VALUES_XML = new ValuesXml(this);
    private CoderSetting setting;

    /* JADX INFO: renamed from: com.reandroid.arsc.coder.xml.XmlCoder$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$reandroid$arsc$coder$xml$XmlCoder$ChildType;

        static {
            int[] iArr = new int[ChildType.values().length];
            $SwitchMap$com$reandroid$arsc$coder$xml$XmlCoder$ChildType = iArr;
            try {
                iArr[ChildType.ATTR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$reandroid$arsc$coder$xml$XmlCoder$ChildType[ChildType.ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$reandroid$arsc$coder$xml$XmlCoder$ChildType[ChildType.PLURAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$reandroid$arsc$coder$xml$XmlCoder$ChildType[ChildType.STYLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static class BagChild {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final ValuesXml valuesXml;

        public BagChild(ValuesXml valuesXml) {
            this.valuesXml = valuesXml;
        }

        private void endTag(XmlSerializer xmlSerializer, String str) throws IOException {
            xmlSerializer.endTag(null, str);
        }

        private AttributeDataFormat getParentArrayType(XMLElement xMLElement) {
            String name;
            int iIndexOf;
            XMLElement parentElement = xMLElement.getParentElement();
            if (parentElement != null && (iIndexOf = (name = parentElement.getName()).indexOf(45)) >= 0) {
                return AttributeDataFormat.fromValueTypeName(name.substring(0, iIndexOf));
            }
            return null;
        }

        private String getValue(XMLElement xMLElement) {
            String attributeValue = xMLElement.getAttributeValue(XmlCoder.ATTR_value);
            if (attributeValue != null) {
                return attributeValue;
            }
            String textContent = xMLElement.getTextContent();
            return textContent != null ? textContent.trim() : textContent;
        }

        private void startTag(XmlSerializer xmlSerializer, String str) throws IOException {
            XmlDecodeUtil.bagIndent(xmlSerializer);
            xmlSerializer.startTag(null, str);
        }

        public int decode(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            int i = AnonymousClass1.$SwitchMap$com$reandroid$arsc$coder$xml$XmlCoder$ChildType[ChildType.getType(entry).ordinal()];
            if (i == 1) {
                return decodeAttr(xmlSerializer, entry);
            }
            if (i == 2) {
                return decodeArray(xmlSerializer, entry);
            }
            if (i == 3) {
                return decodePlural(xmlSerializer, entry);
            }
            if (i != 4) {
                return 0;
            }
            return decodeStyle(xmlSerializer, entry);
        }

        public int decodeArray(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            boolean zEquals = "array".equals(entry.getXmlTag());
            int i = 0;
            for (ResValueMap resValueMap : entry.getResTableMapEntry().getValue()) {
                startTag(xmlSerializer, XmlCoder.TAG_item);
                if (resValueMap.getValueType() == ValueType.STRING) {
                    getStringDecoder().serializeText(resValueMap.getDataAsPoolString(), xmlSerializer);
                } else {
                    resValueMap.serializeText(xmlSerializer, zEquals);
                }
                endTag(xmlSerializer, XmlCoder.TAG_item);
                i++;
            }
            return i;
        }

        public int decodeAttr(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            ResTableMapEntry<ResValueMap> resTableMapEntry = entry.getResTableMapEntry();
            AttributeDataFormat attributeDataFormatTypeOfBag = AttributeDataFormat.typeOfBag(resTableMapEntry.getByType(AttributeType.FORMATS).getData());
            int i = 0;
            for (ResValueMap resValueMap : resTableMapEntry) {
                if (resValueMap.getAttributeType() == null) {
                    startTag(xmlSerializer, attributeDataFormatTypeOfBag.getName());
                    xmlSerializer.attribute(null, "name", resValueMap.decodeName());
                    xmlSerializer.attribute(null, XmlCoder.ATTR_value, resValueMap.decodeValue());
                    endTag(xmlSerializer, attributeDataFormatTypeOfBag.getName());
                    i++;
                }
            }
            return i;
        }

        public int decodePlural(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            int i = 0;
            for (ResValueMap resValueMap : entry.getResTableMapEntry().getValue()) {
                AttributeType attributeType = resValueMap.getAttributeType();
                if (attributeType != null) {
                    startTag(xmlSerializer, XmlCoder.TAG_item);
                    xmlSerializer.attribute(null, XmlCoder.ATTR_quantity, attributeType.getName());
                    if (resValueMap.getValueType() == ValueType.STRING) {
                        getStringDecoder().serializeText(resValueMap.getDataAsPoolString(), xmlSerializer);
                    } else {
                        resValueMap.serializeText(xmlSerializer);
                    }
                    endTag(xmlSerializer, XmlCoder.TAG_item);
                    i++;
                }
            }
            return i;
        }

        public int decodeStyle(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            int i = 0;
            for (ResValueMap resValueMap : entry.getResTableMapEntry().getValue()) {
                startTag(xmlSerializer, XmlCoder.TAG_item);
                boolean z = true;
                String strDecodeName = resValueMap.decodeName(true);
                if (strDecodeName == null) {
                    strDecodeName = ValueCoder.decodeUnknownNameId(resValueMap.getNameId());
                } else {
                    z = false;
                }
                xmlSerializer.attribute(null, "name", strDecodeName);
                if (resValueMap.getValueType() == ValueType.STRING) {
                    getStringDecoder().serializeText(resValueMap.getDataAsPoolString(), xmlSerializer);
                } else {
                    resValueMap.serializeText(xmlSerializer, z);
                }
                endTag(xmlSerializer, XmlCoder.TAG_item);
                i++;
            }
            return i;
        }

        public void encode(XMLElement xMLElement, Entry entry) throws IOException {
            ChildType type = ChildType.getType(xMLElement);
            if (type == null) {
                o0g.a("Unknown child bag: ", xMLElement.getDebugText());
                return;
            }
            int i = AnonymousClass1.$SwitchMap$com$reandroid$arsc$coder$xml$XmlCoder$ChildType[type.ordinal()];
            if (i == 1) {
                encodeAttr(xMLElement, entry);
                return;
            }
            if (i == 2) {
                encodeArray(xMLElement, entry);
            } else if (i == 3) {
                encodePlural(xMLElement, entry);
            } else {
                if (i != 4) {
                    return;
                }
                encodeStyle(xMLElement, entry);
            }
        }

        public void encodeArray(XMLElement xMLElement, Entry entry) throws IOException {
            entry.ensureComplex(true);
            ResValueMap resValueMapCreateNext = entry.getResValueMapArray().createNext();
            resValueMapCreateNext.setArrayIndex();
            String textContent = xMLElement.getTextContent();
            EncodeResult encodeResultEncodeReference = ValueCoder.encodeReference(entry.getPackageBlock(), textContent);
            if (encodeResultEncodeReference == null) {
                encodeResultEncodeReference = ValueCoder.encode(textContent, getParentArrayType(xMLElement));
            }
            if (encodeResultEncodeReference == null) {
                resValueMapCreateNext.setValueAsString(StyleDocument.copyInner(xMLElement));
            } else if (encodeResultEncodeReference.isError()) {
                o0g.a("Unexpected array value: ", xMLElement.getDebugText());
            } else {
                resValueMapCreateNext.setValue(encodeResultEncodeReference);
            }
        }

        public void encodeAttr(XMLElement xMLElement, Entry entry) throws IOException {
            AttributeDataFormat attributeDataFormatFromBagTypeName = AttributeDataFormat.fromBagTypeName(xMLElement.getName());
            entry.ensureComplex(true);
            ResValueMapArray resValueMapArray = entry.getResValueMapArray();
            resValueMapArray.getOrCreateType(AttributeType.FORMATS).addAttributeTypeFormat(attributeDataFormatFromBagTypeName);
            ResValueMap resValueMapCreateNext = resValueMapArray.createNext();
            XMLAttribute attribute = xMLElement.getAttribute("name");
            if (resValueMapCreateNext.encodeIdName(attribute.getPrefix(), attribute.getValueAsString()) == null) {
                q0g.a("Unknown ", attributeDataFormatFromBagTypeName.getName(), " name: ", xMLElement.getDebugText());
                return;
            }
            EncodeResult encodeResultEncode = ValueCoder.encode(getValue(xMLElement));
            if (encodeResultEncode == null) {
                o0g.a("Unexpected value: ", xMLElement.getDebugText());
            } else if (encodeResultEncode.isError()) {
                p0g.a(encodeResultEncode.getError(), xMLElement.getDebugText());
            } else {
                resValueMapCreateNext.setValue(encodeResultEncode);
                entry.getHeader().setPublic(true);
            }
        }

        public void encodePlural(XMLElement xMLElement, Entry entry) throws IOException {
            AttributeType attributeTypeFromName = AttributeType.fromName(xMLElement.getAttributeValue(XmlCoder.ATTR_quantity));
            if (attributeTypeFromName == null) {
                o0g.a("Failed to get attribute 'quantity'", xMLElement.getDebugText());
                return;
            }
            entry.ensureComplex(true);
            ResValueMap resValueMapCreateNext = entry.getResValueMapArray().createNext();
            resValueMapCreateNext.setAttributeType(attributeTypeFromName);
            String textContent = xMLElement.getTextContent();
            EncodeResult encodeResultEncodeReference = ValueCoder.encodeReference(entry.getPackageBlock(), textContent);
            if (encodeResultEncodeReference == null) {
                encodeResultEncodeReference = ValueCoder.encode(textContent);
            }
            if (encodeResultEncodeReference == null) {
                resValueMapCreateNext.setValueAsString(StyleDocument.copyInner(xMLElement));
            } else if (encodeResultEncodeReference.isError()) {
                p0g.a(encodeResultEncodeReference.getError(), xMLElement.getDebugText());
            } else {
                resValueMapCreateNext.setValue(encodeResultEncodeReference);
            }
        }

        public void encodeStyle(XMLElement xMLElement, Entry entry) throws IOException {
            entry.ensureComplex(true);
            EncodeResult encodeResultEncodeStyle = entry.getResValueMapArray().createNext().encodeStyle(xMLElement);
            if (encodeResultEncodeStyle.isError()) {
                p0g.a(encodeResultEncodeStyle.getError(), xMLElement.getDebugText());
            }
        }

        public XmlStringDecoder getStringDecoder() {
            return this.valuesXml.getStringDecoder();
        }
    }

    public static XmlCoder getInstance() {
        XmlCoder xmlCoder;
        XmlCoder xmlCoder2 = sInstance;
        if (xmlCoder2 != null) {
            return xmlCoder2;
        }
        synchronized (XmlCoder.class) {
            try {
                if (sInstance == null) {
                    sInstance = new XmlCoder();
                }
                xmlCoder = sInstance;
            } catch (Throwable th) {
                throw th;
            }
        }
        return xmlCoder;
    }

    @Deprecated
    public XmlCoderLogger getLogger() {
        return getSetting().getLogger();
    }

    public CoderSetting getSetting() {
        CoderSetting coderSetting = this.setting;
        if (coderSetting != null) {
            return coderSetting;
        }
        CoderSetting coderSetting2 = new CoderSetting();
        this.setting = coderSetting2;
        return coderSetting2;
    }

    public XmlStringDecoder getStringDecoder() {
        return getSetting().getStringDecoder();
    }

    @Deprecated
    public void setLogger(XmlCoderLogger xmlCoderLogger) {
        getSetting().setLogger(xmlCoderLogger);
    }

    public void setSetting(CoderSetting coderSetting) {
        this.setting = coderSetting;
    }

    public static class ValuesXml {
        private final XmlCoder xmlCoder;
        private final BagRootAttribute BAG_ROOT_ATTRIBUTE = new BagRootAttribute();
        private final BagChild BAG_CHILD = new BagChild(this);

        public ValuesXml(XmlCoder xmlCoder) {
            this.xmlCoder = xmlCoder;
        }

        private void checkVisibility(Entry entry) {
            TypeBlock typeBlock;
            ValueHeader header = entry.getHeader();
            if (header == null || (typeBlock = entry.getTypeBlock()) == null) {
                return;
            }
            if (typeBlock.isTypeAttr() || typeBlock.isTypeId()) {
                header.setPublic(true);
            }
        }

        private void decodeBag(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            String strStartEntry = startEntry(xmlSerializer, entry);
            this.BAG_ROOT_ATTRIBUTE.decode(xmlSerializer, entry);
            endEntry(xmlSerializer, strStartEntry, this.BAG_CHILD.decode(xmlSerializer, entry) != 0);
        }

        private void decodeScalar(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            String strStartEntry = startEntry(xmlSerializer, entry);
            if (ignoreIdValue(entry)) {
                endEntry(xmlSerializer, strStartEntry);
                return;
            }
            ResValue resValue = entry.getResValue();
            if (resValue.getValueType() == ValueType.STRING) {
                getStringDecoder().serializeText(resValue.getDataAsPoolString(), xmlSerializer);
            } else {
                resValue.serializeText(xmlSerializer, false);
            }
            endEntry(xmlSerializer, strStartEntry);
        }

        private void encodeScalarAny(XMLElement xMLElement, Entry entry) throws IOException {
            ResValue resValue = entry.getResValue();
            if (xMLElement.hasChildElements()) {
                resValue.setValueAsString(StyleDocument.copyInner(xMLElement));
                return;
            }
            String textContent = xMLElement.getTextContent();
            EncodeResult encodeResultEncodeReference = ValueCoder.encodeReference(entry.getPackageBlock(), textContent);
            if (encodeResultEncodeReference == null) {
                AttributeDataFormat attributeDataFormatFromValueTypeName = AttributeDataFormat.fromValueTypeName(xMLElement.getAttributeValue(XmlCoder.ATTR_type));
                if (attributeDataFormatFromValueTypeName == null && (attributeDataFormatFromValueTypeName = AttributeDataFormat.fromValueTypeName(xMLElement.getName())) != AttributeDataFormat.STRING) {
                    attributeDataFormatFromValueTypeName = null;
                }
                EncodeResult encodeResultEncode = ValueCoder.encode(textContent, attributeDataFormatFromValueTypeName);
                if (encodeResultEncode == null && attributeDataFormatFromValueTypeName != null && !attributeDataFormatFromValueTypeName.contains(ValueType.STRING)) {
                    throw new XmlEncodeException("Invalid value: " + xMLElement);
                }
                encodeResultEncodeReference = encodeResultEncode;
            }
            if (encodeResultEncodeReference == null) {
                resValue.setValueAsString(StyleDocument.copyInner(xMLElement));
            } else if (encodeResultEncodeReference.isError()) {
                p0g.a(encodeResultEncodeReference.getError(), xMLElement.getDebugText());
            } else {
                resValue.setValue(encodeResultEncodeReference);
            }
        }

        private void encodeScalarId(XMLElement xMLElement, Entry entry) throws IOException {
            if (xMLElement.hasTextNode()) {
                encodeScalarAny(xMLElement, entry);
            } else {
                entry.setValueAsBoolean(false);
            }
            ValueHeader header = entry.getHeader();
            header.setPublic(true);
            header.setWeak(true);
        }

        private void endEntry(XmlSerializer xmlSerializer, String str, boolean z) throws IOException {
            if (z) {
                XmlDecodeUtil.entryIndent(xmlSerializer);
            }
            xmlSerializer.endTag(null, str);
        }

        private boolean ignoreIdValue(Entry entry) {
            ResValue resValue;
            ValueType valueType;
            if (!TypeString.isTypeId(entry.getTypeName())) {
                return false;
            }
            if (isAapt() || (valueType = (resValue = entry.getResValue()).getValueType()) == ValueType.BOOLEAN) {
                return true;
            }
            if (valueType != ValueType.STRING) {
                return false;
            }
            String valueAsString = resValue.getValueAsString();
            return valueAsString == null || valueAsString.length() == 0;
        }

        private boolean isBag(XMLElement xMLElement) {
            String name = xMLElement.getName();
            if ("string".equals(name)) {
                return false;
            }
            if (xMLElement.hasChildElements()) {
                return true;
            }
            if (xMLElement.hasTextNode()) {
                return false;
            }
            return xMLElement.hasAttribute(XmlCoder.ATTR_parent) || xMLElement.hasAttribute(XmlCoder.ATTR_formats) || TypeString.isTypeArray(name);
        }

        private boolean isTypeId(XMLElement xMLElement) {
            if (xMLElement.hasChildElements()) {
                return false;
            }
            return TypeString.isTypeId(xMLElement.getName());
        }

        private void logMessage(String str) {
            XmlCoderLogger logger = getLogger();
            if (logger != null) {
                logger.logMessage("Decoding", str);
            }
        }

        private void logVerbose(String str) {
            XmlCoderLogger logger = getLogger();
            if (logger != null) {
                logger.logMessage("Decoding", str);
            }
        }

        private String startEntry(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            String xmlTag = entry.getXmlTag();
            XmlDecodeUtil.entryIndent(xmlSerializer);
            xmlSerializer.startTag(null, xmlTag);
            xmlSerializer.attribute(null, "name", entry.getName());
            return xmlTag;
        }

        public boolean decode(XmlSerializer xmlSerializer, ResourceEntry resourceEntry, ResConfig resConfig, Predicate<Entry> predicate) throws IOException {
            Entry entry = resourceEntry.get(resConfig);
            if (entry != null && !predicate.test(entry)) {
                if (entry.isComplex()) {
                    decodeBag(xmlSerializer, entry);
                    return true;
                }
                if (!entry.isNull()) {
                    decodeScalar(xmlSerializer, entry);
                    return true;
                }
            }
            return false;
        }

        public void decodePackage(ValuesSerializerFactory valuesSerializerFactory, PackageBlock packageBlock, Predicate<Entry> predicate) throws IOException {
            packageBlock.sortTypes();
            for (SpecTypePair specTypePair : packageBlock.listSpecTypePairs()) {
                Set<ResConfig> setListResConfig = specTypePair.listResConfig();
                int size = setListResConfig.size();
                int i = 0;
                for (ResConfig resConfig : setListResConfig) {
                    i++;
                    TypeBlock typeBlock = specTypePair.getTypeBlock(resConfig);
                    logVerbose(i + "/" + size + " " + packageBlock.getName() + ":" + typeBlock.getTypeName() + resConfig.getQualifiers());
                    XmlSerializer xmlSerializerCreateSerializer = valuesSerializerFactory.createSerializer(typeBlock);
                    valuesSerializerFactory.onFinish(xmlSerializerCreateSerializer, decode(xmlSerializerCreateSerializer, specTypePair, resConfig, predicate));
                }
            }
        }

        public void decodeTable(ValuesSerializerFactory valuesSerializerFactory, TableBlock tableBlock, Predicate<Entry> predicate) throws IOException {
            Iterator<PackageBlock> it = tableBlock.listPackages().iterator();
            while (it.hasNext()) {
                decodePackage(valuesSerializerFactory, it.next(), predicate);
            }
        }

        public void encode(XmlPullParser xmlPullParser, TypeBlock typeBlock) throws XmlPullParserException, IOException {
            boolean z;
            if (xmlPullParser.getEventType() == 0) {
                xmlPullParser.next();
                z = true;
            } else {
                z = false;
            }
            if (XMLUtil.ensureStartTag(xmlPullParser) != 2) {
                throw new XmlEncodeException(xmlPullParser, "Expecting xml state START_TAG but found: " + XMLUtil.toEventName(xmlPullParser.getEventType()));
            }
            if (PackageBlock.TAG_resources.equals(xmlPullParser.getName())) {
                xmlPullParser.next();
            } else if (z) {
                throw new XmlEncodeException(xmlPullParser, "Expecting <resources> tag but found: " + xmlPullParser.getName());
            }
            while (XMLUtil.ensureStartTag(xmlPullParser) == 2) {
                try {
                    encodeEntry(XMLElement.parseElement(xmlPullParser), typeBlock);
                } catch (XmlEncodeException e) {
                    throw new XmlEncodeException(xmlPullParser, e.getMessage());
                }
            }
            IOUtil.close(xmlPullParser);
        }

        public void encodeBag(XMLElement xMLElement, Entry entry) throws IOException {
            entry.ensureComplex(true);
            this.BAG_ROOT_ATTRIBUTE.encode(xMLElement, entry);
            Iterator elements = xMLElement.getElements();
            while (elements.hasNext()) {
                this.BAG_CHILD.encode((XMLElement) elements.next(), entry);
            }
            checkVisibility(entry);
        }

        public void encodeEntry(XMLElement xMLElement, TypeBlock typeBlock) throws IOException {
            Entry orCreateDefinedEntry = typeBlock.getOrCreateDefinedEntry(xMLElement.getAttributeValue("name"));
            if (orCreateDefinedEntry == null) {
                o0g.a("Undefined entry name: ", xMLElement.getDebugText());
            } else if (isBag(xMLElement)) {
                encodeBag(xMLElement, orCreateDefinedEntry);
            } else {
                encodeScalar(xMLElement, orCreateDefinedEntry);
            }
        }

        public void encodeScalar(XMLElement xMLElement, Entry entry) throws IOException {
            entry.ensureComplex(false);
            if (isTypeId(xMLElement)) {
                encodeScalarId(xMLElement, entry);
            } else {
                encodeScalarAny(xMLElement, entry);
            }
            checkVisibility(entry);
        }

        public XmlCoderLogger getLogger() {
            return this.xmlCoder.getSetting().getLogger();
        }

        public XmlStringDecoder getStringDecoder() {
            return this.xmlCoder.getStringDecoder();
        }

        public boolean isAapt() {
            return this.xmlCoder.getSetting().isAapt();
        }

        private void endEntry(XmlSerializer xmlSerializer, String str) throws IOException {
            endEntry(xmlSerializer, str, false);
        }

        public void decodeTable(File file, TableBlock tableBlock, Predicate<Entry> predicate) throws IOException {
            logMessage("Resource table ...");
            decodeTable(new ValuesDirectorySerializer(file), tableBlock, predicate);
            logMessage("Finished resource table");
        }

        public int decode(XmlSerializer xmlSerializer, Iterator<ResourceEntry> it, ResConfig resConfig, Predicate<Entry> predicate) throws IOException {
            int i = 0;
            while (it.hasNext()) {
                if (decode(xmlSerializer, it.next(), resConfig, predicate)) {
                    i++;
                }
            }
            return i;
        }

        public int decode(XmlSerializer xmlSerializer, SpecTypePair specTypePair, ResConfig resConfig, Predicate<Entry> predicate) throws IOException {
            return decode(xmlSerializer, specTypePair.getResources(), resConfig, predicate);
        }

        public void encode(File file, PackageBlock packageBlock) throws XmlPullParserException, IOException {
            encode(XMLFactory.newPullParser(file), packageBlock.getOrCreateTypeBlock(XmlEncodeUtil.getQualifiersFromValuesXml(file), XmlEncodeUtil.getTypeFromValuesXml(file)));
        }
    }

    public static class BagRootAttribute {
        public void decode(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            decodeParent(xmlSerializer, entry);
            decodeAttrTypes(xmlSerializer, entry);
        }

        public void decodeAttrTypes(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            if (entry.getTypeBlock().isTypeAttr()) {
                ResValueMapArray<ResValueMap> value = entry.getResTableMapEntry().getValue();
                for (ResValueMap resValueMap : value) {
                    AttributeType attributeType = resValueMap.getAttributeType();
                    if (attributeType != null) {
                        resValueMap.serializeAttribute(xmlSerializer, attributeType.getName(), value.size() > 1);
                    }
                }
            }
        }

        public void decodeParent(XmlSerializer xmlSerializer, Entry entry) throws IOException {
            ResTableMapEntry resTableMapEntry = entry.getResTableMapEntry();
            String strDecodeParentId = resTableMapEntry.decodeParentId();
            if (strDecodeParentId != null) {
                xmlSerializer.attribute(null, XmlCoder.ATTR_parent, strDecodeParentId);
                return;
            }
            if (resTableMapEntry.isStyle()) {
                if (resTableMapEntry.childesCount() == 0 || entry.getName().indexOf(46) > 0 || entry.getResourceEntry().getConfigsCount() > 1) {
                    xmlSerializer.attribute(null, XmlCoder.ATTR_parent, XmlPullParser.NO_NAMESPACE);
                }
            }
        }

        public void encode(String str, String str2, Entry entry) throws IOException {
            if (str.equals("name")) {
                encodeName(str2, entry);
                return;
            }
            if (str.equals(XmlCoder.ATTR_parent)) {
                encodeParent(str2, entry);
                return;
            }
            if (str.equals(XmlCoder.ATTR_formats)) {
                encodeFormats(str2, entry);
                return;
            }
            AttributeType attributeTypeFromName = AttributeType.fromName(str);
            if (attributeTypeFromName != null) {
                encodeType(attributeTypeFromName, str2, entry);
            }
        }

        public void encodeFormats(String str, Entry entry) {
            entry.ensureComplex(true);
            entry.getResValueMapArray().getOrCreateType(AttributeType.FORMATS).setData(AttributeDataFormat.sum(AttributeDataFormat.parseValueTypes(str)));
        }

        public void encodeName(String str, Entry entry) {
            if (entry.isDefined() || str == null || str.length() == 0) {
                return;
            }
            entry.setName(str);
        }

        public void encodeParent(String str, Entry entry) throws IOException {
            EncodeResult encodeResultEncodeReference = ValueCoder.encodeReference(entry.getPackageBlock(), str);
            if (encodeResultEncodeReference != null) {
                if (encodeResultEncodeReference.isError()) {
                    throw new XmlEncodeException(encodeResultEncodeReference.getError());
                }
                entry.ensureComplex(true);
                entry.getResTableMapEntry().setParentId(encodeResultEncodeReference.value);
            }
        }

        public void encodeType(AttributeType attributeType, String str, Entry entry) throws IOException {
            entry.ensureComplex(true);
            ResValueMap orCreateType = entry.getResValueMapArray().getOrCreateType(attributeType);
            EncodeResult encodeResultEncodeReference = ValueCoder.encodeReference(entry.getPackageBlock(), str);
            if (encodeResultEncodeReference != null) {
                if (encodeResultEncodeReference.isError()) {
                    throw new XmlEncodeException(encodeResultEncodeReference.getError());
                }
                orCreateType.setValue(encodeResultEncodeReference);
                return;
            }
            EncodeResult encodeResultEncode = ValueCoder.encode(str);
            if (encodeResultEncode == null) {
                orCreateType.setValueAsString(XmlSanitizer.unEscapeUnQuote(str));
            } else {
                if (encodeResultEncode.isError()) {
                    throw new XmlEncodeException(encodeResultEncode.getError());
                }
                orCreateType.setValue(encodeResultEncode);
            }
        }

        public void encode(XMLAttribute xMLAttribute, Entry entry) throws IOException {
            if (xMLAttribute.getPrefix() == null) {
                encode(xMLAttribute.getName(false), xMLAttribute.getValueAsString(false), entry);
            } else {
                o0g.a("Unknown root attribute: ", xMLAttribute.getDebugText());
            }
        }

        public void encode(XMLElement xMLElement, Entry entry) throws IOException {
            Iterator attributes = xMLElement.getAttributes();
            while (attributes.hasNext()) {
                encode((XMLAttribute) attributes.next(), entry);
            }
        }
    }

    public enum ChildType {
        ATTR,
        ARRAY,
        PLURAL,
        STYLE;

        public static ChildType getType(XMLElement xMLElement) {
            String name = xMLElement.getName(false);
            if (AttributeDataFormat.fromBagTypeName(name) != null) {
                return ATTR;
            }
            if (!XmlCoder.TAG_item.equals(name)) {
                return null;
            }
            if (xMLElement.getAttribute("name") != null) {
                return STYLE;
            }
            int attributeCount = xMLElement.getAttributeCount();
            if (attributeCount == 0 || (attributeCount == 1 && xMLElement.getAttribute(XmlCoder.ATTR_type) != null)) {
                return ARRAY;
            }
            AttributeType attributeTypeFromName = AttributeType.fromName(xMLElement.getAttributeValue(XmlCoder.ATTR_quantity));
            return (attributeTypeFromName == null || !attributeTypeFromName.isPlural()) ? STYLE : PLURAL;
        }

        public static ChildType getType(Entry entry) {
            ResTableMapEntry resTableMapEntry = entry.getResTableMapEntry();
            if (resTableMapEntry.isAttr()) {
                return ATTR;
            }
            if (resTableMapEntry.isArray()) {
                return ARRAY;
            }
            if (resTableMapEntry.isPlural()) {
                return PLURAL;
            }
            return STYLE;
        }
    }
}

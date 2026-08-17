package com.reandroid.arsc.chunk.xml;

import com.reandroid.archive.InputSource;
import com.reandroid.arsc.ApkFile;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.chunk.MainChunk;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.ParentChunk;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.header.HeaderBlock;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.pool.ResXmlStringPool;
import com.reandroid.arsc.pool.StringPool;
import com.reandroid.json.JSONException;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.SingleIterator;
import com.reandroid.utils.io.FileUtil;
import com.reandroid.xml.XMLDocument;
import com.reandroid.xml.XMLFactory;
import com.reandroid.xml.XMLUtil;
import com.reandroid.xml.base.Document;
import com.reandroid.xml.base.Element;
import com.reandroid.xml.base.Text;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlDocument extends ResXmlDocumentOrElement implements Document<ResXmlElement>, MainChunk, ParentChunk {
    private ApkFile apkFile;

    public ResXmlDocument() {
        super(new ResXmlDocumentChunk());
    }

    private ResXmlDocument getParentDocument() {
        return (ResXmlDocument) getParentInstance(ResXmlDocument.class);
    }

    public static boolean isResXmlBlock(HeaderBlock headerBlock) {
        return headerBlock != null && headerBlock.getChunkType() == ChunkType.XML;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ boolean autoSetAttributeNames() {
        return super.autoSetAttributeNames();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int autoSetLineNumber(int i) {
        Iterator<ResXmlNode> it = iterator();
        while (it.hasNext()) {
            i = it.next().autoSetLineNumber(i);
        }
        return 0;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ void fixAttributeNames() {
        super.fixAttributeNames();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ void fixNamespaces() {
        super.fixNamespaces();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.json.JSONException */
    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void fromJson(JSONObject jSONObject) throws JSONException {
        getStringPool().setEncoding(jSONObject.optString(ResXmlNode.JSON_encoding));
        nodesFromJson(jSONObject);
        refresh();
    }

    @Override // com.reandroid.arsc.chunk.MainChunk
    public ApkFile getApkFile() {
        ResXmlDocument parentDocument;
        ApkFile apkFile = this.apkFile;
        return (apkFile != null || (parentDocument = getParentDocument()) == null) ? apkFile : parentDocument.getApkFile();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public ResXmlDocumentChunk getChunk() {
        return (ResXmlDocumentChunk) super.getChunk();
    }

    /* JADX INFO: renamed from: getDocumentElement, reason: merged with bridge method [inline-methods] */
    public ResXmlElement m48getDocumentElement() {
        ResXmlElement resXmlElement = (ResXmlElement) CollectionUtil.getFirst(getElements());
        if (resXmlElement == null) {
            ResXmlDocument resXmlDocument = (ResXmlDocument) CollectionUtil.getFirst(iterator(ResXmlDocument.class));
            if (resXmlDocument != null) {
                resXmlElement = (ResXmlElement) CollectionUtil.getFirst(resXmlDocument.getElements());
            }
            if (resXmlElement == null) {
                return newElement();
            }
        }
        return resXmlElement;
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

    public String getEncoding() {
        return XMLUtil.KEEP_CHARSET_ENCODING ? getStringPool().getEncoding() : "utf-8";
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int getEndLineNumber() {
        int startLineNumber = getStartLineNumber();
        ResXmlNode resXmlNodeM61get = m61get(size() - 1);
        if (resXmlNodeM61get != null) {
            startLineNumber += resXmlNodeM61get.getEndLineNumber();
        }
        return startLineNumber + 1;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int getLineNumber() {
        return 1;
    }

    @Override // com.reandroid.arsc.chunk.ParentChunk
    public MainChunk getMainChunk() {
        return this;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNodeTree
    public ResXmlNodeList getNodeList() {
        return getChunk().getNodeList();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ ResXmlElement getOrCreateElement(String str) {
        return super.getOrCreateElement(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ ResXmlTextNode getOrCreateLastText() {
        return super.getOrCreateLastText();
    }

    @Override // com.reandroid.arsc.chunk.ParentChunk
    public PackageBlock getPackageBlock() {
        ResXmlDocument parentDocument;
        PackageBlock packageBlock = getChunk().getPackageBlock();
        return (packageBlock != null || (parentDocument = getParentDocument()) == null) ? packageBlock : parentDocument.getPackageBlock();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public Iterator<ResXmlEvent> getParserEvents() {
        return CombiningIterator.singleTwo(ResXmlEvent.startDocument(this), new IterableIterator<ResXmlNode, ResXmlEvent>(iterator()) { // from class: com.reandroid.arsc.chunk.xml.ResXmlDocument.1
            public Iterator<ResXmlEvent> iterator(ResXmlNode resXmlNode) {
                return resXmlNode.getParserEvents();
            }
        }, SingleIterator.of(ResXmlEvent.endDocument(this)));
    }

    public ResXmlIDMap getResXmlIDMap() {
        return getChunk().getResXmlIDMap();
    }

    @Override // com.reandroid.arsc.chunk.ParentChunk
    /* JADX INFO: renamed from: getSpecStringPool */
    public StringPool<?> mo35getSpecStringPool() {
        return null;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public int getStartLineNumber() {
        ResXmlNode previous = getPrevious();
        if (previous != null) {
            return previous.getEndLineNumber() + 1;
        }
        return 1;
    }

    @Override // com.reandroid.arsc.chunk.MainChunk
    public ResXmlStringPool getStringPool() {
        return getChunk().getStringPool();
    }

    @Override // com.reandroid.arsc.chunk.MainChunk
    public TableBlock getTableBlock() {
        return getChunk().getTableBlock();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator getTexts() {
        return super.getTexts();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ int getTextsCount() {
        return super.getTextsCount();
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
    public boolean isDocument() {
        return true;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ int lastIndexOf(String str) {
        return super.lastIndexOf(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    @Deprecated
    public /* bridge */ /* synthetic */ List listElements(String str) {
        return super.listElements(str);
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
        return ResXmlNode.JSON_node_type_document;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement, com.reandroid.arsc.chunk.xml.ResXmlNode
    public void parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock == null) {
            a16.a("Can not decode without package");
            return;
        }
        setPackageBlock(packageBlock);
        removeNullElements();
        if (xmlPullParser.getEventType() == 0) {
            xmlPullParser.nextToken();
            setEncoding(xmlPullParser.getInputEncoding());
        }
        parseInnerNodes(xmlPullParser);
        refreshFull();
    }

    public void readBytes(File file) throws IOException {
        super.readBytes(new BlockReader(file));
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator recursiveAttributes() {
        return super.recursiveAttributes();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ Iterator recursiveElements() {
        return super.recursiveElements();
    }

    public void refreshFull() {
        removeNullElements();
        fixNamespaces();
        removeUnusedNamespaces();
        removeUndefinedAttributes();
        getChunk().refreshFull();
        refresh();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ boolean removeElementsIf(Predicate predicate) {
        return super.removeElementsIf(predicate);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ boolean removeNullElements() {
        return super.removeNullElements();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ void removeUndefinedAttributes() {
        super.removeUndefinedAttributes();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ void removeUnusedNamespaces() {
        super.removeUnusedNamespaces();
    }

    public PackageBlock selectPackageBlock(TableBlock tableBlock) {
        return getChunk().selectPackageBlock(tableBlock);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void serialize(XmlSerializer xmlSerializer, boolean z) throws IOException {
        PackageBlock packageBlock = getPackageBlock();
        if (z && packageBlock == null) {
            a16.a("Can not decode without package");
            return;
        }
        ResXmlNode.setIndent(xmlSerializer, true);
        String encoding = getEncoding();
        xmlSerializer.startDocument(encoding, encoding == null ? Boolean.FALSE : null);
        fixNamespaces();
        removeUnusedNamespaces();
        serializeNodes(xmlSerializer, z);
        xmlSerializer.endDocument();
    }

    public String serializeToXml() throws IOException {
        StringWriter stringWriter = new StringWriter();
        XmlSerializer xmlSerializerNewSerializer = XMLFactory.newSerializer(stringWriter);
        serialize(xmlSerializerNewSerializer);
        xmlSerializerNewSerializer.flush();
        stringWriter.flush();
        stringWriter.close();
        return stringWriter.toString();
    }

    @Override // com.reandroid.arsc.chunk.MainChunk
    public void setApkFile(ApkFile apkFile) {
        this.apkFile = apkFile;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlDocumentOrElement
    public /* bridge */ /* synthetic */ void setAttributesUnitSize(int i, boolean z) {
        super.setAttributesUnitSize(i, z);
    }

    public void setEncoding(String str) {
        if (XMLUtil.KEEP_CHARSET_ENCODING) {
            getStringPool().setEncoding(str);
        }
    }

    public void setLineNumber(int i) {
    }

    public void setPackageBlock(PackageBlock packageBlock) {
        getChunk().setPackageBlock(packageBlock);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResXmlNode.JSON_encoding, getStringPool().getEncoding());
        jSONObject.put(ResXmlNode.JSON_node_type, nodeTypeName());
        jSONObject.put(ResXmlNode.JSON_nodes, nodesToJson());
        return jSONObject;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    /* JADX INFO: renamed from: toXml, reason: merged with bridge method [inline-methods] */
    public XMLDocument mo52toXml(boolean z) {
        XMLDocument xMLDocument = new XMLDocument();
        xMLDocument.setEncoding(getEncoding());
        Iterator<ResXmlNode> it = iterator();
        while (it.hasNext()) {
            xMLDocument.add(it.next().mo52toXml(z));
        }
        return xMLDocument;
    }

    public final int writeBytes(File file) throws IOException {
        if (isNull()) {
            a16.a("Can NOT save null block");
            return 0;
        }
        OutputStream outputStream = FileUtil.outputStream(file);
        int iWriteBytes = super.writeBytes(outputStream);
        outputStream.close();
        return iWriteBytes;
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
    public /* bridge */ /* synthetic */ Text m50newText() {
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
    public /* bridge */ /* synthetic */ Element m49newElement() {
        return super.newElement();
    }

    public void readBytes(InputStream inputStream) throws IOException {
        super.readBytes(new BlockReader(inputStream));
    }

    public static boolean isResXmlBlock(File file) {
        boolean zIsResXmlBlock = false;
        try {
            InputStream inputStream = FileUtil.inputStream(file);
            zIsResXmlBlock = isResXmlBlock(inputStream);
            inputStream.close();
            return zIsResXmlBlock;
        } catch (IOException unused) {
            return zIsResXmlBlock;
        }
    }

    public static boolean isResXmlBlock(InputStream inputStream) {
        try {
            return isResXmlBlock(BlockReader.readHeaderBlock(inputStream));
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean isResXmlBlock(byte[] bArr) {
        try {
            return isResXmlBlock(BlockReader.readHeaderBlock(bArr));
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean isResXmlBlock(BlockReader blockReader) {
        if (blockReader == null) {
            return false;
        }
        try {
            return isResXmlBlock(blockReader.readHeaderBlock());
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean isResXmlBlock(InputSource inputSource) {
        boolean zIsResXmlBlock = false;
        try {
            InputStream inputStreamOpenStream = inputSource.openStream();
            zIsResXmlBlock = isResXmlBlock(inputStreamOpenStream);
            inputStreamOpenStream.close();
            return zIsResXmlBlock;
        } catch (IOException unused) {
            return zIsResXmlBlock;
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNode
    public void serialize(XmlSerializer xmlSerializer) throws IOException {
        serialize(xmlSerializer, true);
    }
}

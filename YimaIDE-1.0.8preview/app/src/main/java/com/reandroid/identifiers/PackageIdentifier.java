package com.reandroid.identifiers;

import com.intellij.psi.PsiKeyword;
import com.reandroid.apk.XmlHelper;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.Entry;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.io.IOUtil;
import com.reandroid.xml.XMLFactory;
import com.reandroid.xml.XMLUtil;
import com.sun.xml.internal.stream.writers.WriterUtility;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PackageIdentifier extends IdentifierMap<TypeIdentifier> {
    private PackageBlock mPackageBlock;

    public PackageIdentifier() {
        this(0, null);
    }

    private void closeParser(XmlPullParser xmlPullParser) {
        if (xmlPullParser instanceof Closeable) {
            try {
                ((Closeable) xmlPullParser).close();
            } catch (IOException unused) {
            }
        }
    }

    private void closeSerializer(XmlSerializer xmlSerializer) {
        if (xmlSerializer instanceof Closeable) {
            try {
                ((Closeable) xmlSerializer).close();
            } catch (IOException unused) {
            }
        }
    }

    private TypeIdentifier getOrCreate(int i, String str) {
        TypeIdentifier typeIdentifier = (TypeIdentifier) get(i);
        if (typeIdentifier == null) {
            return (TypeIdentifier) super.add(new TypeIdentifier(i, str));
        }
        if (str == null || typeIdentifier.getName() != null) {
            return typeIdentifier;
        }
        typeIdentifier.setName(str);
        return (TypeIdentifier) super.add(typeIdentifier);
    }

    private void loadEntryGroups(PackageBlock packageBlock) {
        Iterator it = packageBlock.listSpecTypePairs().iterator();
        while (it.hasNext()) {
            Iterator resources = ((SpecTypePair) it.next()).getResources();
            while (resources.hasNext()) {
                add((ResourceEntry) resources.next());
            }
        }
    }

    private void loadPackageInfo(XmlPullParser xmlPullParser) {
        int iIntValue;
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (PsiKeyword.PACKAGE.equals(xmlPullParser.getAttributeName(i))) {
                setName(xmlPullParser.getAttributeValue(i));
            } else if ("id".equals(xmlPullParser.getAttributeName(i)) && (iIntValue = Integer.decode(xmlPullParser.getAttributeValue(i)).intValue()) != 0) {
                setId(iIntValue);
            }
        }
    }

    private void parseEntry(XmlPullParser xmlPullParser) throws XmlPullParserException {
        if (!PsiKeyword.PUBLIC.equals(xmlPullParser.getName())) {
            throw new XmlPullParserException(XMLUtil.getSimplePositionDescription(xmlPullParser) + "\nInvalid tag, expecting 'public'");
        }
        int attributeCount = xmlPullParser.getAttributeCount();
        String str = null;
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if ("id".equals(attributeName)) {
                str3 = attributeValue;
            } else if ("type".equals(attributeName)) {
                str = attributeValue;
            } else if ("name".equals(attributeName)) {
                str2 = attributeValue;
            }
        }
        if (str == null) {
            throw new XmlPullParserException(XMLUtil.getSimplePositionDescription(xmlPullParser) + "\nMissing attribute 'type'");
        }
        if (str3 == null) {
            throw new XmlPullParserException(XMLUtil.getSimplePositionDescription(xmlPullParser) + "\nMissing attribute 'id'");
        }
        if (str2 == null) {
            throw new XmlPullParserException(XMLUtil.getSimplePositionDescription(xmlPullParser) + "\nMissing attribute 'name'");
        }
        int iLongValue = (int) Long.decode(str3).longValue();
        int i2 = (iLongValue >> 24) & 255;
        getOrCreate((iLongValue >> 16) & 255, str).add(new ResourceIdentifier(iLongValue & 65535, str2));
        if (getId() == 0) {
            setId(i2);
        }
    }

    private void writePackageInfo(XmlSerializer xmlSerializer) throws IOException {
        String name = getName();
        if (name != null) {
            xmlSerializer.attribute(null, PsiKeyword.PACKAGE, name);
        }
        int id = getId();
        if (id != 0) {
            xmlSerializer.attribute(null, "id", HexUtil.toHex2((byte) id));
        }
    }

    private void writeTypes(XmlSerializer xmlSerializer) throws IOException {
        Iterator it = list().iterator();
        while (it.hasNext()) {
            ((TypeIdentifier) it.next()).write(xmlSerializer);
        }
    }

    public void add(Entry entry) {
        if (entry == null || entry.isNull()) {
            return;
        }
        TypeBlock typeBlock = entry.getTypeBlock();
        getOrCreate(typeBlock.getId(), typeBlock.getTypeName()).add(new ResourceIdentifier(entry.getId(), entry.getName()));
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public void clear() {
        Iterator it = getItems().iterator();
        while (it.hasNext()) {
            ((TypeIdentifier) it.next()).clear();
        }
        super.clear();
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ int compare(Identifier identifier, Identifier identifier2) {
        return super.compare(identifier, identifier2);
    }

    public List<ResourceIdentifier> ensureUniqueResourceNames() {
        ArrayList arrayList = new ArrayList();
        Iterator it = list().iterator();
        while (it.hasNext()) {
            arrayList.addAll(((TypeIdentifier) it.next()).ensureUniqueResourceNames());
        }
        return arrayList;
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ Identifier get(int i) {
        return super.get(i);
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ Identifier getByTag(Object obj) {
        return super.getByTag(obj);
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ Collection getItems() {
        return super.getItems();
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ int getMaxId() {
        return super.getMaxId();
    }

    public PackageBlock getPackageBlock() {
        return this.mPackageBlock;
    }

    public ResourceIdentifier getResourceIdentifier(int i) {
        TypeIdentifier typeIdentifier = (TypeIdentifier) get((i >> 16) & 255);
        if (typeIdentifier != null) {
            return (ResourceIdentifier) typeIdentifier.get(i & 65535);
        }
        return null;
    }

    public int getResourcesCount() {
        Iterator it = getItems().iterator();
        int size = 0;
        while (it.hasNext()) {
            size += ((TypeIdentifier) it.next()).size();
        }
        return size;
    }

    public boolean hasDuplicateResources() {
        Iterator it = getItems().iterator();
        while (it.hasNext()) {
            if (((TypeIdentifier) it.next()).hasDuplicates()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ boolean hasDuplicates() {
        return super.hasDuplicates();
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ List list() {
        return super.list();
    }

    public List<ResourceIdentifier> listDuplicateResources() {
        ArrayList arrayList = new ArrayList();
        Iterator it = list().iterator();
        while (it.hasNext()) {
            arrayList.addAll(((TypeIdentifier) it.next()).listDuplicates());
        }
        return arrayList;
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ List listDuplicates() {
        return super.listDuplicates();
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ List listNames() {
        return super.listNames();
    }

    public void load(PackageBlock packageBlock) {
        setId(packageBlock.getId());
        setName(packageBlock.getName());
        loadEntryGroups(packageBlock);
        setPackageBlock(packageBlock);
    }

    public void loadPublicXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        boolean zEquals = false;
        while (true) {
            int iNextToken = xmlPullParser.nextToken();
            if (iNextToken == 1) {
                closeParser(xmlPullParser);
                return;
            }
            if (iNextToken == 2) {
                if (zEquals) {
                    parseEntry(xmlPullParser);
                } else {
                    zEquals = xmlPullParser.getName().equals(XmlHelper.RESOURCES_TAG);
                    if (!zEquals) {
                        throw new XmlPullParserException(XMLUtil.getSimplePositionDescription(xmlPullParser) + "\nInvalid public.xml, expecting first tag '" + getName() + "'");
                    }
                    loadPackageInfo(xmlPullParser);
                }
            }
        }
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ void reloadNameMap() {
        super.reloadNameMap();
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ void remove(Identifier identifier) {
        super.remove(identifier);
    }

    public int renameBadSpecs() {
        Iterator it = getItems().iterator();
        int iRenameBadSpecs = 0;
        while (it.hasNext()) {
            iRenameBadSpecs += ((TypeIdentifier) it.next()).renameBadSpecs();
        }
        return iRenameBadSpecs;
    }

    public int renameDuplicateSpecs() {
        Iterator it = getItems().iterator();
        int iRenameDuplicateSpecs = 0;
        while (it.hasNext()) {
            iRenameDuplicateSpecs += ((TypeIdentifier) it.next()).renameDuplicateSpecs();
        }
        return iRenameDuplicateSpecs;
    }

    public int renameSpecs() {
        Iterator it = getItems().iterator();
        int iRenameSpecs = 0;
        while (it.hasNext()) {
            iRenameSpecs += ((TypeIdentifier) it.next()).renameSpecs();
        }
        return iRenameSpecs;
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public void setCaseInsensitive(boolean z) {
        super.setCaseInsensitive(z);
        Iterator it = getItems().iterator();
        while (it.hasNext()) {
            ((TypeIdentifier) it.next()).setCaseInsensitive(z);
        }
    }

    public void setPackageBlock(PackageBlock packageBlock) {
        this.mPackageBlock = packageBlock;
    }

    public void setResourceNamesToEntry(ResourceEntry resourceEntry) {
        ResourceIdentifier resourceIdentifier = getResourceIdentifier(resourceEntry.getResourceId());
        if (resourceIdentifier == null) {
            return;
        }
        resourceEntry.setName(resourceIdentifier.getName());
    }

    public void setResourceNamesToPackage(PackageBlock packageBlock) {
        if (packageBlock == null) {
            return;
        }
        Iterator it = packageBlock.listSpecTypePairs().iterator();
        while (it.hasNext()) {
            Iterator resources = ((SpecTypePair) it.next()).getResources();
            while (resources.hasNext()) {
                setResourceNamesToEntry((ResourceEntry) resources.next());
            }
        }
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    @Override // com.reandroid.identifiers.IdentifierMap, com.reandroid.identifiers.Identifier
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public String validateSpecNames() {
        int iRenameDuplicateSpecs = renameDuplicateSpecs();
        int iRenameBadSpecs = renameBadSpecs();
        if (iRenameDuplicateSpecs == 0 && iRenameBadSpecs == 0) {
            return null;
        }
        return "Spec names validated, duplicates = " + iRenameDuplicateSpecs + ", bad = " + iRenameBadSpecs;
    }

    public void write(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startDocument(WriterUtility.UTF_8, null);
        xmlSerializer.text("\n");
        xmlSerializer.startTag(null, XmlHelper.RESOURCES_TAG);
        writePackageInfo(xmlSerializer);
        writeTypes(xmlSerializer);
        xmlSerializer.text("\n");
        xmlSerializer.endTag(null, XmlHelper.RESOURCES_TAG);
        xmlSerializer.endDocument();
        closeSerializer(xmlSerializer);
    }

    public void writePublicXml(OutputStream outputStream) throws IOException {
        XmlSerializer xmlSerializerNewSerializer = XMLFactory.newSerializer(outputStream);
        xmlSerializerNewSerializer.setOutput(outputStream, StandardCharsets.UTF_8.name());
        write(xmlSerializerNewSerializer);
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ Identifier get(String str) {
        return super.get(str);
    }

    public PackageIdentifier(int i, String str) {
        super(i, str);
    }

    public void writePublicXml(File file) throws IOException {
        XmlSerializer xmlSerializerNewSerializer = XMLFactory.newSerializer(file);
        write(xmlSerializerNewSerializer);
        IOUtil.close(xmlSerializerNewSerializer);
    }

    public ResourceIdentifier getResourceIdentifier(String str, String str2) {
        TypeIdentifier typeIdentifier = (TypeIdentifier) get(str);
        if (typeIdentifier != null) {
            return (ResourceIdentifier) typeIdentifier.get(str2);
        }
        return null;
    }

    public void add(ResourceEntry resourceEntry) {
        add(resourceEntry.get());
    }

    @Override // com.reandroid.identifiers.IdentifierMap
    public /* bridge */ /* synthetic */ Identifier add(Identifier identifier) {
        return super.add(identifier);
    }

    public void setResourceNamesToPackage() {
        setResourceNamesToPackage(getPackageBlock());
    }

    public void loadPublicXml(InputStream inputStream) throws XmlPullParserException, IOException {
        loadPublicXml(XMLFactory.newPullParser(inputStream));
    }

    public void loadPublicXml(Reader reader) throws XmlPullParserException, IOException {
        loadPublicXml(XMLFactory.newPullParser(reader));
    }

    public void loadPublicXml(File file) throws XmlPullParserException, IOException {
        loadPublicXml(XMLFactory.newPullParser(file));
    }
}

package com.reandroid.dex.resource;

import com.reandroid.apk.XmlHelper;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.dex.dalvik.DalvikMemberClass;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.model.DexMethod;
import com.reandroid.dex.resource.R;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.InstanceIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.UniqueIterator;
import com.reandroid.xml.XMLFactory;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class R implements Iterable<RTypeItem> {
    private final DexClass dexClass;
    private final ArrayCollection<RTypeItem> typeList = new ArrayCollection<>();
    private boolean typeListLoaded;

    public R(DexClass dexClass) {
        this.dexClass = dexClass;
    }

    private boolean containsOnMembers(TypeKey typeKey) {
        DalvikMemberClass dalvikMemberClassOf = DalvikMemberClass.of(getDexClass());
        if (dalvikMemberClassOf != null) {
            return dalvikMemberClassOf.contains(typeKey);
        }
        return false;
    }

    public static R create(DexClass dexClass) {
        if (dexClass != null) {
            return new R(dexClass);
        }
        return null;
    }

    public static R createValid(DexClass dexClass) {
        R rCreate = create(dexClass);
        if (rCreate == null || !rCreate.isValid()) {
            return null;
        }
        return rCreate;
    }

    public static Iterator<R> findAll(Iterator<? extends DexClass> it) {
        return ComputeIterator.of(it, new Function() { // from class: z1c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return R.createValid((DexClass) obj);
            }
        });
    }

    private boolean isTypesValid() {
        Iterator<RTypeItem> it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (!it.next().isValid()) {
                return false;
            }
            z = true;
        }
        return z;
    }

    private void loadTypes() {
        if (this.typeListLoaded) {
            return;
        }
        this.typeListLoaded = true;
        ArrayCollection<RTypeItem> arrayCollection = this.typeList;
        arrayCollection.clear();
        arrayCollection.addAll(ComputeIterator.of(getDexClass().getClassRepository().getDexClasses(), new Function() { // from class: a2c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.createWithCheck((DexClass) obj);
            }
        }));
    }

    public void appendJava(SmaliWriter smaliWriter) throws IOException {
        loadTypes();
        DexClass dexClass = getDexClass();
        smaliWriter.append("package ");
        smaliWriter.append((CharSequence) dexClass.getKey().getPackageSourceName());
        smaliWriter.append(';');
        smaliWriter.newLine();
        smaliWriter.newLine();
        smaliWriter.appendModifiers(dexClass.getAccessFlags());
        smaliWriter.append("class ");
        smaliWriter.append((CharSequence) getName());
        smaliWriter.append(" {");
        smaliWriter.indentPlus();
        for (RTypeItem rTypeItem : this) {
            smaliWriter.newLine();
            rTypeItem.appendJava(smaliWriter);
        }
        smaliWriter.indentMinus();
        smaliWriter.newLine();
        smaliWriter.append('}');
    }

    public RStyleableType createStyleableType(DexClass dexClass) {
        if (dexClass != null) {
            return new RStyleableType(dexClass);
        }
        return null;
    }

    public RType createType(DexClass dexClass) {
        if (dexClass != null) {
            return new RType(dexClass);
        }
        return null;
    }

    public RTypeItem createWithCheck(DexClass dexClass) {
        if (!isChild(dexClass)) {
            return null;
        }
        RType rTypeCreateType = createType(dexClass);
        if (rTypeCreateType != null && rTypeCreateType.isValid()) {
            return rTypeCreateType;
        }
        RStyleableType rStyleableTypeCreateStyleableType = createStyleableType(dexClass);
        if (rStyleableTypeCreateStyleableType == null || !rStyleableTypeCreateStyleableType.isValid()) {
            return null;
        }
        return rStyleableTypeCreateStyleableType;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof R) {
            return getKey().equals(((R) obj).getKey());
        }
        return false;
    }

    public DexClass getDexClass() {
        return this.dexClass;
    }

    public TypeKey getKey() {
        return getDexClass().getKey();
    }

    public String getName() {
        return getKey().getSimpleInnerName();
    }

    public Iterator<RDeclareStyleable> getRDeclareStyleables() {
        return new UniqueIterator(new IterableIterator<RStyleableType, RDeclareStyleable>(getStyleables()) { // from class: com.reandroid.dex.resource.R.1
            public Iterator<RDeclareStyleable> iterator(RStyleableType rStyleableType) {
                return rStyleableType.getRDeclareStyleables();
            }
        });
    }

    public Iterator<RStyleableType> getStyleables() {
        return InstanceIterator.of(getTypeList().iterator(), RStyleableType.class);
    }

    public List<RTypeItem> getTypeList() {
        loadTypes();
        return this.typeList;
    }

    public int hashCode() {
        return getKey().hashCode();
    }

    public boolean isChild(DexClass dexClass) {
        if (dexClass == null || dexClass.isAbstract() || dexClass.isInterface() || dexClass.isEnum()) {
            return false;
        }
        TypeKey key = getKey();
        TypeKey key2 = dexClass.getKey();
        if (key.equals(key2) || !key.equalsPackage(key2)) {
            return false;
        }
        if (containsOnMembers(key)) {
            return true;
        }
        return key.equals(key2.getEnclosingClass());
    }

    public boolean isValid() {
        DexClass dexClass = getDexClass();
        if (dexClass.isAbstract() || dexClass.isInterface() || dexClass.isEnum() || dexClass.isSynthetic() || dexClass.getDeclaredFields().hasNext()) {
            return false;
        }
        Iterator<DexMethod> declaredMethods = dexClass.getDeclaredMethods();
        while (declaredMethods.hasNext()) {
            if (!declaredMethods.next().isConstructor()) {
                return false;
            }
        }
        return isTypesValid();
    }

    @Override // java.lang.Iterable
    public Iterator<RTypeItem> iterator() {
        return getTypeList().iterator();
    }

    public void refresh() {
        this.typeListLoaded = false;
        loadTypes();
    }

    public void serialize(TableBlock tableBlock, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startDocument("utf8", null);
        XmlHelper.setIndent(xmlSerializer, true);
        xmlSerializer.startTag(null, XmlHelper.RESOURCES_TAG);
        Iterator<RDeclareStyleable> rDeclareStyleables = getRDeclareStyleables();
        while (rDeclareStyleables.hasNext()) {
            rDeclareStyleables.next().serialize(tableBlock, xmlSerializer);
        }
        xmlSerializer.endTag(null, XmlHelper.RESOURCES_TAG);
        xmlSerializer.endDocument();
    }

    public int size() {
        return getTypeList().size();
    }

    public String toJavaString() {
        StringWriter stringWriter = new StringWriter();
        SmaliWriter smaliWriter = new SmaliWriter();
        smaliWriter.setWriter(stringWriter);
        try {
            appendJava(smaliWriter);
            smaliWriter.close();
        } catch (IOException unused) {
        }
        return stringWriter.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean z = this.typeListLoaded;
        if (!z) {
            sb.append("NOT-LOADED: ");
        }
        sb.append(getKey());
        if (z) {
            sb.append(", size = ");
            sb.append(size());
        }
        return sb.toString();
    }

    public String toXml(TableBlock tableBlock) throws IOException {
        StringWriter stringWriter = new StringWriter();
        XmlSerializer xmlSerializerNewSerializer = XMLFactory.newSerializer(stringWriter);
        serialize(tableBlock, xmlSerializerNewSerializer);
        xmlSerializerNewSerializer.flush();
        stringWriter.close();
        return stringWriter.toString();
    }

    public static Iterator<R> findAll(DexClassRepository dexClassRepository) {
        return findAll(dexClassRepository.getDexClasses());
    }
}

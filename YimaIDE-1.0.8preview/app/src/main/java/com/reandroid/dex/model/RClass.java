package com.reandroid.dex.model;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.dalvik.DalvikEnclosingClass;
import com.reandroid.dex.dalvik.DalvikInnerClass;
import com.reandroid.dex.data.ClassData;
import com.reandroid.dex.data.FieldDef;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.io.IOUtil;
import com.sun.xml.internal.stream.writers.WriterUtility;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RClass extends DexClass {
    static final TableBlock EMPTY_TABLE = new TableBlock();
    private static final String SIMPLE_NAME_PREFIX = "R$";

    public RClass(DexLayout dexLayout, ClassId classId) {
        super(dexLayout, classId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RField createRField(FieldDef fieldDef) {
        if (fieldDef.isStatic() && RField.isResourceIdValue(fieldDef.getStaticValue())) {
            return new RField(this, fieldDef);
        }
        return null;
    }

    private void ensureDalvikEnclosingClass() {
        TypeKey key = getKey();
        if (key == null) {
            return;
        }
        TypeKey enclosingClass = key.getEnclosingClass();
        if (key.equals(enclosingClass)) {
            return;
        }
        DalvikEnclosingClass.getOrCreate(this).setEnclosing(enclosingClass);
    }

    private void ensureDalvikInnerClass() {
        TypeKey key = getKey();
        if (key == null) {
            return;
        }
        String simpleInnerName = key.getSimpleInnerName();
        if (AccessFlag.SYNTHETIC.isSet(getAccessFlagsValue()) || simpleInnerName.equals(key.getSimpleName()) || StringsUtil.isDigits(simpleInnerName)) {
            simpleInnerName = null;
        }
        DalvikInnerClass orCreate = DalvikInnerClass.getOrCreate(this);
        orCreate.setName(simpleInnerName);
        orCreate.setAccessFlags(getAccessFlagsValue());
    }

    private void initializeAnnotations() {
        ensureDalvikEnclosingClass();
        ensureDalvikInnerClass();
    }

    public static boolean isRClassName(ClassId classId) {
        if (classId != null) {
            return isRClassName(classId.getName());
        }
        return false;
    }

    public static void serializePublicXml(Collection<RField> collection, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startDocument(WriterUtility.UTF_8, null);
        xmlSerializer.text("\n");
        xmlSerializer.startTag(null, PackageBlock.TAG_resources);
        ArrayCollection arrayCollection = new ArrayCollection();
        arrayCollection.addAll(collection);
        arrayCollection.sort(CompareUtil.getComparableComparator());
        Iterator<T> it = arrayCollection.iterator();
        while (it.hasNext()) {
            ((RField) it.next()).serializePublicXml(xmlSerializer);
        }
        xmlSerializer.text("\n");
        xmlSerializer.endTag(null, PackageBlock.TAG_resources);
        xmlSerializer.endDocument();
        xmlSerializer.flush();
        IOUtil.close(xmlSerializer);
    }

    private static String toResourceTypeName(String str) {
        String simpleName = DexUtils.getSimpleName(str);
        if (simpleName.length() < 4 || !simpleName.startsWith(SIMPLE_NAME_PREFIX)) {
            return null;
        }
        String strSubstring = simpleName.substring(2);
        if (strSubstring.indexOf(36) >= 0) {
            return null;
        }
        return strSubstring;
    }

    @Override // com.reandroid.dex.model.DexClass
    public RField getOrCreateStaticField(FieldKey fieldKey) {
        return new RField(this, getOrCreateStatic(fieldKey));
    }

    public String getResourceType() {
        return toResourceTypeName(getDefining().getTypeName());
    }

    @Override // com.reandroid.dex.model.DexClass, com.reandroid.dex.program.ClassProgram
    public Iterator<DexField> getStaticFields() {
        ClassData classData = getClassData();
        return classData != null ? ComputeIterator.of(classData.getStaticFields(), new Function() { // from class: a5c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.createRField((FieldDef) obj);
            }
        }) : EmptyIterator.of();
    }

    public void initialize() {
        ClassId id = getId();
        id.setSuperClass(TypeKey.OBJECT);
        id.setSourceFile("R.java");
        initializeAnnotations();
    }

    public RField load(ResourceEntry resourceEntry) {
        if (resourceEntry.isEmpty()) {
            return null;
        }
        RField orCreateStaticField = getOrCreateStaticField(FieldKey.create(getKey(), RField.sanitizeResourceName(resourceEntry.getName()), TypeKey.TYPE_I));
        orCreateStaticField.setResourceId(resourceEntry.getResourceId());
        return orCreateStaticField;
    }

    public String toJavaDeclare(boolean z) {
        StringBuilder sb = new StringBuilder("    public static class ");
        sb.append(getResourceType());
        sb.append(" {");
        Iterator<DexField> staticFields = getStaticFields();
        while (staticFields.hasNext()) {
            sb.append("\n        ");
            sb.append(((RField) staticFields.next()).toJavaDeclare(z));
        }
        sb.append("\n    }");
        return sb.toString();
    }

    @Override // com.reandroid.dex.model.DexClass, com.reandroid.dex.model.DexDeclaration, com.reandroid.dex.model.Dex
    public String toString() {
        return toJavaDeclare();
    }

    public static boolean isRClassName(String str) {
        return (str == null || toResourceTypeName(str) == null) ? false : true;
    }

    public String toJavaDeclare() {
        return toJavaDeclare(true);
    }
}

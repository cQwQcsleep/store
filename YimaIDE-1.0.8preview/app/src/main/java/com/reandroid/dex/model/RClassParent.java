package com.reandroid.dex.model;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.DexUtils;
import com.reandroid.dex.dalvik.DalvikMemberClass;
import com.reandroid.dex.data.ClassData;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.data.MethodDef;
import com.reandroid.dex.id.ClassId;
import com.reandroid.dex.ins.Ins35c;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.io.IOUtil;
import com.sun.xml.internal.stream.writers.WriterUtility;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RClassParent extends DexClass {
    private static final String SIMPLE_NAME_PREFIX = "R";
    private final Map<String, RClass> mMembers;
    private final Set<PackageBlock> mPackageBlocks;

    public RClassParent(DexLayout dexLayout, ClassId classId) {
        super(dexLayout, classId);
        this.mMembers = new HashMap();
        this.mPackageBlocks = new HashSet();
    }

    private ResourceEntry getEntry(int i) {
        Iterator<PackageBlock> it = this.mPackageBlocks.iterator();
        while (it.hasNext()) {
            ResourceEntry resource = it.next().getResource(i);
            if (resource != null) {
                return resource;
            }
        }
        return null;
    }

    public static boolean isRParentClassName(String str) {
        if (str == null) {
            return false;
        }
        return SIMPLE_NAME_PREFIX.equals(DexUtils.getSimpleName(str));
    }

    private RField load(ResourceEntry resourceEntry) {
        if (resourceEntry == null || resourceEntry.isEmpty()) {
            return null;
        }
        return getOrCreateMember(resourceEntry.getType()).load(resourceEntry);
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

    public void addMemberAnnotation(String str) {
        DalvikMemberClass.getOrCreate(this).addSimpleName(str);
    }

    public RClass getOrCreateMember(String str) {
        TypeKey typeKeyCreateInnerClass = getKey().createInnerClass(str);
        RClass rClass = this.mMembers.get(typeKeyCreateInnerClass.getTypeName());
        if (rClass != null) {
            return rClass;
        }
        addMemberAnnotation(str);
        RClass rClass2 = new RClass(getDexLayout(), getDexLayout().getOrCreateClassId(typeKeyCreateInnerClass));
        this.mMembers.put(typeKeyCreateInnerClass.getTypeName(), rClass2);
        rClass2.initialize();
        return rClass2;
    }

    public RField getRField(int i) {
        return load(getEntry(i));
    }

    public boolean hasRField(int i) {
        return getEntry(i) != null;
    }

    public void initialize() {
        ClassId id = getId();
        AccessFlag accessFlag = AccessFlag.PUBLIC;
        id.addAccessFlag(accessFlag);
        ClassData orCreateClassData = id.getOrCreateClassData();
        MethodKey methodKeyChangeDeclaring = MethodKey.CONSTRUCTOR.changeDeclaring(id.getKey());
        if (orCreateClassData.getMethod(methodKeyChangeDeclaring) != null) {
            return;
        }
        MethodDef orCreateDirect = orCreateClassData.getOrCreateDirect(methodKeyChangeDeclaring);
        orCreateDirect.addAccessFlag(accessFlag);
        orCreateDirect.addAccessFlag(AccessFlag.CONSTRUCTOR);
        InstructionList orCreateInstructionList = orCreateDirect.getOrCreateInstructionList();
        Ins35c ins35c = (Ins35c) orCreateInstructionList.createNext(Opcode.INVOKE_DIRECT);
        ins35c.setKey(MethodKey.parse("Ljava/lang/Object;-><init>()V"));
        ins35c.setRegistersCount(1);
        ins35c.setRegister(0, 0);
        orCreateInstructionList.createNext(Opcode.RETURN_VOID);
    }

    public static boolean isRParentClassName(ClassId classId) {
        if (classId != null) {
            return isRParentClassName(classId.getName());
        }
        return false;
    }

    public void load(PackageBlock packageBlock) {
        this.mPackageBlocks.add(packageBlock);
    }
}

package com.sun.tools.classfile;

import com.sun.tools.classfile.ConstantPool;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ConstantPool {
    public static final int CONSTANT_Class = 7;
    public static final int CONSTANT_Double = 6;
    public static final int CONSTANT_Dynamic = 17;
    public static final int CONSTANT_Fieldref = 9;
    public static final int CONSTANT_Float = 4;
    public static final int CONSTANT_Integer = 3;
    public static final int CONSTANT_InterfaceMethodref = 11;
    public static final int CONSTANT_InvokeDynamic = 18;
    public static final int CONSTANT_Long = 5;
    public static final int CONSTANT_MethodHandle = 15;
    public static final int CONSTANT_MethodType = 16;
    public static final int CONSTANT_Methodref = 10;
    public static final int CONSTANT_Module = 19;
    public static final int CONSTANT_NameAndType = 12;
    public static final int CONSTANT_Package = 20;
    public static final int CONSTANT_String = 8;
    public static final int CONSTANT_Utf8 = 1;
    private CPInfo[] pool;

    public static class EntryNotFound extends ConstantPoolException {
        private static final long serialVersionUID = 2885537606468581850L;
        public final Object value;

        public EntryNotFound(Object obj) {
            super(-1);
            this.value = obj;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "value not found: " + this.value;
        }
    }

    public static class InvalidEntry extends ConstantPoolException {
        private static final long serialVersionUID = 1000087545585204447L;
        public final int tag;

        public InvalidEntry(int i, int i2) {
            super(i);
            this.tag = i2;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "unexpected tag at #" + this.index + ": " + this.tag;
        }
    }

    public static class InvalidIndex extends ConstantPoolException {
        private static final long serialVersionUID = -4350294289300939730L;

        public InvalidIndex(int i) {
            super(i);
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "invalid index #" + this.index;
        }
    }

    public enum RefKind {
        REF_getField(1),
        REF_getStatic(2),
        REF_putField(3),
        REF_putStatic(4),
        REF_invokeVirtual(5),
        REF_invokeStatic(6),
        REF_invokeSpecial(7),
        REF_newInvokeSpecial(8),
        REF_invokeInterface(9);

        public final int tag;

        RefKind(int i) {
            this.tag = i;
        }

        public static RefKind getRefkind(int i) {
            switch (i) {
                case 1:
                    return REF_getField;
                case 2:
                    return REF_getStatic;
                case 3:
                    return REF_putField;
                case 4:
                    return REF_putStatic;
                case 5:
                    return REF_invokeVirtual;
                case 6:
                    return REF_invokeStatic;
                case 7:
                    return REF_invokeSpecial;
                case 8:
                    return REF_newInvokeSpecial;
                case 9:
                    return REF_invokeInterface;
                default:
                    return null;
            }
        }
    }

    public static class UnexpectedEntry extends ConstantPoolException {
        private static final long serialVersionUID = 6986335935377933211L;
        public final int expected_tag;
        public final int found_tag;

        public UnexpectedEntry(int i, int i2, int i3) {
            super(i);
            this.expected_tag = i2;
            this.found_tag = i3;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            return "unexpected entry at #" + this.index + " -- expected tag " + this.expected_tag + ", found " + this.found_tag;
        }
    }

    public interface Visitor<R, P> {
        R visitClass(CONSTANT_Class_info cONSTANT_Class_info, P p);

        R visitDouble(CONSTANT_Double_info cONSTANT_Double_info, P p);

        R visitDynamicConstant(CONSTANT_Dynamic_info cONSTANT_Dynamic_info, P p);

        R visitFieldref(CONSTANT_Fieldref_info cONSTANT_Fieldref_info, P p);

        R visitFloat(CONSTANT_Float_info cONSTANT_Float_info, P p);

        R visitInteger(CONSTANT_Integer_info cONSTANT_Integer_info, P p);

        R visitInterfaceMethodref(CONSTANT_InterfaceMethodref_info cONSTANT_InterfaceMethodref_info, P p);

        R visitInvokeDynamic(CONSTANT_InvokeDynamic_info cONSTANT_InvokeDynamic_info, P p);

        R visitLong(CONSTANT_Long_info cONSTANT_Long_info, P p);

        R visitMethodHandle(CONSTANT_MethodHandle_info cONSTANT_MethodHandle_info, P p);

        R visitMethodType(CONSTANT_MethodType_info cONSTANT_MethodType_info, P p);

        R visitMethodref(CONSTANT_Methodref_info cONSTANT_Methodref_info, P p);

        R visitModule(CONSTANT_Module_info cONSTANT_Module_info, P p);

        R visitNameAndType(CONSTANT_NameAndType_info cONSTANT_NameAndType_info, P p);

        R visitPackage(CONSTANT_Package_info cONSTANT_Package_info, P p);

        R visitString(CONSTANT_String_info cONSTANT_String_info, P p);

        R visitUtf8(CONSTANT_Utf8_info cONSTANT_Utf8_info, P p);
    }

    public ConstantPool(ClassReader classReader) throws InvalidEntry, IOException {
        int unsignedShort = classReader.readUnsignedShort();
        this.pool = new CPInfo[unsignedShort];
        int i = 1;
        while (i < unsignedShort) {
            int unsignedByte = classReader.readUnsignedByte();
            switch (unsignedByte) {
                case 1:
                    this.pool[i] = new CONSTANT_Utf8_info(classReader);
                    continue;
                    i++;
                    break;
                case 2:
                case 13:
                case 14:
                default:
                    throw new InvalidEntry(i, unsignedByte);
                case 3:
                    this.pool[i] = new CONSTANT_Integer_info(classReader);
                    continue;
                    i++;
                    break;
                case 4:
                    this.pool[i] = new CONSTANT_Float_info(classReader);
                    continue;
                    i++;
                    break;
                case 5:
                    this.pool[i] = new CONSTANT_Long_info(classReader);
                    break;
                case 6:
                    this.pool[i] = new CONSTANT_Double_info(classReader);
                    break;
                case 7:
                    this.pool[i] = new CONSTANT_Class_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 8:
                    this.pool[i] = new CONSTANT_String_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 9:
                    this.pool[i] = new CONSTANT_Fieldref_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 10:
                    this.pool[i] = new CONSTANT_Methodref_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 11:
                    this.pool[i] = new CONSTANT_InterfaceMethodref_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 12:
                    this.pool[i] = new CONSTANT_NameAndType_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 15:
                    this.pool[i] = new CONSTANT_MethodHandle_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 16:
                    this.pool[i] = new CONSTANT_MethodType_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 17:
                    this.pool[i] = new CONSTANT_Dynamic_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 18:
                    this.pool[i] = new CONSTANT_InvokeDynamic_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 19:
                    this.pool[i] = new CONSTANT_Module_info(this, classReader);
                    continue;
                    i++;
                    break;
                case 20:
                    this.pool[i] = new CONSTANT_Package_info(this, classReader);
                    continue;
                    i++;
                    break;
            }
            i++;
            i++;
        }
    }

    public static /* synthetic */ Iterator a(ConstantPool constantPool) {
        constantPool.getClass();
        return new Iterator<CPInfo>() { // from class: com.sun.tools.classfile.ConstantPool.1
            private CPInfo current;
            private int next = 1;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next < ConstantPool.this.pool.length;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public CPInfo next() {
                CPInfo cPInfo = ConstantPool.this.pool[this.next];
                this.current = cPInfo;
                int tag = cPInfo.getTag();
                if (tag == 5 || tag == 6) {
                    this.next += 2;
                } else {
                    this.next++;
                }
                return this.current;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public int byteLength() {
        int iByteLength = 2;
        int size = 1;
        while (size < size()) {
            CPInfo cPInfo = this.pool[size];
            iByteLength += cPInfo.byteLength();
            size += cPInfo.size();
        }
        return iByteLength;
    }

    public Iterable<CPInfo> entries() {
        return new Iterable() { // from class: cs2
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return ConstantPool.a(this.b);
            }
        };
    }

    public CPInfo get(int i) throws InvalidIndex {
        if (i > 0) {
            CPInfo[] cPInfoArr = this.pool;
            if (i < cPInfoArr.length) {
                CPInfo cPInfo = cPInfoArr[i];
                if (cPInfo != null) {
                    return cPInfo;
                }
                throw new InvalidIndex(i);
            }
        }
        throw new InvalidIndex(i);
    }

    public CONSTANT_Class_info getClassInfo(int i) throws InvalidIndex, UnexpectedEntry {
        return (CONSTANT_Class_info) get(i, 7);
    }

    public CONSTANT_Module_info getModuleInfo(int i) throws InvalidIndex, UnexpectedEntry {
        return (CONSTANT_Module_info) get(i, 19);
    }

    public CONSTANT_NameAndType_info getNameAndTypeInfo(int i) throws InvalidIndex, UnexpectedEntry {
        return (CONSTANT_NameAndType_info) get(i, 12);
    }

    public CONSTANT_Package_info getPackageInfo(int i) throws InvalidIndex, UnexpectedEntry {
        return (CONSTANT_Package_info) get(i, 20);
    }

    public int getUTF8Index(String str) throws EntryNotFound {
        int i = 1;
        while (true) {
            CPInfo[] cPInfoArr = this.pool;
            if (i >= cPInfoArr.length) {
                throw new EntryNotFound(str);
            }
            CPInfo cPInfo = cPInfoArr[i];
            if ((cPInfo instanceof CONSTANT_Utf8_info) && ((CONSTANT_Utf8_info) cPInfo).value.equals(str)) {
                return i;
            }
            i++;
        }
    }

    public CONSTANT_Utf8_info getUTF8Info(int i) throws InvalidIndex, UnexpectedEntry {
        return (CONSTANT_Utf8_info) get(i, 1);
    }

    public String getUTF8Value(int i) throws InvalidIndex, UnexpectedEntry {
        return getUTF8Info(i).value;
    }

    public int size() {
        return this.pool.length;
    }

    public static class CONSTANT_Fieldref_info extends CPRefInfo {
        public CONSTANT_Fieldref_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool, classReader, 9);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitFieldref(this, d);
        }

        public String toString() {
            return "CONSTANT_Fieldref_info[class_index: " + this.class_index + ", name_and_type_index: " + this.name_and_type_index + "]";
        }

        public CONSTANT_Fieldref_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool, 9, i, i2);
        }
    }

    public static class CONSTANT_InterfaceMethodref_info extends CPRefInfo {
        public CONSTANT_InterfaceMethodref_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool, classReader, 11);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitInterfaceMethodref(this, d);
        }

        public String toString() {
            return "CONSTANT_InterfaceMethodref_info[class_index: " + this.class_index + ", name_and_type_index: " + this.name_and_type_index + "]";
        }

        public CONSTANT_InterfaceMethodref_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool, 11, i, i2);
        }
    }

    public static class CONSTANT_Methodref_info extends CPRefInfo {
        public CONSTANT_Methodref_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool, classReader, 10);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitMethodref(this, d);
        }

        public String toString() {
            return "CONSTANT_Methodref_info[class_index: " + this.class_index + ", name_and_type_index: " + this.name_and_type_index + "]";
        }

        public CONSTANT_Methodref_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool, 10, i, i2);
        }
    }

    public static abstract class CPInfo {
        protected final ConstantPool cp;

        public CPInfo() {
            this.cp = null;
        }

        public abstract <R, D> R accept(Visitor<R, D> visitor, D d);

        public abstract int byteLength();

        public abstract int getTag();

        public int size() {
            return 1;
        }

        public CPInfo(ConstantPool constantPool) {
            this.cp = constantPool;
        }
    }

    public static class CONSTANT_Class_info extends CPInfo {
        public final int name_index;

        public CONSTANT_Class_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.name_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitClass(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 3;
        }

        public String getBaseName() throws ConstantPoolException {
            String name = getName();
            if (!name.startsWith("[")) {
                return name;
            }
            int iIndexOf = name.indexOf("[L");
            if (iIndexOf == -1) {
                return null;
            }
            return name.substring(iIndexOf + 2, name.length() - 1);
        }

        public int getDimensionCount() throws ConstantPoolException {
            int i = 0;
            while (getName().charAt(i) == '[') {
                i++;
            }
            return i;
        }

        public String getName() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.name_index);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 7;
        }

        public String toString() {
            return "CONSTANT_Class_info[name_index: " + this.name_index + "]";
        }

        public CONSTANT_Class_info(ConstantPool constantPool, int i) {
            super(constantPool);
            this.name_index = i;
        }
    }

    public static class CONSTANT_Double_info extends CPInfo {
        public final double value;

        public CONSTANT_Double_info(ClassReader classReader) throws IOException {
            this.value = classReader.readDouble();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitDouble(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 9;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 6;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int size() {
            return 2;
        }

        public String toString() {
            return "CONSTANT_Double_info[value: " + this.value + "]";
        }

        public CONSTANT_Double_info(double d) {
            this.value = d;
        }
    }

    public static class CONSTANT_Float_info extends CPInfo {
        public final float value;

        public CONSTANT_Float_info(ClassReader classReader) throws IOException {
            this.value = classReader.readFloat();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitFloat(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 4;
        }

        public String toString() {
            return "CONSTANT_Float_info[value: " + this.value + "]";
        }

        public CONSTANT_Float_info(float f) {
            this.value = f;
        }
    }

    public static class CONSTANT_Integer_info extends CPInfo {
        public final int value;

        public CONSTANT_Integer_info(ClassReader classReader) throws IOException {
            this.value = classReader.readInt();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitInteger(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 3;
        }

        public String toString() {
            return "CONSTANT_Integer_info[value: " + this.value + "]";
        }

        public CONSTANT_Integer_info(int i) {
            this.value = i;
        }
    }

    public static class CONSTANT_Long_info extends CPInfo {
        public final long value;

        public CONSTANT_Long_info(ClassReader classReader) throws IOException {
            this.value = classReader.readLong();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitLong(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 9;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 5;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int size() {
            return 2;
        }

        public String toString() {
            return "CONSTANT_Long_info[value: " + this.value + "]";
        }

        public CONSTANT_Long_info(long j) {
            this.value = j;
        }
    }

    public static class CONSTANT_MethodType_info extends CPInfo {
        public final int descriptor_index;

        public CONSTANT_MethodType_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.descriptor_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitMethodType(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 3;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 16;
        }

        public String getType() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.descriptor_index);
        }

        public String toString() {
            return "CONSTANT_MethodType_info[signature_index: " + this.descriptor_index + "]";
        }

        public CONSTANT_MethodType_info(ConstantPool constantPool, int i) {
            super(constantPool);
            this.descriptor_index = i;
        }
    }

    public static class CONSTANT_Module_info extends CPInfo {
        public final int name_index;

        public CONSTANT_Module_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.name_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitModule(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 3;
        }

        public String getName() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.name_index);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 19;
        }

        public String toString() {
            return "CONSTANT_Module_info[name_index: " + this.name_index + "]";
        }

        public CONSTANT_Module_info(ConstantPool constantPool, int i) {
            super(constantPool);
            this.name_index = i;
        }
    }

    public static class CONSTANT_Package_info extends CPInfo {
        public final int name_index;

        public CONSTANT_Package_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.name_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitPackage(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 3;
        }

        public String getName() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.name_index);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 20;
        }

        public String toString() {
            return "CONSTANT_Package_info[name_index: " + this.name_index + "]";
        }

        public CONSTANT_Package_info(ConstantPool constantPool, int i) {
            super(constantPool);
            this.name_index = i;
        }
    }

    public static class CONSTANT_String_info extends CPInfo {
        public final int string_index;

        public CONSTANT_String_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.string_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitString(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 3;
        }

        public String getString() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.string_index);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 8;
        }

        public String toString() {
            return "CONSTANT_String_info[class_index: " + this.string_index + "]";
        }

        public CONSTANT_String_info(ConstantPool constantPool, int i) {
            super(constantPool);
            this.string_index = i;
        }
    }

    public static class CONSTANT_Utf8_info extends CPInfo {
        public final String value;

        /* JADX INFO: renamed from: com.sun.tools.classfile.ConstantPool$CONSTANT_Utf8_info$1SizeOutputStream, reason: invalid class name */
        public class C1SizeOutputStream extends OutputStream {
            int size;

            public C1SizeOutputStream() {
            }

            @Override // java.io.OutputStream
            public void write(int i) {
                this.size++;
            }
        }

        public CONSTANT_Utf8_info(ClassReader classReader) throws IOException {
            this.value = classReader.readUTF();
        }

        public static boolean isPrintableAscii(String str) {
            for (int i = 0; i < str.length(); i++) {
                char cCharAt = str.charAt(i);
                if (cCharAt < ' ' || cCharAt >= 127) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitUtf8(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            C1SizeOutputStream c1SizeOutputStream = new C1SizeOutputStream();
            try {
                new DataOutputStream(c1SizeOutputStream).writeUTF(this.value);
            } catch (IOException unused) {
            }
            return c1SizeOutputStream.size + 1;
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 1;
        }

        public String toString() {
            if (this.value.length() >= 32 || !isPrintableAscii(this.value)) {
                return "CONSTANT_Utf8_info[value: (" + this.value.length() + " chars)]";
            }
            return "CONSTANT_Utf8_info[value: \"" + this.value + "\"]";
        }

        public CONSTANT_Utf8_info(String str) {
            this.value = str;
        }
    }

    public static class CONSTANT_Dynamic_info extends CPInfo {
        public final int bootstrap_method_attr_index;
        public final int name_and_type_index;

        public CONSTANT_Dynamic_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.bootstrap_method_attr_index = classReader.readUnsignedShort();
            this.name_and_type_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitDynamicConstant(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        public CONSTANT_NameAndType_info getNameAndTypeInfo() throws ConstantPoolException {
            return this.cp.getNameAndTypeInfo(this.name_and_type_index);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 17;
        }

        public String toString() {
            return "CONSTANT_Dynamic_info[bootstrap_method_index: " + this.bootstrap_method_attr_index + ", name_and_type_index: " + this.name_and_type_index + "]";
        }

        public CONSTANT_Dynamic_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool);
            this.bootstrap_method_attr_index = i;
            this.name_and_type_index = i2;
        }
    }

    public static class CONSTANT_InvokeDynamic_info extends CPInfo {
        public final int bootstrap_method_attr_index;
        public final int name_and_type_index;

        public CONSTANT_InvokeDynamic_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.bootstrap_method_attr_index = classReader.readUnsignedShort();
            this.name_and_type_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitInvokeDynamic(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        public CONSTANT_NameAndType_info getNameAndTypeInfo() throws ConstantPoolException {
            return this.cp.getNameAndTypeInfo(this.name_and_type_index);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 18;
        }

        public String toString() {
            return "CONSTANT_InvokeDynamic_info[bootstrap_method_index: " + this.bootstrap_method_attr_index + ", name_and_type_index: " + this.name_and_type_index + "]";
        }

        public CONSTANT_InvokeDynamic_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool);
            this.bootstrap_method_attr_index = i;
            this.name_and_type_index = i2;
        }
    }

    public static class CONSTANT_NameAndType_info extends CPInfo {
        public final int name_index;
        public final int type_index;

        public CONSTANT_NameAndType_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.name_index = classReader.readUnsignedShort();
            this.type_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitNameAndType(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        public String getName() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.name_index);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 12;
        }

        public String getType() throws ConstantPoolException {
            return this.cp.getUTF8Value(this.type_index);
        }

        public String toString() {
            return "CONSTANT_NameAndType_info[name_index: " + this.name_index + ", type_index: " + this.type_index + "]";
        }

        public CONSTANT_NameAndType_info(ConstantPool constantPool, int i, int i2) {
            super(constantPool);
            this.name_index = i;
            this.type_index = i2;
        }
    }

    public static abstract class CPRefInfo extends CPInfo {
        public final int class_index;
        public final int name_and_type_index;
        public final int tag;

        public CPRefInfo(ConstantPool constantPool, ClassReader classReader, int i) throws IOException {
            super(constantPool);
            this.tag = i;
            this.class_index = classReader.readUnsignedShort();
            this.name_and_type_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 5;
        }

        public CONSTANT_Class_info getClassInfo() throws ConstantPoolException {
            return this.cp.getClassInfo(this.class_index);
        }

        public String getClassName() throws ConstantPoolException {
            return this.cp.getClassInfo(this.class_index).getName();
        }

        public CONSTANT_NameAndType_info getNameAndTypeInfo() throws ConstantPoolException {
            return this.cp.getNameAndTypeInfo(this.name_and_type_index);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return this.tag;
        }

        public CPRefInfo(ConstantPool constantPool, int i, int i2, int i3) {
            super(constantPool);
            this.tag = i;
            this.class_index = i2;
            this.name_and_type_index = i3;
        }
    }

    public static class CONSTANT_MethodHandle_info extends CPInfo {
        public final int reference_index;
        public final RefKind reference_kind;

        public CONSTANT_MethodHandle_info(ConstantPool constantPool, ClassReader classReader) throws IOException {
            super(constantPool);
            this.reference_kind = RefKind.getRefkind(classReader.readUnsignedByte());
            this.reference_index = classReader.readUnsignedShort();
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public <R, D> R accept(Visitor<R, D> visitor, D d) {
            return visitor.visitMethodHandle(this, d);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int byteLength() {
            return 4;
        }

        public CPRefInfo getCPRefInfo() throws ConstantPoolException {
            int tag = this.cp.get(this.reference_index).getTag();
            if (tag != 9 && tag != 11) {
                tag = 10;
            }
            return (CPRefInfo) this.cp.get(this.reference_index, tag);
        }

        @Override // com.sun.tools.classfile.ConstantPool.CPInfo
        public int getTag() {
            return 15;
        }

        public String toString() {
            return "CONSTANT_MethodHandle_info[ref_kind: " + this.reference_kind + ", member_index: " + this.reference_index + "]";
        }

        public CONSTANT_MethodHandle_info(ConstantPool constantPool, RefKind refKind, int i) {
            super(constantPool);
            this.reference_kind = refKind;
            this.reference_index = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CPInfo get(int i, int i2) throws InvalidIndex, UnexpectedEntry {
        CPInfo cPInfo = get(i);
        if (cPInfo.getTag() == i2) {
            return cPInfo;
        }
        throw new UnexpectedEntry(i, i2, cPInfo.getTag());
    }

    public ConstantPool(CPInfo[] cPInfoArr) {
        this.pool = cPInfoArr;
    }
}

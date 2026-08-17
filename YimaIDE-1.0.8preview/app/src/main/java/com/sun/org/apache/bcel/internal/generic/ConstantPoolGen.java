package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.Constant;
import com.sun.org.apache.bcel.internal.classfile.ConstantCP;
import com.sun.org.apache.bcel.internal.classfile.ConstantClass;
import com.sun.org.apache.bcel.internal.classfile.ConstantDouble;
import com.sun.org.apache.bcel.internal.classfile.ConstantDynamic;
import com.sun.org.apache.bcel.internal.classfile.ConstantFieldref;
import com.sun.org.apache.bcel.internal.classfile.ConstantFloat;
import com.sun.org.apache.bcel.internal.classfile.ConstantInteger;
import com.sun.org.apache.bcel.internal.classfile.ConstantInterfaceMethodref;
import com.sun.org.apache.bcel.internal.classfile.ConstantInvokeDynamic;
import com.sun.org.apache.bcel.internal.classfile.ConstantLong;
import com.sun.org.apache.bcel.internal.classfile.ConstantMethodref;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.ConstantString;
import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import defpackage.aca;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ConstantPoolGen {
    private static final int DEFAULT_BUFFER_SIZE = 256;
    private static final String FIELDREF_DELIM = "&";
    private static final String IMETHODREF_DELIM = "#";
    private static final String METHODREF_DELIM = ":";
    private static final String NAT_DELIM = "%";
    private final Map<String, Integer> classTable;

    @Deprecated
    protected Constant[] constants;
    private final Map<String, Integer> cpTable;

    @Deprecated
    protected int index;
    private final Map<String, Integer> natTable;

    @Deprecated
    protected int size;
    private final Map<String, Integer> stringTable;
    private final Map<String, Integer> utf8Table;

    public ConstantPoolGen(Constant[] constantArr) {
        this.stringTable = new HashMap();
        this.classTable = new HashMap();
        this.utf8Table = new HashMap();
        this.natTable = new HashMap();
        this.cpTable = new HashMap();
        this.index = 1;
        if (constantArr.length > 65535) {
            lpe.a("The number of constants ", constantArr.length, " is over the size limit of the constant pool: 65535");
            throw null;
        }
        StringBuilder sb = new StringBuilder(256);
        int iMin = Math.min(Math.max(256, constantArr.length + 64), 65535);
        this.size = iMin;
        Constant[] constantArr2 = new Constant[iMin];
        this.constants = constantArr2;
        System.arraycopy(constantArr, 0, constantArr2, 0, constantArr.length);
        if (constantArr.length > 0) {
            this.index = constantArr.length;
        }
        for (int i = 1; i < this.index; i++) {
            Constant[] constantArr3 = this.constants;
            Constant constant = constantArr3[i];
            if (constant instanceof ConstantString) {
                String bytes = ((ConstantUtf8) constantArr3[((ConstantString) constant).getStringIndex()]).getBytes();
                if (!this.stringTable.containsKey(bytes)) {
                    this.stringTable.put(bytes, Integer.valueOf(i));
                }
            } else if (constant instanceof ConstantClass) {
                String bytes2 = ((ConstantUtf8) constantArr3[((ConstantClass) constant).getNameIndex()]).getBytes();
                if (!this.classTable.containsKey(bytes2)) {
                    this.classTable.put(bytes2, Integer.valueOf(i));
                }
            } else if (constant instanceof ConstantNameAndType) {
                ConstantNameAndType constantNameAndType = (ConstantNameAndType) constant;
                ConstantUtf8 constantUtf8 = (ConstantUtf8) constantArr3[constantNameAndType.getNameIndex()];
                ConstantUtf8 constantUtf9 = (ConstantUtf8) this.constants[constantNameAndType.getSignatureIndex()];
                sb.append(constantUtf8.getBytes());
                sb.append(NAT_DELIM);
                sb.append(constantUtf9.getBytes());
                String string = sb.toString();
                sb.delete(0, sb.length());
                if (!this.natTable.containsKey(string)) {
                    this.natTable.put(string, Integer.valueOf(i));
                }
            } else if (constant instanceof ConstantUtf8) {
                String bytes3 = ((ConstantUtf8) constant).getBytes();
                if (!this.utf8Table.containsKey(bytes3)) {
                    this.utf8Table.put(bytes3, Integer.valueOf(i));
                }
            } else if (constant instanceof ConstantCP) {
                ConstantCP constantCP = (ConstantCP) constant;
                String string2 = constant instanceof ConstantInvokeDynamic ? Integer.toString(((ConstantInvokeDynamic) constantCP).getBootstrapMethodAttrIndex()) : constant instanceof ConstantDynamic ? Integer.toString(((ConstantDynamic) constantCP).getBootstrapMethodAttrIndex()) : Utility.pathToPackage(((ConstantUtf8) this.constants[((ConstantClass) constantArr3[constantCP.getClassIndex()]).getNameIndex()]).getBytes());
                ConstantNameAndType constantNameAndType2 = (ConstantNameAndType) this.constants[constantCP.getNameAndTypeIndex()];
                String bytes4 = ((ConstantUtf8) this.constants[constantNameAndType2.getNameIndex()]).getBytes();
                String bytes5 = ((ConstantUtf8) this.constants[constantNameAndType2.getSignatureIndex()]).getBytes();
                String str = constant instanceof ConstantInterfaceMethodref ? IMETHODREF_DELIM : constant instanceof ConstantFieldref ? FIELDREF_DELIM : METHODREF_DELIM;
                sb.append(string2);
                sb.append(str);
                sb.append(bytes4);
                sb.append(str);
                sb.append(bytes5);
                String string3 = sb.toString();
                sb.delete(0, sb.length());
                if (!this.cpTable.containsKey(string3)) {
                    this.cpTable.put(string3, Integer.valueOf(i));
                }
            }
        }
    }

    private int addClass_(String str) {
        int iLookupClass = lookupClass(str);
        if (iLookupClass != -1) {
            return iLookupClass;
        }
        adjustSize();
        ConstantClass constantClass = new ConstantClass(addUtf8(str));
        int i = this.index;
        Constant[] constantArr = this.constants;
        this.index = i + 1;
        constantArr[i] = constantClass;
        return computeIfAbsent(this.classTable, str, i);
    }

    private int computeIfAbsent(Map<String, Integer> map, String str, final int i) {
        return map.computeIfAbsent(str, new Function() { // from class: ds2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(i);
            }
        }).intValue();
    }

    private int getIndex(Map<String, Integer> map, String str) {
        return toIndex(map.get(str));
    }

    private int toIndex(Integer num) {
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public int addArrayClass(ArrayType arrayType) {
        return addClass_(arrayType.getSignature());
    }

    public int addClass(ObjectType objectType) {
        return addClass(objectType.getClassName());
    }

    public int addConstant(Constant constant, ConstantPoolGen constantPoolGen) {
        Constant[] constantPool = constantPoolGen.getConstantPool().getConstantPool();
        switch (constant.getTag()) {
            case 1:
                return addUtf8(((ConstantUtf8) constant).getBytes());
            case 2:
            default:
                aca.a("Unknown constant type ", constant);
                return 0;
            case 3:
                return addInteger(((ConstantInteger) constant).getBytes());
            case 4:
                return addFloat(((ConstantFloat) constant).getBytes());
            case 5:
                return addLong(((ConstantLong) constant).getBytes());
            case 6:
                return addDouble(((ConstantDouble) constant).getBytes());
            case 7:
                return addClass(((ConstantUtf8) constantPool[((ConstantClass) constant).getNameIndex()]).getBytes());
            case 8:
                return addString(((ConstantUtf8) constantPool[((ConstantString) constant).getStringIndex()]).getBytes());
            case 9:
            case 10:
            case 11:
                ConstantCP constantCP = (ConstantCP) constant;
                ConstantClass constantClass = (ConstantClass) constantPool[constantCP.getClassIndex()];
                ConstantNameAndType constantNameAndType = (ConstantNameAndType) constantPool[constantCP.getNameAndTypeIndex()];
                String strPathToPackage = Utility.pathToPackage(((ConstantUtf8) constantPool[constantClass.getNameIndex()]).getBytes());
                String bytes = ((ConstantUtf8) constantPool[constantNameAndType.getNameIndex()]).getBytes();
                String bytes2 = ((ConstantUtf8) constantPool[constantNameAndType.getSignatureIndex()]).getBytes();
                switch (constant.getTag()) {
                    case 9:
                        return addFieldref(strPathToPackage, bytes, bytes2);
                    case 10:
                        return addMethodref(strPathToPackage, bytes, bytes2);
                    case 11:
                        return addInterfaceMethodref(strPathToPackage, bytes, bytes2);
                    default:
                        aca.a("Unknown constant type ", constant);
                        return 0;
                }
            case 12:
                ConstantNameAndType constantNameAndType2 = (ConstantNameAndType) constant;
                return addNameAndType(((ConstantUtf8) constantPool[constantNameAndType2.getNameIndex()]).getBytes(), ((ConstantUtf8) constantPool[constantNameAndType2.getSignatureIndex()]).getBytes());
        }
    }

    public int addDouble(double d) {
        int iLookupDouble = lookupDouble(d);
        if (iLookupDouble != -1) {
            return iLookupDouble;
        }
        adjustSize();
        int i = this.index;
        this.constants[i] = new ConstantDouble(d);
        this.index += 2;
        return i;
    }

    public int addFieldref(String str, String str2, String str3) {
        int iLookupFieldref = lookupFieldref(str, str2, str3);
        if (iLookupFieldref != -1) {
            return iLookupFieldref;
        }
        adjustSize();
        int iAddClass = addClass(str);
        int iAddNameAndType = addNameAndType(str2, str3);
        int i = this.index;
        Constant[] constantArr = this.constants;
        this.index = i + 1;
        constantArr[i] = new ConstantFieldref(iAddClass, iAddNameAndType);
        return computeIfAbsent(this.cpTable, str + FIELDREF_DELIM + str2 + FIELDREF_DELIM + str3, i);
    }

    public int addFloat(float f) {
        int iLookupFloat = lookupFloat(f);
        if (iLookupFloat != -1) {
            return iLookupFloat;
        }
        adjustSize();
        int i = this.index;
        Constant[] constantArr = this.constants;
        this.index = i + 1;
        constantArr[i] = new ConstantFloat(f);
        return i;
    }

    public int addInteger(int i) {
        int iLookupInteger = lookupInteger(i);
        if (iLookupInteger != -1) {
            return iLookupInteger;
        }
        adjustSize();
        int i2 = this.index;
        Constant[] constantArr = this.constants;
        this.index = i2 + 1;
        constantArr[i2] = new ConstantInteger(i);
        return i2;
    }

    public int addInterfaceMethodref(String str, String str2, String str3) {
        int iLookupInterfaceMethodref = lookupInterfaceMethodref(str, str2, str3);
        if (iLookupInterfaceMethodref != -1) {
            return iLookupInterfaceMethodref;
        }
        adjustSize();
        int iAddClass = addClass(str);
        int iAddNameAndType = addNameAndType(str2, str3);
        int i = this.index;
        Constant[] constantArr = this.constants;
        this.index = i + 1;
        constantArr[i] = new ConstantInterfaceMethodref(iAddClass, iAddNameAndType);
        return computeIfAbsent(this.cpTable, str + IMETHODREF_DELIM + str2 + IMETHODREF_DELIM + str3, i);
    }

    public int addLong(long j) {
        int iLookupLong = lookupLong(j);
        if (iLookupLong != -1) {
            return iLookupLong;
        }
        adjustSize();
        int i = this.index;
        this.constants[i] = new ConstantLong(j);
        this.index += 2;
        return i;
    }

    public int addMethodref(String str, String str2, String str3) {
        int iLookupMethodref = lookupMethodref(str, str2, str3);
        if (iLookupMethodref != -1) {
            return iLookupMethodref;
        }
        adjustSize();
        int iAddNameAndType = addNameAndType(str2, str3);
        int iAddClass = addClass(str);
        int i = this.index;
        Constant[] constantArr = this.constants;
        this.index = i + 1;
        constantArr[i] = new ConstantMethodref(iAddClass, iAddNameAndType);
        return computeIfAbsent(this.cpTable, str + METHODREF_DELIM + str2 + METHODREF_DELIM + str3, i);
    }

    public int addNameAndType(String str, String str2) {
        int iLookupNameAndType = lookupNameAndType(str, str2);
        if (iLookupNameAndType != -1) {
            return iLookupNameAndType;
        }
        adjustSize();
        int iAddUtf8 = addUtf8(str);
        int iAddUtf9 = addUtf8(str2);
        int i = this.index;
        Constant[] constantArr = this.constants;
        this.index = i + 1;
        constantArr[i] = new ConstantNameAndType(iAddUtf8, iAddUtf9);
        return computeIfAbsent(this.natTable, str + NAT_DELIM + str2, i);
    }

    public int addString(String str) {
        int iLookupString = lookupString(str);
        if (iLookupString != -1) {
            return iLookupString;
        }
        int iAddUtf8 = addUtf8(str);
        adjustSize();
        ConstantString constantString = new ConstantString(iAddUtf8);
        int i = this.index;
        Constant[] constantArr = this.constants;
        this.index = i + 1;
        constantArr[i] = constantString;
        return computeIfAbsent(this.stringTable, str, i);
    }

    public int addUtf8(String str) {
        int iLookupUtf8 = lookupUtf8(str);
        if (iLookupUtf8 != -1) {
            return iLookupUtf8;
        }
        adjustSize();
        int i = this.index;
        Constant[] constantArr = this.constants;
        this.index = i + 1;
        constantArr[i] = new ConstantUtf8(str);
        return computeIfAbsent(this.utf8Table, str, i);
    }

    public void adjustSize() {
        int i = this.index;
        if (i + 3 >= 65535) {
            lpe.a("The number of constants ", this.index + 3, " is over the size limit of the constant pool: 65535");
            return;
        }
        int i2 = i + 3;
        int i3 = this.size;
        if (i2 >= i3) {
            Constant[] constantArr = this.constants;
            int i4 = i3 * 2;
            this.size = i4;
            int iMin = Math.min(i4, 65535);
            this.size = iMin;
            Constant[] constantArr2 = new Constant[iMin];
            this.constants = constantArr2;
            System.arraycopy(constantArr, 0, constantArr2, 0, this.index);
        }
    }

    public Constant getConstant(int i) {
        return this.constants[i];
    }

    public ConstantPool getConstantPool() {
        return new ConstantPool(this.constants);
    }

    public ConstantPool getFinalConstantPool() {
        return new ConstantPool((Constant[]) Arrays.copyOf(this.constants, this.index));
    }

    public int getSize() {
        return this.index;
    }

    public int lookupClass(String str) {
        return getIndex(this.classTable, Utility.packageToPath(str));
    }

    public int lookupDouble(double d) {
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        for (int i = 1; i < this.index; i++) {
            Constant constant = this.constants[i];
            if ((constant instanceof ConstantDouble) && Double.doubleToLongBits(((ConstantDouble) constant).getBytes()) == jDoubleToLongBits) {
                return i;
            }
        }
        return -1;
    }

    public int lookupFieldref(String str, String str2, String str3) {
        return getIndex(this.cpTable, str + FIELDREF_DELIM + str2 + FIELDREF_DELIM + str3);
    }

    public int lookupFloat(float f) {
        int iFloatToIntBits = Float.floatToIntBits(f);
        for (int i = 1; i < this.index; i++) {
            Constant constant = this.constants[i];
            if ((constant instanceof ConstantFloat) && Float.floatToIntBits(((ConstantFloat) constant).getBytes()) == iFloatToIntBits) {
                return i;
            }
        }
        return -1;
    }

    public int lookupInteger(int i) {
        for (int i2 = 1; i2 < this.index; i2++) {
            Constant constant = this.constants[i2];
            if ((constant instanceof ConstantInteger) && ((ConstantInteger) constant).getBytes() == i) {
                return i2;
            }
        }
        return -1;
    }

    public int lookupInterfaceMethodref(String str, String str2, String str3) {
        return getIndex(this.cpTable, str + IMETHODREF_DELIM + str2 + IMETHODREF_DELIM + str3);
    }

    public int lookupLong(long j) {
        for (int i = 1; i < this.index; i++) {
            Constant constant = this.constants[i];
            if ((constant instanceof ConstantLong) && ((ConstantLong) constant).getBytes() == j) {
                return i;
            }
        }
        return -1;
    }

    public int lookupMethodref(String str, String str2, String str3) {
        return getIndex(this.cpTable, str + METHODREF_DELIM + str2 + METHODREF_DELIM + str3);
    }

    public int lookupNameAndType(String str, String str2) {
        return getIndex(this.natTable, str + NAT_DELIM + str2);
    }

    public int lookupString(String str) {
        return getIndex(this.stringTable, str);
    }

    public int lookupUtf8(String str) {
        return getIndex(this.utf8Table, str);
    }

    public void setConstant(int i, Constant constant) {
        this.constants[i] = constant;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < this.index; i++) {
            sb.append(i);
            sb.append(")");
            sb.append(this.constants[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    public int addClass(String str) {
        return addClass_(Utility.packageToPath(str));
    }

    public int lookupInterfaceMethodref(MethodGen methodGen) {
        return lookupInterfaceMethodref(methodGen.getClassName(), methodGen.getName(), methodGen.getSignature());
    }

    public int lookupMethodref(MethodGen methodGen) {
        return lookupMethodref(methodGen.getClassName(), methodGen.getName(), methodGen.getSignature());
    }

    public int addInterfaceMethodref(MethodGen methodGen) {
        return addInterfaceMethodref(methodGen.getClassName(), methodGen.getName(), methodGen.getSignature());
    }

    public int addMethodref(MethodGen methodGen) {
        return addMethodref(methodGen.getClassName(), methodGen.getName(), methodGen.getSignature());
    }

    public ConstantPoolGen() {
        this.stringTable = new HashMap();
        this.classTable = new HashMap();
        this.utf8Table = new HashMap();
        this.natTable = new HashMap();
        this.cpTable = new HashMap();
        this.index = 1;
        this.size = 256;
        this.constants = new Constant[256];
    }

    public ConstantPoolGen(ConstantPool constantPool) {
        this(constantPool.getConstantPool());
    }
}

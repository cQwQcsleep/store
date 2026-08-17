package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.AnnotationEntry;
import com.sun.org.apache.bcel.internal.classfile.Attribute;
import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import com.sun.org.apache.bcel.internal.classfile.ElementValuePair;
import com.sun.org.apache.bcel.internal.classfile.RuntimeInvisibleAnnotations;
import com.sun.org.apache.bcel.internal.classfile.RuntimeInvisibleParameterAnnotations;
import com.sun.org.apache.bcel.internal.classfile.RuntimeVisibleAnnotations;
import com.sun.org.apache.bcel.internal.classfile.RuntimeVisibleParameterAnnotations;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AnnotationEntryGen {
    static final AnnotationEntryGen[] EMPTY_ARRAY = new AnnotationEntryGen[0];
    private final ConstantPoolGen cpool;
    private List<ElementValuePairGen> evs;
    private boolean isRuntimeVisible;
    private int typeIndex;

    public AnnotationEntryGen(AnnotationEntry annotationEntry, ConstantPoolGen constantPoolGen, boolean z) {
        this.cpool = constantPoolGen;
        if (z) {
            this.typeIndex = constantPoolGen.addUtf8(annotationEntry.getAnnotationType());
        } else {
            this.typeIndex = annotationEntry.getAnnotationTypeIndex();
        }
        this.isRuntimeVisible = annotationEntry.isRuntimeVisible();
        this.evs = copyValues(annotationEntry.getElementValuePairs(), constantPoolGen, z);
    }

    private List<ElementValuePairGen> copyValues(ElementValuePair[] elementValuePairArr, ConstantPoolGen constantPoolGen, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (ElementValuePair elementValuePair : elementValuePairArr) {
            arrayList.add(new ElementValuePairGen(elementValuePair, constantPoolGen, z));
        }
        return arrayList;
    }

    public static Attribute[] getAnnotationAttributes(ConstantPoolGen constantPoolGen, AnnotationEntryGen[] annotationEntryGenArr) {
        if (annotationEntryGenArr.length == 0) {
            return Attribute.EMPTY_ARRAY;
        }
        try {
            int i = 0;
            int i2 = 0;
            for (AnnotationEntryGen annotationEntryGen : annotationEntryGenArr) {
                if (annotationEntryGen.isRuntimeVisible()) {
                    i2++;
                } else {
                    i++;
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream2);
                try {
                    dataOutputStream.writeShort(i2);
                    dataOutputStream2.writeShort(i);
                    for (AnnotationEntryGen annotationEntryGen2 : annotationEntryGenArr) {
                        if (annotationEntryGen2.isRuntimeVisible()) {
                            annotationEntryGen2.dump(dataOutputStream);
                        } else {
                            annotationEntryGen2.dump(dataOutputStream2);
                        }
                    }
                    dataOutputStream2.close();
                    dataOutputStream.close();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                    int iAddUtf8 = byteArray.length > 2 ? constantPoolGen.addUtf8(com.sun.tools.classfile.Attribute.RuntimeVisibleAnnotations) : -1;
                    int iAddUtf9 = byteArray2.length > 2 ? constantPoolGen.addUtf8(com.sun.tools.classfile.Attribute.RuntimeInvisibleAnnotations) : -1;
                    ArrayList arrayList = new ArrayList();
                    if (byteArray.length > 2) {
                        arrayList.add(new RuntimeVisibleAnnotations(iAddUtf8, byteArray.length, new DataInputStream(new ByteArrayInputStream(byteArray)), constantPoolGen.getConstantPool()));
                    }
                    if (byteArray2.length > 2) {
                        arrayList.add(new RuntimeInvisibleAnnotations(iAddUtf9, byteArray2.length, new DataInputStream(new ByteArrayInputStream(byteArray2)), constantPoolGen.getConstantPool()));
                    }
                    return (Attribute[]) arrayList.toArray(Attribute.EMPTY_ARRAY);
                } catch (Throwable th) {
                    try {
                        dataOutputStream2.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (IOException e) {
            System.err.println("IOException whilst processing annotations");
            e.printStackTrace();
            return null;
        }
    }

    public static Attribute[] getParameterAnnotationAttributes(ConstantPoolGen constantPoolGen, List<AnnotationEntryGen>[] listArr) {
        int[] iArr = new int[listArr.length];
        int[] iArr2 = new int[listArr.length];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < listArr.length; i3++) {
            try {
                List<AnnotationEntryGen> list = listArr[i3];
                if (list != null) {
                    Iterator<AnnotationEntryGen> it = list.iterator();
                    while (it.hasNext()) {
                        if (it.next().isRuntimeVisible()) {
                            iArr[i3] = iArr[i3] + 1;
                            i++;
                        } else {
                            iArr2[i3] = iArr2[i3] + 1;
                            i2++;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("IOException whilst processing parameter annotations");
                e.printStackTrace();
                return null;
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeByte(listArr.length);
            for (int i4 = 0; i4 < listArr.length; i4++) {
                dataOutputStream.writeShort(iArr[i4]);
                if (iArr[i4] > 0) {
                    for (AnnotationEntryGen annotationEntryGen : listArr[i4]) {
                        if (annotationEntryGen.isRuntimeVisible()) {
                            annotationEntryGen.dump(dataOutputStream);
                        }
                    }
                }
            }
            dataOutputStream.close();
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream2);
            try {
                dataOutputStream2.writeByte(listArr.length);
                for (int i5 = 0; i5 < listArr.length; i5++) {
                    dataOutputStream2.writeShort(iArr2[i5]);
                    if (iArr2[i5] > 0) {
                        for (AnnotationEntryGen annotationEntryGen2 : listArr[i5]) {
                            if (!annotationEntryGen2.isRuntimeVisible()) {
                                annotationEntryGen2.dump(dataOutputStream2);
                            }
                        }
                    }
                }
                dataOutputStream2.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                int iAddUtf8 = i > 0 ? constantPoolGen.addUtf8(com.sun.tools.classfile.Attribute.RuntimeVisibleParameterAnnotations) : -1;
                int iAddUtf9 = i2 > 0 ? constantPoolGen.addUtf8(com.sun.tools.classfile.Attribute.RuntimeInvisibleParameterAnnotations) : -1;
                ArrayList arrayList = new ArrayList();
                if (i > 0) {
                    arrayList.add(new RuntimeVisibleParameterAnnotations(iAddUtf8, byteArray.length, new DataInputStream(new ByteArrayInputStream(byteArray)), constantPoolGen.getConstantPool()));
                }
                if (i2 > 0) {
                    arrayList.add(new RuntimeInvisibleParameterAnnotations(iAddUtf9, byteArray2.length, new DataInputStream(new ByteArrayInputStream(byteArray2)), constantPoolGen.getConstantPool()));
                }
                return (Attribute[]) arrayList.toArray(Attribute.EMPTY_ARRAY);
            } catch (Throwable th) {
                try {
                    dataOutputStream2.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            try {
                dataOutputStream.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static AnnotationEntryGen read(DataInput dataInput, ConstantPoolGen constantPoolGen, boolean z) throws IOException {
        AnnotationEntryGen annotationEntryGen = new AnnotationEntryGen(constantPoolGen);
        annotationEntryGen.typeIndex = dataInput.readUnsignedShort();
        int unsignedShort = dataInput.readUnsignedShort();
        for (int i = 0; i < unsignedShort; i++) {
            annotationEntryGen.addElementNameValuePair(new ElementValuePairGen(dataInput.readUnsignedShort(), ElementValueGen.readElementValue(dataInput, constantPoolGen), constantPoolGen));
        }
        annotationEntryGen.isRuntimeVisible(z);
        return annotationEntryGen;
    }

    public void addElementNameValuePair(ElementValuePairGen elementValuePairGen) {
        if (this.evs == null) {
            this.evs = new ArrayList();
        }
        this.evs.add(elementValuePairGen);
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.typeIndex);
        dataOutputStream.writeShort(this.evs.size());
        Iterator<ElementValuePairGen> it = this.evs.iterator();
        while (it.hasNext()) {
            it.next().dump(dataOutputStream);
        }
    }

    public AnnotationEntry getAnnotation() {
        AnnotationEntry annotationEntry = new AnnotationEntry(this.typeIndex, this.cpool.getConstantPool(), this.isRuntimeVisible);
        Iterator<ElementValuePairGen> it = this.evs.iterator();
        while (it.hasNext()) {
            annotationEntry.addElementNameValuePair(it.next().getElementNameValuePair());
        }
        return annotationEntry;
    }

    public int getTypeIndex() {
        return this.typeIndex;
    }

    public final String getTypeName() {
        return getTypeSignature();
    }

    public final String getTypeSignature() {
        return ((ConstantUtf8) this.cpool.getConstant(this.typeIndex)).getBytes();
    }

    public List<ElementValuePairGen> getValues() {
        return this.evs;
    }

    public boolean isRuntimeVisible() {
        return this.isRuntimeVisible;
    }

    public String toShortString() {
        StringBuilder sb = new StringBuilder("@");
        sb.append(getTypeName());
        sb.append("(");
        int i = 0;
        while (i < this.evs.size()) {
            sb.append(this.evs.get(i));
            i++;
            if (i < this.evs.size()) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append("AnnotationGen:[");
        sb.append(getTypeName());
        sb.append(" #");
        sb.append(this.evs.size());
        sb.append(" {");
        int i = 0;
        while (i < this.evs.size()) {
            sb.append(this.evs.get(i));
            i++;
            if (i < this.evs.size()) {
                sb.append(",");
            }
        }
        sb.append("}]");
        return sb.toString();
    }

    private void isRuntimeVisible(boolean z) {
        this.isRuntimeVisible = z;
    }

    private AnnotationEntryGen(ConstantPoolGen constantPoolGen) {
        this.cpool = constantPoolGen;
    }

    public AnnotationEntryGen(ObjectType objectType, List<ElementValuePairGen> list, boolean z, ConstantPoolGen constantPoolGen) {
        this.cpool = constantPoolGen;
        this.typeIndex = constantPoolGen.addUtf8(objectType.getSignature());
        this.evs = list;
        this.isRuntimeVisible = z;
    }
}

package com.sun.jna.platform.win32;

import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import defpackage.jt6;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Guid {
    public static final IID IID_NULL = new IID();

    public static class CLSID extends GUID {
        public CLSID() {
        }

        public static class ByReference extends GUID {
            public ByReference() {
            }

            public ByReference(GUID guid) {
                super(guid);
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public CLSID(String str) {
            super(str);
        }

        public CLSID(GUID guid) {
            super(guid);
        }
    }

    public static class IID extends GUID {
        public IID(GUID guid) {
            this(guid.toGuidString());
        }

        public IID(Pointer pointer) {
            super(pointer);
        }

        public IID(String str) {
            super(str);
        }

        public IID(byte[] bArr) {
            super(bArr);
        }

        public IID() {
        }
    }

    public static class REFIID extends PointerType {
        public REFIID(IID iid) {
            super(iid.getPointer());
        }

        @Override // com.sun.jna.PointerType
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            return getValue().equals(((REFIID) obj).getValue());
        }

        public IID getValue() {
            return new IID(getPointer());
        }

        @Override // com.sun.jna.PointerType
        public int hashCode() {
            return getValue().hashCode();
        }

        public void setValue(IID iid) {
            setPointer(iid.getPointer());
        }

        public REFIID(Pointer pointer) {
            super(pointer);
        }

        public REFIID() {
        }
    }

    @Structure.FieldOrder({"Data1", "Data2", "Data3", "Data4"})
    public static class GUID extends Structure {
        public int Data1;
        public short Data2;
        public short Data3;
        public byte[] Data4;

        public GUID(GUID guid) {
            this.Data4 = new byte[8];
            this.Data1 = guid.Data1;
            this.Data2 = guid.Data2;
            this.Data3 = guid.Data3;
            this.Data4 = guid.Data4;
            writeFieldsToMemory();
        }

        public static GUID fromBinary(byte[] bArr) {
            if (bArr.length != 16) {
                jt6.a("Invalid data length: ", bArr.length);
                return null;
            }
            GUID guid = new GUID();
            guid.Data1 = (int) ((((((((long) (bArr[0] & 255)) << 8) | ((long) (bArr[1] & 255))) << 8) | ((long) (bArr[2] & 255))) << 8) | ((long) (bArr[3] & 255)));
            guid.Data2 = (short) (((bArr[4] & 255) << 8) | (bArr[5] & 255));
            guid.Data3 = (short) (((bArr[6] & 255) << 8) | (bArr[7] & 255));
            byte[] bArr2 = guid.Data4;
            bArr2[0] = bArr[8];
            bArr2[1] = bArr[9];
            bArr2[2] = bArr[10];
            bArr2[3] = bArr[11];
            bArr2[4] = bArr[12];
            bArr2[5] = bArr[13];
            bArr2[6] = bArr[14];
            bArr2[7] = bArr[15];
            guid.writeFieldsToMemory();
            return guid;
        }

        public static GUID fromString(String str) {
            char[] cArr = new char[32];
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[16];
            GUID guid = new GUID();
            if (str.length() > 38) {
                jt6.a("Invalid guid length: ", str.length());
                return null;
            }
            int i = 0;
            for (char c : charArray) {
                if (c != '{' && c != '-' && c != '}') {
                    cArr[i] = c;
                    i++;
                }
            }
            for (int i2 = 0; i2 < 32; i2 += 2) {
                bArr[i2 / 2] = (byte) (((Character.digit(cArr[i2], 16) << 4) + Character.digit(cArr[i2 + 1], 16)) & 255);
            }
            guid.Data1 = (int) ((((((((long) (bArr[0] & 255)) << 8) | ((long) (bArr[1] & 255))) << 8) | ((long) (bArr[2] & 255))) << 8) | ((long) (bArr[3] & 255)));
            guid.Data2 = (short) (((bArr[4] & 255) << 8) | (bArr[5] & 255));
            guid.Data3 = (short) (((bArr[6] & 255) << 8) | (bArr[7] & 255));
            byte[] bArr2 = guid.Data4;
            bArr2[0] = bArr[8];
            bArr2[1] = bArr[9];
            bArr2[2] = bArr[10];
            bArr2[3] = bArr[11];
            bArr2[4] = bArr[12];
            bArr2[5] = bArr[13];
            bArr2[6] = bArr[14];
            bArr2[7] = bArr[15];
            guid.writeFieldsToMemory();
            return guid;
        }

        public static GUID newGuid() {
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            byte b = (byte) (bArr[6] & 15);
            bArr[6] = b;
            bArr[6] = (byte) (b | ElementValue.ANNOTATION);
            byte b2 = (byte) (bArr[8] & 63);
            bArr[8] = b2;
            bArr[8] = (byte) (b2 | 128);
            return new GUID(bArr);
        }

        @Override // com.sun.jna.Structure
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            GUID guid = (GUID) obj;
            return this.Data1 == guid.Data1 && this.Data2 == guid.Data2 && this.Data3 == guid.Data3 && Arrays.equals(this.Data4, guid.Data4);
        }

        @Override // com.sun.jna.Structure
        public int hashCode() {
            return (Arrays.hashCode(this.Data4) + 65535) & (this.Data1 + this.Data2) & (this.Data3 + 65535);
        }

        public byte[] toByteArray() {
            byte[] bArr = new byte[16];
            int i = this.Data1;
            byte[] bArr2 = {(byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) i};
            short s = this.Data2;
            byte[] bArr3 = {(byte) (s >> 24), (byte) (s >> 16), (byte) (s >> 8), (byte) s};
            short s2 = this.Data3;
            System.arraycopy(bArr2, 0, bArr, 0, 4);
            System.arraycopy(bArr3, 2, bArr, 4, 2);
            System.arraycopy(new byte[]{(byte) (s2 >> 24), (byte) (s2 >> 16), (byte) (s2 >> 8), (byte) s2}, 2, bArr, 6, 2);
            System.arraycopy(this.Data4, 0, bArr, 8, 8);
            return bArr;
        }

        public String toGuidString() {
            byte[] byteArray = toByteArray();
            StringBuilder sb = new StringBuilder(byteArray.length * 2);
            sb.append("{");
            for (int i = 0; i < byteArray.length; i++) {
                char cCharAt = "0123456789ABCDEF".charAt((byteArray[i] & 240) >> 4);
                char cCharAt2 = "0123456789ABCDEF".charAt(byteArray[i] & 15);
                sb.append(cCharAt);
                sb.append(cCharAt2);
                if (i == 3 || i == 5 || i == 7 || i == 9) {
                    sb.append("-");
                }
            }
            sb.append("}");
            return sb.toString();
        }

        public void writeFieldsToMemory() {
            Iterator<String> it = getFieldOrder().iterator();
            while (it.hasNext()) {
                writeField(it.next());
            }
        }

        public static class ByReference extends GUID implements Structure.ByReference {
            public ByReference(GUID guid) {
                super(guid.getPointer());
                this.Data1 = guid.Data1;
                this.Data2 = guid.Data2;
                this.Data3 = guid.Data3;
                this.Data4 = guid.Data4;
            }

            public ByReference() {
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }
        }

        public static class ByValue extends GUID implements Structure.ByValue {
            public ByValue(GUID guid) {
                super(guid.getPointer());
                this.Data1 = guid.Data1;
                this.Data2 = guid.Data2;
                this.Data3 = guid.Data3;
                this.Data4 = guid.Data4;
            }

            public ByValue() {
            }

            public ByValue(Pointer pointer) {
                super(pointer);
            }
        }

        public GUID() {
            this.Data4 = new byte[8];
        }

        public GUID(String str) {
            this(fromString(str));
        }

        public GUID(byte[] bArr) {
            this(fromBinary(bArr));
        }

        public GUID(Pointer pointer) {
            super(pointer);
            this.Data4 = new byte[8];
            read();
        }
    }
}

package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;
import com.sun.jna.ptr.ByReference;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface WTypes {
    public static final int CLSCTX_ACTIVATE_32_BIT_SERVER = 262144;
    public static final int CLSCTX_ACTIVATE_64_BIT_SERVER = 524288;
    public static final int CLSCTX_ACTIVATE_AAA_AS_IU = 8388608;
    public static final int CLSCTX_ALL = 7;
    public static final int CLSCTX_APPCONTAINER = 4194304;
    public static final int CLSCTX_DISABLE_AAA = 32768;
    public static final int CLSCTX_ENABLE_AAA = 65536;
    public static final int CLSCTX_ENABLE_CLOAKING = 1048576;
    public static final int CLSCTX_ENABLE_CODE_DOWNLOAD = 8192;
    public static final int CLSCTX_FROM_DEFAULT_CONTEXT = 131072;
    public static final int CLSCTX_INPROC_HANDLER = 2;
    public static final int CLSCTX_INPROC_HANDLER16 = 32;
    public static final int CLSCTX_INPROC_SERVER = 1;
    public static final int CLSCTX_INPROC_SERVER16 = 8;
    public static final int CLSCTX_LOCAL_SERVER = 4;
    public static final int CLSCTX_NO_CODE_DOWNLOAD = 1024;
    public static final int CLSCTX_NO_CUSTOM_MARSHAL = 4096;
    public static final int CLSCTX_NO_FAILURE_LOG = 16384;
    public static final int CLSCTX_PS_DLL = Integer.MIN_VALUE;
    public static final int CLSCTX_REMOTE_SERVER = 16;
    public static final int CLSCTX_RESERVED1 = 64;
    public static final int CLSCTX_RESERVED2 = 128;
    public static final int CLSCTX_RESERVED3 = 256;
    public static final int CLSCTX_RESERVED4 = 512;
    public static final int CLSCTX_RESERVED5 = 2048;
    public static final int CLSCTX_SERVER = 21;

    public static class VARTYPE extends WinDef.USHORT {
        private static final long serialVersionUID = 1;

        public VARTYPE() {
            this(0);
        }

        public VARTYPE(int i) {
            super(i);
        }
    }

    public static class BSTR extends PointerType {
        @Deprecated
        public BSTR(String str) {
            setValue(str);
        }

        public String getValue() {
            try {
                Pointer pointer = getPointer();
                return pointer == null ? "" : new String(pointer.getByteArray(0L, pointer.getInt(-4L)), XMLEntityManager.EncodingInfo.STR_UTF16LE);
            } catch (UnsupportedEncodingException e) {
                g3c.a("UTF-16LE charset is not supported", e);
                return null;
            }
        }

        @Deprecated
        public void setValue(String str) {
            if (str == null) {
                str = "";
            }
            try {
                byte[] bytes = str.getBytes(XMLEntityManager.EncodingInfo.STR_UTF16LE);
                Memory memory = new Memory(bytes.length + 6);
                memory.clear();
                memory.setInt(0L, bytes.length);
                memory.write(4L, bytes, 0, bytes.length);
                setPointer(memory.share(4L));
            } catch (UnsupportedEncodingException e) {
                g3c.a("UTF-16LE charset is not supported", e);
            }
        }

        @Override // com.sun.jna.PointerType
        public String toString() {
            return getValue();
        }

        public BSTR(Pointer pointer) {
            super(pointer);
        }

        public BSTR() {
            super(Pointer.NULL);
        }
    }

    public static class BSTRByReference extends ByReference {
        public BSTRByReference(BSTR bstr) {
            this();
            setValue(bstr);
        }

        public String getString() {
            BSTR value = getValue();
            if (value == null) {
                return null;
            }
            return value.getValue();
        }

        public BSTR getValue() {
            return new BSTR(getPointer().getPointer(0L));
        }

        public void setValue(BSTR bstr) {
            getPointer().setPointer(0L, bstr.getPointer());
        }

        public BSTRByReference() {
            super(Native.POINTER_SIZE);
        }
    }

    public static class VARTYPEByReference extends ByReference {
        public VARTYPEByReference(short s) {
            super(2);
            getPointer().setShort(0L, s);
        }

        public VARTYPE getValue() {
            return new VARTYPE(getPointer().getShort(0L));
        }

        public void setValue(VARTYPE vartype) {
            getPointer().setShort(0L, vartype.shortValue());
        }

        public VARTYPEByReference(VARTYPE vartype) {
            super(2);
            setValue(vartype);
        }

        public VARTYPEByReference() {
            super(2);
        }
    }

    public static class LPSTR extends PointerType {

        public static class ByReference extends LPSTR implements Structure.ByReference {
        }

        public LPSTR(String str) {
            this(new Memory(((long) str.length()) + 1));
            setValue(str);
        }

        public String getValue() {
            Pointer pointer = getPointer();
            if (pointer != null) {
                return pointer.getString(0L);
            }
            return null;
        }

        public void setValue(String str) {
            getPointer().setString(0L, str);
        }

        @Override // com.sun.jna.PointerType
        public String toString() {
            return getValue();
        }

        public LPSTR(Pointer pointer) {
            super(pointer);
        }

        public LPSTR() {
            super(Pointer.NULL);
        }
    }

    public static class LPOLESTR extends PointerType {

        public static class ByReference extends LPOLESTR implements Structure.ByReference {
        }

        public LPOLESTR(String str) {
            super(new Memory((((long) str.length()) + 1) * ((long) Native.WCHAR_SIZE)));
            setValue(str);
        }

        public String getValue() {
            Pointer pointer = getPointer();
            if (pointer != null) {
                return pointer.getWideString(0L);
            }
            return null;
        }

        public void setValue(String str) {
            getPointer().setWideString(0L, str);
        }

        @Override // com.sun.jna.PointerType
        public String toString() {
            return getValue();
        }

        public LPOLESTR(Pointer pointer) {
            super(pointer);
        }

        public LPOLESTR() {
            super(Pointer.NULL);
        }
    }

    public static class LPWSTR extends PointerType {

        public static class ByReference extends LPWSTR implements Structure.ByReference {
        }

        public LPWSTR(String str) {
            this(new Memory((((long) str.length()) + 1) * ((long) Native.WCHAR_SIZE)));
            setValue(str);
        }

        public String getValue() {
            Pointer pointer = getPointer();
            if (pointer != null) {
                return pointer.getWideString(0L);
            }
            return null;
        }

        public void setValue(String str) {
            getPointer().setWideString(0L, str);
        }

        @Override // com.sun.jna.PointerType
        public String toString() {
            return getValue();
        }

        public LPWSTR(Pointer pointer) {
            super(pointer);
        }

        public LPWSTR() {
            super(Pointer.NULL);
        }
    }
}

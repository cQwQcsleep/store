package com.sun.jna.platform.win32;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.platform.win32.COM.Dispatch;
import com.sun.jna.platform.win32.COM.IDispatch;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import java.util.Date;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Variant {
    public static final int VT_ARRAY = 8192;
    public static final int VT_BLOB = 65;
    public static final int VT_BLOB_OBJECT = 70;
    public static final int VT_BOOL = 11;
    public static final int VT_BSTR = 8;
    public static final int VT_BSTR_BLOB = 4095;
    public static final int VT_BYREF = 16384;
    public static final int VT_CARRAY = 28;
    public static final int VT_CF = 71;
    public static final int VT_CLSID = 72;
    public static final int VT_CY = 6;
    public static final int VT_DATE = 7;
    public static final int VT_DECIMAL = 14;
    public static final int VT_DISPATCH = 9;
    public static final int VT_EMPTY = 0;
    public static final int VT_ERROR = 10;
    public static final int VT_FILETIME = 64;
    public static final int VT_HRESULT = 25;
    public static final int VT_I1 = 16;
    public static final int VT_I2 = 2;
    public static final int VT_I4 = 3;
    public static final int VT_I8 = 20;
    public static final int VT_ILLEGAL = 65535;
    public static final int VT_ILLEGALMASKED = 4095;
    public static final int VT_INT = 22;
    public static final int VT_INT_PTR = 37;
    public static final int VT_LPSTR = 30;
    public static final int VT_LPWSTR = 31;
    public static final int VT_NULL = 1;
    public static final int VT_PTR = 26;
    public static final int VT_R4 = 4;
    public static final int VT_R8 = 5;
    public static final int VT_RECORD = 36;
    public static final int VT_RESERVED = 32768;
    public static final int VT_SAFEARRAY = 27;
    public static final int VT_STORAGE = 67;
    public static final int VT_STORED_OBJECT = 69;
    public static final int VT_STREAM = 66;
    public static final int VT_STREAMED_OBJECT = 68;
    public static final int VT_TYPEMASK = 4095;
    public static final int VT_UI1 = 17;
    public static final int VT_UI2 = 18;
    public static final int VT_UI4 = 19;
    public static final int VT_UI8 = 21;
    public static final int VT_UINT = 23;
    public static final int VT_UINT_PTR = 38;
    public static final int VT_UNKNOWN = 13;
    public static final int VT_USERDEFINED = 29;
    public static final int VT_VARIANT = 12;
    public static final int VT_VECTOR = 4096;
    public static final int VT_VERSIONED_STREAM = 73;
    public static final int VT_VOID = 24;
    public static final OaIdl.VARIANT_BOOL VARIANT_TRUE = new OaIdl.VARIANT_BOOL(65535);
    public static final OaIdl.VARIANT_BOOL VARIANT_FALSE = new OaIdl.VARIANT_BOOL(0);

    @Structure.FieldOrder({"variantArg"})
    public static class VariantArg extends Structure {
        public VARIANT[] variantArg;

        public VariantArg() {
            this.variantArg = new VARIANT[1];
        }

        public void setArraySize(int i) {
            this.variantArg = new VARIANT[i];
            read();
        }

        public static class ByReference extends VariantArg implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(VARIANT[] variantArr) {
                super(variantArr);
            }
        }

        public VariantArg(Pointer pointer) {
            super(pointer);
            this.variantArg = new VARIANT[1];
        }

        public VariantArg(VARIANT[] variantArr) {
            this.variantArg = variantArr;
        }
    }

    public static class VARIANT extends Union {
        public static final VARIANT VARIANT_MISSING;
        public _VARIANT _variant;
        public OaIdl.DECIMAL decVal;

        @Structure.FieldOrder({"vt", "wReserved1", "wReserved2", "wReserved3", "__variant"})
        public static class _VARIANT extends Structure {
            public __VARIANT __variant;
            public WTypes.VARTYPE vt;
            public short wReserved1;
            public short wReserved2;
            public short wReserved3;

            public _VARIANT(Pointer pointer) {
                super(pointer);
                read();
            }

            public static class __VARIANT extends Union {
                public WinDef.BYTE bVal;
                public OaIdl.VARIANT_BOOL boolVal;
                public WTypes.BSTR bstrVal;
                public WinDef.PVOID byref;
                public WinDef.CHAR cVal;
                public OaIdl.CURRENCY cyVal;
                public OaIdl.DATE date;
                public Double dblVal;
                public Float fltVal;
                public WinDef.SHORT iVal;
                public Integer intVal;
                public WinDef.LONG lVal;
                public WinDef.LONGLONG llVal;
                public OaIdl.SAFEARRAY.ByReference parray;
                public ByteByReference pbVal;
                public OaIdl._VARIANT_BOOLByReference pbool;
                public OaIdl.VARIANT_BOOLByReference pboolVal;
                public WTypes.BSTRByReference pbstrVal;
                public WinDef.CHARByReference pcVal;
                public OaIdl.CURRENCY.ByReference pcyVal;
                public OaIdl.DATE.ByReference pdate;
                public DoubleByReference pdblVal;
                public OaIdl.DECIMAL.ByReference pdecVal;
                public Dispatch pdispVal;
                public FloatByReference pfltVal;
                public ShortByReference piVal;
                public IntByReference pintVal;
                public WinDef.LONGByReference plVal;
                public WinDef.LONGLONGByReference pllVal;
                public OaIdl.SAFEARRAYByReference pparray;
                public Dispatch.ByReference ppdispVal;
                public Unknown.ByReference ppunkVal;
                public WinDef.SCODEByReference pscode;
                public WinDef.USHORTByReference puiVal;
                public WinDef.UINTByReference puintVal;
                public WinDef.ULONGByReference pulVal;
                public WinDef.ULONGLONGByReference pullVal;
                public Unknown punkVal;
                public BRECORD pvRecord;
                public ByReference pvarVal;
                public WinDef.SCODE scode;
                public WinDef.USHORT uiVal;
                public WinDef.UINT uintVal;
                public WinDef.ULONG ulVal;
                public WinDef.ULONGLONG ullVal;

                public __VARIANT() {
                    read();
                }

                @Structure.FieldOrder({"pvRecord", "pRecInfo"})
                public static class BRECORD extends Structure {
                    public Pointer pRecInfo;
                    public WinDef.PVOID pvRecord;

                    public static class ByReference extends BRECORD implements Structure.ByReference {
                    }

                    public BRECORD() {
                    }

                    public BRECORD(Pointer pointer) {
                        super(pointer);
                    }
                }

                public __VARIANT(Pointer pointer) {
                    super(pointer);
                    read();
                }
            }

            public _VARIANT() {
            }
        }

        static {
            VARIANT variant = new VARIANT();
            VARIANT_MISSING = variant;
            variant.setValue(10, new WinDef.SCODE(-2147352572L));
        }

        public VARIANT(char c) {
            this();
            setValue(18, new WinDef.USHORT(c));
        }

        public boolean booleanValue() {
            return ((OaIdl.VARIANT_BOOL) getValue()).booleanValue();
        }

        public byte byteValue() {
            return ((Number) getValue()).byteValue();
        }

        public Date dateValue() {
            OaIdl.DATE date = (OaIdl.DATE) getValue();
            if (date == null) {
                return null;
            }
            return date.getAsJavaDate();
        }

        public double doubleValue() {
            return ((Number) getValue()).doubleValue();
        }

        public float floatValue() {
            return ((Number) getValue()).floatValue();
        }

        public Object getValue() {
            int iIntValue = getVarType().intValue();
            if (iIntValue == 13) {
                return this._variant.__variant.readField("punkVal");
            }
            if (iIntValue == 36) {
                return this._variant.__variant.readField("pvRecord");
            }
            if (iIntValue == 16384) {
                return this._variant.__variant.readField("byref");
            }
            switch (iIntValue) {
                case 2:
                    return this._variant.__variant.readField("iVal");
                case 3:
                    return this._variant.__variant.readField("lVal");
                case 4:
                    return this._variant.__variant.readField("fltVal");
                case 5:
                    return this._variant.__variant.readField("dblVal");
                case 6:
                    return this._variant.__variant.readField("cyVal");
                case 7:
                    return this._variant.__variant.readField(SchemaSymbols.ATTVAL_DATE);
                case 8:
                    return this._variant.__variant.readField("bstrVal");
                case 9:
                    return this._variant.__variant.readField("pdispVal");
                case 10:
                    return this._variant.__variant.readField("scode");
                case 11:
                    return this._variant.__variant.readField("boolVal");
                default:
                    switch (iIntValue) {
                        case 16:
                            return this._variant.__variant.readField("cVal");
                        case 17:
                            return this._variant.__variant.readField("bVal");
                        case 18:
                            return this._variant.__variant.readField("uiVal");
                        case 19:
                            return this._variant.__variant.readField("ulVal");
                        case 20:
                            return this._variant.__variant.readField("llVal");
                        case 21:
                            return this._variant.__variant.readField("ullVal");
                        case 22:
                            return this._variant.__variant.readField("intVal");
                        case 23:
                            return this._variant.__variant.readField("uintVal");
                        default:
                            switch (iIntValue) {
                                case Ddeml.DMLERR_DATAACKTIMEOUT /* 16386 */:
                                    return this._variant.__variant.readField("piVal");
                                case Ddeml.DMLERR_DLL_NOT_INITIALIZED /* 16387 */:
                                    return this._variant.__variant.readField("plVal");
                                case Ddeml.DMLERR_DLL_USAGE /* 16388 */:
                                    return this._variant.__variant.readField("pfltVal");
                                case Ddeml.DMLERR_EXECACKTIMEOUT /* 16389 */:
                                    return this._variant.__variant.readField("pdblVal");
                                case Ddeml.DMLERR_INVALIDPARAMETER /* 16390 */:
                                    return this._variant.__variant.readField("pcyVal");
                                case Ddeml.DMLERR_LOW_MEMORY /* 16391 */:
                                    return this._variant.__variant.readField("pdate");
                                case Ddeml.DMLERR_MEMORY_ERROR /* 16392 */:
                                    return this._variant.__variant.readField("pbstrVal");
                                case Ddeml.DMLERR_NOTPROCESSED /* 16393 */:
                                    return this._variant.__variant.readField("ppdispVal");
                                case Ddeml.DMLERR_NO_CONV_ESTABLISHED /* 16394 */:
                                    return this._variant.__variant.readField("pscode");
                                case Ddeml.DMLERR_POKEACKTIMEOUT /* 16395 */:
                                    return this._variant.__variant.readField("pboolVal");
                                case Ddeml.DMLERR_POSTMSG_FAILED /* 16396 */:
                                    return this._variant.__variant.readField("pvarVal");
                                case Ddeml.DMLERR_REENTRANCY /* 16397 */:
                                    return this._variant.__variant.readField("ppunkVal");
                                case Ddeml.DMLERR_SERVER_DIED /* 16398 */:
                                    return this._variant.__variant.readField("pdecVal");
                                default:
                                    switch (iIntValue) {
                                        case 16400:
                                            return this._variant.__variant.readField("pcVal");
                                        case 16401:
                                            return this._variant.__variant.readField("pbVal");
                                        case 16402:
                                            return this._variant.__variant.readField("puiVal");
                                        case 16403:
                                            return this._variant.__variant.readField("pulVal");
                                        case 16404:
                                            return this._variant.__variant.readField("pllVal");
                                        case 16405:
                                            return this._variant.__variant.readField("pullVal");
                                        case 16406:
                                            return this._variant.__variant.readField("pintVal");
                                        case 16407:
                                            return this._variant.__variant.readField("puintVal");
                                        default:
                                            if ((iIntValue & 8192) <= 0) {
                                                return null;
                                            }
                                            int i = iIntValue & 16384;
                                            _VARIANT _variant = this._variant;
                                            return i > 0 ? _variant.__variant.readField("pparray") : _variant.__variant.readField("parray");
                                    }
                            }
                    }
            }
        }

        public WTypes.VARTYPE getVarType() {
            read();
            return this._variant.vt;
        }

        public int intValue() {
            return ((Number) getValue()).intValue();
        }

        public long longValue() {
            return ((Number) getValue()).longValue();
        }

        public void setValue(WTypes.VARTYPE vartype, Object obj) {
            int iIntValue = vartype.intValue();
            if (iIntValue == 13) {
                this._variant.__variant.writeField("punkVal", obj);
            } else if (iIntValue == 36) {
                this._variant.__variant.writeField("pvRecord", obj);
            } else if (iIntValue != 16384) {
                switch (iIntValue) {
                    case 2:
                        this._variant.__variant.writeField("iVal", obj);
                        break;
                    case 3:
                        this._variant.__variant.writeField("lVal", obj);
                        break;
                    case 4:
                        this._variant.__variant.writeField("fltVal", obj);
                        break;
                    case 5:
                        this._variant.__variant.writeField("dblVal", obj);
                        break;
                    case 6:
                        this._variant.__variant.writeField("cyVal", obj);
                        break;
                    case 7:
                        this._variant.__variant.writeField(SchemaSymbols.ATTVAL_DATE, obj);
                        break;
                    case 8:
                        this._variant.__variant.writeField("bstrVal", obj);
                        break;
                    case 9:
                        this._variant.__variant.writeField("pdispVal", obj);
                        break;
                    case 10:
                        this._variant.__variant.writeField("scode", obj);
                        break;
                    case 11:
                        this._variant.__variant.writeField("boolVal", obj);
                        break;
                    default:
                        switch (iIntValue) {
                            case 16:
                                this._variant.__variant.writeField("cVal", obj);
                                break;
                            case 17:
                                this._variant.__variant.writeField("bVal", obj);
                                break;
                            case 18:
                                this._variant.__variant.writeField("uiVal", obj);
                                break;
                            case 19:
                                this._variant.__variant.writeField("ulVal", obj);
                                break;
                            case 20:
                                this._variant.__variant.writeField("llVal", obj);
                                break;
                            case 21:
                                this._variant.__variant.writeField("ullVal", obj);
                                break;
                            case 22:
                                this._variant.__variant.writeField("intVal", obj);
                                break;
                            case 23:
                                this._variant.__variant.writeField("uintVal", obj);
                                break;
                            default:
                                switch (iIntValue) {
                                    case Ddeml.DMLERR_DATAACKTIMEOUT /* 16386 */:
                                        this._variant.__variant.writeField("piVal", obj);
                                        break;
                                    case Ddeml.DMLERR_DLL_NOT_INITIALIZED /* 16387 */:
                                        this._variant.__variant.writeField("plVal", obj);
                                        break;
                                    case Ddeml.DMLERR_DLL_USAGE /* 16388 */:
                                        this._variant.__variant.writeField("pfltVal", obj);
                                        break;
                                    case Ddeml.DMLERR_EXECACKTIMEOUT /* 16389 */:
                                        this._variant.__variant.writeField("pdblVal", obj);
                                        break;
                                    case Ddeml.DMLERR_INVALIDPARAMETER /* 16390 */:
                                        this._variant.__variant.writeField("pcyVal", obj);
                                        break;
                                    case Ddeml.DMLERR_LOW_MEMORY /* 16391 */:
                                        this._variant.__variant.writeField("pdate", obj);
                                        break;
                                    case Ddeml.DMLERR_MEMORY_ERROR /* 16392 */:
                                        this._variant.__variant.writeField("pbstrVal", obj);
                                        break;
                                    case Ddeml.DMLERR_NOTPROCESSED /* 16393 */:
                                        this._variant.__variant.writeField("ppdispVal", obj);
                                        break;
                                    case Ddeml.DMLERR_NO_CONV_ESTABLISHED /* 16394 */:
                                        this._variant.__variant.writeField("pscode", obj);
                                        break;
                                    case Ddeml.DMLERR_POKEACKTIMEOUT /* 16395 */:
                                        this._variant.__variant.writeField("pboolVal", obj);
                                        break;
                                    case Ddeml.DMLERR_POSTMSG_FAILED /* 16396 */:
                                        this._variant.__variant.writeField("pvarVal", obj);
                                        break;
                                    case Ddeml.DMLERR_REENTRANCY /* 16397 */:
                                        this._variant.__variant.writeField("ppunkVal", obj);
                                        break;
                                    case Ddeml.DMLERR_SERVER_DIED /* 16398 */:
                                        this._variant.__variant.writeField("pdecVal", obj);
                                        break;
                                    default:
                                        switch (iIntValue) {
                                            case 16400:
                                                this._variant.__variant.writeField("pcVal", obj);
                                                break;
                                            case 16401:
                                                this._variant.__variant.writeField("pbVal", obj);
                                                break;
                                            case 16402:
                                                this._variant.__variant.writeField("puiVal", obj);
                                                break;
                                            case 16403:
                                                this._variant.__variant.writeField("pulVal", obj);
                                                break;
                                            case 16404:
                                                this._variant.__variant.writeField("pllVal", obj);
                                                break;
                                            case 16405:
                                                this._variant.__variant.writeField("pullVal", obj);
                                                break;
                                            case 16406:
                                                this._variant.__variant.writeField("pintVal", obj);
                                                break;
                                            case 16407:
                                                this._variant.__variant.writeField("puintVal", obj);
                                                break;
                                            default:
                                                if ((iIntValue & 8192) > 0) {
                                                    int i = iIntValue & 16384;
                                                    _VARIANT _variant = this._variant;
                                                    if (i <= 0) {
                                                        _variant.__variant.writeField("parray", obj);
                                                    } else {
                                                        _variant.__variant.writeField("pparray", obj);
                                                    }
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
            } else {
                this._variant.__variant.writeField("byref", obj);
            }
            this._variant.writeField("vt", vartype);
            write();
        }

        public void setVarType(short s) {
            this._variant.vt = new WTypes.VARTYPE(s);
        }

        public short shortValue() {
            return ((Number) getValue()).shortValue();
        }

        public String stringValue() {
            WTypes.BSTR bstr = (WTypes.BSTR) getValue();
            if (bstr == null) {
                return null;
            }
            return bstr.getValue();
        }

        public static class ByReference extends VARIANT implements Structure.ByReference {
            public ByReference(VARIANT variant) {
                setValue(variant.getVarType(), variant.getValue());
            }

            public ByReference(Pointer pointer) {
                super(pointer);
            }

            public ByReference() {
            }
        }

        public static class ByValue extends VARIANT implements Structure.ByValue {
            public ByValue(VARIANT variant) {
                setValue(variant.getVarType(), variant.getValue());
            }

            public ByValue(Pointer pointer) {
                super(pointer);
            }

            public ByValue() {
            }
        }

        public VARIANT(Pointer pointer) {
            super(pointer);
            setType("_variant");
            read();
        }

        public VARIANT(WTypes.BSTR bstr) {
            this();
            setValue(8, bstr);
        }

        public VARIANT(WTypes.BSTRByReference bSTRByReference) {
            this();
            setValue(Ddeml.DMLERR_MEMORY_ERROR, bSTRByReference);
        }

        public VARIANT(OaIdl.VARIANT_BOOL variant_bool) {
            this();
            setValue(11, variant_bool);
        }

        public VARIANT(WinDef.BOOL bool) {
            this(bool.booleanValue());
        }

        public VARIANT(WinDef.LONG r2) {
            this();
            setValue(3, r2);
        }

        public VARIANT(WinDef.SHORT r2) {
            this();
            setValue(2, r2);
        }

        public VARIANT(OaIdl.DATE date) {
            this();
            setValue(7, date);
        }

        public VARIANT(byte b) {
            this(new WinDef.BYTE(b));
        }

        public VARIANT(WinDef.BYTE r2) {
            this();
            setValue(17, r2);
        }

        public VARIANT() {
            setType("_variant");
            read();
        }

        public VARIANT(WinDef.CHAR r2) {
            this();
            setValue(16, r2);
        }

        public VARIANT(short s) {
            this();
            setValue(2, new WinDef.SHORT(s));
        }

        public VARIANT(int i) {
            this();
            setValue(3, new WinDef.LONG(i));
        }

        public VARIANT(IntByReference intByReference) {
            this();
            setValue(16406, intByReference);
        }

        public VARIANT(long j) {
            this();
            setValue(20, new WinDef.LONGLONG(j));
        }

        public VARIANT(float f) {
            this();
            setValue(4, Float.valueOf(f));
        }

        public VARIANT(double d) {
            this();
            setValue(5, Double.valueOf(d));
        }

        public VARIANT(String str) {
            this();
            setValue(8, OleAuto.INSTANCE.SysAllocString(str));
        }

        public VARIANT(boolean z) {
            this();
            setValue(11, new OaIdl.VARIANT_BOOL(z));
        }

        @Deprecated
        public VARIANT(IDispatch iDispatch) {
            this();
            setValue(9, iDispatch);
        }

        public VARIANT(Dispatch dispatch) {
            this();
            setValue(9, dispatch);
        }

        public VARIANT(Date date) {
            this();
            setValue(7, new OaIdl.DATE(date));
        }

        public VARIANT(OaIdl.SAFEARRAY safearray) {
            this();
            setValue(safearray);
        }

        public VARIANT(OaIdl.SAFEARRAYByReference sAFEARRAYByReference) {
            this();
            setValue(sAFEARRAYByReference);
        }

        public void setValue(OaIdl.SAFEARRAY safearray) {
            setValue(safearray.getVarType().intValue() | 8192, safearray);
        }

        public void setValue(OaIdl.SAFEARRAYByReference sAFEARRAYByReference) {
            setValue(sAFEARRAYByReference.pSAFEARRAY.getVarType().intValue() | 24576, sAFEARRAYByReference);
        }

        public void setValue(int i, Object obj) {
            setValue(new WTypes.VARTYPE(i), obj);
        }
    }
}

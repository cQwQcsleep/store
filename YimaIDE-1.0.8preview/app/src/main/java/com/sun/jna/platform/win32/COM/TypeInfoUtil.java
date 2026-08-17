package com.sun.jna.platform.win32.COM;

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeInfoUtil {
    public static final OleAuto OLEAUTO = OleAuto.INSTANCE;
    private ITypeInfo typeInfo;

    public static class ContainingTypeLib {
        private int index;
        private ITypeLib typeLib;

        public ContainingTypeLib(ITypeLib iTypeLib, int i) {
            this.typeLib = iTypeLib;
            this.index = i;
        }

        public int getIndex() {
            return this.index;
        }

        public ITypeLib getTypeLib() {
            return this.typeLib;
        }

        public void setIndex(int i) {
            this.index = i;
        }

        public void setTypeLib(ITypeLib iTypeLib) {
            this.typeLib = iTypeLib;
        }
    }

    public static class DllEntry {
        private String dllName;
        private String name;
        private int ordinal;

        public DllEntry(String str, String str2, int i) {
            this.dllName = str;
            this.name = str2;
            this.ordinal = i;
        }

        public String getDllName() {
            return this.dllName;
        }

        public String getName() {
            return this.name;
        }

        public int getOrdinal() {
            return this.ordinal;
        }

        public void setDllName(String str) {
            this.dllName = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setOrdinal(int i) {
            this.ordinal = i;
        }
    }

    public static class Invoke {
        private OaIdl.EXCEPINFO.ByReference pExcepInfo;
        private Variant.VARIANT.ByReference pVarResult;
        private int puArgErr;

        public Invoke(Variant.VARIANT.ByReference byReference, OaIdl.EXCEPINFO.ByReference byReference2, int i) {
            this.pVarResult = byReference;
            this.pExcepInfo = byReference2;
            this.puArgErr = i;
        }

        public int getPuArgErr() {
            return this.puArgErr;
        }

        public OaIdl.EXCEPINFO.ByReference getpExcepInfo() {
            return this.pExcepInfo;
        }

        public Variant.VARIANT.ByReference getpVarResult() {
            return this.pVarResult;
        }
    }

    public static class TypeInfoDoc {
        private String docString;
        private int helpContext;
        private String helpFile;
        private String name;

        public TypeInfoDoc(String str, String str2, int i, String str3) {
            this.name = str;
            this.docString = str2;
            this.helpContext = i;
            this.helpFile = str3;
        }

        public String getDocString() {
            return this.docString;
        }

        public int getHelpContext() {
            return this.helpContext;
        }

        public String getHelpFile() {
            return this.helpFile;
        }

        public String getName() {
            return this.name;
        }
    }

    public TypeInfoUtil(ITypeInfo iTypeInfo) {
        this.typeInfo = iTypeInfo;
    }

    public PointerByReference AddressOfMember(OaIdl.MEMBERID memberid, OaIdl.INVOKEKIND invokekind) {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(this.typeInfo.AddressOfMember(memberid, invokekind, pointerByReference));
        return pointerByReference;
    }

    public PointerByReference CreateInstance(IUnknown iUnknown, Guid.REFIID refiid) {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(this.typeInfo.CreateInstance(iUnknown, refiid, pointerByReference));
        return pointerByReference;
    }

    public ContainingTypeLib GetContainingTypeLib() {
        PointerByReference pointerByReference = new PointerByReference();
        WinDef.UINTByReference uINTByReference = new WinDef.UINTByReference();
        COMUtils.checkRC(this.typeInfo.GetContainingTypeLib(pointerByReference, uINTByReference));
        return new ContainingTypeLib(new TypeLib(pointerByReference.getValue()), uINTByReference.getValue().intValue());
    }

    public DllEntry GetDllEntry(OaIdl.MEMBERID memberid, OaIdl.INVOKEKIND invokekind) {
        WTypes.BSTRByReference bSTRByReference = new WTypes.BSTRByReference();
        WTypes.BSTRByReference bSTRByReference2 = new WTypes.BSTRByReference();
        WinDef.WORDByReference wORDByReference = new WinDef.WORDByReference();
        COMUtils.checkRC(this.typeInfo.GetDllEntry(memberid, invokekind, bSTRByReference, bSTRByReference2, wORDByReference));
        OleAuto oleAuto = OLEAUTO;
        oleAuto.SysFreeString(bSTRByReference.getValue());
        oleAuto.SysFreeString(bSTRByReference2.getValue());
        return new DllEntry(bSTRByReference.getString(), bSTRByReference2.getString(), wORDByReference.getValue().intValue());
    }

    public String GetMops(OaIdl.MEMBERID memberid) {
        WTypes.BSTRByReference bSTRByReference = new WTypes.BSTRByReference();
        COMUtils.checkRC(this.typeInfo.GetMops(memberid, bSTRByReference));
        return bSTRByReference.getString();
    }

    public Invoke Invoke(WinDef.PVOID pvoid, OaIdl.MEMBERID memberid, WinDef.WORD word, OleAuto.DISPPARAMS.ByReference byReference) {
        Variant.VARIANT.ByReference byReference2 = new Variant.VARIANT.ByReference();
        OaIdl.EXCEPINFO.ByReference byReference3 = new OaIdl.EXCEPINFO.ByReference();
        WinDef.UINTByReference uINTByReference = new WinDef.UINTByReference();
        COMUtils.checkRC(this.typeInfo.Invoke(pvoid, memberid, word, byReference, byReference2, byReference3, uINTByReference));
        return new Invoke(byReference2, byReference3, uINTByReference.getValue().intValue());
    }

    public void ReleaseFuncDesc(OaIdl.FUNCDESC funcdesc) {
        this.typeInfo.ReleaseFuncDesc(funcdesc);
    }

    public void ReleaseTypeAttr(OaIdl.TYPEATTR typeattr) {
        this.typeInfo.ReleaseTypeAttr(typeattr);
    }

    public void ReleaseVarDesc(OaIdl.VARDESC vardesc) {
        this.typeInfo.ReleaseVarDesc(vardesc);
    }

    public TypeInfoDoc getDocumentation(OaIdl.MEMBERID memberid) {
        WTypes.BSTRByReference bSTRByReference = new WTypes.BSTRByReference();
        WTypes.BSTRByReference bSTRByReference2 = new WTypes.BSTRByReference();
        WinDef.DWORDByReference dWORDByReference = new WinDef.DWORDByReference();
        WTypes.BSTRByReference bSTRByReference3 = new WTypes.BSTRByReference();
        COMUtils.checkRC(this.typeInfo.GetDocumentation(memberid, bSTRByReference, bSTRByReference2, dWORDByReference, bSTRByReference3));
        TypeInfoDoc typeInfoDoc = new TypeInfoDoc(bSTRByReference.getString(), bSTRByReference2.getString(), dWORDByReference.getValue().intValue(), bSTRByReference3.getString());
        OleAuto oleAuto = OLEAUTO;
        oleAuto.SysFreeString(bSTRByReference.getValue());
        oleAuto.SysFreeString(bSTRByReference2.getValue());
        oleAuto.SysFreeString(bSTRByReference3.getValue());
        return typeInfoDoc;
    }

    public OaIdl.FUNCDESC getFuncDesc(int i) {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(this.typeInfo.GetFuncDesc(new WinDef.UINT(i), pointerByReference));
        return new OaIdl.FUNCDESC(pointerByReference.getValue());
    }

    public OaIdl.MEMBERID[] getIDsOfNames(WTypes.LPOLESTR[] lpolestrArr, int i) {
        OaIdl.MEMBERID[] memberidArr = new OaIdl.MEMBERID[i];
        COMUtils.checkRC(this.typeInfo.GetIDsOfNames(lpolestrArr, new WinDef.UINT(i), memberidArr));
        return memberidArr;
    }

    public int getImplTypeFlags(int i) {
        IntByReference intByReference = new IntByReference();
        COMUtils.checkRC(this.typeInfo.GetImplTypeFlags(new WinDef.UINT(i), intByReference));
        return intByReference.getValue();
    }

    public String[] getNames(OaIdl.MEMBERID memberid, int i) {
        WTypes.BSTR[] bstrArr = new WTypes.BSTR[i];
        WinDef.UINTByReference uINTByReference = new WinDef.UINTByReference();
        COMUtils.checkRC(this.typeInfo.GetNames(memberid, bstrArr, new WinDef.UINT(i), uINTByReference));
        int iIntValue = uINTByReference.getValue().intValue();
        String[] strArr = new String[iIntValue];
        for (int i2 = 0; i2 < iIntValue; i2++) {
            strArr[i2] = bstrArr[i2].getValue();
            OLEAUTO.SysFreeString(bstrArr[i2]);
        }
        return strArr;
    }

    public ITypeInfo getRefTypeInfo(OaIdl.HREFTYPE hreftype) {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(this.typeInfo.GetRefTypeInfo(hreftype, pointerByReference));
        return new TypeInfo(pointerByReference.getValue());
    }

    public OaIdl.HREFTYPE getRefTypeOfImplType(int i) {
        OaIdl.HREFTYPEByReference hREFTYPEByReference = new OaIdl.HREFTYPEByReference();
        COMUtils.checkRC(this.typeInfo.GetRefTypeOfImplType(new WinDef.UINT(i), hREFTYPEByReference));
        return hREFTYPEByReference.getValue();
    }

    public OaIdl.TYPEATTR getTypeAttr() {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(this.typeInfo.GetTypeAttr(pointerByReference));
        return new OaIdl.TYPEATTR(pointerByReference.getValue());
    }

    public TypeComp getTypeComp() {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(this.typeInfo.GetTypeComp(pointerByReference));
        return new TypeComp(pointerByReference.getValue());
    }

    public OaIdl.VARDESC getVarDesc(int i) {
        PointerByReference pointerByReference = new PointerByReference();
        COMUtils.checkRC(this.typeInfo.GetVarDesc(new WinDef.UINT(i), pointerByReference));
        return new OaIdl.VARDESC(pointerByReference.getValue());
    }
}

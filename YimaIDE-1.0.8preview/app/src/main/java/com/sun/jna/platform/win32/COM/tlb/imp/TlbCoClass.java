package com.sun.jna.platform.win32.COM.tlb.imp;

import com.sun.jna.platform.win32.COM.TypeInfoUtil;
import com.sun.jna.platform.win32.COM.TypeLibUtil;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.org.apache.xalan.internal.templates.Constants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TlbCoClass extends TlbBase {
    public TlbCoClass(int i, String str, TypeLibUtil typeLibUtil, String str2) {
        super(i, typeLibUtil, null);
        TypeInfoUtil typeInfoUtil = typeLibUtil.getTypeInfoUtil(i);
        TypeLibUtil.TypeLibDoc documentation = this.typeLibUtil.getDocumentation(i);
        String docString = documentation.getDocString();
        if (documentation.getName().length() > 0) {
            this.name = documentation.getName();
        }
        logInfo("Type of kind 'CoClass' found: " + this.name);
        createPackageName(str);
        createClassName(this.name);
        setFilename(this.name);
        String guidString = this.typeLibUtil.getLibAttr().guid.toGuidString();
        String str3 = this.typeLibUtil.getLibAttr().wMajorVerNum.intValue() + Constants.ATTRVAL_THIS + this.typeLibUtil.getLibAttr().wMinorVerNum.intValue();
        String guidString2 = typeInfoUtil.getTypeAttr().guid.toGuidString();
        createJavaDocHeader(guidString, str3, docString);
        createCLSID(guidString2);
        createCLSIDName(this.name);
        int iIntValue = typeInfoUtil.getTypeAttr().cImplTypes.intValue();
        String strConcat = "";
        for (int i2 = 0; i2 < iIntValue; i2++) {
            TypeInfoUtil typeInfoUtil2 = new TypeInfoUtil(typeInfoUtil.getRefTypeInfo(typeInfoUtil.getRefTypeOfImplType(i2)));
            createFunctions(typeInfoUtil2, str2);
            strConcat = strConcat + typeInfoUtil2.getDocumentation(new OaIdl.MEMBERID(-1)).getName();
            if (i2 < iIntValue - 1) {
                strConcat = strConcat.concat(", ");
            }
        }
        createInterfaces(strConcat);
        createContent(this.content);
    }

    public void createCLSID(String str) {
        replaceVariable("clsid", str);
    }

    public void createCLSIDName(String str) {
        replaceVariable("clsidname", str.toUpperCase());
    }

    public void createFunctions(TypeInfoUtil typeInfoUtil, String str) {
        TypeInfoUtil typeInfoUtil2;
        TlbAbstractMethod tlbPropertyPut;
        int iIntValue = typeInfoUtil.getTypeAttr().cFuncs.intValue();
        int i = 0;
        while (i < iIntValue) {
            OaIdl.FUNCDESC funcDesc = typeInfoUtil.getFuncDesc(i);
            int i2 = funcDesc.invkind.value;
            if (i2 == OaIdl.INVOKEKIND.INVOKE_FUNC.value) {
                boolean zIsVTableMode = isVTableMode();
                int i3 = this.index;
                if (zIsVTableMode) {
                    typeInfoUtil2 = typeInfoUtil;
                    tlbPropertyPut = new TlbFunctionVTable(i, i3, this.typeLibUtil, funcDesc, typeInfoUtil2);
                } else {
                    typeInfoUtil2 = typeInfoUtil;
                    tlbPropertyPut = new TlbFunctionDispId(i, i3, this.typeLibUtil, funcDesc, typeInfoUtil2);
                }
            } else {
                typeInfoUtil2 = typeInfoUtil;
                if (i2 == OaIdl.INVOKEKIND.INVOKE_PROPERTYGET.value) {
                    tlbPropertyPut = new TlbPropertyGet(i, this.index, this.typeLibUtil, funcDesc, typeInfoUtil2);
                } else {
                    tlbPropertyPut = (i2 == OaIdl.INVOKEKIND.INVOKE_PROPERTYPUT.value || i2 == OaIdl.INVOKEKIND.INVOKE_PROPERTYPUTREF.value) ? new TlbPropertyPut(i, this.index, this.typeLibUtil, funcDesc, typeInfoUtil2) : null;
                }
            }
            if (!isReservedMethod(tlbPropertyPut.getMethodName())) {
                this.content += ((Object) tlbPropertyPut.getClassBuffer());
                if (i < iIntValue - 1) {
                    this.content += "\n";
                }
            }
            typeInfoUtil2.ReleaseFuncDesc(funcDesc);
            i++;
            typeInfoUtil = typeInfoUtil2;
        }
    }

    public void createInterfaces(String str) {
        replaceVariable("interfaces", str);
    }

    public void createJavaDocHeader(String str, String str2, String str3) {
        replaceVariable("uuid", str);
        replaceVariable("version", str2);
        replaceVariable("helpstring", str3);
    }

    @Override // com.sun.jna.platform.win32.COM.tlb.imp.TlbBase
    public String getClassTemplate() {
        return "com/sun/jna/platform/win32/COM/tlb/imp/TlbCoClass.template";
    }
}

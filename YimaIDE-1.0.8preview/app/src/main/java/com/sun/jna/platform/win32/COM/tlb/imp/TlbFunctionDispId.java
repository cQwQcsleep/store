package com.sun.jna.platform.win32.COM.tlb.imp;

import com.sun.jna.platform.win32.COM.TypeInfoUtil;
import com.sun.jna.platform.win32.COM.TypeLibUtil;
import com.sun.jna.platform.win32.OaIdl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TlbFunctionDispId extends TlbAbstractMethod {
    public TlbFunctionDispId(int i, int i2, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcdesc, TypeInfoUtil typeInfoUtil) {
        String str;
        super(i2, typeLibUtil, funcdesc, typeInfoUtil);
        String[] names = typeInfoUtil.getNames(funcdesc.memid, this.paramCount + 1);
        int i3 = 0;
        while (i3 < this.paramCount) {
            OaIdl.ELEMDESC elemdesc = funcdesc.lprgelemdescParam.elemDescArg[i3];
            int i4 = i3 + 1;
            String lowerCase = names[i4].toLowerCase();
            String type = getType(elemdesc.tdesc);
            String strReplaceJavaKeyword = replaceJavaKeyword(lowerCase);
            this.methodparams += type + " " + strReplaceJavaKeyword;
            boolean zEquals = type.equals("VARIANT");
            String str2 = this.methodvariables;
            if (zEquals) {
                this.methodvariables = str2 + strReplaceJavaKeyword;
            } else {
                this.methodvariables = str2 + "new VARIANT(" + strReplaceJavaKeyword + ")";
            }
            if (i3 < this.paramCount - 1) {
                this.methodparams += ", ";
                this.methodvariables += ", ";
            }
            i3 = i4;
        }
        if (this.returnType.equalsIgnoreCase("VARIANT")) {
            str = "pResult";
        } else {
            str = "((" + this.returnType + ") pResult.getValue())";
        }
        replaceVariable("helpstring", this.docStr);
        replaceVariable("returntype", this.returnType);
        replaceVariable("returnvalue", str);
        replaceVariable("methodname", this.methodName);
        replaceVariable("methodparams", this.methodparams);
        replaceVariable("methodvariables", this.methodvariables);
        replaceVariable("vtableid", String.valueOf((int) this.vtableId));
        replaceVariable("memberid", String.valueOf(this.memberid));
        replaceVariable("functionCount", String.valueOf(i));
    }

    @Override // com.sun.jna.platform.win32.COM.tlb.imp.TlbBase
    public String getClassTemplate() {
        return "com/sun/jna/platform/win32/COM/tlb/imp/TlbFunctionDispId.template";
    }
}

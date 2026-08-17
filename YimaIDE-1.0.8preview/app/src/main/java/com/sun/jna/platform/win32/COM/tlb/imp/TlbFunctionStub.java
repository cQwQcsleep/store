package com.sun.jna.platform.win32.COM.tlb.imp;

import com.sun.jna.platform.win32.COM.TypeInfoUtil;
import com.sun.jna.platform.win32.COM.TypeLibUtil;
import com.sun.jna.platform.win32.OaIdl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TlbFunctionStub extends TlbAbstractMethod {
    public TlbFunctionStub(int i, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcdesc, TypeInfoUtil typeInfoUtil) {
        super(i, typeLibUtil, funcdesc, typeInfoUtil);
        TypeInfoUtil.TypeInfoDoc documentation = typeInfoUtil.getDocumentation(funcdesc.memid);
        String name = documentation.getName();
        String docString = documentation.getDocString();
        String[] names = typeInfoUtil.getNames(funcdesc.memid, this.paramCount + 1);
        if (this.paramCount > 0) {
            this.methodvariables = ", ";
        }
        int i2 = 0;
        while (i2 < this.paramCount) {
            OaIdl.ELEMDESC elemdesc = funcdesc.lprgelemdescParam.elemDescArg[i2];
            int i3 = i2 + 1;
            String lowerCase = names[i3].toLowerCase();
            this.methodparams += getType(elemdesc.tdesc) + " " + replaceJavaKeyword(lowerCase);
            this.methodvariables += lowerCase;
            if (i2 < this.paramCount - 1) {
                this.methodparams += ", ";
                this.methodvariables += ", ";
            }
            i2 = i3;
        }
        replaceVariable("helpstring", docString);
        replaceVariable("returntype", this.returnType);
        replaceVariable("methodname", name);
        replaceVariable("methodparams", this.methodparams);
        replaceVariable("vtableid", String.valueOf((int) this.vtableId));
        replaceVariable("memberid", String.valueOf(this.memberid));
    }

    @Override // com.sun.jna.platform.win32.COM.tlb.imp.TlbBase
    public String getClassTemplate() {
        return "com/sun/jna/platform/win32/COM/tlb/imp/TlbFunctionStub.template";
    }
}

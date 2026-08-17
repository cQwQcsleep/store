package com.sun.jna.platform.win32.COM.tlb.imp;

import com.sun.jna.platform.win32.COM.TypeInfoUtil;
import com.sun.jna.platform.win32.COM.TypeLibUtil;
import com.sun.jna.platform.win32.OaIdl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TlbPropertyPutStub extends TlbAbstractMethod {
    public TlbPropertyPutStub(int i, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcdesc, TypeInfoUtil typeInfoUtil) {
        super(i, typeLibUtil, funcdesc, typeInfoUtil);
        TypeInfoUtil.TypeInfoDoc documentation = typeInfoUtil.getDocumentation(funcdesc.memid);
        String docString = documentation.getDocString();
        String str = "set" + documentation.getName();
        String[] names = typeInfoUtil.getNames(funcdesc.memid, this.paramCount + 1);
        for (int i2 = 0; i2 < this.paramCount; i2++) {
            this.methodparams += getType(funcdesc.lprgelemdescParam.elemDescArg[i2]) + " " + replaceJavaKeyword(names[i2].toLowerCase());
            if (i2 < this.paramCount - 1) {
                this.methodparams += ", ";
            }
        }
        replaceVariable("helpstring", docString);
        replaceVariable("methodname", str);
        replaceVariable("methodparams", this.methodparams);
        replaceVariable("vtableid", String.valueOf((int) this.vtableId));
        replaceVariable("memberid", String.valueOf(this.memberid));
    }

    @Override // com.sun.jna.platform.win32.COM.tlb.imp.TlbBase
    public String getClassTemplate() {
        return "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyPutStub.template";
    }
}

package com.sun.jna.platform.win32.COM.tlb.imp;

import com.sun.jna.platform.win32.COM.TypeInfoUtil;
import com.sun.jna.platform.win32.COM.TypeLibUtil;
import com.sun.jna.platform.win32.OaIdl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TlbPropertyGetStub extends TlbAbstractMethod {
    public TlbPropertyGetStub(int i, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcdesc, TypeInfoUtil typeInfoUtil) {
        super(i, typeLibUtil, funcdesc, typeInfoUtil);
        TypeInfoUtil.TypeInfoDoc documentation = typeInfoUtil.getDocumentation(funcdesc.memid);
        String docString = documentation.getDocString();
        String str = "get" + documentation.getName();
        replaceVariable("helpstring", docString);
        replaceVariable("returntype", this.returnType);
        replaceVariable("methodname", str);
        replaceVariable("vtableid", String.valueOf((int) this.vtableId));
        replaceVariable("memberid", String.valueOf(this.memberid));
    }

    @Override // com.sun.jna.platform.win32.COM.tlb.imp.TlbBase
    public String getClassTemplate() {
        return "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyGetStub.template";
    }
}

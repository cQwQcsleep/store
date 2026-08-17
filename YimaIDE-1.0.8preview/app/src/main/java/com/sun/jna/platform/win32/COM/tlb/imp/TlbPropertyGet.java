package com.sun.jna.platform.win32.COM.tlb.imp;

import com.sun.jna.platform.win32.COM.TypeInfoUtil;
import com.sun.jna.platform.win32.COM.TypeLibUtil;
import com.sun.jna.platform.win32.OaIdl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TlbPropertyGet extends TlbAbstractMethod {
    public TlbPropertyGet(int i, int i2, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcdesc, TypeInfoUtil typeInfoUtil) {
        super(i2, typeLibUtil, funcdesc, typeInfoUtil);
        this.methodName = "get" + getMethodName();
        replaceVariable("helpstring", this.docStr);
        replaceVariable("returntype", this.returnType);
        replaceVariable("methodname", this.methodName);
        replaceVariable("vtableid", String.valueOf((int) this.vtableId));
        replaceVariable("memberid", String.valueOf(this.memberid));
        replaceVariable("functionCount", String.valueOf(i));
    }

    @Override // com.sun.jna.platform.win32.COM.tlb.imp.TlbBase
    public String getClassTemplate() {
        return "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyGet.template";
    }
}

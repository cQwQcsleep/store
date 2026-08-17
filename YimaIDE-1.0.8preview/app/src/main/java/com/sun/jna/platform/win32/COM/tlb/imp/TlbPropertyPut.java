package com.sun.jna.platform.win32.COM.tlb.imp;

import com.sun.jna.platform.win32.COM.TypeInfoUtil;
import com.sun.jna.platform.win32.COM.TypeLibUtil;
import com.sun.jna.platform.win32.OaIdl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TlbPropertyPut extends TlbAbstractMethod {
    public TlbPropertyPut(int i, int i2, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcdesc, TypeInfoUtil typeInfoUtil) {
        super(i2, typeLibUtil, funcdesc, typeInfoUtil);
        this.methodName = "set" + getMethodName();
        String[] names = typeInfoUtil.getNames(funcdesc.memid, this.paramCount + 1);
        if (this.paramCount > 0) {
            this.methodvariables += ", ";
        }
        for (int i3 = 0; i3 < this.paramCount; i3++) {
            this.methodparams += getType(funcdesc.lprgelemdescParam.elemDescArg[i3]) + " " + replaceJavaKeyword(names[i3].toLowerCase());
            this.methodvariables += replaceJavaKeyword(names[i3].toLowerCase());
            if (i3 < this.paramCount - 1) {
                this.methodparams += ", ";
                this.methodvariables += ", ";
            }
        }
        replaceVariable("helpstring", this.docStr);
        replaceVariable("methodname", this.methodName);
        replaceVariable("methodparams", this.methodparams);
        replaceVariable("methodvariables", this.methodvariables);
        replaceVariable("vtableid", String.valueOf((int) this.vtableId));
        replaceVariable("memberid", String.valueOf(this.memberid));
        replaceVariable("functionCount", String.valueOf(i));
    }

    @Override // com.sun.jna.platform.win32.COM.tlb.imp.TlbBase
    public String getClassTemplate() {
        return "com/sun/jna/platform/win32/COM/tlb/imp/TlbPropertyPut.template";
    }
}

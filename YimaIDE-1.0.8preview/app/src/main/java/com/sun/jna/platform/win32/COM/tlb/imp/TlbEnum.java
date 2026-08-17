package com.sun.jna.platform.win32.COM.tlb.imp;

import com.sun.jna.platform.win32.COM.TypeInfoUtil;
import com.sun.jna.platform.win32.COM.TypeLibUtil;
import com.sun.jna.platform.win32.OaIdl;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TlbEnum extends TlbBase {
    public TlbEnum(int i, String str, TypeLibUtil typeLibUtil) {
        super(i, typeLibUtil, null);
        TypeLibUtil.TypeLibDoc documentation = this.typeLibUtil.getDocumentation(i);
        String docString = documentation.getDocString();
        if (documentation.getName().length() > 0) {
            this.name = documentation.getName();
        }
        logInfo("Type of kind 'Enum' found: " + this.name);
        createPackageName(str);
        createClassName(this.name);
        setFilename(this.name);
        TypeInfoUtil typeInfoUtil = typeLibUtil.getTypeInfoUtil(i);
        OaIdl.TYPEATTR typeAttr = typeInfoUtil.getTypeAttr();
        createJavaDocHeader(typeAttr.guid.toGuidString(), docString);
        int iIntValue = typeAttr.cVars.intValue();
        for (int i2 = 0; i2 < iIntValue; i2++) {
            OaIdl.VARDESC varDesc = typeInfoUtil.getVarDesc(i2);
            Object value = varDesc._vardesc.lpvarValue.getValue();
            TypeInfoUtil.TypeInfoDoc documentation2 = typeInfoUtil.getDocumentation(varDesc.memid);
            this.content += "\t\t//" + documentation2.getName() + "\n";
            this.content += "\t\tpublic static final int " + documentation2.getName() + " = " + value.toString() + ";";
            if (i2 < iIntValue - 1) {
                this.content += "\n";
            }
            typeInfoUtil.ReleaseVarDesc(varDesc);
        }
        createContent(this.content);
    }

    public void createJavaDocHeader(String str, String str2) {
        replaceVariable("uuid", str);
        replaceVariable("helpstring", str2);
    }

    @Override // com.sun.jna.platform.win32.COM.tlb.imp.TlbBase
    public String getClassTemplate() {
        return "com/sun/jna/platform/win32/COM/tlb/imp/TlbEnum.template";
    }
}

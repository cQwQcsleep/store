package com.reandroid.dex.smali.model;

import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliParamSet extends SmaliSet<SmaliMethodParameter> {
    @Override // com.reandroid.dex.smali.model.SmaliSet
    public SmaliMethodParameter createNext(SmaliReader smaliReader) {
        if (SmaliDirective.parse(smaliReader, false) == SmaliDirective.PARAM) {
            return new SmaliMethodParameter();
        }
        return null;
    }
}

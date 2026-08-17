package com.reandroid.dex.smali.model;

import com.reandroid.dex.common.Register;
import com.reandroid.dex.debug.DebugElementType;
import com.reandroid.dex.smali.SmaliReader;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class SmaliDebugRegister extends SmaliDebugElement implements SmaliRegion {
    private final SmaliRegisterSet registerSet;

    public SmaliDebugRegister() {
        SmaliRegisterSet smaliRegisterSet = new SmaliRegisterSet();
        this.registerSet = smaliRegisterSet;
        smaliRegisterSet.setParent(this);
    }

    @Override // com.reandroid.dex.smali.model.SmaliDebug, com.reandroid.dex.smali.model.Smali, com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        super.append(smaliWriter);
        getRegisterSet().append(smaliWriter);
    }

    @Override // com.reandroid.dex.smali.model.SmaliDebugElement
    public abstract DebugElementType<?> getDebugElementType();

    public Register getRegister() {
        return getRegisterSet().getRegister(0);
    }

    public SmaliRegisterSet getRegisterSet() {
        return this.registerSet;
    }

    @Override // com.reandroid.dex.smali.model.SmaliDebug, com.reandroid.dex.smali.model.SmaliCode, com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
        super.parse(smaliReader);
        getRegisterSet().parse(smaliReader);
    }
}

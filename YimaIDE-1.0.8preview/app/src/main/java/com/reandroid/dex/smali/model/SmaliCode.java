package com.reandroid.dex.smali.model;

import com.reandroid.common.Origin;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.smali.SmaliReader;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliCode extends Smali {
    public String buildOrigin() {
        MethodKey key;
        StringBuilder sb = new StringBuilder();
        Origin origin = getOrigin();
        if (origin != null) {
            sb.append('\n');
            sb.append(origin);
        } else {
            SmaliMethod smaliMethod = (SmaliMethod) getParentInstance(SmaliMethod.class);
            if (smaliMethod != null && (key = smaliMethod.getKey()) != null) {
                sb.append(" on method: ");
                sb.append(key);
            }
        }
        return sb.toString();
    }

    public SmaliCodeSet getCodeSet() {
        return (SmaliCodeSet) getParentInstance(SmaliCodeSet.class);
    }

    @Override // com.reandroid.dex.smali.SmaliParser
    public void parse(SmaliReader smaliReader) throws IOException {
    }
}

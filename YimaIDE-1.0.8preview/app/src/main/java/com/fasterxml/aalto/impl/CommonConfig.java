package com.fasterxml.aalto.impl;

import java.util.HashMap;
import org.codehaus.stax2.XMLStreamProperties;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class CommonConfig {
    static final HashMap<String, Integer> sStdProperties;
    protected int _flagMods;
    protected int _flags;

    static {
        HashMap<String, Integer> map = new HashMap<>(16);
        sStdProperties = map;
        map.put(XMLStreamProperties.XSP_IMPLEMENTATION_NAME, 1);
        map.put(XMLStreamProperties.XSP_IMPLEMENTATION_VERSION, 2);
        map.put(XMLStreamProperties.XSP_SUPPORTS_XML11, 3);
        map.put(XMLStreamProperties.XSP_SUPPORT_XMLID, 4);
        map.put("http://java.sun.com/xml/stream/properties/implementation-name", 1);
    }

    public CommonConfig(int i, int i2) {
        this._flags = i;
        this._flagMods = i2;
    }

    public Object getProperty(String str, boolean z) {
        Integer num = sStdProperties.get(str);
        if (num != null) {
            int iIntValue = num.intValue();
            if (iIntValue == 1) {
                return "aalto";
            }
            if (iIntValue == 2) {
                return "0.9";
            }
            if (iIntValue == 3) {
                return Boolean.FALSE;
            }
            if (iIntValue == 4) {
                return Boolean.FALSE;
            }
        }
        if (!z) {
            return null;
        }
        kg9.a("Unrecognized property '", str, "'");
        return null;
    }

    public final boolean hasFlag(int i) {
        return (this._flags & i) != 0;
    }

    public final void setFlag(int i, boolean z) {
        int i2 = this._flags;
        if (z) {
            this._flags = i2 | i;
        } else {
            this._flags = (~i) & i2;
        }
        this._flagMods = i | this._flagMods;
    }

    public boolean setProperty(String str, Object obj) {
        if (sStdProperties.get(str) != null) {
            return false;
        }
        kg9.a("Unrecognized property '", str, "'");
        return false;
    }
}

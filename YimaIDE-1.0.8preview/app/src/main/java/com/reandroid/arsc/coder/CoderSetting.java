package com.reandroid.arsc.coder;

import com.reandroid.arsc.coder.xml.AaptXmlStringDecoder;
import com.reandroid.arsc.coder.xml.RawXmlStringDecoder;
import com.reandroid.arsc.coder.xml.XmlCoderLogger;
import com.reandroid.arsc.coder.xml.XmlStringDecoder;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class CoderSetting {
    private XmlCoderLogger logger;
    private XmlStringDecoder stringDecoder = new RawXmlStringDecoder();

    public XmlCoderLogger getLogger() {
        return this.logger;
    }

    public XmlStringDecoder getStringDecoder() {
        return this.stringDecoder;
    }

    public boolean isAapt() {
        return this.stringDecoder instanceof AaptXmlStringDecoder;
    }

    public void setLogger(XmlCoderLogger xmlCoderLogger) {
        this.logger = xmlCoderLogger;
    }

    public void setStringDecoder(XmlStringDecoder xmlStringDecoder) {
        if (xmlStringDecoder != null) {
            this.stringDecoder = xmlStringDecoder;
        } else {
            x0e.a("Null XmlStringDecoder");
        }
    }
}

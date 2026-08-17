package com.sun.org.apache.xerces.internal.xinclude;

import com.sun.org.apache.xerces.internal.util.XML11Char;
import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XInclude11TextReader extends XIncludeTextReader {
    public XInclude11TextReader(XMLInputSource xMLInputSource, XIncludeHandler xIncludeHandler, int i) throws IOException {
        super(xMLInputSource, xIncludeHandler, i);
    }

    @Override // com.sun.org.apache.xerces.internal.xinclude.XIncludeTextReader
    public boolean isValid(int i) {
        return XML11Char.isXML11Valid(i);
    }
}

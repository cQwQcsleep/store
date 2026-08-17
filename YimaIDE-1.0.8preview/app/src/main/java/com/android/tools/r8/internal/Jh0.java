package com.android.tools.r8.internal;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Jh0 {
    public static final void a(Reader reader, H50 h50) throws IllegalAccessException, IOException, InvocationTargetException {
        KB.c(h50, "model");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            XMLStreamReader xMLStreamReaderCreateXMLStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(reader);
            boolean z = false;
            while (!z && xMLStreamReaderCreateXMLStreamReader.hasNext()) {
                xMLStreamReaderCreateXMLStreamReader.next();
                if (xMLStreamReaderCreateXMLStreamReader.isStartElement()) {
                    if (KB.a((Object) xMLStreamReaderCreateXMLStreamReader.getLocalName(), (Object) "resources")) {
                        int attributeCount = xMLStreamReaderCreateXMLStreamReader.getAttributeCount();
                        for (int i = 0; i < attributeCount; i++) {
                            if (KB.a((Object) xMLStreamReaderCreateXMLStreamReader.getAttributeNamespace(i), (Object) "http://schemas.android.com/tools")) {
                                String attributeLocalName = xMLStreamReaderCreateXMLStreamReader.getAttributeLocalName(i);
                                KB.b(attributeLocalName, "xmlStreamReader.getAttributeLocalName(i)");
                                String attributeValue = xMLStreamReaderCreateXMLStreamReader.getAttributeValue(i);
                                KB.b(attributeValue, "xmlStreamReader.getAttributeValue(i)");
                                linkedHashMap.put(attributeLocalName, attributeValue);
                            }
                        }
                    }
                    z = true;
                }
            }
            AbstractC2954wd.a(reader, null);
            AbstractC0706Nu.a(linkedHashMap).forEach(new Ih0(h50));
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AbstractC2954wd.a(reader, th);
                throw th2;
            }
        }
    }
}

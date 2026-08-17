package com.reandroid.arsc.coder.xml;

import com.reandroid.arsc.chunk.TypeBlock;
import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ValuesSerializerFactory {
    XmlSerializer createSerializer(TypeBlock typeBlock) throws IOException;

    void onFinish(XmlSerializer xmlSerializer, int i) throws IOException;
}

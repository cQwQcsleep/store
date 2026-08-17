package com.reandroid.arsc.coder.xml;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.utils.io.FileUtil;
import com.reandroid.utils.io.IOUtil;
import com.reandroid.xml.XMLFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ValuesDirectorySerializer implements ValuesSerializerFactory {
    private final File resourcesDir;
    private final Map<XmlSerializer, File> serializerFileMap = new HashMap();

    public ValuesDirectorySerializer(File file) {
        this.resourcesDir = file;
    }

    @Override // com.reandroid.arsc.coder.xml.ValuesSerializerFactory
    public XmlSerializer createSerializer(TypeBlock typeBlock) throws IOException {
        File file = new File(new File(new File(this.resourcesDir, typeBlock.getPackageBlock().buildDecodeDirectoryName()), "res"), "values" + typeBlock.getResConfig().getQualifiers());
        String typeName = typeBlock.getTypeName();
        if (!typeName.endsWith("s")) {
            typeName = typeName.concat("s");
        }
        File file2 = new File(file, typeName.concat(".xml"));
        XmlSerializer xmlSerializerNewSerializer = XMLFactory.newSerializer(file2);
        this.serializerFileMap.put(xmlSerializerNewSerializer, file2);
        xmlSerializerNewSerializer.startDocument("utf-8", null);
        XmlDecodeUtil.rootIndent(xmlSerializerNewSerializer);
        xmlSerializerNewSerializer.startTag(null, PackageBlock.TAG_resources);
        return xmlSerializerNewSerializer;
    }

    @Override // com.reandroid.arsc.coder.xml.ValuesSerializerFactory
    public void onFinish(XmlSerializer xmlSerializer, int i) throws IOException {
        XmlDecodeUtil.rootIndent(xmlSerializer);
        xmlSerializer.endTag(null, PackageBlock.TAG_resources);
        xmlSerializer.endDocument();
        xmlSerializer.flush();
        IOUtil.close(xmlSerializer);
        File fileRemove = this.serializerFileMap.remove(xmlSerializer);
        if (i == 0 && fileRemove != null && fileRemove.isFile()) {
            fileRemove.delete();
            FileUtil.deleteEmptyDirectory(fileRemove.getParentFile());
        }
    }
}

package com.android.apksig.internal.jar;

import java.io.IOException;
import java.io.OutputStream;
import java.util.SortedMap;
import java.util.jar.Attributes;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class SignatureFileWriter {
    private SignatureFileWriter() {
    }

    public static void writeIndividualSection(OutputStream outputStream, String str, Attributes attributes) throws IOException {
        ManifestWriter.writeIndividualSection(outputStream, str, attributes);
    }

    public static void writeMainSection(OutputStream outputStream, Attributes attributes) throws IOException {
        Attributes.Name name = Attributes.Name.SIGNATURE_VERSION;
        String value = attributes.getValue(name);
        if (value == null) {
            kg9.a("Mandatory ", name, " attribute missing");
            return;
        }
        ManifestWriter.writeAttribute(outputStream, name, value);
        if (attributes.size() > 1) {
            SortedMap<String, String> attributesSortedByName = ManifestWriter.getAttributesSortedByName(attributes);
            attributesSortedByName.remove(name.toString());
            ManifestWriter.writeAttributes(outputStream, attributesSortedByName);
        }
        writeSectionDelimiter(outputStream);
    }

    public static void writeSectionDelimiter(OutputStream outputStream) throws IOException {
        ManifestWriter.writeSectionDelimiter(outputStream);
    }
}

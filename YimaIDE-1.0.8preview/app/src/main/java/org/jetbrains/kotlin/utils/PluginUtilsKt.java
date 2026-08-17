package org.jetbrains.kotlin.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\u001a \u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u0002¨\u0006\u0005"}, d2 = {"decodePluginOptions", HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET, "options", "org.jetbrains.kotlin:util"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class PluginUtilsKt {
    public static final Map<String, List<String>> decodePluginOptions(String str) throws IOException {
        str.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(str)));
        int i = objectInputStream.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            String utf = objectInputStream.readUTF();
            int i3 = objectInputStream.readInt();
            ArrayList arrayList = new ArrayList();
            for (int i4 = 0; i4 < i3; i4++) {
                byte[] bArr = new byte[objectInputStream.readInt()];
                objectInputStream.readFully(bArr);
                Charset charset = StandardCharsets.UTF_8;
                charset.getClass();
                arrayList.add(new String(bArr, charset));
            }
            linkedHashMap.put(utf, arrayList);
        }
        return linkedHashMap;
    }
}

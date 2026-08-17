package org.jcodings.spi;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Charsets extends CharsetProvider {
    private static final List<Charset> charsets = Collections.singletonList(ISO_8859_16.INSTANCE);

    @Override // java.nio.charset.spi.CharsetProvider
    public Charset charsetForName(String str) {
        if ("ISO-8859-16".equals(str) || ISO_8859_16.INSTANCE.aliases().contains(str)) {
            return ISO_8859_16.INSTANCE;
        }
        return null;
    }

    @Override // java.nio.charset.spi.CharsetProvider
    public Iterator<Charset> charsets() {
        return charsets.iterator();
    }
}

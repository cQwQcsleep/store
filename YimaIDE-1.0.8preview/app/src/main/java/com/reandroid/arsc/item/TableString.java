package com.reandroid.arsc.item;

import com.reandroid.arsc.item.TableString;
import com.reandroid.arsc.value.Entry;
import com.reandroid.xml.StyleDocument;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TableString extends StringItem {
    public TableString(boolean z) {
        super(z);
    }

    public static /* synthetic */ boolean p(boolean z, Entry entry) {
        return z ? entry.isComplex() : entry.isScalar();
    }

    public Iterator<Entry> getEntries(final boolean z) {
        return super.getUsers(Entry.class, new Predicate() { // from class: n2e
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TableString.p(z, (Entry) obj);
            }
        });
    }

    @Override // com.reandroid.arsc.item.StringItem
    public void serializeText(XmlSerializer xmlSerializer, boolean z) throws IOException {
        StyleDocument styleDocument = getStyleDocument();
        if (styleDocument == null) {
            super.serializeText(xmlSerializer, z);
        } else {
            styleDocument.serialize(xmlSerializer);
        }
    }

    public Iterator<Entry> getEntries(Predicate<Entry> predicate) {
        return super.getUsers(Entry.class, predicate);
    }
}

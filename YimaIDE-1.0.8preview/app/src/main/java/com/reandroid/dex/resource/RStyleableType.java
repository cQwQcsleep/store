package com.reandroid.dex.resource;

import com.reandroid.apk.XmlHelper;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexField;
import com.reandroid.dex.model.DexMethod;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.InstanceIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RStyleableType extends RTypeItem implements Iterable<RStyleableItem> {
    public RStyleableType(DexClass dexClass) {
        super(dexClass);
    }

    private boolean isEntriesValid() {
        int i = 0;
        int i2 = 0;
        for (RStyleableItem rStyleableItem : this) {
            if (!rStyleableItem.isValid()) {
                return false;
            }
            if (rStyleableItem instanceof RStyleableIndex) {
                i++;
            } else {
                i2++;
            }
        }
        return (i == 0 || i2 == 0 || CollectionUtil.count(getDexClass().getStaticFields()) != i + i2) ? false : true;
    }

    @Override // com.reandroid.dex.resource.RTypeItem
    public void appendJavaEntries(SmaliWriter smaliWriter) throws IOException {
        for (RStyleableItem rStyleableItem : this) {
            smaliWriter.newLine();
            rStyleableItem.appendJava(smaliWriter);
        }
    }

    public RStyleableItem create(DexField dexField) {
        if (dexField.isPublic() && dexField.isStatic()) {
            TypeKey type = dexField.getKey().getType();
            if (TypeKey.TYPE_I.equals(type)) {
                return new RStyleableIndex(dexField);
            }
            if (RDeclareStyleable.INT_ARRAY.equals(type)) {
                return new RDeclareStyleable(dexField);
            }
        }
        return null;
    }

    public Iterator<RDeclareStyleable> getRDeclareStyleables() {
        return InstanceIterator.of(iterator(), RDeclareStyleable.class);
    }

    public boolean isEmpty() {
        return !iterator().hasNext();
    }

    @Override // com.reandroid.dex.resource.RTypeItem
    public boolean isValid() {
        DexClass dexClass = getDexClass();
        if (dexClass.getInstanceFields().hasNext()) {
            return false;
        }
        Iterator<DexMethod> declaredMethods = dexClass.getDeclaredMethods();
        while (declaredMethods.hasNext()) {
            if (!declaredMethods.next().isConstructor()) {
                return false;
            }
        }
        return isEntriesValid();
    }

    @Override // java.lang.Iterable
    public Iterator<RStyleableItem> iterator() {
        return ComputeIterator.of(getDexClass().getStaticFields(), new Function() { // from class: t5c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.create((DexField) obj);
            }
        });
    }

    public void serialize(TableBlock tableBlock, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startDocument("utf8", null);
        XmlHelper.setIndent(xmlSerializer, true);
        xmlSerializer.startTag(null, XmlHelper.RESOURCES_TAG);
        Iterator<RStyleableItem> it = iterator();
        while (it.hasNext()) {
            it.next().serialize(tableBlock, xmlSerializer);
        }
        XmlHelper.setIndent(xmlSerializer, true);
        xmlSerializer.endTag(null, XmlHelper.RESOURCES_TAG);
    }
}

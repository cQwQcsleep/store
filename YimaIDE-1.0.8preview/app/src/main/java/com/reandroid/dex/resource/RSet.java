package com.reandroid.dex.resource;

import com.reandroid.apk.XmlHelper;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.resource.RDeclareStyleable;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.UniqueIterator;
import com.reandroid.xml.XMLFactory;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RSet implements Iterable<R> {
    private final ArrayCollection<R> rList = new ArrayCollection<>();

    private ArrayCollection<R> getRList() {
        return this.rList;
    }

    public static List<DeclareStyleable> readStyleables(DexClassRepository dexClassRepository, TableBlock tableBlock) {
        RSet rSet = new RSet();
        rSet.load(dexClassRepository);
        return rSet.listDeclareStyleables(tableBlock);
    }

    public void clear() {
        getRList().clear();
    }

    public Iterator<DeclareStyleable> getDeclareStyleables(final TableBlock tableBlock) {
        return ComputeIterator.of(getRDeclareStyleables(), new Function() { // from class: s5c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((RDeclareStyleable) obj).toDeclareStyleable(tableBlock);
            }
        });
    }

    public Iterator<RDeclareStyleable> getRDeclareStyleables() {
        return new UniqueIterator(new IterableIterator<R, RDeclareStyleable>(iterator()) { // from class: com.reandroid.dex.resource.RSet.1
            public Iterator<RDeclareStyleable> iterator(R r) {
                return r.getRDeclareStyleables();
            }
        });
    }

    @Override // java.lang.Iterable
    public Iterator<R> iterator() {
        return getRList().iterator();
    }

    public List<DeclareStyleable> listDeclareStyleables(TableBlock tableBlock) {
        ArrayCollection arrayCollection = new ArrayCollection();
        arrayCollection.addAll(getDeclareStyleables(tableBlock));
        arrayCollection.sort(CompareUtil.getComparableComparator());
        return arrayCollection;
    }

    public void load(DexClassRepository dexClassRepository) {
        this.rList.addAll(R.findAll(dexClassRepository));
    }

    public void serialize(TableBlock tableBlock, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startDocument("utf8", null);
        XmlHelper.setIndent(xmlSerializer, true);
        xmlSerializer.startTag(null, XmlHelper.RESOURCES_TAG);
        Iterator<DeclareStyleable> it = listDeclareStyleables(tableBlock).iterator();
        while (it.hasNext()) {
            it.next().serialize(xmlSerializer);
        }
        XmlHelper.setIndent(xmlSerializer, true);
        xmlSerializer.endTag(null, XmlHelper.RESOURCES_TAG);
        xmlSerializer.endDocument();
    }

    public int size() {
        return getRList().size();
    }

    public String toXml(TableBlock tableBlock) throws IOException {
        StringWriter stringWriter = new StringWriter();
        XmlSerializer xmlSerializerNewSerializer = XMLFactory.newSerializer(stringWriter);
        serialize(tableBlock, xmlSerializerNewSerializer);
        xmlSerializerNewSerializer.flush();
        stringWriter.close();
        return stringWriter.toString();
    }
}

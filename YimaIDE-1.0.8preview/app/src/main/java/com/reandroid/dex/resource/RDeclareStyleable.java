package com.reandroid.dex.resource;

import com.reandroid.apk.XmlHelper;
import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.NumberIntegerReference;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.arsc.value.AttributeType;
import com.reandroid.arsc.value.ResTableMapEntry;
import com.reandroid.arsc.value.ResValueMap;
import com.reandroid.dex.ins.InsArrayData;
import com.reandroid.dex.ins.InsFillArrayData;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.key.FieldKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexField;
import com.reandroid.dex.model.DexInstruction;
import com.reandroid.dex.model.DexMethod;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.EmptyIterator;
import com.sun.org.apache.xalan.internal.templates.Constants;
import defpackage.b5c;
import java.io.IOException;
import java.util.Iterator;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RDeclareStyleable extends RStyleableItem implements Iterable<IntegerReference>, Comparable<RDeclareStyleable> {
    public static final TypeKey INT_ARRAY = TypeKey.TYPE_I.setArrayDimension(1);

    public static class PrimitiveArrayFillSequence implements Iterable<IntegerReference> {
        private DexInstruction newArrayInstruction;
        private DexInstruction sizeInstruction;
        private final DexInstruction sputObject;

        public PrimitiveArrayFillSequence(DexInstruction dexInstruction) {
            this.sputObject = dexInstruction;
        }

        private DexInstruction getNewArrayInstruction() {
            DexInstruction dexInstruction = this.newArrayInstruction;
            if (dexInstruction != null) {
                return dexInstruction;
            }
            DexInstruction dexInstruction2 = this.sputObject;
            DexInstruction previousSetter = dexInstruction2.getPreviousSetter(dexInstruction2.getRegister(), Opcode.NEW_ARRAY);
            if (previousSetter == null) {
                return null;
            }
            this.newArrayInstruction = previousSetter;
            return previousSetter;
        }

        private DexInstruction getSizeInstruction() {
            DexInstruction previousSetter;
            DexInstruction dexInstruction = this.sizeInstruction;
            if (dexInstruction != null) {
                return dexInstruction;
            }
            DexInstruction newArrayInstruction = getNewArrayInstruction();
            if (newArrayInstruction == null || (previousSetter = newArrayInstruction.getPreviousSetter(newArrayInstruction.getRegister(1), new b5c())) == null) {
                return null;
            }
            this.sizeInstruction = previousSetter;
            return previousSetter;
        }

        private DexInstruction getValueInstruction(int i) {
            DexInstruction previousSetter;
            DexInstruction dexInstruction = this.sputObject;
            for (DexInstruction previousReader = dexInstruction.getPreviousReader(dexInstruction.getRegister(), Opcode.APUT); previousReader != null && (previousSetter = previousReader.getPreviousSetter(previousReader.getRegister(2))) != null; previousReader = previousReader.getPreviousReader(previousReader.getRegister(1), Opcode.APUT)) {
                if (previousSetter.getAsInteger().intValue() == i) {
                    return previousReader.getPreviousSetter(previousReader.getRegister(0), new b5c());
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public IntegerReference getValueReference(int i) {
            if (!isValid()) {
                return null;
            }
            final DexInstruction valueInstruction = getValueInstruction(i);
            return valueInstruction == null ? new NumberIntegerReference() : new IntegerReference() { // from class: com.reandroid.dex.resource.RDeclareStyleable.PrimitiveArrayFillSequence.2
                public int get() {
                    return valueInstruction.getAsInteger().intValue();
                }

                public void set(int i2) {
                    valueInstruction.setAsInteger(i2);
                }
            };
        }

        public boolean isValid() {
            return getSizeInstruction() != null;
        }

        @Override // java.lang.Iterable
        public Iterator<IntegerReference> iterator() {
            if (isValid()) {
                return new Iterator<IntegerReference>() { // from class: com.reandroid.dex.resource.RDeclareStyleable.PrimitiveArrayFillSequence.1
                    private int index;

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.index < PrimitiveArrayFillSequence.this.size();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public IntegerReference next() {
                        IntegerReference valueReference = PrimitiveArrayFillSequence.this.getValueReference(this.index);
                        this.index++;
                        return valueReference;
                    }
                };
            }
            return null;
        }

        public int size() {
            DexInstruction sizeInstruction = getSizeInstruction();
            if (sizeInstruction != null) {
                return sizeInstruction.getAsInteger().intValue();
            }
            return -1;
        }
    }

    public RDeclareStyleable(DexField dexField) {
        super(dexField);
    }

    private void decodeAttributeName(TableBlock tableBlock, ResourceEntry resourceEntry, XmlSerializer xmlSerializer) throws IOException {
        String name = resourceEntry.getName();
        if (!resourceEntry.isContext((Block) tableBlock)) {
            name = resourceEntry.getPackageName() + ":" + name;
        }
        xmlSerializer.attribute(null, "name", name);
    }

    private void decodeFormat(ResourceEntry resourceEntry, XmlSerializer xmlSerializer) throws IOException {
        String strDecodeFormatValue = decodeFormatValue(resourceEntry);
        if (StringsUtil.isEmpty(strDecodeFormatValue)) {
            return;
        }
        xmlSerializer.attribute(null, Constants.ATTRNAME_FORMAT, strDecodeFormatValue);
    }

    private String decodeFormatValue(ResourceEntry resourceEntry) {
        ResValueMap byType;
        ResTableMapEntry resTableMapEntry = resourceEntry.get().getResTableMapEntry();
        if (resTableMapEntry == null || (byType = resTableMapEntry.getByType(AttributeType.FORMATS)) == null) {
            return null;
        }
        return byType.decodeValue();
    }

    private InsArrayData findArrayData(DexInstruction dexInstruction) {
        int register = dexInstruction.getRegister();
        for (DexInstruction previous = dexInstruction.getPrevious(); previous != null; previous = previous.getPrevious()) {
            if (previous.getRegister() == register) {
                if (previous.is(Opcode.FILL_ARRAY_DATA)) {
                    return ((InsFillArrayData) previous.getIns()).getInsArrayData();
                }
                if (!previous.is(Opcode.MOVE_OBJECT) && !previous.is(Opcode.MOVE_OBJECT_16) && !previous.is(Opcode.MOVE_OBJECT_FROM16)) {
                    return null;
                }
                register = previous.getRegister(1);
            }
        }
        return null;
    }

    private DexInstruction getSputObjectInstruction() {
        DexMethod staticConstructor = getDexField().getDexClass().getStaticConstructor();
        if (staticConstructor == null) {
            return null;
        }
        FieldKey key = getKey();
        Iterator<DexInstruction> instructions = staticConstructor.getInstructions();
        while (instructions.hasNext()) {
            DexInstruction next = instructions.next();
            if (next.is(Opcode.SPUT_OBJECT) && key.equals(next.getKeyAsField())) {
                return next;
            }
        }
        return null;
    }

    private DeclareStyleable.Attr toAttr(DeclareStyleable declareStyleable, TableBlock tableBlock, IntegerReference integerReference) {
        int i = integerReference.get();
        ResourceEntry resource = tableBlock.getResource(i);
        if (resource == null || resource.isEmpty()) {
            return null;
        }
        String name = resource.getName();
        if (!resource.isContext((Block) tableBlock)) {
            name = resource.getPackageName() + ":" + name;
        }
        return new DeclareStyleable.Attr(declareStyleable, i, name, decodeFormatValue(resource));
    }

    @Override // com.reandroid.dex.resource.RStyleableItem
    public void appendJavaValue(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append("new int[]{");
        boolean z = false;
        for (IntegerReference integerReference : this) {
            if (z) {
                smaliWriter.append(", ");
            }
            smaliWriter.appendHex(integerReference.get());
            z = true;
        }
        smaliWriter.append('}');
    }

    @Override // java.lang.Comparable
    public int compareTo(RDeclareStyleable rDeclareStyleable) {
        return CompareUtil.compare(getName(), rDeclareStyleable.getName());
    }

    @Override // com.reandroid.dex.resource.RStyleableItem
    public boolean isValid() {
        if (!super.isValid()) {
            return false;
        }
        Iterator<IntegerReference> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!PackageBlock.isResourceId(it.next().get())) {
                return false;
            }
            i++;
        }
        return i != 0;
    }

    @Override // java.lang.Iterable
    public Iterator<IntegerReference> iterator() {
        DexInstruction sputObjectInstruction = getSputObjectInstruction();
        if (sputObjectInstruction == null) {
            return EmptyIterator.of();
        }
        InsArrayData insArrayDataFindArrayData = findArrayData(sputObjectInstruction);
        if (insArrayDataFindArrayData != null) {
            return insArrayDataFindArrayData.getReferences();
        }
        PrimitiveArrayFillSequence primitiveArrayFillSequence = new PrimitiveArrayFillSequence(sputObjectInstruction);
        return primitiveArrayFillSequence.isValid() ? primitiveArrayFillSequence.iterator() : EmptyIterator.of();
    }

    @Override // com.reandroid.dex.resource.RStyleableItem
    public void serialize(TableBlock tableBlock, XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(null, "declare-styleable");
        xmlSerializer.attribute(null, "name", getName());
        Iterator<IntegerReference> it = iterator();
        while (it.hasNext()) {
            ResourceEntry resource = tableBlock.getResource(it.next().get());
            if (resource != null && !resource.isEmpty()) {
                xmlSerializer.startTag(null, "attr");
                XmlHelper.setIndent(xmlSerializer, false);
                decodeAttributeName(tableBlock, resource, xmlSerializer);
                decodeFormat(resource, xmlSerializer);
                XmlHelper.setIndent(xmlSerializer, true);
                xmlSerializer.endTag(null, "attr");
            }
        }
        xmlSerializer.endTag(null, "declare-styleable");
    }

    public DeclareStyleable toDeclareStyleable(TableBlock tableBlock) {
        DeclareStyleable declareStyleable = new DeclareStyleable(getName());
        new ArrayCollection();
        Iterator<IntegerReference> it = iterator();
        while (it.hasNext()) {
            declareStyleable.add(toAttr(declareStyleable, tableBlock, it.next()));
        }
        return declareStyleable;
    }
}

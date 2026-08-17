package com.sun.tools.javap;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Instruction;
import com.sun.tools.classfile.LocalVariableTypeTable_attribute;
import com.sun.tools.classfile.Signature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocalVariableTypeTableWriter extends InstructionDetailWriter {
    private ClassWriter classWriter;
    private Code_attribute codeAttr;
    private Map<Integer, List<LocalVariableTypeTable_attribute.Entry>> pcMap;

    public enum NoteKind {
        START("start") { // from class: com.sun.tools.javap.LocalVariableTypeTableWriter.NoteKind.1
            @Override // com.sun.tools.javap.LocalVariableTypeTableWriter.NoteKind
            public boolean match(LocalVariableTypeTable_attribute.Entry entry, int i) {
                return i == entry.start_pc;
            }
        },
        END("end") { // from class: com.sun.tools.javap.LocalVariableTypeTableWriter.NoteKind.2
            @Override // com.sun.tools.javap.LocalVariableTypeTableWriter.NoteKind
            public boolean match(LocalVariableTypeTable_attribute.Entry entry, int i) {
                return i == entry.start_pc + entry.length;
            }
        };

        public final String text;

        NoteKind(String str) {
            this.text = str;
        }

        public abstract boolean match(LocalVariableTypeTable_attribute.Entry entry, int i);
    }

    public LocalVariableTypeTableWriter(Context context) {
        super(context);
        context.put(LocalVariableTypeTableWriter.class, this);
        this.classWriter = ClassWriter.instance(context);
    }

    public static LocalVariableTypeTableWriter instance(Context context) {
        LocalVariableTypeTableWriter localVariableTypeTableWriter = (LocalVariableTypeTableWriter) context.get(LocalVariableTypeTableWriter.class);
        return localVariableTypeTableWriter == null ? new LocalVariableTypeTableWriter(context) : localVariableTypeTableWriter;
    }

    private void put(int i, LocalVariableTypeTable_attribute.Entry entry) {
        List<LocalVariableTypeTable_attribute.Entry> arrayList = this.pcMap.get(Integer.valueOf(i));
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.pcMap.put(Integer.valueOf(i), arrayList);
        }
        if (arrayList.contains(entry)) {
            return;
        }
        arrayList.add(entry);
    }

    @Override // com.sun.tools.javap.InstructionDetailWriter
    public void flush() {
        writeLocalVariables(this.codeAttr.code_length, NoteKind.END);
    }

    public void reset(Code_attribute code_attribute) {
        this.codeAttr = code_attribute;
        this.pcMap = new HashMap();
        LocalVariableTypeTable_attribute localVariableTypeTable_attribute = (LocalVariableTypeTable_attribute) code_attribute.attributes.get(Attribute.LocalVariableTypeTable);
        if (localVariableTypeTable_attribute == null) {
            return;
        }
        int i = 0;
        while (true) {
            LocalVariableTypeTable_attribute.Entry[] entryArr = localVariableTypeTable_attribute.local_variable_table;
            if (i >= entryArr.length) {
                return;
            }
            LocalVariableTypeTable_attribute.Entry entry = entryArr[i];
            put(entry.start_pc, entry);
            put(entry.start_pc + entry.length, entry);
            i++;
        }
    }

    @Override // com.sun.tools.javap.InstructionDetailWriter
    public void writeDetails(Instruction instruction) {
        int pc = instruction.getPC();
        writeLocalVariables(pc, NoteKind.END);
        writeLocalVariables(pc, NoteKind.START);
    }

    public void writeLocalVariables(int i, NoteKind noteKind) {
        ConstantPool constantPool = this.classWriter.getClassFile().constant_pool;
        String strSpace = space(2);
        List<LocalVariableTypeTable_attribute.Entry> list = this.pcMap.get(Integer.valueOf(i));
        if (list == null) {
            return;
        }
        ListIterator<LocalVariableTypeTable_attribute.Entry> listIterator = list.listIterator(noteKind == NoteKind.END ? list.size() : 0);
        while (true) {
            NoteKind noteKind2 = NoteKind.END;
            if (noteKind == noteKind2) {
                if (!listIterator.hasPrevious()) {
                    return;
                }
            } else if (!listIterator.hasNext()) {
                return;
            }
            LocalVariableTypeTable_attribute.Entry entryPrevious = noteKind == noteKind2 ? listIterator.previous() : listIterator.next();
            if (noteKind.match(entryPrevious, i)) {
                print(strSpace);
                print(noteKind.text);
                print(" generic local ");
                print(Integer.valueOf(entryPrevious.index));
                print(" // ");
                try {
                    print(new Signature(entryPrevious.signature_index).getFieldType(constantPool).replace(PsuedoNames.PSEUDONAME_ROOT, Constants.ATTRVAL_THIS));
                } catch (ConstantPoolException e) {
                    print(report(e));
                } catch (Descriptor.InvalidDescriptor e2) {
                    print(report(e2));
                }
                print(" ");
                try {
                    print(constantPool.getUTF8Value(entryPrevious.name_index));
                } catch (ConstantPoolException e3) {
                    print(report(e3));
                }
                println();
            }
        }
    }
}

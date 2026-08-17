package com.sun.tools.javap;

import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Instruction;
import com.sun.tools.classfile.LocalVariableTable_attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocalVariableTableWriter extends InstructionDetailWriter {
    private ClassWriter classWriter;
    private Code_attribute codeAttr;
    private Map<Integer, List<LocalVariableTable_attribute.Entry>> pcMap;

    public enum NoteKind {
        START("start") { // from class: com.sun.tools.javap.LocalVariableTableWriter.NoteKind.1
            @Override // com.sun.tools.javap.LocalVariableTableWriter.NoteKind
            public boolean match(LocalVariableTable_attribute.Entry entry, int i) {
                return i == entry.start_pc;
            }
        },
        END("end") { // from class: com.sun.tools.javap.LocalVariableTableWriter.NoteKind.2
            @Override // com.sun.tools.javap.LocalVariableTableWriter.NoteKind
            public boolean match(LocalVariableTable_attribute.Entry entry, int i) {
                return i == entry.start_pc + entry.length;
            }
        };

        public final String text;

        NoteKind(String str) {
            this.text = str;
        }

        public abstract boolean match(LocalVariableTable_attribute.Entry entry, int i);
    }

    public LocalVariableTableWriter(Context context) {
        super(context);
        context.put(LocalVariableTableWriter.class, this);
        this.classWriter = ClassWriter.instance(context);
    }

    public static LocalVariableTableWriter instance(Context context) {
        LocalVariableTableWriter localVariableTableWriter = (LocalVariableTableWriter) context.get(LocalVariableTableWriter.class);
        return localVariableTableWriter == null ? new LocalVariableTableWriter(context) : localVariableTableWriter;
    }

    private void put(int i, LocalVariableTable_attribute.Entry entry) {
        List<LocalVariableTable_attribute.Entry> arrayList = this.pcMap.get(Integer.valueOf(i));
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
        LocalVariableTable_attribute localVariableTable_attribute = (LocalVariableTable_attribute) code_attribute.attributes.get(Attribute.LocalVariableTable);
        if (localVariableTable_attribute == null) {
            return;
        }
        int i = 0;
        while (true) {
            LocalVariableTable_attribute.Entry[] entryArr = localVariableTable_attribute.local_variable_table;
            if (i >= entryArr.length) {
                return;
            }
            LocalVariableTable_attribute.Entry entry = entryArr[i];
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
        List<LocalVariableTable_attribute.Entry> list = this.pcMap.get(Integer.valueOf(i));
        if (list == null) {
            return;
        }
        ListIterator<LocalVariableTable_attribute.Entry> listIterator = list.listIterator(noteKind == NoteKind.END ? list.size() : 0);
        while (true) {
            NoteKind noteKind2 = NoteKind.END;
            if (noteKind == noteKind2) {
                if (!listIterator.hasPrevious()) {
                    return;
                }
            } else if (!listIterator.hasNext()) {
                return;
            }
            LocalVariableTable_attribute.Entry entryPrevious = noteKind == noteKind2 ? listIterator.previous() : listIterator.next();
            if (noteKind.match(entryPrevious, i)) {
                print(strSpace);
                print(noteKind.text);
                print(" local ");
                print(Integer.valueOf(entryPrevious.index));
                print(" // ");
                try {
                    print(new Descriptor(entryPrevious.descriptor_index).getFieldType(constantPool));
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

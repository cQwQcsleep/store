package com.sun.tools.javap;

import com.intellij.psi.PsiKeyword;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.Instruction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TryBlockWriter extends InstructionDetailWriter {
    private ConstantWriter constantWriter;
    private Map<Code_attribute.Exception_data, Integer> indexMap;
    private Map<Integer, List<Code_attribute.Exception_data>> pcMap;

    public enum NoteKind {
        START(PsiKeyword.TRY) { // from class: com.sun.tools.javap.TryBlockWriter.NoteKind.1
            @Override // com.sun.tools.javap.TryBlockWriter.NoteKind
            public boolean match(Code_attribute.Exception_data exception_data, int i) {
                return i == exception_data.start_pc;
            }
        },
        END("end try") { // from class: com.sun.tools.javap.TryBlockWriter.NoteKind.2
            @Override // com.sun.tools.javap.TryBlockWriter.NoteKind
            public boolean match(Code_attribute.Exception_data exception_data, int i) {
                return i == exception_data.end_pc;
            }
        },
        HANDLER(PsiKeyword.CATCH) { // from class: com.sun.tools.javap.TryBlockWriter.NoteKind.3
            @Override // com.sun.tools.javap.TryBlockWriter.NoteKind
            public boolean match(Code_attribute.Exception_data exception_data, int i) {
                return i == exception_data.handler_pc;
            }
        };

        public final String text;

        NoteKind(String str) {
            this.text = str;
        }

        public abstract boolean match(Code_attribute.Exception_data exception_data, int i);
    }

    public TryBlockWriter(Context context) {
        super(context);
        context.put(TryBlockWriter.class, this);
        this.constantWriter = ConstantWriter.instance(context);
    }

    public static TryBlockWriter instance(Context context) {
        TryBlockWriter tryBlockWriter = (TryBlockWriter) context.get(TryBlockWriter.class);
        return tryBlockWriter == null ? new TryBlockWriter(context) : tryBlockWriter;
    }

    private void put(int i, Code_attribute.Exception_data exception_data) {
        List<Code_attribute.Exception_data> arrayList = this.pcMap.get(Integer.valueOf(i));
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.pcMap.put(Integer.valueOf(i), arrayList);
        }
        if (arrayList.contains(exception_data)) {
            return;
        }
        arrayList.add(exception_data);
    }

    public void reset(Code_attribute code_attribute) {
        this.indexMap = new HashMap();
        this.pcMap = new HashMap();
        int i = 0;
        while (true) {
            Code_attribute.Exception_data[] exception_dataArr = code_attribute.exception_table;
            if (i >= exception_dataArr.length) {
                return;
            }
            Code_attribute.Exception_data exception_data = exception_dataArr[i];
            this.indexMap.put(exception_data, Integer.valueOf(i));
            put(exception_data.start_pc, exception_data);
            put(exception_data.end_pc, exception_data);
            put(exception_data.handler_pc, exception_data);
            i++;
        }
    }

    @Override // com.sun.tools.javap.InstructionDetailWriter
    public void writeDetails(Instruction instruction) {
        writeTrys(instruction, NoteKind.END);
        writeTrys(instruction, NoteKind.START);
        writeTrys(instruction, NoteKind.HANDLER);
    }

    public void writeTrys(Instruction instruction, NoteKind noteKind) {
        String strSpace = space(2);
        int pc = instruction.getPC();
        List<Code_attribute.Exception_data> list = this.pcMap.get(Integer.valueOf(pc));
        if (list == null) {
            return;
        }
        ListIterator<Code_attribute.Exception_data> listIterator = list.listIterator(noteKind == NoteKind.END ? list.size() : 0);
        while (true) {
            NoteKind noteKind2 = NoteKind.END;
            if (noteKind == noteKind2) {
                if (!listIterator.hasPrevious()) {
                    return;
                }
            } else if (!listIterator.hasNext()) {
                return;
            }
            Code_attribute.Exception_data exception_dataPrevious = noteKind == noteKind2 ? listIterator.previous() : listIterator.next();
            if (noteKind.match(exception_dataPrevious, pc)) {
                print(strSpace);
                print(noteKind.text);
                print("[");
                print(this.indexMap.get(exception_dataPrevious));
                print("] ");
                if (exception_dataPrevious.catch_type == 0) {
                    print(PsiKeyword.FINALLY);
                } else {
                    print("#" + exception_dataPrevious.catch_type);
                    print(" // ");
                    this.constantWriter.write(exception_dataPrevious.catch_type);
                }
                println();
            }
        }
    }
}

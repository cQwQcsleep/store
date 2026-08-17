package com.sun.tools.javap;

import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.Instruction;
import com.sun.tools.classfile.Method;
import com.sun.tools.classfile.RuntimeInvisibleTypeAnnotations_attribute;
import com.sun.tools.classfile.RuntimeTypeAnnotations_attribute;
import com.sun.tools.classfile.RuntimeVisibleTypeAnnotations_attribute;
import com.sun.tools.classfile.TypeAnnotation;
import com.sun.tools.javac.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeAnnotationWriter extends InstructionDetailWriter {
    private AnnotationWriter annotationWriter;
    private ClassWriter classWriter;
    private Map<Integer, List<Note>> pcMap;

    public static class Note {
        public final TypeAnnotation anno;
        public final NoteKind kind;

        public Note(NoteKind noteKind, TypeAnnotation typeAnnotation) {
            this.kind = noteKind;
            this.anno = typeAnnotation;
        }
    }

    public enum NoteKind {
        VISIBLE,
        INVISIBLE
    }

    public TypeAnnotationWriter(Context context) {
        super(context);
        context.put(TypeAnnotationWriter.class, this);
        this.annotationWriter = AnnotationWriter.instance(context);
        this.classWriter = ClassWriter.instance(context);
    }

    private void addNote(int i, Note note) {
        List<Note> arrayList = this.pcMap.get(Integer.valueOf(i));
        if (arrayList == null) {
            Map<Integer, List<Note>> map = this.pcMap;
            Integer numValueOf = Integer.valueOf(i);
            arrayList = new ArrayList<>();
            map.put(numValueOf, arrayList);
        }
        arrayList.add(note);
    }

    private void check(NoteKind noteKind, RuntimeTypeAnnotations_attribute runtimeTypeAnnotations_attribute) {
        Note note;
        if (runtimeTypeAnnotations_attribute == null) {
            return;
        }
        for (TypeAnnotation typeAnnotation : runtimeTypeAnnotations_attribute.annotations) {
            TypeAnnotation.Position position = typeAnnotation.position;
            int i = position.offset;
            if (i != -1) {
                note = new Note(noteKind, typeAnnotation);
                addNote(i, note);
            } else {
                note = null;
            }
            if (position.lvarOffset != null) {
                for (int i2 = 0; i2 < position.lvarOffset.length; i2++) {
                    if (note == null) {
                        note = new Note(noteKind, typeAnnotation);
                    }
                    addNote(position.lvarOffset[i2], note);
                }
            }
        }
    }

    public static TypeAnnotationWriter instance(Context context) {
        TypeAnnotationWriter typeAnnotationWriter = (TypeAnnotationWriter) context.get(TypeAnnotationWriter.class);
        return typeAnnotationWriter == null ? new TypeAnnotationWriter(context) : typeAnnotationWriter;
    }

    public void reset(Code_attribute code_attribute) {
        Method method = this.classWriter.getMethod();
        this.pcMap = new HashMap();
        check(NoteKind.VISIBLE, (RuntimeVisibleTypeAnnotations_attribute) method.attributes.get(Attribute.RuntimeVisibleTypeAnnotations));
        check(NoteKind.INVISIBLE, (RuntimeInvisibleTypeAnnotations_attribute) method.attributes.get(Attribute.RuntimeInvisibleTypeAnnotations));
    }

    @Override // com.sun.tools.javap.InstructionDetailWriter
    public void writeDetails(Instruction instruction) {
        String strSpace = space(2);
        List<Note> list = this.pcMap.get(Integer.valueOf(instruction.getPC()));
        if (list != null) {
            for (Note note : list) {
                print(strSpace);
                print("@");
                this.annotationWriter.write(note.anno, false, true);
                print(", ");
                println(StringUtils.toLowerCase(note.kind.toString()));
            }
        }
    }
}

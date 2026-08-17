package com.sun.tools.javap;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.ClassFile;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Instruction;
import com.sun.tools.classfile.LineNumberTable_attribute;
import com.sun.tools.classfile.SourceFile_attribute;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SourceWriter extends InstructionDetailWriter {
    private ClassFile classFile;
    private JavaFileManager fileManager;
    private List<Integer> lineList;
    private SortedMap<Integer, SortedSet<Integer>> lineMap;
    private String[] sourceLines;

    public SourceWriter(Context context) {
        super(context);
        context.put(SourceWriter.class, this);
    }

    public static SourceWriter instance(Context context) {
        SourceWriter sourceWriter = (SourceWriter) context.get(SourceWriter.class);
        return sourceWriter == null ? new SourceWriter(context) : sourceWriter;
    }

    private int nextLine(int i) {
        int iIndexOf = this.lineList.indexOf(Integer.valueOf(i));
        if (iIndexOf == -1 || iIndexOf == this.lineList.size() - 1) {
            return -1;
        }
        return this.lineList.get(iIndexOf + 1).intValue();
    }

    private String readSource(ClassFile classFile) {
        JavaFileManager javaFileManager = this.fileManager;
        if (javaFileManager == null) {
            return null;
        }
        StandardLocation standardLocation = StandardLocation.SOURCE_PATH;
        if (!javaFileManager.hasLocation(standardLocation)) {
            standardLocation = StandardLocation.CLASS_PATH;
        }
        try {
            String name = classFile.getName();
            SourceFile_attribute sourceFile_attribute = (SourceFile_attribute) classFile.attributes.get(Attribute.SourceFile);
            if (sourceFile_attribute == null) {
                report(this.messages.getMessage("err.no.SourceFile.attribute", new Object[0]));
                return null;
            }
            String sourceFile = sourceFile_attribute.getSourceFile(classFile.constant_pool);
            if (sourceFile.endsWith(".java")) {
                sourceFile = sourceFile.substring(0, sourceFile.length() - 5);
            }
            int iLastIndexOf = name.lastIndexOf(PsuedoNames.PSEUDONAME_ROOT);
            JavaFileObject javaFileForInput = this.fileManager.getJavaFileForInput(standardLocation, (iLastIndexOf == -1 ? "" : name.substring(0, iLastIndexOf + 1)).concat(sourceFile).replace('/', '.'), JavaFileObject.Kind.SOURCE);
            if (javaFileForInput != null) {
                return javaFileForInput.getCharContent(true).toString();
            }
            report(this.messages.getMessage("err.source.file.not.found", new Object[0]));
            return null;
        } catch (ConstantPoolException e) {
            report(e);
            return null;
        } catch (IOException e2) {
            report(e2.getLocalizedMessage());
            return null;
        }
    }

    private void setLineMap(Code_attribute code_attribute) {
        TreeMap treeMap = new TreeMap();
        TreeSet treeSet = new TreeSet();
        for (Attribute attribute : code_attribute.attributes) {
            if (attribute instanceof LineNumberTable_attribute) {
                for (LineNumberTable_attribute.Entry entry : ((LineNumberTable_attribute) attribute).line_number_table) {
                    int i = entry.start_pc;
                    int i2 = entry.line_number;
                    Set treeSet2 = (SortedSet) treeMap.get(Integer.valueOf(i));
                    if (treeSet2 == null) {
                        treeSet2 = new TreeSet();
                        treeMap.put(Integer.valueOf(i), treeSet2);
                    }
                    treeSet2.add(Integer.valueOf(i2));
                    treeSet.add(Integer.valueOf(i2));
                }
            }
        }
        this.lineMap = treeMap;
        this.lineList = new ArrayList(treeSet);
    }

    private void setSource(ClassFile classFile) {
        if (classFile != this.classFile) {
            this.classFile = classFile;
            this.sourceLines = splitLines(readSource(classFile));
        }
    }

    private static String[] splitLines(String str) {
        if (str == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("");
        try {
            BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                arrayList.add(line);
            }
        } catch (IOException unused) {
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public boolean hasSource() {
        return this.sourceLines.length > 0;
    }

    public void reset(ClassFile classFile, Code_attribute code_attribute) {
        setSource(classFile);
        setLineMap(code_attribute);
    }

    public void setFileManager(JavaFileManager javaFileManager) {
        this.fileManager = javaFileManager;
    }

    @Override // com.sun.tools.javap.InstructionDetailWriter
    public void writeDetails(Instruction instruction) {
        String strSpace = space(40);
        SortedSet<Integer> sortedSet = this.lineMap.get(Integer.valueOf(instruction.getPC()));
        if (sortedSet != null) {
            for (Integer num : sortedSet) {
                int iIntValue = num.intValue();
                print(strSpace);
                print(String.format(" %4d ", num));
                String[] strArr = this.sourceLines;
                if (iIntValue < strArr.length) {
                    print(strArr[iIntValue]);
                }
                println();
                int iNextLine = nextLine(iIntValue);
                while (true) {
                    iIntValue++;
                    if (iIntValue < iNextLine) {
                        print(strSpace);
                        print(String.format("(%4d)", Integer.valueOf(iIntValue)));
                        String[] strArr2 = this.sourceLines;
                        if (iIntValue < strArr2.length) {
                            print(strArr2[iIntValue]);
                        }
                        println();
                    }
                }
            }
        }
    }
}

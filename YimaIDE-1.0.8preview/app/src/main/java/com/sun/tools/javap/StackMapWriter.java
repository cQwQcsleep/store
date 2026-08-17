package com.sun.tools.javap;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.tools.classfile.Attribute;
import com.sun.tools.classfile.Code_attribute;
import com.sun.tools.classfile.ConstantPool;
import com.sun.tools.classfile.ConstantPoolException;
import com.sun.tools.classfile.Descriptor;
import com.sun.tools.classfile.Instruction;
import com.sun.tools.classfile.Method;
import com.sun.tools.classfile.StackMapTable_attribute;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StackMapWriter extends InstructionDetailWriter {
    private ClassWriter classWriter;
    private final StackMapTable_attribute.verification_type_info[] empty;
    private Map<Integer, StackMap> map;

    public static class CustomVerificationTypeInfo extends StackMapTable_attribute.verification_type_info {
        private String text;

        public CustomVerificationTypeInfo(String str) {
            super(-1);
            this.text = str;
        }
    }

    public static class StackMap {
        private final StackMapTable_attribute.verification_type_info[] locals;
        private final StackMapTable_attribute.verification_type_info[] stack;

        public StackMap(StackMapTable_attribute.verification_type_info[] verification_type_infoVarArr, StackMapTable_attribute.verification_type_info[] verification_type_infoVarArr2) {
            this.locals = verification_type_infoVarArr;
            this.stack = verification_type_infoVarArr2;
        }
    }

    public class StackMapBuilder implements StackMapTable_attribute.stack_map_frame.Visitor<Integer, Integer> {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        public StackMapBuilder() {
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Integer visit_append_frame(StackMapTable_attribute.append_frame append_frameVar, Integer num) {
            int iIntValue = num.intValue() + append_frameVar.getOffsetDelta() + 1;
            StackMap stackMap = (StackMap) StackMapWriter.this.map.get(num);
            StackMapTable_attribute.verification_type_info[] verification_type_infoVarArr = new StackMapTable_attribute.verification_type_info[stackMap.locals.length + append_frameVar.locals.length];
            System.arraycopy(stackMap.locals, 0, verification_type_infoVarArr, 0, stackMap.locals.length);
            System.arraycopy(append_frameVar.locals, 0, verification_type_infoVarArr, stackMap.locals.length, append_frameVar.locals.length);
            StackMapWriter.this.map.put(Integer.valueOf(iIntValue), new StackMap(verification_type_infoVarArr, StackMapWriter.this.empty));
            return Integer.valueOf(iIntValue);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Integer visit_chop_frame(StackMapTable_attribute.chop_frame chop_frameVar, Integer num) {
            int iIntValue = num.intValue() + chop_frameVar.getOffsetDelta() + 1;
            StackMap stackMap = (StackMap) StackMapWriter.this.map.get(num);
            StackMapWriter.this.map.put(Integer.valueOf(iIntValue), new StackMap((StackMapTable_attribute.verification_type_info[]) Arrays.copyOf(stackMap.locals, stackMap.locals.length - (251 - chop_frameVar.frame_type)), StackMapWriter.this.empty));
            return Integer.valueOf(iIntValue);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Integer visit_full_frame(StackMapTable_attribute.full_frame full_frameVar, Integer num) {
            int iIntValue = num.intValue() + full_frameVar.getOffsetDelta() + 1;
            StackMapWriter.this.map.put(Integer.valueOf(iIntValue), new StackMap(full_frameVar.locals, full_frameVar.stack));
            return Integer.valueOf(iIntValue);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Integer visit_same_frame(StackMapTable_attribute.same_frame same_frameVar, Integer num) {
            int iIntValue = num.intValue() + same_frameVar.getOffsetDelta() + 1;
            StackMapWriter.this.map.put(Integer.valueOf(iIntValue), (StackMap) StackMapWriter.this.map.get(num));
            return Integer.valueOf(iIntValue);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Integer visit_same_frame_extended(StackMapTable_attribute.same_frame_extended same_frame_extendedVar, Integer num) {
            int iIntValue = num.intValue() + same_frame_extendedVar.getOffsetDelta();
            StackMapWriter.this.map.put(Integer.valueOf(iIntValue), (StackMap) StackMapWriter.this.map.get(num));
            return Integer.valueOf(iIntValue);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Integer visit_same_locals_1_stack_item_frame(StackMapTable_attribute.same_locals_1_stack_item_frame same_locals_1_stack_item_frameVar, Integer num) {
            int iIntValue = num.intValue() + same_locals_1_stack_item_frameVar.getOffsetDelta() + 1;
            StackMapWriter.this.map.put(Integer.valueOf(iIntValue), new StackMap(((StackMap) StackMapWriter.this.map.get(num)).locals, same_locals_1_stack_item_frameVar.stack));
            return Integer.valueOf(iIntValue);
        }

        @Override // com.sun.tools.classfile.StackMapTable_attribute.stack_map_frame.Visitor
        public Integer visit_same_locals_1_stack_item_frame_extended(StackMapTable_attribute.same_locals_1_stack_item_frame_extended same_locals_1_stack_item_frame_extendedVar, Integer num) {
            int iIntValue = num.intValue() + same_locals_1_stack_item_frame_extendedVar.getOffsetDelta() + 1;
            StackMapWriter.this.map.put(Integer.valueOf(iIntValue), new StackMap(((StackMap) StackMapWriter.this.map.get(num)).locals, same_locals_1_stack_item_frame_extendedVar.stack));
            return Integer.valueOf(iIntValue);
        }
    }

    public StackMapWriter(Context context) {
        super(context);
        this.empty = new StackMapTable_attribute.verification_type_info[0];
        context.put(StackMapWriter.class, this);
        this.classWriter = ClassWriter.instance(context);
    }

    public static StackMapWriter instance(Context context) {
        StackMapWriter stackMapWriter = (StackMapWriter) context.get(StackMapWriter.class);
        return stackMapWriter == null ? new StackMapWriter(context) : stackMapWriter;
    }

    private void writeDetails(int i) {
        StackMap stackMap;
        Map<Integer, StackMap> map = this.map;
        if (map == null || (stackMap = map.get(Integer.valueOf(i))) == null) {
            return;
        }
        print("StackMap locals: ", stackMap.locals);
        print("StackMap stack: ", stackMap.stack);
    }

    public void print(StackMapTable_attribute.verification_type_info verification_type_infoVar) {
        if (verification_type_infoVar == null) {
            print("ERROR");
        }
        switch (verification_type_infoVar.tag) {
            case -1:
                print(((CustomVerificationTypeInfo) verification_type_infoVar).text);
                break;
            case 0:
                print("top");
                break;
            case 1:
                print("int");
                break;
            case 2:
                print("float");
                break;
            case 3:
                print("double");
                break;
            case 4:
                print("long");
                break;
            case 5:
                print(PsiKeyword.NULL);
                break;
            case 6:
                print("uninit_this");
                break;
            case 7:
                try {
                    ConstantPool constantPool = this.classWriter.getClassFile().constant_pool;
                    print(constantPool.getUTF8Value(constantPool.getClassInfo(((StackMapTable_attribute.Object_variable_info) verification_type_infoVar).cpool_index).name_index));
                } catch (ConstantPoolException unused) {
                    print("??");
                    return;
                }
                break;
            case 8:
                print(Integer.valueOf(((StackMapTable_attribute.Uninitialized_variable_info) verification_type_infoVar).offset));
                break;
        }
    }

    public void reset(Code_attribute code_attribute) {
        setStackMap((StackMapTable_attribute) code_attribute.attributes.get(Attribute.StackMapTable));
    }

    public void setStackMap(StackMapTable_attribute stackMapTable_attribute) {
        if (stackMapTable_attribute == null) {
            this.map = null;
            return;
        }
        Method method = this.classWriter.getMethod();
        try {
            String parameterTypes = method.descriptor.getParameterTypes(this.classWriter.getClassFile().constant_pool);
            String[] strArrSplit = parameterTypes.substring(1, parameterTypes.length() - 1).split("[, ]+");
            boolean zIs = method.access_flags.is(8);
            StackMapTable_attribute.verification_type_info[] verification_type_infoVarArr = new StackMapTable_attribute.verification_type_info[(!zIs ? 1 : 0) + strArrSplit.length];
            int i = 0;
            if (!zIs) {
                verification_type_infoVarArr[0] = new CustomVerificationTypeInfo(PsiKeyword.THIS);
            }
            for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                verification_type_infoVarArr[(!zIs ? 1 : 0) + i2] = new CustomVerificationTypeInfo(strArrSplit[i2].replace(Constants.ATTRVAL_THIS, PsuedoNames.PSEUDONAME_ROOT));
            }
            this.map = new HashMap();
            StackMapBuilder stackMapBuilder = new StackMapBuilder();
            int iIntValue = -1;
            this.map.put(-1, new StackMap(verification_type_infoVarArr, this.empty));
            while (true) {
                StackMapTable_attribute.stack_map_frame[] stack_map_frameVarArr = stackMapTable_attribute.entries;
                if (i >= stack_map_frameVarArr.length) {
                    return;
                }
                iIntValue = ((Integer) stack_map_frameVarArr[i].accept(stackMapBuilder, Integer.valueOf(iIntValue))).intValue();
                i++;
            }
        } catch (ConstantPoolException | Descriptor.InvalidDescriptor unused) {
        }
    }

    public void writeInitialDetails() {
        writeDetails(-1);
    }

    @Override // com.sun.tools.javap.InstructionDetailWriter
    public void writeDetails(Instruction instruction) {
        writeDetails(instruction.getPC());
    }

    public void print(String str, StackMapTable_attribute.verification_type_info[] verification_type_infoVarArr) {
        print(str);
        for (StackMapTable_attribute.verification_type_info verification_type_infoVar : verification_type_infoVarArr) {
            print(" ");
            print(verification_type_infoVar);
        }
        println();
    }
}

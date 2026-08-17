package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import defpackage.yz0;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InstructionHandle {
    public static final InstructionHandle[] EMPTY_ARRAY = new InstructionHandle[0];
    static final InstructionTargeter[] EMPTY_INSTRUCTION_TARGETER_ARRAY = new InstructionTargeter[0];
    private Map<Object, Object> attributes;

    @Deprecated
    protected int i_position = -1;
    private Instruction instruction;
    private InstructionHandle next;
    private InstructionHandle prev;
    private Set<InstructionTargeter> targeters;

    public InstructionHandle(Instruction instruction) {
        setInstruction(instruction);
    }

    public static InstructionHandle getInstructionHandle(Instruction instruction) {
        return new InstructionHandle(instruction);
    }

    public void accept(Visitor visitor) {
        this.instruction.accept(visitor);
    }

    public void addAttribute(Object obj, Object obj2) {
        if (this.attributes == null) {
            this.attributes = new HashMap(3);
        }
        this.attributes.put(obj, obj2);
    }

    @Deprecated
    public void addHandle() {
    }

    public void addTargeter(InstructionTargeter instructionTargeter) {
        if (this.targeters == null) {
            this.targeters = new HashSet();
        }
        this.targeters.add(instructionTargeter);
    }

    public void dispose() {
        this.prev = null;
        this.next = null;
        this.instruction.dispose();
        this.instruction = null;
        this.i_position = -1;
        this.attributes = null;
        removeAllTargeters();
    }

    public Object getAttribute(Object obj) {
        Map<Object, Object> map = this.attributes;
        if (map != null) {
            return map.get(obj);
        }
        return null;
    }

    public Collection<Object> getAttributes() {
        if (this.attributes == null) {
            this.attributes = new HashMap(3);
        }
        return this.attributes.values();
    }

    public final Instruction getInstruction() {
        return this.instruction;
    }

    public final InstructionHandle getNext() {
        return this.next;
    }

    public int getPosition() {
        return this.i_position;
    }

    public final InstructionHandle getPrev() {
        return this.prev;
    }

    public InstructionTargeter[] getTargeters() {
        if (!hasTargeters()) {
            return EMPTY_INSTRUCTION_TARGETER_ARRAY;
        }
        InstructionTargeter[] instructionTargeterArr = new InstructionTargeter[this.targeters.size()];
        this.targeters.toArray(instructionTargeterArr);
        return instructionTargeterArr;
    }

    public boolean hasTargeters() {
        Set<InstructionTargeter> set = this.targeters;
        return (set == null || set.isEmpty()) ? false : true;
    }

    public void removeAllTargeters() {
        Set<InstructionTargeter> set = this.targeters;
        if (set != null) {
            set.clear();
        }
    }

    public void removeAttribute(Object obj) {
        Map<Object, Object> map = this.attributes;
        if (map != null) {
            map.remove(obj);
        }
    }

    public void removeTargeter(InstructionTargeter instructionTargeter) {
        Set<InstructionTargeter> set = this.targeters;
        if (set != null) {
            set.remove(instructionTargeter);
        }
    }

    public void setInstruction(Instruction instruction) {
        if (instruction == null) {
            throw new ClassGenException("Assigning null to handle");
        }
        if (getClass() != BranchHandle.class && (instruction instanceof BranchInstruction)) {
            yz0.a("Assigning branch instruction ", instruction, " to plain handle");
            return;
        }
        Instruction instruction2 = this.instruction;
        if (instruction2 != null) {
            instruction2.dispose();
        }
        this.instruction = instruction;
    }

    public final InstructionHandle setNext(InstructionHandle instructionHandle) {
        this.next = instructionHandle;
        return instructionHandle;
    }

    public void setPosition(int i) {
        this.i_position = i;
    }

    public final InstructionHandle setPrev(InstructionHandle instructionHandle) {
        this.prev = instructionHandle;
        return instructionHandle;
    }

    public Instruction swapInstruction(Instruction instruction) {
        Instruction instruction2 = this.instruction;
        this.instruction = instruction;
        return instruction2;
    }

    public String toString(boolean z) {
        return Utility.format(this.i_position, 4, false, ' ') + ": " + this.instruction.toString(z);
    }

    public int updatePosition(int i, int i2) {
        this.i_position += i;
        return 0;
    }

    public String toString() {
        return toString(true);
    }
}

package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.ByteSequence;
import defpackage.fr6;
import defpackage.iva;
import defpackage.lo4;
import defpackage.yz0;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InstructionList implements Iterable<InstructionHandle> {
    private int[] bytePositions;
    private InstructionHandle end;
    private int length;
    private List<InstructionListObserver> observers;
    private InstructionHandle start;

    public InstructionList(byte[] bArr) {
        try {
            ByteSequence byteSequence = new ByteSequence(bArr);
            try {
                InstructionHandle[] instructionHandleArr = new InstructionHandle[bArr.length];
                int[] iArr = new int[bArr.length];
                int i = 0;
                while (byteSequence.available() > 0) {
                    int index = byteSequence.getIndex();
                    iArr[i] = index;
                    Instruction instruction = Instruction.readInstruction(byteSequence);
                    InstructionHandle instructionHandleAppend = instruction instanceof BranchInstruction ? append((BranchInstruction) instruction) : append(instruction);
                    instructionHandleAppend.setPosition(index);
                    instructionHandleArr[i] = instructionHandleAppend;
                    i++;
                }
                byteSequence.close();
                this.bytePositions = Arrays.copyOf(iArr, i);
                for (int i2 = 0; i2 < i; i2++) {
                    InstructionHandle instructionHandle = instructionHandleArr[i2];
                    if (instructionHandle instanceof BranchHandle) {
                        BranchInstruction branchInstruction = (BranchInstruction) instructionHandle.getInstruction();
                        InstructionHandle instructionHandleFindHandle = findHandle(instructionHandleArr, iArr, i, branchInstruction.getPosition() + branchInstruction.getIndex());
                        if (instructionHandleFindHandle == null) {
                            iva.a("Couldn't find target for branch: ", branchInstruction);
                            throw null;
                        }
                        branchInstruction.setTarget(instructionHandleFindHandle);
                        if (branchInstruction instanceof Select) {
                            Select select = (Select) branchInstruction;
                            int[] indices = select.getIndices();
                            for (int i3 = 0; i3 < indices.length; i3++) {
                                InstructionHandle instructionHandleFindHandle2 = findHandle(instructionHandleArr, iArr, i, branchInstruction.getPosition() + indices[i3]);
                                if (instructionHandleFindHandle2 == null) {
                                    iva.a("Couldn't find target for switch: ", branchInstruction);
                                    throw null;
                                }
                                select.setTarget(i3, instructionHandleFindHandle2);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            } catch (Throwable th) {
                try {
                    byteSequence.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            throw new ClassGenException(e.toString(), e);
        }
    }

    private void clear() {
        this.end = null;
        this.start = null;
        this.length = 0;
    }

    public static InstructionHandle findHandle(InstructionHandle[] instructionHandleArr, int[] iArr, int i, int i2) {
        int i3 = i - 1;
        int i4 = 0;
        do {
            int i5 = (i4 + i3) >>> 1;
            int i6 = iArr[i5];
            if (i6 == i2) {
                return instructionHandleArr[i5];
            }
            if (i2 < i6) {
                i3 = i5 - 1;
            } else {
                i4 = i5 + 1;
            }
        } while (i4 <= i3);
        return null;
    }

    private InstructionHandle findInstruction1(Instruction instruction) {
        for (InstructionHandle next = this.start; next != null; next = next.getNext()) {
            if (next.getInstruction() == instruction) {
                return next;
            }
        }
        return null;
    }

    private InstructionHandle findInstruction2(Instruction instruction) {
        for (InstructionHandle prev = this.end; prev != null; prev = prev.getPrev()) {
            if (prev.getInstruction() == instruction) {
                return prev;
            }
        }
        return null;
    }

    private void remove(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) throws TargetLostException {
        InstructionHandle next;
        InstructionHandle instructionHandle3;
        InstructionHandle instructionHandle4;
        if (instructionHandle == null && instructionHandle2 == null) {
            instructionHandle3 = this.start;
            instructionHandle4 = this.end;
            this.end = null;
            this.start = null;
        } else {
            if (instructionHandle == null) {
                next = this.start;
                this.start = instructionHandle2;
            } else {
                next = instructionHandle.getNext();
                instructionHandle.setNext(instructionHandle2);
            }
            if (instructionHandle2 == null) {
                instructionHandle4 = this.end;
                this.end = instructionHandle;
                instructionHandle3 = next;
            } else {
                InstructionHandle prev = instructionHandle2.getPrev();
                instructionHandle2.setPrev(instructionHandle);
                instructionHandle3 = next;
                instructionHandle4 = prev;
            }
        }
        instructionHandle3.setPrev(null);
        instructionHandle4.setNext(null);
        ArrayList arrayList = new ArrayList();
        for (InstructionHandle next2 = instructionHandle3; next2 != null; next2 = next2.getNext()) {
            next2.getInstruction().dispose();
        }
        StringBuilder sb = new StringBuilder("{ ");
        while (instructionHandle3 != null) {
            InstructionHandle next3 = instructionHandle3.getNext();
            this.length--;
            if (instructionHandle3.hasTargeters()) {
                arrayList.add(instructionHandle3);
                sb.append(instructionHandle3.toString(true));
                sb.append(" ");
                instructionHandle3.setNext(instructionHandle3.setPrev(null));
            } else {
                instructionHandle3.dispose();
            }
            instructionHandle3 = next3;
        }
        sb.append("}");
        if (!arrayList.isEmpty()) {
            throw new TargetLostException((InstructionHandle[]) arrayList.toArray(InstructionHandle.EMPTY_ARRAY), sb.toString());
        }
    }

    public void addObserver(InstructionListObserver instructionListObserver) {
        if (this.observers == null) {
            this.observers = new ArrayList();
        }
        this.observers.add(instructionListObserver);
    }

    public InstructionHandle append(InstructionHandle instructionHandle, InstructionList instructionList) {
        if (instructionList == null) {
            throw new ClassGenException("Appending null InstructionList");
        }
        if (instructionList.isEmpty()) {
            return instructionHandle;
        }
        InstructionHandle next = instructionHandle.getNext();
        InstructionHandle instructionHandle2 = instructionList.start;
        instructionHandle.setNext(instructionHandle2);
        instructionList.start.setPrev(instructionHandle);
        instructionList.end.setNext(next);
        InstructionHandle instructionHandle3 = instructionList.end;
        if (next != null) {
            next.setPrev(instructionHandle3);
        } else {
            this.end = instructionHandle3;
        }
        this.length += instructionList.length;
        instructionList.clear();
        return instructionHandle2;
    }

    public boolean contains(InstructionHandle instructionHandle) {
        if (instructionHandle == null) {
            return false;
        }
        for (InstructionHandle next = this.start; next != null; next = next.getNext()) {
            if (next == instructionHandle) {
                return true;
            }
        }
        return false;
    }

    public InstructionList copy() {
        HashMap map = new HashMap();
        InstructionList instructionList = new InstructionList();
        for (InstructionHandle next = this.start; next != null; next = next.getNext()) {
            Instruction instructionCopy = next.getInstruction().copy();
            if (instructionCopy instanceof BranchInstruction) {
                map.put(next, instructionList.append((BranchInstruction) instructionCopy));
            } else {
                map.put(next, instructionList.append(instructionCopy));
            }
        }
        InstructionHandle next2 = this.start;
        InstructionHandle next3 = instructionList.start;
        while (next2 != null) {
            Instruction instruction = next2.getInstruction();
            Instruction instruction2 = next3.getInstruction();
            if (instruction instanceof BranchInstruction) {
                BranchInstruction branchInstruction = (BranchInstruction) instruction;
                BranchInstruction branchInstruction2 = (BranchInstruction) instruction2;
                branchInstruction2.setTarget((InstructionHandle) map.get(branchInstruction.getTarget()));
                if (branchInstruction instanceof Select) {
                    InstructionHandle[] targets = ((Select) branchInstruction).getTargets();
                    InstructionHandle[] targets2 = ((Select) branchInstruction2).getTargets();
                    for (int i = 0; i < targets.length; i++) {
                        targets2[i] = (InstructionHandle) map.get(targets[i]);
                    }
                }
            }
            next2 = next2.getNext();
            next3 = next3.getNext();
        }
        return instructionList;
    }

    public void delete(Instruction instruction, Instruction instruction2) throws TargetLostException {
        InstructionHandle instructionHandleFindInstruction1 = findInstruction1(instruction);
        if (instructionHandleFindInstruction1 == null) {
            yz0.a("Instruction ", instruction, " is not contained in this list.");
            return;
        }
        InstructionHandle instructionHandleFindInstruction2 = findInstruction2(instruction2);
        if (instructionHandleFindInstruction2 != null) {
            delete(instructionHandleFindInstruction1, instructionHandleFindInstruction2);
        } else {
            yz0.a("Instruction ", instruction2, " is not contained in this list.");
        }
    }

    public void dispose() {
        for (InstructionHandle prev = this.end; prev != null; prev = prev.getPrev()) {
            prev.dispose();
        }
        clear();
    }

    public byte[] getByteCode() {
        setPositions();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            for (InstructionHandle next = this.start; next != null; next = next.getNext()) {
                next.getInstruction().dump(dataOutputStream);
            }
            dataOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            System.err.println(e);
            return Const.EMPTY_BYTE_ARRAY;
        }
    }

    public InstructionHandle getEnd() {
        return this.end;
    }

    public InstructionHandle[] getInstructionHandles() {
        InstructionHandle[] instructionHandleArr = new InstructionHandle[this.length];
        InstructionHandle next = this.start;
        for (int i = 0; i < this.length; i++) {
            instructionHandleArr[i] = next;
            next = next.getNext();
        }
        return instructionHandleArr;
    }

    public int[] getInstructionPositions() {
        return this.bytePositions;
    }

    public Instruction[] getInstructions() {
        ArrayList arrayList = new ArrayList();
        try {
            ByteSequence byteSequence = new ByteSequence(getByteCode());
            while (byteSequence.available() > 0) {
                try {
                    arrayList.add(Instruction.readInstruction(byteSequence));
                } catch (Throwable th) {
                    try {
                        byteSequence.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            byteSequence.close();
            return (Instruction[]) arrayList.toArray(Instruction.EMPTY_ARRAY);
        } catch (IOException e) {
            throw new ClassGenException(e.toString(), e);
        }
    }

    public int getLength() {
        return this.length;
    }

    public InstructionHandle getStart() {
        return this.start;
    }

    public InstructionHandle insert(InstructionHandle instructionHandle, InstructionList instructionList) {
        if (instructionList == null) {
            throw new ClassGenException("Inserting null InstructionList");
        }
        if (instructionList.isEmpty()) {
            return instructionHandle;
        }
        InstructionHandle prev = instructionHandle.getPrev();
        InstructionHandle instructionHandle2 = instructionList.start;
        instructionHandle.setPrev(instructionList.end);
        instructionList.end.setNext(instructionHandle);
        instructionList.start.setPrev(prev);
        InstructionHandle instructionHandle3 = instructionList.start;
        if (prev != null) {
            prev.setNext(instructionHandle3);
        } else {
            this.start = instructionHandle3;
        }
        this.length += instructionList.length;
        instructionList.clear();
        return instructionHandle2;
    }

    public boolean isEmpty() {
        return this.start == null;
    }

    @Override // java.lang.Iterable
    public Iterator<InstructionHandle> iterator() {
        return new Iterator<InstructionHandle>() { // from class: com.sun.org.apache.bcel.internal.generic.InstructionList.1
            private InstructionHandle ih;

            {
                this.ih = InstructionList.this.start;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.ih != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public InstructionHandle next() throws NoSuchElementException {
                InstructionHandle instructionHandle = this.ih;
                if (instructionHandle != null) {
                    this.ih = instructionHandle.getNext();
                    return instructionHandle;
                }
                z0e.a();
                return null;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public void move(InstructionHandle instructionHandle, InstructionHandle instructionHandle2, InstructionHandle instructionHandle3) {
        if (instructionHandle == null || instructionHandle2 == null) {
            lo4.a("Invalid null handle: From ", instructionHandle, " to ", instructionHandle2);
            return;
        }
        if (instructionHandle3 == instructionHandle || instructionHandle3 == instructionHandle2) {
            throw new ClassGenException("Invalid range: From " + instructionHandle + " to " + instructionHandle2 + " contains target " + instructionHandle3);
        }
        for (InstructionHandle next = instructionHandle; next != instructionHandle2.getNext(); next = next.getNext()) {
            if (next == null) {
                lo4.a("Invalid range: From ", instructionHandle, " to ", instructionHandle2);
                return;
            }
            if (next == instructionHandle3) {
                throw new ClassGenException("Invalid range: From " + instructionHandle + " to " + instructionHandle2 + " contains target " + instructionHandle3);
            }
        }
        InstructionHandle prev = instructionHandle.getPrev();
        InstructionHandle next2 = instructionHandle2.getNext();
        if (prev != null) {
            prev.setNext(next2);
        } else {
            this.start = next2;
        }
        if (next2 != null) {
            next2.setPrev(prev);
        } else {
            this.end = prev;
        }
        instructionHandle.setPrev(instructionHandle2.setNext(null));
        if (instructionHandle3 == null) {
            InstructionHandle instructionHandle4 = this.start;
            if (instructionHandle4 != null) {
                instructionHandle4.setPrev(instructionHandle2);
            }
            instructionHandle2.setNext(this.start);
            this.start = instructionHandle;
            return;
        }
        InstructionHandle next3 = instructionHandle3.getNext();
        instructionHandle3.setNext(instructionHandle);
        instructionHandle.setPrev(instructionHandle3);
        instructionHandle2.setNext(next3);
        if (next3 != null) {
            next3.setPrev(instructionHandle2);
        } else {
            this.end = instructionHandle2;
        }
    }

    public void redirectBranches(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        for (InstructionHandle next = this.start; next != null; next = next.getNext()) {
            Instruction instruction = next.getInstruction();
            if (instruction instanceof BranchInstruction) {
                BranchInstruction branchInstruction = (BranchInstruction) instruction;
                if (branchInstruction.getTarget() == instructionHandle) {
                    branchInstruction.setTarget(instructionHandle2);
                }
                if (branchInstruction instanceof Select) {
                    Select select = (Select) branchInstruction;
                    InstructionHandle[] targets = select.getTargets();
                    for (int i = 0; i < targets.length; i++) {
                        if (targets[i] == instructionHandle) {
                            select.setTarget(i, instructionHandle2);
                        }
                    }
                }
            }
        }
    }

    public void redirectExceptionHandlers(CodeExceptionGen[] codeExceptionGenArr, InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        for (CodeExceptionGen codeExceptionGen : codeExceptionGenArr) {
            if (codeExceptionGen.getStartPC() == instructionHandle) {
                codeExceptionGen.setStartPC(instructionHandle2);
            }
            if (codeExceptionGen.getEndPC() == instructionHandle) {
                codeExceptionGen.setEndPC(instructionHandle2);
            }
            if (codeExceptionGen.getHandlerPC() == instructionHandle) {
                codeExceptionGen.setHandlerPC(instructionHandle2);
            }
        }
    }

    public void redirectLocalVariables(LocalVariableGen[] localVariableGenArr, InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        for (LocalVariableGen localVariableGen : localVariableGenArr) {
            InstructionHandle start = localVariableGen.getStart();
            InstructionHandle end = localVariableGen.getEnd();
            if (start == instructionHandle) {
                localVariableGen.setStart(instructionHandle2);
            }
            if (end == instructionHandle) {
                localVariableGen.setEnd(instructionHandle2);
            }
        }
    }

    public void removeObserver(InstructionListObserver instructionListObserver) {
        List<InstructionListObserver> list = this.observers;
        if (list != null) {
            list.remove(instructionListObserver);
        }
    }

    public void replaceConstantPool(ConstantPoolGen constantPoolGen, ConstantPoolGen constantPoolGen2) {
        for (InstructionHandle next = this.start; next != null; next = next.getNext()) {
            Instruction instruction = next.getInstruction();
            if (instruction instanceof CPInstruction) {
                CPInstruction cPInstruction = (CPInstruction) instruction;
                cPInstruction.setIndex(constantPoolGen2.addConstant(constantPoolGen.getConstant(cPInstruction.getIndex()), constantPoolGen));
            }
        }
    }

    public void setPositions(boolean z) {
        int[] iArr = new int[this.length];
        if (z) {
            for (InstructionHandle next = this.start; next != null; next = next.getNext()) {
                Instruction instruction = next.getInstruction();
                if (instruction instanceof BranchInstruction) {
                    Instruction instruction2 = ((BranchInstruction) instruction).getTarget().getInstruction();
                    if (!contains(instruction2)) {
                        fr6.a("Branch target of ", Const.getOpcodeName(instruction.getOpcode()), instruction2, " not in instruction list");
                        return;
                    }
                    if (instruction instanceof Select) {
                        for (InstructionHandle instructionHandle : ((Select) instruction).getTargets()) {
                            instruction2 = instructionHandle.getInstruction();
                            if (!contains(instruction2)) {
                                fr6.a("Branch target of ", Const.getOpcodeName(instruction.getOpcode()), instruction2, " not in instruction list");
                                return;
                            }
                        }
                    }
                    if (!(next instanceof BranchHandle)) {
                        fr6.a("Branch instruction ", Const.getOpcodeName(instruction.getOpcode()), instruction2, " not contained in BranchHandle.");
                        return;
                    }
                }
            }
        }
        InstructionHandle next2 = this.start;
        int length = 0;
        int i = 0;
        int i2 = 0;
        while (next2 != null) {
            Instruction instruction3 = next2.getInstruction();
            next2.setPosition(length);
            int i3 = i + 1;
            iArr[i] = length;
            switch (instruction3.getOpcode()) {
                case 167:
                case 168:
                    i2 += 2;
                    break;
                case 170:
                case 171:
                    i2 += 3;
                    break;
            }
            length += instruction3.getLength();
            next2 = next2.getNext();
            i = i3;
        }
        int iUpdatePosition = 0;
        for (InstructionHandle next3 = this.start; next3 != null; next3 = next3.getNext()) {
            iUpdatePosition += next3.updatePosition(iUpdatePosition, i2);
        }
        InstructionHandle next4 = this.start;
        int i4 = 0;
        int length2 = 0;
        while (next4 != null) {
            Instruction instruction4 = next4.getInstruction();
            next4.setPosition(length2);
            iArr[i4] = length2;
            length2 += instruction4.getLength();
            next4 = next4.getNext();
            i4++;
        }
        int[] iArr2 = new int[i4];
        this.bytePositions = iArr2;
        System.arraycopy(iArr, 0, iArr2, 0, i4);
    }

    public int size() {
        return this.length;
    }

    public String toString(boolean z) {
        StringBuilder sb = new StringBuilder();
        for (InstructionHandle next = this.start; next != null; next = next.getNext()) {
            sb.append(next.toString(z));
            sb.append("\n");
        }
        return sb.toString();
    }

    public void update() {
        List<InstructionListObserver> list = this.observers;
        if (list != null) {
            Iterator<InstructionListObserver> it = list.iterator();
            while (it.hasNext()) {
                it.next().notify(this);
            }
        }
    }

    public boolean contains(Instruction instruction) {
        return findInstruction1(instruction) != null;
    }

    public InstructionHandle findHandle(int i) {
        int[] iArr = this.bytePositions;
        InstructionHandle next = this.start;
        for (int i2 = 0; i2 < this.length; i2++) {
            if (iArr[i2] == i) {
                return next;
            }
            next = next.getNext();
        }
        return null;
    }

    public void delete(Instruction instruction) throws TargetLostException {
        InstructionHandle instructionHandleFindInstruction1 = findInstruction1(instruction);
        if (instructionHandleFindInstruction1 != null) {
            delete(instructionHandleFindInstruction1);
        } else {
            yz0.a("Instruction ", instruction, " is not contained in this list.");
        }
    }

    public void delete(InstructionHandle instructionHandle) throws TargetLostException {
        remove(instructionHandle.getPrev(), instructionHandle.getNext());
    }

    public String toString() {
        return toString(true);
    }

    public void delete(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) throws TargetLostException {
        remove(instructionHandle.getPrev(), instructionHandle2.getNext());
    }

    public InstructionHandle append(CompoundInstruction compoundInstruction) {
        return append(compoundInstruction.getInstructionList());
    }

    public InstructionHandle append(Instruction instruction) {
        InstructionHandle instructionHandle = InstructionHandle.getInstructionHandle(instruction);
        append(instructionHandle);
        return instructionHandle;
    }

    public InstructionHandle insert(CompoundInstruction compoundInstruction) {
        return insert(compoundInstruction.getInstructionList());
    }

    public InstructionHandle append(Instruction instruction, CompoundInstruction compoundInstruction) {
        return append(instruction, compoundInstruction.getInstructionList());
    }

    public InstructionHandle insert(Instruction instruction) {
        InstructionHandle instructionHandle = InstructionHandle.getInstructionHandle(instruction);
        insert(instructionHandle);
        return instructionHandle;
    }

    public InstructionHandle append(Instruction instruction, Instruction instruction2) {
        return append(instruction, new InstructionList(instruction2));
    }

    public InstructionHandle append(Instruction instruction, InstructionList instructionList) {
        InstructionHandle instructionHandleFindInstruction2 = findInstruction2(instruction);
        if (instructionHandleFindInstruction2 != null) {
            return append(instructionHandleFindInstruction2, instructionList);
        }
        yz0.a("Instruction ", instruction, " is not contained in this list.");
        return null;
    }

    public InstructionHandle insert(Instruction instruction, CompoundInstruction compoundInstruction) {
        return insert(instruction, compoundInstruction.getInstructionList());
    }

    public InstructionHandle insert(Instruction instruction, Instruction instruction2) {
        return insert(instruction, new InstructionList(instruction2));
    }

    public InstructionHandle insert(Instruction instruction, InstructionList instructionList) {
        InstructionHandle instructionHandleFindInstruction1 = findInstruction1(instruction);
        if (instructionHandleFindInstruction1 != null) {
            return insert(instructionHandleFindInstruction1, instructionList);
        }
        yz0.a("Instruction ", instruction, " is not contained in this list.");
        return null;
    }

    private void append(InstructionHandle instructionHandle) {
        if (isEmpty()) {
            this.end = instructionHandle;
            this.start = instructionHandle;
            instructionHandle.setNext(instructionHandle.setPrev(null));
        } else {
            this.end.setNext(instructionHandle);
            instructionHandle.setPrev(this.end);
            instructionHandle.setNext(null);
            this.end = instructionHandle;
        }
        this.length++;
    }

    private void insert(InstructionHandle instructionHandle) {
        if (isEmpty()) {
            this.end = instructionHandle;
            this.start = instructionHandle;
            instructionHandle.setNext(instructionHandle.setPrev(null));
        } else {
            this.start.setPrev(instructionHandle);
            instructionHandle.setNext(this.start);
            instructionHandle.setPrev(null);
            this.start = instructionHandle;
        }
        this.length++;
    }

    public BranchHandle append(InstructionHandle instructionHandle, BranchInstruction branchInstruction) {
        BranchHandle branchHandle = BranchHandle.getBranchHandle(branchInstruction);
        InstructionList instructionList = new InstructionList();
        instructionList.append(branchHandle);
        append(instructionHandle, instructionList);
        return branchHandle;
    }

    public BranchHandle insert(InstructionHandle instructionHandle, BranchInstruction branchInstruction) {
        BranchHandle branchHandle = BranchHandle.getBranchHandle(branchInstruction);
        InstructionList instructionList = new InstructionList();
        instructionList.append(branchHandle);
        insert(instructionHandle, instructionList);
        return branchHandle;
    }

    public InstructionHandle append(InstructionHandle instructionHandle, CompoundInstruction compoundInstruction) {
        return append(instructionHandle, compoundInstruction.getInstructionList());
    }

    public InstructionHandle append(InstructionHandle instructionHandle, Instruction instruction) {
        return append(instructionHandle, new InstructionList(instruction));
    }

    public BranchHandle append(BranchInstruction branchInstruction) {
        BranchHandle branchHandle = BranchHandle.getBranchHandle(branchInstruction);
        append(branchHandle);
        return branchHandle;
    }

    public InstructionHandle insert(InstructionHandle instructionHandle, CompoundInstruction compoundInstruction) {
        return insert(instructionHandle, compoundInstruction.getInstructionList());
    }

    public InstructionHandle insert(InstructionHandle instructionHandle, Instruction instruction) {
        return insert(instructionHandle, new InstructionList(instruction));
    }

    public InstructionHandle append(InstructionList instructionList) {
        if (instructionList != null) {
            if (instructionList.isEmpty()) {
                return null;
            }
            if (isEmpty()) {
                this.start = instructionList.start;
                this.end = instructionList.end;
                this.length = instructionList.length;
                instructionList.clear();
                return this.start;
            }
            return append(this.end, instructionList);
        }
        throw new ClassGenException("Appending null InstructionList");
    }

    public BranchHandle insert(BranchInstruction branchInstruction) {
        BranchHandle branchHandle = BranchHandle.getBranchHandle(branchInstruction);
        insert(branchHandle);
        return branchHandle;
    }

    public InstructionHandle insert(InstructionList instructionList) {
        if (isEmpty()) {
            append(instructionList);
            return this.start;
        }
        return insert(this.start, instructionList);
    }

    public InstructionList(BranchInstruction branchInstruction) {
        append(branchInstruction);
    }

    public void move(InstructionHandle instructionHandle, InstructionHandle instructionHandle2) {
        move(instructionHandle, instructionHandle, instructionHandle2);
    }

    public InstructionList() {
    }

    public InstructionList(CompoundInstruction compoundInstruction) {
        append(compoundInstruction.getInstructionList());
    }

    public InstructionList(Instruction instruction) {
        append(instruction);
    }

    public void setPositions() {
        setPositions(false);
    }
}

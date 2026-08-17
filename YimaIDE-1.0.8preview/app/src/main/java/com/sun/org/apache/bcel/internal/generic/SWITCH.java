package com.sun.org.apache.bcel.internal.generic;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class SWITCH implements CompoundInstruction {
    private final Select instruction;

    public SWITCH(int[] iArr, InstructionHandle[] instructionHandleArr, InstructionHandle instructionHandle, int i) {
        int[] iArr2 = (int[]) iArr.clone();
        InstructionHandle[] instructionHandleArr2 = (InstructionHandle[]) instructionHandleArr.clone();
        int length = iArr.length;
        if (length < 2) {
            this.instruction = new TABLESWITCH(iArr, instructionHandleArr, instructionHandle);
            return;
        }
        sort(0, length - 1, iArr2, instructionHandleArr2);
        if (!matchIsOrdered(iArr2, length, i)) {
            this.instruction = new LOOKUPSWITCH(iArr2, instructionHandleArr2, instructionHandle);
            return;
        }
        int i2 = (i * length) + length;
        int[] iArr3 = new int[i2];
        InstructionHandle[] instructionHandleArr3 = new InstructionHandle[i2];
        iArr3[0] = iArr[0];
        instructionHandleArr3[0] = instructionHandleArr[0];
        int i3 = 1;
        for (int i4 = 1; i4 < length; i4++) {
            int i5 = iArr[i4 - 1];
            int i6 = iArr[i4] - i5;
            for (int i7 = 1; i7 < i6; i7++) {
                iArr3[i3] = i5 + i7;
                instructionHandleArr3[i3] = instructionHandle;
                i3++;
            }
            iArr3[i3] = iArr[i4];
            instructionHandleArr3[i3] = instructionHandleArr[i4];
            i3++;
        }
        this.instruction = new TABLESWITCH(Arrays.copyOf(iArr3, i3), (InstructionHandle[]) Arrays.copyOf(instructionHandleArr3, i3), instructionHandle);
    }

    private static boolean matchIsOrdered(int[] iArr, int i, int i2) {
        for (int i3 = 1; i3 < i; i3++) {
            if (iArr[i3] - iArr[i3 - 1] > i2) {
                return false;
            }
        }
        return true;
    }

    private static void sort(int i, int i2, int[] iArr, InstructionHandle[] instructionHandleArr) {
        int i3;
        int i4 = iArr[(i + i2) >>> 1];
        int i5 = i;
        int i6 = i2;
        while (true) {
            if (iArr[i5] < i4) {
                i5++;
            } else {
                while (true) {
                    i3 = iArr[i6];
                    if (i4 >= i3) {
                        break;
                    } else {
                        i6--;
                    }
                }
                if (i5 <= i6) {
                    int i7 = iArr[i5];
                    iArr[i5] = i3;
                    iArr[i6] = i7;
                    InstructionHandle instructionHandle = instructionHandleArr[i5];
                    instructionHandleArr[i5] = instructionHandleArr[i6];
                    instructionHandleArr[i6] = instructionHandle;
                    i5++;
                    i6--;
                }
                if (i5 > i6) {
                    break;
                }
            }
        }
        if (i < i6) {
            sort(i, i6, iArr, instructionHandleArr);
        }
        if (i5 < i2) {
            sort(i5, i2, iArr, instructionHandleArr);
        }
    }

    public Instruction getInstruction() {
        return this.instruction;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CompoundInstruction
    public InstructionList getInstructionList() {
        return new InstructionList((BranchInstruction) this.instruction);
    }

    public SWITCH(int[] iArr, InstructionHandle[] instructionHandleArr, InstructionHandle instructionHandle) {
        this(iArr, instructionHandleArr, instructionHandle, 1);
    }
}

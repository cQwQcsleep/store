package com.reandroid.arsc.base;

import com.reandroid.utils.collection.ArrayCollection;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class BlockDiff {
    private static final int LENGTH_DIFFERENCE = -2;
    private static final int MAX_RESULTS = 10;
    private static final int NO_DIFFERENCE = -1;
    private final Block block_a;
    private final Block block_b;

    public static class DiffResult {
        public final BlockLocator.Result BLOCK_A;
        public final BlockLocator.Result BLOCK_B;

        public DiffResult(BlockLocator.Result result, BlockLocator.Result result2) {
            this.BLOCK_A = result;
            this.BLOCK_B = result2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("BLOCK_A {");
            BlockLocator.Result result = this.BLOCK_A;
            if (result != null) {
                sb.append(result.getMessage());
            } else {
                sb.append("null");
            }
            sb.append("}, BLOCK_B {");
            BlockLocator.Result result2 = this.BLOCK_B;
            if (result2 != null) {
                sb.append(result2.getMessage());
            } else {
                sb.append("null");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public BlockDiff(Block block, Block block2) {
        this.block_a = block;
        this.block_b = block2;
    }

    private int findByteDifferencePosition(int i, byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        if (length > bArr2.length) {
            length = bArr2.length;
        }
        while (i < length) {
            if (bArr[i] != bArr2[i]) {
                return i;
            }
            i++;
        }
        if (bArr.length != bArr2.length) {
            return LENGTH_DIFFERENCE;
        }
        return -1;
    }

    public static String toString(DiffResult[] diffResultArr) {
        if (diffResultArr == null || diffResultArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < diffResultArr.length; i++) {
            if (i != 0) {
                sb.append("\n");
            }
            sb.append(diffResultArr[i]);
        }
        return sb.toString();
    }

    public DiffResult[] find(int i) {
        byte[] bytes = this.block_a.getBytes();
        byte[] bytes2 = this.block_b.getBytes();
        ArrayCollection arrayCollection = new ArrayCollection();
        int iFindByteDifferencePosition = findByteDifferencePosition(0, bytes, bytes2);
        int iCountBytes = 0;
        while (true) {
            if ((i >= 0 && arrayCollection.size() >= i) || iFindByteDifferencePosition == -1) {
                break;
            }
            if (iFindByteDifferencePosition == LENGTH_DIFFERENCE) {
                iFindByteDifferencePosition = iCountBytes;
            }
            BlockLocator.Result resultLocateBlock = this.block_a.locateBlock(iFindByteDifferencePosition);
            BlockLocator.Result resultLocateBlock2 = this.block_b.locateBlock(iFindByteDifferencePosition);
            if (Objects.equals(resultLocateBlock, resultLocateBlock2)) {
                break;
            }
            arrayCollection.add(new DiffResult(resultLocateBlock, resultLocateBlock2));
            if (resultLocateBlock == null || resultLocateBlock2 == null) {
                break;
            }
            iCountBytes = iFindByteDifferencePosition + resultLocateBlock.block.countBytes() + 1;
            iFindByteDifferencePosition = findByteDifferencePosition(iCountBytes, bytes, bytes2);
        }
        return (DiffResult[]) arrayCollection.toArray(new DiffResult[0]);
    }

    public DiffResult[] find() {
        return find(10);
    }
}

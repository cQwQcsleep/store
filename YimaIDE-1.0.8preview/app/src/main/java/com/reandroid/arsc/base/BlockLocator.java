package com.reandroid.arsc.base;

import com.reandroid.arsc.item.ByteArray;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class BlockLocator extends BlockCounter {
    private static final int PARENT_TRACE_LENGTH = 10;
    private final int bytePosition;
    private Block current;
    private Block previous;

    public static class Result {
        public final Block block;
        public final Block[] parentTraces;
        public final int position;

        public Result(int i, Block block, Block[] blockArr) {
            this.position = i;
            this.block = block;
            this.parentTraces = blockArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (this.block == null || obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Result result = (Result) obj;
            if (result.block == null) {
                return false;
            }
            return ByteArray.equals(this.block.getBytes(), result.block.getBytes());
        }

        public String getMessage() {
            return "Position=" + this.position + ", " + BlockLocator.toResultName(this.block);
        }

        public int hashCode() {
            return Objects.hash(this.block);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getMessage());
            for (Block block : this.parentTraces) {
                sb.append("\n  ");
                sb.append(BlockLocator.toResultName(block));
            }
            return sb.toString();
        }
    }

    public BlockLocator(int i) {
        super(null);
        this.bytePosition = i;
    }

    private static Block[] buildParentTraces(Block block, Block block2) {
        Block[] blockArr = new Block[10];
        Block parent = block.getParent();
        if (parent != null) {
            block2 = parent;
        }
        int i = 0;
        while (block2 != null && i < 10) {
            blockArr[i] = block2;
            block2 = block2.getParent();
            i++;
        }
        Block[] blockArr2 = new Block[i];
        for (int i2 = 0; i2 < i; i2++) {
            blockArr2[i2] = blockArr[i2];
        }
        return blockArr2;
    }

    public static String toResultName(Block block) {
        if (block == null) {
            return "null";
        }
        String simpleName = block.getClass().getSimpleName();
        String string = block.toString();
        String str = string != null ? string : "null";
        if (str.startsWith(simpleName)) {
            return str;
        }
        return simpleName + " " + str;
    }

    public int getBytePosition() {
        return this.bytePosition;
    }

    public Result getResult() {
        if (!this.FOUND || this.current == null) {
            return null;
        }
        int bytePosition = getBytePosition();
        Block block = this.current;
        return new Result(bytePosition, block, buildParentTraces(block, this.previous));
    }

    @Override // com.reandroid.arsc.base.BlockCounter
    public void onCountAdded(int i) {
        if (i > this.bytePosition) {
            this.FOUND = true;
        }
    }

    @Override // com.reandroid.arsc.base.BlockCounter
    public void setCurrent(Block block) {
        if (block == null || this.FOUND) {
            return;
        }
        this.previous = this.current;
        this.current = block;
        if (getCountValue() > this.bytePosition) {
            this.FOUND = true;
        }
    }

    @Override // com.reandroid.arsc.base.BlockCounter
    public String toString() {
        Result result = getResult();
        if (result != null) {
            return result.toString();
        }
        return getCountValue() + "/" + this.bytePosition;
    }
}

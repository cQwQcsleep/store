package org.joni;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
class StackEntry {
    private int E1;
    private int E2;
    private int E3;
    private int E4;
    int type;

    public void decreaseRepeatCount() {
        this.E1--;
    }

    public int getAbsentEndStr() {
        return this.E2;
    }

    public int getAbsentStr() {
        return this.E1;
    }

    public int getCallFrameNum() {
        return this.E2;
    }

    public int getCallFramePStr() {
        return this.E3;
    }

    public int getCallFrameRetAddr() {
        return this.E1;
    }

    public int getMemEnd() {
        return this.E4;
    }

    public int getMemNum() {
        return this.E1;
    }

    public int getMemPStr() {
        return this.E2;
    }

    public int getMemStart() {
        return this.E3;
    }

    public int getNullCheckNum() {
        return this.E1;
    }

    public int getNullCheckPStr() {
        return this.E2;
    }

    public int getPKeep() {
        return this.E4;
    }

    public int getRepeatCount() {
        return this.E1;
    }

    public int getRepeatNum() {
        return this.E3;
    }

    public int getRepeatPCode() {
        return this.E2;
    }

    public int getSi() {
        return this.E1;
    }

    public int getStatePCode() {
        return this.E1;
    }

    public int getStatePStr() {
        return this.E2;
    }

    public int getStatePStrPrev() {
        return this.E3;
    }

    public void increaseRepeatCount() {
        this.E1++;
    }

    public void setAbsentEndStr(int i) {
        this.E2 = i;
    }

    public void setAbsentStr(int i) {
        this.E1 = i;
    }

    public void setCallFrameNum(int i) {
        this.E2 = i;
    }

    public void setCallFramePStr(int i) {
        this.E3 = i;
    }

    public void setCallFrameRetAddr(int i) {
        this.E1 = i;
    }

    public void setMemEnd(int i) {
        this.E4 = i;
    }

    public void setMemNum(int i) {
        this.E1 = i;
    }

    public void setMemPstr(int i) {
        this.E2 = i;
    }

    public void setMemStart(int i) {
        this.E3 = i;
    }

    public void setNullCheckNum(int i) {
        this.E1 = i;
    }

    public void setNullCheckPStr(int i) {
        this.E2 = i;
    }

    public void setPKeep(int i) {
        this.E4 = i;
    }

    public void setRepeatCount(int i) {
        this.E1 = i;
    }

    public void setRepeatNum(int i) {
        this.E3 = i;
    }

    public void setRepeatPCode(int i) {
        this.E2 = i;
    }

    public void setSi(int i) {
        this.E1 = i;
    }

    public void setStatePCode(int i) {
        this.E1 = i;
    }

    public void setStatePStr(int i) {
        this.E2 = i;
    }

    public void setStatePStrPrev(int i) {
        this.E3 = i;
    }
}

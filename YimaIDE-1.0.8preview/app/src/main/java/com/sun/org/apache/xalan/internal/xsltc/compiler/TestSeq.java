package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.GOTO_W;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class TestSeq {
    private Template _default;
    private InstructionList _instructionList;
    private int _kernelType;
    private Mode _mode;
    private List<LocationPathPattern> _patterns;
    private InstructionHandle _start;

    public TestSeq(List<LocationPathPattern> list, int i, Mode mode) {
        this._default = null;
        this._start = null;
        this._patterns = list;
        this._kernelType = i;
        this._mode = mode;
    }

    private LocationPathPattern getPattern(int i) {
        return this._patterns.get(i);
    }

    private InstructionHandle getTemplateHandle(Template template) {
        return this._mode.getTemplateInstructionHandle(template);
    }

    public InstructionHandle compile(ClassGenerator classGenerator, MethodGenerator methodGenerator, InstructionHandle instructionHandle) {
        InstructionHandle instructionHandle2 = this._start;
        if (instructionHandle2 != null) {
            return instructionHandle2;
        }
        int size = this._patterns.size();
        Template template = this._default;
        if (size == 0) {
            InstructionHandle templateHandle = getTemplateHandle(template);
            this._start = templateHandle;
            return templateHandle;
        }
        if (template != null) {
            instructionHandle = getTemplateHandle(template);
        }
        for (int i = size - 1; i >= 0; i--) {
            LocationPathPattern pattern = getPattern(i);
            Template template2 = pattern.getTemplate();
            InstructionList instructionList = new InstructionList();
            instructionList.append(methodGenerator.loadCurrentNode());
            InstructionList instructionList2 = methodGenerator.getInstructionList(pattern);
            if (instructionList2 == null) {
                instructionList2 = pattern.compile(classGenerator, methodGenerator);
                methodGenerator.addInstructionList(pattern, instructionList2);
            }
            InstructionList instructionListCopy = instructionList2.copy();
            FlowList trueList = pattern.getTrueList();
            if (trueList != null) {
                trueList = trueList.copyAndRedirect(instructionList2, instructionListCopy);
            }
            FlowList falseList = pattern.getFalseList();
            if (falseList != null) {
                falseList = falseList.copyAndRedirect(instructionList2, instructionListCopy);
            }
            instructionList.append(instructionListCopy);
            BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO_W(getTemplateHandle(template2)));
            if (trueList != null) {
                trueList.backPatch(branchHandleAppend);
            }
            if (falseList != null) {
                falseList.backPatch(instructionHandle);
            }
            instructionHandle = instructionList.getStart();
            InstructionList instructionList3 = this._instructionList;
            if (instructionList3 != null) {
                instructionList.append(instructionList3);
            }
            this._instructionList = instructionList;
        }
        this._start = instructionHandle;
        return instructionHandle;
    }

    public void findTemplates(Map<Template, Object> map) {
        Template template = this._default;
        if (template != null) {
            map.put(template, this);
        }
        Iterator<LocationPathPattern> it = this._patterns.iterator();
        while (it.hasNext()) {
            map.put(it.next().getTemplate(), this);
        }
    }

    public InstructionList getInstructionList() {
        return this._instructionList;
    }

    public int getPosition() {
        return (this._patterns.isEmpty() ? this._default : this._patterns.get(0).getTemplate()).getPosition();
    }

    public double getPriority() {
        return (this._patterns.isEmpty() ? this._default : this._patterns.get(0).getTemplate()).getPriority();
    }

    public void reduce() {
        ArrayList arrayList = new ArrayList();
        for (LocationPathPattern locationPathPattern : this._patterns) {
            locationPathPattern.reduceKernelPattern();
            if (locationPathPattern.isWildcard()) {
                this._default = locationPathPattern.getTemplate();
                break;
            }
            arrayList.add(locationPathPattern);
        }
        this._patterns = arrayList;
    }

    public String toString() {
        int size = this._patterns.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            LocationPathPattern locationPathPattern = this._patterns.get(i);
            if (i == 0) {
                stringBuffer.append("Testseq for kernel ");
                stringBuffer.append(this._kernelType);
                stringBuffer.append('\n');
            }
            stringBuffer.append("   pattern ");
            stringBuffer.append(i);
            stringBuffer.append(": ");
            stringBuffer.append(locationPathPattern.toString());
            stringBuffer.append('\n');
        }
        return stringBuffer.toString();
    }

    public TestSeq(List<LocationPathPattern> list, Mode mode) {
        this(list, -2, mode);
    }
}

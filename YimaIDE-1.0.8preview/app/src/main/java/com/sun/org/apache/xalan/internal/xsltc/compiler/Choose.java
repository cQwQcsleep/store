package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class Choose extends Instruction {
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("Choose");
        int i2 = i + 4;
        indent(i2);
        displayContents(i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ArrayList arrayList = new ArrayList();
        Iterator<SyntaxTreeNode> itElements = elements();
        getLineNumber();
        Otherwise otherwise = null;
        while (itElements.hasNext()) {
            SyntaxTreeNode next = itElements.next();
            if (next instanceof When) {
                arrayList.add(next);
            } else if (next instanceof Otherwise) {
                if (otherwise == null) {
                    otherwise = (Otherwise) next;
                } else {
                    getParser().reportError(3, new ErrorMsg(ErrorMsg.MULTIPLE_OTHERWISE_ERR, (SyntaxTreeNode) this));
                }
            } else if (next instanceof Text) {
                ((Text) next).ignore();
            } else {
                getParser().reportError(3, new ErrorMsg(ErrorMsg.WHEN_ELEMENT_ERR, (SyntaxTreeNode) this));
            }
        }
        if (arrayList.size() == 0) {
            getParser().reportError(3, new ErrorMsg(ErrorMsg.MISSING_WHEN_ERR, (SyntaxTreeNode) this));
            return;
        }
        InstructionList instructionList = methodGenerator.getInstructionList();
        ArrayList arrayList2 = new ArrayList();
        Enumeration enumeration = Collections.enumeration(arrayList);
        BranchHandle branchHandleAppend = null;
        InstructionHandle instructionHandleAppend = null;
        while (enumeration.hasMoreElements()) {
            When when = (When) enumeration.nextElement();
            Expression test = when.getTest();
            instructionList.getEnd();
            if (branchHandleAppend != null) {
                branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
            }
            test.translateDesynthesized(classGenerator, methodGenerator);
            if (test instanceof FunctionCall) {
                try {
                    if (((FunctionCall) test).typeCheck(getParser().getSymbolTable()) != Type.Boolean) {
                        test._falseList.add(instructionList.append((BranchInstruction) new IFEQ(null)));
                    }
                } catch (TypeCheckError unused) {
                }
            }
            InstructionHandle end = instructionList.getEnd();
            if (!when.ignore()) {
                when.translateContents(classGenerator, methodGenerator);
            }
            arrayList2.add(instructionList.append((BranchInstruction) new GOTO(null)));
            if (enumeration.hasMoreElements() || otherwise != null) {
                branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
                test.backPatchFalseList(branchHandleAppend);
            } else {
                instructionHandleAppend = instructionList.append(Constants.NOP);
                test.backPatchFalseList(instructionHandleAppend);
            }
            test.backPatchTrueList(end.getNext());
        }
        if (otherwise != null) {
            com.sun.org.apache.bcel.internal.generic.Instruction instruction = Constants.NOP;
            branchHandleAppend.setTarget(instructionList.append(instruction));
            otherwise.translateContents(classGenerator, methodGenerator);
            instructionHandleAppend = instructionList.append(instruction);
        }
        Enumeration enumeration2 = Collections.enumeration(arrayList2);
        while (enumeration2.hasMoreElements()) {
            ((BranchHandle) enumeration2.nextElement()).setTarget(instructionHandleAppend);
        }
    }
}

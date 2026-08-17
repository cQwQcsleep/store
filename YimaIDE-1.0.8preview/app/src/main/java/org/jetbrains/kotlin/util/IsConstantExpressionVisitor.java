package org.jetbrains.kotlin.util;

import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.PsiClassObjectAccessExpression;
import com.intellij.psi.PsiConditionalExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiEnumConstant;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.PsiParenthesizedExpression;
import com.intellij.psi.PsiPolyadicExpression;
import com.intellij.psi.PsiPrefixExpression;
import com.intellij.psi.PsiPrimitiveType;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiTypeCastExpression;
import com.intellij.psi.PsiTypeElement;
import com.intellij.psi.PsiVariable;
import com.intellij.psi.impl.compiled.ClsFieldImpl;
import com.intellij.psi.impl.java.stubs.PsiFieldStub;
import com.intellij.psi.impl.source.PsiFieldImpl;
import com.intellij.psi.tree.IElementType;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.kotlin.load.java.structure.impl.NotEvaluatedConstAware;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
final class IsConstantExpressionVisitor extends JavaElementVisitor {
    private boolean myIsConstant;
    private final Map<PsiVariable, Boolean> varIsConst = new HashMap();

    private boolean checkForNotYetEvaluatedConstant(PsiExpression psiExpression) {
        PsiVariable psiVariableResolve;
        NotEvaluatedConstAware notEvaluatedConstAware;
        if (!(psiExpression instanceof PsiReferenceExpression) || (notEvaluatedConstAware = getNotEvaluatedConstAware((psiVariableResolve = ((PsiReferenceExpression) psiExpression).resolve()))) == null) {
            return false;
        }
        if (notEvaluatedConstAware.isNotYetComputed()) {
            this.myIsConstant = true;
            this.varIsConst.put(psiVariableResolve, Boolean.TRUE);
        }
        return true;
    }

    private static NotEvaluatedConstAware getNotEvaluatedConstAware(PsiElement psiElement) {
        if (!(psiElement instanceof ClsFieldImpl)) {
            if (psiElement instanceof NotEvaluatedConstAware) {
                return (NotEvaluatedConstAware) psiElement;
            }
            return null;
        }
        NotEvaluatedConstAware notEvaluatedConstAware = (PsiFieldStub) ((ClsFieldImpl) psiElement).getStub();
        if (notEvaluatedConstAware instanceof NotEvaluatedConstAware) {
            return notEvaluatedConstAware;
        }
        return null;
    }

    public boolean isConstant() {
        return this.myIsConstant;
    }

    public void visitClassObjectAccessExpression(PsiClassObjectAccessExpression psiClassObjectAccessExpression) {
        this.myIsConstant = true;
    }

    public void visitConditionalExpression(PsiConditionalExpression psiConditionalExpression) {
        PsiExpression thenExpression = psiConditionalExpression.getThenExpression();
        PsiExpression elseExpression = psiConditionalExpression.getElseExpression();
        if (thenExpression == null || elseExpression == null) {
            this.myIsConstant = false;
            return;
        }
        psiConditionalExpression.getCondition().accept(this);
        if (this.myIsConstant) {
            thenExpression.accept(this);
            if (this.myIsConstant) {
                elseExpression.accept(this);
            }
        }
    }

    public void visitExpression(PsiExpression psiExpression) {
        this.myIsConstant = false;
    }

    public void visitLiteralExpression(PsiLiteralExpression psiLiteralExpression) {
        this.myIsConstant = !"null".equals(psiLiteralExpression.getText());
    }

    public void visitParenthesizedExpression(PsiParenthesizedExpression psiParenthesizedExpression) {
        PsiExpression expression = psiParenthesizedExpression.getExpression();
        if (expression != null) {
            expression.accept(this);
        }
    }

    public void visitPolyadicExpression(PsiPolyadicExpression psiPolyadicExpression) {
        for (PsiElement psiElement : psiPolyadicExpression.getOperands()) {
            psiElement.accept(this);
            if (!this.myIsConstant || checkForNotYetEvaluatedConstant(psiElement)) {
                return;
            }
            PsiType type = psiElement.getType();
            if (type != null && !(type instanceof PsiPrimitiveType) && !type.equalsToText("java.lang.String")) {
                this.myIsConstant = false;
                return;
            }
        }
    }

    public void visitPrefixExpression(PsiPrefixExpression psiPrefixExpression) {
        IElementType operationTokenType;
        PsiExpression operand = psiPrefixExpression.getOperand();
        if (operand == null) {
            this.myIsConstant = false;
            return;
        }
        operand.accept(this);
        if (!this.myIsConstant || (operationTokenType = psiPrefixExpression.getOperationTokenType()) == JavaTokenType.PLUS || operationTokenType == JavaTokenType.MINUS || operationTokenType == JavaTokenType.TILDE || operationTokenType == JavaTokenType.EXCL) {
            return;
        }
        this.myIsConstant = false;
    }

    public void visitReferenceExpression(PsiReferenceExpression psiReferenceExpression) {
        PsiExpression qualifierExpression = psiReferenceExpression.getQualifierExpression();
        if (qualifierExpression != null && !(qualifierExpression instanceof PsiReferenceExpression)) {
            this.myIsConstant = false;
            return;
        }
        PsiVariable psiVariableResolve = psiReferenceExpression.resolve();
        if (!(psiVariableResolve instanceof PsiVariable)) {
            this.myIsConstant = false;
            return;
        }
        PsiVariable psiVariable = psiVariableResolve;
        Boolean bool = this.varIsConst.get(psiVariable);
        if (bool != null) {
            this.myIsConstant &= bool.booleanValue();
            return;
        }
        if (psiVariable instanceof PsiEnumConstant) {
            this.myIsConstant = true;
            this.varIsConst.put(psiVariable, Boolean.TRUE);
            return;
        }
        this.varIsConst.put(psiVariable, Boolean.FALSE);
        if (!psiVariable.hasModifierProperty("final")) {
            this.myIsConstant = false;
            return;
        }
        if (checkForNotYetEvaluatedConstant(psiReferenceExpression)) {
            return;
        }
        psiVariable.hasInitializer();
        PsiExpression detachedInitializer = PsiFieldImpl.getDetachedInitializer(psiVariable);
        if (detachedInitializer == null) {
            this.myIsConstant = false;
        } else {
            detachedInitializer.accept(this);
            this.varIsConst.put(psiVariable, Boolean.valueOf(this.myIsConstant));
        }
    }

    public void visitTypeCastExpression(PsiTypeCastExpression psiTypeCastExpression) {
        PsiExpression operand = psiTypeCastExpression.getOperand();
        if (operand == null) {
            this.myIsConstant = false;
            return;
        }
        operand.accept(this);
        if (this.myIsConstant) {
            PsiTypeElement castType = psiTypeCastExpression.getCastType();
            if (castType == null) {
                this.myIsConstant = false;
                return;
            }
            PsiType type = castType.getType();
            if ((type instanceof PsiPrimitiveType) || type.equalsToText("java.lang.String")) {
                return;
            }
            this.myIsConstant = false;
        }
    }
}

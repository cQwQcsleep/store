package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class FunctionAvailableCall extends FunctionCall {
    private Expression _arg;
    private boolean _isFunctionAvailable;
    private String _nameOfFunct;
    private String _namespaceOfFunct;

    public FunctionAvailableCall(QName qName, List<Expression> list) {
        super(qName, list);
        this._nameOfFunct = null;
        this._namespaceOfFunct = null;
        this._isFunctionAvailable = false;
        Expression expression = list.get(0);
        this._arg = expression;
        this._type = null;
        if (expression instanceof LiteralExpr) {
            LiteralExpr literalExpr = (LiteralExpr) expression;
            this._namespaceOfFunct = literalExpr.getNamespace();
            this._nameOfFunct = literalExpr.getValue();
            if (isInternalNamespace()) {
                return;
            }
            this._isFunctionAvailable = hasMethods();
        }
    }

    private boolean hasMethods() {
        int iLastIndexOf;
        String classNameFromUri = getClassNameFromUri(this._namespaceOfFunct);
        int iIndexOf = this._nameOfFunct.indexOf(":");
        String strReplaceDash = this._nameOfFunct;
        if (iIndexOf > 0 && (iLastIndexOf = (strReplaceDash = strReplaceDash.substring(iIndexOf + 1)).lastIndexOf(46)) > 0) {
            String strSubstring = strReplaceDash.substring(iLastIndexOf + 1);
            classNameFromUri = (classNameFromUri == null || classNameFromUri.length() == 0) ? strReplaceDash.substring(0, iLastIndexOf) : classNameFromUri + com.sun.org.apache.xalan.internal.templates.Constants.ATTRVAL_THIS + strReplaceDash.substring(0, iLastIndexOf);
            strReplaceDash = strSubstring;
        }
        if (classNameFromUri != null && strReplaceDash != null) {
            if (strReplaceDash.indexOf(45) > 0) {
                strReplaceDash = FunctionCall.replaceDash(strReplaceDash);
            }
            try {
                Class<?> clsFindProviderClass = ObjectFactory.findProviderClass(classNameFromUri, true);
                if (clsFindProviderClass == null) {
                    return false;
                }
                Method[] methods = clsFindProviderClass.getMethods();
                for (int i = 0; i < methods.length; i++) {
                    int modifiers = methods[i].getModifiers();
                    if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && methods[i].getName().equals(strReplaceDash)) {
                        return true;
                    }
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        return false;
    }

    private boolean isInternalNamespace() {
        String str = this._namespaceOfFunct;
        return str == null || str.equals("") || this._namespaceOfFunct.equals(Constants.TRANSLET_URI);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public Object evaluateAtCompileTime() {
        return getResult() ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean getResult() {
        if (this._nameOfFunct == null) {
            return false;
        }
        if (isInternalNamespace()) {
            this._isFunctionAvailable = getParser().functionSupported(Util.getLocalName(this._nameOfFunct));
        }
        return this._isFunctionAvailable;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        methodGenerator.getInstructionList().append(new PUSH(classGenerator.getConstantPool(), getResult()));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type type = this._type;
        if (type != null) {
            return type;
        }
        if (!(this._arg instanceof LiteralExpr)) {
            throw new TypeCheckError(new ErrorMsg(ErrorMsg.NEED_LITERAL_ERR, (Object) Keywords.FUNC_EXT_FUNCTION_AVAILABLE_STRING, (SyntaxTreeNode) this));
        }
        Type type2 = Type.Boolean;
        this._type = type2;
        return type2;
    }
}

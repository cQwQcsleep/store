package org.jetbrains.kotlin.checkers.utils;

import com.google.common.collect.ImmutableSet;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyGetterDescriptor;
import org.jetbrains.kotlin.descriptors.PropertySetterDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.diagnostics.Diagnostic;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.DiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.Errors;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.psi.KtArrayAccessExpression;
import org.jetbrains.kotlin.psi.KtDestructuringDeclaration;
import org.jetbrains.kotlin.psi.KtDestructuringDeclarationEntry;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtForExpression;
import org.jetbrains.kotlin.psi.KtNameReferenceExpression;
import org.jetbrains.kotlin.psi.KtProperty;
import org.jetbrains.kotlin.psi.KtPropertyDelegate;
import org.jetbrains.kotlin.psi.KtReferenceExpression;
import org.jetbrains.kotlin.psi.KtSimpleNameExpression;
import org.jetbrains.kotlin.psi.KtSuperExpression;
import org.jetbrains.kotlin.psi.KtThisExpression;
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid;
import org.jetbrains.kotlin.resolve.BindingContext;
import org.jetbrains.kotlin.resolve.BindingContextUtils;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.calls.tasks.DynamicCallsKt;
import org.jetbrains.kotlin.resolve.calls.util.CallUtilKt;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.error.ErrorUtils;
import org.jetbrains.kotlin.util.slicedMap.WritableSlice;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u0013\u0010\u0004\u001a\u00070\u0005¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0007\u001a\u00070\u0005¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/checkers/utils/DebugInfoUtil;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "MAY_BE_UNRESOLVED", "Lcom/intellij/psi/tree/TokenSet;", "Lorg/jetbrains/annotations/NotNull;", "EXCLUDED", "markDebugAnnotations", "", "root", "Lcom/intellij/psi/PsiElement;", "bindingContext", "Lorg/jetbrains/kotlin/resolve/BindingContext;", "debugInfoReporter", "Lorg/jetbrains/kotlin/checkers/utils/DebugInfoUtil$DebugInfoReporter;", "reportIfDynamic", "", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/psi/KtElement;", "declarationDescriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "DebugInfoReporter", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class DebugInfoUtil {
    private static final TokenSet EXCLUDED;
    public static final DebugInfoUtil INSTANCE = new DebugInfoUtil();
    private static final TokenSet MAY_BE_UNRESOLVED;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH&J\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/checkers/utils/DebugInfoUtil$DebugInfoReporter;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "preProcessReference", "", "expression", "Lorg/jetbrains/kotlin/psi/KtReferenceExpression;", "reportElementWithErrorType", "reportMissingUnresolved", "reportUnresolvedWithTarget", "target", "", "reportDynamicCall", CapturedVarsOptimizationMethodTransformerKt.REF_ELEMENT_FIELD, "Lorg/jetbrains/kotlin/psi/KtElement;", "declarationDescriptor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static abstract class DebugInfoReporter {
        public final void preProcessReference(KtReferenceExpression expression) {
            expression.getClass();
        }

        public void reportDynamicCall(KtElement element, DeclarationDescriptor declarationDescriptor) {
            element.getClass();
            declarationDescriptor.getClass();
        }

        public abstract void reportElementWithErrorType(KtReferenceExpression expression);

        public abstract void reportMissingUnresolved(KtReferenceExpression expression);

        public abstract void reportUnresolvedWithTarget(KtReferenceExpression expression, String target);
    }

    static {
        TokenSet tokenSetCreate = TokenSet.create(new IElementType[]{KtTokens.IN_KEYWORD, KtTokens.NOT_IN});
        tokenSetCreate.getClass();
        MAY_BE_UNRESOLVED = tokenSetCreate;
        TokenSet tokenSetCreate2 = TokenSet.create(new IElementType[]{KtTokens.COLON, KtTokens.AS_KEYWORD, KtTokens.AS_SAFE, KtTokens.IS_KEYWORD, KtTokens.NOT_IS, KtTokens.OROR, KtTokens.ANDAND, KtTokens.EQ, KtTokens.EQEQEQ, KtTokens.EXCLEQEQEQ, KtTokens.ELVIS, KtTokens.EXCLEXCL});
        tokenSetCreate2.getClass();
        EXCLUDED = tokenSetCreate2;
    }

    private DebugInfoUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean reportIfDynamic(KtElement element, DeclarationDescriptor declarationDescriptor, DebugInfoReporter debugInfoReporter) {
        if (declarationDescriptor == null || !DynamicCallsKt.isDynamic(declarationDescriptor)) {
            return false;
        }
        debugInfoReporter.reportDynamicCall(element, declarationDescriptor);
        return true;
    }

    public final void markDebugAnnotations(PsiElement root, final BindingContext bindingContext, final DebugInfoReporter debugInfoReporter) {
        root.getClass();
        bindingContext.getClass();
        debugInfoReporter.getClass();
        final HashMap map = new HashMap();
        for (Diagnostic diagnostic : bindingContext.getDiagnostics()) {
            DiagnosticFactory1 factory = diagnostic.getFactory();
            ImmutableSet immutableSet = Errors.UNRESOLVED_REFERENCE_DIAGNOSTICS;
            immutableSet.getClass();
            if (CollectionsKt.contains(immutableSet, diagnostic.getFactory())) {
                KtReferenceExpression psiElement = diagnostic.getPsiElement();
                psiElement.getClass();
                map.put(psiElement, factory);
            } else if (factory == Errors.SUPER_IS_NOT_AN_EXPRESSION || factory == Errors.SUPER_NOT_AVAILABLE) {
                KtSuperExpression psiElement2 = diagnostic.getPsiElement();
                psiElement2.getClass();
                map.put(psiElement2.getInstanceReference(), factory);
            } else if (factory == Errors.EXPRESSION_EXPECTED_PACKAGE_FOUND) {
                KtSimpleNameExpression psiElement3 = diagnostic.getPsiElement();
                psiElement3.getClass();
                map.put(psiElement3, factory);
            } else if (factory == Errors.UNSUPPORTED || factory == Errors.UNSUPPORTED_FEATURE) {
                Iterator it = PsiTreeUtil.findChildrenOfType(diagnostic.getPsiElement(), KtReferenceExpression.class).iterator();
                while (it.hasNext()) {
                    map.put((KtReferenceExpression) it.next(), factory);
                }
            }
        }
        root.acceptChildren(new KtTreeVisitorVoid() { // from class: org.jetbrains.kotlin.checkers.utils.DebugInfoUtil.markDebugAnnotations.1
            private final <E extends KtElement, K, D extends CallableDescriptor> boolean reportIfDynamicCall(E element, K key, WritableSlice<K, ResolvedCall<D>> slice) {
                ResolvedCall resolvedCall = (ResolvedCall) bindingContext.get(slice, key);
                if (resolvedCall != null) {
                    return DebugInfoUtil.INSTANCE.reportIfDynamic(element, resolvedCall.getResultingDescriptor(), debugInfoReporter);
                }
                return false;
            }

            public void visitDestructuringDeclaration(KtDestructuringDeclaration destructuringDeclaration) {
                destructuringDeclaration.getClass();
                for (KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry : destructuringDeclaration.getEntries()) {
                    ktDestructuringDeclarationEntry.getClass();
                    WritableSlice writableSlice = BindingContext.COMPONENT_RESOLVED_CALL;
                    writableSlice.getClass();
                    reportIfDynamicCall(ktDestructuringDeclarationEntry, ktDestructuringDeclarationEntry, writableSlice);
                }
                super/*org.jetbrains.kotlin.psi.KtVisitorVoid*/.visitDestructuringDeclaration(destructuringDeclaration);
            }

            public void visitForExpression(KtForExpression expression) {
                expression.getClass();
                KtExpression loopRange = expression.getLoopRange();
                if (loopRange != null) {
                    WritableSlice writableSlice = BindingContext.LOOP_RANGE_ITERATOR_RESOLVED_CALL;
                    writableSlice.getClass();
                    reportIfDynamicCall(loopRange, loopRange, writableSlice);
                    WritableSlice writableSlice2 = BindingContext.LOOP_RANGE_HAS_NEXT_RESOLVED_CALL;
                    writableSlice2.getClass();
                    reportIfDynamicCall(loopRange, loopRange, writableSlice2);
                    WritableSlice writableSlice3 = BindingContext.LOOP_RANGE_NEXT_RESOLVED_CALL;
                    writableSlice3.getClass();
                    reportIfDynamicCall(loopRange, loopRange, writableSlice3);
                }
                super/*org.jetbrains.kotlin.psi.KtVisitorVoid*/.visitForExpression(expression);
            }

            public void visitProperty(KtProperty property) {
                property.getClass();
                PropertyDescriptor propertyDescriptor = (VariableDescriptor) bindingContext.get(BindingContext.VARIABLE, property);
                KtPropertyDelegate delegate = property.getDelegate();
                if ((propertyDescriptor instanceof PropertyDescriptor) && delegate != null) {
                    WritableSlice writableSlice = BindingContext.PROVIDE_DELEGATE_RESOLVED_CALL;
                    writableSlice.getClass();
                    reportIfDynamicCall(delegate, propertyDescriptor, writableSlice);
                    PropertyDescriptor propertyDescriptor2 = propertyDescriptor;
                    PropertyGetterDescriptor getter = propertyDescriptor2.getGetter();
                    WritableSlice writableSlice2 = BindingContext.DELEGATED_PROPERTY_RESOLVED_CALL;
                    writableSlice2.getClass();
                    reportIfDynamicCall(delegate, getter, writableSlice2);
                    PropertySetterDescriptor setter = propertyDescriptor2.getSetter();
                    writableSlice2.getClass();
                    reportIfDynamicCall(delegate, setter, writableSlice2);
                }
                super/*org.jetbrains.kotlin.psi.KtVisitorVoid*/.visitProperty(property);
            }

            public void visitReferenceExpression(KtReferenceExpression expression) {
                IElementType referencedNameElementType;
                Collection collection;
                Collection collection2;
                PsiElement psiElement4;
                expression.getClass();
                super/*org.jetbrains.kotlin.psi.KtVisitorVoid*/.visitReferenceExpression(expression);
                if (BindingContextUtils.isExpressionWithValidReference(expression, bindingContext)) {
                    String text = null;
                    if (expression instanceof KtSimpleNameExpression) {
                        IElementType elementType = expression.getNode().getElementType();
                        elementType.getClass();
                        if (elementType == KtNodeTypes.OPERATION_REFERENCE) {
                            referencedNameElementType = ((KtSimpleNameExpression) expression).getReferencedNameElementType();
                            if (DebugInfoUtil.EXCLUDED.contains(referencedNameElementType)) {
                                return;
                            }
                        } else {
                            referencedNameElementType = null;
                        }
                        if (elementType == KtNodeTypes.LABEL || ((KtSimpleNameExpression) expression).getReferencedNameElementType() == KtTokens.THIS_KEYWORD) {
                            return;
                        }
                    } else {
                        referencedNameElementType = null;
                    }
                    debugInfoReporter.preProcessReference(expression);
                    DeclarationDescriptor declarationDescriptor = (DeclarationDescriptor) bindingContext.get(BindingContext.REFERENCE_TARGET, expression);
                    if (declarationDescriptor != null) {
                        text = declarationDescriptor.toString();
                        DebugInfoUtil.INSTANCE.reportIfDynamic(expression, declarationDescriptor, debugInfoReporter);
                    }
                    if (text == null && (psiElement4 = (PsiElement) bindingContext.get(BindingContext.LABEL_TARGET, expression)) != null) {
                        text = psiElement4.getText();
                    }
                    if (text == null && (collection2 = (Collection) bindingContext.get(BindingContext.AMBIGUOUS_REFERENCE_TARGET, expression)) != null) {
                        text = "[" + collection2.size() + " descriptors]";
                    }
                    if (text == null && (collection = (Collection) bindingContext.get(BindingContext.AMBIGUOUS_LABEL_TARGET, expression)) != null) {
                        text = "[" + collection.size() + " elements]";
                    }
                    if (DebugInfoUtil.MAY_BE_UNRESOLVED.contains(referencedNameElementType)) {
                        return;
                    }
                    if ((expression instanceof KtNameReferenceExpression) && ((KtNameReferenceExpression) expression).isPlaceholder()) {
                        return;
                    }
                    boolean z = text != null;
                    boolean zContainsKey = ((expression instanceof KtArrayAccessExpression) && map.containsKey(((KtArrayAccessExpression) expression).getArrayExpression())) ? true : map.containsKey(expression);
                    KotlinType type = bindingContext.getType(expression);
                    DiagnosticFactory0 diagnosticFactory0 = (DiagnosticFactory) map.get(expression);
                    if (declarationDescriptor != null && ((ErrorUtils.isError(declarationDescriptor) || ErrorUtils.INSTANCE.containsErrorType(type)) && diagnosticFactory0 != Errors.EXPRESSION_EXPECTED_PACKAGE_FOUND)) {
                        debugInfoReporter.reportElementWithErrorType(expression);
                    }
                    if (!z || !zContainsKey) {
                        if (z || zContainsKey) {
                            return;
                        }
                        debugInfoReporter.reportMissingUnresolved(expression);
                        return;
                    }
                    ImmutableSet immutableSet2 = Errors.UNRESOLVED_REFERENCE_DIAGNOSTICS;
                    immutableSet2.getClass();
                    if (CollectionsKt.contains(immutableSet2, diagnosticFactory0)) {
                        debugInfoReporter.reportUnresolvedWithTarget(expression, text);
                    }
                }
            }

            public void visitThisExpression(KtThisExpression expression) {
                expression.getClass();
                ResolvedCall resolvedCall = CallUtilKt.getResolvedCall(expression, bindingContext);
                if (resolvedCall != null) {
                    DebugInfoUtil.INSTANCE.reportIfDynamic(expression, resolvedCall.getResultingDescriptor(), debugInfoReporter);
                }
                super/*org.jetbrains.kotlin.psi.KtVisitorVoid*/.visitThisExpression(expression);
            }
        });
    }
}

package org.jetbrains.kotlin.codegen.inline;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002¨\u0006\t"}, d2 = {"incrementScopeNumbersOfVariables", Argument.Delimiters.none, "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/org/objectweb/asm/Label;", "incrementScopeNumbers", Argument.Delimiters.none, ModuleXmlParser.NAME, "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MethodInlinerKt {
    private static final String incrementScopeNumbers(String str) {
        InlineScopeInfo inlineScopeInfo = InlineScopeUtilsKt.getInlineScopeInfo(str);
        if (inlineScopeInfo == null) {
            return str;
        }
        int scopeNumber = inlineScopeInfo.getScopeNumber();
        Integer callSiteLineNumber = inlineScopeInfo.getCallSiteLineNumber();
        Integer surroundingScopeNumber = inlineScopeInfo.getSurroundingScopeNumber();
        StringBuilder sb = new StringBuilder();
        sb.append(InlineScopeUtilsKt.dropInlineScopeInfo(str));
        sb.append(InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR);
        sb.append(scopeNumber + 1);
        if (callSiteLineNumber != null) {
            sb.append(InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR);
            sb.append(callSiteLineNumber.intValue());
        }
        if (surroundingScopeNumber != null) {
            int iIntValue = surroundingScopeNumber.intValue() != 0 ? surroundingScopeNumber.intValue() + 1 : 0;
            sb.append(InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR);
            sb.append(iIntValue);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int incrementScopeNumbersOfVariables(MethodNode methodNode, Label label) {
        List<LocalVariableNode> list = methodNode.localVariables;
        int i = 0;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        Map<Label, Integer> labelToIndexMap = InlineScopesGeneratorKt.getLabelToIndexMap(methodNode);
        Integer num = labelToIndexMap.get(label);
        if (num != null) {
            int iIntValue = num.intValue();
            for (LocalVariableNode localVariableNode : list) {
                Integer num2 = labelToIndexMap.get(localVariableNode.start.getLabel());
                if (num2 != null) {
                    int iIntValue2 = num2.intValue();
                    if (iIntValue2 < iIntValue) {
                        String str = localVariableNode.name;
                        str.getClass();
                        if (JvmAbi.isFakeLocalVariableForInline(str)) {
                            i++;
                        }
                    }
                    if (iIntValue2 > iIntValue) {
                        String str2 = localVariableNode.name;
                        str2.getClass();
                        localVariableNode.name = incrementScopeNumbers(str2);
                    }
                }
            }
        }
        return i;
    }
}

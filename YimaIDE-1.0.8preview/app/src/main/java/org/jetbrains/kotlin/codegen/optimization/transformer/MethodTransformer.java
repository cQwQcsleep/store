package org.jetbrains.kotlin.codegen.optimization.transformer;

import org.jetbrains.kotlin.codegen.optimization.common.FastMethodAnalyzer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.ExceptionUtilsKt;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public abstract class MethodTransformer {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 7) ? 2 : 3];
        switch (i) {
            case 1:
            case 4:
                objArr[0] = "internalClassName";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 5:
                objArr[0] = "node";
                break;
            case 3:
            case 7:
                objArr[0] = "org/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer";
                break;
            case 6:
                objArr[0] = "interpreter";
                break;
            default:
                objArr[0] = "analyzer";
                break;
        }
        if (i == 3) {
            objArr[1] = "runAnalyzer";
        } else if (i != 7) {
            objArr[1] = "org/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer";
        } else {
            objArr[1] = "analyze";
        }
        if (i != 3) {
            if (i == 4 || i == 5 || i == 6) {
                objArr[2] = "analyze";
            } else if (i != 7) {
                objArr[2] = "runAnalyzer";
            }
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static <V extends Value> Frame<V>[] analyze(String str, MethodNode methodNode, Interpreter<V> interpreter) {
        if (str == null) {
            $$$reportNull$$$0(4);
        }
        if (methodNode == null) {
            $$$reportNull$$$0(5);
        }
        if (interpreter == null) {
            $$$reportNull$$$0(6);
        }
        try {
            Frame<V>[] frameArr = (Frame<V>[]) new FastMethodAnalyzer(str, methodNode, interpreter).analyze();
            if (frameArr == null) {
                $$$reportNull$$$0(7);
            }
            return frameArr;
        } catch (Exception e) {
            throw ExceptionUtilsKt.rethrow(e);
        }
    }

    public abstract void transform(String str, MethodNode methodNode);
}

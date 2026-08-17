package org.jetbrains.kotlin.codegen.inline;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.jetbrains.kotlin.codegen.inline.NameGenerator;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class NameGenerator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final String generatorClass;
    private int nextLambdaIndex = 1;
    private int nextWhenIndex = 1;
    private final Map<String, NameGenerator> subGenerators = new HashMap();

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = "original";
        } else {
            objArr[0] = "org/jetbrains/kotlin/codegen/inline/NameGenerator";
        }
        if (i != 1) {
            objArr[1] = "org/jetbrains/kotlin/codegen/inline/NameGenerator";
        } else {
            objArr[1] = "subGenerator";
        }
        if (i != 1) {
            objArr[2] = "genWhenClassName";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public NameGenerator(String str) {
        this.generatorClass = str;
    }

    public static /* synthetic */ NameGenerator a(NameGenerator nameGenerator, String str) {
        return new NameGenerator(nameGenerator.generatorClass + InlineCodegenUtilsKt.CAPTURED_FIELD_PREFIX + str);
    }

    private String genLambdaClassName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.generatorClass);
        sb.append(InlineCodegenUtilsKt.CAPTURED_FIELD_PREFIX);
        int i = this.nextLambdaIndex;
        this.nextLambdaIndex = i + 1;
        sb.append(i);
        return sb.toString();
    }

    private String genWhenClassName(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.generatorClass);
        sb.append(InlineCodegenUtilsKt.CAPTURED_FIELD_PREFIX);
        int i = this.nextWhenIndex;
        this.nextWhenIndex = i + 1;
        sb.append(i);
        sb.append(WhenMappingTransformationInfo.TRANSFORMED_WHEN_MAPPING_MARKER);
        sb.append(str);
        return sb.toString();
    }

    public String getGeneratorClass() {
        return this.generatorClass;
    }

    public NameGenerator subGenerator(boolean z, String str) {
        String strGenLambdaClassName = z ? genLambdaClassName() : genWhenClassName(str);
        NameGenerator nameGenerator = new NameGenerator(strGenLambdaClassName);
        this.subGenerators.put(strGenLambdaClassName, nameGenerator);
        return nameGenerator;
    }

    public NameGenerator subGenerator(String str) {
        return this.subGenerators.computeIfAbsent(str, new Function() { // from class: jba
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return NameGenerator.a(this.b, (String) obj);
            }
        });
    }
}

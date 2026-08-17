package androidx.compose.compiler.plugins.kotlin.lower;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.ir.declarations.IrClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u0015\u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\u0006¢\u0006\u0002\u0010\u000fR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/ClassStabilityInferredCollection;", "", "<init>", "()V", "classesToValues", "", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "", "addClass", "", "c", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "stabilityInferredParametersValue", "getParametersValue", "descriptor", "(Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;)Ljava/lang/Integer;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ClassStabilityInferredCollection {
    private final Map<ClassDescriptor, Integer> classesToValues = new LinkedHashMap();

    public final void addClass(IrClass c, int stabilityInferredParametersValue) {
        c.getClass();
        this.classesToValues.put(c.getDescriptor(), Integer.valueOf(stabilityInferredParametersValue));
    }

    public final Integer getParametersValue(ClassDescriptor descriptor) {
        descriptor.getClass();
        return this.classesToValues.get(descriptor);
    }
}

package org.jetbrains.kotlin.ir.declarations;

import com.intellij.psi.PsiElement;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Named;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.ScriptDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptorWithAccessors;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\f\r\u000e\u000f\u0010\u0011\u0012B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u0082\u0001\u0007\u0013\u0014\u0015\u0016\u0017\u0018\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource;", "<init>", "()V", "descriptor", "Lorg/jetbrains/kotlin/descriptors/Named;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/Named;", "name", "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "File", "Class", "Script", "CodeFragment", "Function", "Property", "LocalDelegatedProperty", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$Class;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$CodeFragment;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$File;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$Function;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$LocalDelegatedProperty;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$Property;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$Script;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class DescriptorMetadataSource implements MetadataSource {

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$CodeFragment;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$CodeFragment;", "<init>", "()V", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class CodeFragment extends DescriptorMetadataSource implements MetadataSource.CodeFragment {
        public CodeFragment() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$File;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$File;", "descriptors", "", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "<init>", "(Ljava/util/List;)V", "getDescriptors", "()Ljava/util/List;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class File extends DescriptorMetadataSource implements MetadataSource.File {
        private final List<DeclarationDescriptor> descriptors;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public File(List<? extends DeclarationDescriptor> list) {
            super(null);
            list.getClass();
            this.descriptors = list;
        }

        public final List<DeclarationDescriptor> getDescriptors() {
            return this.descriptors;
        }
    }

    public /* synthetic */ DescriptorMetadataSource(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: renamed from: getDescriptor */
    public Named mo259getDescriptor() {
        return null;
    }

    @Override // org.jetbrains.kotlin.ir.declarations.MetadataSource
    public Name getName() {
        Named namedMo259getDescriptor = mo259getDescriptor();
        if (namedMo259getDescriptor != null) {
            return namedMo259getDescriptor.getName();
        }
        return null;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$Class;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Class;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "recordLocalClassType", "", "type", "Lorg/jetbrains/kotlin/name/FqName;", "asFirSymbol", "", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Class extends DescriptorMetadataSource implements MetadataSource.Class {
        private final ClassDescriptor descriptor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Class(ClassDescriptor classDescriptor) {
            super(null);
            classDescriptor.getClass();
            this.descriptor = classDescriptor;
        }

        @Override // org.jetbrains.kotlin.ir.declarations.MetadataSource.Class
        public Object asFirSymbol() {
            return null;
        }

        @Override // org.jetbrains.kotlin.ir.declarations.MetadataSource.Class
        public void recordLocalClassType(FqName type) {
            type.getClass();
        }

        @Override // org.jetbrains.kotlin.ir.declarations.DescriptorMetadataSource
        /* JADX INFO: renamed from: getDescriptor, reason: from getter and merged with bridge method [inline-methods] */
        public ClassDescriptor mo259getDescriptor() {
            return this.descriptor;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$Function;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Function;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Function extends DescriptorMetadataSource implements MetadataSource.Function {
        private final FunctionDescriptor descriptor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Function(FunctionDescriptor functionDescriptor) {
            super(null);
            functionDescriptor.getClass();
            this.descriptor = functionDescriptor;
        }

        @Override // org.jetbrains.kotlin.ir.declarations.DescriptorMetadataSource
        /* JADX INFO: renamed from: getDescriptor, reason: from getter and merged with bridge method [inline-methods] */
        public FunctionDescriptor mo259getDescriptor() {
            return this.descriptor;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$LocalDelegatedProperty;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Property;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptorWithAccessors;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/VariableDescriptorWithAccessors;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/VariableDescriptorWithAccessors;", "isConst", "", "()Z", "psi", "Lcom/intellij/psi/PsiElement;", "getPsi", "()Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class LocalDelegatedProperty extends DescriptorMetadataSource implements MetadataSource.Property {
        private final VariableDescriptorWithAccessors descriptor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LocalDelegatedProperty(VariableDescriptorWithAccessors variableDescriptorWithAccessors) {
            super(null);
            variableDescriptorWithAccessors.getClass();
            this.descriptor = variableDescriptorWithAccessors;
        }

        public PsiElement getPsi() {
            return null;
        }

        public boolean isConst() {
            return mo259getDescriptor().isConst();
        }

        @Override // org.jetbrains.kotlin.ir.declarations.DescriptorMetadataSource
        /* JADX INFO: renamed from: getDescriptor, reason: from getter and merged with bridge method [inline-methods] */
        public VariableDescriptorWithAccessors mo259getDescriptor() {
            return this.descriptor;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0016\u0010\f\u001a\u0004\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$Property;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Property;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "isConst", "", "()Z", "psi", "Lcom/intellij/psi/PsiElement;", "getPsi", "()Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Property extends DescriptorMetadataSource implements MetadataSource.Property {
        private final PropertyDescriptor descriptor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Property(PropertyDescriptor propertyDescriptor) {
            super(null);
            propertyDescriptor.getClass();
            this.descriptor = propertyDescriptor;
        }

        public PsiElement getPsi() {
            return null;
        }

        public boolean isConst() {
            return mo259getDescriptor().isConst();
        }

        @Override // org.jetbrains.kotlin.ir.declarations.DescriptorMetadataSource
        /* JADX INFO: renamed from: getDescriptor, reason: from getter and merged with bridge method [inline-methods] */
        public PropertyDescriptor mo259getDescriptor() {
            return this.descriptor;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource$Script;", "Lorg/jetbrains/kotlin/ir/declarations/DescriptorMetadataSource;", "Lorg/jetbrains/kotlin/ir/declarations/MetadataSource$Script;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/ScriptDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/ScriptDescriptor;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ScriptDescriptor;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Script extends DescriptorMetadataSource implements MetadataSource.Script {
        private final ScriptDescriptor descriptor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Script(ScriptDescriptor scriptDescriptor) {
            super(null);
            scriptDescriptor.getClass();
            this.descriptor = scriptDescriptor;
        }

        @Override // org.jetbrains.kotlin.ir.declarations.DescriptorMetadataSource
        /* JADX INFO: renamed from: getDescriptor, reason: from getter and merged with bridge method [inline-methods] */
        public ScriptDescriptor mo259getDescriptor() {
            return this.descriptor;
        }
    }

    private DescriptorMetadataSource() {
    }
}

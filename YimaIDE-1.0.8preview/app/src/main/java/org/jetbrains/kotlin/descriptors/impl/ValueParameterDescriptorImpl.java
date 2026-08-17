package org.jetbrains.kotlin.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor$DefaultImpls;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.descriptors.impl.ValueParameterDescriptorImpl;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\b\u0016\u0018\u0000 52\u00020\u00012\u00020\u0002:\u000256Bc\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u000f\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010\u001c\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u001d\u001a\u00020\u0002H\u0016J\u0010\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 H\u0016J5\u0010!\u001a\u0002H\"\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H#0%2\u0006\u0010&\u001a\u0002H#H\u0016¢\u0006\u0002\u0010'J\b\u0010(\u001a\u00020\u000fH\u0016J\n\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020,H\u0016J \u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\u0007H\u0016J\b\u00101\u001a\u000202H\u0016J\u000e\u00103\u001a\b\u0012\u0004\u0012\u00020\u000204H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0019R\u0014\u0010\u0011\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0019R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/ValueParameterDescriptorImpl;", "Lorg/jetbrains/kotlin/descriptors/impl/VariableDescriptorImpl;", "Lorg/jetbrains/kotlin/descriptors/ValueParameterDescriptor;", "containingDeclaration", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "original", "index", Argument.Delimiters.none, "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "outType", "Lorg/jetbrains/kotlin/types/KotlinType;", "declaresDefaultValue", Argument.Delimiters.none, "isCrossinline", "isNoinline", "varargElementType", "source", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;Lorg/jetbrains/kotlin/descriptors/ValueParameterDescriptor;ILorg/jetbrains/kotlin/descriptors/annotations/Annotations;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/types/KotlinType;ZZZLorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/descriptors/SourceElement;)V", "getIndex", "()I", "()Z", "getVarargElementType", "()Lorg/jetbrains/kotlin/types/KotlinType;", "getContainingDeclaration", "getOriginal", "substitute", "substitutor", "Lorg/jetbrains/kotlin/types/TypeSubstitutor;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;", "data", "(Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptorVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "isVar", "getCompileTimeInitializer", Argument.Delimiters.none, "cleanCompileTimeInitializerCache", Argument.Delimiters.none, "copy", "newOwner", "newName", "newIndex", "getVisibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "getOverriddenDescriptors", Argument.Delimiters.none, "Companion", "WithDestructuringDeclaration", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class ValueParameterDescriptorImpl extends VariableDescriptorImpl implements ValueParameterDescriptor {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean declaresDefaultValue;
    private final int index;
    private final boolean isCrossinline;
    private final boolean isNoinline;
    private final ValueParameterDescriptor original;
    private final KotlinType varargElementType;

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001By\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u000f\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0012\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ \u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u0007H\u0016R!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001c¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/ValueParameterDescriptorImpl$WithDestructuringDeclaration;", "Lorg/jetbrains/kotlin/descriptors/impl/ValueParameterDescriptorImpl;", "containingDeclaration", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "original", "Lorg/jetbrains/kotlin/descriptors/ValueParameterDescriptor;", "index", Argument.Delimiters.none, "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "outType", "Lorg/jetbrains/kotlin/types/KotlinType;", "declaresDefaultValue", Argument.Delimiters.none, "isCrossinline", "isNoinline", "varargElementType", "source", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "destructuringVariables", "Lkotlin/Function0;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;Lorg/jetbrains/kotlin/descriptors/ValueParameterDescriptor;ILorg/jetbrains/kotlin/descriptors/annotations/Annotations;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/types/KotlinType;ZZZLorg/jetbrains/kotlin/types/KotlinType;Lorg/jetbrains/kotlin/descriptors/SourceElement;Lkotlin/jvm/functions/Function0;)V", "getDestructuringVariables", "()Ljava/util/List;", "destructuringVariables$delegate", "Lkotlin/Lazy;", "copy", "newOwner", "newName", "newIndex", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class WithDestructuringDeclaration extends ValueParameterDescriptorImpl {

        /* JADX INFO: renamed from: destructuringVariables$delegate, reason: from kotlin metadata */
        private final Lazy destructuringVariables;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WithDestructuringDeclaration(CallableDescriptor callableDescriptor, ValueParameterDescriptor valueParameterDescriptor, int i, Annotations annotations, Name name, KotlinType kotlinType, boolean z, boolean z2, boolean z3, KotlinType kotlinType2, SourceElement sourceElement, Function0<? extends List<? extends VariableDescriptor>> function0) {
            super(callableDescriptor, valueParameterDescriptor, i, annotations, name, kotlinType, z, z2, z3, kotlinType2, sourceElement);
            callableDescriptor.getClass();
            annotations.getClass();
            name.getClass();
            kotlinType.getClass();
            sourceElement.getClass();
            function0.getClass();
            this.destructuringVariables = LazyKt.lazy(function0);
        }

        public static List a(WithDestructuringDeclaration withDestructuringDeclaration) {
            return withDestructuringDeclaration.getDestructuringVariables();
        }

        @Override // org.jetbrains.kotlin.descriptors.impl.ValueParameterDescriptorImpl
        public ValueParameterDescriptor copy(CallableDescriptor newOwner, Name newName, int newIndex) {
            newOwner.getClass();
            newName.getClass();
            Annotations annotations = getAnnotations();
            annotations.getClass();
            KotlinType type = getType();
            type.getClass();
            boolean zDeclaresDefaultValue = declaresDefaultValue();
            boolean isCrossinline = getIsCrossinline();
            boolean isNoinline = getIsNoinline();
            KotlinType varargElementType = getVarargElementType();
            SourceElement sourceElement = SourceElement.NO_SOURCE;
            sourceElement.getClass();
            return new WithDestructuringDeclaration(newOwner, null, newIndex, annotations, newName, type, zDeclaresDefaultValue, isCrossinline, isNoinline, varargElementType, sourceElement, new Function0() { // from class: s9f
                public final Object invoke() {
                    return ValueParameterDescriptorImpl.WithDestructuringDeclaration.a(this.b);
                }
            });
        }

        public final List<VariableDescriptor> getDestructuringVariables() {
            return (List) this.destructuringVariables.getValue();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ValueParameterDescriptorImpl(CallableDescriptor callableDescriptor, ValueParameterDescriptor valueParameterDescriptor, int i, Annotations annotations, Name name, KotlinType kotlinType, boolean z, boolean z2, boolean z3, KotlinType kotlinType2, SourceElement sourceElement) {
        super(callableDescriptor, annotations, name, kotlinType, sourceElement);
        callableDescriptor.getClass();
        annotations.getClass();
        name.getClass();
        kotlinType.getClass();
        sourceElement.getClass();
        this.index = i;
        this.declaresDefaultValue = z;
        this.isCrossinline = z2;
        this.isNoinline = z3;
        this.varargElementType = kotlinType2;
        this.original = valueParameterDescriptor == null ? this : valueParameterDescriptor;
    }

    @JvmStatic
    public static final ValueParameterDescriptorImpl createWithDestructuringDeclarations(CallableDescriptor callableDescriptor, ValueParameterDescriptor valueParameterDescriptor, int i, Annotations annotations, Name name, KotlinType kotlinType, boolean z, boolean z2, boolean z3, KotlinType kotlinType2, SourceElement sourceElement, Function0<? extends List<? extends VariableDescriptor>> function0) {
        return INSTANCE.createWithDestructuringDeclarations(callableDescriptor, valueParameterDescriptor, i, annotations, name, kotlinType, z, z2, z3, kotlinType2, sourceElement, function0);
    }

    @JvmStatic
    public static final List<VariableDescriptor> getDestructuringVariablesOrNull(ValueParameterDescriptor valueParameterDescriptor) {
        return INSTANCE.getDestructuringVariablesOrNull(valueParameterDescriptor);
    }

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> visitor, D data) {
        visitor.getClass();
        return (R) visitor.visitValueParameterDescriptor(this, data);
    }

    public void cleanCompileTimeInitializerCache() {
    }

    public ValueParameterDescriptor copy(CallableDescriptor newOwner, Name newName, int newIndex) {
        newOwner.getClass();
        newName.getClass();
        Annotations annotations = getAnnotations();
        annotations.getClass();
        KotlinType type = getType();
        type.getClass();
        boolean zDeclaresDefaultValue = declaresDefaultValue();
        boolean isCrossinline = getIsCrossinline();
        boolean isNoinline = getIsNoinline();
        KotlinType varargElementType = getVarargElementType();
        SourceElement sourceElement = SourceElement.NO_SOURCE;
        sourceElement.getClass();
        return new ValueParameterDescriptorImpl(newOwner, null, newIndex, annotations, newName, type, zDeclaresDefaultValue, isCrossinline, isNoinline, varargElementType, sourceElement);
    }

    public boolean declaresDefaultValue() {
        if (!this.declaresDefaultValue) {
            return false;
        }
        CallableMemberDescriptor containingDeclaration = getContainingDeclaration();
        containingDeclaration.getClass();
        return containingDeclaration.getKind().isReal();
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.DeclarationDescriptorNonRootImpl, org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public CallableDescriptor getContainingDeclaration() {
        DeclarationDescriptor containingDeclaration = super.getContainingDeclaration();
        containingDeclaration.getClass();
        return (CallableDescriptor) containingDeclaration;
    }

    public int getIndex() {
        return this.index;
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.VariableDescriptorImpl
    /* JADX INFO: renamed from: getOriginal, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ValueParameterDescriptor m84getOriginal() {
        ValueParameterDescriptor valueParameterDescriptor = this.original;
        return valueParameterDescriptor == this ? this : valueParameterDescriptor.getOriginal();
    }

    @Override // org.jetbrains.kotlin.descriptors.impl.VariableDescriptorImpl
    public Collection<ValueParameterDescriptor> getOverriddenDescriptors() {
        Collection<? extends CallableDescriptor> overriddenDescriptors = getContainingDeclaration().getOverriddenDescriptors();
        overriddenDescriptors.getClass();
        Collection<? extends CallableDescriptor> collection = overriddenDescriptors;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((CallableDescriptor) it.next()).getValueParameters().get(getIndex()));
        }
        return arrayList;
    }

    public KotlinType getVarargElementType() {
        return this.varargElementType;
    }

    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.LOCAL;
        descriptorVisibility.getClass();
        return descriptorVisibility;
    }

    /* JADX INFO: renamed from: isCrossinline, reason: from getter */
    public boolean getIsCrossinline() {
        return this.isCrossinline;
    }

    public /* bridge */ boolean isLateInit() {
        return ValueParameterDescriptor$DefaultImpls.isLateInit(this);
    }

    /* JADX INFO: renamed from: isNoinline, reason: from getter */
    public boolean getIsNoinline() {
        return this.isNoinline;
    }

    public boolean isVar() {
        return false;
    }

    /* JADX INFO: renamed from: substitute, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ValueParameterDescriptor m171substitute(TypeSubstitutor substitutor) {
        substitutor.getClass();
        if (substitutor.isEmpty()) {
            return this;
        }
        a9g.a();
        return null;
    }

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0007Jz\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001b\u001a\u00020\u001c2\u0014\u0010\u001d\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u001eH\u0007¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/impl/ValueParameterDescriptorImpl$Companion;", Argument.Delimiters.none, "<init>", "()V", "getDestructuringVariablesOrNull", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "valueParameterDescriptor", "Lorg/jetbrains/kotlin/descriptors/ValueParameterDescriptor;", "createWithDestructuringDeclarations", "Lorg/jetbrains/kotlin/descriptors/impl/ValueParameterDescriptorImpl;", "containingDeclaration", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "original", "index", Argument.Delimiters.none, "annotations", "Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "outType", "Lorg/jetbrains/kotlin/types/KotlinType;", "declaresDefaultValue", Argument.Delimiters.none, "isCrossinline", "isNoinline", "varargElementType", "source", "Lorg/jetbrains/kotlin/descriptors/SourceElement;", "destructuringVariables", "Lkotlin/Function0;", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ValueParameterDescriptorImpl createWithDestructuringDeclarations(CallableDescriptor containingDeclaration, ValueParameterDescriptor original, int index, Annotations annotations, Name name, KotlinType outType, boolean declaresDefaultValue, boolean isCrossinline, boolean isNoinline, KotlinType varargElementType, SourceElement source, Function0<? extends List<? extends VariableDescriptor>> destructuringVariables) {
            containingDeclaration.getClass();
            annotations.getClass();
            name.getClass();
            outType.getClass();
            source.getClass();
            return destructuringVariables == null ? new ValueParameterDescriptorImpl(containingDeclaration, original, index, annotations, name, outType, declaresDefaultValue, isCrossinline, isNoinline, varargElementType, source) : new WithDestructuringDeclaration(containingDeclaration, original, index, annotations, name, outType, declaresDefaultValue, isCrossinline, isNoinline, varargElementType, source, destructuringVariables);
        }

        @JvmStatic
        public final List<VariableDescriptor> getDestructuringVariablesOrNull(ValueParameterDescriptor valueParameterDescriptor) {
            valueParameterDescriptor.getClass();
            WithDestructuringDeclaration withDestructuringDeclaration = valueParameterDescriptor instanceof WithDestructuringDeclaration ? (WithDestructuringDeclaration) valueParameterDescriptor : null;
            if (withDestructuringDeclaration != null) {
                return withDestructuringDeclaration.getDestructuringVariables();
            }
            return null;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: getCompileTimeInitializer, reason: merged with bridge method [inline-methods] */
    public Void m168getCompileTimeInitializer() {
        return null;
    }
}

package org.jetbrains.kotlin.descriptors.impl;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorVisitor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.VariableDescriptorWithAccessors;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class LocalVariableDescriptor extends VariableDescriptorWithInitializerImpl implements VariableDescriptorWithAccessors {
    private LocalVariableAccessorDescriptor$Getter getter;
    private final boolean isDelegated;
    private final boolean isLateInit;
    private LocalVariableAccessorDescriptor$Setter setter;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 13 || i == 14) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 13 || i == 14) ? 2 : 3];
        switch (i) {
            case 1:
            case 5:
            case 9:
                objArr[0] = "annotations";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 6:
            case 10:
                objArr[0] = ModuleXmlParser.NAME;
                break;
            case 3:
            case 7:
            case 11:
                objArr[0] = "source";
                break;
            case 4:
            case 8:
            default:
                objArr[0] = "containingDeclaration";
                break;
            case 12:
                objArr[0] = "substitutor";
                break;
            case 13:
            case 14:
                objArr[0] = "org/jetbrains/kotlin/descriptors/impl/LocalVariableDescriptor";
                break;
        }
        if (i == 13) {
            objArr[1] = "substitute";
        } else if (i != 14) {
            objArr[1] = "org/jetbrains/kotlin/descriptors/impl/LocalVariableDescriptor";
        } else {
            objArr[1] = "getVisibility";
        }
        switch (i) {
            case 12:
                objArr[2] = "substitute";
                break;
            case 13:
            case 14:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 13 && i != 14) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalVariableDescriptor(DeclarationDescriptor declarationDescriptor, Annotations annotations, Name name, KotlinType kotlinType, SourceElement sourceElement) {
        this(declarationDescriptor, annotations, name, kotlinType, false, false, false, sourceElement);
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(8);
        }
        if (annotations == null) {
            $$$reportNull$$$0(9);
        }
        if (name == null) {
            $$$reportNull$$$0(10);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(11);
        }
    }

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    public <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d) {
        return (R) declarationDescriptorVisitor.visitVariableDescriptor(this, d);
    }

    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.LOCAL;
        if (descriptorVisibility == null) {
            $$$reportNull$$$0(14);
        }
        return descriptorVisibility;
    }

    public boolean isDelegated() {
        return this.isDelegated;
    }

    public boolean isLateInit() {
        return this.isLateInit;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [org.jetbrains.kotlin.descriptors.impl.LocalVariableAccessorDescriptor$Getter] */
    /* JADX WARN: Type inference failed for: r1v4, types: [org.jetbrains.kotlin.descriptors.impl.LocalVariableAccessorDescriptor$Setter] */
    @Override // org.jetbrains.kotlin.descriptors.impl.VariableDescriptorImpl
    public void setOutType(KotlinType kotlinType) {
        super.setOutType(kotlinType);
        if (this.isDelegated) {
            this.getter = new LocalVariableAccessorDescriptor(this) { // from class: org.jetbrains.kotlin.descriptors.impl.LocalVariableAccessorDescriptor$Getter
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(this, true, (DefaultConstructorMarker) null);
                    this.getClass();
                }
            };
            if (isVar()) {
                this.setter = new LocalVariableAccessorDescriptor(this) { // from class: org.jetbrains.kotlin.descriptors.impl.LocalVariableAccessorDescriptor$Setter
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(this, false, (DefaultConstructorMarker) null);
                        this.getClass();
                    }
                };
            }
        }
    }

    /* JADX INFO: renamed from: substitute, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public LocalVariableDescriptor m137substitute(TypeSubstitutor typeSubstitutor) {
        if (typeSubstitutor == null) {
            $$$reportNull$$$0(12);
        }
        if (typeSubstitutor.isEmpty()) {
            return this;
        }
        a9g.a();
        return null;
    }

    public LocalVariableAccessorDescriptor$Getter getGetter() {
        return this.getter;
    }

    public LocalVariableAccessorDescriptor$Setter getSetter() {
        return this.setter;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LocalVariableDescriptor(DeclarationDescriptor declarationDescriptor, Annotations annotations, Name name, KotlinType kotlinType, boolean z, boolean z2, SourceElement sourceElement) {
        this(declarationDescriptor, annotations, name, kotlinType, z, z2, false, sourceElement);
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(4);
        }
        if (annotations == null) {
            $$$reportNull$$$0(5);
        }
        if (name == null) {
            $$$reportNull$$$0(6);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(7);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LocalVariableDescriptor(DeclarationDescriptor declarationDescriptor, Annotations annotations, Name name, KotlinType kotlinType, boolean z, boolean z2, boolean z3, SourceElement sourceElement) {
        super(declarationDescriptor, annotations, name, kotlinType, z, sourceElement);
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        if (annotations == null) {
            $$$reportNull$$$0(1);
        }
        if (name == null) {
            $$$reportNull$$$0(2);
        }
        if (sourceElement == null) {
            $$$reportNull$$$0(3);
        }
        this.isDelegated = z2;
        this.isLateInit = z3;
    }
}

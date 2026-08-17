package org.jetbrains.kotlin.descriptors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue;
import org.jetbrains.kotlin.resolve.scopes.receivers.ThisClassReceiver;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.util.ModuleVisibilityHelper;
import org.jetbrains.kotlin.utils.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class DescriptorVisibilities {
    public static final ReceiverValue ALWAYS_SUITABLE_RECEIVER;
    public static final DescriptorVisibility DEFAULT_VISIBILITY;

    @Deprecated
    public static final ReceiverValue FALSE_IF_PROTECTED;
    public static final DescriptorVisibility INHERITED;
    public static final DescriptorVisibility INTERNAL;
    public static final DescriptorVisibility INVISIBLE_FAKE;
    public static final Set<DescriptorVisibility> INVISIBLE_FROM_OTHER_MODULES;
    private static final ReceiverValue IRRELEVANT_RECEIVER;
    public static final DescriptorVisibility LOCAL;
    private static final ModuleVisibilityHelper MODULE_VISIBILITY_HELPER;
    private static final Map<DescriptorVisibility, Integer> ORDERED_VISIBILITIES;
    public static final DescriptorVisibility PRIVATE;
    public static final DescriptorVisibility PRIVATE_TO_THIS;
    public static final DescriptorVisibility PROTECTED;
    public static final DescriptorVisibility PUBLIC;
    public static final DescriptorVisibility UNKNOWN;
    private static final Map<Visibility, DescriptorVisibility> visibilitiesMapping;

    /* JADX WARN: Code duplicated, block: B:23:0x003a  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 16 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 16 ? 3 : 2];
        if (i != 1 && i != 3 && i != 5 && i != 7) {
            switch (i) {
                case 9:
                    objArr[0] = "from";
                    break;
                case 10:
                case 12:
                    objArr[0] = "first";
                    break;
                case 11:
                case 13:
                    objArr[0] = "second";
                    break;
                case 14:
                case 15:
                    objArr[0] = "visibility";
                    break;
                case 16:
                    objArr[0] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities";
                    break;
                default:
                    objArr[0] = "what";
                    break;
            }
        } else {
            objArr[0] = "from";
        }
        if (i != 16) {
            objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities";
        } else {
            objArr[1] = "toDescriptorVisibility";
        }
        switch (i) {
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
                objArr[2] = "isVisibleIgnoringReceiver";
                break;
            case 4:
            case 5:
                objArr[2] = "isVisibleWithAnyReceiver";
                break;
            case 6:
            case 7:
                objArr[2] = "inSameFile";
                break;
            case 8:
            case 9:
                objArr[2] = "findInvisibleMember";
                break;
            case 10:
            case 11:
                objArr[2] = "compareLocal";
                break;
            case 12:
            case 13:
                objArr[2] = "compare";
                break;
            case 14:
                objArr[2] = "isPrivate";
                break;
            case 15:
                objArr[2] = "toDescriptorVisibility";
                break;
            case 16:
                break;
            default:
                objArr[2] = "isVisible";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 16) {
            throw new IllegalStateException(str2);
        }
    }

    static {
        DescriptorVisibility descriptorVisibility = new 1(Visibilities.Private.INSTANCE);
        PRIVATE = descriptorVisibility;
        DelegatedDescriptorVisibility delegatedDescriptorVisibility = new DelegatedDescriptorVisibility(Visibilities.PrivateToThis.INSTANCE) { // from class: org.jetbrains.kotlin.descriptors.DescriptorVisibilities.2
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i != 1) {
                    objArr[0] = "what";
                } else {
                    objArr[0] = "from";
                }
                objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$2";
                objArr[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
                DeclarationDescriptor parentOfType;
                if (declarationDescriptorWithVisibility == null) {
                    $$$reportNull$$$0(0);
                }
                if (declarationDescriptor == null) {
                    $$$reportNull$$$0(1);
                }
                if (!DescriptorVisibilities.PRIVATE.isVisible(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor, z)) {
                    return false;
                }
                if (receiverValue == DescriptorVisibilities.ALWAYS_SUITABLE_RECEIVER) {
                    return true;
                }
                if (receiverValue == DescriptorVisibilities.IRRELEVANT_RECEIVER || (parentOfType = DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility, ClassDescriptor.class)) == null || !(receiverValue instanceof ThisClassReceiver)) {
                    return false;
                }
                return ((ThisClassReceiver) receiverValue).getClassDescriptor().getOriginal().equals(parentOfType.m84getOriginal());
            }
        };
        PRIVATE_TO_THIS = delegatedDescriptorVisibility;
        3 r2 = new 3(Visibilities.Protected.INSTANCE);
        PROTECTED = r2;
        DelegatedDescriptorVisibility delegatedDescriptorVisibility2 = new DelegatedDescriptorVisibility(Visibilities.Internal.INSTANCE) { // from class: org.jetbrains.kotlin.descriptors.DescriptorVisibilities.4
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i != 1) {
                    objArr[0] = "what";
                } else {
                    objArr[0] = "from";
                }
                objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$4";
                objArr[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
                if (declarationDescriptorWithVisibility == null) {
                    $$$reportNull$$$0(0);
                }
                if (declarationDescriptor == null) {
                    $$$reportNull$$$0(1);
                }
                if (DescriptorUtils.getContainingModule(declarationDescriptor).shouldSeeInternalsOf(DescriptorUtils.getContainingModule(declarationDescriptorWithVisibility))) {
                    return DescriptorVisibilities.MODULE_VISIBILITY_HELPER.isInFriendModule(declarationDescriptorWithVisibility, declarationDescriptor);
                }
                return false;
            }
        };
        INTERNAL = delegatedDescriptorVisibility2;
        DelegatedDescriptorVisibility delegatedDescriptorVisibility3 = new DelegatedDescriptorVisibility(Visibilities.Public.INSTANCE) { // from class: org.jetbrains.kotlin.descriptors.DescriptorVisibilities.5
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i != 1) {
                    objArr[0] = "what";
                } else {
                    objArr[0] = "from";
                }
                objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$5";
                objArr[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
                if (declarationDescriptorWithVisibility == null) {
                    $$$reportNull$$$0(0);
                }
                if (declarationDescriptor == null) {
                    $$$reportNull$$$0(1);
                }
                return true;
            }
        };
        PUBLIC = delegatedDescriptorVisibility3;
        DelegatedDescriptorVisibility delegatedDescriptorVisibility4 = new DelegatedDescriptorVisibility(Visibilities.Local.INSTANCE) { // from class: org.jetbrains.kotlin.descriptors.DescriptorVisibilities.6
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i != 1) {
                    objArr[0] = "what";
                } else {
                    objArr[0] = "from";
                }
                objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$6";
                objArr[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
                if (declarationDescriptorWithVisibility == null) {
                    $$$reportNull$$$0(0);
                }
                if (declarationDescriptor == null) {
                    $$$reportNull$$$0(1);
                }
                throw new IllegalStateException("This method shouldn't be invoked for LOCAL visibility");
            }
        };
        LOCAL = delegatedDescriptorVisibility4;
        DelegatedDescriptorVisibility delegatedDescriptorVisibility5 = new DelegatedDescriptorVisibility(Visibilities.Inherited.INSTANCE) { // from class: org.jetbrains.kotlin.descriptors.DescriptorVisibilities.7
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i != 1) {
                    objArr[0] = "what";
                } else {
                    objArr[0] = "from";
                }
                objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$7";
                objArr[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
                if (declarationDescriptorWithVisibility == null) {
                    $$$reportNull$$$0(0);
                }
                if (declarationDescriptor == null) {
                    $$$reportNull$$$0(1);
                }
                throw new IllegalStateException("Visibility is unknown yet");
            }
        };
        INHERITED = delegatedDescriptorVisibility5;
        DelegatedDescriptorVisibility delegatedDescriptorVisibility6 = new DelegatedDescriptorVisibility(Visibilities.InvisibleFake.INSTANCE) { // from class: org.jetbrains.kotlin.descriptors.DescriptorVisibilities.8
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i != 1) {
                    objArr[0] = "what";
                } else {
                    objArr[0] = "from";
                }
                objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$8";
                objArr[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
                if (declarationDescriptorWithVisibility == null) {
                    $$$reportNull$$$0(0);
                }
                if (declarationDescriptor == null) {
                    $$$reportNull$$$0(1);
                }
                return false;
            }
        };
        INVISIBLE_FAKE = delegatedDescriptorVisibility6;
        DelegatedDescriptorVisibility delegatedDescriptorVisibility7 = new DelegatedDescriptorVisibility(Visibilities.Unknown.INSTANCE) { // from class: org.jetbrains.kotlin.descriptors.DescriptorVisibilities.9
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i != 1) {
                    objArr[0] = "what";
                } else {
                    objArr[0] = "from";
                }
                objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$9";
                objArr[2] = "isVisible";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // org.jetbrains.kotlin.descriptors.DescriptorVisibility
            public boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
                if (declarationDescriptorWithVisibility == null) {
                    $$$reportNull$$$0(0);
                }
                if (declarationDescriptor == null) {
                    $$$reportNull$$$0(1);
                }
                return false;
            }
        };
        UNKNOWN = delegatedDescriptorVisibility7;
        INVISIBLE_FROM_OTHER_MODULES = Collections.unmodifiableSet(SetsKt.setOf(new DescriptorVisibility[]{descriptorVisibility, delegatedDescriptorVisibility, delegatedDescriptorVisibility2, delegatedDescriptorVisibility4}));
        HashMap mapNewHashMapWithExpectedSize = CollectionsKt.newHashMapWithExpectedSize(4);
        mapNewHashMapWithExpectedSize.put(delegatedDescriptorVisibility, 0);
        mapNewHashMapWithExpectedSize.put(descriptorVisibility, 0);
        mapNewHashMapWithExpectedSize.put(delegatedDescriptorVisibility2, 1);
        mapNewHashMapWithExpectedSize.put(r2, 1);
        mapNewHashMapWithExpectedSize.put(delegatedDescriptorVisibility3, 2);
        ORDERED_VISIBILITIES = Collections.unmodifiableMap(mapNewHashMapWithExpectedSize);
        DEFAULT_VISIBILITY = delegatedDescriptorVisibility3;
        IRRELEVANT_RECEIVER = new ReceiverValue() { // from class: org.jetbrains.kotlin.descriptors.DescriptorVisibilities.10
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
                Object[] objArr = new Object[i != 1 ? 3 : 2];
                if (i != 1) {
                    objArr[0] = "newType";
                } else {
                    objArr[0] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$10";
                }
                if (i != 1) {
                    objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$10";
                } else {
                    objArr[1] = "getOriginal";
                }
                if (i != 1) {
                    objArr[2] = "replaceType";
                }
                String str2 = String.format(str, objArr);
                if (i == 1) {
                    throw new IllegalStateException(str2);
                }
            }

            public ReceiverValue getOriginal() {
                return this;
            }

            public KotlinType getType() {
                throw new IllegalStateException("This method should not be called");
            }

            public ReceiverValue replaceType(KotlinType kotlinType) {
                if (kotlinType == null) {
                    $$$reportNull$$$0(0);
                }
                throw new IllegalStateException("This method should not be called");
            }
        };
        ALWAYS_SUITABLE_RECEIVER = new ReceiverValue() { // from class: org.jetbrains.kotlin.descriptors.DescriptorVisibilities.11
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
                Object[] objArr = new Object[i != 1 ? 3 : 2];
                if (i != 1) {
                    objArr[0] = "newType";
                } else {
                    objArr[0] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$11";
                }
                if (i != 1) {
                    objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$11";
                } else {
                    objArr[1] = "getOriginal";
                }
                if (i != 1) {
                    objArr[2] = "replaceType";
                }
                String str2 = String.format(str, objArr);
                if (i == 1) {
                    throw new IllegalStateException(str2);
                }
            }

            public ReceiverValue getOriginal() {
                return this;
            }

            public KotlinType getType() {
                throw new IllegalStateException("This method should not be called");
            }

            public ReceiverValue replaceType(KotlinType kotlinType) {
                if (kotlinType == null) {
                    $$$reportNull$$$0(0);
                }
                throw new IllegalStateException("This method should not be called");
            }
        };
        FALSE_IF_PROTECTED = new ReceiverValue() { // from class: org.jetbrains.kotlin.descriptors.DescriptorVisibilities.12
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
                Object[] objArr = new Object[i != 1 ? 3 : 2];
                if (i != 1) {
                    objArr[0] = "newType";
                } else {
                    objArr[0] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$12";
                }
                if (i != 1) {
                    objArr[1] = "org/jetbrains/kotlin/descriptors/DescriptorVisibilities$12";
                } else {
                    objArr[1] = "getOriginal";
                }
                if (i != 1) {
                    objArr[2] = "replaceType";
                }
                String str2 = String.format(str, objArr);
                if (i == 1) {
                    throw new IllegalStateException(str2);
                }
            }

            public ReceiverValue getOriginal() {
                return this;
            }

            public KotlinType getType() {
                throw new IllegalStateException("This method should not be called");
            }

            public ReceiverValue replaceType(KotlinType kotlinType) {
                if (kotlinType == null) {
                    $$$reportNull$$$0(0);
                }
                throw new IllegalStateException("This method should not be called");
            }
        };
        Iterator it = ServiceLoader.load(ModuleVisibilityHelper.class, ModuleVisibilityHelper.class.getClassLoader()).iterator();
        MODULE_VISIBILITY_HELPER = it.hasNext() ? (ModuleVisibilityHelper) it.next() : ModuleVisibilityHelper.EMPTY.INSTANCE;
        visibilitiesMapping = new HashMap();
        recordVisibilityMapping(descriptorVisibility);
        recordVisibilityMapping(delegatedDescriptorVisibility);
        recordVisibilityMapping(r2);
        recordVisibilityMapping(delegatedDescriptorVisibility2);
        recordVisibilityMapping(delegatedDescriptorVisibility3);
        recordVisibilityMapping(delegatedDescriptorVisibility4);
        recordVisibilityMapping(delegatedDescriptorVisibility5);
        recordVisibilityMapping(delegatedDescriptorVisibility6);
        recordVisibilityMapping(delegatedDescriptorVisibility7);
    }

    private DescriptorVisibilities() {
    }

    public static Integer compare(DescriptorVisibility descriptorVisibility, DescriptorVisibility descriptorVisibility2) {
        if (descriptorVisibility == null) {
            $$$reportNull$$$0(12);
        }
        if (descriptorVisibility2 == null) {
            $$$reportNull$$$0(13);
        }
        Integer numCompareTo = descriptorVisibility.compareTo(descriptorVisibility2);
        if (numCompareTo != null) {
            return numCompareTo;
        }
        Integer numCompareTo2 = descriptorVisibility2.compareTo(descriptorVisibility);
        if (numCompareTo2 != null) {
            return Integer.valueOf(-numCompareTo2.intValue());
        }
        return null;
    }

    public static DeclarationDescriptorWithVisibility findInvisibleMember(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
        DeclarationDescriptorWithVisibility declarationDescriptorWithVisibilityFindInvisibleMember;
        if (declarationDescriptorWithVisibility == null) {
            $$$reportNull$$$0(8);
        }
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(9);
        }
        for (DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility2 = (DeclarationDescriptorWithVisibility) declarationDescriptorWithVisibility.m84getOriginal(); declarationDescriptorWithVisibility2 != null && declarationDescriptorWithVisibility2.getVisibility() != LOCAL; declarationDescriptorWithVisibility2 = (DeclarationDescriptorWithVisibility) DescriptorUtils.getParentOfType(declarationDescriptorWithVisibility2, DeclarationDescriptorWithVisibility.class)) {
            if (!declarationDescriptorWithVisibility2.getVisibility().isVisible(receiverValue, declarationDescriptorWithVisibility2, declarationDescriptor, z)) {
                return declarationDescriptorWithVisibility2;
            }
        }
        if (!(declarationDescriptorWithVisibility instanceof TypeAliasConstructorDescriptor) || (declarationDescriptorWithVisibilityFindInvisibleMember = findInvisibleMember(receiverValue, ((TypeAliasConstructorDescriptor) declarationDescriptorWithVisibility).getUnderlyingConstructorDescriptor(), declarationDescriptor, z)) == null) {
            return null;
        }
        return declarationDescriptorWithVisibilityFindInvisibleMember;
    }

    public static boolean inSameFile(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(6);
        }
        if (declarationDescriptor2 == null) {
            $$$reportNull$$$0(7);
        }
        SourceFile containingSourceFile = DescriptorUtils.getContainingSourceFile(declarationDescriptor2);
        if (containingSourceFile != SourceFile.NO_SOURCE_FILE) {
            return containingSourceFile.equals(DescriptorUtils.getContainingSourceFile(declarationDescriptor));
        }
        return false;
    }

    public static boolean isPrivate(DescriptorVisibility descriptorVisibility) {
        if (descriptorVisibility == null) {
            $$$reportNull$$$0(14);
        }
        return descriptorVisibility == PRIVATE || descriptorVisibility == PRIVATE_TO_THIS;
    }

    public static boolean isVisible(ReceiverValue receiverValue, DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
        if (declarationDescriptorWithVisibility == null) {
            $$$reportNull$$$0(0);
        }
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(1);
        }
        return findInvisibleMember(receiverValue, declarationDescriptorWithVisibility, declarationDescriptor, z) == null;
    }

    public static boolean isVisibleIgnoringReceiver(DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
        if (declarationDescriptorWithVisibility == null) {
            $$$reportNull$$$0(2);
        }
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(3);
        }
        return findInvisibleMember(ALWAYS_SUITABLE_RECEIVER, declarationDescriptorWithVisibility, declarationDescriptor, z) == null;
    }

    public static boolean isVisibleWithAnyReceiver(DeclarationDescriptorWithVisibility declarationDescriptorWithVisibility, DeclarationDescriptor declarationDescriptor, boolean z) {
        if (declarationDescriptorWithVisibility == null) {
            $$$reportNull$$$0(4);
        }
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(5);
        }
        return findInvisibleMember(IRRELEVANT_RECEIVER, declarationDescriptorWithVisibility, declarationDescriptor, z) == null;
    }

    private static void recordVisibilityMapping(DescriptorVisibility descriptorVisibility) {
        visibilitiesMapping.put(descriptorVisibility.getDelegate(), descriptorVisibility);
    }

    public static DescriptorVisibility toDescriptorVisibility(Visibility visibility) {
        if (visibility == null) {
            $$$reportNull$$$0(15);
        }
        DescriptorVisibility descriptorVisibility = visibilitiesMapping.get(visibility);
        if (descriptorVisibility != null) {
            return descriptorVisibility;
        }
        aca.a("Inapplicable visibility: ", visibility);
        return null;
    }
}

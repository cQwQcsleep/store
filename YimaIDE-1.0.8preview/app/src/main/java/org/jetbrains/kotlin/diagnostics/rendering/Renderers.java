package org.jetbrains.kotlin.diagnostics.rendering;

import com.google.common.collect.Lists;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.util.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.analyzer.ModuleInfo;
import org.jetbrains.kotlin.analyzer.ModuleInfoUtilsKt;
import org.jetbrains.kotlin.analyzer.ModuleSourceInfoBaseKt;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptor;
import org.jetbrains.kotlin.descriptors.ClassifierDescriptorWithTypeParameters;
import org.jetbrains.kotlin.descriptors.ConstructorDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithVisibility;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.MemberDescriptor;
import org.jetbrains.kotlin.descriptors.ModuleDescriptor;
import org.jetbrains.kotlin.descriptors.Named;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyGetterDescriptor;
import org.jetbrains.kotlin.descriptors.PropertySetterDescriptor;
import org.jetbrains.kotlin.descriptors.TypeAliasDescriptor;
import org.jetbrains.kotlin.descriptors.TypeParameterDescriptor;
import org.jetbrains.kotlin.descriptors.ValueParameterDescriptor;
import org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor;
import org.jetbrains.kotlin.diagnostics.WhenMissingCase;
import org.jetbrains.kotlin.diagnostics.rendering.Renderers;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingContext;
import org.jetbrains.kotlin.name.FqNameUnsafe;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.platform.SimplePlatform;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.TargetPlatformKt;
import org.jetbrains.kotlin.psi.Call;
import org.jetbrains.kotlin.psi.KtClass;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtNamedDeclaration;
import org.jetbrains.kotlin.renderer.DescriptorRenderer;
import org.jetbrains.kotlin.renderer.DescriptorRendererOptions;
import org.jetbrains.kotlin.renderer.PropertyAccessorRenderingPolicy;
import org.jetbrains.kotlin.resolve.DescriptorUtils;
import org.jetbrains.kotlin.resolve.MemberComparator;
import org.jetbrains.kotlin.resolve.calls.inference.CallHandle;
import org.jetbrains.kotlin.resolve.calls.inference.CannotCapture;
import org.jetbrains.kotlin.resolve.calls.inference.CapturedTypeConstructor;
import org.jetbrains.kotlin.resolve.calls.inference.CapturedTypeConstructorKt;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintError;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystem;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemStatus;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintSystemUtilsKt;
import org.jetbrains.kotlin.resolve.calls.inference.ConstraintsUtil;
import org.jetbrains.kotlin.resolve.calls.inference.InferenceErrorData;
import org.jetbrains.kotlin.resolve.calls.inference.ParameterConstraintError;
import org.jetbrains.kotlin.resolve.calls.inference.TypeBounds;
import org.jetbrains.kotlin.resolve.calls.inference.TypeVariable;
import org.jetbrains.kotlin.resolve.calls.inference.TypeVariableKt;
import org.jetbrains.kotlin.resolve.calls.inference.constraintPosition.ConstraintPosition;
import org.jetbrains.kotlin.resolve.calls.inference.constraintPosition.ConstraintPositionKind;
import org.jetbrains.kotlin.resolve.calls.inference.constraintPosition.ConstraintPositionKt;
import org.jetbrains.kotlin.resolve.calls.inference.constraintPosition.ValidityConstraintForConstituentType;
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall;
import org.jetbrains.kotlin.resolve.descriptorUtil.DescriptorUtilsKt;
import org.jetbrains.kotlin.types.AbstractTypeConstructor;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeConstructor;
import org.jetbrains.kotlin.types.TypeConstructorSubstitution;
import org.jetbrains.kotlin.types.TypeIntersector;
import org.jetbrains.kotlin.types.TypeProjection;
import org.jetbrains.kotlin.types.TypeProjectionImpl;
import org.jetbrains.kotlin.types.TypeSubstitutor;
import org.jetbrains.kotlin.types.TypeUtils;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.checker.KotlinTypeChecker;
import org.jetbrains.kotlin.utils.IDEAPlatforms;
import org.jetbrains.kotlin.utils.IDEAPluginsCompatibilityAPI;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000²\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0087\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020)0&H\u0002JT\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H00&0/\"\u0004\b\u0000\u001002\f\u00101\u001a\b\u0012\u0004\u0012\u0002H00/H\u0007b\u0002\b2b&\b3\u0012\u000e\b4\u0012\n\b\fJ\u0006\b\n0586\u0012\b\b7\u0012\u0004\b\b(8\u0012\b\b9\u0012\u0004\b\b(:J\u001c\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020<2\u0006\u0010D\u001a\u00020BH\u0007b\u0002\b2J\u001c\u0010E\u001a\u00020B2\u0006\u0010C\u001a\u00020<2\u0006\u0010F\u001a\u00020BH\u0007b\u0002\b2J\u001c\u0010G\u001a\u00020B2\u0006\u0010C\u001a\u00020<2\u0006\u0010D\u001a\u00020BH\u0007b\u0002\b2J\u001c\u0010H\u001a\u00020B2\u0006\u0010C\u001a\u00020<2\u0006\u0010D\u001a\u00020BH\u0007b\u0002\b2J\"\u0010I\u001a\u0004\u0018\u00010B2\u0006\u0010C\u001a\u00020<2\u0006\u0010D\u001a\u00020B2\u0006\u0010J\u001a\u00020KH\u0002J\u001c\u0010L\u001a\u00020B2\u0006\u0010C\u001a\u00020<2\u0006\u0010D\u001a\u00020BH\u0007b\u0002\b2J,\u0010O\u001a\u00020,2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020Q0&2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020Q0/2\u0006\u0010S\u001a\u00020TH\u0002J\u0016\u0010V\u001a\u00020,2\u0006\u0010W\u001a\u00020K2\u0006\u0010X\u001a\u00020YJ\u0018\u0010Z\u001a\u00020,2\u0006\u0010[\u001a\u00020\\2\u0006\u0010X\u001a\u00020YH\u0002J\u0018\u0010]\u001a\u00020,2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020aH\u0002J\u0018\u0010b\u001a\u00020,2\u0006\u0010c\u001a\u00020d2\u0006\u0010X\u001a\u00020YH\u0002J\u0010\u0010e\u001a\u00020,2\u0006\u0010f\u001a\u00020gH\u0002J\"\u0010h\u001a\u00020,2\u0006\u00107\u001a\u00020,2\u0006\u0010C\u001a\u00020<2\b\b\u0002\u0010X\u001a\u00020YH\u0002J\f\u0010i\u001a\u00020,*\u00020,H\u0002J\f\u0010i\u001a\u00020,*\u00020jH\u0002J#\u0010\u007f\u001a\u00020,2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010Q2\u0010\u0010\u0081\u0001\u001a\u000b\u0012\u0004\u0012\u00020Q\u0018\u00010\u0082\u0001J\u001c\u0010\u0083\u0001\u001a\u00020,2\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u00012\u0007\u0010\u0086\u0001\u001a\u00020,R\u0013\u0010\u0004\u001a\u00070\u0005¢\u0006\u0002\b\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001d\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010 \u001a\u00020!8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010\"\u001a\u00020!8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R%\u0010%\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030'0&0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R!\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0&0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010=\u001a\b\u0012\u0004\u0012\u00020<0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010>\u001a\b\u0012\u0004\u0012\u00020<0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010?\u001a\b\u0012\u0004\u0012\u00020<0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u001b\u0010@\u001a\b\u0012\u0004\u0012\u00020<0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R!\u0010M\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020N0&0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R!\u0010U\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q0&0/8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020lX\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010m\u001a\u00020a*\b\u0012\u0004\u0012\u00020o0n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bp\u0010qR!\u0010r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020o0n0\b8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010s\u001a\u00020t8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010u\u001a\u00020v8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010w\u001a\u00020t8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010x\u001a\u00020t8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010y\u001a\u00020t8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010z\u001a\u00020t8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010{\u001a\u00020t8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010|\u001a\u00020t8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R\u0015\u0010}\u001a\u00020t8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000R!\u0010~\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150&0/8\u0006X\u0087\u0004\u0092\u0002\u0002\b\t¢\u0006\u0002\n\u0000¨\u0006\u0088\u0001"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/Renderers;", Argument.Delimiters.none, "<init>", "()V", "LOG", "Lcom/intellij/openapi/diagnostic/Logger;", "Lorg/jetbrains/annotations/NotNull;", "TO_STRING", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "Lkotlin/jvm/JvmField;", "NOT_RENDERED", "NAME", "Lorg/jetbrains/kotlin/descriptors/Named;", "FQ_NAME", "Lorg/jetbrains/kotlin/descriptors/MemberDescriptor;", "MODULE_WITH_PLATFORM", "Lorg/jetbrains/kotlin/descriptors/ModuleDescriptor;", "MODULE", "VISIBILITY", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "DECLARATION_NAME_WITH_KIND", "Lorg/jetbrains/kotlin/descriptors/DeclarationDescriptor;", "CAPITALIZED_DECLARATION_NAME_WITH_KIND_AND_PLATFORM", "NAME_OF_CONTAINING_DECLARATION_OR_FILE", "ELEMENT_TEXT", "Lcom/intellij/psi/PsiElement;", "DECLARATION_NAME", "Lorg/jetbrains/kotlin/psi/KtNamedDeclaration;", "RENDER_CLASS_OR_OBJECT", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "RENDER_CLASS_OR_OBJECT_NAME", "Lorg/jetbrains/kotlin/descriptors/ClassifierDescriptorWithTypeParameters;", "RENDER_TYPE", "Lorg/jetbrains/kotlin/diagnostics/rendering/SmartTypeRenderer;", "RENDER_TYPE_WITH_ANNOTATIONS", "TYPE_PROJECTION", "Lorg/jetbrains/kotlin/types/TypeProjection;", "AMBIGUOUS_CALLS", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/calls/model/ResolvedCall;", "COMPATIBILITY_CANDIDATE", "Lorg/jetbrains/kotlin/descriptors/CallableDescriptor;", "AMBIGUOUS_CALLABLE_REFERENCES", "renderAmbiguousDescriptors", Argument.Delimiters.none, "descriptors", "commaSeparated", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticParameterRenderer;", "T", "itemRenderer", "Lkotlin/jvm/JvmStatic;", "Lorg/jetbrains/kotlin/utils/IDEAPluginsCompatibilityAPI;", "usedIn", "Lorg/jetbrains/kotlin/utils/IDEAPlatforms;", "_213", "message", "Please use the CommonRenderers.commaSeparated instead", "plugins", "Android plugin in IDEA", "TYPE_INFERENCE_CONFLICTING_SUBSTITUTIONS_RENDERER", "Lorg/jetbrains/kotlin/resolve/calls/inference/InferenceErrorData;", "TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR_RENDERER", "TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER_RENDERER", "TYPE_INFERENCE_UPPER_BOUND_VIOLATED_RENDERER", "TYPE_INFERENCE_CANNOT_CAPTURE_TYPES_RENDERER", "renderConflictingSubstitutionsInferenceError", "Lorg/jetbrains/kotlin/diagnostics/rendering/TabledDescriptorRenderer;", "inferenceErrorData", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "renderParameterConstraintError", "renderer", "renderNoInformationForParameterError", "renderUpperBoundViolatedInferenceError", "renderUpperBoundViolatedInferenceErrorForTypeAliasConstructor", "systemWithoutWeakConstraints", "Lorg/jetbrains/kotlin/resolve/calls/inference/ConstraintSystem;", "renderCannotCaptureTypeParameterError", "CLASSES_OR_SEPARATED", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "renderTypes", "types", "Lorg/jetbrains/kotlin/types/KotlinType;", "typeRenderer", "context", "Lorg/jetbrains/kotlin/diagnostics/rendering/RenderingContext;", "RENDER_COLLECTION_OF_TYPES", "renderConstraintSystem", "constraintSystem", "verbosity", "Lorg/jetbrains/kotlin/diagnostics/rendering/Renderers$ConstraintSystemRenderingVerbosity;", "renderTypeBounds", "typeBounds", "Lorg/jetbrains/kotlin/resolve/calls/inference/TypeBounds;", "renderTypeVariable", "typeVariable", "Lorg/jetbrains/kotlin/resolve/calls/inference/TypeVariable;", "includeTypeConstructor", Argument.Delimiters.none, "renderTypeBound", "bound", "Lorg/jetbrains/kotlin/resolve/calls/inference/TypeBounds$Bound;", "renderTypeConstructor", "typeConstructor", "Lorg/jetbrains/kotlin/types/TypeConstructor;", "debugMessage", "wrapIntoQuotes", "Lorg/jetbrains/kotlin/name/Name;", "WHEN_MISSING_LIMIT", Argument.Delimiters.none, "assumesElseBranchOnly", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/WhenMissingCase;", "getAssumesElseBranchOnly", "(Ljava/util/List;)Z", "RENDER_WHEN_MISSING_CASES", "FQ_NAMES_IN_TYPES", "Lorg/jetbrains/kotlin/diagnostics/rendering/SmartDescriptorRenderer;", "FQ_NAMES_IN_TYPES_ANNOTATIONS_WHITELIST", "Lorg/jetbrains/kotlin/diagnostics/rendering/AnnotationsWhitelistDescriptorRenderer;", "FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS", "COMPACT", "COMPACT_WITHOUT_SUPERTYPES", "WITHOUT_MODIFIERS", "SHORT_NAMES_IN_TYPES", "COMPACT_WITH_MODIFIERS", "DEPRECATION_RENDERER", "DESCRIPTORS_ON_NEWLINE_WITH_INDENT", "renderExpressionType", ModuleXmlParser.TYPE, "dataFlowTypes", Argument.Delimiters.none, "renderCallInfo", "fqName", "Lorg/jetbrains/kotlin/name/FqNameUnsafe;", "typeCall", "ConstraintSystemRenderingVerbosity", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Renderers {
    public static final ContextIndependentParameterRenderer<Collection<? extends CallableDescriptor>> AMBIGUOUS_CALLABLE_REFERENCES;
    public static final ContextIndependentParameterRenderer<Collection<? extends ResolvedCall<?>>> AMBIGUOUS_CALLS;
    public static final ContextIndependentParameterRenderer<DeclarationDescriptor> CAPITALIZED_DECLARATION_NAME_WITH_KIND_AND_PLATFORM;
    public static final ContextIndependentParameterRenderer<Collection<? extends ClassDescriptor>> CLASSES_OR_SEPARATED;
    public static final SmartDescriptorRenderer COMPACT;
    public static final SmartDescriptorRenderer COMPACT_WITHOUT_SUPERTYPES;
    public static final SmartDescriptorRenderer COMPACT_WITH_MODIFIERS;
    public static final ContextIndependentParameterRenderer<CallableDescriptor> COMPATIBILITY_CANDIDATE;
    public static final ContextIndependentParameterRenderer<KtNamedDeclaration> DECLARATION_NAME;
    public static final ContextIndependentParameterRenderer<DeclarationDescriptor> DECLARATION_NAME_WITH_KIND;
    public static final SmartDescriptorRenderer DEPRECATION_RENDERER;
    public static final DiagnosticParameterRenderer<Collection<? extends DeclarationDescriptor>> DESCRIPTORS_ON_NEWLINE_WITH_INDENT;
    public static final ContextIndependentParameterRenderer<PsiElement> ELEMENT_TEXT;
    public static final ContextIndependentParameterRenderer<MemberDescriptor> FQ_NAME;
    public static final SmartDescriptorRenderer FQ_NAMES_IN_TYPES;
    public static final AnnotationsWhitelistDescriptorRenderer FQ_NAMES_IN_TYPES_ANNOTATIONS_WHITELIST;
    public static final SmartDescriptorRenderer FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS;
    public static final Renderers INSTANCE = new Renderers();
    private static final Logger LOG;
    public static final ContextIndependentParameterRenderer<ModuleDescriptor> MODULE;
    public static final ContextIndependentParameterRenderer<ModuleDescriptor> MODULE_WITH_PLATFORM;
    public static final ContextIndependentParameterRenderer<Named> NAME;
    public static final ContextIndependentParameterRenderer<DeclarationDescriptor> NAME_OF_CONTAINING_DECLARATION_OR_FILE;
    public static final ContextIndependentParameterRenderer<Object> NOT_RENDERED;
    public static final ContextIndependentParameterRenderer<KtClassOrObject> RENDER_CLASS_OR_OBJECT;
    public static final ContextIndependentParameterRenderer<ClassifierDescriptorWithTypeParameters> RENDER_CLASS_OR_OBJECT_NAME;
    public static final DiagnosticParameterRenderer<Collection<? extends KotlinType>> RENDER_COLLECTION_OF_TYPES;
    public static final SmartTypeRenderer RENDER_TYPE;
    public static final SmartTypeRenderer RENDER_TYPE_WITH_ANNOTATIONS;
    public static final ContextIndependentParameterRenderer<List<? extends WhenMissingCase>> RENDER_WHEN_MISSING_CASES;
    public static final SmartDescriptorRenderer SHORT_NAMES_IN_TYPES;
    public static final ContextIndependentParameterRenderer<Object> TO_STRING;
    public static final ContextIndependentParameterRenderer<InferenceErrorData> TYPE_INFERENCE_CANNOT_CAPTURE_TYPES_RENDERER;
    public static final ContextIndependentParameterRenderer<InferenceErrorData> TYPE_INFERENCE_CONFLICTING_SUBSTITUTIONS_RENDERER;
    public static final ContextIndependentParameterRenderer<InferenceErrorData> TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER_RENDERER;
    public static final ContextIndependentParameterRenderer<InferenceErrorData> TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR_RENDERER;
    public static final ContextIndependentParameterRenderer<InferenceErrorData> TYPE_INFERENCE_UPPER_BOUND_VIOLATED_RENDERER;
    public static final ContextIndependentParameterRenderer<TypeProjection> TYPE_PROJECTION;
    public static final ContextIndependentParameterRenderer<DescriptorVisibility> VISIBILITY;
    private static final int WHEN_MISSING_LIMIT;
    public static final SmartDescriptorRenderer WITHOUT_MODIFIERS;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/Renderers$ConstraintSystemRenderingVerbosity;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "COMPACT", "DEBUG", "EXTRA_VERBOSE", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum ConstraintSystemRenderingVerbosity {
        COMPACT,
        DEBUG,
        EXTRA_VERBOSE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ConstraintSystemRenderingVerbosity> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TypeBounds.BoundKind.values().length];
            try {
                iArr[TypeBounds.BoundKind.LOWER_BOUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TypeBounds.BoundKind.UPPER_BOUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ConstraintSystemRenderingVerbosity.values().length];
            try {
                iArr2[ConstraintSystemRenderingVerbosity.COMPACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[ConstraintSystemRenderingVerbosity.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ConstraintSystemRenderingVerbosity.EXTRA_VERBOSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static {
        Logger logger = Logger.getInstance(Renderers.class);
        logger.getClass();
        LOG = logger;
        TO_STRING = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: pec
            public final Object invoke(Object obj) {
                return Renderers.A(obj);
            }
        });
        NOT_RENDERED = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: pdc
            public final Object invoke(Object obj) {
                return Renderers.h(obj);
            }
        });
        NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: aec
            public final Object invoke(Object obj) {
                return Renderers.g((Named) obj);
            }
        });
        FQ_NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: bec
            public final Object invoke(Object obj) {
                return Renderers.y((MemberDescriptor) obj);
            }
        });
        MODULE_WITH_PLATFORM = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: cec
            public final Object invoke(Object obj) {
                return Renderers.q((ModuleDescriptor) obj);
            }
        });
        MODULE = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: dec
            public final Object invoke(Object obj) {
                return Renderers.z((ModuleDescriptor) obj);
            }
        });
        VISIBILITY = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: fec
            public final Object invoke(Object obj) {
                return Renderers.D((DescriptorVisibility) obj);
            }
        });
        DECLARATION_NAME_WITH_KIND = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: gec
            public final Object invoke(Object obj) {
                return Renderers.a((DeclarationDescriptor) obj);
            }
        });
        CAPITALIZED_DECLARATION_NAME_WITH_KIND_AND_PLATFORM = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: hec
            public final Object invoke(Object obj) {
                return Renderers.u((DeclarationDescriptor) obj);
            }
        });
        NAME_OF_CONTAINING_DECLARATION_OR_FILE = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: iec
            public final Object invoke(Object obj) {
                return Renderers.w((DeclarationDescriptor) obj);
            }
        });
        ELEMENT_TEXT = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: qec
            public final Object invoke(Object obj) {
                return Renderers.c((PsiElement) obj);
            }
        });
        DECLARATION_NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: rec
            public final Object invoke(Object obj) {
                return Renderers.n((KtNamedDeclaration) obj);
            }
        });
        RENDER_CLASS_OR_OBJECT = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: sec
            public final Object invoke(Object obj) {
                return Renderers.C((KtClassOrObject) obj);
            }
        });
        RENDER_CLASS_OR_OBJECT_NAME = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: tec
            public final Object invoke(Object obj) {
                return Renderers.v((ClassifierDescriptorWithTypeParameters) obj);
            }
        });
        DescriptorRenderer descriptorRenderer = DescriptorRenderer.FQ_NAMES_IN_TYPES;
        RENDER_TYPE = new SmartTypeRenderer(descriptorRenderer.withOptions(new Function1() { // from class: jdc
            public final Object invoke(Object obj) {
                return Renderers.p((DescriptorRendererOptions) obj);
            }
        }));
        DescriptorRenderer descriptorRenderer2 = DescriptorRenderer.FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS;
        RENDER_TYPE_WITH_ANNOTATIONS = new SmartTypeRenderer(descriptorRenderer2.withOptions(new Function1() { // from class: kdc
            public final Object invoke(Object obj) {
                return Renderers.L((DescriptorRendererOptions) obj);
            }
        }));
        TYPE_PROJECTION = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: ldc
            public final Object invoke(Object obj) {
                return Renderers.E((TypeProjection) obj);
            }
        });
        AMBIGUOUS_CALLS = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: mdc
            public final Object invoke(Object obj) {
                return Renderers.f((Collection) obj);
            }
        });
        COMPATIBILITY_CANDIDATE = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: ndc
            public final Object invoke(Object obj) {
                return Renderers.b((CallableDescriptor) obj);
            }
        });
        AMBIGUOUS_CALLABLE_REFERENCES = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: odc
            public final Object invoke(Object obj) {
                return Renderers.I((Collection) obj);
            }
        });
        TYPE_INFERENCE_CONFLICTING_SUBSTITUTIONS_RENDERER = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: qdc
            public final Object invoke(Object obj) {
                return Renderers.e((InferenceErrorData) obj);
            }
        });
        TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR_RENDERER = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: rdc
            public final Object invoke(Object obj) {
                return Renderers.F((InferenceErrorData) obj);
            }
        });
        TYPE_INFERENCE_NO_INFORMATION_FOR_PARAMETER_RENDERER = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: sdc
            public final Object invoke(Object obj) {
                return Renderers.m((InferenceErrorData) obj);
            }
        });
        TYPE_INFERENCE_UPPER_BOUND_VIOLATED_RENDERER = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: udc
            public final Object invoke(Object obj) {
                return Renderers.H((InferenceErrorData) obj);
            }
        });
        TYPE_INFERENCE_CANNOT_CAPTURE_TYPES_RENDERER = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: vdc
            public final Object invoke(Object obj) {
                return Renderers.B((InferenceErrorData) obj);
            }
        });
        CLASSES_OR_SEPARATED = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: wdc
            public final Object invoke(Object obj) {
                return Renderers.d((Collection) obj);
            }
        });
        RENDER_COLLECTION_OF_TYPES = DiagnosticParameterRendererKt.ContextDependentRenderer(new Function2() { // from class: xdc
            public final Object invoke(Object obj, Object obj2) {
                return Renderers.l((Collection) obj, (RenderingContext) obj2);
            }
        });
        WHEN_MISSING_LIMIT = 7;
        RENDER_WHEN_MISSING_CASES = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: ydc
            public final Object invoke(Object obj) {
                return Renderers.J((List) obj);
            }
        });
        FQ_NAMES_IN_TYPES = RenderersKt.asRenderer(descriptorRenderer);
        FQ_NAMES_IN_TYPES_ANNOTATIONS_WHITELIST = AnnotationsWhitelistDescriptorRendererKt.withAnnotationsWhitelist$default(descriptorRenderer2, null, 1, null);
        FQ_NAMES_IN_TYPES_WITH_ANNOTATIONS = RenderersKt.asRenderer(descriptorRenderer2);
        COMPACT = RenderersKt.asRenderer(DescriptorRenderer.COMPACT);
        COMPACT_WITHOUT_SUPERTYPES = RenderersKt.asRenderer(DescriptorRenderer.COMPACT_WITHOUT_SUPERTYPES);
        WITHOUT_MODIFIERS = RenderersKt.asRenderer(DescriptorRenderer.WITHOUT_MODIFIERS);
        SHORT_NAMES_IN_TYPES = RenderersKt.asRenderer(DescriptorRenderer.SHORT_NAMES_IN_TYPES);
        COMPACT_WITH_MODIFIERS = RenderersKt.asRenderer(DescriptorRenderer.COMPACT_WITH_MODIFIERS);
        DEPRECATION_RENDERER = RenderersKt.asRenderer(DescriptorRenderer.ONLY_NAMES_WITH_SHORT_TYPES.withOptions(new Function1() { // from class: zdc
            public final Object invoke(Object obj) {
                return Renderers.j((DescriptorRendererOptions) obj);
            }
        }));
        DESCRIPTORS_ON_NEWLINE_WITH_INDENT = new DiagnosticParameterRenderer<Collection<? extends DeclarationDescriptor>>() { // from class: org.jetbrains.kotlin.diagnostics.rendering.Renderers$DESCRIPTORS_ON_NEWLINE_WITH_INDENT$1
            private final MultiplatformDiagnosticRenderingMode mode = new MultiplatformDiagnosticRenderingMode();

            @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRenderer
            public String render(Collection<? extends DeclarationDescriptor> obj, RenderingContext renderingContext) {
                obj.getClass();
                renderingContext.getClass();
                StringBuilder sb = new StringBuilder();
                for (DeclarationDescriptor declarationDescriptor : obj) {
                    this.mode.newLine(sb);
                    this.mode.renderDescriptor(sb, declarationDescriptor, renderingContext, Argument.Delimiters.none);
                }
                return sb.toString();
            }
        };
    }

    private Renderers() {
    }

    public static String A(Object obj) {
        obj.getClass();
        if (obj instanceof DeclarationDescriptor) {
            LOG.warn("Diagnostic renderer TO_STRING was used to render an instance of DeclarationDescriptor.\nThis is usually a bad idea, because descriptors' toString() includes some debug information, which should not be seen by the user.\nDescriptor: " + obj);
        }
        return obj.toString();
    }

    public static String B(InferenceErrorData inferenceErrorData) {
        inferenceErrorData.getClass();
        TabledDescriptorRenderer tabledDescriptorRendererCreate = TabledDescriptorRenderer.create();
        tabledDescriptorRendererCreate.getClass();
        String string = renderCannotCaptureTypeParameterError(inferenceErrorData, tabledDescriptorRendererCreate).toString();
        string.getClass();
        return string;
    }

    public static String C(KtClassOrObject ktClassOrObject) {
        String str;
        ktClassOrObject.getClass();
        String name = ktClassOrObject.getName();
        if (name != null) {
            str = Argument.Delimiters.space + INSTANCE.wrapIntoQuotes(name);
        } else {
            str = Argument.Delimiters.none;
        }
        if (ktClassOrObject instanceof KtClass) {
            return ((KtClass) ktClassOrObject).isInterface() ? "Interface".concat(str) : "Class".concat(str);
        }
        return "Object".concat(str);
    }

    public static String D(DescriptorVisibility descriptorVisibility) {
        descriptorVisibility.getClass();
        return descriptorVisibility.getExternalDisplayName();
    }

    public static String E(TypeProjection typeProjection) {
        typeProjection.getClass();
        if (typeProjection.isStarProjection()) {
            return "*";
        }
        if (typeProjection.getProjectionKind() == Variance.INVARIANT) {
            SmartTypeRenderer smartTypeRenderer = RENDER_TYPE;
            KotlinType type = typeProjection.getType();
            type.getClass();
            return smartTypeRenderer.render(type, RenderingContext.INSTANCE.of(typeProjection.getType()));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(typeProjection.getProjectionKind());
        sb.append(' ');
        SmartTypeRenderer smartTypeRenderer2 = RENDER_TYPE;
        KotlinType type2 = typeProjection.getType();
        type2.getClass();
        sb.append(smartTypeRenderer2.render(type2, RenderingContext.INSTANCE.of(typeProjection.getType())));
        return sb.toString();
    }

    public static String F(InferenceErrorData inferenceErrorData) {
        inferenceErrorData.getClass();
        TabledDescriptorRenderer tabledDescriptorRendererCreate = TabledDescriptorRenderer.create();
        tabledDescriptorRendererCreate.getClass();
        String string = renderParameterConstraintError(inferenceErrorData, tabledDescriptorRendererCreate).toString();
        string.getClass();
        return string;
    }

    public static CharSequence G(DescriptorRenderer descriptorRenderer, KotlinType kotlinType) {
        StringBuilder sb = new StringBuilder("- ");
        kotlinType.getClass();
        sb.append(descriptorRenderer.renderType(kotlinType));
        sb.append(", TypeConstructor info: ");
        sb.append(INSTANCE.renderTypeConstructor(kotlinType.getConstructor()));
        return sb.toString();
    }

    public static String H(InferenceErrorData inferenceErrorData) {
        inferenceErrorData.getClass();
        TabledDescriptorRenderer tabledDescriptorRendererCreate = TabledDescriptorRenderer.create();
        tabledDescriptorRendererCreate.getClass();
        String string = renderUpperBoundViolatedInferenceError(inferenceErrorData, tabledDescriptorRendererCreate).toString();
        string.getClass();
        return string;
    }

    public static String I(Collection collection) {
        collection.getClass();
        return INSTANCE.renderAmbiguousDescriptors(collection);
    }

    public static String J(List list) {
        list.getClass();
        if (INSTANCE.getAssumesElseBranchOnly(list)) {
            return "'else' branch";
        }
        return CollectionsKt.joinToString$default(list, ", ", (CharSequence) null, (CharSequence) null, WHEN_MISSING_LIMIT, (CharSequence) null, new Function1() { // from class: idc
            public final Object invoke(Object obj) {
                return Renderers.RENDER_WHEN_MISSING_CASES$lambda$0$0((WhenMissingCase) obj);
            }
        }, 22, (Object) null) + ' ' + (list.size() > 1 ? "branches" : "branch") + " or 'else' branch instead";
    }

    public static CharSequence K(ConstraintSystemRenderingVerbosity constraintSystemRenderingVerbosity, TypeBounds typeBounds) {
        typeBounds.getClass();
        return INSTANCE.renderTypeBounds(typeBounds, constraintSystemRenderingVerbosity);
    }

    public static Unit L(DescriptorRendererOptions descriptorRendererOptions) {
        descriptorRendererOptions.getClass();
        descriptorRendererOptions.setParameterNamesInFunctionalTypes(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence RENDER_WHEN_MISSING_CASES$lambda$0$0(WhenMissingCase whenMissingCase) {
        whenMissingCase.getClass();
        return "'" + whenMissingCase + '\'';
    }

    public static String a(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        String strAsString = declarationDescriptor.getName().asString();
        strAsString.getClass();
        if (declarationDescriptor instanceof PackageFragmentDescriptor) {
            return "package '" + strAsString + '\'';
        }
        if (declarationDescriptor instanceof ClassDescriptor) {
            return DiagnosticRendererUtilKt.renderKind((ClassDescriptor) declarationDescriptor) + " '" + strAsString + '\'';
        }
        if (declarationDescriptor instanceof TypeAliasDescriptor) {
            return "typealias '" + strAsString + '\'';
        }
        if (declarationDescriptor instanceof TypeAliasConstructorDescriptor) {
            return "constructor of '" + ((TypeAliasConstructorDescriptor) declarationDescriptor).getTypeAliasDescriptor().getName().asString() + '\'';
        }
        if (declarationDescriptor instanceof ConstructorDescriptor) {
            return "constructor of '" + ((ConstructorDescriptor) declarationDescriptor).getConstructedClass().getName().asString() + '\'';
        }
        if (declarationDescriptor instanceof PropertyGetterDescriptor) {
            return "getter of property '" + ((PropertyGetterDescriptor) declarationDescriptor).getCorrespondingProperty().getName().asString() + '\'';
        }
        if (declarationDescriptor instanceof PropertySetterDescriptor) {
            return "setter of property '" + ((PropertySetterDescriptor) declarationDescriptor).getCorrespondingProperty().getName().asString() + '\'';
        }
        if (declarationDescriptor instanceof FunctionDescriptor) {
            return "function '" + strAsString + '\'';
        }
        if (!(declarationDescriptor instanceof PropertyDescriptor)) {
            s22.a("Unexpected declaration kind: ", declarationDescriptor);
            return null;
        }
        return "property '" + strAsString + '\'';
    }

    public static String b(CallableDescriptor callableDescriptor) {
        callableDescriptor.getClass();
        return INSTANCE.renderAmbiguousDescriptors(CollectionsKt.listOf(callableDescriptor));
    }

    public static String c(PsiElement psiElement) {
        psiElement.getClass();
        String text = psiElement.getText();
        text.getClass();
        return text;
    }

    @JvmStatic
    @IDEAPluginsCompatibilityAPI(message = "Please use the CommonRenderers.commaSeparated instead", plugins = "Android plugin in IDEA", usedIn = {IDEAPlatforms._213})
    public static final <T> DiagnosticParameterRenderer<Collection<? extends T>> commaSeparated(DiagnosticParameterRenderer<? super T> itemRenderer) {
        itemRenderer.getClass();
        return CommonRenderers.commaSeparated(itemRenderer);
    }

    public static String d(Collection collection) {
        collection.getClass();
        StringBuilder sb = new StringBuilder();
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            sb.append(DescriptorUtils.getFqName((ClassDescriptor) it.next()).asString());
            i++;
            if (i <= collection.size() - 2) {
                sb.append(", ");
            } else if (i == collection.size() - 1) {
                sb.append(" or ");
            }
        }
        return sb.toString();
    }

    private final String debugMessage(String message, InferenceErrorData inferenceErrorData, ConstraintSystemRenderingVerbosity verbosity) {
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        sb.append("\nConstraint system: \n");
        Renderers renderers = INSTANCE;
        ConstraintSystem constraintSystem = inferenceErrorData.constraintSystem;
        constraintSystem.getClass();
        sb.append(renderers.renderConstraintSystem(constraintSystem, verbosity));
        sb.append("\nDescriptor:\n");
        sb.append(inferenceErrorData.descriptor);
        sb.append("\nExpected type:\n");
        RenderingContext.Empty empty = RenderingContext.Empty.INSTANCE;
        boolean zNoExpectedType = TypeUtils.noExpectedType(inferenceErrorData.expectedType);
        KotlinType kotlinType = inferenceErrorData.expectedType;
        if (zNoExpectedType) {
            sb.append(kotlinType);
        } else {
            SmartTypeRenderer smartTypeRenderer = RENDER_TYPE_WITH_ANNOTATIONS;
            kotlinType.getClass();
            sb.append(smartTypeRenderer.render(kotlinType, (RenderingContext) empty));
        }
        sb.append("\nArgument types:\n");
        KotlinType kotlinType2 = inferenceErrorData.receiverArgumentType;
        if (kotlinType2 != null) {
            sb.append(RENDER_TYPE_WITH_ANNOTATIONS.render(kotlinType2, (RenderingContext) empty));
            sb.append(".");
        }
        sb.append("(");
        List list = inferenceErrorData.valueArgumentsTypes;
        list.getClass();
        sb.append(renderers.renderTypes(list, RENDER_TYPE_WITH_ANNOTATIONS, empty));
        sb.append(")");
        return sb.toString();
    }

    public static /* synthetic */ String debugMessage$default(Renderers renderers, String str, InferenceErrorData inferenceErrorData, ConstraintSystemRenderingVerbosity constraintSystemRenderingVerbosity, int i, Object obj) {
        if ((i & 4) != 0) {
            constraintSystemRenderingVerbosity = ConstraintSystemRenderingVerbosity.DEBUG;
        }
        return renderers.debugMessage(str, inferenceErrorData, constraintSystemRenderingVerbosity);
    }

    public static String e(InferenceErrorData inferenceErrorData) {
        inferenceErrorData.getClass();
        TabledDescriptorRenderer tabledDescriptorRendererCreate = TabledDescriptorRenderer.create();
        tabledDescriptorRendererCreate.getClass();
        String string = renderConflictingSubstitutionsInferenceError(inferenceErrorData, tabledDescriptorRendererCreate).toString();
        string.getClass();
        return string;
    }

    public static String f(Collection collection) {
        collection.getClass();
        Collection collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        Iterator it = collection2.iterator();
        while (it.hasNext()) {
            CallableDescriptor resultingDescriptor = ((ResolvedCall) it.next()).getResultingDescriptor();
            resultingDescriptor.getClass();
            arrayList.add(resultingDescriptor);
        }
        return INSTANCE.renderAmbiguousDescriptors(arrayList);
    }

    public static String g(Named named) {
        named.getClass();
        String strAsString = named.getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    private final boolean getAssumesElseBranchOnly(List<? extends WhenMissingCase> list) {
        List<? extends WhenMissingCase> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        for (WhenMissingCase whenMissingCase : list2) {
            if (Intrinsics.areEqual(whenMissingCase, WhenMissingCase.Unknown.INSTANCE) || (whenMissingCase instanceof WhenMissingCase.ConditionTypeIsExpect)) {
                return true;
            }
        }
        return false;
    }

    public static String h(Object obj) {
        return Argument.Delimiters.none;
    }

    public static boolean i(List list, ConstraintPosition constraintPosition) {
        return list.contains(constraintPosition);
    }

    public static Unit j(DescriptorRendererOptions descriptorRendererOptions) {
        descriptorRendererOptions.getClass();
        descriptorRendererOptions.setWithoutTypeParameters(false);
        descriptorRendererOptions.setReceiverAfterName(false);
        descriptorRendererOptions.setPropertyAccessorRenderingPolicy(PropertyAccessorRenderingPolicy.PRETTY);
        return Unit.INSTANCE;
    }

    public static String l(Collection collection, RenderingContext renderingContext) {
        collection.getClass();
        renderingContext.getClass();
        return INSTANCE.renderTypes(collection, RENDER_TYPE, renderingContext);
    }

    public static String m(InferenceErrorData inferenceErrorData) {
        inferenceErrorData.getClass();
        TabledDescriptorRenderer tabledDescriptorRendererCreate = TabledDescriptorRenderer.create();
        tabledDescriptorRendererCreate.getClass();
        String string = renderNoInformationForParameterError(inferenceErrorData, tabledDescriptorRendererCreate).toString();
        string.getClass();
        return string;
    }

    public static String n(KtNamedDeclaration ktNamedDeclaration) {
        ktNamedDeclaration.getClass();
        String strAsString = ktNamedDeclaration.getNameAsSafeName().asString();
        strAsString.getClass();
        return strAsString;
    }

    public static String o(Function1 function1, Object obj) {
        return (String) function1.invoke(obj);
    }

    public static Unit p(DescriptorRendererOptions descriptorRendererOptions) {
        descriptorRendererOptions.getClass();
        descriptorRendererOptions.setParameterNamesInFunctionalTypes(false);
        return Unit.INSTANCE;
    }

    public static String q(ModuleDescriptor moduleDescriptor) {
        String str;
        moduleDescriptor.getClass();
        TargetPlatform platform = moduleDescriptor.getPlatform();
        String strRender = MODULE.render(moduleDescriptor);
        if (platform == null || TargetPlatformKt.isCommon(platform)) {
            str = Argument.Delimiters.none;
        } else {
            str = " for " + ((SimplePlatform) CollectionsKt.single(platform)).getPlatformName();
        }
        return strRender + str;
    }

    public static CharSequence r(RenderingContext.Impl impl, CallableDescriptor callableDescriptor) {
        callableDescriptor.getClass();
        return FQ_NAMES_IN_TYPES.render((DeclarationDescriptor) callableDescriptor, (RenderingContext) impl);
    }

    private final String renderAmbiguousDescriptors(Collection<? extends CallableDescriptor> descriptors) {
        final RenderingContext.Impl impl = new RenderingContext.Impl(descriptors);
        MemberComparator memberComparator = MemberComparator.INSTANCE;
        memberComparator.getClass();
        return CollectionsKt.joinToString$default(CollectionsKt.sortedWith(descriptors, memberComparator), "\n", "\n", (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: mec
            public final Object invoke(Object obj) {
                return Renderers.r(impl, (CallableDescriptor) obj);
            }
        }, 28, (Object) null);
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [org.jetbrains.kotlin.descriptors.Named, org.jetbrains.kotlin.descriptors.TypeParameterDescriptor] */
    @JvmStatic
    public static final TabledDescriptorRenderer renderCannotCaptureTypeParameterError(InferenceErrorData inferenceErrorData, TabledDescriptorRenderer result) {
        Object next;
        Object next2;
        KotlinType constrainingType;
        inferenceErrorData.getClass();
        result.getClass();
        ConstraintSystem constraintSystem = inferenceErrorData.constraintSystem;
        constraintSystem.getClass();
        Iterator it = constraintSystem.getStatus().getConstraintErrors().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(next instanceof CannotCapture));
        CannotCapture cannotCapture = (CannotCapture) next;
        TypeVariable typeVariable = cannotCapture != null ? cannotCapture.getTypeVariable() : null;
        if (typeVariable == null) {
            LOG.error(debugMessage$default(INSTANCE, "An error 'cannot capture type parameter' is not found in errors", inferenceErrorData, null, 4, null));
            return result;
        }
        CapturedTypeConstructor capturedTypeConstructor = null;
        Iterator it2 = constraintSystem.getTypeBounds(typeVariable).getBounds().iterator();
        do {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
        } while (!CapturedTypeConstructorKt.isCaptured(((TypeBounds.Bound) next2).getConstrainingType()));
        TypeBounds.Bound bound = (TypeBounds.Bound) next2;
        TypeConstructor constructor = (bound == null || (constrainingType = bound.getConstrainingType()) == null) ? null : constrainingType.getConstructor();
        if (constructor instanceof CapturedTypeConstructor) {
            capturedTypeConstructor = (CapturedTypeConstructor) constructor;
        }
        if (capturedTypeConstructor == null) {
            LOG.error(debugMessage$default(INSTANCE, "There is no captured type in bounds, but there is an error 'cannot capture type parameter'", inferenceErrorData, null, 4, null));
            return result;
        }
        ?? originalTypeParameter = typeVariable.getOriginalTypeParameter();
        KotlinType upperBoundsAsType = TypeIntersector.getUpperBoundsAsType((TypeParameterDescriptor) originalTypeParameter);
        upperBoundsAsType.getClass();
        if (!KotlinBuiltIns.isNullableAny(upperBoundsAsType)) {
            capturedTypeConstructor.getProjection().getProjectionKind();
            Variance variance = Variance.IN_VARIANCE;
        }
        StringBuilder sb = new StringBuilder("Type parameter has an upper bound ");
        Renderers renderers = INSTANCE;
        DiagnosticParameterRenderer<KotlinType> typeRenderer = result.getTypeRenderer();
        RenderingContext.Companion companion = RenderingContext.INSTANCE;
        sb.append(renderers.wrapIntoQuotes(typeRenderer.render(upperBoundsAsType, companion.of(upperBoundsAsType))));
        sb.append(" that cannot be satisfied capturing 'in' projection");
        String string = sb.toString();
        TabledDescriptorRenderer.TextRenderer textRendererNewText = TabledDescriptorRenderer.newText();
        StringBuilder sb2 = new StringBuilder();
        Name name = originalTypeParameter.getName();
        name.getClass();
        sb2.append(renderers.wrapIntoQuotes(name));
        sb2.append(" cannot capture ");
        sb2.append(renderers.wrapIntoQuotes(result.getTypeProjectionRenderer().render(capturedTypeConstructor.getProjection(), companion.of(capturedTypeConstructor.getProjection()))));
        sb2.append(". ");
        sb2.append(string);
        result.text(textRendererNewText.normal(sb2.toString()));
        return result;
    }

    @JvmStatic
    public static final TabledDescriptorRenderer renderConflictingSubstitutionsInferenceError(InferenceErrorData inferenceErrorData, TabledDescriptorRenderer result) {
        KotlinType kotlinType;
        inferenceErrorData.getClass();
        result.getClass();
        LOG.assertTrue(inferenceErrorData.constraintSystem.getStatus().hasConflictingConstraints(), debugMessage$default(INSTANCE, "Conflicting substitutions inference error renderer is applied for incorrect status", inferenceErrorData, null, 4, null));
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        Collection<TypeSubstitutor> substitutorsForConflictingParameters = ConstraintsUtil.getSubstitutorsForConflictingParameters(inferenceErrorData.constraintSystem);
        substitutorsForConflictingParameters.getClass();
        for (TypeSubstitutor typeSubstitutor : substitutorsForConflictingParameters) {
            CallableDescriptor callableDescriptor = inferenceErrorData.descriptor;
            typeSubstitutor.getClass();
            arrayListNewArrayList.add(callableDescriptor.substitute(typeSubstitutor));
        }
        TypeVariable firstConflictingVariable = ConstraintsUtil.getFirstConflictingVariable(inferenceErrorData.constraintSystem);
        if (firstConflictingVariable == null) {
            LOG.error(debugMessage$default(INSTANCE, "There is no conflicting parameter for 'conflicting constraints' error.", inferenceErrorData, null, 4, null));
            return result;
        }
        result.text(TabledDescriptorRenderer.newText().normal("Cannot infer type parameter ").strong(firstConflictingVariable.getName()).normal(" in "));
        TabledDescriptorRenderer.TableRenderer tableRendererNewTable = TabledDescriptorRenderer.newTable();
        result.table(tableRendererNewTable);
        tableRendererNewTable.descriptor(inferenceErrorData.descriptor).text("None of the following substitutions");
        Iterator it = arrayListNewArrayList.iterator();
        it.getClass();
        while (it.hasNext()) {
            CallableDescriptor callableDescriptor2 = (CallableDescriptor) it.next();
            KotlinType receiverParameterType = DescriptorUtils.getReceiverParameterType(callableDescriptor2.getExtensionReceiverParameter());
            final HashSet hashSet = new HashSet();
            ArrayList arrayListNewArrayList2 = Lists.newArrayList();
            for (ValueParameterDescriptor valueParameterDescriptor : callableDescriptor2.getValueParameters()) {
                arrayListNewArrayList2.add(valueParameterDescriptor.getType());
                if (valueParameterDescriptor.getIndex() < inferenceErrorData.valueArgumentsTypes.size()) {
                    if (!KotlinTypeChecker.DEFAULT.isSubtypeOf((KotlinType) inferenceErrorData.valueArgumentsTypes.get(valueParameterDescriptor.getIndex()), valueParameterDescriptor.getType())) {
                        hashSet.add(ConstraintPositionKind.VALUE_PARAMETER_POSITION.position(valueParameterDescriptor.getIndex()));
                    }
                }
            }
            if (receiverParameterType != null && (kotlinType = inferenceErrorData.receiverArgumentType) != null && !KotlinTypeChecker.DEFAULT.isSubtypeOf(kotlinType, receiverParameterType)) {
                hashSet.add(ConstraintPositionKind.RECEIVER_POSITION.position());
            }
            tableRendererNewTable.functionArgumentTypeList(receiverParameterType, arrayListNewArrayList2, new Predicate() { // from class: nec
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Renderers.x(hashSet, (ConstraintPosition) obj);
                }
            });
        }
        tableRendererNewTable.text("can be applied to").functionArgumentTypeList(inferenceErrorData.receiverArgumentType, inferenceErrorData.valueArgumentsTypes);
        return result;
    }

    @JvmStatic
    public static final TabledDescriptorRenderer renderNoInformationForParameterError(InferenceErrorData inferenceErrorData, TabledDescriptorRenderer result) {
        Object next;
        inferenceErrorData.getClass();
        result.getClass();
        Iterator it = inferenceErrorData.constraintSystem.getTypeVariables().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!inferenceErrorData.constraintSystem.getTypeBounds((TypeVariable) next).getValues().isEmpty());
        TypeVariable typeVariable = (TypeVariable) next;
        if (typeVariable == null) {
            LOG.error(debugMessage$default(INSTANCE, "There is no unknown parameter for 'no information for parameter error'.", inferenceErrorData, null, 4, null));
            return result;
        }
        TabledDescriptorRenderer tabledDescriptorRendererTable = result.text(TabledDescriptorRenderer.newText().normal("Not enough information to infer parameter ").strong(typeVariable.getName()).normal(" in ")).table(TabledDescriptorRenderer.newTable().descriptor(inferenceErrorData.descriptor).text("Please specify it explicitly."));
        tabledDescriptorRendererTable.getClass();
        return tabledDescriptorRendererTable;
    }

    @JvmStatic
    public static final TabledDescriptorRenderer renderParameterConstraintError(InferenceErrorData inferenceErrorData, TabledDescriptorRenderer renderer) {
        inferenceErrorData.getClass();
        renderer.getClass();
        List constraintErrors = inferenceErrorData.constraintSystem.getStatus().getConstraintErrors();
        ArrayList arrayList = new ArrayList();
        for (Object obj : constraintErrors) {
            if (((ConstraintError) obj) instanceof ParameterConstraintError) {
                arrayList.add(obj);
            }
        }
        final ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ConstraintError) it.next()).getConstraintPosition());
        }
        TabledDescriptorRenderer tabledDescriptorRendererTable = renderer.table(TabledDescriptorRenderer.newTable().descriptor(inferenceErrorData.descriptor).text("cannot be applied to").functionArgumentTypeList(inferenceErrorData.receiverArgumentType, inferenceErrorData.valueArgumentsTypes, new Predicate() { // from class: jec
            @Override // java.util.function.Predicate
            public final boolean test(Object obj2) {
                return Renderers.i(arrayList2, (ConstraintPosition) obj2);
            }
        }));
        tabledDescriptorRendererTable.getClass();
        return tabledDescriptorRendererTable;
    }

    private final String renderTypeBound(TypeBounds.Bound bound, ConstraintSystemRenderingVerbosity verbosity) {
        String str;
        final DescriptorRenderer descriptorRenderer = verbosity == ConstraintSystemRenderingVerbosity.COMPACT ? DescriptorRenderer.SHORT_NAMES_IN_TYPES : DescriptorRenderer.FQ_NAMES_IN_TYPES;
        int i = WhenMappings.$EnumSwitchMapping$0[bound.getKind().ordinal()];
        if (i != 1) {
            str = i != 2 ? ":= " : "<: ";
        } else {
            str = ">: ";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(descriptorRenderer.renderType(bound.getConstrainingType()));
        sb.append(!bound.isProper() ? "*" : Argument.Delimiters.none);
        String string = sb.toString();
        int i2 = WhenMappings.$EnumSwitchMapping$1[verbosity.ordinal()];
        if (i2 == 1) {
            return string;
        }
        if (i2 == 2) {
            return string + " (" + bound.getPosition() + ") ";
        }
        if (i2 != 3) {
            bu8.a();
            return null;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(string);
        sb2.append(" (");
        sb2.append(bound.getPosition());
        sb2.append(")\nConstraining type additional info: ");
        sb2.append(renderTypeConstructor(bound.getConstrainingType().getConstructor()));
        sb2.append("\nSupertypes of constraining type:\n");
        Set allSupertypes = TypeUtils.getAllSupertypes(bound.getConstrainingType());
        allSupertypes.getClass();
        sb2.append(CollectionsKt.joinToString$default(allSupertypes, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: oec
            public final Object invoke(Object obj) {
                return Renderers.G(descriptorRenderer, (KotlinType) obj);
            }
        }, 30, (Object) null));
        sb2.append('\n');
        return sb2.toString();
    }

    private final String renderTypeBounds(TypeBounds typeBounds, final ConstraintSystemRenderingVerbosity verbosity) {
        TypeVariable typeVariable = typeBounds.getTypeVariable();
        ConstraintSystemRenderingVerbosity constraintSystemRenderingVerbosity = ConstraintSystemRenderingVerbosity.EXTRA_VERBOSE;
        String strRenderTypeVariable = renderTypeVariable(typeVariable, verbosity == constraintSystemRenderingVerbosity);
        if (typeBounds.getBounds().isEmpty()) {
            return strRenderTypeVariable;
        }
        return strRenderTypeVariable + (verbosity == constraintSystemRenderingVerbosity ? "\n" : Argument.Delimiters.space) + CollectionsKt.joinToString$default(typeBounds.getBounds(), verbosity != constraintSystemRenderingVerbosity ? ", " : "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: lec
            public final Object invoke(Object obj) {
                return Renderers.t(verbosity, (TypeBounds.Bound) obj);
            }
        }, 30, (Object) null);
    }

    private final String renderTypeConstructor(TypeConstructor typeConstructor) {
        StringBuilder sb = new StringBuilder();
        sb.append(typeConstructor);
        sb.append('[');
        sb.append(typeConstructor.getClass().getName());
        sb.append("], ");
        AbstractTypeConstructor abstractTypeConstructor = typeConstructor instanceof AbstractTypeConstructor ? (AbstractTypeConstructor) typeConstructor : null;
        sb.append(abstractTypeConstructor != null ? abstractTypeConstructor.renderAdditionalDebugInformation() : null);
        return sb.toString();
    }

    private final String renderTypeVariable(TypeVariable typeVariable, boolean includeTypeConstructor) {
        String strAsString = typeVariable.getName().asString();
        strAsString.getClass();
        if (!includeTypeConstructor) {
            return strAsString;
        }
        StringBuilder sb = new StringBuilder("TypeVariable ");
        sb.append(strAsString);
        sb.append(", descriptor = ");
        sb.append(typeVariable.getFreshTypeParameter$org_jetbrains_kotlin_frontend());
        sb.append(", typeConstructor = ");
        TypeConstructor typeConstructor = typeVariable.getFreshTypeParameter$org_jetbrains_kotlin_frontend().getTypeConstructor();
        typeConstructor.getClass();
        sb.append(renderTypeConstructor(typeConstructor));
        return sb.toString();
    }

    private final String renderTypes(Collection<? extends KotlinType> types, final DiagnosticParameterRenderer<? super KotlinType> typeRenderer, final RenderingContext context) {
        final Function1 function1 = new Function1() { // from class: tdc
            public final Object invoke(Object obj) {
                return Renderers.s(typeRenderer, context, (KotlinType) obj);
            }
        };
        String strJoin = StringUtil.join(types, new Function() { // from class: eec
            public final Object fun(Object obj) {
                return Renderers.o(function1, obj);
            }
        }, ", ");
        strJoin.getClass();
        return strJoin;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [org.jetbrains.kotlin.descriptors.Named, org.jetbrains.kotlin.descriptors.TypeParameterDescriptor] */
    @JvmStatic
    public static final TabledDescriptorRenderer renderUpperBoundViolatedInferenceError(InferenceErrorData inferenceErrorData, TabledDescriptorRenderer result) {
        KotlinType kotlinType;
        Object next;
        TabledDescriptorRenderer tabledDescriptorRendererRenderUpperBoundViolatedInferenceErrorForTypeAliasConstructor;
        inferenceErrorData.getClass();
        result.getClass();
        ConstraintSystem constraintSystem = inferenceErrorData.constraintSystem;
        constraintSystem.getClass();
        ConstraintSystemStatus status = constraintSystem.getStatus();
        LOG.assertTrue(status.hasViolatedUpperBound(), debugMessage$default(INSTANCE, "Upper bound violated renderer is applied for incorrect status", inferenceErrorData, null, 4, null));
        ConstraintSystem constraintSystemFilterConstraintsOut = ConstraintSystemUtilsKt.filterConstraintsOut(constraintSystem, ConstraintPositionKind.TYPE_BOUND_POSITION);
        List<TypeParameterDescriptor> typeParameters = inferenceErrorData.descriptor.getTypeParameters();
        typeParameters.getClass();
        Iterator<T> it = typeParameters.iterator();
        do {
            kotlinType = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (ConstraintsUtil.checkUpperBoundIsSatisfied(constraintSystemFilterConstraintsOut, (TypeParameterDescriptor) next, inferenceErrorData.call, true));
        ?? r2 = (TypeParameterDescriptor) next;
        if (r2 == 0) {
            if ((inferenceErrorData.descriptor instanceof TypeAliasConstructorDescriptor) && (tabledDescriptorRendererRenderUpperBoundViolatedInferenceErrorForTypeAliasConstructor = INSTANCE.renderUpperBoundViolatedInferenceErrorForTypeAliasConstructor(inferenceErrorData, result, constraintSystemFilterConstraintsOut)) != null) {
                return tabledDescriptorRendererRenderUpperBoundViolatedInferenceErrorForTypeAliasConstructor;
            }
            if (status.hasConflictingConstraints()) {
                return renderConflictingSubstitutionsInferenceError(inferenceErrorData, result);
            }
            LOG.error(INSTANCE.debugMessage("There is no type parameter with violated upper bound for 'upper bound violated' error", inferenceErrorData, ConstraintSystemRenderingVerbosity.EXTRA_VERBOSE));
            return result;
        }
        Call call = inferenceErrorData.call;
        call.getClass();
        KotlinType value = constraintSystemFilterConstraintsOut.getTypeBounds(ConstraintSystemUtilsKt.descriptorToVariable(constraintSystemFilterConstraintsOut, TypeVariableKt.toHandle(call), (TypeParameterDescriptor) r2)).getValue();
        if (value == null) {
            LOG.error(debugMessage$default(INSTANCE, "System without weak constraints is not successful, there is no value for type parameter " + r2.getName() + "\n: " + constraintSystemFilterConstraintsOut, inferenceErrorData, null, 4, null));
            return result;
        }
        result.text(TabledDescriptorRenderer.newText().normal("Type parameter bound for ").strong(r2.getName()).normal(" in ")).table(TabledDescriptorRenderer.newTable().descriptor(inferenceErrorData.descriptor));
        Iterator<KotlinType> it2 = r2.getUpperBounds().iterator();
        while (it2.hasNext()) {
            KotlinType kotlinTypeSubstitute = constraintSystemFilterConstraintsOut.getResultingSubstitutor().substitute(it2.next(), Variance.INVARIANT);
            if (kotlinTypeSubstitute != null && !KotlinTypeChecker.DEFAULT.isSubtypeOf(value, kotlinTypeSubstitute)) {
                kotlinType = kotlinTypeSubstitute;
                break;
            }
        }
        if (kotlinType != null) {
            RenderingContext renderingContextOf = RenderingContext.INSTANCE.of(value, kotlinType);
            DiagnosticParameterRenderer<KotlinType> typeRenderer = result.getTypeRenderer();
            typeRenderer.getClass();
            result.text(TabledDescriptorRenderer.newText().normal(" is not satisfied: inferred type ").error(typeRenderer.render(value, renderingContextOf)).normal(" is not a subtype of ").strong(typeRenderer.render(kotlinType, renderingContextOf)));
            return result;
        }
        LOG.error(debugMessage$default(INSTANCE, "Type parameter (chosen as violating its upper bound)" + r2.getName() + " violates no bounds after substitution", inferenceErrorData, null, 4, null));
        return result;
    }

    private final TabledDescriptorRenderer renderUpperBoundViolatedInferenceErrorForTypeAliasConstructor(InferenceErrorData inferenceErrorData, TabledDescriptorRenderer result, ConstraintSystem systemWithoutWeakConstraints) {
        final CallableDescriptor callableDescriptor = inferenceErrorData.descriptor;
        callableDescriptor.getClass();
        if (!(callableDescriptor instanceof TypeAliasConstructorDescriptor)) {
            LOG.error("Type alias constructor descriptor expected: " + callableDescriptor);
            return result;
        }
        List typeParameters = ((TypeAliasConstructorDescriptor) callableDescriptor).getTypeParameters();
        typeParameters.getClass();
        List<TypeParameterDescriptor> list = typeParameters;
        final ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (TypeParameterDescriptor typeParameterDescriptor : list) {
            Call call = inferenceErrorData.call;
            call.getClass();
            CallHandle handle = TypeVariableKt.toHandle(call);
            typeParameterDescriptor.getClass();
            arrayList.add(systemWithoutWeakConstraints.getTypeBounds(ConstraintSystemUtilsKt.descriptorToVariable(systemWithoutWeakConstraints, handle, typeParameterDescriptor)).getValue());
        }
        TypeSubstitutor typeSubstitutorCreate = TypeSubstitutor.create(new TypeConstructorSubstitution() { // from class: org.jetbrains.kotlin.diagnostics.rendering.Renderers$renderUpperBoundViolatedInferenceErrorForTypeAliasConstructor$inferredTypeSubstitutor$1
            public TypeProjection get(TypeConstructor key) {
                KotlinType kotlinType;
                key.getClass();
                ClassifierDescriptor declarationDescriptor = key.getDeclarationDescriptor();
                TypeParameterDescriptor typeParameterDescriptor2 = declarationDescriptor instanceof TypeParameterDescriptor ? (TypeParameterDescriptor) declarationDescriptor : null;
                if (typeParameterDescriptor2 == null || !Intrinsics.areEqual(typeParameterDescriptor2.getDeclarationDescriptor(), ((TypeAliasConstructorDescriptor) callableDescriptor).getTypeAliasDescriptor()) || (kotlinType = arrayList.get(typeParameterDescriptor2.getIndex())) == null) {
                    return null;
                }
                return new TypeProjectionImpl(kotlinType);
            }
        });
        typeSubstitutorCreate.getClass();
        Iterator it = inferenceErrorData.constraintSystem.getStatus().getConstraintErrors().iterator();
        while (it.hasNext()) {
            ValidityConstraintForConstituentType validityConstraintForConstituentType = ConstraintPositionKt.getValidityConstraintForConstituentType(((ConstraintError) it.next()).getConstraintPosition());
            if (validityConstraintForConstituentType != null) {
                KotlinType bound = validityConstraintForConstituentType.getBound();
                Variance variance = Variance.INVARIANT;
                KotlinType kotlinTypeSafeSubstitute = typeSubstitutorCreate.safeSubstitute(bound, variance);
                kotlinTypeSafeSubstitute.getClass();
                KotlinType kotlinTypeSafeSubstitute2 = typeSubstitutorCreate.safeSubstitute(validityConstraintForConstituentType.getTypeArgument(), variance);
                kotlinTypeSafeSubstitute2.getClass();
                RenderingContext renderingContextOf = RenderingContext.INSTANCE.of(kotlinTypeSafeSubstitute2, kotlinTypeSafeSubstitute);
                DiagnosticParameterRenderer<KotlinType> typeRenderer = result.getTypeRenderer();
                typeRenderer.getClass();
                result.text(TabledDescriptorRenderer.newText().normal("Type parameter bound for ").strong(validityConstraintForConstituentType.getTypeParameter().getName()).normal(" in type inferred from type alias expansion for ")).table(TabledDescriptorRenderer.newTable().descriptor(inferenceErrorData.descriptor));
                result.text(TabledDescriptorRenderer.newText().normal(" is not satisfied: inferred type ").error(typeRenderer.render(kotlinTypeSafeSubstitute2, renderingContextOf)).normal(" is not a subtype of ").strong(typeRenderer.render(kotlinTypeSafeSubstitute, renderingContextOf)));
                return result;
            }
        }
        return null;
    }

    public static String s(DiagnosticParameterRenderer diagnosticParameterRenderer, RenderingContext renderingContext, KotlinType kotlinType) {
        kotlinType.getClass();
        return diagnosticParameterRenderer.render(kotlinType, renderingContext);
    }

    public static CharSequence t(ConstraintSystemRenderingVerbosity constraintSystemRenderingVerbosity, TypeBounds.Bound bound) {
        bound.getClass();
        return INSTANCE.renderTypeBound(bound, constraintSystemRenderingVerbosity);
    }

    public static String u(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        String strRender = DECLARATION_NAME_WITH_KIND.render(declarationDescriptor);
        if ((declarationDescriptor instanceof MemberDescriptor) && ((MemberDescriptor) declarationDescriptor).isActual()) {
            strRender = "actual " + strRender;
        }
        if (strRender.length() <= 0) {
            return strRender;
        }
        return Character.toUpperCase(strRender.charAt(0)) + strRender.substring(1);
    }

    public static String v(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters) {
        classifierDescriptorWithTypeParameters.getClass();
        return DiagnosticRendererUtilKt.renderKindWithName(classifierDescriptorWithTypeParameters);
    }

    public static String w(DeclarationDescriptor declarationDescriptor) {
        declarationDescriptor.getClass();
        if (DescriptorUtils.isTopLevelDeclaration(declarationDescriptor) && (declarationDescriptor instanceof DeclarationDescriptorWithVisibility) && Intrinsics.areEqual(((DeclarationDescriptorWithVisibility) declarationDescriptor).getVisibility(), DescriptorVisibilities.PRIVATE)) {
            return "file";
        }
        PackageFragmentDescriptor containingDeclaration = declarationDescriptor.getContainingDeclaration();
        if (containingDeclaration instanceof PackageFragmentDescriptor) {
            return INSTANCE.wrapIntoQuotes(containingDeclaration.getFqName().asString());
        }
        Renderers renderers = INSTANCE;
        containingDeclaration.getClass();
        String strAsString = containingDeclaration.getName().asString();
        strAsString.getClass();
        return renderers.wrapIntoQuotes(strAsString);
    }

    private final String wrapIntoQuotes(Name name) {
        return "'" + name.asString() + '\'';
    }

    public static boolean x(HashSet hashSet, ConstraintPosition constraintPosition) {
        return hashSet.contains(constraintPosition);
    }

    public static String y(MemberDescriptor memberDescriptor) {
        memberDescriptor.getClass();
        return DescriptorUtilsKt.getFqNameSafe(memberDescriptor).asString();
    }

    public static String z(ModuleDescriptor moduleDescriptor) {
        ModuleInfo moduleInfoUnwrapPlatform;
        String displayedName;
        moduleDescriptor.getClass();
        ModuleInfo moduleInfo = ModuleInfoUtilsKt.getModuleInfo(moduleDescriptor);
        if (moduleInfo != null && (moduleInfoUnwrapPlatform = ModuleSourceInfoBaseKt.unwrapPlatform(moduleInfo)) != null && (displayedName = moduleInfoUnwrapPlatform.getDisplayedName()) != null) {
            return displayedName;
        }
        String strAsString = moduleDescriptor.getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    public final String renderCallInfo(FqNameUnsafe fqName, String typeCall) {
        String strAsString;
        typeCall.getClass();
        StringBuilder sb = new StringBuilder("fqName: ");
        if (fqName == null || (strAsString = fqName.asString()) == null) {
            strAsString = "fqName is unknown";
        }
        sb.append(strAsString);
        sb.append("; ");
        return sb.toString().concat("typeCall: " + typeCall);
    }

    public final String renderConstraintSystem(ConstraintSystem constraintSystem, final ConstraintSystemRenderingVerbosity verbosity) {
        constraintSystem.getClass();
        verbosity.getClass();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = constraintSystem.getTypeVariables().iterator();
        while (it.hasNext()) {
            linkedHashSet.add(constraintSystem.getTypeBounds((TypeVariable) it.next()));
        }
        return "type parameter bounds:\n" + CollectionsKt.joinToString$default(linkedHashSet, verbosity == ConstraintSystemRenderingVerbosity.EXTRA_VERBOSE ? "\n\n" : "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: kec
            public final Object invoke(Object obj) {
                return Renderers.K(verbosity, (TypeBounds) obj);
            }
        }, 30, (Object) null) + "\n\nstatus:\n" + ConstraintsUtil.getDebugMessageForStatus(constraintSystem.getStatus());
    }

    public final String renderExpressionType(KotlinType type, Set<? extends KotlinType> dataFlowTypes) {
        if (type == null) {
            return "Type is unknown";
        }
        if (dataFlowTypes == null) {
            return DescriptorRenderer.DEBUG_TEXT.renderType(type);
        }
        Set<? extends KotlinType> set = dataFlowTypes;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(DescriptorRenderer.DEBUG_TEXT.renderType((KotlinType) it.next()));
        }
        Set mutableSet = CollectionsKt.toMutableSet(arrayList);
        mutableSet.add(DescriptorRenderer.DEBUG_TEXT.renderType(type));
        return CollectionsKt.joinToString$default(CollectionsKt.sorted(mutableSet), " & ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    private final String wrapIntoQuotes(String str) {
        return "'" + str + '\'';
    }
}

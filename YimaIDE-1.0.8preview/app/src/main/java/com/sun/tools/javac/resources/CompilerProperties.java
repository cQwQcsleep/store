package com.sun.tools.javac.resources;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.FlagsEnum;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.jvm.Profile;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.parser.Tokens;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Name;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CompilerProperties {

    public static class Errors {
        public static final JCDiagnostic.Error AbstractMethCantHaveBody = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "abstract.meth.cant.have.body", new Object[0]);
        public static final JCDiagnostic.Error AddmodsAllModulePathInvalid = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "addmods.all.module.path.invalid", new Object[0]);
        public static final JCDiagnostic.Error AnnotationDeclNotAllowedHere = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.decl.not.allowed.here", new Object[0]);
        public static final JCDiagnostic.Error AnnotationMissingElementValue = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.missing.element.value", new Object[0]);
        public static final JCDiagnostic.Error AnnotationTypeNotApplicable = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.type.not.applicable", new Object[0]);
        public static final JCDiagnostic.Error AnnotationValueMustBeAnnotation = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.value.must.be.annotation", new Object[0]);
        public static final JCDiagnostic.Error AnnotationValueMustBeClassLiteral = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.value.must.be.class.literal", new Object[0]);
        public static final JCDiagnostic.Error AnnotationValueMustBeNameValue = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.value.must.be.name.value", new Object[0]);
        public static final JCDiagnostic.Error AnnotationValueNotAllowableType = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.value.not.allowable.type", new Object[0]);
        public static final JCDiagnostic.Error AnonClassImplIntfNoArgs = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "anon.class.impl.intf.no.args", new Object[0]);
        public static final JCDiagnostic.Error AnonClassImplIntfNoQualForNew = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "anon.class.impl.intf.no.qual.for.new", new Object[0]);
        public static final JCDiagnostic.Error AnonClassImplIntfNoTypeargs = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "anon.class.impl.intf.no.typeargs", new Object[0]);
        public static final JCDiagnostic.Error ArrayAndReceiver = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "array.and.receiver", new Object[0]);
        public static final JCDiagnostic.Error ArrayDimensionMissing = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "array.dimension.missing", new Object[0]);
        public static final JCDiagnostic.Error AssertAsIdentifier = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "assert.as.identifier", new Object[0]);
        public static final JCDiagnostic.Error AttributeValueMustBeConstant = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "attribute.value.must.be.constant", new Object[0]);
        public static final JCDiagnostic.Error BadFunctionalIntfAnno = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.functional.intf.anno", new Object[0]);
        public static final JCDiagnostic.Error BreakOutsideSwitchExpression = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "break.outside.switch.expression", new Object[0]);
        public static final JCDiagnostic.Error BreakOutsideSwitchLoop = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "break.outside.switch.loop", new Object[0]);
        public static final JCDiagnostic.Error CallMustOnlyAppearInCtor = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "call.must.only.appear.in.ctor", new Object[0]);
        public static final JCDiagnostic.Error CannotCreateArrayWithDiamond = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cannot.create.array.with.diamond", new Object[0]);
        public static final JCDiagnostic.Error CannotCreateArrayWithTypeArguments = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cannot.create.array.with.type.arguments", new Object[0]);
        public static final JCDiagnostic.Error CantAssignValToThis = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.assign.val.to.this", new Object[0]);
        public static final JCDiagnostic.Error CantExtendIntfAnnotation = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.extend.intf.annotation", new Object[0]);
        public static final JCDiagnostic.Error CantInheritFromAnon = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.inherit.from.anon", new Object[0]);
        public static final JCDiagnostic.Error CantReadFile = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.read.file", new Object[0]);
        public static final JCDiagnostic.Error CantSelectStaticClassFromParamType = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.select.static.class.from.param.type", new Object[0]);
        public static final JCDiagnostic.Error CatchWithoutTry = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "catch.without.try", new Object[0]);
        public static final JCDiagnostic.Error ClassMethodOrFieldExpected = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "class.method.or.field.expected", new Object[0]);
        public static final JCDiagnostic.Error ClassNotAllowed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "class.not.allowed", new Object[0]);
        public static final JCDiagnostic.Error ConstExprReq = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "const.expr.req", new Object[0]);
        public static final JCDiagnostic.Error ContOutsideLoop = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cont.outside.loop", new Object[0]);
        public static final JCDiagnostic.Error ContinueOutsideSwitchExpression = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "continue.outside.switch.expression", new Object[0]);
        public static final JCDiagnostic.Error CtorCallsNotAllowedHere = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "ctor.calls.not.allowed.here", new Object[0]);
        public static final JCDiagnostic.Error DcBadEntity = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.bad.entity", new Object[0]);
        public static final JCDiagnostic.Error DcBadInlineTag = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.bad.inline.tag", new Object[0]);
        public static final JCDiagnostic.Error DcGtExpected = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.gt.expected", new Object[0]);
        public static final JCDiagnostic.Error DcIdentifierExpected = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.identifier.expected", new Object[0]);
        public static final JCDiagnostic.Error DcInvalidHtml = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.invalid.html", new Object[0]);
        public static final JCDiagnostic.Error DcMalformedHtml = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.malformed.html", new Object[0]);
        public static final JCDiagnostic.Error DcMissingSemicolon = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.missing.semicolon", new Object[0]);
        public static final JCDiagnostic.Error DcNoContent = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.no.content", new Object[0]);
        public static final JCDiagnostic.Error DcNoTagName = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.no.tag.name", new Object[0]);
        public static final JCDiagnostic.Error DcNoTitle = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.no.title", new Object[0]);
        public static final JCDiagnostic.Error DcNoUrl = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.no.url", new Object[0]);
        public static final JCDiagnostic.Error DcRefAnnotationsNotAllowed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.ref.annotations.not.allowed", new Object[0]);
        public static final JCDiagnostic.Error DcRefBadParens = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.ref.bad.parens", new Object[0]);
        public static final JCDiagnostic.Error DcRefSyntaxError = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.ref.syntax.error", new Object[0]);
        public static final JCDiagnostic.Error DcRefUnexpectedInput = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.ref.unexpected.input", new Object[0]);
        public static final JCDiagnostic.Error DcUnexpectedContent = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.unexpected.content", new Object[0]);
        public static final JCDiagnostic.Error DcUnterminatedInlineTag = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.unterminated.inline.tag", new Object[0]);
        public static final JCDiagnostic.Error DcUnterminatedSignature = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.unterminated.signature", new Object[0]);
        public static final JCDiagnostic.Error DcUnterminatedString = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dc.unterminated.string", new Object[0]);
        public static final JCDiagnostic.Error DeconstructionPatternVarNotAllowed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deconstruction.pattern.var.not.allowed", new Object[0]);
        public static final JCDiagnostic.Error DefaultAllowedInIntfAnnotationMember = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "default.allowed.in.intf.annotation.member", new Object[0]);
        public static final JCDiagnostic.Error DefaultAndBothBooleanValues = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "default.and.both.boolean.values", new Object[0]);
        public static final JCDiagnostic.Error DefaultLabelNotAllowed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "default.label.not.allowed", new Object[0]);
        public static final JCDiagnostic.Error DotClassExpected = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "dot.class.expected", new Object[0]);
        public static final JCDiagnostic.Error DuplicateCaseLabel = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.case.label", new Object[0]);
        public static final JCDiagnostic.Error DuplicateDefaultLabel = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.default.label", new Object[0]);
        public static final JCDiagnostic.Error DuplicateUnconditionalPattern = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.unconditional.pattern", new Object[0]);
        public static final JCDiagnostic.Error ElseWithoutIf = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "else.without.if", new Object[0]);
        public static final JCDiagnostic.Error EmptyAArgument = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "empty.A.argument", new Object[0]);
        public static final JCDiagnostic.Error EmptyCharLit = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "empty.char.lit", new Object[0]);
        public static final JCDiagnostic.Error EnumAnnotationMustBeEnumConstant = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.annotation.must.be.enum.constant", new Object[0]);
        public static final JCDiagnostic.Error EnumAsIdentifier = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.as.identifier", new Object[0]);
        public static final JCDiagnostic.Error EnumCantBeGeneric = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.cant.be.generic", new Object[0]);
        public static final JCDiagnostic.Error EnumCantBeInstantiated = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.cant.be.instantiated", new Object[0]);
        public static final JCDiagnostic.Error EnumConstantExpected = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.constant.expected", new Object[0]);
        public static final JCDiagnostic.Error EnumConstantNotExpected = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.constant.not.expected", new Object[0]);
        public static final JCDiagnostic.Error EnumLabelMustBeEnumConstant = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.label.must.be.enum.constant", new Object[0]);
        public static final JCDiagnostic.Error EnumLabelMustBeUnqualifiedEnum = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.label.must.be.unqualified.enum", new Object[0]);
        public static final JCDiagnostic.Error EnumNoFinalize = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.no.finalize", new Object[0]);
        public static final JCDiagnostic.Error EnumNoSubclassing = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.no.subclassing", new Object[0]);
        public static final JCDiagnostic.Error EnumTypesNotExtensible = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enum.types.not.extensible", new Object[0]);
        public static final JCDiagnostic.Error Error = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "error", new Object[0]);
        public static final JCDiagnostic.Error ExpectedModule = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "expected.module", new Object[0]);
        public static final JCDiagnostic.Error ExpectedModuleOrOpen = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "expected.module.or.open", new Object[0]);
        public static final JCDiagnostic.Error ExpressionNotAllowableAsAnnotationValue = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "expression.not.allowable.as.annotation.value", new Object[0]);
        public static final JCDiagnostic.Error ExtraneousSemicolon = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "extraneous.semicolon", new Object[0]);
        public static final JCDiagnostic.Error FileSbOnSourceOrPatchPathForModule = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.sb.on.source.or.patch.path.for.module", new Object[0]);
        public static final JCDiagnostic.Error FinallyWithoutTry = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "finally.without.try", new Object[0]);
        public static final JCDiagnostic.Error FlowsThroughFromPattern = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "flows.through.from.pattern", new Object[0]);
        public static final JCDiagnostic.Error FlowsThroughToPattern = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "flows.through.to.pattern", new Object[0]);
        public static final JCDiagnostic.Error FpNumberTooLarge = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "fp.number.too.large", new Object[0]);
        public static final JCDiagnostic.Error FpNumberTooSmall = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "fp.number.too.small", new Object[0]);
        public static final JCDiagnostic.Error GenericArrayCreation = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "generic.array.creation", new Object[0]);
        public static final JCDiagnostic.Error GenericThrowable = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "generic.throwable", new Object[0]);
        public static final JCDiagnostic.Error GuardHasConstantExpressionFalse = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "guard.has.constant.expression.false", new Object[0]);
        public static final JCDiagnostic.Error GuardNotAllowed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "guard.not.allowed", new Object[0]);
        public static final JCDiagnostic.Error IllegalArrayCreationBothDimensionAndInitialization = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.array.creation.both.dimension.and.initialization", new Object[0]);
        public static final JCDiagnostic.Error IllegalCharLiteralMultipleSurrogates = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.char.literal.multiple.surrogates", new Object[0]);
        public static final JCDiagnostic.Error IllegalDigitInBinaryLiteral = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.digit.in.binary.literal", new Object[0]);
        public static final JCDiagnostic.Error IllegalDigitInOctalLiteral = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.digit.in.octal.literal", new Object[0]);
        public static final JCDiagnostic.Error IllegalDot = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.dot", new Object[0]);
        public static final JCDiagnostic.Error IllegalEnumStaticRef = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.enum.static.ref", new Object[0]);
        public static final JCDiagnostic.Error IllegalEscChar = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.esc.char", new Object[0]);
        public static final JCDiagnostic.Error IllegalForwardRef = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.forward.ref", new Object[0]);
        public static final JCDiagnostic.Error IllegalLineEndInCharLit = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.line.end.in.char.lit", new Object[0]);
        public static final JCDiagnostic.Error IllegalNonasciiDigit = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.nonascii.digit", new Object[0]);
        public static final JCDiagnostic.Error IllegalParenthesizedExpression = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.parenthesized.expression", new Object[0]);
        public static final JCDiagnostic.Error IllegalSelfRef = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.self.ref", new Object[0]);
        public static final JCDiagnostic.Error IllegalStartOfExpr = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.start.of.expr", new Object[0]);
        public static final JCDiagnostic.Error IllegalStartOfStmt = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.start.of.stmt", new Object[0]);
        public static final JCDiagnostic.Error IllegalStartOfType = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.start.of.type", new Object[0]);
        public static final JCDiagnostic.Error IllegalTextBlockOpen = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.text.block.open", new Object[0]);
        public static final JCDiagnostic.Error IllegalUnderscore = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.underscore", new Object[0]);
        public static final JCDiagnostic.Error IllegalUnicodeEsc = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.unicode.esc", new Object[0]);
        public static final JCDiagnostic.Error ImplicitClassDoesNotHaveMainMethod = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "implicit.class.does.not.have.main.method", new Object[0]);
        public static final JCDiagnostic.Error ImplicitClassShouldNotHavePackageDeclaration = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "implicit.class.should.not.have.package.declaration", new Object[0]);
        public static final JCDiagnostic.Error ImproperlyFormedTypeInnerRawParam = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "improperly.formed.type.inner.raw.param", new Object[0]);
        public static final JCDiagnostic.Error ImproperlyFormedTypeParamMissing = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "improperly.formed.type.param.missing", new Object[0]);
        public static final JCDiagnostic.Error InitializerMustBeAbleToCompleteNormally = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "initializer.must.be.able.to.complete.normally", new Object[0]);
        public static final JCDiagnostic.Error InitializerNotAllowed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "initializer.not.allowed", new Object[0]);
        public static final JCDiagnostic.Error InstanceInitializerNotAllowedInRecords = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "instance.initializer.not.allowed.in.records", new Object[0]);
        public static final JCDiagnostic.Error IntfAnnotationMembersCantHaveParams = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "intf.annotation.members.cant.have.params", new Object[0]);
        public static final JCDiagnostic.Error IntfAnnotationMembersCantHaveTypeParams = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "intf.annotation.members.cant.have.type.params", new Object[0]);
        public static final JCDiagnostic.Error IntfExpectedHere = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "intf.expected.here", new Object[0]);
        public static final JCDiagnostic.Error IntfMethCantHaveBody = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "intf.meth.cant.have.body", new Object[0]);
        public static final JCDiagnostic.Error IntfNotAllowedHere = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "intf.not.allowed.here", new Object[0]);
        public static final JCDiagnostic.Error InvalidAnnotationMemberType = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.annotation.member.type", new Object[0]);
        public static final JCDiagnostic.Error InvalidBinaryNumber = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.binary.number", new Object[0]);
        public static final JCDiagnostic.Error InvalidCaseLabelCombination = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.case.label.combination", new Object[0]);
        public static final JCDiagnostic.Error InvalidHexNumber = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.hex.number", new Object[0]);
        public static final JCDiagnostic.Error InvalidMethDeclRetTypeReq = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.meth.decl.ret.type.req", new Object[0]);
        public static final JCDiagnostic.Error InvalidModuleDirective = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.module.directive", new Object[0]);
        public static final JCDiagnostic.Error InvalidYield = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.yield", new Object[0]);
        public static final JCDiagnostic.Error IoException = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "io.exception", new Object[0]);
        public static final JCDiagnostic.Error LambdaBodyNeitherValueNorVoidCompatible = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "lambda.body.neither.value.nor.void.compatible", new Object[0]);
        public static final JCDiagnostic.Error LimitCode = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "limit.code", new Object[0]);
        public static final JCDiagnostic.Error LimitCodeTooLargeForTryStmt = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "limit.code.too.large.for.try.stmt", new Object[0]);
        public static final JCDiagnostic.Error LimitDimensions = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "limit.dimensions", new Object[0]);
        public static final JCDiagnostic.Error LimitLocals = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "limit.locals", new Object[0]);
        public static final JCDiagnostic.Error LimitParameters = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "limit.parameters", new Object[0]);
        public static final JCDiagnostic.Error LimitPool = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "limit.pool", new Object[0]);
        public static final JCDiagnostic.Error LimitPoolInClass = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "limit.pool.in.class", new Object[0]);
        public static final JCDiagnostic.Error LimitStack = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "limit.stack", new Object[0]);
        public static final JCDiagnostic.Error LimitString = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "limit.string", new Object[0]);
        public static final JCDiagnostic.Error LocalEnum = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.enum", new Object[0]);
        public static final JCDiagnostic.Error MalformedFpLit = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "malformed.fp.lit", new Object[0]);
        public static final JCDiagnostic.Error MatchBindingExists = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "match.binding.exists", new Object[0]);
        public static final JCDiagnostic.Error MissingRetStmt = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "missing.ret.stmt", new Object[0]);
        public static final JCDiagnostic.Error ModuleDeclSbInModuleInfoJava = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "module.decl.sb.in.module-info.java", new Object[0]);
        public static final JCDiagnostic.Error ModuleNotFoundOnModuleSourcePath = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "module.not.found.on.module.source.path", new Object[0]);
        public static final JCDiagnostic.Error ModulesourcepathMustBeSpecifiedWithDashMOption = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "modulesourcepath.must.be.specified.with.dash.m.option", new Object[0]);
        public static final JCDiagnostic.Error MultipleValuesForModuleSourcePath = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "multiple.values.for.module.source.path", new Object[0]);
        public static final JCDiagnostic.Error NameReservedForInternalUse = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "name.reserved.for.internal.use", new Object[0]);
        public static final JCDiagnostic.Error NativeMethCantHaveBody = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "native.meth.cant.have.body", new Object[0]);
        public static final JCDiagnostic.Error NewNotAllowedInAnnotation = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "new.not.allowed.in.annotation", new Object[0]);
        public static final JCDiagnostic.Error NoAnnotationsOnDotClass = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.annotations.on.dot.class", new Object[0]);
        public static final JCDiagnostic.Error NoIntfExpectedHere = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.intf.expected.here", new Object[0]);
        public static final JCDiagnostic.Error NoJavaLang = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.java.lang", new Object[0]);
        public static final JCDiagnostic.Error NoMatchEntry = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.match.entry", new Object[0]);
        public static final JCDiagnostic.Error NoOpensUnlessStrong = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.opens.unless.strong", new Object[0]);
        public static final JCDiagnostic.Error NoOutputDir = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.output.dir", new Object[0]);
        public static final JCDiagnostic.Error NoPkgInModuleInfoJava = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.pkg.in.module-info.java", new Object[0]);
        public static final JCDiagnostic.Error NoSourceFiles = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.source.files", new Object[0]);
        public static final JCDiagnostic.Error NoSourceFilesClasses = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.source.files.classes", new Object[0]);
        public static final JCDiagnostic.Error NoSwitchExpression = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.switch.expression", new Object[0]);
        public static final JCDiagnostic.Error NoSwitchExpressionQualify = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.switch.expression.qualify", new Object[0]);
        public static final JCDiagnostic.Error NonSealedOrSealedExpected = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "non.sealed.or.sealed.expected", new Object[0]);
        public static final JCDiagnostic.Error NonSealedSealedOrFinalExpected = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "non.sealed.sealed.or.final.expected", new Object[0]);
        public static final JCDiagnostic.Error NotExhaustive = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.exhaustive", new Object[0]);
        public static final JCDiagnostic.Error NotExhaustiveStatement = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.exhaustive.statement", new Object[0]);
        public static final JCDiagnostic.Error NotInModuleOnModuleSourcePath = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.in.module.on.module.source.path", new Object[0]);
        public static final JCDiagnostic.Error NotStmt = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.stmt", new Object[0]);
        public static final JCDiagnostic.Error OutputDirMustBeSpecifiedWithDashMOption = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "output.dir.must.be.specified.with.dash.m.option", new Object[0]);
        public static final JCDiagnostic.Error PatternDominated = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "pattern.dominated", new Object[0]);
        public static final JCDiagnostic.Error PatternExpected = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "pattern.expected", new Object[0]);
        public static final JCDiagnostic.Error PatternOrEnumReq = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "pattern.or.enum.req", new Object[0]);
        public static final JCDiagnostic.Error PatternTypeCannotInfer = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "pattern.type.cannot.infer", new Object[0]);
        public static final JCDiagnostic.Error PkgAnnotationsSbInPackageInfoJava = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "pkg.annotations.sb.in.package-info.java", new Object[0]);
        public static final JCDiagnostic.Error PrematureEof = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "premature.eof", new Object[0]);
        public static final JCDiagnostic.Error PreviewWithoutSourceOrRelease = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.without.source.or.release", new Object[0]);
        public static final JCDiagnostic.Error ProcCantCreateLoader = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.cant.create.loader", new Object[0]);
        public static final JCDiagnostic.Error ProcServiceProblem = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.service.problem", new Object[0]);
        public static final JCDiagnostic.Error ProcessorpathNoProcessormodulepath = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "processorpath.no.processormodulepath", new Object[0]);
        public static final JCDiagnostic.Error ProfileBootclasspathConflict = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "profile.bootclasspath.conflict", new Object[0]);
        public static final JCDiagnostic.Error ReceiverParameterNotApplicableConstructorToplevelClass = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "receiver.parameter.not.applicable.constructor.toplevel.class", new Object[0]);
        public static final JCDiagnostic.Error RecordCannotDeclareInstanceFields = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "record.cannot.declare.instance.fields", new Object[0]);
        public static final JCDiagnostic.Error RecordCantDeclareFieldModifiers = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "record.cant.declare.field.modifiers", new Object[0]);
        public static final JCDiagnostic.Error RecordComponentAndOldArraySyntax = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "record.component.and.old.array.syntax", new Object[0]);
        public static final JCDiagnostic.Error RecordPatternsAnnotationsNotAllowed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "record.patterns.annotations.not.allowed", new Object[0]);
        public static final JCDiagnostic.Error RecursiveCtorInvocation = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "recursive.ctor.invocation", new Object[0]);
        public static final JCDiagnostic.Error RedundantSuperclassInit = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "redundant.superclass.init", new Object[0]);
        public static final JCDiagnostic.Error RepeatedAnnotationTarget = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "repeated.annotation.target", new Object[0]);
        public static final JCDiagnostic.Error RepeatedInterface = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "repeated.interface", new Object[0]);
        public static final JCDiagnostic.Error RepeatedModifier = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "repeated.modifier", new Object[0]);
        public static final JCDiagnostic.Error RetOutsideMeth = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "ret.outside.meth", new Object[0]);
        public static final JCDiagnostic.Error ReturnBeforeSuperclassInitialized = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "return.before.superclass.initialized", new Object[0]);
        public static final JCDiagnostic.Error ReturnOutsideSwitchExpression = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "return.outside.switch.expression", new Object[0]);
        public static final JCDiagnostic.Error RuleCompletesNormally = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "rule.completes.normally", new Object[0]);
        public static final JCDiagnostic.Error SealedClassMustHaveSubclasses = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "sealed.class.must.have.subclasses", new Object[0]);
        public static final JCDiagnostic.Error SealedOrNonSealedLocalClassesNotAllowed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "sealed.or.non.sealed.local.classes.not.allowed", new Object[0]);
        public static final JCDiagnostic.Error ServiceImplementationMustBeSubtypeOfServiceInterface = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "service.implementation.must.be.subtype.of.service.interface", new Object[0]);
        public static final JCDiagnostic.Error ServiceImplementationProviderReturnMustBeSubtypeOfServiceInterface = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "service.implementation.provider.return.must.be.subtype.of.service.interface", new Object[0]);
        public static final JCDiagnostic.Error SignatureDoesntMatchIntf = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "signature.doesnt.match.intf", new Object[0]);
        public static final JCDiagnostic.Error SignatureDoesntMatchSupertype = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "signature.doesnt.match.supertype", new Object[0]);
        public static final JCDiagnostic.Error SourcepathModulesourcepathConflict = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "sourcepath.modulesourcepath.conflict", new Object[0]);
        public static final JCDiagnostic.Error StatementNotExpected = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "statement.not.expected", new Object[0]);
        public static final JCDiagnostic.Error StaticDeclarationNotAllowedInInnerClasses = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "static.declaration.not.allowed.in.inner.classes", new Object[0]);
        public static final JCDiagnostic.Error StaticImpOnlyClassesAndInterfaces = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "static.imp.only.classes.and.interfaces", new Object[0]);
        public static final JCDiagnostic.Error StringConstReq = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "string.const.req", new Object[0]);
        public static final JCDiagnostic.Error SwitchCaseUnexpectedStatement = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "switch.case.unexpected.statement", new Object[0]);
        public static final JCDiagnostic.Error SwitchExpressionCompletesNormally = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "switch.expression.completes.normally", new Object[0]);
        public static final JCDiagnostic.Error SwitchExpressionEmpty = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "switch.expression.empty", new Object[0]);
        public static final JCDiagnostic.Error SwitchExpressionNoResultExpressions = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "switch.expression.no.result.expressions", new Object[0]);
        public static final JCDiagnostic.Error SwitchMixingCaseTypes = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "switch.mixing.case.types", new Object[0]);
        public static final JCDiagnostic.Error ThisAsIdentifier = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "this.as.identifier", new Object[0]);
        public static final JCDiagnostic.Error ThrowsNotAllowedInIntfAnnotation = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "throws.not.allowed.in.intf.annotation", new Object[0]);
        public static final JCDiagnostic.Error TooManyModules = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "too.many.modules", new Object[0]);
        public static final JCDiagnostic.Error TryWithResourcesExprNeedsVar = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "try.with.resources.expr.needs.var", new Object[0]);
        public static final JCDiagnostic.Error TryWithoutCatchFinallyOrResourceDecls = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "try.without.catch.finally.or.resource.decls", new Object[0]);
        public static final JCDiagnostic.Error TwoClassLoaders1 = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "two.class.loaders.1", new Object[0]);
        public static final JCDiagnostic.Error TypeVarCantBeDeref = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.var.cant.be.deref", new Object[0]);
        public static final JCDiagnostic.Error TypeVarMayNotBeFollowedByOtherBounds = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.var.may.not.be.followed.by.other.bounds", new Object[0]);
        public static final JCDiagnostic.Error TypeVarMoreThanOnce = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.var.more.than.once", new Object[0]);
        public static final JCDiagnostic.Error TypeVarMoreThanOnceInResult = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.var.more.than.once.in.result", new Object[0]);
        public static final JCDiagnostic.Error UnclosedCharLit = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unclosed.char.lit", new Object[0]);
        public static final JCDiagnostic.Error UnclosedComment = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unclosed.comment", new Object[0]);
        public static final JCDiagnostic.Error UnclosedStrLit = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unclosed.str.lit", new Object[0]);
        public static final JCDiagnostic.Error UnclosedTextBlock = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unclosed.text.block", new Object[0]);
        public static final JCDiagnostic.Error UnconditionalPatternAndBothBooleanValues = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unconditional.pattern.and.both.boolean.values", new Object[0]);
        public static final JCDiagnostic.Error UnconditionalPatternAndDefault = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unconditional.pattern.and.default", new Object[0]);
        public static final JCDiagnostic.Error UnderscoreAsIdentifier = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "underscore.as.identifier", new Object[0]);
        public static final JCDiagnostic.Error UnexpectedLambda = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unexpected.lambda", new Object[0]);
        public static final JCDiagnostic.Error UnexpectedMref = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unexpected.mref", new Object[0]);
        public static final JCDiagnostic.Error UnnamedPkgNotAllowedNamedModules = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unnamed.pkg.not.allowed.named.modules", new Object[0]);
        public static final JCDiagnostic.Error UnreachableStmt = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unreachable.stmt", new Object[0]);
        public static final JCDiagnostic.Error UseOfUnderscoreNotAllowed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "use.of.underscore.not.allowed", new Object[0]);
        public static final JCDiagnostic.Error UseOfUnderscoreNotAllowedNonVariable = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "use.of.underscore.not.allowed.non.variable", new Object[0]);
        public static final JCDiagnostic.Error UseOfUnderscoreNotAllowedWithBrackets = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "use.of.underscore.not.allowed.with.brackets", new Object[0]);
        public static final JCDiagnostic.Error VarargsAndOldArraySyntax = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.and.old.array.syntax", new Object[0]);
        public static final JCDiagnostic.Error VarargsAndReceiver = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.and.receiver", new Object[0]);
        public static final JCDiagnostic.Error VarargsMustBeLast = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.must.be.last", new Object[0]);
        public static final JCDiagnostic.Error VariableNotAllowed = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "variable.not.allowed", new Object[0]);
        public static final JCDiagnostic.Error VoidNotAllowedHere = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "void.not.allowed.here", new Object[0]);
        public static final JCDiagnostic.Error WarningsAndWerror = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "warnings.and.werror", new Object[0]);
        public static final JCDiagnostic.Error WrongReceiver = new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "wrong.receiver", new Object[0]);

        public static JCDiagnostic.Error AbstractCantBeAccessedDirectly(Kinds.KindName kindName, Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "abstract.cant.be.accessed.directly", kindName, symbol, symbol2);
        }

        public static JCDiagnostic.Error AbstractCantBeInstantiated(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "abstract.cant.be.instantiated", symbol);
        }

        public static JCDiagnostic.Error AddExportsWithRelease(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "add.exports.with.release", symbol);
        }

        public static JCDiagnostic.Error AddReadsWithRelease(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "add.reads.with.release", symbol);
        }

        public static JCDiagnostic.Error AlreadyAnnotated(Kinds.KindName kindName, Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "already.annotated", kindName, symbol);
        }

        public static JCDiagnostic.Error AlreadyDefined(Kinds.KindName kindName, Symbol symbol, Kinds.KindName kindName2, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "already.defined", kindName, symbol, kindName2, symbol2);
        }

        public static JCDiagnostic.Error AlreadyDefinedInClinit(Kinds.KindName kindName, Symbol symbol, Kinds.KindName kindName2, Kinds.KindName kindName3, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "already.defined.in.clinit", kindName, symbol, kindName2, kindName3, symbol2);
        }

        public static JCDiagnostic.Error AlreadyDefinedSingleImport(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "already.defined.single.import", symbol);
        }

        public static JCDiagnostic.Error AlreadyDefinedStaticSingleImport(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "already.defined.static.single.import", symbol);
        }

        public static JCDiagnostic.Error AlreadyDefinedThisUnit(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "already.defined.this.unit", symbol);
        }

        public static JCDiagnostic.Error AnnotationArrayTooLarge(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.array.too.large", symbol);
        }

        public static JCDiagnostic.Error AnnotationMissingDefaultValue(Type type, List<? extends Name> list) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.missing.default.value", type, list);
        }

        public static JCDiagnostic.Error AnnotationMissingDefaultValue1(Type type, List<? extends Name> list) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.missing.default.value.1", type, list);
        }

        public static JCDiagnostic.Error AnnotationNotValidForType(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.not.valid.for.type", type);
        }

        public static JCDiagnostic.Error AnnotationTypeNotApplicableToType(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.type.not.applicable.to.type", type);
        }

        public static JCDiagnostic.Error AnnotationUnrecognizedAttributeName(Type type, Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "annotation.unrecognized.attribute.name", type, name);
        }

        public static JCDiagnostic.Error AnonymousDiamondMethodDoesNotOverrideSuperclass(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "anonymous.diamond.method.does.not.override.superclass", jCDiagnostic);
        }

        public static JCDiagnostic.Error ArrayAndVarargs(Symbol symbol, Symbol symbol2, Symbol symbol3) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "array.and.varargs", symbol, symbol2, symbol3);
        }

        public static JCDiagnostic.Error ArrayReqButFound(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "array.req.but.found", type);
        }

        public static JCDiagnostic.Error BadFileName(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.file.name", str);
        }

        public static JCDiagnostic.Error BadFunctionalIntfAnno1(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.functional.intf.anno.1", jCDiagnostic);
        }

        public static JCDiagnostic.Error BadInitializer(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.initializer", str);
        }

        public static JCDiagnostic.Error BadNameForOption(Option option, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.name.for.option", option, str);
        }

        public static JCDiagnostic.Error BadValueForOption(String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.value.for.option", str, str2);
        }

        public static JCDiagnostic.Error CallToSuperNotAllowedInEnumCtor(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "call.to.super.not.allowed.in.enum.ctor", symbol);
        }

        public static JCDiagnostic.Error CannotAssignNotDeclaredGuard(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cannot.assign.not.declared.guard", symbol);
        }

        public static JCDiagnostic.Error CannotGenerateClass(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cannot.generate.class", symbol, fragment);
        }

        public static JCDiagnostic.Error CantAccess(Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.access", symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantApplyDiamond(JCDiagnostic jCDiagnostic, Void r4) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond", jCDiagnostic, r4);
        }

        public static JCDiagnostic.Error CantApplyDiamond1(JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", jCDiagnostic, jCDiagnostic2);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, List<? extends Type> list2, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, list2, kind2, type, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantApplySymbolNoargs(Kinds.Kind kind, Name name, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol.noargs", kind, name, kind2, type, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantApplySymbols(Kinds.Kind kind, Name name, List<? extends Type> list) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbols", kind, name, list);
        }

        public static JCDiagnostic.Error CantAssignInitializedBeforeCtorCalled(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.assign.initialized.before.ctor.called", symbol);
        }

        public static JCDiagnostic.Error CantAssignValToVar(Set<? extends FlagsEnum> set, Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.assign.val.to.var", set, symbol);
        }

        public static JCDiagnostic.Error CantAttachTypeAnnotations(List<? extends Attribute.Compound> list, Symbol symbol, Name name, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.attach.type.annotations", list, symbol, name, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantDeref(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.deref", type);
        }

        public static JCDiagnostic.Error CantInferLocalVarType(Name name, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.infer.local.var.type", name, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantInheritDiffArg(Symbol symbol, String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.inherit.diff.arg", symbol, str, str2);
        }

        public static JCDiagnostic.Error CantInheritFromFinal(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.inherit.from.final", symbol);
        }

        public static JCDiagnostic.Error CantInheritFromSealed(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.inherit.from.sealed", symbol);
        }

        public static JCDiagnostic.Error CantRefBeforeCtorCalled(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.ref.before.ctor.called", symbol);
        }

        public static JCDiagnostic.Error CantRefNonEffectivelyFinalVar(Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.ref.non.effectively.final.var", symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantResolve(Kinds.KindName kindName, Name name, Void r5, Void r6) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve", kindName, name, r5, r6);
        }

        public static JCDiagnostic.Error CantResolveArgs(Kinds.KindName kindName, Name name, Void r5, List<? extends Type> list) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.args", kindName, name, r5, list);
        }

        public static JCDiagnostic.Error CantResolveArgsParams(Kinds.KindName kindName, Name name, List<? extends Type> list, List<? extends Type> list2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.args.params", kindName, name, list, list2);
        }

        public static JCDiagnostic.Error CantResolveLocation(Kinds.KindName kindName, Name name, Void r5, Void r6, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.location", kindName, name, r5, r6, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantResolveLocationArgs(Kinds.KindName kindName, Name name, Void r5, List<? extends Type> list, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.location.args", kindName, name, r5, list, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantResolveLocationArgsParams(Kinds.KindName kindName, Name name, List<? extends Type> list, List list2, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.location.args.params", kindName, name, list, list2, jCDiagnostic);
        }

        public static JCDiagnostic.Error ClashWithPkgOfSameName(Kinds.KindName kindName, Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "clash.with.pkg.of.same.name", kindName, symbol);
        }

        public static JCDiagnostic.Error ClassCantWrite(Symbol symbol, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "class.cant.write", symbol, str);
        }

        public static JCDiagnostic.Error ClassInModuleCantExtendSealedInDiffModule(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "class.in.module.cant.extend.sealed.in.diff.module", symbol, symbol2);
        }

        public static JCDiagnostic.Error ClassInUnnamedModuleCantExtendSealedInDiffPackage(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "class.in.unnamed.module.cant.extend.sealed.in.diff.package", symbol);
        }

        public static JCDiagnostic.Error ClassPublicShouldBeInFile(Kinds.KindName kindName, Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "class.public.should.be.in.file", kindName, name);
        }

        public static JCDiagnostic.Error ConcreteInheritanceConflict(Symbol symbol, Type type, Symbol symbol2, Type type2, Type type3) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "concrete.inheritance.conflict", symbol, type, symbol2, type2, type3);
        }

        public static JCDiagnostic.Error ConflictingExports(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "conflicting.exports", symbol);
        }

        public static JCDiagnostic.Error ConflictingExportsToModule(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "conflicting.exports.to.module", symbol);
        }

        public static JCDiagnostic.Error ConflictingOpens(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "conflicting.opens", symbol);
        }

        public static JCDiagnostic.Error ConflictingOpensToModule(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "conflicting.opens.to.module", symbol);
        }

        public static JCDiagnostic.Error ConstantLabelNotCompatible(Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "constant.label.not.compatible", type, type2);
        }

        public static JCDiagnostic.Error CyclicAnnotationElement(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cyclic.annotation.element", symbol);
        }

        public static JCDiagnostic.Error CyclicInheritance(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cyclic.inheritance", symbol);
        }

        public static JCDiagnostic.Error CyclicRequires(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cyclic.requires", symbol);
        }

        public static JCDiagnostic.Error DeconstructionPatternOnlyRecords(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deconstruction.pattern.only.records", symbol);
        }

        public static JCDiagnostic.Error DefaultOverridesObjectMember(Name name, Kinds.KindName kindName, Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "default.overrides.object.member", name, kindName, symbol);
        }

        public static JCDiagnostic.Error DoesNotOverrideAbstract(Symbol symbol, Symbol symbol2, Symbol symbol3) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "does.not.override.abstract", symbol, symbol2, symbol3);
        }

        public static JCDiagnostic.Error DoesntExist(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "doesnt.exist", symbol);
        }

        public static JCDiagnostic.Error DuplicateAnnotationInvalidRepeated(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.annotation.invalid.repeated", type);
        }

        public static JCDiagnostic.Error DuplicateAnnotationMemberValue(Name name, Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.annotation.member.value", name, type);
        }

        public static JCDiagnostic.Error DuplicateAnnotationMissingContainer(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.annotation.missing.container", type);
        }

        public static JCDiagnostic.Error DuplicateClass(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.class", name);
        }

        public static JCDiagnostic.Error DuplicateModule(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.module", symbol);
        }

        public static JCDiagnostic.Error DuplicateModuleOnPath(JCDiagnostic.Fragment fragment, Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.module.on.path", fragment, name);
        }

        public static JCDiagnostic.Error DuplicateProvides(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.provides", symbol, symbol2);
        }

        public static JCDiagnostic.Error DuplicateRequires(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.requires", symbol);
        }

        public static JCDiagnostic.Error DuplicateUses(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "duplicate.uses", symbol);
        }

        public static JCDiagnostic.Error EnclClassRequired(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "encl.class.required", symbol);
        }

        public static JCDiagnostic.Error EnclosingClassTypeNonDenotable(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "enclosing.class.type.non.denotable", type);
        }

        public static JCDiagnostic.Error ErrorReadingFile(File file, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "error.reading.file", file, str);
        }

        public static JCDiagnostic.Error ErrorWritingFile(String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "error.writing.file", str, str2);
        }

        public static JCDiagnostic.Error ExceptAlreadyCaught(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "except.already.caught", type);
        }

        public static JCDiagnostic.Error ExceptNeverThrownInTry(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "except.never.thrown.in.try", type);
        }

        public static JCDiagnostic.Error Expected(Tokens.TokenKind tokenKind) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "expected", tokenKind);
        }

        public static JCDiagnostic.Error Expected2(Tokens.TokenKind tokenKind, Tokens.TokenKind tokenKind2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "expected2", tokenKind, tokenKind2);
        }

        public static JCDiagnostic.Error Expected3(Tokens.TokenKind tokenKind, Tokens.TokenKind tokenKind2, Tokens.TokenKind tokenKind3) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "expected3", tokenKind, tokenKind2, tokenKind3);
        }

        public static JCDiagnostic.Error Expected4(Tokens.TokenKind tokenKind, Tokens.TokenKind tokenKind2, Tokens.TokenKind tokenKind3, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "expected4", tokenKind, tokenKind2, tokenKind3, str);
        }

        public static JCDiagnostic.Error ExpectedStr(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "expected.str", str);
        }

        public static JCDiagnostic.Error FeatureNotSupportedInSource(JCDiagnostic jCDiagnostic, String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.of(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL), "compiler", "feature.not.supported.in.source", jCDiagnostic, str, str2);
        }

        public static JCDiagnostic.Error FeatureNotSupportedInSourcePlural(JCDiagnostic jCDiagnostic, String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.of(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL), "compiler", "feature.not.supported.in.source.plural", jCDiagnostic, str, str2);
        }

        public static JCDiagnostic.Error FileNotDirectory(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.not.directory", str);
        }

        public static JCDiagnostic.Error FileNotFile(Object obj) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.not.file", obj);
        }

        public static JCDiagnostic.Error FileNotFound(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.not.found", str);
        }

        public static JCDiagnostic.Error FilePatchedAndMsp(Name name, Name name2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.patched.and.msp", name, name2);
        }

        public static JCDiagnostic.Error FinalParameterMayNotBeAssigned(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "final.parameter.may.not.be.assigned", symbol);
        }

        public static JCDiagnostic.Error ForeachNotApplicableToType(Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "foreach.not.applicable.to.type", type, jCDiagnostic);
        }

        public static JCDiagnostic.Error IclsCantHaveStaticDecl(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "icls.cant.have.static.decl", symbol);
        }

        public static JCDiagnostic.Error IllegalArgumentForOption(String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.argument.for.option", str, str2);
        }

        public static JCDiagnostic.Error IllegalChar(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.char", str);
        }

        public static JCDiagnostic.Error IllegalCharForEncoding(String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.char.for.encoding", str, str2);
        }

        public static JCDiagnostic.Error IllegalCombinationOfModifiers(Set<? extends FlagsEnum> set, Set<? extends FlagsEnum> set2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.combination.of.modifiers", set, set2);
        }

        public static JCDiagnostic.Error IllegalDefaultSuperCall(Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.default.super.call", symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Error IllegalInitializerForType(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.initializer.for.type", type);
        }

        public static JCDiagnostic.Error IllegalQualNotIcls(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.qual.not.icls", symbol);
        }

        public static JCDiagnostic.Error IllegalRecordComponentName(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.record.component.name", symbol);
        }

        public static JCDiagnostic.Error IllegalRefToRestrictedType(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.ref.to.restricted.type", name);
        }

        public static JCDiagnostic.Error IllegalStaticIntfMethCall(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.static.intf.meth.call", type);
        }

        public static JCDiagnostic.Error ImportModuleDoesNotRead(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "import.module.does.not.read", symbol, symbol2);
        }

        public static JCDiagnostic.Error ImportModuleDoesNotReadUnnamed(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "import.module.does.not.read.unnamed", symbol);
        }

        public static JCDiagnostic.Error ImportModuleNotFound(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "import.module.not.found", name);
        }

        public static JCDiagnostic.Error ImportRequiresCanonical(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "import.requires.canonical", symbol);
        }

        public static JCDiagnostic.Error IncomparableTypes(Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incomparable.types", type, type2);
        }

        public static JCDiagnostic.Error IncompatibleDiffRetSameType(Type type, Name name, List<? extends Type> list) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.diff.ret.same.type", type, name, list);
        }

        public static JCDiagnostic.Error IncompatibleThrownTypesInMref(List<? extends Type> list) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.thrown.types.in.mref", list);
        }

        public static JCDiagnostic.Error IncorrectConstructorReceiverName(Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incorrect.constructor.receiver.name", type, type2);
        }

        public static JCDiagnostic.Error IncorrectConstructorReceiverType(Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incorrect.constructor.receiver.type", type, type2);
        }

        public static JCDiagnostic.Error IncorrectNumberOfNestedPatterns(List<? extends Type> list, List<? extends Type> list2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incorrect.number.of.nested.patterns", list, list2);
        }

        public static JCDiagnostic.Error IncorrectReceiverName(Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incorrect.receiver.name", type, type2);
        }

        public static JCDiagnostic.Error IncorrectReceiverType(Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incorrect.receiver.type", type, type2);
        }

        public static JCDiagnostic.Error InstanceofReifiableNotSafe(Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "instanceof.reifiable.not.safe", type, type2);
        }

        public static JCDiagnostic.Error IntNumberTooLarge(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "int.number.too.large", str);
        }

        public static JCDiagnostic.Error IntfAnnotationCantHaveTypeParams(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "intf.annotation.cant.have.type.params", symbol);
        }

        public static JCDiagnostic.Error IntfAnnotationMemberClash(Symbol symbol, Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "intf.annotation.member.clash", symbol, type);
        }

        public static JCDiagnostic.Error InvalidAKey(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.A.key", str);
        }

        public static JCDiagnostic.Error InvalidAccessorMethodInRecord(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.accessor.method.in.record", symbol, fragment);
        }

        public static JCDiagnostic.Error InvalidCanonicalConstructorInRecord(JCDiagnostic.Fragment fragment, Name name, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.canonical.constructor.in.record", fragment, name, fragment2);
        }

        public static JCDiagnostic.Error InvalidFlag(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.flag", str);
        }

        public static JCDiagnostic.Error InvalidLambdaParameterDeclaration(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.lambda.parameter.declaration", fragment);
        }

        public static JCDiagnostic.Error InvalidModuleSpecifier(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.module.specifier", str);
        }

        public static JCDiagnostic.Error InvalidMref(Kinds.KindName kindName, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.mref", kindName, jCDiagnostic);
        }

        public static JCDiagnostic.Error InvalidPath(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.path", str);
        }

        public static JCDiagnostic.Error InvalidPermitsClause(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.permits.clause", fragment);
        }

        public static JCDiagnostic.Error InvalidProfile(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.profile", str);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotation(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation", symbol);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationElemNondefault(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.elem.nondefault", symbol, symbol2);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationIncompatibleTarget(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.incompatible.target", symbol, symbol2);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationInvalidValue(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.invalid.value", type);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationMultipleValues(Type type, int i) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.multiple.values", type, Integer.valueOf(i));
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationNoValue(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.no.value", symbol);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationNotApplicable(Type type, Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.not.applicable", type, symbol);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationNotApplicableInContext(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.not.applicable.in.context", type);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationNotDocumented(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.not.documented", symbol, symbol2);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationNotInherited(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.not.inherited", symbol, symbol2);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationRepeatedAndContainerPresent(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.repeated.and.container.present", symbol);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationRetention(Symbol symbol, String str, Symbol symbol2, String str2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.retention", symbol, str, symbol2, str2);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationValueReturn(Symbol symbol, Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.value.return", symbol, type, type2);
        }

        public static JCDiagnostic.Error InvalidSource(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.source", str);
        }

        public static JCDiagnostic.Error InvalidSupertypeRecord(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.supertype.record", symbol);
        }

        public static JCDiagnostic.Error InvalidTarget(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.target", str);
        }

        public static JCDiagnostic.Error IsPreview(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "is.preview", symbol);
        }

        public static JCDiagnostic.Error LabelAlreadyInUse(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "label.already.in.use", name);
        }

        public static JCDiagnostic.Error LimitStringOverflow(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "limit.string.overflow", str);
        }

        public static JCDiagnostic.Error LocalCantBeInstStatic(Kinds.Kind kind, Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.cant.be.inst.static", kind, symbol);
        }

        public static JCDiagnostic.Error LocalClassesCantExtendSealed(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.classes.cant.extend.sealed", fragment);
        }

        public static JCDiagnostic.Error LocnBadModuleInfo(Path path) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "locn.bad.module-info", path);
        }

        public static JCDiagnostic.Error LocnCantGetModuleNameForJar(Path path) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "locn.cant.get.module.name.for.jar", path);
        }

        public static JCDiagnostic.Error LocnCantReadDirectory(Path path) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "locn.cant.read.directory", path);
        }

        public static JCDiagnostic.Error LocnCantReadFile(Path path) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "locn.cant.read.file", path);
        }

        public static JCDiagnostic.Error LocnInvalidArgForXpatch(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "locn.invalid.arg.for.xpatch", str);
        }

        public static JCDiagnostic.Error LocnModuleInfoNotAllowedOnPatchPath(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "locn.module-info.not.allowed.on.patch.path", javaFileObject);
        }

        public static JCDiagnostic.Error MethodDoesNotOverrideSuperclass(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "method.does.not.override.superclass", symbol, symbol2);
        }

        public static JCDiagnostic.Error MissingMethBodyOrDeclAbstract(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "missing.meth.body.or.decl.abstract", symbol, symbol2);
        }

        public static JCDiagnostic.Error ModNotAllowedHere(Set<? extends FlagsEnum> set) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "mod.not.allowed.here", set);
        }

        public static JCDiagnostic.Error ModuleNameMismatch(Name name, Name name2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "module.name.mismatch", name, name2);
        }

        public static JCDiagnostic.Error ModuleNonZeroOpens(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "module.non.zero.opens", name);
        }

        public static JCDiagnostic.Error ModuleNotFound(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "module.not.found", symbol);
        }

        public static JCDiagnostic.Error ModuleNotFoundInModuleSourcePath(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "module.not.found.in.module.source.path", str);
        }

        public static JCDiagnostic.Error MultiModuleOutdirCannotBeExplodedModule(Path path) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "multi-module.outdir.cannot.be.exploded.module", path);
        }

        public static JCDiagnostic.Error MulticatchParameterMayNotBeAssigned(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "multicatch.parameter.may.not.be.assigned", symbol);
        }

        public static JCDiagnostic.Error MulticatchTypesMustBeDisjoint(Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "multicatch.types.must.be.disjoint", type, type2);
        }

        public static JCDiagnostic.Error NameClashSameErasure(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "name.clash.same.erasure", symbol, symbol2);
        }

        public static JCDiagnostic.Error NameClashSameErasureNoHide(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "name.clash.same.erasure.no.hide", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Error NameClashSameErasureNoOverride(Name name, List<? extends Type> list, Symbol symbol, Name name2, List<? extends Type> list2, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "name.clash.same.erasure.no.override", name, list, symbol, name2, list2, symbol2);
        }

        public static JCDiagnostic.Error NameClashSameErasureNoOverride1(String str, Name name, Name name2, List<? extends Type> list, Symbol symbol, Name name3, List<? extends Type> list2, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "name.clash.same.erasure.no.override.1", str, name, name2, list, symbol, name3, list2, symbol2);
        }

        public static JCDiagnostic.Error NoAnnotationMember(Name name, Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.annotation.member", name, type);
        }

        public static JCDiagnostic.Error NoEnclInstanceOfTypeInScope(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.encl.instance.of.type.in.scope", symbol);
        }

        public static JCDiagnostic.Error NoSuperclass(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.superclass", type);
        }

        public static JCDiagnostic.Error NoValueForOption(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.value.for.option", str);
        }

        public static JCDiagnostic.Error NoZipfsForArchive(Path path) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.zipfs.for.archive", path);
        }

        public static JCDiagnostic.Error NonCanonicalConstructorInvokeAnotherConstructor(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "non.canonical.constructor.invoke.another.constructor", symbol);
        }

        public static JCDiagnostic.Error NonSealedWithNoSealedSupertype(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "non.sealed.with.no.sealed.supertype", symbol);
        }

        public static JCDiagnostic.Error NonStaticCantBeRef(Kinds.Kind kind, Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "non-static.cant.be.ref", kind, symbol);
        }

        public static JCDiagnostic.Error NotAnnotationType(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.annotation.type", type);
        }

        public static JCDiagnostic.Error NotDefAccessClassIntfCantAccess(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.class.intf.cant.access", symbol, symbol2);
        }

        public static JCDiagnostic.Error NotDefAccessClassIntfCantAccessReason(Symbol symbol, Symbol symbol2, Symbol symbol3, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.class.intf.cant.access.reason", symbol, symbol2, symbol3, jCDiagnostic);
        }

        public static JCDiagnostic.Error NotDefAccessPackageCantAccess(Symbol symbol, Symbol symbol2, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.package.cant.access", symbol, symbol2, jCDiagnostic);
        }

        public static JCDiagnostic.Error NotDefPublic(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.public", symbol, symbol2);
        }

        public static JCDiagnostic.Error NotDefPublicCantAccess(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.public.cant.access", symbol, symbol2);
        }

        public static JCDiagnostic.Error NotEnclClass(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.encl.class", symbol);
        }

        public static JCDiagnostic.Error NotInProfile(Symbol symbol, Object obj) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.in.profile", symbol, obj);
        }

        public static JCDiagnostic.Error NotLoopLabel(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.loop.label", name);
        }

        public static JCDiagnostic.Error NotWithinBounds(Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.within.bounds", type, type2);
        }

        public static JCDiagnostic.Error OperatorCantBeApplied(Name name, Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "operator.cant.be.applied", name, type);
        }

        public static JCDiagnostic.Error OperatorCantBeApplied1(Name name, Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "operator.cant.be.applied.1", name, type, type2);
        }

        public static JCDiagnostic.Error OptionNotAllowedWithTarget(Option option, Target target) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "option.not.allowed.with.target", option, target);
        }

        public static JCDiagnostic.Error OptionRemovedSource(String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "option.removed.source", str, str2);
        }

        public static JCDiagnostic.Error OptionRemovedTarget(Target target, Target target2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "option.removed.target", target, target2);
        }

        public static JCDiagnostic.Error OptionTooMany(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "option.too.many", str);
        }

        public static JCDiagnostic.Error Orphaned(Tokens.TokenKind tokenKind) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "orphaned", tokenKind);
        }

        public static JCDiagnostic.Error OverrideIncompatibleRet(JCDiagnostic jCDiagnostic, Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.incompatible.ret", jCDiagnostic, type, type2);
        }

        public static JCDiagnostic.Error OverrideMeth(JCDiagnostic jCDiagnostic, Set<? extends FlagsEnum> set) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.meth", jCDiagnostic, set);
        }

        public static JCDiagnostic.Error OverrideMethDoesntThrow(JCDiagnostic jCDiagnostic, Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.meth.doesnt.throw", jCDiagnostic, type);
        }

        public static JCDiagnostic.Error OverrideStatic(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.static", jCDiagnostic);
        }

        public static JCDiagnostic.Error OverrideWeakerAccess(JCDiagnostic jCDiagnostic, Set<? extends FlagsEnum> set) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.weaker.access", jCDiagnostic, set);
        }

        public static JCDiagnostic.Error PackageClashFromRequires(Symbol symbol, Name name, Symbol symbol2, Symbol symbol3) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "package.clash.from.requires", symbol, name, symbol2, symbol3);
        }

        public static JCDiagnostic.Error PackageClashFromRequiresInUnnamed(Name name, Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "package.clash.from.requires.in.unnamed", name, symbol, symbol2);
        }

        public static JCDiagnostic.Error PackageEmptyOrNotFound(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "package.empty.or.not.found", symbol);
        }

        public static JCDiagnostic.Error PackageInOtherModule(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "package.in.other.module", symbol);
        }

        public static JCDiagnostic.Error PackageNotVisible(Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "package.not.visible", symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Error PkgClashesWithClassOfSameName(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "pkg.clashes.with.class.of.same.name", symbol);
        }

        public static JCDiagnostic.Error PluginNotFound(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "plugin.not.found", str);
        }

        public static JCDiagnostic.Error PreviewFeatureDisabled(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.of(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL), "compiler", "preview.feature.disabled", jCDiagnostic);
        }

        public static JCDiagnostic.Error PreviewFeatureDisabledClassfile(JavaFileObject javaFileObject, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.feature.disabled.classfile", javaFileObject, str);
        }

        public static JCDiagnostic.Error PreviewFeatureDisabledPlural(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.of(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL), "compiler", "preview.feature.disabled.plural", jCDiagnostic);
        }

        public static JCDiagnostic.Error PreviewNotLatest(String str, Source source) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.not.latest", str, source);
        }

        public static JCDiagnostic.Error ProbFoundReq(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "prob.found.req", jCDiagnostic);
        }

        public static JCDiagnostic.Error ProcBadConfigFile(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.bad.config.file", str);
        }

        public static JCDiagnostic.Error ProcCantAccess(Symbol symbol, JCDiagnostic jCDiagnostic, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.cant.access", symbol, jCDiagnostic, str);
        }

        public static JCDiagnostic.Error ProcCantAccess1(Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.cant.access.1", symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Error ProcCantFindClass(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.cant.find.class", str);
        }

        public static JCDiagnostic.Error ProcCantLoadClass(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.cant.load.class", str);
        }

        public static JCDiagnostic.Error ProcMessager(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.messager", str);
        }

        public static JCDiagnostic.Error ProcNoExplicitAnnotationProcessingRequested(Collection<? extends String> collection) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.no.explicit.annotation.processing.requested", collection);
        }

        public static JCDiagnostic.Error ProcProcessorBadOptionName(String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.processor.bad.option.name", str, str2);
        }

        public static JCDiagnostic.Error ProcProcessorCantInstantiate(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.processor.cant.instantiate", str);
        }

        public static JCDiagnostic.Error ProcProcessorNotFound(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.processor.not.found", str);
        }

        public static JCDiagnostic.Error ProcProcessorWrongType(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.processor.wrong.type", str);
        }

        public static JCDiagnostic.Error QualifiedNewOfStaticClass(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "qualified.new.of.static.class", symbol);
        }

        public static JCDiagnostic.Error RefAmbiguous(Name name, Kinds.Kind kind, Symbol symbol, Symbol symbol2, Kinds.Kind kind2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "ref.ambiguous", name, kind, symbol, symbol2, kind2, symbol3, symbol4);
        }

        public static JCDiagnostic.Error ReleaseBootclasspathConflict(Option option) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "release.bootclasspath.conflict", option);
        }

        public static JCDiagnostic.Error RepeatedProvidesForService(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "repeated.provides.for.service", symbol);
        }

        public static JCDiagnostic.Error RepeatedValueForModuleSourcePath(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "repeated.value.for.module.source.path", str);
        }

        public static JCDiagnostic.Error RepeatedValueForPatchModule(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "repeated.value.for.patch.module", str);
        }

        public static JCDiagnostic.Error ReportAccess(Symbol symbol, Set<? extends Modifier> set, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "report.access", symbol, set, symbol2);
        }

        public static JCDiagnostic.Error ReqArg(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "req.arg", str);
        }

        public static JCDiagnostic.Error RestrictedTypeNotAllowed(Name name, Source source) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "restricted.type.not.allowed", name, source);
        }

        public static JCDiagnostic.Error RestrictedTypeNotAllowedArray(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "restricted.type.not.allowed.array", name);
        }

        public static JCDiagnostic.Error RestrictedTypeNotAllowedCompound(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "restricted.type.not.allowed.compound", name);
        }

        public static JCDiagnostic.Error RestrictedTypeNotAllowedHere(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "restricted.type.not.allowed.here", name);
        }

        public static JCDiagnostic.Error SameBinaryName(Name name, Name name2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "same.binary.name", name, name2);
        }

        public static JCDiagnostic.Error ServiceDefinitionIsEnum(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "service.definition.is.enum", symbol);
        }

        public static JCDiagnostic.Error ServiceImplementationDoesntHaveANoArgsConstructor(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "service.implementation.doesnt.have.a.no.args.constructor", symbol);
        }

        public static JCDiagnostic.Error ServiceImplementationIsAbstract(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "service.implementation.is.abstract", symbol);
        }

        public static JCDiagnostic.Error ServiceImplementationIsInner(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "service.implementation.is.inner", symbol);
        }

        public static JCDiagnostic.Error ServiceImplementationNoArgsConstructorNotPublic(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "service.implementation.no.args.constructor.not.public", symbol);
        }

        public static JCDiagnostic.Error ServiceImplementationNotInRightModule(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "service.implementation.not.in.right.module", symbol);
        }

        public static JCDiagnostic.Error SourceCantOverwriteInputFile(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "source.cant.overwrite.input.file", javaFileObject);
        }

        public static JCDiagnostic.Error SourceTargetConflict(String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "source.target.conflict", str, str2);
        }

        public static JCDiagnostic.Error StackSimError(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "stack.sim.error", symbol);
        }

        public static JCDiagnostic.Error StaticMethodsCannotBeAnnotatedWithOverride(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "static.methods.cannot.be.annotated.with.override", symbol, symbol2);
        }

        public static JCDiagnostic.Error TargetDefaultSourceConflict(String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "target.default.source.conflict", str, str2);
        }

        public static JCDiagnostic.Error TooManyPatchedModules(Set<? extends String> set) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "too.many.patched.modules", set);
        }

        public static JCDiagnostic.Error TryResourceMayNotBeAssigned(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "try.resource.may.not.be.assigned", symbol);
        }

        public static JCDiagnostic.Error TryWithResourcesExprEffectivelyFinalVar(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "try.with.resources.expr.effectively.final.var", symbol);
        }

        public static JCDiagnostic.Error TwoClassLoaders2(URL url, URL url2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "two.class.loaders.2", url, url2);
        }

        public static JCDiagnostic.Error TypeAnnotationInadmissible(JCDiagnostic.Fragment fragment, Symbol symbol, JCDiagnostic.AnnotatedType annotatedType) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.annotation.inadmissible", fragment, symbol, annotatedType);
        }

        public static JCDiagnostic.Error TypeDoesntTakeParams(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.doesnt.take.params", symbol);
        }

        public static JCDiagnostic.Error TypeFoundReq(Object obj, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.found.req", obj, jCDiagnostic);
        }

        public static JCDiagnostic.Error TypesIncompatible(Type type, Type type2, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "types.incompatible", type, type2, fragment);
        }

        public static JCDiagnostic.Error UndefLabel(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "undef.label", name);
        }

        public static JCDiagnostic.Error UnexpectedType(Set<? extends Kinds.KindName> set, Set<? extends Kinds.KindName> set2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unexpected.type", set, set2);
        }

        public static JCDiagnostic.Error UnmatchedQuote(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unmatched.quote", str);
        }

        public static JCDiagnostic.Error UnreportedExceptionDefaultConstructor(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unreported.exception.default.constructor", type);
        }

        public static JCDiagnostic.Error UnreportedExceptionImplicitClose(Type type, Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unreported.exception.implicit.close", type, name);
        }

        public static JCDiagnostic.Error UnreportedExceptionNeedToCatchOrThrow(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unreported.exception.need.to.catch.or.throw", type);
        }

        public static JCDiagnostic.Error UnsupportedEncoding(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unsupported.encoding", str);
        }

        public static JCDiagnostic.Error UnsupportedReleaseVersion(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unsupported.release.version", str);
        }

        public static JCDiagnostic.Error VarMightAlreadyBeAssigned(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "var.might.already.be.assigned", symbol);
        }

        public static JCDiagnostic.Error VarMightBeAssignedInLoop(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "var.might.be.assigned.in.loop", symbol);
        }

        public static JCDiagnostic.Error VarMightNotHaveBeenInitialized(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "var.might.not.have.been.initialized", symbol);
        }

        public static JCDiagnostic.Error VarNotInitializedInDefaultConstructor(Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "var.not.initialized.in.default.constructor", symbol);
        }

        public static JCDiagnostic.Error VarargsInvalidTrustmeAnno(Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.invalid.trustme.anno", symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Error WrongNumberTypeArgs(String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "wrong.number.type.args", str);
        }

        public static JCDiagnostic.Error AnonymousDiamondMethodDoesNotOverrideSuperclass(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "anonymous.diamond.method.does.not.override.superclass", fragment);
        }

        public static JCDiagnostic.Error BadFunctionalIntfAnno1(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.functional.intf.anno.1", fragment);
        }

        public static JCDiagnostic.Error CantAccess(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.access", symbol, fragment);
        }

        public static JCDiagnostic.Error CantApplyDiamond(JCDiagnostic.Fragment fragment, Void r4) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond", fragment, r4);
        }

        public static JCDiagnostic.Error CantApplyDiamond1(JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", jCDiagnostic, fragment);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, List<? extends Type> list2, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, list2, kind2, type, fragment);
        }

        public static JCDiagnostic.Error CantApplySymbolNoargs(Kinds.Kind kind, Name name, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol.noargs", kind, name, kind2, type, fragment);
        }

        public static JCDiagnostic.Error CantAssignInitializedBeforeCtorCalled(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.assign.initialized.before.ctor.called", name);
        }

        public static JCDiagnostic.Error CantAssignValToVar(String str, Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.assign.val.to.var", str, symbol);
        }

        public static JCDiagnostic.Error CantAttachTypeAnnotations(List<? extends Attribute.Compound> list, Symbol symbol, Name name, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.attach.type.annotations", list, symbol, name, fragment);
        }

        public static JCDiagnostic.Error CantInferLocalVarType(Name name, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.infer.local.var.type", name, fragment);
        }

        public static JCDiagnostic.Error CantRefBeforeCtorCalled(Name name) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.ref.before.ctor.called", name);
        }

        public static JCDiagnostic.Error CantRefNonEffectivelyFinalVar(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.ref.non.effectively.final.var", symbol, fragment);
        }

        public static JCDiagnostic.Error CantResolveLocation(Kinds.KindName kindName, Name name, Void r5, Void r6, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.location", kindName, name, r5, r6, fragment);
        }

        public static JCDiagnostic.Error CantResolveLocationArgs(Kinds.KindName kindName, Name name, Void r5, List<? extends Type> list, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.location.args", kindName, name, r5, list, fragment);
        }

        public static JCDiagnostic.Error CantResolveLocationArgsParams(Kinds.KindName kindName, Name name, List<? extends Type> list, List list2, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.location.args.params", kindName, name, list, list2, fragment);
        }

        public static JCDiagnostic.Error CyclicInheritance(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cyclic.inheritance", type);
        }

        public static JCDiagnostic.Error ErrorReadingFile(JavaFileObject javaFileObject, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "error.reading.file", javaFileObject, str);
        }

        public static JCDiagnostic.Error FeatureNotSupportedInSource(JCDiagnostic.Fragment fragment, String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.of(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL), "compiler", "feature.not.supported.in.source", fragment, str, str2);
        }

        public static JCDiagnostic.Error FeatureNotSupportedInSourcePlural(JCDiagnostic.Fragment fragment, String str, String str2) {
            return new JCDiagnostic.Error(EnumSet.of(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL), "compiler", "feature.not.supported.in.source.plural", fragment, str, str2);
        }

        public static JCDiagnostic.Error ForeachNotApplicableToType(Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "foreach.not.applicable.to.type", type, fragment);
        }

        public static JCDiagnostic.Error IllegalDefaultSuperCall(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.default.super.call", symbol, fragment);
        }

        public static JCDiagnostic.Error InvalidMref(Kinds.KindName kindName, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.mref", kindName, fragment);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationElemNondefault(Type type, Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.elem.nondefault", type, symbol);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationNoValue(Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.no.value", type);
        }

        public static JCDiagnostic.Error InvalidRepeatableAnnotationValueReturn(Type type, Type type2, Type type3) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.repeatable.annotation.value.return", type, type2, type3);
        }

        public static JCDiagnostic.Error NotDefAccessClassIntfCantAccessReason(Symbol symbol, Symbol symbol2, Symbol symbol3, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.class.intf.cant.access.reason", symbol, symbol2, symbol3, fragment);
        }

        public static JCDiagnostic.Error NotDefAccessPackageCantAccess(Symbol symbol, Symbol symbol2, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.package.cant.access", symbol, symbol2, fragment);
        }

        public static JCDiagnostic.Error NotWithinBounds(Type type, Symbol symbol) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.within.bounds", type, symbol);
        }

        public static JCDiagnostic.Error OverrideIncompatibleRet(JCDiagnostic.Fragment fragment, Type type, Type type2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.incompatible.ret", fragment, type, type2);
        }

        public static JCDiagnostic.Error OverrideMeth(JCDiagnostic.Fragment fragment, Set<? extends FlagsEnum> set) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.meth", fragment, set);
        }

        public static JCDiagnostic.Error OverrideMethDoesntThrow(JCDiagnostic.Fragment fragment, Type type) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.meth.doesnt.throw", fragment, type);
        }

        public static JCDiagnostic.Error OverrideStatic(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.static", fragment);
        }

        public static JCDiagnostic.Error OverrideWeakerAccess(JCDiagnostic jCDiagnostic, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.weaker.access", jCDiagnostic, str);
        }

        public static JCDiagnostic.Error PackageNotVisible(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "package.not.visible", symbol, fragment);
        }

        public static JCDiagnostic.Error PreviewFeatureDisabled(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.of(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL), "compiler", "preview.feature.disabled", fragment);
        }

        public static JCDiagnostic.Error PreviewFeatureDisabledPlural(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.of(JCDiagnostic.DiagnosticFlag.SOURCE_LEVEL), "compiler", "preview.feature.disabled.plural", fragment);
        }

        public static JCDiagnostic.Error ProbFoundReq(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "prob.found.req", fragment);
        }

        public static JCDiagnostic.Error ProcCantAccess(Symbol symbol, JCDiagnostic.Fragment fragment, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.cant.access", symbol, fragment, str);
        }

        public static JCDiagnostic.Error ProcCantAccess1(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.cant.access.1", symbol, fragment);
        }

        public static JCDiagnostic.Error CantApplyDiamond1(JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", fragment, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, JCDiagnostic jCDiagnostic, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, jCDiagnostic, kind2, type, jCDiagnostic2);
        }

        public static JCDiagnostic.Error ErrorReadingFile(Path path, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "error.reading.file", path, str);
        }

        public static JCDiagnostic.Error IllegalDefaultSuperCall(Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.default.super.call", type, jCDiagnostic);
        }

        public static JCDiagnostic.Error OverrideWeakerAccess(JCDiagnostic.Fragment fragment, Set<? extends FlagsEnum> set) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.weaker.access", fragment, set);
        }

        public static JCDiagnostic.Error TypeFoundReq(Object obj, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.found.req", obj, fragment);
        }

        public static JCDiagnostic.Error VarargsInvalidTrustmeAnno(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.invalid.trustme.anno", symbol, fragment);
        }

        public static JCDiagnostic.Error CantApplyDiamond1(JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", fragment, fragment2);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, JCDiagnostic jCDiagnostic, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, jCDiagnostic, kind2, type, fragment);
        }

        public static JCDiagnostic.Error IllegalDefaultSuperCall(Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.default.super.call", type, fragment);
        }

        public static JCDiagnostic.Error OverrideWeakerAccess(JCDiagnostic.Fragment fragment, String str) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.weaker.access", fragment, str);
        }

        public static JCDiagnostic.Error CantApplyDiamond1(Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", type, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, JCDiagnostic.Fragment fragment, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, fragment, kind2, type, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantApplyDiamond1(Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", type, fragment);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, JCDiagnostic.Fragment fragment, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, fragment, kind2, type, fragment2);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, List<? extends Type> list, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, list, kind2, type, jCDiagnostic2);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, List<? extends Type> list, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, list, kind2, type, fragment);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic3) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, jCDiagnostic2, kind2, type, jCDiagnostic3);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, jCDiagnostic2, kind2, type, fragment);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, fragment, kind2, type, jCDiagnostic2);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, fragment, kind2, type, fragment2);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, List<? extends Type> list, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, list, kind2, type, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, List<? extends Type> list, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, list, kind2, type, fragment2);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, jCDiagnostic, kind2, type, jCDiagnostic2);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, jCDiagnostic, kind2, type, fragment2);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, fragment2, kind2, type, jCDiagnostic);
        }

        public static JCDiagnostic.Error CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment3) {
            return new JCDiagnostic.Error(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, fragment2, kind2, type, fragment3);
        }
    }

    public static class Fragments {
        public static final JCDiagnostic.Fragment AccessorMethodCantThrowException = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "accessor.method.cant.throw.exception", new Object[0]);
        public static final JCDiagnostic.Fragment AccessorMethodMustNotBeGeneric = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "accessor.method.must.not.be.generic", new Object[0]);
        public static final JCDiagnostic.Fragment AccessorMethodMustNotBeStatic = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "accessor.method.must.not.be.static", new Object[0]);
        public static final JCDiagnostic.Fragment Anonymous = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "anonymous", new Object[0]);
        public static final JCDiagnostic.Fragment ArgLengthMismatch = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "arg.length.mismatch", new Object[0]);
        public static final JCDiagnostic.Fragment BadClassSignature = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.class.signature", new Object[0]);
        public static final JCDiagnostic.Fragment BadConstPoolTag = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.const.pool.tag", new Object[0]);
        public static final JCDiagnostic.Fragment BadConstPoolTagAt = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.const.pool.tag.at", new Object[0]);
        public static final JCDiagnostic.Fragment BadEnclosingClass = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.enclosing.class", new Object[0]);
        public static final JCDiagnostic.Fragment BadModuleInfoName = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.module-info.name", new Object[0]);
        public static final JCDiagnostic.Fragment BadRequiresFlag = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.requires.flag", new Object[0]);
        public static final JCDiagnostic.Fragment BadSignature = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.signature", new Object[0]);
        public static final JCDiagnostic.Fragment BadTypeAnnotationValue = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.type.annotation.value", new Object[0]);
        public static final JCDiagnostic.Fragment Bound = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bound", new Object[0]);
        public static final JCDiagnostic.Fragment Canonical = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "canonical", new Object[0]);
        public static final JCDiagnostic.Fragment CanonicalCantHaveReturnStatement = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "canonical.cant.have.return.statement", new Object[0]);
        public static final JCDiagnostic.Fragment CanonicalMustNotContainExplicitConstructorInvocation = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "canonical.must.not.contain.explicit.constructor.invocation", new Object[0]);
        public static final JCDiagnostic.Fragment CanonicalMustNotDeclareTypeVariables = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "canonical.must.not.declare.type.variables", new Object[0]);
        public static final JCDiagnostic.Fragment CanonicalWithNameMismatch = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "canonical.with.name.mismatch", new Object[0]);
        public static final JCDiagnostic.Fragment CantResolveModules = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.modules", new Object[0]);
        public static final JCDiagnostic.Fragment ClassFileWrongClass = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "class.file.wrong.class", new Object[0]);
        public static final JCDiagnostic.Fragment Compact = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "compact", new Object[0]);
        public static final JCDiagnostic.Fragment ConditionalTargetCantBeVoid = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "conditional.target.cant.be.void", new Object[0]);
        public static final JCDiagnostic.Fragment DiamondAnonymousMethodsImplicitlyOverride = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "diamond.anonymous.methods.implicitly.override", new Object[0]);
        public static final JCDiagnostic.Fragment FatalErrCantClose = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "fatal.err.cant.close", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureCaseNull = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.case.null", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureDeconstructionPatterns = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.deconstruction.patterns", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureDiamondAndAnonClass = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.diamond.and.anon.class", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureFlexibleConstructors = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.flexible.constructors", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureImplicitClasses = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.implicit.classes", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureJavaBaseTransitive = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.java.base.transitive", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureModuleImports = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.module.imports", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureModules = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.modules", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureMultipleCaseLabels = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.multiple.case.labels", new Object[0]);
        public static final JCDiagnostic.Fragment FeaturePatternMatchingInstanceof = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.pattern.matching.instanceof", new Object[0]);
        public static final JCDiagnostic.Fragment FeaturePatternSwitch = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.pattern.switch", new Object[0]);
        public static final JCDiagnostic.Fragment FeaturePrimitivePatterns = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.primitive.patterns", new Object[0]);
        public static final JCDiagnostic.Fragment FeaturePrivateIntfMethods = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.private.intf.methods", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureRecords = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.records", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureReifiableTypesInstanceof = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.reifiable.types.instanceof", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureSealedClasses = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.sealed.classes", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureSwitchExpressions = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.switch.expressions", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureSwitchRules = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.switch.rules", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureTextBlocks = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.text.blocks", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureUnconditionalPatternsInInstanceof = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.unconditional.patterns.in.instanceof", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureUnnamedVariables = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.unnamed.variables", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureVarInTryWithResources = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.var.in.try.with.resources", new Object[0]);
        public static final JCDiagnostic.Fragment FeatureVarSyntaxInImplicitLambda = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.var.syntax.in.implicit.lambda", new Object[0]);
        public static final JCDiagnostic.Fragment FileDoesNotContainModule = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.does.not.contain.module", new Object[0]);
        public static final JCDiagnostic.Fragment Guard = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "guard", new Object[0]);
        public static final JCDiagnostic.Fragment IllegalFlagCombo = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.flag.combo", new Object[0]);
        public static final JCDiagnostic.Fragment IllegalStartOfClassFile = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.start.of.class.file", new Object[0]);
        public static final JCDiagnostic.Fragment ImplicitAndExplicitNotAllowed = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "implicit.and.explicit.not.allowed", new Object[0]);
        public static final JCDiagnostic.Fragment IncompatibleArgTypesInLambda = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.arg.types.in.lambda", new Object[0]);
        public static final JCDiagnostic.Fragment IncompatibleArgTypesInMref = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.arg.types.in.mref", new Object[0]);
        public static final JCDiagnostic.Fragment InnerCls = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "inner.cls", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameAnnotation = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.annotation", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameClass = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.class", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameConstructor = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.constructor", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameEnum = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.enum", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameInstanceInit = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.instance.init", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameInterface = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.interface", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameMethod = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.method", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameModule = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.module", new Object[0]);
        public static final JCDiagnostic.Fragment KindnamePackage = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.package", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameRecord = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.record", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameRecordComponent = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.record.component", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameStatic = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.static", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameStaticInit = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.static.init", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameTypeVariable = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.type.variable", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameTypeVariableBound = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.type.variable.bound", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameValue = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.value", new Object[0]);
        public static final JCDiagnostic.Fragment KindnameVariable = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "kindname.variable", new Object[0]);
        public static final JCDiagnostic.Fragment Lambda = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "lambda", new Object[0]);
        public static final JCDiagnostic.Fragment Local = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local", new Object[0]);
        public static final JCDiagnostic.Fragment LocalArrayMissingTarget = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.array.missing.target", new Object[0]);
        public static final JCDiagnostic.Fragment LocalCantInferNull = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.cant.infer.null", new Object[0]);
        public static final JCDiagnostic.Fragment LocalCantInferVoid = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.cant.infer.void", new Object[0]);
        public static final JCDiagnostic.Fragment LocalLambdaMissingTarget = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.lambda.missing.target", new Object[0]);
        public static final JCDiagnostic.Fragment LocalMissingInit = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.missing.init", new Object[0]);
        public static final JCDiagnostic.Fragment LocalMrefMissingTarget = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.mref.missing.target", new Object[0]);
        public static final JCDiagnostic.Fragment LocalSelfRef = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.self.ref", new Object[0]);
        public static final JCDiagnostic.Fragment LocnModule_path = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "locn.module_path", new Object[0]);
        public static final JCDiagnostic.Fragment LocnModule_source_path = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "locn.module_source_path", new Object[0]);
        public static final JCDiagnostic.Fragment LocnSystem_modules = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "locn.system_modules", new Object[0]);
        public static final JCDiagnostic.Fragment LocnUpgrade_module_path = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "locn.upgrade_module_path", new Object[0]);
        public static final JCDiagnostic.Fragment MalformedVarargMethod = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "malformed.vararg.method", new Object[0]);
        public static final JCDiagnostic.Fragment MethodMustBePublic = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "method.must.be.public", new Object[0]);
        public static final JCDiagnostic.Fragment ModuleInfoDefinitionExpected = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "module.info.definition.expected", new Object[0]);
        public static final JCDiagnostic.Fragment ModuleInfoInvalidSuperClass = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "module.info.invalid.super.class", new Object[0]);
        public static final JCDiagnostic.Fragment MrefInferAndExplicitParams = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "mref.infer.and.explicit.params", new Object[0]);
        public static final JCDiagnostic.Fragment MustNotBeSameClass = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "must.not.be.same.class", new Object[0]);
        public static final JCDiagnostic.Fragment NoArgs = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.args", new Object[0]);
        public static final JCDiagnostic.Fragment NonStatic = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "non.static", new Object[0]);
        public static final JCDiagnostic.Fragment ResumeAbort = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "resume.abort", new Object[0]);
        public static final JCDiagnostic.Fragment SourceUnavailable = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "source.unavailable", new Object[0]);
        public static final JCDiagnostic.Fragment StatExprExpected = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "stat.expr.expected", new Object[0]);
        public static final JCDiagnostic.Fragment Static = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", PsiKeyword.STATIC, new Object[0]);
        public static final JCDiagnostic.Fragment StaticMrefWithTargs = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "static.mref.with.targs", new Object[0]);
        public static final JCDiagnostic.Fragment SwitchExpressionTargetCantBeVoid = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "switch.expression.target.cant.be.void", new Object[0]);
        public static final JCDiagnostic.Fragment TokenBadSymbol = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "token.bad-symbol", new Object[0]);
        public static final JCDiagnostic.Fragment TokenCharacter = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "token.character", new Object[0]);
        public static final JCDiagnostic.Fragment TokenDouble = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "token.double", new Object[0]);
        public static final JCDiagnostic.Fragment TokenEndOfInput = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "token.end-of-input", new Object[0]);
        public static final JCDiagnostic.Fragment TokenFloat = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "token.float", new Object[0]);
        public static final JCDiagnostic.Fragment TokenIdentifier = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "token.identifier", new Object[0]);
        public static final JCDiagnostic.Fragment TokenInteger = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "token.integer", new Object[0]);
        public static final JCDiagnostic.Fragment TokenLongInteger = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "token.long-integer", new Object[0]);
        public static final JCDiagnostic.Fragment TokenString = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "token.string", new Object[0]);
        public static final JCDiagnostic.Fragment TypeCaptureof1 = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.captureof.1", new Object[0]);
        public static final JCDiagnostic.Fragment TypeMustBeIdenticalToCorrespondingRecordComponentType = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.must.be.identical.to.corresponding.record.component.type", new Object[0]);
        public static final JCDiagnostic.Fragment TypeNone = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.none", new Object[0]);
        public static final JCDiagnostic.Fragment TypeNull = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.null", new Object[0]);
        public static final JCDiagnostic.Fragment TypeReqArrayOrIterable = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.req.array.or.iterable", new Object[0]);
        public static final JCDiagnostic.Fragment TypeReqClass = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.req.class", new Object[0]);
        public static final JCDiagnostic.Fragment TypeReqClassArray = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.req.class.array", new Object[0]);
        public static final JCDiagnostic.Fragment TypeReqExact = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.req.exact", new Object[0]);
        public static final JCDiagnostic.Fragment TypeReqRef = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.req.ref", new Object[0]);
        public static final JCDiagnostic.Fragment UnableToAccessFile = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unable.to.access.file", new Object[0]);
        public static final JCDiagnostic.Fragment Unbound = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unbound", new Object[0]);
        public static final JCDiagnostic.Fragment UncheckedAssign = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.assign", new Object[0]);
        public static final JCDiagnostic.Fragment UncheckedCastToType = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.cast.to.type", new Object[0]);
        public static final JCDiagnostic.Fragment UndeclTypeVar = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "undecl.type.var", new Object[0]);
        public static final JCDiagnostic.Fragment UnexpectedConstPoolTagAt = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unexpected.const.pool.tag.at", new Object[0]);
        public static final JCDiagnostic.Fragment UnexpectedRetVal = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unexpected.ret.val", new Object[0]);
        public static final JCDiagnostic.Fragment UnicodeStrNotSupported = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unicode.str.not.supported", new Object[0]);
        public static final JCDiagnostic.Fragment UnnamedModule = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unnamed.module", new Object[0]);
        public static final JCDiagnostic.Fragment UnnamedPackage = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unnamed.package", new Object[0]);
        public static final JCDiagnostic.Fragment UserSelectedCompletionFailure = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "user.selected.completion.failure", new Object[0]);
        public static final JCDiagnostic.Fragment VarAndExplicitNotAllowed = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "var.and.explicit.not.allowed", new Object[0]);
        public static final JCDiagnostic.Fragment VarAndImplicitNotAllowed = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "var.and.implicit.not.allowed", new Object[0]);
        public static final JCDiagnostic.Fragment VersionNotAvailable = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "version.not.available", new Object[0]);
        public static final JCDiagnostic.Fragment WhereDescriptionCaptured = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.description.captured", new Object[0]);
        public static final JCDiagnostic.Fragment WrongVersion = new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "wrong.version", new Object[0]);

        public static JCDiagnostic.Fragment AccessorReturnTypeDoesntMatch(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "accessor.return.type.doesnt.match", symbol, symbol2);
        }

        public static JCDiagnostic.Fragment AnachronisticModuleInfo(String str, String str2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "anachronistic.module.info", str, str2);
        }

        public static JCDiagnostic.Fragment AnonymousClass(Name name) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "anonymous.class", name);
        }

        public static JCDiagnostic.Fragment ApplicableMethodFound(int i, Symbol symbol, Void r4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "applicable.method.found", Integer.valueOf(i), symbol, r4);
        }

        public static JCDiagnostic.Fragment ApplicableMethodFound1(int i, Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "applicable.method.found.1", Integer.valueOf(i), symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment ApplicableMethodFound2(int i, JCDiagnostic.Fragment fragment, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "applicable.method.found.2", Integer.valueOf(i), fragment, symbol);
        }

        public static JCDiagnostic.Fragment ApplicableMethodFound3(int i, JCDiagnostic.Fragment fragment, Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "applicable.method.found.3", Integer.valueOf(i), fragment, symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment BadClassFile(Name name) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.class.file", name);
        }

        public static JCDiagnostic.Fragment BadClassFileHeader(File file, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.class.file.header", file, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment BadClassTruncatedAtOffset(int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.class.truncated.at.offset", Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment BadConstPoolEntry(File file, String str, int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.const.pool.entry", file, str, Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment BadConstPoolIndex(File file, int i, int i2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.const.pool.index", file, Integer.valueOf(i), Integer.valueOf(i2));
        }

        public static JCDiagnostic.Fragment BadConstantRange(String str, Symbol symbol, Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.constant.range", str, symbol, type);
        }

        public static JCDiagnostic.Fragment BadConstantValue(String str, Symbol symbol, String str2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.constant.value", str, symbol, str2);
        }

        public static JCDiagnostic.Fragment BadConstantValueType(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.constant.value.type", type);
        }

        public static JCDiagnostic.Fragment BadEnclosingMethod(Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.enclosing.method", symbol);
        }

        public static JCDiagnostic.Fragment BadInstanceMethodInUnboundLookup(Kinds.Kind kind, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.instance.method.in.unbound.lookup", kind, symbol);
        }

        public static JCDiagnostic.Fragment BadIntersectionTargetForFunctionalExpr(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.intersection.target.for.functional.expr", jCDiagnostic);
        }

        public static JCDiagnostic.Fragment BadSourceFileHeader(File file, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.source.file.header", file, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment BadStaticMethodInBoundLookup(Kinds.Kind kind, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.static.method.in.bound.lookup", kind, symbol);
        }

        public static JCDiagnostic.Fragment BadStaticMethodInUnboundLookup(Kinds.Kind kind, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.static.method.in.unbound.lookup", kind, symbol);
        }

        public static JCDiagnostic.Fragment BadUtf8ByteSequenceAt(int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.utf8.byte.sequence.at", Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment CanonicalMustNotHaveStrongerAccess(Set<? extends FlagsEnum> set) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "canonical.must.not.have.stronger.access", set);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(List<? extends Type> list, List<? extends Type> list2, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", list, list2, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplyDiamond1(JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", jCDiagnostic, jCDiagnostic2);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, List<? extends Type> list2, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, list2, kind2, type, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplySymbols(Kinds.Kind kind, Name name, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbols", kind, name, list);
        }

        public static JCDiagnostic.Fragment CantHide(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.hide", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment CantImplement(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.implement", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment CantOverride(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.override", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment CantResolveArgs(Kinds.KindName kindName, Name name, Void r5, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.args", kindName, name, r5, list);
        }

        public static JCDiagnostic.Fragment CantResolveLocationArgs(Kinds.KindName kindName, Name name, Void r5, List<? extends Type> list, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.location.args", kindName, name, r5, list, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantResolveLocationArgsParams(Kinds.KindName kindName, Name name, List<? extends Type> list, List list2, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.location.args.params", kindName, name, list, list2, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CapturedType(int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "captured.type", Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment ClashesWith(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "clashes.with", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment ClassFileNotFound(Name name) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "class.file.not.found", name);
        }

        public static JCDiagnostic.Fragment ClassIsNotSealed(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "class.is.not.sealed", str);
        }

        public static JCDiagnostic.Fragment CountError(int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "count.error", Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment CountErrorPlural(int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "count.error.plural", Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment CountErrorRecompile(int i, int i2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "count.error.recompile", Integer.valueOf(i), Integer.valueOf(i2));
        }

        public static JCDiagnostic.Fragment CountWarn(int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "count.warn", Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment CountWarnPlural(int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "count.warn.plural", Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment CountWarnRecompile(int i, int i2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "count.warn.recompile", Integer.valueOf(i), Integer.valueOf(i2));
        }

        public static JCDiagnostic.Fragment Descriptor(Name name, List<? extends Type> list, Type type, List<? extends Type> list2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "descriptor", name, list, type, list2);
        }

        public static JCDiagnostic.Fragment DescriptorThrows(Name name, List<? extends Type> list, Type type, List<? extends Type> list2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "descriptor.throws", name, list, type, list2);
        }

        public static JCDiagnostic.Fragment Diamond(Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "diamond", symbol);
        }

        public static JCDiagnostic.Fragment DiamondAndExplicitParams(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "diamond.and.explicit.params", type);
        }

        public static JCDiagnostic.Fragment DiamondInvalidArg(List<? extends Type> list, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "diamond.invalid.arg", list, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment DiamondInvalidArgs(List<? extends Type> list, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "diamond.invalid.args", list, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment DiamondNonGeneric(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "diamond.non.generic", type);
        }

        public static JCDiagnostic.Fragment DoesntExtendSealed(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "doesnt.extend.sealed", type);
        }

        public static JCDiagnostic.Fragment DoesntImplementSealed(Kinds.KindName kindName, Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "doesnt.implement.sealed", kindName, type);
        }

        public static JCDiagnostic.Fragment EqBounds(List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "eq.bounds", list);
        }

        public static JCDiagnostic.Fragment ExceptionMessage(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "exception.message", str);
        }

        public static JCDiagnostic.Fragment ExplicitParamDoNotConformToBounds(Type type, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "explicit.param.do.not.conform.to.bounds", type, list);
        }

        public static JCDiagnostic.Fragment FatalErrCantLocateCtor(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "fatal.err.cant.locate.ctor", type);
        }

        public static JCDiagnostic.Fragment FatalErrCantLocateField(Name name) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "fatal.err.cant.locate.field", name);
        }

        public static JCDiagnostic.Fragment FatalErrCantLocateMeth(Name name) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "fatal.err.cant.locate.meth", name);
        }

        public static JCDiagnostic.Fragment FeatureNotSupportedInSource(JCDiagnostic jCDiagnostic, String str, String str2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.not.supported.in.source", jCDiagnostic, str, str2);
        }

        public static JCDiagnostic.Fragment FeatureNotSupportedInSourcePlural(JCDiagnostic jCDiagnostic, String str, String str2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.not.supported.in.source.plural", jCDiagnostic, str, str2);
        }

        public static JCDiagnostic.Fragment FileDoesNotContainPackage(Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.does.not.contain.package", symbol);
        }

        public static JCDiagnostic.Fragment FileDoesntContainClass(Name name) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.doesnt.contain.class", name);
        }

        public static JCDiagnostic.Fragment IllegalSignature(Symbol symbol, Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.signature", symbol, type);
        }

        public static JCDiagnostic.Fragment InaccessibleVarargsType(Type type, Kinds.Kind kind, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "inaccessible.varargs.type", type, kind, symbol);
        }

        public static JCDiagnostic.Fragment InapplicableMethod(Kinds.KindName kindName, Symbol symbol, Symbol symbol2, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "inapplicable.method", kindName, symbol, symbol2, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment IncompatibleAbstractDefault(Kinds.KindName kindName, Type type, Name name, List<? extends Type> list, Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.abstract.default", kindName, type, name, list, symbol, symbol2);
        }

        public static JCDiagnostic.Fragment IncompatibleAbstracts(Kinds.KindName kindName, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.abstracts", kindName, symbol);
        }

        public static JCDiagnostic.Fragment IncompatibleBounds(Type type, JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.bounds", type, fragment, fragment2);
        }

        public static JCDiagnostic.Fragment IncompatibleDescsInFunctionalIntf(Kinds.KindName kindName, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.descs.in.functional.intf", kindName, symbol);
        }

        public static JCDiagnostic.Fragment IncompatibleDiffRet(Name name, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.diff.ret", name, list);
        }

        public static JCDiagnostic.Fragment IncompatibleEqBounds(Type type, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.eq.bounds", type, list);
        }

        public static JCDiagnostic.Fragment IncompatibleRetTypeInLambda(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.ret.type.in.lambda", jCDiagnostic);
        }

        public static JCDiagnostic.Fragment IncompatibleRetTypeInMref(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.ret.type.in.mref", jCDiagnostic);
        }

        public static JCDiagnostic.Fragment IncompatibleTypeInConditional(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.type.in.conditional", jCDiagnostic);
        }

        public static JCDiagnostic.Fragment IncompatibleTypeInSwitchExpression(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.type.in.switch.expression", jCDiagnostic);
        }

        public static JCDiagnostic.Fragment IncompatibleUnrelatedDefaults(Kinds.KindName kindName, Type type, Name name, List<? extends Type> list, Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.unrelated.defaults", kindName, type, name, list, symbol, symbol2);
        }

        public static JCDiagnostic.Fragment IncompatibleUpperBounds(Type type, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.upper.bounds", type, list);
        }

        public static JCDiagnostic.Fragment InconvertibleTypes(Type type, Type type2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "inconvertible.types", type, type2);
        }

        public static JCDiagnostic.Fragment InferArgLengthMismatch(List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "infer.arg.length.mismatch", list);
        }

        public static JCDiagnostic.Fragment InferNoConformingAssignmentExists(List<? extends Type> list, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "infer.no.conforming.assignment.exists", list, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment InferNoConformingInstanceExists(List<? extends Type> list, Type type, Type type2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "infer.no.conforming.instance.exists", list, type, type2);
        }

        public static JCDiagnostic.Fragment InferVarargsArgumentMismatch(List<? extends Type> list, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "infer.varargs.argument.mismatch", list, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment InferredDoNotConformToEqBounds(Type type, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "inferred.do.not.conform.to.eq.bounds", type, list);
        }

        public static JCDiagnostic.Fragment InferredDoNotConformToLowerBounds(Type type, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "inferred.do.not.conform.to.lower.bounds", type, list);
        }

        public static JCDiagnostic.Fragment InferredDoNotConformToUpperBounds(Type type, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "inferred.do.not.conform.to.upper.bounds", type, list);
        }

        public static JCDiagnostic.Fragment IntersectionType(int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "intersection.type", Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment InvalidDefaultInterface(String str, String str2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.default.interface", str, str2);
        }

        public static JCDiagnostic.Fragment InvalidGenericLambdaTarget(Type type, Kinds.KindName kindName, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.generic.lambda.target", type, kindName, symbol);
        }

        public static JCDiagnostic.Fragment InvalidMref(Kinds.KindName kindName, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.mref", kindName, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment InvalidStaticInterface(String str, String str2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.static.interface", str, str2);
        }

        public static JCDiagnostic.Fragment IsATypeVariable(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "is.a.type.variable", type);
        }

        public static JCDiagnostic.Fragment IsDuplicated(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "is.duplicated", type);
        }

        public static JCDiagnostic.Fragment Location(Kinds.KindName kindName, Type type, Void r5) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "location", kindName, type, r5);
        }

        public static JCDiagnostic.Fragment Location1(Kinds.KindName kindName, Symbol symbol, Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "location.1", kindName, symbol, type);
        }

        public static JCDiagnostic.Fragment LowerBounds(List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "lower.bounds", list);
        }

        public static JCDiagnostic.Fragment MethodDescriptorInvalid(Name name) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "method.descriptor.invalid", name);
        }

        public static JCDiagnostic.Fragment MissingRetVal(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "missing.ret.val", type);
        }

        public static JCDiagnostic.Fragment ModuleNameMismatch(Name name, Name name2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "module.name.mismatch", name, name2);
        }

        public static JCDiagnostic.Fragment ModuleNonZeroOpens(Name name) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "module.non.zero.opens", name);
        }

        public static JCDiagnostic.Fragment MustNotBeSupertype(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "must.not.be.supertype", type);
        }

        public static JCDiagnostic.Fragment NoAbstracts(Kinds.KindName kindName, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.abstracts", kindName, symbol);
        }

        public static JCDiagnostic.Fragment NoConformingAssignmentExists(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.conforming.assignment.exists", jCDiagnostic);
        }

        public static JCDiagnostic.Fragment NoSuitableFunctionalIntfInst(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.suitable.functional.intf.inst", type);
        }

        public static JCDiagnostic.Fragment NoUniqueMaximalInstanceExists(Type type, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.unique.maximal.instance.exists", type, list);
        }

        public static JCDiagnostic.Fragment NoUniqueMinimalInstanceExists(Type type, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.unique.minimal.instance.exists", type, list);
        }

        public static JCDiagnostic.Fragment NotAFunctionalIntf(Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.a.functional.intf", symbol);
        }

        public static JCDiagnostic.Fragment NotAFunctionalIntf1(Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.a.functional.intf.1", symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment NotAnIntfComponent(Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.an.intf.component", symbol);
        }

        public static JCDiagnostic.Fragment NotApplicableMethodFound(int i, Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.applicable.method.found", Integer.valueOf(i), symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment NotDefAccessClassIntfCantAccess(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.class.intf.cant.access", symbol, symbol2);
        }

        public static JCDiagnostic.Fragment NotDefAccessClassIntfCantAccessReason(Symbol symbol, Symbol symbol2, Symbol symbol3, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.class.intf.cant.access.reason", symbol, symbol2, symbol3, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment NotDefAccessDoesNotRead(Symbol symbol, Symbol symbol2, Symbol symbol3) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.does.not.read", symbol, symbol2, symbol3);
        }

        public static JCDiagnostic.Fragment NotDefAccessDoesNotReadFromUnnamed(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.does.not.read.from.unnamed", symbol, symbol2);
        }

        public static JCDiagnostic.Fragment NotDefAccessDoesNotReadUnnamed(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.does.not.read.unnamed", symbol, symbol2);
        }

        public static JCDiagnostic.Fragment NotDefAccessNotExported(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.not.exported", symbol, symbol2);
        }

        public static JCDiagnostic.Fragment NotDefAccessNotExportedFromUnnamed(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.not.exported.from.unnamed", symbol, symbol2);
        }

        public static JCDiagnostic.Fragment NotDefAccessNotExportedToModule(Symbol symbol, Symbol symbol2, Symbol symbol3) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.not.exported.to.module", symbol, symbol2, symbol3);
        }

        public static JCDiagnostic.Fragment NotDefAccessNotExportedToModuleFromUnnamed(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.not.exported.to.module.from.unnamed", symbol, symbol2);
        }

        public static JCDiagnostic.Fragment NotDefAccessPackageCantAccess(Symbol symbol, Symbol symbol2, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.package.cant.access", symbol, symbol2, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment NotDefPublicCantAccess(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.public.cant.access", symbol, symbol2);
        }

        public static JCDiagnostic.Fragment OverriddenDefault(Symbol symbol, Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "overridden.default", symbol, type);
        }

        public static JCDiagnostic.Fragment PackageNotVisible(Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "package.not.visible", symbol, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment PartialInstSig(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "partial.inst.sig", type);
        }

        public static JCDiagnostic.Fragment PossibleLossOfPrecision(Type type, Type type2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "possible.loss.of.precision", type, type2);
        }

        public static JCDiagnostic.Fragment ProbFoundReq(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "prob.found.req", jCDiagnostic);
        }

        public static JCDiagnostic.Fragment RedundantSupertype(Symbol symbol, Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "redundant.supertype", symbol, type);
        }

        public static JCDiagnostic.Fragment RefAmbiguous(Name name, Kinds.Kind kind, Symbol symbol, Symbol symbol2, Kinds.Kind kind2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "ref.ambiguous", name, kind, symbol, symbol2, kind2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment ReportAccess(Symbol symbol, Set<? extends Modifier> set, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "report.access", symbol, set, symbol2);
        }

        public static JCDiagnostic.Fragment SourceNoBootclasspath(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "source.no.bootclasspath", str);
        }

        public static JCDiagnostic.Fragment SourceNoBootclasspathWithTarget(String str, String str2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "source.no.bootclasspath.with.target", str, str2);
        }

        public static JCDiagnostic.Fragment SourceNoSystemModulesPath(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "source.no.system.modules.path", str);
        }

        public static JCDiagnostic.Fragment SourceNoSystemModulesPathWithTarget(String str, String str2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "source.no.system.modules.path.with.target", str, str2);
        }

        public static JCDiagnostic.Fragment SyntheticNameConflict(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "synthetic.name.conflict", symbol, symbol2);
        }

        public static JCDiagnostic.Fragment ThrowsClauseNotAllowedForCanonicalConstructor(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "throws.clause.not.allowed.for.canonical.constructor", fragment);
        }

        public static JCDiagnostic.Fragment TryNotApplicableToType(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "try.not.applicable.to.type", jCDiagnostic);
        }

        public static JCDiagnostic.Fragment TypeAnnotation(List<? extends Attribute.Compound> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.annotation", list);
        }

        public static JCDiagnostic.Fragment TypeAnnotation1(Attribute.Compound compound) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.annotation.1", compound);
        }

        public static JCDiagnostic.Fragment TypeCaptureof(Name name, Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.captureof", name, type);
        }

        public static JCDiagnostic.Fragment TypeParameter(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.parameter", type);
        }

        public static JCDiagnostic.Fragment TypeVar(Name name, int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "type.var", name, Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment UncheckedClashWith(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.clash.with", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment UncheckedImplement(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.implement", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment UncheckedOverride(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.override", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment UpperBounds(List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "upper.bounds", list);
        }

        public static JCDiagnostic.Fragment VarargsArgumentMismatch(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.argument.mismatch", jCDiagnostic);
        }

        public static JCDiagnostic.Fragment VarargsClashWith(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.clash.with", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment VarargsImplement(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.implement", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment VarargsOverride(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.override", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.Fragment VarargsTrustmeOnNonVarargsAccessor(Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.trustme.on.non.varargs.accessor", symbol);
        }

        public static JCDiagnostic.Fragment VarargsTrustmeOnNonVarargsMeth(Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.trustme.on.non.varargs.meth", symbol);
        }

        public static JCDiagnostic.Fragment VarargsTrustmeOnReifiableVarargs(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.trustme.on.reifiable.varargs", type);
        }

        public static JCDiagnostic.Fragment VarargsTrustmeOnVirtualVarargs(Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.trustme.on.virtual.varargs", symbol);
        }

        public static JCDiagnostic.Fragment VarargsTrustmeOnVirtualVarargsFinalOnly(Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.trustme.on.virtual.varargs.final.only", symbol);
        }

        public static JCDiagnostic.Fragment VerboseCheckingAttribution(Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.checking.attribution", symbol);
        }

        public static JCDiagnostic.Fragment VerboseClasspath(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.classpath", str);
        }

        public static JCDiagnostic.Fragment VerboseLoading(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.loading", str);
        }

        public static JCDiagnostic.Fragment VerboseParsingDone(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.parsing.done", str);
        }

        public static JCDiagnostic.Fragment VerboseParsingStarted(File file) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.parsing.started", file);
        }

        public static JCDiagnostic.Fragment VerboseSourcepath(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.sourcepath", str);
        }

        public static JCDiagnostic.Fragment VerboseTotal(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.total", str);
        }

        public static JCDiagnostic.Fragment VerboseWroteFile(File file) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.wrote.file", file);
        }

        public static JCDiagnostic.Fragment WhereCaptured(Type type, Type type2, Type type3, Type type4) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.captured", type, type2, type3, type4);
        }

        public static JCDiagnostic.Fragment WhereCaptured1(Type type, Type type2, Void r5, Type type3) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.captured.1", type, type2, r5, type3);
        }

        public static JCDiagnostic.Fragment WhereDescriptionCaptured1(Set<? extends Type> set) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.description.captured.1", set);
        }

        public static JCDiagnostic.Fragment WhereDescriptionIntersection(Set<? extends Type> set) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.description.intersection", set);
        }

        public static JCDiagnostic.Fragment WhereDescriptionIntersection1(Set<? extends Type> set) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.description.intersection.1", set);
        }

        public static JCDiagnostic.Fragment WhereDescriptionTypevar(Set<? extends Type> set) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.description.typevar", set);
        }

        public static JCDiagnostic.Fragment WhereDescriptionTypevar1(Set<? extends Type> set) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.description.typevar.1", set);
        }

        public static JCDiagnostic.Fragment WhereFreshTypevar(Type type, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.fresh.typevar", type, list);
        }

        public static JCDiagnostic.Fragment WhereIntersection(Type type, List<? extends Type> list) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.intersection", type, list);
        }

        public static JCDiagnostic.Fragment WhereTypevar(Type type, List<? extends Type> list, Kinds.Kind kind, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.typevar", type, list, kind, symbol);
        }

        public static JCDiagnostic.Fragment WhereTypevar1(Type type, List<? extends Type> list, Kinds.Kind kind, Symbol symbol) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "where.typevar.1", type, list, kind, symbol);
        }

        public static JCDiagnostic.Fragment WrongNumberTypeArgs(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "wrong.number.type.args", str);
        }

        public static JCDiagnostic.Fragment XPrintProcessorInfo(String str, String str2, boolean z) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "x.print.processor.info", str, str2, Boolean.valueOf(z));
        }

        public static JCDiagnostic.Fragment XPrintRounds(int i, String str, Set<? extends Symbol> set, boolean z) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "x.print.rounds", Integer.valueOf(i), str, set, Boolean.valueOf(z));
        }

        public static JCDiagnostic.Fragment BadClassFileHeader(File file, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.class.file.header", file, fragment);
        }

        public static JCDiagnostic.Fragment BadIntersectionTargetForFunctionalExpr(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.intersection.target.for.functional.expr", fragment);
        }

        public static JCDiagnostic.Fragment BadSourceFileHeader(File file, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.source.file.header", file, fragment);
        }

        public static JCDiagnostic.Fragment CanonicalMustNotHaveStrongerAccess(String str) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "canonical.must.not.have.stronger.access", str);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(List<? extends Type> list, List<? extends Type> list2, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", list, list2, fragment);
        }

        public static JCDiagnostic.Fragment CantApplyDiamond1(JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", jCDiagnostic, fragment);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, List<? extends Type> list2, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, list2, kind2, type, fragment);
        }

        public static JCDiagnostic.Fragment CantResolveLocationArgs(Kinds.KindName kindName, Name name, Void r5, List<? extends Type> list, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.location.args", kindName, name, r5, list, fragment);
        }

        public static JCDiagnostic.Fragment CantResolveLocationArgsParams(Kinds.KindName kindName, Name name, List<? extends Type> list, List list2, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.resolve.location.args.params", kindName, name, list, list2, fragment);
        }

        public static JCDiagnostic.Fragment DiamondInvalidArg(List<? extends Type> list, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "diamond.invalid.arg", list, fragment);
        }

        public static JCDiagnostic.Fragment DiamondInvalidArgs(List<? extends Type> list, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "diamond.invalid.args", list, fragment);
        }

        public static JCDiagnostic.Fragment FeatureNotSupportedInSource(JCDiagnostic.Fragment fragment, String str, String str2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.not.supported.in.source", fragment, str, str2);
        }

        public static JCDiagnostic.Fragment FeatureNotSupportedInSourcePlural(JCDiagnostic.Fragment fragment, String str, String str2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "feature.not.supported.in.source.plural", fragment, str, str2);
        }

        public static JCDiagnostic.Fragment InapplicableMethod(Kinds.KindName kindName, Symbol symbol, Symbol symbol2, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "inapplicable.method", kindName, symbol, symbol2, fragment);
        }

        public static JCDiagnostic.Fragment IncompatibleRetTypeInLambda(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.ret.type.in.lambda", fragment);
        }

        public static JCDiagnostic.Fragment IncompatibleRetTypeInMref(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.ret.type.in.mref", fragment);
        }

        public static JCDiagnostic.Fragment IncompatibleTypeInConditional(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.type.in.conditional", fragment);
        }

        public static JCDiagnostic.Fragment IncompatibleTypeInSwitchExpression(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "incompatible.type.in.switch.expression", fragment);
        }

        public static JCDiagnostic.Fragment InferNoConformingAssignmentExists(List<? extends Type> list, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "infer.no.conforming.assignment.exists", list, fragment);
        }

        public static JCDiagnostic.Fragment InferVarargsArgumentMismatch(List<? extends Type> list, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "infer.varargs.argument.mismatch", list, fragment);
        }

        public static JCDiagnostic.Fragment InvalidMref(Kinds.KindName kindName, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.mref", kindName, fragment);
        }

        public static JCDiagnostic.Fragment Location(Kinds.KindName kindName, Symbol symbol, Void r5) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "location", kindName, symbol, r5);
        }

        public static JCDiagnostic.Fragment NoConformingAssignmentExists(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "no.conforming.assignment.exists", fragment);
        }

        public static JCDiagnostic.Fragment NotAFunctionalIntf1(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.a.functional.intf.1", symbol, fragment);
        }

        public static JCDiagnostic.Fragment NotAnIntfComponent(Type type) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.an.intf.component", type);
        }

        public static JCDiagnostic.Fragment NotDefAccessClassIntfCantAccessReason(Symbol symbol, Symbol symbol2, Symbol symbol3, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.class.intf.cant.access.reason", symbol, symbol2, symbol3, fragment);
        }

        public static JCDiagnostic.Fragment NotDefAccessPackageCantAccess(Symbol symbol, Symbol symbol2, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.def.access.package.cant.access", symbol, symbol2, fragment);
        }

        public static JCDiagnostic.Fragment PackageNotVisible(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "package.not.visible", symbol, fragment);
        }

        public static JCDiagnostic.Fragment ProbFoundReq(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "prob.found.req", fragment);
        }

        public static JCDiagnostic.Fragment BadClassFileHeader(JavaFileObject javaFileObject, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.class.file.header", javaFileObject, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment BadSourceFileHeader(JavaFileObject javaFileObject, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.source.file.header", javaFileObject, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(List<? extends Type> list, JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", list, jCDiagnostic, jCDiagnostic2);
        }

        public static JCDiagnostic.Fragment CantApplyDiamond1(JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", fragment, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, JCDiagnostic jCDiagnostic, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, jCDiagnostic, kind2, type, jCDiagnostic2);
        }

        public static JCDiagnostic.Fragment RedundantSupertype(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "redundant.supertype", symbol, symbol2);
        }

        public static JCDiagnostic.Fragment TryNotApplicableToType(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "try.not.applicable.to.type", fragment);
        }

        public static JCDiagnostic.Fragment VarargsArgumentMismatch(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "varargs.argument.mismatch", fragment);
        }

        public static JCDiagnostic.Fragment VerboseParsingStarted(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.parsing.started", javaFileObject);
        }

        public static JCDiagnostic.Fragment VerboseWroteFile(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.wrote.file", javaFileObject);
        }

        public static JCDiagnostic.Fragment BadClassFileHeader(JavaFileObject javaFileObject, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.class.file.header", javaFileObject, fragment);
        }

        public static JCDiagnostic.Fragment BadSourceFileHeader(JavaFileObject javaFileObject, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.source.file.header", javaFileObject, fragment);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(List<? extends Type> list, JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", list, jCDiagnostic, fragment);
        }

        public static JCDiagnostic.Fragment CantApplyDiamond1(JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", fragment, fragment2);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, JCDiagnostic jCDiagnostic, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, jCDiagnostic, kind2, type, fragment);
        }

        public static JCDiagnostic.Fragment VerboseParsingStarted(Path path) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.parsing.started", path);
        }

        public static JCDiagnostic.Fragment VerboseWroteFile(Path path) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.wrote.file", path);
        }

        public static JCDiagnostic.Fragment BadClassFileHeader(Path path, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.class.file.header", path, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment BadSourceFileHeader(Path path, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.source.file.header", path, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(List<? extends Type> list, JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", list, fragment, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplyDiamond1(Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", type, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, JCDiagnostic.Fragment fragment, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, fragment, kind2, type, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment ApplicableMethodFound1(int i, Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "applicable.method.found.1", Integer.valueOf(i), symbol, fragment);
        }

        public static JCDiagnostic.Fragment ApplicableMethodFound3(int i, JCDiagnostic.Fragment fragment, Symbol symbol, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "applicable.method.found.3", Integer.valueOf(i), fragment, symbol, fragment2);
        }

        public static JCDiagnostic.Fragment BadClassFileHeader(Path path, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.class.file.header", path, fragment);
        }

        public static JCDiagnostic.Fragment BadConstPoolEntry(JavaFileObject javaFileObject, String str, int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.const.pool.entry", javaFileObject, str, Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment BadSourceFileHeader(Path path, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.source.file.header", path, fragment);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(List<? extends Type> list, JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", list, fragment, fragment2);
        }

        public static JCDiagnostic.Fragment CantApplyDiamond1(Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.diamond.1", type, fragment);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, List<? extends Type> list, JCDiagnostic.Fragment fragment, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, list, fragment, kind2, type, fragment2);
        }

        public static JCDiagnostic.Fragment NotApplicableMethodFound(int i, Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "not.applicable.method.found", Integer.valueOf(i), symbol, fragment);
        }

        public static JCDiagnostic.Fragment BadConstPoolEntry(Path path, String str, int i) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.const.pool.entry", path, str, Integer.valueOf(i));
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic jCDiagnostic, List<? extends Type> list, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", jCDiagnostic, list, jCDiagnostic2);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, List<? extends Type> list, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, list, kind2, type, jCDiagnostic2);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic jCDiagnostic, List<? extends Type> list, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", jCDiagnostic, list, fragment);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, List<? extends Type> list, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, list, kind2, type, fragment);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2, JCDiagnostic jCDiagnostic3) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", jCDiagnostic, jCDiagnostic2, jCDiagnostic3);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic3) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, jCDiagnostic2, kind2, type, jCDiagnostic3);
        }

        public static JCDiagnostic.Fragment BadConstPoolIndex(JavaFileObject javaFileObject, int i, int i2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.const.pool.index", javaFileObject, Integer.valueOf(i), Integer.valueOf(i2));
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", jCDiagnostic, jCDiagnostic2, fragment);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, jCDiagnostic2, kind2, type, fragment);
        }

        public static JCDiagnostic.Fragment BadConstPoolIndex(Path path, int i, int i2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.const.pool.index", path, Integer.valueOf(i), Integer.valueOf(i2));
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", jCDiagnostic, fragment, jCDiagnostic2);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, fragment, kind2, type, jCDiagnostic2);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", jCDiagnostic, fragment, fragment2);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, jCDiagnostic, fragment, kind2, type, fragment2);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic.Fragment fragment, List<? extends Type> list, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", fragment, list, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, List<? extends Type> list, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, list, kind2, type, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic.Fragment fragment, List<? extends Type> list, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", fragment, list, fragment2);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, List<? extends Type> list, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, list, kind2, type, fragment2);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", fragment, jCDiagnostic, jCDiagnostic2);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, jCDiagnostic, kind2, type, jCDiagnostic2);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", fragment, jCDiagnostic, fragment2);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, jCDiagnostic, kind2, type, fragment2);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", fragment, fragment2, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2, Kinds.Kind kind2, Type type, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, fragment2, kind2, type, jCDiagnostic);
        }

        public static JCDiagnostic.Fragment CantApplyArrayCtor(JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2, JCDiagnostic.Fragment fragment3) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.array.ctor", fragment, fragment2, fragment3);
        }

        public static JCDiagnostic.Fragment CantApplySymbol(Kinds.Kind kind, Name name, JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2, Kinds.Kind kind2, Type type, JCDiagnostic.Fragment fragment3) {
            return new JCDiagnostic.Fragment(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "cant.apply.symbol", kind, name, fragment, fragment2, kind2, type, fragment3);
        }
    }

    public static class Notes {
        public static final JCDiagnostic.Note CompressedDiags = new JCDiagnostic.Note(EnumSet.of(JCDiagnostic.DiagnosticFlag.MANDATORY), "compiler", "compressed.diags", new Object[0]);
        public static final JCDiagnostic.Note DeprecatedPlural = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deprecated.plural", new Object[0]);
        public static final JCDiagnostic.Note DeprecatedPluralAdditional = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deprecated.plural.additional", new Object[0]);
        public static final JCDiagnostic.Note DeprecatedRecompile = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deprecated.recompile", new Object[0]);
        public static final JCDiagnostic.Note ImplicitAnnotationProcessing = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "implicit.annotation.processing", new Object[0]);
        public static final JCDiagnostic.Note Note = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "note", new Object[0]);
        public static final JCDiagnostic.Note PreviewRecompile = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.recompile", new Object[0]);
        public static final JCDiagnostic.Note RemovalPlural = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "removal.plural", new Object[0]);
        public static final JCDiagnostic.Note RemovalPluralAdditional = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "removal.plural.additional", new Object[0]);
        public static final JCDiagnostic.Note RemovalRecompile = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "removal.recompile", new Object[0]);
        public static final JCDiagnostic.Note UncheckedPlural = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.plural", new Object[0]);
        public static final JCDiagnostic.Note UncheckedPluralAdditional = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.plural.additional", new Object[0]);
        public static final JCDiagnostic.Note UncheckedRecompile = new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.recompile", new Object[0]);

        public static JCDiagnostic.Note DeferredMethodInst(Symbol symbol, Type type, Type type2) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deferred.method.inst", symbol, type, type2);
        }

        public static JCDiagnostic.Note DeprecatedFilename(File file) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deprecated.filename", file);
        }

        public static JCDiagnostic.Note DeprecatedFilenameAdditional(File file) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deprecated.filename.additional", file);
        }

        public static JCDiagnostic.Note LambdaStat(boolean z, Symbol symbol) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "lambda.stat", Boolean.valueOf(z), symbol);
        }

        public static JCDiagnostic.Note MethodRefSearchResultsMulti(JCDiagnostic.Fragment fragment, String str, int i) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "method.ref.search.results.multi", fragment, str, Integer.valueOf(i));
        }

        public static JCDiagnostic.Note MrefStat(boolean z, Void r4) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "mref.stat", Boolean.valueOf(z), r4);
        }

        public static JCDiagnostic.Note MrefStat1(boolean z, Symbol symbol) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "mref.stat.1", Boolean.valueOf(z), symbol);
        }

        public static JCDiagnostic.Note MultipleElements(String str, String str2, String str3) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "multiple.elements", str, str2, str3);
        }

        public static JCDiagnostic.Note PreviewFilename(File file, Source source) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.filename", file, source);
        }

        public static JCDiagnostic.Note PreviewFilenameAdditional(File file, Source source) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.filename.additional", file, source);
        }

        public static JCDiagnostic.Note PreviewPlural(Source source) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.plural", source);
        }

        public static JCDiagnostic.Note PreviewPluralAdditional(Source source) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.plural.additional", source);
        }

        public static JCDiagnostic.Note ProcMessager(String str) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.messager", str);
        }

        public static JCDiagnostic.Note RemovalFilename(File file) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "removal.filename", file);
        }

        public static JCDiagnostic.Note RemovalFilenameAdditional(File file) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "removal.filename.additional", file);
        }

        public static JCDiagnostic.Note UncheckedFilename(File file) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.filename", file);
        }

        public static JCDiagnostic.Note UncheckedFilenameAdditional(File file) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.filename.additional", file);
        }

        public static JCDiagnostic.Note VerboseL2mDeduplicate(Symbol symbol) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.l2m.deduplicate", symbol);
        }

        public static JCDiagnostic.Note VerboseResolveMulti(Name name, Symbol symbol, int i, String str, List<? extends Type> list, List<? extends Type> list2) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi", name, symbol, Integer.valueOf(i), str, list, list2);
        }

        public static JCDiagnostic.Note VerboseResolveMulti1(Name name, Symbol symbol, Void r5, String str, List<? extends Type> list, List<? extends Type> list2) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi.1", name, symbol, r5, str, list, list2);
        }

        public static JCDiagnostic.Note DeprecatedFilename(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deprecated.filename", javaFileObject);
        }

        public static JCDiagnostic.Note DeprecatedFilenameAdditional(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deprecated.filename.additional", javaFileObject);
        }

        public static JCDiagnostic.Note PreviewFilename(JavaFileObject javaFileObject, Source source) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.filename", javaFileObject, source);
        }

        public static JCDiagnostic.Note PreviewFilenameAdditional(JavaFileObject javaFileObject, Source source) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.filename.additional", javaFileObject, source);
        }

        public static JCDiagnostic.Note DeprecatedFilename(Path path) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deprecated.filename", path);
        }

        public static JCDiagnostic.Note DeprecatedFilenameAdditional(Path path) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "deprecated.filename.additional", path);
        }

        public static JCDiagnostic.Note PreviewFilename(Path path, Source source) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.filename", path, source);
        }

        public static JCDiagnostic.Note PreviewFilenameAdditional(Path path, Source source) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "preview.filename.additional", path, source);
        }

        public static JCDiagnostic.Note RemovalFilename(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "removal.filename", javaFileObject);
        }

        public static JCDiagnostic.Note RemovalFilenameAdditional(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "removal.filename.additional", javaFileObject);
        }

        public static JCDiagnostic.Note UncheckedFilename(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.filename", javaFileObject);
        }

        public static JCDiagnostic.Note UncheckedFilenameAdditional(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.filename.additional", javaFileObject);
        }

        public static JCDiagnostic.Note VerboseResolveMulti1(Name name, Symbol symbol, Void r5, String str, List<? extends Type> list, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi.1", name, symbol, r5, str, list, jCDiagnostic);
        }

        public static JCDiagnostic.Note RemovalFilename(Path path) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "removal.filename", path);
        }

        public static JCDiagnostic.Note RemovalFilenameAdditional(Path path) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "removal.filename.additional", path);
        }

        public static JCDiagnostic.Note UncheckedFilename(Path path) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.filename", path);
        }

        public static JCDiagnostic.Note UncheckedFilenameAdditional(Path path) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unchecked.filename.additional", path);
        }

        public static JCDiagnostic.Note VerboseResolveMulti1(Name name, Symbol symbol, Void r5, String str, List<? extends Type> list, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi.1", name, symbol, r5, str, list, fragment);
        }

        public static JCDiagnostic.Note VerboseResolveMulti1(Name name, Symbol symbol, Void r5, String str, JCDiagnostic jCDiagnostic, List<? extends Type> list) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi.1", name, symbol, r5, str, jCDiagnostic, list);
        }

        public static JCDiagnostic.Note VerboseResolveMulti1(Name name, Symbol symbol, Void r5, String str, JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi.1", name, symbol, r5, str, jCDiagnostic, jCDiagnostic2);
        }

        public static JCDiagnostic.Note VerboseResolveMulti(Name name, Symbol symbol, int i, String str, List<? extends Type> list, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi", name, symbol, Integer.valueOf(i), str, list, jCDiagnostic);
        }

        public static JCDiagnostic.Note VerboseResolveMulti1(Name name, Symbol symbol, Void r5, String str, JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi.1", name, symbol, r5, str, jCDiagnostic, fragment);
        }

        public static JCDiagnostic.Note VerboseResolveMulti(Name name, Symbol symbol, int i, String str, List<? extends Type> list, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi", name, symbol, Integer.valueOf(i), str, list, fragment);
        }

        public static JCDiagnostic.Note VerboseResolveMulti1(Name name, Symbol symbol, Void r5, String str, JCDiagnostic.Fragment fragment, List<? extends Type> list) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi.1", name, symbol, r5, str, fragment, list);
        }

        public static JCDiagnostic.Note VerboseResolveMulti(Name name, Symbol symbol, int i, String str, JCDiagnostic jCDiagnostic, List<? extends Type> list) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi", name, symbol, Integer.valueOf(i), str, jCDiagnostic, list);
        }

        public static JCDiagnostic.Note VerboseResolveMulti1(Name name, Symbol symbol, Void r5, String str, JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi.1", name, symbol, r5, str, fragment, jCDiagnostic);
        }

        public static JCDiagnostic.Note VerboseResolveMulti(Name name, Symbol symbol, int i, String str, JCDiagnostic jCDiagnostic, JCDiagnostic jCDiagnostic2) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi", name, symbol, Integer.valueOf(i), str, jCDiagnostic, jCDiagnostic2);
        }

        public static JCDiagnostic.Note VerboseResolveMulti1(Name name, Symbol symbol, Void r5, String str, JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi.1", name, symbol, r5, str, fragment, fragment2);
        }

        public static JCDiagnostic.Note VerboseResolveMulti(Name name, Symbol symbol, int i, String str, JCDiagnostic jCDiagnostic, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi", name, symbol, Integer.valueOf(i), str, jCDiagnostic, fragment);
        }

        public static JCDiagnostic.Note VerboseResolveMulti(Name name, Symbol symbol, int i, String str, JCDiagnostic.Fragment fragment, List<? extends Type> list) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi", name, symbol, Integer.valueOf(i), str, fragment, list);
        }

        public static JCDiagnostic.Note VerboseResolveMulti(Name name, Symbol symbol, int i, String str, JCDiagnostic.Fragment fragment, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi", name, symbol, Integer.valueOf(i), str, fragment, jCDiagnostic);
        }

        public static JCDiagnostic.Note VerboseResolveMulti(Name name, Symbol symbol, int i, String str, JCDiagnostic.Fragment fragment, JCDiagnostic.Fragment fragment2) {
            return new JCDiagnostic.Note(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "verbose.resolve.multi", name, symbol, Integer.valueOf(i), str, fragment, fragment2);
        }
    }

    public static class Warnings {
        public static final JCDiagnostic.Warning DiamondRedundantArgs = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "diamond.redundant.args", new Object[0]);
        public static final JCDiagnostic.Warning DoclintNotAvailable = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "doclint.not.available", new Object[0]);
        public static final JCDiagnostic.Warning ExtraneousSemicolon = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "extraneous.semicolon", new Object[0]);
        public static final JCDiagnostic.Warning IllegalCharForEncoding = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.char.for.encoding", new Object[0]);
        public static final JCDiagnostic.Warning InvalidYield = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.yield", new Object[0]);
        public static final JCDiagnostic.Warning LocalRedundantType = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "local.redundant.type", new Object[0]);
        public static final JCDiagnostic.Warning MethodRedundantTypeargs = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "method.redundant.typeargs", new Object[0]);
        public static final JCDiagnostic.Warning PotentialLambdaFound = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "potential.lambda.found", new Object[0]);
        public static final JCDiagnostic.Warning ProcProcOnlyRequestedNoProcs = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.proc-only.requested.no.procs", new Object[0]);
        public static final JCDiagnostic.Warning ProcUseImplicit = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.use.implicit", new Object[0]);
        public static final JCDiagnostic.Warning ProcUseProcOrImplicit = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.use.proc.or.implicit", new Object[0]);
        public static final JCDiagnostic.Warning UnderscoreAsIdentifier = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "underscore.as.identifier", new Object[0]);
        public static final JCDiagnostic.Warning Warning = new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "warning", new Object[0]);

        public static JCDiagnostic.Warning BadNameForOption(Option option, String str) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "bad.name.for.option", option, str);
        }

        public static JCDiagnostic.Warning BigMajorVersion(File file, int i, int i2) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "big.major.version", file, Integer.valueOf(i), Integer.valueOf(i2));
        }

        public static JCDiagnostic.Warning FileFromFuture(File file) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.from.future", file);
        }

        public static JCDiagnostic.Warning ForwardRef(Symbol symbol) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "forward.ref", symbol);
        }

        public static JCDiagnostic.Warning IllegalRefToRestrictedType(Name name) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "illegal.ref.to.restricted.type", name);
        }

        public static JCDiagnostic.Warning InexactNonVarargsCall(Type type, Type type2) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "inexact.non-varargs.call", type, type2);
        }

        public static JCDiagnostic.Warning InvalidUtf8InClassfile(File file, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.utf8.in.classfile", file, fragment);
        }

        public static JCDiagnostic.Warning LintOption(Option option) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "lintOption", option);
        }

        public static JCDiagnostic.Warning OverrideBridge(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.bridge", jCDiagnostic);
        }

        public static JCDiagnostic.Warning PkgInfoAlreadySeen(Symbol symbol) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "pkg-info.already.seen", symbol);
        }

        public static JCDiagnostic.Warning PositionOverflow(int i) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "position.overflow", Integer.valueOf(i));
        }

        public static JCDiagnostic.Warning ProcFileCreateLastRound(String str) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.file.create.last.round", str);
        }

        public static JCDiagnostic.Warning ProcMessager(String str) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.messager", str);
        }

        public static JCDiagnostic.Warning ProcPackageDoesNotExist(String str) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.package.does.not.exist", str);
        }

        public static JCDiagnostic.Warning ProcProcessorIncompatibleSourceVersion(SourceVersion sourceVersion, String str, String str2) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.processor.incompatible.source.version", sourceVersion, str, str2);
        }

        public static JCDiagnostic.Warning ProcUnclosedTypeFiles(Set<? extends String> set) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.unclosed.type.files", set);
        }

        public static JCDiagnostic.Warning ProcUnmatchedProcessorOptions(String str) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "proc.unmatched.processor.options", str);
        }

        public static JCDiagnostic.Warning ProfileTargetConflict(Profile profile, Target target) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "profile.target.conflict", profile, target);
        }

        public static JCDiagnostic.Warning RestrictedTypeNotAllowed(Name name, Source source) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "restricted.type.not.allowed", name, source);
        }

        public static JCDiagnostic.Warning RestrictedTypeNotAllowedPreview(Name name, Source source) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "restricted.type.not.allowed.preview", name, source);
        }

        public static JCDiagnostic.Warning SelfRef(Symbol symbol) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "self.ref", symbol);
        }

        public static JCDiagnostic.Warning ServiceProvidedButNotExportedOrUsed(Symbol symbol) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "service.provided.but.not.exported.or.used", symbol);
        }

        public static JCDiagnostic.Warning SunProprietary(Symbol symbol) {
            return new JCDiagnostic.Warning(EnumSet.of(JCDiagnostic.DiagnosticFlag.STRICT), "compiler", "sun.proprietary", symbol);
        }

        public static JCDiagnostic.Warning UnknownEnumConstant(JavaFileObject javaFileObject, Symbol symbol, Name name) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unknown.enum.constant", javaFileObject, symbol, name);
        }

        public static JCDiagnostic.Warning UnknownEnumConstantReason(JavaFileObject javaFileObject, Symbol symbol, Name name, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unknown.enum.constant.reason", javaFileObject, symbol, name, jCDiagnostic);
        }

        public static JCDiagnostic.Warning UnreachableCatch(List<? extends Type> list) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unreachable.catch", list);
        }

        public static JCDiagnostic.Warning UnreachableCatch1(List<? extends Type> list) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unreachable.catch.1", list);
        }

        public static JCDiagnostic.Warning FileFromFuture(JavaFileObject javaFileObject) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.from.future", javaFileObject);
        }

        public static JCDiagnostic.Warning InvalidUtf8InClassfile(JavaFileObject javaFileObject, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.utf8.in.classfile", javaFileObject, fragment);
        }

        public static JCDiagnostic.Warning OverrideBridge(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "override.bridge", fragment);
        }

        public static JCDiagnostic.Warning FileFromFuture(Path path) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "file.from.future", path);
        }

        public static JCDiagnostic.Warning InvalidUtf8InClassfile(Path path, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "invalid.utf8.in.classfile", path, fragment);
        }

        public static JCDiagnostic.Warning UnknownEnumConstantReason(JavaFileObject javaFileObject, Symbol symbol, Name name, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "unknown.enum.constant.reason", javaFileObject, symbol, name, fragment);
        }

        public static JCDiagnostic.Warning BigMajorVersion(JavaFileObject javaFileObject, int i, int i2) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "big.major.version", javaFileObject, Integer.valueOf(i), Integer.valueOf(i2));
        }

        public static JCDiagnostic.Warning BigMajorVersion(Path path, int i, int i2) {
            return new JCDiagnostic.Warning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), "compiler", "big.major.version", path, Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    public static class LintWarnings {
        public static final JCDiagnostic.LintWarning AddopensIgnored;
        public static final JCDiagnostic.LintWarning AttemptToSynchronizeOnInstanceOfValueBasedClass;
        public static final JCDiagnostic.LintWarning AttemptToUseValueBasedWhereIdentityExpected;
        public static final JCDiagnostic.LintWarning DanglingDocComment;
        public static final JCDiagnostic.LintWarning DefaultIneffective;
        public static final JCDiagnostic.LintWarning DivZero;
        public static final JCDiagnostic.LintWarning EmptyIf;
        public static final JCDiagnostic.LintWarning ExternalizableMissingPublicNoArgCtor;
        public static final JCDiagnostic.LintWarning FinallyCannotComplete;
        public static final JCDiagnostic.LintWarning ImproperSPF;
        public static final JCDiagnostic.LintWarning InconsistentWhiteSpaceIndentation;
        public static final JCDiagnostic.LintWarning IneffectualSerialFieldExternalizable;
        public static final JCDiagnostic.LintWarning IneffectualSerialFieldInterface;
        public static final JCDiagnostic.LintWarning IneffectualSerialFieldRecord;
        public static final JCDiagnostic.LintWarning MissingDeprecatedAnnotation;
        public static final JCDiagnostic.LintWarning NonPrivateMethodWeakerAccess;
        public static final JCDiagnostic.LintWarning NonSerializableInstanceField;
        public static final JCDiagnostic.LintWarning OptionObsoleteSuppression;
        public static final JCDiagnostic.LintWarning PossibleFallThroughIntoCase;
        public static final JCDiagnostic.LintWarning PossibleThisEscape;
        public static final JCDiagnostic.LintWarning PossibleThisEscapeLocation;
        public static final JCDiagnostic.LintWarning RequiresAutomatic;
        public static final JCDiagnostic.LintWarning RequiresTransitiveAutomatic;
        public static final JCDiagnostic.LintWarning Strictfp;
        public static final JCDiagnostic.LintWarning TrailingWhiteSpaceWillBeRemoved;
        public static final JCDiagnostic.LintWarning TryExplicitCloseCall;
        public static final JCDiagnostic.LintWarning UncheckedAssign;
        public static final JCDiagnostic.LintWarning UncheckedCastToType;
        public static final JCDiagnostic.LintWarning OSFArraySPF = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "OSF.array.SPF", new Object[0]);
        public static final JCDiagnostic.LintWarning SPFNullInit = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "SPF.null.init", new Object[0]);

        static {
            JCDiagnostic.DiagnosticFlag diagnosticFlag = JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED;
            AddopensIgnored = new JCDiagnostic.LintWarning(EnumSet.of(diagnosticFlag), Lint.LintCategory.get("options").get(), "compiler", "addopens.ignored", new Object[0]);
            AttemptToSynchronizeOnInstanceOfValueBasedClass = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("identity").get(), "compiler", "attempt.to.synchronize.on.instance.of.value.based.class", new Object[0]);
            AttemptToUseValueBasedWhereIdentityExpected = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("identity").get(), "compiler", "attempt.to.use.value.based.where.identity.expected", new Object[0]);
            DanglingDocComment = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("dangling-doc-comments").get(), "compiler", "dangling.doc.comment", new Object[0]);
            DefaultIneffective = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "default.ineffective", new Object[0]);
            DivZero = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("divzero").get(), "compiler", "div.zero", new Object[0]);
            EmptyIf = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(Constants.ELEMNAME_EMPTY_STRING).get(), "compiler", "empty.if", new Object[0]);
            ExternalizableMissingPublicNoArgCtor = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "externalizable.missing.public.no.arg.ctor", new Object[0]);
            FinallyCannotComplete = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.FINALLY).get(), "compiler", "finally.cannot.complete", new Object[0]);
            ImproperSPF = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "improper.SPF", new Object[0]);
            InconsistentWhiteSpaceIndentation = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("text-blocks").get(), "compiler", "inconsistent.white.space.indentation", new Object[0]);
            IneffectualSerialFieldExternalizable = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "ineffectual.serial.field.externalizable", new Object[0]);
            IneffectualSerialFieldInterface = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "ineffectual.serial.field.interface", new Object[0]);
            IneffectualSerialFieldRecord = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "ineffectual.serial.field.record", new Object[0]);
            MissingDeprecatedAnnotation = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("dep-ann").get(), "compiler", "missing.deprecated.annotation", new Object[0]);
            NonPrivateMethodWeakerAccess = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "non.private.method.weaker.access", new Object[0]);
            NonSerializableInstanceField = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "non.serializable.instance.field", new Object[0]);
            OptionObsoleteSuppression = new JCDiagnostic.LintWarning(EnumSet.of(diagnosticFlag), Lint.LintCategory.get("options").get(), "compiler", "option.obsolete.suppression", new Object[0]);
            PossibleFallThroughIntoCase = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("fallthrough").get(), "compiler", "possible.fall-through.into.case", new Object[0]);
            PossibleThisEscape = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("this-escape").get(), "compiler", "possible.this.escape", new Object[0]);
            PossibleThisEscapeLocation = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("this-escape").get(), "compiler", "possible.this.escape.location", new Object[0]);
            RequiresAutomatic = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("requires-automatic").get(), "compiler", "requires.automatic", new Object[0]);
            RequiresTransitiveAutomatic = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("requires-transitive-automatic").get(), "compiler", "requires.transitive.automatic", new Object[0]);
            Strictfp = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.STRICTFP).get(), "compiler", PsiKeyword.STRICTFP, new Object[0]);
            TrailingWhiteSpaceWillBeRemoved = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("text-blocks").get(), "compiler", "trailing.white.space.will.be.removed", new Object[0]);
            TryExplicitCloseCall = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.TRY).get(), "compiler", "try.explicit.close.call", new Object[0]);
            UncheckedAssign = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("unchecked").get(), "compiler", "unchecked.assign", new Object[0]);
            UncheckedCastToType = new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("unchecked").get(), "compiler", "unchecked.cast.to.type", new Object[0]);
        }

        public static JCDiagnostic.LintWarning AccessToMemberFromSerializableElement(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "access.to.member.from.serializable.element", symbol);
        }

        public static JCDiagnostic.LintWarning AccessToMemberFromSerializableLambda(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "access.to.member.from.serializable.lambda", symbol);
        }

        public static JCDiagnostic.LintWarning AnnotationMethodNotFound(Type type, Name name) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "annotation.method.not.found", type, name);
        }

        public static JCDiagnostic.LintWarning AnnotationMethodNotFoundReason(Type type, Name name, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "annotation.method.not.found.reason", type, name, jCDiagnostic);
        }

        public static JCDiagnostic.LintWarning AuxiliaryClassAccessedFromOutsideOfItsSourceFile(Symbol symbol, File file) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("auxiliaryclass").get(), "compiler", "auxiliary.class.accessed.from.outside.of.its.source.file", symbol, file);
        }

        public static JCDiagnostic.LintWarning BitShiftOutOfRange(Type type, long j, int i) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("lossy-conversions").get(), "compiler", "bit.shift.out.of.range", type, Long.valueOf(j), Integer.valueOf(i));
        }

        public static JCDiagnostic.LintWarning ConstantSVUID(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "constant.SVUID", symbol);
        }

        public static JCDiagnostic.LintWarning DeclaredUsingPreview(Kinds.KindName kindName, Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("preview").get(), "compiler", "declared.using.preview", kindName, symbol);
        }

        public static JCDiagnostic.LintWarning DeprecatedAnnotationHasNoEffect(Kinds.KindName kindName) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("deprecation").get(), "compiler", "deprecated.annotation.has.no.effect", kindName);
        }

        public static JCDiagnostic.LintWarning DirPathElementNotDirectory(File file) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("path").get(), "compiler", "dir.path.element.not.directory", file);
        }

        public static JCDiagnostic.LintWarning DirPathElementNotFound(Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("path").get(), "compiler", "dir.path.element.not.found", path);
        }

        public static JCDiagnostic.LintWarning FutureAttr(Name name, int i, int i2, int i3, int i4) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "future.attr", name, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        }

        public static JCDiagnostic.LintWarning HasBeenDeprecated(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("deprecation").get(), "compiler", "has.been.deprecated", symbol, symbol2);
        }

        public static JCDiagnostic.LintWarning HasBeenDeprecatedForRemoval(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("removal").get(), "compiler", "has.been.deprecated.for.removal", symbol, symbol2);
        }

        public static JCDiagnostic.LintWarning HasBeenDeprecatedForRemovalModule(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("removal").get(), "compiler", "has.been.deprecated.for.removal.module", symbol);
        }

        public static JCDiagnostic.LintWarning HasBeenDeprecatedModule(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("deprecation").get(), "compiler", "has.been.deprecated.module", symbol);
        }

        public static JCDiagnostic.LintWarning ImproperSVUID(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "improper.SVUID", symbol);
        }

        public static JCDiagnostic.LintWarning InconsistentInnerClasses(Symbol symbol, JavaFileObject javaFileObject) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "inconsistent.inner.classes", symbol, javaFileObject);
        }

        public static JCDiagnostic.LintWarning IncubatingModules(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("incubating").get(), "compiler", "incubating.modules", str);
        }

        public static JCDiagnostic.LintWarning IneffectualExternMethodEnum(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "ineffectual.extern.method.enum", str);
        }

        public static JCDiagnostic.LintWarning IneffectualExternalizableMethodRecord(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "ineffectual.externalizable.method.record", str);
        }

        public static JCDiagnostic.LintWarning IneffectualSerialFieldEnum(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "ineffectual.serial.field.enum", str);
        }

        public static JCDiagnostic.LintWarning IneffectualSerialMethodEnum(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "ineffectual.serial.method.enum", str);
        }

        public static JCDiagnostic.LintWarning IneffectualSerialMethodExternalizable(Name name) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "ineffectual.serial.method.externalizable", name);
        }

        public static JCDiagnostic.LintWarning IneffectualSerialMethodRecord(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "ineffectual.serial.method.record", str);
        }

        public static JCDiagnostic.LintWarning InvalidArchiveFile(Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("path").get(), "compiler", "invalid.archive.file", path);
        }

        public static JCDiagnostic.LintWarning InvalidPath(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("path").get(), "compiler", "invalid.path", str);
        }

        public static JCDiagnostic.LintWarning IsPreview(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("preview").get(), "compiler", "is.preview", symbol);
        }

        public static JCDiagnostic.LintWarning IsPreviewReflective(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("preview").get(), "compiler", "is.preview.reflective", symbol);
        }

        public static JCDiagnostic.LintWarning LeaksNotAccessible(Kinds.KindName kindName, Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.EXPORTS).get(), "compiler", "leaks.not.accessible", kindName, symbol, symbol2);
        }

        public static JCDiagnostic.LintWarning LeaksNotAccessibleNotRequiredTransitive(Kinds.KindName kindName, Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.EXPORTS).get(), "compiler", "leaks.not.accessible.not.required.transitive", kindName, symbol, symbol2);
        }

        public static JCDiagnostic.LintWarning LeaksNotAccessibleUnexported(Kinds.KindName kindName, Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.EXPORTS).get(), "compiler", "leaks.not.accessible.unexported", kindName, symbol, symbol2);
        }

        public static JCDiagnostic.LintWarning LeaksNotAccessibleUnexportedQualified(Kinds.KindName kindName, Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.EXPORTS).get(), "compiler", "leaks.not.accessible.unexported.qualified", kindName, symbol, symbol2);
        }

        public static JCDiagnostic.LintWarning LocnUnknownFileOnModulePath(Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("path").get(), "compiler", "locn.unknown.file.on.module.path", path);
        }

        public static JCDiagnostic.LintWarning LongSVUID(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "long.SVUID", symbol);
        }

        public static JCDiagnostic.LintWarning MissingExplicitCtor(Symbol symbol, Symbol symbol2, Symbol symbol3) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("missing-explicit-ctor").get(), "compiler", "missing-explicit-ctor", symbol, symbol2, symbol3);
        }

        public static JCDiagnostic.LintWarning MissingSVUID(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "missing.SVUID", symbol);
        }

        public static JCDiagnostic.LintWarning ModuleForOptionNotFound(Option option, Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("options").get(), "compiler", "module.for.option.not.found", option, symbol);
        }

        public static JCDiagnostic.LintWarning ModuleNotFound(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.MODULE).get(), "compiler", "module.not.found", symbol);
        }

        public static JCDiagnostic.LintWarning NonSerializableInstanceFieldArray(Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "non.serializable.instance.field.array", type);
        }

        public static JCDiagnostic.LintWarning OptionObsoleteSource(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("options").get(), "compiler", "option.obsolete.source", str);
        }

        public static JCDiagnostic.LintWarning OptionObsoleteTarget(Target target) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("options").get(), "compiler", "option.obsolete.target", target);
        }

        public static JCDiagnostic.LintWarning OutdirIsInExplodedModule(Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("path").get(), "compiler", "outdir.is.in.exploded.module", path);
        }

        public static JCDiagnostic.LintWarning OutputFileClash(Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("output-file-clash").get(), "compiler", "output.file.clash", path);
        }

        public static JCDiagnostic.LintWarning OverrideEqualsButNotHashcode(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("overrides").get(), "compiler", "override.equals.but.not.hashcode", symbol);
        }

        public static JCDiagnostic.LintWarning OverrideUncheckedRet(JCDiagnostic jCDiagnostic, Type type, Type type2) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "override.unchecked.ret", jCDiagnostic, type, type2);
        }

        public static JCDiagnostic.LintWarning OverrideUncheckedThrown(JCDiagnostic jCDiagnostic, Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "override.unchecked.thrown", jCDiagnostic, type);
        }

        public static JCDiagnostic.LintWarning OverrideVarargsExtra(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("overrides").get(), "compiler", "override.varargs.extra", jCDiagnostic);
        }

        public static JCDiagnostic.LintWarning OverrideVarargsMissing(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("overrides").get(), "compiler", "override.varargs.missing", jCDiagnostic);
        }

        public static JCDiagnostic.LintWarning PackageEmptyOrNotFound(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.OPENS).get(), "compiler", "package.empty.or.not.found", symbol);
        }

        public static JCDiagnostic.LintWarning PathElementNotFound(Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("path").get(), "compiler", "path.element.not.found", path);
        }

        public static JCDiagnostic.LintWarning PoorChoiceForModuleName(Name name) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.MODULE).get(), "compiler", "poor.choice.for.module.name", name);
        }

        public static JCDiagnostic.LintWarning PossibleLossOfPrecision(Type type, Type type2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("lossy-conversions").get(), "compiler", "possible.loss.of.precision", type, type2);
        }

        public static JCDiagnostic.LintWarning PotentiallyAmbiguousOverload(Symbol symbol, Symbol symbol2, Symbol symbol3, Symbol symbol4) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("overloads").get(), "compiler", "potentially.ambiguous.overload", symbol, symbol2, symbol3, symbol4);
        }

        public static JCDiagnostic.LintWarning PreviewFeatureUse(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("preview").get(), "compiler", "preview.feature.use", jCDiagnostic);
        }

        public static JCDiagnostic.LintWarning PreviewFeatureUseClassfile(JavaFileObject javaFileObject, String str) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.MANDATORY), Lint.LintCategory.get("preview").get(), "compiler", "preview.feature.use.classfile", javaFileObject, str);
        }

        public static JCDiagnostic.LintWarning PreviewFeatureUsePlural(JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("preview").get(), "compiler", "preview.feature.use.plural", jCDiagnostic);
        }

        public static JCDiagnostic.LintWarning ProbFoundReq(JCDiagnostic jCDiagnostic, Type type, Type type2) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "prob.found.req", jCDiagnostic, type, type2);
        }

        public static JCDiagnostic.LintWarning ProcAnnotationsWithoutProcessors(Set<? extends String> set) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("processing").get(), "compiler", "proc.annotations.without.processors", set);
        }

        public static JCDiagnostic.LintWarning ProcDuplicateOptionName(String str, String str2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("processing").get(), "compiler", "proc.duplicate.option.name", str, str2);
        }

        public static JCDiagnostic.LintWarning ProcDuplicateSupportedAnnotation(String str, String str2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("processing").get(), "compiler", "proc.duplicate.supported.annotation", str, str2);
        }

        public static JCDiagnostic.LintWarning ProcFileReopening(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("processing").get(), "compiler", "proc.file.reopening", str);
        }

        public static JCDiagnostic.LintWarning ProcIllegalFileName(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("processing").get(), "compiler", "proc.illegal.file.name", str);
        }

        public static JCDiagnostic.LintWarning ProcMalformedSupportedString(String str, String str2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("processing").get(), "compiler", "proc.malformed.supported.string", str, str2);
        }

        public static JCDiagnostic.LintWarning ProcRedundantTypesWithWildcard(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("processing").get(), "compiler", "proc.redundant.types.with.wildcard", str);
        }

        public static JCDiagnostic.LintWarning ProcSuspiciousClassName(String str, String str2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("processing").get(), "compiler", "proc.suspicious.class.name", str, str2);
        }

        public static JCDiagnostic.LintWarning ProcTypeAlreadyExists(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("processing").get(), "compiler", "proc.type.already.exists", str);
        }

        public static JCDiagnostic.LintWarning ProcTypeRecreate(String str) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("processing").get(), "compiler", "proc.type.recreate", str);
        }

        public static JCDiagnostic.LintWarning RawClassUse(Type type, Type type2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("rawtypes").get(), "compiler", "raw.class.use", type, type2);
        }

        public static JCDiagnostic.LintWarning RedundantCast(Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("cast").get(), "compiler", "redundant.cast", type);
        }

        public static JCDiagnostic.LintWarning RestrictedMethod(Symbol symbol, Symbol symbol2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("restricted").get(), "compiler", "restricted.method", symbol, symbol2);
        }

        public static JCDiagnostic.LintWarning RuntimeInvisibleParameterAnnotations(File file) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "runtime.invisible.parameter.annotations", file);
        }

        public static JCDiagnostic.LintWarning RuntimeVisibleInvisibleParamAnnotationsMismatch(File file) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "runtime.visible.invisible.param.annotations.mismatch", file);
        }

        public static JCDiagnostic.LintWarning SerialConcreteInstanceMethod(Name name) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "serial.concrete.instance.method", name);
        }

        public static JCDiagnostic.LintWarning SerialMethodNoArgs(Name name) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "serial.method.no.args", name);
        }

        public static JCDiagnostic.LintWarning SerialMethodNotPrivate(Name name) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "serial.method.not.private", name);
        }

        public static JCDiagnostic.LintWarning SerialMethodOneArg(Name name, int i) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "serial.method.one.arg", name, Integer.valueOf(i));
        }

        public static JCDiagnostic.LintWarning SerialMethodParameterType(Name name, Type type, Type type2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "serial.method.parameter.type", name, type, type2);
        }

        public static JCDiagnostic.LintWarning SerialMethodStatic(Name name) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "serial.method.static", name);
        }

        public static JCDiagnostic.LintWarning SerialMethodUnexpectedException(Name name, Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "serial.method.unexpected.exception", name, type);
        }

        public static JCDiagnostic.LintWarning SerialMethodUnexpectedReturnType(Name name, Type type, Type type2) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "serial.method.unexpected.return.type", name, type, type2);
        }

        public static JCDiagnostic.LintWarning SerializableMissingAccessNoArgCtor(Name name) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("serial").get(), "compiler", "serializable.missing.access.no.arg.ctor", name);
        }

        public static JCDiagnostic.LintWarning SourceNoBootclasspath(String str, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("options").get(), "compiler", "source.no.bootclasspath", str, fragment);
        }

        public static JCDiagnostic.LintWarning SourceNoSystemModulesPath(String str, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("options").get(), "compiler", "source.no.system.modules.path", str, fragment);
        }

        public static JCDiagnostic.LintWarning StaticNotQualifiedByType(Kinds.KindName kindName, Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.STATIC).get(), "compiler", "static.not.qualified.by.type", kindName, symbol);
        }

        public static JCDiagnostic.LintWarning StaticNotQualifiedByType2(Kinds.KindName kindName) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.STATIC).get(), "compiler", "static.not.qualified.by.type2", kindName);
        }

        public static JCDiagnostic.LintWarning TryResourceCanThrowInterruptedExc(Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.TRY).get(), "compiler", "try.resource.can.throw.interrupted.exc", type);
        }

        public static JCDiagnostic.LintWarning TryResourceNotReferenced(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.TRY).get(), "compiler", "try.resource.not.referenced", symbol);
        }

        public static JCDiagnostic.LintWarning TryResourceThrowsInterruptedExc(Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get(PsiKeyword.TRY).get(), "compiler", "try.resource.throws.interrupted.exc", type);
        }

        public static JCDiagnostic.LintWarning UncheckedAssignToVar(Symbol symbol, Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "unchecked.assign.to.var", symbol, type);
        }

        public static JCDiagnostic.LintWarning UncheckedCallMbrOfRawType(Symbol symbol, Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "unchecked.call.mbr.of.raw.type", symbol, type);
        }

        public static JCDiagnostic.LintWarning UncheckedGenericArrayCreation(Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "unchecked.generic.array.creation", type);
        }

        public static JCDiagnostic.LintWarning UncheckedMethInvocationApplied(Kinds.KindName kindName, Name name, Object obj, Object obj2, Kinds.KindName kindName2, Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "unchecked.meth.invocation.applied", kindName, name, obj, obj2, kindName2, symbol);
        }

        public static JCDiagnostic.LintWarning UncheckedVarargsNonReifiableType(Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "unchecked.varargs.non.reifiable.type", type);
        }

        public static JCDiagnostic.LintWarning UnexpectedArchiveFile(Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("path").get(), "compiler", "unexpected.archive.file", path);
        }

        public static JCDiagnostic.LintWarning VarargsRedundantTrustmeAnno(Symbol symbol, JCDiagnostic jCDiagnostic) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("varargs").get(), "compiler", "varargs.redundant.trustme.anno", symbol, jCDiagnostic);
        }

        public static JCDiagnostic.LintWarning VarargsUnsafeUseVarargsParam(Symbol symbol) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("varargs").get(), "compiler", "varargs.unsafe.use.varargs.param", symbol);
        }

        public static JCDiagnostic.LintWarning AnnotationMethodNotFoundReason(Type type, Name name, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "annotation.method.not.found.reason", type, name, fragment);
        }

        public static JCDiagnostic.LintWarning AuxiliaryClassAccessedFromOutsideOfItsSourceFile(Symbol symbol, JavaFileObject javaFileObject) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("auxiliaryclass").get(), "compiler", "auxiliary.class.accessed.from.outside.of.its.source.file", symbol, javaFileObject);
        }

        public static JCDiagnostic.LintWarning DirPathElementNotDirectory(JavaFileObject javaFileObject) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("path").get(), "compiler", "dir.path.element.not.directory", javaFileObject);
        }

        public static JCDiagnostic.LintWarning OverrideVarargsExtra(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("overrides").get(), "compiler", "override.varargs.extra", fragment);
        }

        public static JCDiagnostic.LintWarning OverrideVarargsMissing(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("overrides").get(), "compiler", "override.varargs.missing", fragment);
        }

        public static JCDiagnostic.LintWarning AuxiliaryClassAccessedFromOutsideOfItsSourceFile(Symbol symbol, Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("auxiliaryclass").get(), "compiler", "auxiliary.class.accessed.from.outside.of.its.source.file", symbol, path);
        }

        public static JCDiagnostic.LintWarning DirPathElementNotDirectory(Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("path").get(), "compiler", "dir.path.element.not.directory", path);
        }

        public static JCDiagnostic.LintWarning RuntimeInvisibleParameterAnnotations(JavaFileObject javaFileObject) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "runtime.invisible.parameter.annotations", javaFileObject);
        }

        public static JCDiagnostic.LintWarning RuntimeVisibleInvisibleParamAnnotationsMismatch(JavaFileObject javaFileObject) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "runtime.visible.invisible.param.annotations.mismatch", javaFileObject);
        }

        public static JCDiagnostic.LintWarning AuxiliaryClassAccessedFromOutsideOfItsSourceFile(Type type, File file) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("auxiliaryclass").get(), "compiler", "auxiliary.class.accessed.from.outside.of.its.source.file", type, file);
        }

        public static JCDiagnostic.LintWarning RuntimeInvisibleParameterAnnotations(Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "runtime.invisible.parameter.annotations", path);
        }

        public static JCDiagnostic.LintWarning RuntimeVisibleInvisibleParamAnnotationsMismatch(Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("classfile").get(), "compiler", "runtime.visible.invisible.param.annotations.mismatch", path);
        }

        public static JCDiagnostic.LintWarning VarargsRedundantTrustmeAnno(Symbol symbol, JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("varargs").get(), "compiler", "varargs.redundant.trustme.anno", symbol, fragment);
        }

        public static JCDiagnostic.LintWarning AuxiliaryClassAccessedFromOutsideOfItsSourceFile(Type type, JavaFileObject javaFileObject) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("auxiliaryclass").get(), "compiler", "auxiliary.class.accessed.from.outside.of.its.source.file", type, javaFileObject);
        }

        public static JCDiagnostic.LintWarning AuxiliaryClassAccessedFromOutsideOfItsSourceFile(Type type, Path path) {
            return new JCDiagnostic.LintWarning(EnumSet.noneOf(JCDiagnostic.DiagnosticFlag.class), Lint.LintCategory.get("auxiliaryclass").get(), "compiler", "auxiliary.class.accessed.from.outside.of.its.source.file", type, path);
        }

        public static JCDiagnostic.LintWarning PreviewFeatureUse(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("preview").get(), "compiler", "preview.feature.use", fragment);
        }

        public static JCDiagnostic.LintWarning PreviewFeatureUsePlural(JCDiagnostic.Fragment fragment) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("preview").get(), "compiler", "preview.feature.use.plural", fragment);
        }

        public static JCDiagnostic.LintWarning OverrideUncheckedRet(JCDiagnostic.Fragment fragment, Type type, Type type2) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "override.unchecked.ret", fragment, type, type2);
        }

        public static JCDiagnostic.LintWarning OverrideUncheckedThrown(JCDiagnostic.Fragment fragment, Type type) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "override.unchecked.thrown", fragment, type);
        }

        public static JCDiagnostic.LintWarning ProbFoundReq(JCDiagnostic.Fragment fragment, Type type, Type type2) {
            return new JCDiagnostic.LintWarning(EnumSet.of(JCDiagnostic.DiagnosticFlag.AGGREGATE, JCDiagnostic.DiagnosticFlag.MANDATORY, JCDiagnostic.DiagnosticFlag.DEFAULT_ENABLED), Lint.LintCategory.get("unchecked").get(), "compiler", "prob.found.req", fragment, type, type2);
        }
    }
}

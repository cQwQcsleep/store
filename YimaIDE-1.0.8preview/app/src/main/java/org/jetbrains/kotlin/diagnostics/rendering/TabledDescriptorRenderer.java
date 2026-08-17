package org.jetbrains.kotlin.diagnostics.rendering;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.CallableDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.diagnostics.rendering.TabledDescriptorRenderer;
import org.jetbrains.kotlin.resolve.calls.inference.constraintPosition.ConstraintPosition;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class TabledDescriptorRenderer {
    protected final List<TableOrTextRenderer> renderers = Lists.newArrayList();

    public interface TableOrTextRenderer {
    }

    public enum TextElementType {
        STRONG,
        ERROR,
        DEFAULT
    }

    public static class TextRenderer implements TableOrTextRenderer, TableRenderer.TableRow {
        public final List<TextElement> elements = Lists.newArrayList();

        public static class TextElement {
            public String text;
            public TextElementType type;

            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i != 1) {
                    objArr[0] = ModuleXmlParser.TYPE;
                } else {
                    objArr[0] = "text";
                }
                objArr[1] = "org/jetbrains/kotlin/diagnostics/rendering/TabledDescriptorRenderer$TextRenderer$TextElement";
                objArr[2] = "<init>";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            public TextElement(TextElementType textElementType, String str) {
                if (textElementType == null) {
                    $$$reportNull$$$0(0);
                }
                if (str == null) {
                    $$$reportNull$$$0(1);
                }
                this.type = textElementType;
                this.text = str;
            }
        }

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            objArr[0] = "text";
            objArr[1] = "org/jetbrains/kotlin/diagnostics/rendering/TabledDescriptorRenderer$TextRenderer";
            if (i == 1) {
                objArr[2] = "error";
            } else if (i != 2) {
                objArr[2] = "normal";
            } else {
                objArr[2] = "strong";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public TextRenderer error(Object obj) {
            if (obj == null) {
                $$$reportNull$$$0(1);
            }
            this.elements.add(new TextElement(TextElementType.ERROR, obj.toString()));
            return this;
        }

        public TextRenderer normal(Object obj) {
            if (obj == null) {
                $$$reportNull$$$0(0);
            }
            this.elements.add(new TextElement(TextElementType.DEFAULT, obj.toString()));
            return this;
        }

        public TextRenderer strong(Object obj) {
            if (obj == null) {
                $$$reportNull$$$0(2);
            }
            this.elements.add(new TextElement(TextElementType.STRONG, obj.toString()));
            return this;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "tableRenderer";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
                objArr[0] = "org/jetbrains/kotlin/diagnostics/rendering/TabledDescriptorRenderer";
                break;
            case 4:
                objArr[0] = "argumentTypes";
                break;
            case 5:
                objArr[0] = "context";
                break;
            case 6:
                objArr[0] = "table";
                break;
            default:
                objArr[0] = "textRenderer";
                break;
        }
        if (i == 2) {
            objArr[1] = "getTypeRenderer";
        } else if (i != 3) {
            objArr[1] = "org/jetbrains/kotlin/diagnostics/rendering/TabledDescriptorRenderer";
        } else {
            objArr[1] = "getTypeProjectionRenderer";
        }
        switch (i) {
            case 1:
                objArr[2] = "table";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
                break;
            case 4:
            case 5:
                objArr[2] = "renderFunctionArguments";
                break;
            case 6:
                objArr[2] = "computeRenderingContext";
                break;
            default:
                objArr[2] = "text";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static RenderingContext computeRenderingContext(TableRenderer tableRenderer) {
        if (tableRenderer == null) {
            $$$reportNull$$$0(6);
        }
        ArrayList arrayList = new ArrayList();
        for (TableRenderer.TableRow tableRow : tableRenderer.rows) {
            if (tableRow instanceof TableRenderer.DescriptorRow) {
                arrayList.add(((TableRenderer.DescriptorRow) tableRow).descriptor);
            } else if (tableRow instanceof TableRenderer.FunctionArgumentsRow) {
                TableRenderer.FunctionArgumentsRow functionArgumentsRow = (TableRenderer.FunctionArgumentsRow) tableRow;
                arrayList.add(functionArgumentsRow.receiverType);
                arrayList.addAll(functionArgumentsRow.argumentTypes);
            } else if (!(tableRow instanceof TextRenderer)) {
                pe1.a("Unknown row of type ", tableRow.getClass());
                return null;
            }
        }
        return new RenderingContext.Impl(arrayList);
    }

    public static TabledDescriptorRenderer create() {
        return new TabledDescriptorRenderer();
    }

    public static TableRenderer newTable() {
        return new TableRenderer();
    }

    public static TextRenderer newText() {
        return new TextRenderer();
    }

    private void renderFunctionArguments(KotlinType kotlinType, List<KotlinType> list, StringBuilder sb, RenderingContext renderingContext) {
        if (list == null) {
            $$$reportNull$$$0(4);
        }
        if (renderingContext == null) {
            $$$reportNull$$$0(5);
        }
        if (kotlinType != null) {
            sb.append("receiver: ");
            sb.append(getTypeRenderer().render(kotlinType, renderingContext));
            sb.append("  arguments: ");
        }
        if (list.isEmpty()) {
            sb.append("()");
            return;
        }
        sb.append("(");
        Iterator<KotlinType> it = list.iterator();
        while (it.hasNext()) {
            KotlinType next = it.next();
            if (next == null) {
                sb.append("<unknown>");
            } else {
                sb.append(getTypeRenderer().render(next, renderingContext));
            }
            if (it.hasNext()) {
                sb.append(Argument.Delimiters.default);
            }
        }
        sb.append(")");
    }

    public DiagnosticParameterRenderer<TypeProjection> getTypeProjectionRenderer() {
        ContextIndependentParameterRenderer<TypeProjection> contextIndependentParameterRenderer = Renderers.TYPE_PROJECTION;
        if (contextIndependentParameterRenderer == null) {
            $$$reportNull$$$0(3);
        }
        return contextIndependentParameterRenderer;
    }

    public DiagnosticParameterRenderer<KotlinType> getTypeRenderer() {
        SmartTypeRenderer smartTypeRenderer = Renderers.RENDER_TYPE;
        if (smartTypeRenderer == null) {
            $$$reportNull$$$0(2);
        }
        return smartTypeRenderer;
    }

    public void renderTable(TableRenderer tableRenderer, StringBuilder sb) {
        if (tableRenderer.rows.isEmpty()) {
            return;
        }
        RenderingContext renderingContextComputeRenderingContext = computeRenderingContext(tableRenderer);
        for (TableRenderer.TableRow tableRow : tableRenderer.rows) {
            if (tableRow instanceof TextRenderer) {
                renderText((TextRenderer) tableRow, sb);
            }
            if (tableRow instanceof TableRenderer.DescriptorRow) {
                sb.append(Renderers.COMPACT.render((DeclarationDescriptor) ((TableRenderer.DescriptorRow) tableRow).descriptor, renderingContextComputeRenderingContext));
            }
            if (tableRow instanceof TableRenderer.FunctionArgumentsRow) {
                TableRenderer.FunctionArgumentsRow functionArgumentsRow = (TableRenderer.FunctionArgumentsRow) tableRow;
                renderFunctionArguments(functionArgumentsRow.receiverType, functionArgumentsRow.argumentTypes, sb, renderingContextComputeRenderingContext);
            }
            sb.append("\n");
        }
    }

    public void renderText(TextRenderer textRenderer, StringBuilder sb) {
        Iterator<TextRenderer.TextElement> it = textRenderer.elements.iterator();
        while (it.hasNext()) {
            sb.append(it.next().text);
        }
    }

    public TabledDescriptorRenderer table(TableRenderer tableRenderer) {
        if (tableRenderer == null) {
            $$$reportNull$$$0(1);
        }
        this.renderers.add(tableRenderer);
        return this;
    }

    public TabledDescriptorRenderer text(TextRenderer textRenderer) {
        if (textRenderer == null) {
            $$$reportNull$$$0(0);
        }
        this.renderers.add(textRenderer);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TableOrTextRenderer tableOrTextRenderer : this.renderers) {
            if (tableOrTextRenderer instanceof TableRenderer) {
                renderTable((TableRenderer) tableOrTextRenderer, sb);
            } else {
                renderText((TextRenderer) tableOrTextRenderer, sb);
            }
        }
        return sb.toString();
    }

    public static class TableRenderer implements TableOrTextRenderer {
        public final List<TableRow> rows = Lists.newArrayList();

        public static class DescriptorRow implements TableRow {
            public final CallableDescriptor descriptor;

            public DescriptorRow(CallableDescriptor callableDescriptor) {
                this.descriptor = callableDescriptor;
            }
        }

        public static class FunctionArgumentsRow implements TableRow {
            public final List<KotlinType> argumentTypes;
            public final Predicate<ConstraintPosition> isErrorPosition;
            public final KotlinType receiverType;

            public FunctionArgumentsRow(KotlinType kotlinType, List<KotlinType> list, Predicate<ConstraintPosition> predicate) {
                this.receiverType = kotlinType;
                this.argumentTypes = list;
                this.isErrorPosition = predicate;
            }
        }

        public interface TableRow {
        }

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 2) {
                objArr[0] = "isErrorPosition";
            } else if (i == 3) {
                objArr[0] = "text";
            } else if (i != 4) {
                objArr[0] = "argumentTypes";
            } else {
                objArr[0] = "textRenderer";
            }
            objArr[1] = "org/jetbrains/kotlin/diagnostics/rendering/TabledDescriptorRenderer$TableRenderer";
            if (i == 3 || i == 4) {
                objArr[2] = "text";
            } else {
                objArr[2] = "functionArgumentTypeList";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public static /* synthetic */ boolean a(ConstraintPosition constraintPosition) {
            return false;
        }

        public TableRenderer descriptor(CallableDescriptor callableDescriptor) {
            this.rows.add(new DescriptorRow(callableDescriptor));
            return this;
        }

        public TableRenderer functionArgumentTypeList(KotlinType kotlinType, List<KotlinType> list, Predicate<ConstraintPosition> predicate) {
            if (list == null) {
                $$$reportNull$$$0(1);
            }
            if (predicate == null) {
                $$$reportNull$$$0(2);
            }
            this.rows.add(new FunctionArgumentsRow(kotlinType, list, predicate));
            return this;
        }

        public TableRenderer text(String str) {
            if (str == null) {
                $$$reportNull$$$0(3);
            }
            this.rows.add(TabledDescriptorRenderer.newText().normal(str));
            return this;
        }

        public TableRenderer text(TextRenderer textRenderer) {
            if (textRenderer == null) {
                $$$reportNull$$$0(4);
            }
            this.rows.add(textRenderer);
            return this;
        }

        public TableRenderer functionArgumentTypeList(KotlinType kotlinType, List<KotlinType> list) {
            if (list == null) {
                $$$reportNull$$$0(0);
            }
            return functionArgumentTypeList(kotlinType, list, new Predicate() { // from class: p2e
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return TabledDescriptorRenderer.TableRenderer.a((ConstraintPosition) obj);
                }
            });
        }
    }
}

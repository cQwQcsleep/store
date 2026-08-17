package org.jetbrains.kotlin.cli.pipeline.jvm;

import javax.xml.stream.XMLStreamWriter;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u001c\u0010\t\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\n"}, d2 = {"indent", Argument.Delimiters.none, "Ljavax/xml/stream/XMLStreamWriter;", "depth", "Lorg/jetbrains/kotlin/cli/pipeline/jvm/PrettyPrintDepth;", "start", ModuleXmlParser.NAME, Argument.Delimiters.none, "end", "empty", "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmFrontendPipelinePhaseKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void empty(XMLStreamWriter xMLStreamWriter, String str, PrettyPrintDepth prettyPrintDepth) {
        indent(xMLStreamWriter, prettyPrintDepth);
        xMLStreamWriter.writeEmptyElement(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void end(XMLStreamWriter xMLStreamWriter, PrettyPrintDepth prettyPrintDepth) {
        prettyPrintDepth.setValue(prettyPrintDepth.getValue() - 1);
        indent(xMLStreamWriter, prettyPrintDepth);
        xMLStreamWriter.writeEndElement();
    }

    private static final void indent(XMLStreamWriter xMLStreamWriter, PrettyPrintDepth prettyPrintDepth) {
        xMLStreamWriter.writeCharacters("\n");
        if (prettyPrintDepth.getValue() > 0) {
            xMLStreamWriter.writeCharacters(StringsKt.repeat("  ", prettyPrintDepth.getValue()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void start(XMLStreamWriter xMLStreamWriter, String str, PrettyPrintDepth prettyPrintDepth) {
        indent(xMLStreamWriter, prettyPrintDepth);
        xMLStreamWriter.writeStartElement(str);
        prettyPrintDepth.setValue(prettyPrintDepth.getValue() + 1);
    }
}

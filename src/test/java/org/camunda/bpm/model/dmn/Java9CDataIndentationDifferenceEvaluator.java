package org.camunda.bpm.model.dmn;

import java.util.regex.Pattern;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.DifferenceEvaluator;

public class Java9CDataIndentationDifferenceEvaluator implements DifferenceEvaluator {

	@Override
	public ComparisonResult evaluate(Comparison comparison, ComparisonResult outcome) {
		if (outcome == ComparisonResult.EQUAL) return outcome; // only evaluate differences.
		// TODO implement a similar comparison for the text node itself
        final Node controlNode = comparison.getControlDetails().getTarget();
        final Node testNode = comparison.getTestDetails().getTarget();
        return testForJava9CDataIndentation(outcome, controlNode, testNode);
    }

	private ComparisonResult testForJava9CDataIndentation(ComparisonResult outcome, final Node controlNode, final Node testNode) {
		if (controlNode != null && testNode != null) {
	        final NodeList controlChildNodes = controlNode.getChildNodes();
	        final NodeList testChildNodes = testNode.getChildNodes();
			if (controlChildNodes.getLength() == 1
	        	&& testChildNodes.getLength() == 3) {
	        	Node ctrlChildNode = controlChildNodes.item(0);
				Node testChildNode0 = testChildNodes.item(0);
				Node testChildNode1 = testChildNodes.item(1);
				Node testChildNode2 = testChildNodes.item(2);
				if (ctrlChildNode instanceof CDATASection
		        	&& testChildNode0 instanceof Text
		        	&& testChildNode1 instanceof CDATASection
		        	&& testChildNode2 instanceof Text) {
					CDATASection ctrlCdataSection = (CDATASection) ctrlChildNode;
					Text testTextBefore = (Text) testChildNode0;
					CDATASection testCdataSection = (CDATASection) testChildNode1;
					Text testTextAfter = (Text) testChildNode2;
					if (Pattern.matches("\\A[ \\n\\t\\r]+\\z", testTextBefore.getData())
			        	&& Pattern.matches("\\A[ \\n\\t\\r]+\\z", testTextAfter.getData())
			        	&& ctrlCdataSection.getData().equals(testCdataSection.getData())
			        	) {
	                    return ComparisonResult.SIMILAR;
					}
	        	}
	        } else if (controlChildNodes.getLength() == 0
		        	&& testChildNodes.getLength() == 0
		        	&& controlNode instanceof CDATASection
		        	&& testNode instanceof Text) {
				CDATASection ctrlCdataSection = (CDATASection) controlNode;
				Text testTextBefore = (Text) testNode;
				if (Pattern.matches("\\A[ \\n\\t\\r]+\\z", testTextBefore.getData())) {
					return testForJava9CDataIndentation(outcome, ctrlCdataSection.getParentNode(), testTextBefore.getParentNode());
				}
	        }
        }
        return outcome;
	}

}

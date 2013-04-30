package ch.spacebase.openclassic.api.config;

import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.CollectionNode;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

/**
 * An empty null representer.
 */
public class EmptyNullRepresenter extends Representer {

	public EmptyNullRepresenter() {
		super();
		this.nullRepresenter = new EmptyRepresentNull();
	}

	/**
	 * A null representer.
	 */
	protected class EmptyRepresentNull implements Represent {
		@Override
		public Node representData(Object data) {
			return representScalar(Tag.NULL, "");
		}
	}

	// Borrowed from (http://code.google.com/p/snakeyaml/source/browse/src/test/java/org/yaml/snakeyaml/issues/issue60/SkipBeanTest.java)
	@Override
	protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
		NodeTuple tuple = super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
		Node valueNode = tuple.getValueNode();
		
		if (valueNode instanceof CollectionNode) {
			if (Tag.SEQ.equals(valueNode.getTag())) {
				SequenceNode seq = (SequenceNode) valueNode;
				if (seq.getValue().isEmpty()) {
					return null;
				}
			}
			if (Tag.MAP.equals(valueNode.getTag())) {
				MappingNode seq = (MappingNode) valueNode;
				if (seq.getValue().isEmpty()) {
					return null;
				}
			}
		}
		
		return tuple;
	}
	// End of borrowed code
	
}

/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camunda.bpm.model.dmn.impl;

import static org.camunda.bpm.model.dmn.impl.DmnModelConstants.DMN10_NS;
import static org.camunda.bpm.model.dmn.impl.DmnModelConstants.DMN_ATTRIBUTE_IS_ORDERED;
import static org.camunda.bpm.model.dmn.impl.DmnModelConstants.DMN_ATTRIBUTE_NAME;
import static org.camunda.bpm.model.dmn.impl.DmnModelConstants.DMN_ELEMENT_CLAUSE;

import java.util.Collection;

import org.camunda.bpm.model.dmn.instance.Clause;
import org.camunda.bpm.model.dmn.instance.InputEntry;
import org.camunda.bpm.model.dmn.instance.InputExpression;
import org.camunda.bpm.model.dmn.instance.ItemDefinition;
import org.camunda.bpm.model.dmn.instance.OutputDefinitionReference;
import org.camunda.bpm.model.dmn.instance.OutputEntry;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder.ModelTypeInstanceProvider;
import org.camunda.bpm.model.xml.type.attribute.Attribute;
import org.camunda.bpm.model.xml.type.child.ChildElement;
import org.camunda.bpm.model.xml.type.child.ChildElementCollection;
import org.camunda.bpm.model.xml.type.child.SequenceBuilder;
import org.camunda.bpm.model.xml.type.reference.ElementReference;

public class ClauseImpl extends DmnModelElementInstanceImpl implements Clause {

  protected static Attribute<String> nameAttribute;
  protected static Attribute<Boolean> isOrderedAttribute;
  protected static ChildElement<InputExpression> inputExpressionChild;
  protected static ChildElementCollection<InputEntry> inputEntryCollection;
  protected static ElementReference<ItemDefinition, OutputDefinitionReference> outputDefinitionRefChild;
  protected static ChildElementCollection<OutputEntry> outputEntryCollection;

  public ClauseImpl(ModelTypeInstanceContext instanceContext) {
    super(instanceContext);
  }

  public String getName() {
    return nameAttribute.getValue(this);
  }

  public void setName(String name) {
    nameAttribute.setValue(this, name);
  }

  public boolean isOrdered() {
    return isOrderedAttribute.getValue(this);
  }

  public void setOrdered(boolean isOrdered) {
    isOrderedAttribute.setValue(this, isOrdered);
  }

  public InputExpression getInputExpression() {
    return inputExpressionChild.getChild(this);
  }

  public void setInputExpression(InputExpression inputExpression) {
    inputExpressionChild.setChild(this, inputExpression);
  }

  public Collection<InputEntry> getInputEntries() {
    return inputEntryCollection.get(this);
  }

  public ItemDefinition getOutputDefinition() {
    return outputDefinitionRefChild.getReferenceTargetElement(this);
  }

  public void setOutputDefinition(ItemDefinition outputDefinition) {
    outputDefinitionRefChild.setReferenceTargetElement(this, outputDefinition);
  }

  public Collection<OutputEntry> getOutputEntries() {
    return outputEntryCollection.get(this);
  }

  public static void registerType(ModelBuilder modelBuilder) {
    ModelElementTypeBuilder typeBuilder = modelBuilder.defineType(Clause.class, DMN_ELEMENT_CLAUSE)
      .namespaceUri(DMN10_NS)
      .instanceProvider(new ModelTypeInstanceProvider<Clause>() {
        public Clause newInstance(ModelTypeInstanceContext instanceContext) {
          return new ClauseImpl(instanceContext);
        }
      });

    nameAttribute = typeBuilder.stringAttribute(DMN_ATTRIBUTE_NAME)
      .build();

    isOrderedAttribute = typeBuilder.booleanAttribute(DMN_ATTRIBUTE_IS_ORDERED)
      .defaultValue(false)
      .build();

    SequenceBuilder sequenceBuilder = typeBuilder.sequence();

    inputExpressionChild = sequenceBuilder.element(InputExpression.class)
      .build();

    inputEntryCollection = sequenceBuilder.elementCollection(InputEntry.class)
      .build();

    outputDefinitionRefChild = sequenceBuilder.element(OutputDefinitionReference.class)
      .uriElementReference(ItemDefinition.class)
      .build();

    outputEntryCollection = sequenceBuilder.elementCollection(OutputEntry.class)
      .build();

    typeBuilder.build();
  }

}

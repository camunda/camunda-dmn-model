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
import static org.camunda.bpm.model.dmn.impl.DmnModelConstants.DMN_ELEMENT_DECISION_RULE;

import java.util.Collection;

import org.camunda.bpm.model.dmn.instance.Conclusion;
import org.camunda.bpm.model.dmn.instance.Condition;
import org.camunda.bpm.model.dmn.instance.DecisionRule;
import org.camunda.bpm.model.dmn.instance.Expression;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder.ModelTypeInstanceProvider;
import org.camunda.bpm.model.xml.type.child.SequenceBuilder;
import org.camunda.bpm.model.xml.type.reference.ElementReferenceCollection;

public class DecisionRuleImpl extends DmnModelElementInstanceImpl implements DecisionRule {
  
  protected static ElementReferenceCollection<Expression, Condition> conditionRefCollection;
  protected static ElementReferenceCollection<Expression, Conclusion> conclusionRefCollection;

  public DecisionRuleImpl(ModelTypeInstanceContext instanceContext) {
    super(instanceContext);
  }

  public Collection<Expression> getConditions() {
    return conditionRefCollection.getReferenceTargetElements(this);
  }

  public Collection<Expression> getConclusions() {
    return conclusionRefCollection.getReferenceTargetElements(this);
  }

  public static void registerType(ModelBuilder modelBuilder) {
    ModelElementTypeBuilder typeBuilder = modelBuilder.defineType(DecisionRule.class, DMN_ELEMENT_DECISION_RULE)
      .namespaceUri(DMN10_NS)
      .instanceProvider(new ModelTypeInstanceProvider<DecisionRule>() {
        public DecisionRule newInstance(ModelTypeInstanceContext instanceContext) {
          return new DecisionRuleImpl(instanceContext);
        }
      });

    SequenceBuilder sequenceBuilder = typeBuilder.sequence();

    conditionRefCollection = sequenceBuilder.elementCollection(Condition.class)
      .idsElementReferenceCollection(Expression.class)
      .build();

    conclusionRefCollection = sequenceBuilder.elementCollection(Conclusion.class)
      .idsElementReferenceCollection(Expression.class)
      .build();

    typeBuilder.build();
  }

}

/*
 * Copyright © 2015-2018 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
package org.camunda.bpm.model.dmn.instance;

import java.util.Collection;

import org.camunda.bpm.model.dmn.BuiltinAggregator;
import org.camunda.bpm.model.dmn.DecisionTableOrientation;
import org.camunda.bpm.model.dmn.HitPolicy;

public interface DecisionTable extends Expression {

  HitPolicy getHitPolicy();
  
  void setHitPolicy(HitPolicy hitPolicy);
  
  BuiltinAggregator getAggregation();
  
  void setAggregation(BuiltinAggregator aggregation);
  
  DecisionTableOrientation getPreferredOrientation();
  
  void setPreferredOrientation(DecisionTableOrientation preferredOrientation);

  String getOutputLabel();

  void setOutputLabel(String outputLabel);

  Collection<Input> getInputs();

  Collection<Output> getOutputs();

  Collection<Rule> getRules();

}

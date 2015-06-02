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
import static org.camunda.bpm.model.dmn.impl.DmnModelConstants.DMN_ELEMENT_IMPACTED_PERFORMANCE_INDICATOR;

import org.camunda.bpm.model.dmn.instance.DmnElementReference;
import org.camunda.bpm.model.dmn.instance.ImpactedPerformanceIndicatorReference;
import org.camunda.bpm.model.xml.ModelBuilder;
import org.camunda.bpm.model.xml.impl.instance.ModelTypeInstanceContext;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder;
import org.camunda.bpm.model.xml.type.ModelElementTypeBuilder.ModelTypeInstanceProvider;

public class ImpactedPerformanceIndicatorReferenceImpl extends DmnElementReferenceImpl implements ImpactedPerformanceIndicatorReference {

  public ImpactedPerformanceIndicatorReferenceImpl(ModelTypeInstanceContext instanceContext) {
    super(instanceContext);
  }

  public static void registerType(ModelBuilder modelBuilder) {
    ModelElementTypeBuilder typeBuilder = modelBuilder.defineType(ImpactedPerformanceIndicatorReference.class, DMN_ELEMENT_IMPACTED_PERFORMANCE_INDICATOR)
      .namespaceUri(DMN10_NS)
      .extendsType(DmnElementReference.class)
      .instanceProvider(new ModelTypeInstanceProvider<ImpactedPerformanceIndicatorReference>() {
        public ImpactedPerformanceIndicatorReference newInstance(ModelTypeInstanceContext instanceContext) {
          return new ImpactedPerformanceIndicatorReferenceImpl(instanceContext);
        }
      });

    typeBuilder.build();
  }

}

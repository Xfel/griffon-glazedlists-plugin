/*
 * Copyright 2014 the original author or authors.
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
package griffon.builder.swing.factory

import ca.odell.glazedlists.EventList
import ca.odell.glazedlists.swing.DefaultEventListModel

import static ca.odell.glazedlists.swing.GlazedListsSwing.eventListModel
import static ca.odell.glazedlists.swing.GlazedListsSwing.eventListModelWithThreadProxyList

/**
 * @author Andres Almiray
 */
class EventListModelFactory extends AbstractModelFactory {
    Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes)
        throws InstantiationException, IllegalAccessException {
        if (FactoryBuilderSupport.checkValueIsTypeNotString(value, name, DefaultEventListModel)) {
            return value
        }

        if (!attributes.containsKey('source')) {
            throw new IllegalArgumentException("In $name you must define a value for source: of type ${EventList.class.name}")
        }
        EventList source = attributes.remove('source')
        boolean wrap = true
        if (attributes.containsKey('wrap')) wrap = attributes.remove('wrap') as boolean
        wrap ? eventListModelWithThreadProxyList(source) : eventListModel(source)
    }
}

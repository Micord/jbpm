/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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
package org.jbpm.persistence.correlation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import org.kie.internal.KieInternalServices.Factory;
import org.kie.internal.process.CorrelationKey;
import org.kie.internal.process.CorrelationKeyFactory;
import org.kie.internal.process.CorrelationProperty;

public class CorrelationKeyXmlAdapter extends XmlAdapter<String, CorrelationKey> {
  public CorrelationKeyXmlAdapter() {
  }

  public CorrelationKey unmarshal(String key) throws Exception {
    return unmarshalCorrelationKey(key);
  }

  public static CorrelationKey unmarshalCorrelationKey(String key) {
    CorrelationKeyFactory factory = Factory.get().newCorrelationKeyFactory();
    if (key != null && !key.isEmpty()) {
      List<String> keys = new ArrayList();
      String[] var3 = key.split(":");
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
        String k = var3[var5];
        keys.add(k);
      }

      return Factory.get().newCorrelationKeyFactory().newCorrelationKey(keys);
    } else {
      return factory.newCorrelationKey(Collections.EMPTY_LIST);
    }
  }

  public String marshal(CorrelationKey key) throws Exception {
    return marshalCorrelationKey(key);
  }

  public static String marshalCorrelationKey(CorrelationKey key) {
    if (key == null) {
      return "";
    } else {
      StringBuffer result = new StringBuffer();
      if (!key.getProperties().isEmpty()) {
        Iterator<CorrelationProperty<?>> iter = key.getProperties().iterator();
        CorrelationProperty<?> prop = (CorrelationProperty)iter.next();
        if (prop != null) {
          result.append(prop.getValue().toString());
        }

        while(iter.hasNext()) {
          prop = (CorrelationProperty)iter.next();
          if (prop != null) {
            result.append(":").append(prop.getValue().toString());
          }
        }
      }

      return result.toString();
    }
  }
}

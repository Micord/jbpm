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
package org.jbpm.util;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author zamaliev
 */
public class SignalEventDesc {

  private UUID signalUUID;
  private Map<String, Object> params;

  public SignalEventDesc(UUID signalUUID, Map<String, Object> params) {
    this.signalUUID = Objects.requireNonNull(signalUUID);
    this.params = params;
  }

  public UUID getSignalUUID() {
    return signalUUID;
  }

  public void setSignalUUID(UUID signalUUID) {
    this.signalUUID = signalUUID;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public void setParams(Map<String, Object> params) {
    this.params = params;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SignalEventDesc that = (SignalEventDesc) o;
    return signalUUID.equals(that.signalUUID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signalUUID);
  }
}

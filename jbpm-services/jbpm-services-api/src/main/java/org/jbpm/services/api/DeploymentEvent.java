/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.services.api;

import org.jbpm.services.api.model.DeployedUnit;

public class DeploymentEvent {
    
    private String deploymentId;
    private DeployedUnit deployedUnit;
    
    public DeploymentEvent(String deploymentId, DeployedUnit deployedUnit) {
        this.deployedUnit = deployedUnit;
        this.deploymentId = deploymentId;
    }

    public DeployedUnit getDeployedUnit() {
        return deployedUnit;
    }

    public void setDeployedUnit(DeployedUnit deployedUnit) {
        this.deployedUnit = deployedUnit;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }
    
}

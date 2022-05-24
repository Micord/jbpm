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

package org.jbpm.workflow.core.node;

import org.jbpm.bpmn2.BpmnNodeIllegalArgumentException;
import org.jbpm.process.core.timer.Timer;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.kie.api.definition.process.Connection;
import org.kie.api.definition.process.NodeType;

public class TimerNode extends ExtendedNodeImpl {

    private static final long serialVersionUID = 510l;
    
    private Timer timer;
    
    public TimerNode() {
        super(NodeType.TIMER);
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    public Timer getTimer() {
        return this.timer;
    }

    public void validateAddIncomingConnection(final String type, final Connection connection) {
        super.validateAddIncomingConnection(type, connection);
        if (!org.jbpm.workflow.core.Node.CONNECTION_DEFAULT_TYPE.equals(type)) {
            throw new BpmnNodeIllegalArgumentException("This type of node only accepts default incoming connection type!",
                connection.getFrom().getName(),
                connection.getFrom().getNodeUniqueId());
        }
        if (getFrom() != null && !"true".equals(System.getProperty("jbpm.enable.multi.con"))) {
            throw new BpmnNodeIllegalArgumentException("This type of node cannot have more than one incoming connection!",
                connection.getTo().getName(),
                connection.getTo().getNodeUniqueId());
        }
    }

    public void validateAddOutgoingConnection(final String type, final Connection connection) {
        super.validateAddOutgoingConnection(type, connection);
        if (!org.jbpm.workflow.core.Node.CONNECTION_DEFAULT_TYPE.equals(type)) {
            throw new BpmnNodeIllegalArgumentException("This type of node only accepts default outgoing connection type!",
                connection.getFrom().getName(),
                connection.getFrom().getNodeUniqueId());
        }
        if (getTo() != null && !"true".equals(System.getProperty("jbpm.enable.multi.con"))) {
            throw new BpmnNodeIllegalArgumentException("This type of node cannot have more than one outgoing connection!",
                connection.getTo().getName(),
                connection.getTo().getNodeUniqueId());
        }
    }
    
}

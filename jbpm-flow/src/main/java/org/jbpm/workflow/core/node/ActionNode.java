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
import org.jbpm.workflow.core.DroolsAction;
import org.jbpm.workflow.core.impl.ExtendedNodeImpl;
import org.kie.api.definition.process.Connection;
import org.kie.api.definition.process.NodeType;


/**
 * Default implementation of an action node.
 * 
 */
public class ActionNode extends ExtendedNodeImpl {

	private static final long serialVersionUID = 510l;
	
	private DroolsAction action;

    private boolean executeActionAfterComplete = false;

    


    public ActionNode() {
        super(NodeType.SCRIPT_TASK);
    }

    public ActionNode(NodeType nodeType) {
        super(nodeType);
    }

    
    public void setExecuteActionAfterComplete(boolean executeActionAfterComplete) {
        this.executeActionAfterComplete = executeActionAfterComplete;
    }

    public boolean isExecuteActionAfterComplete() {
        return executeActionAfterComplete;
    }

	public DroolsAction getAction() {
		return action;
	}

	public void setAction(DroolsAction action) {
		this.action = action;
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

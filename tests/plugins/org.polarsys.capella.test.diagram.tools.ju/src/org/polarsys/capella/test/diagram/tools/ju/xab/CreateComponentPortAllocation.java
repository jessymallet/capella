/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.xab;

import org.eclipse.sirius.business.api.session.Session;

import org.polarsys.capella.test.diagram.common.ju.context.PABDiagram;
import org.polarsys.capella.test.framework.context.SessionContext;

public class CreateComponentPortAllocation extends XABDiagramsProject {

  @Override
  public void test() throws Exception {
    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    testOnXAB(context, PA__PAB_DIAGRAM, PA__PAB_PHYSICAL_PORT_PP1,
        PA__PAB_COMPONENT_PORT_1);
  }

  public void testOnXAB(SessionContext context, String diagramName, String sourceId, String targetId) {
    PABDiagram pabDiagram = PABDiagram.openDiagram(context, diagramName);
    pabDiagram.createComponentPortAllocation(sourceId, targetId);
  }

  
}

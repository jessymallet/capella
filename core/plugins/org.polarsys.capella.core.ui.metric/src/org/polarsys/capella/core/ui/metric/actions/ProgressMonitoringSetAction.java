/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.metric.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;
import org.polarsys.capella.core.ui.metric.IImageKeys;
import org.polarsys.capella.core.ui.metric.MetricActivator;
import org.polarsys.capella.core.ui.metric.MetricMessages;
import org.polarsys.capella.core.ui.metric.utils.ProgressMonitoringPropagator;
import org.polarsys.capella.core.ui.metric.utils.Utils;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

public class ProgressMonitoringSetAction extends BaseSelectionListenerAction {

  private static final Logger logger = ReportManagerRegistry.getInstance().subscribe("Progress Monitoring"); //$NON-NLS-1$

  /**
   * Constructor.
   */
  public ProgressMonitoringSetAction() {
    super(MetricMessages.progressMonitoring_setAction_lbl);
    setImageDescriptor(MetricActivator.getDefault().getImageDescriptor(IImageKeys.IMG_PROGRESS_MONITORING));
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Assume that all selected objects are in the same Capella Project
    final Collection<EObject> selectedObjects = ProgressMonitoringActionsHelper
        .getSelectedEObjects(getStructuredSelection());
    if (selectedObjects.isEmpty()) {
      return;
    }

    final ProgressSetDialog dialog = getRunSetup(selectedObjects);
    if (dialog != null) {
      TransactionHelper.getExecutionManager(selectedObjects.iterator().next()).execute(new AbstractReadWriteCommand() {
        @SuppressWarnings("synthetic-access")
        public void run() {
          Collection<EObject> result = ProgressMonitoringPropagator.getInstance().applyPropertiesOn(
              Collections.singletonList(dialog.getSelectedEnum()), selectedObjects,
              dialog.isPropagateWithoutFiltering(), dialog.isPropagateToRepresentations(),
              dialog.useFilterStatus(), getLabel(dialog), dialog.mustCleanReview());

          // Capella Elements
          Collection<EObject> capellaElements = Collections2.filter(result, new Predicate<EObject>() {
            public boolean apply(EObject input) {
              return input instanceof CapellaElement;
            }
          });
          if (capellaElements.size() == 0) {
            logger.info(NLS.bind(MetricMessages.progressMonitoring_setAction_nochanges_info, "Capella"));
          } else {
            logger.info(NLS.bind(MetricMessages.progressMonitoring_setAction_changes_info, capellaElements.size(),
                "Capella"));
          }

          // DRepresentation Elements
          Collection<EObject> representationElements = Collections2.filter(result, new Predicate<EObject>() {
            public boolean apply(EObject input) {
              return input instanceof DRepresentation;
            }
          });
          if (representationElements.size() == 0) {
            logger.info(NLS.bind(MetricMessages.progressMonitoring_setAction_nochanges_info, "Representation"));
          } else {
            logger.info(NLS.bind(MetricMessages.progressMonitoring_setAction_changes_info, capellaElements.size(),
                "Representation"));
          }
        }

        private String getLabel(final ProgressSetDialog runSetup) {
          return runSetup.getFilterStatus() == null ? null:runSetup.getFilterStatus().getLabel();
        }

        @Override
        public String getName() {
          return MetricMessages.progressMonitoring_setAction_cmd_lbl;
        }
      });
    }
  }

  protected ProgressSetDialog getRunSetup(Collection<EObject> selectedObjects) {
    Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
    final ProgressSetDialog dialog = new ProgressSetDialog(shell, selectedObjects);
    if (dialog.open() == Window.OK) {
      return dialog;
    }
    return null;
  }

  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    if (selection != null) {
      boolean enabled = true;
      Set<IProject> projects = new HashSet<IProject>();
      Iterator<?> iterator = selection.iterator();
      while (iterator.hasNext()) {
        Object object = iterator.next();
        enabled &= isEnabled(object);
        projects.add(getProject(object));
      }
      return enabled && projects.size() == 1;
    }
    return false;
  }

  private IProject getProject(Object object) {
    if (object instanceof EObject) {
      return getProject(EcoreUtil2.getFile(((EObject) object).eResource()));
    } else if (object instanceof IFile) {
      return ((IFile) object).getProject();
    }
    return null;
  }

  private boolean isEnabled(Object selection) {
    if (selection instanceof IFile) {
      Session session = SessionHelper.getSession((IFile) selection);
      if ((null != session) && session.isOpen()) {
        Resource resource = session.getSessionResource();
        DAnalysis da = (DAnalysis) EcoreUtil.getObjectByType(resource.getContents(),
            ViewpointPackage.Literals.DANALYSIS);
        Collection<EObject> models = new ArrayList<EObject>();
        models.addAll(da.getModels());
        for (DAnalysis refDa : da.getReferencedAnalysis()) {
          models.addAll(refDa.getModels());
        }

        boolean showProgressAction = false;
        for (EObject model : models) {
          showProgressAction = showProgressAction
              || ProgressMonitoringPropagator.getInstance().isEnumerationPropertyTypeDefinedForProject(model);
        }
        return showProgressAction;
      }
    } else if (selection instanceof DRepresentation) {
      return ProgressMonitoringPropagator.getInstance().isEnumerationPropertyTypeDefinedForProject(
          Utils.getTarget((DRepresentation) selection));
    } else if (selection instanceof EObject) {
      return ProgressMonitoringPropagator.getInstance().isEnumerationPropertyTypeDefinedForProject((EObject) selection);
    }
    return false;
  }
}

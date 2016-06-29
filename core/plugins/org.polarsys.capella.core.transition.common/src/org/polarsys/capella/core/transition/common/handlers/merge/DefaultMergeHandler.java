/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.handlers.merge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DefaultMergeHandler implements IMergeHandler {

  protected Collection<ICategoryItem> categories;

  public void addCategory(ICategoryItem filter, IContext context) {
    if (categories == null) {
      categories = new LinkedList<ICategoryItem>();
    }
    categories.add(filter);
  }

  public IStatus processDifferences(IContext context, Collection<IDifference> diffSource,
      Collection<IDifference> diffTarget) {
    processDifferences(context, diffSource, Role.TARGET);
    return Status.OK_STATUS;
  }

  public IStatus processDifferences(IContext context, Collection<IDifference> differences, Role role) {
    Collection<IDifference> result = new ArrayList<IDifference>();
    for (IDifference difference : differences) {
      for (ICategoryItem item : categories) {
        if (item.isActive() && !isFiltered(difference)) {
          result.add(difference);
        }
      }
    }

    EComparison comparison = (EComparison) context.get(ITransitionConstants.MERGE_COMPARISON);
    comparison.merge(result, role, true, new NullProgressMonitor());

    return Status.OK_STATUS;
  }

  public boolean isFiltered(IDifference difference) {
    boolean focused = false;
    boolean excluded = false;

    for (ICategoryItem category : categories) {
      if (category.isInFocusMode()) {
        focused = focused || category.covers(difference);
      } else {
        excluded = excluded || category.covers(difference);
      }
    }
    if (excluded)
      return true;

    if (focused)
      return false;

    return true;
  }

  public IStatus init(IContext context) {
    categories = new LinkedList<ICategoryItem>();
    return Status.OK_STATUS;
  }

  public IStatus dispose(IContext context) {
    if (categories != null) {
      categories.clear();
      categories = null;
    }
    return Status.OK_STATUS;
  }

  public Collection<ICategoryItem> getCategories(IContext context) {
    return Collections.unmodifiableCollection(categories);
  }

}

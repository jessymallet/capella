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
package org.polarsys.capella.common.re.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.re.ReFactory;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.util.ReAdapterFactory;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.extension.edit.ChildCreationExtenderManager;
import org.polarsys.kitalpha.emde.model.EmdePackage;
import org.polarsys.kitalpha.emde.model.ExtensibleElement;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;
import org.polarsys.kitalpha.emde.model.util.EmdeSwitch;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ReItemProviderAdapterFactory extends ReAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable, IChildCreationExtender {
  /**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected ComposedAdapterFactory parentAdapterFactory;

  /**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected IChangeNotifier changeNotifier = new ChangeNotifier();

  /**
	 * This helps manage the child creation extenders.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected ChildCreationExtenderManager childCreationExtenderManager = new ChildCreationExtenderManager(ReEditPlugin.INSTANCE, RePackage.eNS_URI);

  /**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
	 * @generated
	 */
  protected Collection<Object> supportedTypes = new ArrayList<Object>();

  /**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ReItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.common.re.RecCatalog} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected RecCatalogItemProvider recCatalogItemProvider;

  /**
	 * This creates an adapter for a {@link org.polarsys.capella.common.re.RecCatalog}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createRecCatalogAdapter() {
		if (recCatalogItemProvider == null) {
			recCatalogItemProvider = new RecCatalogItemProvider(this);
		}

		return recCatalogItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.common.re.GroupingElementPkg} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected GroupingElementPkgItemProvider groupingElementPkgItemProvider;

  /**
	 * This creates an adapter for a {@link org.polarsys.capella.common.re.GroupingElementPkg}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createGroupingElementPkgAdapter() {
		if (groupingElementPkgItemProvider == null) {
			groupingElementPkgItemProvider = new GroupingElementPkgItemProvider(this);
		}

		return groupingElementPkgItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.common.re.CatalogElementPkg} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected CatalogElementPkgItemProvider catalogElementPkgItemProvider;

  /**
	 * This creates an adapter for a {@link org.polarsys.capella.common.re.CatalogElementPkg}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createCatalogElementPkgAdapter() {
		if (catalogElementPkgItemProvider == null) {
			catalogElementPkgItemProvider = new CatalogElementPkgItemProvider(this);
		}

		return catalogElementPkgItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.common.re.CatalogElementLink} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected CatalogElementLinkItemProvider catalogElementLinkItemProvider;

  /**
	 * This creates an adapter for a {@link org.polarsys.capella.common.re.CatalogElementLink}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createCatalogElementLinkAdapter() {
		if (catalogElementLinkItemProvider == null) {
			catalogElementLinkItemProvider = new CatalogElementLinkItemProvider(this);
		}

		return catalogElementLinkItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.common.re.CatalogElement} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected CatalogElementItemProvider catalogElementItemProvider;

  /**
	 * This creates an adapter for a {@link org.polarsys.capella.common.re.CatalogElement}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createCatalogElementAdapter() {
		if (catalogElementItemProvider == null) {
			catalogElementItemProvider = new CatalogElementItemProvider(this);
		}

		return catalogElementItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.common.re.CompliancyDefinitionPkg} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected CompliancyDefinitionPkgItemProvider compliancyDefinitionPkgItemProvider;

  /**
	 * This creates an adapter for a {@link org.polarsys.capella.common.re.CompliancyDefinitionPkg}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createCompliancyDefinitionPkgAdapter() {
		if (compliancyDefinitionPkgItemProvider == null) {
			compliancyDefinitionPkgItemProvider = new CompliancyDefinitionPkgItemProvider(this);
		}

		return compliancyDefinitionPkgItemProvider;
	}

  /**
	 * This keeps track of the one adapter used for all {@link org.polarsys.capella.common.re.CompliancyDefinition} instances.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected CompliancyDefinitionItemProvider compliancyDefinitionItemProvider;

  /**
	 * This creates an adapter for a {@link org.polarsys.capella.common.re.CompliancyDefinition}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter createCompliancyDefinitionAdapter() {
		if (compliancyDefinitionItemProvider == null) {
			compliancyDefinitionItemProvider = new CompliancyDefinitionItemProvider(this);
		}

		return compliancyDefinitionItemProvider;
	}

  /**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

  /**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

  /**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public List<IChildCreationExtender> getChildCreationExtenders() {
		return childCreationExtenderManager.getChildCreationExtenders();
	}

  /**
	 * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
	 * @generated
	 */
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
		return childCreationExtenderManager.getNewChildDescriptors(object, editingDomain);
	}

  /**
	 * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
	 * @generated
	 */
  public ResourceLocator getResourceLocator() {
		return childCreationExtenderManager;
	}

  /**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
	 * @generated
	 */
  public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

  /**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

  /**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

  /**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void dispose() {
		if (catalogElementPkgItemProvider != null) catalogElementPkgItemProvider.dispose();
		if (recCatalogItemProvider != null) recCatalogItemProvider.dispose();
		if (groupingElementPkgItemProvider != null) groupingElementPkgItemProvider.dispose();
		if (catalogElementLinkItemProvider != null) catalogElementLinkItemProvider.dispose();
		if (catalogElementItemProvider != null) catalogElementItemProvider.dispose();
		if (compliancyDefinitionPkgItemProvider != null) compliancyDefinitionPkgItemProvider.dispose();
		if (compliancyDefinitionItemProvider != null) compliancyDefinitionItemProvider.dispose();
	}

  /**
	 * A child creation extender for the {@link EmdePackage}.
	 * <!-- begin-user-doc -->
  * <!-- end-user-doc -->
	 * @generated
	 */
  public static class EmdeChildCreationExtender implements IChildCreationExtender {
    /**
		 * The switch for creating child descriptors specific to each extended class.
		 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
		 * @generated
		 */
    protected static class CreationSwitch extends EmdeSwitch<Object> {
      /**
			 * The child descriptors being populated.
			 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
			 * @generated
			 */
      protected List<Object> newChildDescriptors;

      /**
			 * The domain in which to create the children.
			 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
			 * @generated
			 */
      protected EditingDomain editingDomain;

      /**
			 * Creates the a switch for populating child descriptors in the given domain.
			 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
			 * @generated
			 */
      CreationSwitch(List<Object> newChildDescriptors, EditingDomain editingDomain) {
				this.newChildDescriptors = newChildDescriptors;
				this.editingDomain = editingDomain;
			}

      /**
			 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
			 * @generated
			 */
      @Override
      public Object caseExtensibleElement(ExtensibleElement object) {
				// begin-extension-code
				if (ModelExtensionHelper.getInstance(object).isExtensionModelDisabled(EcoreUtil.getRootContainer(object).eClass().getEPackage().getNsURI(), "http://www.polarsys.org/capella/common/re/1.2.0")) { //$NON-NLS-1$
					return null;				
				}
				// end-extension-code
                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (EmdePackage.Literals.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS,
                         ReFactory.eINSTANCE.createRecCatalog());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code



                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (EmdePackage.Literals.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS,
                         ReFactory.eINSTANCE.createGroupingElementPkg());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code



				return null;
			}

      /**
			 * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
			 * @generated
			 */
      protected CommandParameter createChildParameter(Object feature, Object child) {
				return new CommandParameter(null, feature, child);
			}

    }

    /**
		 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
		 * @generated
		 */
    public Collection<Object> getNewChildDescriptors(Object object, EditingDomain editingDomain) {
			ArrayList<Object> result = new ArrayList<Object>();
		   new CreationSwitch(result, editingDomain).doSwitch((EObject)object);
		   return result;
		}

    /**
		 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
		 * @generated
		 */
    public ResourceLocator getResourceLocator() {
			return ReEditPlugin.INSTANCE;
		}
  }

}
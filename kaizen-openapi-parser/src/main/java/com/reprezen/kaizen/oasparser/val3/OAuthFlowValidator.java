/*******************************************************************************
 *  Copyright (c) 2017 ModelSolv, Inc. and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     ModelSolv, Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package com.reprezen.kaizen.oasparser.val3;

import com.reprezen.kaizen.oasparser.model3.OAuthFlow;
import com.reprezen.kaizen.oasparser.val.ObjectValidatorBase;

public class OAuthFlowValidator extends ObjectValidatorBase<OAuthFlow> {

	@Override
	public void runObjectValidations() {
		OAuthFlow oauthFlow = (OAuthFlow) value.getOverlay();
		validateUrlField("authorizationUrl", true, false);
		validateUrlField("tokenUrl", true, false);
		validateUrlField("refreshUrl", true, false);
		validateMapField("scopes", true, false, String.class, null);
		validateExtensions(oauthFlow.getExtensions());
	}
}

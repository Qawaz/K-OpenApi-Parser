/*********************************************************************
*  Copyright (c) 2017 ModelSolv, Inc. and others.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 *     ModelSolv, Inc. 
 *     - initial API and implementation and/or initial documentation
**********************************************************************/
package com.reprezen.jsonoverlay;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.reprezen.jsonoverlay.ResolutionException.ReferenceCycleException;
import com.reprezen.jsonoverlay.model.TestModelParser;
import com.reprezen.jsonoverlay.model.intf.Color;
import com.reprezen.jsonoverlay.model.intf.Scalars;
import com.reprezen.jsonoverlay.model.intf.TestModel;

public class ReferenceTests extends Assert {

	private TestModel model;

	@Before
	public void setup() throws IOException {
		model = TestModelParser.parse(ReferenceTests.class.getResource("/refTest.yaml"));
	}

	@Test
	public void testRefAccess() {
		assertEquals("Reference Test", model.getDescription());
		checkScalarsValues(model.getScalar("s1"), true, true);
		checkScalarsValues(model.getScalar("s2"), false, true);
		checkScalarsValues(model.getScalar("s3"), true, true);
		checkScalarsValues(model.getScalar("s4"), true, true);
		checkScalarsValues(model.getScalar("s5"), false, true);
		checkScalarsValues(model.getScalar("ext1"), false, false);
		checkScalarsValues(model.getScalar("ext2"), false, false);
		checkScalarsValues(model.getScalar("ext3"), true, true);
	}

	private void checkScalarsValues(Scalars s, boolean sharedRoot, boolean sharedValues) {
		assertEquals("hello", s.getStringValue());
		assertEquals(Integer.valueOf(10), s.getIntValue());
		assertEquals(3.1, s.getNumberValue());
		assertEquals(Boolean.TRUE, s.getBoolValue());
		assertEquals(Arrays.asList(1, 2, 3), s.getObjValue());
		assertEquals("abcde", s.getPrimValue());
		assertEquals(Color.BLUE, s.getColorValue());
		checkScalarsSharing(s, sharedRoot, sharedValues);
	}

	private void checkScalarsSharing(Scalars s, boolean sharedRoot, boolean sharedValues) {
		Scalars s1 = model.getScalar("s1");
		if (sharedRoot) {
			assertSame("Scalars objects should be shared", s1, s);
		} else if (sharedValues) {
			assertSame("String value should be shared", s1.getStringValue(), s.getStringValue());
		}
	}

	@Test
	public void checkRecursion() {
		assertSame(model.getScalar("s6").getEmbeddedScalars(), model.getScalar("s5"));
		assertSame(model.getScalar("s7").getEmbeddedScalars(), model.getScalar("s7"));
		assertSame(model.getScalar("s8a").getEmbeddedScalars(), model.getScalar("s8b"));
		assertSame(model.getScalar("s8b").getEmbeddedScalars(), model.getScalar("s8a"));
		assertSame(model.getScalar("ext1"), model.getScalar("ext2"));
	}

	@Test
	public void checkBadRefs() {
		assertNull(model.getScalar("badPointer"));
		checkBadRef(Overlay.of(model.getScalars()).getReference("badPointer"));
		checkBadRef(Overlay.of(model.getScalars()).getReference("cycle"));
		assertTrue(Overlay.of(model.getScalars()).getReference("cycle")
				.getInvalidReason() instanceof ReferenceCycleException);
	}

	private void checkBadRef(Reference ref) {
		assertTrue(ref.isInvalid());
		assertTrue(ref.getInvalidReason() instanceof ResolutionException);
	}

	@Test
	public void testRoots() {
		assertSame(model, Overlay.of(model).getRoot());
		assertSame(model, Overlay.of(model.getScalar("s1")).getRoot());
		Scalars ext1 = model.getScalar("ext1");
		assertSame(ext1, Overlay.of(ext1).getRoot());
		assertSame(model, Overlay.of(model.getScalar("s3")).getRoot());

		assertSame(model, Overlay.of(model).getModel());
		assertSame(model, Overlay.of(model.getScalar("s1")).getModel());
		assertNull(Overlay.of(ext1).getModel());
		assertSame(model, Overlay.of(model.getScalar("s3")).getModel());
	}

	@Test
	public void testFind() {
		assertSame(model.getScalar("s1"), Overlay.of(model).find("/scalars/s1"));
		assertSame(model.getScalar("s3"), Overlay.of(model).find("/scalars/s1"));
		assertSame(model.getScalar("s3"), Overlay.of(model).find("/scalars/s3"));
		assertSame(model.getScalar("ext1"), Overlay.of(model).find("/scalars/ext1"));
		assertSame(model.getScalar("ext2"), Overlay.of(model).find("/scalars/ext1"));
		assertSame(model.getScalar("ext3"), Overlay.of(model).find("/scalars/s1"));
	}

	@Test
	public void testJsonRefs() {
		String url = getClass().getResource("/refTest.yaml").toString();
		String ext = getClass().getResource("/external.yaml").toString();
		assertEquals(url, Overlay.of(model).getJsonReference());
		assertEquals(url + "#/scalars/s1", Overlay.of(model.getScalars(), "s1").getJsonReference());
		assertEquals(url + "#/scalars/s1/stringValue",
				Overlay.of(model.getScalar("s1"), "stringValue", String.class).getJsonReference());
		assertEquals(url + "#/scalars/s1/stringValue",
				Overlay.of(model.getScalar("s2"), "stringValue", String.class).getJsonReference());
		assertEquals(url + "#/scalars/s1", Overlay.of(model.getScalar("s3")).getJsonReference());
		assertEquals(ext + "#/scalar1", Overlay.of(model.getScalar("ext1")).getJsonReference());
		assertEquals(ext + "#/scalar1", Overlay.of(model.getScalar("ext2")).getJsonReference());
		assertEquals(url + "#/scalars/s1", Overlay.of(model.getScalar("ext3")).getJsonReference());
	}
}

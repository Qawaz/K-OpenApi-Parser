/*********************************************************************
*  Copyright (c) 2023 ModelSolv, Inc. and others.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
 *
 *  Contributors:
 * WaqasTahir - Contributions after 2022
    WaqasTahir 
 *     - initial API and implementation and/or initial documentation
**********************************************************************/
package com.wakaztahir.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeData {

	private Collection<Type> types;
	public Map<String, String> imports = new HashMap<>();
	private final List<String> defaultExtendInterfaces = null;
	private Map<String, Type> typeMap = null;
	public String modelType = null;
	public String discriminator = null;

	// Container for "decls" section that is solely used to define reusable
	// anchors
	@JsonProperty
	private Object decls;

	public void init() {
		typeMap = types.stream().collect(Collectors.toMap(Type::getName, t -> t));
		types.stream().forEach(t -> t.init(this));
	}

	public Collection<Type> getTypes() {
		return types;
	}

	public Map<String, Type> getTypeMap() {
		return typeMap;
	}

	public Type getType(String typeName) {
		return typeMap.get(typeName);
	}

	public static class Type {

		public String name;
		public Map<String, Field> fields = new LinkedHashMap<>();
		public List<String> extendInterfaces = new ArrayList<>();
		public Map<String, Collection<String>> imports = new HashMap<>();
		public boolean noGen = false;
		public String extensionOf;
		@JsonProperty("abstract")
		public boolean abstractType = false;
		public String discriminator = null;
		public String discriminatorValue = null;
		public List<String> enumValues = new ArrayList<>();

		private TypeData typeData;

		public Type() {
		}

		public void init(TypeData typeData) {
			this.typeData = typeData;
			for (Entry<String, Field> field : fields.entrySet()) {
				field.getValue().init(field.getKey(), this);
			}
		}

		public TypeData getTypeData() {
			return typeData;
		}

		public String getName() {
			return name;
		}

	}

	public static class Field {
		public String name;
		public String plural;
		public Structure structure = Structure.scalar;
		public String type;
		public String keyName = "name";
		public String keyPattern;
		public boolean noImpl;
		public String id;
		public boolean boolDefault = false;
		public String parentPath;

		private Type container;

		public void init(String id, Type container) {
			this.id = id;
			this.container = container;
			if (this.name == null) {
				String[] parts = id.split("/");
				String lastPart = parts[parts.length - 1];
				String defaultName = lastPart.substring(0, 1).toUpperCase() + lastPart.substring(1);
				if (this.structure == Structure.scalar) {
					this.name = defaultName;
				} else {
					this.name = defaultName.endsWith("s") ? defaultName.substring(0, defaultName.length() - 1)
							: defaultName;
				}
			}
			if (this.type == null) {
				this.type = getTypeData().getType(name) != null ? name : "String";
			}
		}

		public TypeData getTypeData() {
			return container.getTypeData();
		}

	}

	public static class Method {
		private String name;
		private String type;
		private List<String> argDecls;
		private List<String> code;

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		public List<String> getArgDecls() {
			return argDecls;
		}

		public List<String> getCode() {
			return code;
		}
	}

	public enum Structure {
		scalar, collection, map
	}
}

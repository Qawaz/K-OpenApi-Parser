/*******************************************************************************
 *  Copyright (c) $(date) ModelSolv, Inc. and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *     ModelSolv, Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package com.reprezen.swaggerparser.impl3;

import com.fasterxml.jackson.databind.JsonNode;
import com.reprezen.swaggerparser.impl3.MediaTypeImpl;
import com.reprezen.swaggerparser.impl3.SchemaImpl;
import com.reprezen.swaggerparser.impl3.SwaggerObjectImpl;
import com.reprezen.swaggerparser.jsonoverlay.JsonOverlay;
import com.reprezen.swaggerparser.jsonoverlay.JsonOverlayFactory;
import com.reprezen.swaggerparser.jsonoverlay.coll.MapOverlay;
import com.reprezen.swaggerparser.jsonoverlay.coll.ValListOverlay;
import com.reprezen.swaggerparser.jsonoverlay.coll.ValMapOverlay;
import com.reprezen.swaggerparser.jsonoverlay.std.AnyObjectOverlay;
import com.reprezen.swaggerparser.jsonoverlay.std.BooleanOverlay;
import com.reprezen.swaggerparser.jsonoverlay.std.StringOverlay;
import com.reprezen.swaggerparser.model3.MediaType;
import com.reprezen.swaggerparser.model3.Parameter;
import com.reprezen.swaggerparser.model3.Schema;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Generated;

public class ParameterImpl extends SwaggerObjectImpl implements Parameter {

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public ParameterImpl(String key, JsonNode json, JsonOverlay<?> parent) {
        super(key, json, parent);
    }

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public ParameterImpl(String key, JsonOverlay<?> parent) {
        super(key, parent);
    }

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private StringOverlay name = registerField("name", "name", null, new StringOverlay("name", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private StringOverlay in = registerField("in", "in", null, new StringOverlay("in", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private StringOverlay description = registerField("description", "description", null, new StringOverlay("description", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private BooleanOverlay required = registerField("required", "required", null, new BooleanOverlay("required", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private BooleanOverlay deprecated = registerField("deprecated", "deprecated", null, new BooleanOverlay("deprecated", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private BooleanOverlay allowEmptyValue = registerField("allowEmptyValue", "allowEmptyValue", null, new BooleanOverlay("allowEmptyValue", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private StringOverlay style = registerField("style", "style", null, new StringOverlay("style", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private BooleanOverlay explode = registerField("explode", "explode", null, new BooleanOverlay("explode", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private BooleanOverlay allowReserved = registerField("allowReserved", "allowReserved", null, new BooleanOverlay("allowReserved", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private SchemaImpl schema = registerField("schema", "schema", null, SchemaImpl.factory.create("schema", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private AnyObjectOverlay example = registerField("example", "example", null, new AnyObjectOverlay("example", this));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private ValListOverlay<Object, AnyObjectOverlay> examples = registerField("examples", "examples", null, new ValListOverlay<Object, AnyObjectOverlay>("examples", this, AnyObjectOverlay.factory));;

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private MapOverlay<MediaTypeImpl> contentMediaTypes = registerField("content", "contentMediaTypes", null, new MapOverlay<MediaTypeImpl>("content", this, MediaTypeImpl.factory, null));

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    private ValMapOverlay<Object,AnyObjectOverlay> extensions = registerField("", "extensions", "x-.*", new ValMapOverlay<Object, AnyObjectOverlay>("", this, AnyObjectOverlay.factory, "x-.*"));

    // Name
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public String getName() {
        return name.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setName(String name) {
        this.name.set(name);
    }

    // In
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public String getIn() {
        return in.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setIn(String in) {
        this.in.set(in);
    }

    // Description
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public String getDescription() {
        return description.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setDescription(String description) {
        this.description.set(description);
    }

    // Required
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Boolean getRequired() {
        return required.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public boolean isRequired() {
        return required.get() != null ? required.get() : false;
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setRequired(Boolean required) {
        this.required.set(required);
    }

    // Deprecated
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Boolean getDeprecated() {
        return deprecated.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public boolean isDeprecated() {
        return deprecated.get() != null ? deprecated.get() : false;
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setDeprecated(Boolean deprecated) {
        this.deprecated.set(deprecated);
    }

    // AllowEmptyValue
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Boolean getAllowEmptyValue() {
        return allowEmptyValue.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public boolean isAllowEmptyValue() {
        return allowEmptyValue.get() != null ? allowEmptyValue.get() : false;
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setAllowEmptyValue(Boolean allowEmptyValue) {
        this.allowEmptyValue.set(allowEmptyValue);
    }

    // Style
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public String getStyle() {
        return style.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setStyle(String style) {
        this.style.set(style);
    }

    // Explode
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Boolean getExplode() {
        return explode.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public boolean isExplode() {
        return explode.get() != null ? explode.get() : false;
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setExplode(Boolean explode) {
        this.explode.set(explode);
    }

    // AllowReserved
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Boolean getAllowReserved() {
        return allowReserved.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public boolean isAllowReserved() {
        return allowReserved.get() != null ? allowReserved.get() : false;
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setAllowReserved(Boolean allowReserved) {
        this.allowReserved.set(allowReserved);
    }

    // Schema
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Schema getSchema() {
        return schema;
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setSchema(Schema schema) {
        this.schema.set((SchemaImpl) schema);
    }

    // Example
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Object getExample() {
        return example.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setExample(Object example) {
        this.example.set(example);
    }

    // Example
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Collection<Object> getExamples() {
        return examples.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Object getExample(int index) {
        return examples.get(index);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setExamples(Collection<Object> examples) {
        this.examples.set((Collection<Object>) examples);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setExample(int index, Object example) {
        examples.set(index, example);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void addExample(Object example) {
        examples.add(example);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void removeExample(int index) {
        examples.remove(index);
    }

    // ContentMediaType
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Map<String, ? extends MediaType> getContentMediaTypes() {
        return contentMediaTypes.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public boolean hasContentMediaType(String name) {
        return contentMediaTypes.containsKey(name);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public MediaType getContentMediaType(String name) {
        return contentMediaTypes.get(name);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setContentMediaTypes(Map<String, ? extends MediaType> contentMediaTypes) {
        @SuppressWarnings("unchecked")
            Map<String,MediaTypeImpl> implContentMediaTypes = (Map<String, MediaTypeImpl>) contentMediaTypes;
            this.contentMediaTypes.set(implContentMediaTypes);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setContentMediaType(String name, MediaType contentMediaType) {
        contentMediaTypes.set(name, (MediaTypeImpl) contentMediaType);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void removeContentMediaType(String name) {
        contentMediaTypes.remove(name);
    }

    // Extension
    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Map<String, Object> getExtensions() {
        return extensions.get();
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public boolean hasExtension(String name) {
        return extensions.containsKey(name);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public Object getExtension(String name) {
        return extensions.get(name);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setExtensions(Map<String, Object> extensions) {
        this.extensions.set(extensions);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void setExtension(String name, Object extension) {
        extensions.set(name, extension);
    }

    @Override
    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public void removeExtension(String name) {
        extensions.remove(name);
    }

    @Generated("com.reprezen.swaggerparser.jsonoverlay.gen.CodeGenerator")
    public static JsonOverlayFactory<ParameterImpl> factory = new JsonOverlayFactory<ParameterImpl>() {
    @Override
    public ParameterImpl create(String key, JsonNode json, JsonOverlay<?> parent) {
        return isEmptyRecursive(parent, ParameterImpl.class) ? null : new ParameterImpl(key, json, parent);
    }
};

}

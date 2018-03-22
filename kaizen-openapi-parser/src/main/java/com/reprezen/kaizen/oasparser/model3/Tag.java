package com.reprezen.kaizen.oasparser.model3;

import java.util.Map;

import javax.annotation.Generated;

import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.IModelPart;

public interface Tag extends IJsonOverlay<Tag>, IModelPart<OpenApi3, Tag> {

	// Name
	@Generated("com.reprezen.gen.CodeGenerator")
	String getName();

	@Generated("com.reprezen.gen.CodeGenerator")
	String getName(boolean elaborate);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setName(String name);

	// Description
	@Generated("com.reprezen.gen.CodeGenerator")
	String getDescription();

	@Generated("com.reprezen.gen.CodeGenerator")
	String getDescription(boolean elaborate);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setDescription(String description);

	// ExternalDocs
	@Generated("com.reprezen.gen.CodeGenerator")
	ExternalDocs getExternalDocs();

	@Generated("com.reprezen.gen.CodeGenerator")
	ExternalDocs getExternalDocs(boolean elaborate);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setExternalDocs(ExternalDocs externalDocs);

	// Extension
	@Generated("com.reprezen.gen.CodeGenerator")
	Map<String, Object> getExtensions();

	@Generated("com.reprezen.gen.CodeGenerator")
	Map<String, Object> getExtensions(boolean elaborate);

	@Generated("com.reprezen.gen.CodeGenerator")
	boolean hasExtension(String name);

	@Generated("com.reprezen.gen.CodeGenerator")
	Object getExtension(String name);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setExtensions(Map<String, Object> extensions);

	@Generated("com.reprezen.gen.CodeGenerator")
	void setExtension(String name, Object extension);

	@Generated("com.reprezen.gen.CodeGenerator")
	void removeExtension(String name);
}

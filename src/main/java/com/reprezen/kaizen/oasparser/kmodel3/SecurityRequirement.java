package com.reprezen.kaizen.oasparser.kmodel3;

import com.reprezen.jsonoverlay.IJsonOverlay;
import com.reprezen.jsonoverlay.IModelPart;

import javax.annotation.Generated;
import java.util.Map;

public interface SecurityRequirement extends IJsonOverlay<SecurityRequirement>, IModelPart<OpenApi3, SecurityRequirement> {

    // Requirement
    @Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
    Map<String, SecurityParameter> getRequirements();

    @Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
    Map<String, SecurityParameter> getRequirements(boolean elaborate);

    @Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
    boolean hasRequirements();

    @Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
    boolean hasRequirement(String name);

    @Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
    SecurityParameter getRequirement(String name);

    @Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
    void setRequirements(Map<String, SecurityParameter> requirements);

    @Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
    void setRequirement(String name, SecurityParameter requirement);

    @Generated("com.reprezen.jsonoverlay.gen.CodeGenerator")
    void removeRequirement(String name);
}